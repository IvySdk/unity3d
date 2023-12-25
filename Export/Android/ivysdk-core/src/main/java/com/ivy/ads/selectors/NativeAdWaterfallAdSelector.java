package com.ivy.ads.selectors;

import android.content.Context;
import android.os.Handler;

import com.ivy.ads.configuration.NativeAdsConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;

public class NativeAdWaterfallAdSelector extends WaterfallAdSelector<NativeAdsConfig> {
  public NativeAdWaterfallAdSelector(Handler uiHandler, Context context, BaseEventHandler eventHandler) {
    super(IvyAdType.NATIVE_AD, uiHandler, context, eventHandler);
  }

  protected long getTimeout(NativeAdsConfig config) {
    return config.ad.gameWallLoadTimeoutSeconds;
  }
}
