package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;

import com.ivy.IvyUtils;
import com.ivy.Logger;
import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.configuration.PromoteConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyPromote;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.summary.AdSummaryEventHandler;
import com.ivy.networks.DownloadFeedback;
import com.ivy.task.TaskFeedback;


import org.json.JSONObject;

import java.io.File;
import java.util.List;

public class PromoteAdManager extends CommonAdManager<PromoteConfig> implements IvyPromote {


  private static String cacheDirPath;
//  private static PromoCreativeImageHandlerPool imageHandlerPool;

  public PromoteAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProvidersRegistry, Handler uiHandler, Handler handler, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
    super(activity, configurationParser, adSelector, adProvidersRegistry, uiHandler, handler, IvyAdType.PROMOTE, eventHandler, adSummaryEventHandler);
//    cacheDirPath = createCacheDirPath();
//
//    PromoCreativeImageCachePool promoCreativeImageCachePool = new PromoCreativeImageCachePool();
//
//    imageHandlerPool = new PromoCreativeImageHandlerPool();
//    imageHandlerPool.setCachePool(promoCreativeImageCachePool);
  }

  @Override
  public List<JSONObject> getGridProviderList() {
    return null;
  }

  @Override
  public Class<PromoteConfig> getManagerConfigClass() {
    return PromoteConfig.class;
  }

  private static final TaskFeedback<String> TASK_FEEDBACK_EMPTY = new TaskFeedback<String>() {
    @Override
    public void onCancel() {
    }

    @Override
    public void onError(Exception exception) {
    }

    @Override
    public void onFinish(String s) {
    }

    @Override
    public void onStart() {
    }
  };

  public static String checkCreativeReloaded(String url) {
//    final PromoCreativeImageHandler imageHandler = imageHandlerPool.getOrCreate(url, cacheDirPath);
//    String filePath = imageHandler.getCache().getFilePath();
//    if (new File(filePath).exists()) {
//      return filePath;
//    }
//
//    imageHandler.prepareAsync(TASK_FEEDBACK_EMPTY, false);
//    return filePath;
    return "";
  }

  public static boolean isCreativeDownloaded(String url) {
//    final PromoCreativeImageHandler imageHandler = imageHandlerPool.getOrCreate(url, cacheDirPath);
//    String filePath = imageHandler.getCache().getFilePath();
//    if (new File(filePath).exists()) {
//      return true;
//    }
    return false;
  }

  public static boolean isCreativeAvailable(String url) {
//    final PromoCreativeImageHandler imageHandler = imageHandlerPool.getOrCreate(url, cacheDirPath);
//    String filePath = imageHandler.getCache().getFilePath();
//    if (new File(filePath).exists()) {
//      return true;
//    }
//    imageHandler.prepareAsync(TASK_FEEDBACK_EMPTY, false);
    return false;
  }

  public static String getCreativeCachePath(String url) {
//    if (url == null) {
//      return null;
//    }
//    final PromoCreativeImageHandler imageHandler = imageHandlerPool.getOrCreate(url, cacheDirPath);
//    String filePath = imageHandler.getCache().getFilePath();
//    if (new File(filePath).exists()) {
//        return new File(filePath).getAbsolutePath();
//    }
    return null;
  }

  public boolean getCreativePath(String url, final DownloadFeedback downloadFeedback) {
//    if (url == null) {
//      return false;
//    }
//
//    final PromoCreativeImageHandler imageHandler = imageHandlerPool.getOrCreate(url, cacheDirPath);
//    String filePath = imageHandler.getCache().getFilePath();
//    if (new File(filePath).exists()) {
//      Logger.debug( "file already cached.");
//      if (downloadFeedback != null) {
//        Activity activity = getActivity();
//        if (activity != null && !activity.isFinishing()) {
//          activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//              downloadFeedback.onSuccess(filePath);
//            }
//          });
//        }
//      }
//      return false;
//    }
//
//    if (!IvyUtils.isOnline(mActivity)) {
//      Logger.debug( "Not online, do not try to download.");
//      return false;
//    }
//
//    if (imageHandler.getFailedCount() > 3) {
//      if (downloadFeedback != null) {
//        Activity activity = getActivity();
//        if (activity != null && !activity.isFinishing()) {
//          activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//              downloadFeedback.onFail();
//            }
//          });
//        }
//      }
//      return false;
//    }
//
//    imageHandler.prepareAsync(new TaskFeedback<String>() {
//      @Override
//      public void onCancel() {
//      }
//
//      @Override
//      public void onError(Exception exception) {
////        Logger.error( "onError", exception);
//        if (downloadFeedback != null) {
//          Activity activity = getActivity();
//          if (activity != null && !activity.isFinishing()) {
//            activity.runOnUiThread(new Runnable() {
//              @Override
//              public void run() {
//                downloadFeedback.onFail();
//              }
//            });
//          }
//        }
//      }
//
//      @Override
//      public void onFinish(String result) {
//        if (downloadFeedback != null) {
//          String filePath = imageHandler.getCache().getFilePath();
//
//          Activity activity = getActivity();
//          if (activity != null && !activity.isFinishing()) {
//            activity.runOnUiThread(new Runnable() {
//              @Override
//              public void run() {
//                downloadFeedback.onSuccess(filePath);
//              }
//            });
//          }
//        }
//      }
//
//      @Override
//      public void onStart() {
//      }
//    }, false);
//    return true;
    return false;
  }

  private String createCacheDirPath() {
    return new File(getActivity().getFilesDir().getPath(), "promocreatives").getPath();
  }

  /**
   * 初始化promote环境
   * 1. 下载引用的delicious配置资源
   * 2. 其他资源在show的时候显示
   */
  @Override
  public void setupAdProviders() {
    Logger.debug( "setupAdProviders");
  }

  @Override
  public void showPromoteAd(String tag) {
//    Full.show(this, tag, getManagerConfig());
  }

  @Override
  public void showDeliciousIcon(final int x, final int y, final int width, final int height, String promotepackage) {
  }

  @Override
  public void closeDeliciousIconAd() {

  }

  @Override
  public void closeDeliciousBanner() {
  }

  @Override
  public void showDeliciousBanner(final int x, final int y, final int w, final int h, String defaultpackage) {

  }

  @Override
  @Deprecated
  public void showDeliciousAd() {

  }


  @Override
  public void onQuit() {
//    final PromoteConfig config = getManagerConfig();
//    getActivity().runOnUiThread(new Runnable() {
//      @Override
//      public void run() {
//        FullAdActivity.launch(getActivity(), "exit", config);
//      }
//    });
  }

  /**
   * 打开游戏墙Activity
   */
  @Override
  public void moreGame() {
//    try {
//      getActivity().runOnUiThread(new Runnable() {
//        @Override
//        public void run() {
//          GameActivity.launch(getActivity(), getManagerConfig());
//        }
//      });
//    } catch(Throwable t) {
//      t.printStackTrace();
//    }
  }
}
