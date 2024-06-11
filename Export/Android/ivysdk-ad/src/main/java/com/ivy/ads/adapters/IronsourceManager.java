package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.util.Log;
//
//import com.ironsource.mediationsdk.IronSource;
//import com.ironsource.mediationsdk.logger.IronSourceError;
//import com.ironsource.mediationsdk.logger.IronSourceLogger;
//import com.ironsource.mediationsdk.logger.LogListener;
//import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
//import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
//import com.ivy.ads.interfaces.Adapter;
//import com.ivy.util.Logger;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class IronsourceManager {
//  private static final String TAG = "Ironsource";
//
//  private static Map<String, ISDemandOnlyInterstitialListener> interstitialMap = new HashMap<>();
//  private static Map<String, ISDemandOnlyRewardedVideoListener> rewardedVideoMap = new HashMap<>();
//
//  private static boolean mInitialized = false;
//
//  private static final ISDemandOnlyInterstitialListener iSDemandOnlyInterstitialListener = new ISDemandOnlyInterstitialListener() {
//    @Override
//    public void onInterstitialAdReady(String instanceId) {
//      if (interstitialMap.containsKey(instanceId)) {
//        interstitialMap.get(instanceId).onInterstitialAdReady(instanceId);
//      } else {
//        Logger.debug(TAG, instanceId + " is ready, but no listener");
//      }
//    }
//
//    @Override
//    public void onInterstitialAdLoadFailed(String instanceId, IronSourceError ironSourceError) {
//      if (interstitialMap.containsKey(instanceId)) {
//        interstitialMap.get(instanceId).onInterstitialAdLoadFailed(instanceId, ironSourceError);
//      } else {
//        Logger.debug(TAG, instanceId + " load failed, but no listener");
//      }
//    }
//
//    @Override
//    public void onInterstitialAdOpened(String instanceId) {
//      if (interstitialMap.containsKey(instanceId)) {
//        interstitialMap.get(instanceId).onInterstitialAdOpened(instanceId);
//      }
//    }
//
//    @Override
//    public void onInterstitialAdClosed(String instanceId) {
//      if (interstitialMap.containsKey(instanceId)) {
//        interstitialMap.get(instanceId).onInterstitialAdClosed(instanceId);
//      }
//    }
//
//    @Override
//    public void onInterstitialAdShowFailed(String instanceId, IronSourceError ironSourceError) {
//      if (interstitialMap.containsKey(instanceId)) {
//        interstitialMap.get(instanceId).onInterstitialAdShowFailed(instanceId, ironSourceError);
//      }
//    }
//
//    @Override
//    public void onInterstitialAdClicked(String instanceId) {
//      if (interstitialMap.containsKey(instanceId)) {
//        interstitialMap.get(instanceId).onInterstitialAdClicked(instanceId);
//      }
//    }
//  };
//
//  private static final ISDemandOnlyRewardedVideoListener iSDemandOnlyRewardedVideoListener = new ISDemandOnlyRewardedVideoListener() {
//    @Override
//    public void onRewardedVideoAdLoadSuccess(String instanceId) {
//      if (rewardedVideoMap.containsKey(instanceId)) {
//        rewardedVideoMap.get(instanceId).onRewardedVideoAdLoadSuccess(instanceId);
//      } else {
//        Logger.debug(TAG, instanceId + " onRewardedVideoAdLoadSuccess, but no listener");
//      }
//    }
//
//    @Override
//    public void onRewardedVideoAdLoadFailed(String instanceId, IronSourceError ironSourceError) {
//      if (rewardedVideoMap.containsKey(instanceId)) {
//        rewardedVideoMap.get(instanceId).onRewardedVideoAdLoadFailed(instanceId, ironSourceError);
//      } else {
//        Logger.debug(TAG, instanceId + " onRewardedVideoAdLoadFailed, but no listener");
//      }
//    }
//
//    @Override
//    public void onRewardedVideoAdOpened(String instanceId) {
//      if (rewardedVideoMap.containsKey(instanceId)) {
//        rewardedVideoMap.get(instanceId).onRewardedVideoAdOpened(instanceId);
//      }
//    }
//
//    @Override
//    public void onRewardedVideoAdClosed(String instanceId) {
//      if (rewardedVideoMap.containsKey(instanceId)) {
//        rewardedVideoMap.get(instanceId).onRewardedVideoAdClosed(instanceId);
//      }
//    }
//
//    @Override
//    public void onRewardedVideoAdShowFailed(String instanceId, IronSourceError ironSourceError) {
//      if (rewardedVideoMap.containsKey(instanceId)) {
//        rewardedVideoMap.get(instanceId).onRewardedVideoAdShowFailed(instanceId, ironSourceError);
//      }
//    }
//
//    @Override
//    public void onRewardedVideoAdClicked(String instanceId) {
//      if (rewardedVideoMap.containsKey(instanceId)) {
//        rewardedVideoMap.get(instanceId).onRewardedVideoAdClicked(instanceId);
//      }
//    }
//
//    @Override
//    public void onRewardedVideoAdRewarded(String instanceId) {
//      if (rewardedVideoMap.containsKey(instanceId)) {
//        rewardedVideoMap.get(instanceId).onRewardedVideoAdRewarded(instanceId);
//      }
//    }
//  };
//
//  public static synchronized void init(Adapter adapter, Activity activity, String appKey, IronSource.AD_UNIT adUnit) {
//    synchronized (IronsourceManager.class) {
//      IronSource.initISDemandOnly(activity.getApplicationContext(), appKey, adUnit);
//      mInitialized = true;
//      if (adapter.isDebugMode()) {
//        IronSource.setLogListener((ironSourceTag, message, logLevel) -> Logger.debug(TAG, "Ironsource initialized: " + message));
//      }
//    }
//
//    if (IronSource.AD_UNIT.INTERSTITIAL.equals(adUnit)) {
//      IronSource.setISDemandOnlyInterstitialListener(iSDemandOnlyInterstitialListener);
//    }
//
//    if (IronSource.AD_UNIT.REWARDED_VIDEO.equals(adUnit)) {
//      IronSource.setISDemandOnlyRewardedVideoListener(iSDemandOnlyRewardedVideoListener);
//    }
//  }
//
//  public static void setInterstitial(String instanceId, ISDemandOnlyInterstitialListener listener) {
//    interstitialMap.put(instanceId, listener);
//  }
//
//  public static void setRewardedVideoMap(String instanceId, ISDemandOnlyRewardedVideoListener listener) {
//    rewardedVideoMap.put(instanceId, listener);
//  }
//
//  public static void onResume(Activity activity) {
//    if (mInitialized) {
//      IronSource.onResume(activity);
//    }
//  }
//
//  public static void onPause(Activity activity) {
//    if (mInitialized) {
//      IronSource.onPause(activity);
//    }
//  }
//}
