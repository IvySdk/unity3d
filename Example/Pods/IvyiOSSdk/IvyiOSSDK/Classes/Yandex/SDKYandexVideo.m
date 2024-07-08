//
//  SDKYandexVideo.m
//  Pods
//
//  Created by IceStar on 2023/06/01.
//
//

#import "SDKYandexVideo.h"
#import <IvyiOSSdk/SDKFacade.h>
@implementation SDKYandexVideo
{
@private
    YMARewardedAd *_rewardedAd;
}
#pragma mark ================== Reward Video ===================
-(BOOL)loadAd:(UIViewController *)vc
{
    if([super loadAd:vc]) {
        _rewardedAd = [[YMARewardedAd alloc] initWithAdUnitID:_adId];
        _rewardedAd.delegate = self;
        
        [_rewardedAd load];
        return true;
    }
    return false;
}

-(void)showAd:(UIViewController *)vc
{
    [super showAd:vc];
    if (self.isAvailable && _rewardedAd) {
        [_rewardedAd presentFromViewController:vc];
    } else {
        [self adShowFailed];
    }
}

-(void)rewardedAd:(YMARewardedAd *)rewardedAd didReward:(id<YMAReward>)reward
{
    _hasReward = YES;
}

-(void)rewardedAdDidLoad:(YMARewardedAd *)rewardedAd
{
    [self adLoaded];
}

-(void)rewardedAdDidFailToLoad:(YMARewardedAd *)rewardedAd error:(NSError *)error
{
    [self adFailed:error.localizedDescription];
}

-(void)rewardedAdDidFailToPresent:(YMARewardedAd *)rewardedAd error:(NSError *)error
{
    [self adShowFailed];
}

-(void)rewardedAdDidAppear:(YMARewardedAd *)rewardedAd
{

    [self adDidShown];
}

-(void)rewardedAdDidClick:(YMARewardedAd *)rewardedAd
{
    [self adDidClick];
}

-(void)rewardedAdDidDisappear:(YMARewardedAd *)rewardedAd
{
    [self adDidClose];
}

-(void)rewardedAd:(YMARewardedAd *)rewardedAd didTrackImpressionWithData:(id<YMAImpressionData>)impressionData
{
    @try {
        if (impressionData != NULL && impressionData.rawData != NULL) {
            NSString * rawData = impressionData.rawData;
            NSData* jsonData = [rawData dataUsingEncoding:NSUTF8StringEncoding];
            NSError* error;
            NSDictionary* dict = [NSJSONSerialization JSONObjectWithData:jsonData options:kNilOptions error:&error];
            if (error) {
                NSLog(@"parse impression data error::%@", error);
            } else {
                NSDictionary* network = dict[@"network"];
                [[SDKFacade sharedInstance] logAdRevenue:network[@"name"] mediationType:SdkMediationNetworkTypeYandex adType:@"rewarded" adUnit:network[@"ad_unit_id"] placement:self->_tag country:[SDKHelper getCountryCode].lowercaseString currency:@"USD" value:dict[@"revenue_USD"]];
            }
        }
    } @catch (NSException *exception) {
        NSLog(@"log yandex ad revenue error::%@", exception);
    }
}

@end

