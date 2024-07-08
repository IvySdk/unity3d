//
//  SMAAdMobSmaatoInterstitialAdapter.m
//  SmaatoSDKAdapters
//
//  Created by Smaato Inc on 26.11.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <GoogleMobileAds/GoogleMobileAds.h>
#import <SmaatoSDKInterstitial/SmaatoSDKInterstitial.h>
#import "SMAAdMobSmaatoInterstitialAdapter.h"

static NSString *const kSMAAdMobCustomEventInfoPublisherIdKey = @"publisherId";
static NSString *const kSMAAdMobCustomEventInfoAdSpaceIdKey = @"adspaceId";
static NSString *const kSMAAdMobSmaatoInterstitialAdapterVersion = @"10.10.0.0";

@interface SMAAdMobSmaatoInterstitialAdapter () <SMAInterstitialDelegate, GADMediationInterstitialAd, GADMediationAdapter>
@property (nonatomic) SMAInterstitial *interstitial;
@property (nonatomic, weak, nullable) id<GADMediationInterstitialAdEventDelegate> delegate;
@property (nonatomic) GADMediationInterstitialLoadCompletionHandler loadCompletionHandler;
@property (nonatomic) UIViewController *presentingModalViewController;
@end

@implementation SMAAdMobSmaatoInterstitialAdapter

+ (NSString *)version
{
    return kSMAAdMobSmaatoInterstitialAdapterVersion;
}

- (void)loadInterstitialForAdConfiguration:(nonnull GADMediationInterstitialAdConfiguration *)adConfiguration
                   completionHandler:
                       (nonnull GADMediationInterstitialLoadCompletionHandler)completionHandler {
    
    //Assign delegate and save completion handler for later use
    self.loadCompletionHandler = completionHandler;

    // Extract key-value pairs from passed server parameter string
    NSDictionary *info = nil;
    
    if ([adConfiguration.credentials.settings objectForKey:@"parameter"]) {
        info = [self dictionaryFromServerParameter:adConfiguration.credentials.settings[@"parameter"]];
    }

    // Extract ad space information
    NSString *adSpaceId = [self fetchValueForKey:kSMAAdMobCustomEventInfoAdSpaceIdKey fromEventInfo:info];

    // Verify ad space information
    if (![self checkCredentialsWithAdSpaceId:adSpaceId]) {
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
    adRequestParams.mediationAdapterVersion = kSMAAdMobSmaatoInterstitialAdapterVersion;
    adRequestParams.mediationNetworkSDKVersion = [NSString stringWithFormat:@"%s", GoogleMobileAdsVersionString];

    // Load ad
    [SmaatoSDK loadInterstitialForAdSpaceId:adSpaceId delegate:self requestParams:adRequestParams];
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

- (BOOL)checkCredentialsWithAdSpaceId:(NSString *)adSpaceId
{
    if (adSpaceId) {
        return YES;
    }

    NSString *errorMessage = @"AdSpaceId can not be extracted. Please check your configuration on AdMob dashboard.";
    NSLog(@"[SmaatoSDK] [Error] %@: %@", [self smaatoMediationNetworkName], errorMessage);

    if ([self.delegate respondsToSelector:@selector(didFailToPresentWithError:)]) {
        NSDictionary *userInfo = @{ NSLocalizedDescriptionKey : errorMessage };
        NSError *error = [NSError errorWithDomain:[self smaatoMediationNetworkName] code:kSMAErrorCodeInvalidRequest userInfo:userInfo];

        [self.delegate didFailToPresentWithError:error];
    }

    return NO;
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

- (NSString *)smaatoMediationNetworkName
{
    return NSStringFromClass([self class]);
}

- (void)presentFromViewController:(nonnull UIViewController *)viewController {
    if (self.interstitial.availableForPresentation) {
        // The interstitial ad is available, present the ad.
        [self.interstitial showFromViewController:viewController];
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

#pragma mark - GADMediationAdapter

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
    NSString *versionString = kSMAAdMobSmaatoInterstitialAdapterVersion;
    NSArray *versionComponents = [versionString componentsSeparatedByString:@"."];
    GADVersionNumber version = { 0 };
    if (versionComponents.count == 4) {
        version.majorVersion = [versionComponents[0] integerValue];
        version.minorVersion = [versionComponents[1] integerValue];
        version.patchVersion = [versionComponents[2] integerValue];
    }
    return version;
}

+ (nullable Class<GADAdNetworkExtras>)networkExtrasClass {
    //we donot support keyword targetting, hence passing as nil
    //keywords can be always set through GADRequest.keywords by publisher, if needed
    return nil;
}

#pragma mark - SMAInterstitialDelegate

- (void)interstitialDidLoad:(SMAInterstitial *)interstitial
{
    self.interstitial = interstitial;
    if (self.loadCompletionHandler) {
        self.delegate = self.loadCompletionHandler(self, nil);
        self.loadCompletionHandler = nil;
    }
}

- (void)interstitial:(SMAInterstitial *)interstitial didFailWithError:(NSError *)error
{
    if ([self.delegate respondsToSelector:@selector(didFailToPresentWithError:)]) {
        [self.delegate didFailToPresentWithError:error];
    }
}

- (void)interstitialDidTTLExpire:(SMAInterstitial *)interstitial
{
    // No corresponding method from AdMob SDK available.
}

- (void)interstitialWillAppear:(SMAInterstitial *)interstitial
{
    if ([self.delegate respondsToSelector:@selector(willPresentFullScreenView)]) {
        [self.delegate willPresentFullScreenView];
    }
}

- (void)interstitialDidAppear:(SMAInterstitial *)interstitial
{
    if ([self.delegate respondsToSelector:@selector(reportImpression)]) {
        [self.delegate reportImpression];
    }
}

- (void)interstitialWillDisappear:(SMAInterstitial *)interstitial
{
    if ([self.delegate respondsToSelector:@selector(willDismissFullScreenView)]) {
        [self.delegate willDismissFullScreenView];
    }
}

- (void)interstitialDidDisappear:(SMAInterstitial *)interstitial
{
    if ([self.delegate respondsToSelector:@selector(didDismissFullScreenView)]) {
        [self.delegate didDismissFullScreenView];
    }
}

- (void)interstitialDidClick:(SMAInterstitial *)interstitial
{
    if ([self.delegate respondsToSelector:@selector(reportClick)]) {
        [self.delegate reportClick];
    }
}

- (void)interstitialWillLeaveApplication:(SMAInterstitial *)interstitial
{
    // No corresponding method from AdMob SDK available. willBackgroundApplication is been depricated.
}

@end
