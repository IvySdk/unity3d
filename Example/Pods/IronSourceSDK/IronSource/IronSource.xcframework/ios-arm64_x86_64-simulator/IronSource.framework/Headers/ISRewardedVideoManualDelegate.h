//
//  ISRewardedVideoManualDelegate.h
//  IronSource
//
//  Created by Guy Lis on 03/10/2021.
//  Copyright © 2021 IronSource. All rights reserved.
//

#ifndef ISRewardedVideoManualDelegate_h
#define ISRewardedVideoManualDelegate_h

__attribute__((deprecated("This class has been deprecated as of SDK 7.3.0. Please use LevelPlayRewardedVideoManualDelegate instead.")))
@protocol ISRewardedVideoManualDelegate <ISRewardedVideoDelegate>

@required

/**
 Called after an rewarded video has been loaded in manual mode
 */
- (void)rewardedVideoDidLoad __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the alternate API in LevelPlayRewardedVideoManualDelegate instead.")));

/**
 Called after a rewarded video has attempted to load but failed in manual mode

 @param error The reason for the error
 */
- (void)rewardedVideoDidFailToLoadWithError:(NSError *)error __attribute__((deprecated("This API has been deprecated as of SDK 7.3.0. Please use the alternate API in LevelPlayRewardedVideoManualDelegate instead.")));

@end

#endif /* ISRewardedVideoManualDelegate_h */
