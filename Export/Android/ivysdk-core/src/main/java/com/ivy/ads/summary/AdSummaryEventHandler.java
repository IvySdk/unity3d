package com.ivy.ads.summary;

import android.app.Activity;

import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.networks.tracker.EventTracker;

public interface AdSummaryEventHandler {
  void init(Activity activity, EventTracker eventTracker);

  void onImpression(IvyAdType adType, String str, float revenue);

  void onAdPaid(IvyAdType adType, String str, float revenue);

  void onLoad(IvyAdType adType, String str);

  void onRequest(IvyAdType adType, String str);

  void onAction(IvyAdType adType, String str);

  void saveAdSummaryData();

  long[] getSummaryData(IvyAdType adType, String provider);
}
