//
//  SMAAdMobSmaatoBannerAdapter.m
//  SmaatoSDKAdapters
//
//  Created by Smaato Inc on 20.11.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <GoogleMobileAds/GoogleMobileAds.h>
#import <SmaatoSDKBanner/SmaatoSDKBanner.h>
#import "SMAAdMobSmaatoBannerAdapter.h"

static NSString *const kSMAAdMobCustomEventInfoAdSpaceIdKey = @"adspaceId";
static NSString *const kSMAAdMobSmaatoBannerAdapterVersion = @"10.10.0.0";

@interface SMAAdMobSmaatoBannerAdapter () <GADMediationBannerAd, SMABannerViewDelegate>
@property (nonatomic) SMABannerView *bannerView;
@property (nonatomic, weak, nullable) id<GADMediationBannerAdEventDelegate> delegate;
@property (nonatomic) GADMediationBannerLoadCompletionHandler loadCompletionHandler;
@property (nonatomic) UIViewController *presentingModalViewController;
@end

@implementation SMAAdMobSmaatoBannerAdapter

@synthesize view;

+ (NSString *)version
{
    return kSMAAdMobSmaatoBannerAdapterVersion;
}

- (void)loadBannerForAdConfiguration:(nonnull GADMediationBannerAdConfiguration *)adConfiguration
                   completionHandler:
                       (nonnull GADMediationBannerLoadCompletionHandler)completionHandler
{
    //Assign delegate and save completion handler for later use
    self.loadCompletionHandler = completionHandler;
    
    // Convert ad size format
    SMABannerAdSize convertedAdSize = [self SMABannerAdSizeFromRequestedSize:adConfiguration.adSize];
    
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
    
    // Create and configure ad view object
    self.bannerView = [[SMABannerView alloc] initWithFrame:CGRectMake(0.0f, 0.0f, adConfiguration.adSize.size.width, adConfiguration.adSize.size.height)];
    self.bannerView.delegate = self;
    self.bannerView.autoreloadInterval = kSMABannerAutoreloadIntervalDisabled;
    
    self.presentingModalViewController = adConfiguration.topViewController;

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
    adRequestParams.mediationAdapterVersion = kSMAAdMobSmaatoBannerAdapterVersion;
    adRequestParams.mediationNetworkSDKVersion = [NSString stringWithFormat:@"%s", GoogleMobileAdsVersionString];

    // Load ad
    [self.bannerView loadWithAdSpaceId:adSpaceId adSize:convertedAdSize requestParams:adRequestParams];
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

- (SMABannerAdSize)SMABannerAdSizeFromRequestedSize:(GADAdSize)requestedSize
{
    CGSize requestAdSize = requestedSize.size;
    
    if ((int)(requestAdSize.height) >= 600 && (int)(requestAdSize.width) >= 120) {
        return kSMABannerAdSizeSkyscraper_120x600;
    } else if ((int)(requestAdSize.height) >= 250 && (int)(requestAdSize.width) >= 300) {
        return kSMABannerAdSizeMediumRectangle_300x250;
    } else if ((int)(requestAdSize.height) >= 90 && (int)(requestAdSize.width) >= 728) {
        return kSMABannerAdSizeLeaderboard_728x90;
    } else {
        return kSMABannerAdSizeXXLarge_320x50;
    }
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
    NSString *versionString = kSMAAdMobSmaatoBannerAdapterVersion;
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

#pragma mark - SMABannerViewDelegate

- (UIViewController *)presentingViewControllerForBannerView:(SMABannerView *)bannerView
{
    return self.presentingModalViewController;
}

- (void)bannerViewDidLoad:(SMABannerView *)bannerView
{
    view = bannerView;
    if (self.loadCompletionHandler) {
        self.delegate = self.loadCompletionHandler(self, nil);
        if ([self.delegate respondsToSelector:@selector(reportImpression)]) {
            [self.delegate reportImpression];
        }
        self.loadCompletionHandler = nil;
    }
}

- (void)bannerViewDidClick:(SMABannerView *)bannerView
{
    if ([self.delegate respondsToSelector:@selector(reportClick)]) {
        [self.delegate reportClick];
    }
}

- (void)bannerView:(SMABannerView *)bannerView didFailWithError:(NSError *)error
{
    self.loadCompletionHandler(nil, error);
}

- (void)bannerViewWillPresentModalContent:(SMABannerView *)bannerView
{
    if ([self.delegate respondsToSelector:@selector(willPresentFullScreenView)]) {
        [self.delegate willPresentFullScreenView];
    }
}

- (void)bannerViewDidPresentModalContent:(SMABannerView *)bannerView
{
    // No corresponding method from AdMob SDK available.
}

- (void)bannerViewDidDismissModalContent:(SMABannerView *)bannerView
{
    if ([self.delegate respondsToSelector:@selector(didDismissFullScreenView)]) {
        [self.delegate didDismissFullScreenView];
    }
}

- (void)bannerWillLeaveApplicationFromAd:(SMABannerView *)bannerView
{
    // No corresponding method from AdMob SDK v9+ available.
}

- (void)bannerViewDidTTLExpire:(SMABannerView *)bannerView
{
    if ([self.delegate respondsToSelector:@selector(didFailToPresentWithError:)]) {
        NSString *errorMessage = @"Banner TTL has expired.";
        NSDictionary *userInfo = @{ NSLocalizedDescriptionKey : errorMessage };
        NSError *error = [NSError errorWithDomain:[self smaatoMediationNetworkName] code:kSMAErrorCodeNoAdAvailable userInfo:userInfo];
        
        [self.delegate didFailToPresentWithError:error];
    }
}

@end
