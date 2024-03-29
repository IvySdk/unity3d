package com.ivy.ads.selectors;

import android.content.Context;
import android.os.Handler;

import com.ivy.ads.configuration.RewardedInterstitialConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;

public class RewardedInterstitialWaterfallAdSelector extends WaterfallAdSelector<RewardedInterstitialConfig> {
  public RewardedInterstitialWaterfallAdSelector(Handler uiHandler, Context context, BaseEventHandler eventHandler) {
    super(IvyAdType.REWARDED_INTERSTITIAL, uiHandler, context, eventHandler);
  }

  protected long getTimeout(RewardedInterstitialConfig config) {
    return 60L;
  }
}
