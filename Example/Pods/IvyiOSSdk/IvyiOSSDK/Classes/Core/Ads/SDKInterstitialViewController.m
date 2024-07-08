//
//  SDKInterstitialViewViewController.m
//  Pods
//
//  Created by 余冰星 on 2017/6/15.
//
//

#import "SDKInterstitialViewController.h"
#import <IvyiOSSdk/SDKObjectPool.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/UIKit+SDKNetworking.h>
@implementation SDKInterstitialViewController
{
@private
    NSString *_appStoreId;
}
@synthesize isShowing = _isShowing;
@synthesize owner = _owner;
- (void)viewDidLoad {
    [super viewDidLoad];
    [self.imageView setUserInteractionEnabled:YES];
    self.imageView.backgroundColor = [UIColor grayColor];
    self.imageView.enableFade = YES;
    [self.imageView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onImagePressed:)]];
    [self.closeBtn setUserInteractionEnabled:YES];
    [self.closeBtn addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClosePressed:)]];
}

-(void)onImagePressed:(UITapGestureRecognizer *)gestureRecognizer
{
    [SDKFacade trackAndOpenStore:_appStoreId type:SDK_ADTYPE_INTERTITIAL];
    [_owner adDidClick];
    [self close];
}

-(void)onClosePressed:(UITapGestureRecognizer *)gestureRecognizer
{
    [self close];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(void)show:(UIViewController *)vc data:(nonnull NSDictionary *)data
{
    if(!_isShowing) {
        _isShowing = YES;
        vc.definesPresentationContext = YES;
        self.view.backgroundColor = [UIColor colorWithRed:0 green:0 blue:0 alpha:.4];
        //    self.view.backgroundColor = [UIColor clearColor];
        self.modalPresentationStyle = UIModalPresentationOverCurrentContext;
        float version = [UIDevice currentDevice].systemVersion.floatValue;
        if (version < 8.0) {
            vc.modalPresentationStyle = UIModalPresentationCurrentContext;
        }
        [vc presentViewController:self animated:NO completion:nil];
    }
    NSURL *url = [NSURL URLWithString:[data objectForKey:@"cover"]];
    _appStoreId = [data objectForKey:@"appstoreid"];
    [self.imageView setImageWithURL:url];
    [SDKFacade trackCrossPromoteImpression:_appStoreId type:SDK_ADTYPE_INTERTITIAL];
}

-(void)close
{
    [[[self class] getPool] returnObjectToPool:self];
    [self dismissViewControllerAnimated:NO completion:nil];
    [SDKHelper sendNotif:@"SDK_INTERSTITIAL_CLOSE"];
    _isShowing = NO;
}
static SDKObjectPool * pool;

+(SDKObjectPool *)getPool
{
    if(pool == nil || pool == NULL) {
        pool = [SDKObjectPool poolWithCreateBlock:^id(NSError **outError) {
            NSBundle *bundle = [NSBundle bundleForClass:self.class];
            bundle = [NSBundle bundleWithURL:[bundle URLForResource:@"IvyiOSSdk-Core" withExtension:@"bundle"]];
            return [[SDKInterstitialViewController alloc] initWithNibName:@"sdk_interstitial_portrait" bundle:bundle];
        }];
    }
    return pool;
}

+(SDKInterstitialViewController *)fetchInstance
{
    return [[[self class] getPool] objectFromPoolWithError:nil];
}
@end
