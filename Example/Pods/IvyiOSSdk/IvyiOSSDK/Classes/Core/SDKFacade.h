//
//  IvySdkCore.h
//  IvySdkCore
//
//  Created by 余冰星 on 2017/5/25.
//  Copyright © 2017年 IVY. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <WebKit/WebKit.h>
#import <IvyiOSSdk/NSString+Base64.h>
#import <IvyiOSSdk/SDKHelper.h>
#import <IvyiOSSdk/MBProgressHUD.h>
#import <IvyiOSSdk/SDKConstants.h>
#import <IvyiOSSdk/SDKDelegate.h>
#import <IvyiOSSdk/SDKAdDelegate.h>
#import <IvyiOSSdk/SDKSNSDelegate.h>
#import <IvyiOSSdk/SDKRemoteConfigDelegate.h>
#import <IvyiOSSdk/SDKExtendDelegate.h>
#import <IvyiOSSdk/SDKPaymentDelegate.h>
#import <IvyiOSSdk/SDKCloudFunctionDelegate.h>
#import <IvyiOSSdk/SDKFirestoreDelegate.h>
#import <IvyiOSSdk/SDKFirebaseDatabaseDelegate.h>
#import <IvyiOSSdk/SDKInAppMessageDelegate.h>
#import <IvyiOSSdk/SDKWebviewDelegate.h>
#import <IvyiOSSdk/SDKAppsflyerDelegate.h>
@interface SDKFacade : NSObject<SDKDelegate, SDKExtendDelegate, SDKRemoteConfigDelegate, SDKInAppMessageDelegate, WKScriptMessageHandler, WKNavigationDelegate>
@property (nonatomic, nullable, strong, readonly) UIWindow *window;
@property (nonatomic, nullable, strong, readonly) UIViewController *rootVC;
@property (nonatomic, nullable, strong, readonly) UIWindow *originWindow;
@property (nonatomic, nullable, strong, readonly) NSMutableDictionary *config;
@property (nonatomic, nullable, strong, readonly) NSMutableDictionary *paymentData;
@property (nonatomic, nullable, strong) id<SDKDelegate> sdkDelegate;
@property (nonatomic, nullable, strong) id<SDKAdDelegate> adDelegate;
@property (nonatomic, nullable, strong) id<SDKPaymentDelegate> paymentDelegate;
@property (nonatomic, nullable, strong) id<SDKSNSDelegate> snsDelegate;
//@property (nonatomic, nullable, strong) id<SDKPerformanceDelegate> performance;
@property (nonatomic, nullable, strong) id<SDKExtendDelegate> extend;
@property (nonatomic, nullable, strong) id<SDKCloudFunctionDelegate> cloudFunctionDelegate;
@property (nonatomic, nullable, strong) id<SDKFirestoreDelegate> firestoreDelegate;
@property (nonatomic, nullable, strong) id<SDKFirebaseDatabaseDelegate> firebaseDatabaseDelegate;
@property (nonatomic, nullable, strong) id<SDKInAppMessageDelegate> inAppMessageDelegate;
@property (nonatomic, nullable, strong) id<SDKAppsflyerDelegate> appsflyerDelegate;
@property (nonatomic, nullable, strong, readonly) NSString *backHomeAdTag;
@property (nonatomic, nullable, strong) NSString *gts;
@property (nonatomic, nullable, strong) NSString *userId;
@property (nonatomic, nullable, strong) NSString *appid;
//@property (nonatomic, nullable, strong) NSString *referrer;
@property (nonatomic, nullable) NSString* currentNonce;
#ifdef FIREBASE_PLUS
@property (nonatomic, nullable) NSPersonNameComponents* appleFullName;
#endif
@property (nonatomic) BOOL debug;
@property (nonatomic) BOOL adsUnderAgeMode;
@property (nonatomic) BOOL isChangeVersion;
@property (nonatomic) BOOL justShowFullAd;
@property (nonatomic) int adTimeout;
@property (nonatomic) int vcCount;
@property (nonatomic, readonly) long firstOpenTime;
-(nullable NSDictionary *)getPushData;
-(int)getScreenWidth;
-(int)getScreenHeight;
-(int)getScreenDesignWidth;
-(int)getScreenDesignHeight;
-(BOOL)hasNotch;
-(int)getNotchHeight;
-(nullable id)getExtraData;
-(nullable NSString *)getExtraDataJson;
-(void)onCreate;
-(void)showWebView:(nonnull NSString *)url frame:(CGRect)frame delegate:(nullable id<SDKWebviewDelegate>)delegate;
-(void)closeWebView;
-(BOOL)isWebViewDisplayed;
-(void)rateUs:(CGFloat)star;
-(void)rateInApp:(CGFloat)star;
-(nullable NSString *)getConfig:(SDK_CONFIG_ID)cid;
-(nullable id)getConf:(nonnull NSString *)key;
 
-(void)openApplicationSetting;
-(void)setTargetForChild;
//webview
-(void)displayHelpEngagement:(nonnull NSString*)customerName customInfo:(nonnull NSString*)customInfo;

//AIHelp
// entranceId  自定义入口id
// meta 用户数据 Json字符串
// tag  用户标签 需要提前在AIHelp后台配置好
// wMsg  自定义欢迎语
-(void)showAIHelp:(nullable NSString*) entranceId message:(nullable NSString*)meta tag:(nullable NSString*) tags welcome:(nullable NSString*) wMsg;
//打开指定faq页面
// mnt FQA页面展示练习客服的时机 0 不显示 1 点踩后显示 2 在回复页显示; 默认一直显示
-(void)showSignleFAQ:(nonnull NSString*) faqId momemt:(int)mnt;
//AIHelp 是否已经初始化完成
-(BOOL)isAIHelpInitialized;
//开启未读消息轮询 每五分钟轮询一次
// onlyOnce 只获取一次
// 结果在SDkDelegate中 unreadMeasgeCount返回
-(void)loadAIHelpUnreadMessageCount:(BOOL) onlyOnce;
//停止未读消息轮询
-(void)stopLoadAIHelpUnreadMessageCount;
//主动关闭AIHelp
-(void)closeAIHelp;
//切换 AiHelp 语言设置
-(void)switchLanguage:(nonnull NSString*) lang;

//ads
-(void)onAdClose;
-(BOOL)hasGdpr;
-(void)resetGdpr;
-(BOOL)isBannerShowing;
-(BOOL)isBannerAvailable;
-(BOOL)isBannerAvailable:(nonnull NSString *)tag;
-(nullable UIView *)getBannerView;
-(nullable UIView *)getBannerView:(nonnull NSString *)tag;
-(nullable UIView *)getBannerView:(nonnull NSString *)tag withFrame:(CGRect)frame;
-(void)loadBannerAd:(nonnull NSString *)tag;
-(void)showBanner:(SDK_BANNER_POS)pos;
-(void)showBanner:(nonnull NSString *)tag atPos:(SDK_BANNER_POS)pos;
-(void)showBanner:(nonnull NSString *)tag withFrame:(CGRect)frame;
-(void)closeBanner;
-(BOOL)isVideoAvailable;
-(BOOL)isVideoAvailable:(nonnull NSString *)tag;
-(void)loadRewardVideo:(nonnull NSString *)tag;
-(void)showRewardVideo:(int)rewardId;
-(void)showRewardVideoWithTag:(nonnull NSString *)tag rewardId:(int)rewardId;
-(BOOL)isInterstitialAvailable:(nonnull NSString *)tag;
-(void)loadInterstitialAd:(nonnull NSString *)tag;
-(void)showInterstitialAd:(nonnull NSString *)tag;
-(void)showInterstitialAd:(nonnull NSString *)tag waitSeconds:(int)seconds;
-(void)showInterstitialAd:(nonnull NSString *)tag waitSeconds:(int)seconds timeInterval:(double)interval;
-(BOOL)isNativeAvailable:(nonnull NSString *)tag;
-(nullable NSDictionary *)fetchNativeAd:(nonnull NSString *)tag;
-(nullable NSString *)fetchNativeAdJson:(nonnull NSString *)tag;
-(void)loadNativeAd:(nonnull NSString *)tag;
-(void)closeNativeAd:(nonnull NSString *)tag;
-(nullable UIView *)getNativeAdView:(nonnull NSString *)tag configJson:(nonnull NSString *)json;
-(nullable UIView *)getNativeAdView:(nonnull NSString *)tag frame:(CGRect)frame configJson:(nonnull NSString *)json;
-(void)showNativeAd:(nonnull NSString *)tag x:(float)x y:(float)y configJson:(nonnull NSString *)json;
-(void)showNativeAd:(nonnull NSString *)tag withFrame:(CGRect)frame configJson:(nonnull NSString *)json;
-(void)showPopupIconAds;
-(void)hidePopupIconAds;
-(nonnull NSArray *)getPopupIconAdsData;
-(void)showIconAd:(float)width xPercent:(float)xPercent yPercent:(float)yPercent;
-(void)closeIconAd;
-(nonnull NSMutableArray *)getOurAdDatas:(SDK_ADTYPE)adType;
-(nonnull NSMutableArray *)getOurAdDatasWithTag:(NSString *_Nonnull)adTag;
-(nullable NSDictionary *)pickAvailableApp:(SDK_ADTYPE)type;
-(BOOL)isDeliciousAdAvailable:(BOOL)withVideo;
-(nullable NSMutableArray *)getDeliciousAdDatas:(BOOL)withVideo;
-(nullable UIView *)getDeliciousBannerView:(NSString *_Nullable)configJson;
-(void)showDeliciousInterstitialAd:(NSString *_Nullable)configJson;
-(void)showDeliciousBannerAd:(CGRect)frame configJson:(NSString *_Nullable)json;
-(void)closeDeliciousBannerAd;
-(nullable UIView *)getDeliciousIconView:(NSString *_Nullable)configJson;
-(void)showDeliciousIconAd:(CGRect)frame configJson:(NSString *_Nullable)json;
-(void)closeDeliciousIconAd;
-(void)cacheGifIcon:(BOOL)withVideo;

//Apple Login
-(BOOL)showSignInWithApple:(CGRect)frame useBlackBackground:(BOOL)useBlack;
-(void)hideSignInWithApple;
-(nonnull NSString *)getSignedAppleUID;
-(nullable NSDictionary *)getSignedAppleInfo;
//sns
-(void)setSNSDelegate:(nullable id<SDKSNSDelegate>)delegate;
-(void)login:(sns_login_result _Nullable)handler;
-(void)logout;
-(BOOL)isLogin;
-(nonnull NSString *)meFirstName;
-(nonnull NSString *)meLastName;
-(nonnull NSString *)meName;
-(nonnull NSString *)meId;
-(nonnull NSString *)mePictureURL;
-(nonnull NSString *)me;
-(nonnull NSString *)friends;
-(void)invite;
-(void)fetchFriends:(BOOL)invitable;
-(void)fetchScores;
-(void)sendScore: (int)score;
-(void)sendAchievement: (int)achievement;
-(void)sendAppInvite:(nonnull NSString *)appLinkURL previewImgURL:(nullable NSString *)previewURL;
-(void)share;
-(void)share:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote;
-(void)shareSheet:(nonnull NSString *)linkURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote;
-(void)shareSheetOS:(nonnull NSString *)linkURL title:(nonnull NSString *)title;
-(void)sendInviteWithFriendIDs: (nonnull NSArray *)friendIDs title:(nonnull NSString *)title message:(nonnull NSString *)message;
-(void)sendRequestWithFriendIDs: (nonnull NSArray *)friendIDs title:(nonnull NSString *)title message:(nonnull NSString *)message data:(nonnull NSDictionary *)data;
-(nullable UIView *)getLoginButton;
-(nullable UIView *)getShareButton:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote;
-(nullable UIView *)getSendButton:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote;
-(nullable UIView *)getLikeButton:(nonnull NSString *)url;
//Application delegate
-(void)applicationDidBecomeActive:(nullable UIApplication *)application;
-(void)applicationDidEnterBackground:(nullable UIApplication *)application;
-(BOOL)application:(nullable UIApplication *)application didFinishLaunchingWithOptions:(nullable NSDictionary *)launchOptions;
-(BOOL)application:(nullable UIApplication *)application openURL:(nullable NSURL *)url sourceApplication:(nullable NSString *)sourceApplication annotation:(nullable id)annotation;
- (BOOL)application:(nullable UIApplication *)application openURL:(nullable NSURL *)url options:(nullable NSDictionary *) options;
- (BOOL)application:(nullable UIApplication *)application continueUserActivity:(nullable NSUserActivity *)userActivity restorationHandler:(void (^_Nullable)(NSArray<id<UIUserActivityRestoring>> * _Nullable))restorationHandler;
- (void)application:(nullable UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(nullable NSData *)deviceToken;
- (void)application:(nullable UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(nullable NSError *)error;
- (void)application:(nullable UIApplication *)application didReceiveRemoteNotification:(nullable NSDictionary *)userInfo;
- (void)application:(nullable UIApplication *)application didReceiveLocalNotification:(nullable UILocalNotification *)notification;
- (void)applicationWillTerminate:(nullable UIApplication *)application;
//gameCenter
-(BOOL)isGameCenterAvailable;
-(void)loginGameCenter;
-(void)showLeaderboards;
-(void)showLeaderboard:(int)leaderboardId;
-(void)showAchievements;
-(void)showChallenges;
-(void)submitScore:(int)leaderboardId score:(long long)score;
- (long long)myHighScore:(int)leaderboardId;
-(void)submitAchievement:(int)achievementId percent:(double)percent;
- (double)getAchievementProgress:(int)achievementId;

//payment
-(void)setPayVerifyUrl:(nullable NSString *)url;
-(void)pay:(int)paymentId;
-(void)pay:(int)paymentId payload:(nullable NSString *)payload;
-(nullable NSString *)getPaymentIdByIdentifier:(nullable NSString *)identifier;
-(void)onAppStorePayRequest:(int)payId;
-(void)restorePayments;
-(nullable NSArray<NSString *> *)getPurchasedIds;
-(void)clearPurchasedIds;
-(void)clearPurchasedId:(int)paymentId;
-(void)isSubscriptionActive;
-(nullable NSDictionary *)getPaymentData:(int)paymentId;
-(nullable NSString *)getPaymentDatas;
-(void)recheckFailedPayments;

// Cloud Function & FireStore
-(void) callCloudFunction: (nonnull NSString *)method params:(nullable NSString *)params;
-(void) updateFirestore:(nonnull NSString *)collection transactionId:(nonnull NSString *)transactionId jsonData:(nonnull NSString *)jsonData;
-(void) setFirestore:(nonnull NSString *)collection jsonData:(nonnull NSString *)jsonData;
-(void) mergeFirestore:(nonnull NSString *)collection jsonData:(nonnull NSString *)jsonData;
-(void) deleteFirestore:(nonnull NSString *)collection;
-(void) readFirestore:(nonnull NSString *)collection;
-(void) readFirestore:(nonnull NSString *)collection document:(nonnull NSString*)document;
-(void) snapshotFirestore:(nonnull NSString *)collection;
-(void) initFirestoreAfterSignIn:(nonnull NSString *)provider;
-(void) initializeAndLinkFacebookAfterSignIn;
-(void) loginAppleBeforeFirestore:(bool) isForce;
-(void) linkFirestoreAfterAppleSignIn;
-(void) deleteAppleCountFromFirestore;
-(void) signOutFirestore;

-(void) sendChat:(nonnull NSString *)database path:(nonnull NSString *)path jsonData:(nonnull NSString *)jsonData;

-(void) listen:(nonnull NSString *)database path:(nonnull NSString *)path;

-(void)displayUrl:(nonnull NSString *)url;
-(void)inAppReview;

// InAppMessage
-(void)triggerInAppMessage:(nonnull NSString*)eventName;
-(void) inAppMessageDismissed;
-(void) inAppMessageClicked;
-(void)saveUserAttribute:(nonnull NSString*)data;
-(nonnull NSString*) getFirebaseUserId;

//push
-(void)registerPush;
-(BOOL)isPushRegistered;
-(void)queryPushNotification;
-(void)cancelLocalNotification:(nonnull NSString *)key;
-(void)cancelAllLocalNotifications;
-(nullable NSDictionary *)getLocalNotificationData;
-(nullable NSString *)getLocalNotificationDataJson;
/** 发送本地通知
 @param key 本地通知表示，在将来可以通过key来取消通知
 @param title 本地通知的标题
 @param msg 本地通知的内容
 @param seconds 如果是<=0就是立即发送，否则按照指定时间戳发送，时间戳精确到秒
 @param interval 循环的秒数
 @param repeat 是否循环
 @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
 @param soundName           默认为default，如需自定义请设置该选项
 @param userInfo 用户自定义数据，当点击通知打开应用的时候获取到
 */
-(void)pushLocalNotification:(nonnull NSString *)key title:(nonnull NSString *)title msg:(nonnull NSString *)msg pushTime:(NSTimeInterval)seconds interval:(SDK_INTERVAL)interval repeat:(BOOL)repeat useSound:(BOOL)useSound soundName:(nullable NSString *)soundName userInfo:(nullable NSDictionary *)userInfo;
/** 发送本地通知
 @param key 本地通知表示，在将来可以通过key来取消通知
 @param title 本地通知的标题
 @param msg 本地通知的内容
 @param dateStr 通知触发日期，按照 yyyy-MM-dd HH:mm:ss 的时间格式定义
 @param interval 循环的秒数
 @param repeat 是否循环
 @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
 @param soundName           默认为default，如需自定义请设置该选项
 @param userInfo 用户自定义数据，当点击通知打开应用的时候获取到
 */
-(void)pushLocalNotification:(nonnull NSString *)key title:(nonnull NSString *)title msg:(nonnull NSString *)msg dateStr:(nonnull NSString *)dateStr interval:(SDK_INTERVAL)interval repeat:(BOOL)repeat useSound:(BOOL)useSound soundName:(nullable NSString *)soundName userInfo:(nullable NSDictionary *)userInfo;

/** 发送远程通知任务
 @param key                 消息的id
 @param title               消息的标题
 @param content             消息内容
 @param useLocalTimeZone    是否按照收件人时区发送
 @param seconds            如果是<=0就是立即发送，否则按照指定时间戳发送，时间戳精确到秒
 @param facebookIds         用户的facebook id列表，多个ID以逗号“,”分隔，下同
 @param uuids               设备的唯一识别码列表，多个ID以逗号“,”分隔，下同
 @param topic               对注册对应主题的设备发送消息(参数优先级：facebookIds, uuids, topic)
 @param iosBadge            ios设备的角标
 @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
 @param soundName           默认为default，如需自定义请设置该选项
 @param data                捆绑的KV对，格式为json字符串："{\"hello\":\"world\"}"
 */
- (void)pushRemoteNotification:(nullable NSString *)key title:(nonnull NSString *)title content:(nonnull NSString *)content pushTime:(long)seconds useLocalTimeZone:(BOOL)useLocalTimeZone facebookIds:(nullable NSString *)facebookIds uuids:(nullable NSString *)uuids topic:(nullable NSString *)topic iosBadge:(int)iosBadge useSound:(BOOL)useSound soundName:(nullable NSString *)soundName extraData:(nullable NSDictionary *)data;


- (void)cancelRemoteNotification:(nonnull NSString *)key;
///** 发送循环远程通知任务
// @param messageId           消息ID
// @param title               消息的标题
// @param content             消息内容
// @param useLocalTimeZone    是否按照收件人时区发送
// @param pushTime            发送的时间戳，精确到秒
// @param facebookIds         用户的facebook id列表，多个ID以逗号“,”分隔，下同
// @param uuids               设备的唯一识别码列表，多个ID以逗号“,”分隔，下同
// @param topic               对注册对应主题的设备发送消息(参数优先级：facebookIds, uuids, topic)
// @param iosBadge            ios设备的角标
// @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
// @param soundName           默认为default，如需自定义请设置该选项
// @param data                捆绑的KV对，格式为json字符串："{\"hello\":\"world\"}"
// @param repeatInterval      重复发送周期，<=0时不重复发送，单位为秒
// */
//- (void)pushRepeatRemoteNotification:(nonnull NSString *)messageId title:(nonnull NSString *)title content:(nonnull NSString *)content pushTime:(long)pushTime useLocalTimeZone:(BOOL)useLocalTimeZone facebookIds:(nullable NSString *)facebookIds uuids:(nullable NSString *)uuids topic:(nullable NSString *)topic iosBadge:(int)iosBadge useSound:(BOOL)useSound soundName:(nullable NSString *)soundName extraData:(nullable NSDictionary *)data repeatInterval:(int)repeatInterval;
//
///** 取消循环远程通知任务
// @param messageId           消息ID
// */
//- (void)cancelRepeatRemoteNotification:(nonnull NSString *)messageId;

//analysis
-(void)logError:(int)errorcode errorDomain:(nonnull NSString *)errorDomain reason:(nonnull NSString *)reason  description:(nonnull NSString *)desc
    suggest:(nullable NSString *)suggest;
-(void)logPlayerLevel:(int)levelId;
-(void)logPageStart:(nullable NSString *)pageName;
-(void)logPageEnd:(nullable NSString *)pageName;
//-(void)logStats:(nullable NSString *)referrer;
//-(void)logAdAction:(nonnull NSString *)action platform:(nonnull NSString *)platform;
-(void)logEventWithoutCheck:(nullable NSString *)eventId;
-(void)logIvyEvent:(nullable NSString *)eventId;
-(void)logIvyEvent:(nonnull NSString *)eventId withData:(nullable NSDictionary<NSString *, id> *) data;
-(void)logEventToParfka:(nonnull NSString *)eventId withData:(nullable NSDictionary<NSString *, id> *) data;
-(void)logFirebaseEvent:(nullable NSString *)eventId;
-(void)logFirebaseEvent:(nonnull NSString *)eventId withData:(nullable NSDictionary<NSString *, id> *) data;
-(void)trackScreen:(nonnull NSString*) screenClass screenName:(nonnull NSString*)screenName;

-(void)logEvent:(nullable NSString *)eventId;
-(void)logEvent:(nonnull NSString *)name withData:(nullable NSDictionary<NSString *, id> *) data;
-(void)logEvent:(nullable NSString *)eventId action:(nullable NSString *)action;
-(void)logEvent:(nonnull NSString *)eventId action:(nullable NSString *)action label:(nullable NSString *)label value:(long)value;
-(void)logStartLevel:(nullable NSString *)level;
-(void)logFailLevel:(nullable NSString *)level;
-(void)logFinishLevel:(nullable NSString *)level;
-(void)logFinishAchievement:(nonnull NSString *)achievement;
-(void)logFinishTutorial:(nonnull NSString *)tutorial;
-(void)logRate:(CGFloat)star;
-(void)logAdRevenue:(nonnull NSString *)adClassName mediationType:(SDK_MEDIATION_TYPE)mType adType:(nonnull NSString *)adType adUnit:(nonnull NSString *)adUnit placement:(nonnull NSString *)placement country:(nonnull NSString *)country currency:(nonnull NSString *)currency value:(NSNumber * _Nonnull)value;
-(void)logAdClick:(nonnull NSString *)adType adPos:(nonnull NSString *)adPos platform:(nonnull NSString *)platform;
-(void)logAdImpression:(nonnull NSString *)adType adPos:(nonnull NSString *)adPos platform:(nonnull NSString *)platform;
-(void)logBuy:(nullable NSString *)itemName itemType:(nonnull NSString *)itemType count:(int)count price:(double)price;
-(void)logUse:(nullable NSString *)itemName number:(int)number price:(double)price;
-(void)logBonus:(nullable NSString *)itemName number:(int)number price:(double)price trigger:(int)trigger;
-(void)logRegister:(nullable NSString *)channel;

//实名认证
-(int)getIdCardVerifiedAge;
-(void)setOnIdCardVerifiedDelegate:(nonnull dispatch_block_t)block;
-(void)verifyIdCard:(nonnull NSString *) uuid;
-(void)resetVerifyIdCard;

//other
-(nullable NSString *)fetchDeepLink;
-(BOOL)isNetworkConnected;
-(void)toast:(nonnull NSString *)msg isLongTime:(BOOL)longTime;
-(BOOL)isAdsEnabled;
-(void)setAdsEnable:(BOOL)enable;

-(nullable NSString *)cacheUrl:(nonnull NSString *)url;
-(void)cacheUrl:(nonnull NSString *)tag url:(nonnull NSString *)url succes:(void(^_Nullable)(NSString * _Nonnull tag, NSString * _Nonnull filePath))success failure:(void(^_Nullable)(NSString * _Nonnull tag))failure;

- (void)sendMail:(nonnull NSString *)address subject:(nonnull NSString *)subject content:(nonnull NSString *)content isHTML:(BOOL)isHTML;
-(void)copyToClipboard:(nonnull NSString *)copyStr;
-(BOOL)hasNewVersion;
-(nonnull NSString *)getAppstoreVersion;
-(nullable NSArray *)getTrackAdCountArray:(nonnull NSString *)key;

//MMKV
-(void) mmEnableNumberConfuse:(BOOL)enableConfuse;
-(void) mmSetIntValue:(nonnull NSString *)key value:(int)value;
-(int) mmGetIntValue:(nonnull NSString *)key defaultValue:(int)defaultValue;
-(void) mmSetLongValue:(nonnull NSString *)key value:(long)value;
-(long) mmGetLongValue:(nonnull NSString *)key defaultValue:(long)defaultValue;
-(void) mmSetBoolValue:(nonnull NSString *)key value:(BOOL)value;
-(bool) mmGetBoolValue:(nonnull NSString *)key defaultValue:(BOOL)defaultValue;
-(void) mmSetFloatValue:(nonnull NSString *)key value:(float)value;
-(float) mmGetFloatValue:(nonnull NSString *)key defaultValue:(float)defaultValue;
-(void) mmSetStringValue:(nonnull NSString *)key value:(nullable NSString *)value;
-(nonnull NSString *) mmGetStringValue:(nonnull NSString *)key defaultValue:(nonnull NSString *)defaultValue;
-(bool) mmContainsKey:(nonnull NSString *)key;
-(void) mmRemoveKey:(nonnull NSString *)key;
-(void) mmRemoveKeys:(nonnull NSString *)keys;
-(void) mmClearAll;

// ThirdLogin
- (void)loginNoAnonymous;
- (void)loginNoAnonymousCancelable;
- (void)showGameProtocolDialog;
- (void)showPrivacyDialog;
- (void)showUserAgreementDialog;
- (void)setIdCardVerified:(int) age;
- (int)getIdCardVerifyedAge;

+ (void)trackCrossPromoteImpression:(nonnull NSString*)appID type:(SDK_ADTYPE)type;
+ (void)trackAndOpenStore:(nonnull NSString*) appID type:(SDK_ADTYPE)type;
+ (void)shareScreenshot:(nullable SDKShareCompleteHandler)completeHandler;
+ (void)shareScreenshot:(nullable NSString *)title completeHandler:(nullable SDKShareCompleteHandler)completeHandler;
+ (void)shareScreenshot:(nullable NSString *)title url:(nullable NSURL *)url completeHandler:(nullable SDKShareCompleteHandler)completeHandler;
+ (void)share:(nullable NSString *)title image:(nullable UIImage *)image url:(nullable NSURL *)url completeHandler:(nullable SDKShareCompleteHandler)completeHandler;
+ (nonnull UIImage *)imageWithScreenshot;
+ (void)saveBase64ImageToCameraRoll:(nonnull NSString *)base64Image;
+ (void)takeScreenshotToCameraRoll;
+ (nonnull NSString *)takeScreenshotToDocumentsDirectory;
+ (nonnull NSString *)takeScreenshotToDirectoryAtPath:(nonnull NSString *)path;
+(BOOL)isIPhoneX;
+(BOOL)isIPad;
+(long)getFreeMem;
+(long long)getTotalDiskSize;
+(long long)getAvailableDiskSize;
+(void)requestIDFA;
+ (BOOL)joinQQGroup:(nullable NSString *)groupUin key:(nullable NSString *)key;
+ (void)playAHAP:(nonnull NSString *)name inDirectory:(nullable NSString *)folder;

//appsflyer
- (void)onAppsflyerInitSuccess;
- (void)onAppsflyerInitFail:(nullable NSString *)data;
- (void)onAppsflyerAppOpenAttribution:(nullable NSDictionary *)attributionData;
- (void)onAppsflyerAppOpenAttributionFailure:(nonnull NSError *)error;
- (void)onAppsflyerConversionDataFail:(nonnull NSError *)error;
- (void)onAppsflyerConversionDataSuccesss:(nullable NSDictionary *)installData;


+ (nonnull SDKFacade *) sharedInstance;
//+(nullable UIWindow *)keyWindow;
@end
