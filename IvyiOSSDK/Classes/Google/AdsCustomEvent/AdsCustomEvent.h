//
//  AdsCustomEvent.h
//  Pods
//
//  Created by iOS打包3 on 2023/5/23.
//

#ifndef AdsCustomEvent_h
#define AdsCustomEvent_h

#import <Foundation/Foundation.h>
#import <GoogleMobileAds/GoogleMobileAds.h>

typedef NS_ENUM(NSInteger, AdsCustomEventErrorCode) {
  AdsCustomEventErrorInvalidServerParameters = 101,
  AdsCustomEventErrorAdNotAvailable = 102,
  AdsCustomEventErrorAdLoadFailureCallback = 103,
  AdsCustomEventErrorAdNotLoaded = 104,
  AdsCustomEventErrorBannerSizeInValid = 105
};


@interface AdsCustomEvent : NSObject <GADMediationAdapter>

@end


#endif /* AdsCustomEvent_h */
