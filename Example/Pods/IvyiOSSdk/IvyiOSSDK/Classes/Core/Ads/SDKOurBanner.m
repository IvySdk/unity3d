//
//  SDKOurBanner.m
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//

#import "SDKOurBanner.h"
#import <IvyiOSSdk/UIKit+SDKNetworking.h>
#import <IvyiOSSdk/SDKTimer.h>
#import "SDKFacade.h"
int const BANNER_CHANGE_INTERVAL = 15;
@implementation SDKOurBanner
{
    @private
    SDKTimer *_timer;
    NSString *_appStoreId;
}

-(BOOL)loadAd:(UIViewController *)vc
{
    [super loadAd:vc];
    if (_adSize && [_adSize isEqualToString:@"rect"]) {
        
    } else {
        NSDictionary *app = [[SDKFacade sharedInstance] pickAvailableApp:SDK_ADTYPE_BANNER];
        if([self changeBanner:app]) {
            [self adLoaded];
        } else {
            [self adFailed:nil];
        }
    }
    return true;
}

-(UIView *)adView
{
    if(!_adView) {
        UIImageView *_bannerView = nil;
        if(isPad) {
            _bannerView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, Screen_width * 0.7f, Screen_width * 0.7f * 50 / 320)];
        } else {
            _bannerView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, Screen_width, Screen_width * 50 / 320)];
        }
        _adView = _bannerView;
        _bannerView.enableFade = YES;
        [_bannerView setUserInteractionEnabled:YES];
        [_bannerView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onBannerClick:)]];
    }
    return _adView;
}

-(void)onBannerShow:(UIView *)view
{
    [super onBannerShow:view];
    if(_adView && [_adView superview]) {
        if(_timer) {
            [_timer stop];
        }
        __weak typeof(self) weakSelf = self;
        _timer = [SDKTimer startTimer:0 interval:BANNER_CHANGE_INTERVAL onComplete:nil onUpdate:^(void) {
            NSDictionary *app = [[SDKFacade sharedInstance] pickAvailableApp:SDK_ADTYPE_BANNER];
            [weakSelf changeBanner:app];
        }];
        
        [SDKFacade trackCrossPromoteImpression:_appStoreId type:SDK_ADTYPE_BANNER];
    }
}

-(void)onBannerClick:(UITapGestureRecognizer *)gestureRecognizer
{
    [self adDidClick];
    [SDKFacade trackAndOpenStore:_appStoreId type:SDK_ADTYPE_BANNER];
}

-(BOOL)changeBanner:(NSDictionary *)app
{
    if(app) {
        NSURL *url = [NSURL URLWithString:[app objectForKey:@"banner"]];
        [(UIImageView *)self.adView setImageWithURL:url];
        _appStoreId = [app objectForKey:@"appstoreid"];
        return true;
    }
    return false;
}

-(void)close
{
    [super close];
    if(_timer) {
        [_timer stop];
    }
}
@end
