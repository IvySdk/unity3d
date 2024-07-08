////
////  SDKAppsflyerInit.m
////  Bolts
////
////  Created by 余冰星 on 2017/10/24.
////
//
//#import "SDKAppsflyerInit.h"
//#import <IvyiOSSdk/SDKCache.h>
//#import <IvyiOSSdk/SDKFacade.h>
//@implementation SDKAppsflyerInit
//-(void)doInit:(NSDictionary *)data onComplete:(nullable dispatch_block_t)onComplete
//{
//    @try {
//        DLog(@"%@", @"[init] init Appsflyer");
//        NSString *key = [data valueForKey:@"key"];
//        if(key) {
//            NSString *appid = [data valueForKey:@"appid"];
//            [AppsFlyerLib shared].appsFlyerDevKey = key;
//            [AppsFlyerLib shared].appleAppID = appid;
//            [AppsFlyerLib shared].delegate = self;
//#if DEBUG
//            [AppsFlyerLib shared].isDebug = true;
//            [AppsFlyerLib shared].useReceiptValidationSandbox = true;
//            [AppsFlyerLib shared].useUninstallSandbox = NO;
//#endif
//            DLog(@"[init] Appsflyer init success, id = %@", key);
//            if (onComplete) {
//                onComplete();
//            }
//        } else {
//            DLog(@"[init] Appsflyer init failure, please see the default.json analyse config.");
//        }
//    } @catch (NSException *exception) {
//        DLog(@"[init] Appsflyer: %@", exception.description);
//    } @finally {
//    }
//}
//#pragma mark -
//#pragma mark delegate
//- (void)onConversionDataReceived:(NSDictionary *)installData
//{
//    id status = [installData objectForKey:@"af_status"];
//    if([status isEqualToString:@"Non-organic"]) {
//        id sourceID = [installData objectForKey:@"media_source"];
//        id campaign = [installData objectForKey:@"campaign"];
//        DLog(@"This is a none organic install. Media source: %@  Campaign: %@",sourceID,campaign);
//    } else if([status isEqualToString:@"Organic"]) {
//        DLog(@"This is an organic install.");
//    }
//}
//
//- (void)onConversionDataRequestFailure:(NSError *)error
//{
//    DLog(@"%@",error);
//}
//
//- (void)onAppOpenAttribution:(NSDictionary *)attributionData
//{
//}
//
//- (void)onAppOpenAttributionFailure:(NSError *)error
//{
//}
//
//- (void)onConversionDataFail:(nonnull NSError *)error {
//}
//
//- (void)onConversionDataSuccess:(nonnull NSDictionary *)conversionInfo {
//}
//@end
