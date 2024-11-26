package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.android.client.AndroidSdk;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ivy.IvySdk;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.device.DeviceUtil;

import java.util.Arrays;

public class AdmobManager {

    private static final String TAG = "AdmobManager";
    private static boolean initialized = false;
    public static boolean isCalledInitialized = false;
    private static final AdmobManager INSTANCE = new AdmobManager();


    public static synchronized AdmobManager getInstance() {
        return INSTANCE;
    }

    public static void checkDeviceMemory(Context context) {
        long bytes = AndroidSdk.getTotalMem();
        float gb = DeviceUtil.getInstance().byteToGB(bytes);
        double config = IvySdk.getGridConfigDouble("AdmobMinMemory", 2.0);
        Log.e("admobLog", "setup block memory:" + config + "; device memory:" + gb);
        if (gb <= config) {
            Log.e("admobLog", "api disableMediationAdapterInitialization called");
            MobileAds.disableMediationAdapterInitialization(context);
        }
    }

    public synchronized void initialize(Activity activity, OnInitializationCompleteListener initializationCompleteListener) {
        isCalledInitialized = true;
        if (!initialized) {
            boolean debug = false;
            try {
//                try {
//                    String smaatoAppId = null;
                    try {
                        ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
                        if (ai != null && ai.metaData != null) {
                            debug = ai.metaData.getBoolean("ivy.debug", false);
                          //  smaatoAppId = ai.metaData.getString("smaato.appId", null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    if (smaatoAppId != null) {
//                        SmaatoSdk.init(activity.getApplication(),
//                                Config.builder()
//                                        .setLogLevel(debug ? LogLevel.DEBUG : LogLevel.ERROR)
//                                        .setHttpsOnly(false)
//                                        .build(),
//                                smaatoAppId);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                try {
//                    POBApplicationInfo pobApplicationInfo = new POBApplicationInfo();
//                    String packageName = activity.getPackageName();
//                    if (!TextUtils.isEmpty(packageName)) {
//                        pobApplicationInfo.setStoreURL(new URL("https://play.google.com/store/apps/details?id=" + packageName + "&hl=en"));
//                    }
//                    OpenWrapSDK.setApplicationInfo(pobApplicationInfo);
//                    OpenWrapSDK.setLogLevel(debug ? OpenWrapSDK.LogLevel.Debug : OpenWrapSDK.LogLevel.Error);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                if (debug) {
//                    MobileAds.setRequestConfiguration(new RequestConfiguration.Builder()
//                            .setTestDeviceIds(Arrays.asList("F3EDE78A2C3C4127A07CA5E97F0FDD02", "F7866672B5B290E5948728B76E3CA555", "BFAC108ACBCDABDA1C0CEDA8B7FE54E8"))
//                            .build());
                }

                //关闭applovin creative debugger
                AppLovinSdk.getInstance(activity).getSettings().setCreativeDebuggerEnabled(false);
                checkDeviceMemory(activity.getApplicationContext());
                MobileAds.initialize(activity, initializationCompleteListener);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
            initialized = true;
        }
    }

    public static final String errorCodeToMessage(int errorCode) {
        String reason = "unknow";
        switch (errorCode) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                reason = "internal_error";
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                reason = "invalid_request";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                reason = "network_error";
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                reason = IvyLoadStatus.NO_FILL;
                break;
        }
        return reason;
    }
}
