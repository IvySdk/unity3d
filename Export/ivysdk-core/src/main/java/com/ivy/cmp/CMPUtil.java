package com.ivy.cmp;

import android.app.Activity;
import android.util.Log;

import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;

public class CMPUtil {

    private ConsentInformation consentInformation;
    private  volatile boolean alreadyStartLoadAds = false;
    private final String TAG = "CMP";

    public void init(Activity activity, ICMPCallback callback) {
//        ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(activity.getApplicationContext())
//                .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
//                .addTestDeviceHashedId("F3EDE78A2C3C4127A07CA5E97F0FDD02")
//                .build();

        ConsentRequestParameters params = new ConsentRequestParameters.Builder().setTagForUnderAgeOfConsent(false)
//                .setConsentDebugSettings(debugSettings)
                .build();
        consentInformation = UserMessagingPlatform.getConsentInformation(activity.getApplicationContext());
        consentInformation.requestConsentInfoUpdate(activity, params, () -> {
            UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity, (loadAndShowErr) -> {
                if (loadAndShowErr != null) {
                    Log.d(TAG, "consent gathering failed.");
                } else {
                    Log.d(TAG, "consent gathering success.");
                }
                if (consentInformation != null && consentInformation.canRequestAds() && !alreadyStartLoadAds) {
                    alreadyStartLoadAds = true;
                    if (callback != null) callback.onResult(true);
                }
            });
        }, (formError) -> {
            if (formError != null) {
                Log.d(TAG, "load consent info failed:" + formError.getMessage());
            }
            if (callback != null && !alreadyStartLoadAds){
                alreadyStartLoadAds = true;
                callback.onResult(true);
            }
        });
        if (callback != null && consentInformation != null && consentInformation.canRequestAds() && !alreadyStartLoadAds) {
            alreadyStartLoadAds = true;
            Log.d(TAG, "last time consent already gathering success.");
            callback.onResult(true);
        }
    }

    public void reset() {
        if (consentInformation != null) consentInformation.reset();
    }

}
