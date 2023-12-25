package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.ClipConfig;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.summary.AdSummaryEventHandler;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RewardedAdManager extends FullpageAdManager<ClipConfig> {

  public RewardedAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProvidersRegistry, Handler uiHandler, Handler handler, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
    super(activity, configurationParser, adSelector, adProvidersRegistry, uiHandler, handler, IvyAdType.REWARDED, eventHandler, adSummaryEventHandler);
  }

  public Class<ClipConfig> getManagerConfigClass() {
    return ClipConfig.class;
  }

  public List<JSONObject> getGridProviderList() {
    ClipConfig clipConfig = getManagerConfig();
    if (clipConfig != null) {
      return getManagerConfig().adRewardsProviders;
    }
    return new ArrayList<>();
  }

  public boolean showNativeAd(Activity activity, Map<String, View> map, Map<String, Object> map2) {
    return false;
  }

  public void closeNativeAd() {
  }
}
