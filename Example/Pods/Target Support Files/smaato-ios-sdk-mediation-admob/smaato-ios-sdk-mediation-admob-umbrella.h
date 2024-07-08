#ifdef __OBJC__
#import <UIKit/UIKit.h>
#else
#ifndef FOUNDATION_EXPORT
#if defined(__cplusplus)
#define FOUNDATION_EXPORT extern "C"
#else
#define FOUNDATION_EXPORT extern
#endif
#endif
#endif

#import "SMAAdMobSmaatoBannerAdapter.h"
#import "SMAAdMobSmaatoInterstitialAdapter.h"
#import "SMAAdMobSmaatoNativeAdapter.h"
#import "SMAAdMobSmaatoRewardedVideoAdapter.h"

FOUNDATION_EXPORT double smaato_ios_sdk_mediation_admobVersionNumber;
FOUNDATION_EXPORT const unsigned char smaato_ios_sdk_mediation_admobVersionString[];

