package com.android.client;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.ivy.IvySdk;
import com.ivy.IvyUtils;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.billing.impl.IPurchaseQueryCallback;
import com.ivy.util.Logger;


public class Cocos {
    private static final String TAG = Cocos.class.getSimpleName();

    // receive reward
    public static native void rr(boolean success, int id, String tag, boolean skip);

    public static final int AD_FULL = 1;
    public static final int AD_VIDEO = 2;
    public static final int AD_BANNER = 3;
    public static final int AD_NATIVE = 5;
    public static final int AD_GIF_ICON = 6;
    public static final int AD_APPOPEN = 7;

    //ad was clicked
    public static native void awc(int adType, String tag);

    //ad was closed(dismiss)
    public static native void awd(int adType, String tag);

    //ad shows success
    public static native void aws(int adType, String tag);

    // user center message
    public static native void sns(int msg, boolean success, int extra);

    public static native void lar(String tag, boolean success);

    // google result listener
    public static native void gr(int tag, boolean success);

    // home ad loading
    public static native void hal(boolean yes);

    //消费查询
    public static native void ph(String purchaseHistory);

    //订阅是否有效
    public static native void isa(boolean status);

    /**
     * @param bill
     * @param success 0: success , 1: cancel ,2:failure
     */
    public static native void pr(int bill, int success);

    // payment system is valid
    public static native void pv();

    // payment system error
    public static native void pe(String message);

    // bill prices and currency type
    // {"currency":"USD", "1":"0.99", "2":"1.99"}
    public static native void ps(String prices);

    // server result
    public static native void sr(int msg, boolean success, String extra);

    // cache url
    public static native void url(int tag, boolean success, String extra);

    public static native void nd(String data);

    public static native void networkstatus(boolean online);

    public static native void adloaded(int adtype);

    public static native void installreward(String extra);

    public static native void eventoccurred(String eventName);

    public static native void deeplink(String uri);

    // leader board
    public static native void lb(boolean isSubmit, boolean success, String leaderBoardId, String data);

    public static native void deliciouseiconclicked(String bannerLocalUrl, String appstoreUrl);

    public static native void wvsuccess();

    public static native void wvfail();

    public static native void wvclose();

    public static native void wvcall(String params);


    public static final int SNS_RESULT_LOGIN = 1;
    public static final int SNS_RESULT_INVITE = 2;
    public static final int SNS_RESULT_CHALLENGE = 3;
    public static final int SNS_RESULT_LIKE = 4;
    public static final int SNS_RESULT_FRIENDS = 5;

    public static void onCreate(Activity activity) {
        try {
            AndroidSdk.setDisplayInNotch(activity);
            AndroidSdk.Builder builder = new AndroidSdk.Builder();
            builder.setSdkResultListener(new SdkResultListener() {
                @Override
                public void onInitialized() {
                }

                @Override
                public void onReceiveServerExtra(String data) {
                }

                @Override
                public void onReceiveNotificationData(String data) {
                    try {
                        nd(data);
                    } catch (Exception | Error e) {
                        e.printStackTrace();
                    }
                }
            }).setPaymentListener(new PaymentSystemListener() {
                @Override
                public void onPaymentSuccess(int billId) {
                    try {
                        pr(billId, 0);
                    } catch (Throwable t) {
                    }
                }

                @Override
                public void onStoreLoaded(String skuType) {

                }

                @Override
                public void onPaymentSuccess(int billId, String payload) {
                    try {
                        pr(billId, 0);
                    } catch (Throwable t) {

                    }
                }

                @Override
                public void onPaymentFail(int billId) {
                    try {
                        pr(billId, 2);
                    } catch (Throwable t) {

                    }
                }

                @Override
                public void onPaymentSystemValid() {
                    try {
                        pv();
                    } catch (Error e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onPaymentCanceled(int bill) {
                    pr(bill, 1);
                }

                @Override
                public void onPaymentSystemError(int code, String message) {
                    pe(message);
                }

                @Override
                public void onReceiveBillPrices(String prices) {
                    ps(prices);
                }
            }).setUrlListener(new UrlListener() {
                @Override
                public void onSuccess(int tag, String path) {
                    url(tag, true, path);
                }

                @Override
                public void onFailure(int tag) {
                    url(tag, false, "");
                }
            }).setUserCenterListener(new UserCenterListener() {
                @Override
                public void onReceiveLoginResult(boolean success) {
                    Logger.debug(TAG, "Cocos.onReceiveLoginResult ");
                    sns(SNS_RESULT_LOGIN, success, 0);
                }

                @Override
                public void onReceiveFriends(String friends) {
                    Logger.debug(TAG, "Cocos.onReceiveFriends ");
                    sns(SNS_RESULT_FRIENDS, friends != null && !"[]".equals(friends), 0);
                }

                @Override
                public void onReceiveInviteResult(boolean success) {
                    sns(SNS_RESULT_INVITE, success, 0);
                }

                @Override
                public void onReceiveChallengeResult(int count) {
                    sns(SNS_RESULT_CHALLENGE, count != 0, count);
                }

                @Override
                public void onReceiveLikeResult(boolean success) {
                    sns(SNS_RESULT_LIKE, success, 0);
                }
            }).setNetworkChangeListener(new NetworkChangeListener() {
                @Override
                public void onReceive(boolean online) {
                    try {
                        Log.d(TAG, "Notify network status: " + online);
                        // networkstatus(online);
                    } catch (Throwable t) {
                        Log.w(TAG, "networkstatus not defined");
                    }
                }
            }).setAdLoadedListener(new AdLoadedListener() {
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
                        }
                        Log.d(TAG, "Notify AdLoaded: " + newAdType);

                        adloaded(newAdType);
                    } catch (Throwable t) {
                        Log.w(TAG, "adloaded not defined");
                    }
                }
            }).setInstallRewardListener(new InstallRewardListener() {
                @Override
                public void onReward(String extra) {
                    try {
                        installreward(extra);
                    } catch (Throwable t) {
                        Log.w(TAG, "installreward not defined");
                    }
                }
            }).setEventOccurredListener(new EventOccurredListener() {
                @Override
                public void onEventOccurred(String eventName) {
                    try {
                        eventoccurred(eventName);
                    } catch (Throwable t) {
                        Log.w(TAG, "eventoccurred not defined");
                    }
                }
            }).setDeepLinkReceivedListener(new DeepLinkReceivedListener() {
                @Override
                public void onDeepLinkReceived(String uri) {
                    try {
                        deeplink(uri);
                    } catch (Throwable t) {
                        Log.w(TAG, "deeplink not defined");
                    }
                }
            }).setDeliciousIconClickedListener(new DeliciousIconClickedListener() {
                @Override
                public void clicked(String bannerLocalUrl, String storeUrl) {
                    try {
                        Logger.debug(TAG, "delicious icon clicked, " + bannerLocalUrl + ", " + storeUrl);
                        deliciouseiconclicked(bannerLocalUrl, storeUrl);
                    } catch (Throwable t) {
                        Log.w(TAG, "deliciouseiconclicked not defined");
                    }
                }
            }).setFilterActionListener(new IFilterActionListener() {
                @Override
                public void onAction(String action) {

                }
            });

            AndroidSdk.onCreate(activity, builder);
            AndroidSdk.registerAdEventListener(new AdEventListener() {
                @Override
                public void onAdShow(String tag, String platform, int type) {
                    super.onAdShow(tag, platform, type);
                    aws(type, tag);
                }

                @Override
                public void onAdClicked(String tag, String platform, int type) {
                    super.onAdClicked(tag, platform, type);
                    awc(type, tag);
                }
            });

            AndroidSdk.setHomeAdListener(new AndroidSdk.HomeAdListener() {
                @Override
                public void showLoading() {
                    hal(true);
                }

                @Override
                public void closeLoading() {
                    hal(false);
                }
            });
        } catch (Throwable t) {
            Log.e(TAG, "SDK ONCREATE FAILED, CHECK REQUIREDs", t);
        }
    }

    public static void onStart() {
        AndroidSdk.onStart();
    }

    public static void onStop() {
        AndroidSdk.onStop();
    }

    public static void onPause() {
        AndroidSdk.onPause();
    }

    public static void onResume(Activity activity) {
        AndroidSdk.onResume(activity);
    }

    public static void onDestroy() {
        AndroidSdk.onDestroy();
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult called");
        try {
            AndroidSdk.onActivityResult(requestCode, resultCode, data);
        } catch (Throwable t) {
            Log.e(TAG, "SDK onActivityResult FAILED, CHECK REQUIRED");
        }
    }

    public static void showBanner(int pos) {
        Log.d(TAG, "showBanner called");
        AndroidSdk.showBanner("default", pos);
    }

    public static void showBanner(String tag, int pos) {
        Log.d(TAG, "showBanner called with pos");
        AndroidSdk.showBanner(tag, pos);
    }

    public static void showBanner(String tag, int pos, int animate) {
        Log.d(TAG, "showBanner called with animate");
        AndroidSdk.showBanner(tag, pos, animate);
    }

    public static void showBannerNonExistent(int pos) {
        AndroidSdk.showBannerNonExistent("default", pos);
    }

    public static void closeBanner() {
        closeBanner("default");
    }

    public static void closeBanner(String tag) {
        Log.d(TAG, "closeBanner called, tag: " + tag);
        AndroidSdk.closeBanner(tag);
    }

    public static void showPromoteAd(String tag) {
        IvySdk.showPromoteAd(tag);
    }

    public static void showFullAd(final String tag) {
        Log.d(TAG, "show full ad called, tag: " + tag);
        AndroidSdk.showFullAd(tag, new AdListener() {
            @Override
            public void onAdClosed() {
                try {
                    awd(AD_FULL, tag);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            @Override
            public void onAdShow() {
                try {
                    aws(AD_FULL, tag);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            @Override
            public void onAdClicked() {
                try {
                    awc(AD_FULL, tag);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            @Override
            public void onAdShowFails() {

            }
        });
    }

    @Deprecated
    public static void showAppOpenAd(final String tag) {

    }

    public static void showRewardAd(final int id) {
        showRewardAd("default", id);
    }

    public static void showRewardAd(final String tag, final int id) {
        Log.d(TAG, "showRewardAd, tag: " + tag);
        AndroidSdk.showRewardAd(tag, new AdListener() {
            @Override
            public void onAdReward(boolean skip) {
                Log.d(TAG, "onAdReward skip: " + skip);
                try {
                    rr(!skip, id, tag, skip);
                } catch (Throwable t) {

                }
            }

            @Override
            public void onAdShowFails() {
                Log.d(TAG, "onAdShowFails skip: ");
                try {
                    rr(false, id, tag, false);
                } catch (Throwable t) {

                }
            }

            @Override
            public void onAdClosed() {
                try {
                    awd(AD_VIDEO, tag);
                } catch (Throwable t) {
                }
            }

            @Override
            public void onAdShow() {
                super.onAdShow();
                try {
                    aws(AD_VIDEO, tag + "|" + id);
                } catch (Throwable e) {
                }
            }
        });

    }

    @Deprecated
    public static boolean hasAppOpenAd() {
        return false;
    }

    public static boolean hasRewardAd() {
        return AndroidSdk.hasRewardAd("default");
    }

    public static boolean hasRewardAd(String tag) {
        return AndroidSdk.hasRewardAd(tag);
    }

    public static boolean hasRewardedInterstitial() {
        return AndroidSdk.hasRewardedInterstitial("default");
    }

    public static void showRewardedInterstitial() {
        showRewardedInterstitial(0, "default");
    }

    public static void showRewardedInterstitial(final int id, String tag) {
        Log.d(TAG, "showRewardedInterstitial: " + tag);
        AndroidSdk.showRewardedInterstitial(tag, new AdListener() {
            @Override
            public void onAdReward(boolean skip) {
                Log.d(TAG, "onAdReward skip: " + skip);
                try {
                    rr(!skip, id, tag, skip);
                } catch (Throwable t) {

                }
            }

            @Override
            public void onAdShowFails() {
                Log.d(TAG, "onAdShowFails skip: ");
                try {
                    rr(false, id, tag, false);
                } catch (Throwable t) {
                }
            }

            @Override
            public void onAdClosed() {
                try {
                    awd(AD_VIDEO, tag);
                } catch (Throwable t) {
                }
            }
        });
    }


    public static boolean hasFull() {
        return AndroidSdk.hasFull("default");
    }

    public static boolean hasFull(String tag) {
        return AndroidSdk.hasFull(tag);
    }

    public static void showSplashBanner(int pos) {
        AndroidSdk.showSplashBanner(pos);
    }

    public static void closeSplashBanner(int pos) {
        AndroidSdk.closeSplashBanner();
    }

    public static boolean hasBanner(String tag) {
        return AndroidSdk.hasBanner(tag);
    }

    public static void pushMessage(String key, String title, String content, int pushTime, boolean localTimeZone, String fbIds, String uuids, String topics, int iosBadge, boolean useSound, String soundName, String extraData) {
        AndroidSdk.pushMessage(key, title, content, pushTime, localTimeZone, fbIds, uuids, topics, iosBadge, useSound, soundName, extraData);
    }

    public static void pushLocalMessage(String key, String title, String content, int pushTime, int interval, boolean useSound, String soundName, String userInfo) {
        AndroidSdk.pushLocalMessage(key, title, content, pushTime, interval, useSound, soundName, userInfo);
    }

    public static void pushLocalMessage(String key, String title, String content, int delayTime) {
        Logger.error(TAG, "Really? pushLocalMessage already removed");
    }

    public static void cancelLocalMessage(String key) {
        AndroidSdk.cancelLocalMessage(key);
    }

    public static void cancelMessage(String key) {
        AndroidSdk.cancelMessage(key);
    }

    public static boolean pushLocalNotification(String tag, //任务标记
                                                String title, // 通知栏标题
                                                String subtitle, //通知栏内容
                                                long delay, // 延迟时间，单位秒
                                                boolean autoCancel, // 通知栏是否可关闭
                                                String action,     // 通知栏行为，可为空，默认是打开游戏主界面
                                                boolean repeat,  // 是否为重复任务
                                                boolean onNetWorkOn, // 是否需要在联网状态显示通知
                                                boolean requireCharging) { // 是否需要在充电状态显示通知
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

    /**
     *  添加3D touch 按钮
     * @param id                按钮唯一 id
     * @param order             排序 序号
     * @param shortLabel        短称
     * @param longLabel         长称
     * @param icon              图标 ；放置再assets目录下的图片文件的全名
     * @param action            行为
     */
    public static void addShortcut(String id, int order, String shortLabel, String longLabel, String icon, String action) {
        AndroidSdk.addShortcut(id, order, shortLabel, longLabel, icon, action);
    }
    /**
     * 更新3D touch 按钮
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
     * @param id
     */
    public static void deleteShortcut(String id) {
        AndroidSdk.deleteShortcut(id);
    }

    public static void support(String email, String extra) {
        AndroidSdk.support(email, extra);
    }

    public static void support(String email, String title, String extra) {
        AndroidSdk.support(email, title, extra);
    }

    public static boolean hasGDPR() {
        return AndroidSdk.hasGDPR();
    }

    public static void resetGDPR() {
        AndroidSdk.resetGDPR();
    }

    public static void pay(int id) {
        AndroidSdk.pay(id);
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
                ph(data);
            }
        });
    }

    public static void changeSku(int oldId, int newId) {
        AndroidSdk.changeSku(oldId, newId, null);
    }

    public static void changeSku(int oldId, int newId, String payload) {
        AndroidSdk.changeSku(oldId, newId, payload);
    }

    public static String getPaymentDatas() {
        Log.d(TAG, "getPaymentDatas");
        return AndroidSdk.getPrices();
    }

    public static boolean isPaymentValid() {
        return AndroidSdk.isPaymentValid();
    }

    public static String getPrices() {
        return AndroidSdk.getPrices();
    }

    public static void share() {
        Log.d(TAG, "share()");
        AndroidSdk.share();
    }

    public static void share(String url) {
        Log.d(TAG, "share() " + url);
        AndroidSdk.share(url, null);
    }

    public static void share(String url, String quote) {
        AndroidSdk.share(url, quote, null, new ShareResultListener() {
            @Override
            public void onSuccess(String postId) {
                Logger.debug(TAG, "share#onSuccess, postId: " + postId);
            }

            @Override
            public void onCancel() {
                Logger.debug(TAG, "share#onCancel");
            }

            @Override
            public void onError(String message) {
                Logger.error(TAG, message);
            }
        });
    }

    public static void shareVideo(String url) {
        Log.d(TAG, "shareVideo() " + url);
        AndroidSdk.shareVideo(url);
    }

    public static void shareMessage(String msg, boolean sysOnly) {
        Log.d(TAG, "shareMessage()");
        AndroidSdk.share(null, null);
    }

    public static void rate() {
        Log.d(TAG, "rate()");
        AndroidSdk.rateUs();
    }

    public static void rate(float star) {
        Log.d(TAG, "rate to: " + star);
        AndroidSdk.rateUs(star);
    }

    public static String getAfInviteId(){
        return AndroidSdk.getAfInviteId();
    }

    public static void inviteUserByAf(String currentUserId) {
        AndroidSdk.inviteUserByAf(currentUserId);
    }

    public static void moreGame() {
        AndroidSdk.moreGame();
    }

    public static boolean isOtherMusicOn() {
        return AndroidSdk.isOtherMusicOn();
    }

    public static String getPaymentData(int bill) {
        String skuDetail = AndroidSdk.getSKUDetail(bill);
        return skuDetail == null ? "{}" : skuDetail;
    }

    public static void trackEvent(String eventName, String action, String label, int value) {
        AndroidSdk.track(eventName, action, label, value);
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

    public static void trackEvent(String event, String data) {
        AndroidSdk.track(event, data);
    }

    public static void trackEventToConversion(String event, String data) {
        AndroidSdk.track(event, data, 5);
    }

    public static void trackEventToFirebase(String event, String data) {
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

    public static void track(String screenName) {
        AndroidSdk.track(screenName);
    }

    public static void showNative(String tag, int yPercent) {
        Logger.error(TAG, "Really? showNative already removed");
    }

    public static void hideNative(String tag) {
        Logger.error(TAG, "Really? hideNative already removed");
    }

    public static boolean hasNative(String tag) {
        Log.d(TAG, "hasNative called");
        return AndroidSdk.hasNative(tag);
    }

    public static String getExtraData() {
        return AndroidSdk.getExtraData();
    }

    public static void onQuit() {
        Log.d(TAG, "onQuit called");
        AndroidSdk.onQuit();
    }

    public static void login() {
        AndroidSdk.login();
    }

    public static void logout() {
        AndroidSdk.logout();
    }

    public static boolean isLogin() {
        return AndroidSdk.isLogin();
    }

    public static void invite() {
    }

    public static void challenge(String title, String message) {
    }

    public static boolean hasNotch() {
        return AndroidSdk.hasNotch();
    }

    public static int getNotchHeight() {
        return AndroidSdk.getNotchHeight();
    }

    public static String me() {
        return AndroidSdk.me();
    }

    public static String friends() {
        return AndroidSdk.friends();
    }

    public static void like() {
    }

    public static boolean isNetworkConnected() {
        return AndroidSdk.isNetworkConnected();
    }

    /**
     * download this url and cache the result into sdcard
     *
     * @param url
     * @return file path in sdcard: /sdcard/0/.cache/383292918283483291
     */
    public static String cacheUrl(String url) {
        return AndroidSdk.cacheUrl(url);
    }

    public static void clickUrl(String url) {
        AndroidSdk.clickUrl(url);
    }

    public static void cacheUrl(int tag, String url) {
        AndroidSdk.cacheUrl(tag, url);
    }

    public static String getConfig(int configKey) {
        String result = AndroidSdk.getConfig(configKey);
        Logger.debug(TAG, "GetConfig " + configKey + " >>> " + result);
        return result == null ? "" : result;
    }

    public static String getConfig(String packageName, int configKey) {
        String result = AndroidSdk.getConfig(packageName, configKey);
        Logger.debug(TAG, "GetConfigPackageName " + configKey + " >>> " + result);
        return result == null ? "" : result;
    }

    public static void alert(String title, String message) {
        AndroidSdk.alert(title, message);
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

    public static String showNativeAd(String tag) {
        Log.d(TAG, "showNativeAd");
        return AndroidSdk.showNativeAd(tag);
    }

    public static void closeNativeAd(String tag) {
        Log.d(TAG, "closeNativeAd");
        AndroidSdk.closeNativeAd(tag);
    }

    @Deprecated
    public static void clickNativeAd(String tag) {
        Log.d(TAG, "clickNativeAd");
        AndroidSdk.clickNativeAd(tag);
    }

    @Deprecated
    public static void showFloaterReward(int stopX, int stopY) {
    }

    @Deprecated
    public static boolean showNativeBanner(String tag, int xPercent, int yPercent, String configFile) {
        return false;
    }

    public static boolean showNativeBanner(final String tag, final int x, final int y, final int w, final int h, final String configFile) {
        Log.d(TAG, "showNativeBanner");
        AndroidSdk.showNativeBanner(tag, x, y, w, h, configFile);
        return true;
    }

    public static void closeNativeBanner(String tag) {
        AndroidSdk.closeNativeAd(tag);
    }

    public static void loadFullAd(final String tag) {
        Log.d(TAG, "loadFullAd");
        AndroidSdk.loadFullAd(tag, null);
    }

    public static void toast(String s) {
        AndroidSdk.toast(s);
    }

    public static boolean hasDeliciousVideoAd() {
        Log.d(TAG, "hasDeliciousVideoAd");
        return AndroidSdk.hasDeliciousVideoAd();
    }

    public static boolean hasDeliciousIconAd() {
        return AndroidSdk.hasDeliciousIconAd();
    }

    public static boolean hasDeliciousBannerAd() {
        return AndroidSdk.hasDeliciousBannerAd();
    }

    public static void showDeliciousIconAd(final int x, final int y, final int w, final int h, final String config) {
        AndroidSdk.showDeliciousIconAd(x, y, w, h, config);
    }

    public static void closeDeliciousIconAd() {
        AndroidSdk.closeDeliciousIconAd();
    }

    public static void showDeliciousBannerAd(final int x, final int y, final int w, final int h, final String config) {
        Log.d(TAG, "showDeliciousBannerAd");
        AndroidSdk.showDeliciousBannerAd(x, y, w, h, config);
    }

    public static void closeDeliciousBannerAd() {
        AndroidSdk.closeDeliciousBannerAd();
    }

    public static void showDeliciousVideoAd(final String config) {
        AndroidSdk.showDeliciousVideoAd(config);
    }

    public static boolean hasDeliciousAd() {
        return AndroidSdk.hasDeliciousAd();
    }

    public static void setUserTag(String userTag) {
        AndroidSdk.setUserTag(userTag);
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

    public static void silentLoginGoogle(final int tag) {
        Log.d(TAG, "silentLoginGoogle called");
        AndroidSdk.silentLoginGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                Log.d(TAG, "Google Sign in onSuccess()");
            }

            @Override
            public void onFails() {
                Log.d(TAG, "Google Sign in onFails()");
            }
        });
    }

    public static void loginGoogle(final int tag) {
        Logger.debug(TAG, "Cocos#loginGoogle called");
        AndroidSdk.loginGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                Log.d(TAG, "loginGoogle onSuccess()");
            }

            @Override
            public void onFails() {
                Log.d(TAG, "loginGoogle onFails()");
            }
        });
    }

    public static void logoutGoogle(final int tag) {
        Logger.debug(TAG, "logoutGoogle()");
        AndroidSdk.logoutGoogle(new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                Log.d(TAG, "logoutGoogle onSuccess()");
            }

            @Override
            public void onFails() {
                Log.d(TAG, "logoutGoogle onFails()");
            }
        });
    }

    public static boolean isGoogleLogin() {
        Logger.debug(TAG, "isGoogleLogin()");
        return AndroidSdk.isGoogleLogin();
    }

    public static void updateGoogleAchievement(final int tag, String id, int step) {
        Logger.debug(TAG, "updateGoogleAchievement: " + tag + ", id: " + id + ", step: " + step);
        AndroidSdk.updateGoogleAchievement(id, step, new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                Log.d(TAG, "updateGoogleAchievement onSuccess()");
            }

            @Override
            public void onFails() {
                Log.d(TAG, "updateGoogleAchievement onFails()");
            }
        });
    }

    public static void updateGoogleLeaderBoard(final int tag, String id, long value) {
        Logger.debug(TAG, "updateGoogleLeaderBoard called, id: " + id + ", value: " + value);
        AndroidSdk.updateGoogleLeaderBoard(id, value, new GoogleListener() {
            @Override
            public void onSuccess(String googleId, String googleEmail) {
                Cocos.gr(tag, true);
            }

            @Override
            public void onFails() {
                Cocos.gr(tag, false);
            }
        });
    }

    public static void showGoogleAchievements() {
        Log.d(TAG, "showGoogleAchievements called");
        AndroidSdk.showGoogleAchievements();
    }

    public static void showGoogleLeaderBoard(String id) {
        Log.d(TAG, "showGoogleLeaderBoard called: id: " + id);
        AndroidSdk.showGoogleLeaderBoards();
    }

    public static void showGoogleLeaderBoards() {
        Log.d(TAG, "showGoogleLeaderBoards()");
        AndroidSdk.showGoogleLeaderBoards();
    }

    public static boolean isGoogleSupport() {
        Log.d(TAG, "isGoogleSupport()");
        return AndroidSdk.isGoogleSupport();
    }

    private static WebViewHelper webViewHelper = null;

    public static void showWebView(final String url, final int x, final int y, final int w, final int h) {
        Activity activity = IvySdk.getActivity();
        if (activity == null || activity.isFinishing()) {
            Log.e(TAG, "Activity is disposed, display web view is not possible");
            return;
        }
        if (webViewHelper == null) {
            webViewHelper = new WebViewHelper();
        }
        webViewHelper.showWebView(activity, url, x, y, w, h, new WebViewHelper.WebViewListener() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "Webview OnSuccess");
                try {
                    wvsuccess();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            @Override
            public void onFailure() {
                Log.e(TAG, "Webview onFailure");
                try {
                    wvfail();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            @Override
            public void onClose() {
                Log.e(TAG, "Webview onClose");
                try {
                    wvclose();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            @Override
            public String call(String param) {
                Log.e(TAG, "Webview call " + param);
                try {
                    param = param == null ? "" : param;
                    wvcall(param);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
                return "";
            }
        });
    }

    public static void closeWebView() {
        if (webViewHelper != null) {
            webViewHelper.closeWebView();
        }
    }

    public static boolean isWebViewDisplayed() {
        if (webViewHelper != null) {
            return webViewHelper.isWebViewDisplayed();
        }
        return false;
    }

    public void verifyIdCard() {
        AndroidSdk.verifyIdCard();
    }

    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        AndroidSdk.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static void setDisplayInNotch(Activity activity) {
        AndroidSdk.setDisplayInNotch(activity);
    }

    public static void setPayVerifyUrl(String verifyUrl) {
        AndroidSdk.setPayVerifyUrl(verifyUrl);
    }
//
//  public static boolean isSubscriptionActive(int billId) {
//    return AndroidSdk.isSubscriptionActive(billId);
//  }

    public static void isSubscriptionActive(int billId) {
        AndroidSdk.isSubscriptionActive(billId, new IPurchaseQueryCallback<Boolean>() {
            @Override
            public void onResult(Boolean data) {
                isa(data);
            }
        });
    }

    public static long getAppInstallTimestamp(){
        return AndroidSdk.getAppInstallTimestamp();
    }

    public static void forceQuit() {
        AndroidSdk.forceQuit();
    }

    public static final String getSdkType() {
        return AndroidSdk.getSdkType();
    }

    public static final void checkUpdate() {

    }

    public static void copyText(final String str) {
        AndroidSdk.copyText(str);
    }

    public static void resetIdCheck() {
        AndroidSdk.resetIdCheck();
    }

    public static int getIdCardVerifyedAge() {
        return AndroidSdk.getIdCardVerifyedAge();
    }

    public static long getTotalMem() {
        return AndroidSdk.getTotalMem();
    }

    public static long getFreeMem() {
        return AndroidSdk.getFreeMem();
    }

    public static void mmSetIntValue(String key, int value) {
    }

    public static int mmGetIntValue(String key, int defaultValue) {
        return 0;
    }

    public static void mmSetLongValue(String key, long value) {
    }

    public static long mmGetLongValue(String key, long defaultValue) {
        return 0L;
    }

    public static void mmSetBoolValue(String key, boolean value) {
    }

    public static boolean mmGetBoolValue(String key, boolean defaultValue) {
        return false;
    }

    public static void mmSetFloatValue(String key, float value) {
    }

    public static float mmGetFloatValue(String key, float defaultValue) {
        return 0.0f;
    }

    public static void mmSetStringValue(String key, String value) {
    }

    public static String mmGetStringValue(String key, String defaultValue) {
        return "";
    }

    public static boolean mmContainsKey(String key) {
        return false;
    }

    public static void mmRemoveKey(String key) {
    }

    public static void mmRemoveKeys(String keys) {
    }

    public static void mmClearAll() {
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

    public static void setAdStatus(boolean enabled) {
        IvySdk.updateAdStatus(enabled);
    }

    public static void showRectBanner(int x, int y, int w, int h) {
        AndroidSdk.showRectBanner(x, y, w, h);
    }

    public static void closeRectBanner() {
        AndroidSdk.closeRectBanner();
    }

    public static void preload() {
        IvySdk.preloadAds();
    }

    public static String getKeyHashSha1() {
        return AndroidSdk.getKeyHash();
    }

    public static void setTargetForChild() {
        AndroidSdk.setTargetForChild();
    }

    @Deprecated
    public static float getPredConfig(String key) {
        return 0.0f;
    }

    public void setPlayer(String data) {
        AndroidSdk.setPlayer(data);
    }


}