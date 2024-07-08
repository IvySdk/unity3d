//
//  SDKOurInterstitial.m
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//

#import "SDKOurInterstitial.h"
#import <IvyiOSSdk/UIKit+SDKNetworking.h>
#import <IvyiOSSdk/SDKTimer.h>
#import <IvyiOSSdk/SDKFacade.h>
#import "SDKInterstitialViewController.h"
int const INTERSTITIAL_CHANGE_INTERVAL = 15;
@implementation SDKOurInterstitial
{
@private
    SDKTimer *_interstitialTimer;
}
#pragma mark ================== Interstitial AD ===================
-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    _name = nil;
    _loadIntervalSec = 0;
    return self;
}
-(BOOL)loadAd:(UIViewController *)vc
{
    [super loadAd:vc];
    NSDictionary *app = [[SDKFacade sharedInstance] pickAvailableApp:SDK_ADTYPE_INTERTITIAL];
    if(app) {
        [self adLoaded];
    } else {
        [self adFailed:nil];
    }
    return true;
}

-(void)showAd:(UIViewController *)vc
{
    [super showAd:vc];
    [SDKHelper registerNotif:@"SDK_INTERSTITIAL_CLOSE" observer:self selector:@selector(adDidClose)];
    NSDictionary *app = [[SDKFacade sharedInstance] pickAvailableApp:SDK_ADTYPE_INTERTITIAL];
    if(app) {
        __weak SDKInterstitialViewController *inter = [SDKInterstitialViewController fetchInstance];
        inter.owner = self;
        [inter show:vc data:app];
        [self adDidShown];
        if(inter.isShowing) {
            if(_interstitialTimer) {
                [_interstitialTimer stop];
            }
            _interstitialTimer = [SDKTimer startTimer:0 interval:INTERSTITIAL_CHANGE_INTERVAL onComplete:nil onUpdate:^(void) {
                if(inter && inter.isShowing) {
                    [inter show:vc data:[[SDKFacade sharedInstance] pickAvailableApp:SDK_ADTYPE_INTERTITIAL]];
                } else {
                    [self adDidClose];
                }
            }];
        }
    } else {
        [self adShowFailed];
    }
}

-(void)adDidClose
{
    [SDKHelper removeNotif:@"SDK_INTERSTITIAL_CLOSE" observer:self];
    if(_interstitialTimer) {
        [_interstitialTimer stop];
        _interstitialTimer = nil;
    }
    [super adDidClose];
}
@end
