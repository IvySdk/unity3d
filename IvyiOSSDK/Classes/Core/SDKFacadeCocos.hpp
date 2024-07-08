//
//  SDKFacadeCocos.hpp
//  Pods
//
//  Created by 余冰星 on 2017/6/20.
//
//

#ifndef SDKFacadeCocos_h
#define SDKFacadeCocos_h
#ifdef __cplusplus
class WebviewDelegate {
public:
    virtual void onWebviewLoadSuccess(){}
    virtual void onWebviewLoadFailure(){}
    virtual void onWebviewClose(){}
    virtual void jsCallOC(const char * _Nullable param){}
};

class SDKResultDelegate {
public:
    virtual void onInitialized(){}
    virtual void onDeepLinkReceived(const char * _Nullable url){}
    virtual void onNetworkChanged(int status){}
    virtual void onIdCardVerified(){}
    virtual void onReceiveServerExtra(const char * _Nullable data){}
    virtual void onReceiveNotificationData(const char * _Nullable data){}
    virtual void cacheUrlSuccess(const char * _Nonnull tag, const char * _Nonnull path) {}
    virtual void cacheUrlFailure(const char * _Nonnull tag) {}
    
    virtual void onReceiveLoginSuccess(){}
    virtual void onReceiveLoginFailure(){}
    virtual void onPrivacyAccept(){}
    virtual void onPrivacyDeny(){}
    virtual void onReceiveIdCardVerifiedResult(int age){}
    virtual void onAttAccepted(bool isAccept){}
    virtual void unreadMeasgeCount(int count){}
    virtual void onNotificationOpen(bool isOpen){}
};

class AdDelegate {
public:
    virtual void adLoaded(const char * _Nonnull tag, int adType) {}
    virtual void adFailed(const char * _Nonnull tag, int adType, const char * _Nullable error) {}
    virtual void adDidShown(const char * _Nonnull tag, int adType) {}
    virtual void adShowFailed(const char * _Nonnull tag, int adType) {}
    virtual void adDidClose(const char * _Nonnull tag, int adType) {}
    virtual void adDidClick(const char * _Nonnull tag, int adType) {}
    virtual void adReward(const char * _Nonnull tag, int rewardId) {};
    virtual void deliciousIconClick(const char * _Nonnull bannerDownPath, const char * _Nonnull jumpUrl) {};
};

class PaymentDelegate {
public:
    virtual void onPaymentSuccess(int billId) = 0;
    virtual void onPaymentFailure(int billId, const char * _Nullable error) = 0;
    virtual void onPaymentSuccess(int billId, const char * _Nonnull payload) {}
    virtual void onPaymentSuccessWithPayloadAndOrderInfo(int billId, const char * _Nonnull payload, const char * _Nonnull orderInfo) {}
    virtual void onPaymentReady() {}
    virtual void onCheckSubscriptionResult(int billId, long remainSeconds) {}
    virtual void onRestoreSuccess(int billId) {}
    virtual void onRestoreFailure(const char * _Nullable error) {};
    virtual void onAppStorePayRequest(int billId) {}
};

class SNSDelegate {
public:
    virtual void signInAppleSuccess(const char * _Nullable appleId) = 0;
    virtual void signInAppleFailure(const char * _Nullable error) = 0;
    virtual void snsLoginSuccess() = 0;
    virtual void snsLoginCancel() = 0;
    virtual void snsLoginFailure(const char * _Nullable error) = 0;
    virtual void snsShareSuccess() {}
    virtual void snsShareFailure() {}
    virtual void snsShareCancel() {}
    virtual void onGameCenterLoginSuccess(const char * _Nullable playerId) {}
    virtual void onGameCenterLoginFailure() {}
};

class SDKFacadeCocos
{
public:
    typedef void (*complete) (const char* _Nullable result);
    typedef void (*editComplete) (const char* _Nullable result);
    typedef void (*shareResult) (bool success, const char* _Nullable error);
    typedef void (*requestPermissionResult) (bool permit);
    
    static const int SDK_CONFIG_KEY_APP_ID = 1;
    static const int SDK_CONFIG_KEY_LEADER_BOARD_URL = 2;
    static const int SDK_CONFIG_KEY_API_VERSION = 3;
    static const int SDK_CONFIG_KEY_SCREEN_WIDTH = 4;
    static const int SDK_CONFIG_KEY_SCREEN_HEIGHT = 5;
    static const int SDK_CONFIG_KEY_LANGUAGE = 6;
    static const int SDK_CONFIG_KEY_COUNTRY = 7;
    static const int SDK_CONFIG_KEY_VERSION_CODE = 8;
    static const int SDK_CONFIG_KEY_VERSION_NAME = 9;
    static const int SDK_CONFIG_KEY_PACKAGE_NAME = 10;
    static const int SDK_CONFIG_KEY_UUID = 11;
    static const int SDK_CONFIG_KEY_FACEBOOK_ID = 12;
    static const int SDK_CONFIG_KEY_CHANNEL = 20;
    static const int SDK_CONFIG_KEY_JSON_VERSION = 21;
    static const int SDK_CONFIG_KEY_FIREBASE_USERID = 22;
    
    static const int SDK_BANNER_POS_LEFT_TOP = 1;
    static const int SDK_BANNER_POS_TOP = 3;
    static const int SDK_BANNER_POS_RIGHT_TOP = 6;
    static const int SDK_BANNER_POS_LEFT_CENTER = 8;
    static const int SDK_BANNER_POS_CENTER = 5;
    static const int SDK_BANNER_POS_RIGHT_CENTER = 9;
    static const int SDK_BANNER_POS_LEFT_BOTTOM = 2;
    static const int SDK_BANNER_POS_BOTTOM = 4;
    static const int SDK_BANNER_POS_RIGHT_BOTTOM = 7;
    
    static const int SDK_ADTYPE_INTERTITIAL = 1;
    static const int SDK_ADTYPE_VIDEO = 2;
    static const int SDK_ADTYPE_BANNER = 3;
    static const int SDK_ADTYPE_ICON = 4;
    static const int SDK_ADTYPE_NATIVE = 5;
    static const int SDK_ADTYPE_GIFICON = 6;
    
    static const int SDK_INTERVAL_YEAR = (1UL << 2);
    static const int SDK_INTERVAL_MONTH = (1UL << 3);
    static const int SDK_INTERVAL_DAY = (1UL << 4);
    static const int SDK_INTERVAL_HOUR = (1UL << 5);
    static const int SDK_INTERVAL_MINUTE = (1UL << 6);
    static const int SDK_INTERVAL_SECOND = (1UL << 7);
    static const int SDK_INTERVAL_WEEKDAY = (1UL << 9);
    static const int SDK_INTERVAL_WEEKDAYORDINAL = (1UL << 10);
    
    static void showWebView(const char * _Nullable url, int x, int y, int w, int h, WebviewDelegate* _Nullable delegate);
    static void closeWebView();
    static bool isWebViewDisplayed();
    static const char * _Nullable getConfig(int cid);
    static void openApplicationSetting();
    static bool isAdsEnabled();
    static void setAdsEnable(bool enable);
    static const char * _Nullable getPushData();
    static int getScreenWidth();
    static int getScreenHeight();
    static int getScreenDesignWidth();
    static int getScreenDesignHeight();
    static const char * _Nullable getExtraData();
    static void onCreate();
    static void toast(const char * _Nullable msg);
    
    static void showAIHelp(const char* _Nonnull entranceId, const char* _Nonnull meta, const char* _Nullable tags, const char* _Nullable msg);
    static void showSignleFAQ(const char* _Nonnull faqId, int moment);
    static bool isAIHelpInitialized();
    static void loadAIHelpUnreadMessageCount(bool onlyOnce);
    static void stopLoadAIHelpUnreadMessageCount();
    static void closeAIHelp();
    static void switchLanguage(const char* _Nonnull lang);
    
    static bool hasGdpr();
    static void resetGdpr();
    static void setAdsUnderAgeMode(bool value);
    static bool isBannerShowing();
    static bool isBannerAvailable();
    static bool isBannerAvailable(const char * _Nonnull tag);
    static void loadBannerAd(const char * _Nonnull tag);
    static void showBanner(int pos);
    static void showBanner(const char * _Nonnull tag, int pos);
    static void showBanner(const char * _Nonnull tag, float x, float y, float w, float h);
    static void closeBanner();
    static bool isVideoAvailable();
    static bool isVideoAvailable(const char * _Nonnull tag);
    static void loadRewardVideo(const char * _Nonnull tag);
    static void showRewardVideo(int rewardId);
    static void showRewardVideo(const char * _Nonnull tag, int rewardId);
    static bool isInterstitialAvailable(const char * _Nonnull tag);
    static void loadInterstitialAd(const char * _Nonnull tag);
    static void showInterstitialAd(const char * _Nonnull tag);
    static void showInterstitialAd(const char * _Nonnull tag, int seconds);
    static void showInterstitialAd(const char * _Nonnull tag, int seconds, double timeInterval);
    static void showIconAd(float width, float xPercent, float yPercent);
    static void showPopupIconAds();
    static void hidePopupIconAds();
    static const char * _Nonnull getPopupIconAdsData();
    static void closeIconAd();
    static bool isNativeAvailable(const char * _Nonnull tag);
    static const char * _Nullable fetchNativeAdJson(const char * _Nonnull tag);
    static void loadNativeAd(const char * _Nonnull tag);
    static void closeNativeAd(const char * _Nonnull tag);
    static void showNativeAd(const char * _Nonnull tag, float x, float y, const char * _Nonnull json);
    static void showNativeAd(const char *_Nonnull tag, float x, float y, float w, float h, const char * _Nonnull json);
    static bool isDeliciousAdAvailable();
    static void showDeliciousInterstitialAd(const char * _Nonnull json);
    static void showDeliciousBannerAd(float x, float y, float w, float h, const char * _Nonnull json);
    static void closeDeliciousBannerAd();
    static void showDeliciousIconAd(float x, float y, float w, float h, const char * _Nonnull json);
    static void closeDeliciousIconAd();
    static void rateUs();
    static void rateInApp();
    static void rateUs(float star);
    static void rateInApp(float star);
    static const char * _Nonnull fetchDeepLink();
    static bool isNetworkConnected();
    static bool isNetworkAvailable();
    static void setAdDelegate(AdDelegate* _Nullable delegate);
    static void setPaymentDelegate(PaymentDelegate* _Nullable delegate);
    static void setSNSDelegate(SNSDelegate* _Nullable delegate);
    static void setSDKDelegate(SDKResultDelegate* _Nullable delegate);
    static void setPayVerifyUrl(const char * _Nullable url);
    static void pay(int payId);
    static void pay(int payId, const char *_Nonnull payload);
    static void restorePayments();
    static void recheckFailedPayments();
    static const char * _Nullable getPaymentData(int payId);
    static const char * _Nullable getPaymentDatas();
    static void isSubscriptionActive();
    static int* _Nullable getPurchasedIds();
    static void clearPurchasedIds();
    static void clearPurchasedId(int paymentId);
    
    static bool isGameCenterAvailable();
    static void loginGameCenter();
    static void showLeaderboards();
    static void showLeaderboard(int leaderboardId);
    static void showAchievements();
    static void submitScore(int leaderboardId, long long score);
    static long long myHighScore(int leaderboardId);
    static void submitAchievement(int achievementId, double percent);
    static double getAchievementProgress(int achievementId);
    
    static int getRemoteConfigIntValue(const char * _Nonnull key);
    static long getRemoteConfigLongValue(const char * _Nonnull key);
    static double getRemoteConfigDoubleValue(const char * _Nonnull key);
    static bool getRemoteConfigBoolValue(const char * _Nonnull key);
    static const char * _Nonnull getRemoteConfigStringValue(const char * _Nonnull key);
    static void setUserPropertyString(const char * _Nonnull key, const char * _Nonnull value);
    static const char * _Nonnull getSignedAppleUID();
    static const char * _Nonnull getSignedAppleInfo();
    static bool showSignInWithApple(float x, float y, float w, float h, bool useBlackBackground);
    static void hideSignInWithApple();
    static void login();
    static void logout();
    static bool isLogin();
    static const char * _Nonnull meFirstName();
    static const char * _Nonnull meLastName();
    static const char * _Nonnull meName();
    static const char * _Nonnull meId();
    static const char * _Nonnull mePictureURL();
    static const char * _Nonnull me();
    static const char * _Nonnull friends();
    static void fetchFriends(bool invitable);
    static void fetchScores();
    static void invite();
    static void share();
    static void shareContent(const char * _Nonnull contentURL, const char * _Nullable tag, const char * _Nullable quote);
    static void shareSheet(const char * _Nonnull linkURL, const char * _Nullable tag, const char * _Nullable quote);
    static void shareSheetOS(const char * _Nonnull linkURL, const char * _Nullable title);
    static void logError(int errorcode, const char *errorDomain, const char *reason, const char *desc, const char *suggest);
    static void logIvyEventWithData(const char * _Nonnull name, const char * _Nullable data);
    static void logEventWithData(const char * _Nonnull name, const char * _Nullable data);
    static void logPlayerLevel(int levelId);
    static void logPageStart(const char* _Nonnull pageName);
    static void logPageEnd(const char* _Nonnull pageName);
    static void logEvent(const char* _Nonnull eventId);
    static void logEvent(const char* _Nonnull eventId, const char* _Nullable action);
    static void logEvent(const char* _Nonnull eventId, const char* _Nullable action, const char* _Nullable label, long value);
    static void logStartLevel(const char* _Nonnull level);
    static void logFailLevel(const char* _Nonnull level);
    static void logFinishLevel(const char* _Nonnull level);
    static void logFinishAchievement(const char* _Nonnull achievement);
    static void logFinishTutorial(const char* _Nonnull tutorial);
    static void logBuy(const char* _Nonnull itemName, const char* _Nonnull itemType, int count, double price);
    static void logUse(const char* _Nonnull itemName, int number, double price);
    static void logBonus(const char* _Nonnull itemName, int number, double price, int trigger);
    static void logRegister(const char* _Nonnull channel);
    static void registerPush();
    static bool isPushRegistered();
    static void cancelLocalNotification(const char* _Nonnull key);
    static void cancelAllLocalNotifications();
    static const char* _Nullable getLocalNotificationDataJson();
    
    /** 发送本地通知
     @param key 本地通知表示，在将来可以通过key来取消通知
     @param title 本地通知的标题
     @param msg 本地通知的内容
     @param pushTime 如果小于当前时间戳，就按照延迟秒数来发送，如果是<=0就是立即发送，否则按照指定时间戳发送，时间戳精确到秒
     @param interval 通知的循环周期，0表示不循环，可以设置按年，月，日，时，分循环
     @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
     @param soundName           默认为default，如需自定义请设置该选项
     @param userInfo 用户自定义数据，当点击通知打开应用的时候获取到
     */
    static void pushLocalNotification(const char* _Nonnull key, const char* _Nonnull title, const char* _Nonnull msg, long pushTime, int interval, bool repeat, bool useSound, const char* _Nullable soundName, const char* _Nonnull userInfo);
    
    /** 发送本地通知
     @param key 本地通知表示，在将来可以通过key来取消通知
     @param title 本地通知的标题
     @param msg 本地通知的内容
     @param dateStr 通知触发日期，按照 yyyy-MM-dd HH:mm:ss 的时间格式定义
     @param interval 通知的循环周期，0表示不循环，可以设置按年，月，日，时，分循环
     @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
     @param soundName           默认为default，如需自定义请设置该选项
     @param userInfo 用户自定义数据，当点击通知打开应用的时候获取到
     */
    static void pushLocalNotification(const char* _Nonnull key, const char* _Nonnull title, const char* _Nonnull msg, const char* _Nonnull dateStr, int interval, bool repeat, bool useSound, const char* _Nullable soundName, const char* _Nonnull userInfo);
    
    /** 发送远程通知任务
     @param key                 消息的id
     @param title               消息的标题
     @param content             消息内容
     @param useLocalTimeZone    是否按照收件人时区发送
     @param pushTime            发送的时间戳，精确到秒
     @param facebookIds         用户的facebook id列表，多个ID以逗号“,”分隔，下同
     @param uuids               设备的唯一识别码列表，多个ID以逗号“,”分隔，下同
     @param topic               对注册对应主题的设备发送消息(参数优先级：facebookIds, uuids, topic)
     @param iosBadge            ios设备的角标
     @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
     @param soundName           默认为default，如需自定义请设置该选项
     @param dataJson            捆绑的KV对，格式为json字符串："{\"hello\":\"world\"}"
     */
    static void pushRemoteNotification(const char* _Nullable key, const char* _Nonnull title, const char* _Nonnull content, long pushTime, bool useLocalTimeZone, const char* _Nullable facebookIds, const char* _Nullable uuids, const char* _Nullable topic, int iosBadge, bool useSound, const char* _Nullable soundName, const char* _Nullable dataJson);
    
    static void cancelRemoteNotification(const char* _Nonnull key);
    
    static void sendMail(const char* _Nonnull address, const char* _Nonnull subject, const char* _Nonnull content, bool isHTML);
    static const char * _Nullable cacheUrl(const char* _Nonnull url);
    static void cacheUrlWithTag(const char* _Nonnull tag, const char* _Nonnull url);
    
    static void saveBase64ImageToCameraRoll(const char* _Nonnull base64Image);
    static void takeScreenshotToCameraRoll();
    static const char * _Nonnull takeScreenshotToDocumentsDirectory();
    static const char * _Nonnull takeScreenshotToDirectoryAtPath(const char* _Nonnull path);
    
    static bool isIPhoneX();
    static bool isIPad();
    static bool hasNewVersion();
    static const char * _Nonnull getAppstoreVersion();
    static bool hasNotch();
    static int getIdCardVerifiedAge();
    static void verifyIdCard(const char* _Nonnull uuid);
    static void resetVerifyIdCard();
    static bool justShowFullAd();
    static void requestPhotoWritePermission(requestPermissionResult _Nullable p);
    static bool hasPhotoWritePermission();
    static void openSettingPage();
    static bool isRecording();
    static void startRecording();
    static void pauseRecording();
    static void continueRecording();
    static void stopRecording(complete _Nullable p);
    static void editVideo(const char* _Nonnull path, complete _Nullable p);
    static void sharePhotos(const char* _Nonnull photoIdentifiers, shareResult _Nullable p);
    static void shareVideos(const char* _Nonnull photoIdentifiers, shareResult _Nullable p);
    static void shareRecentRecordVideo(shareResult _Nullable p);
    static void showPromotion(float x, float y);
    static void copyToClipboard(const char* _Nonnull copyStr);
    static long getFreeMem();
    
    //MMKV
    static void mmEnableNumberConfuse(bool enableConfuse);
    static void mmSetIntValue(const char* _Nonnull key, int value);
    static int mmGetIntValue(const char* _Nonnull key, int defaultValue);
    static void mmSetLongValue(const char* _Nonnull key, long value);
    static long mmGetLongValue(const char* _Nonnull key, long defaultValue);
    static void mmSetBoolValue(const char* _Nonnull key, bool value);
    static bool mmGetBoolValue(const char* _Nonnull key, bool defaultValue);
    static void mmSetFloatValue(const char* _Nonnull key, float value);
    static float mmGetFloatValue(const char* _Nonnull key, float defaultValue);
    static void mmSetStringValue(const char* _Nonnull key, const char* _Nullable value);
    static const char * _Nonnull mmGetStringValue(const char* _Nonnull key, const char* _Nonnull defaultValue);
    static bool mmContainsKey(const char* _Nonnull key);
    static void mmRemoveKey(const char* _Nonnull key);
    static void mmRemoveKeys(const char* _Nonnull keys);
    static void requestIDFA();
    static bool joinQQGroup(const char * _Nullable groupUin, const char * _Nullable key);
    static void loginNoAnonymous();
    static void loginNoAnonymousCancelable();
    
    static void showGameProtocolDialog();
    static void showPrivacyDialog();
    static void showUserAgreementDialog();
    
    static int getIdCardVerifyedAge();
    static void setIdCardVerified(int age);
    
    static long getTotalDiskSize();
    static long getAvailableDiskSize();
    
    static void playAHAP(const char* _Nonnull name, const char* _Nullable folder);
};
#endif
#endif /* SDKFacadeCocos_h */
