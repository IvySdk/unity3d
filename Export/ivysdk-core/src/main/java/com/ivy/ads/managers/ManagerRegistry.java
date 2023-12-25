package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;

import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.events.BannerEventHandler;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.events.NativeEventHandler;
import com.ivy.ads.events.NonRewardedEventHandler;
import com.ivy.ads.events.RewardedEventHandler;
import com.ivy.ads.events.RewardedInterstitialEventHandler;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.selectors.BannerWaterfallAdSelector;
import com.ivy.ads.selectors.NativeAdWaterfallAdSelector;
import com.ivy.ads.selectors.NonRewardedWaterfallAdSelector;
import com.ivy.ads.selectors.RewardedInterstitialWaterfallAdSelector;
import com.ivy.ads.selectors.RewardedWaterfallAdSelector;
import com.ivy.ads.summary.AdSummaryEventHandler;
import com.ivy.ads.utils.HandlerFactory;
import com.ivy.networks.grid.GridManager;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.util.Logger;

import java.util.HashMap;
import java.util.Map;

public class ManagerRegistry implements GridManager.AdProvidersCallback {
  private static final String TAG = "ManagerRegistry";
  private final Map<IvyAdType, CommonAdManager> mManagerRegistry = new HashMap();

  public ManagerRegistry(Activity activity, ConfigurationParser configurationParser, AdProvidersRegistry adProvidersMap, EventTracker eventLogger, AdSummaryEventHandler adSummaryEventHandler) {
    Handler uiHandler = HandlerFactory.createUiHandler();

    BaseEventHandler nonRewardedEventHandler = new NonRewardedEventHandler(eventLogger);
    BaseEventHandler rewardedEventHandler = new RewardedEventHandler(eventLogger);
    BaseEventHandler bannerEventHandler = new BannerEventHandler(eventLogger);
    BaseEventHandler nativeEventHandler = new NativeEventHandler(eventLogger);
    BaseEventHandler rewardInterstitialEventHandler = new RewardedInterstitialEventHandler(eventLogger);

    AdSelector rewardedWaterfallAdSelector = new RewardedWaterfallAdSelector(uiHandler, activity, rewardedEventHandler);
    AdSelector nonRewardedAdSelector = new NonRewardedWaterfallAdSelector(uiHandler, activity, nonRewardedEventHandler);
    AdSelector bannerWaterfallAdSelector = new BannerWaterfallAdSelector(uiHandler, activity, rewardedEventHandler);
    AdSelector nativeAdWaterfallAdSelector = new NativeAdWaterfallAdSelector(uiHandler, activity, rewardedEventHandler);
    AdSelector rewardInterstitialAdSelector = new RewardedInterstitialWaterfallAdSelector(uiHandler, activity, rewardInterstitialEventHandler);

    CommonAdManager nonRewardedAds = new NonRewardedAdManager(activity, configurationParser, nonRewardedAdSelector, adProvidersMap, uiHandler, HandlerFactory.createHandler(NonRewardedAdManager.class), nonRewardedEventHandler, adSummaryEventHandler);
    CommonAdManager rewardedAds = new RewardedAdManager(activity, configurationParser, rewardedWaterfallAdSelector, adProvidersMap, uiHandler, HandlerFactory.createHandler(RewardedAdManager.class), rewardedEventHandler, adSummaryEventHandler);
    CommonAdManager bannerAds = new BannerAdManager(activity, configurationParser, bannerWaterfallAdSelector, adProvidersMap, uiHandler, HandlerFactory.createHandler(BannerAdManager.class), bannerEventHandler, adSummaryEventHandler);
    CommonAdManager nativeAds = new NativeAdManager(activity, configurationParser, nativeAdWaterfallAdSelector, adProvidersMap, uiHandler, HandlerFactory.createHandler(NativeAdManager.class), nativeEventHandler, adSummaryEventHandler);
    CommonAdManager promoteAdManager = new PromoteAdManager(activity, configurationParser, null, adProvidersMap, uiHandler, HandlerFactory.createHandler(PromoteAdManager.class), null, adSummaryEventHandler);

    CommonAdManager rewardedInterstitialAds = new RewardedInterstitialAdManager(activity, configurationParser, rewardInterstitialAdSelector, adProvidersMap, uiHandler, HandlerFactory.createHandler(RewardedInterstitialAdManager.class), rewardInterstitialEventHandler, adSummaryEventHandler);


    this.mManagerRegistry.put(IvyAdType.BANNER, bannerAds);
    this.mManagerRegistry.put(IvyAdType.INTERSTITIAL, nonRewardedAds);
    this.mManagerRegistry.put(IvyAdType.REWARDED, rewardedAds);
    this.mManagerRegistry.put(IvyAdType.NATIVE_AD, nativeAds);
    this.mManagerRegistry.put(IvyAdType.PROMOTE, promoteAdManager);
    this.mManagerRegistry.put(IvyAdType.REWARDED_INTERSTITIAL, rewardedInterstitialAds);
  }

  public void onResume(Activity activity) {
    for (CommonAdManager adManager : this.mManagerRegistry.values()) {
      adManager.onResume(activity);
    }
  }

  public void onDestroy(Activity activity) {

  }

  public void onPause(Activity activity) {
    for (CommonAdManager adManager : this.mManagerRegistry.values()) {
      adManager.onPause(activity);
    }
  }

  public void setDebugMode(boolean isDebugMode) {
    for (CommonAdManager adManager : this.mManagerRegistry.values()) {
      adManager.setDebugMode(isDebugMode);
    }
  }

  public void setTestMode(boolean isTestMode) {
    for (CommonAdManager adManager : this.mManagerRegistry.values()) {
      adManager.setTestMode(isTestMode);
    }
  }

  public CommonAdManager getAdManager(IvyAdType type) {
    return this.mManagerRegistry.get(type);
  }

  public void setupAdProviders(String adProviderPriority, boolean shouldReset) {
    Logger.debug(TAG, "Grid called setupAdProviders() callback");
    for (CommonAdManager adManager : this.mManagerRegistry.values()) {
      adManager.setupAdProviders();
    }
  }
}
