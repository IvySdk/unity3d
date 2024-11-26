package com.ivy.networks.tracker;

import android.app.Activity;
import android.os.Bundle;

import com.appsflyer.MediationNetwork;
import com.ivy.event.EventListener;

import java.util.Currency;
import java.util.Map;

public abstract  class EventTracker implements EventListener {
  public static final String KEY_EVENT_PRIPORITY = "ep_now";
  public static final String PREFS_EVENT_TRACKER_KEY = "prefsEventTrackerKey";
  public static final String PREFS_SESSION_ID_KEY = "prefsSessionIdKey";
  public static final String SHARED_PREF_NEW_SESSION_DEVEL = "prefsEventTrackerDevel";
  private static final String PREFS_SESSION_END_CALLED_KEY = "prefsSessionEndCallKey";
  private static final String PREFS_SESSION_END_KEY = "prefsSessionEndKey";
  private static final String PREFS_SESSION_START_KEY = "prefsSessionStartKey";
  private static final long SESSION_TIMEOUT = 180000;
  private static final String TAG = EventTracker.class.getSimpleName();
  private final Activity activity;

  public EventTracker(Activity activity) {
    this.activity = activity;
  }


  public abstract void logToAppsflyer(String eventId, Bundle bundle);
  public abstract void logToFirebase(String eventId, Bundle bundle);
  public abstract void logEvent(String eventId);

  public abstract void afAdImpressionPing(String monetizationNetwork, MediationNetwork mediationNetwork, String currency, double revenue, Map<String, Object> params);

  public abstract void logPurchase(String contentType, String contentId, String currency, float revenue);

  public abstract void logEvent(String eventId, Bundle bundle);

  public void onEvent(int eventId, Object eventData) {

  }

  public abstract void setUserProperty(String key, String value);

  public abstract void parfkaLog(String eventId, Bundle bundle);

  public abstract void pingROAS(float revenue, String type);

  public abstract void onResume();
  public abstract void onPause();

  public abstract long getEngagementTimestamp();

  public abstract void trackEngagement(long seconds);

  public abstract EventTrackerProvider getEventTracker(String provider);
}
