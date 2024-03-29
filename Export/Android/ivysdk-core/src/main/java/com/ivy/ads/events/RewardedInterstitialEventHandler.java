package com.ivy.ads.events;

import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.networks.tracker.EventTracker;

public class RewardedInterstitialEventHandler extends BaseEventHandler {
  public RewardedInterstitialEventHandler(EventTracker eventLogger) {
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
//    EventParams eventParams = new EventParams();
//    eventParams.putAll(adapter.getEventParams());
//    eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
//    eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
//    if (adapter.getMediation() != null) {
//      eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
//    }
//    String displayedTag = adapter.getDisplayedTag();
//    if (displayedTag != null) {
//      eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
//    }
//    logEvent(EventID.REWARD_INTERSTITIAL_SHOWN, eventParams, this.eventLogger);
  }

  public void onAdShowFailCalled(BaseAdapter adapter) {
//    EventParams eventParams = new EventParams();
//    eventParams.putAll(adapter.getEventParams());
//    eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
//    eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
//    eventParams.addParam(EventParams.PARAM_REASON, adapter.getShowStatus().toString());
//    if (adapter.getMediation() != null) {
//      eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
//    }
//    String displayedTag = adapter.getDisplayedTag();
//    if (displayedTag != null) {
//      eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
//    }
//    logEvent(EventID.REWARD_INTERSTITIAL_FAILED, eventParams, this.eventLogger);
  }

  public void onAdClickCalled(BaseAdapter adapter) {
  }

  public void onAdClosedCalled(BaseAdapter adapter, boolean isReward) {
//    if (isReward) {
//      EventParams eventParams = new EventParams();
//      eventParams.putAll(adapter.getEventParams());
//      eventParams.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
//      eventParams.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
//      eventParams.addParam(EventParams.PARAM_SHOWNTIME, adapter.getShowTimeMs());
//    if (adapter.getMediation() != null) {
//      eventParams.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
//    }
//      String displayedTag = adapter.getDisplayedTag();
//      if (displayedTag != null) {
//        eventParams.addParam(EventParams.PARAM_LABEL, displayedTag);
//      }
//      logEvent(EventID.REWARD_INTERSTITIAL_COMPLETED, eventParams, this.eventLogger);
//    }
  }
}
