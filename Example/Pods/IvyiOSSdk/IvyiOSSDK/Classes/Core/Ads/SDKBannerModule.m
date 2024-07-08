//
//  SDKBaseBannerAdapter.m
//  Pods
//
//  Created by 余冰星 on 2017/9/19.
//
//

#import "SDKBannerModule.h"
#import "SDKHelper.h"
#import <objc/message.h>
#import <IvyiOSSdk/SDKGCDTimer.h>
NSString * const SDK_BANNER_REFRESH_TIMER = @"SDK_BANNER_REFRESH_TIMER";
@implementation SDKBannerModule
{
@private
    SDK_BANNER_POS _bannerPos;
    SDKBaseBanner *_banner;
    CGRect _bannerFrame;
    int _refreshSeconds;
    bool _autoReload;
}
static bool SHOW_FLAG;

-(instancetype)init
{
    _classSuffix = @"Banner";
    _adType = SDK_ADTYPE_BANNER;
    return [super init];
}

-(void)onAdCreated:(SDKBaseAd *)ad
{
    if (_config) {
        NSNumber *tmp = (NSNumber *)[_config objectForKey:@"reloadInterval"];
        int interval = tmp ? tmp.intValue : 120;
        _refreshSeconds = interval;
        ((SDKBaseBanner *)ad).reloadInterval = interval;
        tmp = (NSNumber *)[_config objectForKey:@"autoReload"];
        _autoReload = tmp.boolValue;
    }
}

-(CGRect)getBannerFrameByPos:(UIView *)adView adPos:(SDK_BANNER_POS)pos
{
    CGRect frame = CGRectZero;
    if(adView) {
        CGFloat sw = Screen_width;
        CGFloat sh = Screen_height;
        CGFloat x = 0;
        CGFloat y = 0;
        CGFloat w = [adView frame].size.width;
        CGFloat h = [adView frame].size.height;
        w = sw < w ? sw : w;
        y = sh - h;
        switch (pos) {
            case SDK_BANNER_POS_BOTTOM:
                x = (sw - w) / 2;
                y = sh - h;
                break;
            case SDK_BANNER_POS_LEFT_BOTTOM:
                x = 0;
                y = sh - h;
                break;
            case SDK_BANNER_POS_RIGHT_BOTTOM:
                x = sw - w;
                y = sh - h;
                break;
            case SDK_BANNER_POS_CENTER:
                x = (sw - w) / 2;
                y = (sh - h) / 2;
                break;
            case SDK_BANNER_POS_LEFT_CENTER:
                x = 0;
                y = (sh - h) / 2;
                break;
            case SDK_BANNER_POS_RIGHT_CENTER:
                x = sw - w;
                y = (sh - h) / 2;
                break;
            case SDK_BANNER_POS_TOP:
                x = (sw - w) / 2;
                y = 0;
                break;
            case SDK_BANNER_POS_RIGHT_TOP:
                x = sw - w;
                y = 0;
                break;
            default:
                x = 0;
                y = 0;
                break;
        }
        frame = CGRectMake(x, y, w, h);
    }
    return frame;
}

-(void)showBannerAtPos:(SDK_BANNER_POS)pos
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [self getBannerViewWithPos:pos show:YES];
    });
}

-(void)showBannerWithFrame:(CGRect)frame
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [self getBannerViewWithFrame:frame show:YES];
    });
}

-(nullable UIView *)getBannerView:(bool)show
{
    UIView *bannerView = [SDKBannerModule bannerView];
    SHOW_FLAG = show;
    @synchronized (bannerView) {
        [bannerView setHidden:!show];
        _banner = (SDKBaseBanner *)[self getAvailableAd];
        if(_banner && show) {
            UIView* adView = _banner.adView;
            if (adView) {
                [bannerView removeFromSuperview];
                NSArray<UIView *> *arr = bannerView.subviews;
                if (arr && arr.count > 0) {
                    for (UIView *view in arr) {
                        [view removeFromSuperview];
                    }
                }
                [bannerView setFrame:adView.frame];
                [bannerView addSubview:adView];
                [self->_banner onBannerShow:adView];
//                UIViewController* vc = [[[UIApplication sharedApplication] keyWindow] rootViewController];
                [self._rootVC.view addSubview:bannerView];
                if (_autoReload && _refreshSeconds >= 20) {
                    [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:SDK_BANNER_REFRESH_TIMER interval:_refreshSeconds queue:dispatch_get_main_queue() repeats:YES option:SDKGCDTimerOptionCancelPrevAction action:^{
                        if (self->_banner) {
                            [self->_banner adNeedReload];
                        } else {
                            [[SDKGCDTimer sharedInstance] cancelTimerWithName:SDK_BANNER_REFRESH_TIMER];
                        }
                    }];
                }
            }
        }
    }
    return bannerView;
}

-(nullable UIView *)getBannerViewWithPos:(SDK_BANNER_POS)pos show:(bool)show;
{
    _bannerPos = pos;
    UIView* bannerView = [self getBannerView:show];
    if (bannerView) {
        _bannerFrame = [self getBannerFrameByPos:bannerView adPos:pos];
        [bannerView setBackgroundColor:[UIColor greenColor]];
        [bannerView setFrame:_bannerFrame];
        [bannerView setFrame:_bannerFrame];
    }
    return bannerView;
}

-(nullable UIView *)getBannerViewWithFrame:(CGRect)frame show:(bool)show
{
    _bannerFrame = frame;
    UIView* bannerView = [self getBannerView:show];
    if (bannerView) {
        [bannerView setFrame:_bannerFrame];
        [bannerView setFrame:_bannerFrame];
    }
    return bannerView;
}

-(void)adLoaded:(NSString *)tag adType:(int)type
{
    [super adLoaded:tag adType:type];
    UIView *bannerView = nil;
    if (_bannerFrame.size.width <= 0 || _bannerFrame.size.height <= 0) {
        bannerView = [self getBannerViewWithPos:_bannerPos show:[SDKBannerModule isBannerShowing]];
    } else {
        bannerView = [self getBannerViewWithFrame:_bannerFrame show:[SDKBannerModule isBannerShowing]];
    }
}

+(void)closeBanner
{
    SHOW_FLAG = NO;
    [[SDKGCDTimer sharedInstance] cancelTimerWithName:SDK_BANNER_REFRESH_TIMER];
    dispatch_async(dispatch_get_main_queue(), ^{
        [[SDKBannerModule bannerView] setHidden:YES];
        [SDKHelper sendNotif:SDK_BANNER_CLOSE_NOTIF];
    });
}

+(nonnull UIView *)bannerView
{
    static UIView *_bannerView;
    if (!_bannerView) {
        _bannerView = [[UIView alloc] init];
        [_bannerView setContentMode:UIViewContentModeScaleToFill];
    }
    return _bannerView;
}

+(BOOL)isBannerShowing
{
    return SHOW_FLAG;
}
@end

