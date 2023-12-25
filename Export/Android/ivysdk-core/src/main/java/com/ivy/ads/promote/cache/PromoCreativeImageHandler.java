package com.ivy.ads.promote.cache;

import android.os.Looper;

import com.ivy.networks.util.Util;
import com.ivy.task.HandlerTaskFeedbackWrapper;
import com.ivy.task.TaskFeedback;
import com.ivy.util.Logger;

import java.io.File;

public class PromoCreativeImageHandler {
  private static final String TAG = PromoCreativeImageHandler.class.getSimpleName();
  private final String cacheDirPath;
  private final PromoCreativeImageCachePool cachePool;
  private final String url;
  protected PromoCreativeImageCache cache;
  private volatile boolean done;
  private volatile boolean preparing;

  private PromoCreativeImageDownloader downloader;

  public PromoCreativeImageHandler(PromoCreativeImageDownloader downloader, PromoCreativeImageCachePool cachePool, String cacheDirPath, String url) {
    this.downloader = downloader;
    this.cachePool = cachePool;
    this.cacheDirPath = cacheDirPath;
    this.url = url;
  }

  public void prepareAsync(TaskFeedback<String> feedback, boolean force) {
    final TaskFeedback<String> feedbackWrapper;
    boolean cancel = false;
    synchronized (this) {
      if (this.preparing || (!force && this.done)) {
        cancel = true;
        Logger.debug(TAG, String.format("Won't prepare - preparing=%b, done=%b, force=%b - %s", new Object[]{Boolean.valueOf(this.preparing), Boolean.valueOf(this.done), Boolean.valueOf(force), this}));
      } else {
        this.preparing = true;
        this.done = false;
      }
    }

    if (feedback != null) {
      feedback.onStart();
    }

    if (Looper.myLooper() != null) {
      feedbackWrapper = new HandlerTaskFeedbackWrapper<>(feedback);
    } else {
      feedbackWrapper = feedback;
    }

    if (cancel) {
      if (feedback != null) {
        feedback.onCancel();
      }
    }

    try {
      Thread thread = new Thread(() -> prepare(feedbackWrapper));
      thread.start();
    } catch(Throwable t) {
      t.printStackTrace();
    }
  }

  public int getFailedCount() {
    return getCache().getFailedDownloadCount();
  }

  private void prepare(TaskFeedback<String> feedback) {
    Logger.debug(TAG, "Preparing started... '%s'", this);
    if (!tryPrepareFromCache(feedback)) {
      Logger.debug(TAG, "Cache not valid, downloading... '%s'",this);
      startDownloading(feedback);
    }
  }

  private boolean tryPrepareFromCache(TaskFeedback<String> feedback) {
    try {
      boolean exists = getCache().exists();
      if (exists) {
        handleFinish(feedback, getCache().getFilePath(), true);
        return true;
      }
      return false;
    } catch (Exception e) {
      Logger.warning(TAG, "Cannot read data from cache: '%s' - '%s'", getCache().getFilePath(), this, e);
      return false;
    }
  }

  private void startDownloading(final TaskFeedback<String> feedback) {
    this.downloader.downloadDataAsync(url, getCache().getFilePath(), new TaskFeedback<String>() {
      @Override
      public void onStart() {
        handleDownloadStart(feedback);
      }

      @Override
      public void onCancel() {
        handleDownloadCancel(feedback);
      }

      @Override
      public void onFinish(String filePath) {
        handleDownloadFinish(feedback, filePath);
      }

      @Override
      public void onError(Exception e) {
        handleDownloadError(feedback, e);
      }
    });
  }

  private void handleFinish(TaskFeedback<String> feedback, String data, boolean fromCache) {
    setDone(data);
    if (feedback != null) {
      feedback.onFinish(data);
    }
  }

  private void handleDownloadStart(TaskFeedback<String> taskFeedback) {
  }

  private void handleDownloadCancel(TaskFeedback<String> feedback) {
    setDone(null);
    Logger.debug(TAG, "Preparing canceled. Downloading from: '%s' - '%s'", this.url, this);
    if (feedback != null) {
      feedback.onCancel();
    }
  }

  private void handleDownloadFinish(TaskFeedback<String> feedback, String filePath) {
    if (filePath == null || !new File(filePath).exists()) {
      handleDownloadError(feedback, new Exception("Downloaded zero data"));
      return;
    }
    handleFinish(feedback, filePath, false);
  }

  private void handleDownloadError(TaskFeedback<String> feedback, Exception error) {
    setDone(null);
    Logger.warning(TAG, "Preparing error. Downloading from: '%s' - '%s'", this.url, this, error);
    if (feedback != null) {
      feedback.onError(error);
    }
    getCache().incFailedCount();
  }

  public PromoCreativeImageCache getCache() {
    if (this.cache == null) {
      this.cache = this.cachePool.getOrCreate(new File(this.cacheDirPath, Util.SHA1(this.url)).getPath());
    }
    return this.cache;
  }


  public boolean isPreparing() {
    return this.preparing;
  }

  public boolean isDone() {
    return this.done;
  }

  private void setDone(String filePath) {
    synchronized (this) {
      this.preparing = false;
      this.done = true;
    }
  }
}
