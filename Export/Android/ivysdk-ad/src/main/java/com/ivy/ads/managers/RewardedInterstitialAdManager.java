package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.configuration.RewardedInterstitialConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.summary.AdSummaryEventHandler;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class RewardedInterstitialAdManager extends FullpageAdManager<RewardedInterstitialConfig> {

  public RewardedInterstitialAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProvidersRegistry, Handler uiHandler, Handler handler, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
    super(activity, configurationParser, adSelector, adProvidersRegistry, uiHandler, handler, IvyAdType.REWARDED_INTERSTITIAL, eventHandler, adSummaryEventHandler);
  }

  public Class<RewardedInterstitialConfig> getManagerConfigClass() {
    return RewardedInterstitialConfig.class;
  }

  public List<JSONObject> getGridProviderList() {
    return getManagerConfig().adRewardsProviders;
  }

  public boolean showNativeAd(Activity activity, Map<String, View> map, Map<String, Object> map2) {
    return false;
  }

  public void closeNativeAd() {
  }

}
