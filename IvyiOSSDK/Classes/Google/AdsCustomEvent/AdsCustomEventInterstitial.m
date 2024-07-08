//
//  AdsCustomEventInterstitial.m
//  abseil
//
//  Created by iOS打包3 on 2023/5/23.
//


#import <Foundation/Foundation.h>
#import <stdatomic.h>
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKAdmobRequest.h>
#import "AdsCustomEventInterstitial.h"
#import "AdsCustomEventUtils.h"

@interface AdsCustomEventInterstitial () <GADMediationInterstitialAd> {
  /// The sample interstitial ad.
  GADInterstitialAd* _interstitialAd;
  BOOL interstitialLoaded;

  GADMediationInterstitialAdConfiguration *_adConfiguration;
  /// The completion handler to call when the ad loading succeeds or fails.
  GADMediationInterstitialLoadCompletionHandler _loadCompletionHandler;

  /// The ad event delegate to forward ad rendering events to the Google Mobile Ads SDK.
  id<GADMediationInterstitialAdEventDelegate> _adEventDelegate;
}

@end

@implementation AdsCustomEventInterstitial

- (void)loadInterstitialForAdConfiguration:(GADMediationInterstitialAdConfiguration *)adConfiguration
                         completionHandler:(GADMediationInterstitialLoadCompletionHandler)completionHandler {
    __block atomic_flag completionHandlerCalled = ATOMIC_FLAG_INIT;
    __block GADMediationInterstitialLoadCompletionHandler originalCompletionHandler =
    [completionHandler copy];
    interstitialLoaded = NO;
    _adConfiguration = adConfiguration;
    _loadCompletionHandler = ^id<GADMediationInterstitialAdEventDelegate>(
          _Nullable id<GADMediationInterstitialAd> ad, NSError *_Nullable error) {
          // Only allow completion handler to be called once.
          if (atomic_flag_test_and_set(&completionHandlerCalled)) {
              return nil;
          }
          
          id<GADMediationInterstitialAdEventDelegate> delegate = nil;
          if (originalCompletionHandler) {
              // Call original handler and hold on to its return value.
              delegate = originalCompletionHandler(ad, error);
          }
          
          // Release reference to handler. Objects retained by the handler will also be released.
          originalCompletionHandler = nil;
          
          return delegate;
      };
    
    NSString *adUnit = adConfiguration.credentials.settings[@"parameter"];
    GADRequest *request = [SDKAdmobRequest createRequest];
    [GADInterstitialAd loadWithAdUnitID: adUnit request:request completionHandler:^(GADInterstitialAd *ad, NSError *error) {
        if (error) {
            DLog(@"Failed to load interstitial ad with error: %@", [error localizedDescription]);
            [self adFailed:[error localizedDescription]];
            self->_adEventDelegate = self->_loadCompletionHandler(self, error);
            DLog(@"[adlog] [%@:adFailed] [id:%@] [adapter:%@]", NSStringFromClass(self.class), adUnit, ad.responseInfo.loadedAdNetworkResponseInfo?ad.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob");
            return;
        }
        self->_interstitialAd = ad;
        self->_interstitialAd.fullScreenContentDelegate = self;
        [self adLoaded];
    }];
};

-(void)adLoaded{
    interstitialLoaded = TRUE;
    self->_adEventDelegate = self->_loadCompletionHandler(self, nil);
}

-(void)adFailed:(NSString *)error
{
    _interstitialAd = nil;
}

#pragma mark GADMediationInterstitialAd implementation

- (void)presentFromViewController:(UIViewController *)viewController {
    if (interstitialLoaded) {
        [_interstitialAd presentFromRootViewController:viewController];
    } else {
      NSError *error = AdsCustomEventErrorWithCodeAndDescription(
          AdsCustomEventErrorAdNotLoaded,
          [NSString stringWithFormat:
                        @"The interstitial ad failed to present because the ad was not loaded."]);
        [_adEventDelegate didFailToPresentWithError:error];
    }
}

#pragma mark GADFullScreenContentDelegate implementation

- (void)adDidRecordImpression:(nonnull id<GADFullScreenPresentingAd>)ad{
    [_adEventDelegate reportImpression];
}

/// Tells the delegate that a click has been recorded for the ad.
- (void)adDidRecordClick:(nonnull id<GADFullScreenPresentingAd>)ad{
    [_adEventDelegate reportClick];
}

/// Tells the delegate that the ad failed to present full screen content.
- (void)ad:(nonnull id<GADFullScreenPresentingAd>)ad
        didFailToPresentFullScreenContentWithError:(nonnull NSError *)error{
    [_adEventDelegate didFailToPresentWithError:error];
}

/// Tells the delegate that the ad will present full screen content.
- (void)adWillPresentFullScreenContent:(nonnull id<GADFullScreenPresentingAd>)ad{
    [_adEventDelegate willPresentFullScreenView];
}

/// Tells the delegate that the ad will dismiss full screen content.
- (void)adWillDismissFullScreenContent:(nonnull id<GADFullScreenPresentingAd>)ad{
    [_adEventDelegate willDismissFullScreenView];
}

/// Tells the delegate that the ad dismissed full screen content.
- (void)adDidDismissFullScreenContent:(nonnull id<GADFullScreenPresentingAd>)ad{
    [_adEventDelegate didDismissFullScreenView];
}

@end
