package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;

import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.BaseConfig;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyFullpageAd;
import com.ivy.ads.interfaces.IvySoftCallbacks;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.summary.AdSummaryEventHandler;


public abstract class FullpageAdManager<T extends BaseConfig> extends CommonFullpageAdManager<T> implements IvyFullpageAd {

  private IvySoftCallbacks mSoftCallbacks;

  public FullpageAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProvidersRegistry, Handler uiHandler, Handler handler, IvyAdType adType, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
    super(activity, configurationParser, adSelector, adProvidersRegistry, uiHandler, handler, adType, eventHandler, adSummaryEventHandler);
  }

  public void setSoftCallback(IvySoftCallbacks callback) {
    this.mSoftCallbacks = callback;
  }

  public void softPause(IvyAdInfo o7AdInfo) {
    if (this.mSoftCallbacks != null) {
      this.mSoftCallbacks.softPause(o7AdInfo);
    }
  }

  public void softResume(IvyAdInfo o7AdInfo) {
    if (this.mSoftCallbacks != null) {
      this.mSoftCallbacks.softResume(o7AdInfo);
    }
  }

  public void show(Activity activity, String tag) {
    if (isLoaded()) {
      if (tag != null) {
        this.mAdapter.updateDisplayedTag(tag);
      } else {
        this.mAdapter.resetDisplayTag();
      }
      this.mAdapter.show(activity, this);
    } else {
      onAdShownFail(getAdType());
    }

    if (!this.mIsLoading && isLoaded()) {
      resetAdapter();
    }
  }
}
