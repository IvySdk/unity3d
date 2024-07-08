//
//  SDKBaseBanner.m
//  BGFMDB
//
//  Created by 余冰星 on 2020/3/2.
//

#import "SDKBaseBanner.h"
#import <IvyiOSSdk/SDKFacade.h>

@implementation SDKBaseBanner
{
    @protected
    long _reloadTime;
}
@synthesize adView = _adView;
@synthesize reloadInterval = _reloadInterval;
-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(nullable NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    _adType = SDK_ADTYPE_BANNER;
    _reloadInterval = 600;
    return self;
}

-(void)adDidShown
{
    [super adDidShown];
    _reloadTime = CACurrentMediaTime();
    [SDKHelper registerNotif:SDK_BANNER_CLOSE_NOTIF observer:self selector:@selector(close)];
    _isAvailable = YES;
}

-(void)onBannerShow:(nonnull UIView *)view
{
    [self adDidShown];
    [[SDKFacade sharedInstance] logEvent:@"banner_shown"];
}

- (void)adDidClick
{
    [super adDidClick];
    [[SDKFacade sharedInstance] logEvent:@"banner_clicked"];
}

-(void)adNeedReload
{
    long now = CACurrentMediaTime();
    if (now - _reloadTime >= _reloadInterval) {
        _reloadTime = now;
        _adView = nil;
        _isAvailable = NO;
        [super adNeedReload];
    }
}

-(void)close
{
    [SDKHelper removeNotif:SDK_BANNER_CLOSE_NOTIF observer:self];
    if (_adView) {
        if([_adView superview]) {
            [_adView removeFromSuperview];
        }
        if (_hasShown) {
            [self adDidClose];
        }
    }
}
@end
