package com.ivy.ads.adapters;


import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.util.Logger;


class ApplovinManager {
    private static final String TAG = "ApplovinManager";
    private static boolean testMode = false;

    ApplovinManager() {
    }

    static synchronized AppLovinSdk getInstance(Activity activity) {
        AppLovinSdk sdk = null;
        if (activity == null) {
            return null;
        }

        try {
            ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
            if (ai != null && ai.metaData != null) {
                testMode = ai.metaData.getBoolean("ivy.debug", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        synchronized (ApplovinManager.class) {
            try {
                sdk = AppLovinSdk.getInstance(activity.getApplicationContext());
                if (!sdk.isInitialized()) {

                    if (!AppLovinPrivacySettings.isUserConsentSet(activity.getApplicationContext())) {
                        AppLovinPrivacySettings.setHasUserConsent(true, activity.getApplicationContext());
                    }

                    if (!AppLovinPrivacySettings.isAgeRestrictedUser(activity.getApplicationContext())) {
                        AppLovinPrivacySettings.setIsAgeRestrictedUser(false, activity.getApplicationContext());
                    }

                    if (!AppLovinPrivacySettings.isDoNotSellSet(activity.getApplicationContext())) {
                        AppLovinPrivacySettings.setDoNotSell(true, activity.getApplicationContext());
                    }
                    //关闭applovin creative debugger
                    AppLovinSdk.getInstance(activity).getSettings().setCreativeDebuggerEnabled(false);

                    sdk.initializeSdk(config -> {
                        Logger.debug(TAG, "Applovin initialized");

//                        if (testMode) {
//                            /** 以下部分需要放在初始化成功回调内部，否则debugger内测试广告会出现 max sdk未初始化的错误  */
//                            AppLovinSdk.getInstance(activity.getApplicationContext()).showMediationDebugger();
//                            AppLovinSdk.getInstance(activity.getApplicationContext()).getSettings().setVerboseLogging(true);
//                        }
                    });

                }
            } catch (Throwable ignored) {
            }
        }

        return sdk;
    }

    public static String errorCodeToMessage(int errorCode) {
        String reason = "unknow";
        switch (errorCode) {
            case AppLovinErrorCodes.SDK_DISABLED:
                reason = "sdk_disabled";
                break;
            case AppLovinErrorCodes.FETCH_AD_TIMEOUT:
                reason = "fetch_ad_timeout";
                break;
            case AppLovinErrorCodes.NO_FILL:
                reason = IvyLoadStatus.NO_FILL;
                break;
            case AppLovinErrorCodes.INVALID_ZONE:
                reason = "invalid_zone";
                break;
            case AppLovinErrorCodes.NO_NETWORK:
                reason = "no_network";
                break;
            case AppLovinErrorCodes.UNABLE_TO_RENDER_AD:
                reason = "unable_to_render_ad";
                break;
            case AppLovinErrorCodes.UNSPECIFIED_ERROR:
                reason = "unspecified_error";
                break;
        }
        return reason;
    }
}
