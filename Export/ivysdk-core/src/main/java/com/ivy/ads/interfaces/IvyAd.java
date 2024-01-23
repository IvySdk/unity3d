package com.ivy.ads.interfaces;

import android.app.Activity;

public interface IvyAd {
  void onPause(Activity activity);

  void onResume(Activity activity);

  void setCallback(IvyAdCallbacks o7AdCallbacks);

}
