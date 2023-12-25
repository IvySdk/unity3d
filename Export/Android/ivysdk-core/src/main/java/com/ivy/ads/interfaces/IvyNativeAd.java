package com.ivy.ads.interfaces;

import android.app.Activity;
import android.view.View;

import androidx.annotation.UiThread;

import java.util.Map;

public interface IvyNativeAd extends IvyAd {
  @UiThread
  void closeNativeAd();

  @UiThread
  void fetch(Activity activity);

  @UiThread
  boolean isLoaded();

  @UiThread
  boolean isLoading();

  @UiThread
  boolean showNativeAd(Activity activity, Map<String, View> map, Map<String, Object> map2);
}
