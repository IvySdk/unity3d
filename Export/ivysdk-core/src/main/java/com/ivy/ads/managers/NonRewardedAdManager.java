package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.configuration.InterstitialConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.summary.AdSummaryEventHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NonRewardedAdManager extends FullpageAdManager<InterstitialConfig> {
  public NonRewardedAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProviderList, Handler uiHandler, Handler mHandler, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
    super(activity, configurationParser, adSelector, adProviderList, uiHandler, mHandler, IvyAdType.INTERSTITIAL, eventHandler, adSummaryEventHandler);
  }

  public Class<InterstitialConfig> getManagerConfigClass() {
    return InterstitialConfig.class;
  }

  public List<JSONObject> getGridProviderList() {
    InterstitialConfig config = getManagerConfig();
    if (config != null) {
      return config.fullPageAdProviders;
    }
    return new ArrayList<>();
  }

  public boolean showNativeAd(Activity activity, Map<String, View> map, Map<String, Object> map2) {
    return false;
  }

  public void closeNativeAd() {
  }
}
