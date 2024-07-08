//
//  SDKFacadeCocos.c
//  Pods
//
//  Created by 余冰星 on 2017/6/20.
//
//

#include <stdio.h>
#include "SDKFacadeCocos.hpp"
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKJSONHelper.h>
#import <IvyiOSSdk/SDKAdDelegate.h>
#import <IvyiOSSdk/SDKAdDelegate.h>
#import <IvyiOSSdk/SDKPaymentDelegate.h>
#import <IvyiOSSdk/SDKSNSDelegate.h>
#import <IvyiOSSdk/SDKWebviewDelegate.h>
@interface MyWebviewHandler : NSObject<SDKWebviewDelegate>
-(instancetype)initWithDelegate:(WebviewDelegate *)delegate;
@end
@implementation MyWebviewHandler
{
@private
    WebviewDelegate *_delegate;
}
-(instancetype)initWithDelegate:(WebviewDelegate *)delegate
{
    if ((self = [super init])) {
        _delegate = delegate;
    }
    return self;
}
-(void)onWebviewLoadSuccess
{
    if (_delegate)
    {
        @try {
            _delegate->onWebviewLoadSuccess();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onWebviewLoadFailure
{
    if (_delegate)
    {
        @try {
            _delegate->onWebviewLoadFailure();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onWebviewClose
{
    if (_delegate)
    {
        @try {
            _delegate->onWebviewClose();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)jsCallOC:(NSString *)param
{
    if (_delegate)
    {
        @try {
            _delegate->jsCallOC([param UTF8String]);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
@end

@interface MySDKHandler : NSObject<SDKDelegate>
-(instancetype)initWithDelegate:(SDKResultDelegate *)delegate;
@end
@implementation MySDKHandler
{
@private
    SDKResultDelegate *_delegate;
}
-(instancetype)initWithDelegate:(SDKResultDelegate *)delegate
{
    if ((self = [super init])) {
        _delegate = delegate;
    }
    return self;
}
-(void)onInitialized
{
    if (_delegate)
    {
        @try {
            _delegate->onInitialized();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onDeepLinkReceived:(NSURL *)url
{
    if (_delegate)
    {
        @try {
            if (url) {
                _delegate->onDeepLinkReceived([[url absoluteString] UTF8String]);
            }
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onNetworkChanged:(int)status
{
    if (_delegate)
    {
        @try {
            _delegate->onNetworkChanged(status);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onReceiveServerExtra:(NSString *)data
{
    if (_delegate)
    {
        @try {
            data = data ? data : @"{}";
            const char * extra = [data UTF8String];
            _delegate->onReceiveServerExtra(extra);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onReceiveNotificationData:(NSString *)data
{
    if (_delegate)
    {
        @try {
            data = data ? data : @"{}";
            const char * extra = [data UTF8String];
            _delegate->onReceiveNotificationData(extra);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
- (void)onThirdLoginAnonymous {
    if(_delegate){
        @try {
            _delegate->onReceiveLoginSuccess();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}


- (void)onThirdLoginCancel:(NSString * _Nonnull)platform{
    
}


- (void)onThirdLoginClientNotInstalled:(NSString * _Nonnull)platform {
    
}


- (void)onThirdLoginError:(NSString * _Nonnull)errorMsg platformType:(NSString * _Nonnull)platform {
    if(_delegate){
        @try {
            _delegate->onReceiveLoginFailure();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}


- (void)onThirdLoginPrivacyAccept:(BOOL)isAccept {
    if(_delegate){
        @try {
            _delegate->onPrivacyAccept();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}


- (void)onThirdLoginReceiveIdCardVerifiedResult:(int)age {
    if(_delegate){
        @try {
            _delegate->onIdCardVerified();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}


- (void)onThirdLoginSuccess:(NSString * _Nonnull)openId platformType:(NSString * _Nonnull)platform {
    if(_delegate){
        @try {
            _delegate->onReceiveLoginSuccess();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}

- (void)onAttAccepted:(BOOL)isAccept {
    if(_delegate){
        @try {
            _delegate->onAttAccepted(isAccept);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}

-(void)unreadMessageCount:(int)count {
    if(_delegate){
        @try {
            _delegate->unreadMeasgeCount(count);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}

- (void)onNotificationOpen:(BOOL)isOpen{
    if(_delegate){
        @try {
            _delegate->onNotificationOpen(isOpen);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}

-(void)cacheUrlSuccess:(NSString *)tag path:(NSString *)path
{
    if (_delegate)
    {
        @try {
            _delegate->cacheUrlSuccess([tag UTF8String], [path UTF8String]);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)cacheUrlFailure:(NSString *)tag
{
    if (_delegate)
    {
        @try {
            _delegate->cacheUrlFailure([tag UTF8String]);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
@end
@interface AdHandler : NSObject<SDKAdDelegate>
-(instancetype)initWithDelegate:(AdDelegate *)delegate;
@end
@implementation AdHandler
{
@private
    AdDelegate *_delegate;
}
-(instancetype)initWithDelegate:(AdDelegate *)delegate
{
    if ((self = [super init])) {
        _delegate = delegate;
    }
    return self;
}
/// Tells the delegate that the interstitial based video ad failed to load.
- (void)adFailed:(NSString *)tag adType:(int)adType forError:(NSError *)error
{
    if (_delegate)
    {
        @try {
            _delegate->adFailed([tag UTF8String], adType, error ? [[error localizedDescription] UTF8String] : "");
        } @catch (NSException *exception) {
            DLog(@"Exception: adType: %d, desc: %@", adType, [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: adType: %d, desc: %@", adType, [error localizedDescription]);
        } @finally {
            
        }
    }
}
/// Tells the delegate that a interstitial based video ad was received.
- (void)adLoaded:(NSString *)tag adType:(int)adType
{
    if (_delegate)
    {
        @try {
            _delegate->adLoaded([tag UTF8String], adType);
        } @catch (NSException *exception) {
            DLog(@"Exception: adType: %d, desc: %@", adType, [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: adType: %d, desc: %@", adType, [error localizedDescription]);
        } @finally {
            
        }
    }
}
/// Tells the delegate that the interstitial based video ad closed.
- (void)adDidClose:(NSString *)tag adType:(int)adType
{
    if (_delegate)
    {
        @try {
            _delegate->adDidClose([tag UTF8String], adType);
        } @catch (NSException *exception) {
            DLog(@"Exception: adType: %d, desc: %@", adType, [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: adType: %d, desc: %@", adType, [error localizedDescription]);
        } @finally {
            
        }
    }
}
/// Tells the delegate that the interstitial based video ad did click.
- (void)adDidClick:(NSString *)tag adType:(int)adType
{
    if (_delegate)
    {
        @try {
            _delegate->adDidClick([tag UTF8String], adType);
        } @catch (NSException *exception) {
            DLog(@"Exception: adType: %d, desc: %@", adType, [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: adType: %d, desc: %@", adType, [error localizedDescription]);
        } @finally {
            
        }
    }
}
- (void)adDidShown:(NSString *)tag adType:(int)adType
{
    if (_delegate)
    {
        @try {
            _delegate->adDidShown([tag UTF8String], adType);
        } @catch (NSException *exception) {
            DLog(@"Exception: adType: %d, desc: %@", adType, [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: adType: %d, desc: %@", adType, [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)adShowFailed:(NSString *)tag adType:(int)adType
{
    if (_delegate)
    {
        @try {
            _delegate->adShowFailed([tag UTF8String], adType);
        } @catch (NSException *exception) {
            DLog(@"Exception: adType: %d, desc: %@", adType, [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: adType: %d, desc: %@", adType, [error localizedDescription]);
        } @finally {
            
        }
    }
}
- (void)adReward:(NSString *)tag rewardId:(int)rewardId
{
    if (_delegate)
    {
        @try {
            _delegate->adReward([tag UTF8String], rewardId);
        } @catch (NSException *exception) {
            DLog(@"Exception: adType: video, desc: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: adType: video, desc: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}

- (void)deliciousIconClick:(NSString *)bannerDownPath jumpURL:(NSString *)url
{
    if (_delegate)
    {
        @try {
            _delegate->deliciousIconClick([bannerDownPath UTF8String], [url UTF8String]);
        } @catch (NSException *exception) {
            DLog(@"Exception: adType: video, desc: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: adType: video, desc: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
@end
@interface MyPaymentHandler : NSObject<SDKPaymentDelegate>
-(instancetype)initWithDelegate:(PaymentDelegate *)delegate;
@end
@implementation MyPaymentHandler
{
@private
    PaymentDelegate *paymentDelegate;
}
-(instancetype)initWithDelegate:(PaymentDelegate *)delegate
{
    if ((self = [super init])) {
        paymentDelegate = delegate;
    }
    return self;
}
-(void)onPaymentReady
{
    if(paymentDelegate)
    {
        @try {
            paymentDelegate->onPaymentReady();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onPaymentSuccess:(int)paymentId
{
    if(paymentDelegate)
    {
        @try {
            paymentDelegate->onPaymentSuccess(paymentId);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onPaymentSuccess:(int)paymentId payload:(NSString *)payload
{
    if(paymentDelegate)
    {
        @try {
            if (payload) {
                paymentDelegate->onPaymentSuccess(paymentId, [payload UTF8String]);
            }
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onPaymentSuccess:(int)paymentId payload:(NSString *)payload orderInfo:(NSString *)orderInfo
{
    if(paymentDelegate)
    {
        @try {
            if (payload) {
                paymentDelegate->onPaymentSuccessWithPayloadAndOrderInfo(paymentId, [payload UTF8String], [orderInfo UTF8String]);
            }
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onPaymentFailure:(int)paymentId forError:(NSString *)error
{
    if(paymentDelegate)
    {
        @try {
            paymentDelegate->onPaymentFailure(paymentId, error ? [error UTF8String] : "");
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onAppStorePayRequest:(int)paymentId
{
    if(paymentDelegate)
    {
        @try {
            paymentDelegate->onAppStorePayRequest(paymentId);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onCheckSubscriptionResult:(int)billId remainSeconds:(long)seconds
{
    if(paymentDelegate)
    {
        @try {
            paymentDelegate->onCheckSubscriptionResult(billId, seconds);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onRestoreSuccess:(int)paymentId
{
    if(paymentDelegate)
    {
        @try {
            paymentDelegate->onRestoreSuccess(paymentId);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onRestoreFailure:(NSString *)error
{
    if(paymentDelegate)
    {
        @try {
            paymentDelegate->onRestoreFailure(error ? [error UTF8String] : "");
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
@end
@interface MySNSHandler : NSObject<SDKSNSDelegate>
-(instancetype)initWithDelegate:(SNSDelegate *)delegate;
@end
@implementation MySNSHandler
{
@private
    SNSDelegate *snsDelegate;
}
-(instancetype)initWithDelegate:(SNSDelegate *)delegate
{
    if ((self = [super init])) {
        snsDelegate = delegate;
    }
    return self;
}
-(void)onGameCenterLoginSuccess:(NSString *)playerId
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->onGameCenterLoginSuccess([playerId UTF8String]);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)onGameCenterLoginFailure
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->onGameCenterLoginFailure();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)signInAppleSuccess:(NSString *)appleAccountInfo
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->signInAppleSuccess([appleAccountInfo UTF8String]);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)signInAppleFailure:(NSString *)error
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->signInAppleFailure([error UTF8String]);
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)snsLoginSuccess
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->snsLoginSuccess();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)snsLoginCancel
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->snsLoginCancel();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)snsLoginFailure:(NSString *)error
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->snsLoginFailure(error ? [error UTF8String] : "");
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)snsShareSuccess
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->snsShareSuccess();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)snsShareFailure
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->snsShareFailure();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
-(void)snsShareCancel
{
    if(snsDelegate)
    {
        @try {
            snsDelegate->snsShareCancel();
        } @catch (NSException *exception) {
            DLog(@"Exception: %@", [exception description]);
        } @catch (NSError *error) {
            DLog(@"Error: %@", [error localizedDescription]);
        } @finally {
            
        }
    }
}
@end

#ifdef __cplusplus
SDKResultDelegate * _sdkDelegate;
bool SDKFacadeCocos::isWebViewDisplayed()
{
    return [[SDKFacade sharedInstance] isWebViewDisplayed];
}

void SDKFacadeCocos::showWebView(const char * _Nullable url, int x, int y, int w, int h, WebviewDelegate* delegate)
{
    [[SDKFacade sharedInstance] showWebView:[NSString stringWithUTF8String:url] frame:CGRectMake(x, y, w, h) delegate:[[MyWebviewHandler alloc] initWithDelegate:delegate]];
}

void SDKFacadeCocos::closeWebView()
{
    [[SDKFacade sharedInstance] closeWebView];
}

const char *SDKFacadeCocos::getPushData()
{
    NSDictionary *data = [[SDKFacade sharedInstance] getPushData];
    NSString *result = [SDKJSONHelper toJSONString:data];
    return result ? [result UTF8String] : "{}";
}

const char *SDKFacadeCocos::getConfig(int cid)
{
    NSString *result = [[SDKFacade sharedInstance] getConfig:cid];
    return result ? [result UTF8String] : "";
}

void SDKFacadeCocos::openApplicationSetting(){
    [[SDKFacade sharedInstance] openApplicationSetting];
}

void SDKFacadeCocos::showAIHelp(const char* entranceId, const char* meta, const char* tags, const char* msg){
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

void SDKFacadeCocos::showSignleFAQ(const char* faqId, int moment){
    [[SDKFacade sharedInstance] showSignleFAQ:[NSString stringWithUTF8String:faqId] momemt:moment];
}

bool SDKFacadeCocos::isAIHelpInitialized() {
    return  [[SDKFacade sharedInstance] isAIHelpInitialized];
}

void SDKFacadeCocos::loadAIHelpUnreadMessageCount(bool onlyOnce){
    [[SDKFacade sharedInstance] loadAIHelpUnreadMessageCount:onlyOnce];
}

void SDKFacadeCocos::stopLoadAIHelpUnreadMessageCount() {
    [[SDKFacade sharedInstance] stopLoadAIHelpUnreadMessageCount];
}

void SDKFacadeCocos::closeAIHelp(){
    [[SDKFacade sharedInstance] closeAIHelp];
}

void SDKFacadeCocos::switchLanguage(const char* lang){
    [[SDKFacade sharedInstance] switchLanguage:[NSString stringWithUTF8String:lang]];
}

bool SDKFacadeCocos::hasGdpr() {
    return [[SDKFacade sharedInstance] hasGdpr];
}

void SDKFacadeCocos::resetGdpr() {
    [[SDKFacade sharedInstance] resetGdpr];
}

void SDKFacadeCocos::setAdsUnderAgeMode(bool value) {
    [[SDKFacade sharedInstance] setAdsUnderAgeMode:value];
}

bool SDKFacadeCocos::isAdsEnabled()
{
    return [[SDKFacade sharedInstance] isAdsEnabled];
}

void SDKFacadeCocos::setAdsEnable(bool enable)
{
    [[SDKFacade sharedInstance] setAdsEnable:enable];
}

int SDKFacadeCocos::getScreenWidth()
{
    return [[SDKFacade sharedInstance] getScreenWidth];
}

int SDKFacadeCocos::getScreenHeight()
{
    return [[SDKFacade sharedInstance] getScreenHeight];
}

int SDKFacadeCocos::getScreenDesignWidth()
{
    return [[SDKFacade sharedInstance] getScreenDesignWidth];
}

int SDKFacadeCocos::getScreenDesignHeight()
{
    return [[SDKFacade sharedInstance] getScreenDesignHeight];
}

void SDKFacadeCocos::onCreate()
{
    [[SDKFacade sharedInstance] onCreate];
}

bool SDKFacadeCocos::isBannerShowing()
{
    return [[SDKFacade sharedInstance] isBannerShowing];
}

bool SDKFacadeCocos::isBannerAvailable()
{
    return [[SDKFacade sharedInstance] isBannerAvailable];
}

bool SDKFacadeCocos::isBannerAvailable(const char *tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    return [[SDKFacade sharedInstance] isBannerAvailable:objc_tag];
}

void SDKFacadeCocos::loadBannerAd(const char *tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] loadBannerAd:objc_tag];
}

void SDKFacadeCocos::showBanner(int pos)
{
    [[SDKFacade sharedInstance] showBanner:pos];
}

void SDKFacadeCocos::showBanner(const char *tag, int pos)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] showBanner:objc_tag atPos:pos];
}

void SDKFacadeCocos::showBanner(const char *tag, float x, float y, float w, float h)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] showBanner:objc_tag withFrame:CGRectMake(x, y, w, h)];
}

void SDKFacadeCocos::closeBanner()
{
    [[SDKFacade sharedInstance] closeBanner];
}

bool SDKFacadeCocos::isVideoAvailable()
{
    return [[SDKFacade sharedInstance] isVideoAvailable];
}

bool SDKFacadeCocos::isVideoAvailable(const char *tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    return [[SDKFacade sharedInstance] isVideoAvailable:objc_tag];
}

void SDKFacadeCocos::loadRewardVideo(const char * _Nonnull tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] loadRewardVideo:objc_tag];
}

void SDKFacadeCocos::showRewardVideo(int rewardId)
{
    [[SDKFacade sharedInstance] showRewardVideo:rewardId];
}

void SDKFacadeCocos::showRewardVideo(const char *tag, int rewardId)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] showRewardVideoWithTag:objc_tag rewardId:rewardId];
}

bool SDKFacadeCocos::isInterstitialAvailable(const char *tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    return [[SDKFacade sharedInstance] isInterstitialAvailable:objc_tag];
}

void SDKFacadeCocos::loadInterstitialAd(const char *tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] loadInterstitialAd:objc_tag];
}

void SDKFacadeCocos::showInterstitialAd(const char *tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] showInterstitialAd:objc_tag];
}

void SDKFacadeCocos::showInterstitialAd(const char *tag, int seconds)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] showInterstitialAd:objc_tag waitSeconds:seconds];
}

void SDKFacadeCocos::showInterstitialAd(const char *tag, int seconds, double timeInterval)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] showInterstitialAd:objc_tag waitSeconds:seconds timeInterval:timeInterval];
}

void SDKFacadeCocos::showIconAd(float width, float xPercent, float yPercent)
{
    [[SDKFacade sharedInstance] showIconAd:width xPercent:xPercent yPercent:yPercent];
}

void SDKFacadeCocos::showPopupIconAds()
{
    [[SDKFacade sharedInstance] showPopupIconAds];
}

void SDKFacadeCocos::hidePopupIconAds()
{
    [[SDKFacade sharedInstance] hidePopupIconAds];
}

const char * SDKFacadeCocos::getPopupIconAdsData()
{
    NSArray * arr = [[SDKFacade sharedInstance] getPopupIconAdsData];
    return [[SDKJSONHelper toJSONString:arr] UTF8String];
}

void SDKFacadeCocos::closeIconAd()
{
    [[SDKFacade sharedInstance] closeIconAd];
}

bool SDKFacadeCocos::isNativeAvailable(const char *tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    return [[SDKFacade sharedInstance] isNativeAvailable:objc_tag];
}

const char * _Nullable SDKFacadeCocos::fetchNativeAdJson(const char * _Nonnull tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    NSString * result = [[SDKFacade sharedInstance] fetchNativeAdJson:objc_tag];
    return result ? [result UTF8String] : "";
}

void SDKFacadeCocos::loadNativeAd(const char * _Nonnull tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] loadNativeAd:objc_tag];
}

void SDKFacadeCocos::closeNativeAd(const char * _Nonnull tag)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    [[SDKFacade sharedInstance] closeNativeAd:objc_tag];
}

void SDKFacadeCocos::showNativeAd(const char * _Nonnull tag, float x, float y, const char * _Nonnull json)
{
    NSString * objc_tag = [NSString stringWithUTF8String:tag];
    NSString * objc_json = [NSString stringWithUTF8String:json];
    float scale = [UIScreen mainScreen].scale;
    x = x / scale;
    y = y / scale;
    [[SDKFacade sharedInstance] showNativeAd:objc_tag x:x y:y configJson:objc_json];
}

void SDKFacadeCocos::showNativeAd(const char *_Nonnull tag, float x, float y, float w, float h, const char * _Nonnull json)
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

bool SDKFacadeCocos::isDeliciousAdAvailable()
{
    return [[SDKFacade sharedInstance] isDeliciousAdAvailable:NO];
}

void SDKFacadeCocos::showDeliciousInterstitialAd(const char * _Nonnull json)
{
    NSString * objc_json = [NSString stringWithUTF8String:json];
    [[SDKFacade sharedInstance] showDeliciousInterstitialAd:objc_json];
}

void SDKFacadeCocos::showDeliciousBannerAd(float x, float y, float w, float h, const char * _Nonnull json)
{
    NSString * objc_json = [NSString stringWithUTF8String:json];
    float scale = [UIScreen mainScreen].scale;
    x = x / scale;
    y = y / scale;
    w = w / scale;
    h = h / scale;
    [[SDKFacade sharedInstance] showDeliciousBannerAd:CGRectMake(x, y, w, h) configJson:objc_json];
}

void SDKFacadeCocos::closeDeliciousBannerAd()
{
    [[SDKFacade sharedInstance] closeDeliciousBannerAd];
}

void SDKFacadeCocos::showDeliciousIconAd(float x, float y, float w, float h, const char * _Nonnull json)
{
    NSString * objc_json = [NSString stringWithUTF8String:json];
    float scale = [UIScreen mainScreen].scale;
    x = x / scale;
    y = y / scale;
    w = w / scale;
    h = h / scale;
    [[SDKFacade sharedInstance] showDeliciousIconAd:CGRectMake(x, y, w, h) configJson:objc_json];
}

void SDKFacadeCocos::closeDeliciousIconAd()
{
    [[SDKFacade sharedInstance] closeDeliciousIconAd];
}

void SDKFacadeCocos::rateUs()
{
    [[SDKFacade sharedInstance] rateUs:5];
}

void SDKFacadeCocos::rateInApp()
{
    [[SDKFacade sharedInstance] rateInApp:5];
}

void SDKFacadeCocos::rateUs(float star)
{
    [[SDKFacade sharedInstance] rateUs:star];
}

void SDKFacadeCocos::rateInApp(float star)
{
    [[SDKFacade sharedInstance] rateInApp:star];
}

const char * _Nonnull SDKFacadeCocos::fetchDeepLink()
{
    NSString *url = [[SDKFacade sharedInstance] fetchDeepLink];
    return url ? [url UTF8String] : "";
}

bool SDKFacadeCocos::isNetworkConnected()
{
    return [[SDKFacade sharedInstance] isNetworkConnected];
}

bool SDKFacadeCocos::isNetworkAvailable()
{
    return [[SDKFacade sharedInstance] isNetworkConnected];
}

void SDKFacadeCocos::setAdDelegate(AdDelegate* delegate)
{
    [[SDKFacade sharedInstance] setAdDelegate:[[AdHandler alloc] initWithDelegate:delegate]];
}

void SDKFacadeCocos::setPaymentDelegate(PaymentDelegate* delegate)
{
    [[SDKFacade sharedInstance] setPaymentDelegate:[[MyPaymentHandler alloc] initWithDelegate:delegate]];
}

void SDKFacadeCocos::setSNSDelegate(SNSDelegate* delegate)
{
    [[SDKFacade sharedInstance] setSNSDelegate:[[MySNSHandler alloc] initWithDelegate:delegate]];
}

void SDKFacadeCocos::setSDKDelegate(SDKResultDelegate* delegate)
{
    _sdkDelegate = delegate;
    [[SDKFacade sharedInstance] setOnIdCardVerifiedDelegate:^{
        _sdkDelegate->onIdCardVerified();
    }];
    [[SDKFacade sharedInstance] setSdkDelegate:[[MySDKHandler alloc] initWithDelegate:delegate]];
}

void SDKFacadeCocos::setPayVerifyUrl(const char * _Nullable url)
{
    if (url != NULL) {
        NSString * objc_url = [NSString stringWithUTF8String:url];
        [[SDKFacade sharedInstance] setPayVerifyUrl:objc_url];
    }
}

void SDKFacadeCocos::pay(int payId)
{
    [[SDKFacade sharedInstance] pay:payId];
}

void SDKFacadeCocos::pay(int payId, const char *_Nonnull payload)
{
    NSString * objc_payload = [NSString stringWithUTF8String:payload];
    [[SDKFacade sharedInstance] pay:payId payload:objc_payload];
}

void SDKFacadeCocos::restorePayments()
{
    [[SDKFacade sharedInstance] restorePayments];
}

void SDKFacadeCocos::recheckFailedPayments()
{
    [[SDKFacade sharedInstance] recheckFailedPayments];
}

const char *SDKFacadeCocos::getPaymentData(int payId)
{
    NSString *data = [SDKJSONHelper toJSONString:[[SDKFacade sharedInstance] getPaymentData:payId]];
    return data ? [data UTF8String] : "{}";
}

const char *SDKFacadeCocos::getPaymentDatas()
{
    NSString *data = [[SDKFacade sharedInstance] getPaymentDatas];
    return data ? [data UTF8String] : "{}";
}

const char *SDKFacadeCocos::getExtraData()
{
    NSString *data = [[SDKFacade sharedInstance] getExtraDataJson];
    return data ? [data UTF8String] : "";
}

void SDKFacadeCocos::isSubscriptionActive()
{
    [[SDKFacade sharedInstance] isSubscriptionActive];
}

int* SDKFacadeCocos::getPurchasedIds()
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

void SDKFacadeCocos::clearPurchasedIds()
{
    [[SDKFacade sharedInstance] clearPurchasedIds];
}

void SDKFacadeCocos::clearPurchasedId(int paymentId)
{
    [[SDKFacade sharedInstance] clearPurchasedId:paymentId];
}

bool SDKFacadeCocos::isGameCenterAvailable()
{
    return [[SDKFacade sharedInstance] isGameCenterAvailable];
}

void SDKFacadeCocos::loginGameCenter()
{
    [[SDKFacade sharedInstance] loginGameCenter];
}

void SDKFacadeCocos::showLeaderboards()
{
    [[SDKFacade sharedInstance] showLeaderboards];
}

void SDKFacadeCocos::showLeaderboard(int leaderboardId)
{
    [[SDKFacade sharedInstance] showLeaderboard:leaderboardId];
}

void SDKFacadeCocos::showAchievements()
{
    [[SDKFacade sharedInstance] showAchievements];
}

void SDKFacadeCocos::submitScore(int leaderboardId, long long score)
{
    [[SDKFacade sharedInstance] submitScore:leaderboardId score:score];
}

long long SDKFacadeCocos::myHighScore(int leaderboardId)
{
    return [[SDKFacade sharedInstance] myHighScore:leaderboardId];
}

void SDKFacadeCocos::submitAchievement(int achievementId, double percent)
{
    [[SDKFacade sharedInstance] submitAchievement:achievementId percent:percent];
}

double SDKFacadeCocos::getAchievementProgress(int achievementId)
{
    return [[SDKFacade sharedInstance] getAchievementProgress:achievementId];
}

void SDKFacadeCocos::toast(const char *msg)
{
    [[SDKFacade sharedInstance] toast:[NSString stringWithUTF8String:msg] isLongTime:NO];
}

//void SDKFacadeCocos::startTraceWithName(const char * _Nonnull name)
//{
//    NSString * objc_name = [NSString stringWithUTF8String:name];
//    [[SDKFacade sharedInstance] startTraceWithName:objc_name];
//}
//
//void SDKFacadeCocos::stopTraceWithName(const char * _Nonnull name)
//{
//    NSString * objc_name = [NSString stringWithUTF8String:name];
//    [[SDKFacade sharedInstance] stopTraceWithName:objc_name];
//}
//
//void SDKFacadeCocos::incrementMetric(const char * _Nonnull name, int intValue)
//{
//    NSString * objc_name = [NSString stringWithUTF8String:name];
//    [[SDKFacade sharedInstance] incrementMetric:objc_name byInt:intValue];
//}

int SDKFacadeCocos::getRemoteConfigIntValue(const char * _Nonnull key)
{
    NSString * objc_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] getRemoteConfigIntValue:objc_key];
}

long SDKFacadeCocos::getRemoteConfigLongValue(const char * _Nonnull key)
{
    NSString * objc_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] getRemoteConfigLongValue:objc_key];
}

double SDKFacadeCocos::getRemoteConfigDoubleValue(const char * _Nonnull key)
{
    NSString * objc_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] getRemoteConfigDoubleValue:objc_key];
}

bool SDKFacadeCocos::getRemoteConfigBoolValue(const char * _Nonnull key)
{
    NSString * objc_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] getRemoteConfigBoolValue:objc_key];
}

const char * _Nonnull SDKFacadeCocos::getRemoteConfigStringValue(const char * _Nonnull key)
{
    NSString * objc_key = [NSString stringWithUTF8String:key];
    return [[[SDKFacade sharedInstance] getRemoteConfigStringValue:objc_key] UTF8String];
}

void SDKFacadeCocos::setUserPropertyString(const char * _Nonnull key, const char * _Nonnull value)
{
    NSString * objc_key = [NSString stringWithUTF8String:key];
    NSString * objc_value = [NSString stringWithUTF8String:value];
    [[SDKFacade sharedInstance] setUserPropertyString:objc_value forName:objc_key];
}

const char * _Nonnull SDKFacadeCocos::getSignedAppleUID()
{
    return [[[SDKFacade sharedInstance] getSignedAppleUID] UTF8String];
}

const char * _Nonnull SDKFacadeCocos::getSignedAppleInfo()
{
    NSString *json = [SDKJSONHelper toJSONString:[[SDKFacade sharedInstance] getSignedAppleInfo]];
    return [(json ?:@"") UTF8String];
}

bool SDKFacadeCocos::showSignInWithApple(float x, float y, float w, float h, bool useBlackBackground)
{
    float scale = [UIScreen mainScreen].scale;
    x = x / scale;
    y = y / scale;
    w = w / scale;
    h = h / scale;
    return [[SDKFacade sharedInstance] showSignInWithApple:CGRectMake(x, y, w, h) useBlackBackground:useBlackBackground];
}

void SDKFacadeCocos::hideSignInWithApple()
{
    [[SDKFacade sharedInstance] hideSignInWithApple];
}

void SDKFacadeCocos::login()
{
    [[SDKFacade sharedInstance] login:nil];
}

void SDKFacadeCocos::logout()
{
    [[SDKFacade sharedInstance] logout];
}

bool SDKFacadeCocos::isLogin()
{
    return [[SDKFacade sharedInstance] isLogin];
}

const char *SDKFacadeCocos::meFirstName()
{
    return [[[SDKFacade sharedInstance] meFirstName] UTF8String];
}

const char *SDKFacadeCocos::meLastName()
{
    return [[[SDKFacade sharedInstance] meLastName] UTF8String];
}

const char *SDKFacadeCocos::meName()
{
    return [[[SDKFacade sharedInstance] meName] UTF8String];
}

const char *SDKFacadeCocos::meId()
{
    return [[[SDKFacade sharedInstance] meId] UTF8String];
}

const char *SDKFacadeCocos::mePictureURL()
{
    return [[[SDKFacade sharedInstance] mePictureURL] UTF8String];
}

const char *SDKFacadeCocos::me()
{
    return [[[SDKFacade sharedInstance] me] UTF8String];
}

const char *SDKFacadeCocos::friends()
{
    return [[[SDKFacade sharedInstance] friends] UTF8String];
}

void SDKFacadeCocos::fetchFriends(bool invitable)
{
    [[SDKFacade sharedInstance] fetchFriends:invitable];
}

void SDKFacadeCocos::fetchScores()
{
    [[SDKFacade sharedInstance] fetchScores];
}

void SDKFacadeCocos::invite()
{
    [[SDKFacade sharedInstance] invite];
}

void SDKFacadeCocos::share()
{
    [[SDKFacade sharedInstance] share];
}

bool SDKFacadeCocos::isIPhoneX()
{
    return [SDKFacade isIPhoneX];
}

bool SDKFacadeCocos::hasNewVersion()
{
    return [[SDKFacade sharedInstance] hasNewVersion];
}

const char * _Nonnull SDKFacadeCocos::getAppstoreVersion()
{
    return [[SDKFacade sharedInstance] getAppstoreVersion].UTF8String;
}

bool SDKFacadeCocos::hasNotch()
{
    return [[SDKFacade sharedInstance] hasNotch];
}

int getNotchHeight()
{
    return [[SDKFacade sharedInstance] getNotchHeight];
}

int SDKFacadeCocos::getIdCardVerifiedAge()
{
    return [[SDKFacade sharedInstance] getIdCardVerifiedAge];
}

void SDKFacadeCocos::verifyIdCard(const char* _Nonnull uuid)
{
    NSString *obj_uuid = [NSString stringWithUTF8String:uuid];
    [[SDKFacade sharedInstance] verifyIdCard:obj_uuid];
}

void SDKFacadeCocos::resetVerifyIdCard()
{
    [[SDKFacade sharedInstance] resetVerifyIdCard];
}

bool SDKFacadeCocos::isIPad()
{
    return [SDKFacade isIPad];
}

void SDKFacadeCocos::shareContent(const char *contentURL, const char *tag, const char *quote)
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

void SDKFacadeCocos::shareSheet(const char *linkURL, const char *tag, const char *quote)
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

void SDKFacadeCocos::shareSheetOS(const char *linkURL, const char *title)
{
    NSString *_title = nil;
    if(title != NULL && title != NULL && strlen(title) > 0) {
        _title = [NSString stringWithUTF8String:title];
    }
    [[SDKFacade sharedInstance] shareSheetOS:[NSString stringWithUTF8String:linkURL] title:_title];
}

void SDKFacadeCocos::logError(int errorcode, const char *errorDomain, const char *reason, const char *desc, const char *suggest)
{
    NSString *objc_errorDomain = [NSString stringWithUTF8String:errorDomain];
    NSString *objc_reason = [NSString stringWithUTF8String:reason];
    NSString *objc_desc = [NSString stringWithUTF8String:desc];
    NSString *objc_suggest = suggest != NULL ? [NSString stringWithUTF8String:suggest] : nil;
    [[SDKFacade sharedInstance]logError:errorcode errorDomain:objc_errorDomain reason:objc_reason description:objc_desc suggest:objc_suggest];
}

void SDKFacadeCocos::logIvyEventWithData(const char *name, const char *data)
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

void SDKFacadeCocos::logEventWithData(const char *name, const char *data)
{
    if (name != NULL) {
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

void SDKFacadeCocos::logPlayerLevel(int levelId)
{
    [[SDKFacade sharedInstance] logPlayerLevel:levelId];
}

void SDKFacadeCocos::logPageStart(const char* pageName)
{
    if (pageName != NULL) {
        [[SDKFacade sharedInstance] logPageStart:[NSString stringWithUTF8String:pageName]];
    }
}

void SDKFacadeCocos::logPageEnd(const char* pageName)
{
    if (pageName != NULL) {
        [[SDKFacade sharedInstance] logPageEnd:[NSString stringWithUTF8String:pageName]];
    }
}

void SDKFacadeCocos::logEvent(const char* eventId)
{
    if (eventId != NULL) {
        [[SDKFacade sharedInstance] logEvent:[NSString stringWithUTF8String:eventId]];
    }
}

void SDKFacadeCocos::logEvent(const char* eventId, const char* action)
{
    if (eventId != NULL) {
        [[SDKFacade sharedInstance] logEvent:[NSString stringWithUTF8String:eventId] action:action == NULL ? nil : [NSString stringWithUTF8String:action]];
    }
}

void SDKFacadeCocos::logEvent(const char* eventId, const char* action, const char* label, long value)
{
    if (eventId != NULL) {
        NSString *_eventId = [NSString stringWithUTF8String:eventId];
        NSString *_action = action == NULL ? nil : [NSString stringWithUTF8String:action];
        NSString *_label = label == NULL ? nil : [NSString stringWithUTF8String:label];
        [[SDKFacade sharedInstance] logEvent:_eventId action:_action label:_label value:value];
    }
}

void SDKFacadeCocos::logStartLevel(const char* level)
{
    if (level != NULL) {
        [[SDKFacade sharedInstance] logStartLevel:[NSString stringWithUTF8String:level]];
    }
}

void SDKFacadeCocos::logFailLevel(const char* level)
{
    if (level != NULL) {
        [[SDKFacade sharedInstance] logFailLevel:[NSString stringWithUTF8String:level]];
    }
}

void SDKFacadeCocos::logFinishLevel(const char* level)
{
    if (level != NULL) {
        [[SDKFacade sharedInstance] logFinishLevel:[NSString stringWithUTF8String:level]];
    }
}

void SDKFacadeCocos::logFinishAchievement(const char* achievement)
{
    if (achievement != NULL) {
        [[SDKFacade sharedInstance] logFinishAchievement:[NSString stringWithUTF8String:achievement]];
    }
}

void SDKFacadeCocos::logFinishTutorial(const char* tutorial)
{
    if (tutorial != NULL) {
        [[SDKFacade sharedInstance] logFinishTutorial:[NSString stringWithUTF8String:tutorial]];
    }
}

void SDKFacadeCocos::logBuy(const char* itemName, const char* itemType, int count, double price)
{
    if(itemName != NULL) {
        [[SDKFacade sharedInstance] logBuy:[NSString stringWithUTF8String:itemName] itemType:[NSString stringWithUTF8String:itemType] count:count price:price];
    }
}

void SDKFacadeCocos::logUse(const char* itemName, int number, double price)
{
    if(itemName != NULL) {
        [[SDKFacade sharedInstance] logUse:[NSString stringWithUTF8String:itemName] number:number price:price];
    }
}

void SDKFacadeCocos::logBonus(const char* itemName, int number, double price, int trigger)
{
    if(itemName != NULL) {
        [[SDKFacade sharedInstance] logBonus:[NSString stringWithUTF8String:itemName] number:number price:price trigger:trigger];
    }
}

void SDKFacadeCocos::logRegister(const char* channel)
{
    if(channel != NULL) {
//        [[SDKFacade sharedInstance] logRegister:[NSString stringWithUTF8String:channel]];
    }
}

void SDKFacadeCocos::registerPush()
{
    [[SDKFacade sharedInstance] registerPush];
}

bool SDKFacadeCocos::isPushRegistered()
{
    return [[SDKFacade sharedInstance] isPushRegistered];
}

void SDKFacadeCocos::cancelLocalNotification(const char* _Nonnull key)
{
    NSString *objc_key = [NSString stringWithUTF8String:key];
    [[SDKFacade sharedInstance] cancelLocalNotification:objc_key];
}

void SDKFacadeCocos::cancelAllLocalNotifications()
{
    [[SDKFacade sharedInstance] cancelAllLocalNotifications];
}

const char* _Nullable SDKFacadeCocos::getLocalNotificationDataJson()
{
    NSString *json = [[SDKFacade sharedInstance] getLocalNotificationDataJson];
    return json && json.length > 0 ? [json UTF8String] : NULL;
}

void SDKFacadeCocos::pushLocalNotification(const char* _Nonnull key, const char* _Nonnull title, const char* _Nonnull msg, long pushTime, int interval, bool repeat, bool useSound, const char* _Nullable soundName, const char* _Nonnull userInfo)
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

void SDKFacadeCocos::pushLocalNotification(const char* _Nonnull key, const char* _Nonnull title, const char* _Nonnull msg, const char* _Nonnull dateStr, int interval, bool repeat,bool useSound, const char* _Nullable soundName, const char* _Nonnull userInfo)
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

void SDKFacadeCocos::pushRemoteNotification(const char* _Nullable key, const char* _Nonnull title, const char* _Nonnull content, long pushTime, bool useLocalTimeZone, const char* _Nullable facebookIds, const char* _Nullable uuids, const char* _Nullable topic, int iosBadge, bool useSound, const char* _Nullable soundName, const char* _Nullable dataJson)
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

void SDKFacadeCocos::cancelRemoteNotification(const char* _Nonnull key)
{
    NSString *objc_key = [NSString stringWithUTF8String:key];
    [[SDKFacade sharedInstance] cancelRemoteNotification:objc_key];
}
//void SDKFacadeCocos::pushRepeatRemoteNotification(const char* _Nonnull messageId, const char* _Nonnull title, const char* _Nonnull content, long pushTime, bool useLocalTimeZone, const char* _Nullable facebookIds, const char* _Nullable uuids, const char* _Nullable topic, int iosBadge, bool useSound, const char* _Nullable soundName, const char* _Nullable dataJson, int repeatInterval)
//{
//    NSString *objc_key = [NSString stringWithUTF8String:messageId];
//    NSString *objc_title = [NSString stringWithUTF8String:title];
//    NSString *objc_msg = [NSString stringWithUTF8String:content];
//    NSString *objc_facebookIds = nil;
//    if(facebookIds != NULL && facebookIds != NULL && strlen(facebookIds) > 0) {
//        objc_facebookIds = [NSString stringWithUTF8String:facebookIds];
//    }
//    NSString *objc_uuids = nil;
//    if(uuids != NULL && uuids != NULL && strlen(uuids) > 0) {
//        objc_uuids = [NSString stringWithUTF8String:uuids];
//    }
//    NSString *objc_topic = nil;
//    if(topic != NULL && topic != NULL && strlen(topic) > 0) {
//        objc_topic = [NSString stringWithUTF8String:topic];
//    }
//    NSString *objc_soundName = nil;
//    if(soundName != NULL && soundName != NULL && strlen(soundName) > 0) {
//        objc_soundName = [NSString stringWithUTF8String:soundName];
//    }
//    NSDictionary *data = nil;
//    if(dataJson != NULL && dataJson != NULL && strlen(dataJson) > 0) {
//        data = [SDKJSONHelper toArrayOrNSDictionary:[NSString stringWithUTF8String:dataJson]];
//    }
//    [[SDKFacade sharedInstance] pushRepeatRemoteNotification:objc_key title:objc_title content:objc_msg pushTime:pushTime useLocalTimeZone:useLocalTimeZone facebookIds:objc_facebookIds uuids:objc_uuids topic:objc_topic iosBadge:iosBadge useSound:useSound soundName:objc_soundName extraData:data repeatInterval:repeatInterval];
//}
//
//void SDKFacadeCocos::cancelRepeatRemoteNotification(const char* _Nonnull messageId)
//{
//    NSString *objc_key = [NSString stringWithUTF8String:messageId];
//    [[SDKFacade sharedInstance] cancelRepeatRemoteNotification:objc_key];
//}

const char *SDKFacadeCocos::cacheUrl(const char* url)
{
    if(url != NULL) {
        NSString *result = [[SDKFacade sharedInstance] cacheUrl:[NSString stringWithUTF8String:url]];
        return result ? [result UTF8String] : NULL;
    }
    return NULL;
}

void SDKFacadeCocos::cacheUrlWithTag(const char* tag, const char* url)
{
    NSString *_tag = tag != NULL ? [NSString stringWithUTF8String:tag] : nil;
    NSString *_url = url != NULL ? [NSString stringWithUTF8String:url] : nil;
    [[SDKFacade sharedInstance] cacheUrl:_tag url:_url succes:^(NSString * __tag, NSString * __path) {
        if(_sdkDelegate != NULL) {
            _sdkDelegate->cacheUrlSuccess([__tag UTF8String], [__path UTF8String]);
        }
    } failure:^(NSString * __tag) {
        if(_sdkDelegate != NULL) {
            _sdkDelegate->cacheUrlFailure([__tag UTF8String]);
        }
    }];
}

void SDKFacadeCocos::sendMail(const char* address, const char* subject, const char* content, bool isHTML)
{
    NSString *obj_address = address != NULL ? [NSString stringWithUTF8String:address] : nil;
    NSString *obj_subject = subject != NULL ? [NSString stringWithUTF8String:subject] : nil;
    NSString *obj_content = content != NULL ? [NSString stringWithUTF8String:content] : nil;
    if (obj_address && obj_subject && obj_content) {
        [[SDKFacade sharedInstance] sendMail:obj_address subject:obj_subject content:obj_content isHTML:isHTML];
    }
}

void SDKFacadeCocos::saveBase64ImageToCameraRoll(const char* _Nonnull base64Image)
{
    NSString *obj_str = [NSString stringWithUTF8String:base64Image];
    [SDKFacade saveBase64ImageToCameraRoll:obj_str];
}

void SDKFacadeCocos::takeScreenshotToCameraRoll()
{
    [SDKFacade takeScreenshotToCameraRoll];
}

const char * _Nonnull SDKFacadeCocos::takeScreenshotToDocumentsDirectory()
{
    return [[SDKFacade takeScreenshotToDocumentsDirectory] UTF8String];
}

const char * _Nonnull SDKFacadeCocos::takeScreenshotToDirectoryAtPath(const char* _Nonnull path)
{
    NSString *obj_path = [NSString stringWithUTF8String:path];
    return [[SDKFacade takeScreenshotToDirectoryAtPath:obj_path] UTF8String];
}

bool SDKFacadeCocos::justShowFullAd()
{
    return [SDKFacade sharedInstance].justShowFullAd;
}

void SDKFacadeCocos::requestPhotoWritePermission(requestPermissionResult p)
{
    [[SDKFacade sharedInstance] requestPhotoWritePermission:^(BOOL permit) {
        if (p != NULL) {
            (*p) (permit);
        }
    }];
}

bool SDKFacadeCocos::hasPhotoWritePermission()
{
    return [[SDKFacade sharedInstance] hasPhotoWritePermission];
}

void SDKFacadeCocos::openSettingPage()
{
    [[SDKFacade sharedInstance] openSettingPage];
}

bool SDKFacadeCocos::isRecording()
{
    return [[SDKFacade sharedInstance] isRecording];
}

void SDKFacadeCocos::startRecording()
{
    [[SDKFacade sharedInstance] startRecording];
}

void SDKFacadeCocos::pauseRecording()
{
    [[SDKFacade sharedInstance] pauseRecording];
}

void SDKFacadeCocos::continueRecording()
{
    [[SDKFacade sharedInstance] continueRecording];
}

void SDKFacadeCocos::stopRecording(complete p)
{
    [[SDKFacade sharedInstance] stopRecording:^(BOOL result) {
        if (p != NULL) {
            const char * _msg = result ? "true" : "";
            (*p)(_msg);
        }
    }];
}

void SDKFacadeCocos::editVideo(const char* _Nonnull path, editComplete p)
{
    NSString *obj_path = [NSString stringWithUTF8String:path];
    [[SDKFacade sharedInstance] editVideo:obj_path complete:^(NSString * _Nullable localIdent) {
        if (p != NULL) {
            const char * _msg = localIdent ? [localIdent UTF8String] : "";
            (*p)(_msg);
        }
    }];
}

void SDKFacadeCocos::sharePhotos(const char* _Nonnull photoIdentifiers, shareResult p)
{
    NSString *idents = [NSString stringWithUTF8String:photoIdentifiers];
    NSArray *arr = [idents componentsSeparatedByString:@","];
    [[SDKFacade sharedInstance] sharePhotos:arr result:^(BOOL success, NSString * _Nullable error) {
        if (p != NULL) {
            const char * _msg = error? [error UTF8String] : "";
            (*p)(success, _msg);
        }
    }];
}

void SDKFacadeCocos::shareVideos(const char* _Nonnull photoIdentifiers, shareResult p)
{
    NSString *idents = [NSString stringWithUTF8String:photoIdentifiers];
    NSArray *arr = [idents componentsSeparatedByString:@","];
    [[SDKFacade sharedInstance] shareVideos:arr result:^(BOOL success, NSString * _Nullable error) {
        if (p != NULL) {
            const char * _msg = error? [error UTF8String] : "";
            (*p)(success, _msg);
        }
    }];
}

void SDKFacadeCocos::shareRecentRecordVideo(shareResult p)
{
    [[SDKFacade sharedInstance] shareRecentRecordVideo:^(BOOL success, NSString * _Nullable error) {
        if (p != NULL) {
            const char * _msg = error? [error UTF8String] : "";
            (*p)(success, _msg);
        }
    }];
}

void SDKFacadeCocos::showPromotion(float x, float y)
{
    [[SDKFacade sharedInstance] showPromotion:CGPointMake(x, y)];
}

void SDKFacadeCocos::copyToClipboard(const char* _Nonnull copyStr)
{
    NSString *oc_copyStr = [NSString stringWithUTF8String:copyStr];
    [[SDKFacade sharedInstance] copyToClipboard:oc_copyStr];
}

long SDKFacadeCocos::getFreeMem()
{
    return [SDKFacade getFreeMem];
}

//MMKV
void SDKFacadeCocos::mmEnableNumberConfuse(bool enableConfuse)
{
    [[SDKFacade sharedInstance] mmEnableNumberConfuse:enableConfuse];
}
void SDKFacadeCocos::mmSetIntValue(const char* _Nonnull key, int value)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    [[SDKFacade sharedInstance] mmSetIntValue:obj_key value:value];
}
int SDKFacadeCocos::mmGetIntValue(const char* _Nonnull key, int defaultValue)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] mmGetIntValue:obj_key defaultValue:defaultValue];
}
void SDKFacadeCocos::mmSetLongValue(const char* _Nonnull key, long value)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    [[SDKFacade sharedInstance] mmSetLongValue:obj_key value:value];
}
long SDKFacadeCocos::mmGetLongValue(const char* _Nonnull key, long defaultValue)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] mmGetLongValue:obj_key defaultValue:defaultValue];
}
void SDKFacadeCocos::mmSetBoolValue(const char* _Nonnull key, bool value)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    [[SDKFacade sharedInstance] mmSetBoolValue:obj_key value:value];
}
bool SDKFacadeCocos::mmGetBoolValue(const char* _Nonnull key, bool defaultValue)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] mmGetBoolValue:obj_key defaultValue:defaultValue];
}
void SDKFacadeCocos::mmSetFloatValue(const char* _Nonnull key, float value)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    [[SDKFacade sharedInstance] mmSetFloatValue:obj_key value:value];
}
float SDKFacadeCocos::mmGetFloatValue(const char* _Nonnull key, float defaultValue)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] mmGetFloatValue:obj_key defaultValue:defaultValue];
}
void SDKFacadeCocos::mmSetStringValue(const char* _Nonnull key, const char* _Nullable value)
{
    if (value != NULL) {
        NSString *obj_key = [NSString stringWithUTF8String:key];
        NSString *obj_value = [NSString stringWithUTF8String:value];
        [[SDKFacade sharedInstance] mmSetStringValue:obj_key value:obj_value];
    }
}
const char * _Nonnull SDKFacadeCocos::mmGetStringValue(const char* _Nonnull key, const char* _Nonnull defaultValue)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
     NSString *obj_value = [NSString stringWithUTF8String:defaultValue];
    return [[[SDKFacade sharedInstance] mmGetStringValue:obj_key defaultValue:obj_value] UTF8String];
}
bool SDKFacadeCocos::mmContainsKey(const char* _Nonnull key)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    return [[SDKFacade sharedInstance] mmContainsKey:obj_key];
}
void SDKFacadeCocos::mmRemoveKey(const char* _Nonnull key)
{
    NSString *obj_key = [NSString stringWithUTF8String:key];
    [[SDKFacade sharedInstance] mmRemoveKey:obj_key];
}
void SDKFacadeCocos::mmRemoveKeys(const char* _Nonnull keys)
{
    NSString *obj_keys = [NSString stringWithUTF8String:keys];
    [[SDKFacade sharedInstance] mmRemoveKeys:obj_keys];
}
void SDKFacadeCocos::requestIDFA()
{
    [SDKFacade requestIDFA];
}
bool SDKFacadeCocos::joinQQGroup(const char * _Nullable groupUin, const char * _Nullable key)
{
    if (groupUin != NULL && key != NULL) {
        NSString *obj_gid = [NSString stringWithUTF8String:groupUin];
        NSString *obj_key = [NSString stringWithUTF8String:key];
        return [SDKFacade joinQQGroup:obj_gid key:obj_key];
    }
    return false;
}
void SDKFacadeCocos::loginNoAnonymous()
{
    [[SDKFacade sharedInstance] loginNoAnonymous];
}
void SDKFacadeCocos::loginNoAnonymousCancelable()
{
    [[SDKFacade sharedInstance] loginNoAnonymousCancelable];
}
void SDKFacadeCocos::showGameProtocolDialog()
{
    [[SDKFacade sharedInstance] showGameProtocolDialog];
}
void SDKFacadeCocos::showPrivacyDialog()
{
    [[SDKFacade sharedInstance] showPrivacyDialog];
}
void SDKFacadeCocos::showUserAgreementDialog()
{
    [[SDKFacade sharedInstance] showUserAgreementDialog];
}
void SDKFacadeCocos::setIdCardVerified(int age)
{
    [[SDKFacade sharedInstance] setIdCardVerified:age];
}
int SDKFacadeCocos::getIdCardVerifyedAge()
{
    return [[SDKFacade sharedInstance] getIdCardVerifyedAge];
}
long SDKFacadeCocos::getTotalDiskSize()
{
    return [SDKFacade getTotalDiskSize];
}
long SDKFacadeCocos::getAvailableDiskSize()
{
    return [SDKFacade getAvailableDiskSize];
}

void SDKFacadeCocos::playAHAP(const char* _Nonnull name, const char* _Nullable folder)
{
    NSString *oc_name = [NSString stringWithUTF8String:name];
    if (folder != NULL) {
        NSString *oc_folder = [NSString stringWithUTF8String:folder];
        [SDKFacade playAHAP:oc_name inDirectory:oc_folder];
    } else {
        [SDKFacade playAHAP:oc_name inDirectory:nil];
    }
}
#endif
