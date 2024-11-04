//
//  LevelPlayRewardedVideoDelegate.h
//  IronSource
//
//  Copyright © 2022 IronSource. All rights reserved.
//
#import "ISAdInfo.h"
#import "LevelPlayRewardedVideoBaseDelegate.h"

#ifndef LevelPlayRewardedVideoDelegate_h
#define LevelPlayRewardedVideoDelegate_h

@protocol LevelPlayRewardedVideoDelegate <LevelPlayRewardedVideoBaseDelegate>

@required

/**
 Called after a rewarded video has changed its availability to true.
 @param adInfo The info of the ad.
 */
- (void)hasAvailableAdWithAdInfo:(ISAdInfo *)adInfo;

/**
 Called after a rewarded video has changed its availability to false.
 */
- (void)hasNoAvailableAd;

@end

#endif /* LevelPlayRewardedVideoDelegate_h */
