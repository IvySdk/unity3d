//
//  SDKAdmobInit.m
//  BGFMDB
//
//  Created by 余冰星 on 2020/3/24.
//

#import "SDKAdmobInit.h"
#import <IvyiOSSdk/SDKDefine.h>
#import <GoogleMobileAds/GoogleMobileAds.h>
@implementation SDKAdmobInit
-(void)doInit:(NSDictionary *)data onComplete:(nullable dispatch_block_t)onComplete
{
    NSDictionary *info = [[NSBundle mainBundle] infoDictionary];
    NSString *appId = [info objectForKey:@"GADApplicationIdentifier"];
//    [[GADMobileAds sharedInstance] startWithCompletionHandler:^(GADInitializationStatus * _Nonnull status) {
//        if (onComplete) {
//            onComplete();
//        }
//    }];
    DLog(@"[adlog] [init] [GoogleAds] [%@]", appId);
}
@end
