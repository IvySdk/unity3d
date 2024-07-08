//
//  SDKVideo.m
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//

#import "SDKVideo.h"
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKCache.h>
@implementation SDKVideo
{
    @private
    CFTimeInterval _startTime;
}
@synthesize rewardId = _rewardId;
NSString * const CLICK_VIDEO = @"CLICK_VIDEO";
NSString * const SHOW_VIDEO = @"SHOW_VIDEO";
-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(nullable NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    _adType = SDK_ADTYPE_VIDEO;
    _rewardSeconds = 10;
    return [super init];
}

-(BOOL)loadAd:(UIViewController *)vc
{
    if (_isShowing) {
        return false;
    }
    return [super loadAd:vc];
}

-(void)showAd:(UIViewController *)vc
{
    _startTime = 0;
    [super showAd:vc];
}

-(void)adReward
{
    DLog(@"[adlog] [tag:%@(%d)] [%@:adReward] [id:%@]", _tag, _index, NSStringFromClass(self.class), _adId);
    [[SDKFacade sharedInstance] logAdClick:[SDKConstants getAdTypeName:_adType] adPos:_tag platform:_platform];
    [[SDKFacade sharedInstance] logEvent:@"video_completed"];
//        [[SDKFacade sharedInstance] logEvent:@"video_rewarded"];
    [SDKFacade sharedInstance].vcCount = [SDKFacade sharedInstance].vcCount + 1;
    NSArray *arr = [[SDKFacade sharedInstance] getTrackAdCountArray:@"vc"];
    int vcCount = [SDKFacade sharedInstance].vcCount;
    if (arr && arr.count > 0) {
        for (int i=0; i<arr.count; i++) {
            long value = [(NSNumber *)[arr objectAtIndex:i] longValue];
            if (vcCount == value) {
                [[SDKFacade sharedInstance] logEvent:[NSString stringWithFormat:@"vc%d", vcCount]];
                break;
            }
        }
    }
    if(_delegate && [_delegate respondsToSelector:@selector(adReward:rewardId:)]) {
        [_delegate adReward:_tag rewardId:_rewardId];
    }
    if (![_platform isEqualToString:@"applovin"] && ![_platform isEqualToString:@"our"] && ![_platform isEqualToString:@"delicious"]) {
        id tmp = [[SDKCache cache] objectForKey:CLICK_VIDEO];
        int count = tmp ? [(NSNumber *)tmp intValue]: 0;
        count = count + 1;
        NSArray *arr = [[SDKFacade sharedInstance] getTrackAdCountArray:@"cv"];
        if (arr && arr.count > 0) {
            for (int i=0; i<arr.count; i++) {
                long value = [(NSNumber *)[arr objectAtIndex:i] intValue];
                if (count == value) {
                    [[SDKFacade sharedInstance] logEvent:[NSString stringWithFormat:@"cv%d", count]];
                    break;
                }
            }
        }
        [[SDKCache cache] setObject:[NSNumber numberWithInt:count] forKey:CLICK_VIDEO];
    }
}

-(void)adDidClose
{
    if (_hasReward || (_hasShown && CACurrentMediaTime() - _startTime >= _rewardSeconds)) {
        [self adReward];
    }
    _hasReward = NO;
    [SDKFacade sharedInstance].justShowFullAd = NO;
    [[SDKFacade sharedInstance].originWindow makeKeyAndVisible];
    [super adDidClose];
}

-(void)adShowFailed
{
    _hasReward = NO;
    [super adShowFailed];
}

-(void)adDidShown
{
    _hasReward = NO;
    _startTime = CACurrentMediaTime();
    [super adDidShown];
    [SDKFacade sharedInstance].justShowFullAd = YES;
    if (![_platform isEqualToString:@"our"] && ![_platform isEqualToString:@"delicious"]) {
        id tmp = [[SDKCache cache] objectForKey:SHOW_VIDEO];
        int count = tmp ? [(NSNumber *)tmp intValue]: 0;
        count = count + 1;
        NSArray *arr = [[SDKFacade sharedInstance] getTrackAdCountArray:@"sv"];
        if (arr && arr.count > 0) {
            for (int i=0; i<arr.count; i++) {
                long value = [(NSNumber *)[arr objectAtIndex:i] intValue];
                if (count == value) {
                    [[SDKFacade sharedInstance] logEvent:[NSString stringWithFormat:@"sv%d", count]];
                    break;
                }
            }
        }
        [[SDKCache cache] setObject:[NSNumber numberWithInt:count] forKey:SHOW_VIDEO];
    }
}
@end
