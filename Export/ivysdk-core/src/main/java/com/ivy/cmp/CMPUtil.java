package com.ivy.cmp;

import android.app.Activity;

import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;

public class CMPUtil {

    private ConsentInformation consentInformation;
    //    private  volatile boolean alreadyStartLoadAds = false;
    private final String TAG = "CMP";

    public void init(Activity activity, Boolean debug, ICMPCallback callback) {
        ConsentRequestParameters.Builder builder = new ConsentRequestParameters.Builder()
                .setTagForUnderAgeOfConsent(false);
        if (debug){
            ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(activity.getApplicationContext())
                    .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
                    .addTestDeviceHashedId("F3EDE78A2C3C4127A07CA5E97F0FDD02")
                    .build();
            builder.setConsentDebugSettings(debugSettings);
        }
        ConsentRequestParameters params = builder.build();
        consentInformation = UserMessagingPlatform.getConsentInformation(activity.getApplicationContext());
        consentInformation.requestConsentInfoUpdate(activity, params, () -> {
            UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity, (loadAndShowError) -> {
                if (callback != null) {
                    callback.onResult(consentInformation.canRequestAds(), consentInformation.getConsentStatus());
                }
            });
        }, (requestFormError) -> {
            if (callback != null) {
                callback.onResult(consentInformation.canRequestAds(), consentInformation.getConsentStatus());
            }
        });
        if (consentInformation.canRequestAds()) {
            if (callback != null) {
                callback.onResult(true, consentInformation.getConsentStatus());
            }
        }


//        consentInformation.requestConsentInfoUpdate(activity, params, () -> {
//            UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity, (loadAndShowErr) -> {
//                if (loadAndShowErr != null) {
//                    Log.d(TAG, "consent gathering failed.");
//                } else {
//                    Log.d(TAG, "consent gathering success.");
//                }
//                if (consentInformation != null && consentInformation.canRequestAds() && !alreadyStartLoadAds) {
//                    alreadyStartLoadAds = true;
//                    if (callback != null) callback.onResult(true, consentInformation.getConsentStatus());
//                }
//            });
//        }, (formError) -> {
//            if (formError != null) {
//                Log.d(TAG, "load consent info failed:" + formError.getMessage());
//            }
//            if (callback != null && !alreadyStartLoadAds){
//                alreadyStartLoadAds = true;
//                if (consentInformation != null) {
//                    callback.onResult(true, consentInformation.getConsentStatus());
//                } else {
//                    callback.onResult(true, 1);
//                }
//            }
//        });
//        if (callback != null && consentInformation != null && consentInformation.canRequestAds() && !alreadyStartLoadAds) {
//            alreadyStartLoadAds = true;
//            Log.d(TAG, "last time consent already gathering success.");
//            callback.onResult(true, consentInformation.getConsentStatus());
//        }
    }

    public void reset() {
        if (consentInformation != null) consentInformation.reset();
    }

}
