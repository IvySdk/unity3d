//package com.ivy.networks.tracker.impl;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.text.TextUtils;
//
//import com.android.client.AndroidSdk;
//import com.ivy.IvySdk;
//import com.ivy.networks.tracker.EventTrackerProvider;
//
//import org.json.JSONObject;
//
//import java.util.Set;
//
//import ivy.data.analytics.TDAnalytics;
//import ivy.data.analytics.TDConfig;
//
//public class ThinkingDataTracker implements EventTrackerProvider {
//    private String userId = null;
//
//    @Override
//    public void initialize(Context context) {
//    }
//
//    public void init(String serverUrl) {
//        if (!TextUtils.isEmpty(serverUrl)) {
//            TDConfig tdConfig = TDConfig.getInstance(IvySdk.getActivity().getApplicationContext(), AndroidSdk.getConfig(AndroidSdk.CONFIG_KEY_APP_ID), serverUrl);
//            tdConfig.setMode(TDConfig.TDMode.NORMAL);
//            TDAnalytics.init(tdConfig);
//            TDAnalytics.enableAutoTrack(TDAnalytics.TDAutoTrackEventType.APP_INSTALL |
//                    TDAnalytics.TDAutoTrackEventType.APP_START |
//                    TDAnalytics.TDAutoTrackEventType.APP_END |
//                    TDAnalytics.TDAutoTrackEventType.APP_CRASH, new TDAnalytics.TDAutoTrackEventHandler() {
//                @Override
//                public JSONObject getAutoTrackEventProperties(int i, JSONObject jsonObject) {
//                    return null;
//                }
//            });
//        }
//    }
//
//    @Override
//    public void setUserID(String userID) {
//        this.userId = userID;
//        if (userID != null) {
//            TDAnalytics.login(userID);
//        }
//    }
//
//    @Override
//    public void logEvent(String eventName, Bundle bundle) {
//        JSONObject params = new JSONObject();
//        try {
//            if (bundle != null) {
//                Set<String> keys = bundle.keySet();
//                for (String key : keys) {
//                    params.put(key, bundle.get(key));
//                }
//                params.put("roleId", IvySdk.getUUID());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        TDAnalytics.track(eventName, params);
//    }
//
//    @Override
//    public void logPurchase(String contentType, String contentId, String currency, float revenue) {
//    }
//
//    @Override
//    public void setSuppress(boolean suppress) {
//    }
//
//    @Override
//    public void setUserProperty(String key, String value) {
//    }
//}