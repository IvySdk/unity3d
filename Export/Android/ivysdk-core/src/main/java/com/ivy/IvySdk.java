package com.ivy;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.adsfall.BuildConfig;
import com.adsfall.R;
import com.alibaba.fastjson.JSON;
import com.android.client.AndroidSdk;
import com.android.client.GoogleListener;
import com.android.client.OnSkuDetailsListener;
import com.android.client.PlayGameProfileListener;
import com.android.client.SKUDetail;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.google.android.gms.ads.AdInspectorError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnAdInspectorClosedListener;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.AnnotatedData;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PlayGamesAuthProvider;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplay;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.model.Action;
import com.google.firebase.inappmessaging.model.BannerMessage;
import com.google.firebase.inappmessaging.model.CampaignMetadata;
import com.google.firebase.inappmessaging.model.ImageData;
import com.google.firebase.inappmessaging.model.ImageOnlyMessage;
import com.google.firebase.inappmessaging.model.InAppMessage;
import com.google.firebase.inappmessaging.model.MessageType;
import com.google.firebase.inappmessaging.model.ModalMessage;
import com.google.firebase.inappmessaging.model.Text;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import com.ivy.ads.IvyAds;
import com.ivy.ads.IvyAdsManager;
import com.ivy.ads.adapters.AdmobManager;
import com.ivy.ads.events.DefaultEventLogger;
import com.ivy.ads.interfaces.IvyAdCallbacks;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.managers.BannerAdManager;
import com.ivy.billing.PurchaseManager;
import com.ivy.billing.PurchaseManagerWrapper;
import com.ivy.billing.impl.IPurchaseQueryCallback;
import com.ivy.billing.impl.PurchaseManagerImpl;
import com.ivy.cmp.CMPUtil;
import com.ivy.cmp.ICMPCallback;
import com.ivy.event.EventBus;
import com.ivy.networks.DownloadFeedback;
import com.ivy.networks.grid.GridManager;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.networks.ui.dialog.ImmersiveDialog;
import com.ivy.networks.util.Util;
import com.ivy.util.Logger;
import com.ivy.xsolla.DLog;
import com.ivy.xsolla.XsollaPurchaseImpl;
import com.tencent.mmkv.MMKV;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public final class IvySdk {
    private static final String TAG = IvySdk.class.getCanonicalName();

    private static final int RC_SIGN_IN = 100;
    private static final int RC_SIGN_IN_RESOLUTION = 101;

    private static final int RC_ACHIEVEMENT_UI = 9003;

    private static final int RC_REQUEST_UPDATE = 8001;

    private static final int RC_SAVED_GAMES = 9009;
    public static final int RC_WEBVIEW = 880;

    public static final int POS_LEFT_TOP = 1;
    public static final int POS_LEFT_BOTTOM = 2;
    public static final int POS_CENTER_TOP = 3;
    public static final int POS_CENTER_BOTTOM = 4;
    public static final int POS_CENTER = 5;
    public static final int POS_RIGHT_TOP = 6;
    public static final int POS_RIGHT_BOTTOM = 7;

    public static final String KEY_GAME_MAIN_LINE = "internal_main_line";
    public static final String KEY_VIRUTAL_CURRENCY = "internal_virtual_currency";

    private static final String KEY_AUTO_INAPP_MESSAGE_EVENT = "auto_inapp_message_event";

    public static boolean skipPauseOnce = false;
    public static boolean skipResumeOnce = false;

    public static Context CONTEXT;

    private static Boolean sdkInitialized = false;
    private static IvyAdsManager adManager;
    private static GridManager gridManager = null;
    private static DefaultEventLogger eventTracker;
    private static long startupTime = 0L;

    private static PurchaseManagerWrapper purchaseManagerWrapper;

    public static XsollaPurchaseImpl xsollaPurchaseImpl;

    private static WeakReference<Activity> main = null;
    private static boolean alreadyResumed = false;
    private static boolean alreadyPaused = false;

    private static Map<String, JSONObject> storeItems = new HashMap<>();

    private static JSONObject googleAchievements = null;
    private static JSONObject googleLeaderBoards = null;


    private static MMKV gameDataMMKV = null;
    private static String ID_MMKV_GAMEDATA = "_gamedata_";

    private static String userAttributeUrl = null;


    public static synchronized boolean isInitialized() {
        boolean booleanValue;
        synchronized (IvySdk.class) {
            booleanValue = sdkInitialized;
        }
        return booleanValue;
    }

    /**
     * this method will called by ContentProvider, we fetch basic application information from xml
     *
     * @param applicationContext application context
     */
    public static synchronized void sdkInitialize(Context applicationContext) {
        Log.d(TAG, "IvySDK initialized");
        CONTEXT = applicationContext;
    }

    public static void queryPurchases(String productId) {
        if (purchaseManagerWrapper != null) {
            purchaseManagerWrapper.queryPurchases(productId);
        }
    }

    public static void queryUnconsumedPurchases() {
        if (purchaseManagerWrapper != null) {
            purchaseManagerWrapper.queryUnconsumedPurchases();
        }
    }

    public static void getPurchaseHistory(String skuType, IPurchaseQueryCallback<List<JSONObject>> callback) {
        if (purchaseManagerWrapper != null) {
            purchaseManagerWrapper.getPurchaseHistory(skuType, callback);
            return;
        } else {
            if (callback != null) callback.onResult(null);
        }
    }

    public static boolean isPaymentClientCheck() {
        if (purchaseManagerWrapper != null) {
            return purchaseManagerWrapper.isClientCheck();
        }
        return false;
    }

    public static void changeSku(String newIapId, String oldIapId, String payload) {
        if (purchaseManagerWrapper != null) {
            purchaseManagerWrapper.changeSku(newIapId, oldIapId, payload);
        }
    }


    private static boolean adEnabled = true;

    public static void updateAdStatus(boolean enabledAd) {
        adEnabled = enabledAd;
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.getSharedPreferences("prefs", Context.MODE_PRIVATE).edit().putBoolean("adsfall_ad_status", enabledAd).apply();
        if (!adEnabled) {
            closeBanners();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private static FirebaseRemoteConfig mFirebaseRemoteConfig;

    private static final String SP_KEY_PARFKA_URL = "_parfka_url";
    private static final String SP_KEY_PARFKA_URL2 = "_parfka_url_2";

    private static final AtomicBoolean userOnlineFlag = new AtomicBoolean(true);

    public static void updateCurrentActivity(Activity activity) {
        if (activity != null) {
            main = new WeakReference<>(activity);
        } else {
            main = null;
        }
    }

    private static boolean afConversionTracked = false;

    /**
     * The sdk real initialize function.
     *
     * @param activity
     * @param callback
     */
    public static synchronized void initialize(@NonNull final Activity activity, final Bundle savedInstanceState, final InitializeCallback callback) {
        try {
            FirebaseApp.initializeApp(activity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        main = new WeakReference<>(activity);

        startupTime = System.currentTimeMillis();

        ApplicationInfo ai = null;
        try {
            ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        final SharedPreferences sp = main.get().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        boolean forceUpdate = false;

        if (ai != null && ai.metaData != null) {
            forceUpdate = ai.metaData.getBoolean("ivy.debug", false);
            DLog.setEnable(forceUpdate);
            if (forceUpdate) {
                Logger.enableLogging();
            } else {
                Logger.disableLogging();
            }
        }

        try {
            MMKV.initialize(activity);

            gameDataMMKV = MMKV.mmkvWithID(ID_MMKV_GAMEDATA);

            mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        } catch (Throwable t) {
            Logger.error(TAG, "initialize MMKV exception", t);
        }

        eventTracker = new DefaultEventLogger(activity);

        gridManager = new GridManager(activity, eventTracker, startupTime, false);

        // read store data
        JSONObject gridData = GridManager.getGridData();

        eventTracker.setGridData(gridData);
        if (gridData != null && gridData.has("gen_events")) {
            eventTracker.setEventHooks(gridData.optJSONObject("gen_events"));
        }

        if (gridData != null && gridData.has("event_targets")) {
            eventTracker.setEventTargets(gridData.optJSONObject("event_targets"));
        }

        adEnabled = sp.getBoolean("adsfall_ad_status", true);
        if (AndroidSdk.isXsollaSupport()) {
            DLog.d("current is xsolla support");
            xsollaPurchaseImpl = new XsollaPurchaseImpl(activity, EventBus.getInstance());
        } else {
            // 内购
            purchaseManagerWrapper = new PurchaseManagerWrapper(activity);
            storeItems = new HashMap<>();

            List<String> iapIds = new ArrayList<>();
            if (gridData != null && gridData.has("payment")) {
                try {
                    JSONObject checkout = gridData.optJSONObject("payment").optJSONObject("checkout");
                    Iterator<String> iapItems = checkout.keys();
                    while (iapItems.hasNext()) {
                        String billId = iapItems.next();
                        JSONObject iapItem = checkout.optJSONObject(billId);
                        if (iapItem != null) {
                            String feeName = iapItem.optString("feename");
                            if (!"".equals(feeName)) {
                                iapIds.add(feeName);
                                iapItem.put("billId", billId);
                                iapItem.put("usd", iapItem.optDouble("usd", 0));
                                if (!iapItem.has("autoload")) {
                                    iapItem.put("autoload", 1);
                                }
                                storeItems.put(iapItem.optString("feename"), iapItem);
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (storeItems != null && storeItems.size() > 0) {
                try {
                    purchaseManagerWrapper.startLoadingStoreData(activity, iapIds, storeItems);
                } catch (Throwable t) {
                    Logger.error(TAG, "startLoadingStoreData exception", t);
                }
            }
        }

        // read google achievements data in grid data
        JSONObject googleEntry = gridData.optJSONObject("google");
        if (googleEntry != null && googleEntry.has("achievement")) {
            googleAchievements = googleEntry.optJSONObject("achievement");
        }

        if (googleEntry != null && googleEntry.has("leaderboard")) {
            googleLeaderBoards = googleEntry.optJSONObject("leaderboard");
            if (googleLeaderBoards != null) {
                Log.d(TAG, "enable google leaderboards: " + googleLeaderBoards.toString());
            }
        }

        adManager = new IvyAdsManager();
        adManager.onCreate(activity, eventTracker, gridManager);

        boolean debugMode = GridManager.getGridData().optBoolean("debug") || BuildConfig.DEBUG;
        if (!debugMode) {
            debugMode = forceUpdate;
        }

        if (debugMode) {
            Toast.makeText(activity, "DO NOT PUBLISH THIS!", Toast.LENGTH_LONG).show();
        }

        setDebug(debugMode);

        try {
            if (gridData != null && gridData.has("remoteconfig")) {
                JSONObject remoteconfig = gridData.optJSONObject("remoteconfig");
                if (remoteconfig != null) {
                    Map<String, Object> map = new HashMap<>();
                    Iterator<String> keys = remoteconfig.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        try {
                            map.put(key, remoteconfig.get(key));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mFirebaseRemoteConfig.setDefaultsAsync(map);
                }
            }

            final boolean inDebugMode = debugMode;
            long minimumFetchIntervalInSeconds = debugMode ? 60 : 3600 * 2;
            if (gridData.has("remoteConfigFetchInterval")) {
                minimumFetchIntervalInSeconds = gridData.optInt("remoteConfigFetchInterval", 3600 * 2);
            }
            mFirebaseRemoteConfig.fetch(minimumFetchIntervalInSeconds).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        mFirebaseRemoteConfig.activate();
                        Log.w(TAG, "Remote config fetched");
                        if (eventTracker != null) {
                            eventTracker.overrideConfigByRemoteConfig(mFirebaseRemoteConfig);
                            eventTracker.checkRemoteConfigEvents(mFirebaseRemoteConfig);
                        }

                        String autoInAppMessageEvent = mFirebaseRemoteConfig.getString(KEY_AUTO_INAPP_MESSAGE_EVENT);
                        if (!"".equals(autoInAppMessageEvent)) {
                            triggerInAppMessage(autoInAppMessageEvent);
                        }

                        if (inDebugMode) {
                            Map<String, FirebaseRemoteConfigValue> allConfigs = mFirebaseRemoteConfig.getAll();
                            if (allConfigs != null) {
                                Iterator<String> keys = allConfigs.keySet().iterator();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    FirebaseRemoteConfigValue value = allConfigs.get(key);
                                    Logger.debug(TAG, "Remote Config, " + key + " =====> " + (value != null ? value.asString() : " null"));
                                }
                            }
                        }

                        if (callback != null) {
                            callback.onRemoteConfigUpdated();
                        }
                    } else {
                        Exception ex = task.getException();
                        if (ex != null) {
                            Log.e(TAG, "fetch remote config failed: ", ex);
                        }
                    }
                }
            });
        } catch (Throwable ex) {
            Logger.error(TAG, "Remote Config failed", ex);
        }

        try {
            String oldFirebaseToken = sp.getString("firebase_token", null);

            JSONObject pushEntity = getPushEntity();
            if (pushEntity != null) {
                FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Logger.warning(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        if (token == null) {
                            return;
                        }
                        Logger.debug(TAG, "token>>> " + token);

                        if (oldFirebaseToken != null && oldFirebaseToken.equals(token)) {
                            FirebaseOptions options = FirebaseApp.getInstance().getOptions();
                            if (options != null) {
                                initFirebaseToken(token, options.getProjectId());
                            }
                            return;
                        }

                        sp.edit().putString("firebase_token", token).apply();

                        Logger.debug(TAG, "receive token: " + token);
                        FirebaseOptions options = FirebaseApp.getInstance().getOptions();
                        if (options != null) {
                            initFirebaseToken(token, options.getProjectId());
                            Logger.debug(TAG, "mf is created " + token + "\n projectId " + options.getProjectId());
                            initLocalPush();
                        }
                    }
                });
            }
        } catch (Throwable t) {
            Logger.error(TAG, "Push Settings failed", t);
        }

        if (gridData.has("appflyers.devkey")) {
            String appflyersDevKey = gridData.optString("appflyers.devkey");
            if (!"".equals(appflyersDevKey)) {
                AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
                    @Override
                    public void onConversionDataSuccess(Map<String, Object> conversionData) {
                        Logger.debug(TAG, "onConversionDataSuccess");
                        AndroidSdk.onConversionDataSuccess(conversionData);
                        if (afConversionTracked) {
                            return;
                        }

                        if (eventTracker != null && conversionData != null) {
                            Bundle bundle = new Bundle();
                            for (String attrName : conversionData.keySet()) {
                                bundle.putString(attrName, String.valueOf(conversionData.get(attrName)));
                            }
                            eventTracker.trackConversion("af_conversion", bundle);

                            eventTracker.parfkaLog("af_conversion", bundle);

                            Object status = conversionData.get("af_status");
                            if (status != null && !status.equals("Organic")) {
                                if (conversionData.containsKey("media_source")) {
                                    String mediaSource = String.valueOf(conversionData.get("media_source"));
                                    eventTracker.setUserProperty("af_media_source", mediaSource);
                                }

                                if (conversionData.containsKey("campaign")) {
                                    String campaign = String.valueOf(conversionData.get("campaign"));
                                    eventTracker.setUserProperty("af_campaign", campaign);
                                }

                                if (conversionData.containsKey("af_adset")) {
                                    String adset = String.valueOf(conversionData.get("af_adset"));
                                    eventTracker.setUserProperty("af_adset", adset);
                                }
                            } else {
                                eventTracker.setUserProperty("af_campaign", "Organic");
                            }
                            afConversionTracked = true;
                        }
                    }

                    @Override
                    public void onConversionDataFail(String s) {
                        AndroidSdk.onConversionDataFail(s);
                    }

                    @Override
                    public void onAppOpenAttribution(Map<String, String> conversionData) {
                        AndroidSdk.onAppOpenAttribution(conversionData);
                        if (eventTracker != null && conversionData != null) {
                            Bundle bundle = new Bundle();
                            for (String attrName : conversionData.keySet()) {
                                bundle.putString(attrName, String.valueOf(conversionData.get(attrName)));
                            }
                            eventTracker.parfkaLog("af_app_open_attribution", bundle);
                        }
                    }

                    @Override
                    public void onAttributionFailure(String s) {
                        AndroidSdk.onAttributionFailure(s);
                    }
                };
                AppsFlyerLib.getInstance().init(appflyersDevKey, conversionListener, activity.getApplicationContext());
//                AppsFlyerLib.getInstance().start(activity);
                AppsFlyerLib.getInstance().start(activity, appflyersDevKey, new AppsFlyerRequestListener() {
                    @Override
                    public void onSuccess() {
                        AndroidSdk.onAfInitSuccess();
                    }

                    @Override
                    public void onError(int i, @NonNull String s) {
                        AndroidSdk.onAfInitFailed(i, s);
                    }
                });
            }
        }

        try {
            trackAppOpen(activity);
        } catch (Throwable ex) {
            Logger.error(TAG, "track app open failed", ex);
        }

        userAttributeUrl = gridData.optString("user.attribute.url", "");
        try {
            long _duration = mmGetLongValue("sdk_running_time", -1);
            Log.e("IvySdk", "play time:" + _duration);
            if (_duration > 0) {
                Bundle bundle = new Bundle();
                bundle.putLong("duration", _duration);
                long trackDate = mmGetLongValue("sdk_running_date", System.currentTimeMillis());
                bundle.putString("trackDate", trackDate + "");
                eventTracker.logToFirebase("play_time", bundle);
            }
        } catch (Exception e) {
        } finally {
            mmSetLongValue("sdk_running_time", 0);
        }

        CMPUtil cmpUtil = new CMPUtil();
        boolean finalDebugMode = debugMode;
        cmpUtil.init(activity, new ICMPCallback() {
            @Override
            public void onResult(boolean ableLoadAds) {
                try {
                    Log.d("CMP", "start load ads");
                    try {
                        AdmobManager.getInstance().initialize(activity, initializationStatus -> {
                            try {
                                Logger.debug(TAG, "Admob initialized");
                                if (finalDebugMode) {
                                    MobileAds.openAdInspector(activity, new OnAdInspectorClosedListener() {
                                        public void onAdInspectorClosed(@Nullable AdInspectorError error) {
                                            // Error will be non-null if ad inspector closed due to an error.
                                            Log.e("Error", "" + (error == null ? "" : error.getMessage()));
                                        }
                                    });
                                }
                                Map<String, AdapterStatus> adapterStatusMap = initializationStatus.getAdapterStatusMap();
                                for (Map.Entry<String, AdapterStatus> entry : adapterStatusMap.entrySet()) {
                                    Logger.debug(TAG, "adapter status: " + entry.getKey() + " >>> " + entry.getValue().getInitializationState());
                                }
                                adManager.preload(gridData);
                            } catch (Throwable t) {
                                Logger.error(TAG, "adManager preload exception", t);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                }
            }
        });

    }

    public static void preloadAds() {

    }


    public static String firebaseToken = null;
    public static String firebaseProjectId = null;

    private static void initFirebaseToken(String token, String fcmProjectId) {
        firebaseToken = token;
        firebaseProjectId = fcmProjectId;
    }

    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
        }
        return okHttpClient;
    }

    public static void cancelPush(String key) {
        try {

            JSONObject pushEntity = getPushEntity();

            if (pushEntity != null) {
                String url = pushEntity.optString("push-server-url");
                if ("".equals(url)) {
                    Logger.error(TAG, "push server url not config");
                    return;
                }
                Request.Builder requestBuilder = new Request.Builder();
                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                httpBuilder.addQueryParameter("data", getCancelPushParams(key));
                Request request = requestBuilder.url(httpBuilder.build()).build();
                getOkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        try {
                            Logger.debug(TAG, response.body().string());
                        } catch (Throwable ignored) {
                        }
                    }
                });
            }
        } catch (Throwable t) {
            Logger.error(TAG, "Cancel push failed", t);
        }
    }

    public static void push(String key, String title, String content, long pushTime, boolean localTimeZone, String fbIds, String uuids, String topics, int iosBadge, boolean useSound, String soundName, String extraData) {
        try {
            if (firebaseProjectId == null || firebaseToken == null) {
                Log.e(TAG, "not able to push, not initialized firebase");
                return;
            }
            JSONObject gridData = GridManager.getGridData();
            if (gridData == null) {
                return;
            }

            Logger.debug(TAG, "push start called");

            JSONObject pushEntity = getPushEntity();
            if (pushEntity != null) {
                String url = pushEntity.optString("push-server-url");
                if ("".equals(url)) {
                    Logger.error(TAG, "push server url not config");
                    return;
                }

                JSONObject map = new JSONObject();
                try {
                    JSONObject message = new JSONObject();

                    String appId = gridData.optString("appid");
                    String versionName = "unknow";
                    String packageName = "";
                    try {
                        Context context = IvySdk.CONTEXT;
                        PackageManager packageManager = context.getPackageManager();
                        packageName = context.getPackageName();
                        PackageInfo info = packageManager.getPackageInfo(packageName, 0);
                        versionName = info.versionName;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    if (key != null) {
                        message.put("key", IvySdk.getUUID() + "_" + key);
                    }
                    message.put("uuid", IvySdk.getUUID());
                    message.put("facebookIds", fbIds == null ? "" : fbIds);
                    message.put("uuids", uuids == null ? "" : uuids);
                    message.put("receive_appid", appId);
                    message.put("receive_pkg", packageName);
                    message.put("receive_topic", topics == null ? "" : topics);
                    message.put("data", extraData == null ? "" : extraData);
                    message.put("iosBadge", iosBadge);
                    message.put("sound", useSound);
                    message.put("soundName", soundName);
                    message.put("project", IvySdk.firebaseProjectId);
                    message.put("title", title);
                    message.put("content", content);
                    message.put("pushTime", pushTime);
                    message.put("delayTime", pushTime - System.currentTimeMillis());
                    message.put("useLocalTimeZone", localTimeZone);
                    int timeZone = TimeZone.getDefault().getRawOffset() / 3600000;
                    if (timeZone > 0) {
                        message.put("sendTimeZone", "+" + timeZone);
                    } else {
                        message.put("sendTimeZone", String.valueOf(timeZone));
                    }

                    message.put("languageCode", Locale.getDefault().getLanguage());
                    message.put("countryCode", Locale.getDefault().getCountry());
                    message.put("versionName", versionName);

                    map.put("method", "push");
                    map.put("message", message);

                    Request.Builder requestBuilder = new Request.Builder();

                    RequestBody requestBody = new FormBody.Builder().add("data", encryptPushParams(map.toString())).build();
                    Request request = requestBuilder.url(url).post(requestBody).build();
                    getOkHttpClient().newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Logger.debug(TAG, "Register Push Failed");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Logger.debug(TAG, "Register Push success");
                            try {
                                Logger.debug(TAG, response.body().string());
                            } catch (Throwable t) {

                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable t) {
            Logger.error(TAG, "register push failed", t);
        }
    }

    private static String encryptPushParams(String s) {
        if (s == null) {
            return "";
        }
        JSONObject gridData = GridManager.getGridData();
        boolean encodePushParams = gridData == null || gridData.optBoolean("encodePushParams", true);
        if (encodePushParams) {
            byte[] bytes = s.getBytes();
            int len = bytes.length;
            byte buffer[] = new byte[len + 5];
            byte seed = (byte) ((Math.random() * 63) + 1);
            buffer[0] = seed;
            buffer[1] = (byte) ((len >> 24) & 0xFF);
            buffer[2] = (byte) ((len >> 16) & 0xFF);
            buffer[3] = (byte) ((len >> 8) & 0xFF);
            buffer[4] = (byte) (len & 0xFF);

            for (int i = 0; i < len; i++) {
                int tmp = bytes[i];
                tmp = (tmp + seed) < 256 ? (tmp + seed) : tmp;
                buffer[i + 5] = (byte) tmp;
            }

            return Base64.encodeToString(buffer, Base64.NO_WRAP).trim();
        } else {
            try {
                return Base64.encodeToString(s.getBytes("UTF-8"), Base64.NO_WRAP);
            } catch (Throwable t) {
                return "";
            }
        }
    }

    private static String getCancelPushParams(String key) {
        JSONObject map = new JSONObject();
        try {
            map.put("method", "cancel");
            if (key != null) {
                map.put("key", getUUID() + "_" + key);
            }
            map.put("uuid", getUUID());
            return encryptPushParams(map.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getPushParams() {
        JSONObject map = new JSONObject();
        try {
            JSONObject gridData = GridManager.getGridData();

            String appId = gridData.optString("appid");
            String versionName = "unknown";
            String packageName = "";
            try {
                Context context = IvySdk.CONTEXT;
                PackageManager packageManager = context.getPackageManager();
                packageName = context.getPackageName();
                PackageInfo info = packageManager.getPackageInfo(packageName, 0);
                versionName = info.versionName;
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            map.put("method", "saveToken");
            map.put("uuid", getUUID());
            map.put("appid", appId);
            map.put("pkg", packageName);
            map.put("token", firebaseToken);
            map.put("facebookId", "");
            map.put("fcmProjectId", firebaseProjectId);
            map.put("languageCode", Locale.getDefault().getLanguage());
            map.put("countryCode", Locale.getDefault().getCountry());
            map.put("versionName", versionName);
            return encryptPushParams(map.toString());
        } catch (JSONException e) {
            return "";
        }
    }

    private static JSONObject getPushEntity() {
        JSONObject gridData = GridManager.getGridData();
        if (gridData == null || !gridData.has("data")) {
            return null;
        }
        JSONObject dataObject = gridData.optJSONObject("data");
        if (dataObject == null || !dataObject.has("push")) {
            return null;
        }
        JSONArray pushArray = dataObject.optJSONArray("push");
        if (pushArray != null && pushArray.length() > 0) {
            return pushArray.optJSONObject(0);
        }
        return null;
    }

    private static void initLocalPush() {
        Logger.debug(TAG, "initLocalPush called ");
        try {
            JSONObject pushEntity = getPushEntity();
            if (pushEntity != null) {
                String url = pushEntity.optString("push-server-url");

                Request.Builder requestBuilder = new Request.Builder();
                HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
                httpBuilder.addQueryParameter("data", getPushParams());
                Request request = requestBuilder.url(httpBuilder.build()).build();
                getOkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Logger.error(TAG, "Register Push Failed", e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response != null) {
                            ResponseBody responseBody = response.body();
                            if (responseBody != null) {
                                Logger.debug(TAG, "Register Push success " + responseBody.string());
                            }
                        }
                    }
                });
            }
        } catch (Throwable t) {
            Logger.error(TAG, "initLocalPush exception", t);
        }
    }

    public static JSONObject getStoreItem(String productId) {
        if (storeItems != null && storeItems.containsKey(productId)) {
            return storeItems.get(productId);
        }
        return null;
    }

    public static void querySKUDetail(List<String> iapIds, OnSkuDetailsListener skuDetailsListener) {
        if (purchaseManagerWrapper != null) {
            purchaseManagerWrapper.querySKUDetails(iapIds, skuDetailsListener);
        }
    }

    public static SKUDetail getSKUDetail(String productId) {
        if (purchaseManagerWrapper != null) {
            return purchaseManagerWrapper.getSKUDetail(productId);
        }

        return null;
    }

    public static void setAdCallback(IvyAdType adType, IvyAdCallbacks callback) {
        if (adManager != null) {
            adManager.setAdCallback(adType, callback);
        } else {
            Logger.error(TAG, "Ad not configured? ");
        }
    }

    // 应用启动计数
    public static int appStartTimes = 1;
    // 应用的首次启动时间戳
    public static long firstAppStartTime = 0L;
    // 应用最后启动时间
    private static long lastAppStartTime = 0L;
    private static int totalOrders = 0;
    private static float totalRevenues = 0.0f;

    /**
     * 应用启动逻辑
     * <p>
     * appOpen: 用户打开时,触发此事件,
     * 1. 触发启动次数summary统计逻辑
     * 2. 触发留存summary统计逻辑
     * 3. 触发连续活跃统计逻辑
     */
    private static void trackAppOpen(Activity activity) {
        try {
            if (activity == null) {
                return;
            }

            SharedPreferences sharedPreferences = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE);
            int appStartTimes = sharedPreferences.getInt("_app_start_times", 0) + 1;
            sharedPreferences.edit().putInt("_app_start_times", appStartTimes).apply();

            IvySdk.appStartTimes = appStartTimes;
            IvySdk.lastAppStartTime = System.currentTimeMillis();

            long currentTimeStamp = System.currentTimeMillis();
            // 第一次启动日期
            long firstStartTimestamp = sharedPreferences.getLong("_first_start_timestamp", 0);
            if (firstStartTimestamp == 0) {
                firstStartTimestamp = currentTimeStamp;
                sharedPreferences.edit().putLong("_first_start_timestamp", firstStartTimestamp).apply();
            }
            IvySdk.firstAppStartTime = firstStartTimestamp;


            SharedPreferences paySp = activity.getSharedPreferences("pays", Context.MODE_PRIVATE);
            totalOrders = 0;
            totalRevenues = 0.0f;
            if (paySp != null) {
                totalOrders = paySp.getInt("total_orders", 0);
                totalRevenues = paySp.getFloat("total_revenue", 0.0f);
            }

            Bundle apbundle = new Bundle();
            apbundle.putInt("times", appStartTimes);
            if (totalOrders > 0 && totalRevenues > 0) {
                apbundle.putInt("value", totalOrders);
                apbundle.putFloat("revenue", totalRevenues);
            }
            apbundle.putInt(EventTracker.KEY_EVENT_PRIPORITY, 1);
            eventTracker.logEvent("app_open", apbundle);

            if (appStartTimes == 1) {
                eventTracker.parfkaLog("first_open", null);
            }

            // check summary events settings
            JSONObject gridData = GridManager.getGridData();
            if (gridData == null || !gridData.has("summary_events")) {
                Logger.warning(TAG, "Grid data is null or no summary event settings");
                return;
            }

            JSONObject eventSettings = gridData.optJSONObject("summary_events");
            if (eventSettings == null) {
                Logger.warning(TAG, "eventSettings is null");
                return;
            }

            eventTracker.setSummaryEventSettings(eventSettings);

            // 用户启动里程碑事件
            if (eventSettings.has("op")) {
                JSONArray sent = eventSettings.optJSONArray("op");
                for (int i = 0; i < sent.length(); i++) {
                    if (sent.optInt(i) == appStartTimes) {
                        Bundle bundle = new Bundle();
                        eventTracker.logEvent("op_" + appStartTimes, bundle);
                        break;
                    }
                }
            }

            // 用户留存里程碑事件, 单天留存一个用户只记录一次
            int retentionDays = (int) ((currentTimeStamp - firstStartTimestamp) / (24 * 3600 * 1000));
            if (eventSettings.has("retention")) {
                JSONArray sent = eventSettings.optJSONArray("retention");
                for (int i = 0; i < sent.length(); i++) {
                    int checkDays = sent.optInt(i);
                    if (retentionDays >= checkDays) {
                        if (sharedPreferences.getInt("retention_" + checkDays, 0) != 1) {
                            Bundle bundle = new Bundle();
                            eventTracker.logEvent("retention_" + checkDays, bundle);
                            sharedPreferences.edit().putInt("retention_" + checkDays, 1).apply();
                        }
                    }
                }
            }

        } catch (Throwable ex) {
            Logger.error(TAG, "trackApp Open failed", ex);
        }
    }

    public static void onStart() {
    }

    public static void onStop() {
    }


    public static void pause() {
        if (skipPauseOnce) {
            skipPauseOnce = false;
            return;
        }
        if (!alreadyPaused) {
            alreadyPaused = true;
            alreadyResumed = false;
            Activity activity = getActivity();
            if (adManager != null && activity != null) {
                adManager.onPause(activity);
            }
        }
    }

    public static void onPause() {
        try {
            pause();
            if (eventTracker != null) {
                eventTracker.onPause();
            }

//      Activity activity = getActivity();
//      IronSource.onPause(activity);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    public static void onResume() {
        try {
            resume();
            if (eventTracker != null) {
                eventTracker.onResume();
            }

            //  Activity activity = getActivity();

//      IronSource.onResume(activity);

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void runOnUiThreadCustom(final Runnable r) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(r);
        } else {
            Logger.error(TAG, "Activity is null, thread not able to start");
        }
    }


    private static void resume() {
        if (skipResumeOnce) {
            skipResumeOnce = false;
            return;
        }
        if (!alreadyResumed) {
            alreadyResumed = true;
            alreadyPaused = false;
            Activity activity = getActivity();
            if (adManager != null && activity != null) {
                adManager.onResume(activity);
            }
        }
    }

    public static void setupGamePlayTime(long time) throws Exception {
        mmSetLongValue("sdk_running_time", time);
        mmSetLongValue("sdk_running_date", System.currentTimeMillis());
    }

    public static void onDestroy() {
        Logger.debug(TAG, "OnDestroy called");
        try {
            Activity activity = getActivity();
            if (activity == null) {
                return;
            }
            adManager.onDestroy(activity);
            EventBus.getInstance().removeAllListeners();

            if (eventTracker != null) {
                eventTracker.destroy();
            }

            updateCurrentActivity(null);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        if (xsollaPurchaseImpl != null) {
            xsollaPurchaseImpl.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode == RC_WEBVIEW) {
            return;
        }

        if (requestCode == RC_SIGN_IN || requestCode == RC_SIGN_IN_RESOLUTION) {
            Log.e("login", "onActivityResult google sign");
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleListener == null) {
                Log.e("login", "onActivityResult google sign listener is null");
                Logger.warning(TAG, "No listener resigisted for Google Sign in");
                return;
            }

            if (result == null) {
                Log.e("login", "onActivityResult google Sign in result null");
                Logger.warning(TAG, "Sign in result null");
                if (googleListener != null) {
                    if (googleSignInProcess) {
                        googleListener.onFails();
                        googleSignInProcess = false;
                    }
                    googleListener = null;
                }
                return;
            }

            if (result.isSuccess()) {
                // The signed in account is stored in the result.
                Log.e("login", "onActivityResult google Sign success");
                GoogleSignInAccount signedInAccount = result.getSignInAccount();
                if (signedInAccount != null) {
                    try {
                        if (activity == null) {
                            Log.e("login", "onActivityResult google Sign activity destroyed?");
                            Logger.error(TAG, "activity destroyed? ");
                            return;
                        }

                        Games.getPlayersClient(activity, signedInAccount).getCurrentPlayer().addOnCompleteListener(new OnCompleteListener<Player>() {
                            @Override
                            public void onComplete(@NonNull Task<Player> task) {
                                if (task.isSuccessful()) {
                                    Log.e("login", "onActivityResult google Sign player success");
                                    Player player = task.getResult();
                                    if (player != null) {
                                        String playerId = player.getPlayerId();
                                        String email = signedInAccount.getEmail() != null ? signedInAccount.getEmail() : "";
                                        Logger.debug(TAG, "PlayerID: " + playerId + ", email: " + email);
                                        if (googleSignInProcess) {
                                            googleListener.onSuccess(playerId, email);
                                            googleSignInProcess = false;
                                        }
                                    }
                                    googleListener = null;
                                    initPlayGamePopup();
                                } else {
                                    Log.e("login", "onActivityResult google Sign player failed:" + task.getException());
                                    if (googleListener != null) {
                                        if (googleSignInProcess) {
                                            googleListener.onFails();
                                            googleSignInProcess = false;
                                        }
                                        googleListener = null;
                                    }
                                }
                            }
                        });
                    } catch (Throwable t) {
                        Log.e("login", "onActivityResult google Sign failed t:" + t.getMessage());
                        Logger.error(TAG, "Sign in failed", t);
                        if (googleListener != null) {
                            if (googleSignInProcess) {
                                googleListener.onFails();
                                googleSignInProcess = false;
                            }
                            googleListener = null;
                        }
                    }
                } else {
                    Log.e("login", "onActivityResult google Sign account is null");
                    if (googleListener != null) {
                        if (googleSignInProcess) {
                            googleListener.onFails();
                            googleSignInProcess = false;
                        }
                        googleListener = null;
                    }
                }
            } else {
                Status status = result.getStatus();
                Log.e("login", "onActivityResult google Sign failed:" + result.getStatus().getStatusMessage());
                if (requestCode == RC_SIGN_IN && status.hasResolution()) {
                    Logger.debug(TAG, "startResolutionForResult >>> ");
                    try {
                        status.startResolutionForResult(activity, RC_SIGN_IN_RESOLUTION);
                    } catch (Throwable t) {
                        Logger.error(TAG, "startResolutionForResult failed", t);
                    }
                    return;
                }

                debugToast("Google Sign In Failed: " + status.getStatusCode() + ": " + status.getStatusMessage());
                if (googleListener != null) {
                    if (googleSignInProcess) {
                        googleListener.onFails();
                        googleSignInProcess = false;
                    }
                    googleListener = null;
                }
            }
            return;
        }

        if (requestCode == RC_REQUEST_UPDATE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                // user cancelled the app update
                SharedPreferences sp = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE);
                sp.edit().putLong("last_check_update_time", System.currentTimeMillis()).apply();
            }
            return;
        }
    }

    public static void closeBanners() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lastBannerShowCalledTimes = 0;
                Log.e("parfka", "Close Banner");
                Logger.debug(TAG, "Close Banner");
                if (adManager != null) {
                    try {
                        adManager.hideBanners();
                    } catch (Throwable t) {
                        // ignore
                    }
                }
            }
        });
    }

    public static void debugToast(final String message) {
        Logger.debug(TAG, message);

    }

    public static void showToast(final String message) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!activity.isFinishing()) {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static final String TAG_BANNER_VIEW = "banner_view";

    private static long lastBannerShowCalledTimes = 0;

    //## 广告相关接口
    // if banner already present, and displayed less than 10s, just ignore the new call
    public static void showBannerAd(final int adPos) {
        if (!adEnabled) {
            return;
        }
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (adManager == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FrameLayout rootView = (FrameLayout) activity.getWindow().getDecorView().getRootView();

                View bannerView = rootView.findViewWithTag(TAG_BANNER_VIEW);
                if (bannerView != null) {
                    if (System.currentTimeMillis() - lastBannerShowCalledTimes < 10000) {
                        return;
                    }
                    rootView.removeView(bannerView);
                }

                FrameLayout rl = new FrameLayout(activity);
                int gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
                int internalBannerPosition = -1;
                switch (adPos) {
                    case POS_CENTER:
                        gravity = Gravity.CENTER;
                        break;

                    case POS_CENTER_BOTTOM:
                        gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_BOTTOM;
                        break;

                    case POS_CENTER_TOP:
                        gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_TOP;
                        break;

                    case POS_LEFT_BOTTOM:
                        gravity = Gravity.START | Gravity.BOTTOM;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_BOTTOM;
                        break;

                    case POS_LEFT_TOP:
                        gravity = Gravity.START | Gravity.TOP;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_TOP;
                        break;

                    case POS_RIGHT_BOTTOM:
                        gravity = Gravity.END | Gravity.BOTTOM;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_BOTTOM;
                        break;

                    case POS_RIGHT_TOP:
                        gravity = Gravity.END | Gravity.TOP;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_TOP;
                        break;
                }

                try {
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dpToPx(main.get(), 60), gravity);
                    rl.setTag(TAG_BANNER_VIEW);
                    rootView.addView(rl, lp);

                    adManager.showBanners(activity, rl);
                    adManager.setBannerPosition(internalBannerPosition, activity);

                    lastBannerShowCalledTimes = System.currentTimeMillis();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    // 不重复创建banner 容器
    public static void showBannerNonExistent(final int adPos) {
        if (!adEnabled) {
            return;
        }
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (adManager == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FrameLayout rootView = (FrameLayout) activity.getWindow().getDecorView().getRootView();

                View bannerView = rootView.findViewWithTag(TAG_BANNER_VIEW);
                if (bannerView != null) {
                    return;
                }

                FrameLayout rl = new FrameLayout(activity);
                int gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
                int internalBannerPosition = -1;
                switch (adPos) {
                    case POS_CENTER:
                        gravity = Gravity.CENTER;
                        break;

                    case POS_CENTER_BOTTOM:
                        gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_BOTTOM;
                        break;

                    case POS_CENTER_TOP:
                        gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_TOP;
                        break;

                    case POS_LEFT_BOTTOM:
                        gravity = Gravity.START | Gravity.BOTTOM;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_BOTTOM;
                        break;

                    case POS_LEFT_TOP:
                        gravity = Gravity.START | Gravity.TOP;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_TOP;
                        break;

                    case POS_RIGHT_BOTTOM:
                        gravity = Gravity.END | Gravity.BOTTOM;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_BOTTOM;
                        break;

                    case POS_RIGHT_TOP:
                        gravity = Gravity.END | Gravity.TOP;
                        internalBannerPosition = BannerAdManager.BANNER_POSITION_TOP;
                        break;
                }

                try {
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Util.dpToPx(main.get(), 60), gravity);
                    rl.setTag(TAG_BANNER_VIEW);
                    rootView.addView(rl, lp);

                    adManager.showBanners(activity, rl);
                    adManager.setBannerPosition(internalBannerPosition, activity);

                    lastBannerShowCalledTimes = System.currentTimeMillis();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        });
    }

    public static void fetchRewardedInterstitial() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    adManager.fetchRewardedInterstitial(activity);
                }
            }
        });
    }

    public static void fetchInterstitial() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    adManager.fetchInterstitial(activity);
                }
            }
        });
    }

    public static void showRewardedInterstitial() {
        showRewardedInterstitial("default");
    }

    public static void showRewardedInterstitial(final String tag) {
        if (!adEnabled) {
            Logger.debug(TAG, "Ad disabled");
            return;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    adManager.showRewardedInterstitial(activity, tag);
                }
            }
        });
    }

    public static void showInterstitialAd() {
        showInterstitialAd("default");
    }

    public static void showInterstitialAd(final String tag) {
        if (!adEnabled) {
            Logger.debug(TAG, "Ad disabled");
            return;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        boolean interstitial_disabled = getRemoteConfigAsBoolean("interstitial_disabled");
        if (interstitial_disabled) {
            Logger.warning(TAG, "Interstitial disabled by config. ignore this display");
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    adManager.showInterstitial(activity, tag);
                }
            }
        });
    }

    public static void closeNativeAd() {
        Logger.debug(TAG, "closeNativeAd called");

        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    try {
                        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                        View nativeBarView = decorView.findViewWithTag("_native_bar");

                        if (nativeBarView != null) {
                            decorView.removeView(nativeBarView);
                        }

                        adManager.closeNativeAd();
                    } catch (Throwable t) {

                    }
                }
            }
        });

    }

    public static void showNativeAd(final View holerView) {
        Logger.debug(TAG, "showNativeAd called,");
        if (!adEnabled) {
            IvySdk.logEvent("click_ad_disabled", null);
            return;
        }
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (adManager == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Map<String, View> adViews = new HashMap<>();
                if (holerView != null) {
                    adViews.put("NativeAdsContainerView", holerView);
                }

                Map<String, Object> params = new HashMap<>();
                try {
                    boolean result = adManager.showNativeAd(activity, adViews, params);
                    if (!result) {
                        Log.d(TAG, "Native ad displayed failed, no fill");
                    }
                } catch (Exception ex) {
                    Logger.error(TAG, "showNativeAd exception", ex);
                }
            }
        });
    }

    public static void showNativeAd(final int x, final int y, final int width, final int height, final int sw, final int sh) {
        Logger.debug(TAG, "showNativeAd called, x: " + x + ",y : " + y + ", width: " + width + "， height: " + height + ", sw: " + sw + ", sh: " + sh);
        if (!adEnabled) {
            IvySdk.logEvent("click_ad_disabled", null);
            return;
        }
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (adManager == null) {
            return;
        }
        JSONObject gridData = GridManager.getGridData();
        int designedScreenWidth = gridData.optInt("designedScreenWidth");
        if (sw != 0) {
            designedScreenWidth = sw;
        }
        int designedScreenHeight = gridData.optInt("designedScreenHeight");
        if (sh != 0) {
            designedScreenHeight = sh;
        }

        int screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = activity.getResources().getDisplayMetrics().heightPixels;

        float scaleX = screenWidth * 1.0f / designedScreenWidth;
        float scaleY = screenHeight * 1.0f / designedScreenHeight;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
                View holerView = decorView.findViewWithTag("_native_bar");
                if (holerView == null) {
                    holerView = LayoutInflater.from(activity).inflate(R.layout.native_static_ad, decorView, false);
                    holerView.setTag("_native_bar");

                    decorView.addView(holerView, (int) (width * scaleX), (int) (height * scaleY));
                }
                holerView.setTranslationX(x * scaleX);
                holerView.setTranslationY(y * scaleY);

                Map<String, View> adViews = new HashMap<>();

                View container = holerView.findViewById(R.id.main_content);
                if (container != null) {
                    adViews.put("NativeAdsContainerView", container);
                }

                Map<String, Object> params = new HashMap<>();
                try {
                    boolean result = adManager.showNativeAd(activity, adViews, params);
                    if (!result) {
                        Log.d(TAG, "Native ad displayed failed, no fill");
                    }
                } catch (Exception ex) {
                    Logger.error(TAG, "showNativeAd exception", ex);
                }
            }
        });
    }

    public static void fetchRewardVideo() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    adManager.fetchRewarded(activity);
                }
            }
        });
    }

    public static boolean isNativeAdLoaded() {
        if (adManager != null) {
            return adManager.isNativeAdLoaded();
        }
        return false;
    }

    public static void fetchNativeAd() {
        Activity activity = getActivity();
        if (adManager != null && activity != null) {
            adManager.fetchNativeAd(activity);
        }
    }

    public static void showRewardAd(String tag) {
        Activity activity = getActivity();
        if (activity == null) {
            Logger.error(TAG, "Activity is null, showRewardAd not possible");
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    adManager.showRewarded(activity, tag);
                }
            }
        });
    }

    public static String loadGridData() {
        Activity activity = getActivity();
        if (activity == null) {
            return "{}";
        }
        return Util.retrieveData(activity, GridManager.FILE_JSON_RESPONSE);
    }

    /**
     * IVY的划屏全屏广告。
     */
    public static void showPromoteAd(String tag) {
        Logger.debug(TAG, "showPromoteAd()");
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    adManager.showPromoteAd(activity);
                }
            }
        });
    }

    /**
     * 在当前屏幕显示一个图标，图标可以打开ivy delicious 推广链接。
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param promotepackage 要推广的包名，如果包名为null,将在promote中随机选择一个。优先选择gif,没有gif显示icon
     */
    public static void showDeliciousIcon(int x, int y, int width, int height, String promotepackage) {
        Logger.debug(TAG, "showDeliciousIcon ");
        boolean enable_delicious = GridManager.getGridData().optBoolean("enable_delicious", true);
        if (!enable_delicious) {
            return;
        }

        if (adManager != null) {
            adManager.showDeliciousIcon(x, y, width, height, promotepackage);
        }
    }

    public static void closeDeliciousIconAd() {
        if (adManager != null) {
            adManager.closeDeliciousIconAd();
        }
    }

    /**
     * 在屏幕位置显示delicious banner
     *
     * @param x
     * @param y
     * @param w
     * @param h
     * @param defaultpackage
     */
    public static void showDeliciousBanner(int x, int y, int w, int h, String defaultpackage) {
        boolean enable_delicious = GridManager.getGridData().optBoolean("enable_delicious", true);
        if (enable_delicious && adManager != null) {
            adManager.showDeliciousBanner(x, y, w, h, defaultpackage);
        }
    }

    public static void closeDeliciousBanner() {
        if (adManager != null) {
            adManager.closeDeliciousBanner();
        }
    }

    /**
     * 启动delicious视频集
     */
    public static void showDeliciousAd() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adManager != null) {
                    adManager.showDeliciousAd();
                }
            }
        });
    }


    /**
     * 检查是否有退出广告位，如果有，退出广告位完毕,退出游戏
     */
    public static void onQuit() {
        if (adManager != null) {
            adManager.onQuit();
        }
    }

    public static void getCreativePath(String url, DownloadFeedback downloadFeedback) {
        if (adManager != null) {
            adManager.getCreativePath(url, downloadFeedback);
        }
    }

    /**
     * 显示推广墙
     */
    public static void moreGame() {
        if (adManager != null) {
            adManager.moreGame();
        }
    }

    /**
     * IVY内部交叉推广的形式。 交叉推广内容从grid数据的promote中读取
     *
     * 1. 全屏广告，可以滑动的从promote中提取的左右滑动的全屏广告  showPromoteAd
     * 2. GIF ICON, 客户设定大小，及屏幕绝对位置的GIF动画，点击GIF动画进入一个新的Activity,播放视频广告动画 showDeliciousIcon
     * 3. GIF BANNER 显示所有BANNER动画推广 showDeliciousBanner
     * 4. 全屏包含GIF BANNER的视频广告 showDeliciousAd
     * 5. 游戏退出广告位, 显示自定义的退出插屏及更多游戏链接 onQuit
     * 6. 游戏墙 moreGame
     */
    /**
     * Callback passed to the sdkInitialize function.
     */
    public interface InitializeCallback {
        /**
         * Called when the sdk has been initialized.
         */
        void onInitialized();

        void onRemoteConfigUpdated();
    }

    public static boolean isDebugMode() {
        return IvyAds.isDebugMode();
    }

    public static void setTestMode(boolean flag) {
        IvyAds.setTestMode(flag);
    }

    public static void setDebug(final boolean flag) {
        IvyAds.setDebugMode(flag);
    }

    public static void tryStartInAppReview() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        String inAppReviewUrl = GridManager.getGridData().optString("inapp.review.url", "");
        if (!"".equals(inAppReviewUrl)) {
            AndroidSdk.showWebView(activity.getString(R.string.user_survey), inAppReviewUrl);
        }
    }

    private static void openPlaystoreInBrowser(Activity activity) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                IvyUtils.openPlayStore(activity, activity.getPackageName(), "rate", null);
            }
        });
    }

    public static void rate(int rateStar) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        openPlaystoreInBrowser(activity);


    }

    public static PurchaseManager getPurchaseManager(@NonNull Context context) {
        return new PurchaseManagerImpl(context, EventBus.getInstance(), eventTracker);
    }

    public static void pay(String productId, String productName, String payLoad) {
        Logger.debug(TAG, "start buying: " + productId + ", payload: " + payLoad);
        Activity activity = getActivity();
        if (activity == null) {
            Logger.error(TAG, "Activity is null, pay impossible");
            return;
        }
        if (!IvyUtils.isOnline(activity)) {
            Logger.error(TAG, "Network not connected");
            return;
        }
        if (purchaseManagerWrapper != null) {
            purchaseManagerWrapper.setBillItemName(productName);
            purchaseManagerWrapper.startBuying(productId, payLoad);
        }
    }

    public static boolean checkGooglePlayService() {
        Activity activity = getActivity();
        if (activity == null) {
            return false;
        }
        int resultCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            return false;
        }
        return true;
    }

    private static void initPlayGamePopup() {
        boolean disablePlayPopup = getGridConfigBoolean("disablePlayPopup", false);
        if (disablePlayPopup) {
            return;
        }

        Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        try {
            GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(activity);
            if (signInAccount != null) {
                View rootView = activity.getWindow().getDecorView().getRootView();
                if (rootView != null) {
                    GamesClient gamesClient = Games.getGamesClient(activity, signInAccount);
                    gamesClient.setViewForPopups(rootView);
                    gamesClient.setGravityForPopups(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                }
            }
        } catch (Throwable t) {
            Logger.error(TAG, "initPlayGamePopup failed");
        }
    }

    public static void logoutGoogle(GoogleListener listener) {
        Logger.debug(TAG, "logoutGoogle");
        if (!checkGooglePlayService()) {
            if (listener != null) {
                listener.onFails();
            }
            return;
        }

        Activity activity = getActivity();
        if (activity == null) {
            if (listener != null) {
                listener.onFails();
            }
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
                GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
                if (account == null) {
                    Logger.debug(TAG, "already signedOut");
                    if (listener != null) {
                        listener.onSuccess(null, null);
                    }
                } else {
                    GoogleSignInClient signInClient = GoogleSignIn.getClient(activity, signInOptions);
                    signInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                if (listener != null) {
                                    listener.onSuccess(null, null);
                                }
                            } else {
                                if (listener != null) {
                                    listener.onFails();
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    private static GoogleListener googleListener = null;

    private volatile static boolean googleSignInProcess = false;

    public static void slientLoginGoogle(GoogleListener listener) {
        Logger.debug(TAG, "Configure login google");
        Log.e("login", "Configure login google");
        if (!checkGooglePlayService()) {
            Log.e("login", "Google play service not available");
            Logger.warning(TAG, "Google play service not available");
            if (listener != null) {
                listener.onFails();
            }
            return;
        }

        Activity activity = getActivity();
        if (activity == null) {
            Log.e("login", "Activity not initialized? google Signin is not possible.");
            Logger.error(TAG, "Activity not initialized? google Signin is not possible.");
            if (listener != null) {
                listener.onFails();
            }
            return;
        }

        final int googleSignInTimeout = getGridConfigInt("googleSignInTimeout", 0);
        if (googleSignInTimeout > 0) {
            try {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (googleSignInProcess) {
                            Log.e("login", "sign in timeout.");
                            if (listener != null) {
                                listener.onFails();
                            }
                            googleSignInProcess = false;
                        }
                    }
                }, googleSignInTimeout * 1000);
            } catch (Throwable ignored) {
            }
        }

        googleSignInProcess = true;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);

                boolean requireProfile = getGridConfigBoolean("requireProfile", false);
                GoogleSignInOptions.Builder signInOptionsBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
                if (requireProfile) {
                    Logger.debug(TAG, "requireProfile : true");
                    signInOptionsBuilder.requestEmail();
                    signInOptionsBuilder.requestId();
                }


                String googleWebServerClientId = getGridConfigString("google_web_client_id");
                if (googleWebServerClientId != null && !"".equals(googleWebServerClientId)) {
                    signInOptionsBuilder.requestServerAuthCode(googleWebServerClientId);
                    signInOptionsBuilder.requestIdToken(googleWebServerClientId);
                }

                GoogleSignInOptions signInOptions = signInOptionsBuilder.build();

                boolean checkGoogleAccountAuthCode = getGridConfigBoolean("checkGoogleAccountAuthCode", false);
                boolean authCodeValid = true;
                if (account != null && checkGoogleAccountAuthCode) {
                    String authCode = account.getServerAuthCode();
                    if (authCode == null || "".equals(authCode)) {
                        Logger.debug(TAG, "AuthCode invalid, sign in required");
                        Log.e("login", "AuthCode invalid, sign in required");
                        authCodeValid = false;
                    }
                }
                if (account != null && GoogleSignIn.hasPermissions(account, signInOptions.getScopeArray()) && authCodeValid) {
                    Logger.debug(TAG, "already sign in google");
                    Log.e("login", "already sign in google");
                    if (listener != null) {
                        Games.getPlayersClient(activity, account).getCurrentPlayer().addOnCompleteListener(new OnCompleteListener<Player>() {
                            @Override
                            public void onComplete(@NonNull Task<Player> task) {
                                if (task.isSuccessful()) {
                                    Player player = task.getResult();
                                    Log.e("login", "player login success");
                                    if (player != null) {
                                        String playerId = player.getPlayerId();
                                        String email = account.getEmail() != null ? account.getEmail() : "";
                                        Logger.debug(TAG, "PlayerID: " + playerId + ", email: " + email);
                                        Log.e("login", "PlayerID: " + playerId + ", email: " + email);
                                        if (googleSignInProcess) {
                                            listener.onSuccess(playerId, email);
                                            googleSignInProcess = false;
                                        }
                                    }
                                    initPlayGamePopup();
                                } else {
                                    Log.e("login", "player login failed:" + task.getException());
                                    if (googleSignInProcess) {
                                        listener.onFails();
                                        googleSignInProcess = false;
                                    }
                                }
                            }
                        });
                    }
                } else {
                    Logger.debug(TAG, "Google Signin start >>> ");
                    Log.e("login", "Google Signin start >>>");
                    GoogleSignInClient signInClient = GoogleSignIn.getClient(activity, signInOptions);
                    signInClient.silentSignIn().addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() {
                        @Override
                        public void onComplete(@NonNull Task<GoogleSignInAccount> task) {

                            Logger.debug(TAG, "Google sign in onComplete: " + task.isSuccessful());
                            if (task.isSuccessful()) {
                                Log.e("login", "Google Signin success");
                                if (listener != null) {
                                    GoogleSignInAccount signInAccount = task.getResult();
                                    if (signInAccount != null) {
                                        Games.getPlayersClient(activity, signInAccount).getCurrentPlayer().addOnCompleteListener(new OnCompleteListener<Player>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Player> task) {
                                                if (task.isSuccessful()) {
                                                    Log.e("login", "player Signin success");
                                                    Player player = task.getResult();
                                                    if (player != null) {
                                                        String playerId = player.getPlayerId();
                                                        String email = signInAccount.getEmail() != null ? signInAccount.getEmail() : "";
                                                        Logger.debug(TAG, "PlayerID: " + playerId + ", email: " + email);
                                                        Log.e("login", "PlayerID: " + playerId + ", email: " + email);
                                                        if (googleSignInProcess) {
                                                            listener.onSuccess(playerId, email);
                                                            googleSignInProcess = false;
                                                        }
                                                    }
                                                    initPlayGamePopup();
                                                } else {
                                                    Log.e("login", "player Signin failed:" + task.getException());
                                                    if (googleSignInProcess) {
                                                        listener.onFails();
                                                        googleSignInProcess = false;
                                                    }
                                                }
                                            }
                                        });
                                    }
                                }
                            } else {
                                Log.e("login", "Error sign in google:" + task.getException());
                                Logger.error(TAG, "Error sign in google ", task.getException());
                                if (googleSignInTimeout > 0) {
                                    if (listener != null) {
                                        if (googleSignInProcess) {
                                            listener.onFails();
                                            googleSignInProcess = false;
                                        }
                                        return;
                                    }
                                }
                                googleListener = listener;
                                GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
                                boolean requireProfile = getGridConfigBoolean("requireProfile", false);
                                String googleWebServerClientId = getGridConfigString("google_web_client_id");

                                GoogleSignInOptions.Builder builder = null;
                                if (requireProfile || (googleWebServerClientId != null && !"".equals(googleWebServerClientId))) {
                                    builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
                                    if (requireProfile) {
                                        builder.requestEmail();
                                        builder.requestId();
                                    }
                                    if (googleWebServerClientId != null && !"".equals(googleWebServerClientId)) {
                                        builder.requestServerAuthCode(googleWebServerClientId);
                                        builder.requestIdToken(googleWebServerClientId);
                                    }
                                    signInOptions = builder.build();
                                }
                                GoogleSignInClient signInClient = GoogleSignIn.getClient(activity, signInOptions);
                                Intent intent = signInClient.getSignInIntent();
                                activity.startActivityForResult(intent, RC_SIGN_IN);
                                Log.e("login", "retry login");
                            }
                        }
                    });
                }
            }
        });
    }

    public static Activity getActivity() {
        if (main == null) {
            return null;
        }
        return main.get();
    }

    public static void showGoogleAchievement() {
        if (!checkGooglePlayService()) {
            return;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
                GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
                if (account != null && GoogleSignIn.hasPermissions(account, signInOptions.getScopeArray())) {
                    AchievementsClient achievementsClient = Games.getAchievementsClient(activity, account);
                    achievementsClient
                            .getAchievementsIntent()
                            .addOnSuccessListener(new OnSuccessListener<Intent>() {
                                @Override
                                public void onSuccess(Intent intent) {
                                    try {
                                        activity.startActivityForResult(intent, RC_ACHIEVEMENT_UI);
                                    } catch (Throwable t) {
                                        t.printStackTrace();
                                    }
                                }
                            });
                }
            }
        });
    }

    public static void setGoogleAchievementSteps(final String id, int step, GoogleListener googleListener) {
        if (!checkGooglePlayService()) {
            if (googleListener != null) {
                googleListener.onFails();
            }
            return;
        }
        if (googleAchievements == null || !googleAchievements.has(id)) {
            Logger.debug(TAG, "No google achievement, id: " + id);
            if (googleListener != null) {
                googleListener.onFails();
            }
            return;
        }


        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(CONTEXT);
        GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
        if (account != null && GoogleSignIn.hasPermissions(account, signInOptions.getScopeArray())) {
            AchievementsClient achievementsClient = Games.getAchievementsClient(CONTEXT, account);
            JSONObject achievement = googleAchievements.optJSONObject(id);
            boolean incremental = achievement.optBoolean("incremental");
            String achievementId = achievement.optString("id");
            if (incremental) {
                achievementsClient.setStepsImmediate(achievementId, step).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Logger.error(TAG, "Step Achievement:", e);
                    }
                }).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (googleListener != null) {
                            if (task.isSuccessful()) {
                                googleListener.onSuccess(account.getId(), account.getEmail());
                            } else {
                                googleListener.onFails();
                            }
                        }
                    }
                });
            } else {
                achievementsClient.unlockImmediate(achievementId).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Logger.error(TAG, "unlock Achievement exception", e);
                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (googleListener != null) {
                            if (task.isSuccessful()) {
                                googleListener.onSuccess(account.getId(), account.getEmail());
                            } else {
                                googleListener.onFails();
                            }
                        }
                    }
                });
            }
        } else {
            if (googleListener != null) {
                googleListener.onFails();
            }
        }

    }

    public static void updateGoogleAchievement(final String id, int step, GoogleListener googleListener) {
        if (!checkGooglePlayService()) {
            if (googleListener != null) {
                googleListener.onFails();
            }
            return;
        }
        if (googleAchievements == null || !googleAchievements.has(id)) {
            Logger.debug(TAG, "No google achievement, id: " + id);
            if (googleListener != null) {
                googleListener.onFails();
            }
            return;
        }

        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(CONTEXT);
        GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
        if (account != null && GoogleSignIn.hasPermissions(account, signInOptions.getScopeArray())) {
            AchievementsClient achievementsClient = Games.getAchievementsClient(CONTEXT, account);
            JSONObject achievement = googleAchievements.optJSONObject(id);
            boolean incremental = achievement.optBoolean("incremental");
            String achievementId = achievement.optString("id");
            if (incremental) {
                achievementsClient.incrementImmediate(achievementId, step).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Logger.error(TAG, "Step Achievement:", e);
                    }
                }).addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (googleListener != null) {
                            if (task.isSuccessful()) {
                                googleListener.onSuccess(account.getId(), account.getEmail());
                            } else {
                                googleListener.onFails();
                            }
                        }
                    }
                });
            } else {
                achievementsClient.unlockImmediate(achievementId).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Logger.error(TAG, "unlock Achievement exception", e);
                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (googleListener != null) {
                            if (task.isSuccessful()) {
                                googleListener.onSuccess(account.getId(), account.getEmail());
                            } else {
                                googleListener.onFails();
                            }
                        }
                    }
                });
            }
        } else {
            if (googleListener != null) {
                googleListener.onFails();
            }
        }

    }

    public static void notifyGoogleLeaderBoardRank(String id) {
        final Activity activity = main.get();
        if (activity == null) {
            return;
        }

        if (googleLeaderBoards == null || !googleLeaderBoards.has(id)) {
            return;
        }

        String googleLeaderboardId = googleLeaderBoards.optJSONObject(id).optString("id");
        if (googleLeaderboardId == null) {
            return;
        }

        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
        if (account == null) {
            return;
        }
        Games.getLeaderboardsClient(activity, account).loadCurrentPlayerLeaderboardScore(googleLeaderboardId, LeaderboardVariant.TIME_SPAN_ALL_TIME, LeaderboardVariant.COLLECTION_PUBLIC).addOnSuccessListener(new OnSuccessListener<AnnotatedData<LeaderboardScore>>() {
            @Override
            public void onSuccess(AnnotatedData<LeaderboardScore> leaderboardScoreAnnotatedData) {

            }
        });
    }

    public static void updateGoogleLeaderBoard(String id, final long value, GoogleListener googleListener) {
        if (!checkGooglePlayService()) {
            Logger.debug(TAG, "Google Play service not available");
            if (googleListener != null) {
                googleListener.onFails();
            }
            return;
        }

        if (googleLeaderBoards == null || !googleLeaderBoards.has(id)) {
            Logger.debug(TAG, "no leaderboard found for: " + id);
            if (googleListener != null) {
                googleListener.onFails();
            }
            return;
        }

        String googleLeaderboardId = googleLeaderBoards.optJSONObject(id).optString("id");
        Logger.debug(TAG, "updateGoogleLeaderBoard called, id: " + googleLeaderboardId);

        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(CONTEXT);
        if (account != null) {
            Games.getLeaderboardsClient(CONTEXT, account).submitScoreImmediate(googleLeaderboardId, value).addOnCompleteListener(new OnCompleteListener<ScoreSubmissionData>() {
                @Override
                public void onComplete(@NonNull Task<ScoreSubmissionData> task) {
                    if (task.isSuccessful()) {
                        Logger.debug(TAG, "submit score success");
                        if (googleListener != null) {
                            googleListener.onSuccess(account.getId(), account.getEmail());
                        }
                    } else {
                        Exception exception = task.getException();
                        if (exception != null) {
                            Logger.error(TAG, "submitscore exception", exception);
                        }
                        if (googleListener != null) {
                            googleListener.onFails();
                        }
                    }
                }
            });
        } else {
            if (googleListener != null) {
                googleListener.onFails();
            }
        }
    }

    public static void displayGameLeaderboards() {
        final Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!checkGooglePlayService()) {
                    Toast.makeText(activity, activity.getString(R.string.google_leaderboard_not_available), Toast.LENGTH_SHORT).show();
                    return;
                }
                // auto login google play service and display the leaderboards
                GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
                final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(activity);
                if (account != null && GoogleSignIn.hasPermissions(account, signInOptions.getScopeArray())) {
                    // Already signed in.
                    // The signed in account is stored in the 'account' variable.
                    Games.getLeaderboardsClient(activity, account).getAllLeaderboardsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
                        @Override
                        public void onSuccess(Intent intent) {
                            if (activity != null) {
                                try {
                                    activity.startActivityForResult(intent, RC_SIGN_IN + 2);
                                } catch (Throwable t) {
                                    t.printStackTrace();
                                }
                            }
                        }
                    });
                } else {
                    // Haven't been signed-in before. Try the silent sign-in first.
                    GoogleSignInClient signInClient = GoogleSignIn.getClient(activity, signInOptions);
                    signInClient
                            .silentSignIn().addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() {
                                @Override
                                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                                    if (task.isSuccessful()) {
                                        // The signed in account is stored in the task's result.
                                        GoogleSignInAccount signedInAccount = task.getResult();
                                        if (signedInAccount != null) {
                                            Games.getLeaderboardsClient(activity, signedInAccount).getAllLeaderboardsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
                                                @Override
                                                public void onSuccess(Intent intent) {
                                                    if (activity != null) {
                                                        try {
                                                            activity.startActivityForResult(intent, RC_SIGN_IN + 2);
                                                        } catch (Throwable ex) {
                                                            ex.printStackTrace();
                                                        }
                                                    }
                                                }
                                            });
                                        }
                                    } else {
                                        // Player will need to sign-in explicitly using via UI.
                                        // See [sign-in best practices](http://developers.google.com/games/services/checklist) for guidance on how and when to implement Interactive Sign-in,
                                        // and [Performing Interactive Sign-in](http://developers.google.com/games/services/android/signin#performing_interactive_sign-in) for details on how to implement
                                        // Interactive Sign-in.
                                        GoogleSignInClient signInClient = GoogleSignIn.getClient(activity, GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
                                        Intent intent = signInClient.getSignInIntent();
                                        activity.startActivityForResult(intent, RC_SIGN_IN);
                                    }
                                }
                            });
                }
            }
        });
    }


    public static boolean haveRewardAd() {
        if (adManager != null) {
            return adManager.haveRewardAd();
        }
        return false;
    }

    public static boolean haveInterstitial() {
        if (adManager != null) {
            return adManager.haveInterstitial();
        }
        return false;
    }

    public static boolean haveRewardedInterstitial() {
        if (adManager != null) {
            return adManager.haveRewardedInterstitial();
        }
        return false;
    }

    public static boolean haveBanner() {
        return true;
    }

    public static boolean haveNative() {
        if (adManager != null) {
            return adManager.haveNative();
        }
        return false;
    }


    public static boolean isAdEnabled() {
        return adEnabled;
    }

    /**
     * SDK自定义事件接口. 根据grid的配置，自定义事件将保存到firebase,
     * 和 ivy.
     *
     * @param eventName
     * @param bundle
     */
    public static void logEvent(String eventName, Bundle bundle) {
        if (eventTracker != null) {
            eventTracker.logEvent(eventName, bundle);
        }
    }

    public static void logToFirebase(String eventName, Bundle bundle) {
        if (eventTracker != null) {
            eventTracker.logToFirebase(eventName, bundle);
        }
    }

    public static void logIvyEvent(String eventName, Bundle bundle) {
        if (eventTracker != null) {
            eventTracker.parfkaLog(eventName, bundle);
        }
    }

    public static void logToFacebook(String eventName, Bundle bundle) {
        if (eventTracker != null) {
            eventTracker.logToFacebook(eventName, bundle);
        }
    }

    public static void logToConversation(String eventName, Bundle bundle) {
        if (eventTracker != null) {
            eventTracker.logToConversation(eventName, bundle);
        }
    }

    public static void logToAppsflyer(String eventName, Bundle bundle) {
        if (eventTracker != null) {
            eventTracker.logToAppsflyer(eventName, bundle);
        }
    }

    public static void trackEngagement(long seconds) {
        if (eventTracker != null) {
            eventTracker.trackEngagement(seconds);
        }
    }

    public static int getRemoteConfigAsInt(String key) {
        if (mFirebaseRemoteConfig != null) {
            return (int) (mFirebaseRemoteConfig.getLong(key));
        }

        JSONObject gridData = GridManager.getGridData();
        if (gridData == null) {
            return 0;
        }
        JSONObject remoteconfig = gridData.optJSONObject("remoteconfig");
        if (remoteconfig != null && remoteconfig.has(key)) {
            return remoteconfig.optInt(key);
        }
        return 0;
    }

    public static long getRemoteConfigAsLong(String key) {
        if (mFirebaseRemoteConfig != null) {
            return mFirebaseRemoteConfig.getLong(key);
        }

        JSONObject gridData = GridManager.getGridData();
        if (gridData == null) {
            return 0L;
        }
        JSONObject remoteconfig = gridData.optJSONObject("remoteconfig");
        if (remoteconfig != null && remoteconfig.has(key)) {
            return remoteconfig.optLong(key);
        }
        return 0L;
    }

    public static String getFirebaseRemoteConfigAsString(String key, String defaultValue) {
        try {
            if (mFirebaseRemoteConfig == null) {
                Logger.error(TAG, "Remote Config not initialized");
                return defaultValue;
            }
            String valueInFirebase = mFirebaseRemoteConfig.getString(key);
            if (!FirebaseRemoteConfig.DEFAULT_VALUE_FOR_STRING.equals(valueInFirebase)) {
                return valueInFirebase;
            }
            return defaultValue;
        } catch (Throwable t) {
            Logger.error(TAG, "getFirebaseRemoteConfigAsString exception", defaultValue);
            return defaultValue;
        }
    }

    public static double getFirebaseRemoteConfigAsDouble(String key, double defaultValue) {
        try {
            if (mFirebaseRemoteConfig == null) {
                return defaultValue;
            }
            double valueInFirebase = mFirebaseRemoteConfig.getDouble(key);
            if (valueInFirebase > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return valueInFirebase;
            }
            return defaultValue;
        } catch (Throwable t) {
            Logger.error(TAG, "getFirebaseRemoteConfigAsDouble exception", defaultValue);
            return defaultValue;
        }
    }

    public static double getRemoteConfigAsDouble(String key) {
        if (mFirebaseRemoteConfig != null) {
            return mFirebaseRemoteConfig.getDouble(key);
        }

        JSONObject gridData = GridManager.getGridData();
        if (gridData == null) {
            return 0;
        }
        JSONObject remoteconfig = gridData.optJSONObject("remoteconfig");
        if (remoteconfig != null && remoteconfig.has(key)) {
            return remoteconfig.optDouble(key);
        }
        return 0;
    }

    public static boolean getRemoteConfigAsBoolean(String key) {
        if (mFirebaseRemoteConfig != null) {
            return mFirebaseRemoteConfig.getBoolean(key);
        }

        JSONObject gridData = GridManager.getGridData();
        if (gridData == null) {
            return false;
        }
        JSONObject remoteconfig = gridData.optJSONObject("remoteconfig");
        if (remoteconfig != null && remoteconfig.has(key)) {
            return remoteconfig.optBoolean(key);
        }
        return false;
    }

    private static final String EMPTY = "";

    public static String getRemoteConfigAsString(String key) {
        if (mFirebaseRemoteConfig != null) {
            return mFirebaseRemoteConfig.getString(key);
        }

        JSONObject gridData = GridManager.getGridData();
        if (gridData == null) {
            return EMPTY;
        }
        JSONObject remoteconfig = gridData.optJSONObject("remoteconfig");
        if (remoteconfig != null && remoteconfig.has(key)) {
            return remoteconfig.optString(key);
        }
        return EMPTY;
    }

    public static boolean hasGridConfig(String key) {
        if (GridManager.getGridData() == null) {
            return false;
        }
        return GridManager.getGridData().has(key);
    }

    public static JSONObject getGridConfigJson(String key) {
        if (GridManager.getGridData() == null) {
            return null;
        }

        return GridManager.getGridData().optJSONObject(key);
    }

    public static boolean getGridConfigBoolean(String key, boolean defaultValue) {
        if (GridManager.getGridData() == null) {
            return false;
        }
        return GridManager.getGridData().optBoolean(key, defaultValue);
    }

    public static int getGridConfigInt(String key, int defaultValue) {
        if (GridManager.getGridData() == null) {
            return defaultValue;
        }
        return GridManager.getGridData().optInt(key, defaultValue);
    }

    public static boolean getGridConfigBoolean(String key) {
        if (GridManager.getGridData() == null) {
            return false;
        }
        return GridManager.getGridData().optBoolean(key);
    }

    public static String getGridConfigString(String key) {
        if (GridManager.getGridData() == null) {
            return null;
        }
        return GridManager.getGridData().optString(key);
    }

    public static String getGridConfigString(String key, String defaultValue) {
        if (GridManager.getGridData() == null) {
            return null;
        }
        return GridManager.getGridData().optString(key, defaultValue);
    }

    public static boolean isGoogleLogin() {
        Activity activity = getActivity();
        if (activity == null) {
            return false;
        }
        return GoogleSignIn.getLastSignedInAccount(activity) != null;
    }

    public static void setUserProperty(String key, String value) {
        Logger.debug(TAG, "setUserProperty : " + key + " >>> " + value);
        if (eventTracker != null) {
            eventTracker.setUserProperty(key, value);
        }
    }

    public static void setUserID(String userID) {
        if (eventTracker != null) {
            eventTracker.setUserProperty("af_customer_user_id", userID);
        }
    }

    public static boolean scheduleTask(int time, String activityName, String extra) {
        return true;
    }

    public static boolean cancelTask(String activityName, String extra) {

        return true;
    }

    private static String uuid = "";

    public static String getUUID() {
        try {
            if (uuid == null || "".equals(uuid)) {
                String uuidKey = "_ANDROID_*****_UUID_";
                Activity activity = getActivity();
                if (activity == null) {
                    return "";
                }
                SharedPreferences sp = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE);
                uuid = sp.getString(uuidKey, null);
                if (uuid == null || uuid.length() == 0) {
                    uuid = UUID.randomUUID().toString().toUpperCase();
                    sp.edit().putString(uuidKey, uuid).apply();
                }
            }
            return uuid;
        } catch (Throwable t) {
            //
        }

        return "";
    }

    public static JSONObject getInventory() {
        JSONObject inventory = new JSONObject();

        if (storeItems == null || purchaseManagerWrapper == null) {
            return inventory;
        }

        try {
            Iterator<JSONObject> it = storeItems.values().iterator();
            while (it.hasNext()) {
                JSONObject iap = it.next();
                String billId = iap.optString("billId");
                String iapId = iap.optString("feename");
                SKUDetail skuDetail = purchaseManagerWrapper.getSKUDetail(iapId);
                if (skuDetail != null) {
                    inventory.put(billId, skuDetail.toJson());
                }
            }
        } catch (Exception ex) {
            Logger.error(TAG, "getInventory failed", ex);
        }
        return inventory;
    }

    public static String getCountryCode() {
        try {
            String mobileCountryCode = Locale.getDefault().getCountry().toLowerCase(Locale.ENGLISH);
            if (mmContainsKey(KEY_MM_COUNTRY)) {
                return mmGetStringValue(KEY_MM_COUNTRY, mobileCountryCode);
            }
            if (gridManager == null) {
                return mobileCountryCode;
            }
            String clientCountryCode = gridManager.getClientCountryCode();
            if (clientCountryCode == null || "".equals(clientCountryCode)) {
                return mobileCountryCode;
            }
            return clientCountryCode;
        } catch (Throwable t) {
            Logger.error(TAG, "getCountryCode exception", t);
            return Locale.ENGLISH.getCountry();
        }
    }

    public static void setPayVerifyUrl(String verifyUrl) {
        if (purchaseManagerWrapper != null) {
            purchaseManagerWrapper.setPayVerifyUrl(verifyUrl);
        }
    }

    /**
     * 特殊的预置事件
     */
    /**
     * 记录用户登录成功事件.
     *
     * @param sid
     * @param revenue
     */
    public static void trackLoginCompleted(String sid, float revenue) {

    }

    public static boolean isNotificationChannelEnabled(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                return NotificationManagerCompat.from(activity).areNotificationsEnabled();
            } catch (Throwable t) {
                Logger.error(TAG, "isNotificationChannelEnabled exception", t);
            }
        }
        return true;
    }

    public static void openNotificationSettings(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            Logger.warning(TAG, "activity not valid, openNotificationSettings ignore");
            return;
        }
        try {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.getPackageName());
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", activity.getPackageName());
                intent.putExtra("app_uid", activity.getApplicationInfo().uid);
            } else {
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
            }
            activity.startActivity(intent);
        } catch (Throwable t) {
            Logger.error(TAG, "openNotificationSettings exception", t);
        }
    }

    private static Dialog progress = null;

    public static void hideProgressBar(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progress != null) {
                    try {
                        progress.dismiss();
                        progress = null;
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
        });
    }

    public static void showProgressBar(final Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(() -> {
            try {
                if (progress != null) {
                    progress.dismiss();
                    progress = null;
                }
                progress = new ImmersiveDialog(activity);
                progress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                ProgressBar progressBar = new ProgressBar(activity, null, 16842874);
                progress.requestWindowFeature(1);
                progress.setContentView(progressBar);
                progress.setOwnerActivity(activity);
                progress.show();
            } catch (Throwable e) {
                // ignore
            }
        });
    }

    public static void executeCloudFunction(String funcName, JSONObject parameters, OnCloudFunctionResult onCloudFunctionResult) {
        try {
            String functionLocation = IvySdk.getGridConfigString("cf.location", "us-central1");
            FirebaseFunctions mFunctions = FirebaseFunctions.getInstance(functionLocation);
            mFunctions.getHttpsCallable(funcName).call(parameters).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    try {
                        String resultString = JSON.toJSONString(task.getResult().getData());
                        Logger.debug(TAG, " success <<< " + resultString);
                        if (onCloudFunctionResult != null) {
                            onCloudFunctionResult.onResult(resultString);
                        }
                    } catch (Throwable t) {
                        t.printStackTrace();
                        if (onCloudFunctionResult != null) {
                            onCloudFunctionResult.onFail("result error");
                        }
                    }
                } else {
                    Logger.debug(TAG, " fail <<< " + task.getException());

                    if (onCloudFunctionResult != null) {
                        onCloudFunctionResult.onFail("fail");
                    }
                }
            });
        } catch (Throwable t) {
            Logger.error(TAG, "executeCloudFunction exception", t);
            if (onCloudFunctionResult != null) {
                onCloudFunctionResult.onFail(t.getLocalizedMessage());
            }
        }
    }

    public static void trackScreen(String screenClass, String screenName) {
        if (eventTracker == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_CLASS, screenClass);
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);

        eventTracker.logToFirebase(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
    }

    public static String getPushToken() {
        if (main == null || main.get() == null) {
            return "";
        }
        final SharedPreferences sp = main.get().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        return sp.getString("firebase_token", "");
    }

    public static void supressInAppMessage(boolean suppressMessage) {
        Logger.debug(TAG, "supressInAppMessage >>> " + suppressMessage);

        FirebaseInAppMessaging.getInstance().setMessagesSuppressed(suppressMessage);
    }

    public static void triggerInAppMessage(String eventName) {
        try {
            FirebaseInAppMessaging.getInstance().triggerEvent(eventName);
        } catch (Throwable t) {
            Logger.debug(TAG, "triggerInAppMessage exception", t);
        }
    }

    private static final Map<String, FirebaseInAppMessagingDisplayCallbacks> currentFirebaseInAppMessagingDisplayCallbacksMap = new HashMap<>();
    private static final Map<String, Action> currentFirebaseInAppMessageActionsMap = new HashMap<>();

    public static void inAppMessageClicked(String campaignId) {
        Logger.debug(TAG, "inAppMessageClicked");
        try {
            FirebaseInAppMessagingDisplayCallbacks callbacks = currentFirebaseInAppMessagingDisplayCallbacksMap.get(campaignId);
            if (callbacks == null) {
                return;
            }
            Action action = currentFirebaseInAppMessageActionsMap.get(campaignId);
            if (action == null) {
                return;
            }
            callbacks.messageClicked(action).addOnCompleteListener(task -> Logger.debug(TAG, "inAppMessageClicked reported >>> " + task.isSuccessful()));
        } catch (Throwable ignored) {
        }
    }

    private static String inAppMessageInstallationId = null;

    public static void registerInAppMessageService(final InAppMessageClickListener listener) {
        try {
            FirebaseInAppMessaging firebaseInAppMessaging = FirebaseInAppMessaging.getInstance();

            FirebaseInstallations.getInstance().getId().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    inAppMessageInstallationId = task.getResult();
                    Logger.debug(TAG, "installationID >>> " + task.getResult());
                } else {
                    Logger.error(TAG, "InAppMessageService exception");
                }
            });

            firebaseInAppMessaging.setMessagesSuppressed(false);
            firebaseInAppMessaging.setMessageDisplayComponent(new FirebaseInAppMessagingDisplay() {
                @Override
                public void displayMessage(@NonNull InAppMessage inAppMessage, @NonNull FirebaseInAppMessagingDisplayCallbacks firebaseInAppMessagingDisplayCallbacks) {
                    try {
                        Logger.debug(TAG, "display In App Messenger");
                        MessageType messageType = inAppMessage.getMessageType();
                        if (messageType == MessageType.BANNER && inAppMessage instanceof BannerMessage) {
                            return;
                        }

                        CampaignMetadata campaignMetadata = inAppMessage.getCampaignMetadata();

                        String campaignId = campaignMetadata != null ? campaignMetadata.getCampaignName() : "";

                        // record this callback
                        currentFirebaseInAppMessagingDisplayCallbacksMap.put(campaignId, firebaseInAppMessagingDisplayCallbacks);

                        JSONObject inAppMessageJson = new JSONObject();
                        inAppMessageJson.put("id", campaignId);
                        if (messageType == MessageType.MODAL && inAppMessage instanceof ModalMessage) {
                            ModalMessage modalMessage = (ModalMessage) inAppMessage;
                            Text titleText = modalMessage.getTitle();
                            inAppMessageJson.put("title", titleText.getText());

                            Text bodyText = modalMessage.getBody();
                            if (bodyText != null) {
                                inAppMessageJson.put("body", bodyText.getText());
                            }

                            ImageData imageData = modalMessage.getImageData();
                            if (imageData != null) {
                                String imageUrl = imageData.getImageUrl();
                                if (!"".equals(imageUrl)) {
                                    inAppMessageJson.put("image", imageUrl);
                                }
                            }

                            Action action = modalMessage.getAction();
                            if (action != null) {
                                String actionUrl = action.getActionUrl();
                                if (actionUrl.contains("dummy")) {
                                    return;
                                } else {
                                    inAppMessageJson.put("action", actionUrl);
                                    JSONObject actionParams = IvyUtils.getUrlParametersWithJson(actionUrl);
                                    inAppMessageJson.put("action_params", actionParams);

                                    currentFirebaseInAppMessageActionsMap.put(campaignId, action);
                                }
                            }
                        } else if (messageType == MessageType.IMAGE_ONLY && inAppMessage instanceof ImageOnlyMessage) {
                            ImageOnlyMessage imageOnlyMessage = (ImageOnlyMessage) inAppMessage;

                            ImageData imageData = imageOnlyMessage.getImageData();
                            String imageUrl = imageData.getImageUrl();
                            if (!"".equals(imageUrl)) {
                                inAppMessageJson.put("image", imageUrl);
                            }

                            Action action = imageOnlyMessage.getAction();
                            if (action != null) {
                                String actionUrl = action.getActionUrl();
                                if (actionUrl.contains("dummy")) {
                                    return;
                                } else {
                                    inAppMessageJson.put("action", actionUrl);
                                    JSONObject actionParams = IvyUtils.getUrlParametersWithJson(actionUrl);
                                    inAppMessageJson.put("action_params", actionParams);
                                    currentFirebaseInAppMessageActionsMap.put(campaignId, action);
                                }
                            }
                        } else {
                            Logger.error(TAG, "unknown inapp message type");
                            return;
                        }

                        Logger.debug(TAG, "Inappmessage>>> " + inAppMessageJson.toString());
                        if (listener != null) {
                            listener.messageDisplayed(campaignId, inAppMessageJson.toString());
                        }

                        // impression
                        firebaseInAppMessagingDisplayCallbacks.impressionDetected().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
                    } catch (Throwable t) {
                        Logger.error(TAG, "display in app message exception", t);
                    }
                }
            });
        } catch (Throwable t) {
            Logger.error(TAG, "registerInAppMessageService exception", t);
        }
    }

    private static long lastErrorLoggedTime = 0L;

    public static void logError(String message) {
        if (System.currentTimeMillis() - lastErrorLoggedTime < 5000L) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("stack", message);
        eventTracker.parfkaLog("unity_error", bundle);

        lastErrorLoggedTime = System.currentTimeMillis();
    }

    public static FirebaseRemoteConfig getFirebaseRemoteConfig() {
        return mFirebaseRemoteConfig;
    }

    public static long mmActualSize() {
        return gameDataMMKV.actualSize();
    }

    public static void mmSetIntValue(String key, int value) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return;
        }
        gameDataMMKV.encode(key, value);
    }

    public static int mmGetIntValue(String key, int defaultValue) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return defaultValue;
        }
        return gameDataMMKV.decodeInt(key, defaultValue);
    }

    public static void mmSetLongValue(String key, long value) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return;
        }
        gameDataMMKV.encode(key, value);
    }

    public static long mmGetLongValue(String key, long defaultValue) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return defaultValue;
        }
        return gameDataMMKV.decodeLong(key, defaultValue);
    }

    public static void mmSetBoolValue(String key, boolean value) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return;
        }
        gameDataMMKV.encode(key, value);
    }

    public static boolean mmGetBoolValue(String key, boolean defaultValue) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return defaultValue;
        }
        return gameDataMMKV.decodeBool(key, defaultValue);
    }

    public static void mmSetFloatValue(String key, float value) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return;
        }
        gameDataMMKV.encode(key, value);
    }

    public static float mmGetFloatValue(String key, float defaultValue) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return defaultValue;
        }
        return gameDataMMKV.decodeFloat(key, defaultValue);
    }

    public static void mmSetStringValue(String key, String value) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return;
        }
        gameDataMMKV.encode(key, value);
    }

    public static String mmGetStringValue(String key, String defaultValue) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return defaultValue;
        }
        return gameDataMMKV.decodeString(key, defaultValue);
    }

    public static boolean mmContainsKey(String key) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return false;
        }

        return gameDataMMKV.containsKey(key);
    }

    public static void mmRemoveKey(String key) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return;
        }
        gameDataMMKV.removeValueForKey(key);
    }

    public static void mmRemoveKeys(String keys) {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return;
        }
        if (keys == null) {
            return;
        }
        String[] newKeys = keys.split(",");
        if (newKeys.length > 0) {
            gameDataMMKV.removeValuesForKeys(newKeys);
        }
    }

    public static void mmClearAll() {
        if (gameDataMMKV == null) {
            Log.e(TAG, "gameDataMMKV invalid");
            return;
        }
        gameDataMMKV.clearAll();
    }

    public static void recordEventConversion(String conversionUrl, String eventName, Bundle bundle, final InAppMessageClickListener listener) {
        Logger.debug(TAG, "recordEventConversion >>> " + eventName);
        try {
            FormBody.Builder formBuilder = new FormBody.Builder();

            // uid
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if (firebaseUser != null) {
                formBuilder.add("uid", firebaseUser.getUid());
            }

            formBuilder.add("country", getCountryCode());

            formBuilder.add("event_token", eventName);
            // 基本启动指标
            formBuilder.add("app_first_start", String.valueOf(IvySdk.firstAppStartTime));
            formBuilder.add("app_last_start", String.valueOf(IvySdk.lastAppStartTime));
            formBuilder.add("app_start_times", String.valueOf(IvySdk.appStartTimes));
            formBuilder.add("total_orders", String.valueOf(IvySdk.totalOrders));
            formBuilder.add("total_revenue", String.valueOf(IvySdk.totalRevenues));

            if (inAppMessageInstallationId != null && !"".equals(inAppMessageInstallationId)) {
                formBuilder.add("inapp_installation", inAppMessageInstallationId);
            }


            if (eventTracker != null) {
                long engagement = eventTracker.getEngagementTimestamp();
                formBuilder.add("engagement", String.valueOf(engagement));
            }

            String adid = null;//Adjust.getAdid();
            if (adid != null) {
                formBuilder.add("adid", adid);
            }

            if (bundle != null) {
                for (String key : bundle.keySet()) {
                    String value = bundle.getString(key, null);
                    if (value != null) {
                        formBuilder.add(key, value);
                    }
                }
            }

            // collect all pa_ remote configs
            if (mFirebaseRemoteConfig != null) {
                for (String key : mFirebaseRemoteConfig.getKeysByPrefix(KEY_REMOTE_USER_ATTRIBUTE)) {
                    String value = mFirebaseRemoteConfig.getString(key);
                    if (key != null && value != null && !"".equals(key) && !"".equals(value)) {
                        formBuilder.add(key, value);
                    }
                }
            }

            Request request = new Request.Builder()
                    .url(conversionUrl)
                    .post(formBuilder.build())
                    .build();

            getOkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Logger.warning(TAG, "event conversion report failed");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Logger.debug(TAG, "event conversion reported");
                    try {
                        String data = response.body().string();
                        JSONObject result = new JSONObject(data);
                        if (result.has("inapp_event")) {
                            String inAppEvent = result.optString("inapp_event");
                            if (!"".equals(inAppEvent)) {
                                triggerInAppMessage(inAppEvent);
                            }
                        }

                        if (result.has("event")) {
                            String event = result.optString("event");
                            if (!"".equals(event)) {
                                eventTracker.logToFirebase(event, null);
                                eventTracker.parfkaLog(event, null);
                            }
                        }

                        if (result.has("event_once")) {
                            String event = result.optString("event_once");
                            if (!"".equals(event)) {
                                boolean alreadySent = mmGetBoolValue("sent_" + event, false);
                                if (!alreadySent) {
                                    eventTracker.logToFirebase(event, null);
                                    eventTracker.parfkaLog(event, null);
                                    mmSetBoolValue("sent_" + event, true);
                                }
                            }
                        }

                        if (result.has("user_properties")) {
                            JSONObject userProperties = result.optJSONObject("user_properties");
                            if (userProperties != null && userProperties.length() > 0) {
                                Iterator<String> keys = userProperties.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    String value = userProperties.optString(key);
                                    eventTracker.setUserProperty(key, value);
                                }
                            }
                        }

                        if (result.has("inapp_message")) {
                            JSONObject inappMessage = result.optJSONObject("inapp_message");
                            if (inappMessage != null && listener != null) {
                                listener.messageDisplayed(inappMessage.optString("id"), inappMessage.toString());
                            }
                        }
                    } catch (Throwable t) {
                        Logger.error(TAG, "parse response exception", t);
                    }
                }
            });
        } catch (Throwable t) {
            // crash protected
        }
    }


    private static final String KEY_REMOTE_USER_ATTRIBUTE = "pa_";

    private static final String KEY_MM_COUNTRY = "__country";

    /**
     * 如果配置了用户属性地址，调用此接口，同步用户属性数据，
     * 用户属性数据是用户基本数据的快照，运营可以根据此接口的数据根据条件组合进行push或其他有趣的操作。
     */
    public static void saveUserAttribute(JSONObject dataMap, final InAppMessageClickListener listener) {
        if (userAttributeUrl == null || "".equals(userAttributeUrl)) {
            return;
        }

        try {
            FormBody.Builder formBuilder = new FormBody.Builder();

            // uid
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            if (firebaseUser != null) {
                formBuilder.add("uid", firebaseUser.getUid());
                String displayName = firebaseUser.getDisplayName();
                if (displayName != null) {
                    formBuilder.add("username", displayName);
                }
                String email = firebaseUser.getEmail();
                if (email != null && !"".equals(email)) {
                    formBuilder.add("email", email);
                }
            }

            formBuilder.add("country", getCountryCode());
            // 基本启动指标
            formBuilder.add("app_first_start", String.valueOf(IvySdk.firstAppStartTime));
            formBuilder.add("app_last_start", String.valueOf(IvySdk.lastAppStartTime));
            formBuilder.add("app_start_times", String.valueOf(IvySdk.appStartTimes));
            if (IvySdk.totalOrders > 0) {
                formBuilder.add("total_orders", String.valueOf(IvySdk.totalOrders));
            }
            if (IvySdk.totalRevenues > 0) {
                formBuilder.add("total_revenue", String.valueOf(IvySdk.totalRevenues));
            }

            if (inAppMessageInstallationId != null && !"".equals(inAppMessageInstallationId)) {
                formBuilder.add("inapp_installation", inAppMessageInstallationId);
            }


            String adid = null;//Adjust.getAdid();
            if (adid != null) {
                formBuilder.add("adid", adid);
            }

            if (eventTracker != null) {
                long engagement = eventTracker.getEngagementTimestamp();
                formBuilder.add("engagement", String.valueOf(engagement));
            }

            // push_token
            String push_token = getPushToken();
            if (push_token != null && !"".equals(push_token)) {
                formBuilder.add("push_token", push_token);
            }

            // dataMap
            if (dataMap != null) {
                Iterator<String> it = dataMap.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = dataMap.optString(key, null);
                    if (value != null) {
                        formBuilder.add(key, value);
                    }
                }
            }

            // remote config里以pa_开头的所有变量
            if (mFirebaseRemoteConfig != null) {
                for (String key : mFirebaseRemoteConfig.getKeysByPrefix(KEY_REMOTE_USER_ATTRIBUTE)) {
                    String value = mFirebaseRemoteConfig.getString(key);
                    if (!"".equals(key) && !"".equals(value)) {
                        formBuilder.add(key, value);
                    }
                }
            }

            try {
                String savedMainLine = IvySdk.mmGetStringValue(IvySdk.KEY_GAME_MAIN_LINE, "");
                if (!"".equals(savedMainLine)) {
                    JSONObject mainLineData = new JSONObject(savedMainLine);
                    Iterator<String> keys = mainLineData.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        int value = mainLineData.getInt(key);
                        formBuilder.add(key, String.valueOf(value));
                    }
                }

                String remaingCurrency = IvySdk.mmGetStringValue(IvySdk.KEY_VIRUTAL_CURRENCY, "");
                if (!"".equals(remaingCurrency)) {
                    JSONObject currencyData = new JSONObject(remaingCurrency);
                    Iterator<String> keys = currencyData.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        int value = currencyData.getInt(key);
                        formBuilder.add(key, String.valueOf(value));
                    }
                }
            } catch (Throwable ignored) {
            }

            Request request = new Request.Builder()
                    .url(userAttributeUrl)
                    .post(formBuilder.build())
                    .build();

            getOkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Logger.warning(TAG, "user attribute report failed");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Logger.debug(TAG, "user attribute reported");
                    try {
                        String data = response.body().string();
                        JSONObject result = new JSONObject(data);
                        if (result.has("inapp_event")) {
                            String inAppEvent = result.optString("inapp_event");
                            if (!"".equals(inAppEvent)) {
                                triggerInAppMessage(inAppEvent);
                            }
                        }
                        if (result.has("event")) {
                            String event = result.optString("event");
                            if (!"".equals(event)) {
                                logEvent(event, null);
                            }
                        }

                        if (result.has("event_once")) {
                            String event = result.optString("event_once");
                            if (!"".equals(event)) {
                                boolean alreadySent = mmGetBoolValue("sent_" + event, false);
                                if (!alreadySent) {
                                    eventTracker.logToFirebase(event, null);
                                    eventTracker.parfkaLog(event, null);
                                    mmSetBoolValue("sent_" + event, true);
                                }
                            }
                        }

                        if (result.has("user_properties")) {
                            JSONObject userProperties = result.optJSONObject("user_properties");
                            if (userProperties != null && userProperties.length() > 0) {
                                Iterator<String> keys = userProperties.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    String value = userProperties.optString(key);
                                    eventTracker.setUserProperty(key, value);
                                }
                            }
                        }

                        if (result.has("inapp_message") && listener != null) {
                            JSONObject inAppMessage = result.optJSONObject("inapp_message");
                            if (inAppMessage != null) {
                                listener.messageDisplayed(inAppMessage.optString("id"), inAppMessage.toString());
                            }
                        }

                        if (result.has(KEY_MM_COUNTRY)) {
                            mmSetStringValue(KEY_MM_COUNTRY, result.optString(KEY_MM_COUNTRY));
                        }

                    } catch (Throwable t) {
                        Logger.error(TAG, "parse response exception", t);
                    }
                }
            });
        } catch (Throwable t) {
            // crash protected
        }
    }

    public static void signInAnonymously(final PlayGameProfileListener playGameProfileListener) {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            try {
                JSONObject profile = new JSONObject();
                profile.put("id", currentUser.getUid());
                if (currentUser.getDisplayName() != null) {
                    profile.put("name", currentUser.getDisplayName());
                }
                if (currentUser.getPhotoUrl() != null) {
                    profile.put("picture", currentUser.getPhotoUrl().toString());
                }
                playGameProfileListener.onSuccess(profile);
            } catch (Throwable t) {
                Logger.error(TAG, "signInAnonymously exception", t);
            }
            return;
        }

        auth.signInAnonymously().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Logger.debug(TAG, "signInAnonymously:success");
                FirebaseUser user = auth.getCurrentUser();

                try {
                    JSONObject profile = new JSONObject();
                    profile.put("id", user.getUid());
                    if (user.getDisplayName() != null) {
                        profile.put("name", user.getDisplayName());
                    }
                    if (user.getPhotoUrl() != null) {
                        profile.put("picture", user.getPhotoUrl().toString());
                    }
                    playGameProfileListener.onSuccess(profile);
                } catch (Throwable t) {
                    Logger.error(TAG, "signInAnonymously exception", t);
                }
            } else {
                // If sign in fails, display a message to the user.
                playGameProfileListener.onFail(task.getException().getMessage());
            }
        });
    }

    public static void getGoogleProfile(Context context, final PlayGameProfileListener playGameProfileListener) {
        GoogleSignInAccount signedInAccount = GoogleSignIn.getLastSignedInAccount(context);
        if (signedInAccount == null || signedInAccount.getServerAuthCode() == null) {
            playGameProfileListener.onFail("Not Signed In");
            return;
        }

        final FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            try {
                JSONObject profile = new JSONObject();
                profile.put("id", currentUser.getUid());
                profile.put("name", currentUser.getDisplayName());
                profile.put("picture", currentUser.getPhotoUrl().toString());
                playGameProfileListener.onSuccess(profile);
            } catch (Throwable t) {
                Logger.error(TAG, "getGoogleProfile exception", t);
            }
            return;
        }

        AuthCredential credential = PlayGamesAuthProvider.getCredential(signedInAccount.getServerAuthCode());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Logger.debug(TAG, "signInWithCredential:success");
                        FirebaseUser user = auth.getCurrentUser();

                        try {
                            JSONObject profile = new JSONObject();
                            profile.put("id", user.getUid());
                            profile.put("name", user.getDisplayName());
                            profile.put("picture", user.getPhotoUrl().toString());
                            playGameProfileListener.onSuccess(profile);
                        } catch (Throwable t) {
                            Logger.error(TAG, "getGoogleProfile exception", t);
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Logger.warning(TAG, "signInWithCredential:failure", task.getException());
                        playGameProfileListener.onFail(task.getException().getMessage());
                    }
                });
    }
}

