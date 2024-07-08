//
//  SDKAdmobBanner.m
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//

#import "SDKAdmobBanner.h"
#import "SDKAdmobRequest.h"
#import <GoogleMobileAds/GADBannerView.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKHelper.h>
@implementation SDKAdmobBanner
#pragma mark ================== Banner AD ===================
-(BOOL)loadAd:(UIViewController *)vc
{
#if DEBUG
//    _adId = @"ca-app-pub-3940256099942544/2934735716";
#endif
    if([super loadAd:vc]) {
//        GADCurrentOrientationBannerAdSizeWithWidth(Screen_width);
        if (!_adView) {
            GADBannerView *adView = [[GADBannerView alloc] init];
            _adView = adView;
            if (_adSize && [_adSize isEqualToString:@"rect"]) {
                [adView setAdSize:GADAdSizeMediumRectangle];
            } else if (_adSize && [_adSize isEqualToString:@"large"]) {
                [adView setAdSize:GADAdSizeLargeBanner];
            } else if (_adSize && [_adSize isEqualToString:@"smart"]) {
                [adView setAdSize:GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth(Screen_width)];
            } else if (_adSize && [_adSize isEqualToString:@"full"]) {
                if(Screen_width <= 320) {
                    [adView setAdSize:GADAdSizeBanner];
                } else {
                    [adView setAdSize:GADAdSizeFullBanner];
                }
            } else if (_adSize && [_adSize isEqualToString:@"adaptive"]) {
                [adView setAdSize:GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth(Screen_width)];
            } else {
                if(isPad) {
                    [adView setAdSize:GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth(Screen_width)];
                } else {
                    if(Screen_width <= 320) {
                        [adView setAdSize:GADAdSizeBanner];
                    } else {
                        [adView setAdSize:GADAdSizeFullBanner];
                    }
                }
            }
            adView.adUnitID = _adId;
            adView.delegate = self;
            adView.autoloadEnabled = YES;
            adView.rootViewController = vc;
        }
        if (_adView) {
            GADBannerView *adView = (GADBannerView *)_adView;
            adView.delegate = self;
            __weak typeof(self) _self = self;
            adView.paidEventHandler = ^void(GADAdValue *_Nonnull adValue) {
                __strong typeof(_self) self = _self;
                GADBannerView *bannerView = (GADBannerView *)self->_adView;
                [[SDKFacade sharedInstance] logAdRevenue:bannerView && bannerView.responseInfo.loadedAdNetworkResponseInfo ? bannerView.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob" mediationType:SdkMediationNetworkTypeGoogleAdMob adType:@"banner" adUnit:self->_adId placement:self->_tag country:[SDKHelper getCountryCode].lowercaseString currency:adValue.currencyCode value:adValue.value];
            };
            GADRequest *request = [SDKAdmobRequest createRequest];
            [adView loadRequest:request];
            return true;
        }
    }
    return false;
}

- (void)bannerViewDidReceiveAd:(GADBannerView *)bannerView
{
    bannerView.delegate = nil;
    [self adLoaded];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adLoaded] [id:%@] [adapter:%@]", _tag, _index, NSStringFromClass(self.class), _adId, bannerView.responseInfo.loadedAdNetworkResponseInfo ? bannerView.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
}

- (void)bannerView:(nonnull GADBannerView *)bannerView didFailToReceiveAdWithError:(nonnull NSError *)error;
{
    [self adFailed:error ? error.description : nil];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adFailed] [id:%@] [adapter:%@]", _tag, _index, NSStringFromClass(self.class), _adId, bannerView.responseInfo.loadedAdNetworkResponseInfo ? bannerView.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
}

- (void)bannerViewDidDismissScreen:(GADBannerView *)bannerView
{
    [self adDidClick];
}
@end
