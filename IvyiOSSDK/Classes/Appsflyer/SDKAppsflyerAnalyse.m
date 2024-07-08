//
//  SDKAppsflyerAnalyse.m
//  Pods
//
//  Created by 余冰星 on 2017/8/8.
//
//

#import "SDKAppsflyerAnalyse.h"
#import <AppsFlyerLib/AppsFlyerLib.h>
#import <IvyiOSSdk/SDKJSONHelper.h>
#import <IvyiOSSdk/SDKConstants.h>
@implementation SDKAppsflyerAnalyse
#pragma mark -
#pragma mark methods
-(void)setup:(nonnull NSDictionary *)conf
{
    _platform = SDK_ANALYSE_APPSFLYER;
    NSString *key = [conf valueForKey:@"key"];
    if(key) {
        NSString *appid = [conf valueForKey:@"id"];
        AppsFlyerLib *appsflyer = [AppsFlyerLib shared];
        if (appsflyer.appsFlyerDevKey && ![key isEqualToString:appsflyer.appsFlyerDevKey]) {
            appsflyer.appsFlyerDevKey = key;
        }
        if (appid && appsflyer.appleAppID && ![appid isEqualToString:appsflyer.appleAppID]) {
            appsflyer.appleAppID = appid;
        }
#if DEBUG
        appsflyer.isDebug = true;
        appsflyer.useReceiptValidationSandbox = true;
        appsflyer.useUninstallSandbox = NO;
#endif
        DLog(@"[analyse] init appsflyer success, id = %@", key);
    } else {
        DLog(@"[analyse] appsflyer init failure, please see the default.json analyse config.");
    }
}

-(void)logPlayerLevel:(int)levelId
{
    [self logEvent:@"user_level" withData:@{AFEventParamLevel : @(levelId)}];
}
-(void)logPageStart:(nonnull NSString *)pageName
{
    [self logEvent:@"pageStart" withData:@{@"page": pageName}];
}
-(void)logPageEnd:(nonnull NSString *)pageName
{
    [self logEvent:@"pageEnd" withData:@{@"page": pageName}];
}
-(void)logEvent:(NSString *)eventId
{
    [self logEvent:eventId withData:nil];
}
-(void)logEvent:(nonnull NSString *)eventId action:(nullable NSString *)action
{
    [self logEvent:eventId withData:@{@"action": action ? action : @"null"}];
}
-(void)logEvent:(NSString *)eventId action:(NSString *)action label:(NSString *)label value:(long)value
{
    [self logEvent:eventId withData:@{@"action": action ? action : @"null", @"label":label ? label : @"null", @"value":@(value)}];
}
-(void)logEvent:(NSString *)eventId withData:(NSDictionary<NSString *,id> *)data
{
    [[AppsFlyerLib shared] logEvent:eventId withValues:data];
    DLog(@"Send AFEvent: %@", eventId);
}
-(void)logEvent:(NSString *)eventId valueToSum:(double)value parameters:(nonnull NSDictionary *)parameters
{
    NSMutableDictionary *data = [NSMutableDictionary dictionaryWithDictionary:parameters];
    [data setObject:[@(value) stringValue] forKey:@"value"];
    [self logEvent:eventId withData:data];
}
-(void)logStartLevel:(nonnull NSString *)level
{
    [self logEvent:@"startLevel" withData:@{AFEventParamLevel : level}];
}
-(void)logFailLevel:(nonnull NSString *)level
{
    [self logEvent:@"failLevel" withData:@{AFEventParamLevel : level}];
}
-(void)logFinishLevel:(nonnull NSString *)level
{
    [self logEvent:AFEventLevelAchieved withData:@{AFEventParamLevel : level}];
}
-(void)logRate:(CGFloat)star
{
    [self logEvent:AFEventRate withData:@{
        AFEventParamRatingValue:@(star),
        AFEventParamMaxRatingValue:@(5)
        }];
}
-(void)logAdClick:(NSString *)eventName params:(NSDictionary *)params
{
//    [self logEvent:AFEventAdClick withData:@{
//        AFEventParamAdRevenueAdType:[params objectForKey:@"ad_type"],
//        AFEventParamAdRevenueNetworkName:[params objectForKey:@"platform"]
//        }];
    [self logEvent:eventName withData:params];
}
-(void)logAdImpression:(NSString *)eventName params:(NSDictionary *)params
{
//    [self logEvent:AFEventAdView withData:@{
//        AFEventParamAdRevenueAdType:[params objectForKey:@"ad_type"],
//        AFEventParamAdRevenueNetworkName:[params objectForKey:@"platform"]
//        }];
    [self logEvent:eventName withData:params];
}
-(void)logPay:(NSString *)payId price:(NSNumber *)price name:(NSString *)itemName number:(int)number currency:(NSString *)currency
{
    [[AppsFlyerLib shared] logEvent:AFEventPurchase withValues:@{
                                                  AFEventParamContentId:payId,
                                                  AFEventParamContent: itemName,
                                                  AFEventParamQuantity : @(number),
                                                  AFEventParamPrice: price,
                                                  AFEventParamRevenue: price,
                                                  AFEventParamCurrency:currency
                                                  }];
}
-(void)logStartPay:(NSDictionary *)data
{
    NSDictionary *params = @{
                             AFEventParamContentId : [data objectForKey:@"id"],
                             AFEventParamPrice : [data objectForKey:@"price"],
                             AFEventParamCurrency : [data objectForKey:@"currency"]
                             };
    [[AppsFlyerLib shared] logEvent:AFEventInitiatedCheckout withValues:params];
}
-(void)logSubscribe:(NSDictionary *)data
{
    NSDictionary *params = @{
                             AFEventParamContentId : [data objectForKey:@"id"],
                             AFEventParamPrice : [data objectForKey:@"price"],
                             AFEventParamOrderId : [data objectForKey:@"orderId"],
                             AFEventParamCurrency: [data objectForKey:@"currency"]};
    [[AppsFlyerLib shared] logEvent:AFEventSubscribe withValues:params];
}
-(void)logBuy:(nonnull NSString *)itemName count:(int)count price:(double)price
{
    NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
    if(itemName)
        [data setObject:itemName forKey:@"itemName"];
    [data setObject:[NSNumber numberWithDouble:price] forKey:@"price"];
    [self logEvent:@"buy" valueToSum:count parameters:data];
}
-(void)logUse:(nonnull NSString *)itemName number:(int)number price:(double)price
{
    NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
    if(itemName)
        [data setObject:itemName forKey:@"itemName"];
    [data setObject:[NSNumber numberWithDouble:price] forKey:@"price"];
    [self logEvent:@"use" valueToSum:number parameters:data];
}
-(void)logBonus:(nonnull NSString *)itemName number:(int)number price:(double)price trigger:(int)trigger
{
    NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
    if(itemName)
        [data setObject:itemName forKey:@"itemName"];
    [data setObject:[NSNumber numberWithDouble:price] forKey:@"price"];
    [self logEvent:@"bonus" valueToSum:number parameters:data];
}

@end
