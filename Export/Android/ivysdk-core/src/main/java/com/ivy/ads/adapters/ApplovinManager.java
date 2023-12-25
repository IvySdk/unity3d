package com.ivy.ads.adapters;


import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.util.Logger;

import java.net.URL;


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

        try {
            // Amazon requires an 'Activity' instance
//            ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
//            String amazonAppId = null;
//            if (ai != null && ai.metaData != null) {
//                Object o = ai.metaData.get("aps.id");
//                if (o instanceof String) {
//                    amazonAppId = String.valueOf(o);
//                }
//            }
//            if (TextUtils.isEmpty(amazonAppId))
//                throw new NullPointerException("Amazon app Id can't be null!!!");
//            AdRegistration.getInstance(amazonAppId, activity.getApplicationContext());
//            AdRegistration.setAdNetworkInfo(new DTBAdNetworkInfo(DTBAdNetwork.MAX));
//            AdRegistration.setMRAIDSupportedVersions(new String[]{"1.0", "2.0", "3.0"});
//            AdRegistration.setMRAIDPolicy(MRAIDPolicy.CUSTOM);
//            /** amazon ad 测试 */
//            if (testMode) {
//                AdRegistration.enableTesting(true);
//                AdRegistration.enableLogging(true);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (ApplovinManager.class) {
            try {
                sdk = AppLovinSdk.getInstance(activity.getApplicationContext());
                sdk.setMediationProvider(AppLovinMediationProvider.MAX);
                if (!sdk.isInitialized()) {
//                    try {
//                        POBApplicationInfo appInfo = new POBApplicationInfo();
//                        String pkg = activity.getApplicationContext().getApplicationInfo().packageName;
//                        appInfo.setStoreURL(new URL("https://play.google.com/store/apps/details?id=" + pkg));
//                        OpenWrapSDK.setApplicationInfo(appInfo);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    if (!AppLovinPrivacySettings.isUserConsentSet(activity.getApplicationContext())) {
                        AppLovinPrivacySettings.setHasUserConsent(true, activity.getApplicationContext());
                    }

                    if (!AppLovinPrivacySettings.isAgeRestrictedUser(activity.getApplicationContext())) {
                        AppLovinPrivacySettings.setIsAgeRestrictedUser(false, activity.getApplicationContext());
                    }

                    if (!AppLovinPrivacySettings.isDoNotSellSet(activity.getApplicationContext())) {
                        AppLovinPrivacySettings.setDoNotSell(true, activity.getApplicationContext());
                    }

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
