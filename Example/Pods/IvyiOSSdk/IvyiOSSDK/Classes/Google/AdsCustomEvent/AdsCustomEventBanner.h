//
//  AdsCustomEventBanner.h
//  Pods
//
//  Created by iOS打包3 on 2023/5/23.
//

#ifndef AdsCustomEventBanner_h
#define AdsCustomEventBanner_h

#import <Foundation/Foundation.h>
#import <GoogleMobileAds/GoogleMobileAds.h>

@interface AdsCustomEventBanner : NSObject<GADBannerViewDelegate>

- (void)loadBannerForAdConfiguration:(GADMediationBannerAdConfiguration *)adConfiguration
                   completionHandler:(GADMediationBannerLoadCompletionHandler)completionHandler;

@end

#endif /* AdsCustomEventBanner_h */
