package com.ivy.ads.promote.cache;

import android.os.Looper;

import com.ivy.IvySdk;
import com.ivy.task.HandlerTaskFeedbackWrapper;
import com.ivy.task.TaskFeedback;
import com.ivy.util.Logger;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;

public class PromoCreativeImageDownloader {
  private static final String TAG = PromoCreativeImageDownloader.class.getSimpleName();

  private OkHttpClient okHttpClient;

  public PromoCreativeImageDownloader() {
    this.okHttpClient = IvySdk.getOkHttpClient();
  }

  public void downloadDataAsync(String url, String savedFilePath, TaskFeedback<String> feedback) {
    try {
      final TaskFeedback<String> feedbackWrapper;
      if (Looper.myLooper() != null) {
        feedbackWrapper = new HandlerTaskFeedbackWrapper<>(feedback);
      } else {
        feedbackWrapper = feedback;
      }

      Request request = new Request.Builder().url(url).build();
      okHttpClient.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
          Logger.warning(TAG, "Loading file failed: " + url, e);
          if (feedbackWrapper != null) {
            feedbackWrapper.onError(e);
          }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
          Logger.debug(TAG, "File " + url + " downloaded");
          if (response == null) {
            Logger.warning(TAG, " >>> no response ");
            return;
          }

          if (response.code() != 200) {
            Logger.warning(TAG, " >>> Response code: " + response.code());
            if (feedbackWrapper != null) {
              feedbackWrapper.onError(new IOException("httperror_" + response.code()));
            }
            return;
          }

          try {
            ResponseBody body = response.body();
            if (body != null) {
              File tmpFile = new File(savedFilePath + ".tmp");
              if (tmpFile.exists()) {
                tmpFile.delete();
              }
              File file = new File(savedFilePath);
              if (!file.getParentFile().exists()) {
                boolean pfexists = file.getParentFile().mkdirs();
                if (!pfexists) {
                  Logger.warning(TAG, "Not able to create parent files");
                  return;
                }
              }

              try (BufferedSink sink = Okio.buffer(Okio.sink(tmpFile))) {
                sink.writeAll(body.source());
                sink.flush();
                try {
                  response.close();
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
              }

              boolean result = tmpFile.renameTo(file);
              if (result && feedbackWrapper != null) {
                feedbackWrapper.onFinish(savedFilePath);
              }
            } else {
              Logger.error(TAG, " error response");
            }
          } catch(Throwable t) {
            // ignore
            Logger.error(TAG, "download failed", t);
          }
        }
      });
    } catch(Exception ex) {
      Logger.warning(TAG, "exception : " + url, ex);
    }
  }
}
