package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.ivy.ads.interfaces.IvyAdType;

import java.util.Map;

public abstract class NativeAdapter<T extends BaseAdapter.GridParams> extends BaseAdapter<T> {
  public static final String NativeAdsAdChoicesView = "NativeAdsAdChoicesView";
  public static final String NativeAdsAdFlagView = "NativeAdsAdFlagView";
  public static final String NativeAdsCallToActionButton = "NativeAdsCtaButton";
  public static final String NativeAdsContainerView = "NativeAdsContainerView";
  public static final String NativeAdsDescriptionLabel = "NativeAdsDescriptionLabel";
  public static final String NativeAdsIconView = "NativeAdsIconView";
  public static final String NativeAdsMediaView = "NativeAdsMediaView";
  public static final String NativeAdsTitleLabel = "NativeAdsTitleLabel";

  public NativeAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  public abstract void closeNativeAd();

  public abstract boolean showNativeAd(Activity activity, Map<String, View> map);

  public boolean showNativeAd(Activity activity, Map<String, View> adViews, AdOpenCloseCallback adOpenCloseCallback) {
    this.mAdOpenCloseCallback = adOpenCloseCallback;
    this.mShowStartTime = System.currentTimeMillis();
    return showNativeAd(activity, adViews);
  }

  @Override
  public String getMediation() {
    return null;
  }
}
