package com.ivy.ads.selectors;

import android.content.Context;
import android.os.Handler;

import com.ivy.ads.configuration.InterstitialConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;

public class NonRewardedWaterfallAdSelector extends WaterfallAdSelector<InterstitialConfig> {
  public NonRewardedWaterfallAdSelector(Handler uiHandler, Context context, BaseEventHandler eventHandler) {
    super(IvyAdType.INTERSTITIAL, uiHandler, context, eventHandler);
  }

  protected long getTimeout(InterstitialConfig config) {
    return config.ad.interstitialLoadTimeoutSeconds;
  }
}
