//
//  AdsCustomEventUtils.h
//  Pods
//
//  Created by iOS打包3 on 2023/5/24.
//

#ifndef AdsCustomEventUtils_h
#define AdsCustomEventUtils_h

#import <Foundation/Foundation.h>
#import "AdsCustomEvent.h"
#import <GoogleMobileAds/GoogleMobileAds.h>
NSError *_Nonnull AdsCustomEventErrorWithCodeAndDescription(AdsCustomEventErrorCode code,
                                                               NSString *_Nonnull description);

CGSize BannerSizeFromAdConfiguration(GADMediationBannerAdConfiguration* _Nonnull adConfiguration,NSError *_Nullable *_Nullable errorPtr);

#endif /* AdsCustomEventUtils_h */
