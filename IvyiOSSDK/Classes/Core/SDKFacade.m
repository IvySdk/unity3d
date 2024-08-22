//
//  IvySdkCore.m
//  IvySdkCore
//
//  Created by 余冰星 on 2017/5/25.
//  Copyright © 2017年 IVY. All rights reserved.
//
#import <mach/mach.h>
#import <malloc/malloc.h>
#import <AppTrackingTransparency/AppTrackingTransparency.h>
#import <sys/sysctl.h>
#import <UIKit/UIKit.h>
#import <StoreKit/StoreKit.h>
#import <iAd/iAd.h>
#import <CommonCrypto/CommonCrypto.h>
#import <AdSupport/AdSupport.h>
#import <IvyiOSSdk/UIKit+SDKNetworking.h>
#import <IvyiOSSdk/SDKDebug.h>
#import <IvyiOSSdk/SDKJSONHelper.h>
#import <IvyiOSSdk/SDKCache.h>
#import <IvyiOSSdk/SDKUserDB.h>
#import <IvyiOSSdk/SDKNetworkHelper.h>
#import <IvyiOSSdk/SDKSNSAdapter.h>
#import <IvyiOSSdk/SDKAnalyse.h>
#import <IvyiOSSdk/SDKPush.h>
#import <IvyiOSSdk/SDKBaseInit.h>
#import <IvyiOSSdk/SDKConstants.h>
#import <IvyiOSSdk/SDKTimer.h>
#import <IvyiOSSdk/SDKGCDTimer.h>
#import <IvyiOSSdk/SDKGdpr.h>
#import <IvyiOSSdk/SDKAutoEvent.h>
#import <IvyiOSSdk/NSString+Base64.h>
#import <IvyiOSSdk/SDKNetworkReachabilityManager.h>
#import <IvyiOSSdk/SDKIvyAnalyse.h>
#import <IvyiOSSdk/SDKStartPromotionAd.h>
#import <IvyiOSSdk/EasyTextGlobalConfig.h>
#import <IvyiOSSdk/EasyTextView.h>
#import <IvyiOSSdk/NSString+URLEncoding.h>
#import <IvyiOSSdk/SDKHapticHelper.h>
#import "SDKRemoteConfig.h"
#import "SDKBaseAdModule.h"
#import "SDKVideoModule.h"
#import "SDKNativeModule.h"
#import "SDKBannerModule.h"
#import "SDKInterstitialModule.h"
#import "SDKPopupIconAdView.h"
#import "SDKDeliciousBannerView.h"
#import "SDKDeliciousIconView.h"
#import "SDKDeliciousBanner.h"
#import "SDKDeliciousInterstitial.h"
#import "SDKGCManager.h"
#import "SDKInterstitialViewController.h"
#import "SDKIAPHelper.h"

#import <IvyiOSSdk/SDKGoogleGdpr.h>
#import <GoogleMobileAds/GoogleMobileAds.h>
#import <CoreTelephony/CTCarrier.h>
#import <CoreTelephony/CTTelephonyNetworkInfo.h>

#ifdef VerifyIdCard
#import <IvyiOSSdk/SDKVerifyIdCard.h>
#endif
#ifdef ThirdLogin
#import <IvyiOSSdk/SDKThirdLogin.h>
#endif
#ifdef GoogleTest
#import <IvyiOSSdk/SDKGoogleTestInit.h>
#endif
#ifdef FIREBASE
#import <IvyiOSSdk/SDKFirebaseInit.h>
#import <IvyiOSSdk/SDKFirebasePush.h>
#endif
#ifdef FIREBASE_PLUS
#import <IvyiOSSdk/SDKFirebaseFunction.h>
#import <IvyiOSSdk/SDKFirebaseFirestore.h>
#import <IvyiOSSdk/SDKFirebaseDatabase.h>
#import <IvyiOSSdk/SDKFirebaseInAppMessage.h>
@import FBSDKLoginKit;
#endif
//#ifdef FACEBOOK
//#import <IvyiOSSdk/SDKFacebookInit.h>
//@import FBSDKCoreKit;
//#endif
#import <FBSDKCoreKit/FBSDKAppEvents.h>
#ifdef FB_AUDIENCE
#import <FBAudienceNetwork/FBAdSettings.h>
#endif
#ifdef APPSFLYER
#import <IvyiOSSdk/SDKAppsflyerInit.h>
#endif
//#if APPLOVIN
#import <AppLovinSDK/AppLovinSDK.h>
//#endif
#if APPLOVIN_MAX
#import <OpenWrapSDK/OpenWrapSDK.h>
#import <DTBiOSSDK/DTBiOSSDK.h>
#endif
#ifdef AIHelp
#import <IvyiOSSdk/SDKAIHelp.h>
#endif

//如果有google或者max,引入UMP框架
#define HasUMP (APPLOVIN_MAX||GOOGLE)
#if HasUMP
#import <UserMessagingPlatform/UserMessagingPlatform.h>
#endif
#import <AdServices/AdServices.h>

#if defined(__IPHONE_13_0) && __IPHONE_OS_VERSION_MAX_ALLOWED >= __IPHONE_13_0
#import <UserNotifications/UserNotifications.h>
#import <AuthenticationServices/AuthenticationServices.h>
@interface SDKFacade()<UNUserNotificationCenterDelegate, ASAuthorizationControllerDelegate, ASAuthorizationControllerPresentationContextProviding>
@end
#elif defined(__IPHONE_10_0) && __IPHONE_OS_VERSION_MAX_ALLOWED >= __IPHONE_10_0
#import <UserNotifications/UserNotifications.h>
@interface SDKFacade()<UNUserNotificationCenterDelegate>
@end
#endif

#import <sys/utsname.h>

int const ICON_CHANGE_INTERVAL = 15;
NSString * const SDK_LOCALNOTIFICATION_USERINFO = @"LOCALNOTIFICATION_USERINFO";
NSString * const LAST_GROUP = @"LAST_GROUP";
NSString * const SDK_CONFIG = @"CONFIG";
NSString * const SDK_GTS = @"SDK_GTS";
NSString * const VC_COUNT = @"VC_COUNT";
//NSString * const REFERRER = @"REFERRER";
NSString * const APP_OPEN = @"APP_OPEN";
NSString * const SDK_APPLE_ACCOUNT = @"SDK_APPLE_ACCOUNT";
NSString * const SDK_APPLE_REFERRER = @"SDK_APPLE_REFERRER";
NSString * const SDK_APPLE_FIREBASEAUTH = @"SDK_APPLE_FIREBASEAUTH2.0";
NSString * const SDK_APPLE_REFERRER_SENDED = @"SDK_APPLE_REFERRER_SENDED";
NSString * const SDK_RECHECCK_PAY = @"SDK_RECHECCK_PAY";
NSString * const SDK_FAILED_CONSUMED_PAY = @"SDK_FAILED_CONSUMED_PAY";
NSString * const PAY_SUCCESS_COUNT = @"PAY_SUCCESS_COUNT";
NSString * const HAS_PURCHASED = @"HAS_PURCHASED";
NSString * const PURCHASED_REVENUE = @"PURCHASED_REVENUE";
//NSString * const GET_COUNTRY_CODE_URL = @"http://ip.taobao.com/service/getIpInfo.php?ip=myip";
//NSString * const GET_COUNTRY_CODE_URL2 = @"http://ip-api.com/json";
NSString * const PAY_VERIFY_KEY = @"c3fcd3d76192e4007dfb496cca67e13b";
NSString * const SDK_CONFIG_KEY = @"66e3d50482fb2b467642a858af7574ee";
NSString * const SDK_APPSTORE_APPVERSION = @"SDK_APPSTORE_VERSION";
NSString * const SDK_MM_NUMBER_CONFUSE = @"SDK_MM_NUMBER_CONFUSE";
NSString * const SDK_MM_CONFUSE_PREFIX = @"SDK_MM_CONFUSE:";
NSString * const SDK_MM_CONFUSE_NUMBER = @"SDK_MM_CONFUSE_NUMBER";
NSString * const SDK_HAS_LOGREGISTER = @"SDK_HAS_LOGREGISTER";
NSString * const SDK_HAS_TRACKIDFT = @"SDK_HAS_TRACKIDFT";
NSString * const SDK_REGISTER_PUSH = @"SDK_REGISTER_PUSH";
NSString * const SHOW_BANNER_TIMER = @"SHOW_BANNER_TIMER";
@implementation SDKFacade
{
@private
    BOOL _hasInit;
    BOOL _hasInitAfter;
    BOOL _isFirstOpen;
    BOOL _hasSendFirstOpen;
    NSMutableDictionary<NSString *, SDKBaseAdModule *> *_adModules;
    NSMutableDictionary<NSString *, SKProduct *> *_paymentProducts;
    NSMutableDictionary<NSString *, NSString *> *_paymentIdMap;
    NSMutableDictionary<NSString *, NSNumber *> *_transactions;
    NSMutableDictionary<NSString *, NSNumber *> *_confuseNumber;
    NSMutableArray<NSString *> *_invalidProductIds;
    NSMutableDictionary<NSString *, SDKAutoEvent *> *_autoEvents;
    NSDictionary<NSString *, NSNumber *> *_eventTargets;
    NSDictionary<NSString *, NSString *> *_eventFacebookMapping;
    NSMutableDictionary<NSString *, NSDictionary *> *_leaderboardData;
    NSMutableDictionary<NSString *, NSDictionary *> *_achievementData;
    NSMutableDictionary<NSString *, NSMutableArray *> *_pickAppData;
    NSMutableDictionary<NSString *, NSArray *> *_track;
    NSString *_paymentSharedSecret;
    NSString *_paymentVerifyUrl;
    NSString *_deepLink;
    int _force_check;
    BOOL _gamecenterEnable;
    BOOL _needCallIsSubscriptionActive;
    UIImageView *_currentIconView;
    UIView *_signInAppleBtn;
    WKWebView *_webview;
    BOOL _isWebviewLoadFailed;
    NSString *_showBannerTag;
    SDK_BANNER_POS _showBannerPos;
    id<SDKWebviewDelegate> _webviewDelegate;
    NSMutableDictionary<NSString *, SDKAnalyse *> *_analysesMap;
    NSMutableDictionary<NSString *, NSArray<SDKAnalyse *> *> *_analysesQueueMap;
    NSMutableArray<SDKBaseInit *> *_inits;
    NSMutableArray<SDKPush *> *_pushes;
    SDKSNSAdapter *_snsAdapter;
#ifdef FIREBASE_PLUS
    SDKFirebaseFunction* _firebaseFunctions;
    SDKFirebaseFirestore* _fireStore;
    SDKFirebaseDatabase* _firebaseDatabase;
    SDKFirebaseInAppMessage* _firebaseInAppMessage;
#endif
    NSString *_jsonVersion;
    SDKTimer *_initLaterTimer;
    SDKTimer *_iconTimer;
    NSString *_iconAppStoreId;
    SDKDeliciousInterstitial *_deliciousInterstitial;
    SDKDeliciousBannerView *_deliciousBannerView;
    SDKDeliciousIconView *_deliciousIconView;
    CAKeyframeAnimation* _iconAnimation;
    SDKGdpr *_gdpr;
    id<SDKRemoteConfigDelegate> _remoteConfig;
    id<SDKRemoteConfigDelegate> _sdkRemoteConfig;
    SDKBaseAd *_currentShowAd;
    NSDictionary *_initData;
#ifdef ThirdLogin
    SDKThirdLogin *_thirdLogin;
#endif
    
#ifdef AIHelp
    SDKAIHelp* aiHelper;
#endif
    NSString* userAttributeUrl;
    NSString* eventConversionUrl;
    NSMutableDictionary<NSString *, NSNumber*> *_inAppConversionEvents;
    long _opCount;
    long _startTime;
    long _noAdTime;
    long _backHomeAdInterval;
    long _lastShowBackHomeAdTime;
    BOOL _adsEnabled;
    BOOL _isOverNoAdTime;
    BOOL _canShowBackHomeAd;
    BOOL hasRunCacheGifIconAndVideos;
    BOOL _iPad;
    BOOL _mmEnableConfuse;
    BOOL _enableATT;
    BOOL _af_adrevenue;
}
@synthesize window = _window;
@synthesize rootVC = _rootVC;
@synthesize adsUnderAgeMode = _adsUnderAgeMode;
@synthesize originWindow = _originWindow;
@synthesize config = _config;
@synthesize debug = _debug;
@synthesize adTimeout = _adTimeout;
@synthesize backHomeAdTag = _backHomeAdTag;
@synthesize sdkDelegate = _sdkDelegate;
@synthesize paymentData = _paymentData;
@synthesize paymentDelegate = _paymentDelegate;
@synthesize snsDelegate = _snsDelegate;
@synthesize adDelegate = _adDelegate;
@synthesize extend = _extend;
@synthesize justShowFullAd = _justShowFullAd;
@synthesize firstOpenTime = _firstOpenTime;
@synthesize gts = _gts;
@synthesize userId = _userId;
@synthesize isChangeVersion = _isChangeVersion;
@synthesize appid = _appid;
@synthesize cloudFunctionDelegate = _cloudFunctionDelegate;
@synthesize firestoreDelegate = _firestoreDelegate;
@synthesize firebaseDatabaseDelegate = _firebaseDatabaseDelegate;
@synthesize vcCount = _vcCount;
@synthesize inAppMessageDelegate = _inAppMessageDelegate;
@synthesize appsflyerDelegate = _appsflyerDelegate;
static NSString * CRASH_EMAIL_ADDR;
#pragma mark -
#pragma mark Properties Method
-(NSString *)gts
{
    if (!_gts) {
        _gts = (NSString *)[[SDKCache cache] objectForKey:SDK_GTS];
        _gts = _gts ? _gts : @"0";
    }
    return _gts;
}

-(void)setGts:(NSString *)gts
{
    if (gts) {
        _gts = gts;
        [[SDKCache cache] setObject:gts forKey:SDK_GTS];
    }
}

-(long)firstOpenTime
{
    if (_firstOpenTime <= 0) {
        NSNumber* time = (NSNumber *)[[SDKCache cache] objectForKey:SDK_FIRST_OPEN];
        _firstOpenTime = [time longValue];
    }
    return _firstOpenTime;
}

- (nullable NSMutableDictionary *)config
{
    if(_config == nil || _config == NULL) {
        _config = (NSMutableDictionary *)[[SDKCache cache] objectForKey:SDK_CONFIG];
    }
    return _config;
}

-(void)setVcCount:(int)vcCount
{
    _vcCount = vcCount;
    [[SDKCache cache] setObject:[NSNumber numberWithInt:_vcCount] forKey:VC_COUNT];
}
#pragma mark -
#pragma mark Public
- (void)onCreate
{
    static dispatch_once_t oncreate;
    dispatch_once(&oncreate, ^{
        self->_window = [[UIWindow alloc] initWithFrame:ScreenRect];
        self->_window.rootViewController = [[UIViewController alloc]init];
        self->_originWindow = [UIApplication sharedApplication].keyWindow;
        self->_rootVC = self->_originWindow.rootViewController;
        [self _initConfig];
        [self _syncConfig];
//        [self _initAIHelp];
#if HasUMP
        [self initUmpRequest];
#endif
        [self initAdsOnce];
        self->_initLaterTimer = [SDKTimer startTimer:5 interval:1 onComplete:^{
            [self->_initLaterTimer stop];
            self->_initLaterTimer = nil;
            [[SDKFacade sharedInstance] _initAfterNetwork];
        } onUpdate:nil];
        [self onInitialized];
    });
}

-(void) initAdsOnce{
#if HasUMP
    static dispatch_once_t initAdsOnceToken;
    if(UMPConsentInformation.sharedInstance.canRequestAds) {
        dispatch_once(&initAdsOnceToken, ^{
            [self _initAds];
        });
    }
#else
    [self _initAds];
#endif
}

-(NSArray *)getTrackAdCountArray:(NSString *)key
{
    return _track ? [_track objectForKey:key] : nil;
}
#pragma mark -
#pragma mark WebView
-(void)showWebView:(NSString *)url frame:(CGRect)frame delegate:(id<SDKWebviewDelegate>)delegate
{
    _webviewDelegate = delegate;
    _isWebviewLoadFailed = false;
    [self closeWebView];
    float scale = [UIScreen mainScreen].scale;
    frame = CGRectMake(frame.origin.x / scale, frame.origin.y / scale, frame.size.width / scale, frame.size.height / scale);
    //需要JS调用iOS 原生的方法，都要添加到这里
    WKUserContentController *userContentController = [[WKUserContentController alloc] init];
    // WKWebView的配置
    [userContentController addScriptMessageHandler:self name:@"jsCallOC"];
    [userContentController addScriptMessageHandler:self name:@"closeWebView"];
    // 显示WKWebView
    WKWebViewConfiguration *configuration = [[WKWebViewConfiguration alloc] init];
    configuration.userContentController = userContentController;
    _webview = [[WKWebView alloc] initWithFrame:frame configuration:configuration];
    _webview.scrollView.alwaysBounceVertical = YES;
    _webview.scrollView.showsHorizontalScrollIndicator = NO;
    _webview.scrollView.bouncesZoom = NO;
    _webview.navigationDelegate = self;
    NSURL *_url = [NSURL URLWithString:url];
    NSURLRequest *request = [NSURLRequest requestWithURL:_url];
    [_webview loadRequest:request];
}

-(BOOL)isWebViewDisplayed
{
    return _webview && _webview.superview;
}

-(void)closeWebView
{
    if (_webview && _webview.superview) {
        [_webview removeFromSuperview];
        _webview = nil;
        if (_webviewDelegate) {
            [_webviewDelegate onWebviewClose];
        }
    }
}

-(void)displayUrl:(NSString *)url
{
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:url] options:@{} completionHandler:^(BOOL success) {
    }];
}

-(void)jsCallOC:(NSString *)param
{
    if (_webviewDelegate) {
        [_webviewDelegate jsCallOC:param];
    }
}

#pragma mark -
#pragma mark Webview delegate

-(void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message
{
    if ([message.name isEqualToString:@"jsCallOC"]) {
        [self jsCallOC:message.body];
    } else if ([message.name isEqualToString:@"closeWebView"]) {
        [self closeWebView];
    } else if ([message.name isEqualToString:@"closeHelpEngagement"]) {
       if (_webview && _webview.superview) {
           [UIView transitionWithView:_webview
                             duration:.2
                              options: UIViewAnimationOptionCurveEaseOut
                           animations:^{
                               CGFloat width = self->_webview.frame.size.width;
                               CGFloat height = self->_webview.frame.size.height;
                               self->_webview.frame = CGRectMake(0, height, width, 0);
                            }
                           completion:^(BOOL finished){
                               if (finished){
                                   if (@available(iOS 14.0, *)) {
                                       [self->_webview.configuration.userContentController removeAllScriptMessageHandlers];
                                   }

                                   [self->_webview removeFromSuperview];
                                   self->_webview = nil;
                                   if (self->_webviewDelegate) {
                                       [self->_webviewDelegate onWebviewClose];
                                   }
                               }
           }];
       }
    }
}

-(void)webView:(WKWebView *)webView decidePolicyForNavigationAction:(WKNavigationAction *)navigationAction decisionHandler:(void (^)(WKNavigationActionPolicy))decisionHandler
{
    if (decisionHandler) {
        decisionHandler(WKNavigationActionPolicyAllow);
    }
}

-(void)webView:(WKWebView *)webView didFinishNavigation:(WKNavigation *)navigation
{
    if (_isWebviewLoadFailed) {
        return;
    }
    if (_webview) {
        if (_webviewDelegate) {
            [_webviewDelegate onWebviewLoadSuccess];
        }
        [_rootVC.view addSubview:_webview];
    }
}


-(void)onWebViewFailure
{
    if (_isWebviewLoadFailed) {
        return;
    }
    _isWebviewLoadFailed = true;
    [self closeWebView];
    if (_webviewDelegate) {
        [_webviewDelegate onWebviewLoadFailure];
        _webviewDelegate = nil;
    }
}

-(void)webView:(WKWebView *)webView decidePolicyForNavigationResponse:(WKNavigationResponse *)navigationResponse decisionHandler:(void (^)(WKNavigationResponsePolicy))decisionHandler
{
    long code = ((NSHTTPURLResponse *) navigationResponse.response).statusCode;
    if (code >= 400) {
        if (decisionHandler) {
            decisionHandler(WKNavigationResponsePolicyCancel);
        }
        [self onWebViewFailure];
    } else {
        if (decisionHandler) {
            decisionHandler(WKNavigationResponsePolicyAllow);
        }
    }
}

-(void)webView:(WKWebView *)webView didFailNavigation:(WKNavigation *)navigation withError:(NSError *)error
{
    [self onWebViewFailure];
}

-(void)webView:(WKWebView *)webView didFailProvisionalNavigation:(WKNavigation *)navigation withError:(NSError *)error
{
    [self onWebViewFailure];
}

#pragma mark -
#pragma mark Private
//-(NSString *)referrer
//{
//    if (!_referrer) {
//        _referrer = (NSString *)[[SDKCache cache] objectForKey:REFERRER];
//        _referrer = _referrer ? _referrer : @"unknown";
//    }
//    return _referrer;
//}
//
//-(void)setReferrer:(NSString *)referrer
//{
//    if (referrer) {
//        _referrer = referrer;
//        [[SDKCache cache] setObject:referrer forKey:REFERRER];
//    }
//}
-(void)_initAfterNetwork
{
    if(self->_rootVC == nil){
        if ([[UIApplication sharedApplication] keyWindow] != nil) {
            self->_rootVC = [[[UIApplication sharedApplication] keyWindow] rootViewController];
        }
    }
    if (_hasInitAfter)
        return;
    if (_initLaterTimer) {
        [_initLaterTimer stop];
        _initLaterTimer = nil;
    }
    @try {
        if ([self isNetworkConnected]) {
            NSNumber *flag = (NSNumber *)[[SDKCache cache] objectForKey:SDK_HAS_LOGREGISTER];
            if (!flag.boolValue) {
                NSString *channel = [self.config objectForKey:@"channel"];
                [self logRegister:channel ? channel : @"apple"];
                [[SDKCache cache] setObject:[NSNumber numberWithBool:true] forKey:SDK_HAS_LOGREGISTER];
            }
        }
    } @catch (NSException *exception) {
        DLog(@"[sdk] error : %@", exception.description);
#if DEBUG
        [EasyTextView showErrorText:exception.description];
#endif
    } @finally {
    }
    _hasInitAfter = true;
}

-(NSString *)findGroup:(NSDictionary *)data country:(NSString *)country
{
    for (NSString *group in data) {
        NSArray *arr = (NSArray *)[data objectForKey:group];
        if (arr) {
            for (NSString *code in arr) {
                if ([country isEqualToString:code]) {
                    return group;
                }
            }
        }
    }
    return nil;
}

-(void)_initConfig
{
    if (!_config) {
        _vcCount = ((NSNumber *)[[SDKCache cache] objectForKey:VC_COUNT]).intValue;
        NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
        NSString *buildVersion = [NSString stringWithFormat:@"%@:%@", [infoDictionary objectForKey:@"CFBundleShortVersionString"], [infoDictionary objectForKey:@"CFBundleVersion"]];
        NSNumber *disable_att = (NSNumber *)[infoDictionary objectForKey:@"DISABLE_ATT"];
        _enableATT = !disable_att;
        id tmp = [[SDKCache cache] objectForKey:@"BUILD_VERSION"];
        NSString *lastBuildVersion = tmp ? (NSString *)tmp : @"";
        _isChangeVersion = YES;//![buildVersion isEqualToString:lastBuildVersion];
        if(!self.config || _isChangeVersion) {
            [[SDKCache cache] setObject:buildVersion forKey:@"BUILD_VERSION"];
            NSDictionary *sdk_cg = [SDKJSONHelper getJsonDataFrom:@"sdk_cg" bundle:nil extension:@"json" AESKey:nil];
            NSMutableDictionary *data = nil;
            NSString *group = nil;
            if (sdk_cg) {
                NSString *country = [SDKHelper getCountryCode].lowercaseString;
                group = [self findGroup:sdk_cg country:country];
            }
            if (group) {
                NSString *fileName = [SDKHelper md5:[NSString stringWithFormat:@"sdk_%@", group]];
                data = [SDKJSONHelper getJsonDataFrom:fileName bundle:nil extension:@"json" AESKey:SDK_CONFIG_KEY];
            }
            if(!data){
//                data = [SDKJSONHelper getJsonDataFrom:@"default" bundle:nil extension:@"json" AESKey:SDK_CONFIG_KEY];
                data = [SDKJSONHelper getJsonDataFrom:@"default" bundle:nil];
            }
            if(data) {
                _config = data;
                self.gts = @"0";
            } else {
#if DEBUG
                [EasyTextView showErrorText:@"本地缺少default.json文件，请检查配置！"];
                DLog(@"[sdk] local default.json format incorrect!");
#endif
            }
        }
        [self _setConfig:_config];
        userAttributeUrl = [_config objectForKey:@"user.attribute.url"];
        eventConversionUrl = [_config objectForKey:@"event.conversion.url"];
        _af_adrevenue = !![_config objectForKey:@"af_ad_revenue"];
        //[self _initGdpr:_config];
    }
}

- (void)_setConfig:(NSMutableDictionary *)config
{
    if(config) {
        _config = config;
        _initData = [_config objectForKey:@"init"];
        NSNumber *_tmp = [_config objectForKey:@"appid"];
        _appid = _tmp ? [_tmp stringValue] : @"0";
//        DLog(@"[sdk] setConfig: %@", [SDKJSONHelper toJSONString:_config]);
        _track = [_config objectForKey:@"track"];
        self.gts = [_config objectForKey:@"gts"];
        [[SDKCache cache] setObject:_config forKey:SDK_CONFIG];
    }
}

-(void)_syncConfig
{
    NSString *url = [self _getConfigUrl];
    if(url != nil && url != NULL) {
        __weak SDKFacade *weakself = self;
        DLog(@"[country] [%@]", [SDKHelper getCountryCode]);
        [[SDKNetworkHelper sharedHelper] GET:url parameters:nil jsonRequest:false jsonResponse:false success:^(id responseObject) {
            //请求成功
            if(responseObject) {
                NSString *str = [[NSString alloc] initWithData:responseObject encoding:NSUTF8StringEncoding];
                NSString *decrypt = [SDKHelper decryptWithAES:str key:SDK_CONFIG_KEY];
                if (decrypt) {
                    NSMutableDictionary *data = [SDKJSONHelper toArrayOrNSDictionary:decrypt];
                    if ([data isKindOfClass:[NSDictionary class]] && data.count > 0) {
                        __strong SDKFacade *_self = weakself;
                        DLog(@"sync config: %@", data);
                        [_self _setConfig:data];
                        NSString *countryCode = [data objectForKey:@"clientCountryCode"];
                        [SDKHelper setCountryCode:countryCode];
                        DLog(@"[remote country] [%@]", [SDKHelper getCountryCode]);
                        if (_self->_config) {
                            id tmp = [[SDKCache cache] objectForKey:@"SDK_LAST_PUB_VERSION"];
                            long lastPubV = tmp ? [(NSNumber *)tmp intValue] : LONG_MAX;
                            tmp = [_self->_config objectForKey:@"v_pub"];
                            int curPubV = tmp ? [(NSNumber *)tmp intValue] : 0;
                            if (curPubV > lastPubV) {
                                [[SDKCache cache] setObject:[NSNumber numberWithInt:curPubV] forKey:@"SDK_LAST_PUB_VERSION"];
                                [SDKFacade trackCrossPromoteImpression:[_self->_config objectForKey:@"appstoreid"] type:SDK_ADTYPE_INTERTITIAL];
                                [SDKHelper showAlertDialog:[SDKHelper localizedStr:@"SDK_NOTICE_UPDATE_TITLE" defaultString:@"SDK_NOTICE_UPDATE_TITLE"] desc:[SDKHelper localizedStr:@"SDK_NOTICE_UPDATE_DESC" defaultString:@"SDK_NOTICE_UPDATE_DESC"] okBtn:[SDKHelper localizedStr:@"SDK_NOTICE_UPDATE_CONFIRM" defaultString:@"SDK_NOTICE_UPDATE_CONFIRM"] cancelBtn:[SDKHelper localizedStr:@"SDK_NOTICE_UPDATE_CANCEL" defaultString:@"SDK_NOTICE_UPDATE_CANCEL"] onOk:^{
                                    [SDKFacade trackAndOpenStore:[_self->_config objectForKey:@"appstoreid"] type:SDK_ADTYPE_INTERTITIAL];
                                } onCancel:nil];
                            }
                            tmp = [_self->_config objectForKey:@"remoteconfig"];
                            if (tmp) {
                                [_self setDefaults:tmp];
                            }
                        }
                        [_self onReceiveServerExtra:[_self getExtraDataJson]];
                        [_self _initAfterNetwork];
                    }
                }
            }
        } failure:^(NSError *error) {
            //请求失败
            DLog(@"[sdk] error : %@", [error localizedDescription]);
#if DEBUG
            [EasyTextView showErrorText:error.localizedDescription];
#endif
            [[SDKFacade sharedInstance] _initAfterNetwork];
        }];
    }
}

-(NSString *)_getGroupsUrl
{
    if(self.config != nil && self.config != NULL){
        NSString *url = [self.config objectForKey:@"domain"];
        if(url) {
            url = [NSString stringWithFormat:@"%@%@", url, @"groups.json"];
            return url;
        }
    }
    return nil;
}

-(NSString *)_getConfigUrl
{
    if(self.config){
        NSString *domain = [self.config objectForKey:@"domain"];
        if(domain && domain.length > 0) {
            domain = [domain stringByReplacingOccurrencesOfString:@"/data" withString:@"/edata"];
            NSRange range = [domain rangeOfString:@"v_api="];
            NSString *tmp = [domain substringFromIndex:range.location + range.length];
            range = [tmp rangeOfString:@"&"];
            _jsonVersion = [tmp substringToIndex:range.location];
            NSURL *url = [NSURL URLWithString:domain];
            NSURLComponents *urlComponents = [[NSURLComponents alloc] initWithString:url.absoluteString];
            
            NSMutableDictionary *paramer = [[NSMutableDictionary alloc]init];
            [urlComponents.queryItems enumerateObjectsUsingBlock:^(NSURLQueryItem * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                [paramer setObject:obj.value forKey:obj.name];
            }];
            [paramer setObject:@"appstore" forKey:@"channel"];
            [paramer setObject:self.gts forKey:@"gts"];
            [paramer setObject:[SDKHelper getCountryCode] forKey:@"country"];
#if DEBUG
            [paramer setObject:@"ivy_json" forKey:@"debug"];
#endif
            
            NSString *_url = @"";
            for (NSString *key in paramer) {
                _url = [_url stringByAppendingFormat:@"%@=%@&", key, [paramer objectForKey:key]];
            }
            _url = [_url substringToIndex:_url.length - 1];
            _url = [NSString stringWithFormat:@"%@://%@%@?%@", url.scheme, url.host, url.path, _url];
            return _url;
        }
    }
    return nil;
}

-(void)_initBefore
{
    @try {
#ifdef ThirdLogin
        _thirdLogin = [[SDKThirdLogin alloc] initWithConf:[self getConf:@"thirdlogin"]];
#endif
        id tmp = [[SDKCache cache] objectForKey:@"adsUnderAgeMode"];
        self.adsUnderAgeMode = tmp ? [(NSNumber *)tmp boolValue] : NO;
    } @catch (NSException *exception) {
        DLog(@"%@", exception.description);
    } @finally {
    }
    @try {
        _inits = [[NSMutableArray alloc] init];
        _sdkRemoteConfig = [[SDKRemoteConfig alloc] init];
#ifdef FIREBASE
        SDKFirebaseInit *firebase = [[SDKFirebaseInit alloc] init];
        [_inits addObject:firebase];
#ifdef FIREBASE_PLUS
        _firebaseInAppMessage = [[SDKFirebaseInAppMessage alloc] initWithInAppMessageDelegate:self];
        _firebaseFunctions = [[SDKFirebaseFunction alloc] init];
        _fireStore = [[SDKFirebaseFirestore alloc] init];
        _firebaseDatabase = [[SDKFirebaseDatabase alloc] init];
#endif
        [firebase doInit:nil onComplete:^{
            self->_remoteConfig = firebase;
#ifdef FIREBASE_PLUS
            [self checkAutoInAppMessage];
            [self initEventConverstionEventsMap];
            [self checkAutoEventTrigger];
#endif
            id data = [SDKJSONHelper toArrayOrNSDictionary:[self getRemoteConfigStringValue:@"default_json"]];
            if (data && [data isKindOfClass:[NSDictionary class]]) {
                [self _setConfig:data];
            }
        }];
        _remoteConfig = firebase;
#endif
#ifdef FACEBOOK
        SDKFacebookInit *facebook = [[SDKFacebookInit alloc] init];
        [_inits addObject:facebook];
        [facebook doInit:nil onComplete:nil];
#endif
//        NSString *remoteconfigtype = [_config objectForKey:@"remoteconfig-type"];
//        remoteconfigtype = remoteconfigtype ? remoteconfigtype : @"firebase";
//        if(_initData && _initData.count > 0) {
//            for (NSString *key in _initData.allKeys) {
//                NSDictionary *_data = [_initData objectForKey:key];
//                if (_data && [_data objectForKey:@"appid"]) {
//                    NSString *className = [NSString stringWithFormat:@"SDK%@Init", [[key lowercaseString] capitalizedString]];
//                    Class class = NSClassFromString(className);
//                    if(class) {
//                        SDKBaseInit *initObj = [[class alloc] init];
//                        if(initObj) {
//                            [_inits addObject:initObj];
//                            [initObj doInit:_data onComplete:nil];
//                            if ([remoteconfigtype isEqualToString:className.lowercaseString] && [initObj conformsToProtocol:@protocol(SDKRemoteConfigDelegate)]) {
//                                _remoteConfig = (id<SDKRemoteConfigDelegate>)initObj;
//                            }
//                            if ([initObj conformsToProtocol:@protocol(SDKExtendDelegate)]) {
//                                _extend = (id<SDKExtendDelegate>)initObj;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        [self setDefaults:[_config objectForKey:@"remoteconfig"]];
    } @catch (NSException *exception) {
        DLog(@"%@", exception.description);
#if DEBUG
        [EasyTextView showErrorText:exception.description];
#endif
    } @finally {
    }
#if APPLOVIN_MAX
    NSString *aps_appid = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"APS_APPID"];
    if (aps_appid) {
        [[DTBAds sharedInstance] setAppKey: aps_appid];
        DTBAdNetworkInfo *adNetworkInfo = [[DTBAdNetworkInfo alloc] initWithNetworkName: DTBADNETWORK_MAX];
        [DTBAds sharedInstance].mraidCustomVersions = @[@"1.0", @"2.0", @"3.0"];
        [[DTBAds sharedInstance] setAdNetworkInfo: adNetworkInfo];
        [DTBAds sharedInstance].mraidPolicy = CUSTOM_MRAID;
#if DEBUG
        [[DTBAds sharedInstance] setTestMode:YES];
        [[DTBAds sharedInstance] setLogLevel:DTBLogLevelAll];
#endif
    }
    POBApplicationInfo *appInfo = [[POBApplicationInfo alloc] init];
    appInfo.storeURL = [NSURL URLWithString:[NSString stringWithFormat:@"https://itunes.apple.com/us/app/myapp/id%@?mt=0", [_config objectForKey:@"appstoreid"]]];
    [OpenWrapSDK setApplicationInfo:appInfo];

#endif
#if APPLOVIN
    [ALSdk shared].mediationProvider = @"max";
    [ALSdk shared].userIdentifier = [SDKHelper getIDFVString];
    [[ALSdk shared] initializeSdkWithCompletionHandler:^(ALSdkConfiguration *configuration) {
#if DEBUG
        [ALSdk shared].settings.verboseLoggingEnabled = YES;
#if APPLOVIN_MAX
        [[ALSdk shared] showMediationDebugger];
        //[[DTBAds sharedInstance] setTestMode:YES];
#endif
#endif
    }];
#endif
//    @try {
//        // init sns adapters
//        NSDictionary *snsData = [_config objectForKey:@"sns"];
//        if(snsData) {
//            NSString *api = [snsData objectForKey:@"api"];
//            api = api ? api : @"facebook";
//            api = [[api lowercaseString] capitalizedString];
//            Class class = NSClassFromString([NSString stringWithFormat:@"SDKSNS%@Adapter", api]);
//            if(class) {
//                _snsAdapter = [[class alloc] init];
//                if(_snsAdapter) {
//                    [_snsAdapter setup:snsData];
//                }
//            }
//        }
//    } @catch (NSException *exception) {
//        DLog(@"%@", exception.description);
//#if DEBUG
//        [EasyTextView showErrorText:exception.description];
//#endif
//    } @finally {
//    }
    @try {
        _eventTargets = [_config objectForKey:@"event_targets"];
        // init sns adapters
        NSDictionary *data = [_config objectForKey:@"gen_events"];
        if(data) {
            _autoEvents = [[NSMutableDictionary alloc] init];
            for (NSString *key in data) {
                NSArray *arr = data[key];
                for (NSMutableDictionary *obj in arr) {
                    NSString *targetEvent = (NSString *)[obj objectForKey:@"e1"];
                    if (_iPad && isPad) {
                        targetEvent = [NSString stringWithFormat:@"ipad_%@", targetEvent];
                        [obj setObject:targetEvent forKey:@"e1"];
                    }
                    if (targetEvent) {
                        SDKAutoEvent *e = [_autoEvents objectForKey:targetEvent];
                        if (!e) {
                            e = [[SDKAutoEvent alloc] initWithTargetEvent:targetEvent];
                        }
                        NSString *sendEvent = key;
                        if (_iPad && isPad) {
                            sendEvent = [NSString stringWithFormat:@"ipad_%@", key];
                        }
                        [e add:sendEvent data:obj];
                        if ([e isValid]) {
                            [_autoEvents setObject:e forKey:targetEvent];
                        }
                    }
                }
            }
        }
    } @catch (NSException *exception) {
        DLog(@"%@", exception.description);
#if DEBUG
        [EasyTextView showErrorText:exception.description];
#endif
    } @finally {
    }
    @try {
//        _eventFacebookMapping = [_config objectForKey:@"facebookEventMapping"];
//        NSNumber *tmp = [_config objectForKey:@"ipad-event"];
//        if (tmp && tmp.boolValue) {
//            _iPad = true;
//        }
        // init analyses
        NSArray *analyseArr = [_config objectForKey:@"analyse"];
        if(analyseArr && [analyseArr count] > 0) {
            _analysesMap = [[NSMutableDictionary alloc] init];
            for (int i=0; i<[analyseArr count]; i++) {
                NSDictionary *data = [analyseArr objectAtIndex:i];
                if(data) {
                    NSString *api = [data objectForKey:@"api"];
                    if(api) {
                        id tmp = [data objectForKey:@"enable"];
                        BOOL enable = tmp ? [((NSNumber *)tmp) boolValue] : YES;
                        if (enable) {
                            api = [[api lowercaseString] capitalizedString];
                            Class class = NSClassFromString([NSString stringWithFormat:@"SDK%@Analyse", api]);
                            if(class) {
                                SDKAnalyse *obj = [[class alloc] init];
                                if(obj) {
                                    [obj setup:data];
                                    [_analysesMap setObject:obj forKey:obj.platform];
                                }
                            }
                        }
                    }
                }
            }
        }
//        NSString *_parfkaUrl = [_config objectForKey:@"parfkaUrl"];
//        if (_parfkaUrl) {
//            SDKIvyAnalyse *analyse = [[SDKIvyAnalyse alloc] init];
//            analyse.parfkaUrl = _parfkaUrl;
//            [analyse setup:_config];
//            [_analysesMap setObject:analyse forKey:analyse.platform];
//        }
    } @catch (NSException *exception) {
        DLog(@"%@", exception.description);
#if DEBUG
        [EasyTextView showErrorText:exception.description];
#endif
    } @finally {
    }
#ifdef FIREBASE
    @try {
        // init push
        NSArray *pushArr = [_config objectForKey:@"push"];
        if(pushArr && [pushArr count] > 0) {
            _pushes = [[NSMutableArray alloc] init];
            for (int i=0; i<[pushArr count]; i++) {
                NSDictionary *data = [pushArr objectAtIndex:i];
                if(data) {
                    NSString *api = [data objectForKey:@"api"];
                    if(api) {
                        api = [[api lowercaseString] capitalizedString];
                        Class class = NSClassFromString([NSString stringWithFormat:@"SDK%@Push", api]);
                        if(class) {
                            SDKPush *obj = [[class alloc] init];
                            if(obj) {
                                [obj setup:data];
                                [_pushes addObject:obj];
                            }
                        }
                    }
                }
            }
        }
    } @catch (NSException *exception) {
        DLog(@"%@", exception.description);
#if DEBUG
        [EasyTextView showErrorText:exception.description];
#endif
    } @finally {
    }
#endif
#if !DEBUG
    // 发送 Apple 归因
    if([[NSUserDefaults standardUserDefaults] objectForKey:SDK_APPLE_REFERRER_SENDED] == nil) {
        if (@available(iOS 14.3, *)) {
            NSString *token = [AAAttribution attributionTokenWithError:nil];
            if (token) {
                NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
                NSURLSession *session = [NSURLSession sessionWithConfiguration:configuration delegate:nil delegateQueue:nil];
                NSURL *url = [NSURL URLWithString:@"https://api-adservices.apple.com/api/v1/"];
                NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:url
                                                                       cachePolicy:NSURLRequestUseProtocolCachePolicy
                                                                   timeoutInterval:60.0];
                [request addValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
                [request setHTTPMethod:@"POST"];
                NSData* postData = [token dataUsingEncoding:NSUTF8StringEncoding];
                [request setHTTPBody:postData];
                NSURLSessionDataTask *postDataTask = [session dataTaskWithRequest:request completionHandler:^(NSData *data, NSURLResponse *response, NSError *error) {
                    if (!error) {
                        NSError *resError;
                        NSMutableDictionary *resDic = [NSJSONSerialization JSONObjectWithData:data options:kNilOptions error:&resError];
                        if (resDic)
                            [[SDKCache cache] setObject:resDic forKey:SDK_APPLE_REFERRER];
                    }
                }];
                [postDataTask resume];
            } else {
                // Fallback on earlier versions
            }
        }
        NSDictionary *apple_ref = (NSDictionary *)[[SDKCache cache] objectForKey:SDK_APPLE_REFERRER];
        if (apple_ref) {
            [self logEvent:@"apple_referrer" withData:apple_ref];
            [[NSUserDefaults standardUserDefaults] setBool:YES forKey:SDK_APPLE_REFERRER_SENDED];
            [[NSUserDefaults standardUserDefaults] synchronize];
        }
    }
#endif
}

#pragma mark -
#pragma mark Gdpr
-(void)_initGdpr:(nonnull NSDictionary *)config
{
#ifdef GOOGLE
    @try {
        NSDictionary *ads = [_config objectForKey:@"ads"];
        if(ads) {
            _gdpr = [[SDKGoogleGdpr alloc] init];
            [_gdpr onCreate:ads];
        }
    } @catch (NSException *exception) {
        DLog(@"[sdk]Exception: %@", [exception description]);
    } @catch (NSError *error) {
        DLog(@"[sdk]Error: %@", [error localizedDescription]);
    } @finally {
    }
#endif
}

-(BOOL)hasGdpr
{
    if(_gdpr) {
        return [_gdpr hasGdpr];
    }
    return NO;
}

-(void)resetGdpr
{
    if(_gdpr) {
        [_gdpr resetGdpr];
    }
}

-(void)setAdsUnderAgeMode:(BOOL)value
{
    _adsUnderAgeMode = value;
    [[SDKCache cache] setObject:[NSNumber numberWithBool:value] forKey:@"adsUnderAgeMode"];
}

-(void)displayHelpEngagement:(nonnull NSString*)customerName customInfo:(nonnull NSString*)customInfo;
{

    NSString* userName = [self getFirebaseUserId];
    if (!userName || [userName isEqualToString:@""]) {
        userName = [self getConfig:SDK_CONFIG_KEY_UUID];
    }
    [self closeWebView];

    CGFloat width = _rootVC.view.frame.size.width;
    CGFloat height = _rootVC.view.frame.size.height;

    //需要JS调用iOS 原生的方法，都要添加到这里
    WKUserContentController *userContentController = [[WKUserContentController alloc] init];
    // WKWebView的配置
    [userContentController addScriptMessageHandler:self name:@"closeHelpEngagement"];

    // 显示WKWebView
    WKWebViewConfiguration *config = [[WKWebViewConfiguration alloc] init];
    config.userContentController = userContentController;

    _webview = [[WKWebView alloc] initWithFrame:CGRectMake(0, 0, width, height) configuration:config];
    NSString* widgetURL = @"tiledesk_projectid=%@&tiledesk_userFullname=%@&tiledesk_fullscreenMode=true&tiledesk_hideHeaderCloseButton=false&tiledesk_isOpen=true&extra=%@";

    NSString* projectId = @"62e72ac738480f797ef40eb7";
    if(self.config) {
        projectId = [_config objectForKey:@"helpengage.appId"];
        if (projectId == NULL) {
            projectId = @"62e72ac738480f797ef40eb7";
        }
    }

    NSString* resultWidgetURL = [NSString stringWithFormat:widgetURL, projectId, userName, customInfo];
    resultWidgetURL = [resultWidgetURL stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLQueryAllowedCharacterSet]];

    resultWidgetURL = [NSString stringWithFormat:@"%@%@", @"https://widget.adsfall.com/assets/twp/index.html?", resultWidgetURL];
    // 2.创建URL
    NSURL *url = [NSURL URLWithString:resultWidgetURL];
    // 3.创建Request
    NSURLRequest *request =[NSURLRequest requestWithURL:url];
    // 4.加载网页
    [_webview loadRequest:request];
    // 5.最后将webView添加到界面
    [_rootVC.view addSubview:_webview];
}

#pragma mark -
#pragma mark AIHelp

-(void) _initAIHelp{
    static dispatch_once_t initAIHelp;
    dispatch_once(&initAIHelp, ^{
#ifdef AIHelp
        aiHelper = nil;
        NSDictionary* helpConfig = [self->_config objectForKey:@"AIHelp"];
        if(helpConfig == nil) {
            return;
        }
        NSString* apiKey = [helpConfig objectForKey:@"appKey"];
        NSString* appId = [helpConfig objectForKey:@"appId"];
        NSString* domain = [helpConfig objectForKey:@"domain"];
        aiHelper = [[SDKAIHelp alloc] initWithkey:apiKey withAppId:appId withUrl:domain];
#endif
    });
}

-(void)showAIHelp:(nullable NSString*) entranceId message:(nullable NSString*)meta tag:(nullable NSString*) tags welcome:(nullable NSString*) wMsg{
#ifdef AIHelp
    if(aiHelper == nil) {
        return;
    }
    if(entranceId == nil) {
        return;
    }
    if(meta == nil) {
        return;
    }
    
    [aiHelper showAIHelp:entranceId message:meta tag:tags welcome:wMsg];
#endif
}

-(void)showSignleFAQ:(nonnull NSString*) faqId momemt:(int)mnt{
#ifdef AIHelp
    if(aiHelper == nil) {
        return;
    }
    [aiHelper showSignleFAQ:faqId momemt:mnt];
#endif
}

-(BOOL)isAIHelpInitialized{
#ifdef AIHelp
    if(aiHelper == nil) {
        return FALSE;
    }
    return [aiHelper isAIHelpInitialized];
#else
    return FALSE;
#endif

}

-(void)loadAIHelpUnreadMessageCount:(BOOL) onlyOnce{
#ifdef AIHelp
    if(aiHelper == nil) {
        return;
    }
    [aiHelper loadAIHelpUnreadMessageCount:onlyOnce];
#endif
}

-(void)stopLoadAIHelpUnreadMessageCount{
#ifdef AIHelp
    if(aiHelper == nil) {
        return;
    }
    [aiHelper stopLoadAIHelpUnreadMessageCount];
#endif
}


-(void)closeAIHelp{
#ifdef AIHelp
    if(aiHelper == nil) {
        return;
    }
    [aiHelper closeAIHelp];
#endif
}

-(void)switchLanguage:(nonnull NSString*) lang{
#ifdef AIHelp
    if(aiHelper == nil) {
        return;
    }
    [aiHelper switchLanguage:lang];
#endif
}

#pragma mark -
#pragma mark Ads
-(void)onAdClose
{
    _currentShowAd = nil;
}

-(void)saveAdsEnabled:(BOOL)adsEnabled
{
    _adsEnabled = adsEnabled;
    [[SDKCache cache] setObject:[NSNumber numberWithBool:_adsEnabled]  forKey:SDK_ADS_ENABLE];
}

-(BOOL)isAdsEnabled
{
    return _adsEnabled;
}

-(void)setAdsEnable:(BOOL)enable
{
    if(!_adsEnabled && _hasInitAfter && enable) {
        [self _initAds];
    }
    [self saveAdsEnabled:enable];
}

-(void) _initAds
{
    if(_config) {
#ifdef GoogleTest
        [[[SDKGoogleTestInit alloc] init] doInit:nil];
#endif
#if FB_AUDIENCE
        [FBAdSettings setAdvertiserTrackingEnabled:YES];
#if DEBUG
        [FBAdSettings setLogLevel:FBAdLogLevelLog];
#else
        [FBAdSettings clearTestDevice:[FBAdSettings testDeviceHash]];
#endif
#endif
#if DEBUG
        GADMobileAds.sharedInstance.requestConfiguration.testDeviceIdentifiers = @[ @"cf4e177a1e26d0c1e5d0e83310084eb2" ];
#endif
        [GADMobileAds.sharedInstance startWithCompletionHandler:^(GADInitializationStatus * _Nonnull status) {
            DLog(@"[adlog] GADMobileAds start");
            ALSdk *sdk = [ALSdk sharedWithKey:@"E8pVhU9mykQd3y0TD0Ksoq4vpf_Muat6ifcP9m96UakTWk5klQaWEeQ2IPOA-GHgxu54eEA8pvgKcn2MBdtQGH"];
            [sdk.settings setExtraParameterForKey: @"pisw" value: @"true"];
#if DEBUG
            [GADMobileAds.sharedInstance presentAdInspectorFromViewController:self->_rootVC completionHandler:^(NSError * _Nullable error) {
                if(error != nil){
                    DLog(@"[ad]");
                }
            }];
#endif
        }];

        DLog(@"[adlog] ============= init ads start =============");
        [self _prepareAdData];
        NSDictionary *ads = [_config objectForKey:@"ads"];
        if(ads) {
            _backHomeAdTag = [ads objectForKey:@"backHomeAd"];
            _backHomeAdTag = _backHomeAdTag ? _backHomeAdTag : [ads objectForKey:@"back_home_ad"];
            id tmp = [ads objectForKey:@"backHomeAdInterval"];
            _backHomeAdInterval = tmp ? [((NSNumber *)tmp) longValue] : 0;
            
            tmp = [ads objectForKey:@"noAdTime"];
            tmp = tmp ? tmp : [ads objectForKey:@"no_ad_time"];
            if(tmp) {
                _noAdTime = [((NSNumber *)tmp) longValue];
            }
            NSDictionary *splash_promotion = (NSDictionary *)[ads objectForKey:@"splash_promotion"];
            if (splash_promotion) {
                SDKStartPromotionAd *ad = [[SDKStartPromotionAd alloc] initWithData:splash_promotion];
                if ([ad isValid]) {
                    [ad show:nil];
                }
            }
            if(self.isAdsEnabled) {
                // init banners
                NSDictionary *banners = [ads objectForKey:@"banner"];
                if(banners) {
                    NSArray *tags = [banners allKeys];
                    for (NSString *tag in tags) {
                        NSDictionary *data = [banners objectForKey:tag];
                        [self createAdModule:SDK_ADTYPE_BANNER withTag:tag moduleData:data];
                    }
                }
                
                // init interstitials
                NSDictionary *inters = [ads objectForKey:@"full"];
                if(inters) {
                    NSArray *tags = [inters allKeys];
                    for (NSString *tag in tags) {
                        NSDictionary *data = [inters objectForKey:tag];
                        [self createAdModule:SDK_ADTYPE_INTERTITIAL withTag:tag moduleData:data];
                    }
                }
                
                // init native
                NSDictionary *natives = [ads objectForKey:@"native"];
                if(natives) {
                    NSArray *tags = [natives allKeys];
                    for (NSString *tag in tags) {
                        NSDictionary *data = [natives objectForKey:tag];
                        [self createAdModule:SDK_ADTYPE_NATIVE withTag:tag moduleData:data];
                    }
                }
            }
            
            // init videos
            NSDictionary *videos = [ads objectForKey:@"video"];
            if(videos) {
                NSArray *tags = [videos allKeys];
                for (NSString *tag in tags) {
                    NSDictionary *data = [videos objectForKey:tag];
                    [self createAdModule:SDK_ADTYPE_VIDEO withTag:tag moduleData:data];
                }
            }
        }
    }
}

-(void)createAdModule:(SDK_ADTYPE)adType withTag:(nonnull NSString *)tag moduleData:(NSDictionary *)data
{
    @synchronized(_adModules) {
        if(!_adModules) {
            _adModules = [[NSMutableDictionary alloc] init];
        }
        NSNumber *tmp = (NSNumber *) [data objectForKey:@"enable"];
        BOOL enable = tmp ? [tmp boolValue] : false;
        if(enable) {
            SDKBaseAdModule *module = nil;
            switch (adType) {
                case SDK_ADTYPE_INTERTITIAL:
                    module = [[SDKInterstitialModule alloc] init:tag config:data];
                    break;
                case SDK_ADTYPE_BANNER:
                    module = [[SDKBannerModule alloc] init:tag config:data];
                    break;
                case SDK_ADTYPE_VIDEO:
                    module = [[SDKVideoModule alloc] init:tag config:data];
                    break;
                case SDK_ADTYPE_NATIVE:
                    module = [[SDKNativeModule alloc] init:tag config:data];
                    break;
                default:
                    break;
            }
            if (module && [module enable]) {
                [_adModules setObject:module forKey:[NSString stringWithFormat:@"%d.%@", (int)adType, tag]];
            }
        }
    }
}

-(id)getAdModule:(SDK_ADTYPE)adType withTag:(nonnull NSString *)tag
{
    if(_adModules) {
        SDKBaseAdModule *module = [_adModules objectForKey:[NSString stringWithFormat:@"%d.%@", (int)adType, tag]];
        if (module) {
            return module;
        }
    }
    return nil;
}

-(NSString *)getOurADTag:(SDK_ADTYPE)adType
{
    NSString *tag = nil;
    switch (adType) {
        case SDK_ADTYPE_VIDEO:
            tag = SDK_ADTAG_VIDEO;
            break;
        case SDK_ADTYPE_BANNER:
            tag = SDK_ADTAG_BANNER;
            break;
        case SDK_ADTYPE_ICON:
            tag = SDK_ADTAG_ICON;
            break;
        case SDK_ADTYPE_NATIVE:
            tag = SDK_ADTAG_NATIVE;
            break;
        case SDK_ADTYPE_GIFICON:
            tag = SDK_ADTAG_GIFICON;
            break;
        default:
            tag = SDK_ADTAG_INTERTITIAL;
            break;
    }
    return tag;
}

-(void)addOurAdData:(SDK_ADTYPE)adType data:(NSDictionary *)data maxCount:(int)max cache:(BOOL)cache
{
    NSString *adTag = [self getOurADTag:adType];
    NSMutableArray *arr = [self getOurAdDatasWithTag:adTag];
    if (arr.count < max) {
        NSString *value = [data objectForKey:adTag];
        if (adType == SDK_ADTYPE_GIFICON) {
            hasRunCacheGifIconAndVideos = NO;
        }
#ifdef DEBUG
//        //native, video, gificon 测试模拟数据
//        NSMutableDictionary *tmp = [NSMutableDictionary dictionaryWithDictionary:data];
//        if (adType == SDK_ADTYPE_NATIVE) {
//            value = @"https://c1.staticflickr.com/5/4687/39461625392_8158aba924.jpg";
//        } else if (adType == SDK_ADTYPE_GIFICON) {
//            NSArray<NSString *> *downs = @[@"http://farm2.staticflickr.com//1757//28793232858_343b1010aa_o.png",
//                                            @"http://farm2.staticflickr.com//1757//28793232858_343b1010aa_o.png",
//                                            @"http://farm2.staticflickr.com//1757//28793232858_343b1010aa_o.png",
//                                            @"http://farm2.staticflickr.com//1757//28793232858_343b1010aa_o.png"];
//            int i = arc4random() % downs.count;
//            NSString *down = [downs objectAtIndex:i];
//            [tmp setObject:down forKey:SDK_ADTAG_DOWN_ICON];
//
//            NSArray<NSString *> *videos = @[@"https://vt.media.tumblr.com/tumblr_p9ryiu8TO61v3ngze.mp4",
//                                            @"https://vt.media.tumblr.com/tumblr_p9ryq7c8tC1v3ngze.mp4",
//                                            @"https://vt.media.tumblr.com/tumblr_p9ryvpUe641v3ngze.mp4",
//                                            @"https://vt.media.tumblr.com/tumblr_p9rz3ygwtE1v3ngze.mp4"];
//            NSString *video = [videos objectAtIndex:i];
//            [tmp setObject:video forKey:SDK_ADTAG_VIDEO];
//
//            NSArray<NSString *> *icons = @[@"https://78.media.tumblr.com/7085bf1f70af4216c3e9f5366bae23bf/tumblr_p9n43yg3Za1v3ngzeo1_250.gif",
//                                           @"https://78.media.tumblr.com/7085bf1f70af4216c3e9f5366bae23bf/tumblr_p9n43yg3Za1v3ngzeo1_250.gif",
//                                           @"https://78.media.tumblr.com/7085bf1f70af4216c3e9f5366bae23bf/tumblr_p9n43yg3Za1v3ngzeo1_250.gif",
//                                           @"https://78.media.tumblr.com/7085bf1f70af4216c3e9f5366bae23bf/tumblr_p9n43yg3Za1v3ngzeo1_250.gif"];
//            value = [icons objectAtIndex:i];
//        }
//        [tmp setObject:value forKey:[self getOurADTag:adType]];
//        data = tmp;
#endif
        if (value && value.length > 4) {
            if(cache) {
                [UIImageView cacheURL:[NSURL URLWithString:value]];
            }
            [arr addObject:data];
        }
    }
}

-(nonnull NSMutableArray *)getOurAdDatas:(SDK_ADTYPE)adType
{
    NSString *adTag = [self getOurADTag:adType];
    return [self getOurAdDatasWithTag:adTag];
}

-(nonnull NSMutableArray *)getOurAdDatasWithTag:(NSString *)adTag
{
    if (!_pickAppData) {
        _pickAppData = [[NSMutableDictionary alloc] init];
    }
    id tmp = [_pickAppData objectForKey:adTag];
    if (tmp) {
        return tmp;
    } else {
        NSMutableArray *arr = [[NSMutableArray alloc] init];
        [_pickAppData setObject:arr forKey:adTag];
        return arr;
    }
}

-(nullable NSMutableArray *)getDeliciousAdDatas:(BOOL)withVideo
{
    [self cacheGifIcon:withVideo];
    NSArray *oarr = [self getOurAdDatas:SDK_ADTYPE_GIFICON];
    if (oarr) {
        NSMutableArray *arr = [[NSMutableArray alloc] init];
        if (withVideo) {
            for (NSDictionary *data in oarr) {
                NSString *gificon = [data objectForKey:SDK_ADTAG_GIFICON];
                NSString *down = [data objectForKey:SDK_ADTAG_DOWN_ICON];
                NSString *video = [data objectForKey:SDK_ADTAG_VIDEO];
                if ([[SDKNetworkHelper sharedHelper] hasURLDownloaded:gificon]
                    && [[SDKNetworkHelper sharedHelper] hasURLDownloaded:down]
                    && [[SDKNetworkHelper sharedHelper] hasURLDownloaded:video]) {
                    [arr addObject:data];
                }
            }
        } else {
            for (NSDictionary *data in oarr) {
                NSString *gificon = [data objectForKey:SDK_ADTAG_GIFICON];
                if ([[SDKNetworkHelper sharedHelper] hasURLDownloaded:gificon]) {
                    [arr addObject:data];
                }
                NSString *delicious_banner = [data objectForKey:SDK_ADTAG_DELICIOUS_BANNER];
                if (delicious_banner) {
                    //如果有配置 delicious_banner 就预下载
                    [[SDKNetworkHelper sharedHelper] downloadWithURL:delicious_banner progress:nil success:nil failure:nil];
                }
            }
        }
        return arr;
    }
    return nil;
}

-(BOOL)isDeliciousAdAvailable:(BOOL)withVideo
{
    NSArray *arr = [self getDeliciousAdDatas:withVideo];
    return arr && arr.count > 0;
}

-(UIView *)getDeliciousBannerView:(NSString *)configJson
{
    if (![self isDeliciousAdAvailable:NO]) {
        return nil;
    }
    NSDictionary *config = [SDKJSONHelper getJsonDataFrom:configJson bundle:nil];
    SDKDeliciousBannerView *view = [[SDKDeliciousBannerView alloc] initWithConfig:config];
    view.adDatas = [self getDeliciousAdDatas:NO];
    return view;
}

-(void)showDeliciousInterstitialAd:(NSString *_Nullable)configJson
{
    if (![self isDeliciousAdAvailable:YES]) {
        return;
    }
    if (_deliciousInterstitial && _deliciousInterstitial.isShowing) {
        return;
    }
    NSDictionary *config = [SDKJSONHelper getJsonDataFrom:configJson bundle:nil];
    _deliciousInterstitial = [[SDKDeliciousInterstitial alloc] initWithConfig:config];
    [_window makeKeyAndVisible];
    [_deliciousInterstitial showAd:_window.rootViewController];
}

-(void)showDeliciousBannerAd:(CGRect)frame configJson:(NSString *_Nullable)json
{
    if (![self isDeliciousAdAvailable:NO]) {
        return;
    }
//    [self closeDeliciousBannerAd];
    if (!_deliciousBannerView) {
        _deliciousBannerView = (SDKDeliciousBannerView *)[self getDeliciousBannerView:json];
    }
    if (_deliciousBannerView) {
        float w = _deliciousBannerView.frame.size.width;
        float h = _deliciousBannerView.frame.size.height;
        _deliciousBannerView.frame = CGRectMake(frame.origin.x, frame.origin.y, frame.size.width > 0 ? frame.size.width : w, frame.size.height > 0 ? frame.size.height : h);
        [_deliciousBannerView select:0];
        [_rootVC.view addSubview:_deliciousBannerView];
    }
}

-(void)closeDeliciousBannerAd
{
    if (_deliciousBannerView) {
        [_deliciousBannerView removeFromSuperview];
        _deliciousBannerView = nil;
    }
}

-(UIView *)getDeliciousIconView:(NSString *)configJson
{
    if (![self isDeliciousAdAvailable:NO]) {
        return nil;
    }
    NSDictionary *config = [SDKJSONHelper getJsonDataFrom:configJson bundle:nil];
    SDKDeliciousIconView *view = [[SDKDeliciousIconView alloc] initWithConfig:config];
    view.adDatas = [self getDeliciousAdDatas:NO];
    return view;
}

-(void)showDeliciousIconAd:(CGRect)frame configJson:(NSString *_Nullable)json
{
    if (![self isDeliciousAdAvailable:NO]) {
        return;
    }
//    [self closeDeliciousIconAd];
    if (!_deliciousIconView) {
        _deliciousIconView = (SDKDeliciousIconView *)[self getDeliciousIconView:json];
    }
    if (_deliciousIconView) {
        float w = _deliciousIconView.frame.size.width;
        float h = _deliciousIconView.frame.size.height;
        CGRect _frame = CGRectMake(frame.origin.x, frame.origin.y, frame.size.width > 0 ? frame.size.width : w, frame.size.height > 0 ? frame.size.height : h);
        [_deliciousIconView addTo:_rootVC.view frame:_frame];
        [[SDKFacade sharedInstance] logAdImpression:[SDKConstants getAdTypeName:SDK_ADTYPE_GIFICON] adPos:@"icon" platform:@"our"];
    }
}

-(void)closeDeliciousIconAd
{
    if (_deliciousIconView) {
        [_deliciousIconView removeSelf];
        _deliciousIconView = nil;
    }
}
/**
 * 获取可用的cover,banner,icon的应用数据
 */
-(nullable NSDictionary *)pickAvailableApp:(SDK_ADTYPE)adType
{
    NSMutableArray *arr = [self getOurAdDatas:adType];
    if([arr count] > 0) {
        NSMutableArray *tmp = [NSMutableArray arrayWithArray:arr];
        do {
            NSUInteger randomIndex = arc4random() % [tmp count];
            NSMutableDictionary *app = [tmp objectAtIndex:randomIndex];
            if(app) {
                NSString *checkValue = [app objectForKey:[self getOurADTag:adType]];
                if(checkValue && checkValue.length > 4) {
                    return app;
                }
            }
            [tmp removeObjectAtIndex:randomIndex];
        } while ([tmp count] > 0);
    }
    return nil;
}

-(nullable UIView *)getBannerView
{
    return [self getBannerView:SDK_ADTAG_DEFAULT];
}

-(nullable UIView *)getBannerView:(nonnull NSString *)tag
{
    SDKBannerModule *banner = [self getAdModule:SDK_ADTYPE_BANNER withTag:tag];
    if(banner) {
        return [banner getBannerView:YES];
    }
    return nil;
}

-(nullable UIView *)getBannerView:(nonnull NSString *)tag withFrame:(CGRect)frame
{
    SDKBannerModule *banner = (SDKBannerModule *)[self getAdModule:SDK_ADTYPE_BANNER withTag:tag];
    if(banner) {
        return [banner getBannerViewWithFrame:frame show:YES];
    }
    return nil;
}

-(void)closeBanner
{
    [[SDKGCDTimer sharedInstance] cancelTimerWithName:SHOW_BANNER_TIMER];
    self->_showBannerTag = nil;
    [SDKBannerModule closeBanner];
}

-(BOOL)isBannerShowing
{
    return [SDKBannerModule isBannerShowing];
}

-(BOOL)isBannerAvailable
{
    return [self isBannerAvailable:SDK_ADTAG_DEFAULT];
}

-(BOOL)isBannerAvailable:(nonnull NSString *)tag
{
    SDKBannerModule *banner = (SDKBannerModule *)[self getAdModule:SDK_ADTYPE_BANNER withTag:tag];
    if (banner) {
        return !![banner getAvailableAd];
    }
    return NO;
}

-(void)loadBannerAd:(NSString *)tag
{
    if([self isAdsEnabled]) {
        __weak SDKBannerModule *module = [self getAdModule:SDK_ADTYPE_BANNER withTag:tag];
        if(module) {
            [module loadAd];
        } else {
            if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
                NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
                [_adDelegate adFailed:tag adType:SDK_ADTYPE_BANNER forError:error];
            }
        }
    }
}

-(void)showBanner:(SDK_BANNER_POS)pos
{
    [self showBanner:SDK_ADTAG_DEFAULT atPos:pos];
}

-(void)showBanner:(nonnull NSString *)tag atPos:(SDK_BANNER_POS)pos
{
    if([self isAdsEnabled]) {
        [self logEvent:@"click_show_banner"];
        self->_showBannerTag = tag;
        self->_showBannerPos = pos;
        __weak SDKBannerModule *module = [self getAdModule:SDK_ADTYPE_BANNER withTag:tag];
        if(module) {
            [module showBannerAtPos:pos];
        }
        if (self->_showBannerTag && ![self isBannerShowing]) {
            [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:SHOW_BANNER_TIMER interval:10 queue:dispatch_get_main_queue() repeats:YES option:SDKGCDTimerOptionCancelPrevAction action:^{
                if (self->_showBannerTag && ![self isBannerShowing]) {
                    [self showBanner:self->_showBannerTag atPos:self->_showBannerPos];
                } else {
                    [[SDKGCDTimer sharedInstance] cancelTimerWithName:SHOW_BANNER_TIMER];
                }
            }];
        }
    }
}

-(void)showBanner:(NSString *)tag withFrame:(CGRect)frame
{
    if([self isAdsEnabled]) {
        [self logEvent:@"click_show_banner"];
        __weak SDKBannerModule *module = [self getAdModule:SDK_ADTYPE_BANNER withTag:tag];
        if(module) {
            [module showBannerWithFrame:frame];
        }
    }
}

-(BOOL)isInterstitialAvailable:(NSString *)tag
{
    SDKInterstitialModule *inter = [self getAdModule:SDK_ADTYPE_INTERTITIAL withTag:tag];
    return inter ? !![inter getAvailableAd] : NO;
}

-(void)loadInterstitialAd:(NSString *)tag
{
    if([self isAdsEnabled]) {
        __weak SDKInterstitialModule *module = [self getAdModule:SDK_ADTYPE_INTERTITIAL withTag:tag];
        if(module) {
            [module loadAd];
        } else {
            if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
                NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
                [_adDelegate adFailed:tag adType:SDK_ADTYPE_INTERTITIAL forError:error];
            }
        }
    }
}

-(void)showInterstitialAd:(NSString *)tag
{
    if([self isAdsEnabled]) {
        [self logEvent:@"click_show_interstitial"];
        __weak SDKInterstitialModule *module = [self getAdModule:SDK_ADTYPE_INTERTITIAL withTag:tag];
        if(module) {
            _currentShowAd = [module showAd:_rootVC];
        } else {
            if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
                NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
                [_adDelegate adFailed:tag adType:SDK_ADTYPE_INTERTITIAL forError:error];
            }
        }
    }
}

-(void)showInterstitialAd:(NSString *)tag waitSeconds:(int)seconds timeInterval:(double)interval
{
    if(seconds > 0) {
        __weak __typeof__(self) weakself = self;
        [SDKTimer startTimer:seconds interval:interval onComplete:^{
            [weakself showInterstitialAd:tag];
        } onUpdate:nil];
//        [SDKGCDTimer2 startTimer:seconds interval:interval isCountDown:YES onComplete:^{
//            [weakself showInterstitialAd:tag];
//        } onUpdate:nil];
    } else {
        [self showInterstitialAd:tag];
    }
}

-(void)showInterstitialAd:(NSString *)tag waitSeconds:(int)seconds
{
    [self showInterstitialAd:tag waitSeconds:seconds timeInterval:1.0];
}

-(BOOL)isVideoAvailable
{
    return [self isVideoAvailable:SDK_ADTAG_DEFAULT];
}

-(BOOL)isVideoAvailable:(nonnull NSString *)tag
{
    SDKVideoModule *video = [self getAdModule:SDK_ADTYPE_VIDEO withTag:tag];
    return video ? !![video getAvailableAd] : NO;
}

-(void)loadRewardVideo:(NSString *)tag
{
    __weak SDKVideoModule *module = [self getAdModule:SDK_ADTYPE_VIDEO withTag:tag];
    if(module) {
        [module loadAd];
    } else {
        if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
            NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
            [_adDelegate adFailed:tag adType:SDK_ADTYPE_VIDEO forError:error];
        }
    }
}

-(void)showRewardVideo:(int)rewardId
{
    [self showRewardVideoWithTag:SDK_ADTAG_DEFAULT rewardId:rewardId];
}

-(void)showRewardVideoWithTag:(NSString *)tag rewardId:(int)rewardId
{
    [self logEvent:@"click_show_rewarded"];
    __weak SDKVideoModule *module = [self getAdModule:SDK_ADTYPE_VIDEO withTag:tag];
    if(module) {
        _currentShowAd = [module showVideo:_rootVC rewardId:rewardId];
    } else {
        if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
            NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
            [_adDelegate adFailed:tag adType:SDK_ADTYPE_VIDEO forError:error];
        }
    }
}

-(BOOL)isNativeAvailable:(nonnull NSString *)tag
{
    SDKNativeModule *native = [self getAdModule:SDK_ADTYPE_NATIVE withTag:tag];
    return native ? !![native getAvailableAd] : NO;
}

-(nullable NSDictionary *)fetchNativeAd:(nonnull NSString *)tag
{
    SDKNativeModule *module = [self getAdModule:SDK_ADTYPE_NATIVE withTag:tag];
    if(module) {
        return [module fetchNativeAd];
    }
    return nil;
}

-(nullable NSString *)fetchNativeAdJson:(nonnull NSString *)tag
{
    NSDictionary *data = [self fetchNativeAd:tag];
    return [SDKJSONHelper toJSONString:data];
}

-(void)loadNativeAd:(NSString *)tag
{
    if([self isAdsEnabled]) {
        SDKNativeModule *module = [self getAdModule:SDK_ADTYPE_NATIVE withTag:tag];
        if(module) {
            [module loadAd];
        } else {
            if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
                NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
                [_adDelegate adFailed:tag adType:SDK_ADTYPE_NATIVE forError:error];
            }
        }
    }
}

-(nullable UIView *)getNativeAdView:(nonnull NSString *)tag configJson:(nonnull NSString *)json
{
    if([self isAdsEnabled]) {
        SDKNativeModule *module = [self getAdModule:SDK_ADTYPE_NATIVE withTag:tag];
        if(module) {
            return [module getNativeAdView:_rootVC json:json];
        } else {
            if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
                NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
                [_adDelegate adFailed:tag adType:SDK_ADTYPE_NATIVE forError:error];
            }
        }
    }
    return nil;
}

-(nullable UIView *)getNativeAdView:(nonnull NSString *)tag frame:(CGRect)frame configJson:(nonnull NSString *)json
{
    if([self isAdsEnabled]) {
        SDKNativeModule *module = [self getAdModule:SDK_ADTYPE_NATIVE withTag:tag];
        if(module) {
            return [module getNativeAdViewWithFrame:_rootVC frame:frame configJson:json];
        } else {
            if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
                NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
                [_adDelegate adFailed:tag adType:SDK_ADTYPE_NATIVE forError:error];
            }
        }
    }
    return nil;
}

-(void)showNativeAd:(nonnull NSString *)tag x:(float)x y:(float)y configJson:(nonnull NSString *)json
{
    if([self isAdsEnabled]) {
        __weak SDKNativeModule *module = [self getAdModule:SDK_ADTYPE_NATIVE withTag:tag];
        if(module) {
            [module showNativeAd:_rootVC x:x y:y configJson:json];
        } else {
            if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
                NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
                [_adDelegate adFailed:tag adType:SDK_ADTYPE_NATIVE forError:error];
            }
        }
    }
}

-(void)showNativeAd:(nonnull NSString *)tag withFrame:(CGRect)frame configJson:(nonnull NSString *)json
{
    if([self isAdsEnabled]) {
        __weak SDKNativeModule *module = [self getAdModule:SDK_ADTYPE_NATIVE withTag:tag];
        if(module) {
            [module showNativeAdWithFrame:self->_rootVC frame:frame configJson:json];
        } else {
            if(_adDelegate && [_adDelegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
                NSError *error = [[NSError alloc] initWithDomain:[NSString stringWithFormat:@"No config with %@", tag] code:0 userInfo:nil];
                [_adDelegate adFailed:tag adType:SDK_ADTYPE_NATIVE forError:error];
            }
        }
    }
}

-(void)closeNativeAd:(nonnull NSString *)tag
{
    __weak SDKNativeModule *module = [self getAdModule:SDK_ADTYPE_NATIVE withTag:tag];
    if(module) {
        dispatch_async(dispatch_get_main_queue(), ^{
            [module closeNativeAd];
        });
    }
}

-(void)_prepareAdData
{
    _lastShowBackHomeAdTime = 0;
    // init AdsEnabled
    NSNumber* enable = (NSNumber *)[[SDKCache cache] objectForKey: SDK_ADS_ENABLE];
    if(!_isOverNoAdTime && _noAdTime > 0) {
        _isOverNoAdTime = [SDKHelper currentTime] - [self firstOpenTime] >= _noAdTime;
    } else {
        _isOverNoAdTime = true;
    }
    [self saveAdsEnabled:(enable != nil ? [enable boolValue] : YES) && _isOverNoAdTime];
    
    if(!_iconAnimation) {
        _iconAnimation = [CAKeyframeAnimation animationWithKeyPath:@"transform"];
        _iconAnimation.duration = 1.5;// 动画时间
        NSMutableArray *values = [NSMutableArray array];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.1, 1.1, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(0.9, 0.9, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
        _iconAnimation.values = values;
        _iconAnimation.repeatCount = MAXFLOAT;
    }
    _currentIconView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, 80, 80)];
    _currentIconView.enableFade = YES;
    [_currentIconView setUserInteractionEnabled:YES];
    [_currentIconView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onIconClick:)]];
    NSMutableDictionary *promote = [_config objectForKey:@"promote"];
    if (promote && promote.count > 0) {
        NSString *more = [promote objectForKey:@"more"];
            if(more && more.length > 0) {
                NSArray *arr = [more componentsSeparatedByString:@","];
                if(arr && [arr count] > 0) {
                    NSMutableArray *_appIds = [NSMutableArray arrayWithArray:arr];
                    if(_appIds != nil && _appIds != NULL && [_appIds count] > 0) {
                        NSDictionary *_apps = [promote objectForKey:@"apps"];
                        if(_apps != nil && _apps != NULL) {
                            NSMutableDictionary *app = nil;
                            NSInteger count = _appIds.count;
                            NSMutableArray *removeObjects = [[NSMutableArray alloc] init];
                            for (int i=0; i<count; i++) {
                                NSString *appid = [_appIds objectAtIndex:i];
                                app = [_apps objectForKey:appid];
                                if(app) {
                                    if([appid isEqualToString:_appid] || [SDKHelper isAppInstalled:appid]) {
                                        [removeObjects addObject:appid];
                                    }
                                }
                            }
                            if(removeObjects.count > 0) {
                                [_appIds removeObjectsInArray:removeObjects];
                            }
                            for (int i=0; i<_appIds.count; i++) {
                                NSString *appid = [_appIds objectAtIndex:i];
                                NSDictionary *app = [_apps objectForKey:appid];
                                NSString *appstoreId = [app objectForKey:@"appstoreid"];
                                if(appstoreId) {
        //                            [[SDKHelper sharedHelper] cacheAppStoreId:appstoreId];
                                    [self addOurAdData:SDK_ADTYPE_INTERTITIAL data:app maxCount:3 cache:YES];
                                    [self addOurAdData:SDK_ADTYPE_BANNER data:app maxCount:3 cache:NO];
                                    [self addOurAdData:SDK_ADTYPE_ICON data:app maxCount:4 cache:YES];
                                    [self addOurAdData:SDK_ADTYPE_NATIVE data:app maxCount:4 cache:NO];
                                    [self addOurAdData:SDK_ADTYPE_GIFICON data:app maxCount:8 cache:NO];
                                }
                            }
                        }
                    }
                }
            }
    }
}

-(void)cacheGifIcon:(BOOL)withVideo
{
    NSArray *arr = [self getOurAdDatas:SDK_ADTYPE_GIFICON];
    if(arr.count <= 0 || hasRunCacheGifIconAndVideos)
        return;
    hasRunCacheGifIconAndVideos = YES;
    DLog(@"[sdk] start cacheGifIconAndVideos");
    //cache videos and down icons
    if (withVideo) {
        for (NSDictionary *data in arr) {
            NSString *video = [data objectForKey:SDK_ADTAG_VIDEO];
            if (video) {
                if ([[video pathExtension] isEqualToString:@"mp4"]) {
                    [[SDKNetworkHelper sharedHelper] downloadWithURL:video progress:^(NSProgress *progress){
                        if (@available(iOS 11.0, *)) {
                            DLog(@"[download] %@ %@", video, progress.localizedDescription);
                        } else {
                            // Fallback on earlier versions
                        }
                    } success:^(NSString *filePath) {
                        DLog(@"[download] %@ complete!", filePath);
                    } failure:^(NSError *error) {
                        DLog(@"[download] %@ error: %@", video, [error localizedDescription]);
                    }];
                }
            }
            NSString *down = [data objectForKey:SDK_ADTAG_DOWN_ICON];
            if (down) {
                [[SDKNetworkHelper sharedHelper] downloadWithURL:down progress:^(NSProgress *progress){
                    if (@available(iOS 11.0, *)) {
                        DLog(@"[download] %@ %@", down, progress.localizedDescription);
                    } else {
                        // Fallback on earlier versions
                    }
                } success:^(NSString *filePath) {
                    DLog(@"[download] %@ complete!", filePath);
                } failure:^(NSError *error) {
                    DLog(@"[download] %@ error: %@", down, [error localizedDescription]);
                }];
            }
        }
    } else {
        for (NSDictionary *data in arr) {
            NSString *gificon = [data objectForKey:SDK_ADTAG_GIFICON];
            if (gificon) {
                [[SDKNetworkHelper sharedHelper] downloadWithURL:gificon progress:^(NSProgress *progress){
                    if (@available(iOS 11.0, *)) {
                        DLog(@"[download] %@ %@", gificon, progress.localizedDescription);
                    } else {
                        // Fallback on earlier versions
                    }
                } success:^(NSString *filePath) {
                    DLog(@"[download] %@ complete!", filePath);
                } failure:^(NSError *error) {
                    DLog(@"[download] %@ error: %@", gificon, [error localizedDescription]);
                }];
            }
        }
    }
}

-(void)closeIconAd
{
    if(_iconTimer) {
        [_iconTimer stop];
    }
    if(_currentIconView != nil && _currentIconView != NULL) {
        [_currentIconView removeFromSuperview];
    }
}

-(void)showPopupIconAds
{
    NSArray *_iconAds = [self getOurAdDatas:SDK_ADTYPE_ICON];
    [SDKPopupIconAdView show:_iconAds];
}

-(void)hidePopupIconAds
{
    [SDKPopupIconAdView hide];
}

-(NSArray *)getPopupIconAdsData
{
    return [self getOurAdDatas:SDK_ADTYPE_ICON];
}

-(void)showIconAd:(float)width xPercent:(float)xPercent yPercent:(float)yPercent
{
    if([self changeIcon:[self pickAvailableApp:SDK_ADTYPE_ICON]]) {
        float x = Screen_width * xPercent;
        float y = Screen_height * yPercent;
        x = x < 0 ? 0 : (x > Screen_width - width) ? Screen_width - width : x;
        y = y < 0 ? 0 : (y > Screen_height - width) ? Screen_height - width : y;
        //        _currentIconView.backgroundColor = [UIColor blueColor];
        [_currentIconView setFrame:CGRectMake(x, y, width, width)];
        UIViewController *container = _rootVC;
        [container.view addSubview:_currentIconView];
        [[SDKFacade sharedInstance] logAdImpression:[SDKConstants getAdTypeName:SDK_ADTYPE_ICON] adPos:@"icon" platform:@"our"];
        if(_currentIconView && [_currentIconView superview]) {
            if(_iconTimer) {
                [_iconTimer stop];
            }
            _iconTimer = [SDKTimer startTimer:0 interval:ICON_CHANGE_INTERVAL onComplete:nil onUpdate:^(void) {
                if(self->_currentIconView.superview) {
                    NSDictionary *app = [self pickAvailableApp:SDK_ADTYPE_ICON];
                    [self changeIcon:app];
                } else {
                    [self closeIconAd];
                }
            }];
        }
    }
}

-(void)onIconClick:(UITapGestureRecognizer *)gestureRecognizer
{
    [SDKFacade trackAndOpenStore:_iconAppStoreId type:SDK_ADTYPE_ICON];
    [[SDKFacade sharedInstance] logAdClick:[SDKConstants getAdTypeName:SDK_ADTYPE_ICON] adPos:@"icon" platform:@"our"];
}

-(BOOL)changeIcon:(NSDictionary *)app
{
    if(app) {
        NSURL *url = [NSURL URLWithString:[app objectForKey:@"icon"]];
        [_currentIconView setImageWithURL:url];
        
        _currentIconView.layer.cornerRadius = 15;
        _currentIconView.layer.masksToBounds = true;
        [_currentIconView.layer addAnimation:_iconAnimation forKey:nil];
        
        _iconAppStoreId = [app objectForKey:@"appstoreid"];
        [SDKFacade trackCrossPromoteImpression:_iconAppStoreId type:SDK_ADTYPE_ICON];
        return true;
    }
    return false;
}

#pragma mark -
#pragma mark Sdk delegate
- (void)onInitialized
{
    if (_sdkDelegate) {
        [_sdkDelegate onInitialized];
    }
}

- (void)onReceiveServerExtra:(nullable NSString *)data
{
    if (_sdkDelegate) {
        [_sdkDelegate onReceiveServerExtra:data];
    }
}

-(void)onReceiveNotificationData:(NSString *)data
{
    if (_sdkDelegate) {
        [_sdkDelegate onReceiveNotificationData:data];
    }
}

- (void)onDeepLinkReceived:(nullable NSURL *)url {
    if (url) {
        _deepLink = [url absoluteString];
    }
    if (_sdkDelegate) {
        [_sdkDelegate onDeepLinkReceived:url];
    }
}

- (void)onThirdLoginAnonymous {
    if (_sdkDelegate) {
        [_sdkDelegate onThirdLoginAnonymous];
    }
}

- (void)onThirdLoginCancel:(NSString * _Nonnull)platform {
    if (_sdkDelegate) {
        [_sdkDelegate onThirdLoginCancel:platform];
    }
}

- (void)onThirdLoginClientNotInstalled:(NSString * _Nonnull)platform {
    if (_sdkDelegate) {
        [_sdkDelegate onThirdLoginClientNotInstalled:platform];
    }
}

- (void)onThirdLoginError:(NSString * _Nonnull)errorMsg platformType:(NSString * _Nonnull)platform {
    if (_sdkDelegate) {
        [_sdkDelegate onThirdLoginError:errorMsg platformType:platform];
    }
}

- (void)onThirdLoginPrivacyAccept:(BOOL)isAccept {
    if (_sdkDelegate) {
        [_sdkDelegate onThirdLoginPrivacyAccept:isAccept];
    }
}

- (void)onThirdLoginReceiveIdCardVerifiedResult:(int)age {
    if (_sdkDelegate) {
        [_sdkDelegate onThirdLoginReceiveIdCardVerifiedResult:age];
    }
}

- (void)onAttAccepted:(BOOL)isAccept {
    if(_sdkDelegate) {
        [_sdkDelegate onAttAccepted:isAccept];
    }
}

- (void)unreadMessageCount:(int)count{
    if(_sdkDelegate) {
        [_sdkDelegate unreadMessageCount:count];
    }
}

-(void)onNotificationOpen:(BOOL)isOpen {
    if(_sdkDelegate) {
        [_sdkDelegate onNotificationOpen:isOpen];
    }
}

- (void)onThirdLoginSuccess:(NSString * _Nonnull)openId platformType:(NSString * _Nonnull)platform {
    if (_sdkDelegate) {
        [_sdkDelegate onThirdLoginSuccess:openId platformType:platform];
    }
}

#pragma mark -
#pragma mark App delegate
- (BOOL)application:(nullable UIApplication *)application didFinishLaunchingWithOptions:(nullable NSDictionary *)launchOptions
{
    DLog(@"%@", @"application didFinishLaunchingWithOptions");
#ifdef APPSFLYER
    if (@available(iOS 14, *)) {
        [[AppsFlyerLib shared] waitForATTUserAuthorizationWithTimeoutInterval:120];
    }
//    [AppsFlyerAdRevenue start];
#endif
//    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(didBecomeActive) name:UIApplicationDidBecomeActiveNotification object:nil];
//    [self observeAuthticationState];
    //_originWindow = [UIApplication sharedApplication].keyWindow;
    //_rootVC = _originWindow.rootViewController;
    
    /**显示文字**/
    EasyTextGlobalConfig *config = [EasyTextGlobalConfig shared];
    config.bgColor = [UIColor whiteColor];
    config.titleColor = [UIColor blackColor];
    [[SDKNetworkHelper sharedHelper] checkReachability:^(SDKNetworkReachabilityStatus status) {
        [self onNetworkChanged:(int)status];
    }];
    
    _startTime = [SDKHelper currentTime];

    NSNumber *tmp = (NSNumber *)[[SDKCache cache] objectForKey:SDK_MM_NUMBER_CONFUSE];
    [self mmEnableNumberConfuse:(tmp ? tmp.boolValue : false)];
    
    if([[NSUserDefaults standardUserDefaults] objectForKey:SDK_FIRST_OPEN] == nil) {
        [[NSUserDefaults standardUserDefaults] setBool:YES forKey:SDK_FIRST_OPEN];
        _isFirstOpen = YES;
    } else {
        _isFirstOpen = NO;
    }
    
    _opCount = [[NSUserDefaults standardUserDefaults] integerForKey:APP_OPEN] + 1;
    [[NSUserDefaults standardUserDefaults] setInteger:_opCount forKey:APP_OPEN];
    
    [[UIApplication sharedApplication] setApplicationIconBadgeNumber:0];
    [self _initConfig];
    if (_config) {
        @try {
            CRASH_EMAIL_ADDR = [_config objectForKey:@"crash"];
            if(CRASH_EMAIL_ADDR && [CRASH_EMAIL_ADDR containsString:@"@"]) {
                //异常捕获
                NSSetUncaughtExceptionHandler(&UncaughtExceptionHandler);
            }
            if (@available(iOS 10.0, *)) {
                UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
                //设置代理
                center.delegate = self;
            }
            if (@available(iOS 10.3, *)) {
                [SKStoreReviewController initialize];
            } else {
//                [[SDKHelper sharedHelper] cacheAppStoreId:[self.config objectForKey:@"appstoreid"]];
            }
            [self _initBefore];
            [self _setUserProps];
        } @catch (NSException *exception) {
            DLog(@"[sdk] error : %@", exception.description);
#if DEBUG
            [EasyTextView showErrorText:exception.description];
#endif
        } @finally {
        }
        @try {
            [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:@"sdk_delay_app_open" interval:3 queue:dispatch_get_main_queue() repeats:NO option:SDKGCDTimerOptionCancelPrevAction action:^{
                if (!self->_hasSendFirstOpen) {
                    self->_hasSendFirstOpen = YES;
                    id tmp = [[SDKCache cache] objectForKey:PURCHASED_REVENUE];
                    NSNumber *revenue = [NSNumber numberWithInt:(tmp ? [(NSNumber *)tmp intValue]: 0)];
                    [self logEvent:@"sdk_app_open" withData:@{@"revenue": revenue}];
                    if (self->_isFirstOpen) {
                        [self logEvent:@"sdk_first_open"];
                        // iAd归因统计
                        // Check for iOS 10 attribution implementation
                        if ([[ADClient sharedClient] respondsToSelector:@selector(requestAttributionDetailsWithBlock:)]) {
                            DLog(@"iOS 10 call exists");
                            [[ADClient sharedClient] requestAttributionDetailsWithBlock:^(NSDictionary *attributionDetails, NSError *error) {
                                // Look inside of the returned dictionary for all attribution details
                                if (attributionDetails) {
                                    DLog(@"iAd Attribution Dictionary: %@", attributionDetails);
                                    for (NSString *key in attributionDetails) {
                                        id tmp = attributionDetails[key];
                                        if ([tmp isKindOfClass:NSDictionary.class]) {
                                            if ([tmp objectForKey:@"iad-attribution"] != nil) {
                                                [self logEvent:@"iad_referrer" withData:tmp];
                                                
                                                NSString *iad_campaign_id = [tmp objectForKey:@"iad-campaign-id"];
                                                [self setUserPropertyString:iad_campaign_id forName:@"iad-campaign-id"];
                                                
                                                NSString *iad_adgroup_id = [tmp objectForKey:@"iad-adgroup-id"];
                                                [self setUserPropertyString:iad_adgroup_id forName:@"iad-adgroup-id"];
                                                
                                                NSString *iad_keyword_id = [tmp objectForKey:@"iad-keyword-id"];
                                                [self setUserPropertyString:iad_keyword_id forName:@"iad-keyword-id"];
                                                
                                                NSString *iad_creativeset_id = [tmp objectForKey:@"iad-creativeset-id"];
                                                [self setUserPropertyString:iad_creativeset_id forName:@"iad-creativeset-id"];
                                                
                                                break;
                                            }
                                        }
                                    }
                                }
                            }];
                        }
                    }
                    NSArray *arr = [[SDKFacade sharedInstance] getTrackAdCountArray:@"op"];
                    if (arr && arr.count > 0) {
                        for (int i=0; i<arr.count; i++) {
                            long value = [(NSNumber *)[arr objectAtIndex:i] longValue];
                            if (value == 5 && self->_opCount % 5 == 0) {
                                [[SDKFacade sharedInstance] logEvent:@"op5"];
                                if (self->_opCount == 5) {
                                    break;
                                }
                            }
                            if (self->_opCount == value) {
                                [[SDKFacade sharedInstance] logEvent:[NSString stringWithFormat:@"op%ld", self->_opCount]];
                                break;
                            }
                        }
                    }
                    [[NSUserDefaults standardUserDefaults] synchronize];
                }
            }];
//            [self _initPayment];
//            [self _initGameCenter];
            
#ifdef FACEBOOK
            [[FBSDKApplicationDelegate sharedInstance] application:application didFinishLaunchingWithOptions:launchOptions];
            [FBSDKAppLinkUtility fetchDeferredAppLink:^(NSURL *url, NSError *error) {
                if (error) {
                    DLog(@"[analyse] FacebookAnalytics error while fetching deferred app link %@", error);
                }
                if (url) {
                    NSString *_url = [url.absoluteString urlDecodeString];
                    NSRange range = [_url rangeOfString:@"referrer="];
                    if (range.location != NSNotFound) {
                        _url = [_url substringFromIndex:(range.location+range.length)];
                    }
                    if (_url) {
                        NSArray *arr = [_url componentsSeparatedByString:@"&"];
                        NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
                        for (NSString *str in arr) {
                            NSArray *tmp = [str componentsSeparatedByString:@"="];
                            if (tmp.count > 1) {
                                NSString *key = [tmp objectAtIndex:0];
                                NSString *value = [tmp objectAtIndex:1];
                                if ([key containsString:@"utm_"]) {
                                    [self setUserPropertyString:value forName:key];
                                    [data setObject:value forKey:key];
                                }
                            }
                        }
                        [self logEvent:@"fb_referrer" withData:data];
                    }
                }
            }];
#endif
        } @catch (NSException *exception) {
            DLog(@"[sdk] error : %@", exception.description);
#if DEBUG
            [EasyTextView showErrorText:exception.description];
#endif
        } @finally {
            _hasInit = YES;
        }
    }
    return YES;
}

- (void)didBecomeActive
{
    if (_enableATT) {
        [SDKFacade requestIDFA];
    } else {
        [self _checkAndTrackIDFT];
    }
}

-(void)onNetworkChanged:(int)status
{
    if (status > 0) {
        if (_hasInitAfter) {
            [self _syncConfig];
            if (_paymentData) {
                [self recheckFailedPayments];
            }
            [self reSendFailedConsumedPayments];
        }
        if (_gamecenterEnable) {
            _gamecenterEnable = false;
            [[SDKGCManager sharedManager] checkGameCenterAvailable:false];
        }
    }
    if (_sdkDelegate && [_sdkDelegate respondsToSelector:@selector(onNetworkChanged:)]) {
        [_sdkDelegate onNetworkChanged:status];
    }
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    long now = [SDKHelper currentTime];
    long delta = now - _startTime / 1000;
    if (delta > 1800) {
        [self logEvent:@"ActiveOver30Minutes"];
    } else if (delta > 1200) {
        [self logEvent:@"ActiveOver20Minutes"];
    } else if (delta > 600) {
        [self logEvent:@"ActiveOver10Minutes"];
    } else if (delta > 300) {
        [self logEvent:@"ActiveOver5Minutes"];
    }
    DLog(@"[sdk] application will terminate, active time = %ld", delta);
}

-(void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
//    if(_pushes && _pushes.count > 0) {
//        for (int i=0,n=(int)_pushes.count; i<n; i++) {
//            SDKPush *push = [_pushes objectAtIndex:i];
//            [push didRegisterForRemoteNotificationsWithDeviceToken:deviceToken];
//        }
//    }
#ifdef APPSFLYER
    [[AppsFlyerLib shared] registerUninstall:deviceToken];
#endif
}

- (void)application:(nullable UIApplication *)application didReceiveRemoteNotification:(nullable NSDictionary *)userInfo
{
//    if(_pushes && _pushes.count > 0) {
//        for (int i=0,n=(int)_pushes.count; i<n; i++) {
//            SDKPush *push = [_pushes objectAtIndex:i];
//            [push didReceiveRemoteNotification:userInfo];
//        }
//    }
#ifdef APPSFLYER
    [[AppsFlyerLib shared] handlePushNotification:userInfo];
#endif
}

- (void)application:(nullable UIApplication *)application didReceiveLocalNotification:(nullable UILocalNotification *)notification
{
//    if (notification && notification.userInfo) {
//        NSString *data = [SDKJSONHelper toJSONString:notification.userInfo];
//        [[SDKCache cache] setObject:notification.userInfo forKey:SDK_LOCALNOTIFICATION_USERINFO];
//    }
}

- (void)userNotificationCenter:(UNUserNotificationCenter *)center didReceiveNotificationResponse:(UNNotificationResponse *)response withCompletionHandler:(void(^)(void))completionHandler API_AVAILABLE(ios(10.0))
{
//    // 如果在前台直接返回
//    if (response) {
//        [self logEvent:@"push_open"];
//        NSDictionary *userInfo = response.notification.request.content.userInfo;
//        if (userInfo) {
//            NSString *data = [SDKJSONHelper toJSONString:userInfo];
//            [self onReceiveNotificationData:data];
//            [[SDKCache cache] setObject:userInfo forKey:SDK_LOCALNOTIFICATION_USERINFO];
//            if(_pushes && _pushes.count > 0) {
//                for (int i=0,n=(int)_pushes.count; i<n; i++) {
//                    SDKPush *push = [_pushes objectAtIndex:i];
//                    [push didReceiveRemoteNotification:userInfo];
//                }
//            }
//        }
//    }
//    if (completionHandler)
//        completionHandler();
}

- (void)application:(nullable UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(nullable NSError *)error
{
//    if(_pushes && _pushes.count > 0) {
//        for (int i=0,n=(int)_pushes.count; i<n; i++) {
//            SDKPush *push = [_pushes objectAtIndex:i];
//            [push didFailToRegisterForRemoteNotificationsWithError:error];
//        }
//    }
}

- (void)applicationDidBecomeActive:(nullable UIApplication *)application
{
    if(_hasInit) {
//        if(_pushes && _pushes.count > 0) {
//            for (int i=0,n=(int)_pushes.count; i<n; i++) {
//                SDKPush *push = [_pushes objectAtIndex:i];
//                [push applicationDidBecomeActive:application];
//            }
//        }
#ifdef APPSFLYER
        [[AppsFlyerLib shared] startWithCompletionHandler:^(NSDictionary<NSString *,id> * _Nullable dictionary, NSError * _Nullable error) {
            if (error) {
                DLog(@"Appsflyer start err: %@", error.debugDescription);
            }
        }];
#endif
//#ifdef FACEBOOK
        [[FBSDKAppEvents shared] activateApp];
//#endif
        [self reSendFailedConsumedPayments];
    }
    
    if (_currentShowAd)
    {
        [_currentShowAd didBecomeActive];
    }
    
    if (_hasInitAfter && _canShowBackHomeAd && !_justShowFullAd && ![SDKHelper isEmptyString:_backHomeAdTag])
    {
        long now = [SDKHelper currentTime];
        if(now - _lastShowBackHomeAdTime > _backHomeAdInterval) {
            _canShowBackHomeAd = false;
            [self showInterstitialAd:_backHomeAdTag];
        }
    }
}

- (void)applicationDidEnterBackground:(nullable UIApplication *)application
{
    if(_hasInit) {
//        if(_pushes && _pushes.count > 0) {
//            for (int i=0,n=(int)_pushes.count; i<n; i++) {
//                SDKPush *push = [_pushes objectAtIndex:i];
//                [push applicationDidEnterBackground:application];
//                _lastShowBackHomeAdTime = [SDKHelper currentTime];
//            }
//        }
    }
    if (_hasInitAfter) {
        _canShowBackHomeAd = ![SDKHelper isEmptyString:_backHomeAdTag];
    }
}
-(BOOL)application:(UIApplication *)application continueUserActivity:(NSUserActivity *)userActivity restorationHandler:(void (^)(NSArray<id<UIUserActivityRestoring>> * _Nullable))restorationHandler
{
    BOOL handle = YES;
    [self onDeepLinkReceived:userActivity.webpageURL];
#ifdef ThirdLogin
    [_thirdLogin handleOpenUniversalLink:userActivity];
#endif
#ifdef APPSFLYER
    handle = [[AppsFlyerLib shared] continueUserActivity:userActivity restorationHandler:restorationHandler];
#endif
#ifdef FACEBOOK
    handle = [[FBSDKApplicationDelegate sharedInstance] application:application continueUserActivity:userActivity];
#endif
    return handle;
}
- (BOOL)application:(nullable UIApplication *)application openURL:(nullable NSURL *)url sourceApplication:(nullable NSString *)sourceApplication annotation:(nullable id)annotation
{
    BOOL handle = YES;
    [self onDeepLinkReceived:url];
#ifdef ThirdLogin
    [_thirdLogin handleOpenUrl:url];
#endif
#ifdef APPSFLYER
    [[AppsFlyerLib shared] handleOpenURL:url sourceApplication:sourceApplication withAnnotation:annotation];
#endif
#ifdef FACEBOOK
    handle = [[FBSDKApplicationDelegate sharedInstance] application:application openURL:url sourceApplication:sourceApplication annotation:annotation];
#endif
    return handle;
}

-(BOOL)application:(UIApplication *)application openURL:(NSURL *)url options:(NSDictionary *)options
{
    BOOL handle = NO;
    [self onDeepLinkReceived:url];
#ifdef ThirdLogin
    [_thirdLogin handleOpenUrl:url];
#endif
#ifdef APPSFLYER
    [[AppsFlyerLib shared] handleOpenUrl:url options:options];
#endif
#ifdef FACEBOOK
    handle = [[FBSDKApplicationDelegate sharedInstance] application:application openURL:url options:options];
#endif
    return handle;
}
#pragma mark -
#pragma mark Apple Login
-(NSString *)getSignedAppleUID
{
    NSDictionary *dict = [self getSignedAppleInfo];
    return dict ? [dict objectForKey:@"uid"] : @"";
}

-(NSDictionary *)getSignedAppleInfo
{
    return (NSDictionary *)[[SDKCache cache] objectForKey:SDK_APPLE_ACCOUNT];
}

-(BOOL)showSignInWithApple:(CGRect)frame useBlackBackground:(BOOL)useBlack
{
    if (@available(iOS 13.0, *)) {
        if (_signInAppleBtn) {
            [_signInAppleBtn removeFromSuperview];
        }
        // Sign In With Apple Button
        ASAuthorizationAppleIDButton *appleIDButton = [[ASAuthorizationAppleIDButton alloc] initWithAuthorizationButtonType: ASAuthorizationAppleIDButtonTypeSignIn authorizationButtonStyle:(useBlack ? ASAuthorizationAppleIDButtonStyleBlack : ASAuthorizationAppleIDButtonStyleWhiteOutline)];
        appleIDButton.frame = frame;
        appleIDButton.cornerRadius = CGRectGetHeight(appleIDButton.frame) * 0.25;
        [self.rootVC.view addSubview:appleIDButton];
        [appleIDButton addTarget:self action:@selector(handleAuthrization:) forControlEvents:UIControlEventTouchUpInside];
        _signInAppleBtn = appleIDButton;
        return true;
    }
    return false;
}

-(void)hideSignInWithApple
{
    if (_signInAppleBtn) {
        [_signInAppleBtn removeFromSuperview];
    }
}

- (NSString *)randomNonce:(NSInteger)length {
    NSAssert(length > 0, @"Expected nonce to have positive length");
    NSString *characterSet = @"0123456789ABCDEFGHIJKLMNOPQRSTUVXYZabcdefghijklmnopqrstuvwxyz-._";
    NSMutableString *result = [NSMutableString string];
    NSInteger remainingLength = length;
    
    while (remainingLength > 0) {
        NSMutableArray *randoms = [NSMutableArray arrayWithCapacity:16];
        for (NSInteger i = 0; i < 16; i++) {
            uint8_t random = 0;
            int errorCode = SecRandomCopyBytes(kSecRandomDefault, 1, &random);
            NSAssert(errorCode == errSecSuccess, @"Unable to generate nonce: OSStatus %i", errorCode);
            
            [randoms addObject:@(random)];
        }
        
        for (NSNumber *random in randoms) {
            if (remainingLength == 0) {
                break;
            }
            
            if (random.unsignedIntValue < characterSet.length) {
                unichar character = [characterSet characterAtIndex:random.unsignedIntValue];
                [result appendFormat:@"%C", character];
                remainingLength--;
            }
        }
    }
    
    return result;
}

- (NSString *)stringBySha256HashingString:(NSString *)input
{
    const char *string = [input UTF8String];
    unsigned char result[CC_SHA256_DIGEST_LENGTH];
    CC_SHA256(string, (CC_LONG)strlen(string), result);
    
    NSMutableString *hashed = [NSMutableString stringWithCapacity:CC_SHA256_DIGEST_LENGTH * 2];
    for (NSInteger i = 0; i < CC_SHA256_DIGEST_LENGTH; i++) {
        [hashed appendFormat:@"%02x", result[i]];
    }
    return hashed;
}

-(void)performAppleIdSignIn
{
    [[SDKCache cache] setObject:@"true" forKey:@"newLoginUser"];
    if (@available(iOS 13.0, *)) {
        NSString *nonce = [self randomNonce:32];
        self.currentNonce = nonce;
        ASAuthorizationAppleIDProvider *appleIDProvider = [[ASAuthorizationAppleIDProvider alloc] init];
        ASAuthorizationAppleIDRequest *request = [appleIDProvider createRequest];
        request.requestedScopes = @[ASAuthorizationScopeFullName, ASAuthorizationScopeEmail];
        request.nonce = [self stringBySha256HashingString:nonce];
        
        ASAuthorizationController *authorizationController = [[ASAuthorizationController alloc] initWithAuthorizationRequests:@[request]];
        authorizationController.delegate = self;
        authorizationController.presentationContextProvider = self;
        [authorizationController performRequests];
        
        [SDKHelper showLoading];
        DLog(@"[signInWithApple] send request");
    }
}

#if defined(__IPHONE_13_0) && __IPHONE_OS_VERSION_MAX_ALLOWED >= __IPHONE_13_0
//! 处理授权
- (void)handleAuthrization:(UIButton *)sender {
    if (@available(iOS 13.0, *)) {
        // A mechanism for generating requests to authenticate users based on their Apple ID.
        // 基于用户的Apple ID授权用户，生成用户授权请求的一种机制
        ASAuthorizationAppleIDProvider *appleIDProvider = [ASAuthorizationAppleIDProvider new];
        // Creates a new Apple ID authorization request.
        // 创建新的AppleID 授权请求
        ASAuthorizationAppleIDRequest *request = appleIDProvider.createRequest;
        // The contact information to be requested from the user during authentication.
        // 在用户授权期间请求的联系信息
        request.requestedScopes = @[ASAuthorizationScopeFullName, ASAuthorizationScopeEmail];
        // A controller that manages authorization requests created by a provider.
        // 由ASAuthorizationAppleIDProvider创建的授权请求 管理授权请求的控制器
        ASAuthorizationController *controller = [[ASAuthorizationController alloc] initWithAuthorizationRequests:@[request]];
        // A delegate that the authorization controller informs about the success or failure of an authorization attempt.
        // 设置授权控制器通知授权请求的成功与失败的代理
        controller.delegate = self;
        // A delegate that provides a display context in which the system can present an authorization interface to the user.
        // 设置提供 展示上下文的代理，在这个上下文中 系统可以展示授权界面给用户
        controller.presentationContextProvider = self;
        // starts the authorization flows named during controller initialization.
        // 在控制器初始化期间启动授权流
        [controller performRequests];
        [SDKHelper showLoading];
        DLog(@"[signInWithApple] send request");
    }
}

//! 授权成功地回调
- (void)authorizationController:(ASAuthorizationController *)controller didCompleteWithAuthorization:(ASAuthorization *)authorization  API_AVAILABLE(ios(13.0)){
    [SDKHelper hideLoading];
    NSLog(@"authorization.credential：%@", authorization.credential);
    NSMutableDictionary *info = [[NSMutableDictionary alloc] init];
    NSString *user = @"";
    if ([authorization.credential isKindOfClass:[ASAuthorizationAppleIDCredential class]]) {
        // 用户登录使用ASAuthorizationAppleIDCredential
        ASAuthorizationAppleIDCredential *appleIDCredential = (ASAuthorizationAppleIDCredential *)authorization.credential;
        user = appleIDCredential.user;
        [info setObject:user?:@"" forKey:@"uid"];
        NSString *familyName = appleIDCredential.fullName.familyName;
#ifdef FIREBASE_PLUS
        self.appleFullName = appleIDCredential.fullName;
#endif
        [info setObject:familyName?:@"" forKey:@"familyName"];
        NSString *givenName = appleIDCredential.fullName.givenName;
        [info setObject:givenName?:@"" forKey:@"givenName"];
        NSString *email = appleIDCredential.email;
        [info setObject:email?:@"" forKey:@"email"];
        NSString* idToken = [[NSString alloc] initWithData:appleIDCredential.identityToken encoding:NSUTF8StringEncoding];
        [info setObject:idToken?:@"" forKey:@"idToken"];
        [info setObject:self.currentNonce?:@"" forKey:@"nonce"];
        NSString* authCode = [[NSString alloc] initWithData:appleIDCredential.authorizationCode encoding:NSUTF8StringEncoding];
        [info setObject:authCode?:@"" forKey:@"authCode"];
    } else if ([authorization.credential isKindOfClass:[ASPasswordCredential class]]) {
        // 用户登录使用现有的密码凭证
        ASPasswordCredential *passwordCredential = (ASPasswordCredential *)authorization.credential;
        // 密码凭证对象的用户标识 用户的唯一标识
        user = passwordCredential.user;
        [info setObject:user?:@"" forKey:@"uid"];
        // 密码凭证对象的密码
        NSString *password = passwordCredential.password;
        [info setObject:password?:@"" forKey:@"password"];
    }
    if (info.count > 0) {
        [[SDKCache cache] setObject:info forKey:SDK_APPLE_ACCOUNT];
        DLog(@"[signInWithApple] 登录成功：%@", info);
        if (_snsDelegate && [_snsDelegate respondsToSelector:@selector(signInAppleSuccess:)]) {
            dispatch_async(dispatch_get_main_queue(), ^{
                [self->_snsDelegate signInAppleSuccess:user];
            });
        }
    } else {
        NSString *errorMsg = @"授权信息均不符";
        DLog(@"[signInWithApple] 错误信息：%@", errorMsg);
        if (_snsDelegate && [_snsDelegate respondsToSelector:@selector(signInAppleFailure:)]) {
            dispatch_async(dispatch_get_main_queue(), ^{
                [self->_snsDelegate signInAppleFailure:errorMsg];
            });
        }
    }
}

//! 授权失败的回调
- (void)authorizationController:(ASAuthorizationController *)controller didCompleteWithError:(NSError *)error API_AVAILABLE(ios(13.0)){
    [SDKHelper hideLoading];
    NSString *errorMsg = nil;
    switch (error.code) {
        case ASAuthorizationErrorCanceled:
            errorMsg = @"用户取消了授权请求";
            break;
        case ASAuthorizationErrorFailed:
            errorMsg = @"授权请求失败";
            break;
        case ASAuthorizationErrorInvalidResponse:
            errorMsg = @"授权请求响应无效";
            break;
        case ASAuthorizationErrorNotHandled:
            errorMsg = @"未能处理授权请求";
            break;
        case ASAuthorizationErrorUnknown:
            errorMsg = @"授权请求失败未知原因";
            break;
    }
    errorMsg = errorMsg ? errorMsg : error.localizedDescription;
    if (errorMsg) {
        DLog(@"[signInWithApple] 错误信息：%@", errorMsg);
        if (_snsDelegate && [_snsDelegate respondsToSelector:@selector(signInAppleFailure:)]) {
            dispatch_async(dispatch_get_main_queue(), ^{
                [self->_snsDelegate signInAppleFailure:errorMsg];
            });
        }
    }
}

//! Tells the delegate from which window it should present content to the user.
//! 告诉代理应该在哪个window 展示内容给用户
- (ASPresentationAnchor)presentationAnchorForAuthorizationController:(ASAuthorizationController *)controller  API_AVAILABLE(ios(13.0)){
    // 返回window
    return self.window;
}


//! 观察授权状态
- (void)observeAuthticationState {
    
    if (@available(iOS 13.0, *)) {
        // A mechanism for generating requests to authenticate users based on their Apple ID.
        // 基于用户的Apple ID 生成授权用户请求的机制
        ASAuthorizationAppleIDProvider *appleIDProvider = [[ASAuthorizationAppleIDProvider alloc] init];
        // 注意 存储用户标识信息需要使用钥匙串来存储 这里笔者简单期间 使用NSUserDefaults 做的简单示例2
        NSString *userIdentifier = [self getSignedAppleUID];
        
        if (userIdentifier) {
            NSString* __block errorMsg = nil;
            //Returns the credentil state for the given user in a completion handler.
            // 在回调中返回用户的授权状态
            [appleIDProvider getCredentialStateForUserID:userIdentifier completion:^(ASAuthorizationAppleIDProviderCredentialState credentialState, NSError * _Nullable error) {
                BOOL flag = true;
                switch (credentialState) {
                        // 苹果证书的授权状态
                    case ASAuthorizationAppleIDProviderCredentialRevoked:
                        // 苹果授权凭证失效
                        errorMsg = @"苹果授权凭证失效";
                        break;
                    case ASAuthorizationAppleIDProviderCredentialAuthorized:
                        // 苹果授权凭证状态良好
                        errorMsg = @"苹果授权凭证状态良好";
                        flag = false;
                        break;
                    case ASAuthorizationAppleIDProviderCredentialNotFound:
                        // 未发现苹果授权凭证
                        errorMsg = @"未发现苹果授权凭证";
                        // 可以引导用户重新登录
                        break;
                    case ASAuthorizationAppleIDProviderCredentialTransferred:
                        // 未发现苹果授权凭证
                        errorMsg = @"苹果授权信息变动";
                        break;
                }
                if (flag) {
                    [[SDKCache cache] removeObjectForKey:SDK_APPLE_ACCOUNT];
                }
                DLog(@"[signInWithApple] 授权状态 : %@", errorMsg);
            }];
        }
    }
}

#endif

#pragma mark -
#pragma mark SNS
-(void)setSNSDelegate:(id<SDKSNSDelegate>)delegate
{
    _snsDelegate = delegate;
    if(_snsAdapter) {
        [_snsAdapter setSNSDelegate:delegate];
    }
}

-(void)login:(sns_login_result)handler
{
#ifdef ThirdLogin
    [_thirdLogin startLoginWithAnonymous:true isCancelable:false];
    if (handler)
        handler(nil);
#else
    if(_snsAdapter) {
        [_snsAdapter login:handler];
    }
#endif
}

-(void)logout
{
#ifdef ThirdLogin
    [self -> _thirdLogin resetVerifyIdCard];
#else
    if(_snsAdapter) {
        [_snsAdapter logout];
    }
#endif
}

-(BOOL)isLogin
{
    if(_snsAdapter) {
        return [_snsAdapter isLogin];
    }
    return NO;
}

-(nonnull NSString *)meFirstName
{
    return _snsAdapter ? [_snsAdapter meFirstName] : @"";
}

-(nonnull NSString *)meLastName
{
    return _snsAdapter ? [_snsAdapter meLastName] : @"";
}

-(nonnull NSString *)meName {
    return _snsAdapter ? [_snsAdapter meName] : @"";
}

-(nonnull NSString *)meId
{
    return _snsAdapter ? [_snsAdapter meId] : @"";
}

-(nonnull NSString *)mePictureURL
{
    return _snsAdapter ? [_snsAdapter mePictureURL] : @"";
}

-(nonnull NSString *)me
{
#ifdef ThirdLogin
    return [_thirdLogin me];
#else
    return _snsAdapter ? [_snsAdapter me] : @"{}";
#endif
}

-(nonnull NSString *)friends
{
    return _snsAdapter ? [_snsAdapter friends] : @"[]";
}

-(void)invite
{
    if(_snsAdapter) {
        [_snsAdapter invite];
    }
}

-(void)fetchFriends:(BOOL)invitable
{
    if(_snsAdapter) {
        [_snsAdapter fetchFriends:invitable];
    }
}

-(void)fetchScores
{
    if(_snsAdapter) {
        [_snsAdapter fetchScores];
    }
}

-(void)sendScore:(int)score
{
    if (_snsAdapter) {
        [_snsAdapter sendScore:score];
    }
}

-(void)sendAchievement:(int)achievement
{
    if (_snsAdapter) {
        [_snsAdapter sendAchievement:achievement];
    }
}

-(void)sendAppInvite:(NSString *)appLinkURL previewImgURL:(NSString *)previewURL
{
    if (_snsAdapter) {
        [_snsAdapter sendAppInvite:appLinkURL previewImgURL:previewURL];
    }
}

-(void)share
{
    if(_snsAdapter) {
        dispatch_async(dispatch_get_main_queue(), ^{
            [self->_snsAdapter share];
        });
    }
}

-(void)share:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote
{
    if(_snsAdapter) {
        dispatch_async(dispatch_get_main_queue(), ^{
            [self->_snsAdapter share:contentURL withTag:tag withQuote:quote];
        });
    }
}

-(void)shareSheet:(NSString *)linkURL withTag:(NSString *)tag withQuote:(NSString *)quote
{
    if(_snsAdapter) {
        dispatch_async(dispatch_get_main_queue(), ^{
            [self->_snsAdapter shareSheet:linkURL withTag:tag withQuote:quote];
        });
    }
}

-(void)shareSheetOS:(NSString *)linkURL title:(NSString *)title
{
    if(_snsAdapter) {
        dispatch_async(dispatch_get_main_queue(), ^{
            [self->_snsAdapter shareSheetOS:linkURL title:title];
        });
    } else {
        dispatch_async(dispatch_get_main_queue(), ^{
            [SDKHelper share:title image:nil url:[NSURL URLWithString:linkURL] completeHandler:nil];
        });
    }
}

-(void)sendInviteWithFriendIDs:(NSArray *)friendIDs title:(NSString *)title message:(NSString *)message
{
    if(_snsAdapter) {
        [_snsAdapter sendInviteWithFriendIDs:friendIDs title:title message:message];
    }
}

-(void)sendRequestWithFriendIDs:(NSArray *)friendIDs title:(NSString *)title message:(NSString *)message data:(NSDictionary *)data
{
    if(_snsAdapter) {
        [_snsAdapter sendRequestWithFriendIDs:friendIDs title:title message:message data:data];
    }
}

-(UIView *)getLoginButton
{
    if(_snsAdapter) {
        return [_snsAdapter getLoginButton];
    }
    return nil;
}

-(UIView *)getShareButton:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote
{
    if(_snsAdapter) {
        return [_snsAdapter getShareButton:contentURL withTag:tag withQuote:quote];
    }
    return nil;
}

-(UIView *)getSendButton:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote
{
    if(_snsAdapter) {
        return [_snsAdapter getSendButton:contentURL withTag:tag withQuote:quote];
    }
    return nil;
}

-(UIView *)getLikeButton:(nonnull NSString *)url
{
    if(_snsAdapter) {
        return [_snsAdapter getLikeButton:url];
    }
    return nil;
}
#pragma mark -
#pragma mark GameCenter

-(void)_initGameCenter
{
    if(_config) {
        NSDictionary *data = [_config objectForKey:@"gamecenter"];
        if(data) {
            NSNumber *tmp = (NSNumber *)[data objectForKey:@"enable"];
            _gamecenterEnable = tmp ? tmp.boolValue : false;
            _leaderboardData = [SDKHelper toDictionary:[data objectForKey:@"leaderboard"]];
            _achievementData = [SDKHelper toDictionary:[data objectForKey:@"achievement"]];
        }
    }
}

-(void)loginGameCenter
{
    [[SDKGCManager sharedManager] login:^(BOOL success, NSString * _Nullable result) {
        if (success) {
            if (self.snsDelegate && [self.snsDelegate respondsToSelector:@selector(onGameCenterLoginSuccess:)]) {
                [self.snsDelegate onGameCenterLoginSuccess:result];
            }
        } else {
            if (self.snsDelegate && [self.snsDelegate respondsToSelector:@selector(onGameCenterLoginFailure)]) {
                [self.snsDelegate onGameCenterLoginFailure];
            }
        }
    }];
}

-(NSString *)getLeaderboardTimeScope:(int)leaderboardId
{
    NSString *scope = nil;
    if(_leaderboardData) {
        NSDictionary *data = [_leaderboardData objectForKey:[@(leaderboardId) stringValue]];
        if(data) {
            scope = [data objectForKey:@"timescope"];
        }
    }
    return scope ? scope : @"all";
}

-(GameCenterSortOrder)getLeaderboardSortOrderById:(int)leaderboardId
{
    GameCenterSortOrder order = GameCenterSortOrderHighToLow;
    if(_leaderboardData) {
        NSDictionary *data = [_leaderboardData objectForKey:[@(leaderboardId) stringValue]];
        if(data) {
            NSNumber *_order = [data objectForKey:@"sort"];
            if(_order) {
                order = [_order intValue] > 0 ? GameCenterSortOrderHighToLow : GameCenterSortOrderLowToHigh;
            }
        }
    }
    return order;
}

-(nullable NSString *)getLeaderboardIdentifierById:(int)leaderboardId
{
    NSString *identifier = nil;
    if(_leaderboardData) {
        NSDictionary *data = [_leaderboardData objectForKey:[@(leaderboardId) stringValue]];
        if(data) {
            identifier = [data objectForKey:@"id"];
        }
    }
    return identifier;
}

-(nullable NSString *)getAchievementIdentifierById:(int)leaderboardId
{
    NSString *identifier = nil;
    if(_achievementData) {
        NSDictionary *data = [_achievementData objectForKey:[@(leaderboardId) stringValue]];
        if(data) {
            identifier = [data objectForKey:@"id"];
        }
    }
    return identifier;
}

-(BOOL)isGameCenterAvailable
{
    BOOL isAvailable = [SDKGCManager sharedManager].isGameCenterAvailable;
    if (!isAvailable) {
        [[SDKGCManager sharedManager] checkGameCenterAvailable:true];
    }
    return isAvailable;
}

-(void)showLeaderboards
{
    if (![self isGameCenterAvailable])return;
    GKLeaderboardTimeScope scope = GKLeaderboardTimeScopeAllTime;
    [[SDKGCManager sharedManager] presentLeaderboardsOnViewController:[KeyWindow rootViewController] withLeaderboard:nil timeScope:scope];
}

-(void)showLeaderboard:(int)leaderboardId
{
    if (![self isGameCenterAvailable])return;
    GKLeaderboardTimeScope scope = GKLeaderboardTimeScopeAllTime;
    NSString *_scope = [self getLeaderboardTimeScope:leaderboardId];
    if ([_scope isEqualToString:@"week"]) {
        scope = GKLeaderboardTimeScopeWeek;
    } else if ([_scope isEqualToString:@"today"]) {
        scope = GKLeaderboardTimeScopeToday;
    }
    [[SDKGCManager sharedManager] presentLeaderboardsOnViewController:[KeyWindow rootViewController] withLeaderboard:[self getLeaderboardIdentifierById:leaderboardId] timeScope:scope];
}

-(void)showAchievements
{
    if (![self isGameCenterAvailable])return;
    [[SDKGCManager sharedManager] presentAchievementsOnViewController:[KeyWindow rootViewController]];
}

-(void)showChallenges
{
    if (![self isGameCenterAvailable])return;
    [[SDKGCManager sharedManager] presentChallengesOnViewController:[KeyWindow rootViewController]];
}

-(void)submitScore:(int)leaderboardId score:(long long)score
{
    NSString *identifier = [self getLeaderboardIdentifierById:leaderboardId];
    if(identifier) {
        GameCenterSortOrder order = [self getLeaderboardSortOrderById:leaderboardId];
        [[SDKGCManager sharedManager] saveAndReportScore:score leaderboard:identifier sortOrder:order];
    }
}

- (long long)myHighScore:(int)leaderboardId {
    NSString *identifier = [self getLeaderboardIdentifierById:leaderboardId];
    if(identifier) {
        return [[SDKGCManager sharedManager] myHighScore:identifier];
    }
    return 0;
}

-(void)submitAchievement:(int)achievementId percent:(double)percent
{
    NSString *identifier = [self getAchievementIdentifierById:achievementId];
    if(identifier) {
        [[SDKGCManager sharedManager] saveAndReportAchievement:identifier percentComplete:percent shouldDisplayNotification:YES];
    }
}

- (double)getAchievementProgress:(int)achievementId {
    NSString *identifier = [self getAchievementIdentifierById:achievementId];
    if(identifier) {
        return [[SDKGCManager sharedManager] getAchievementProgress:identifier];
    }
    return 0;
}
#pragma mark -
#pragma mark Payment
-(void)_setPaymentProductByPaymentId:(NSString *)paymentId product:(SKProduct *)product
{
    if (!_paymentProducts) {
        _paymentProducts = [[NSMutableDictionary<NSString *, SKProduct *> alloc] init];
    }
    [_paymentProducts setObject:product forKey:paymentId];
    NSMutableDictionary *data = [NSMutableDictionary dictionaryWithDictionary:[self->_paymentData objectForKey:paymentId]];
    NSString *title = [product localizedTitle];
    if(title) {
        [data setObject:title forKey:@"title"];
    } else {
        NSString *name = [data objectForKey:@"name"];
        if (name) {
            [data setObject:name forKey:@"title"];
        } else {
            [data setObject:[product productIdentifier] forKey:@"title"];
        }
    }
    NSString *desc = [product localizedDescription];
    if(desc)
        [data setObject:desc forKey:@"desc"];
    [data setObject:[[SDKIAPHelper sharedHelper] getLocalePrice:product] forKey:@"price"];
    NSString *currency = @"USD";
    @try {
        if (@available(iOS 10.0, *)) {
            currency = [product priceLocale].currencyCode;
        } else {
            // Fallback on earlier versions
            currency = [[[product priceLocale].localeIdentifier componentsSeparatedByString:@"="] objectAtIndex:1];
        }
        
    } @catch (NSException *exception) {
        DLog(@"Exception: %@", [exception description]);
    } @catch (NSError *error) {
        DLog(@"Error: %@", [error localizedDescription]);
    } @finally {
        currency = @"USD";
    }
    [data setObject:currency forKey:@"currency"];
    [data setObject:product.price forKey:@"price_amount"];
    [_paymentData setObject:data forKey:paymentId];
}

-(void)_initPayment
{
    if(_config) {
        NSDictionary *payment = [_config objectForKey:@"payment"];
        if (payment && [payment count] > 0) {
            _paymentProducts = nil;
            NSNumber *tmp = (NSNumber *)[payment objectForKey:@"force-check"];
            _force_check = tmp ? tmp.intValue : 0;
            tmp = (NSNumber *)[payment objectForKey:@"recheck-failed-payments-delay"];
            int recheckDelaySeconds = tmp ? tmp.intValue : 0;
            _paymentVerifyUrl = [payment objectForKey:@"verify-url"];
            _paymentSharedSecret = [payment objectForKey:@"sharesecret"];
            _paymentData = [SDKHelper toDictionary:[payment objectForKey:@"data"]];
            _paymentIdMap = [[NSMutableDictionary alloc] init];
            _transactions = [[NSMutableDictionary alloc] init];
            NSArray *keys = [_paymentData allKeys];
            for (int i=0; i<[keys count]; i++) {
                NSString *key = [keys objectAtIndex:i];
                NSMutableDictionary *value = [_paymentData objectForKey:key];
                if(value) {
                    NSString *identifier = (NSString *)[value objectForKey:@"id"];
                    [_paymentIdMap setObject:key forKey:identifier];
                }
            }
            if(_paymentData) {
                NSMutableArray<NSString *> *identifiers = [[NSMutableArray alloc] init];
                NSArray *values = [_paymentData allValues];
                for (int i=0; i<[values count]; i++) {
                    NSMutableDictionary *value = [values objectAtIndex:i];
                    NSString *identifier = [value objectForKey:@"id"];
                    if(identifier) {
                        [identifiers addObject:identifier];
                    }
                }
                NSSet<NSString *>* dataSet = [[NSSet<NSString *> alloc] initWithArray:identifiers];
                [[SDKIAPHelper sharedHelper] setProductIdentifiers:dataSet];
                [SDKIAPHelper sharedHelper].production = YES;
                [[SDKIAPHelper sharedHelper] requestProductsWithCompletion:^(SKProductsRequest* request,SKProductsResponse* response)
                 {
                     if (response) {
                         NSArray<SKProduct *> *products = [response products];
                         if(products && products.count > 0) {
                             for (int i=0; i<[products count]; i++) {
                                 SKProduct *product = [products objectAtIndex:i];
                                 NSString *paymentId = [self getPaymentIdByIdentifier:[product productIdentifier]];
                                 if(paymentId != nil) {
                                     [self _setPaymentProductByPaymentId:paymentId product:product];
                                 }
                             }
                         }
                         DLog(@"[pay] [init] [%@]", [SDKJSONHelper toJSONString:self->_paymentData]);
                         
                         NSArray<NSString *> *invalids = [response invalidProductIdentifiers];
                         if(invalids && invalids.count > 0) {
                             self->_invalidProductIds = [[NSMutableArray alloc] init];
                             for (int i=0; i<invalids.count; i++) {
                                 NSString *_id = [self getPaymentIdByIdentifier:[invalids objectAtIndex:i]];
                                 if(_id) {
                                     [self->_invalidProductIds addObject:_id];
                                 }
                             }
                         }
                         if (invalids.count > 0) {
                             DLog(@"[pay] [invalids] [%@]", [SDKJSONHelper toJSONString:invalids]);
                         }
                         
                         if(self->_paymentDelegate && [self->_paymentDelegate respondsToSelector:@selector(onPaymentReady)]) {
                             [self->_paymentDelegate onPaymentReady];
                         }
                         
                         if (self->_needCallIsSubscriptionActive) {
                             self->_needCallIsSubscriptionActive = false;
                             [self isSubscriptionActive];
                         }
                         
                         if (recheckDelaySeconds > 0) {
                             [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:@"sdk_delay_recheck_payments" interval:recheckDelaySeconds queue:dispatch_get_main_queue() repeats:NO option:SDKGCDTimerOptionCancelPrevAction action:^{
                                 [self recheckFailedPayments];
                             }];
                         }
                         
                         [self reSendFailedConsumedPayments];
                     }
                 }];
            }
        }
    }
}

-(void)verifyPaymentResponse:(NSDictionary *)response paymentId:(int)paymentId payload:(NSString *)payload productIdentifier:(NSString *)productIdentifier transactionIdentifier:(NSString *)transactionIdentifier
{
    //Convert JSON String to NSDictionary
    int status = [response[@"status"] intValue];
    if(status==0)
    {
//        [[SDKIAPHelper sharedHelper] provideContentWithTransaction:trans];
        [[SDKIAPHelper sharedHelper] provideContent:productIdentifier];
        @try {
#if VerifyIdCard
            NSDictionary *data = [self getPaymentData:paymentId];
            NSNumber *price = [data objectForKey:@"price_amount"];
            [[SDKVerifyIdCard sharedInstance] paySuccess:price.doubleValue];
#elif ThirdLogin
            NSDictionary *data = [self getPaymentData:paymentId];
            NSNumber *price = [data objectForKey:@"price_amount"];
            [_thirdLogin paySuccess:price.doubleValue];
#endif
            [self paySuccess:paymentId payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier orderInfo:nil];
        } @catch (NSException *exception) {
            [self payFailure:paymentId productIdentifer:productIdentifier transactionIdentifier:transactionIdentifier error:[exception description]];
        } @finally {
            [SDKHelper hideLoading];
            BOOL isPurchased = [[SDKIAPHelper sharedHelper] isPurchasedProductsIdentifier:productIdentifier];
            DLog(@"%@", isPurchased ? @"true" : @"false");
        }
    } else {
        NSString *err = @"payment trans failed!";
        switch (status) {
            case 21000:
                err = @"App Store不能读取你提供的JSON对象";
                break;
            case 21002:
                err = @"receipt-data域的数据有问题";
                break;
            case 21003:
                err = @"receipt无法通过验证";
                break;
            case 21004:
                err = @"提供的shared secret不匹配你账号中的shared secret";
                break;
            case 21005:
                err = @"receipt服务器当前不可用";
                break;
            case 21006:
                err = @"receipt合法，但是订阅已过期。服务器接收到这个状态码时，receipt数据仍然会解码并一起发送";
                break;
            case 21007:
                err = @"receipt是Sandbox receipt，但却发送至生产系统的验证服务";
                break;
            case 21008:
                err = @"receipt是生产receipt，但却发送至Sandbox环境的验证服务";
                break;
            default:
                break;
        }
        err = [NSString stringWithFormat:@"[%d] %@", status, err];
        [self payFailure:paymentId productIdentifer:productIdentifier transactionIdentifier:transactionIdentifier error:err];
    }
}

-(void)paySuccess:(int)paymentId payload:(NSString *)payload productIdentifier:(NSString *)productIdentifier transactionIdentifier:(NSString *)transactionIdentifier orderInfo:(nullable NSString *)orderInfo
{
    @try {
        [SDKHelper hideLoading];
        DLog(@"[pay] [success] [%d|%@|%@]", paymentId, payload, orderInfo);
        if ([_transactions objectForKey:transactionIdentifier]) {
            return;
        }
#if !DEBUG
        // 统计计费
        [self logPay:paymentId productIdentifier:productIdentifier  transactionIdentifier:transactionIdentifier];
#endif
        if (_paymentDelegate) {
            if (payload) {
                if (orderInfo) {
                    if ([_paymentDelegate respondsToSelector:@selector(onPaymentSuccess:payload:orderInfo:)]) {
                        [_paymentDelegate onPaymentSuccess:paymentId payload:payload orderInfo:orderInfo];
                    }
                } else {
                    if ([_paymentDelegate respondsToSelector:@selector(onPaymentSuccess:payload:)]) {
                        [_paymentDelegate onPaymentSuccess:paymentId payload:payload];
                    }
                }
            } else {
                if([_paymentDelegate respondsToSelector:@selector(onPaymentSuccess:)]) {
                    [_paymentDelegate onPaymentSuccess:paymentId];
                }
            }
            [self removeCheckFailedTransaction:transactionIdentifier];
            [_transactions setObject:[NSNumber numberWithBool:YES] forKey:transactionIdentifier];
            if (_paymentVerifyUrl) {
                [self sendPaymentConsumedOnline:paymentId payload:payload transactionIdentifier:transactionIdentifier reSendFailure:NO];
            }
            [self mmSetBoolValue:[NSString stringWithFormat:@"asp_%@", transactionIdentifier] value:YES];
            
        }
    } @catch (NSException *exception) {
        DLog(@"%@", exception.reason);
    } @finally {
    }
}

-(void)payFailure:(int)paymentId productIdentifer:(NSString *)productIdentifier transactionIdentifier:(NSString *)transactionIdentifier error:(NSString *)err
{
    DLog(@"[pay] [failure] [err: %@]", err);
    [SDKHelper hideLoading];
    [self logEvent:@"iap_failure" withData:@{@"productIdentifier":productIdentifier}];
    [self removeCheckFailedTransaction:transactionIdentifier];
    if(_paymentDelegate && [_paymentDelegate respondsToSelector:@selector(onPaymentFailure:forError:)]) {
        [_paymentDelegate onPaymentFailure:paymentId forError:err];
    }
}

-(NSString *)getPaymentIdByIdentifier:(nullable NSString *)identifier
{
    if(identifier && _paymentIdMap) {
        return [_paymentIdMap objectForKey:identifier];
    }
    return nil;
}

-(void)onAppStorePayRequest:(int)payId
{
    if(_paymentDelegate && [_paymentDelegate respondsToSelector:@selector(onAppStorePayRequest:)]) {
        [_paymentDelegate onAppStorePayRequest:payId];
    }
}

//检查所有漏单接口
-(void)recheckFailedPayments
{
    if (_force_check < 2) {
        NSMutableArray *arr = (NSMutableArray *)[[SDKCache cache] objectForKey:SDK_RECHECCK_PAY];
        if (arr && arr.count > 0) {
            for (NSDictionary *obj in arr) {
                NSNumber *payId = [obj objectForKey:@"payId"];
                NSString *data = [obj objectForKey:@"data"];
                NSString *payload = [obj objectForKey:@"payload"];
                NSString *productIdentifier = [obj objectForKey:@"productIdentifier"];
                NSString *transactionIdentifier = [obj objectForKey:@"transactionIdentifier"];
                if (_paymentVerifyUrl) {
                    [self checkPurchasedOnline:payId.intValue data:data payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                } else {
                    [[SDKIAPHelper sharedHelper] checkReceipt:[NSData dataWithContentsOfURL:[[NSBundle mainBundle] appStoreReceiptURL]] AndSharedSecret:_paymentSharedSecret onCompletion:^(NSDictionary *response, NSError *error) {
                        [self verifyPaymentResponse:response paymentId:payId.intValue payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                    }];
                }
            }
        }
    }
}

//检查某个计费点是否漏单接口
-(BOOL)recheckFailedPayment:(int)payId
{
    if (_force_check < 2) {
        NSMutableArray *arr = (NSMutableArray *)[[SDKCache cache] objectForKey:SDK_RECHECCK_PAY];
        if (arr && arr.count > 0) {
            for (NSDictionary *obj in arr) {
                NSNumber *_payId = [obj objectForKey:@"payId"];
                if (_payId.intValue == payId) {
                    NSString *data = [obj objectForKey:@"data"];
                    NSString *payload = [obj objectForKey:@"payload"];
                    NSString *productIdentifier = [obj objectForKey:@"productIdentifier"];
                    NSString *transactionIdentifier = [obj objectForKey:@"transactionIdentifier"];
                    [self checkPurchasedOnline:payId data:data payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                    return true;
                }
            }
        }
    }
    return false;
}

//从漏单里面移除计费订单
-(void)removeCheckFailedTransaction:(NSString *)transactionIdentifier
{
    if (transactionIdentifier) {
        NSMutableArray *arr = (NSMutableArray *)[[SDKCache cache] objectForKey:SDK_RECHECCK_PAY];
        if (arr && arr.count > 0) {
            NSArray *_arr = [NSArray arrayWithArray:arr];
            for (int i=0; i < _arr.count; i++) {
                NSDictionary *obj = [_arr objectAtIndex:i];
                NSString *_transactionIdentifier = [obj objectForKey:@"transactionIdentifier"];
                if ([_transactionIdentifier isEqualToString:transactionIdentifier]) {
                    [arr removeObjectAtIndex:i];
                }
            }
            [[SDKCache cache] setObject:arr forKey:SDK_RECHECCK_PAY];
        }
    }
}

-(void)storeFailedCheckPayment:(int)payId data:(NSString *)data payload:(NSString *)payload productIdentifier:(NSString *)productIdentifier transactionIdentifier:(NSString *)transactionIdentifier
{
    NSMutableArray *arr = (NSMutableArray *)[[SDKCache cache] objectForKey:SDK_RECHECCK_PAY];
    if (!arr) {
        arr = [[NSMutableArray alloc] init];
    }
    NSMutableDictionary *obj = [[NSMutableDictionary alloc] init];
    [obj setObject:@(payId) forKey:@"payId"];
    [obj setObject:(data ? data : @"") forKey:@"data"];
    if (payload) {
        [obj setObject:payload forKey:@"payload"];
    }
    [obj setObject:productIdentifier forKey:@"productIdentifier"];
    [obj setObject:transactionIdentifier forKey:@"transactionIdentifier"];
    [arr addObject:obj];
    [[SDKCache cache] setObject:arr forKey:SDK_RECHECCK_PAY];
}
    
-(void)storeFailedConsumedPayment:(int)payId payload:(NSString *)payload transactionIdentifier:(NSString *)transactionIdentifier
{
    NSMutableArray *arr = (NSMutableArray *)[[SDKCache cache] objectForKey:SDK_FAILED_CONSUMED_PAY];
    if (!arr) {
        arr = [[NSMutableArray alloc] init];
    }
    NSMutableDictionary *obj = [[NSMutableDictionary alloc] init];
    [obj setObject:@(payId) forKey:@"payId"];
    if (payload) {
        [obj setObject:payload forKey:@"payload"];
    }
    [obj setObject:transactionIdentifier forKey:@"transactionIdentifier"];
    [arr addObject:obj];
    [[SDKCache cache] setObject:arr forKey:SDK_FAILED_CONSUMED_PAY];
}

-(void)removeFailedConsumedPayment:(NSString *)transactionIdentifier
{
    if (transactionIdentifier) {
        NSMutableArray *arr = (NSMutableArray *)[[SDKCache cache] objectForKey:SDK_FAILED_CONSUMED_PAY];
        if (arr && arr.count > 0) {
            NSMutableArray *arr2 = [[NSMutableArray alloc] init];
            NSArray *_arr = [NSArray arrayWithArray:arr];
            for (int i=0; i < _arr.count; i++) {
                NSDictionary *obj = [_arr objectAtIndex:i];
                NSString *_transactionIdentifier = [obj objectForKey:@"transactionIdentifier"];
                if (![_transactionIdentifier isEqualToString:transactionIdentifier]) {
                    [arr2 addObject:obj];
                }
            }
            [[SDKCache cache] setObject:arr2 forKey:SDK_FAILED_CONSUMED_PAY];
        }
    }
}
    
-(void)reSendFailedConsumedPayments
{
    if (_paymentVerifyUrl) {
        NSMutableArray *arr = (NSMutableArray *)[[SDKCache cache] objectForKey:SDK_FAILED_CONSUMED_PAY];
        if (arr && arr.count > 0) {
            for (int i=0; i < arr.count; i++) {
                NSDictionary *obj = [arr objectAtIndex:i];
                NSNumber *payId = [obj objectForKey:@"payId"];
                NSString *payload = [obj objectForKey:@"payload"];
                NSString *transactionIdentifier = [obj objectForKey:@"transactionIdentifier"];
                if (_paymentVerifyUrl) {
                    [self sendPaymentConsumedOnline:payId.intValue payload:payload transactionIdentifier:transactionIdentifier reSendFailure:YES];
                }
            }
        }
    }
}

//向服务器提交确认计费发货成功请求
-(void)sendPaymentConsumedOnline:(int)payId payload:(NSString *)payload transactionIdentifier:(NSString *)transactionIdentifier reSendFailure:(BOOL)reSendFailure
{
    NSString *paymentId = [@(payId) stringValue];
    NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithDictionary:@{
        @"consumed":[NSNumber numberWithBool:true],
        @"country":[SDKHelper getCountryCode].uppercaseString,
        @"platform":@"ios",
        @"payId":paymentId,
        @"payload":(payload ? payload : @""),
        @"appid":_appid,
        @"transactionIdentifier":transactionIdentifier,
        @"package": [NSBundle mainBundle].bundleIdentifier,
        @"name":[SDKHelper getAppName],
        @"uuid":[[[UIDevice currentDevice] identifierForVendor] UUIDString]}];
    [params setValuesForKeysWithDictionary:[_paymentData objectForKey:paymentId]];
    NSString *data = [SDKJSONHelper toJSONString:params];
    data = [SDKHelper encryptWithAES:data key:PAY_VERIFY_KEY];
    NSMutableURLRequest *req = [[NSMutableURLRequest alloc] initWithURL:[NSURL URLWithString:_paymentVerifyUrl]];
    [req setHTTPMethod:@"POST"];
    [req setHTTPBody:[[NSString stringWithFormat:@"data=%@", data] dataUsingEncoding:kCFStringEncodingUTF8]];
    if (!reSendFailure) {
        [self storeFailedConsumedPayment:payId payload:payload transactionIdentifier:transactionIdentifier];
    }
    
    NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
    configuration.timeoutIntervalForRequest = 30;
    configuration.timeoutIntervalForResource = 30;
    NSURLSession *session = [NSURLSession sessionWithConfiguration:configuration];
    [[session dataTaskWithRequest:req completionHandler:^(NSData *nsdata, NSURLResponse *response, NSError *error) {
        if(error){
        } else {
            [self removeFailedConsumedPayment:transactionIdentifier];
        }
    }] resume];
}

-(void)setTargetForChild
{
#ifdef GOOGLE
    GADMobileAds.sharedInstance.requestConfiguration.tagForChildDirectedTreatment = @YES;
#endif
}

-(void)checkPurchasedOnline:(int)payId data:(NSString *)data payload:(NSString *)payload productIdentifier:(NSString *)productIdentifier transactionIdentifier:(NSString *)transactionIdentifier
{
    NSMutableURLRequest *req = [[NSMutableURLRequest alloc] initWithURL:[NSURL URLWithString:_paymentVerifyUrl]];
    [req setHTTPMethod:@"POST"];
    [req setHTTPBody:[[NSString stringWithFormat:@"data=%@", data] dataUsingEncoding:kCFStringEncodingUTF8]];

    NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
    configuration.timeoutIntervalForRequest = 30;
    configuration.timeoutIntervalForResource = 30;
    NSURLSession *session = [NSURLSession sessionWithConfiguration:configuration];
    [[session dataTaskWithRequest:req completionHandler:^(NSData *nsdata, NSURLResponse *response, NSError *error) {

        if(error){
            if (self->_force_check > 0) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    [EasyTextView showErrorText:@"Server error! Please check your network connection."];
                    [SDKHelper hideLoading];
                });
            } else {
                [[SDKIAPHelper sharedHelper] checkReceipt:[NSData dataWithContentsOfURL:[[NSBundle mainBundle] appStoreReceiptURL]] AndSharedSecret:self->_paymentSharedSecret onCompletion:^(NSDictionary *response, NSError *error) {
                    [self verifyPaymentResponse:response paymentId:payId payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                }];
            }
        } else {
            NSDictionary *responseObject = [NSJSONSerialization JSONObjectWithData:nsdata options:0 error:&error];
            if (responseObject && [responseObject isKindOfClass:[NSDictionary class]]) {
                NSNumber *status = (NSNumber *)[responseObject objectForKey:@"code"];
                if ([status intValue] >= 5000) {
                    NSString *__key = [NSString stringWithFormat:@"has_delay_check_online_%@", transactionIdentifier];
                    if ([[SDKCache cache] containsObjectForKey:__key]) {
                        [[SDKCache cache] removeObjectForKey:__key];
                    } else {
                        // 5s后台重试验证
                        __block int __payId = payId;
                        __block NSString * __data = data;
                        __block NSString * __payload = payload;
                        __block NSString * __productIdentifier = productIdentifier;
                        __block NSString * __transactionIdentifier = transactionIdentifier;
                        [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:__transactionIdentifier interval:5 queue:dispatch_get_main_queue() repeats:NO option:SDKGCDTimerOptionCancelPrevAction action:^{
                            [self checkPurchasedOnline:__payId data:__data payload:__payload productIdentifier:__productIdentifier transactionIdentifier:__transactionIdentifier];
                            __data = nil;
                            __payload = nil;
                            __productIdentifier = nil;
                            __transactionIdentifier = nil;
                        }];
                        [[SDKCache cache] setObject:[NSNumber numberWithInt:1] forKey:__key];
                    }
                } else if ([status intValue] < 4000) {
                    @try {
                        NSString *edata = (NSString *)[responseObject objectForKey:@"edata"];
                        NSString *decryptJson = [SDKHelper decryptWithAES:edata key:PAY_VERIFY_KEY];
                        NSDictionary *response = [SDKJSONHelper toArrayOrNSDictionary:decryptJson];
                        [self verifyPaymentResponse:response paymentId:payId payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                    } @catch (NSException *ex) {
                        [self payFailure:payId productIdentifer:productIdentifier transactionIdentifier:transactionIdentifier error:ex.description];
                    } @catch (NSError *er) {
                        [self payFailure:payId productIdentifer:productIdentifier transactionIdentifier:transactionIdentifier error:er.description];
                    }
                } else {
                    NSString *err = (NSString *)[responseObject objectForKey:@"err"];
                    [self payFailure:payId productIdentifer:productIdentifier transactionIdentifier:transactionIdentifier error:err];
                }
            }
        }

    }] resume];
    
//    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0), ^{
//        [[SDKIAPHelper sharedHelper] checkReceipt:[NSData dataWithContentsOfURL:[[NSBundle mainBundle] appStoreReceiptURL]] AndSharedSecret:self->_paymentSharedSecret onCompletion:nil];
//    });
}

-(void)pay:(int)paymentId
{
    [self pay:paymentId payload:nil];
}

-(void)pay:(int)paymentId payload:(NSString *)payload
{
    __block NSString *payId = [@(paymentId) stringValue];
    if(_invalidProductIds && [_invalidProductIds containsObject:payId]) {
        [self payFailure:paymentId productIdentifer:@"" transactionIdentifier:nil error:@"该计费点不合法或已失效！"];
        return;
    }
    
    __block SKProduct *_product = [_paymentProducts objectForKey:payId];
    if (!_product) {
        NSDictionary *payment = [self getPaymentData:paymentId];
        NSString *identifier = [payment objectForKey:@"id"];
        [SDKHelper showLoading];
        [[SDKIAPHelper sharedHelper] requestProductsWithIdentifier:identifier completion:^(SKProductsRequest* request,SKProductsResponse* response)
         {
             if (response) {
                 dispatch_async(dispatch_get_main_queue(), ^{
                     [SDKHelper hideLoading];
                     NSArray<SKProduct *> *products = [response products];
                     if(products && products.count > 0) {
                         SKProduct *product = products.firstObject;
                         [self _setPaymentProductByPaymentId:payId product:product];
                         [self pay:paymentId payload:payload];
                     }
                 });
             }
        }];
        return;
    }
    if(_product) {
        @try {
            if (![self isNetworkConnected]) {
                [self payFailure:paymentId productIdentifer:_product.productIdentifier transactionIdentifier:nil error:@"计费失败，请检查网络！"];
                return;
            }
#if VerifyIdCard
            if (![[SDKVerifyIdCard sharedInstance] checkCanPay]) {
                [self payFailure:paymentId productIdentifer:_product.productIdentifier transactionIdentifier:nil error:@"计费失败"];
                return;
            }
#endif
#if ThirdLogin
            if (![_thirdLogin checkCanPay]) {
                [self payFailure:paymentId productIdentifer:_product.productIdentifier transactionIdentifier:nil error:@"计费失败"];
                return;
            }
#endif
            [SDKHelper showLoading];
            DLog(@"[pay] [start] [%d|%@]", paymentId, _product.productIdentifier);
#if !DEBUG
            [self logStartPay:[_paymentData objectForKey:payId]];
#endif
            if (_paymentVerifyUrl && _force_check && [self recheckFailedPayment:paymentId]) {
                DLog(@"[pay] [recheck success : %d|%@]", paymentId, _product.productIdentifier);
                [SDKHelper hideLoading];
                return;
            }
            [[SDKIAPHelper sharedHelper] buyProduct:_product onCompletion:^(SKPaymentTransaction* trans){
                __strong SKProduct *product = _product;
                NSString *transactionIdentifier = trans.transactionIdentifier;
                if(trans.error)
                {
                    NSString *reason = [trans.error localizedFailureReason];
                    reason = reason ? reason : [trans.error description];
                    [self payFailure:paymentId productIdentifer:product.productIdentifier transactionIdentifier:transactionIdentifier error:reason];
                } else if(trans.transactionState == SKPaymentTransactionStatePurchased || trans.transactionState == SKPaymentTransactionStateRestored) {
                    NSString *productIdentifier = [[SDKIAPHelper sharedHelper] getProductIdentifierFromTransaction:trans];
                    productIdentifier = productIdentifier ? productIdentifier : product.productIdentifier;
                    if (self->_paymentVerifyUrl || self->_force_check >= 2) {
                        NSData *receipt = [NSData dataWithContentsOfURL:[[NSBundle mainBundle] appStoreReceiptURL]];
                        NSString *receiptBase64 = [NSString base64StringFromData:receipt length:[receipt length]];
                        NSMutableDictionary *params = [[NSMutableDictionary alloc] initWithDictionary:@{
                            @"receipt":receiptBase64,
                            @"country":[SDKHelper getCountryCode].uppercaseString,
                            @"platform":@"ios",
                            @"payId":payId,
                            @"payload":(payload ? payload : @""),
                            @"appid":self->_appid,
                            @"transactionIdentifier":transactionIdentifier,
                            @"package": [NSBundle mainBundle].bundleIdentifier,
                            @"name":[SDKHelper getAppName],
                            @"uuid":[[[UIDevice currentDevice] identifierForVendor] UUIDString]}];
                        [params setValuesForKeysWithDictionary:[self->_paymentData objectForKey:payId]];
                        NSString *data = [SDKJSONHelper toJSONString:params];
                        if (self->_force_check >= 2) {
                            [self paySuccess:paymentId payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier orderInfo:data];
                        } else {
                            data = [SDKHelper encryptWithAES:data key:PAY_VERIFY_KEY];
                            [self storeFailedCheckPayment:paymentId data:data payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                            [self checkPurchasedOnline:paymentId data:data payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                        }
                    } else {
                        [self storeFailedCheckPayment:paymentId data:nil payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                        [[SDKIAPHelper sharedHelper] checkReceipt:[NSData dataWithContentsOfURL:[[NSBundle mainBundle] appStoreReceiptURL]] AndSharedSecret:self->_paymentSharedSecret onCompletion:^(NSDictionary *response, NSError *error) {
                            [self verifyPaymentResponse:response paymentId:paymentId payload:payload productIdentifier:productIdentifier transactionIdentifier:transactionIdentifier];
                        }];
                    }
                } else if(trans.transactionState == SKPaymentTransactionStateFailed) {
                    [self payFailure:paymentId productIdentifer:product.productIdentifier transactionIdentifier:transactionIdentifier error:@"payment trans failed!"];
                }
            }];
        } @catch (NSException *exception) {
            DLog(@"[sdk]Exception: %@", [exception description]);
            [self payFailure:paymentId productIdentifer:_product.productIdentifier transactionIdentifier:nil error:[exception description]];
        } @catch (NSError *error) {
            DLog(@"[sdk]Error: %@", [error localizedDescription]);
            [self payFailure:paymentId productIdentifer:_product.productIdentifier transactionIdentifier:nil error:[error localizedDescription]];
        } @finally {
        }
    } else {
        [self payFailure:paymentId productIdentifer:_product.productIdentifier transactionIdentifier:nil error:@"没有配置该计费点！"];
    }
}

-(void)payRestoreFailure:(NSString *)err
{
    DLog(@"[pay] [restore] [failure] [err : %@]", err);
    [SDKHelper hideLoading];
    if(_paymentDelegate && [_paymentDelegate respondsToSelector:@selector(onRestoreFailure:)]) {
        [_paymentDelegate onRestoreFailure:err];
    }
}

//恢复订阅型，非消耗型计费点
-(void)restorePayments
{
    DLog(@"[pay] [restore]");
    [SDKHelper showLoading];
    [[SDKIAPHelper sharedHelper] restoreProductsWithCompletion:^(SKPaymentQueue* _Nonnull payment, NSError*_Nullable error) {
        if(error) {
            [self payRestoreFailure:[error description]];
        } else {
            NSURL *url = [[NSBundle mainBundle] appStoreReceiptURL];
            __block NSArray<SKPaymentTransaction *> *trans = payment.transactions;
            if(trans && trans.count > 0) {
                [[SDKIAPHelper sharedHelper] checkReceipt:[NSData dataWithContentsOfURL:url] AndSharedSecret:self->_paymentSharedSecret onCompletion:^(NSDictionary *response, NSError *error) {
                    [SDKHelper hideLoading];
                    if(response) {
                        double timestamp = [[NSDate date] timeIntervalSince1970];
                        int64_t now = (int64_t)(timestamp*1000);
                        NSArray *latest_receipt = response[@"latest_receipt_info"];
                        if(latest_receipt && latest_receipt.count > 0) {
                            if(payment) {
                                NSMutableDictionary *products = [[NSMutableDictionary alloc] init];
                                for(long i=latest_receipt.count-1; i>=0; i--) {
                                    NSDictionary *data = [latest_receipt objectAtIndex:i];
                                    NSString *trans_id = [data objectForKey:@"original_transaction_id"];
                                    
                                    NSString *ms = [data objectForKey:@"expires_date_ms"];
                                    long long _ms = ms ? [ms longLongValue] : now + 1000;
                                    
                                    if(trans_id) {
                                        NSDictionary *tmp = [products objectForKey:trans_id];
                                        long long _lms = 0;
                                        if(tmp) {
                                            NSString *lms = [tmp objectForKey:@"expires_date_ms"];
                                            _lms = lms ? [lms longLongValue] : 0;
                                        }
                                        if(_ms > _lms) {
                                            [products setObject:data forKey:trans_id];
                                        }
                                    }
                                }
                                NSMutableDictionary *hasDoRestoreIds = [[NSMutableDictionary alloc] init];
                                for (SKPaymentTransaction *transaction in trans)
                                {
                                    NSDictionary *data = [products objectForKey:transaction.originalTransaction.transactionIdentifier];
                                    if(data) {
                                        NSString *productIdentifier = transaction.payment.productIdentifier;
                                        NSString *_id = [data objectForKey:@"product_id"];
                                        if([productIdentifier isEqualToString:_id]) {
                                            NSString *expires_date_ms = [data objectForKey:@"expires_date_ms"];
                                            long long _expires_date_ms = expires_date_ms ? [expires_date_ms longLongValue] : now;
                                            if(now <= _expires_date_ms) {
                                                NSString *payId = [self getPaymentIdByIdentifier:productIdentifier];
                                                if(payId && ![hasDoRestoreIds objectForKey:payId]) {
                                                    int paymentId = [payId intValue];
                                                    @try {
                                                        if(self->_paymentDelegate && [self->_paymentDelegate respondsToSelector:@selector(onRestoreSuccess:)]) {
                                                            [self->_paymentDelegate onRestoreSuccess:paymentId];
                                                        }
                                                        [hasDoRestoreIds setObject:payId forKey:payId];
                                                        DLog(@"[pay] [restore] [success] [%d|%@]", paymentId, productIdentifier);
                                                    } @catch (NSException *exception) {
                                                        [self payRestoreFailure:[exception description]];
                                                    } @catch (NSError *error) {
                                                        [self payRestoreFailure:[error description]];
                                                    } @finally {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                [self payRestoreFailure:@"Restore payments failure 1!"];
                            }
                        } else {
                            for (SKPaymentTransaction *transaction in trans) {
                                SKPayment *payment = transaction.payment;
                                if(payment) {
                                    NSString *productIdentifier = payment.productIdentifier;
                                    NSString *payId = [self getPaymentIdByIdentifier:productIdentifier];
                                    if(payId) {
                                        int paymentId = [payId intValue];
                                        @try {
                                            if(self->_paymentDelegate && [self->_paymentDelegate respondsToSelector:@selector(onRestoreSuccess:)]) {
                                                [self->_paymentDelegate onRestoreSuccess:paymentId];
                                            }
                                        } @catch (NSException *exception) {
                                            [self payRestoreFailure:[exception description]];
                                        } @catch (NSError *error) {
                                            [self payRestoreFailure:[error description]];
                                        } @finally {
                                        }
                                    }
                                }
                            }
                        }
                        return;
                    }
                    [self payRestoreFailure:@"Restore payments failure 2!"];
                }];
            } else {
                [self payRestoreFailure:@"Restore payments failure 3!"];
            }
        }
    }];
}

// 只检查订阅计费点是否过期
-(void)isSubscriptionActive
{
    if (!_paymentProducts) {
        _needCallIsSubscriptionActive = true;
        return;
    }
    NSURL *url = [[NSBundle mainBundle] appStoreReceiptURL];
    [[SDKIAPHelper sharedHelper] checkReceipt:[NSData dataWithContentsOfURL:url] AndSharedSecret:_paymentSharedSecret onCompletion:^(id response, NSError *error) {
        
        NSDictionary* rec = (NSDictionary *)response;
        if(rec) {
            NSArray *latest_receipt = rec[@"latest_receipt_info"];
            if(latest_receipt && latest_receipt.count > 0) {
                NSMutableDictionary<NSString *, NSNumber *> *maxExpireMS = [[NSMutableDictionary alloc] init];
                double timestamp = [[NSDate date] timeIntervalSince1970];
                int64_t now = (int64_t)(timestamp*1000);
                for(long i=latest_receipt.count-1; i>=0; i--) {
                    NSDictionary *data = [latest_receipt objectAtIndex:i];
                    NSString *_id = [data objectForKey:@"product_id"];
                    NSString *paymentId = [self getPaymentIdByIdentifier:_id];
                    if(paymentId) {
                        NSString *expires_date_ms = [data objectForKey:@"expires_date_ms"];
                        long long _expires_date_ms = [expires_date_ms longLongValue];
                        NSNumber *tmp = [maxExpireMS objectForKey:paymentId];
                        long long saved = tmp ? tmp.longLongValue : 0;
                        if (_expires_date_ms > saved) {
                            [maxExpireMS setObject:@(_expires_date_ms) forKey:paymentId];
                        }
                    }
                }
                if (maxExpireMS.count > 0) {
                    for (NSString *paymentId in maxExpireMS) {
                        NSNumber *tmp = [maxExpireMS objectForKey:paymentId];
                        long long expireMs = tmp ? tmp.longLongValue : now;
                        long remainSeconds = (long)(expireMs - now);
                        if(self->_paymentDelegate && [self->_paymentDelegate respondsToSelector:@selector(onCheckSubscriptionResult:remainSeconds:)]) {
                            [self->_paymentDelegate onCheckSubscriptionResult:[paymentId intValue] remainSeconds:remainSeconds];
                        }
                    }
                }
            } else {
                if(self->_paymentDelegate && [self->_paymentDelegate respondsToSelector:@selector(onCheckSubscriptionResult:remainSeconds:)]) {
                    [self->_paymentDelegate onCheckSubscriptionResult:-1 remainSeconds:0];
                }
            }
        }
    }];
}

-(void)setPayVerifyUrl:(NSString *)url
{
    if (url) {
        _paymentVerifyUrl = url;
    }
}

-(NSArray<NSString *> *)getPurchasedIds
{
    NSArray *products = [[SDKIAPHelper sharedHelper].purchasedProducts allObjects];
    if(products && products.count > 0) {
        NSMutableArray<NSString *> *ids = [[NSMutableArray alloc] init];
        for (int i=0; i<products.count; i++) {
            [ids addObject:[self getPaymentIdByIdentifier:[products objectAtIndex:i]]];
        }
        return ids;
    }
    return nil;
}

/**
 {
 "3": {
 "price": "CN¥28.00",
 "id": "com.e688.sdkdemo.monthvip1",
 "title": "Month VIP",
 "name": "Month VIP1",
 "autorenew": 1,
 "desc": "Support VIP in one month"
 },
 "1": {
 "id": "com.e688.sdkdemo.gold100",
 "title": "100 coins",
 "name": "100 coins",
 "desc": "Consume 1 dollar can buy 100 coins.",
 "price": "CN¥6.00"
 },
 "4": {
 "id": "com.e688.sdkdemo.vip",
 "title": "Lifelong VIP",
 "name": "Lifelong VIP",
 "desc": "Lifelong VIP",
 "price": "CN¥98.00"
 },
 "2": {
 "id": "com.e688.sdkdemo.gold500",
 "title": "500 coins",
 "name": "500 coins",
 "desc": "Consume 5 dollar can buy 500 coins.",
 "price": "CN¥30.00"
 },
 "5": {
 "id": "com.e688.sdkdemo.vip2",
 "title": "VIP2",
 "name": "VIP2",
 "desc": "Buy 10 times vip",
 "price": "CN¥30.00"
 }
 }
 */
-(NSDictionary *)getPaymentData:(int)paymentId
{
    if(_paymentData) {
        NSDictionary *data = [_paymentData objectForKey:[@(paymentId) stringValue]];
        return data;
    }
    return nil;
}

-(NSString *)getPaymentDatas
{
    return [SDKJSONHelper toJSONString:_paymentData];
}

-(void)clearPurchasedIds
{
    [[SDKIAPHelper sharedHelper] clearSavedPurchasedProducts];
}

-(void)clearPurchasedId:(int)paymentId
{
    NSString *payId = [@(paymentId) stringValue];
    NSDictionary *product = [_paymentData objectForKey:payId];
    if(product) {
        NSString *productIdentifier = [product objectForKey:@"id"];
        if(productIdentifier) {
            [[SDKIAPHelper sharedHelper] clearSavedPurchasedProductByID:productIdentifier];
        }
    }
}
#pragma mark -
#pragma mark Push
-(void)registerPush
{
    // iOS 10 or later
    if (@available(iOS 10.0, *)) {
        UNUserNotificationCenter *center = [UNUserNotificationCenter currentNotificationCenter];
        UNAuthorizationOptions authOptions =
        UNAuthorizationOptionAlert
        | UNAuthorizationOptionSound
        | UNAuthorizationOptionBadge;
        [center requestAuthorizationWithOptions:authOptions completionHandler:^(BOOL granted, NSError * _Nullable error) {
            if (error) {
                DLog(@"[sdk]%@", [error localizedDescription]);
            } else if (granted) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    [[UIApplication sharedApplication] registerForRemoteNotifications];
                    [self isPushRegistered];
                });
            }
        }];
        [center getNotificationSettingsWithCompletionHandler:^(UNNotificationSettings * _Nonnull settings) {
            DLog(@"[sdk]======注册成功要处理的事情========");
        }];
    } else {
        // iOS 8 or later
        // [START register_for_notifications]
        UIApplication *application = [UIApplication sharedApplication];
        if (floor(NSFoundationVersionNumber) <= NSFoundationVersionNumber_iOS_7_1) {
            // iOS 7.1 or earlier. Disable the deprecation warnings.
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wdeprecated-declarations"
            UIRemoteNotificationType allNotificationTypes =
            (UIRemoteNotificationTypeSound |
             UIRemoteNotificationTypeAlert |
             UIRemoteNotificationTypeBadge);
            [application registerForRemoteNotificationTypes:allNotificationTypes];
#pragma clang diagnostic pop
        }
        if([application respondsToSelector:@selector(registerUserNotificationSettings:)]) {
            UIUserNotificationSettings *settings =
            [UIUserNotificationSettings settingsForTypes:UIUserNotificationTypeAlert | UIUserNotificationTypeBadge | UIUserNotificationTypeSound categories:nil];
            [application registerUserNotificationSettings:settings];
        }
        
        [application registerForRemoteNotifications];
        [self isPushRegistered];
    }
    // [END register_for_notifications]
}
    
-(BOOL)isPushRegistered
{
    [[UNUserNotificationCenter currentNotificationCenter] getNotificationSettingsWithCompletionHandler:^(UNNotificationSettings * _Nonnull settings) {
        BOOL value = settings.authorizationStatus != UNAuthorizationStatusNotDetermined && settings.authorizationStatus != UNAuthorizationStatusDenied;
        [[SDKCache cache] setObject:[NSNumber numberWithBool:value] forKey:SDK_REGISTER_PUSH];
    }];
//    UIUserNotificationSettings *notificationSettings = [[UIApplication sharedApplication] currentUserNotificationSettings];
//    return notificationSettings.types != UIUserNotificationTypeNone;
    NSNumber *tmp = (NSNumber *)[[SDKCache cache] objectForKey:SDK_REGISTER_PUSH];
    return tmp && tmp.boolValue;
}

-(void)queryPushNotification{
    if(@available(iOS 10.0, *)){
        [[UNUserNotificationCenter currentNotificationCenter] getNotificationSettingsWithCompletionHandler:^(UNNotificationSettings * _Nonnull settings) {
            switch(settings.authorizationStatus) {
                case UNAuthorizationStatusDenied:
                case UNAuthorizationStatusNotDetermined:
                    [self onNotificationOpen:FALSE];
                    break;
                default:
                    [self onNotificationOpen:TRUE];
                    break;
            }
        }];
    }
}

-(nullable NSDictionary *)getPushData
{
    SDKCache *cache = [SDKCache cache];
    NSDictionary *data = (NSDictionary *)[cache objectForKey:SDK_PUSH_DATA];
    [cache removeObjectForKey:SDK_PUSH_DATA];
    return data;
}

-(void)initEventConverstionEventsMap
{
    NSString* inAppConversions = [self getRemoteConfigStringValue:@"inapp_conversions"];
    if (inAppConversions != nil && ![inAppConversions isEqualToString:@""]) {
        _inAppConversionEvents = [[NSMutableDictionary alloc] init];
        id data = [SDKJSONHelper toArrayOrNSDictionary:inAppConversions];
        if (data && [data isKindOfClass:[NSArray class]]) {
            for (id eventKey in data) {
                if (eventKey != nil && [eventKey isKindOfClass:[NSString class]]) {
                    [_inAppConversionEvents setObject:[NSNumber numberWithInt:1] forKey:eventKey];
                }
            }
        }
    }
}

-(void)sendRecordEventConversion:(nonnull NSString*) eventName withData:(nullable NSDictionary<NSString *, id> *)withData
{
    
    if (eventConversionUrl == nil || [eventConversionUrl isEqualToString:@""]) {
        return;
    }
    
    NSMutableDictionary *jsonData = nil;
    if (withData == nil) {
        jsonData = [[NSMutableDictionary alloc] init];
    } else {
        jsonData = [[NSMutableDictionary alloc] initWithDictionary:withData];
    }
    
    [jsonData setObject:eventName forKey:@"event_token"];
    [jsonData setObject:@"ios" forKey:@"platform"];
    
#ifdef FIREBASE
    FIRUser* currentUser = [[FIRAuth auth] currentUser];
    if (currentUser != nil) {
        [jsonData setObject:[currentUser uid] forKey:@"uid"];
    }
#endif
    
    // collect all pa_ remote configs
    NSSet * userPropertyKeys = [self remoteConfigKeysWithPrefix:@"pa_"];
    if (userPropertyKeys && [userPropertyKeys count] > 0) {
        for(NSString* key in userPropertyKeys) {
            NSString* v = [self getRemoteConfigStringValue:key];
            [jsonData setObject:v forKey:key];
        }
    }
    
    [[SDKNetworkHelper sharedHelper] POST:eventConversionUrl parameters:jsonData jsonRequest:true jsonResponse:true success:^(id responseObject) {
        if(responseObject && [responseObject isKindOfClass:[NSDictionary class]]) {
            NSDictionary *data = (NSDictionary*)responseObject;
            if (data) {
                NSString* inAppEvent = [data objectForKey:@"inapp_event"];
                if (inAppEvent && ![inAppEvent isEqualToString:@""]) {
#ifdef FIREBASE_PLUS
                    [self triggerInAppMessage:inAppEvent];
#endif
                }
                
                NSString* event = [data objectForKey:@"event"];
                if (event && ![event isEqualToString:@""]) {
                    [self logEvent:event];
                }
                
                NSDictionary* inAppMessage = [data objectForKey:@"inapp_message"];
                if (inAppMessage && self->_inAppMessageDelegate) {
                    NSString* inAppMessageString = [SDKJSONHelper toJSONString:inAppMessage];
                    if (inAppMessageString) {
                        [self->_inAppMessageDelegate inAppMessageEvent:inAppMessageString];
                    }
                }
                
            }
        }
    } failure:^(NSError *error) {
        DLog(@"save user attribution exception %@", error.description);
    }];
}

-(void)recordEventConversion:(nonnull NSString*) eventName withData:(nullable NSDictionary<NSString *, id> *)withData
{
    if (eventConversionUrl == nil || [eventConversionUrl isEqualToString:@""]) {
        return;
    }
    
    BOOL conversionEvent = [eventName isEqualToString:@"af_conversion"] ? YES : NO;
    
    if (!conversionEvent) {
        if (_inAppConversionEvents == nil || [_inAppConversionEvents objectForKey:eventName].intValue != 1) {
            return;
        }
    }
    
    [self sendRecordEventConversion:eventName withData:withData];
}

-(void)checkAutoEventTrigger
{
    NSString* autoEventTrigger = [self getRemoteConfigStringValue:@"auto_event_trigger"];
    if (autoEventTrigger != nil && ![autoEventTrigger isEqualToString:@""]) {
        NSString* saveKey = [NSString stringWithFormat:@"sent_%@", autoEventTrigger];
        BOOL alreadySent = [self mmGetBoolValue:saveKey defaultValue: NO];
        
        if (alreadySent == NO) {
            [self logEvent:autoEventTrigger];
            [self mmSetBoolValue:saveKey value:YES];
        }
    }
}
#ifdef FIREBASE_PLUS
-(void)checkAutoInAppMessage
{
    
    NSString* autoInAppMessageEvent = [self getRemoteConfigStringValue:@"auto_inapp_message_event"];
    if (autoInAppMessageEvent != nil && ![autoInAppMessageEvent isEqualToString:@""]) {
        [self triggerInAppMessage: autoInAppMessageEvent];
    }
}
#endif
-(void)saveUserAttribute:(nonnull NSString*)data
{
    if (userAttributeUrl == nil || [userAttributeUrl isEqualToString:@""]) {
        return;
    }
    
    NSMutableDictionary *jsonData = nil;
    if (data) {
        jsonData = [SDKJSONHelper toArrayOrNSDictionary:data];
    } else {
        jsonData = [[NSMutableDictionary alloc] init];
    }
    
    [jsonData setObject:@"ios" forKey:@"platform"];
    
#ifdef FIREBASE
    FIRUser* currentUser = [[FIRAuth auth] currentUser];
    if (currentUser != nil) {
        [jsonData setObject:[currentUser uid] forKey:@"uid"];
    }
#endif
#ifdef FIREBASE_PLUS
    NSString* installationId = [_firebaseInAppMessage installationId];
    if (installationId != nil) {
        [jsonData setObject:installationId forKey:@"inapp_installation"];
    }
#endif
    
    // collect all pa_ remote configs
    NSSet * userPropertyKeys = [self remoteConfigKeysWithPrefix:@"pa_"];
    if (userPropertyKeys && [userPropertyKeys count] > 0) {
        for(NSString* key in userPropertyKeys) {
            NSString* v = [self getRemoteConfigStringValue:key];
            [jsonData setObject:v forKey:key];
        }
    }
    
    [[SDKNetworkHelper sharedHelper] POST:userAttributeUrl parameters:jsonData jsonRequest:true jsonResponse:true success:^(id responseObject) {
        if(responseObject && [responseObject isKindOfClass:[NSDictionary class]]) {
            NSDictionary *data = (NSDictionary*)responseObject;
            if (data) {
                NSString* inAppEvent = [data objectForKey:@"inapp_event"];
                if (inAppEvent && ![inAppEvent isEqualToString:@""]) {
#ifdef FIREBASE_PLUS
                    [self triggerInAppMessage:inAppEvent];
#endif
                }
                
                NSString* event = [data objectForKey:@"event"];
                if (event && ![event isEqualToString:@""]) {
                    [self logEvent:event];
                }
                
                NSDictionary* inAppMessage = [data objectForKey:@"inapp_message"];
                if (inAppMessage && self->_inAppMessageDelegate) {
                    NSString* inAppMessageString = [SDKJSONHelper toJSONString:inAppMessage];
                    if (inAppMessageString) {
                        [self->_inAppMessageDelegate inAppMessageEvent:inAppMessageString];
                    }
                }
            }
        }
    } failure:^(NSError *error) {
        DLog(@"save user attribution exception %@", error.description);
    }];
}

-(nonnull NSString*) getFirebaseUserId
{
#ifdef FIREBASE_PLUS
    FIRUser* firUser =  [[FIRAuth auth] currentUser];
    if (firUser == nil) {
        return @"";
    }
    return [firUser uid];
#else
    return @"";
#endif
}

-(void)triggerInAppMessage:(nonnull NSString*)eventName {
#ifdef FIREBASE_PLUS
    [FIRInAppMessaging.inAppMessaging triggerEvent:eventName];
#endif
}

-(void) inAppMessageDismissed
{
#ifdef FIREBASE_PLUS
    [_firebaseInAppMessage inAppMessageDismissed];
    if (_inAppMessageDelegate) {
        [_inAppMessageDelegate inAppMessageDismissed];
    }
#endif
}

-(void) inAppMessageClicked
{
#ifdef FIREBASE_PLUS
    [_firebaseInAppMessage inAppMessageClicked];
    if (_inAppMessageDelegate) {
        [_inAppMessageDelegate inAppMessageClicked];
    }
#endif
}

- (void)inAppMessageEvent:(nonnull NSString *)dataJson {
#ifdef FIREBASE_PLUS
    if (_inAppMessageDelegate) {
        [_inAppMessageDelegate inAppMessageEvent:dataJson];
    }
#endif
}


- (void)onInAppMessageDisplayed:(nonnull NSString *)dataJson {
#ifdef FIREBASE_PLUS
    if (_inAppMessageDelegate) {
        [_inAppMessageDelegate onInAppMessageDisplayed:dataJson];
    }
#endif
}

-(void)callCloudFunction: (nonnull NSString *)method params:(NSString *)params
{
#ifdef FIREBASE_PLUS
    [_firebaseFunctions call:method params:params handler: _cloudFunctionDelegate];
#endif
}

-(void) updateFirestore:(nonnull NSString *)collection transactionId: (nonnull NSString *)transactionId jsonData:(nonnull NSString *)jsonData
{
#ifdef FIREBASE_PLUS
    [_fireStore updateFirestore: collection transactionId: transactionId jsonData:jsonData handler:_firestoreDelegate];
#endif
}

-(void) setFirestore:(nonnull NSString *)collection jsonData:(nonnull NSString *)jsonData
{
#ifdef FIREBASE_PLUS
    [_fireStore setFirestore: collection jsonData:jsonData handler:_firestoreDelegate];
#endif
}

-(void) mergeFirestore:(nonnull NSString *)collection jsonData:(nonnull NSString *)jsonData
{
#ifdef FIREBASE_PLUS
    [_fireStore mergeFirestore: collection jsonData:jsonData handler:_firestoreDelegate];
#endif
}

-(void) deleteFirestore:(nonnull NSString *)collection
{
#ifdef FIREBASE_PLUS
    [_fireStore deleteFirestore: collection handler:_firestoreDelegate];
#endif
}

-(void) readFirestore:(nonnull NSString *)collection
{
#ifdef FIREBASE_PLUS
    [_fireStore readFirestore: collection handler:_firestoreDelegate];
#endif
}

-(void) readFirestore:(nonnull NSString *)collection document:(NSString*)document
{
#ifdef FIREBASE_PLUS
    [_fireStore readFirestore: collection document:document handler:_firestoreDelegate];
#endif
}

-(void) snapshotFirestore:(nonnull NSString *)collection
{
#ifdef FIREBASE_PLUS
    [_fireStore snapshotFirestore: collection handler:_firestoreDelegate];
#endif
}

-(void) initFirestoreAfterSignIn:(nonnull NSString *)provider
{
#ifdef FIREBASE_PLUS
    if ([provider isEqualToString:@"apple"]) {
        // Initialize a Firebase credential.
        FIRUser* currentUser = [[FIRAuth auth] currentUser];
        NSString* appleId = [self getSignedAppleUID];
        if ((currentUser != nil) && ![appleId isEqualToString:@""]) {
            [self->_firestoreDelegate onFirestoreConnected: appleId];
        } else {
            [self performAppleIdSignIn];
        }
    } else if ([provider isEqualToString:@"facebook"]) {
        [self initializeAndLinkFacebookAfterSignIn];
    } else {
        [_fireStore initFirestoreAfterSignIn: provider handler:_firestoreDelegate];
    }
#endif
}

-(void) loginAppleBeforeFirestore:(bool) isForce {
    //强制登录
    if(isForce){
        [self performAppleIdSignIn];
        return;
    }
#ifdef FIREBASE_PLUS
    FIRUser* currentUser = [[FIRAuth auth] currentUser];
    NSString* appleId = [self getSignedAppleUID];
    //firebase store的user不为空，并且本地存储了appid
    //游戏没有卸载重装
    if ((currentUser != nil) && ![appleId isEqualToString:@""]) {
        if (_snsDelegate && [_snsDelegate respondsToSelector:@selector(signInAppleSuccess:)]) {
            dispatch_async(dispatch_get_main_queue(), ^{
                [self->_snsDelegate signInAppleSuccess:appleId];
            });
        }
    } else {
        [self performAppleIdSignIn];
    }
#endif
}

#pragma mark -
#pragma mark Analysis
-(SDKAnalyse *)getAnalyse:(NSString *)platform
{
    if (_analysesMap) {
        return [_analysesMap objectForKey:platform];
    }
    return nil;
}

-(NSArray *)_AnalyseQueue:(NSNumber *)targets
{
    NSMutableArray *arr = [[NSMutableArray alloc] init];
    NSArray *platforms = @[SDK_ANALYSE_FIREBASE, SDK_ANALYSE_APPSFLYER, SDK_ANALYSE_IVY];
    if (targets) {
        switch (targets.intValue) {
            case 0:
                platforms = @[];
                break;
            case 1:
                platforms = @[SDK_ANALYSE_FIREBASE];
                break;
            case 2:
                platforms = @[SDK_ANALYSE_IVY];
                break;
            case 3:
                platforms = @[SDK_ANALYSE_FIREBASE, SDK_ANALYSE_APPSFLYER, SDK_ANALYSE_FACEBOOK];
                break;
            case 4:
                platforms = @[SDK_ANALYSE_FIREBASE, SDK_ANALYSE_APPSFLYER, SDK_ANALYSE_FACEBOOK, SDK_ANALYSE_IVY];
                break;
            case 9:
                platforms = @[SDK_ANALYSE_FACEBOOK];
                break;
            case 10:
                platforms = @[SDK_ANALYSE_APPSFLYER];
                break;
            default:
                break;
        }
    }
    for (NSString *platform in platforms) {
        SDKAnalyse *analyse = [self getAnalyse:platform];
        if (analyse) {
            [arr addObject:analyse];
        }
    }
    return arr;
}

-(NSArray *)analyseQueue:(NSString *)eventId
{
    NSArray *result = nil;
    if (!_analysesQueueMap) {
        _analysesQueueMap = [[NSMutableDictionary alloc] init];
    }
    NSNumber *targets = nil;
    if (_eventTargets) {
        targets = [_eventTargets objectForKey:eventId];
    }
    targets = targets ? targets : [NSNumber numberWithInt:-1];
    result = [_analysesQueueMap objectForKey:targets.stringValue];
    if (!result) {
        result = [self _AnalyseQueue:targets];
        [_analysesQueueMap setObject:result forKey:targets.stringValue];
    }
    return result;
}

-(void)logEventWithoutCheck:(NSString *)eventId
{
    NSArray *_analyses = [self analyseQueue:eventId];
    if(_analyses && _analyses.count > 0) {
        for (int i=0,n=(int)_analyses.count; i<n; i++) {
            SDKAnalyse *_analyse = [_analyses objectAtIndex:i];
            [_analyse logEvent:eventId];
        }
    }
    
    // check the facebook event Mapping
    if (_eventFacebookMapping && [_eventFacebookMapping objectForKey : eventId]) {
        NSString* newEventId = [_eventFacebookMapping objectForKey : eventId];
        SDKAnalyse *_analyse = [self getAnalyse:SDK_ANALYSE_FACEBOOK];
        if(_analyse) {
            [_analyse logEvent:newEventId];
        }
    }
}

-(void)logIvyEvent:(nullable NSString *)eventId
{
    if (_iPad && isPad) {
        eventId = [NSString stringWithFormat:@"ipad_%@", eventId];
    }
    [self checkAutoEvent:eventId];
    SDKAnalyse *_analyse = [self getAnalyse:SDK_ANALYSE_IVY];
    if(_analyse) {
        [_analyse logEvent:eventId];
    }
}

-(void)logIvyEvent:(nonnull NSString *)eventId withData:(nullable NSDictionary<NSString *, id> *) data
{
    if (_iPad && isPad) {
        eventId = [NSString stringWithFormat:@"ipad_%@", eventId];
    }
    SDKAnalyse *_analyse = [self getAnalyse:SDK_ANALYSE_IVY];
    if(_analyse) {
        [_analyse logEvent:eventId withData:data];
    }
}

-(void)logEventToParfka:(nonnull NSString *)eventId withData:(nullable NSDictionary<NSString *, id> *) data
{
    [self logIvyEvent:eventId withData:data];
}

- (void)logFirebaseEvent:(NSString *)eventId
{
    SDKAnalyse *_analyse = [self getAnalyse:SDK_ANALYSE_FIREBASE];
    if(_analyse) {
        [_analyse logEvent:eventId];
    }
}

- (void)logFirebaseEvent:(NSString *)eventId withData:(NSDictionary<NSString *,id> *)data
{
    SDKAnalyse *_analyse = [self getAnalyse:SDK_ANALYSE_FIREBASE];
    if(_analyse) {
        [_analyse logEvent:eventId withData:data];
    }
}

-(void)trackScreen:(nonnull NSString*)screenClass screenName:(nonnull NSString*)screenName
{
    NSArray *_analyses = [self analyseQueue:screenName];
    if(_analyses && _analyses.count > 0) {
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse trackScreen:screenClass screenName:screenName];
        }
    }
}

-(void)logEvent:(nullable NSString *)eventId
{
    if (_iPad && isPad) {
        eventId = [NSString stringWithFormat:@"ipad_%@", eventId];
    }
    NSArray *_analyses = [self analyseQueue:eventId];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:eventId];
        for (int i=0,n=(int)_analyses.count; i<n; i++) {
            SDKAnalyse *_analyse = [_analyses objectAtIndex:i];
            [_analyse logEvent:eventId];
        }
    }
    [self recordEventConversion:eventId withData:nil];
}

-(void)logEvent:(nonnull NSString *)eventId withData:(nullable NSDictionary<NSString *, id> *) data
{
    if (_iPad && isPad) {
        eventId = [NSString stringWithFormat:@"ipad_%@", eventId];
    }
    NSArray *_analyses = [self analyseQueue:eventId];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:eventId];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logEvent:eventId withData:data];
        }
    }
    [self recordEventConversion:eventId withData:data];
}

-(void)logEvent:(nullable NSString *)eventId action:(nullable NSString *)tag
{
    if (_iPad && isPad) {
        eventId = [NSString stringWithFormat:@"ipad_%@", eventId];
    }
    NSArray *_analyses = [self analyseQueue:eventId];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:eventId];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logEvent:eventId action:tag];
        }
    }
}
- (void)logEvent:(NSString *)eventId action:(NSString *)action label:(NSString *)label value:(long)value
{
    if (_iPad && isPad) {
        eventId = [NSString stringWithFormat:@"ipad_%@", eventId];
    }
    NSArray *_analyses = [self analyseQueue:eventId];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:eventId];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logEvent:eventId action:action label:label value:value];
        }
    }
}

-(void)logError:(int)errorcode errorDomain:(NSString *)errorDomain reason:(NSString *)reason description:(NSString *)desc suggest:(NSString *)suggest
{
    NSArray *_analyses = [self analyseQueue:errorDomain];
    if(_analyses && _analyses.count > 0) {
        for (int i=0,n=(int)_analyses.count; i<n; i++) {
            SDKAnalyse *_analyse = [_analyses objectAtIndex:i];
            [_analyse logError:errorcode errorDomain:errorDomain reason:reason description:desc suggest:suggest];
        }
    }
}

-(void)logPlayerLevel:(int)levelId
{
    NSArray *_analyses = [self analyseQueue:@""];
    if(_analyses && _analyses.count > 0) {
        for (int i=0,n=(int)_analyses.count; i<n; i++) {
            SDKAnalyse *_analyse = [_analyses objectAtIndex:i];
            [_analyse logPlayerLevel:levelId];
        }
    }
}
-(void)logPageStart:(nullable NSString *)pageName
{
    NSArray *_analyses = [self analyseQueue:pageName];
    if(_analyses && _analyses.count > 0) {
        for (int i=0,n=(int)_analyses.count; i<n; i++) {
            SDKAnalyse *_analyse = [_analyses objectAtIndex:i];
            [_analyse logPageStart:pageName];
        }
    }
}
-(void)logPageEnd:(nullable NSString *)pageName
{
    NSArray *_analyses = [self analyseQueue:pageName];
    if(_analyses && _analyses.count > 0) {
        for (int i=0,n=(int)_analyses.count; i<n; i++) {
            SDKAnalyse *_analyse = [_analyses objectAtIndex:i];
            [_analyse logPageEnd:pageName];
        }
    }
}

-(void)checkAutoEvent:(nonnull NSString *)eventId
{
    if (_autoEvents) {
        SDKAutoEvent *e = [_autoEvents objectForKey:eventId];
        if (e) {
            [e check];
        }
    }
}

-(void)logStartLevel:(nullable NSString *)level
{
    NSArray *_analyses = [self analyseQueue:level];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"level_start"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logStartLevel:level];
        }
    }
}
-(void)logFailLevel:(nullable NSString *)level
{
    NSArray *_analyses = [self analyseQueue:level];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"level_fail"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logFailLevel:level];
        }
    }
}
-(void)logFinishLevel:(nullable NSString *)level
{
    NSArray *_analyses = [self analyseQueue:level];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"level_end"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logFinishLevel:level];
        }
    }
}
-(void)logFinishAchievement:(nonnull NSString *)achievement
{
    NSArray *_analyses = [self analyseQueue:achievement];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"unlock_achievement"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logFinishAchievement:achievement];
        }
    }
}
-(void)logFinishTutorial:(nonnull NSString *)tutorial
{
    NSArray *_analyses = [self analyseQueue:tutorial];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"tutorial_complete"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logFinishTutorial:tutorial];
        }
    }
}
-(void)logRate:(CGFloat)star
{
    NSArray *_analyses = [self analyseQueue:@"rate"];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"rate"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logRate:star];
        }
    }
}
-(void)_setUserProps
{
    CTTelephonyNetworkInfo *info = [[CTTelephonyNetworkInfo alloc] init];
    CTCarrier *carrier = [info subscriberCellularProvider];
    NSString *mcc = [carrier mobileCountryCode]; // 国家码 如：460
    NSString *mnc = [carrier mobileNetworkCode]; // 网络码 如：01
    mcc = mcc ? mcc : @"0";
    mnc = mnc ? mnc : @"0";
    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
    NSString *appid = [(NSNumber *)[_config objectForKey:@"appid"] stringValue];
    NSString *build = [infoDictionary objectForKey:(NSString *)kCFBundleVersionKey];
    NSString *package = [infoDictionary objectForKey:(NSString *)kCFBundleIdentifierKey];
    NSString *version = [infoDictionary objectForKey:@"CFBundleVersion"];
    NSString *country = [SDKHelper getCountryCode];
    NSString *uuid = [[[UIDevice currentDevice] identifierForVendor] UUIDString];
    NSString *lang = [SDKHelper getLangcode];
    NSString *model = [SDKDebug deviceModel];
    NSString *sysVersion = [[UIDevice currentDevice] systemVersion];
    NSString *idfa = [SDKHelper getIDFAString];
    NSString *idfv = [SDKHelper getIDFVString];
    [self setUserPropertyString:appid forName:@"appid"];
    [self setUserPropertyString:package forName:@"package"];
    [self setUserPropertyString:version forName:@"version"];
    [self setUserPropertyString:build forName:@"build"];
    [self setUserPropertyString:country forName:@"country"];
    [self setUserPropertyString:lang forName:@"language"];
    [self setUserPropertyString:uuid forName:@"uuid"];
    [self setUserPropertyString:model forName:@"model"];
    [self setUserPropertyString:sysVersion forName:@"ios"];
    [self setUserPropertyString:(isPad ? @"ipad" : @"iphone") forName:@"device_type"];
    [self setUserPropertyString:mcc forName:@"mcc"];
    [self setUserPropertyString:mnc forName:@"mnc"];
    [self setUserPropertyString:idfa forName:@"idfa"];
    [self setUserPropertyString:idfv forName:@"idfv"];
}
-(void)logAdRevenue:(nonnull NSString *)adClassName mediationType:(SDK_MEDIATION_TYPE)mType adType:(nonnull NSString *)adType adUnit:(nonnull NSString *)adUnit placement:(nonnull NSString *)placement country:(nonnull NSString *)country currency:(nonnull NSString *)currency value:(NSNumber * _Nonnull)value
{
    [self logEvent:@"gms_ad_paid_event" withData: @{
        @"ad_network": adClassName,
        @"ad_format": adType,
        @"placement" : placement,
        @"adunit": adUnit,
        @"currency": currency,
        @"value": value
    }];
    
    @try{
        NSMutableDictionary * dict = [[NSMutableDictionary alloc] init];
        [dict setValue:adClassName forKey:@"ad_network"];
        [dict setValue:adType forKey:@"ad_format"];
        [dict setValue:placement forKey:@"placement"];
        [dict setValue:adUnit forKey:@"adunit"];
        [dict setValue:currency forKey:@"currency"];
        [dict setValue:value forKey:@"value"];
        NSError *error;
        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dict options:0 error:&error];
        if (jsonData != nil && error == nil) {
            NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
            if (_adDelegate) {
                [_adDelegate adPaid:jsonString];
            }
        } else if (error != nil) {
            NSLog(@"Error converting to JSON string: %@", error.localizedDescription);
        }
    } @catch (NSException * e){
        
    }
    
    
//#ifdef APPSFLYER
//    if (_af_adrevenue) {
//        AppsFlyerAdRevenueMediationNetworkType amType = AppsFlyerAdRevenueMediationNetworkTypeGoogleAdMob;
//        switch(mType) {
//            case SdkMediationNetworkTypeApplovinMax:
//                amType = AppsFlyerAdRevenueMediationNetworkTypeApplovinMax;
//                break;
//            default:
//                amType = AppsFlyerAdRevenueMediationNetworkTypeGoogleAdMob;
//                break;
//        }
//        [[AppsFlyerAdRevenue shared] logAdRevenueWithMonetizationNetwork:adClassName mediationNetwork:amType eventRevenue:value revenueCurrency:currency additionalParameters:@{
//                    kAppsFlyerAdRevenueAdType: adType,
//                    kAppsFlyerAdRevenueCountry: country,
//                    kAppsFlyerAdRevenueAdUnit: adUnit,
//                    kAppsFlyerAdRevenuePlacement: placement}];
//    }
//#endif
}
-(void)logAdClick:(nonnull NSString *)adType adPos:(nonnull NSString *)adPos platform:(nonnull NSString *)platform
{
    NSDictionary *params = @{@"ad_type" : adType, @"ad_pos" : (adPos ? adPos : @"null"), @"platform" : platform, @"fst": [NSString stringWithFormat:@"%ld", self.firstOpenTime]};
    BOOL isCross = [platform isEqualToString:@"our"] || [platform isEqualToString:@"delicious"];
    BOOL isVideo = [adType isEqualToString:@"video"];
    BOOL isInter = [adType isEqualToString:@"interstitial"];
    NSString *eventId = nil;
    if (isCross) {
        eventId = @"sdk_cross_click";
    } else {
        if (isVideo) {
            eventId = @"video_clicked";
        } else if(isInter) {
            eventId = @"interstitial_clicked";
        }
    }
    
    if (eventId) {
        if (_iPad && isPad) {
            eventId = [NSString stringWithFormat:@"ipad_%@", eventId];
        }
        [self checkAutoEvent:eventId];
        NSArray *_analyses = [self analyseQueue:eventId];
        if(_analyses && _analyses.count > 0) {
            for (SDKAnalyse *_analyse in _analyses) {
                [_analyse logAdClick:eventId params:params];
            }
        }
    }
}
-(void)logAdImpression:(nonnull NSString *)adType adPos:(nonnull NSString *)adPos platform:(nonnull NSString *)platform
{
    NSDictionary *params = @{@"ad_type" : adType, @"ad_pos" : (adPos ? adPos : @"null"), @"platform" : platform, @"fst": [NSString stringWithFormat:@"%ld", self.firstOpenTime]};
    BOOL isCross = [platform isEqualToString:@"our"] || [platform isEqualToString:@"delicious"];
    BOOL isVideo = [adType isEqualToString:@"video"];
    BOOL isInter = [adType isEqualToString:@"interstitial"];
    NSString *eventId = nil;
    if (isCross) {
        eventId = @"sdk_cross_show";
    } else {
        if (isVideo) {
            eventId = @"video_shown";
        } else if(isInter) {
            eventId = @"interstitial_shown";
        }
    }
    if (eventId) {
        if (_iPad && isPad) {
            eventId = [NSString stringWithFormat:@"ipad_%@", eventId];
        }
        [self checkAutoEvent:eventId];
        NSArray *_analyses = [self analyseQueue:eventId];
        if(_analyses && _analyses.count > 0) {
            for (SDKAnalyse *_analyse in _analyses) {
                [_analyse logAdImpression:eventId params:params];
            }
        }
    }
}
-(void)logStartPay:(nonnull NSDictionary *)data
{
    NSArray *_analyses = [self analyseQueue:@"begin_checkout"];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"begin_checkout"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logStartPay:data];
        }
    }
}

-(void)logPay:(int)paymentId productIdentifier:(NSString *)productIdentifier transactionIdentifier:(NSString *)transactionIdentifier
{
    SDKCache *cache = [SDKCache cache];
    id tmp = [cache objectForKey:PAY_SUCCESS_COUNT];
    int count = tmp ? [(NSNumber *)tmp intValue]: 0;
    count = count + 1;
    NSArray *arr = [self getTrackAdCountArray:@"aps"];
    if (arr && arr.count > 0) {
        for (int i=0; i<arr.count; i++) {
            long value = [(NSNumber *)[arr objectAtIndex:i] intValue];
            if (count == value) {
                [self logEvent:[NSString stringWithFormat:@"aps%d", count]];
                break;
            }
        }
    }
    tmp = [cache objectForKey:PURCHASED_REVENUE];
    int revenue = tmp ? [(NSNumber *)tmp intValue]: 0;
    [cache setObject:[NSNumber numberWithInt:count] forKey:PAY_SUCCESS_COUNT];
    NSArray *_analyses = [self analyseQueue:@"in_app_purchase"];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"in_app_purchase"];
        NSDictionary *data = [self getPaymentData:paymentId];
        NSString *currency = [data objectForKey:@"currency"];
        NSNumber *sub = [data objectForKey:@"sub"];
        NSNumber *price = [data objectForKey:@"price_amount"];
        
        NSNumber *usd = [data objectForKey:@"usd"];
        if (usd != nil) {
            price = usd;
            currency = [data objectForKey:@"usd_currency"];
            currency = currency ? currency : @"USD";
        }
        revenue = revenue + price.intValue;
        [cache setObject:[NSNumber numberWithInt:revenue] forKey:PURCHASED_REVENUE];
        BOOL isSubscribe = sub ? sub.intValue > 0 : false;
        if (isSubscribe) {
            NSMutableDictionary *tmp = [NSMutableDictionary dictionaryWithDictionary:data];
            [tmp setObject:transactionIdentifier forKey:@"orderId"];
            data = tmp;
        }
        NSString *title = [data objectForKey:@"id"];
        BOOL isFirstPurchase = ![cache objectForKey:HAS_PURCHASED];
        if (isFirstPurchase) {
            [cache setObject:[NSNumber numberWithBool:YES] forKey:HAS_PURCHASED];
            [self sendRecordEventConversion:@"iap_purchased" withData:@{
                @"itemid": productIdentifier,
                @"transactionIdentifier": transactionIdentifier,
                @"catalog":@"first_purchase"
            }];
        } else {
            [self sendRecordEventConversion:@"iap_purchased" withData:@{
                @"itemid": productIdentifier,
                @"transactionIdentifier": transactionIdentifier
            }];
        }
        for (SDKAnalyse *_analyse in _analyses) {
            if (isSubscribe) {
                [_analyse logSubscribe:data];
            }
            if (@available(iOS 10.0, *)) {
                [_analyse logPay:productIdentifier price:price name:title number:1 currency:currency];
            } else {
                // Fallback on earlier versions
                [_analyse logPay:productIdentifier price:price name:title number:1 currency:currency];
            }
        }
    }
}
-(void)logBuy:(nullable NSString *)itemName itemType:(nonnull NSString *)itemType count:(int)count price:(double)price
{
    NSArray *_analyses = [self analyseQueue:@"buy"];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"buy"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logBuy:itemName itemType:itemType count:count price:price];
        }
    }
}
-(void)logUse:(nullable NSString *)itemName number:(int)number price:(double)price
{
    NSArray *_analyses = [self analyseQueue:@"use"];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"use"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logUse:itemName number:number price:price];
        }
    }
}
-(void)logBonus:(nullable NSString *)itemName number:(int)number price:(double)price trigger:(int)trigger
{
    NSArray *_analyses = [self analyseQueue:@"bonus"];
    if(_analyses && _analyses.count > 0) {
        [self checkAutoEvent:@"bonus"];
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logBonus:itemName number:number price:price trigger:trigger];
        }
    }
}

-(void)logRegister:(nullable NSString *)channel
{
    NSArray *_analyses = [self analyseQueue:channel];
    if(_analyses && _analyses.count > 0) {
        for (SDKAnalyse *_analyse in _analyses) {
            [_analyse logRegister:channel];
        }
    }
}

#pragma mark -
#pragma mark Remote Config
-(void)setDefaults:(NSDictionary *)data
{
    if([data isKindOfClass:[NSDictionary class]]) {
        if (_remoteConfig) {
            [_remoteConfig setDefaults:data];
        }
        if (_sdkRemoteConfig) {
            [_sdkRemoteConfig setDefaults:data];
        }
    }
}

-(nonnull NSSet *)remoteConfigKeysWithPrefix:(nullable NSString *)prefix
{
    if (_remoteConfig) {
        return [_remoteConfig remoteConfigKeysWithPrefix:prefix];
    }
    if (_sdkRemoteConfig) {
        return [_sdkRemoteConfig remoteConfigKeysWithPrefix:prefix];
    }
    return [[NSMutableSet alloc] init];
}

-(int)getRemoteConfigIntValue:(nonnull NSString *)key
{
    if (_remoteConfig) {
        return [_remoteConfig getRemoteConfigIntValue:key];
    }
    if (_sdkRemoteConfig) {
        return [_sdkRemoteConfig getRemoteConfigIntValue:key];
    }
    return 0;
}

-(long)getRemoteConfigLongValue:(nonnull NSString *)key
{
    if (_remoteConfig) {
        return [_remoteConfig getRemoteConfigLongValue:key];
    }
    if (_sdkRemoteConfig) {
        return [_sdkRemoteConfig getRemoteConfigLongValue:key];
    }
    return 0;
}

-(double)getRemoteConfigDoubleValue:(nonnull NSString *)key
{
    if (_remoteConfig) {
        return [_remoteConfig getRemoteConfigDoubleValue:key];
    }
    if (_sdkRemoteConfig) {
        return [_sdkRemoteConfig getRemoteConfigDoubleValue:key];
    }
    return 0;
}

-(BOOL)getRemoteConfigBoolValue:(nonnull NSString *)key
{
    if (_remoteConfig) {
        return [_remoteConfig getRemoteConfigBoolValue:key];
    }
    if (_sdkRemoteConfig) {
        return [_sdkRemoteConfig getRemoteConfigBoolValue:key];
    }
    return NO;
}

-(nonnull NSString *)getRemoteConfigStringValue:(nonnull NSString *)key
{
    if (_remoteConfig) {
        return [_remoteConfig getRemoteConfigStringValue:key];
    }
    if (_sdkRemoteConfig) {
        return [_sdkRemoteConfig getRemoteConfigStringValue:key];
    }
    return @"";
}

-(void)setUserPropertyString:(NSString *)value forName:(NSString *)key
{
    if (_remoteConfig && value) {
        [_remoteConfig setUserPropertyString:value forName:key];
    }
    if ([key isEqualToString:@"af_customer_user_id"]) {
        _userId = value;
    } else if ([key isEqualToString:@"firebase_userId"]) {
        _userId = value;
    }
}

-(BOOL)hasNewVersion
{
    @try {
        NSString *newVersion = [self.config objectForKey:@"version"];
        NSString *localVersion = [[[NSBundle mainBundle]infoDictionary] objectForKey:@"CFBundleShortVersionString"];//本地版本
        if (!newVersion) {
            newVersion = @"0";
        }
        //对比发现的新版本和本地的版本
        NSInteger result = [newVersion compare:localVersion];
        if (result == NSOrderedAscending){//localVersion > newVersion
            return NO;
        }else if (result == NSOrderedSame){ //localVersion = newVersion
            return NO;
        }else if (result == NSOrderedDescending){//localVersion < newVersion
            return YES;
        }
        return NO;
    } @catch (NSException *exception) {
    } @finally {
    }
    return NO;
}

-(NSString *)getAppstoreVersion
{
    NSNumber *appstoreId = [self.config objectForKey:@"appstoreid"];
    if (!appstoreId) {
        return [self getConfig:SDK_CONFIG_KEY_VERSION_NAME];
    }
    NSString *newVersion = (NSString *)[[SDKCache cache] objectForKey:SDK_APPSTORE_APPVERSION];//appstore版本
    NSString *url = [NSString stringWithFormat:@"http://itunes.apple.com/lookup?id=%@",appstoreId];//这个URL地址是该app在iTunes connect里面的相关配置信息。其中id是该app在app store唯一的ID编号。
    [[SDKNetworkHelper sharedHelper] GET:url parameters:nil jsonRequest:false jsonResponse:true success:^(id  _Nullable responseObject) {
        if (responseObject) {
            @try {
                NSArray *array = responseObject[@"results"];
                for (NSDictionary *dic in array) {
                    NSNumber *trackId = [dic objectForKey:@"trackId"];
                    if (trackId && trackId.longValue == appstoreId.longValue) {
                        NSString *version = [dic objectForKey:@"version"];
                        if (version) {
                            [[SDKCache cache] setObject:version forKey:SDK_APPSTORE_APPVERSION];
                        }
                        break;
                    }
                }
            } @catch (NSException *exception) {
                DLog(@"Exception: %@", [exception description]);
            } @catch (NSError *error) {
                DLog(@"Error: %@", [error localizedDescription]);
            } @finally {
                
            }
        }
    } failure:^(NSError * _Nullable error) {
        DLog(@"Get appstore version err: %@", error.localizedDescription);
    }];
    return newVersion ? newVersion : [self getConfig:SDK_CONFIG_KEY_VERSION_NAME];
}

#pragma mark - ThirdLogin
- (void)showGameProtocolDialog
{
#ifdef ThirdLogin
    [_thirdLogin showUserProtocolDialog];
#endif
}

- (void)showPrivacyDialog
{
#ifdef ThirdLogin
    [_thirdLogin showPrivacyDialog];
#endif
}

- (void)showUserAgreementDialog
{
#ifdef ThirdLogin
    [_thirdLogin showUserAgreementDialog];
#endif
}

- (void)loginNoAnonymous
{
#ifdef ThirdLogin
    [_thirdLogin startLoginWithAnonymous:false isCancelable:false];
#endif
}

- (void)loginNoAnonymousCancelable
{
#ifdef ThirdLogin
    [_thirdLogin startLoginWithAnonymous:false isCancelable:true];
#endif
}

- (void)setIdCardVerified:(int) age
{
#ifdef ThirdLogin
    [_thirdLogin setIdCardVerified:age];
#endif
}

- (int)getIdCardVerifyedAge
{
#ifdef ThirdLogin
    return [_thirdLogin getIdCardVerifiedAge];
#else
    return 0;
#endif
}

#pragma mark -
#pragma mark Extend Method
- (void)requestPhotoWritePermission:(void (^)(BOOL))result
{
    if (_extend && [_extend respondsToSelector:@selector(requestPhotoWritePermission:)]) {
        [_extend requestPhotoWritePermission:result];
    }
}

- (BOOL)hasPhotoWritePermission
{
    if (_extend && [_extend respondsToSelector:@selector(hasPhotoWritePermission)]) {
        return  [_extend hasPhotoWritePermission];
    }
    return NO;
}

- (void)openSettingPage
{
    if (_extend && [_extend respondsToSelector:@selector(openSettingPage)]) {
        [_extend openSettingPage];
    }
}

-(BOOL)isRecording
{
    if (_extend && [_extend respondsToSelector:@selector(isRecording)]) {
       return  [_extend isRecording];
    }
    return NO;
}

- (void)startRecording
{
    if (_extend && [_extend respondsToSelector:@selector(startRecording)]) {
        [_extend startRecording];
    }
}

- (void)pauseRecording
{
    if (_extend && [_extend respondsToSelector:@selector(pauseRecording)]) {
        [_extend pauseRecording];
    }
}

- (void)continueRecording
{
    if (_extend && [_extend respondsToSelector:@selector(continueRecording)]) {
        [_extend continueRecording];
    }
}

- (void)stopRecording:(bool_result_block)complete
{
    if (_extend && [_extend respondsToSelector:@selector(stopRecording:)]) {
        [_extend stopRecording:complete];
    }
}

- (void)editVideo:(NSString *)path complete:(void (^)(NSString * _Nullable))complete
{
    if (_extend && [_extend respondsToSelector:@selector(editVideo:complete:)]) {
        [_extend editVideo:path complete:complete];
    }
}

- (BOOL)sharePhotos:(NSArray *)photoIdentifiers result:(void (^)(BOOL, NSString * _Nullable))result
{
    if (_extend && [_extend respondsToSelector:@selector(sharePhotos:result:)]) {
        return [_extend sharePhotos:photoIdentifiers result:result];
    }
    return false;
}

- (BOOL)shareVideos:(NSArray *)videoIdentifiers result:(void (^)(BOOL, NSString * _Nullable))result
{
    if (_extend && [_extend respondsToSelector:@selector(shareVideos:result:)]) {
        return [_extend shareVideos:videoIdentifiers result:result];
    }
    return false;
}

- (void)shareRecentRecordVideo:(void (^)(BOOL, NSString * _Nullable))result
{
    if (_extend && [_extend respondsToSelector:@selector(shareRecentRecordVideo:)]) {
        [_extend shareRecentRecordVideo:result];
    }
}

- (void)showPromotion:(CGPoint)position
{
    if (_extend && [_extend respondsToSelector:@selector(showPromotion:)]) {
        [_extend showPromotion:position];
    }
}

#pragma mark -
#pragma mark 实名认证
-(int)getIdCardVerifiedAge
{
#if VerifyIdCard
    return [SDKVerifyIdCard sharedInstance].age;
#elif ThirdLogin
    return [_thirdLogin getIdCardVerifiedAge];
#else
    return 0;
#endif
}

-(void)setOnIdCardVerifiedDelegate:(dispatch_block_t)block
{
#if VerifyIdCard
    [SDKVerifyIdCard sharedInstance].onIdCardVerified = block;
#endif
}

-(void)verifyIdCard:(nonnull NSString *) uuid
{
#if VerifyIdCard
    [[SDKVerifyIdCard sharedInstance] verifyIdCard];
#elif ThirdLogin
    return [_thirdLogin startVerifyIdCard:uuid];
#endif
}

-(void)resetVerifyIdCard
{
#if VerifyIdCard
    [[SDKVerifyIdCard sharedInstance] resetVerifyIdCard];
#elif ThirdLogin
    return [_thirdLogin resetVerifyIdCard];
#endif
}

#pragma mark -
#pragma mark Other
-(nullable id)getConf:(nonnull NSString *)key
{
    if (_config) {
        return [_config objectForKey:key];
    }
    return nil;
}

-(void) openApplicationSetting{
    NSURL* url = [NSURL URLWithString:UIApplicationOpenSettingsURLString];
    if(@available(iOS 10.0, *)) {
        [[UIApplication sharedApplication] openURL:url options:@{} completionHandler:nil];
    }else {
        [[UIApplication sharedApplication] openURL:url];
    }
}

-(nullable NSString *)getConfig:(SDK_CONFIG_ID)cid;
{
    NSString *result = nil;
    switch (cid) {
        case SDK_CONFIG_KEY_APP_ID:
            if(self.config) {
                result = [(NSNumber *)[_config objectForKey:@"appid"] stringValue];
            }
            break;
        case SDK_CONFIG_KEY_LANGUAGE:
            result = [SDKHelper getLangcode];
            break;
        case SDK_CONFIG_KEY_COUNTRY:
            result = [SDKHelper getCountryCode];
            break;
        case SDK_CONFIG_KEY_API_VERSION:
            if(self.config) {
                result = [(NSNumber *)[_config objectForKey:@"v_api"] stringValue];
            }
            break;
        case SDK_CONFIG_KEY_PACKAGE_NAME:
            result = [NSBundle mainBundle].bundleIdentifier;
            break;
        case SDK_CONFIG_KEY_SCREEN_WIDTH:
            result = [@([self getScreenWidth]) stringValue];
            break;
        case SDK_CONFIG_KEY_SCREEN_HEIGHT:
            result = [@([self getScreenHeight]) stringValue];
            break;
        case SDK_CONFIG_KEY_VERSION_CODE:
            result = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleVersion"];
            break;
        case SDK_CONFIG_KEY_VERSION_NAME:
            result = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleShortVersionString"];
            break;
        case SDK_CONFIG_KEY_LEADER_BOARD_URL:
            if(self.config) {
                result = [_config objectForKey:@"leaderboard_url"];
            }
            break;
        case SDK_CONFIG_KEY_UUID:
#if DEBUG
            result = [SDKHelper UUIDString];
#else
            result = [SDKHelper getFromKeychain:SDK_UUID];
            if (!result) {
                result = [[[UIDevice currentDevice] identifierForVendor] UUIDString];
                [SDKHelper saveToKeychain:result forKey:SDK_UUID];
            }
#endif
            break;
        case SDK_CONFIG_KEY_FACEBOOK_ID:
            result = _snsAdapter ? [_snsAdapter meId] : nil;
            break;
        case SDK_CONFIG_KEY_CHANNEL:
            if(self.config) {
                result = [_config objectForKey:@"channel"];
            }
            break;
        case SDK_CONFIG_KEY_JSON_VERSION:
            result = _jsonVersion;
            break;
        case SDK_CONFIG_KEY_FIREBASE_USERID:
            result = (NSString *)[[SDKCache cache] objectForKey:SDK_FIREBASE_USERID];
            break;
        default:
            break;
    }
    return result ? result : @"";
}

-(id)getExtraData
{
    if(self.config) {
        return [_config objectForKey:@"data"];
    }
    return nil;
}

-(NSString *)getExtraDataJson
{
    return [SDKJSONHelper toJSONString:[self getExtraData]];
}
-(void)rateUs:(CGFloat)star
{
    [[SDKHelper sharedHelper] openAppStoreId:[self.config objectForKey:@"appstoreid"]];
    [self logRate:star];
}

-(void)inAppReview
{
    if (@available(iOS 10.3, *)) {
        [SKStoreReviewController requestReview];
    } else {
        [[SDKHelper sharedHelper] openAppStoreId:[self.config objectForKey:@"appstoreid"]];
    }
}

-(void)rateInApp:(CGFloat)star
{
    NSNumber* count = [[SDKCache cache] containsObjectForKey:SDK_SYS_RATE_COUNT] ? (NSNumber *)[[SDKCache cache] objectForKey:SDK_SYS_RATE_COUNT] : 0;
    if(count.intValue < 3) {
        if (@available(iOS 10.3, *)) {
            [self logRate:star];
            [SKStoreReviewController requestReview];
            [[SDKCache cache] setObject:[NSNumber numberWithInt:(count.intValue + 1)] forKey:SDK_SYS_RATE_COUNT];
        } else {
            // Fallback on earlier versions
            [self rateUs:star];
        }
    } else {
        [self rateUs:star];
    }
}

#if HasUMP

-(void) initUmpRequest {
    UMPRequestParameters* params = [[UMPRequestParameters alloc] init];
    //默认设置为用户达到同意年龄
    params.tagForUnderAgeOfConsent = NO;
#if APPLOVIN_MAX
    [ALPrivacySettings setHasUserConsent:NO];
#endif
#if DEBUG
//    [UMPConsentInformation.sharedInstance reset];
//    //调试UMP
//    UMPDebugSettings* debugSettings = [[UMPDebugSettings alloc] init];
//    debugSettings.testDeviceIdentifiers = @[@"19D759AD-EFF0-4B23-84E4-513B1187AD29"];
//    debugSettings.geography = UMPDebugGeographyEEA;
//    params.debugSettings = debugSettings;
#endif

    [UMPConsentInformation.sharedInstance requestConsentInfoUpdateWithParameters:params completionHandler:^(NSError * _Nullable error) {
        if(error){
            [self _initAds];
            DLog(@"UMPConsentInformation request:%@", error.localizedDescription);
            return;
        }
        [UMPConsentForm loadAndPresentIfRequiredFromViewController:self.rootVC completionHandler:^(NSError * _Nullable error) {
            if(error){
                //[self initAdsOnce];
                DLog(@"UMPConsentForm present:%@", error.localizedDescription);
                //return;
            }
            [self initAdsOnce];
        }];
    }];
}

//-(void) loadUmpForm{
//    [UMPConsentForm loadWithCompletionHandler:^(UMPConsentForm * _Nullable consentForm, NSError * _Nullable error) {
//        if(error){
//            DLog(@"UMPConsentForm load:%@", error.localizedDescription);
//            return;
//        }
//        UMPFormStatus formStatus = UMPConsentInformation.sharedInstance.formStatus;
//        if(formStatus != UMPConsentStatusNotRequired){
//            DLog(@"UMPConsentForm load status:%ld", formStatus);
//            return;
//        }
//        [consentForm presentFromViewController:self.rootVC completionHandler:^(NSError * _Nullable error) {
//            UMPFormStatus formStatus = UMPConsentInformation.sharedInstance.formStatus;
//            if(formStatus != UMPConsentStatusObtained){
//                [self loadUmpForm];
//                return;
//            }
//#if APPLOVIN_MAX
//            [ALPrivacySettings setHasUserConsent:YES];
//#endif
//        }];
//    }];
//}
#endif
-(int)getScreenWidth
{
    return [UIScreen mainScreen].currentMode.size.width;
}

-(int)getScreenHeight
{
    return [UIScreen mainScreen].currentMode.size.height;
}

-(int)getScreenDesignWidth
{
    return Screen_width;
}

-(int)getScreenDesignHeight
{
    return Screen_height;
}

- (NSString*)deviceVersion
{
    struct utsname systemInfo;
    uname(&systemInfo);
    NSString * deviceString = [NSString stringWithCString:systemInfo.machine encoding:NSUTF8StringEncoding];
    //iPhone
    if ([deviceString isEqualToString:@"iPhone1,1"])    return @"iPhone 1G";
    if ([deviceString isEqualToString:@"iPhone1,2"])    return @"iPhone 3G";
    if ([deviceString isEqualToString:@"iPhone2,1"])    return @"iPhone 3GS";
    if ([deviceString isEqualToString:@"iPhone3,1"])    return @"iPhone 4";
    if ([deviceString isEqualToString:@"iPhone3,2"])    return @"iPhone 4";
    if ([deviceString isEqualToString:@"iPhone4,1"])    return @"iPhone 4S";
    if ([deviceString isEqualToString:@"iPhone5,1"])    return @"iPhone 5";
    if ([deviceString isEqualToString:@"iPhone5,2"])    return @"iPhone 5";
    if ([deviceString isEqualToString:@"iPhone5,3"])    return @"iPhone 5C";
    if ([deviceString isEqualToString:@"iPhone5,4"])    return @"iPhone 5C";
    if ([deviceString isEqualToString:@"iPhone6,1"])    return @"iPhone 5S";
    if ([deviceString isEqualToString:@"iPhone6,2"])    return @"iPhone 5S";
    if ([deviceString isEqualToString:@"iPhone7,1"])    return @"iPhone 6 Plus";
    if ([deviceString isEqualToString:@"iPhone7,2"])    return @"iPhone 6";
    if ([deviceString isEqualToString:@"iPhone8,1"])    return @"iPhone 6s";
    if ([deviceString isEqualToString:@"iPhone8,2"])    return @"iPhone 6s Plus";
    if ([deviceString isEqualToString:@"iPhone9,1"])    return @"iPhone 7";
    if ([deviceString isEqualToString:@"iPhone9,2"])    return @"iPhone 7 Plus";
    if ([deviceString  isEqualToString:@"iPhone10,1"])   return @"iPhone 8";
    if ([deviceString  isEqualToString:@"iPhone10,4"])   return @"iPhone 8";
    if ([deviceString  isEqualToString:@"iPhone10,2"])   return @"iPhone 8 Plus";
    if ([deviceString  isEqualToString:@"iPhone10,5"])   return @"iPhone 8 Plus";
    if ([deviceString  isEqualToString:@"iPhone10,3"])   return @"iPhone X";
    if ([deviceString  isEqualToString:@"iPhone10,6"])   return @"iPhone X";
    
    return deviceString;
}

- (BOOL)isNotchScreen {

    if ([UIDevice currentDevice].userInterfaceIdiom == UIUserInterfaceIdiomPad) {
        return NO;
    }

    CGSize size = [UIScreen mainScreen].bounds.size;
    NSInteger notchValue = size.width / size.height * 100;

    if (216 == notchValue || 46 == notchValue) {
        return YES;
    }

    return NO;
}


-(BOOL)hasNotch
{
    return [self isNotchScreen];
    //return [[self class] isIPhoneX]||(UIApplication.sharedApplication.keyWindow.safeAreaInsets.top > 20.f);
    //参考 https://blog.csdn.net/LANGJ1/article/details/123749379
    //修复 跑酷刘海屏判断问题
    //return UIApplication.sharedApplication.keyWindow.safeAreaInsets.top > 20.f;
}

-(int)getNotchHeight{
    return [self hasNotch]?44:0;
}

-(nullable NSString *)fetchDeepLink
{
    return _deepLink;
}

-(BOOL)isNetworkConnected
{
    return [[SDKNetworkHelper sharedHelper] isNetwork];
}

-(void)toast:(NSString *)msg isLongTime:(BOOL)longTime
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [SDKHelper toast:msg isLongTime:longTime];
    });
}

-(NSString *)cacheUrl:(NSString *)url
{
    return [[SDKNetworkHelper sharedHelper] getDownloadPathWithURL:url];
}

-(void)cacheUrl:(nonnull NSString *)tag url:(nonnull NSString *)url succes:(void(^_Nullable)(NSString * _Nonnull tag, NSString * _Nonnull filePath))success failure:(void(^_Nullable)(NSString * _Nonnull tag))failure
{
    if(url && tag) {
        __weak NSString *_tag = tag;
        [[SDKNetworkHelper sharedHelper] downloadWithURL:url progress:nil success:^(NSString *path) {
            if(success) {
                success(_tag ? _tag : @"NULL", path ? path : @"NULL");
            }
        } failure:^(NSError *error) {
            if(failure) {
                failure(_tag ? _tag : @"NULL");
            }
        }];
    }
}

-(void)sendMail:(NSString *)address subject:(NSString *)subject content:(NSString *)content isHTML:(BOOL)isHTML
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [[SDKHelper sharedHelper] sendMail:address subject:subject content:content isHTML:isHTML];
    });
}

-(void)copyToClipboard:(nonnull NSString *)copyStr
{
    [SDKHelper copyToClipboard:copyStr];
}

#pragma mark -
#pragma mark MMKV
-(NSNumber *) mmGetConfuseNumber:(nonnull NSString *)key
{
    NSNumber *num = (NSNumber *)[_confuseNumber objectForKey:key];
    if (!num) {
        num = (NSNumber *)[[SDKCache cache] objectForKey:[NSString stringWithFormat:@"%@%@", SDK_MM_CONFUSE_PREFIX, key]];
    }
    return num != nil ? num : [NSNumber numberWithInt:0];
}
-(NSNumber *) mmResetConfuseNumber:(nonnull NSString *)key size:(int)size
{
    NSNumber *num = [NSNumber numberWithInt:(arc4random() % (size << 1) - size)];
    [_confuseNumber setObject:num forKey:key];
    [[SDKCache cache] setObject:num forKey:[NSString stringWithFormat:@"%@%@", SDK_MM_CONFUSE_PREFIX, key]];
    return num;
}
-(void) mmEnableNumberConfuse:(BOOL)enableConfuse
{
    _mmEnableConfuse = enableConfuse;
    if (_mmEnableConfuse && !_confuseNumber) {
        _confuseNumber = [[NSMutableDictionary alloc] init];
    }
    [[SDKCache cache] setObject:[NSNumber numberWithBool:enableConfuse] forKey:SDK_MM_NUMBER_CONFUSE];
}
-(void) mmSetIntValue:(nonnull NSString *)key value:(int)value
{
    if (_mmEnableConfuse) {
        NSNumber *confuse = [self mmResetConfuseNumber:key size:1000000];
        value += confuse.intValue;
    }
    [[SDKCache cache] setObject:[NSNumber numberWithInt:value] forKey:key];
}
-(int) mmGetIntValue:(nonnull NSString *)key defaultValue:(int)defaultValue
{
    NSNumber *value = (NSNumber *)[[SDKCache cache] objectForKey:key];
    int num = value != nil ? value.intValue : defaultValue;
    if (_mmEnableConfuse) {
        NSNumber *confuse = [self mmGetConfuseNumber:key];
        num -= confuse.intValue;
    }
    return num;
}
-(void) mmSetLongValue:(nonnull NSString *)key value:(long)value
{
    if (_mmEnableConfuse) {
        NSNumber *confuse = [self mmResetConfuseNumber:key size:1000000];
        value += confuse.intValue;
    }
    [[SDKCache cache] setObject:[NSNumber numberWithLong:value] forKey:key];
}
-(long) mmGetLongValue:(nonnull NSString *)key defaultValue:(long)defaultValue
{
    NSNumber *value = (NSNumber *)[[SDKCache cache] objectForKey:key];
    long num = value != nil ? value.longValue : defaultValue;
    if (_mmEnableConfuse) {
        NSNumber *confuse = [self mmGetConfuseNumber:key];
        num -= confuse.intValue;
    }
    return num;
    
}
-(void) mmSetBoolValue:(nonnull NSString *)key value:(BOOL)value
{
    [[SDKCache cache] setObject:[NSNumber numberWithBool:value] forKey:key];
}
-(bool) mmGetBoolValue:(nonnull NSString *)key defaultValue:(BOOL)defaultValue
{
    NSNumber *value = (NSNumber *)[[SDKCache cache] objectForKey:key];
    return value != nil ? value.boolValue : defaultValue;
}
-(void) mmSetFloatValue:(nonnull NSString *)key value:(float)value
{
    if (_mmEnableConfuse) {
        NSNumber *confuse = [self mmResetConfuseNumber:key size:100];
        value *= confuse.intValue;
    }
    [[SDKCache cache] setObject:[NSNumber numberWithFloat:value] forKey:key];
}
-(float) mmGetFloatValue:(nonnull NSString *)key defaultValue:(float)defaultValue
{
    NSNumber *value = (NSNumber *)[[SDKCache cache] objectForKey:key];
    float num = value != nil ? value.floatValue : defaultValue;
    if (_mmEnableConfuse) {
        NSNumber *confuse = [self mmGetConfuseNumber:key];
        num /= confuse.intValue;
    }
    return num;
}
-(void) mmSetStringValue:(nonnull NSString *)key value:(nullable NSString *)value
{
    [[SDKCache cache] setObject:value forKey:key];
}
-(nonnull NSString *) mmGetStringValue:(nonnull NSString *)key defaultValue:(nonnull NSString *)defaultValue
{
    NSString *value = (NSString *)[[SDKCache cache] objectForKey:key];
    return value != nil ? value : defaultValue;
}
-(bool) mmContainsKey:(nonnull NSString *)key
{
    return [[SDKCache cache] containsObjectForKey:key];
}
-(void) mmRemoveKey:(nonnull NSString *)key
{
    [[SDKCache cache] removeObjectForKey:key];
}
-(void) mmRemoveKeys:(nonnull NSString *)keys
{
    if (keys) {
        NSArray<NSString *>*keyArr = [keys componentsSeparatedByString:@","];
        for (NSString *key in keyArr) {
            [[SDKCache cache] removeObjectForKey:key];
        }
    }
}
-(void)mmClearAll
{
}
#pragma mark -
#pragma mark Static Method
+ (SDKFacade *)sharedInstance
{
    static SDKFacade *_instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[self alloc] init];
    });
    return _instance;
}

+ (void)trackCrossPromoteImpression:(nonnull NSString*)appID type:(SDK_ADTYPE)type
{
#ifdef APPSFLYER
    if (appID) {
        [AppsFlyerCrossPromotionHelper logCrossPromoteImpression:appID campaign:[NSString stringWithFormat:@"%@:%@", SDK_CROSS_PROMOTED_CAMPAIGN, [SDKConstants getAdTypeName:type]] parameters:nil];
    }
#endif
}
+ (void)trackAndOpenStore:(nonnull NSString*) appID type:(SDK_ADTYPE)type
{
#ifdef APPSFLYER
    if (appID) {
        NSString *myAppId = [[SDKFacade sharedInstance] getConfig:SDK_CONFIG_KEY_APP_ID];
        NSString *myPackage = [NSBundle mainBundle].bundleIdentifier;
        [AppsFlyerCrossPromotionHelper logAndOpenStore:appID campaign:[NSString stringWithFormat:@"%@:%@", SDK_CROSS_PROMOTED_CAMPAIGN, [SDKConstants getAdTypeName:type]] parameters:@{
                                                                                                                @"fromAppId":myAppId,
                                                                                                                @"fromPackage":myPackage
        } openStore:^(NSURLSession * _Nonnull urlSession, NSURL * _Nonnull clickURL) {
            NSURLSessionDataTask *dataTask;
            dataTask = [urlSession dataTaskWithURL:clickURL
                                 completionHandler:^(NSData * _Nullable data,
                                                     NSURLResponse * _Nullable response,
                                                     NSError * _Nullable error)
                        {
                            if (error) {
                                DLog(@"[analyse] AppsFlyer crossPromotionViewed Connection failed! Error - %@",[error localizedDescription]);
                            }
                        }];
            [dataTask resume];
            [[SDKHelper sharedHelper] openAppStoreId:appID];
        }];
    }
#else
    [[SDKHelper sharedHelper] openAppStoreId:appID];
#endif
}
+ (void)share:(NSString *)title image:(UIImage *)image url:(NSURL *)url completeHandler:(SDKShareCompleteHandler)completeHandler
{
    [SDKHelper share:title image:image url:url completeHandler:completeHandler];
}

+(void)shareScreenshot:(SDKShareCompleteHandler)completeHandler
{
    [SDKHelper shareScreenshot:completeHandler];
}

+(void)shareScreenshot:(NSString *)title completeHandler:(SDKShareCompleteHandler)completeHandler
{
    [SDKHelper shareScreenshot:title completeHandler:completeHandler];
}

+(void)shareScreenshot:(NSString *)title url:(NSURL *)url completeHandler:(SDKShareCompleteHandler)completeHandler
{
    [SDKHelper shareScreenshot:title url:url completeHandler:completeHandler];
}

+ (nonnull UIImage *)imageWithScreenshot
{
    return [SDKHelper imageWithScreenshot];
}

+ (void)saveBase64ImageToCameraRoll:(nonnull NSString *)base64Image
{
    [SDKHelper saveBase64ImageToCameraRoll:base64Image];
}

+ (void)takeScreenshotToCameraRoll
{
    [SDKHelper takeScreenshotToCameraRoll];
}

+ (nonnull NSString *)takeScreenshotToDocumentsDirectory
{
    return [SDKHelper takeScreenshotToDocumentsDirectory];
}

+ (nonnull NSString *)takeScreenshotToDirectoryAtPath:(nonnull NSString *)path
{
    return [SDKHelper takeScreenshotToDirectoryAtPath:path];
}

+(BOOL)isIPhoneX
{
    //return UIScreen.mainScreen.bounds.size.height / UIScreen.mainScreen.bounds.size.width > 2;
    return ([UIScreen instancesRespondToSelector:@selector(currentMode)] ? CGSizeEqualToSize(CGSizeMake(1125, 2436), [[UIScreen mainScreen] currentMode].size) : NO);
}

+(BOOL)isIPad
{
    return isPad;
}

+(long)getFreeMem
{
    vm_statistics_data_t vmStats;
    mach_msg_type_number_t infoCount = HOST_VM_INFO_COUNT;
    kern_return_t kernReturn = host_statistics(mach_host_self(),
                                               HOST_VM_INFO,
                                               (host_info_t)&vmStats,
                                               &infoCount);
    
    if (kernReturn != KERN_SUCCESS) {
        return -1;
    }
    
    return vm_page_size * vmStats.free_count;
}

+(long long)getTotalDiskSize
{
    uint64_t totalSpace = 0;
    NSError *error = nil;
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSDictionary *dictionary = [[NSFileManager defaultManager] attributesOfFileSystemForPath:[paths lastObject] error: &error];
    if (dictionary.count) {
        NSNumber *fileSystemSizeInBytes = dictionary[NSFileSystemSize];
        totalSpace = [fileSystemSizeInBytes unsignedLongLongValue];
    }
    return totalSpace;
}

+(long long)getAvailableDiskSize
{
    uint64_t totalFreeSpace = 0;
    NSError *error = nil;
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSDictionary *dictionary = [[NSFileManager defaultManager] attributesOfFileSystemForPath:[paths lastObject] error: &error];
    if (dictionary.count) {
        NSNumber *freeFileSystemSizeInBytes = dictionary[NSFileSystemFreeSize];
        totalFreeSpace = [freeFileSystemSizeInBytes unsignedLongLongValue];
    }
    return totalFreeSpace;
}

-(void)_checkAndTrackIDFT
{
    if([[NSUserDefaults standardUserDefaults] objectForKey:SDK_HAS_TRACKIDFT] == nil) {
        NSString *idfa = [SDKHelper getIDFAString];
        DLog(@"[idfa] == %@", idfa);
        if ([idfa containsString:@"-0000-0000"]) {
            [self logEvent:@"noidfa"];
            [self onAttAccepted:FALSE];
        } else {
            [self logEvent:@"idfa"];
            [self onAttAccepted:TRUE];
        }
        [[NSUserDefaults standardUserDefaults] setBool:YES forKey:SDK_HAS_TRACKIDFT];
        [[NSUserDefaults standardUserDefaults] synchronize];
    }
    id tmp = [_config objectForKey:@"register-push"];
    BOOL autoRegister = tmp ? [(NSNumber *)tmp boolValue] : YES;
    if (autoRegister) {
        [self registerPush];
    }
}

+(void)requestIDFA {
    if (@available(iOS 14, *)) {
        [ATTrackingManager requestTrackingAuthorizationWithCompletionHandler:^(ATTrackingManagerAuthorizationStatus status) {
            // Tracking authorization completed. Start loading ads here.
            // [self loadAd];
            DLog(@"requestIDFA : %lu", (unsigned long)status);
            if (status == ATTrackingManagerAuthorizationStatusNotDetermined) {
                [SDKTimer startTimer:3 interval:1 onComplete:^{
                    [SDKFacade requestIDFA];
                } onUpdate:nil];
            } else {
                [[SDKFacade sharedInstance] onAttAccepted: status == ATTrackingManagerAuthorizationStatusAuthorized];
                [[SDKFacade sharedInstance] _checkAndTrackIDFT];
            }
        }];
    } else {
        [[SDKFacade sharedInstance] onAttAccepted: true];
        [[SDKFacade sharedInstance] _checkAndTrackIDFT];
    }
}

+ (BOOL)joinQQGroup:(NSString *)groupUin key:(NSString *)key
{
    if (groupUin && key) {
        NSString *urlStr = [NSString stringWithFormat:@"mqqapi://card/show_pslcard?src_type=internal&version=1&uin=%@&key=%@&card_type=group&source=external&jump_from=webapi", groupUin, key];
        NSURL *url = [NSURL URLWithString:urlStr];
        if([[UIApplication sharedApplication] canOpenURL:url]){
            [[UIApplication sharedApplication] openURL:url options:@{} completionHandler:nil];
            return YES;
        } else {
            [EasyTextView showErrorText:@"未安装手机QQ或安装的版本不支持。"];
            return NO;
        }
    } else {
        [EasyTextView showErrorText:@"QQ号不正确或者不存在。"];
        return NO;
    }
}

+ (void)playAHAP:(nonnull NSString *)name inDirectory:(nullable NSString *)folder
{
    [SDKHapticHelper playAHAP:name inDirectory:folder];
}

//+(UIWindow *)keyWindow
//{
//    NSArray <UIWindow *>*windows = [UIApplication sharedApplication].windows;
//    for (UIWindow *window in windows.reverseOwbjectEnumerator) {
//        CGFloat alpha = [window alpha];
//        BOOL isHidden = [window isHidden];
//        if ([NSStringFromClass(window.class) isEqualToString:@"UITextEffectsWindow"] && !isHidden && alpha > 0) {
//            return window;
//        }
//    }
//    return [UIApplication sharedApplication].keyWindow;
//}


//异常捕获
void UncaughtExceptionHandler(NSException *exception) {
    if(CRASH_EMAIL_ADDR) {
        /**
         *  获取异常崩溃信息
         */
        NSArray *callStack = [exception callStackSymbols];
        NSString *reason = [exception reason];
        NSString *name = [exception name];
        NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
        NSString *app_Version = [infoDictionary objectForKey:@"CFBundleShortVersionString"];
        NSString *content = [NSString stringWithFormat:@"========[%@:%@] Crash Report : %@========\ncallStackSymbols:\n%@", name, app_Version, reason,[callStack componentsJoinedByString:@"\n"]];
        
        /**
         *  把异常崩溃信息发送至开发者邮件
         */
        NSMutableString *mailUrl = [NSMutableString string];
        [mailUrl appendString:[NSString stringWithFormat:@"mailto:%@", CRASH_EMAIL_ADDR]];
        [mailUrl appendString:[NSString stringWithFormat:@"?subject=%@ Crash Reporting！", name]];
        [mailUrl appendFormat:@"&body=%@", content];
        // 打开地址
        NSString *mailPath = [mailUrl stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet characterSetWithCharactersInString:@"`#%^{}\"[]|\\<> "]];
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:mailPath] options:@{} completionHandler:nil];
    }
}

- (void)onAppsflyerInitSuccess
{
    if (_appsflyerDelegate) {
        [_appsflyerDelegate onAppsflyerInitSuccess];
    }
}

- (void)onAppsflyerInitFail:(NSString *)data
{
    if (_appsflyerDelegate) {
        [_appsflyerDelegate onAppsflyerInitFail:data];
    }
}

- (void)onAppsflyerAppOpenAttribution:(NSDictionary *)attributionData
{
    @try{
        NSError *error;
        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:attributionData options:0 error:&error];
        if (jsonData != nil && error == nil) {
            NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
            if (_appsflyerDelegate) {
                [_appsflyerDelegate onAppsflyerAppOpenAttribution:jsonString];
            }
        } else if (error != nil) {
        }
    } @catch (NSException * e){
        
    }
}

- (void)onAppsflyerAppOpenAttributionFailure:(NSError *)error
{
    if (error != nil && _appsflyerDelegate) {
        [_appsflyerDelegate onAppsflyerAppOpenAttributionFailure:error.description];
    }
}

- (void)onAppsflyerConversionDataFail:(NSError *)error
{
    if (error != nil && _appsflyerDelegate) {
        [_appsflyerDelegate onAppsflyerConversionDataFail:error.description];
    }
}

- (void)onAppsflyerConversionDataSuccesss:(NSDictionary *)installData
{
    @try{
        NSError *error;
        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:installData options:0 error:&error];
        if (jsonData != nil && error == nil) {
            NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
            if (_appsflyerDelegate) {
                [_appsflyerDelegate onAppsflyerConversionDataSuccesss:jsonString];
            }
        } else if (error != nil) {
        }
    } @catch (NSException * e){
        
    }
}




@end
