//
//  AdsCustomEvent.m
//  IvyiOSSdk-IvyiOSSdk-Admob
//
//  Created by iOS打包3 on 2023/5/23.
//

#import <Foundation/Foundation.h>
#import "AdsCustomEvent.h"
#import "AdsCustomEventBanner.h"
#import "AdsCustomEventConstants.h"
#import "AdsCustomEventInterstitial.h"
#import "AdsCustomEventRewardAd.h"

@implementation AdsCustomEvent {
    AdsCustomEventRewardAd *sampleRewarded;
    
    AdsCustomEventBanner *sampleBanner;
    
    
    AdsCustomEventInterstitial *sampleInterstitial;
}


#pragma mark GADMediationAdapter implementation

+(GADVersionNumber)adSDKVersion {
    NSArray *versionComponents = [@"1.0.0" componentsSeparatedByString:@"."];
    GADVersionNumber version = {0};
    if (versionComponents.count >= 3) {
        version.majorVersion = [versionComponents[0] integerValue];
        version.minorVersion = [versionComponents[1] integerValue];
        version.patchVersion = [versionComponents[2] integerValue];
    }
    return version;
}

+(GADVersionNumber)adapterVersion {
    NSArray *versionComponents = [@"1.0.0.0" componentsSeparatedByString:@"."];
    GADVersionNumber version = {0};
    if (versionComponents.count == 4) {
        version.majorVersion = [versionComponents[0] integerValue];
        version.minorVersion = [versionComponents[1] integerValue];
        version.patchVersion =
        [versionComponents[2] integerValue] * 100 + [versionComponents[3] integerValue];
    }
    return version;
}

+ (nullable Class<GADAdNetworkExtras>)networkExtrasClass {
    return Nil;
}

+ (void)setUpWithConfiguration:(GADMediationServerConfiguration *)configuration
             completionHandler:(GADMediationAdapterSetUpCompletionBlock)completionHandler {
    // This is where you you will initialize the SDK that this custom event is built for.
    // Upon finishing the SDK initialization, call the completion handler with success.
    completionHandler(nil);
}

- (void)loadBannerForAdConfiguration:(GADMediationBannerAdConfiguration *)adConfiguration
                   completionHandler:(GADMediationBannerLoadCompletionHandler)completionHandler {
  sampleBanner = [[AdsCustomEventBanner alloc] init];
  [sampleBanner loadBannerForAdConfiguration:adConfiguration completionHandler:completionHandler];
}

- (void)loadInterstitialForAdConfiguration:
            (GADMediationInterstitialAdConfiguration *)adConfiguration
                         completionHandler:
                             (GADMediationInterstitialLoadCompletionHandler)completionHandler {
  sampleInterstitial = [[AdsCustomEventInterstitial alloc] init];
  [sampleInterstitial loadInterstitialForAdConfiguration:adConfiguration
                                       completionHandler:completionHandler];
}

- (void)loadRewardedAdForAdConfiguration:(GADMediationRewardedAdConfiguration *)adConfiguration
                       completionHandler:
                           (GADMediationRewardedLoadCompletionHandler)completionHandler {
  sampleRewarded = [[AdsCustomEventRewardAd alloc] init];
  [sampleRewarded loadRewardedAdForAdConfiguration:adConfiguration
                                 completionHandler:completionHandler];
}

- (void)loadNativeAdForAdConfiguration:(GADMediationNativeAdConfiguration *)adConfiguration
                     completionHandler:(GADMediationNativeLoadCompletionHandler)completionHandler {
}

@end
