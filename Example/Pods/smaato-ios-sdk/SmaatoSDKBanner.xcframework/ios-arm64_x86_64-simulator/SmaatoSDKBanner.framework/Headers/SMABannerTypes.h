//
//  SMABannerTypes.h
//  SmaatoSDKBanner
//
//  Created by Smaato Inc on 22.01.19.
//  Copyright © 2019 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>

/**
 Banner sizes currently supported by the SDK.
 */
typedef NS_ENUM(NSUInteger, SMABannerAdSize) {
    /** Any size */
    kSMABannerAdSizeAny,
    /** XXLarge (320x50) */
    kSMABannerAdSizeXXLarge_320x50,
    /** Medium Rectangle (300x250) */
    kSMABannerAdSizeMediumRectangle_300x250,
    /** Leaderboard (728x90) */
    kSMABannerAdSizeLeaderboard_728x90,
    /** Skyscraper (120x600) */
    kSMABannerAdSizeSkyscraper_120x600,
};

/**
 Banner autoreload interval.
 */
typedef NS_ENUM(NSInteger, SMABannerAutoreloadInterval) {
    /** Disabled */
    kSMABannerAutoreloadIntervalDisabled = -1,
    /** Default autoreload time interval. */
    kSMABannerAutoreloadIntervalDefault = 0,
    /** Very short time interval, approximately 10 seconds. */
    kSMABannerAutoreloadIntervalVeryShort = 1,
    /** Short time interval, approximately 30 seconds. */
    kSMABannerAutoreloadIntervalShort = 2,
    /** Normal time interval, approximately 60 seconds. */
    kSMABannerAutoreloadIntervalNormal = 3,
    /** Long time interval, approximately 120 seconds. */
    kSMABannerAutoreloadIntervalLong = 4,
    /** Very long time interval, approximately 240 seconds. */
    kSMABannerAutoreloadIntervalVeryLong = 5,
};
