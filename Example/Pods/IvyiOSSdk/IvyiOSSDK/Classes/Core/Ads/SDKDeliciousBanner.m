//
//  SDKDeliciousBanner.m
//  Bolts
//
//  Created by 余冰星 on 2018/5/21.
//

#import "SDKDeliciousBanner.h"
#import <IvyiOSSdk/UIKit+SDKNetworking.h>
#import <IvyiOSSdk/SDKHelper.h>
#import "SDKFacade.h"
#import "SDKDeliciousBannerView.h"
@implementation SDKDeliciousBanner
{
    @private
    NSString *configJsonName;
}
@synthesize adDatas = _adDatas;
-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    _loadIntervalSec = 0;
    if (_config) {
        configJsonName = [_config objectForKey:@"config"];
    }
    return self;
}

- (BOOL)isAvailable
{
    _isAvailable = [[SDKFacade sharedInstance] isDeliciousAdAvailable:NO] && self.adView;
    return [super isAvailable];
}

-(BOOL)loadAd:(UIViewController *)vc
{
    [super loadAd:vc];
    if ([self isAvailable]) {
        [self adLoaded];
    } else {
        [self adFailed:@"no fill"];
    }
    return true;
}

-(UIView *)adView
{
    if(!_adView) {
        if ([[SDKFacade sharedInstance] isDeliciousAdAvailable:NO] && configJsonName) {
            SDKDeliciousBannerView *_deliciousBannerView = (SDKDeliciousBannerView *)[[SDKFacade sharedInstance] getDeliciousBannerView:configJsonName];
            if (_deliciousBannerView) {
                if(isPad) {
                    _deliciousBannerView.frame = CGRectMake(0, 0, Screen_width * 0.7f, Screen_width * 0.7f * 50 / 320);
                } else {
                    _deliciousBannerView.frame = CGRectMake(0, 0, Screen_width, Screen_width * 50 / 320);
                }
                _adView = _deliciousBannerView;
            }
        }
    }
    return _adView;
}

-(void)onBannerShow:(UIView *)view
{
    [self onBannerShow:view];
    if (_adView && [_adView superview]) {
        [(SDKDeliciousBannerView *)_adView select:0];
    }
}
@end
