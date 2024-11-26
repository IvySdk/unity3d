package com.android.client;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.appsflyer.AFLogger;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.deeplink.DeepLink;
import com.appsflyer.deeplink.DeepLinkListener;
import com.appsflyer.deeplink.DeepLinkResult;
import com.ivy.IvySdk;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Application extends MultiDexApplication implements DefaultLifecycleObserver {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

//            String appflyersDevKey = "J6ejjnUP9fMkv29PqBuYzR";
//            if (!"".equals(appflyersDevKey)) {
//                AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
//                    @Override
//                    public void onConversionDataSuccess(Map<String, Object> conversionData) {
//
//                    }
//
//                    @Override
//                    public void onConversionDataFail(String s) {
//                        Log.e("ADSFALL", "appsflyer Conversion failed:" + s);
//                    }
//
//                    @Override
//                    public void onAppOpenAttribution(Map<String, String> conversionData) {
//                        Log.e("ADSFALL", "appsflyer open attr:" + (conversionData == null ? "" : conversionData.toString()));
//
//                    }
//
//                    @Override
//                    public void onAttributionFailure(String s) {
//                        Log.e("ADSFALL", "appsflyer open attr failed:" + s);
//                    }
//                };
//
//                AppsFlyerLib.getInstance().enableTCFDataCollection(true);
//
//                AppsFlyerLib.getInstance().setAppInviteOneLink("5qjY");
//
//                    AppsFlyerLib.getInstance().setLogLevel(AFLogger.LogLevel.VERBOSE);
//                    AppsFlyerLib.getInstance().setDebugLog(true);
//                    AppsFlyerLib.getInstance().setMinTimeBetweenSessions(0);
//
//
//                AppsFlyerLib.getInstance().subscribeForDeepLink(new DeepLinkListener() {
//                    @Override
//                    public void onDeepLinking(@NonNull DeepLinkResult deepLinkResult) {
//                        DeepLinkResult.Status dlStatus = deepLinkResult.getStatus();
//                        if (dlStatus == DeepLinkResult.Status.FOUND) {
//                            Log.d("ADSFALL", "Deep link found");
//                        } else if (dlStatus == DeepLinkResult.Status.NOT_FOUND) {
//                            Log.d("ADSFALL", "Deep link not found");
//                            return;
//                        } else {
//                            // dlStatus == DeepLinkResult.Status.ERROR
//                            DeepLinkResult.Error dlError = deepLinkResult.getError();
//                            Log.d("ADSFALL", "There was an error getting Deep Link data: " + dlError.toString());
//                            return;
//                        }
//                        DeepLink deepLinkObj = deepLinkResult.getDeepLink();
//                        try {
//                            Log.d("ADSFALL", "The DeepLink data is: " + deepLinkObj.toString());
//                        } catch (Exception e) {
//                            Log.d("ADSFALL", "DeepLink data came back null");
//                            return;
//                        }
//                        // An example for using is_deferred
//                        if (deepLinkObj.isDeferred()) {
//                            Log.d("ADSFALL", "This is a deferred deep link");
//                        } else {
//                            Log.d("ADSFALL", "This is a direct deep link");
//                        }
//                        try {
//
//                            JSONObject dlData = deepLinkObj.getClickEvent();
//                            String inviteUserId = null;
//                            if (dlData.has("deep_link_sub1")) {
//                                inviteUserId = deepLinkObj.getStringValue("deep_link_sub1");
//                                Log.d("ADSFALL", "The invite user id is: " + inviteUserId);
//                            } else {
//                                Log.d("ADSFALL", "The invite user id not found");
//                            }
//                            if (!TextUtils.isEmpty(inviteUserId)) {
//                              //  String currentUserId = mmGetStringValue("invite_current_user_id", "");
////                                if (inviteUserId.equals(currentUserId)){
////                                    Log.e("ADSFALL", "user id equal invite id, it is same user");
////                                } else {
////                                    Log.d("ADSFALL", "retrieved invite user id:" + inviteUserId);
////                                   // mmSetStringValue("af_invite_id", inviteUserId);
////                                }
//                            }
//                        } catch (Exception e) {
//                            Log.e("ADSFALL", "parse af deeplink err:" + e.getMessage());
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                AppsFlyerLib.getInstance().init(appflyersDevKey, conversionListener, this);
//                AppsFlyerLib.getInstance().start(this);
//
////                boolean isPreInstallApp = AppsFlyerLib.getInstance().isPreInstalledApp(IvySdk.getActivity().getApplicationContext());
////                Log.d("ADSFALL", "af check is pre install app:" + isPreInstallApp);
//
////                AppsFlyerAdRevenue.Builder afRevenueBuilder = new AppsFlyerAdRevenue.Builder(activity.getApplication());
////                AppsFlyerAdRevenue.initialize(afRevenueBuilder.build());
//
//                // init
////                PurchaseClient.Builder builder = new PurchaseClient.Builder(activity.getApplicationContext(), Store.GOOGLE);
////                // Make sure to keep this instance
////                PurchaseClient purchaseClient = builder.build();
////                purchaseClient.startObservingTransactions();
//            }
////        }

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    private long GAME_PLAY_TIME = 0L;
    private boolean GAME_STOPPED = false;
    private Timer timer;

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        GAME_STOPPED = false;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (!GAME_STOPPED) {
//                        Log.e("IvySdk", "timer is working");
                        GAME_PLAY_TIME += 5;
                        IvySdk.setupGamePlayTime(GAME_PLAY_TIME);
                    }
                } catch (Exception e) {
                }
            }
        }, 0, 5000);
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        GAME_STOPPED = false;
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        GAME_STOPPED = true;
    }
}
