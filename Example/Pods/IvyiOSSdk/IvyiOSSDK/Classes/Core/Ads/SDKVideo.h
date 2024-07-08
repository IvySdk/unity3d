//
//  SDKVideo.h
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//
#import "SDKBaseAd.h"

@interface SDKVideo : SDKBaseAd
{
    @protected
    BOOL _hasReward;
    int _rewardSeconds;
}
-(void)adReward;
//-(void)adVideoCompleted;
@end
