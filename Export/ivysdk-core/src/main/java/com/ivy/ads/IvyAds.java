package com.ivy.ads;

import android.app.Activity;

import androidx.annotation.Nullable;

import com.ivy.ads.adapters.AdapterList;
import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyBannerAd;
import com.ivy.ads.interfaces.IvyFullpageAd;
import com.ivy.ads.interfaces.IvyNativeAd;
import com.ivy.ads.interfaces.IvyPromote;
import com.ivy.ads.managers.ManagerRegistry;
import com.ivy.ads.summary.AdSummaryEventFileHandler;
import com.ivy.ads.summary.AdSummaryEventHandler;
import com.ivy.networks.grid.GridManager;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.util.Logger;

import java.util.Map;
import java.util.Set;

public class IvyAds {
  private static final String TAG = IvyAds.class.getCanonicalName();
  private static final AdProvidersRegistry mAdProvidersRegistry = new AdProvidersRegistry();
  private static final AdSummaryEventHandler mAdSummaryEventHandler = new AdSummaryEventFileHandler();

  private static boolean mIsDebugMode;
  private static boolean mIsInitialized;
  private static boolean mIsProvidersRegistered;
  private static boolean mIsTestMode;
  private static ManagerRegistry mManager;

  public static Map<IvyAdType, Map<String, BaseAdapter>> getAdProviders() {
    return mAdProvidersRegistry.getAdProviders();
  }

  private static synchronized void registerProviders(Set<BaseAdapter> registerProviders) {
    synchronized (IvyAds.class) {
      if (mIsProvidersRegistered) {
        Logger.debug(TAG, "Ad providers are already registered. Ignoring this call...");
      } else {
        for (BaseAdapter adapter : registerProviders) {
          mAdProvidersRegistry.getAdProvidersMap(adapter.getAdType()).put(adapter.getName(), adapter);
          Logger.debug(TAG, "Registering provider: %s", adapter);
        }
        mIsProvidersRegistered = true;
      }
    }
  }

  public static synchronized void init(final Activity activity, EventTracker eventLogger, @Nullable GridManager gridManager) {
    registerProviders(AdapterList.getAdapters(activity));

    synchronized (IvyAds.class) {
      if (mIsProvidersRegistered) {
        if (!mIsInitialized) {
          mAdSummaryEventHandler.init(activity, eventLogger);
          mManager = new ManagerRegistry(activity, new ConfigurationParser(activity.getApplicationContext()), mAdProvidersRegistry, eventLogger, mAdSummaryEventHandler);
          mManager.setupAdProviders(null, true);
          mIsInitialized = true;
        }
      } else {
        throw new IllegalStateException("Before calling this method, providers/adapters must be registered by calling 'registerProviders()' method");
      }
    }

  }

  public static void onResume(Activity activity) {
    if (mManager != null) {
      mManager.onResume(activity);
    }
    mAdProvidersRegistry.onResume(activity);
  }

  public static void onDestroy(Activity activity) {
    if (mManager != null) {
      mManager.onDestroy(activity);
    }
    mAdProvidersRegistry.onDestroy(activity);
  }

  public static void onPause(Activity activity) {
    if (mManager != null) {
      mManager.onPause(activity);
    }
    mAdProvidersRegistry.onPause(activity);
    mAdSummaryEventHandler.saveAdSummaryData();
  }

  public static IvyBannerAd getBanners() {
    return (IvyBannerAd) mManager.getAdManager(IvyAdType.BANNER);
  }

  public static IvyPromote getPromote() {
    return (IvyPromote) mManager.getAdManager(IvyAdType.PROMOTE);
  }

  public static IvyFullpageAd getInterstitials() {
    return (IvyFullpageAd) mManager.getAdManager(IvyAdType.INTERSTITIAL);
  }

  public static IvyFullpageAd getRewardedInterstitials() {
    return (IvyFullpageAd)mManager.getAdManager(IvyAdType.REWARDED_INTERSTITIAL);
  }

  public static IvyFullpageAd getRewardedVideos() {
    return (IvyFullpageAd) mManager.getAdManager(IvyAdType.REWARDED);
  }

  public static IvyNativeAd getNativeAds() {
    return (IvyNativeAd) mManager.getAdManager(IvyAdType.NATIVE_AD);
  }

  public static boolean isDebugMode() {
    return mIsDebugMode;
  }

  public static void setDebugMode(boolean isDebugMode) {
    if (mManager != null) {
      mManager.setDebugMode(isDebugMode);
    }
    mIsDebugMode = isDebugMode;
  }

  public static boolean isTestMode() {
    return mIsTestMode;
  }

  public static void setTestMode(boolean isTestMode) {
    mIsTestMode = isTestMode;
    if (mManager != null) {
      mManager.setTestMode(isTestMode);
    }
  }

  public static void setupAdProviders(String adProviderPriority, boolean shouldReset) {
    mManager.setupAdProviders(adProviderPriority, shouldReset);
  }
}
