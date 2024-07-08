//
//  AdsCustomEventUtils.m
//  IvyiOSSdk
//
//  Created by iOS打包3 on 2023/5/24.
//

#import <Foundation/Foundation.h>
#import "AdsCustomEventUtils.h"


NSError *_Nonnull AdsCustomEventErrorWithCodeAndDescription(AdsCustomEventErrorCode code,
                                                               NSString *_Nonnull description) {
  NSDictionary *userInfo =
      @{NSLocalizedDescriptionKey : description, NSLocalizedFailureReasonErrorKey : description};
  NSError *error = [NSError errorWithDomain:@"com.google.customevent.samplenetwork"
                                       code:code
                                   userInfo:userInfo];
  return error;
}


CGSize BannerSizeFromAdConfiguration(GADMediationBannerAdConfiguration* _Nonnull adConfiguration,NSError *_Nullable *_Nullable errorPtr) {
  GADAdSize adSize320x50 = GADAdSizeFromCGSize(CGSizeMake(320, 50));
  GADAdSize adSize320x100 = GADAdSizeFromCGSize(CGSizeMake(320, 100));
  GADAdSize adSize300x250 = GADAdSizeFromCGSize(CGSizeMake(300, 250));
  GADAdSize adSize728x90 = GADAdSizeFromCGSize(CGSizeMake(728, 90));
  NSArray<NSValue *> *possibleSizes =
      @[ @(adSize320x50), @(adSize320x100), @(adSize300x250), @(adSize728x90) ];

  GADAdSize requestedSize = adConfiguration.adSize;
  GADAdSize closestAdSize = GADClosestValidSizeForAdSizes(requestedSize, possibleSizes);

  if (GADAdSizeEqualToSize(closestAdSize, GADAdSizeInvalid)) {
    NSString *errorMessage = [NSString
                              stringWithFormat:@"The requested banner size: %@ is not supported by SDK.",
                              NSStringFromGADAdSize(requestedSize)];
    *errorPtr = AdsCustomEventErrorWithCodeAndDescription(AdsCustomEventErrorBannerSizeInValid,errorMessage);
    return CGSizeZero;
  }

  return CGSizeMake(closestAdSize.size.width, closestAdSize.size.height);
}
