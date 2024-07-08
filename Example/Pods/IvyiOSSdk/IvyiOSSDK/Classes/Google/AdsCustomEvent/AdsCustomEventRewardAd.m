//
//  AdsCustomEventRewarded.m
//  abseil
//
//  Created by iOS打包3 on 2023/5/23.
//

#import <Foundation/Foundation.h>
#include <stdatomic.h>
#import <IvyiOSSdk/SDKAdmobRequest.h>
#import <IvyiOSSdk/SDKFacade.h>
#import "AdsCustomEventRewardAd.h"
#import "AdsCustomEventConstants.h"
#import "AdsCustomEventUtils.h"

@interface AdsCustomEventRewardAd () <GADMediationRewardedAd> {
    /// Handle rewarded ads from Sample SDK.
    GADRewardedAd *_rewardedAd;
    
    BOOL _adLoaded;
    /// Completion handler to call when sample rewarded ad finishes loading.
    GADMediationRewardedLoadCompletionHandler _loadCompletionHandler;
    
    ///  Delegate for receiving rewarded ad notifications.
    id<GADMediationRewardedAdEventDelegate> _adEventDelegate;
    NSString *_adUnitId;
}

@end


@implementation AdsCustomEventRewardAd

- (void)loadRewardedAdForAdConfiguration:(GADMediationRewardedAdConfiguration *)adConfiguration
                       completionHandler:
(GADMediationRewardedLoadCompletionHandler)completionHandler {
    __block atomic_flag completionHandlerCalled = ATOMIC_FLAG_INIT;
    __block GADMediationRewardedLoadCompletionHandler originalCompletionHandler =
    [completionHandler copy];
    _adLoaded = NO;
    _loadCompletionHandler = ^id<GADMediationRewardedAdEventDelegate>(_Nullable id<GADMediationRewardedAd> ad, NSError *_Nullable error) {
            // Only allow completion handler to be called once.
            if (atomic_flag_test_and_set(&completionHandlerCalled)) {
                return nil;
            }
                                                                          
            id<GADMediationRewardedAdEventDelegate> delegate = nil;
            if (originalCompletionHandler) {
                // Call original handler and hold on to its return value.
                delegate = originalCompletionHandler(ad, error);
            }
                                                                          
            // Release reference to handler. Objects retained by the handler will also be released.
            originalCompletionHandler = nil;
                                                                          
            return delegate;
    };
    
    _adUnitId = adConfiguration.credentials.settings[@"parameter"];
    [GADRewardedAd loadWithAdUnitID:_adUnitId request:[SDKAdmobRequest createRequest] completionHandler:^(GADRewardedAd* ad, NSError* error){
        if(error){
            self->_adEventDelegate = self->_loadCompletionHandler(self, error);
            return;
        }
        self->_rewardedAd = ad;
        self->_rewardedAd.fullScreenContentDelegate = self;
        [self adLoaded];
    }];
}

-(void)adLoaded{
    self->_adLoaded = YES;
    self->_adEventDelegate = self->_loadCompletionHandler(self, nil);
}

#pragma mark GADMediationRewardedAd implementation

- (void)presentFromViewController:(nonnull UIViewController *)viewController {
    if (_adLoaded && _rewardedAd) {
        //[_interstitialAd sh  ow];
        [_rewardedAd presentFromRootViewController:viewController userDidEarnRewardHandler:^ {
            [self->_adEventDelegate didRewardUser];
        }];
    } else {
      NSError *error = AdsCustomEventErrorWithCodeAndDescription(
          AdsCustomEventErrorAdNotLoaded,
          [NSString stringWithFormat:
                        @"The rewardVideo ad failed to present because the ad was not loaded."]);
        [_adEventDelegate didFailToPresentWithError:error];
    }
}

#pragma mark GADFullScreenContentDelegate implementation

/// Tells the delegate that an impression has been recorded for the ad.
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
    [_adEventDelegate didStartVideo];
}

/// Tells the delegate that the ad will dismiss full screen content.
- (void)adWillDismissFullScreenContent:(nonnull id<GADFullScreenPresentingAd>)ad{
    [_adEventDelegate willDismissFullScreenView];
}

/// Tells the delegate that the ad dismissed full screen content.
- (void)adDidDismissFullScreenContent:(nonnull id<GADFullScreenPresentingAd>)ad{
    [_adEventDelegate didEndVideo];
    [_adEventDelegate didDismissFullScreenView];
}

@end
