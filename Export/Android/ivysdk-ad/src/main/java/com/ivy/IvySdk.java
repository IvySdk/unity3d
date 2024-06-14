package com.ivy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdInspectorError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnAdInspectorClosedListener;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ivy.ads.IvyAds;
import com.ivy.ads.IvyAdsManager;
import com.ivy.ads.adapters.AdmobManager;
import com.ivy.ads.events.DefaultEventLogger;
import com.ivy.ads.interfaces.IvyAdCallbacks;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.managers.BannerAdManager;
import com.ivy.cmp.CMPUtil;
import com.ivy.cmp.ICMPCallback;
import com.ivy.event.EventBus;
import com.ivy.networks.grid.GridManager;
import com.ivy.networks.util.Util;
import com.tencent.mmkv.MMKV;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public final class IvySdk {
    private static final String TAG = IvySdk.class.getCanonicalName();
    public static final int POS_LEFT_TOP = 1;
    public static final int POS_LEFT_BOTTOM = 2;
    public static final int POS_CENTER_TOP = 3;
    public static final int POS_CENTER_BOTTOM = 4;
    public static final int POS_CENTER = 5;
    public static final int POS_RIGHT_TOP = 6;
    public static final int POS_RIGHT_BOTTOM = 7;
    public static boolean skipPauseOnce = false;
    public static boolean skipResumeOnce = false;
    private static Boolean sdkInitialized = false;
    private static IvyAdsManager adManager;
    private static GridManager gridManager = null;
    private static DefaultEventLogger eventTracker;
    private static long startupTime = 0L;
    private static WeakReference<Activity> main = null;
    private static boolean alreadyResumed = false;
    private static boolean alreadyPaused = false;
    private static MMKV gameDataMMKV = null;
    private static String ID_MMKV_GAMEDATA = "_gamedata_";

    public static synchronized boolean isInitialized() {
        boolean booleanValue;
        synchronized (IvySdk.class) {
            booleanValue = sdkInitialized;
        }
        return booleanValue;
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

        main = new WeakReference<>(activity);

        startupTime = System.currentTimeMillis();

        ApplicationInfo ai = null;
        try {
            ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        final SharedPreferences sp = main.get().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        boolean debugMode = false;

        if (ai != null && ai.metaData != null) {
            debugMode = ai.metaData.getBoolean("ivy.debug", false);
            Logger.setEnable(debugMode);
        }

        try {
            MMKV.initialize(activity);

            gameDataMMKV = MMKV.mmkvWithID(ID_MMKV_GAMEDATA);

        } catch (Throwable t) {
            Logger.error("initialize MMKV exception::" + t.getMessage());
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

        adManager = new IvyAdsManager();
        adManager.onCreate(activity, eventTracker, gridManager);

        if (debugMode) {
            Toast.makeText(activity, "DO NOT PUBLISH THIS!", Toast.LENGTH_LONG).show();
        }

        setDebug(debugMode);

        CMPUtil cmpUtil = new CMPUtil();
        boolean finalDebugMode = debugMode;
        cmpUtil.init(activity, debugMode, new ICMPCallback() {
            @Override
            public void onResult(boolean ableLoadAds) {
                try {
                    FirebaseAnalytics.ConsentStatus status = FirebaseAnalytics.ConsentStatus.GRANTED;
                    Map<FirebaseAnalytics.ConsentType, FirebaseAnalytics.ConsentStatus> consentMap = new EnumMap<>(FirebaseAnalytics.ConsentType.class);
                    consentMap.put(FirebaseAnalytics.ConsentType.ANALYTICS_STORAGE, status);
                    consentMap.put(FirebaseAnalytics.ConsentType.AD_STORAGE, status);
                    consentMap.put(FirebaseAnalytics.ConsentType.AD_USER_DATA, status);
                    consentMap.put(FirebaseAnalytics.ConsentType.AD_PERSONALIZATION, status);
                    FirebaseAnalytics.getInstance(activity.getApplicationContext()).setConsent(consentMap);

                    Log.d("CMP", "start load ads");
                    try {
                        AdmobManager.getInstance().initialize(activity, initializationStatus -> {
                            try {
                                Logger.debug("Admob initialized");
                                if (finalDebugMode) {
                                    MobileAds.openAdInspector(activity, new OnAdInspectorClosedListener() {
                                        public void onAdInspectorClosed(@Nullable AdInspectorError error) {
                                            // Error will be non-null if ad inspector closed due to an error.
                                            Logger.error("" + (error == null ? "" : error.getMessage()));
                                        }
                                    });
                                }
                                Map<String, AdapterStatus> adapterStatusMap = initializationStatus.getAdapterStatusMap();
                                for (Map.Entry<String, AdapterStatus> entry : adapterStatusMap.entrySet()) {
                                    Logger.debug("adapter status: " + entry.getKey() + " >>> " + entry.getValue().getInitializationState());
                                }
                                adManager.preload(gridData);
                            } catch (Throwable t) {
                                Logger.error("adManager preload exception::" + t.getMessage());
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

    public static void setAdCallback(IvyAdType adType, IvyAdCallbacks callback) {
        if (adManager != null) {
            adManager.setAdCallback(adType, callback);
        } else {
            Logger.error(TAG, "Ad not configured? ");
        }
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
        } catch (Throwable t) {
            t.printStackTrace();
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


    public static void onDestroy() {
        Logger.debug("OnDestroy called");
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
                Logger.debug("Close Banner");
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
            Logger.debug("Ad disabled");
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
            Logger.debug("Ad disabled");
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
                    adManager.showInterstitial(activity, tag);
                }
            }
        });
    }

    public static void closeNativeAd() {
        Logger.debug("closeNativeAd called");

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
        Logger.debug("showNativeAd called,");
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
                    Logger.error("showNativeAd exception::" + ex);
                }
            }
        });
    }

    public static void showNativeAd(final int x, final int y, final int width, final int height, final int sw, final int sh) {
        Logger.debug("showNativeAd called, x: " + x + ",y : " + y + ", width: " + width + "， height: " + height + ", sw: " + sw + ", sh: " + sh);
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
                    Logger.error("showNativeAd exception::" + ex);
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

    }

    public static boolean isDebugMode() {
        return IvyAds.isDebugMode();
    }

    public static void setDebug(final boolean flag) {
        IvyAds.setDebugMode(flag);
    }

    public static Activity getActivity() {
        if (main == null) {
            return null;
        }
        return main.get();
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
//
//    public static void logToFirebase(String eventName, Bundle bundle) {
//        if (eventTracker != null) {
//            eventTracker.logToFirebase(eventName, bundle);
//        }
//    }
//
//    public static void logIvyEvent(String eventName, Bundle bundle) {
//        if (eventTracker != null) {
//            eventTracker.parfkaLog(eventName, bundle);
//        }
//    }
//
//    public static void logToFacebook(String eventName, Bundle bundle) {
//        if (eventTracker != null) {
//            eventTracker.logToFacebook(eventName, bundle);
//        }
//    }
//
//    public static void logToConversation(String eventName, Bundle bundle) {
//        if (eventTracker != null) {
//            eventTracker.logToConversation(eventName, bundle);
//        }
//    }
//
//    public static void logToAppsflyer(String eventName, Bundle bundle) {
//        if (eventTracker != null) {
//            eventTracker.logToAppsflyer(eventName, bundle);
//        }
//    }

    public static void setUserProperty(String key, String value) {
        Logger.debug("setUserProperty : " + key + " >>> " + value);
        if (eventTracker != null) {
            eventTracker.setUserProperty(key, value);
        }
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

}

