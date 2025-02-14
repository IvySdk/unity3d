package com.android.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PlayGamesAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.ivy.AIHelp.AIHelp;
import com.ivy.IvySdk;
import com.ivy.IvyUtils;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.billing.impl.IPurchaseQueryCallback;
import com.ivy.facebook.FacebookLoginListener;
import com.ivy.firestore.FirestoreAdapter;
import com.ivy.util.Logger;
import com.unity3d.player.UnityPlayer;

import net.aihelp.ui.listener.OnMessageCountArrivedCallback;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Unity {
    private static final String TAG = "Unity";
    static WeakReference<Activity> activityWeakReference;
    static Handler handler;
    public static final String TRUE = "0";
    public static final String FALSE = "1";
    public static final int AD_FULL = 1;
    public static final int AD_VIDEO = 2;
    public static final int AD_BANNER = 3;
    public static final int AD_NATIVE = 5;
    public static final int AD_GIF_ICON = 6;
    public static final int AD_REWARDED_INTERSTITIAL = 7;
    private static final String MSG_FIRESTORE_CONNECTED = "onFirestoreConnected";
    public static void onCreate(@NonNull Activity activity) {
        try {
            activityWeakReference = new WeakReference<>(activity);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    handler = new Handler(Looper.getMainLooper());
                    if (activityWeakReference.get() != null) {
                        AndroidSdk.Builder builder = new AndroidSdk.Builder();
                        builder.setSdkResultListener(new SdkResultListener() {
                            @Override
                            public void onInitialized() {
                                sendMessage("onInitialized", "");
                            }
                            @Override
                            public void onReceiveServerExtra(String data) {
                                sendMessage("onReceiveServerExtra", data);
                            }
                            @Override
                            public void onReceiveNotificationData(String data) {
                                sendMessage("onReceiveNotificationData", data);
                            }
                        });
                        builder.setPaymentListener(new PaymentSystemListener() {
                            @Override
                            public void onPaymentSuccess(int billId) {
                                Logger.debug(TAG, "Unity#onPaymentSuccess " + billId);
                                Log.e("SendMessage", "Unity#onPaymentSuccess: " + billId);
                                sendMessage("onPaymentSuccess",String.valueOf(billId));
                            }
                            @Override
                            public void onPaymentSuccess(int billId, String payload) {
                                Logger.debug(TAG, "Unity#onPaymentSuccessWithPayload " + billId + "|" + payload);
                                sendMessage("onPaymentSuccessWithPayload", billId + "|" + payload);
                            }
                            @Override
                            public void onPaymentSuccessWithOrderId(int billId, String orderId) {
//                                super.onPaymentSuccessWithOrderId(bill, orderId);
                                sendMessage("onPaymentSuccessWithOrderId", billId + "|" + orderId);
                            }
                            @Override
                            public void onPaymentSuccessWithOrderId(int billId, String payload, String orderId) {
//                                super.onPaymentSuccessWithOrderId(bill, payload, orderId);
                                sendMessage("onPaymentSuccessWithOrderIdPayload", billId + "|" + payload + "|" + orderId);
                            }
                            @Override
                            public void onPaymentSuccess(int billId, String payload, String orderInfo) {
                                Logger.debug(TAG, "Unity#onPaymentSuccessWithPayloadAndOrderInfo " + billId + "|" + payload);
                                sendMessage("onPaymentSuccessWithPayloadAndOrderInfo", billId + "|" + payload + "|" + orderInfo);
                            }
                            @Override
                            public void onPaymentFail(int billId) {
                                Logger.debug(TAG, "Unity#onPaymentFail ");
                                sendMessage("onPaymentFail", String.valueOf(billId));
                            }
                            @Override
                            public void onStoreLoaded(String skuType) {
                                sendMessage("onStoreLoaded", skuType);
                            }
                            @Override
                            public void onPaymentSystemValid() {
                                Logger.debug(TAG, "Unity#onPaymentSystemValid ");
                                sendMessage("onPaymentSystemValid", "");
                            }
                            @Override
                            public void onPaymentCanceled(int bill) {
                                sendMessage("onPaymentCanceled", String.valueOf(bill));
                            }
                            @Override
                            public void onPaymentSystemError(int code, String message) {
                                Logger.debug(TAG, "Unity#onPaymentSystemError: " + code + "|" + message);
                                sendMessage("onPaymentSystemError", code + "|" + message);
                            }
                            @Override
                            public void onReceiveBillPrices(String prices) {
                                sendMessage("onReceiveBillPrices", prices);
                            }
                        });
                        builder.setUrlListener(new UrlListener() {
                            @Override
                            public void onSuccess(int tag, String path) {
                                sendMessage("onCacheUrlResult", tag + "|0|" + path);
                            }
                            @Override
                            public void onFailure(int tag) {
                                sendMessage("onCacheUrlResult", tag + "|1");
                            }
                        });
                        builder.setUserCenterListener(new UserCenterListener() {
                            @Override
                            public void onReceiveLoginResult(boolean success) {
                                sendMessage("onReceiveLoginResult", success ? TRUE : FALSE);
                            }
                            @Override
                            public void onReceiveFriends(String friends) {
                                sendMessage("onReceiveFriends", friends);
                            }
                            @Override
                            public void onReceiveInviteResult(boolean success) {
                                sendMessage("onReceiveInviteResult", success ? TRUE : FALSE);
                            }
                            @Override
                            public void onReceiveChallengeResult(int count) {
                                sendMessage("onReceiveChallengeResult", String.valueOf(count));
                            }
                            @Override
                            public void onReceiveLikeResult(boolean success) {
                                sendMessage("onReceiveLikeResult", success ? TRUE : FALSE);
                            }
                        });
                        builder.setAdLoadedListener(new AdLoadedListener() {
                            @Override
                            public void onAdLoaded(int adType) {
                                try {
                                    int newAdType = 0;
                                    if (adType == IvyAdType.INTERSTITIAL.ordinal()) {
                                        newAdType = AD_FULL;
                                    } else if (adType == IvyAdType.REWARDED.ordinal()) {
                                        newAdType = AD_VIDEO;
                                    } else if (adType == IvyAdType.BANNER.ordinal()) {
                                        newAdType = AD_BANNER;
                                    } else if (adType == IvyAdType.NATIVE_AD.ordinal()) {
                                        newAdType = AD_NATIVE;
                                    } else if (adType == IvyAdType.REWARDED_INTERSTITIAL.ordinal()) {
                                        newAdType = AD_REWARDED_INTERSTITIAL;
                                    }
                                    Logger.debug(TAG, "Notify AdLoaded: " + newAdType);
                                    sendMessage("onAdLoaded", String.valueOf(newAdType));
                                } catch (Throwable t) {
                                    Logger.error(TAG, "adloaded not defined", t);
                                }
                            }
                        });
                        builder.setInstallRewardListener(new InstallRewardListener() {
                            @Override
                            public void onReward(String extra) {
                                Log.d(TAG, "Reward Received");
                                sendMessage("onInstallReward", extra);
                            }
                        });
                        builder.setEventOccurredListener(new EventOccurredListener() {
                            @Override
                            public void onEventOccurred(String eventName) {
                                sendMessage("onEventOccurred", eventName);
                            }
                        });
                        builder.setDeepLinkReceivedListener(new DeepLinkReceivedListener() {
                            @Override
                            public void onDeepLinkReceived(String uri) {
                                sendMessage("onDeepLinkReceived", uri);
                            }
                        });
                        builder.setDeliciousIconClickedListener(new DeliciousIconClickedListener() {
                            @Override
                            public void clicked(String bannerLocalUrl, String storeUrl) {
                                try {
                                    Logger.debug(TAG, "bannerLocalUrl: " + bannerLocalUrl);
                                    Logger.debug(TAG, "StoreURL: " + storeUrl);
                                    JSONObject data = new JSONObject();
                                    data.put("filePath", bannerLocalUrl);
                                    data.put("storeUrl", storeUrl);
                                    sendMessage("onDeliciousIconClicked", data.toString());
                                } catch (Exception ex) {
                                    Logger.error(TAG, "Delicious icon clicked exception", ex);
                                }
                            }
                        });
                        builder.setNetworkChangeListener(new NetworkChangeListener() {
                            @Override
                            public void onReceive(boolean online) {
                                try {
                                    Log.d(TAG, "Notify network status: " + online);
                                    sendMessage("onNetworkChanged", online ? "1" : "0");
                                } catch (Throwable t) {
                                    Log.w(TAG, "networkstatus not defined");
                                }
                            }
                        });
                        builder.setSavedGameListener(new SavedGameListener() {
                            @Override
                            public void onDataRead(String name, String data) {
                                sendMessage("onSavedGameRead", data);
                            }
                            @Override
                            public void onDataWritten(String name) {
                                sendMessage("onSaveGameSuccess", name);
                            }
                            @Override
                            public void onFail() {
                                sendMessage("onSaveGameFailed", "1");
                            }
                        });
                        builder.setInAppMessageClickListener(new InAppMessageClickListener() {
                            @Override
                            public void messageDisplayed(String campaignId, String dataJson) {
                                sendMessage("onInAppMessageDisplayed", dataJson);
                            }
                            @Override
                            public void messageClicked(String actionUrl) {
                                sendMessage("onInAppMessageClicked", actionUrl);
                            }
                        });
                        builder.setFilterActionListener(new IFilterActionListener() {
                            @Override
                            public void onAction(String action) {
                                sendMessage("onReceivedFilterAction", action);
                            }
                        });
                        builder.setHelpEngagementListener(data -> sendMessage("onReceiveHelpEngagementMessage", data));
                        AndroidSdk.onCreate(activityWeakReference.get(), builder);
                        AndroidSdk.registerAdEventListener(new AdEventListener() {
                            @Override
                            public void onAdShow(String tag, String platform, int type) {
                                super.onAdShow(tag, platform, type);
                                sendMessage("onAdShow", type + "|" + tag);
                            }
                            @Override
                            public void onAdClicked(String tag, String platform, int type) {
                                super.onAdClicked(tag, platform, type);
                                sendMessage("onAdClicked", type + "|" + tag);
                            }
                        });
                        AndroidSdk.setHomeAdListener(new AndroidSdk.HomeAdListener() {
                            @Override
                            public void showLoading() {
                                sendMessage("showHomeAdLoading", TRUE);
                            }
                            @Override
                            public void closeLoading() {
                                sendMessage("showHomeAdLoading", FALSE);
                            }
                        });
                    }
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    public static void cancelLocalMessage(String key) {
        AndroidSdk.cancelLocalMessage(key);
    }

    /**
     * 添加3D touch 按钮
     *
     * @param id         按钮唯一 id
     * @param order      排序 序号
     * @param shortLabel 短称
     * @param longLabel  长称
     * @param icon       图标 ；放置再assets目录下的图片文件的全名
     * @param action     行为
     */
    public static void addShortcut(String id, int order, String shortLabel, String longLabel, String icon, String action) {
        AndroidSdk.addShortcut(id, order, shortLabel, longLabel, icon, action);
    }
    /**
     * 更新3D touch 按钮
     *
     * @param id
     * @param order
     * @param shortLabel
     * @param longLabel
     * @param icon
     * @param action
     */
    public static void updateShortcut(String id, int order, String shortLabel, String longLabel, String icon, String action) {
        AndroidSdk.updateShortcut(id, order, shortLabel, longLabel, icon, action);
    }
    /**
     * 删除3D touch 按钮
     *
     * @param id
     */
    public static void deleteShortcut(String id) {
        AndroidSdk.deleteShortcut(id);
    }


    public static void cancelMessage(String key) {
        AndroidSdk.cancelMessage(key);
    }
    public static void pushMessage(String key, String title, String content, int pushTime, boolean localTimeZone, String fbIds, String uuids, String topics, int iosBadge, boolean useSound, String soundName, String extraData) {
        AndroidSdk.pushMessage(key, title, content, pushTime, localTimeZone, fbIds, uuids, topics, iosBadge, useSound, soundName, extraData);
    }
    public static void pushLocalMessage(String key, String title, String content, int pushTime, int interval, boolean useSound, String soundName, String userInfo) {
        AndroidSdk.pushLocalMessage(key, title, content, pushTime, interval, useSound, soundName, userInfo);
    }
    public static boolean pushLocalNotification(String tag, //任务标记
                                                String title, // 通知栏标题
                                                String subtitle, //通知栏内容
                                                long delay, // 延迟时间，单位秒
                                                boolean autoCancel, // 通知栏是否可关闭
                                                String action,     // 通知栏行为，可为空，默认是打开游戏主界面
                                                boolean repeat,  // 是否为重复任务
                                                boolean onNetWorkOn, // 是否需要在联网状态显示通知
                                                boolean requireCharging){ // 是否需要在充电状态显示通知
        return AndroidSdk.pushLocalNotification(tag, title, subtitle, delay, autoCancel, action, repeat, onNetWorkOn, requireCharging);
    }

    public static boolean pushLocalNotificationWithBigText(String tag,
                                                           String title,
                                                           String subtitle,
                                                           String bigText,
                                                           String smallIcon,
                                                           String largeIcon,
                                                           String action,
                                                           boolean autoCancel,
                                                           long delay,
                                                           boolean repeat,
                                                           boolean onNetWorkOn,
                                                           boolean requireCharging) {
        return AndroidSdk.pushLocalNotification(tag, title, subtitle, bigText, smallIcon, largeIcon, null, action, autoCancel, delay, repeat, onNetWorkOn, requireCharging);
    }

    public static boolean pushLocalNotificationWithBigPicture(String tag,
                                                              String title,
                                                              String subtitle,
                                                              String smallIcon,
                                                              String largeIcon,
                                                              String bigPicture,
                                                              String action,
                                                              boolean autoCancel,
                                                              long delay,
                                                              boolean repeat,
                                                              boolean onNetWorkOn,
                                                              boolean requireCharging) {
        return AndroidSdk.pushLocalNotification(tag, title, subtitle, null, smallIcon, largeIcon, bigPicture, action, autoCancel, delay, repeat, onNetWorkOn, requireCharging);
    }
    public static void cancelLocalNotification(String key){
        AndroidSdk.cancelLocalNotification(key);
    }
    public static void sendMessage(String method, String data) {
        Log.e("SendMessage", "SendMessage method: " + method + ", data: " + data);
        Log.d(TAG, "SendMessage method: " + method + ", data: " + data);
        try {
            UnityPlayer.UnitySendMessage("RiseSdkListener", method, data);
        } catch (Throwable t) {
            Log.e(TAG, "sendMessage exception", t);
        }
    }
    public static void onStart() {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    AndroidSdk.onStart();
                }
            });
        }
    }
    public static void onStop() {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    AndroidSdk.onStop();
                }
            });
        }
    }
    public static void onPause() {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    AndroidSdk.onPause();
                }
            });
        }
    }
    public static void onResume() {
        if (activityWeakReference.get() != null) {
            if (handler != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        AndroidSdk.onResume(activityWeakReference.get());
                    }
                });
            }
        }
    }
    public static void onDestroy() {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    AndroidSdk.onDestroy();
                }
            });
        }
    }
    public static void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        Log.d(TAG, "Unity#onActivityResult, requestCode: " + requestCode);
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    AndroidSdk.onActivityResult(requestCode, resultCode, data);
                }
            });
        }
    }
    public static void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    AndroidSdk.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            });
        }
    }
    public static void showSplashBanner(int pos) {
        AndroidSdk.showSplashBanner(pos);
    }
    public static void closeSplashBanner(int pos){
        AndroidSdk.closeSplashBanner();
    }
    public static void showBanner(String tag, int pos, int animate) {
        AndroidSdk.showBanner(tag, pos, animate);
    }
    public static void showBanner(String tag, int pos) {
        AndroidSdk.showBanner(tag, pos);
    }
    public static void showBanner(int pos) {
        AndroidSdk.showBanner("default", pos);
    }
    public static void showBannerNonExistent(int pos) {
        AndroidSdk.showBannerNonExistent("default", pos);
    }
    public static void closeBanner() {
        AndroidSdk.closeBanner("default");
    }
    public static void closeBanner(String tag) {
        AndroidSdk.closeBanner(tag);
    }
    public static void showRectBanner(int x, int y, int w, int h) {
        AndroidSdk.showRectBanner(x, y, w, h);
    }
    public static void closeRectBanner() {
        AndroidSdk.closeRectBanner();
    }
    public static void showPromoteAd(final String tag) {
        IvySdk.showPromoteAd(tag);
    }
    public static void showFullAd(final String tag) {
        AndroidSdk.showFullAd(tag, new AdListener() {
            @Override
            public void onAdClosed() {
                sendMessage("onFullAdClosed", tag);
            }
            @Override
            public void onAdShow() {
                sendMessage("onFullAdShown", tag);
            }
            @Override
            public void onAdClicked() {
                sendMessage("onFullAdClicked", tag);
            }
        });
    }
    public static void showRewardAd(int id) {
        showRewardAd("default", id);
    }
    public static boolean hasRewardAd() {
        return AndroidSdk.hasRewardAd("default");
    }
    public static void showRewardAd(final String tag, final int id) {
        Logger.debug(TAG, "showRewardAd: " + tag + ", " + id);
        AndroidSdk.showRewardAd(tag, new AdListener() {
            @Override
            public void onAdReward(boolean skip) {
                Logger.debug(TAG, "onAdReward, skip: " + skip);
                sendMessage("onReceiveReward", (skip ? FALSE : TRUE) + "|" + id + "|" + tag + "|" + (skip ? TRUE : FALSE));
            }
            @Override
            public void onAdLoadFails() {
                Logger.debug(TAG, "onAdLoadFails");
                sendMessage("onAdLoadFailed", "" + id);
            }
            @Override
            public void onAdLoadSuccess() {
                Logger.debug(TAG, "onAdLoadSuccess");
                sendMessage("onAdLoadSuccess", "" + id);
            }
            @Override
            public void onAdShowFails() {
                Logger.debug(TAG, "onAdShowFails, ");
                sendMessage("onReceiveReward", FALSE + "|" + id + "|" + tag + "|" + FALSE);
            }
            @Override
            public void onAdClosed() {
                Logger.debug(TAG, "onAdClosed, ");
                sendMessage("onVideoAdClosed", tag);
            }
            @Override
            public void onAdShow() {
                super.onAdShow();
                sendMessage("onVideoAdShow", tag + "｜" + id);
            }
        });
    }
    public static boolean hasBanner(String tag) {
        return AndroidSdk.hasBanner(tag);
    }
    public static boolean hasFull(String tag) {
        return AndroidSdk.hasFull(tag);
    }
    public static boolean hasRewardAd(String tag) {
        return AndroidSdk.hasRewardAd(tag);
    }
    public static void pay(int id) {
        AndroidSdk.pay(id);
    }
    public static void pay(int id, String payload) {
        AndroidSdk.pay(id, null, payload);
    }
    public static void pay(int id, String itemName, String payload) {
        AndroidSdk.pay(id, itemName, payload);
    }
    public static void changeSku(int oldId, int newId) {
        AndroidSdk.changeSku(oldId, newId, null);
    }
    public static void changeSku(int oldId, int newId, String payload) {
        AndroidSdk.changeSku(oldId, newId, payload);
    }
    public static void query(int id) {
        AndroidSdk.query(id);
    }
//  public static String getPurchaseHistory(String skuType) {
//    return AndroidSdk.getPurchaseHistory(skuType);
//  }
    /**
     * 获取消费记录;
     * Cocos平台须实现native方法 native void ph(String purchaseHistory) 监听查询结果
     * Unity平台须监听 onPurchaseHistory(String data) 获取查询结果
     *
     * @param skuType 订单类型；订阅： subs； 一次性付费： inapp
     */
    public static void getPurchaseHistory(String skuType) {
        AndroidSdk.getPurchaseHistory(skuType, new IPurchaseQueryCallback<String>() {
            @Override
            public void onResult(String data) {
                sendMessage("onPurchaseHistory", data);
            }
        });
    }
    public static void getPaymentDataAsyn(int bill) {
        AndroidSdk.querySKUDetail(bill, new OnSkuDetailsListener() {
            @Override
            public void onReceived() {
                sendMessage("onPaymentData", bill + "|" + getPaymentData(bill));
            }
        });
    }
    public static String getPaymentData(int bill) {
        String skuDetail = AndroidSdk.getSKUDetail(bill);
        return skuDetail == null ? "{}" : skuDetail;
    }
    public static boolean isXsollaSupport() {
        return AndroidSdk.isXsollaSupport();
    }
    public static boolean isXsollaLoggedIn() {
        return AndroidSdk.isXsollaLoggedIn();
    }
    public static void loginXsolla() {
        AndroidSdk.loginXsolla();
    }
    public static void logoutXsolla() {
        AndroidSdk.logoutXsolla();
    }
    public static void share(final String shareId, String url, String quote, String hashtag) {
        AndroidSdk.share(url, quote, hashtag, new ShareResultListener() {
            @Override
            public void onSuccess(String postId) {
                sendMessage("onShareSuccess", shareId + "|" + postId);
            }
            @Override
            public void onCancel() {
                sendMessage("onShareCancel", shareId);
            }
            @Override
            public void onError(String message) {
                sendMessage("onShareError", shareId + "|" + message);
            }
        });
    }
    public static void share() {
        AndroidSdk.share();
    }
    public static void share(String url) {
        AndroidSdk.share(url, null);
    }
    public static void shareMessage(String msg, boolean sysOnly) {
        AndroidSdk.share();
    }
    public static void shareVideo(String url) {
        AndroidSdk.shareVideo(url);
    }
    public static String getAfInviteId(){
        return AndroidSdk.getAfInviteId();
    }
    public static void inviteUserByAf(String currentUserId) {
        AndroidSdk.inviteUserByAf(currentUserId);
    }
    public static void rate() {
        AndroidSdk.rateUs();
    }
    public static void inAppReview() {
        IvySdk.tryStartInAppReview();
    }
    public static void rate(float star) {
        AndroidSdk.rateUs(star);
    }
    public static void moreGame() {
        AndroidSdk.moreGame();
    }
    public static void trackEvent(String category, String action, String label, int value) {
        AndroidSdk.track(category, action, label, value);
    }
    public static void trackEventToConversion(String eventName, String action, String label, int value) {
        AndroidSdk.track(eventName, action, label, value, 5);
    }
    public static void trackEventToFirebase(String eventName, String action, String label, int value) {
        AndroidSdk.track(eventName, action, label, value, 1);
    }
    public static void trackEventToFacebook(String eventName, String action, String label, int value) {
        AndroidSdk.track(eventName, action, label, value, 2);
    }
    public static void trackEventToAppsflyer(String eventName, String action, String label, int value) {
        AndroidSdk.track(eventName, action, label, value, 3);
    }
    public static void trackEventToIvy(String eventName, String action, String label, int value) {
        AndroidSdk.track(eventName, action, label, value, 4);
    }
    public static void trackScreen(String screenClass, String screenName) {
        IvySdk.trackScreen(screenClass, screenName);
    }
    public static void trackIvyEvent(String event, String data) {
        Bundle extra = new Bundle();
        if (data != null) {
            String[] splits = data.split(",");
            if (splits.length > 1) {
                for (int i = 0; i < splits.length; i += 2) {
                    try {
                        extra.putDouble(splits[i], Double.parseDouble(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }
                    try {
                        extra.putFloat(splits[i], Float.parseFloat(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }
                    try {
                        extra.putLong(splits[i], Long.parseLong(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }
                    try {
                        extra.putInt(splits[i], Integer.parseInt(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }
                    try {
                        extra.putString(splits[i], splits[i + 1]);
                    } catch (Exception ignore) {
                    }
                }
            }
        }
        IvySdk.logIvyEvent(event, extra);
    }
    public static void trackEvent(String event, String data) {
        Map<String, Object> extra = new HashMap<>();
        if (data != null) {
            String[] splits = data.split(",");
            if (splits.length > 1) {
                for (int i = 0; i < splits.length; i += 2) {
                    try {
                        extra.put(splits[i], Double.parseDouble(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }
                    try {
                        extra.put(splits[i], Float.parseFloat(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }
                    try {
                        extra.put(splits[i], Long.parseLong(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }
                    try {
                        extra.put(splits[i], Integer.parseInt(splits[i + 1]));
                        continue;
                    } catch (Exception ignore) {
                    }
                    try {
                        extra.put(splits[i], splits[i + 1]);
                    } catch (Exception ignore) {
                    }
                }
            }
        }
        AndroidSdk.track(event, extra);
    }
    public static void trackEventToConversion(String event, String data) {
        AndroidSdk.track(event, data, 5);
    }
    public static void trackEventToFirebase(String event, String data) {
        Log.e("firebase-event", "trackEventToFirebase::" + event + "::" + data);
        AndroidSdk.track(event, data, 1);
    }
    public static void trackEventToFacebook(String event, String data) {
        AndroidSdk.track(event, data, 2);
    }
    public static void trackEventToAppsflyer(String event, String data) {
        AndroidSdk.track(event, data, 3);
    }
    public static void trackEventToIvy(String event, String data) {
        AndroidSdk.track(event, data, 4);
    }
    @Deprecated
    public static void showNative(String tag, int yPercent) {
        Log.e(TAG, "showNative(tag, yPercent) deprecated");
    }
    @Deprecated
    public static boolean showNativeBanner(String tag, int xPercent, int yPercent, String configFile) {
        return AndroidSdk.showNativeBanner(tag, xPercent, yPercent, configFile);
    }
    public static boolean showNativeBanner(final String tag, final int x, final int y, final int w, final int h, final String configFile) {
        return AndroidSdk.showNativeBanner(tag, x, y, w, h, configFile);
    }
    public static boolean showNativeBanner(final String tag, final float x, final float y, final float w, final float h) {
        return AndroidSdk.showNativeBanner(tag, (int) x, (int) y, (int) w, (int) h, null);
    }
    public static boolean showNativeBanner(final String tag, final int x, final int y, final int w, final int h, final int sw, final int sh) {
        return AndroidSdk.showNativeBanner(tag, x, y, w, h, sw, sh);
    }
    public static void closeNativeBanner(String tag) {
        AndroidSdk.hideNativeBanner(tag);
    }
    public static void hideNative(String tag) {
        AndroidSdk.hideNativeAdScrollView(tag);
    }
    public static boolean hasNative(String tag) {
        return AndroidSdk.hasNativeAd(tag);
    }
    public static String getExtraData() {
        return AndroidSdk.getExtraData();
    }
    public static void onQuit() {
        AndroidSdk.onQuit();
    }
    public static void login() {
        AndroidSdk.login();
//    if (activityWeakReference.get() != null) {
//      Unity3dPlayerActivity.launchUserCenter(activityWeakReference.get(), Unity3dPlayerActivity.TYPE_LOGIN);
//    }
    }
    public static void logout() {
        AndroidSdk.logout();
    }
    public static boolean isLogin() {
        return AndroidSdk.isLogin();
    }
    public static void invite() {
//    if (activityWeakReference.get() != null) {
//      Unity3dPlayerActivity.launchUserCenter(activityWeakReference.get(), Unity3dPlayerActivity.TYPE_INVITE);
//    }
    }
    public static void challenge(String title, String message) {
//    if (activityWeakReference.get() != null) {
//      Unity3dPlayerActivity.launchUserCenter(activityWeakReference.get(), Unity3dPlayerActivity.TYPE_CHALLENGE, title, message);
//    }
    }
    public static String me() {
        return AndroidSdk.me();
    }
    public static String friends() {
        return AndroidSdk.friends();
    }
    public static void showDeliciousIconAd(final int x, final int y, final int w, final int h, final String config) {
        AndroidSdk.showDeliciousIconAd(x, y, w, h, config);
    }
    public static boolean hasDeliciousIconAd() {
        return AndroidSdk.hasDeliciousIconAd();
    }
    public static void closeDeliciousIconAd() {
        AndroidSdk.closeDeliciousIconAd();
    }
    public static void showDeliciousBannerAd(final int x, final int y, final int w, final int h, final String config) {
        AndroidSdk.showDeliciousBannerAd(x, y, w, h, config);
    }
    public static boolean hasDeliciousBannerAd() {
        return AndroidSdk.hasDeliciousBannerAd();
    }
    public static void closeDeliciousBannerAd() {
        AndroidSdk.closeDeliciousBannerAd();
    }
    public static boolean hasDeliciousVideoAd() {
        return AndroidSdk.hasDeliciousVideoAd();
    }
    public static void showDeliciousVideoAd(final String config) {
        AndroidSdk.showDeliciousVideoAd(config);
    }
    public static boolean hasDeliciousAd() {
        return AndroidSdk.hasDeliciousAd();
    }
    public static void loadFullAd(final String tag) {
        AndroidSdk.loadFullAd(tag, new AdListener() {
            @Override
            public void onAdLoadSuccess() {
                sendMessage("onFullAdLoadSuccess", tag);
            }
            @Override
            public void onAdLoadFails() {
                sendMessage("onFullAdLoadFails", tag);
            }
        });
    }
    public static boolean isOtherMusicOn() {
        return AndroidSdk.isOtherMusicOn();
    }
    public static boolean hasNotch() {
        return AndroidSdk.hasNotch();
    }
    public static int getNotchHeight() {
        return AndroidSdk.getNotchHeight();
    }
    public static boolean hasGDPR() {
        return AndroidSdk.hasGDPR();
    }
    public static void resetGDPR() {
        AndroidSdk.resetGDPR();
    }
    public static String showNativeAd(String tag) {
        return AndroidSdk.showNativeAd(tag);
    }
    public static void closeNativeAd(String tag) {
        AndroidSdk.closeNativeAd(tag);
    }
    public static void clickNativeAd(String tag) {
        AndroidSdk.clickNativeAd(tag);
    }
    public static boolean isNetworkConnected() {
        return AndroidSdk.isNetworkConnected();
    }
    /**
     * download this url and cachesh the result into sdcard
     *
     * @param url
     * @return file path in sdcard: /sdcard/0/.cache/383292918283483291
     */
    public static String cacheUrl(String url) {
        return AndroidSdk.cacheUrl(url);
    }
    public static void cacheUrl(int tag, String url) {
        AndroidSdk.cacheUrl(tag, url);
    }
    public static void openFacebook(String userId, String userName) {
        AndroidSdk.openFacebook(userId, userName);
    }
    public static String getConfig(int configKey) {
        return AndroidSdk.getConfig(configKey);
    }
    public static boolean hasApp(String packageName) {
        Activity activity = IvySdk.getActivity();
        return IvyUtils.hasApp(activity, packageName);
    }
    public static void launchApp(String packageName) {
        AndroidSdk.launchApp(packageName);
    }
    public static void getApp(String packageName) {
        AndroidSdk.openAppStore(packageName);
    }
    public static void alert(String title, String message) {
        AndroidSdk.alert(title, message);
    }
    public static void toast(String message) {
        AndroidSdk.toast(message);
    }
    public static String getConfig(String packageName, int configKey) {
        return AndroidSdk.getConfig(packageName, configKey);
    }
    public static boolean isPaymentValid() {
        return AndroidSdk.isPaymentValid();
    }
    public static String getPaymentDatas() {
        return AndroidSdk.getPrices();
    }
    public static String getPrices() {
        return AndroidSdk.getPrices();
    }
    public static void onKill() {
        AndroidSdk.onKill();
    }
    public static void setUserTag(String userTag) {
        AndroidSdk.setUserTag(userTag);
    }
    public static void support(String email, String extra) {
        AndroidSdk.support(email, extra);
    }
    public static void support(String email, String title, String extra) {
        AndroidSdk.support(email, title, extra);
    }
    public static int getRemoteConfigInt(String key) {
        return AndroidSdk.getRemoteConfigInt(key);
    }
    public static long getRemoteConfigLong(String key) {
        return AndroidSdk.getRemoteConfigLong(key);
    }
    public static double getRemoteConfigDouble(String key) {
        return AndroidSdk.getRemoteConfigDouble(key);
    }
    public static boolean getRemoteConfigBoolean(String key) {
        return AndroidSdk.getRemoteConfigBoolean(key);
    }
    public static String getRemoteConfigString(String key) {
        return AndroidSdk.getRemoteConfigString(key);
    }
    public static void setUserProperty(String key, String value) {
        AndroidSdk.setUserProperty(key, value);
    }
    public static String encodeParams(String params) {
        return AndroidSdk.encodeParams(params);
    }
    public static String decodeParams(String params) {
        return AndroidSdk.decodeParams(params);
    }
    public static void silentLoginGoogle() {
        AndroidSdk.silentLoginGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                sendMessage("onSilentLoginGoogle", TRUE);
            }
            @Override
            public void onFails() {
                sendMessage("onSilentLoginGoogleFailed", FALSE);
            }
        });
    }
    public static void loginGoogle() {
        AndroidSdk.loginGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                Logger.debug(TAG, "Google onSuccess: " + googleId);
                sendMessage("onLoginGoogleSuccess", googleId);
            }
            @Override
            public void onFails() {
                Logger.debug(TAG, "Google onFails: ");
                sendMessage("onLoginGoogleFailure", FALSE);
            }
        });
    }
    public static void logoutGoogle() {
        AndroidSdk.logoutGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                sendMessage("onLogoutGoogle", TRUE);
            }
            @Override
            public void onFails() {
                sendMessage("onLogoutGoogle", FALSE);
            }
        });
    }
    public static boolean isGoogleLogin() {
        return AndroidSdk.isGoogleLogin();
    }
    public static void updateGoogleAchievement(final String id, int step) {
        AndroidSdk.updateGoogleAchievement(id, step, new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                sendMessage("onUpdateAchievement", id + "|" + TRUE);
            }
            @Override
            public void onFails() {
                sendMessage("onUpdateAchievement", id + "|" + FALSE);
            }
        });
    }
    public static void updateGoogleLeaderBoard(final String id, long value) {
        AndroidSdk.updateGoogleLeaderBoard(id, value, new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                sendMessage("onUpdateLeaderBoard", id + "|" + TRUE);
            }
            @Override
            public void onFails() {
                sendMessage("onUpdateLeaderBoard", id + "|" + FALSE);
            }
        });
    }
    public static void showGoogleAchievements() {
        AndroidSdk.showGoogleAchievements();
    }
    public static void showGoogleLeaderBoard(String id) {
        AndroidSdk.showGoogleLeaderBoards(id);
    }
    public static void showGoogleLeaderBoards() {
        AndroidSdk.showGoogleLeaderBoards();
    }
    public static boolean isGoogleSupport() {
        return AndroidSdk.isGoogleSupport();
    }
    public static boolean isActiveUser(int day) {
        return AndroidSdk.isActiveUser(day);
    }
    public static boolean scheduleTask(int time, String activityName, String extra) {
        return AndroidSdk.scheduleTask(time, activityName, extra);
    }
    public static boolean cancelTask(String activityName, String extra) {
        return AndroidSdk.cancelTask(activityName, extra);
    }
    public static boolean hasHomeAd() {
        return AndroidSdk.hasHomeAd();
    }
    public static void showHomeAd(final int callBackId) {
        AndroidSdk.showHomeAd(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                sendMessage("onHomeAdClosed", "");
            }
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                sendMessage("onHomeAdClicked", "");
            }
            @Override
            public void onAdShowFails() {
                super.onAdShowFails();
                sendMessage("onHomeAdShowFails", "");
            }
            @Override
            public void onAdShow() {
                super.onAdShow();
                sendMessage("onHomeAdShowSuccess", "");
            }
        });
    }
    public static void setDisplayInNotch(Activity activity) {
        AndroidSdk.setDisplayInNotch(activity);
    }
//  public static boolean isSubscriptionActive(int billId) {
//    return AndroidSdk.isSubscriptionActive(billId);
//  }
    /**
     * 查询订阅是否有效
     * Cocos平台须实现native方法 native void isa(boolean status) 监听查询结果
     * Unity平台须监听 onSubscriptionState(String data) 获取查询结果， data == "1" ? 订阅有效 : 无效
     *
     * @param billId 计费点id
     */
    public static void isSubscriptionActive(int billId) {
        AndroidSdk.isSubscriptionActive(billId, new IPurchaseQueryCallback<Boolean>() {
            @Override
            public void onResult(Boolean data) {
                sendMessage("onSubscriptionState", data ? "1" : "0");
            }
        });
    }
    public static void setPayVerifyUrl(String verifyUrl) {
        AndroidSdk.setPayVerifyUrl(verifyUrl);
    }
    public static void forceQuit() {
        AndroidSdk.forceQuit();
    }
    public static final String getSdkType() {
        return AndroidSdk.getSdkType();
    }
    public static void copyText(final String str) {
        AndroidSdk.copyText(str);
    }
    public static void verifyIdCard() {
        AndroidSdk.verifyIdCard();
    }
    public static void resetIdCheck() {
        AndroidSdk.resetIdCheck();
    }
    public static int getIdCardVerifyedAge() {
        return AndroidSdk.getIdCardVerifyedAge();
    }
    public static void setIdCardVerified(int age) {
        AndroidSdk.setIdCardVerified(age);
    }
    public static long getTotalMem() {
        return AndroidSdk.getTotalMem();
    }
    public static long getFreeMem() {
        return AndroidSdk.getFreeMem();
    }
    public static long getTotalDiskSize(){
        return AndroidSdk.getTotalDiskSize();
    }
    public static long getAvailableDiskSize(){
        return AndroidSdk.getAvailableDiskSize();
    }
    public static long mmActualSize() {
        return IvySdk.mmActualSize();
    }
    public static void mmSetIntValue(String key, int value) {
        IvySdk.mmSetIntValue(key, value);
    }
    public static int mmGetIntValue(String key, int defaultValue) {
        return IvySdk.mmGetIntValue(key, defaultValue);
    }
    public static void mmSetLongValue(String key, long value) {
        IvySdk.mmSetLongValue(key, value);
    }
    public static long mmGetLongValue(String key, long defaultValue) {
        return IvySdk.mmGetLongValue(key, defaultValue);
    }
    public static void mmSetBoolValue(String key, boolean value) {
        IvySdk.mmSetBoolValue(key, value);
    }
    public static boolean mmGetBoolValue(String key, boolean defaultValue) {
        return IvySdk.mmGetBoolValue(key, defaultValue);
    }
    public static void mmSetFloatValue(String key, float value) {
        IvySdk.mmSetFloatValue(key, value);
    }
    public static float mmGetFloatValue(String key, float defaultValue) {
        return IvySdk.mmGetFloatValue(key, defaultValue);
    }
    public static void mmSetStringValue(String key, String value) {
        IvySdk.mmSetStringValue(key, value);
    }
    public static void mmSetStringValueWithExpiredSeconds(String key, String value, int seconds) {
        IvySdk.mmSetStringValueWithExpiredSeconds(key, value, seconds);
    }
    public static String mmGetStringValue(String key, String defaultValue) {
        return IvySdk.mmGetStringValue(key, defaultValue);
    }
    public static boolean mmContainsKey(String key) {
        return IvySdk.mmContainsKey(key);
    }
    public static void mmRemoveKey(String key) {
        IvySdk.mmRemoveKey(key);
    }
    public static void mmRemoveKeys(String keys) {
        IvySdk.mmRemoveKeys(keys);
    }
    public static void mmClearAll() {
        IvySdk.mmClearAll();
    }
    public static boolean isNotificationEnabled() {
        return IvySdk.isNotificationChannelEnabled(IvySdk.getActivity());
    }
    public static int getNotificationPermissionState(){
        return IvySdk.getNotificationPermissionState(IvySdk.getActivity());
    }
    public static void requestNotificationPermission() {
        IvySdk.requestNotificationPermission(IvySdk.getActivity());
    }
    public static void openNotificationSettings() {
        IvySdk.openNotificationSettings(IvySdk.getActivity());
    }
    public static String getKeyHashSha1() {
        return AndroidSdk.getKeyHash();
    }
    public static void clickUrl(String url) {
        AndroidSdk.clickUrl(url);
    }
    public static void setAdStatus(boolean enabled) {
        IvySdk.updateAdStatus(enabled);
    }
    public static boolean hasRewardedInterstitial() {
        return AndroidSdk.hasRewardedInterstitial("default");
    }
    public static void showRewardedInterstitial(final int id) {
        showRewardedInterstitial("default", id);
    }
    public static void showRewardedInterstitial(final String tag, final int id) {
        Logger.debug(TAG, "showRewardedInterstitial: " + tag + ", " + id);
        AndroidSdk.showRewardedInterstitial(tag, new AdListener() {
            @Override
            public void onAdReward(boolean skip) {
                Logger.debug(TAG, "onAdReward, skip: " + skip);
                sendMessage("onReceiveReward", (skip ? FALSE : TRUE) + "|" + id + "|" + tag + "|" + (skip ? TRUE : FALSE));
            }
            @Override
            public void onAdLoadFails() {
                Logger.debug(TAG, "onAdLoadFails");
                sendMessage("onAdLoadFailed", "" + id);
            }
            @Override
            public void onAdLoadSuccess() {
                Logger.debug(TAG, "onAdLoadSuccess");
                sendMessage("onAdLoadSuccess", "" + id);
            }
            @Override
            public void onAdShowFails() {
                Logger.debug(TAG, "onAdShowFails, ");
                sendMessage("onReceiveReward", FALSE + "|" + id + "|" + tag + "|" + FALSE);
            }
            @Override
            public void onAdClosed() {
                Logger.debug(TAG, "onAdClosed, ");
                sendMessage("onVideoAdClosed", tag);
            }
        });
    }
    public static void signOutFirestore() {
        FirestoreAdapter.getInstance().signOutFirestore();
    }
    public static void initFirestoreAfterSignIn(String provider) {
        FirestoreAdapter.getInstance().initializeAfterSignIn(provider, new DatabaseConnectListener() {
            @Override
            public void onSuccess() {
                sendMessage("onFirestoreConnected", "");
            }
            @Override
            public void onFail() {
                sendMessage("onFirestoreConnectError", "");
            }
            @Override
            public void onAccountLinkFail() {
                sendMessage("onFirestoreLinkError", "");
            }
        });
    }
    /**
     * facebook登陆状态将账号link到google上
     */
    public static void initializeAndLinkGoogleAfterSignInFacebook() {
        AndroidSdk.loginGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                FirestoreAdapter.getInstance().initializeAndLinkGoogleAfterSignInFacebook(new DatabaseConnectListener() {
                    @Override
                    public void onSuccess() {
                        String me = AndroidSdk.me();
                        sendMessage("onFirestoreConnected", me);
                    }
                    @Override
                    public void onFail() {
                        sendMessage("onFirestoreConnectError", "");
                    }
                    @Override
                    public void onAccountLinkFail() {
                        sendMessage("onFirestoreLinkError", "");
                    }
                });
            }
            @Override
            public void onFails() {
                sendMessage("onFirestoreConnectError", "login_google_error");
            }
        });
    }
    /**
     * Firebase google登陆  关联
     */
    public static void initializeAndLinkGoogleAfterSignIn() {
        AndroidSdk.loginGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                FirestoreAdapter.getInstance().initializeAndLinkGoogleAfterSignIn(new DatabaseConnectListener() {
                    @Override
                    public void onSuccess() {
                        String me = AndroidSdk.me();
                        sendMessage("onFirestoreConnected", me);
                    }
                    @Override
                    public void onFail() {
                        sendMessage("onFirestoreConnectError", "");
                    }
                    @Override
                    public void onAccountLinkFail() {
                        sendMessage("onFirestoreLinkError", "");
                    }
                });
            }
            @Override
            public void onFails() {
                sendMessage("onFirestoreConnectError", "login_google_error");
            }
        });
    }
    /**
     * Firebase  Facebook登陆 关联
     */
    public static void initializeAndLinkFacebookAfterSignIn() {
        AndroidSdk.loginFacebook(new FacebookLoginListener() {
            @Override
            public void onReceiveLoginResult(boolean success) {
                if (success) {
                    FirestoreAdapter.getInstance().initializeAndLinkFacebookAfterSignIn(new DatabaseConnectListener() {
                        @Override
                        public void onSuccess() {
                            String me = AndroidSdk.me();
                            sendMessage("onFirestoreConnected", me);
                        }
                        @Override
                        public void onFail() {
                            sendMessage("onFirestoreConnectError", "");
                        }
                        @Override
                        public void onAccountLinkFail() {
                            sendMessage("onFirestoreLinkError", "");
                        }
                    });
                } else {
                    sendMessage("onFirestoreConnectError", "login_facebook_error");
                }
            }
            @Override
            public void onReceiveFriends(String friends) {
            }
        });
    }
    public static void readFirestore(String collection, String documentId) {
        FirestoreAdapter.getInstance().read(collection, documentId, new DatabaseListener() {
            @Override
            public void onData(String collection, String data) {
                sendMessage("onFirestoreReadData", collection + "|" + documentId + "|" + data);
            }
            @Override
            public void onSuccess(String collection) {
                // not use
            }
            @Override
            public void onFail(String collection) {
                sendMessage("onFirestoreReadFail", collection + "|" + documentId);
            }
        });
    }
    public static void readFirestore(String collection) {
        FirestoreAdapter.getInstance().read(collection, new DatabaseListener() {
            @Override
            public void onData(String collection, String data) {
                sendMessage("onFirestoreReadData", collection + "|" + data);
            }
            @Override
            public void onSuccess(String collection) {
                // not use
            }
            @Override
            public void onFail(String collection) {
                sendMessage("onFirestoreReadFail", collection);
            }
        });
    }
    public static void mergeFirestore(String collection, String jsonData) {
        try {
            if (jsonData == null || "".equals(jsonData)) {
                Logger.warning(TAG, "Empty " + jsonData);
                return;
            }
            FirestoreAdapter.getInstance().merge(collection, jsonData, new DatabaseListener() {
                @Override
                public void onData(String collection, String data) {
                    // not use
                }
                @Override
                public void onSuccess(String collection) {
                    sendMessage("onFirestoreMergeSuccess", collection);
                }
                @Override
                public void onFail(String collection) {
                    sendMessage("onFirestoreMergeFail", collection);
                }
            });
        } catch (Throwable t) {
            Logger.error(TAG, "mergeFirestore exception " + jsonData, t);
        }
    }
    public static void setFirestore (String collection, String jsonData) {
        try {
            if (jsonData == null || "".equals(jsonData)) {
                Logger.warning(TAG, "Empty " + jsonData);
                return;
            }
            FirestoreAdapter.getInstance().set(collection, jsonData, new DatabaseListener() {
                @Override
                public void onData(String collection, String data) {
                    // not use
                    //  sendMessage("onFirestoreSetSuccessData", collection);
                }
                @Override
                public void onSuccess(String collection) {
                    sendMessage("onFirestoreSetSuccess", collection);
                }
                @Override
                public void onFail(String collection) {
                    sendMessage("onFirestoreSetData", collection);
                }
            });
        } catch (Throwable t) {
            Logger.error(TAG, "setFirestore exception: " + jsonData, t);
        }
    }
    public static void updateFirestore(String collection, String transactionId, String jsonData) {
        try {
            if (jsonData == null || "".equals(jsonData)) {
                Logger.warning(TAG, "Empty " + jsonData);
                return;
            }
            String id = "";
            FirestoreAdapter.getInstance().update(collection, jsonData, new DatabaseListener() {
                @Override
                public void onData(String collection, String data) {
                    // not use
                }
                @Override
                public void onSuccess(String collection) {
                    sendMessage("onFirestoreUpdateSuccess", collection + "|" + transactionId);
                }
                @Override
                public void onFail(String collection) {
                    sendMessage("onFirestoreUpdateFail", collection + "|" + transactionId);
                }
            });
        } catch (Throwable t) {
            Logger.error(TAG, "updateFirestore exception: " + jsonData, t);
        }
    }
    public static void deleteFirestore(String collection) {
        FirestoreAdapter.getInstance().delete(collection, new DatabaseListener() {
            @Override
            public void onData(String collection, String data) {
                // not use
            }
            @Override
            public void onSuccess(String collection) {
                sendMessage("onFirestoreDeleteSuccess", collection);
            }
            @Override
            public void onFail(String collection) {
                sendMessage("onFirestoreDeleteFail", collection);
            }
        });
    }
    public static void snapshotFirestore(String collection) {
        FirestoreAdapter.getInstance().snapshot(collection, new DatabaseChangedListener() {
            @Override
            public void onData(String collection, String jsonData) {
                sendMessage("onFirestoreSnapshot", collection + "|" + jsonData);
            }
        });
    }
    public static void snapshotFirestore(String collection, String documentId) {
        FirestoreAdapter.getInstance().snapshot(collection, documentId, new DatabaseChangedListener() {
            @Override
            public void onData(String collection, String jsonData) {
                sendMessage("onFirestoreSnapshot", collection + "|" + documentId + "|" + jsonData);
            }
        });
    }
    public static void queryFirestore(String collection) {
        FirestoreAdapter.getInstance().query(collection, new DatabaseListener() {
            @Override
            public void onData(String collection, String data) {
                sendMessage("onFirestoreQueryData", collection + "|" + data);
            }
            @Override
            public void onSuccess(String collection) {
                sendMessage("onFirestoreQuerySuccess", collection);
            }
            @Override
            public void onFail(String collection) {
                sendMessage("onFirestoreQueryFail", collection);
            }
        });
    }
    public static void playerFinder() {
        AndroidSdk.playerFinder();
    }
    public static void cloudfunction(String name, String jsonString) {
        Logger.debug(TAG, ">>> " + name + ", " + jsonString);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
        } catch (Exception ex) {
            Logger.error(TAG, "Error parse to JSON, " + jsonString);
        }
        IvySdk.executeCloudFunction(name, jsonObject, new OnCloudFunctionResult() {
            @Override
            public void onResult(String data) {
                sendMessage("onCloudFunctionResult", name + "|" + data);
            }
            @Override
            public void onFail(String errorMessage) {
                sendMessage("onCloudFunctionFailed", name + "|" + errorMessage);
            }
        });
    }
    public static void cloudfunction(String name) {
        Logger.debug(TAG, ">>> " + name);
        IvySdk.executeCloudFunction(name, null, new OnCloudFunctionResult() {
            @Override
            public void onResult(String data) {
                Logger.debug(TAG, "OnCloudFunctionResult >>> " + data);
                sendMessage("onCloudFunctionResult", name + "|" + data);
            }
            @Override
            public void onFail(String errorMessage) {
                Logger.debug(TAG, "OnCloudFunctionFailed >>> " + errorMessage);
                sendMessage("onCloudFunctionFailed", name + "|" + errorMessage);
            }
        });
    }
    public static void helpEngagement(String customerName, String systemInfo) {
        try {
            AndroidSdk.helpshift(customerName, systemInfo);
        } catch (Throwable t) {
            // ignore
        }
    }
    public static void helpshift(String customerName, String systemInfo) {
        try {
            AndroidSdk.helpshift(customerName, systemInfo);
        } catch (Throwable t) {
            // ignore
        }
    }
    public static void checkHelpEngagement(final String customerName) {
        try {
            AndroidSdk.checkHelpEngagement(customerName);
        } catch (Throwable t) {
            // ignore
        }
    }
    public static void setHelpEngagementMessageRead(final String customerName) {
        try {
            AndroidSdk.setHelpEngagementMessageRead(customerName);
        } catch (Throwable t) {
            // ignore
        }
    }
    /**
     * @param entranceId     自定义入口id
     * @param meta           JsonObject格式，用户数据
     * @param tags           用户标签；在具体为用户配置标签时，你应该确保已经提前在 AIHelp 后台配置好了对应的标签内容
     * @param welcomeMessage 自定义欢迎语
     */
    public static void showAIHelp(String entranceId, String meta, String tags, String welcomeMessage) {
        AndroidSdk.showAIHelp(entranceId, welcomeMessage, meta, tags);
    }
    public static void showAIHelp(String entranceId, String meta, String tags) {
        AndroidSdk.showAIHelp(entranceId, null, meta, tags);
    }
    public static void showAIHelp(String entranceId, String meta) {
        AndroidSdk.showAIHelp(entranceId, null, meta, null);
    }
    /**
     * 打开指定 faq 页面
     *
     * @param faqId
     * @param moment FAQ 页面展示联系客服按钮的时机: 0 不显示；1 点踩后显示；2 只在回复页显示； 默认一直显示；
     */
    public static void showSingleFAQ(String faqId, int moment) {
        AndroidSdk.showSingleFAQ(faqId, moment);
    }
    public static boolean isAIHelpInitialized() {
        return AndroidSdk.isAIHelpInitialized();
    }
    /**
     * 获取未读消息数,每5分钟轮询一次
     *
     * @param onlyOnce 是否只获取一次
     */
    public static void loadAIHelpUnreadMessageCount(boolean onlyOnce) {
        OnMessageCountArrivedCallback onMessageCountArrivedCallback = new OnMessageCountArrivedCallback() {
            @Override
            public void onMessageCountArrived(int msgCount) {
                sendMessage("unreadMessageCount", String.valueOf(msgCount));
                if (onlyOnce) {
                    AIHelp.getInstance().stopUnreadMessageCountPolling();
                }
            }
        };
        AIHelp.getInstance().loadUnreadMessageCount(onMessageCountArrivedCallback);
    }
    //停止轮询读取未读消息数
    public static void stopLoadAIHelpUnreadMessageCount() {
        AIHelp.getInstance().stopUnreadMessageCountPolling();
    }
    public static void closeAIHelp() {
        AIHelp.getInstance().close();
    }
    @Deprecated
    public static void appFeedback(String sectionUrl, String systemInfo) {
        String firebaseUserId = getFirebaseUserId();
        if (firebaseUserId == null) {
            firebaseUserId = IvySdk.getUUID();
        }
        AndroidSdk.helpshift(firebaseUserId, systemInfo);
    }
    @Deprecated
    public static void appFeedback(String sectionUrl, String ticketUrl, String userSurveyUrl, String systemInfo) {
        String firebaseUserId = getFirebaseUserId();
        if (firebaseUserId == null) {
            firebaseUserId = IvySdk.getUUID();
        }
        AndroidSdk.helpshift(firebaseUserId, systemInfo);
    }
    public static String getPushToken() {
        return IvySdk.getPushToken();
    }
    public static String getFirebaseUserId() {
        return AndroidSdk.getFirebaseUserId();
    }
    public static void logoutFacebook() {
        AndroidSdk.logoutFacebook();
    }
    public static void logError(String message) {
        IvySdk.logError(message);
    }
    public static void showProgressBar() {
        IvySdk.showProgressBar(IvySdk.getActivity());
    }
    public static void hideProgressBar() {
        IvySdk.hideProgressBar(IvySdk.getActivity());
    }
    public static void displayUrl(String title, String url) {
        AndroidSdk.showWebView(title, url);
    }
    public static void triggerInAppMessage(String eventName) {
        IvySdk.triggerInAppMessage(eventName);
    }
    public static void suppressInAppMessage(boolean suppress) {
        IvySdk.supressInAppMessage(suppress);
    }
    public static void inAppMessageClicked(String campaignId) {
        IvySdk.inAppMessageClicked(campaignId);
    }
    @Deprecated
    public static void subscribeFCMTopic(String saveKey, String newTopic) {
    }

    public static void sendChat(@NonNull String database, @NonNull String path, @NonNull String data) {
        try {
            Logger.debug(TAG, "Send Chat: " + data);
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(data);
            if (jsonObject != null) {
                FirebaseDatabase.getInstance(database).getReference(path).push().setValue(jsonObject);
            }
        } catch (Throwable t) {
            Logger.error(TAG, "write socket message exception", t);
        }
    }
    @Deprecated
    public static void listen(@NonNull String database, @NonNull String path) {
        listenFirebaseDatabase(database, path);
    }

    public static void deleteFirebaseDatabasePath(@NonNull String database, @NonNull String path) {
        try {
            FirebaseDatabase.getInstance(database).getReference(path).removeValue();
        } catch (Throwable t) {
            Logger.error(TAG, "deleteFirebaseDatabasePath exception", t);
        }
    }

    private static final Map<String, ChildEventListener> childEventListenerMap = new HashMap<>();

    public static void unlistenFirebaseDatabase(@NonNull String database, @NonNull String path) {
        try {
            Logger.debug(TAG, "unlistenFirebaseDatabase " + path);
            if (childEventListenerMap.containsKey(path)) {
                ChildEventListener listener = childEventListenerMap.get(path);
                FirebaseDatabase.getInstance(database).getReference(path).removeEventListener(listener);
            }
        } catch (Throwable ignored) {
        }
    }

    public static void listenFirebaseDatabase(@NonNull String database, @NonNull String path) {
        try {
            Logger.debug(TAG, "listenFirebaseDatabase " + path);
            ChildEventListener childEventListener = null;
            if (!childEventListenerMap.containsKey(path)) {
                childEventListener = new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Object o = snapshot.getValue();
                        if (o instanceof Map) {
                            try {
                                String id = snapshot.getKey();
                                JSONObject resultObject = new JSONObject((Map<String, Object>) o);
                                resultObject.put("_id", id);
                                resultObject.put("path", path);
                                sendMessage("onChatMessage", resultObject.toString());
                            } catch (Throwable e) {
                                Logger.error(TAG, "OnChatMessage exception", e);
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Logger.debug(TAG, "onChildChanged");
                        Object o = snapshot.getValue();
                        if (o instanceof Map) {
                            try {
                                String id = snapshot.getKey();
                                JSONObject resultObject = new JSONObject((Map<String, Object>) o);
                                resultObject.put("_id", id);
                                resultObject.put("path", path);
                                resultObject.put("_previous", previousChildName);
                                sendMessage("onChatMessageChanged", resultObject.toString());
                            } catch (Throwable e) {
                                Logger.error(TAG, "onChatMessageChanged exception", e);
                            }
                        }
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                        Logger.debug(TAG, "onChildRemoved");
                        Object o = snapshot.getValue();
                        if (o instanceof Map) {
                            try {
                                String id = snapshot.getKey();
                                JSONObject resultObject = new JSONObject((Map<String, Object>) o);
                                resultObject.put("_id", id);
                                resultObject.put("path", path);
                                sendMessage("onChatMessageRemoved", resultObject.toString());
                            } catch (Throwable e) {
                                Logger.error(TAG, "onChatMessageChanged exception", e);
                            }
                        }
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Logger.debug(TAG, "onChildMoved");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Logger.debug(TAG, "onCancelled>>> " + error);
                    }
                };
                childEventListenerMap.put(path, childEventListener);
            } else {
                childEventListener = childEventListenerMap.get(path);
            }
            if (childEventListener != null) {
                FirebaseDatabase.getInstance(database).getReference(path).addChildEventListener(childEventListener);
            }
        } catch (Throwable t) {
            Logger.error(TAG, "listenFirebaseDatabase exception", t);
        }
    }

    public static boolean isAnonymous() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return true;
        }
        return user.isAnonymous();
    }

    /**
     * 检查当前账号是否链接指定提供商
     */
    public static boolean isProviderLinked(@NonNull String providerId) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return false;
        }
        List<? extends UserInfo> userInfoList = user.getProviderData();
        for (UserInfo info : userInfoList) {
            if (providerId.equals(info.getProviderId())) {
                return true;
            }
        }
        return false;
    }

    public static String getProviderLinkedDisplayName(@NonNull String providerId) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return "";
        }
        List<? extends UserInfo> userInfoList = user.getProviderData();
        for (UserInfo info : userInfoList) {
            if (providerId.equals(info.getProviderId())) {
                if (EmailAuthProvider.PROVIDER_ID.equals(providerId)) {
                    String email = info.getEmail();
                    if (email != null && !"".equals(email)) {
                        return email;
                    }
                    return "";
                }
                String displayName = info.getDisplayName();
                if (displayName != null && !"".equals(displayName)) {
                    return displayName;
                }
                return "";
            }
        }
        return "";
    }

    /**
     * 更新玩家最后登录方式。检查当前账号，以Play Games, Email, Facebook的方式以此更新玩家最后以此登入方式。
     */
    private static void updateLastSignedProvider(@Nullable String email, @Nullable String password) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            return;
        }
        try {
            List<? extends UserInfo> userInfoList = user.getProviderData();
            List<String> validProviderIds = new ArrayList<>();
            for (UserInfo info : userInfoList) {
                validProviderIds.add(info.getProviderId());
            }
            if (validProviderIds.contains(PlayGamesAuthProvider.PROVIDER_ID)) {
                JSONObject o = new JSONObject();
                o.put("provider", PlayGamesAuthProvider.PROVIDER_ID);
                IvySdk.mmSetStringValue(IvySdk.KEY_LAST_SIGNIN_PROVIDER, o.toString());
                return;
            }
            if (validProviderIds.contains(EmailAuthProvider.PROVIDER_ID)) {
                if (email != null && password != null) {
                    JSONObject o = new JSONObject();
                    o.put("provider", EmailAuthProvider.PROVIDER_ID);
                    o.put("email", email);
                    o.put("password", password);
                    IvySdk.mmSetStringValue(IvySdk.KEY_LAST_SIGNIN_PROVIDER, o.toString());
                    return;
                }
            }
            if (validProviderIds.contains(FacebookAuthProvider.PROVIDER_ID)) {
                JSONObject o = new JSONObject();
                o.put("provider", FacebookAuthProvider.PROVIDER_ID);
                IvySdk.mmSetStringValue(IvySdk.KEY_LAST_SIGNIN_PROVIDER, o.toString());
            }
        } catch (Throwable t) {
            Logger.error(TAG, "updateLastSignedProvider exception", t);
        }
    }

    private static OnAccountSignedInListener onAccountSignedInListener = new OnAccountSignedInListener() {
        @Override
        public void onMessage(String method, String data) {

        }
    };

    /**
     * TODO: 玩家如果没有登入Play Games账号，将自动获取Facebook, Email的token用户自动登录。
     * <p>
     * 在绑定的账号状态丢失时，需要主动掉起由玩家处理
     * <p>
     * 游戏自动登录功能。
     * 检查当前的登录账号，
     * 1. 如果当前用户没有登入用户（一般是新安装)，尝试以PlayGames静默登录（静默登录失败),将匿名登录
     * 2. 如果当前用户已经是登入状态，直接使用当前账号回调
     */
    public static void signIn() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser1 = auth.getCurrentUser();
                    if (currentUser1 != null) {
                        sendMessage(MSG_FIRESTORE_CONNECTED, currentUser1.getUid());
                        IvySdk.setUserID(currentUser1.getUid());
                        IvySdk.onAccountSignedIn(onAccountSignedInListener);
                    }
                } else {
                    Exception e = task.getException();
                    if (e != null) {
                        FirebaseAuthError authError = FirebaseAuthError.fromException(e);
                        sendMessage("onFirestoreConnectError", authError.name() + "|" + authError.getDescription());
                    }
                }
            });
            return;
        }
        // 查询最后一次登录的信息，如果没有记录启动Play games的，启动系统的静默登录
        JSONObject lastSignInProvider = IvySdk.mmGetJsonValue(IvySdk.KEY_LAST_SIGNIN_PROVIDER);
        if (lastSignInProvider == null) {
            IvySdk.slientLoginGoogle(new GoogleListener() {
                @Override
                public void onSuccess(String googleId, String googleEmail) {
                    FirestoreAdapter.getInstance().initializeAfterSignInPlayGames(new DatabaseConnectListener2() {
                        @Override
                        public void onSuccess() {
                            String uid = auth.getUid();
                            if (uid != null) {
                                sendMessage(MSG_FIRESTORE_CONNECTED, uid);
                                IvySdk.onAccountSignedIn(onAccountSignedInListener);
                                updateLastSignedProvider(null, null);
                            } else {
                                signInAnonymously();
                            }
                        }

                        @Override
                        public void onFail(String errorCode, String errorMessage) {
                            signInAnonymously();
                        }
                    });
                }

                @Override
                public void onFails() {
                    signInAnonymously();
                }
            });
            return;
        }
        String provider = lastSignInProvider.optString("provider");
        Logger.debug(TAG, "Use sign in provider " + provider);
        switch (provider) {
            case PlayGamesAuthProvider.PROVIDER_ID:
                signInWithPlayGames();
                break;
            case FacebookAuthProvider.PROVIDER_ID:
                signInWithFacebook();
                break;
            case EmailAuthProvider.PROVIDER_ID:
                String email = lastSignInProvider.optString("email");
                String password = lastSignInProvider.optString("password");
                if (!"".equals(email) && !"".equals(password)) {
                    signInWithEmailAndPassword(email, password);
                }
                break;
        }
    }

    /**
     * 用户主动登入Google Play Games, 不能fallback到静默，
     */
    public static void signInWithPlayGames() {
        Logger.debug(TAG, "signInWithPlayGames");
        IvySdk.slientLoginGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                FirestoreAdapter.getInstance().initializeAfterSignInPlayGames(new DatabaseConnectListener2() {
                    @Override
                    public void onSuccess() {
                        String uid = FirebaseAuth.getInstance().getUid();
                        if (uid != null) {
                            sendMessage(MSG_FIRESTORE_CONNECTED, uid);
                            IvySdk.onAccountSignedIn(onAccountSignedInListener);
                            IvySdk.setUserID(uid);
                            updateLastSignedProvider(null, null);
                        }
                    }

                    @Override
                    public void onFail(String errorCode, String errorMessage) {
                        sendMessage("onFirestoreConnectError", errorCode + "|" + errorMessage);
                    }
                });
            }

            @Override
            public void onFails() {
                sendMessage("onFirestoreConnectError", "ERROR_SIGNIN_GOOGLE" + "|" + "Sign In PlayGames failed");
            }
        });
    }

    public static void invitePlayers(String tagStr) {
        Logger.debug(TAG, "invitePlayers");
        String url = IvySdk.getGridConfigString("share_link_url", "");
        if (url.isEmpty()) {
            return;
        }
        url = url.replaceAll("#tag#", IvySdk.getUUID());
        Logger.debug(TAG, "Invite players url: " + url);
        try {
            Activity activity = IvySdk.getActivity();
            if (activity == null) {
                return;
            }
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, tagStr);
            shareIntent.putExtra(Intent.EXTRA_TEXT, url);
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            activity.startActivity(Intent.createChooser(shareIntent, "share"));
        } catch (Throwable t) {
            Logger.error(TAG, "fallbackToSystemShare exception", t);
        }
    }

    /**
     * 用户主动登录Facebook
     */
    public static void signInWithFacebook() {
        Logger.debug(TAG, "signInWithFacebook");
        AndroidSdk.loginFacebook(new FacebookLoginListener() {
            @Override
            public void onReceiveLoginResult(boolean success) {
                if (success) {
                    FirestoreAdapter.getInstance().initializeAfterSignInFacebook2(new DatabaseConnectListener2() {
                        @Override
                        public void onSuccess() {
                            String uid = FirebaseAuth.getInstance().getUid();
                            if (uid != null) {
                                sendMessage(MSG_FIRESTORE_CONNECTED, uid);
                                IvySdk.setUserID(uid);
                                IvySdk.onAccountSignedIn(onAccountSignedInListener);
                                updateLastSignedProvider(null, null);
                            } else {
                                sendMessage("onFirestoreConnectError", FirebaseAuthError.ERROR_UNKNOWN.name() + "|" + FirebaseAuthError.ERROR_UNKNOWN.getDescription());
                            }
                        }

                        @Override
                        public void onFail(String errorCode, String errorMessage) {
                            sendMessage("onFirestoreConnectError", errorCode + "|" + errorMessage);
                        }
                    });
                } else {
                    sendMessage("onFirestoreConnectError", "ERROR_SIGNIN_FACEBOOK" + "|" + "Sign In Facebook failed");
                }
            }

            @Override
            public void onReceiveFriends(String friends) {
            }
        });
    }

    /**
     * 非静默的Email/Password登录。
     *
     * @param email
     * @param password
     */
    public static void signInWithEmailAndPassword(String email, String password) {
        Logger.debug(TAG, "signInWithEmailAndPassword : ");
        FirestoreAdapter.getInstance().signInWithEmailAndPassword(email, password, new DatabaseConnectListener2() {
            @Override
            public void onSuccess() {
                Logger.debug(TAG, "signInWithEmailAndPassword success");
                String uid = FirebaseAuth.getInstance().getUid();
                IvySdk.setUserID(uid);
                sendMessage(MSG_FIRESTORE_CONNECTED, uid);
                IvySdk.onAccountSignedIn(onAccountSignedInListener);
                updateLastSignedProvider(email, password);
                // save the email and password for later using
                IvySdk.mmSetStringValue("__saved_email", email);
                IvySdk.mmSetStringValue("__saved_password", password);
            }

            @Override
            public void onFail(String errorCode, String errorMessage) {
                Logger.debug(TAG, "signInWithEmailAndPassword exception" + errorCode + ", " + errorMessage);
                sendMessage("onFirestoreConnectError", errorCode + "|" + errorMessage);
            }
        });
    }

    private static void signInAnonymously() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInAnonymously().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String uid = auth.getUid();
                if (uid != null) {
                    sendMessage(MSG_FIRESTORE_CONNECTED, uid);
                    IvySdk.setUserID(uid);
                    IvySdk.onAccountSignedIn(onAccountSignedInListener);
                } else {
                    sendMessage("onFirestoreConnectError", "");
                }
            }
        });
    }

    /**
     * 系统级的账号无法解绑playgames. 当前仅有一个ID无法解绑
     */
    public static boolean canUnlink(@NonNull String providerId) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            return false;
        }
        if (PlayGamesAuthProvider.PROVIDER_ID.equals(providerId)) {
            return false;
        }
        boolean contains = false;
        List<? extends UserInfo> userInfoList = currentUser.getProviderData();
        int providerNum = 0;
        for (UserInfo info : userInfoList) {
            if (info != null) {
                String p = info.getProviderId();
                if (providerId.equals(p)) {
                    contains = true;
                }
                if (PlayGamesAuthProvider.PROVIDER_ID.equals(p) || FacebookAuthProvider.PROVIDER_ID.equals(p) || EmailAuthProvider.PROVIDER_ID.equals(p)) {
                    providerNum++;
                }
            }
        }
        return contains && providerNum >= 2;
    }

    public static void setSignInProvider(@NonNull String providerId, @NonNull String email, @NonNull String password) {
        try {
            JSONObject o = new JSONObject();
            o.put("provider", providerId);
            if (EmailAuthProvider.PROVIDER_ID.equals(providerId)) {
                o.put("email", email);
                o.put("password", password);
            }
            IvySdk.mmSetStringValue(IvySdk.KEY_LAST_SIGNIN_PROVIDER, o.toString());
        } catch (Throwable t) {
            Logger.error(TAG, "setSignInProvider exception", t);
        }
    }

    public static void unlinkProvider(@NonNull String providerId) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            currentUser.unlink(providerId).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sendMessage("onFirestoreUnlinked", providerId);
                        updateLastSignedProvider(null, null);
                    } else {
                        sendMessage("onFirestoreUnlinkedError", providerId);
                    }
                }
            });
        }
    }

    public static void updatePassword(@NonNull String password) {
        FirestoreAdapter.getInstance().updatePassword(password, new OnPasswordChangedListener() {
            @Override
            public void onSuccess() {
                String uid = FirebaseAuth.getInstance().getUid();
                sendMessage("onPasswordChangedSuccess", uid);
            }

            @Override
            public void onError(@NonNull String errorCode, @NonNull String errorMessage) {
                sendMessage("onPasswordChangedError", errorCode + "|" + errorMessage);
            }
        });
    }

    public static void signOut() {
        FirestoreAdapter.getInstance().signOutFirestore();
    }
    public static void preload() {
        IvySdk.preloadAds();
    }
    public static void setTargetForChild() {
        AndroidSdk.setTargetForChild();
    }
    public static long getAppInstallTimestamp(){
        return AndroidSdk.getAppInstallTimestamp();
    }
    @Deprecated
    public static void writeSavedGame(String name, String data) {
    }
    @Deprecated
    public static void showSavedGamesUI() {
    }
    @Deprecated
    public static void checkUpdate() {
    }
    @Deprecated
    public static boolean hasAppOpenAd() {
        return false;
    }
    @Deprecated
    public static void showAppOpenAd(final String tag, final int id) {
    }
    @Deprecated
    public static void like() {
    }
    public static void joinQQGroup(String uid, String ukey) {
    }
    public static String getLocation() {
        return "";
    }
    public static boolean isAdSystemAvailable() {
        return true;
    }
    public void setPlayer(String data){
        AndroidSdk.setPlayer(data);
    }
    @Keep
    public static void saveUserAttribute(String dataJsonString) {
        JSONObject dataJson = null;
        try {
            dataJson = new JSONObject(dataJsonString);
        } catch (Throwable t) {
            // ignore
        }
        try {
            AndroidSdk.saveUserAttribute(dataJson);
        } catch (Throwable t) {
            // ignore
        }
    }
    @Keep
    public static void trackMainLine(String name, int value) {
        AndroidSdk.trackMainLine(name, value);
    }
    @Keep
    public static void trackRetentionStep(int stepId, String stepName) {
        AndroidSdk.trackRetentionStep(stepId, stepName);
    }
    @Keep
    public static void recordCoreAction(String name, int inc) {
        AndroidSdk.recordCoreAction(name, inc);
    }
    @Keep
    public static void commitCoreAction(String name) {
        AndroidSdk.commitCoreAction(name);
    }
    /**
     * 记录玩家当前的虚拟货币数量，在虚拟货币发生变化的时候调用。
     *
     * @param name  虚拟货币名
     * @param value 存留数量
     */
    @Keep
    public static void recordVirtualCurrency(String name, int value) {
        AndroidSdk.recordVirtualCurrency(name, value);
    }
    @Keep
    public static void trackActivityStart(String name) {
        AndroidSdk.trackActivityStart(name, null);
    }
    @Keep
    public static void trackActivityStart(String name, String catalog) {
        AndroidSdk.trackActivityStart(name, catalog);
    }
    @Keep
    public static void trackActivityStep(String name, int step) {
        AndroidSdk.trackActivityStep(name, step);
    }
    @Keep
    public static void trackActivityEnd(String name) {
        AndroidSdk.trackActivityEnd(name);
    }
    @Keep
    public static void trackActivityEvent(String name, String catalog, float value) {
        AndroidSdk.trackActivityEvent(name, catalog, value, false);
    }
    @Keep
    public static void trackActivityEvent(String name, String catalog, float value, boolean iap) {
        AndroidSdk.trackActivityEvent(name, catalog, value, iap);
    }
    @Keep
    public static void spendVirtualCurrency(String virtualCurrencyName, String itemid, int value) {
        AndroidSdk.spendVirtualCurrency(virtualCurrencyName, itemid, value, 0);
    }
    @Keep
    public static void spendVirtualCurrency(String virtualCurrencyName, String itemid, int value, int currentValue) {
        AndroidSdk.spendVirtualCurrency(virtualCurrencyName, itemid, value, currentValue);
    }
    @Keep
    public static void earnVirtualCurrency(String virtualCurrencyName, String itemid, int value) {
        AndroidSdk.earnVirtualCurrency(virtualCurrencyName, itemid, value, 0);
    }
    @Keep
    public static void earnVirtualCurrency(String virtualCurrencyName, String itemid, int value, int currentValue) {
        AndroidSdk.earnVirtualCurrency(virtualCurrencyName, itemid, value, currentValue);
    }
    @Keep
    public static void trackScreenStart(String screenName) {
        AndroidSdk.trackScreenStart(screenName);
    }
    @Keep
    public static void trackScreenEnd(String screenName) {
        AndroidSdk.trackScreenEnd(screenName);
    }
    @Keep
    public static void trackEngagement(long seconds) {
        if (seconds > 0) {
            IvySdk.trackEngagement(seconds);
        }
    }
}