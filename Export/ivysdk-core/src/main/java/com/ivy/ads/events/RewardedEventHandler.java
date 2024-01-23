package com.ivy.ads.events;

import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.networks.tracker.EventTracker;

import java.util.HashMap;
import java.util.Map;

public class RewardedEventHandler extends BaseEventHandler {
  public RewardedEventHandler(EventTracker eventLogger) {
    super(eventLogger);
  }

  private Map<String, Long> startFetchTimes = new HashMap<>();
  private long getDuration(String placementId){
    if (placementId != null && startFetchTimes.containsKey(placementId)){
      return startFetchTimes.remove(placementId);
    }
    return System.currentTimeMillis();
  }

  public void fetchCalled(BaseAdapter adapter) {
    if (adapter == null) return;
    startFetchTimes.put(adapter.getPlacementId(), System.currentTimeMillis());
    EventParams eventParams = new EventParams();
    eventParams.putAll(adapter.getEventParams());
    eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
    eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
    if (adapter.getMediation() != null) {
      eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
    }
    String displayedTag = adapter.getDisplayedTag();
    if (displayedTag != null) {
      eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
    }
    logEvent(EventID.VIDEO_REQUEST_TIMES, eventParams, this.eventLogger);
  }

  public void showCalled(BaseAdapter adapter) {
  }

  public void timeoutCalled(BaseAdapter adapter) {
  }

  @Override
  public void onAdLoadTimeOut(BaseAdapter adapter) {
    if (adapter == null) return;
    EventParams eventParams = new EventParams();
    eventParams.putAll(adapter.getEventParams());
    eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
    eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
    if (adapter.getMediation() != null) {
      eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
    }
    String displayedTag = adapter.getDisplayedTag();
    if (displayedTag != null) {
      eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
    }
    logEvent(EventID.VIDEO_RESPONSE_TIME_OUT, eventParams, this.eventLogger);
  }

  public void onAdLoadSuccessCalled(BaseAdapter adapter) {
    if (adapter == null) return;
    long duration = System.currentTimeMillis() - getDuration(adapter.getPlacementId());
    EventParams eventParams = new EventParams();
    eventParams.putAll(adapter.getEventParams());
    eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
    eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
    if (adapter.getMediation() != null) {
      eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
    }
    String displayedTag = adapter.getDisplayedTag();
    if (displayedTag != null) {
      eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
    }
    if (duration > 0) {
      eventParams.addParam("duration", duration);
      logEvent(EventID.VIDEO_FILL_DURATION, eventParams, this.eventLogger);
    }
    logEvent(EventID.VIDEO_FILLED_TIMES, eventParams, this.eventLogger);
  }

  public void onAdLoadFailCalled(BaseAdapter adapter, String failReason) {
  }

  public void onAdShowSuccessCalled(BaseAdapter adapter) {
    EventParams eventParams = new EventParams();
    eventParams.putAll(adapter.getEventParams());

    eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
    eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
    if (adapter.getMediation() != null) {
      eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
    }
    String displayedTag = adapter.getDisplayedTag();
    if (displayedTag != null) {
      eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
    }
    logEvent(EventID.VIDEO_SHOWN, eventParams, this.eventLogger);
  }

  public void onAdShowFailCalled(BaseAdapter adapter) {
    EventParams eventParams = new EventParams();
    eventParams.putAll(adapter.getEventParams());
    eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
    eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
    eventParams.addParam(EventParams.PARAM_REASON, adapter.getShowStatus().toString());
    if (adapter.getMediation() != null) {
      eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
    }
    String displayedTag = adapter.getDisplayedTag();
    if (displayedTag != null) {
      eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
    }
    logEvent(EventID.VIDEO_FAILED, eventParams, this.eventLogger);
  }

  public void onAdClickCalled(BaseAdapter adapter) {
  }

  public void onAdClosedCalled(BaseAdapter adapter, boolean isReward) {
    if (isReward) {
      EventParams eventParams = new EventParams();
      eventParams.putAll(adapter.getEventParams());
      eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
      eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
      eventParams.addParam(EventParams.PARAM_SHOWNTIME, adapter.getShowTimeMs());
      if (adapter.getMediation() != null) {
        eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
      }
      String displayedTag = adapter.getDisplayedTag();
      if (displayedTag != null) {
        eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
      }
      logEvent(EventID.VIDEO_COMPLETED, eventParams, this.eventLogger);
    }
  }
}
