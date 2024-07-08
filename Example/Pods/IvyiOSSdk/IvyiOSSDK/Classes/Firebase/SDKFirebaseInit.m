////
////  SDKFirebaseInit.m
////  Bolts
////
////  Created by 余冰星 on 2017/10/24.
////
//
//#import "SDKFirebaseInit.h"
//#import <IvyiOSSdk/SDKCache.h>
//#import <IvyiOSSdk/SDKFacade.h>
//@implementation SDKFirebaseInit
//-(instancetype)init
//{
//    self = [super init];
//    [FIRApp configure];
//    return self;
//}
//-(void)doInit:(NSDictionary *)data onComplete:(nullable dispatch_block_t)onComplete
//{
////    @try {
////        DLog(@"[adlog] [init] [firebase]");
////        __weak FIRRemoteConfig *remoteConfig = [FIRRemoteConfig remoteConfig];
////        FIRRemoteConfigSettings *remoteConfigSettings = [[FIRRemoteConfigSettings alloc] init];
////        remoteConfigSettings.minimumFetchInterval = 0;
////        remoteConfig.configSettings = remoteConfigSettings;
////        [remoteConfig fetchWithExpirationDuration:900 completionHandler:^(FIRRemoteConfigFetchStatus status, NSError * _Nullable error) {
////            __strong FIRRemoteConfig *_remoteConfig = remoteConfig;
////            if (error) {
////                DLog(@"[adlog] firebase: %@", error.localizedDescription);
////            } else {
////                DLog(@"[adlog] remote config fetch success!");
////                if (status == FIRRemoteConfigFetchStatusSuccess) {
////                    [[FIRRemoteConfig remoteConfig] fetchAndActivateWithCompletionHandler:^(FIRRemoteConfigFetchAndActivateStatus status, NSError * _Nullable error) {
////#if DEBUG
////                        NSArray * keys = [_remoteConfig allKeysFromSource:FIRRemoteConfigSourceRemote];
////                        for (id key in keys) {
////                            NSString* value = [_remoteConfig configValueForKey:key].stringValue;
////                            DLog(@"RemoteConfig %@ >>> %@", key, value);
////                        }
////#endif
////                        if (onComplete) {
////                            onComplete();
////                        }
////                    }];
////                }
////            }
////        }];
////    } @catch (NSException *exception) {
////        DLog(@"[adlog] firebase: %@", exception.description);
////    } @finally {
////    }
//}
//
//-(void)setDefaults:(NSDictionary *)data
//{
////    if([data isKindOfClass:[NSDictionary class]]) {
////        [[FIRRemoteConfig remoteConfig] setDefaults:data];
////    }
//}
//
//-(int)getRemoteConfigIntValue:(nonnull NSString *)key
//{
//    return -1;//[[FIRRemoteConfig remoteConfig] configValueForKey:key].numberValue.intValue;
//}
//
//-(long)getRemoteConfigLongValue:(nonnull NSString *)key
//{
//    return [[FIRRemoteConfig remoteConfig] configValueForKey:key].numberValue.longValue;
//}
//
//-(double)getRemoteConfigDoubleValue:(nonnull NSString *)key
//{
//    return [[FIRRemoteConfig remoteConfig] configValueForKey:key].numberValue.doubleValue;
//}
//
//-(BOOL)getRemoteConfigBoolValue:(nonnull NSString *)key
//{
//    return [[FIRRemoteConfig remoteConfig] configValueForKey:key].boolValue;
//}
//
//-(nonnull NSString *)getRemoteConfigStringValue:(nonnull NSString *)key
//{
//    return [[FIRRemoteConfig remoteConfig] configValueForKey:key].stringValue;
//}
//
//-(void)setUserPropertyString:(NSString *)value forName:(NSString *)key
//{
//    [FIRAnalytics setUserPropertyString:value forName:key];
//}
//
//- (nonnull NSSet *)remoteConfigKeysWithPrefix:(nullable NSString *)prefix {
//    return [[FIRRemoteConfig remoteConfig] keysWithPrefix:prefix];
//}
//
//#ifdef FIREBASE_PLUS
//
//#endif
//@end
