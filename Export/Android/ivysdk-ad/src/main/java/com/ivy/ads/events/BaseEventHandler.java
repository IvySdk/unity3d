package com.ivy.ads.events;

import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.networks.tracker.EventTracker;


public abstract class BaseEventHandler {
  private static final String TAG = BaseEventHandler.class.getCanonicalName();

  public static final String ADSFALL = "adsfall";

  protected EventTracker eventLogger;

  public BaseEventHandler(EventTracker eventLogger) {
    this.eventLogger = eventLogger;
  }

  public abstract void fetchCalled(BaseAdapter baseAdapter);

  public abstract void onAdClickCalled(BaseAdapter baseAdapter);

  public abstract void onAdClosedCalled(BaseAdapter baseAdapter, boolean z);

  public abstract void onAdLoadFailCalled(BaseAdapter baseAdapter, String loadStatus);

  public abstract void onAdLoadSuccessCalled(BaseAdapter baseAdapter);

  public abstract void onAdShowFailCalled(BaseAdapter baseAdapter);

  public abstract void onAdShowSuccessCalled(BaseAdapter baseAdapter);

  public abstract void showCalled(BaseAdapter baseAdapter);

  public abstract void timeoutCalled(BaseAdapter baseAdapter);

  public abstract void onAdLoadTimeOut(BaseAdapter adapter);

  protected void logEvent(String eventType, EventParams params, EventTracker eventLogger) {
    if (eventLogger != null) {
      eventLogger.logEvent(eventType, params.getParamsBundle());
    } else {
//      Logger.warning(TAG, "logEvent called but eventLogger not initialized. Doing nothing");
    }
  }

  public EventTracker getEventLogger() {
    return this.eventLogger;
  }
}
