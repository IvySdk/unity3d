package com.ivy.ads.managers;

import android.app.Activity;

public interface Interstitial {
  void fetch(Activity activity);
  void checkAndFetch();
  boolean isAvailable();
  void show(Activity activity);
}
