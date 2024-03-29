package com.ivy.ads.interfaces;

import android.app.Activity;
import android.widget.FrameLayout;

import androidx.annotation.UiThread;

public interface IvyBannerAd extends IvyAd {
  @UiThread
  void hide();

  @UiThread
  void setBannerPosition(int i, Activity activity);

  @UiThread
  void show(Activity activity, FrameLayout frameLayout);
}
