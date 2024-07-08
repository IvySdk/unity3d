//
//  SMAAdMobSmaatoNativeAdapter.m
//  SmaatoSDKAdapters
//
//  Created by Smaato Inc on 06.02.20.
//  Copyright © 2020 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <GoogleMobileAds/GoogleMobileAds.h>
#import <SmaatoSDKNative/SmaatoSDKNative.h>
#import "SMAAdMobSmaatoNativeAdapter.h"

static NSString *const kSMAAdMobCustomEventInfoAdSpaceIdKey = @"adspaceId";
static NSString *const kSMAAdMobSmaatoNativeAdapterVersion = @"10.10.0.0";

typedef void (^SMASMAAdMobMediatedNativeAdDeferredCallback)(id<GADMediatedUnifiedNativeAd> mediatedAd);

@interface SMAAdMobSmaatoNativeAdapter () <GADMediationNativeAd, SMANativeAdDelegate, GADMediationAdapter>

@property (nonatomic) SMANativeAd *nativeAd;
@property (nonatomic, weak) UIViewController *presentingViewController;
@property (nonatomic) GADNativeAdImageAdLoaderOptions *imageLoaderOptions;
@property (nonatomic, copy) NSString *adSpaceId;
@property (nonatomic) NSMutableArray<SMASMAAdMobMediatedNativeAdDeferredCallback> *deferredCallbacks;
@property (nonatomic) GADMediationNativeLoadCompletionHandler loadCompletionHandler;
@property (nonatomic, weak, nullable) id<GADMediationNativeAdEventDelegate> delegate;
@property (nonatomic) SMANativeAdRenderer *adRenderer;
@property (nonatomic, copy) NSArray<GADNativeAdImage *> *mainImages;
@property (nonatomic) GADNativeAdImage *iconImage;

@end

@implementation SMAAdMobSmaatoNativeAdapter

+ (NSString *)version
{
    return kSMAAdMobSmaatoNativeAdapterVersion;
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

- (NSString *)getAdNetworkId
{
    return self.adSpaceId;
}

#pragma mark - Assets

- (NSString *)headline
{
    return self.adRenderer.nativeAssets.title;
}

- (NSString *)body
{
    return self.adRenderer.nativeAssets.mainText;
}

- (GADNativeAdImage *)icon
{
    return self.iconImage;
}

- (NSArray<GADNativeAdImage *> *)images
{
    return self.mainImages.count > 0 ? self.mainImages : nil;
}

- (NSString *)callToAction
{
    return self.adRenderer.nativeAssets.cta;
}

- (NSDecimalNumber *)starRating
{
    return nil;
}

- (NSString *)price
{
    return nil;
}

- (NSString *)store
{
    return nil;
}

- (NSDictionary<NSString *, id> *)extraAssets
{
    return nil;
}

- (NSString *)advertiser
{
    return nil;
}

- (UIView *)adChoicesView
{
    return self.adRenderer.privacyView;
}

#pragma mark - <GADMediatedUnifiedNativeAd> Callbacks

- (void)didRenderInView:(UIView *)view
    clickableAssetViews:(NSDictionary<GADNativeAssetIdentifier, UIView *> *)clickableAssetViews
 nonclickableAssetViews:(NSDictionary<GADNativeAssetIdentifier, UIView *> *)nonclickableAssetViews
         viewController:(UIViewController *)viewController
{
    [self.adRenderer registerViewForImpression:view];
    [self.adRenderer registerViewsForClickAction:clickableAssetViews.allValues];
    self.presentingViewController = viewController;
}

- (void)didRecordClickOnAssetWithName:(GADNativeAssetIdentifier)assetName
                                 view:(UIView *)view
                       viewController:(UIViewController *)viewController
{
    // Method is not applicable because Smaato SDK handles click itself
}

- (void)didRecordImpression
{
    // Method is not applicable because Smaato SDK tracks impression itself
}

- (void)nativeAdDidClick:(SMANativeAd *)nativeAd
{
    [GADMediatedUnifiedNativeAdNotificationSource mediatedNativeAdDidRecordClick:self];
}

- (void)nativeAdDidImpress:(SMANativeAd *)nativeAd
{
    [GADMediatedUnifiedNativeAdNotificationSource mediatedNativeAdDidRecordImpression:self];
}

- (void)nativeAdWillPresentModalContent:(SMANativeAd *)nativeAd
{
    [GADMediatedUnifiedNativeAdNotificationSource mediatedNativeAdWillPresentScreen:self];
}

- (void)nativeAdDidPresentModalContent:(SMANativeAd *)nativeAd
{
    // No corresponding delegate method from AdMob SDK available.
}

- (void)nativeAdDidDismissModalContent:(SMANativeAd *)nativeAd
{
    [GADMediatedUnifiedNativeAdNotificationSource mediatedNativeAdWillDismissScreen:self];
    [GADMediatedUnifiedNativeAdNotificationSource mediatedNativeAdDidDismissScreen:self];
}

- (void)nativeAdWillLeaveApplicationFromAd:(SMANativeAd *)nativeAd
{
    //mediatedNativeAdWillLeaveApplication is depricated with no replacement method
    //[GADMediatedUnifiedNativeAdNotificationSource mediatedNativeAdWillLeaveApplication:self];
}
#pragma mark - GADMediationNativeAd

- (void)loadNativeAdForAdConfiguration:(GADMediationNativeAdConfiguration *)adConfiguration completionHandler:(GADMediationNativeLoadCompletionHandler)completionHandler
{
    
    self.loadCompletionHandler = completionHandler;
    
    self.deferredCallbacks = [NSMutableArray new];

    // Persisting instance of presenting view controller
    self.presentingViewController = adConfiguration.topViewController;

    // Extract key-value pairs from passed server parameter string
    NSDictionary *info = nil;
    
    if ([adConfiguration.credentials.settings objectForKey:@"parameter"]) {
        info = [self dictionaryFromServerParameter:adConfiguration.credentials.settings[@"parameter"]];
    }

    self.adSpaceId = [self fetchValueForKey:kSMAAdMobCustomEventInfoAdSpaceIdKey fromEventInfo:info];

    // Verify ad space information
    if (![self checkCredentialsWithAdSpaceId:self.adSpaceId]) {
        return;
    }

    // Create and configure ad object
    self.nativeAd = [[SMANativeAd alloc] init];
    self.nativeAd.delegate = self;

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
     Optional:
     You can also set specific user profile targeting parameters here.
     Please check the Smaato wiki for all available properties and further details.

     Examples:
     SmaatoSDK.userAge = 30;
     SmaatoSDK.userGender = kSMAGenderMale;
     */

    SMAAdRequestParams *adRequestParams = [SMAAdRequestParams new];

    // Passing mediation information
    adRequestParams.mediationNetworkName = [self smaatoMediationNetworkName];
    adRequestParams.mediationAdapterVersion = kSMAAdMobSmaatoNativeAdapterVersion;
    adRequestParams.mediationNetworkSDKVersion = [NSString stringWithFormat:@"%s", GoogleMobileAdsVersionString];

    for (GADNativeAdImageAdLoaderOptions *imageOptions in adConfiguration.options) {
        if (![imageOptions isKindOfClass:[GADNativeAdImageAdLoaderOptions class]]) {
            continue;
        }
        self.imageLoaderOptions = imageOptions;
    }

    SMANativeAdRequest *adRequest = [[SMANativeAdRequest alloc] initWithAdSpaceId:self.adSpaceId];
    adRequest.allowMultipleImages = self.imageLoaderOptions.shouldRequestMultipleImages;
    adRequest.returnUrlsForImageAssets = self.imageLoaderOptions.disableImageLoading;
    [self.nativeAd loadWithAdRequest:adRequest requestParams:adRequestParams];
}

- (BOOL)handlesUserClicks
{
    return YES;
}

- (BOOL)handlesUserImpressions
{
    return YES;
}

#pragma mark - SMANativeAdDelegate

- (void)nativeAd:(SMANativeAd *)nativeAd didLoadWithAdRenderer:(SMANativeAdRenderer *)renderer
{
    [self handleResponse:renderer];
    if (self.loadCompletionHandler) {
        self.delegate = self.loadCompletionHandler(self, nil);
        self.loadCompletionHandler = nil;
    }
}

- (void)nativeAd:(SMANativeAd *)nativeAd didFailWithError:(NSError *)error
{
    if ([self.delegate respondsToSelector:@selector(didFailToPresentWithError:)]) {
        [self.delegate didFailToPresentWithError:error];
    }
}

- (void)nativeAdDidTTLExpire:(SMANativeAd *)nativeAd
{
    if ([self.delegate respondsToSelector:@selector(didFailToPresentWithError:)]) {
        NSString *errorMessage = @"Native TTL has expired.";
        NSDictionary *userInfo = @{ NSLocalizedDescriptionKey : errorMessage };
        NSError *error = [NSError errorWithDomain:[self smaatoMediationNetworkName] code:kSMAErrorCodeNoAdAvailable userInfo:userInfo];

        [self.delegate didFailToPresentWithError:error];
    }
}

- (UIViewController *)presentingViewControllerForNativeAd:(SMANativeAd *)nativeAd
{
    return self.presentingViewController;
}

#pragma mark - Private

- (void)handleResponse:(SMANativeAdRenderer *)renderer
{
    BOOL disableImageLoading = self.imageLoaderOptions.disableImageLoading;
    SMANativeAdAssets *assets = renderer.nativeAssets;
    CGFloat defaultImageScale = 1;

    GADNativeAdImage *iconImage = nil;
    if (disableImageLoading) {
        NSURL *iconURL = assets.icon.url;
        if (iconURL) {
            iconImage = [[GADNativeAdImage alloc] initWithURL:iconURL scale:defaultImageScale];
        }
    } else {
        UIImage *iconCreative = assets.icon.image;
        if (iconCreative) {
            iconImage = [[GADNativeAdImage alloc] initWithImage:iconCreative];
        }
    }

    NSMutableArray<GADNativeAdImage *> *mainImages = [NSMutableArray new];
    [assets.images enumerateObjectsUsingBlock:^(SMANativeImage *obj, NSUInteger idx, BOOL *stop) {
        GADNativeAdImage *image = nil;
        if (disableImageLoading) {
            NSURL *url = obj.url;
            if (url) {
                image = [[GADNativeAdImage alloc] initWithURL:url scale:defaultImageScale];
            }
        } else {
            UIImage *imageCreative = obj.image;
            if (imageCreative) {
                image = [[GADNativeAdImage alloc] initWithImage:imageCreative];
            }
        }
        if (image) {
            [mainImages addObject:image];
        }
    }];
    
    self.iconImage = iconImage;
    self.mainImages = mainImages;
    self.adRenderer = renderer;
}

- (void)callDeferredCallbacksWithMediatedNativeAd:(id<GADMediatedUnifiedNativeAd>)mediatedAd
{
    @synchronized(self.deferredCallbacks) {
        if (mediatedAd) {
            for (SMASMAAdMobMediatedNativeAdDeferredCallback callback in self.deferredCallbacks) {
                callback(mediatedAd);
            }
            [self.deferredCallbacks removeAllObjects];
        }
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
    NSString *versionString = kSMAAdMobSmaatoNativeAdapterVersion;
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
    return nil;
}

@end
