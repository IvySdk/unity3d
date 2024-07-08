//
//  AdsCustomEventInterstitial.h
//  Pods
//
//  Created by iOS打包3 on 2023/5/23.
//

#ifndef AdsCustomEventInterstitial_h
#define AdsCustomEventInterstitial_h

#import <Foundation/Foundation.h>
#import <GoogleMobileAds/GADFullScreenContentDelegate.h>

@interface AdsCustomEventInterstitial : NSObject<GADFullScreenContentDelegate>

- (void)loadInterstitialForAdConfiguration:(GADMediationInterstitialAdConfiguration *)adConfiguration
                         completionHandler:(GADMediationInterstitialLoadCompletionHandler)completionHandler;
@end

#endif /* AdsCustomEventInterstitial_h */
