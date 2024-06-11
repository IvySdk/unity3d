package com.ivy.ads.summary;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.networks.tracker.EventTracker;



public class AdSummaryEventFileHandler implements AdSummaryEventHandler {
  private static final String TAG = "Summary";

  public static final String KEY_AD_PAID = "user_ad_paid";

  private static boolean isInitialized = false;

  private EventTracker eventTracker;


  private SharedPreferences sharedPreferences;

  @Override
  public void init(Activity context, EventTracker eventTracker) {
    if (isInitialized) {
      return;
    }
    this.eventTracker = eventTracker;

    this.sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    isInitialized = true;
  }

  @Override
  public long[] getSummaryData(IvyAdType adType, String provider) {
    return null;
  }

  @Override
  public void onRequest(IvyAdType adType, String partner) {
  }

  @Override
  public void onLoad(IvyAdType adType, String partner) {
  }

  @Override
  public void onAdPaid(IvyAdType adType, String str, float revenue) {
    if (this.sharedPreferences != null && revenue > 0) {
      double totalAdPaid = this.sharedPreferences.getFloat(KEY_AD_PAID, 0) + revenue;
      this.sharedPreferences.edit().putFloat(KEY_AD_PAID, (float) totalAdPaid).apply();

      if (eventTracker != null) {
        try {
          eventTracker.pingROAS(revenue, "ad");
        } catch(Throwable t) {
          // ignore
        }
      }
    }
  }

  @Override
  public void onImpression(IvyAdType adType, String partner, float revenue) {
    if (this.sharedPreferences != null && revenue > 0) {
      double totalAdPaid = this.sharedPreferences.getFloat(KEY_AD_PAID, 0) + revenue;
      this.sharedPreferences.edit().putFloat(KEY_AD_PAID, (float) totalAdPaid).apply();

      try {
        if (eventTracker != null) {
          eventTracker.pingROAS(revenue, "ad");
        }
      } catch(Throwable t) {
      }
    }
  }

  @Override
  public void onAction(IvyAdType adType, String partner) {
  }

  public void saveAdSummaryData() {

  }
}
