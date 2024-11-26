package com.ivy.networks.tracker.impl;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.ivy.ads.IvyAds;
import com.ivy.networks.tracker.EventTrackerProvider;
import com.parfka.adjust.sdk.Adjust;
import com.parfka.adjust.sdk.AdjustEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ParfkaTracker implements EventTrackerProvider {

    private boolean suppress = false;
    private String userId = null;

    private List<String> countries = null;

    @Override
    public void initialize(Context context) {
    }

    @Override
    public void setSuppress(boolean suppress) {
        this.suppress = suppress;
    }

    @Override
    public void setUserID(String userID) {
        this.userId = userID;
    }

    @Override
    public void setUserProperty(String key, String value) {
        Adjust.addSessionPartnerParameter(key, value);
    }

    @Override
    public void logPurchase(String contentType, String contentId, String currency, float revenue) {

    }

    public void addScreenCountries(List<String> data) {
        if (data != null && data.size() > 0){
            countries = new ArrayList<>();
            countries.addAll(data);
        }
    }

    @Override
    public void logEvent(String eventName, Bundle bundle) {
        if (suppress) {
            return;
        }
        String local = Locale.getDefault().getCountry();
        if (countries != null && countries.size() > 0 && !countries.contains(local)){
            return;
        }

        AdjustEvent adjustEvent = new AdjustEvent(eventName);
        if (bundle != null) {
            for (String s : bundle.keySet()) {
                String n = String.valueOf(s);
                Object v = bundle.get(n);
                if (!"".equals(n) && !"".equals(v)) {
                    adjustEvent.addPartnerParameter(n, v);
                }
            }
        }
        if (userId != null) {
            adjustEvent.addPartnerParameter("roleId", userId);
        }
        Adjust.trackEvent(adjustEvent);
        try {
            if (IvyAds.isDebugMode()) {
                String data = "";
                for (String s : bundle.keySet()) {
                    String n = String.valueOf(s);
                    Object v = bundle.get(n);
                    data += n;
                    data += ":";
                    data += v;
                    data += ";";
                }
                Log.e("parfka", eventName + ":" + data);
            }
        } catch (Exception e) {
        }
    }
}
