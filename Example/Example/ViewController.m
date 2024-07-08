//
//  ViewController.m
//  Example
//
//  Created by ivy on 2024/7/1.
//

#import "ViewController.h"
#import <IvyiOSSdk/SDKFacade.h>

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    UIViewController* vc = [[[UIApplication sharedApplication] keyWindow] rootViewController];
    [[SDKFacade sharedInstance] onCreate];
    [[SDKFacade sharedInstance] setAdDelegate:self];
}

- (IBAction)hasFull:(id)sender {
    BOOL available = [[SDKFacade sharedInstance] isInterstitialAvailable:@"default"];
    [[SDKFacade sharedInstance] toast:[NSString stringWithFormat:@"isInterstitialAvailable::%d", available] isLongTime:FALSE];
}

- (IBAction)showFull:(id)sender {
    [[SDKFacade sharedInstance] showInterstitialAd:@"default"];
}

- (IBAction)hasRewarded:(id)sender {
    BOOL available = [[SDKFacade sharedInstance] isVideoAvailable:@"default"];
    [[SDKFacade sharedInstance] toast:[NSString stringWithFormat:@"isVideoAvailable::%d", available] isLongTime:FALSE];
}

- (IBAction)showRewarded:(id)sender {
    [[SDKFacade sharedInstance] showRewardVideoWithTag:@"default" rewardId:1];
}

- (IBAction)showBanner:(id)sender {
//        BOOL available = [[SDKFacade sharedInstance] isBannerAvailable:@"default"];
//        [[SDKFacade sharedInstance] toast:[NSString stringWithFormat:@"isBannerAvailable::%d", available] isLongTime:FALSE];
    [[SDKFacade sharedInstance] showBanner:SDK_BANNER_POS_BOTTOM];
}

- (IBAction)closeBanner:(id)sender {
    [[SDKFacade sharedInstance] closeBanner];
}


-(void)adLoaded:(NSString *)tag adType:(int)adType
{
    NSLog(@"[sdk]::[adLoaded]::tag::%@; adType::%d", tag, adType);
}

- (void)adFailed:(NSString *)tag adType:(int)adType forError:(NSError *)error
{
    NSLog(@"[sdk]::[adFailed]::tag::%@; adType::%d; forError::%@", tag, adType, error);
}

- (void)adDidShown:(NSString *)tag adType:(int)adType
{
    NSLog(@"[sdk]::[adDidShown]::tag::%@; adType::%d", tag, adType);
}

- (void)adShowFailed:(NSString *)tag adType:(int)adType
{
    NSLog(@"[sdk]::[v]::tag::%@; adType::%d", tag, adType);
}

- (void)adDidClose:(NSString *)tag adType:(int)adType
{
    NSLog(@"[sdk]::[adDidClose]::tag::%@; adType::%d", tag, adType);
}

- (void)adReward:(NSString *)tag rewardId:(int)rewardId
{
    NSLog(@"[sdk]::[adReward]::tag::%@; rewardId::%d", tag, rewardId);
}

- (void)adPaid:(NSString *)dict
{
    NSLog(@"[sdk]::[adPaid]::dict::%@", dict);
}

@end
