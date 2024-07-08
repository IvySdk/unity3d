//
//  AdsCustomEventBanner.m
//  abseil
//
//  Created by iOS打包3 on 2023/5/23.
//


#import <Foundation/Foundation.h>
#import <stdatomic.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKHelper.h>
#import <IvyiOSSdk/SDKAdmobRequest.h>
#import "AdsCustomEventBanner.h"
#import "AdsCustomEventUtils.h"

@interface AdsCustomEventBanner () <GADMediationBannerAd> {
    /// The sample banner ad.
    GADBannerView* _bannerAd;
    /// The completion handler to call when the ad loading succeeds or fails.
    GADMediationBannerLoadCompletionHandler _loadCompletionHandler;
    
    /// The ad event delegate to forward ad rendering events to the Google Mobile Ads SDK.
    id<GADMediationBannerAdEventDelegate> _adEventDelegate;
}

@end

@implementation AdsCustomEventBanner

- (void)loadBannerForAdConfiguration:(GADMediationBannerAdConfiguration *)adConfiguration
                   completionHandler:(GADMediationBannerLoadCompletionHandler)completionHandler {
    __block atomic_flag completionHandlerCalled = ATOMIC_FLAG_INIT;
    __block GADMediationBannerLoadCompletionHandler originalCompletionHandler =
         [completionHandler copy];
    
    _loadCompletionHandler = ^id<GADMediationBannerAdEventDelegate>(
          _Nullable id<GADMediationBannerAd> ad, NSError *_Nullable error) {
        // Only allow completion handler to be called once.
        if (atomic_flag_test_and_set(&completionHandlerCalled)) {
          return nil;
        }

        id<GADMediationBannerAdEventDelegate> delegate = nil;
        if (originalCompletionHandler) {
          // Call original handler and hold on to its return value.
          delegate = originalCompletionHandler(ad, error);
        }

        // Release reference to handler. Objects retained by the handler will also be released.
        originalCompletionHandler = nil;

        return delegate;
      };
    
    NSError *error = nil;
    CGSize bannerSize =  BannerSizeFromAdConfiguration(adConfiguration,&error);
    if (error) {
        _loadCompletionHandler(nil, error);
        return;
    }
    _bannerAd = [[GADBannerView alloc] initWithFrame:CGRectMake(0, 0, bannerSize.width, bannerSize.height)];
    _bannerAd.adUnitID = adConfiguration.credentials.settings[@"parameter"];
    _bannerAd.delegate = self;
    _bannerAd.rootViewController = adConfiguration.topViewController;
    [_bannerAd loadRequest:[SDKAdmobRequest createRequest]];
}

#pragma mark GADMediationBannerAd implementation

- (nonnull UIView *)view {
    return _bannerAd;
}

#pragma mark GADBannerViewDelegate implementation
- (void)bannerViewDidReceiveAd:(GADBannerView *)bannerView {
    _adEventDelegate = _loadCompletionHandler(self, nil);
}

- (void)bannerView:(GADBannerView *)banner didFailToReceiveAdWithError:(nonnull NSError *)errorCode {
    NSError *error = errorCode;
    _adEventDelegate = _loadCompletionHandler(nil, error);
}

- (void)bannerViewWillDismissScreen:(GADBannerView *)bannerView
{
    [_adEventDelegate willDismissFullScreenView];
}

- (void)bannerViewWillPresentScreen:(GADBannerView *)bannerView {
    [_adEventDelegate reportClick];
}

- (void)bannerViewDidRecordImpression:(GADBannerView *)bannerView
{
    [_adEventDelegate reportImpression];
}

- (void)bannerViewDidDismissScreen:(GADBannerView *)bannerView
{
    [_adEventDelegate didDismissFullScreenView];
}

- (void)bannerViewDidRecordClick:(GADBannerView *)bannerView
{
    [_adEventDelegate reportClick];
}
@end
