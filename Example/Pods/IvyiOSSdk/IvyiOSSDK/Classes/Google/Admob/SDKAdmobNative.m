//
//  SDKAdmobNative.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/25.
//

#import "SDKAdmobNative.h"
#import "SDKAdmobNativeView.h"
#import "SDKAdmobRequest.h"
#import "SDKFacade.h"
#import <GoogleMobileAds/GADAdLoaderAdTypes.h>
#import <GoogleMobileAds/GADVideoOptions.h>
#import <GoogleMobileAds/GADNativeAdViewAdOptions.h>
#import <GoogleMobileAds/GADNativeAdMediaAdLoaderOptions.h>
// Native Advanced ad unit ID for testing.
@implementation SDKAdmobNative
{
@private
    GADNativeAd *_nativeAd;
    GADAdLoader *_adLoader;
    NSString *_orientation;
    BOOL _videoStartMuted;
}
#pragma mark ================== Native Ads ===================
-(NSString *)defaultBundleName
{
    return @"IvyiOSSdk-Admob";
}

-(NSString *)defaultNibName
{
    return @"UnifiedNativeAdView";
}

-(Class)nativeAdViewClass
{
    return [SDKAdmobNativeView class];
}

-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(nullable NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    NSNumber *tmp = [config objectForKey:@"startMuted"];
    _videoStartMuted = tmp ? [tmp boolValue] : NO;
    return self;
}

-(BOOL)loadAd:(UIViewController *)vc
{
#if DEBUG
    _adId = @"ca-app-pub-3940256099942544/3986624511";
#endif
    if([super loadAd:vc]) {
        GADVideoOptions *videoOptions = [[GADVideoOptions alloc] init];
        videoOptions.startMuted = _videoStartMuted;
        GADNativeAdViewAdOptions *adOptions = [[GADNativeAdViewAdOptions alloc] init];
        adOptions.preferredAdChoicesPosition = GADAdChoicesPositionTopRightCorner;
        GADNativeAdMediaAdLoaderOptions *imageOptions = [[GADNativeAdMediaAdLoaderOptions alloc] init];
        imageOptions.mediaAspectRatio = self.orientation;
//        GADMediaAspectRatioUnknown = 0,
//        GADMediaAspectRatioAny = 1,
//        GADMediaAspectRatioLandscape = 2,
//        GADMediaAspectRatioPortrait = 3,
//        GADMediaAspectRatioSquare = 4
        
        
        _adLoader = [[GADAdLoader alloc] initWithAdUnitID:_adId
                                       rootViewController:vc
                                                  adTypes:@[ GADAdLoaderAdTypeNative ]
                                                  options:@[ adOptions, videoOptions, imageOptions ]];
        _adLoader.delegate = self;
        GADRequest *request = [SDKAdmobRequest createRequest];
        [_adLoader loadRequest:request];
        return true;
    }
    return false;
}

-(NSMutableDictionary *)nativeAdToDict:(nonnull GADNativeAd *)nativeAd
{
    NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
    [data setObject:nativeAd.headline forKey:@"title"];
    [data setObject:nativeAd.body forKey:@"body"];
    [data setObject:nativeAd.callToAction forKey:@"actionText"];
    return data;
}

#pragma mark ================== Admob Delegate ===================
- (void)adLoader:(GADAdLoader *)adLoader didReceiveNativeAd:(GADNativeAd *)nativeAd
{
    _nativeAd = nativeAd;
    _nativeAd.delegate = self;
    __weak typeof(self) _self = self;
    _nativeAd.paidEventHandler = ^void(GADAdValue *_Nonnull adValue) {
        __strong typeof(_self) self = _self;
        [[SDKFacade sharedInstance] logAdRevenue:self->_nativeAd && self->_nativeAd.responseInfo.loadedAdNetworkResponseInfo ? self->_nativeAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob" mediationType:SdkMediationNetworkTypeGoogleAdMob adType:@"native" adUnit:self->_adId placement:self->_tag country:[SDKHelper getCountryCode].lowercaseString currency:adValue.currencyCode value:adValue.value];
    };
    [self adLoaded: _nativeAd];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adLoaded] [id:%@] [adapter:%@]", _tag, _index, NSStringFromClass(self.class), _adId, nativeAd.responseInfo.loadedAdNetworkResponseInfo?nativeAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
}


-(void)adLoaderDidFinishLoading:(GADAdLoader *)adLoader
{
    [self adLoaded:_nativeAd];
}

- (void)adLoader:(GADAdLoader *)adLoader didFailToReceiveAdWithError:(NSError *)error
{
    [self adFailed:error ? error.description : nil];
}

- (void)nativeAdDidRecordImpression:(GADNativeAd *)nativeAd
{
    [self adDidShown];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adDidShown] [id:%@] [adapter:%@]", _tag, _index, NSStringFromClass(self.class), _adId, nativeAd.responseInfo.loadedAdNetworkResponseInfo?nativeAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
}

- (void)nativeAdDidRecordClick:(GADNativeAd *)nativeAd
{
    [self adDidClick];
}

- (void)nativeAdDidDismissScreen:(GADNativeAd *)nativeAd
{
    [self adDidClose];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adDidClose] [id:%@] [adapter:%@]", _tag, _index, NSStringFromClass(self.class), _adId, nativeAd.responseInfo.loadedAdNetworkResponseInfo?nativeAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
}

- (void)nativeAdWillPresentScreen:(GADNativeAd *)nativeAd
{
}

- (void)nativeAdWillDismissScreen:(GADNativeAd *)nativeAd
{
}

- (void)nativeAdWillLeaveApplication:(GADNativeAd *)nativeAd
{
}

- (void)nativeAdIsMuted:(nonnull GADNativeAd *)nativeAd
{
}
@end
