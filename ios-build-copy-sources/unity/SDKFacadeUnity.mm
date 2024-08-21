//
//  SDKFacadeUnity.m
//  Pods
//
//  Created by 余冰星 on 2017/7/25.
//
//
#import "SDKFacadeUnity.h"
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKJSONHelper.h>
#ifdef __cplusplus
extern "C" {
#endif
    void Send(const char* obj, const char* method, const char* msg) {
#if UNITY_IOS || PLATFORM_IOS
        UnitySendMessage(obj, method, msg);
#endif
    }
#ifdef __cplusplus
} // extern "C"
#endif
@implementation SDKFacadeUnity

-(void)onHaveToRequestATT
{
    Send("RiseSdkListener", "onHaveToRequestATT", "");
}

-(void)onHaveToRequestGDPR
{
    Send("RiseSdkListener", "onHaveToRequestGDPR", "");
}

-(void)onInitialized
{
    Send("RiseSdkListener", "onInitialized", "");
}

-(void)onNetworkChanged:(int)status
{
    Send("RiseSdkListener", "onNetworkChanged", @(status).stringValue.UTF8String);
}

-(void)onReceiveServerExtra:(NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "onReceiveServerExtra", _msg);
}

-(void)onReceiveNotificationData:(NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "onReceiveNotificationData", _msg);
}

-(void)onDeepLinkReceived:(NSURL *)url
{
    if (url) {
        const char * _msg = [[url absoluteString] UTF8String];
        Send("RiseSdkListener", "onDeepLinkReceived", _msg);
    }
}
    
-(void)onAttAccepted:(BOOL)isAccept {
    Send("RiseSdkListener", "onAttAccepted", isAccept ? "0": "1");
}
    
-(void)unreadMessageCount:(int) count {
    Send("RiseSdkListener", "unreadMessageCount",  @(count).stringValue.UTF8String);
}

-(void)onNotificationOpen:(BOOL)isOpen {
    Send("RiseSdkListener", "onNotificationOpen", isOpen ? "0": "1");
}

-(void)onThirdLoginSuccess:(NSString * _Nonnull)openId platformType:(NSString * _Nonnull)platform
{
    Send("RiseSdkListener", "onReceiveLoginResult", "0");
}

-(void)onThirdLoginAnonymous
{
    Send("RiseSdkListener", "onReceiveLoginResult", "0");
}

-(void)onThirdLoginCancel:(NSString * _Nonnull)platform
{
    Send("RiseSdkListener", "onReceiveLoginResult", "1");
}

-(void)onThirdLoginError:(NSString * _Nonnull)errorMsg platformType:(NSString * _Nonnull)platform
{
    Send("RiseSdkListener", "onReceiveLoginResult", "1");
}

-(void)onThirdLoginClientNotInstalled:(NSString * _Nonnull) platform
{
    Send("RiseSdkListener", "onReceiveLoginResult", "1");
}

//是否同意用户协议
- (void)onThirdLoginPrivacyAccept:(BOOL) isAccept
{
    Send("RiseSdkListener", "onPrivacyAccept", isAccept ? "0": "1");
}

- (void)onThirdLoginReceiveIdCardVerifiedResult:(int) age
{
    Send("RiseSdkListener", "onReceiveIdCardVerifiedResult", [[NSString stringWithFormat: @"%d",age] UTF8String]);
}

- (void)adReward:(NSString *)tag rewardId:(int)rewardId
{
    const char * _msg = [[NSString stringWithFormat:@"%@|%d", tag, rewardId] UTF8String];
    Send("RiseSdkListener", "adReward", _msg);
}

- (void)adLoaded:(NSString *)tag adType:(int)adType
{
    const char * _msg = [[NSString stringWithFormat:@"%@|%d", tag, adType] UTF8String];
    Send("RiseSdkListener", "adLoaded", _msg);
}

-(void)adFailed:(NSString *)tag adType:(int)adType forError:(NSError *)error
{
    const char * _msg = [[NSString stringWithFormat:@"%@|%d", tag, adType] UTF8String];
    Send("RiseSdkListener", "adFailed", _msg);
}

- (void)adDidShown:(NSString *)tag adType:(int)adType
{
    const char * _msg = [[NSString stringWithFormat:@"%@|%d", tag, adType] UTF8String];
    Send("RiseSdkListener", "adDidShown", _msg);
}

-(void)adShowFailed:(NSString *)tag adType:(int)adType
{
    const char * _msg = [[NSString stringWithFormat:@"%@|%d", tag, adType] UTF8String];
    Send("RiseSdkListener", "adShowFailed", _msg);
}

- (void)adDidClose:(NSString *)tag adType:(int)adType
{
    const char * _msg = [[NSString stringWithFormat:@"%@|%d", tag, adType] UTF8String];
    Send("RiseSdkListener", "adDidClose", _msg);
}

-(void)adDidClick:(NSString *)tag adType:(int)adType
{
    const char * _msg = [[NSString stringWithFormat:@"%@|%d", tag, adType] UTF8String];
    Send("RiseSdkListener", "adDidClick", _msg);
}

- (void)deliciousIconClick:(NSString *)bannerDownPath jumpURL:(NSString *)url
{
    const char * _msg = [[NSString stringWithFormat:@"%@|%@", bannerDownPath, url] UTF8String];
    Send("RiseSdkListener", "deliciousIconClick", _msg);
}

- (void)onPaymentReady
{
    Send("RiseSdkListener", "onPaymentReady", "");
}

- (void)onPaymentSuccess:(int)paymentId
{
    const char * _msg = [[@(paymentId) stringValue] UTF8String];
    Send("RiseSdkListener", "onPaymentSuccess", _msg);
}

- (void)onPaymentSuccess:(int)paymentId payload:(NSString *)payload
{
    const char * _msg = [[NSString stringWithFormat:@"%d|%@", paymentId, payload] UTF8String];
    Send("RiseSdkListener", "onPaymentSuccessWithPayload", _msg);
}

- (void)onPaymentSuccess:(int)paymentId payload:(nullable NSString *)payload orderInfo:(nonnull NSString *)orderInfo
{
    const char * _msg = [[NSString stringWithFormat:@"%d|%@|%@", paymentId, payload, orderInfo] UTF8String];
    Send("RiseSdkListener", "onPaymentSuccessWithPayloadAndOrderInfo", _msg);
}

- (void)onPaymentFailure:(int)paymentId forError:(NSString *)error
{
    const char * _msg = [[@(paymentId) stringValue] UTF8String];
    Send("RiseSdkListener", "onPaymentFailure", _msg);
}

-(void)onAppStorePayRequest:(int)paymentId
{
    const char * _msg = [[@(paymentId) stringValue] UTF8String];
    Send("RiseSdkListener", "onAppStorePayRequest", _msg);
}

- (void)onCheckSubscriptionResult:(int)paymentId remainSeconds:(long)remainSeconds
{
    const char * _msg = [[NSString stringWithFormat:@"%@,%ld", [@(paymentId) stringValue], remainSeconds] UTF8String];
    Send("RiseSdkListener", "onCheckSubscriptionResult", _msg);
}

-(void)onRestoreSuccess:(int)paymentId
{
    const char * _msg = [[@(paymentId) stringValue] UTF8String];
    Send("RiseSdkListener", "onRestoreSuccess", _msg);
}

-(void)onRestoreFailure:(NSString *)error
{
    const char * _msg = error ? [error UTF8String] : "";
    Send("RiseSdkListener", "onRestoreFailure", _msg);
}

-(void)signInAppleSuccess:(NSString *)appleUID
{
    const char * _msg = appleUID ? [appleUID UTF8String] : "";
    Send("RiseSdkListener", "signInAppleSuccess", _msg);
}

-(void)signInAppleFailure:(NSString *)error
{
    const char * _msg = error ? [error UTF8String] : "";
    Send("RiseSdkListener", "signInAppleFailure", _msg);
}

-(void)onGameCenterLoginSuccess:(NSString *)playerId
{
    Send("RiseSdkListener", "onGameCenterLoginSuccess", playerId ? [playerId UTF8String] : "");
}

-(void)onGameCenterLoginFailure
{
    Send("RiseSdkListener", "onGameCenterLoginFailure", "");
}

-(void)snsShareSuccess
{
    Send("RiseSdkListener", "snsShareSuccess", "");
}

-(void)snsLoginCancel
{
    Send("RiseSdkListener", "snsLoginCancel", "");
}

-(void)snsShareFailure
{
    Send("RiseSdkListener", "snsShareFailure", "");
}

-(void)snsShareCancel
{
    Send("RiseSdkListener", "snsShareCancel", "");
}

-(void)snsLoginSuccess
{
    Send("RiseSdkListener", "snsLoginSuccess", "");
}

-(void)snsLoginFailure:(NSString *)error
{
    const char * _msg = error ? [error UTF8String] : "";
    Send("RiseSdkListener", "snsLoginFailure", _msg);
}

- (void)onWebviewLoadSuccess
{
    Send("RiseSdkListener", "onWebviewLoadSuccess", "");
}

- (void)onWebviewLoadFailure
{
    Send("RiseSdkListener", "onWebviewLoadFailure", "");
}

-(void)onWebviewClose
{
    Send("RiseSdkListener", "onWebviewClose", "");
}

-(void)jsCallOC:(NSString *)param
{
    const char * _msg = param ? [param UTF8String] : "";
    Send("RiseSdkListener", "snsLoginFailure", _msg);
}
- (void)onCloudFunctionFailed:(nullable NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onCloudFunctionFailed %@", data);
    Send("RiseSdkListener", "onCloudFunctionFailed", _msg);
}

- (void)onCloudFunctionResult:(nullable NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onCloudFunctionResult %@", data);
    Send("RiseSdkListener", "onCloudFunctionResult", _msg);
}

- (void)onFirestoreConnectError:(nullable NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onFirestoreConnectError %@", data);
    Send("RiseSdkListener", "onFirestoreConnectError", _msg);
}

- (void)onFirestoreConnected:(nullable NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onFirestoreConnected %@", data);
    Send("RiseSdkListener", "onFirestoreConnected", _msg);
}

- (void)onFirestoreData:(nullable NSString *)collection data:(nullable NSString *)data {
    const char * _msg = [[NSString stringWithFormat:@"%@|%@", collection, data] UTF8String];
    DLog(@"<<< onFirestoreReadData %@", [NSString stringWithUTF8String: _msg]);
    Send("RiseSdkListener", "onFirestoreReadData", _msg);
}

- (void)onFirestoreFail:(nullable NSString *)operation data:(nullable NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onCloudFunctionFailed %@", data);
    Send("RiseSdkListener", "onCloudFunctionFailed", _msg);
}

- (void)onFirestoreSnapshot:(nullable NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onFirestoreSnapshot %@", data);
    Send("RiseSdkListener", "onFirestoreSnapshot", _msg);
}

- (void)onFirestoreSuccess:(nullable NSString *)operation data:(nullable NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onFirestoreSuccess %@ %@", operation, data);
    Send("RiseSdkListener", [operation UTF8String], _msg);
}

- (void)onChatMessage:(nonnull NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onChatMessage %@", data);
    Send("RiseSdkListener", "onChatMessage", _msg);
}

- (void)inAppMessageClicked {
    [[SDKFacade sharedInstance] inAppMessageClicked];
}

- (void)inAppMessageDismissed {
    [[SDKFacade sharedInstance] inAppMessageDismissed];
}

- (void)inAppMessageEvent:(nonnull NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< inAppMessageEvent %@", data);
    Send("RiseSdkListener", "onInAppMessageEvent", _msg);
}

- (void)onInAppMessageDisplayed:(nonnull NSString *)data {
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    DLog(@"<<< onInAppMessageDisplayed %@", data);
    Send("RiseSdkListener", "onInAppMessageDisplayed", _msg);
}

- (void)onRequireWeChatLogin:(nullable NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "RequireWXLogin", _msg);
}

- (void)onCloudFuncSuccess:(nonnull NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "cloudFuncSuccess", _msg);
}

- (void)onCloudFuncFail:(nonnull NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "cloudFuncFail", _msg);
}

- (void)onWeChatLogSuccess:(NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "onWXLogSuccess", _msg);
}

- (void)onWeChatLogFailed:(NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "onWXLogFailed", _msg);
}

- (void)onWeChatNotInstall:(NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "onWXUninstall", _msg);
}

- (void)onWeChatPreLogState:(NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "onWXPreLogState", _msg);
}

- (void)onUserProtocol:(NSString *)data
{
    data = data ? data : @"{}";
    const char * _msg = [data UTF8String];
    Send("RiseSdkListener", "onUserProtocol", _msg);
}

- (void)IdVerifySuccess
{
    const char * _msg = [@"{}" UTF8String];
    Send("RiseSdkListener", "IdVerifySuccess", _msg);
}

- (void)IdVerifyFailed:(NSString *)reason
{
    reason = reason ? reason : @"{}";
    const char * _msg = [reason UTF8String];
    Send("RiseSdkListener", "IdVerifyFailed", _msg);
}

@end

#ifdef __cplusplus
extern "C" {
#endif
    static SDKFacadeUnity *handler;
    void onCreate()
    {
        handler = [[SDKFacadeUnity alloc] init];
        [[SDKFacade sharedInstance] setSdkDelegate:handler];
        [[SDKFacade sharedInstance] setAdDelegate:handler];
        [[SDKFacade sharedInstance] setPaymentDelegate:handler];
        [[SDKFacade sharedInstance] setSNSDelegate:handler];
        [[SDKFacade sharedInstance] setOnIdCardVerifiedDelegate:^{
            Send("RiseSdkListener", "onIdCardVerified", "");
        }];
        [[SDKFacade sharedInstance] setCloudFunctionDelegate:handler];
        [[SDKFacade sharedInstance] setFirestoreDelegate:handler];
        [[SDKFacade sharedInstance] setFirebaseDatabaseDelegate:handler];
        [[SDKFacade sharedInstance] setInAppMessageDelegate:handler];
        [[SDKFacade sharedInstance] onCreate];
    }

    void showWebView(const char * _Nullable url, int x, int y, int w, int h)
    {
        [[SDKFacade sharedInstance] showWebView:[NSString stringWithUTF8String:url] frame:CGRectMake(x, y, w, h) delegate:handler];
    }

    bool isWebViewDisplayed()
    {
        return [[SDKFacade sharedInstance] isWebViewDisplayed];
    }

    void closeWebView()
    {
        [[SDKFacade sharedInstance] closeWebView];
    }

    bool isCharNotNull(const char * str)
    {
        return str && *str != '\0';
    }
    
    const char *returnStr(const char * src)
    {
        if (isCharNotNull(src)) {
            long n = strlen(src) + 1;
            const char *str = (char*)malloc(n + 1);
            memset((void*)str, 0, n);
            memcpy((void*)str, (void*)src, n);
            return str;
        }
        return NULL;
    }
    
    const char* concat(const char *s1, const char *s2)
    {
        if (isCharNotNull(s1) && isCharNotNull(s2)) {
            const size_t len1 = strlen(s1);
            const size_t len2 = strlen(s2);
            char *result = (char*)malloc(len1 + len2 + 1); //+1 for the zero-terminator
            //in real code you would check for errors in malloc here
            memcpy(result, s1, len1);
            memcpy(result+len1, s2, len2+1);//+1 to copy the null-terminator
            return result;
        }
        return NULL;
    }
    
    const char *getPushData()
    {
        NSDictionary *data = [[SDKFacade sharedInstance] getPushData];
        NSString *result = [SDKJSONHelper toJSONString:data];
        return returnStr(result ? [result UTF8String] : "{}");
    }
    
    const char *getConfig(int cid)
    {
        NSString *result = [[SDKFacade sharedInstance] getConfig:cid];
        return returnStr(result ? [result UTF8String] : "");
    }
    
    void openApplicationSetting(){
        [[SDKFacade sharedInstance] openApplicationSetting];
    }
    
    bool isAdsEnabled()
    {
        return [[SDKFacade sharedInstance] isAdsEnabled];
    }
    
    void setAdsEnable(bool enable)
    {
        [[SDKFacade sharedInstance] setAdsEnable:enable];
    }
    
    int getScreenWidth()
    {
        return [[SDKFacade sharedInstance] getScreenWidth];
    }
    
    int getScreenHeight()
    {
        return [[SDKFacade sharedInstance] getScreenHeight];
    }
    
    int getScreenDesignWidth()
    {
        return [[SDKFacade sharedInstance] getScreenDesignWidth];
    }
    
    int getScreenDesignHeight()
    {
        return [[SDKFacade sharedInstance] getScreenDesignHeight];
    }
    
    const char *getExtraData()
    {
        NSString *data = [[SDKFacade sharedInstance] getExtraDataJson];
        return returnStr(data ? [data UTF8String] : "");
    }
    
    void toast(const char * msg)
    {
        if(msg != NULL) {
            NSString * objc_msg = [NSString stringWithUTF8String:msg];
            [[SDKFacade sharedInstance] toast:objc_msg isLongTime:NO];
        }
    }
    
    void sdklog(const char * info)
    {
        DLog(@"[sdklog]: %@", [NSString stringWithUTF8String:info]);
    }
    
    void showAIHelp(const char* entranceId, const char* meta, const char* tags, const char* msg){
        NSString* tTags = nil;
        if(tags != nil) {
            tTags = [NSString stringWithUTF8String:tags];
        }
        NSString* tMsg = nil;
        if(msg != nil) {
            tMsg = [NSString stringWithUTF8String:msg];
        }
        [[SDKFacade sharedInstance] showAIHelp:[NSString stringWithUTF8String:entranceId] message:[NSString stringWithUTF8String:meta] tag:tTags welcome:tMsg];
        
    }
    
    void showSignleFAQ(const char* faqId, int moment){
        [[SDKFacade sharedInstance] showSignleFAQ:[NSString stringWithUTF8String:faqId] momemt:moment];
    }
    
    bool isAIHelpInitialized() {
        return  [[SDKFacade sharedInstance] isAIHelpInitialized];
    }
    
    void loadAIHelpUnreadMessageCount(bool onlyOnce){
        [[SDKFacade sharedInstance] loadAIHelpUnreadMessageCount:onlyOnce];
    }
    
    void stopLoadAIHelpUnreadMessageCount() {
        [[SDKFacade sharedInstance] stopLoadAIHelpUnreadMessageCount];
    }
    
    void closeAIHelp(){
        [[SDKFacade sharedInstance] closeAIHelp];
    }
    
    void switchLanguage(const char* lang){
        [[SDKFacade sharedInstance] switchLanguage:[NSString stringWithUTF8String:lang]];
    }
    
    bool hasGdpr() {
        return [[SDKFacade sharedInstance] hasGdpr];
    }
    
    void resetGdpr() {
        [[SDKFacade sharedInstance] resetGdpr];
    }
    
    void setAdsUnderAgeMode(bool value) {
        [[SDKFacade sharedInstance] setAdsUnderAgeMode:value];
    }
    
    bool isBannerAvailable()
    {
        return [[SDKFacade sharedInstance] isBannerAvailable];
    }
    
    bool isBannerShowing()
    {
        return [[SDKFacade sharedInstance] isBannerShowing];
    }
    
    bool isBannerAvailableWithTag(const char *tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        return [[SDKFacade sharedInstance] isBannerAvailable:objc_tag];
    }

    void showBanner(int pos)
    {
        [[SDKFacade sharedInstance] showBanner:pos];
    }
    
    void showBannerWithTag(const char *tag, int pos)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] showBanner:objc_tag atPos:pos];
    }
    
    void showBannerCustom(const char *tag, float x, float y, float w, float h)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        float scale = [UIScreen mainScreen].scale;
        x = x / scale;
        y = y / scale;
        w = w / scale;
        h = h / scale;
        [[SDKFacade sharedInstance] showBanner:objc_tag withFrame:CGRectMake(x, y, w, h)];
    }
    
    void closeBanner()
    {
        [[SDKFacade sharedInstance] closeBanner];
    }
    
    bool isVideoAvailable()
    {
        return [[SDKFacade sharedInstance] isVideoAvailable];
    }

    bool hasRewardedInterstitial() {
        return [[SDKFacade sharedInstance] isVideoAvailable];
    }
    
    bool isVideoAvailableWithTag(const char *tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        return [[SDKFacade sharedInstance] isVideoAvailable:objc_tag];
    }
    
    void loadRewardVideo(const char * _Nonnull tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] loadRewardVideo:objc_tag];
    }
    
    void showRewardVideo(int rewardId)
    {
        [[SDKFacade sharedInstance] showRewardVideo:rewardId];
    }

    void showRewardedInterstitial() {
        [[SDKFacade sharedInstance] showRewardVideo:100];
    }
    
    void showRewardVideoWithTag(const char *tag, int rewardId)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] showRewardVideoWithTag:objc_tag rewardId:rewardId];
    }
    
    bool isInterstitialAvailable(const char *tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        return [[SDKFacade sharedInstance] isInterstitialAvailable:objc_tag];
    }
    
    void loadInterstitialAd(const char *tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] loadInterstitialAd:objc_tag];
    }
    
    void showInterstitialAd(const char *tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] showInterstitialAd:objc_tag];
    }
    
    void showInterstitialAdWithTag(const char *tag, int seconds)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] showInterstitialAd:objc_tag waitSeconds:seconds];
    }
    
    void showInterstitialAdWithTag2(const char *tag, int seconds, double timeInterval)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] showInterstitialAd:objc_tag waitSeconds:seconds timeInterval:timeInterval];
    }
    
    void showIconAd(float width, float xPercent, float yPercent)
    {
        [[SDKFacade sharedInstance] showIconAd:width xPercent:xPercent yPercent:yPercent];
    }
    
    void showPopupIconAds()
    {
        [[SDKFacade sharedInstance] showPopupIconAds];
    }
    
    void hidePopupIconAds()
    {
        [[SDKFacade sharedInstance] hidePopupIconAds];
    }
    
    const char * getPopupIconAdsData()
    {
        NSArray * arr = [[SDKFacade sharedInstance] getPopupIconAdsData];
        return returnStr([[SDKJSONHelper toJSONString:arr] UTF8String]);
    }
    
    void closeIconAd()
    {
        [[SDKFacade sharedInstance] closeIconAd];
    }
    
    bool isNativeAvailable(const char *tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        return [[SDKFacade sharedInstance] isNativeAvailable:objc_tag];
    }
    
    const char * _Nullable fetchNativeAdJson(const char * _Nonnull tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        NSString * result = [[SDKFacade sharedInstance] fetchNativeAdJson:objc_tag];
        return returnStr(result ? [result UTF8String] : "");
    }
    
    void loadNativeAd(const char * _Nonnull tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] loadNativeAd:objc_tag];
    }
    
    void closeNativeAd(const char * _Nonnull tag)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        [[SDKFacade sharedInstance] closeNativeAd:objc_tag];
    }
    
    void showNativeAd(const char * _Nonnull tag, float x, float y, const char * _Nonnull json)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        NSString * objc_json = [NSString stringWithUTF8String:json];
        float scale = [UIScreen mainScreen].scale;
        x = x / scale;
        y = y / scale;
        [[SDKFacade sharedInstance] showNativeAd:objc_tag x:x y:y configJson:objc_json];
    }
    
    void showNativeAdWithFrame(const char *_Nonnull tag, float x, float y, float w, float h, const char * _Nonnull json)
    {
        NSString * objc_tag = [NSString stringWithUTF8String:tag];
        NSString * objc_json = [NSString stringWithUTF8String:json];
        float scale = [UIScreen mainScreen].scale;
        x = x / scale;
        y = y / scale;
        w = w / scale;
        h = h / scale;
        [[SDKFacade sharedInstance] showNativeAd:objc_tag withFrame:CGRectMake(x, y, w, h) configJson:objc_json];
    }
    
    bool isDeliciousAdAvailable()
    {
        return [[SDKFacade sharedInstance] isDeliciousAdAvailable:NO];
    }
    
    void showDeliciousInterstitialAd(const char * _Nonnull json)
    {
        NSString * objc_json = [NSString stringWithUTF8String:json];
        [[SDKFacade sharedInstance] showDeliciousInterstitialAd:objc_json];
    }
    
    void showDeliciousBannerAd(float x, float y, float w, float h, const char * _Nonnull json)
    {
        NSString * objc_json = [NSString stringWithUTF8String:json];
        float scale = [UIScreen mainScreen].scale;
        x = x / scale;
        y = y / scale;
        w = w / scale;
        h = h / scale;
        [[SDKFacade sharedInstance] showDeliciousBannerAd:CGRectMake(x, y, w, h) configJson:objc_json];
    }
    
    void closeDeliciousBannerAd()
    {
        [[SDKFacade sharedInstance] closeDeliciousBannerAd];
    }
    
    void showDeliciousIconAd(float x, float y, float w, float h, const char * _Nonnull json)
    {
        NSString * objc_json = [NSString stringWithUTF8String:json];
        float scale = [UIScreen mainScreen].scale;
        x = x / scale;
        y = y / scale;
        w = w / scale;
        h = h / scale;
        [[SDKFacade sharedInstance] showDeliciousIconAd:CGRectMake(x, y, w, h) configJson:objc_json];
    }
    
    void closeDeliciousIconAd()
    {
        [[SDKFacade sharedInstance] closeDeliciousIconAd];
    }
    
    void rateUs()
    {
        [[SDKFacade sharedInstance] rateUs:5];
    }
    
    void rateInApp()
    {
        [[SDKFacade sharedInstance] rateInApp:5];
    }
    
    void rateUsWithStar(float star)
    {
        [[SDKFacade sharedInstance] rateUs:star];
    }
    
    void rateInAppWithStar(float star)
    {
        [[SDKFacade sharedInstance] rateInApp:star];
    }

    const char * _Nonnull fetchDeepLink()
    {
        NSString *url = [[SDKFacade sharedInstance] fetchDeepLink];
        return returnStr(url ? [url UTF8String] : "");
    }

    
    bool isNetworkConnected()
    {
        return [[SDKFacade sharedInstance] isNetworkConnected];
    }

    bool isNetworkAvailable()
    {
        return [[SDKFacade sharedInstance] isNetworkConnected];
    }

    void setPayVerifyUrl(const char * _Nullable url)
    {
        if (url != NULL) {
            NSString * objc_url = [NSString stringWithUTF8String:url];
            [[SDKFacade sharedInstance] setPayVerifyUrl:objc_url];
        }
    }
    
    void pay(int payId)
    {
        [[SDKFacade sharedInstance] pay:payId];
    }

    void payWithPayload(int payId, const char *_Nonnull payload)
    {
        NSString * objc_payload = [NSString stringWithUTF8String:payload];
        [[SDKFacade sharedInstance] pay:payId payload:objc_payload];
    }
    
    void isSubscriptionActive()
    {
        [[SDKFacade sharedInstance] isSubscriptionActive];
    }
    
    void restorePayments()
    {
        [[SDKFacade sharedInstance] restorePayments];
    }

    void recheckFailedPayments()
    {
        [[SDKFacade sharedInstance] recheckFailedPayments];
    }
    
    const char *getPaymentData(int payId)
    {
        NSString *data = [SDKJSONHelper toJSONString:[[SDKFacade sharedInstance] getPaymentData:payId]];
        return returnStr(data ? [data UTF8String] : "{}");
    }

    
    const char * getPaymentDatas()
    {
        NSString *data = [[SDKFacade sharedInstance] getPaymentDatas];
        return returnStr(data ? [data UTF8String] : "{}");
    }
    
    int* getPurchasedIds()
    {
        NSArray<NSString *> *ids = [[SDKFacade sharedInstance] getPurchasedIds];
        if(ids && ids.count > 0) {
            int *result = new int[ids.count];
            for (int i=0; i<ids.count; i++) {
                NSString *_id = [ids objectAtIndex:i];
                result[i] = [_id intValue];
            }
            return result;
        }
        return NULL;
    }
    
    void clearPurchasedIds()
    {
        [[SDKFacade sharedInstance] clearPurchasedIds];
    }
    
    void clearPurchasedId(int paymentId)
    {
        [[SDKFacade sharedInstance] clearPurchasedId:paymentId];
    }
    
    bool isGameCenterAvailable()
    {
        return [[SDKFacade sharedInstance] isGameCenterAvailable];
    }

    void loginGameCenter()
    {
        [[SDKFacade sharedInstance] loginGameCenter];
    }
    
    void showLeaderboards()
    {
        [[SDKFacade sharedInstance] showLeaderboards];
    }
    
    void showLeaderboard(int leaderboardId)
    {
        [[SDKFacade sharedInstance] showLeaderboard:leaderboardId];
    }
    
    void showAchievements()
    {
        [[SDKFacade sharedInstance] showAchievements];
    }
    
    void submitScore(int leaderboardId, long long score)
    {
        [[SDKFacade sharedInstance] submitScore:leaderboardId score:score];
    }
    
    long long myHighScore(int leaderboardId)
    {
        return [[SDKFacade sharedInstance] myHighScore:leaderboardId];
    }
    
    void submitAchievement(int achievementId, double percent)
    {
        [[SDKFacade sharedInstance] submitAchievement:achievementId percent:percent];
    }
    
    double getAchievementProgress(int achievementId)
    {
        return [[SDKFacade sharedInstance] getAchievementProgress:achievementId];
    }
    
//    void startTraceWithName(const char * _Nonnull name)
//    {
//        NSString * objc_name = [NSString stringWithUTF8String:name];
//        [[SDKFacade sharedInstance] startTraceWithName:objc_name];
//    }
//
//    void stopTraceWithName(const char * _Nonnull name)
//    {
//        NSString * objc_name = [NSString stringWithUTF8String:name];
//        [[SDKFacade sharedInstance] stopTraceWithName:objc_name];
//    }
//
//    void incrementMetric(const char * _Nonnull name, int intValue)
//    {
//        NSString * objc_name = [NSString stringWithUTF8String:name];
//        [[SDKFacade sharedInstance] incrementMetric:objc_name byInt:intValue];
//    }
    
    int getRemoteConfigIntValue(const char * _Nonnull key)
    {
        NSString * objc_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] getRemoteConfigIntValue:objc_key];
    }
    
    long getRemoteConfigLongValue(const char * _Nonnull key)
    {
        NSString * objc_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] getRemoteConfigLongValue:objc_key];
    }
    
    double getRemoteConfigDoubleValue(const char * _Nonnull key)
    {
        NSString * objc_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] getRemoteConfigDoubleValue:objc_key];
    }
    
    bool getRemoteConfigBoolValue(const char * _Nonnull key)
    {
        NSString * objc_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] getRemoteConfigBoolValue:objc_key];
    }
    
    const char * _Nonnull getRemoteConfigStringValue(const char * _Nonnull key)
    {
        NSString * objc_key = [NSString stringWithUTF8String:key];
        return returnStr([[[SDKFacade sharedInstance] getRemoteConfigStringValue:objc_key] UTF8String]);
    }
    
    void setUserPropertyString(const char * _Nonnull key, const char * _Nonnull value)
    {
        NSString * objc_key = [NSString stringWithUTF8String:key];
        NSString * objc_value = [NSString stringWithUTF8String:value];
        [[SDKFacade sharedInstance] setUserPropertyString:objc_value forName:objc_key];
    }

    const char * _Nonnull getSignedAppleUID()
    {
        return returnStr([[[SDKFacade sharedInstance] getSignedAppleUID] UTF8String]);
    }

    const char * _Nonnull getSignedAppleInfo()
    {
        NSString *json = [SDKJSONHelper toJSONString:[[SDKFacade sharedInstance] getSignedAppleInfo]];
        return returnStr([(json ?:@"") UTF8String]);
    }

    bool showSignInWithApple(float x, float y, float w, float h, bool useBlackBackgrounds)
    {
        float scale = [UIScreen mainScreen].scale;
        x = x / scale;
        y = y / scale;
        w = w / scale;
        h = h / scale;
        return [[SDKFacade sharedInstance] showSignInWithApple:CGRectMake(x, y, w, h) useBlackBackground:useBlackBackgrounds];
    }

    void hideSignInWithApple()
    {
        [[SDKFacade sharedInstance] hideSignInWithApple];
    }

    void login()
    {
        [[SDKFacade sharedInstance] login:nil];
    }
    
    void logout()
    {
        [[SDKFacade sharedInstance] logout];
    }

    void logoutFacebook() {
        [[SDKFacade sharedInstance] logout];
    }
    
    bool isLogin()
    {
        return [[SDKFacade sharedInstance] isLogin];
    }
    
    const char *meFirstName()
    {
        return returnStr([[[SDKFacade sharedInstance] meFirstName] UTF8String]);
    }
    
    const char *meLastName()
    {
        return returnStr([[[SDKFacade sharedInstance] meLastName] UTF8String]);
    }
    
    const char *meName()
    {
        return returnStr([[[SDKFacade sharedInstance] meName] UTF8String]);
    }
    
    const char *meId()
    {
        return returnStr([[[SDKFacade sharedInstance] meId] UTF8String]);
    }
    
    const char *mePictureURL()
    {
        return returnStr([[[SDKFacade sharedInstance] mePictureURL] UTF8String]);
    }
    
    const char *me()
    {
        return returnStr([[[SDKFacade sharedInstance] me] UTF8String]);
    }
    
    const char *friends()
    {
        return returnStr([[[SDKFacade sharedInstance] friends] UTF8String]);
    }
    
    void fetchFriends(bool invitable)
    {
        [[SDKFacade sharedInstance] fetchFriends:invitable];
    }
    
    void fetchScores()
    {
        [[SDKFacade sharedInstance] fetchScores];
    }
    
    void invite()
    {
        [[SDKFacade sharedInstance] invite];
    }
    
    void share()
    {
        [[SDKFacade sharedInstance] share];
    }
    
    void shareContent(const char *contentURL, const char *tag, const char *quote)
    {
        NSString *_tag = nil;
        if(tag != NULL && tag != NULL && strlen(tag) > 0) {
            _tag = [NSString stringWithUTF8String:tag];
        }
        NSString *_quote = nil;
        if(quote != NULL && quote != NULL && strlen(quote) > 0) {
            _quote = [NSString stringWithUTF8String:quote];
        }
        [[SDKFacade sharedInstance] share:[NSString stringWithUTF8String:contentURL] withTag:_tag withQuote:_quote];
    }
    
    void shareSheet(const char *linkURL, const char *tag, const char *quote)
    {
        NSString *_tag = nil;
        if(tag != NULL && tag != NULL && strlen(tag) > 0) {
            _tag = [NSString stringWithUTF8String:tag];
        }
        NSString *_quote = nil;
        if(quote != NULL && quote != NULL && strlen(quote) > 0) {
            _quote = [NSString stringWithUTF8String:quote];
        }
        [[SDKFacade sharedInstance] shareSheet:[NSString stringWithUTF8String:linkURL] withTag:_tag withQuote:_quote];
    }
    
    void shareSheetOS(const char *linkURL, const char *title)
    {
        NSString *_title = nil;
        if(title != NULL && title != NULL && strlen(title) > 0) {
            _title = [NSString stringWithUTF8String:title];
        }
        [[SDKFacade sharedInstance] shareSheetOS:[NSString stringWithUTF8String:linkURL] title:_title];
    }

    void logErrorInFirebase(int errorcode, const char *errorDomain, const char *reason, const char *desc, const char *suggest)
    {
        NSString *objc_errorDomain = [NSString stringWithUTF8String:errorDomain];
        NSString *objc_reason = [NSString stringWithUTF8String:reason];
        NSString *objc_desc = [NSString stringWithUTF8String:desc];
        NSString *objc_suggest = suggest != NULL ? [NSString stringWithUTF8String:suggest] : nil;
        [[SDKFacade sharedInstance]logError:errorcode errorDomain:objc_errorDomain reason:objc_reason description:objc_desc suggest:objc_suggest];
    }

    void logIvyEventWithData(const char *name, const char *data)
    {
        if(name != NULL) {
            NSString *objc_name = [NSString stringWithUTF8String:name];
            if(data != NULL) {
                NSMutableDictionary *objc_data = [[NSMutableDictionary alloc] init];
                NSString *_data = [NSString stringWithUTF8String:data];
                NSArray *arr = [_data componentsSeparatedByString:@","];
                if(arr && arr.count > 1) {
                    for (int i=0; i<arr.count-1; i+=2) {
                        NSString *key = [arr objectAtIndex:i];
                        NSString *value = [arr objectAtIndex:i+1];
                        [objc_data setObject:value forKey:key];
                    }
                    [[SDKFacade sharedInstance] logIvyEvent:objc_name withData:objc_data];
                } else {
                    [[SDKFacade sharedInstance] logIvyEvent:objc_name];
                }
            } else {
                [[SDKFacade sharedInstance] logIvyEvent:objc_name];
            }
        }
    }

    void trackScreen(const char* _Nonnull screenClass, const char* _Nonnull screenName) {
        [[SDKFacade sharedInstance] trackScreen:[NSString stringWithUTF8String:screenClass] screenName:[NSString stringWithUTF8String:screenName]];
    }
    
    void logEventWithData(const char *name, const char *data)
    {
        if(name != NULL) {
            NSString *objc_name = [NSString stringWithUTF8String:name];
            if(data != NULL) {
                NSMutableDictionary *objc_data = [[NSMutableDictionary alloc] init];
                NSString *_data = [NSString stringWithUTF8String:data];
                NSArray *arr = [_data componentsSeparatedByString:@","];
                if(arr && arr.count > 1) {
                    for (int i=0; i<arr.count-1; i+=2) {
                        NSString *key = [arr objectAtIndex:i];
                        NSString *value = [arr objectAtIndex:i+1];
                        [objc_data setObject:value forKey:key];
                    }
                    [[SDKFacade sharedInstance] logEvent:objc_name withData:objc_data];
                } else {
                    [[SDKFacade sharedInstance] logEvent:objc_name];
                }
            } else {
                [[SDKFacade sharedInstance] logEvent:objc_name];
            }
        }
    }
    
    void logPlayerLevel(int levelId)
    {
        [[SDKFacade sharedInstance] logPlayerLevel:levelId];
    }
    
    void logPageStart(const char* pageName)
    {
        if (pageName != NULL) {
            [[SDKFacade sharedInstance] logPageStart:[NSString stringWithUTF8String:pageName]];
        }
    }
    
    void logPageEnd(const char* pageName)
    {
        if (pageName != NULL) {
            [[SDKFacade sharedInstance] logPageEnd:[NSString stringWithUTF8String:pageName]];
        }
    }
    
    void logEvent(const char* eventId)
    {
        if (eventId != NULL) {
            [[SDKFacade sharedInstance] logEvent:[NSString stringWithUTF8String:eventId]];
        }
    }
    
    void logEventWithTag(const char* eventId, const char* action)
    {
        if (eventId != NULL) {
            [[SDKFacade sharedInstance] logEvent:[NSString stringWithUTF8String:eventId] action:action == NULL ? nil : [NSString stringWithUTF8String:action]];
        }
    }
    
    void logEventLikeGA(const char* eventId, const char* action, const char* label, long value)
    {
        if (eventId != NULL) {
            [[SDKFacade sharedInstance] logEvent:[NSString stringWithUTF8String:eventId] action:action == NULL ? nil : [NSString stringWithUTF8String:action] label:label == NULL ? nil : [NSString stringWithUTF8String:label] value:value];
        }
    }
    
    void logStartLevel(const char* level)
    {
        if (level != NULL) {
            [[SDKFacade sharedInstance] logStartLevel:[NSString stringWithUTF8String:level]];
        }
    }
    
    void logFailLevel(const char* level)
    {
        if (level != NULL) {
            [[SDKFacade sharedInstance] logFailLevel:[NSString stringWithUTF8String:level]];
        }
    }
    
    void logFinishLevel(const char* level)
    {
        if (level != NULL) {
            [[SDKFacade sharedInstance] logFinishLevel:[NSString stringWithUTF8String:level]];
        }
    }
    
    void logFinishAchievement(const char* achievement)
    {
        if (achievement != NULL) {
            [[SDKFacade sharedInstance] logFinishAchievement:[NSString stringWithUTF8String:achievement]];
        }
    }
    
    void logFinishTutorial(const char* tutorial)
    {
        if (tutorial != NULL) {
            [[SDKFacade sharedInstance] logFinishTutorial:[NSString stringWithUTF8String:tutorial]];
        }
    }
    
    void logBuy(const char* _Nonnull itemName, int count, double price)
    {
        if(itemName != NULL) {
            [[SDKFacade sharedInstance] logBuy:[NSString stringWithUTF8String:itemName] itemType:@"" count:count price:price];
        }
    }
    
    void logBuy2(const char* _Nonnull itemName, const char* _Nonnull itemType, int count, double price)
    {
        if(itemName != NULL) {
            [[SDKFacade sharedInstance] logBuy:[NSString stringWithUTF8String:itemName] itemType:[NSString stringWithUTF8String:itemType] count:count price:price];
        }
    }
    
    void logUse(const char* itemName, int number, double price)
    {
        if(itemName != NULL) {
            [[SDKFacade sharedInstance] logUse:[NSString stringWithUTF8String:itemName] number:number price:price];
        }
    }
    
    void logBonus(const char* itemName, int number, double price, int trigger)
    {
        if(itemName != NULL) {
            [[SDKFacade sharedInstance] logBonus:[NSString stringWithUTF8String:itemName] number:number price:price trigger:trigger];
        }
    }

    void logRegister(const char* channel)
    {
        if(channel != NULL) {
//            [[SDKFacade sharedInstance] logRegister:[NSString stringWithUTF8String:channel]];
        }
    }

    void cloudfunction(const char* _Nonnull funcName) {
        if (funcName != NULL) {
            [[SDKFacade sharedInstance] callCloudFunction:[NSString stringWithUTF8String:funcName] params:nil];
        }
    }
    
    // Cloud Function & FireStore
    void initializeAndLinkFacebookAfterSignIn() {
        DLog(@"initializeAndLinkFacebookAfterSignIn >>> ");
        [[SDKFacade sharedInstance] initializeAndLinkFacebookAfterSignIn];
    }
    void callCloudFunction(const char* method, const char* params)
    {
        if(method != NULL) {
            NSString *oc_method = [NSString stringWithUTF8String:method];
            if (params != NULL) {
                [[SDKFacade sharedInstance] callCloudFunction:oc_method params:[NSString stringWithUTF8String:params]];
            } else {
                [[SDKFacade sharedInstance] callCloudFunction:oc_method params:nil];
            }
        }
    }

    void cloudfunction2(const char* _Nonnull funcName, const char* _Nonnull funcParams) {
        callCloudFunction(funcName, funcParams);
    }

    void updateFirestore(const char* collection, const char*  transactionId, const char* jsonData)
    {
        if (collection != NULL && transactionId != NULL && jsonData != NULL) {
            [[SDKFacade sharedInstance] updateFirestore:[NSString stringWithUTF8String:collection] transactionId:[NSString stringWithUTF8String:transactionId] jsonData:[NSString stringWithUTF8String:jsonData]];
        }
    }
                                                    
    void setFirestore(const char* collection, const char* jsonData)
    {
        if (collection != NULL && jsonData != NULL) {
            [[SDKFacade sharedInstance] setFirestore:[NSString stringWithUTF8String:collection] jsonData:[NSString stringWithUTF8String:jsonData]];
        }
    }
                       
    void mergeFirestore(const char* collection, const char* jsonData)
    {
        if (collection != NULL && jsonData != NULL) {
            [[SDKFacade sharedInstance] mergeFirestore:[NSString stringWithUTF8String:collection] jsonData:[NSString stringWithUTF8String:jsonData]];
        }
    }
    
    void deleteFirestore(const char* collection)
    {
        if (collection != NULL) {
            [[SDKFacade sharedInstance] deleteFirestore:[NSString stringWithUTF8String:collection]];
        }
    }
    
    void readFirestore(const char* collection)
    {
        if (collection != NULL) {
            [[SDKFacade sharedInstance] readFirestore:[NSString stringWithUTF8String:collection]];
        }
    }
    
    void readFirestore2(const char* collection, const char* document)
    {
        if (collection != NULL && document != NULL) {
            [[SDKFacade sharedInstance] readFirestore:[NSString stringWithUTF8String:collection] document:[NSString stringWithUTF8String:document]];
        }
    }
    
    void snapshotFirestore(const char* collection)
    {
        if (collection != NULL) {
            [[SDKFacade sharedInstance] snapshotFirestore:[NSString stringWithUTF8String:collection]];
        }
    }
    
    void initFirestoreAfterSignIn(const char* provider)
    {
        if (provider != NULL) {
            [[SDKFacade sharedInstance] initFirestoreAfterSignIn:[NSString stringWithUTF8String:provider]];
        }
    }

    void loginAppleBeforeFirestore(bool isForce){
        [[SDKFacade sharedInstance] loginAppleBeforeFirestore:isForce];
    }
    
    void linkFirestoreAfterAppleSignIn(){
        [[SDKFacade sharedInstance] linkFirestoreAfterAppleSignIn];
    }
    
    void deleteAppleCountFromFirestore(){
        [[SDKFacade sharedInstance] deleteAppleCountFromFirestore];
    }
    
    void signOutFirestore()
    {
        [[SDKFacade sharedInstance] signOutFirestore];
    }
    
    void initializeAndLinkGoogleAfterSignInFacebook() {
        DLog(@"initializeAndLinkGoogleAfterSignInFacebook >>> ");
    }

    void sendChat(const char* database, const char* path, const char* jsonData)
    {
#if FIREBASE_PLUS
        if (database != NULL && path != NULL && jsonData != NULL) {
            [[SDKFacade sharedInstance] sendChat:[NSString stringWithUTF8String:database] path:[NSString stringWithUTF8String:path] jsonData:[NSString stringWithUTF8String:jsonData]];
        }
#endif
    }

    void listenDB(const char* database, const char* path)
    {
#if FIREBASE_PLUS
        if (database != NULL && path != NULL) {
            [[SDKFacade sharedInstance] listen:[NSString stringWithUTF8String:database] path:[NSString stringWithUTF8String:path]];
        }
#endif
    }

    void displayUrl(const char* url)
    {
        [[SDKFacade sharedInstance] displayUrl:[NSString stringWithUTF8String:url]];
    }
                    
    void inAppReview()
    {
        [[SDKFacade sharedInstance] inAppReview];
    }

// InAppMessage
    void inAppMessageDismissed()
    {
        [[SDKFacade sharedInstance] inAppMessageDismissed];
    }
    
    void inAppMessageClicked()
    {
        [[SDKFacade sharedInstance] inAppMessageClicked];
    }

    void triggerInAppMessage(const char* eventName)
    {
        if (eventName != NULL) {
            [[SDKFacade sharedInstance] triggerInAppMessage:[NSString stringWithUTF8String:eventName]];
        }
    }

    void saveUserAttribute(const char* data)
    {
        if (data != NULL) {
            [[SDKFacade sharedInstance] saveUserAttribute:[NSString stringWithUTF8String:data]];
        }
    }
    
    const char * _Nonnull getFirebaseUserId() {
        NSString* uid = [[SDKFacade sharedInstance] getFirebaseUserId];
        return returnStr([uid UTF8String]);
    }

    const char *cacheUrl(const char* url)
    {
        if(url != NULL) {
            NSString *result = [[SDKFacade sharedInstance] cacheUrl:[NSString stringWithUTF8String:url]];
            return result ? returnStr([result UTF8String]) : NULL;
        }
        return NULL;
    }
    
    void cacheUrlWithTag(const char* tag, const char* url)
    {
        NSString *_tag = tag != NULL ? [NSString stringWithUTF8String:tag] : nil;
        NSString *_url = url != NULL ? [NSString stringWithUTF8String:url] : nil;
        [[SDKFacade sharedInstance] cacheUrl:_tag url:_url succes:^(NSString * __tag, NSString * __path) {
            const char * _msg = [[NSString stringWithFormat:@"%@|%@", __tag, __path] UTF8String];
            Send("RiseSdkListener", "cacheUrlSuccess", _msg);
        } failure:^(NSString * __tag) {
            Send("RiseSdkListener", "cacheUrlFailure", [__tag UTF8String]);
        }];
    }
    
    void sendMail(const char* address, const char* subject, const char* content, bool isHTML)
    {
        NSString *obj_address = address != NULL ? [NSString stringWithUTF8String:address] : nil;
        NSString *obj_subject = subject != NULL ? [NSString stringWithUTF8String:subject] : nil;
        NSString *obj_content = content != NULL ? [NSString stringWithUTF8String:content] : nil;
        if (obj_address && obj_subject && obj_content) {
            [[SDKFacade sharedInstance] sendMail:obj_address subject:obj_subject content:obj_content isHTML:isHTML];
        }
    }
    
    void registerPush()
    {
        [[SDKFacade sharedInstance] registerPush:true];
    }
    
    bool isPushRegistered()
    {
        return [[SDKFacade sharedInstance] isPushRegistered];
    }
    
    void queryPushNotification(){
        [[SDKFacade sharedInstance] queryPushNotification];
    }
    
    void cancelLocalNotification(const char* _Nonnull key)
    {
        NSString *objc_key = [NSString stringWithUTF8String:key];
        [[SDKFacade sharedInstance] cancelLocalNotification:objc_key];
    }
    
    void cancelAllLocalNotifications()
    {
        [[SDKFacade sharedInstance] cancelAllLocalNotifications];
    }
    
    const char* _Nullable getLocalNotificationDataJson()
    {
        NSString *json = [[SDKFacade sharedInstance] getLocalNotificationDataJson];
        return json && json.length > 0 ? returnStr([json UTF8String]) : NULL;
    }
    
    void pushLocalDelayNotification(const char* _Nonnull key, const char* _Nonnull title, const char* _Nonnull msg, long pushTime, long interval, bool repeat, bool useSound, const char* _Nullable soundName, const char* _Nonnull userInfo)
    {
        NSString *objc_key = [NSString stringWithUTF8String:key];
        NSString *objc_title = [NSString stringWithUTF8String:title];
        NSString *objc_msg = [NSString stringWithUTF8String:msg];
        NSString *objc_soundName = nil;
        if(soundName != NULL && soundName != NULL && strlen(soundName) > 0) {
            objc_soundName = [NSString stringWithUTF8String:soundName];
        }
        NSString *objc_userInfoJson = nil;
        if(userInfo != NULL && userInfo != NULL && strlen(userInfo) > 0) {
            objc_userInfoJson = [NSString stringWithUTF8String:userInfo];
        }
        [[SDKFacade sharedInstance] pushLocalDelayNotification:objc_key title:objc_title msg:objc_msg pushTime:pushTime interval:interval repeat:repeat useSound:useSound soundName:objc_soundName userInfo:[SDKJSONHelper toArrayOrNSDictionary:objc_userInfoJson]];
    }

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
    void pushLocalNotification(const char* _Nonnull key, const char* _Nonnull title, const char* _Nonnull msg, long pushTime, int interval, bool repeat, bool useSound, const char* _Nullable soundName, const char* _Nonnull userInfo)
    {
        NSString *objc_key = [NSString stringWithUTF8String:key];
        NSString *objc_title = [NSString stringWithUTF8String:title];
        NSString *objc_msg = [NSString stringWithUTF8String:msg];
        NSString *objc_soundName = nil;
        if(soundName != NULL && soundName != NULL && strlen(soundName) > 0) {
            objc_soundName = [NSString stringWithUTF8String:soundName];
        }
        NSString *objc_userInfoJson = nil;
        if(userInfo != NULL && userInfo != NULL && strlen(userInfo) > 0) {
            objc_userInfoJson = [NSString stringWithUTF8String:userInfo];
        }
        [[SDKFacade sharedInstance] pushLocalNotification:objc_key title:objc_title msg:objc_msg pushTime:pushTime interval:interval repeat:repeat useSound:useSound soundName:objc_soundName userInfo:[SDKJSONHelper toArrayOrNSDictionary:objc_userInfoJson]];
    }
    
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
    void pushLocalNotificationWithDateStr(const char* _Nonnull key, const char* _Nonnull title, const char* _Nonnull msg, const char* _Nonnull dateStr, int interval, bool repeat, bool useSound, const char* _Nullable soundName, const char* _Nonnull userInfo)
    {
        NSString *objc_key = [NSString stringWithUTF8String:key];
        NSString *objc_title = [NSString stringWithUTF8String:title];
        NSString *objc_msg = [NSString stringWithUTF8String:msg];
        NSString *objc_dateStr = [NSString stringWithUTF8String:dateStr];
        NSString *objc_soundName = nil;
        if(soundName != NULL && soundName != NULL && strlen(soundName) > 0) {
            objc_soundName = [NSString stringWithUTF8String:soundName];
        }
        NSString *objc_userInfoJson = nil;
        if(userInfo != NULL && userInfo != NULL && strlen(userInfo) > 0) {
            objc_userInfoJson = [NSString stringWithUTF8String:userInfo];
        }
        [[SDKFacade sharedInstance] pushLocalNotification:objc_key title:objc_title msg:objc_msg dateStr:objc_dateStr interval:interval repeat:repeat useSound:useSound soundName:objc_soundName userInfo:[SDKJSONHelper toArrayOrNSDictionary:objc_userInfoJson]];
    }
    
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
    void pushRemoteNotification(const char* _Nullable key, const char* _Nonnull title, const char* _Nonnull content, long pushTime, bool useLocalTimeZone, const char* _Nullable facebookIds, const char* _Nullable uuids, const char* _Nullable topic, int iosBadge, bool useSound, const char* _Nullable soundName, const char* _Nullable dataJson)
    {
        NSString *objc_key = nil;
        if(key != NULL && key != NULL && strlen(key) > 0) {
            objc_key = [NSString stringWithUTF8String:key];
        }
        NSString *objc_title = [NSString stringWithUTF8String:title];
        NSString *objc_msg = [NSString stringWithUTF8String:content];
        NSString *objc_facebookIds = nil;
        if(facebookIds != NULL && facebookIds != NULL && strlen(facebookIds) > 0) {
            objc_facebookIds = [NSString stringWithUTF8String:facebookIds];
        }
        NSString *objc_uuids = nil;
        if(uuids != NULL && uuids != NULL && strlen(uuids) > 0) {
            objc_uuids = [NSString stringWithUTF8String:uuids];
        }
        NSString *objc_topic = nil;
        if(topic != NULL && topic != NULL && strlen(topic) > 0) {
            objc_topic = [NSString stringWithUTF8String:topic];
        }
        NSString *objc_soundName = nil;
        if(soundName != NULL && soundName != NULL && strlen(soundName) > 0) {
            objc_soundName = [NSString stringWithUTF8String:soundName];
        }
        NSDictionary *data = nil;
        if(dataJson != NULL && dataJson != NULL && strlen(dataJson) > 0) {
            data = [SDKJSONHelper toArrayOrNSDictionary:[NSString stringWithUTF8String:dataJson]];
        }
        [[SDKFacade sharedInstance] pushRemoteNotification:objc_key title:objc_title content:objc_msg pushTime:pushTime useLocalTimeZone:useLocalTimeZone facebookIds:objc_facebookIds uuids:objc_uuids topic:objc_topic iosBadge:iosBadge useSound:useSound soundName:objc_soundName extraData:data];
    }
    
    void cancelRemoteNotification(const char* _Nonnull key)
    {
        NSString *objc_key = [NSString stringWithUTF8String:key];
        [[SDKFacade sharedInstance] cancelRemoteNotification:objc_key];
    }
//    void pushRepeatRemoteNotification(const char* _Nonnull messageId, const char* _Nonnull title, const char* _Nonnull content, long pushTime, bool useLocalTimeZone, const char* _Nullable facebookIds, const char* _Nullable uuids, const char* _Nullable topic, int iosBadge, bool useSound, const char* _Nullable soundName, const char* _Nullable dataJson, int repeatInterval)
//    {
//        NSString *objc_key = [NSString stringWithUTF8String:messageId];
//        NSString *objc_title = [NSString stringWithUTF8String:title];
//        NSString *objc_msg = [NSString stringWithUTF8String:content];
//        NSString *objc_facebookIds = nil;
//        if(facebookIds != NULL && facebookIds != NULL && strlen(facebookIds) > 0) {
//            objc_facebookIds = [NSString stringWithUTF8String:facebookIds];
//        }
//        NSString *objc_uuids = nil;
//        if(uuids != NULL && uuids != NULL && strlen(uuids) > 0) {
//            objc_uuids = [NSString stringWithUTF8String:uuids];
//        }
//        NSString *objc_topic = nil;
//        if(topic != NULL && topic != NULL && strlen(topic) > 0) {
//            objc_topic = [NSString stringWithUTF8String:topic];
//        }
//        NSString *objc_soundName = nil;
//        if(soundName != NULL && soundName != NULL && strlen(soundName) > 0) {
//            objc_soundName = [NSString stringWithUTF8String:soundName];
//        }
//        NSDictionary *data = nil;
//        if(dataJson != NULL && dataJson != NULL && strlen(dataJson) > 0) {
//            data = [SDKJSONHelper toArrayOrNSDictionary:[NSString stringWithUTF8String:dataJson]];
//        }
//        [[SDKFacade sharedInstance] pushRepeatRemoteNotification:objc_key title:objc_title content:objc_msg pushTime:pushTime useLocalTimeZone:useLocalTimeZone facebookIds:objc_facebookIds uuids:objc_uuids topic:objc_topic iosBadge:iosBadge useSound:useSound soundName:objc_soundName extraData:data repeatInterval:repeatInterval];
//    }
//
//    void cancelRepeatRemoteNotification(const char* _Nonnull messageId)
//    {
//        NSString *objc_key = [NSString stringWithUTF8String:messageId];
//        [[SDKFacade sharedInstance] cancelRepeatRemoteNotification:objc_key];
//    }
    
    void saveBase64ImageToCameraRoll(const char* _Nonnull base64Image)
    {
        NSString *obj_str = [NSString stringWithUTF8String:base64Image];
        [SDKFacade saveBase64ImageToCameraRoll:obj_str];
    }
    
    void takeScreenshotToCameraRoll()
    {
        [SDKFacade takeScreenshotToCameraRoll];
    }
    
    const char * _Nonnull takeScreenshotToDocumentsDirectory()
    {
        return returnStr([[SDKFacade takeScreenshotToDocumentsDirectory] UTF8String]);
    }
    
    const char * _Nonnull takeScreenshotToDirectoryAtPath(const char* _Nonnull path)
    {
        NSString *obj_path = [NSString stringWithUTF8String:path];
        return returnStr([[SDKFacade takeScreenshotToDirectoryAtPath:obj_path] UTF8String]);
    }
    
    bool isIPhoneX()
    {
        return [SDKFacade isIPhoneX];
    }
    
    bool isIPad()
    {
        return [SDKFacade isIPad];
    }
    
    bool hasNewVersion()
    {
        return [[SDKFacade sharedInstance] hasNewVersion];
    }

    const char * _Nonnull getAppstoreVersion()
    {
        return returnStr([[SDKFacade sharedInstance] getAppstoreVersion].UTF8String);
    }
    
    bool hasNotch()
    {
        return [[SDKFacade sharedInstance] hasNotch];
    }

    int getNotchHeight()
    {
        return [[SDKFacade sharedInstance] getNotchHeight];
    }

    int getIdCardVerifiedAge()
    {
        return [[SDKFacade sharedInstance] getIdCardVerifiedAge];
    }

    void verifyIdCard(const char* _Nonnull uuid)
    {
        NSString *obj_uuid = [NSString stringWithUTF8String:uuid];
        [[SDKFacade sharedInstance] verifyIdCard:obj_uuid];
    }

    void resetVerifyIdCard()
    {
        [[SDKFacade sharedInstance] resetVerifyIdCard];
    }

    bool justShowFullAd()
    {
        return [SDKFacade sharedInstance].justShowFullAd;
    }

    void requestPhotoWritePermission()
    {
        [[SDKFacade sharedInstance] requestPhotoWritePermission:^(BOOL permit) {
            Send("RiseSdkListener", "requestPhotoWritePermissionResult", permit ? "1" : "0");
        }];
    }
    
    bool hasPhotoWritePermission()
    {
        return [[SDKFacade sharedInstance] hasPhotoWritePermission];
    }
    
    void openSettingPage()
    {
        [[SDKFacade sharedInstance] openSettingPage];
    }
    
    bool isRecording()
    {
        return [[SDKFacade sharedInstance] isRecording];
    }
    
    void startRecording()
    {
        [[SDKFacade sharedInstance] startRecording];
    }
    
    void pauseRecording()
    {
        [[SDKFacade sharedInstance] pauseRecording];
    }
    
    void continueRecording()
    {
        [[SDKFacade sharedInstance] continueRecording];
    }
    
    void stopRecording()
    {
        [[SDKFacade sharedInstance] stopRecording:^(BOOL result) {
            const char * _msg = result ? "true" : "";
            Send("RiseSdkListener", "onRecordingStoped", _msg);
        }];
    }
    
    void editVideo(const char* _Nonnull path)
    {
        NSString *obj_path = [NSString stringWithUTF8String:path];
        [[SDKFacade sharedInstance] editVideo:obj_path complete:^(NSString * _Nullable localIdent) {
            const char * _msg = localIdent ? [localIdent UTF8String] : "";
            Send("RiseSdkListener", "onEditVideoComplete", _msg);
        }];
    }
    
    void sharePhotos(const char* _Nonnull photoIdentifiers)
    {
        NSString *idents = [NSString stringWithUTF8String:photoIdentifiers];
        NSArray *arr = [idents componentsSeparatedByString:@","];
        [[SDKFacade sharedInstance] sharePhotos:arr result:^(BOOL success, NSString * _Nullable error) {
            const char * _msg = error? [error UTF8String] : "";
            if (success) {
                Send("RiseSdkListener", "onSharePhotosSuccess", _msg);
            } else {
                Send("RiseSdkListener", "onSharePhotosFailure", _msg);
            }
        }];
    }
    
    void shareVideos(const char* _Nonnull photoIdentifiers)
    {
        NSString *idents = [NSString stringWithUTF8String:photoIdentifiers];
        NSArray *arr = [idents componentsSeparatedByString:@","];
        [[SDKFacade sharedInstance] shareVideos:arr result:^(BOOL success, NSString * _Nullable error) {
            const char * _msg = error? [error UTF8String] : "";
            if (success) {
                Send("RiseSdkListener", "onShareVideosSuccess", _msg);
            } else {
                Send("RiseSdkListener", "onShareVideosFailure", _msg);
            }
        }];
    }
    
    void shareRecentRecordVideo()
    {
        [[SDKFacade sharedInstance] shareRecentRecordVideo:^(BOOL success, NSString * _Nullable error) {
            const char * _msg = error? [error UTF8String] : "";
            if (success) {
                Send("RiseSdkListener", "onShareVideosSuccess", _msg);
            } else {
                Send("RiseSdkListener", "onShareVideosFailure", _msg);
            }
        }];
    }
    
    void showPromotion(float x, float y)
    {
        [[SDKFacade sharedInstance] showPromotion:CGPointMake(x, y)];
    }

    void copyToClipboard(const char* _Nonnull copyStr)
    {
        NSString *oc_copyStr = [NSString stringWithUTF8String:copyStr];
        [[SDKFacade sharedInstance] copyToClipboard:oc_copyStr];
    }

    long getFreeMem()
    {
        return [SDKFacade getFreeMem];
    }


    void mmEnableNumberConfuse(bool enableConfuse)
    {
        [[SDKFacade sharedInstance] mmEnableNumberConfuse:enableConfuse];
    }

    //MMKV
    void mmSetIntValue(const char* _Nonnull key, int value)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        [[SDKFacade sharedInstance] mmSetIntValue:obj_key value:value];
    }
    int mmGetIntValue(const char* _Nonnull key, int defaultValue)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] mmGetIntValue:obj_key defaultValue:defaultValue];
    }
    void mmSetLongValue(const char* _Nonnull key, long value)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        [[SDKFacade sharedInstance] mmSetLongValue:obj_key value:value];
    }
    long mmGetLongValue(const char* _Nonnull key, long defaultValue)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] mmGetLongValue:obj_key defaultValue:defaultValue];
    }
    void mmSetBoolValue(const char* _Nonnull key, bool value)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        [[SDKFacade sharedInstance] mmSetBoolValue:obj_key value:value];
    }
    bool mmGetBoolValue(const char* _Nonnull key, bool defaultValue)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] mmGetBoolValue:obj_key defaultValue:defaultValue];
    }
    void mmSetFloatValue(const char* _Nonnull key, float value)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        [[SDKFacade sharedInstance] mmSetFloatValue:obj_key value:value];
    }
    float mmGetFloatValue(const char* _Nonnull key, float defaultValue)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] mmGetFloatValue:obj_key defaultValue:defaultValue];
    }
    void mmSetStringValue(const char* _Nonnull key, const char* _Nullable value)
    {
        if (value != NULL) {
            NSString *obj_key = [NSString stringWithUTF8String:key];
            NSString *obj_value = [NSString stringWithUTF8String:value];
            [[SDKFacade sharedInstance] mmSetStringValue:obj_key value:obj_value];
        }
    }
    const char * _Nonnull mmGetStringValue(const char* _Nonnull key, const char* _Nonnull defaultValue)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        NSString *obj_value = [NSString stringWithUTF8String:defaultValue];
        return returnStr([[[SDKFacade sharedInstance] mmGetStringValue:obj_key defaultValue:obj_value] UTF8String]);
    }
    bool mmContainsKey(const char* _Nonnull key)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        return [[SDKFacade sharedInstance] mmContainsKey:obj_key];
    }
    void mmRemoveKey(const char* _Nonnull key)
    {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        [[SDKFacade sharedInstance] mmRemoveKey:obj_key];
    }
    void mmRemoveKeys(const char* _Nonnull keys)
    {
        NSString *obj_keys = [NSString stringWithUTF8String:keys];
        [[SDKFacade sharedInstance] mmRemoveKeys:obj_keys];
    }
    void requestIDFA()
    {
        [SDKFacade requestIDFA];
    }
    bool joinQQGroup(const char * _Nullable groupUin, const char * _Nullable key)
    {
        if (groupUin != NULL && key != NULL) {
            NSString *obj_gid = [NSString stringWithUTF8String:groupUin];
            NSString *obj_key = [NSString stringWithUTF8String:key];
            return [SDKFacade joinQQGroup:obj_gid key:obj_key];
        }
        return false;
    }
    


# pragma mark ThirdLogin methods
    void showGameProtocolDialog()
    {
        [[SDKFacade sharedInstance] showGameProtocolDialog];
    }

    void showPrivacyDialog()
    {
        [[SDKFacade sharedInstance] showPrivacyDialog];
    }

    void showUserAgreementDialog()
    {
        [[SDKFacade sharedInstance] showUserAgreementDialog];
    }

    void loginNoAnonymous()
    {
        [[SDKFacade sharedInstance] loginNoAnonymous];
    }

    void loginNoAnonymousCancelable()
    {
        [[SDKFacade sharedInstance] loginNoAnonymousCancelable];
    }

    void setIdCardVerified(int age){
        [[SDKFacade sharedInstance] setIdCardVerified:age];
    }

    int getIdCardVerifyedAge(){
        return [[SDKFacade sharedInstance] getIdCardVerifiedAge];
    }

    long getTotalDiskSize()
    {
        return [SDKFacade getTotalDiskSize];
    }
    long getAvailableDiskSize()
    {
        return [SDKFacade getAvailableDiskSize];
    }

    void displayHelpEngagement(const char * _Nonnull uid, const char * _Nonnull userInfoJson)
    {
        [[SDKFacade sharedInstance] displayHelpEngagement:[NSString stringWithUTF8String:uid] customInfo:[NSString stringWithUTF8String:userInfoJson]];
    }
  
    void copyText(const char* _Nonnull text) {
        [[SDKFacade sharedInstance] copyToClipboard:[NSString stringWithUTF8String:text]];
    }

    void showProgressBar() {
      [SDKHelper showLoading];
    }

    void hideProgressBar() {
      [SDKHelper hideLoading];
    }

    void playAHAP(const char* _Nonnull name, const char* _Nullable folder)
    {
        NSString *oc_name = [NSString stringWithUTF8String:name];
        if (folder != NULL) {
            NSString *oc_folder = [NSString stringWithUTF8String:folder];
            [SDKFacade playAHAP:oc_name inDirectory:oc_folder];
        } else {
            [SDKFacade playAHAP:oc_name inDirectory:nil];
        }
    }

    void cloudFunc(const char* method, const char* params){
        if (method != NULL) {
            NSString *funcKey = [NSString stringWithUTF8String:method];
            if (params != NULL) {
                NSString *urlParams = [NSString stringWithUTF8String:method];
                NSData* data = [[NSData alloc] initWithBytes:params length:strlen(params)]; 
                NSError * error;
                NSDictionary* dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:&error];
                if (error != nil) {
                    [[SDKFacade sharedInstance] cloudFunc:funcKey params:nil];
                }
                [[SDKFacade sharedInstance] cloudFunc:funcKey params:dict];
            } else {
                [[SDKFacade sharedInstance] cloudFunc:funcKey params:nil];
            }
        }
    }

    void cloudFuncWithoutLogin(const char* method, const char* params){
        if (method != NULL) {
            NSString *funcKey = [NSString stringWithUTF8String:method];
            if (params != NULL) {
                NSString *urlParams = [NSString stringWithUTF8String:method];
                NSData* data = [[NSData alloc] initWithBytes:params length:strlen(params)];
                NSError * error;
                NSDictionary* dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:&error];
                if (error != nil) {
                    [[SDKFacade sharedInstance] cloudFuncWithoutLogin:funcKey params:nil];
                }
                [[SDKFacade sharedInstance] cloudFuncWithoutLogin:funcKey params:dict];
            } else {
                [[SDKFacade sharedInstance] cloudFuncWithoutLogin:funcKey params:nil];
            }
        }
    }

    void adultVerify(const char* name, const char* idCard){
        if (name != NULL && idCard != NULL) {
            NSString * _name = [NSString stringWithUTF8String:name];
            NSString * _idCard = [NSString stringWithUTF8String:idCard];
            [[SDKFacade sharedInstance] idCardVerify:_name card:_idCard];
        }
    }

//bool isNotificationEnabled(){
//    
//}
//
//void enableNotification(){
//    
//}
//
//    void initAds(){
//        [[SDKFacade sharedInstance] startInitAd];
//    }
//
//    void requestGDPR(){
//        [[SDKFacade sharedInstance] requestGDPR];
//    }
//
//    void openFacebook(const char* pageId){
//        NSString* str = [NSString stringWithUTF8String:pageId];
//        [SDKFacade openFacebook:str];
//    }
//
//    void getApp(const char* appstoreId){
//        NSString* str = [NSString stringWithUTF8String:appstoreId];
//        [SDKFacade getApp:str];
//    }

#ifdef __cplusplus
} // extern "C"
#endif
