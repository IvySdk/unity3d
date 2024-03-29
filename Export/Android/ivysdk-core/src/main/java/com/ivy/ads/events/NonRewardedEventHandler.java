package com.ivy.ads.events;

import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.networks.grid.GridManager;
import com.ivy.networks.tracker.EventTracker;

import java.util.HashMap;
import java.util.Map;

public class NonRewardedEventHandler extends BaseEventHandler {
    public NonRewardedEventHandler(EventTracker eventLogger) {
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
        if (adapter.getPlacementId() == null) return;

        startFetchTimes.put(adapter.getPlacementId(), System.currentTimeMillis());
        EventParams params = new EventParams();
        params.putAll(adapter.getEventParams());
        params.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
        params.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
        if (adapter.getMediation() != null) {
            params.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
        }
        String displayedTag = adapter.getDisplayedTag();
        if (displayedTag != null) {
            params.addParam(EventParams.PARAM_LABEL, displayedTag);
        }
        logEvent(EventID.INTERSTITIAL_REQUEST_TIMES, params, this.eventLogger);
    }

    public void showCalled(BaseAdapter adapter) {
    }

    public void timeoutCalled(BaseAdapter adapter) {
    }

    @Override
    public void onAdLoadTimeOut(BaseAdapter adapter) {
        if (adapter == null) return;
        if (adapter.getPlacementId() == null) return;
        EventParams params = new EventParams();
        params.putAll(adapter.getEventParams());
        params.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
        params.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
        if (adapter.getMediation() != null) {
            params.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
        }
        String displayedTag = adapter.getDisplayedTag();
        if (displayedTag != null) {
            params.addParam(EventParams.PARAM_LABEL, displayedTag);
        }
        logEvent(EventID.INTERSTITIAL_RESPONSE_TIME_OUT, params, this.eventLogger);
    }

    public void onAdLoadSuccessCalled(BaseAdapter adapter) {
        if (adapter != null && ADSFALL.equals(adapter.getName())){
            return;
        }
        if (adapter.getPlacementId() == null) return;
        long duration = System.currentTimeMillis() - getDuration(adapter.getPlacementId());
        EventParams params = new EventParams();
        params.putAll(adapter.getEventParams());
        params.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
        params.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
        if (adapter.getMediation() != null) {
            params.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
        }
        String displayedTag = adapter.getDisplayedTag();
        if (displayedTag != null) {
            params.addParam(EventParams.PARAM_LABEL, displayedTag);
        }
        if (duration > 0) {
            params.addParam("duration", duration);
            logEvent(EventID.INTERSTITIAL_FILL_DURATION, params, this.eventLogger);
        }
        logEvent(EventID.INTERSTITIAL_FILLED_TIMES, params, this.eventLogger);
    }

    public void onAdLoadFailCalled(BaseAdapter adapter, String failReason) {
    }

    public void onAdShowSuccessCalled(BaseAdapter adapter) {
        String eventName = GridManager.isMixAdEvents() ? EventID.INTERSTITIAL_SHOWN : (adapter != null && ADSFALL.equals(adapter.getName()) ? EventID.INTERSTITIAL_ADSFALL_SHOWN : EventID.INTERSTITIAL_SHOWN);
        EventParams params = new EventParams();
        params.putAll(adapter.getEventParams());
        params.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
        params.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
        if (adapter.getMediation() != null) {
            params.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
        }
        String displayedTag = adapter.getDisplayedTag();
        if (displayedTag != null) {
            params.addParam(EventParams.PARAM_LABEL, displayedTag);
        }
        logEvent(eventName, params, this.eventLogger);
    }

    public void onAdShowFailCalled(BaseAdapter adapter) {
        String eventName = GridManager.isMixAdEvents() ? EventID.INTERSTITIAL_SHOW_FAILED : (adapter != null && ADSFALL.equals(adapter.getName()) ? EventID.INTERSTITIAL_ADSFALL_SHOW_FAILED : EventID.INTERSTITIAL_SHOW_FAILED);
        EventParams params = new EventParams();
        params.putAll(adapter.getEventParams());
        params.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
        params.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
        if (adapter.getMediation() != null) {
            params.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
        }
        String displayedTag = adapter.getDisplayedTag();
        if (displayedTag != null) {
            params.addParam(EventParams.PARAM_LABEL, displayedTag);
        }
        logEvent(eventName, params, this.eventLogger);
    }

    public void onAdClickCalled(BaseAdapter adapter) {
        String eventName = adapter != null && ADSFALL.equals(adapter.getName()) ? EventID.INTERSTITIAL_ADSFALL_CLICKED : EventID.INTERSTITIAL_CLICKED;

        EventParams params = new EventParams();
        params.putAll(adapter.getEventParams());
        params.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
        params.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
        params.addParam(EventParams.PARAM_SHOWNTIME, adapter.getShowTimeMs());
        if (adapter.getMediation() != null) {
            params.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
        }
        String displayedTag = adapter.getDisplayedTag();
        if (displayedTag != null) {
            params.addParam(EventParams.PARAM_LABEL, displayedTag);
        }
        logEvent(eventName, params, this.eventLogger);
    }

    public void onAdClosedCalled(BaseAdapter adapter, boolean isReward) {
        try {
            String eventName = adapter != null && ADSFALL.equals(adapter.getName()) ? EventID.INTERSTITIAL_ADSFALL_CLOSED : EventID.INTERSTITIAL_CLOSED;

            EventParams params = new EventParams();
            params.putAll(adapter.getEventParams());
            params.addParam(EventParams.PARAM_PROVIDER, adapter.getName());
            params.addParam(EventParams.PARAM_PLACEMENT, adapter.getPlacementId());
            params.addParam(EventParams.PARAM_SHOWNTIME, adapter.getShowTimeMs());
            if (adapter.getMediation() != null) {
                params.addParam(EventParams.PARAM_MEDIATION, adapter.getMediation());
            }
            String displayedTag = adapter.getDisplayedTag();
            if (displayedTag != null) {
                params.addParam(EventParams.PARAM_LABEL, displayedTag);
            }
            logEvent(eventName, params, this.eventLogger);
        } catch (Exception e){

        }
    }
}
