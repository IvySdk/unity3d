package com.ivy.ads.events;

import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.networks.tracker.EventTracker;

public class NativeEventHandler extends BaseEventHandler {
  public NativeEventHandler(EventTracker eventLogger) {
    super(eventLogger);
  }

  public void fetchCalled(BaseAdapter adapter) {
  }

  public void showCalled(BaseAdapter adapter) {
  }

  public void timeoutCalled(BaseAdapter adapter) {
  }

  @Override
  public void onAdLoadTimeOut(BaseAdapter adapter) {

  }

  public void onAdLoadSuccessCalled(BaseAdapter adapter) {
  }

  public void onAdLoadFailCalled(BaseAdapter adapter, String failReason) {
  }

  public void onAdShowSuccessCalled(BaseAdapter adapter) {
//    String eventName = adapter != null && ADSFALL.equals(adapter.getName()) ? EventID.NATIVE_ADSFALL_SHOWN : EventID.NATIVE_SHOWN;
//
//    logEvent(eventName,  new EventParams().putAll(adapter.getEventParams()).addParam(EventParams.PARAM_PROVIDER, adapter.getName()).addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId()), this.eventLogger);
  }

  public void onAdShowFailCalled(BaseAdapter adapter) {
//    String eventName = adapter != null && ADSFALL.equals(adapter.getName()) ? EventID.NATIVE_ADSFALL_SHOW_FAILED : EventID.NATIVE_SHOW_FAILED;
//
//    logEvent(eventName, new EventParams().addParam(EventParams.PARAM_PROVIDER, adapter.getName()), this.eventLogger);
  }

  public void onAdClickCalled(BaseAdapter adapter) {
//    String eventName = adapter != null && ADSFALL.equals(adapter.getName()) ? EventID.NATIVE_ADSFALL_CLICKED : EventID.NATIVE_CLICKED;
//
//    logEvent(eventName,  new EventParams().putAll(adapter.getEventParams()).addParam(EventParams.PARAM_PROVIDER, adapter.getName()).addParam(EventParams.PARAM_SHOWNTIME, adapter.getShowTimeMs()), this.eventLogger);
  }

  public void onAdClosedCalled(BaseAdapter adapter, boolean isReward) {
  }
}
