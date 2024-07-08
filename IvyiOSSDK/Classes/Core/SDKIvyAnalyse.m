////
////  SDKGoogleAnalyse.m
////  Pods
////
////  Created by 余冰星 on 2017/8/8.
////
////
//
//#import "SDKIvyAnalyse.h"
//#import <IvyiOSSdk/SDKJSONHelper.h>
//#import <IvyiOSSdk/SDKConstants.h>
//#import <IvyiOSSdk/SDKHelper.h>
//#import <IvyiOSSdk/SDKCache.h>
//#import <IvyiOSSdk/SDKDebug.h>
//#import <IvyiOSSdk/SDKNetworkHelper.h>
//#import <IvyiOSSdk/SDKFacade.h>
//#import <CoreTelephony/CTCarrier.h>
//#import <CoreTelephony/CTTelephonyNetworkInfo.h>
//NSString * const SDK_WAIT_FOR_SEND_EVENTS = @"SDK_WAIT_FOR_SEND_EVENTS";
//@implementation SDKIvyAnalyse
//{
//    @private
//    NSMutableDictionary<NSString *, id> *_ourEventParams;
//    NSMutableArray<NSString *> *_waitForSendEvents;
//    NSString *_sendEvent;
//    NSMutableDictionary *_sendEventData;
//    int _failCount;
//}
//
//@synthesize parfkaUrl = _parfkaUrl;
//-(void)setup:(nonnull NSDictionary *)conf
//{
//    _platform = SDK_ANALYSE_IVY;
//    _waitForSendEvents = (NSMutableArray *)[[SDKCache cache] objectForKey:SDK_WAIT_FOR_SEND_EVENTS];
//    if (!_waitForSendEvents) {
//        _waitForSendEvents = [[NSMutableArray alloc] init];
//    }
//    _ourEventParams = [[NSMutableDictionary alloc] init];
//    CTTelephonyNetworkInfo *info = [[CTTelephonyNetworkInfo alloc] init];
//    CTCarrier *carrier = [info subscriberCellularProvider];
//    NSString *mcc = [carrier mobileCountryCode]; // 国家码 如：460
//    NSString *mnc = [carrier mobileNetworkCode]; // 网络码 如：01
//    mcc = mcc ? mcc : @"0";
//    mnc = mnc ? mnc : @"0";
//    NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
//    NSString *appid = [(NSNumber *)[conf objectForKey:@"appid"] stringValue];
//    NSString *roleId = [SDKFacade sharedInstance].userId;
//    [_ourEventParams setObject:mcc forKey:@"mcc"];
//    [_ourEventParams setObject:mnc forKey:@"mnc"];
//    [_ourEventParams setObject:appid forKey:@"appid"];
//    [_ourEventParams setObject:[infoDictionary objectForKey:(NSString *)kCFBundleVersionKey] forKey:@"app_build_version"];
//    [_ourEventParams setObject:[infoDictionary objectForKey:(NSString *)kCFBundleIdentifierKey] forKey:@"package_name"];
//    [_ourEventParams setObject:[infoDictionary objectForKey:@"CFBundleShortVersionString"] forKey:@"app_version"];
//    [_ourEventParams setObject:[SDKHelper getCountryCode] forKey:@"country"];
//    [_ourEventParams setObject:[SDKHelper getLangcode] forKey:@"language"];
//    [_ourEventParams setObject:(roleId ? roleId : [[[UIDevice currentDevice] identifierForVendor] UUIDString]) forKey:@"roleId"];
//    [_ourEventParams setObject:[[UIDevice currentDevice] systemName] forKey:@"os_name"];
//    [_ourEventParams setObject:[[UIDevice currentDevice] systemVersion] forKey:@"os_version"];
//    [_ourEventParams setObject:[SDKDebug deviceModel] forKey:@"device_name"];
//    [_ourEventParams setObject:@"ios" forKey:@"os_name"];
//    [_ourEventParams setObject:(isPad ? @"ipad" : @"iphone") forKey:@"device_type"];
//    [_ourEventParams setObject:[SDKHelper getIDFAString] forKey:@"gps_adid"];
//    [_ourEventParams setObject:[SDKHelper getIDFVString] forKey:@"android_uuid"];
//    NSDate *it = [SDKHelper getInstallTime];
//    [_ourEventParams setObject:it.description forKey:@"installed_at"];
//    [_ourEventParams setObject:[NSNumber numberWithLong:it.timeIntervalSince1970] forKey:@"it"];
//    DLog(@"[analyse] init ivy success");
//}
//
//-(void)_startSend
//{
//    if (!_sendEvent && _waitForSendEvents.count > 0) {
//        NSString *key = [_waitForSendEvents firstObject];
//        [_waitForSendEvents removeObject:key];
//        _sendEventData = (NSMutableDictionary *)[[SDKCache cache] objectForKey:key];
//        if (_sendEventData) {
//            _sendEvent = [_sendEventData objectForKey:@"event_token"];
//            [[SDKCache cache] removeObjectForKey:key];
//            [_sendEventData setObject:[NSNumber numberWithLong:[SDKHelper currentTime]] forKey:@"st"];
//            __weak typeof(self) _self = self;
//            [[SDKNetworkHelper sharedHelper] POST:self->_parfkaUrl parameters:_sendEventData jsonRequest:true jsonResponse:true success:^(id  _Nullable responseObject) {
//                __strong typeof(_self) self = _self;
//                self->_sendEvent = nil;
//                self->_sendEventData = nil;
//                self->_failCount = 0;
//                [self _startSend];
//            } failure:^(NSError * _Nullable error) {
//                NSString *key = [NSString stringWithFormat:@"%@:%d", self->_sendEvent, arc4random()];
//                __strong typeof(_self) self = _self;
//                [self->_waitForSendEvents addObject:key];
//                [[SDKCache cache] setObject:self->_sendEventData forKey:key];
//                [[SDKCache cache] setObject:self->_waitForSendEvents forKey:SDK_WAIT_FOR_SEND_EVENTS];
//                self->_sendEvent = nil;
//                self->_sendEventData = nil;
//                if (self->_failCount < 3) {
//                    self->_failCount = self->_failCount + 1;
//                    [self _startSend];
//                }
//            }];
//        }
//    }
//}
//
//-(void)logEvent2Our:(nonnull NSString *)eventId withData:(nullable NSDictionary<NSString *, id> *) data
//{
//    if (_waitForSendEvents) {
//        NSMutableDictionary *params = [NSMutableDictionary dictionaryWithDictionary:_ourEventParams];
//        [params setObject:[NSNumber numberWithLong:[SDKHelper currentTime]] forKey:@"created_at"];
//        [params setObject:eventId forKey:@"event_token"];
//        if ([SDKFacade sharedInstance].userId) {
//            [params setObject:[SDKFacade sharedInstance].userId forKey:@"roleId"];
//        }
//        NSString *key = [NSString stringWithFormat:@"%@:%d", eventId, arc4random()];
//        if (data && data.count > 0) {
//            [params addEntriesFromDictionary:data];
//        }
//        [_waitForSendEvents addObject:key];
//        [[SDKCache cache] setObject:params forKey:key];
//        [[SDKCache cache] setObject:_waitForSendEvents forKey:SDK_WAIT_FOR_SEND_EVENTS];
//        [self _startSend];
//    }
//}
//-(void)logEvent:(nonnull NSString *)eventId
//{
//    [self logEvent:eventId withData:@{}];
//}
//- (void)logEvent:(NSString *)eventId action:(NSString *)action label:(NSString *)label value:(long)value
//{
//    [self logEvent:eventId withData:@{@"action": action, @"label": label, @"value":@(value)}];
//}
//-(void)logEvent:(nonnull NSString *)eventId action:(nullable NSString *)action
//{
//    [self logEvent:eventId withData:@{@"action": action}];
//}
//-(void)logEvent:(NSString *)eventId withData:(NSDictionary<NSString *,id> *)data
//{
//    [self logEvent2Our:eventId withData:data];
//}
//-(void)logEvent:(NSString *)eventId valueToSum:(double)value parameters:(nonnull NSDictionary *)parameters
//{
//    [self logEvent:eventId withData:parameters];
//}
//-(void)logPlayerLevel:(int)levelId
//{
//    [self logEvent:@"playerLevel" withData:@{@"level": @(levelId)}];
//}
//
//-(void)logPageStart:(nonnull NSString *)pageName
//{
//    [self logEvent:@"pageStart" withData:@{@"page": pageName}];
//}
//-(void)logPageEnd:(nonnull NSString *)pageName
//{
//    [self logEvent:@"pageEnd" withData:@{@"page": pageName}];
//}
//-(void)logStartLevel:(nonnull NSString *)level
//{
//    [self logEvent:@"startLevel" withData:@{@"level": level}];
//}
//
//-(void)logFailLevel:(nonnull NSString *)level
//{
//    [self logEvent:@"failLevel" withData:@{@"level": level}];
//}
//
//-(void)logFinishLevel:(nonnull NSString *)level
//{
//    [self logEvent:@"finishLevel" withData:@{@"level": level}];
//}
//
//-(void)logPay:(NSString *)payId price:(NSNumber *)price name:(NSString *)itemName number:(int)number currency:(NSString *)currency
//{
//    [self logEvent2Our:@"iap_purchased" withData:@{@"revenue":price, @"itemid":itemName}];
//}
//-(void)logBuy:(NSString *)itemName itemType:(NSString *)itemType count:(int)count price:(double)price
//{
//    NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
//    [data setObject:itemName forKey:@"itemName"];
//    [data setObject:itemType forKey:@"itemType"];
//    [data setObject:[NSNumber numberWithDouble:price] forKey:@"price"];
//    [self logEvent:@"buy" valueToSum:count parameters:data];
//}
//-(void)logUse:(nonnull NSString *)itemName number:(int)number price:(double)price
//{
//    NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
//    if(itemName)
//        [data setObject:itemName forKey:@"itemName"];
//    [data setObject:[NSNumber numberWithDouble:price] forKey:@"price"];
//    [self logEvent:@"use" valueToSum:number parameters:data];
//}
//-(void)logBonus:(nonnull NSString *)itemName number:(int)number price:(double)price trigger:(int)trigger
//{
//    NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
//    if(itemName)
//        [data setObject:itemName forKey:@"itemName"];
//    [data setObject:[NSNumber numberWithDouble:price] forKey:@"price"];
//    [self logEvent:@"bonus" valueToSum:number parameters:data];
//}
//
//-(void)logAdClick:(NSString *)eventName params:(NSDictionary *)params
//{
//    [self logEvent2Our:eventName withData:params];
//}
//
//-(void)logAdImpression:(NSString *)eventName params:(NSDictionary *)params
//{
//    [self logEvent2Our:eventName withData:params];
//}
//
//@end
