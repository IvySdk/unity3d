package com.ivy.ads.selectors;

import android.content.Context;
import android.os.Handler;

import com.ivy.ads.configuration.ClipConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;

public class RewardedWaterfallAdSelector extends WaterfallAdSelector<ClipConfig> {
  public RewardedWaterfallAdSelector(Handler uiHandler, Context context, BaseEventHandler eventHandler) {
    super(IvyAdType.REWARDED, uiHandler, context, eventHandler);
  }

  protected long getTimeout(ClipConfig config) {
    return config.ad.rewardedClipLoadTimeoutSeconds;
  }
}
