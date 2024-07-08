//
//  SDKAdmobVideo.m
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//

#import "SDKAdmobVideo.h"
#import "SDKAdmobRequest.h"
#import "SDKFacade.h"
#import <UIKit/UIKit.h>
#import <IvyiOSSdk/SDKTimer.h>
#import <GoogleMobileAds/GADRewardedAd.h>
#import <GoogleMobileAds/GADFullScreenContentDelegate.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKHelper.h>
@interface MyCloseBtn : UIView
-(instancetype)initWithTarget:(SDKBaseAd *)target frame:(CGRect)frame;
@end
@implementation MyCloseBtn
{
    @private
    SDKBaseAd *_target;
}
-(instancetype)initWithTarget:(SDKBaseAd *)target frame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    _target = target;
    self.userInteractionEnabled = NO;
    return self;
}
-(void)dealloc
{
    if (_target) {
        [_target adDidClose];
    }
}

@end
@interface SDKAdmobVideo () <GADFullScreenContentDelegate>
@end
@implementation SDKAdmobVideo
{
@private
    GADRewardedAd *_rewardedAd;
    GADRewardedInterstitialAd* _rewardedInterstitialAd;
    BOOL _hasDidClose;
    BOOL _isRewardInterstitial;
}
#pragma mark ================== Reward Video ===================
-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
  
    if (config) {
      if ([config valueForKey:@"reward_interstitial"] && [[config valueForKey:@"reward_interstitial"] integerValue] == 1) {
        DLog(@"Reward Interstitial detected, will use Reward Interstitial API to load this placement");
        _isRewardInterstitial = YES;
      } else {
        _isRewardInterstitial = NO;
      }
    }
    _rewardSeconds = 30;
    return self;
}

-(void)adDidClose
{
    if (_hasDidClose) {
        return;
    }
    _rewardedAd = nil;
    _rewardedInterstitialAd = nil;
    _hasDidClose = YES;
    [super adDidClose];
}

-(BOOL)loadAd:(UIViewController *)vc
{
#if DEBUG
//    _adId = @"ca-app-pub-3940256099942544/1712485313";
#endif
    if([super loadAd:vc]) {
        
        GADRequest *request = [SDKAdmobRequest createRequest];
        
        _rewardedAd = nil;
        _rewardedInterstitialAd = nil;
        if (_isRewardInterstitial) {
            DLog(@"Reward Interstitial detected, will use Reward Interstitial API to load this placement");
            
            [GADRewardedInterstitialAd loadWithAdUnitID:_adId request:request completionHandler:^(GADRewardedInterstitialAd* _Nullable rewardedInterstitialAd, NSError* _Nullable error) {
                if (!error) {
                    self->_rewardedInterstitialAd = rewardedInterstitialAd;
                    self->_rewardedInterstitialAd.fullScreenContentDelegate = self;
                    __weak typeof(self) _self = self;
                    self->_rewardedInterstitialAd.paidEventHandler = ^void(GADAdValue *_Nonnull adValue) {
                        __strong typeof(_self) self = _self;
                        [[SDKFacade sharedInstance] logAdRevenue:self->_rewardedInterstitialAd && self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo ? self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob" mediationType:SdkMediationNetworkTypeGoogleAdMob adType:@"rewarded" adUnit:self->_adId placement:self->_tag country:[SDKHelper getCountryCode].lowercaseString currency:adValue.currencyCode value:adValue.value];
                    };
                    [self adLoaded];
                    DLog(@"[adlog] [tag:%@(%d)] [%@:adLoaded] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo?self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob");
                } else {
                    [self adFailed:[error localizedDescription]];
                    DLog(@"[adlog] [tag:%@(%d)] [%@:adFailed] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo ? self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName : @"admob");
                    return;
                }
            }
            ];
        } else {
            [GADRewardedAd loadWithAdUnitID: _adId request:request completionHandler:^(GADRewardedAd *ad, NSError *error) {
                if (error) {
                    [self adFailed:[error localizedDescription]];
                    DLog(@"[adlog] [tag:%@(%d)] [%@:adFailed] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo?self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob");
                    return;
                }
                self->_rewardedAd = ad;
                self->_rewardedAd.fullScreenContentDelegate = self;
                __weak typeof(self) _self = self;
                self->_rewardedAd.paidEventHandler = ^void(GADAdValue *_Nonnull adValue) {
                    __strong typeof(_self) self = _self;
                    [[SDKFacade sharedInstance] logAdRevenue:self->_rewardedAd && self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo?self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob" mediationType:SdkMediationNetworkTypeGoogleAdMob adType:@"rewarded" adUnit:self->_adId placement:self->_tag country:[SDKHelper getCountryCode].lowercaseString currency:adValue.currencyCode value:adValue.value];
                };
                [self adLoaded];
                DLog(@"[adlog] [tag:%@(%d)] [%@:adLoaded] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo?self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob");
            }];
            return true;
        }
    }
    return false;
}

-(void)adFailed:(NSString *)error
{
    [super adFailed:error];
}

-(void)adShowFailed
{
    [super adShowFailed];
}

-(void)showAd:(UIViewController *)vc
{
    [super showAd:vc];
    if (_isRewardInterstitial) {
      DLog(@"Reward Interstitial detected, will display Reward Interstitial");

      if(self.isAvailable && _rewardedInterstitialAd && [_rewardedInterstitialAd canPresentFromRootViewController:vc error: nil]) {
          
          [_rewardedInterstitialAd presentFromRootViewController:vc userDidEarnRewardHandler:^ {
              self->_hasReward = YES;
              DLog(@"[adlog] [tag:%@(%d)] [%@:adDidShown] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo?self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob");
          }];
          _hasDidClose = NO;
          [self startCheckShowFailedTimer];
      } else {
          DLog(@"Reward Interstitial show failed, not available");
          [self adShowFailed];
      }
    } else {
      if(self.isAvailable && _rewardedAd && [_rewardedAd canPresentFromRootViewController:vc error: nil]) {
          
          [_rewardedAd presentFromRootViewController:vc userDidEarnRewardHandler:^ {
              self->_hasReward = YES;
              DLog(@"[adlog] [tag:%@(%d)] [%@:adDidShown] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo?self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob");
          }];
          _hasDidClose = NO;
          [self startCheckShowFailedTimer];
      } else {
          [self adShowFailed];
      }
    }
}

-(void)adDidRecordImpression:(id<GADFullScreenPresentingAd>)ad
{
    [self adDidShown];
    if (_rewardedInterstitialAd) {
        DLog(@"[adlog] [tag:%@(%d)] [%@:adDidShown] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo?self->_rewardedInterstitialAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob");
    } else {
        DLog(@"[adlog] [tag:%@(%d)] [%@:adDidShown] [id:%@] [adapter:%@]", self->_tag, self->_index, NSStringFromClass(self.class), self->_adId, self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo?self->_rewardedAd.responseInfo.loadedAdNetworkResponseInfo.adNetworkClassName:@"admob");
    }
}

-(void)adDidRecordClick:(id<GADFullScreenPresentingAd>)ad
{
    [self adDidClick];
}

/// Tells the delegate that the rewarded ad failed to present.
- (void)ad:(id)ad
    didFailToPresentFullScreenContentWithError:(NSError *)error {
    [self adShowFailed];
}

/// Tells the delegate that the rewarded ad was dismissed.
- (void)adDidDismissFullScreenContent:(id)ad {
    [self adDidClose];
}

-(void)didBecomeActive
{
    for (UIView *v in [[UIApplication sharedApplication].keyWindow subviews]) {
        if ([NSStringFromClass([v class]) isEqualToString:@"UITransitionView"]) {
            if (v.subviews.count > 0 && v.subviews[0].subviews.count > 0) {
                for (UIView *_v in v.subviews[0].subviews) {
                    if([NSStringFromClass(_v.class) isEqualToString:@"GADCloseButton"]) {
                        MyCloseBtn *_close = [[MyCloseBtn alloc] initWithTarget:self frame:_v.frame];
                        [_v.superview addSubview:_close];
                    }
                }
            }
        }
    }
}

@end

