package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;
import android.view.View;

import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.ads.adapters.NativeAdapter;
import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.configuration.NativeAdsConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.selectors.AdSelectorCallback;
import com.ivy.ads.summary.AdSummaryEventHandler;
import com.ivy.networks.grid.GridManager;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class NativeAdManager extends CommonFullpageAdManager<NativeAdsConfig> {

  public NativeAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProvidersRegistry, Handler uiHandler, Handler handler, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
    super(activity, configurationParser, adSelector, adProvidersRegistry, uiHandler, handler, IvyAdType.NATIVE_AD, eventHandler, adSummaryEventHandler);
  }

  public Class<NativeAdsConfig> getManagerConfigClass() {
    return NativeAdsConfig.class;
  }

  public List<JSONObject> getGridProviderList() {
    return  getManagerConfig().gameWallNativeAdProviderList;
  }

  public boolean showNativeAd(final Activity activity, final Map<String, View> adViews, final Map<String, Object> map) {
//    if (isLoaded()) {
//      return ((NativeAdapter) this.mAdapter).showNativeAd(activity, adViews, this);
//    } else {
//      showAdsFallAdapter(new AdSelectorCallback() {
//        @Override
//        public void adLoadFailed(BaseAdapter adapter) {
//        }
//
//        @Override
//        public void adLoadSuccess(BaseAdapter adapter) {
//          AdsfallNativeAdapter adsAdapter = (AdsfallNativeAdapter)adapter;
//          ((AdsfallNativeAdapter) adapter).showNativeAd(activity, adViews, NativeAdManager.this);
//        }
//      });
//    }
//
//    onAdShownFail(getAdType());
//    if (this.mIsLoading || !isLoaded()) {
//      return false;
//    }
//    resetAdapter();
    return false;
  }

  public void closeNativeAd() {
    if (this.mAdapter != null) {
      ((NativeAdapter) this.mAdapter).closeNativeAd();
      resetAdapter();
    }

    // prefetch
    fetch(getActivity());
//    final AdsfallNativeAdapter adsfallNativeAdapter = (AdsfallNativeAdapter) getAdProvidersMap().get("adsfall");
//    onAdClosed(adsfallNativeAdapter, false);
  }

  public void softPause(IvyAdInfo o7AdInfo) {
  }

  public void softResume(IvyAdInfo o7AdInfo) {
  }

//  protected void showAdsFallAdapter(AdSelectorCallback callback) {
//    boolean preFillNative = GridManager.getGridData().optBoolean("preFillNative", true);
//    if (!preFillNative) {
//      return;
//    }
//
//    final AdsfallNativeAdapter adsfallNativeAdapter = (AdsfallNativeAdapter) getAdProvidersMap().get("adsfall");
//    if (adsfallNativeAdapter != null) {
//      adsfallNativeAdapter.setPromoteConfig(getPromiteConfig());
//      adsfallNativeAdapter.resetOperationCount();
//      adsfallNativeAdapter.fetch(getActivity(), callback);
//    }
//  }
}
