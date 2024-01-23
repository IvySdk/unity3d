package com.ivy.ads.selectors;

import android.content.Context;
import android.os.Handler;

import com.ivy.ads.configuration.BannerConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;

public class BannerWaterfallAdSelector extends WaterfallAdSelector<BannerConfig> {
  public BannerWaterfallAdSelector(Handler uiHandler, Context context, BaseEventHandler eventHandler) {
    super(IvyAdType.BANNER, uiHandler, context, eventHandler);
    // cycle banners for banners
    this.setCycleBanners(false);
  }

  protected long getTimeout(BannerConfig config) {
    return config.ad.bannerLoadTimeoutSeconds;
  }
}
