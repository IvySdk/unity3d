//
//  SDKAdmobInterstitial.m
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//

#import "SDKAdmobInterstitial.h"
#import "SDKAdmobRequest.h"
#import <GoogleMobileAds/GADInterstitialAd.h>
#import <GoogleMobileAds/GADFullScreenContentDelegate.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKHelper.h>

@implementation SDKAdmobInterstitial
{
@private
    GADInterstitialAd *_interstitialAd;
}
#pragma mark ================== Interstitial AD ===================

-(BOOL)loadAd:(UIViewController *)vc
{
#if DEBUG
//    _adId = @"ca-app-pub-3940256099942544/4411468910";
#endif
    _interstitialAd = nil;
    if([super loadAd:vc]) {
        GADRequest *request = [SDKAdmobRequest createRequest];
    
        [GADInterstitialAd loadWithAdUnitID: _adId request:request completionHandler:^(GADInterstitialAd *ad, NSError *error) {
            if (error) {
                DLog(@"Failed to load interstitial ad with error: %@", [error localizedDescription]);
                [self adFailed:[error localizedDescription]];
                DLog(@"[adlog] [tag:%@(%d)] [%@:adFailed] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, ad.responseInfo.loadedAdNetworkResponseInfo ? ad.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
                return;
            }
            self->_interstitialAd = ad;
            self->_interstitialAd.fullScreenContentDelegate = self;
            __weak typeof(self) _self = self;
            self->_interstitialAd.paidEventHandler = ^void(GADAdValue *_Nonnull adValue) {
                __strong typeof(_self) self = _self;
                [[SDKFacade sharedInstance] logAdRevenue:self->_interstitialAd && self->_interstitialAd.responseInfo.loadedAdNetworkResponseInfo ? self->_interstitialAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob" mediationType:SdkMediationNetworkTypeGoogleAdMob adType:@"interstitial" adUnit:self->_adId placement:self->_tag country:[SDKHelper getCountryCode].lowercaseString currency:adValue.currencyCode value:adValue.value];
            };
            [self adLoaded];
            DLog(@"[adlog] [tag:%@(%d)] [%@:adLoaded] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_interstitialAd.responseInfo.loadedAdNetworkResponseInfo ? self->_interstitialAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
        }];
        return true;
    }
    return false;
}

-(void)showAd:(UIViewController *)vc
{
    [super showAd:vc];
    if (self.isAvailable && _interstitialAd && [_interstitialAd canPresentFromRootViewController:vc error:nil]) {
        @try{
            @synchronized (_interstitialAd) {
                [_interstitialAd presentFromRootViewController:vc];
            }
        }@catch (NSException *exception) {
            _interstitialAd = nil;
        }
    } else {
        [self adShowFailed];
    }
}

-(void)adFailed:(NSString *)error
{
    _interstitialAd = nil;
    [super adFailed:error];
}

-(void)adShowFailed
{
    _interstitialAd = nil;
    [super adShowFailed];
}

-(void)adDidClose
{
    [super adDidClose];
    _interstitialAd = nil;
}

-(void)adDidRecordImpression:(id<GADFullScreenPresentingAd>)ad
{
    [self adDidShown];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adDidShown] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_interstitialAd.responseInfo.loadedAdNetworkResponseInfo ? self->_interstitialAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
}

-(void)adDidRecordClick:(id<GADFullScreenPresentingAd>)ad
{
    [self adDidClick];
}

- (void)ad:(id)ad didFailToPresentFullScreenContentWithError:(NSError *)error {
    [self adShowFailed];
    _interstitialAd = nil;
}

- (void)adDidDismissFullScreenContent:(id)ad {
    [self adDidClose];
}
@end
