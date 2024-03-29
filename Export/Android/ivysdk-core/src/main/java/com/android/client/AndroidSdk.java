package com.android.client;

import static com.google.android.gms.ads.RequestConfiguration.TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.ivy.AIHelp.AIHelp;
import com.ivy.IvySdk;
import com.ivy.IvyUtils;
import com.ivy.ads.interfaces.IvyAdCallbacks;
import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.managers.PromoteAdManager;
import com.ivy.billing.PurchaseManager;
import com.ivy.billing.PurchaseStateChangeData;
import com.ivy.billing.impl.IPurchaseQueryCallback;
import com.ivy.event.CommonEvents;
import com.ivy.event.EventBus;
import com.ivy.event.EventListener;
import com.ivy.facebook.FacebookLoginListener;
import com.ivy.facebook.FacebookUserManager;
import com.ivy.help.TiledeskActivity;
import com.ivy.internal.WebViewActivity;
import com.ivy.networks.grid.GridManager;
import com.ivy.util.CommonUtil;
import com.ivy.util.CustomThrowable;
import com.ivy.util.Logger;
import com.ivy.xsolla.DLog;
import com.ivy.xsolla.IXsollaLoginListener;
import com.ivy.xsolla.PayResult;
import com.ivy.xsolla.Product;
import com.smarx.notchlib.NotchScreenManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

public class AndroidSdk {
    public static final String TAG = "AndroidSdk";
    private static Builder builder;
    private static FacebookUserManager facebookUserManager = null;

    public static final int POS_LEFT_TOP = 1;
    public static final int POS_LEFT_BOTTOM = 2;
    public static final int POS_CENTER_TOP = 3;
    public static final int POS_CENTER_BOTTOM = 4;
    public static final int POS_CENTER = 5;
    public static final int POS_RIGHT_TOP = 6;
    public static final int POS_RIGHT_BOTTOM = 7;

    private static BuilderListener sdkListener;
    // public static SDKUserCenterListener sdkUserCenterListener;

    private static boolean paymentSystemValid = false;

    private static boolean sdkInitialized = false;

    interface HomeAdListener {
        void showLoading();

        void closeLoading();
    }

    public static class Builder {
        PaymentSystemListener paymentResultListener;
        UserCenterListener userCenterListener;
        UrlListener urlListener;
        SdkResultListener sdkResultListener;
        AdEventListener adEventListener;
        AdLoadedListener adLoadedListener;
        NetworkChangeListener networkChangeListener;
        InstallRewardListener installRewardListener;
        EventOccurredListener eventOccurredListener;
        DeepLinkReceivedListener deepLinkReceivedListener;

        DeliciousIconClickedListener deliciousIconClickedListener;

        SavedGameListener savedGameListener;
        InAppMessageClickListener inAppMessageClickListener;
        OnHelpEngagementListener onHelpEngagementListener;
        GoogleListener googleListener;

        IGMSPaidEventListener gmsPaidEventListener;

        IAppsflyerConversionListener appsflyerConversionListener;
        IXsollaLoginListener xsollaLoginListener;

        public Builder setPaymentListener(PaymentSystemListener listener) {
            this.paymentResultListener = listener;
            return this;
        }

        public Builder setAdLoadedListener(AdLoadedListener listener) {
            this.adLoadedListener = listener;
            return this;
        }

        public Builder setNetworkChangeListener(NetworkChangeListener listener) {
            this.networkChangeListener = listener;
            return this;
        }

        public Builder setGoogleListener(GoogleListener listener) {
            this.googleListener = listener;
            return this;
        }

        public Builder setDeepLinkReceivedListener(DeepLinkReceivedListener listener) {
            this.deepLinkReceivedListener = listener;
            return this;
        }

        public Builder setInstallRewardListener(InstallRewardListener listener) {
            this.installRewardListener = listener;
            return this;
        }

        public Builder setEventOccurredListener(EventOccurredListener listener) {
            this.eventOccurredListener = listener;
            return this;
        }

        public Builder setUserCenterListener(UserCenterListener listener) {
            this.userCenterListener = listener;
            return this;
        }

        public Builder setUrlListener(UrlListener listener) {
            this.urlListener = listener;
            return this;
        }

        public Builder setSdkResultListener(SdkResultListener listener) {
            this.sdkResultListener = listener;
            return this;
        }

        public Builder setDeliciousIconClickedListener(DeliciousIconClickedListener listener) {
            this.deliciousIconClickedListener = listener;
            return this;
        }

        public Builder setAdEventListener(AdEventListener listener) {
            this.adEventListener = listener;
            return this;
        }

        public Builder setSavedGameListener(SavedGameListener listener) {
            this.savedGameListener = listener;
            return this;
        }

        public void setInAppMessageClickListener(InAppMessageClickListener inAppMessageClickListener) {
            this.inAppMessageClickListener = inAppMessageClickListener;
        }

        public Builder setHelpEngagementListener(OnHelpEngagementListener onHelpEngagementListener) {
            this.onHelpEngagementListener = onHelpEngagementListener;
            return this;
        }

        public Builder setGMSPaidEventListener(IGMSPaidEventListener gmsPaidEventListener) {
            this.gmsPaidEventListener = gmsPaidEventListener;
            return this;
        }

        public Builder setAppsflyerConversionListener(IAppsflyerConversionListener appsflyerConversionListener) {
            this.appsflyerConversionListener = appsflyerConversionListener;
            return this;
        }

        public Builder setXSollaLoginListener(IXsollaLoginListener iXsollaLoginListener) {
            this.xsollaLoginListener = iXsollaLoginListener;
            return this;
        }
    }

    @Deprecated
    public static void onCreate(Activity activity) {
        Log.e(TAG, "Please use onCreate(Activity, builder) to initialize SDK");
    }


    public synchronized static void onCreate(@NonNull final Activity activity, final Builder builder) {
        if (builder == null) {
            Logger.error(TAG, "Pass NULL builder, all events will be not received.");
        }

        if (sdkListener == null) {
            sdkListener = new BuilderListener();
        }

        AndroidSdk.builder = builder;
        sdkListener.setBuilder(builder);

        if (builder != null && builder.adEventListener != null) {
            registerAdEventListener(builder.adEventListener);
        } else {
            registerAdEventListener(new AdEventListener());
        }

        IvySdk.updateCurrentActivity(activity);

        if (sdkInitialized) {
            Logger.warning(TAG, "Already initialized");
            return;
        }

        sdkInitialized = true;


        EventBus.getInstance().addListener(CommonEvents.XSOLLA_LOGIN_RESULT, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i != CommonEvents.XSOLLA_LOGIN_RESULT) {
                    return;
                }
                try {
                    if (builder != null && builder.xsollaLoginListener != null) {
                        if (obj instanceof Boolean) {
                            boolean result = (boolean) obj;
                            if (result) {
                                builder.xsollaLoginListener.onSuccess();
                            } else {
                                builder.xsollaLoginListener.onFail();
                            }
                        }
                    }
                } catch (Throwable t) {
                    // ignore
                    paymentSystemValid = false;
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.XSOLLA_BILLING_VALID, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i != CommonEvents.XSOLLA_BILLING_VALID) {
                    return;
                }
                try {
                    if (builder != null && builder.paymentResultListener != null) {
                        DLog.d("xsolla is ready to pay");
                        paymentSystemValid = true;
                        builder.paymentResultListener.onPaymentSystemValid();
                    }
                } catch (Throwable t) {
                    // ignore
                    paymentSystemValid = false;
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.XSOLLA_BILLING_RESULT, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i != CommonEvents.XSOLLA_BILLING_RESULT) {
                    return;
                }
                try {
                    if (obj instanceof PayResult) {
                        PayResult payResult = (PayResult) obj;
                        DLog.d("xsolla pay result:" + payResult.toString());
                        int billId = Integer.parseInt(payResult.billId);
                        if (payResult.result) {
                            if (payResult.payload == null) {
                                if (builder != null && builder.paymentResultListener != null) {
                                    builder.paymentResultListener.onPaymentSuccess(billId);
                                }
                            } else {
                                if (builder != null && builder.paymentResultListener != null) {
                                    builder.paymentResultListener.onPaymentSuccess(billId, payResult.payload);
                                }
                            }
                        } else {
                            if (builder != null && builder.paymentResultListener != null) {
                                builder.paymentResultListener.onPaymentFail(billId);
                            }
                        }
                    }
                } catch (Exception e) {
                    DLog.d("xsolla pay result parse error:" + e.getMessage());
                }
            }
        });


        IvySdk.initialize(activity, null, new IvySdk.InitializeCallback() {
            @Override
            public void onInitialized() {

            }

            @Override
            public void onRemoteConfigUpdated() {
                if (builder != null && builder.sdkResultListener != null) {
                    builder.sdkResultListener.onReceiveServerExtra("{}");
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.AD_LOADED, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i == CommonEvents.AD_LOADED) {
                    if (obj != null && obj instanceof Integer) {
                        int adType = (Integer) obj;
                        if (builder != null && builder.adLoadedListener != null) {
                            builder.adLoadedListener.onAdLoaded(adType);
                        }
                    }
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.DELICIOUS_ICON_CLICKED, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i == CommonEvents.DELICIOUS_ICON_CLICKED) {
                    if (obj != null && obj instanceof JSONObject) {
                        JSONObject data = (JSONObject) obj;
                        String pkg = data.optString("package");
                        String filePath = data.optString("filePath");
                        if (builder != null && builder.deliciousIconClickedListener != null) {
                            builder.deliciousIconClickedListener.clicked(filePath, pkg);
                        }
                    }
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.NETWORK_STATUS_CHANGED, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                try {
                    if (i == CommonEvents.NETWORK_STATUS_CHANGED) {
                        if (obj != null && obj instanceof Boolean) {
                            boolean networkStatus = (Boolean) obj;
                            if (builder != null && builder.networkChangeListener != null) {
                                builder.networkChangeListener.onReceive(networkStatus);
                            }
                        }
                    }
                } catch (Throwable t) {
                    // ignore
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.DEEPLINK_RECEIVED, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                try {
                    if (i == CommonEvents.DEEPLINK_RECEIVED) {
                        if (obj != null && obj instanceof String) {
                            String uri = (String) obj;
                            if (builder != null && builder.deepLinkReceivedListener != null) {
                                builder.deepLinkReceivedListener.onDeepLinkReceived(uri);
                            }
                        }
                    }
                } catch (Throwable t) {
                    // ignore
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.BILLING_STORE_LOADED, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i != CommonEvents.BILLING_STORE_LOADED) {
                    return;
                }
                Logger.debug(TAG, "Billing StoreLoaded " + obj);
                try {
                    if (obj instanceof String) {
                        String skuType = (String) obj;
                        if (builder != null && builder.paymentResultListener != null) {
                            builder.paymentResultListener.onStoreLoaded(skuType);
                        }
                    }
                } catch (Throwable t) {
                    // ignore
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.BILLING_PURCHASE_STATE_CHANGE, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i != CommonEvents.BILLING_PURCHASE_STATE_CHANGE) {
                    return;
                }
                Logger.debug(TAG, "purchase event called");
                try {
                    if (obj instanceof PurchaseStateChangeData) {
                        PurchaseStateChangeData changeData = (PurchaseStateChangeData) obj;
                        Logger.debug(TAG, "Purchased: " + changeData.toString());

                        String itemId = changeData.getItemId();
                        JSONObject storeItem = IvySdk.getStoreItem(itemId);
                        if (storeItem == null) {
                            Logger.error(TAG, "Not found billId for product: " + itemId);
                            return;
                        }
                        int billId = storeItem.optInt("billId");
                        if (changeData.getPurchaseState() == PurchaseManager.PurchaseState.PURCHASED) {
                            if (builder != null && builder.paymentResultListener != null) {
                                String orderId = changeData.getOrderId();
                                Logger.debug(TAG, "send paymentResult for bill: " + billId);
                                Logger.debug(TAG, "orderID: " + orderId);
                                String developerPayload = changeData.getDeveloperPayload();

                                if (IvySdk.isPaymentClientCheck()) {
                                    JSONObject orderInfo = new JSONObject();
//                  receipt ：需要发送给apple后台的base64校验数据
//                  country ： 大写国家码
//                  platform： 平台
//                  payId： 计费点id
//                  payload ： payload
//                  appid：游戏id
//                  transactionIdentifier：点单号
//                  package：包名
//                  name：游戏名称
//                  uuid：uuid
                                    try {
                                        orderInfo.put("receipt", changeData.getReceipt());
                                        orderInfo.put("signature", changeData.getSignature());
                                        orderInfo.put("purchaseToken", changeData.getPurchaseToken());
                                        orderInfo.put("purchaseTime", changeData.getPurchaseTime());
                                        orderInfo.put("country", IvySdk.getCountryCode());
                                        orderInfo.put("platform", "android");
                                        orderInfo.put("payId", billId);
                                        orderInfo.put("payload", developerPayload != null ? developerPayload : "");
                                        orderInfo.put("appid", IvySdk.getGridConfigString("appid"));
                                        orderInfo.put("transactionIdentifier", orderId);
                                        orderInfo.put("package", changeData.getPackageName());
                                        orderInfo.put("name", "");
                                        orderInfo.put("uuid", IvySdk.getUUID());
                                        String skuJson = changeData.getSkuJson();
                                        if (skuJson != null) {
                                            orderInfo.put("sku_json", skuJson);
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }

                                    builder.paymentResultListener.onPaymentSuccess(billId, developerPayload != null ? developerPayload : "", orderInfo.toString());
                                } else {
                                    if (developerPayload == null) {
                                        Logger.debug(TAG, "developerPayload is null");
                                        builder.paymentResultListener.onPaymentSuccess(billId);
                                    } else {
                                        Logger.debug(TAG, "developerPayload >>> " + developerPayload);
                                        builder.paymentResultListener.onPaymentSuccess(billId, developerPayload);
                                    }
                                }

                                // if contains ad tag, will disable ad
                                if (storeItem.has("action") && "removeads".equals(storeItem.optString("action"))) {
                                    Logger.debug(TAG, "Ad removed");
                                    IvySdk.updateAdStatus(false);
                                }
                            } else {
                                Logger.error(TAG, "onPaymentSuccess failed, no payment callback");
                            }
                        } else if (changeData.getPurchaseState() == PurchaseManager.PurchaseState.CANCELED) {
                            if (builder != null && builder.paymentResultListener != null) {
                                Logger.debug(TAG, "send payment cancelled result for bill: " + billId);
                                builder.paymentResultListener.onPaymentCanceled(billId);
                            } else {
                                Logger.error(TAG, "onPaymentCanceled failed, no payment callback");
                            }
                        } else if (changeData.getPurchaseState() == PurchaseManager.PurchaseState.ERROR) {
                            if (builder != null && builder.paymentResultListener != null) {
                                Logger.debug(TAG, "send payment error result for bill: " + billId);
                                builder.paymentResultListener.onPaymentFail(billId);
                            } else {
                                Logger.error(TAG, "onPaymentFail failed, no payment callback");
                            }
                        }
                    }
                } catch (Throwable t) {
                    Logger.error(TAG, "failed to fullfill payment", t);
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.BILLING_PAYMENT_SYSTEM_ERROR, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i != CommonEvents.BILLING_PAYMENT_SYSTEM_ERROR) {
                    return;
                }
                Logger.debug(TAG, "Payment System Error: " + obj);

                try {
                    if (obj != null && obj instanceof Integer) {
                        if (builder != null && builder.paymentResultListener != null) {
                            builder.paymentResultListener.onPaymentSystemError((Integer) obj, "error");
                        }
                    }
                } catch (Throwable t) {
                    // ignore
                }
            }
        });

        EventBus.getInstance().addListener(CommonEvents.BILLING_BECOMES_AVAILABLE, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i != CommonEvents.BILLING_BECOMES_AVAILABLE) {
                    return;
                }
                Logger.debug(TAG, "BILLING_BECOMES_AVAILABLE");
                try {
                    if (builder != null && builder.paymentResultListener != null) {
                        paymentSystemValid = true;
                        builder.paymentResultListener.onPaymentSystemValid();
                    }
                } catch (Throwable t) {
                    // ignore
                    paymentSystemValid = false;
                }
            }
        });


        boolean slientLoginGoogle = IvySdk.getGridConfigBoolean("slientLoginGoogle");
        if (slientLoginGoogle) {
            Logger.debug(TAG, "Set to slient Login");
            IvySdk.slientLoginGoogle(builder.googleListener);
        }

        try {
            sdkListener.onInitialized();
            facebookUserManager = new FacebookUserManager();
        } catch (Throwable t) {
            Logger.error(TAG, "sdk onInit exception", t);
        }

        if (activity.getIntent() != null) {
            handleIntent(activity.getIntent());
        }

        // will display cross promotion if exists settings
        if (IvySdk.hasGridConfig("splash_promotion")) {
            try {
                JSONObject splashPromotionConfig = IvySdk.getGridConfigJson("splash_promotion");
                JSONArray apps = splashPromotionConfig.optJSONArray("apps");
                if (apps == null || apps.length() == 0) {
                    Logger.warning(TAG, "splash_promotion config exception, apps is empty");
                    return;
                }

                int promotionTime = splashPromotionConfig.optInt("promotionTime", 5);
                int promotionKeep = splashPromotionConfig.optInt("promotionKeep", 3);
                int promotionDayTimes = splashPromotionConfig.optInt("promotionDayTimes", 2);
                int promotionInterval = splashPromotionConfig.optInt("promotionInterval", 7200);

                int size = apps.length();
                JSONObject pickedPackage = null;
                if (size > 1) {
                    int pickIndex = new Random().nextInt(size);
                    pickedPackage = apps.optJSONObject(pickIndex);
                } else {
                    pickedPackage = apps.optJSONObject(0);
                }

                if (pickedPackage != null) {
                    final String imageUrl = pickedPackage.optString("image");
                    final String pkg = pickedPackage.optString("package");

                    if (!TextUtils.isEmpty(imageUrl) && !TextUtils.isEmpty(pkg)) {
                        if (!IvyUtils.hasApp(activity, pkg)) {
                            Logger.debug(TAG, "Start promotion >>> " + imageUrl);
                            CrossPromotionAd.getInstance().loadAd(activity, pkg, imageUrl, promotionTime, promotionKeep, promotionDayTimes, promotionInterval);
                        } else {
                            Logger.debug(TAG, "cross already installed");
                        }
                    }
                }
            } catch (Throwable t) {
                // ignore
            }
        }

        if (IvySdk.getGridConfigBoolean("enableInAppMessage", false)) {
            IvySdk.registerInAppMessageService(builder.inAppMessageClickListener);
        }

        if (IvySdk.hasGridConfig("AIHelp")) {
            JSONObject object = IvySdk.getGridConfigJson("AIHelp");
            try {
                String appKey = object.optString("appKey", null);
                if (TextUtils.isEmpty(appKey))
                    throw new NullPointerException("AIHelp config: app key is null");
                String appId = object.optString("appId", null);
                if (TextUtils.isEmpty(appId))
                    throw new NullPointerException("AIHelp config: app id is null");
                String domain = object.optString("domain", null);
                if (TextUtils.isEmpty(domain))
                    throw new NullPointerException("AIHelp config: domain is null");
                AIHelp.getInstance().init(activity.getApplicationContext(), appKey, domain, appId);
            } catch (Exception e) {
                Log.e(AIHelp.TAG, e.getMessage());
            }
        }

        setDisplayInNotch(activity);

    }

    public static void setHomeAdListener(HomeAdListener listener) {
    }

    private static String lastProcessDeepLinkData = null;

    private static void checkIntent(Intent intent) {
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.size() > 0) {
                Logger.debug(TAG, "get intent extras" + extras.toString());
                if (extras.containsKey("deeplink_data")) {
                    Object deeplinData = extras.get("deeplink_data");
                    IvySdk.debugToast("deeplink data >>> " + deeplinData);
                    if (lastProcessDeepLinkData != null && lastProcessDeepLinkData.equals(deeplinData)) {
                        Logger.debug(TAG, "Deep link data already processed, ignore");
                    } else {
                        if (deeplinData instanceof String && !"".equals(deeplinData)) {
                            EventBus.getInstance().fireEvent(CommonEvents.DEEPLINK_RECEIVED, deeplinData);
                            lastProcessDeepLinkData = String.valueOf(deeplinData);
                        }
                    }
                }
            }
        }

    }

    public static void onNewIntent(Intent intent) {
        Logger.debug(TAG, "onNewIntent");
        handleIntent(intent);
    }

    public static void onCreate(Context context) {
    }

    public static String encodeParams(String params) {
        return CommonUtil.encodeParams(IvySdk.CONTEXT, params);
    }

    public static String decodeParams(String params) {
        return CommonUtil.decodeParams(IvySdk.CONTEXT, params);
    }


    public static void registerAdEventListener(final AdEventListener listener) {
    }


    public static void pushLocalMessage(String key, String title, String content, long pushTime, int interval, boolean useSound, String soundName, String userInfo) {
        if (pushTime > 0 && pushTime < System.currentTimeMillis() / 1000) {
            pushTime = System.currentTimeMillis() + pushTime * 1000;
        } else {
            pushTime *= 1000;
        }
        IvySdk.push(key, title, content, pushTime, false, null, IvySdk.getUUID(), null, 0, useSound, soundName, userInfo);
    }

    public static void cancelLocalMessage(String key) {
        IvySdk.cancelPush(key);
    }

    @Deprecated
    public static void cancelMessage(String key) {
        Log.e(TAG, "cancelMessage deprecated");
    }

    public static void onStart() {
        IvySdk.onStart();
    }

    public static void onStop() {
        IvySdk.onStop();
    }

    public static void onPause() {
        IvySdk.onPause();
    }

    public static void onResume(Activity activity) {
        IvySdk.onResume();
        if (activity != null) {
            try {
                checkIntent(activity.getIntent());
            } catch (Throwable t) {
                //
            }
        }
    }

    public static void onDestroy(Activity activity) {
    }

    public static void onDestroy() {
        Logger.debug(TAG, "AndroidSdk onDestroy");
        IvySdk.onDestroy();
        sdkInitialized = false;
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        Logger.debug(TAG, "onActivityResult(), requestCode: " + requestCode);
        IvySdk.onActivityResult(requestCode, resultCode, data);
        if (facebookUserManager != null) {
            facebookUserManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static void setUserTag(String userTag) {
//        SdkServer.setUserTag(userTag);
    }

    public static int getRemoteConfigInt(String key) {
        int result = IvySdk.getRemoteConfigAsInt(key);
        Logger.debug(TAG, "Read Config Int: " + key + " ==> " + result);
        return result;
    }

    public static long getRemoteConfigLong(String key) {
        long result = IvySdk.getRemoteConfigAsLong(key);
        Logger.debug(TAG, "Read Config Long: " + key + " ==> " + result);
        return result;
    }

    public static double getRemoteConfigDouble(String key) {
        double result = IvySdk.getRemoteConfigAsDouble(key);
        Logger.debug(TAG, "Read Config Double: " + key + " ==> " + result);
        return result;
    }

    public static boolean getRemoteConfigBoolean(String key) {
        boolean result = IvySdk.getRemoteConfigAsBoolean(key);
        Logger.debug(TAG, "Read Config Boolean: " + key + " ==> " + result);
        return result;
    }

    public static String getRemoteConfigString(String key) {
        String result = IvySdk.getRemoteConfigAsString(key);
        Logger.debug(TAG, "Read Config String: " + key + " ==> " + result);
        return result;
    }

    public static boolean hasBanner(String tag) {
        return IvySdk.haveBanner();
    }

    public static boolean hasFull(String tag) {
        try {
            boolean got = IvySdk.haveInterstitial();
            if (!got) {
                Logger.debug(TAG, "No full, try to fetch one");
                IvySdk.fetchInterstitial();
            }
            return IvySdk.haveInterstitial();
        } catch (Throwable t) {
            //
            return false;
        }
    }

    private static long lastCheckRewardInterstitialTime = 0L;

    public static boolean hasRewardedInterstitial(String tag) {
        try {
            boolean got = IvySdk.haveRewardedInterstitial();
            if (!got) {
                if (System.currentTimeMillis() - lastCheckRewardInterstitialTime > 30000) {
                    Logger.debug(TAG, "No reward Interstitial, auto fetch");
                    IvySdk.fetchRewardedInterstitial();
                    lastCheckRewardInterstitialTime = System.currentTimeMillis();
                }
            }
            return IvySdk.haveRewardedInterstitial();
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean hasGDPR() {
//        return AdMaster.master().hasGDPR();
        return false;
    }

    public static void resetGDPR() {
//        AdMaster.master().resetGDPR();
    }

    public static void showBanner(String tag, int pos) {
        try {
            IvySdk.showBannerAd(pos);
        } catch (Exception ex) {
            //
        }
    }

    public static void showBanner(String tag, int pos, int animate) {
        Logger.debug(TAG, "showBanner(tag, pos, animate)");
        try {
            IvySdk.showBannerAd(pos);
        } catch (Exception ex) {
            //
        }
    }

    public static void showBannerNonExistent(String tag, int pos) {
        try {
            IvySdk.showBannerNonExistent(pos);
        } catch (Exception ex) {
            //
        }
    }

    public static void showBannerNonExistent(String tag, int pos, int animate) {
        Logger.debug(TAG, "showBanner(tag, pos, animate)");
        try {
            IvySdk.showBannerNonExistent(pos);
        } catch (Exception ex) {
            //
        }
    }

    public static void closeBanner(String tag) {
        Logger.debug(TAG, "closeBanner(tag)");
        try {
            IvySdk.closeBanners();
        } catch (Throwable t) {
            //
        }
    }

    @Deprecated
    public static View getBannerView(String tag) {
        Log.e(TAG, "getBannerView deprecated, will always result NULL");
//        return AdMaster.master().getBannerView(tag);
        return null;
    }

    @Deprecated
    public static void destroyBannerView(String tag, View v) {
//        AdMaster.master().destroyBannerView(tag, v);
    }

    public static final String FULL_TAG_START = "start";
    public static final String FULL_TAG_PAUSE = "pause";
    public static final String FULL_TAG_PASS_LEVEL = "passlevel";
    public static final String FULL_TAG_EXIT = "exit";
    public static final String FULL_TAG_CUSTOM = "custom";

    public static void moreGame() {
        IvySdk.moreGame();
    }

    public static void showFullAd(String tag) {
        try {
            showFullAd(tag, new AdListener());
        } catch (Exception ex) {
            //
        }
    }

    public static void showRewardedInterstitial(String tag, final AdListener listener) {
        Logger.debug(TAG, "showRewardedInterstitial");
        try {
            IvySdk.setAdCallback(IvyAdType.REWARDED_INTERSTITIAL, new IvyAdCallbacks() {
                @Override
                public void onAdClicked(IvyAdInfo adInfo) {
                    Logger.debug(TAG, "onAdClicked");
                    if (listener != null) {
                        listener.onAdClicked();
                    }
                }

                @Override
                public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                    Logger.debug(TAG, "onAdClosed, gotReward: " + gotReward);
                    if (listener != null) {
                        if (gotReward) {
                            listener.onAdReward(false);
                        }
                        listener.onAdClosed();
                    }
                }

                @Override
                public void onAdLoadFail(IvyAdType adInfo) {
                    Logger.debug(TAG, "onAdLoadFail");

                    if (listener != null) {
                        listener.onAdLoadFails();
                    }
                }

                @Override
                public void onAdLoadSuccess(IvyAdInfo adInfo) {
                    Logger.debug(TAG, "onAdLoadSuccess");

                    if (listener != null) {
                        listener.onAdLoadSuccess();
                    }
                }

                @Override
                public void onAdShowFail(IvyAdType adInfo) {
                    Logger.debug(TAG, "onAdShowFail");

                    if (listener != null) {
                        listener.onAdShowFails();
                    }
                }

                @Override
                public void onAdShowSuccess(IvyAdInfo adInfo) {
                    Logger.debug(TAG, "onAdShowSuccess");
                    if (listener != null) {
                        listener.onAdShow();
                    }
                }
            });
            IvySdk.showRewardedInterstitial(tag);

        } catch (Throwable t) {
            // ignore
        }
    }

    public static void showFullAd(String tag, final AdListener listener) {
        Logger.debug(TAG, "showFullAd called");
        try {
            IvySdk.setAdCallback(IvyAdType.INTERSTITIAL, new IvyAdCallbacks() {
                @Override
                public void onAdClicked(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdClicked();
                    }
                }

                @Override
                public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                    if (listener != null) {
                        listener.onAdClosed();
                    }
                }

                @Override
                public void onAdLoadFail(IvyAdType adInfo) {
                    if (listener != null) {
                        listener.onAdLoadFails();
                    }
                }

                @Override
                public void onAdLoadSuccess(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdLoadSuccess();
                    }
                }

                @Override
                public void onAdShowFail(IvyAdType adInfo) {
                    if (listener != null) {
                        listener.onAdShowFails();
                    }
                }

                @Override
                public void onAdShowSuccess(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdShow();
                    }
                }
            });
            IvySdk.showInterstitialAd(tag);
        } catch (Throwable t) {
            //
        }
    }

    public static void loadFullAd(String tag, AdListener listener) {
        try {
            if (listener != null) {
                IvySdk.setAdCallback(IvyAdType.INTERSTITIAL, new IvyAdCallbacks() {
                    @Override
                    public void onAdClicked(IvyAdInfo adInfo) {
                        if (listener != null) {
                            listener.onAdClicked();
                        }
                    }

                    @Override
                    public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                        if (listener != null) {
                            listener.onAdClosed();
                        }
                    }

                    @Override
                    public void onAdLoadFail(IvyAdType adInfo) {
                        if (listener != null) {
                            listener.onAdLoadFails();
                        }
                    }

                    @Override
                    public void onAdLoadSuccess(IvyAdInfo adInfo) {
                        if (listener != null) {
                            listener.onAdLoadSuccess();
                        }
                    }

                    @Override
                    public void onAdShowFail(IvyAdType adInfo) {
                        if (listener != null) {
                            listener.onAdShowFails();
                        }
                    }

                    @Override
                    public void onAdShowSuccess(IvyAdInfo adInfo) {
                        if (listener != null) {
                            listener.onAdShow();
                        }
                    }
                });
            }
            IvySdk.fetchInterstitial();
        } catch (Throwable t) {
            //
        }
    }

    public static void showRewardAd(String tag, AdListener listener) {
        try {
            IvySdk.setAdCallback(IvyAdType.REWARDED, new IvyAdCallbacks() {
                @Override
                public void onAdClicked(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdClicked();
                    }
                }

                @Override
                public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                    if (listener != null) {
                        listener.onAdReward(!gotReward);
                        listener.onAdClosed();
                    }
                }

                @Override
                public void onAdLoadFail(IvyAdType adInfo) {
                    if (listener != null) {
                        listener.onAdLoadFails();
                    }
                }

                @Override
                public void onAdLoadSuccess(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdLoadSuccess();
                    }
                }

                @Override
                public void onAdShowFail(IvyAdType adInfo) {
                    if (listener != null) {
                        listener.onAdShowFails();
                    }
                }

                @Override
                public void onAdShowSuccess(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdShow();
                    }
                }
            });
            IvySdk.showRewardAd(tag);
        } catch (Throwable t) {
            //
        }
    }

    private static long lastTriggerAutoFetchTime = 0;

    public static boolean hasRewardAd(String tag) {
        try {
            boolean hasReward = IvySdk.haveRewardAd();
            if (!hasReward) {
                if (System.currentTimeMillis() - lastTriggerAutoFetchTime > 30000) {
                    Logger.debug(TAG, "No reward, we trigger to fetch");
                    IvySdk.fetchRewardVideo();
                    lastTriggerAutoFetchTime = System.currentTimeMillis();
                }
                return false;
            }
            hasReward = IvySdk.haveRewardAd();
            Log.d(TAG, "hasRewardAd() : " + hasReward);
            return hasReward;
        } catch (Throwable t) {
            //
            return false;
        }
    }

    public static boolean hasNativeAd(String tag) {
        boolean checkNative = IvySdk.haveNative();
        Logger.debug(TAG, "hasNativeAd, tag: " + tag + " -> " + checkNative);
        return checkNative;
    }

    public static boolean hasNative(String tag) {
        boolean checkNative = IvySdk.haveNative();
        Logger.debug(TAG, "hasNativeAd, tag: " + tag + " -> " + checkNative);
        return checkNative;
    }

    public static boolean hasRewardInterstitial(String tag) {
        boolean checkRewardedInterstitial = IvySdk.haveRewardedInterstitial();
        Logger.debug(TAG, "haveRewardedInterstitial, tag: " + tag + " -> " + checkRewardedInterstitial);
        return checkRewardedInterstitial;
    }

    public static void closeNativeAd(String tag) {
        IvySdk.closeNativeAd();
    }

    /**
     * check the app open ad exists
     *
     * @param tag
     * @return
     */
    public static boolean hasAppOpenAd(String tag) {
        return false;
    }

    public static void clickUrl(final String url) {
        Activity activity = IvySdk.getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    IvyUtils.openBrowser(IvySdk.getActivity(), url);
                }
            });
        }
    }

    public static void launchApp(String packageName) {
        Activity context = IvySdk.getActivity();
        if (context == null || packageName == null) {
            return;
        }
        try {
            context.runOnUiThread(() -> {
                PackageManager pm = context.getPackageManager();
                Intent launchIntent = pm.getLaunchIntentForPackage(packageName);
                context.startActivity(launchIntent);
            });
        } catch (Throwable t) {
            Logger.error(TAG, "launchApp " + packageName + " failed", t);
        }
    }

    public static void openAppStore(String pkg) {
        Activity activity = IvySdk.getActivity();
        if (activity == null) {
            Logger.error(TAG, "Activity is finished?");
            return;
        }
        activity.runOnUiThread(() -> IvyUtils.openPlayStore(activity, pkg, "openstore", null));
    }

    public static boolean showNativeBanner(String tag, int x, int y, int w, int h, String configFile) {
        try {
            IvySdk.showNativeAd(x, y, w, h, 0, 0);
        } catch (Throwable t) {
            // ignore
        }
        return true;
    }

    public static boolean showNativeBanner(String tag, int x, int y, int w, int h, int sw, int sh) {
        try {
            IvySdk.showNativeAd(x, y, w, h, sw, sh);
        } catch (Throwable t) {
            // ignore
        }
        return true;
    }


    public static void hideNativeBanner(String tag) {
        try {
            IvySdk.closeNativeAd();
        } catch (Throwable t) {
            // ignore
        }
    }

    /**
     * 第三方应用是否正在播放音乐
     *
     * @return
     */
    public static boolean isOtherMusicOn() {
        try {
            AudioManager am = (AudioManager) IvySdk.CONTEXT.getSystemService(Context.AUDIO_SERVICE);
            return am.isMusicActive();
        } catch (Exception | Error e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 解绑与 native 广告关联的 view
     *
     * @param tag
     * @param view
     */
    public static void destroyNativeAdView(String tag, View view) {
//        AdMaster.master().destroyNativeAdView(tag, view);
    }

    /**
     * 实时加载一个 native 广告，加载成功以后返回的 view 在销毁的时候必须调用 {@link AndroidSdk#destroyNativeAdView(String, View)}
     *
     * @param tag
     * @param pageLayoutId 加载成功以后要绑定的 view 的 layout id
     * @param loadListener
     */
//    public static void loadNativeAd(String tag, int pageLayoutId, ClientNativeAd.NativeAdLoadListener loadListener) {
//        ModuleAds.loadNativeAd(tag, pageLayoutId, loadListener, null);
//    }

    /**
     * 换一个 native 广告看看
     *
     * @param tag
     * @param reusedView   上一次加载成功的 view, 可以直接利用
     * @param loadListener
     */
//    public static void reLoadNativeAd(String tag, View reusedView, ClientNativeAd.NativeAdLoadListener loadListener) {
//    }

    /**
     * 实时加载一个 native 广告，加载成功以后返回的 view 在销毁的时候必须调用 {@link AndroidSdk#destroyNativeAdView(String, View)}
     *
     * @param tag
     * @param pageLayoutId 加载成功以后要绑定的 view 的 layout id
     * @param loadListener
     */
//    public static void loadNativeAd(String tag, int pageLayoutId, ClientNativeAd.NativeAdLoadListener loadListener, ClientNativeAd.NativeAdClickListener clickListener) {
//        ModuleAds.loadNativeAd(tag, pageLayoutId, loadListener, clickListener);
//    }

    /**
     * 换一个 native 广告看看
     */
//    public static void reLoadNativeAd(String tag, View reusedView, ClientNativeAd.NativeAdLoadListener loadListener, ClientNativeAd.NativeAdClickListener clickListener) {
//        ModuleAds.reLoadNativeAd(tag, reusedView, loadListener, clickListener);
//    }
    public static void track(String eventName, String action, String label, int value) {
//        TrackApi.api().track(category, action, label, value);
        Log.d(TAG, "trackEvent called");
        Bundle bundle = new Bundle();

        bundle.putString("action", action);
        bundle.putString("label", label);
        bundle.putInt(FirebaseAnalytics.Param.VALUE, value);

        IvySdk.logEvent(eventName, bundle);
    }

    public static void track(String eventName, String action, String label, int value, int to) {
//        TrackApi.api().track(category, action, label, value);
        Log.d(TAG, "trackEvent called");
        Bundle bundle = new Bundle();

        bundle.putString("action", action);
        bundle.putString("label", label);
        bundle.putInt(FirebaseAnalytics.Param.VALUE, value);

        if (to == 1) {
            IvySdk.logToFirebase(eventName, bundle);
        } else if (to == 2) {
            IvySdk.logToFacebook(eventName, bundle);
        } else if (to == 3) {
            IvySdk.logToAppsflyer(eventName, bundle);
        } else if (to == 4) {
            IvySdk.logIvyEvent(eventName, bundle);
        } else if (to == 5) {
            IvySdk.logToConversation(eventName, bundle);
        } else {
            IvySdk.logEvent(eventName, bundle);
        }
    }

    public static void track(String event, String data, int to) {
        Log.d(TAG, "trackEvent called");
        Bundle bundle = buildBundle(event, data);
        if (to == 1) {
            IvySdk.logToFirebase(event, bundle);
        } else if (to == 2) {
            IvySdk.logToFacebook(event, bundle);
        } else if (to == 3) {
            IvySdk.logToAppsflyer(event, bundle);
        } else if (to == 4) {
            IvySdk.logIvyEvent(event, bundle);
        } else if (to == 5) {
            IvySdk.logToConversation(event, bundle);
        } else {
            IvySdk.logEvent(event, bundle);
        }
    }

    public static void track(String event, String data) {
        Log.d(TAG, "trackEvent called");
        Bundle bundle = buildBundle(event, data);
        // supress all game event o
        IvySdk.logEvent(event, bundle);
    }

    private static Bundle buildBundle(String event, String data) {
        Bundle bundle = new Bundle();
        if (data != null) {
            String[] splits = data.split(",");
            if (splits.length > 1) {
                for (int i = 0; i < splits.length; i += 2) {
                    try {
                        bundle.putDouble(splits[i], Double.parseDouble(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }

                    try {
                        bundle.putFloat(splits[i], Float.parseFloat(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }

                    try {
                        bundle.putLong(splits[i], Long.parseLong(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }

                    try {
                        bundle.putInt(splits[i], Integer.parseInt(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }

                    try {
                        bundle.putString(splits[i], splits[i + 1]);
                    } catch (Exception ignore) {
                    }
                }
            } else {
                bundle.putString("action", data);
                bundle.putString("label", data);
                bundle.putInt(FirebaseAnalytics.Param.VALUE, 1);
            }
        }
        return bundle;
    }

    public static void track(String event, Map<String, Object> extra) {
//        TrackApi.api().track(event, extra);
        Bundle bundle = new Bundle();
        if (extra != null) {
            Iterator<String> it = extra.keySet().iterator();
            while (it.hasNext()) {
                String k = it.next();
                Object v = extra.get(k);
                if (k != null && !"".equals(k) && v != null) {
                    if (v instanceof String) {
                        bundle.putString(k, String.valueOf(v));
                    } else if (v instanceof Integer) {
                        bundle.putInt(k, (Integer) v);
                    } else if (v instanceof Long) {
                        bundle.putLong(k, (Long) v);
                    } else if (v instanceof Boolean) {
                        bundle.putBoolean(k, (Boolean) v);
                    } else if (v instanceof Float) {
                        bundle.putFloat(k, (Float) v);
                    } else if (v instanceof Double) {
                        bundle.putDouble(k, (Double) v);
                    }
                }
            }
        }

        IvySdk.logEvent(event, bundle);
    }

    public static void track(String screenName) {
        Bundle bundle = new Bundle();
        IvySdk.logEvent(screenName, bundle);
    }


    public static void trackAll(String event) {
//        TrackApi.api().trackAll(event, new HashMap<String, Object>());
        IvySdk.logEvent(event, new Bundle());
    }

    public static boolean isPaymentValid() {
        return paymentSystemValid;
    }

    public static void querySKUDetail(int billId, OnSkuDetailsListener onSkuDetailsListener) {
        JSONObject gridData = GridManager.getGridData();
        if (gridData == null) {
            Log.d(TAG, "No grid data found");
            return;
        }
        if (!gridData.has("payment")) {
            Log.d(TAG, "Grid data not configured for payment");
            return;
        }
        JSONObject checkout = gridData.optJSONObject("payment").optJSONObject("checkout");
        JSONObject product = checkout != null && checkout.has(String.valueOf(billId)) ? checkout.optJSONObject(String.valueOf(billId)) : null;
        if (product == null) {
            return;
        }
        String productId = product.optString("feename");
        List<String> iapIds = new ArrayList<>();
        iapIds.add(productId);
        IvySdk.querySKUDetail(iapIds, onSkuDetailsListener);
    }

    public static String getSKUDetail(int billId) {
        if (isXsollaSupport()) {
            DLog.d("Android xsolla pay called, id: " + billId);
            if (IvySdk.xsollaPurchaseImpl != null) {
                Product product = IvySdk.xsollaPurchaseImpl.getProductDetail(billId + "");
                if (product != null) return product.toJson().toString();
            } else {
                DLog.e("xsolla impl is null, has called onCreate??");
            }
            return null;
        }
        JSONObject gridData = GridManager.getGridData();
        if (gridData == null) {
            Log.d(TAG, "No grid data found");
            return null;
        }
        if (!gridData.has("payment")) {
            Log.d(TAG, "Grid data not configured for payment");
            return null;
        }
        JSONObject checkout = gridData.optJSONObject("payment").optJSONObject("checkout");
        JSONObject product = checkout != null && checkout.has(String.valueOf(billId)) ? checkout.optJSONObject(String.valueOf(billId)) : null;
        if (product == null) {
            return null;
        }
        String productId = product.optString("feename");
        SKUDetail skuDetail = IvySdk.getSKUDetail(productId);
        if (skuDetail != null) return skuDetail.toString();
        return null;
    }

    public static void setPayVerifyUrl(String verifyUrl) {
        IvySdk.setPayVerifyUrl(verifyUrl);
    }

    public static void pay(final int bill) {
        pay(bill, null, null);
    }

    public static void pay(final int bill, final String itemName, final String payload) {
        try {
            if (builder != null && builder.paymentResultListener != null) {
                Log.d(TAG, "Android pay called, id: " + bill);
                if (isXsollaSupport()) {
                    DLog.d("Android xsolla pay called, id: " + bill);
                    if (IvySdk.xsollaPurchaseImpl != null) {
                        IvySdk.xsollaPurchaseImpl.buy(bill + "", payload);
                    } else {
                        DLog.e("xsolla impl is null, has called onCreate??");
                    }
                    return;
                }
                JSONObject gridData = GridManager.getGridData();
                if (gridData == null) {
                    Log.d(TAG, "No grid data found");
                    return;
                }
                if (!gridData.has("payment")) {
                    Log.d(TAG, "Grid data not configured for payment");
                    return;
                }
                JSONObject checkout = gridData.optJSONObject("payment").optJSONObject("checkout");
                JSONObject product = checkout != null && checkout.has(String.valueOf(bill)) ? checkout.optJSONObject(String.valueOf(bill)) : null;
                if (product != null) {
                    try {
                        product.put("billId", String.valueOf(bill));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    String productId = product.optString("feename");
                    if (itemName == null) {

                    }
                    IvySdk.pay(productId, itemName, payload);
                } else {
                    Log.e(TAG, bill + " no defined.");
                }
            } else {
                Log.e(TAG, "builder.paymentResultListener is not defined, ignore");
            }
        } catch (Throwable t) {
            // ignore
        }
    }

    public static void changeSku(int oldBillId, int newBillId, String payload) {
        JSONObject gridData = GridManager.getGridData();
        if (gridData == null || !gridData.has("payment")) {
            return;
        }
        JSONObject checkout = GridManager.getGridData().optJSONObject("payment").optJSONObject("checkout");
        JSONObject newProduct = checkout.has(String.valueOf(newBillId)) ? checkout.optJSONObject(String.valueOf(newBillId)) : null;
        JSONObject oldProduct = checkout.has(String.valueOf(oldBillId)) ? checkout.optJSONObject(String.valueOf(oldBillId)) : null;
        if (newProduct == null || oldProduct == null) {
            Logger.error(TAG, "invalid product");
            return;
        }
        IvySdk.changeSku(newProduct.optString("feename"), oldProduct.optString("feename"), payload);
    }

    public static void query(int bill) {
        if (builder != null && builder.paymentResultListener != null) {
            query(bill, builder.paymentResultListener);
        }
    }

    public static void getPurchaseHistory(String skuType, IPurchaseQueryCallback<String> callback) {
        IvySdk.getPurchaseHistory(skuType, new IPurchaseQueryCallback<List<JSONObject>>() {
            @Override
            public void onResult(List<JSONObject> data) {
                if (data == null) {
                    if (callback != null) callback.onResult("[]");
                } else {
                    if (callback != null) {
                        callback.onResult(new JSONArray(data).toString());
                    }
                }
            }
        });

//        List<JSONObject> history = IvySdk.getPurchaseHistory(skuType);
//        if (history == null) {
//            return "[]";
//        }
//        JSONArray array = new JSONArray(history);
//        return array.toString();
    }

    public static void isSubscriptionActive(int billId, IPurchaseQueryCallback<Boolean> callback) {
        IvySdk.getPurchaseHistory("subs", new IPurchaseQueryCallback<List<JSONObject>>() {
            @Override
            public void onResult(List<JSONObject> data) {
                if (data == null) {
                    if (callback != null) callback.onResult(false);
                } else {
                    for (JSONObject item : data) {
                        if (item.optInt("billId") == billId) {
                            if (callback != null) callback.onResult(true);
                            return;
                        }
                    }
                    if (callback != null) callback.onResult(false);
                }
            }
        });


//        List<JSONObject> history = IvySdk.getPurchaseHistory("subs");
//        if (history == null) {
//            Logger.error(TAG, "getPurchaseHistory result null, billing client initialize failed?");
//            return false;
//        }
//        for (int i = 0; i < history.size(); i++) {
//            JSONObject purchasedItem = history.get(i);
//            if (purchasedItem.optInt("billId") == billId) {
//                return true;
//            }
//        }
//        return false;
    }

    public static void query(int bill, final PaymentSystemListener listener) {
        Logger.debug(TAG, "query id: " + bill);
        try {
            JSONObject gridData = GridManager.getGridData();
            if (gridData == null || !gridData.has("payment")) {
                return;
            }
            if (isXsollaSupport()) {
                DLog.d("xsolla start query:" + bill);
                if (IvySdk.xsollaPurchaseImpl != null) {
                    IvySdk.xsollaPurchaseImpl.queryPaymentState(bill + "");
                } else {
                    DLog.e("xsolla impl is null, has called onCreate??");
                }
                return;
            }

            if (bill == -1) {
                IvySdk.queryUnconsumedPurchases();
                return;
            }
            JSONObject checkout = GridManager.getGridData().optJSONObject("payment").optJSONObject("checkout");
            JSONObject product = checkout.has(String.valueOf(bill)) ? checkout.optJSONObject(String.valueOf(bill)) : null;
            if (product != null) {
                IvySdk.queryPurchases(product.optString("feename"));
            } else {
                Logger.warning(TAG, bill + " no defined.");
            }
        } catch (Throwable t) {
            // ignore
        }
    }

    public static String getPrices() {
        return IvySdk.getInventory().toString();
    }

    private static void doShare(String shareUrl, String quote, String hashtag, ShareResultListener shareResultListener) {
        Activity a = IvySdk.getActivity();
        if (a == null || a.isFinishing()) {
            return;
        }

        if (shareUrl == null) {
            StringBuilder sb = new StringBuilder(IvyUtils.GOOGLE_PLAY_URL + a.getPackageName());
            sb.append("&referrer=utm_source%3D").append("ivy")
                    .append("%26utm_campaign%3D").append(a.getPackageName())
                    .append("%26utm_medium%3D").append("share")
                    .append("%26utm_term%3D").append("share")
                    .append("%26utm_content%3D").append("share");

            shareUrl = sb.toString();
        }

        final String resultShareUrl = shareUrl;
        a.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, resultShareUrl);
                    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);

                    boolean useFacebookShare = IvySdk.getGridConfigBoolean("useFacebookShare", true);
                    if (!useFacebookShare || facebookUserManager == null) {
                        try {
                            shareIntent.setPackage(null);
                            a.startActivity(Intent.createChooser(shareIntent, "Nice play share"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return;
                    }

                    String fb = "com.facebook.katana";
                    if (IvyUtils.validApp(a, fb, shareIntent)) {
                        facebookUserManager.share(a, resultShareUrl, quote, hashtag, shareResultListener);
                    } else {
                        try {
                            shareIntent.setPackage(null);
                            a.startActivity(Intent.createChooser(shareIntent, "Nice play share"));
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Throwable t) {
                    Logger.error(TAG, "share exception", t);
                }
            }
        });
    }

    public static void share() {
        try {
            doShare(null, null, null, null);
        } catch (Throwable t) {
            //
        }
    }

    public static void share(String url, String quote) {
        doShare(url, quote, null, null);
    }

    public static void share(String url, String quote, String hashtag, ShareResultListener shareResultListener) {
        doShare(url, quote, hashtag, shareResultListener);
    }

    public static void shareVideo(String url) {
        doShare(url, null, null, null);
    }

    public static boolean shareVideo_(String url) {
        return false;
    }

    public static boolean shareBitmap(Bitmap bmp) {
        return false;
    }

    public static boolean shareBitmap(final String url) {
        return false;
    }

    public static void support(String email, String extra) {
        Activity context = IvySdk.getActivity();
        if (context == null) {
            Logger.error(TAG, "support(): activity is finished?");
            return;
        }
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email));
                intent.putExtra(Intent.EXTRA_TEXT, extra == null ? "" : extra);

                try {
                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void support(String email, String title, String extra) {
        Activity context = IvySdk.getActivity();
        if (context == null) {
            Logger.error(TAG, "support(): activity is finished?");
            return;
        }
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email));
                if (title != null) {
                    intent.putExtra(Intent.EXTRA_SUBJECT, title);
                }
                intent.putExtra(Intent.EXTRA_TEXT, extra == null ? "" : extra);

                try {
                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void refreshExtraData(final UrlListener listener) {
        Logger.error(TAG, "refreshExtraData not supported!");
    }

    public static void rateUs() {
        rateUs(5);
    }

    public static void rateUs(float star) {
        try {
            IvySdk.rate((int) star);
        } catch (Throwable t) {
            //
        }
    }

    public static void toast(final String msg) {
        IvySdk.showToast(msg);
    }

    public static String getExtraData() {
        JSONObject extraJson = GridManager.getGridData().optJSONObject("data");
        if (extraJson != null) {
            return extraJson.toString();
        }
        return "";
    }

    public static void onQuit() {
        try {
            IvySdk.onQuit();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void forceQuit() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void logoutFacebook() {
        try {
            facebookUserManager.logout();
        } catch (Throwable t) {
            Logger.error(TAG, "logoutFacebook exception", t);
        }
    }

    public static void loginFacebook(FacebookLoginListener facebookLoginListener) {
        try {
            facebookUserManager.login(IvySdk.getActivity(), facebookLoginListener);
        } catch (Throwable t) {
            //
        }
    }


    public static void login() {
        try {
            facebookUserManager.login(IvySdk.getActivity(), new FacebookLoginListener() {
                @Override
                public void onReceiveLoginResult(boolean success) {
                    Logger.debug(TAG, "Facebook login success");
                    if (builder != null && builder.userCenterListener != null) {
                        builder.userCenterListener.onReceiveLoginResult(success);
                    }
                }

                @Override
                public void onReceiveFriends(String friends) {
                    Logger.debug(TAG, "Get Facebook friends" + friends);
                    if (builder != null && builder.userCenterListener != null) {
                        builder.userCenterListener.onReceiveFriends(friends);
                    }
                }
            });
        } catch (Throwable t) {
            //
        }
    }

    public static void logout() {
        Logger.debug(TAG, "logout()");
        if (facebookUserManager != null) {
            facebookUserManager.logout();
        }
    }

    public static boolean isLogin() {
        if (facebookUserManager != null) {
            return facebookUserManager.isLogin();
        }
        return false;
    }

    public static void invite() {
//        UserMaster.master().invite();
    }

    public static void challenge(String title, String message) {
//        UserMaster.master().challenge(title, message);
    }


    public static String me() {
        if (facebookUserManager == null) {
            Logger.error(TAG, "Facebook SDK not initialized?");
            return "{}";
        }
        String me = facebookUserManager.me();
        Logger.debug(TAG, "I am " + me);
        return me;
    }

    public static String getFacebookUserId() {
        if (facebookUserManager != null) {
            return facebookUserManager.getUserId();
        }
        return "";
    }

    public static String friends() {
        try {
            if (facebookUserManager == null) {
                Logger.error(TAG, "Facebook SDK not initialized?");
                return null;
            }
            return facebookUserManager.friends(new FacebookLoginListener() {
                @Override
                public void onReceiveLoginResult(boolean success) {
                    Logger.debug(TAG, "Facebook login success");
                    if (builder != null && builder.userCenterListener != null) {
                        builder.userCenterListener.onReceiveLoginResult(success);
                    }
                }

                @Override
                public void onReceiveFriends(String friends) {
                    Logger.debug(TAG, "Facebook login success");
                    if (builder != null && builder.userCenterListener != null) {
                        builder.userCenterListener.onReceiveFriends(friends);
                    }
                }
            });
        } catch (Throwable t) {
            //
            return null;
        }
    }

    public static boolean isNetworkConnected() {

        return IvyUtils.isOnline(IvySdk.getActivity());
    }

    /**
     * get
     * download this url and cache the result into internal storage
     *
     * @param url
     * @return file path
     */
    public static String cacheUrl(String url) {
        try {
            return PromoteAdManager.checkCreativeReloaded(url);
        } catch (Throwable t) {
            //
        }
        return "";
    }

    public static String cacheUrl(String url, boolean external) {
        try {
            return PromoteAdManager.checkCreativeReloaded(url);
        } catch (Throwable t) {
            //
        }
        return "";
    }

    public static void openFacebook(String userId, String userName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            //打开Facebook应用，xxxxxx为账户id，是一串数字，例如：101480009140700
            Uri uri = Uri.parse("fb://page/" + userId);
            intent.setData(uri);
            IvySdk.getActivity().startActivity(intent);
        } catch (Exception e) {
            //若未安装app，则打开Facebook网页，xxx为账户名
            try {
                Uri uri = Uri.parse("https://www.facebook.com/" + userName);
                intent.setData(uri);
                IvySdk.getActivity().startActivity(intent);
            } catch (Exception e1) {
                Logger.error("open facebook page error:" + e.getMessage());
            }
        }
    }


    public static final int CONFIG_KEY_APP_ID = 1;
    public static final int CONFIG_KEY_LEADER_BOARD_URL = 2;
    public static final int CONFIG_KEY_API_VERSION = 3;
    public static final int CONFIG_KEY_SCREEN_WIDTH = 4;
    public static final int CONFIG_KEY_SCREEN_HEIGHT = 5;
    public static final int CONFIG_KEY_LANGUAGE = 6;
    public static final int CONFIG_KEY_COUNTRY = 7;
    public static final int CONFIG_KEY_VERSION_CODE = 8;
    public static final int CONFIG_KEY_VERSION_NAME = 9;
    public static final int CONFIG_KEY_PACKAGE_NAME = 10;
    public static final int CONFIG_KEY_UUID = 11;

    public static final int SDK_CONFIG_KEY_JSON_VERSION = 21;

    public static final int SDK_CONFIG_KEY_FIREBASE_USERID = 22;

    public static String getConfig(int configKey) {
        Logger.debug(TAG, "Get Config: " + configKey);
        try {
            switch (configKey) {
                case CONFIG_KEY_APP_ID:
                    return GridManager.getGridData().optString("appid");
                case CONFIG_KEY_LEADER_BOARD_URL:
                    JSONObject sns = GridManager.getGridData().optJSONObject("sns");
                    if (sns != null && sns.has("leader_board_url")) {
                        return sns.optString("leader_board_url");
                    } else {
                        return "";
                    }
                case CONFIG_KEY_API_VERSION:
                    return GridManager.getGridData().optString("v_api", "26");
                case CONFIG_KEY_SCREEN_WIDTH: {
                    Activity activity = IvySdk.getActivity();
                    if (activity == null) {
                        return "0";
                    }
                    DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
                    if (displayMetrics != null) {
                        return String.valueOf(displayMetrics.widthPixels);
                    } else {
                        return "0";
                    }
                }
                case CONFIG_KEY_SCREEN_HEIGHT: {
                    Activity activity = IvySdk.getActivity();
                    if (activity == null) {
                        return "0";
                    }
                    DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
                    if (displayMetrics != null) {
                        return String.valueOf(displayMetrics.heightPixels);
                    }
                    return "0";
                }
                case CONFIG_KEY_LANGUAGE:
                    return Locale.getDefault().getLanguage();
                case CONFIG_KEY_COUNTRY:
                    return IvySdk.getCountryCode();
                case CONFIG_KEY_VERSION_CODE:
                    try {
                        Context context = IvySdk.CONTEXT;
                        PackageManager packageManager = context.getPackageManager();
                        String name = context.getPackageName();
                        PackageInfo info = packageManager.getPackageInfo(name, 0);
                        return String.valueOf(info.versionCode);
                    } catch (Exception var5) {
                        return "0";
                    }
                case CONFIG_KEY_VERSION_NAME:
                    try {
                        Context context = IvySdk.CONTEXT;
                        PackageManager packageManager = context.getPackageManager();
                        String name = context.getPackageName();
                        PackageInfo info = packageManager.getPackageInfo(name, 0);
                        return info.versionName;
                    } catch (Exception var5) {
                        return "0";
                    }
                case CONFIG_KEY_PACKAGE_NAME:
                    return IvySdk.CONTEXT.getPackageName();
                case CONFIG_KEY_UUID:
                    return IvySdk.getUUID();
                case SDK_CONFIG_KEY_JSON_VERSION:
                    String domain = IvySdk.getGridConfigString("domain");
                    if (domain == null || "".equals(domain)) {
                        return "";
                    }
                    try {
                        Uri uri = Uri.parse(domain);
                        if (uri != null) {
                            return uri.getQueryParameter("v_api");
                        }
                    } catch (Throwable t) {
                        // ignore
                        t.printStackTrace();
                    }
                    return "";
                case SDK_CONFIG_KEY_FIREBASE_USERID:
                    return "";
                default:
                    Log.e(TAG, "ATTENTION, Unknow config key for " + configKey);
                    return "";
            }
        } catch (Throwable t) {
            return "";
        }
    }


    public static String getConfig(String packageName, int configKey) {
        switch (configKey) {
            case CONFIG_KEY_VERSION_CODE:
                try {
                    return String.valueOf(IvySdk.CONTEXT.getPackageManager().getPackageInfo(packageName, 0).versionCode);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "0";
                }

            case CONFIG_KEY_VERSION_NAME:
                try {
                    return String.valueOf(IvySdk.CONTEXT.getPackageManager().getPackageInfo(packageName, 0).versionName);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "0.0";
                }
            default:
                Log.e(TAG, "ATTENTION, Unknow config key for " + configKey);
                return "";
        }
    }

    public static void alert(final String title, final String message) {
        Activity activity = IvySdk.getActivity();

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new AlertDialog.Builder(activity)
                            .setTitle(title)
                            .setMessage(message)
                            .setPositiveButton(android.R.string.ok, null)
                            .create().show();
                }
            });
        }
    }

    public static void showDeliciousIconAd(final int x, final int y, final int w, final int h, final String config) {
        try {
            IvySdk.showDeliciousIcon(x, y, w, h, config);
        } catch (Throwable t) {
            //
        }
    }

    public static boolean hasDeliciousIconAd() {
        return true; //AdMaster.master().hasDeliciousIconAd();
    }

    public static void closeDeliciousIconAd() {
        try {
            IvySdk.closeDeliciousIconAd();
        } catch (Throwable t) {
            //
        }
    }

    public static void showDeliciousBannerAd(final int x, final int y, final int w, final int h, final String config) {
        try {
            IvySdk.showDeliciousBanner(x, y, w, h, "");
        } catch (Throwable t) {
            //
        }
    }

    public static boolean hasDeliciousBannerAd() {
//        return AdMaster.master().hasDeliciousBannerAd();
        return true;
    }

    public static void closeDeliciousBannerAd() {
//        AdMaster.master().closeDeliciousBannerAd();
        try {
            IvySdk.closeDeliciousBanner();
        } catch (Throwable t) {

        }
    }

    public static boolean hasDeliciousVideoAd() {
        return false;//AdMaster.master().hasDeliciousVideoAd();
    }

    public static void showDeliciousVideoAd(final String config) {
//        AdMaster.master().showDeliciousVideoAd(config);
    }

    public static boolean hasDeliciousAd() {
        return true; //AdMaster.master().hasDeliciousAd();
    }


    public static void setUserProperty(String key, String value) {
        IvySdk.setUserProperty(key, value);
    }

    public static void setUserID(String userID) {

    }

    public static void silentLoginGoogle(final GoogleListener listener) {
        try {
            Log.e("login", "start silent login google");
            IvySdk.slientLoginGoogle(listener);
        } catch (Throwable t) {
            if (listener != null) {
                listener.onFails();
            }
        }
    }

    public static void loginGoogle(final GoogleListener listener) {
        try {
            IvySdk.slientLoginGoogle(listener);
        } catch (Throwable t) {
            t.printStackTrace();
            if (listener != null) {
                listener.onFails();
            }
        }
    }

    public static void logoutGoogle(final GoogleListener listener) {
        IvySdk.logoutGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                if (listener != null) {
                    listener.onSuccess(googleId, googleEmail);
                }
            }

            @Override
            public void onFails() {
                if (listener != null) {
                    listener.onFails();
                }
            }
        });
    }

    public static boolean isGoogleLogin() {
        try {
            return IvySdk.isGoogleLogin();
        } catch (Throwable t) {
            //
        }
        return false;
    }

    public static void updateGoogleAchievement(String id, int step, final GoogleListener listener) {
        try {
            IvySdk.updateGoogleAchievement(id, step, listener);
        } catch (Throwable t) {

        }
    }

    public static void updateGoogleLeaderBoard(String id, long value, final GoogleListener listener) {
        try {
            IvySdk.updateGoogleLeaderBoard(id, value, new GoogleListener() {
                @Override
                public void onSuccess(String googleId, String googleEmail) {
                    if (listener != null) {
                        listener.onSuccess(googleId, googleEmail);
                    }
                }

                @Override
                public void onFails() {
                    if (listener != null) {
                        listener.onFails();
                    }
                }
            });
        } catch (Throwable t) {

        }
    }

    public static void showGoogleAchievements() {
        try {
            IvySdk.showGoogleAchievement();
        } catch (Throwable t) {
            //
        }
    }

    public static void showGoogleLeaderBoards(String... ids) {
        try {
            IvySdk.displayGameLeaderboards();
        } catch (Throwable t) {
            //
        }
    }

    public static boolean isGoogleSupport() {
        try {
            return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(IvySdk.CONTEXT) == 0;
        } catch (Throwable t) {
            //
        }
        return false;
    }

    public static boolean scheduleTask(int time, String activityName, String extra) {
        try {
            return IvySdk.scheduleTask(time, activityName, extra);
        } catch (Throwable t) {
            return false;
        }
    }

    public static boolean cancelTask(String activityName, String extra) {
        try {
            return IvySdk.cancelTask(activityName, extra);
        } catch (Throwable t) {
            //
            return false;
        }
    }

    public static void updateGoogleLeaderBoard(int tag, String id, long value) {
        Log.d(TAG, "updateGoogleLeaderBoard called");
        try {
            IvySdk.updateGoogleLeaderBoard(id, value, new GoogleListener() {
                @Override
                public void onSuccess(String googleId, String googleEmail) {
                }

                @Override
                public void onFails() {
                }
            });
        } catch (Throwable t) {
            //
        }
    }

    public static void showGoogleLeaderBoards() {
        Log.d(TAG, "showGoogleLeaderBoards: ");
        try {
            IvySdk.displayGameLeaderboards();
        } catch (Throwable t) {
            //
        }
    }

    public static boolean hasNotch() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return false;
        }
        Activity activity = IvySdk.getActivity();
        if (activity == null) {
            return false;
        }

        try {
            boolean flag = NotchScreenManager.getInstance().hasNotch(activity);
            JSONObject notchConfig = IvySdk.getGridConfigJson("notch");
            if (notchConfig != null) {
                String deviceModel = Build.MODEL != null ? Build.MODEL.toLowerCase(Locale.ENGLISH) : "unknown";
                if (notchConfig.has(deviceModel)) {
                    int notchHeight = notchConfig.optInt(deviceModel, 0);
                    if (notchHeight > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            return flag;
        } catch (Throwable t) {
            // ignore
        }
        return false;
    }

    public static int getNotchHeight() {
        Activity activity = IvySdk.getActivity();
        if (activity == null) {
            return 0;
        }

        try {
            int notchHeight = NotchScreenManager.getInstance().getNotchHeight(activity);

            JSONObject notchConfig = IvySdk.getGridConfigJson("notch");
            if (notchConfig != null) {
                String deviceModel = Build.MODEL != null ? Build.MODEL.toLowerCase(Locale.ENGLISH) : "unknown";
                if (notchConfig.has(deviceModel)) {
                    return notchConfig.optInt(deviceModel);
                }
            }
            int max = activity.getResources().getDisplayMetrics().heightPixels / 20;
            if (notchHeight > 0) {
                if (notchHeight > max) {
                    notchHeight = max;
                }
                return notchHeight;
            }

            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                notchHeight = activity.getResources().getDimensionPixelSize(resourceId);
            }
            if (notchHeight > max) {
                notchHeight = max;
            }
            return notchHeight;
        } catch (Throwable t) {
            // ignore
        }
        return 0;
    }

    @Deprecated
    public static void clickNativeAd(String tag) {
    }

    @Deprecated
    public static boolean isActiveUser(int day) {
        Log.e(TAG, "isActiveUser deprecated");
        return false;
    }


    @Deprecated
    public static boolean hasHomeAd() {
        Log.e(TAG, "hasHomeAd deprecated, will always return false");
        return false;
    }

    @Deprecated
    public static void showHomeAd(AdListener listener) {
        Log.e(TAG, "showHomeAd deprecated, will always return false");
    }

    @Deprecated
    public static void pushMessage(String key, String title, String content, long pushTime, boolean localTimeZone, String fbIds, String uuids, String topics, int iosBadge, boolean useSound, String soundName, String extraData) {
        Log.e(TAG, "pushMessage deprecated, please use pushLocalMessage");
    }

    @Deprecated
    public static boolean showNativeBanner(String tag, int xPercent, int yPercent, String configFile) {
        Log.e(TAG, "showNativeBanner deprecated, please use showNativeAd");
        return false;
    }

    @Deprecated
    public static void showNativeAdScrollView(String tag, int hideBehavior, int yPercent) {
        Log.e(TAG, "showNativeAdScrollView deprecated");
    }

    @Deprecated
    public static void hideNativeAdScrollView(String tag) {
        Log.e(TAG, "hideNativeAdScrollView deprecated");
    }

    @Deprecated
    public static String showNativeAd(String tag) {
        Log.e(TAG, "showNativeAd(String tag) Deprecated");
        try {
            IvySdk.showNativeAd(0, 0, -1, -2, 0, 0);
        } catch (Throwable t) {
            //
        }
        return "";
    }

    public static void onKill() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    @Deprecated
    public static void like() {
        Log.e(TAG, "Like deprecated");
    }

    @Deprecated
    public static boolean isCachingUrl(String url) {
        Log.e(TAG, "isCachingUrl deprecated");
        return false;
    }

    @Deprecated
    public static void cacheUrl(int tag, String url) {
        Log.e(TAG, "cacheUrl deprecated");
    }

    @Deprecated
    public static void cacheUrl(String url, boolean external, final UrlListener listener) {
        Log.e(TAG, "cacheUrl deprecated");
    }


    public static void verifyIdCard() {
    }

    public static void resetIdCheck() {
    }

    public static int getIdCardVerifyedAge() {
        return 0;
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    public static void setDisplayInNotch(Activity activity) {
        try {
            boolean displayedInNotch = true;
            ApplicationInfo ai = null;
            try {
                ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            if (ai != null && ai.metaData != null) {
                Object o = ai.metaData.get("din");
                if (o instanceof Boolean) {
                    displayedInNotch = (Boolean) o;
                }
            }
            if (!displayedInNotch) {
                NotchScreenManager.getInstance().setEnableDisplayInNotch(false);
            }
            NotchScreenManager.getInstance().setDisplayInNotch(activity);
        } catch (Throwable ex) {
            //  ex.printStackTrace();
        }
    }

    public static void copyText(final String str) {
        try {
            Activity activity = IvySdk.getActivity();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                            clipboard.setText(str);
                        } else {
                            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("clip_text", str);
                            clipboard.setPrimaryClip(clip);
                        }
                    } catch (Throwable t) {
                        //
                    }
                }
            });
        } catch (Throwable e) {
            Logger.error(TAG, "Copytext exception", e);
        }
    }

    public static long getFreeMem() {
        try {
            Activity activity = IvySdk.getActivity();
            if (activity == null) {
                Logger.warning(TAG, "activity is null, getFreeMem is impossible");
                return -1;
            }

            ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
            manager.getMemoryInfo(info);

            Logger.debug(TAG, "Memory Info >>> avail: " + info.availMem + ", total: " + info.totalMem + ", isLowMemory: " + info.lowMemory);
            return info.availMem;
        } catch (Throwable t) {
            Logger.error(TAG, "getFreeMem exception", t);
        }
        return -1L;
    }

    public static String getKeyHash() {
        Activity activity = IvySdk.getActivity();
        if (activity == null) {
            Logger.error(TAG, "Activity is not initialized, forgot onCreate?");
            return "";
        }
        return CommonUtil.getKeyStoreHash(activity);
    }


    public static void setIdCardVerified(int age) {

    }

    public static String getSdkType() {
        return "adsfall";
    }

    public static void handleInAppMessagePayload(Bundle bundle) {
        Logger.debug(TAG, "handleMessagePayload >>> ");
        if (builder.inAppMessageClickListener == null) {
            return;
        }

        String title = bundle.getString("inapp_message_title", "");
        String body = bundle.getString("inapp_message_body", "");
        String action = bundle.getString("inapp_message_action", null);
        String id = bundle.getString("inapp_message_id", "");

        if (action == null) {
            return;
        }

        try {
            JSONObject actionParams = IvyUtils.getUrlParametersWithJson(action);
            if (actionParams.has("title")) {
                title = actionParams.optString("title");
            }
            if (actionParams.has("body")) {
                body = actionParams.optString("body");
            }
            if (actionParams.has("id")) {
                id = actionParams.optString("id");
            }

            JSONObject inAppMessageJson = new JSONObject();
            inAppMessageJson.put("id", id);
            inAppMessageJson.put("title", title);
            inAppMessageJson.put("body", body);
            inAppMessageJson.put("action", action);
            builder.inAppMessageClickListener.messageDisplayed(id, inAppMessageJson.toString());
        } catch (Throwable t) {
            Logger.error(TAG, "handleInAppMessagePayload exception", t);
        }
    }

    // handle app link intent
    public static void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String payload = bundle.getString("inapp_message_action");
            if (payload != null && !"".equals(payload)) {
                Logger.debug(TAG, "message payload >>> " + payload);
                handleInAppMessagePayload(bundle);
            }
        }
    }

    public static void playerFinder() {
        Activity activity = IvySdk.getActivity();
        if (activity == null || facebookUserManager == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                facebookUserManager.playerFinder(activity);
            }
        });
    }

    public static String getFirebaseUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return "";
    }

    public static void setHelpEngagementMessageRead(final String customerName) {
        String projectId = IvySdk.getGridConfigString("helpengage.appId", "62e72ac738480f797ef40eb7");
        if (TextUtils.isEmpty(projectId)) {
            Logger.error(TAG, "help engagement error, projectId empty");
            return;
        }
        String servicePollUrl = IvySdk.getGridConfigString("helpengage.setread", "https://eii2wnwjeukuf2oj6vxjustpve0paafz.lambda-url.ap-southeast-1.on.aws/");
        Request.Builder requestBuilder = new Request.Builder();
        HttpUrl.Builder httpBuilder = HttpUrl.parse(servicePollUrl).newBuilder();

        httpBuilder.addQueryParameter("projectId", projectId);
        httpBuilder.addQueryParameter("customerId", customerName);

        Request request = requestBuilder.url(httpBuilder.build()).build();
        IvySdk.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.error(TAG, "checkHelpEngagement exception", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });

    }

    /**
     * 从poll地址获得当前玩家是否有新的聊天信息，如果获取到新的聊天信息，应该回调给客户端，客户端负责展示并处理。
     * 此检查的调用由客户端负责发起，建议在游戏逻辑理不定期的调用，以获得最新消息的回调。
     *
     * @param customerName
     */
    public static void checkHelpEngagement(final String customerName) {
        String projectId = IvySdk.getGridConfigString("helpengage.appId", "62e72ac738480f797ef40eb7");
        if (TextUtils.isEmpty(projectId)) {
            Logger.error(TAG, "help engagement error, projectId empty");
            return;
        }
        String servicePollUrl = IvySdk.getGridConfigString("helpengage.poll", "https://fw26xfzuzw2jaqchpbm3vvcq2a0tpclt.lambda-url.ap-southeast-1.on.aws/");
        Request.Builder requestBuilder = new Request.Builder();
        HttpUrl.Builder httpBuilder = HttpUrl.parse(servicePollUrl).newBuilder();

        httpBuilder.addQueryParameter("projectId", projectId);
        httpBuilder.addQueryParameter("customerId", customerName);

        Request request = requestBuilder.url(httpBuilder.build()).build();
        IvySdk.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Logger.error(TAG, "checkHelpEngagement exception", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String result = response.body().string();
                    if (result != null && !"".equals(result)) {
                        JSONObject json = new JSONObject(result);
                        if (json.has("result") && "ok".equals(json.optString("result")) && json.has("response")) {
                            JSONObject realResponse = json.optJSONObject("response");
                            if (realResponse != null) {
                                if (builder != null && builder.onHelpEngagementListener != null) {
                                    builder.onHelpEngagementListener.onReceiveHelpEngagementMessage(realResponse.toString());
                                }
                            }
                        }
                    }
                    Logger.debug(TAG, result);
                } catch (Throwable t) {

                }
            }
        });

    }

    public static void helpshift(final String customerName, String info) {
        String projectId = IvySdk.getGridConfigString("helpengage.appId", "62e72ac738480f797ef40eb7");
        Activity activity = IvySdk.getActivity();
        if (activity == null) {
            return;
        }

        if (TextUtils.isEmpty(projectId)) {
            Logger.error(TAG, "help engagement error, projectId empty");
            return;
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(info);
            jsonObject.put("app_version", getConfig(CONFIG_KEY_VERSION_NAME));
            jsonObject.put("device", Build.MODEL != null ? Build.MODEL.toLowerCase(Locale.ENGLISH) : "unknown");
            jsonObject.put("os", Build.VERSION.SDK_INT + " (" + Build.VERSION.RELEASE + ")");
            // append system info
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                jsonObject.put("f_uid", currentUser.getUid());
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        final String resultInfo = jsonObject != null ? jsonObject.toString() : "";
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, TiledeskActivity.class);
                intent.putExtra("projectId", projectId);
                intent.putExtra("customerName", customerName);
                intent.putExtra("customInfo", resultInfo);
                activity.startActivity(intent);
            }
        });
    }


    public static void showWebView(final String title, final String url) {
        final Activity activity = IvySdk.getActivity();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(() -> {
            Intent intent = new Intent(activity, WebViewActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("url", url);
            activity.startActivityForResult(intent, IvySdk.RC_WEBVIEW);
        });
    }

    public static void updateAIHelpUserInfo(String meta, String tags) {
        try {
            if (!AIHelp.getInstance().isHasInitialized()) {
                Log.e(AIHelp.TAG, "AIHelp was not initialized");
                return;
            }
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(meta);
                jsonObject.put("app_version", getConfig(CONFIG_KEY_VERSION_NAME));
                jsonObject.put("device", Build.MODEL != null ? Build.MODEL.toLowerCase(Locale.ENGLISH) : "unknown");
                jsonObject.put("os", Build.VERSION.SDK_INT + " (" + Build.VERSION.RELEASE + ")");
                // append system info
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    jsonObject.put("f_uid", currentUser.getUid());
                }
            } catch (Exception e) {
            }
            AIHelp.getInstance().resetUserInfo();
            AIHelp.getInstance().updateUserInfo(jsonObject, tags);
        } catch (Exception e) {

        }
    }

    public static boolean isAIHelpInitialized() {
        return AIHelp.getInstance().isHasInitialized();
    }

    public static void showAIHelp(String entranceId, String welcomeMessage, String meta, String tags) {
        if (!AIHelp.getInstance().isHasInitialized()) {
            Log.e(AIHelp.TAG, "AIHelp was not initialized");
            return;
        }
        updateAIHelpUserInfo(meta, tags);
        AIHelp.getInstance().show(entranceId, welcomeMessage);
    }

    public static void showSingleFAQ(String faqId, int moment) {
        if (!AIHelp.getInstance().isHasInitialized()) {
            Log.e(AIHelp.TAG, "AIHelp was not initialized");
            return;
        }
        AIHelp.getInstance().showSingleFAQ(faqId, moment);
    }

    public static void saveUserAttribute(JSONObject dataJson) {
        try {
            IvySdk.saveUserAttribute(dataJson, builder.inAppMessageClickListener);
        } catch (Throwable t) {
            // ignore
        }
    }

    public static void recordEventConversion(String conversionUrl, String eventName, Bundle bundle) {
        try {
            IvySdk.recordEventConversion(conversionUrl, eventName, bundle, builder.inAppMessageClickListener);
        } catch (Throwable t) {
            // ignore
        }
    }

    static volatile AdmobRectBanner admobRectBanner = null;

    public static void closeRectBanner() {
        if (admobRectBanner != null) {
            final Activity activity = IvySdk.getActivity();
            if (activity == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    admobRectBanner.close(activity);
                    admobRectBanner = null;
                }
            });
        }
    }

    public static void showRectBanner(int x, int y, int w, int h) {
        final Activity activity = IvySdk.getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String exitBanner = IvySdk.getGridConfigString("exit_admob_banner");
                if (exitBanner != null && !"".equals(exitBanner)) {
                    try {
                        admobRectBanner = new AdmobRectBanner();
                        admobRectBanner.show(activity, exitBanner, x, y);
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }

    public static void setTargetForChild() {
        try {
            RequestConfiguration requestConfiguration = MobileAds.getRequestConfiguration()
                    .toBuilder()
                    .setTagForChildDirectedTreatment(TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE)
                    .build();
            MobileAds.setRequestConfiguration(requestConfiguration);
        } catch (Throwable t) {
            Logger.error(TAG, "setTargetForChild exception", t);
        }
    }

    public static void recordVirtualCurrency(String name, int value) {
        // save this main line
        try {
            String remaingCurrencyString = IvySdk.mmGetStringValue(IvySdk.KEY_VIRUTAL_CURRENCY, "{}");
            JSONObject o = new JSONObject(remaingCurrencyString);
            o.put(name, value);
            IvySdk.mmSetStringValue(IvySdk.KEY_VIRUTAL_CURRENCY, o.toString());
        } catch (Throwable ignored) {
        }
    }

    /**
     * 更新玩家的主线数据
     *
     * @param name  属性名
     * @param value 新值
     */
    public static void trackMainLine(String name, int value) {
        Bundle bundle = new Bundle();

        bundle.putString("label", name);
        bundle.putInt(FirebaseAnalytics.Param.VALUE, value);
        IvySdk.logIvyEvent("track_main_line", bundle);

        // save this main line
        try {
            String savedMainLine = IvySdk.mmGetStringValue(IvySdk.KEY_GAME_MAIN_LINE, "{}");
            JSONObject o = new JSONObject(savedMainLine);
            o.put(name, value);
            IvySdk.mmSetStringValue(IvySdk.KEY_GAME_MAIN_LINE, o.toString());
        } catch (Throwable ignored) {
        }
    }

    public static void trackRetentionStep(int stepId, String stepName) {
        Bundle bundle = new Bundle();
        bundle.putString("label", stepName);
        bundle.putInt("value", stepId);
        IvySdk.logEvent("track_retention_step_" + stepId, bundle);
    }

    /**
     * 记录玩家的游戏核心玩家动作
     *
     * @param name
     * @param inc
     */
    public static void recordCoreAction(String name, int inc) {
        if (name == null || "".equals(name)) {
            return;
        }
        int actionNums = IvySdk.mmGetIntValue(name, 0);
        IvySdk.mmSetIntValue(name, actionNums + inc);
    }

    public static void commitCoreAction(String name) {
        if (name == null || "".equals(name)) {
            return;
        }
        int actionNums = IvySdk.mmGetIntValue(name, 0);

        Bundle bundle = new Bundle();

        bundle.putString("label", name);
        bundle.putInt("times", IvySdk.appStartTimes);
        bundle.putInt("value", actionNums);

        IvySdk.logIvyEvent("track_core_action", bundle);

        IvySdk.mmSetIntValue(name, 0);
    }

    /**
     * 记录活动开启
     *
     * @param name
     */
    public static void trackActivityStart(String name, String catalog) {
        Bundle bundle = new Bundle();

        bundle.putString("label", name);
        if (catalog != null) {
            bundle.putString("catalog", catalog);
        }
        IvySdk.logIvyEvent("track_activity_start", bundle);
    }

    /**
     * 记录玩家的活动的进程
     *
     * @param name
     * @param step
     */
    public static void trackActivityStep(String name, int step) {
        Bundle bundle = new Bundle();

        bundle.putString("label", name);
        bundle.putInt("value", step);

        IvySdk.logIvyEvent("track_activity_step", bundle);
    }

    /**
     * 记录玩家的特定活动结束
     *
     * @param name
     */
    public static void trackActivityEnd(String name) {
        Bundle bundle = new Bundle();

        bundle.putString("label", name);
        IvySdk.logIvyEvent("track_activity_end", bundle);
    }

    /**
     * 记录通用的活动内动作
     *
     * @param name
     * @param catalog
     * @param value
     */
    public static void trackActivityEvent(String name, String catalog, float value, boolean iap) {
        Bundle bundle = new Bundle();

        bundle.putString("label", name);
        bundle.putString("catalog", catalog);
        bundle.putFloat("value", value);
        if (iap) {
            bundle.putFloat("revenue", value);
        }

        IvySdk.logIvyEvent("track_activity_event", bundle);
    }

    public static void spendVirtualCurrency(String virtualCurrencyName, String itemid, int value, int currentValue) {
        Bundle bundle = new Bundle();

        bundle.putString("label", virtualCurrencyName);
        bundle.putString("itemid", itemid);
        bundle.putInt("value", value);

        IvySdk.logIvyEvent("spend_virtual_currency", bundle);

        if (currentValue >= 0) {
            recordVirtualCurrency(virtualCurrencyName, currentValue);
        }
    }

    public static void earnVirtualCurrency(String virtualCurrencyName, String itemid, int value, int currentValue) {
        Bundle bundle = new Bundle();

        bundle.putString("label", virtualCurrencyName);
        bundle.putString("itemid", itemid);
        bundle.putInt("value", value);

        IvySdk.logIvyEvent("earn_virtual_currency", bundle);

        if (currentValue >= 0) {
            recordVirtualCurrency(virtualCurrencyName, currentValue);
        }
    }

    public static void trackScreenStart(String screenName) {
        Bundle bundle = new Bundle();

        bundle.putString("label", screenName);
        IvySdk.logIvyEvent("track_screen_start", bundle);
    }

    public static void trackScreenEnd(String screenName) {
        Bundle bundle = new Bundle();

        bundle.putString("label", screenName);
        IvySdk.logIvyEvent("track_screen_end", bundle);
    }

    public static void recordException(String message, String error) {
        try {
            FirebaseCrashlytics.getInstance().recordException(CustomThrowable.convertUnityStackTraceToAndroid(message, error));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void logException(String err) {
        try {
            FirebaseCrashlytics.getInstance().log(err);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onGMSPaid(Map<String, Object> map) {
        if (builder != null && builder.gmsPaidEventListener != null) {
            builder.gmsPaidEventListener.onGMSPaid(map);
        }
    }

    public static void onAfInitSuccess() {
        if (builder != null && builder.appsflyerConversionListener != null) {
            builder.appsflyerConversionListener.onAfInitSuccess();
        }
    }

    public static void onAfInitFailed(int i, String s) {
        if (builder != null && builder.appsflyerConversionListener != null) {
            builder.appsflyerConversionListener.onAfInitFailed(i, s);
        }
    }

    public static void onAppOpenAttribution(Map<String, String> conversionData) {
        if (builder != null && builder.appsflyerConversionListener != null) {
            builder.appsflyerConversionListener.onAppOpenAttribution(conversionData);
        }
    }

    public static void onConversionDataFail(String s) {
        if (builder != null && builder.appsflyerConversionListener != null) {
            builder.appsflyerConversionListener.onConversionDataFail(s);
        }
    }

    public static void onConversionDataSuccess(Map<String, Object> conversionData) {
        if (builder != null && builder.appsflyerConversionListener != null) {
            builder.appsflyerConversionListener.onConversionDataSuccess(conversionData);
        }
    }

    public static void onAttributionFailure(String s) {
        if (builder != null && builder.appsflyerConversionListener != null) {
            builder.appsflyerConversionListener.onAttributionFailure(s);
        }
    }

    public static boolean isXsollaSupport() {
        JSONObject gridData = GridManager.getGridData();
        if (gridData == null) {
            return false;
        }
        JSONObject obj = gridData.optJSONObject("xsolla");
        if (obj != null &&
                !TextUtils.isEmpty(obj.optString("projectId")) &&
                !TextUtils.isEmpty(obj.optString("oauthId"))) {
            if (DLog.isEnable()) {
                return true;
            } else {
                return isRussia();
            }
        }
        return false;
    }

    public static boolean isXsollaLoggedIn() {
        if (IvySdk.xsollaPurchaseImpl != null) {
            return IvySdk.xsollaPurchaseImpl.isLogIn();
        } else {
            DLog.e("xsolla impl is null, has called onCreate??");
        }
        return false;
    }

    public static void loginXsolla() {
        if (IvySdk.xsollaPurchaseImpl != null) {
            IvySdk.xsollaPurchaseImpl.login();
        } else {
            DLog.e("xsolla impl is null, has called onCreate??");
        }
    }

    public static void logoutXsolla() {
        if (IvySdk.xsollaPurchaseImpl != null) {
            IvySdk.xsollaPurchaseImpl.logout();
        } else {
            DLog.e("xsolla impl is null, has called onCreate??");
        }
    }

    public static boolean isRussia() {
        try {
            return "ru".equals(IvySdk.getActivity().getResources().getConfiguration().locale.getCountry().toLowerCase());
        } catch (Exception e) {

        }
        return false;
    }

}
