package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;

import com.ivy.ads.adapters.AdOpenCloseCallback;
import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.BaseConfig;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyAdapterInfo;
import com.ivy.ads.interfaces.IvyNativeAd;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.summary.AdSummaryEventHandler;


import java.util.List;

public abstract class CommonFullpageAdManager<T extends BaseConfig> extends CommonAdManager<T> implements IvyNativeAd, AdOpenCloseCallback {

  protected volatile BaseAdapter mAdapter;
  protected boolean mIsLoading;


  public CommonFullpageAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProvidersRegistry, Handler uiHandler, Handler handler, IvyAdType adType, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
    super(activity, configurationParser, adSelector, adProvidersRegistry, uiHandler, handler, adType, eventHandler, adSummaryEventHandler);
  }

  @Override
  public void fetch(final Activity activity) {
    List<BaseAdapter> adapterProviders = getGridAndRegisteredProviders();
    if (adapterProviders == null || adapterProviders.size() == 0) {
      return;
    }
    if (this.mIsLoading) {
      return;
    }
    this.mIsLoading = true;

    getManagerHandler().post(new Runnable() {
      public void run() {
        if (mAdapter == null) {
          try {
            mAdapter = getAdSelector().selectAd(activity, getManagerConfig(), adapterProviders);
          } catch (Throwable t) {
          }
        }

        getUiHandler().post(new Runnable() {
          public void run() {
            if (getCallback() != null) {
              if (mAdapter == null) {
                getCallback().onAdLoadFail(getAdType());
              } else {
                getCallback().onAdLoadSuccess(new IvyAdInfo(getAdType(), mAdapter));
              }
            }
          }
        });
        CommonFullpageAdManager.this.mIsLoading = false;
      }
    });
  }

  public boolean isLoading() {
    return this.mIsLoading;
  }

  public boolean isLoaded() {
    if (this.mAdapter == null) {
      this.mAdapter = getAdSelector().getReadyAdapter(this.getGridAndRegisteredProviders());
      if (this.mAdapter != null) {
        return true;
      }
    }
    return this.mAdapter != null;
  }

  protected void resetAdapter() {
    getManagerHandler().post(new Runnable() {
      public void run() {
        mAdapter = null;
      }
    });
  }

  public void onAdShown(IvyAdapterInfo adapter) {
    if (getCallback() != null) {
      getCallback().onAdShowSuccess(new IvyAdInfo(getAdType(), adapter));
    }
    if (getInternalCallback() != null) {
      getInternalCallback().onAdShown(adapter);
    }
  }

  public void onAdShownFail(IvyAdType adType) {
    if (getCallback() != null) {
      getCallback().onAdShowFail(adType);
    }
    if (getInternalCallback() != null) {
      getInternalCallback().onAdShownFail(adType);
    }
  }

  public void onAdClosed(IvyAdapterInfo adapter, boolean isReward) {
    if (getCallback() != null) {
      getCallback().onAdClosed(new IvyAdInfo(getAdType(), adapter), isReward);
    }
    if (getInternalCallback() != null) {
      getInternalCallback().onAdClosed(adapter, isReward);
    }
  }

  public void onAdClicked(IvyAdapterInfo adapter) {
    if (getCallback() != null) {
      getCallback().onAdClicked(new IvyAdInfo(getAdType(), adapter));
    }
    if (getInternalCallback() != null) {
      getInternalCallback().onAdClicked(adapter);
    }
  }
}
