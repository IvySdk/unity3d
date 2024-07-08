//
//  SMAAdMobSmaatoRewardedVideoAdapter.h
//  SmaatoSDKAdapters
//
//  Created by Smaato Inc on 16.06.20.
//  Copyright © 2020 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <GoogleMobileAds/GoogleMobileAds.h>
#import <SmaatoSDKRewardedAds/SmaatoSDKRewardedAds.h>
#import "SMAAdMobSmaatoRewardedVideoAdapter.h"

static NSString *const kSMAAdMobCustomEventInfoAdSpaceIdKey = @"adspaceId";
static NSString *const kSMAAdMobSmaatoRewardedAdapterVersion = @"10.10.0.0";

@interface SMAAdMobSmaatoRewardedVideoAdapter () <SMARewardedInterstitialDelegate, GADMediationRewardedAd>
@property (nonatomic, weak, nullable) id<GADMediationRewardedAdEventDelegate> delegate;
@property (nonatomic, copy, nullable) GADMediationRewardedLoadCompletionHandler completion;
@property (nonatomic) SMARewardedInterstitial *rewardedAd;
@property (nonatomic, copy) NSString *adspaceId;
@end

@implementation SMAAdMobSmaatoRewardedVideoAdapter

+ (nullable Class<GADAdNetworkExtras>)networkExtrasClass
{
    return nil;
}

+ (NSString *)version
{
    return kSMAAdMobSmaatoRewardedAdapterVersion;
}

+ (GADVersionNumber)adSDKVersion {
    NSString *versionString = [SmaatoSDK sdkVersion];
    NSArray *versionComponents = [versionString componentsSeparatedByString:@"."];
    GADVersionNumber version = { 0 };
    if (versionComponents.count == 3) {
        version.majorVersion = [versionComponents[0] integerValue];
        version.minorVersion = [versionComponents[1] integerValue];
        version.patchVersion = [versionComponents[2] integerValue];
    }
    return version;
}

+ (GADVersionNumber)adapterVersion {
    NSString *versionString = kSMAAdMobSmaatoRewardedAdapterVersion;
    NSArray *versionComponents = [versionString componentsSeparatedByString:@"."];
    GADVersionNumber version = { 0 };
    if (versionComponents.count == 4) {
        version.majorVersion = [versionComponents[0] integerValue];
        version.minorVersion = [versionComponents[1] integerValue];
        version.patchVersion = [versionComponents[2] integerValue];
    }
    return version;
}

- (void)loadRewardedAdForAdConfiguration:(GADMediationRewardedAdConfiguration *)adConfiguration
                       completionHandler:(GADMediationRewardedLoadCompletionHandler)completionHandler
{
    // Extract key-value pairs from passed server parameter string
    NSDictionary *info = nil;
    
    if ([adConfiguration.credentials.settings objectForKey:@"parameter"]) {
        info = [self dictionaryFromServerParameter:adConfiguration.credentials.settings[@"parameter"]];
    }

    // Extract ad space information
    self.adspaceId = [self fetchValueForKey:kSMAAdMobCustomEventInfoAdSpaceIdKey fromEventInfo:info];

    self.completion = completionHandler;

    // Verify ad space information
    if (![self checkCredentialsWithAdSpaceId:self.adspaceId]) {
        NSString *description = @"AdSpaceId can not be extracted. Please check your configuration on AdMob dashboard.";
        NSLog(@"[SmaatoSDK] [Error] %@: %@", [self smaatoMediationNetworkName], description);
        NSDictionary *userInfo = @{ NSLocalizedDescriptionKey : description, NSLocalizedFailureReasonErrorKey : description };
        NSError *error = [NSError errorWithDomain:@"com.smaaato.SMAAdMobSmaatoRewardedVideoAdapter" code:0 userInfo:userInfo];

        if (self.completion) {
            self.completion(nil, error);
        }

        return;
    }

    // Pass user location
    /**
      Changes in AdMob 10.0.0
      https://developers.google.com/admob/ios/rel-notes
      Remove already deprecated userGender, userBirthday, userHasLocation, userLatitude, userLongitude, userLocationAccuracyInMeters, and userLocationDescription.
     
     if (adConfiguration.hasUserLocation) {
         SMALocation *userLocation = [[SMALocation alloc] initWithLatitude:adConfiguration.userLatitude
                                                                 longitude:adConfiguration.userLongitude
                                                        horizontalAccuracy:adConfiguration.userLocationAccuracyInMeters
                                                                 timestamp:[NSDate date]];
         SmaatoSDK.userLocation = userLocation;
     }
     
     */
    

    // OPTIONAL: Pass mediation information
    /**
     Optional: You can also set specific user profile targeting parameters here.
     Please check the Smaato wiki for all available properties and further details.

     Examples:
     SmaatoSDK.userAge = 30;
     SmaatoSDK.userGender = kSMAGenderMale;
     */

    // Passing mediation information
    SMAAdRequestParams *adRequestParams = [SMAAdRequestParams new];
    adRequestParams.mediationNetworkName = [self smaatoMediationNetworkName];
    adRequestParams.mediationAdapterVersion = kSMAAdMobSmaatoRewardedAdapterVersion;
    adRequestParams.mediationNetworkSDKVersion = [NSString stringWithFormat:@"%s", GoogleMobileAdsVersionString];

    // load a new ad
    [SmaatoSDK loadRewardedInterstitialForAdSpaceId:self.adspaceId delegate:self requestParams:adRequestParams];
}

- (void)presentFromViewController:(UIViewController *)viewController
{
    if (self.rewardedAd.availableForPresentation) {
        // The reward based video ad is available, present the ad.
        [self.rewardedAd showFromViewController:viewController];
    } else {
        // Because publishers are expected to check that an ad is available before
        // trying to show one, the above conditional should always hold true. If for
        // any reason the adapter is not ready to present an ad, however, it should
        // log an error with reason for failure.
        NSString *description = @"No ads to display. Please make a new ad request. ";
        NSDictionary *userInfo = @{ NSLocalizedDescriptionKey : description, NSLocalizedFailureReasonErrorKey : description };
        NSError *error = [NSError errorWithDomain:@"com.smaaato.SMAAdMobSmaatoRewardedVideoAdapter" code:0 userInfo:userInfo];
        [self.delegate didFailToPresentWithError:error];
    }
}

- (BOOL)checkCredentialsWithAdSpaceId:(NSString *)adSpaceId
{
    if (adSpaceId) {
        return YES;
    }
    return NO;
}

- (NSString *)smaatoMediationNetworkName
{
    return NSStringFromClass([self class]);
}

- (NSDictionary *)dictionaryFromServerParameter:(NSString *)serverParameter
{
    NSMutableDictionary *parsedServerParameters = [NSMutableDictionary new];
    [[serverParameter componentsSeparatedByString:@"&"]
    enumerateObjectsUsingBlock:^(NSString *_Nonnull obj, NSUInteger idx, BOOL *_Nonnull stop) {
        NSArray *pair = [obj componentsSeparatedByString:@"="];
        if (pair.count > 1) {
            id key = pair[0];
            id value = pair[1];
            parsedServerParameters[key] = value;
        }
    }];

    return [parsedServerParameters copy];
}

- (NSString *)fetchValueForKey:(NSString *)definedKey fromEventInfo:(NSDictionary *)info
{
    __block NSString *value;
    [info enumerateKeysAndObjectsUsingBlock:^(NSString *_Nonnull key, id _Nonnull obj, BOOL *_Nonnull stop) {
        if ([definedKey caseInsensitiveCompare:key] == NSOrderedSame) {
            value = obj;
            *stop = YES;
        }
    }];
    return value;
}

#pragma mark - SMARewardedInterstitialDelegate

- (void)rewardedInterstitialDidLoad:(SMARewardedInterstitial *)rewardedInterstitial
{
    self.rewardedAd = rewardedInterstitial;
    if (self.completion) {
        self.delegate = self.completion(self, nil);
        self.completion = nil;
    }
}

- (void)rewardedInterstitialDidFail:(SMARewardedInterstitial *)rewardedInterstitial withError:(NSError *)error
{
    if ([self.delegate respondsToSelector:@selector(didFailToPresentWithError:)]) {
        [self.delegate didFailToPresentWithError:error];
    }
}

- (void)rewardedInterstitialDidTTLExpire:(SMARewardedInterstitial *)rewardedInterstitial
{
    NSString *description = @"Failed to show a rewarded video ad as the ad has expired. Please make a new ad request. ";
    NSDictionary *userInfo = @{ NSLocalizedDescriptionKey : description, NSLocalizedFailureReasonErrorKey : description };
    NSError *error = [NSError errorWithDomain:@"com.smaaato.SMAAdMobSmaatoRewardedVideoAdapter" code:0 userInfo:userInfo];
    if ([self.delegate respondsToSelector:@selector(didFailToPresentWithError:)]) {
        [self.delegate didFailToPresentWithError:error];
    }
}

- (void)rewardedInterstitialWillAppear:(SMARewardedInterstitial *)rewardedInterstitial
{
    // No corresponding method from AdMob SDK available.
}

- (void)rewardedInterstitialDidAppear:(SMARewardedInterstitial *)rewardedInterstitial
{
    if ([self.delegate respondsToSelector:@selector(willPresentFullScreenView)]) {
        [self.delegate willPresentFullScreenView];
    }

    if ([self.delegate respondsToSelector:@selector(reportImpression)]) {
        [self.delegate reportImpression];
    }
}

- (void)rewardedInterstitialDidStart:(SMARewardedInterstitial *)rewardedInterstitial
{
    if ([self.delegate respondsToSelector:@selector(didStartVideo)]) {
        [self.delegate didStartVideo];
    }
}

- (void)rewardedInterstitialWillDisappear:(SMARewardedInterstitial *)rewardedInterstitial
{
    if ([self.delegate respondsToSelector:@selector(willDismissFullScreenView)]) {
        [self.delegate willDismissFullScreenView];
    }
}

- (void)rewardedInterstitialDidDisappear:(SMARewardedInterstitial *)rewardedInterstitial
{
    if ([self.delegate respondsToSelector:@selector(didDismissFullScreenView)]) {
        [self.delegate didDismissFullScreenView];
    }
}

- (void)rewardedInterstitialDidClick:(SMARewardedInterstitial *)rewardedInterstitial
{
    if ([self.delegate respondsToSelector:@selector(reportClick)]) {
        [self.delegate reportClick];
    }
}

- (void)rewardedInterstitialDidReward:(SMARewardedInterstitial *)rewardedInterstitial
{
    // Smaato doesn't retrieve exact reward (currency and amount) after video ad completion, due to this reason
    // publisher receives AdMobRewardedVideoAdapterViewController() callback with default GADAdReward value ( amount = 1 )
    if ([self.delegate respondsToSelector:@selector(didRewardUser)]) {
        [self.delegate didRewardUser];
    }
}

- (void)rewardedInterstitialWillLeaveApplication:(SMARewardedInterstitial *)rewardedInterstitial
{
    // No corresponding method from AdMob SDK available.
}

@end
