//
//  AdsCustomEventRewarded.h
//  Pods
//
//  Created by iOS打包3 on 2023/5/23.
//

#ifndef AdsCustomEventRewarded_h
#define AdsCustomEventRewarded_h

#import <Foundation/Foundation.h>
#import <GoogleMobileAds/GoogleMobileAds.h>

@interface AdsCustomEventRewardAd : NSObject<GADFullScreenContentDelegate>
- (void)loadRewardedAdForAdConfiguration:(GADMediationRewardedAdConfiguration *)adConfiguration
                       completionHandler:(GADMediationRewardedLoadCompletionHandler)completionHandler;
@end

#endif /* AdsCustomEventRewarded_h */
