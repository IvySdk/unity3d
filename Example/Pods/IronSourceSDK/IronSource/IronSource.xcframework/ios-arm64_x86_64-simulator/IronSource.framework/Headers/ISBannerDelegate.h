//
//  ISBannerDelegate.h
//  IronSource
//
//  Created by Pnina Rapoport on 02/04/2017.
//  Copyright © 2017 IronSource. All rights reserved.
//

#ifndef ISBannerDelegate_h
#define ISBannerDelegate_h

#import "ISBannerView.h"

__attribute__((deprecated(
    "This class has been deprecated as of SDK 7.3.0. Please use LevelPlayBannerDelegate instead.")))
@protocol ISBannerDelegate<NSObject>

@required
/**
 Called after a banner ad has been successfully loaded
 */
- (void)bannerDidLoad:(ISBannerView *)bannerView
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayBannerDelegate instead.")));

/**
 Called after a banner has attempted to load an ad but failed.

 @param error The reason for the error
 */
- (void)bannerDidFailToLoadWithError:(NSError *)error
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayBannerDelegate instead.")));

/**
 Called after a banner has been clicked.
 */
- (void)didClickBanner
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayBannerDelegate instead.")));

/**
 Called when a banner is about to present a full screen content.
 */
- (void)bannerWillPresentScreen
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayBannerDelegate instead.")));

/**
 Called after a full screen content has been dismissed.
 */
- (void)bannerDidDismissScreen
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayBannerDelegate instead.")));

/**
 Called when a user would be taken out of the application context.
 */
- (void)bannerWillLeaveApplication
    __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the "
                              "alternate API in LevelPlayBannerDelegate instead.")));

@end

#endif /* ISBannerDelegate_h */
