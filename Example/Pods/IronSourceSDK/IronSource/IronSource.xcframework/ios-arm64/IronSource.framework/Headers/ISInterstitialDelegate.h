//
//  Copyright © 2017 IronSource. All rights reserved.
//

#ifndef IRONSOURCE_INTERSTITIAL_DELEGATE_H
#define IRONSOURCE_INTERSTITIAL_DELEGATE_H

#import <Foundation/Foundation.h>

__attribute__((deprecated("This class has been deprecated as of SDK 7.3.0. Please use "
                          "LevelPlayInterstitialDelegate instead.")))
@protocol ISInterstitialDelegate<NSObject>

@required
/**
 Called after an interstitial has been loaded
 */
- (void)interstitialDidLoad
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayInterstitialDelegate instead.")));

/**
 Called after an interstitial has attempted to load but failed.

 @param error The reason for the error
 */
- (void)interstitialDidFailToLoadWithError:(NSError *)error
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayInterstitialDelegate instead.")));

/**
 Called after an interstitial has been opened.
 */
- (void)interstitialDidOpen
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayInterstitialDelegate instead.")));

/**
  Called after an interstitial has been dismissed.
 */
- (void)interstitialDidClose
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayInterstitialDelegate instead.")));

/**
 Called after an interstitial has been displayed on the screen.
 */
- (void)interstitialDidShow
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayInterstitialDelegate instead.")));

/**
 Called after an interstitial has attempted to show but failed.

 @param error The reason for the error
 */
- (void)interstitialDidFailToShowWithError:(NSError *)error
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayInterstitialDelegate instead.")));

/**
 Called after an interstitial has been clicked.
 */
- (void)didClickInterstitial
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayInterstitialDelegate instead.")));

@end

#endif
