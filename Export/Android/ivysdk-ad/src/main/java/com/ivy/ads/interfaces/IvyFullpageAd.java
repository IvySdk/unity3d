package com.ivy.ads.interfaces;

import android.app.Activity;

import androidx.annotation.UiThread;

public interface IvyFullpageAd extends IvyNativeAd {

  void setSoftCallback(IvySoftCallbacks softCallbacks);

  @UiThread
  void show(Activity activity, String tag);
}
