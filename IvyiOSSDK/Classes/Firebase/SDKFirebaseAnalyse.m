//
//  SDKFirebaseAdapter.m
//  IvyiOSSdk
//
//  Created by 余冰星 on 2017/6/6.
//  Copyright © 2017年 yubingxing. All rights reserved.
//

#import "SDKFirebaseAnalyse.h"
#import <IvyiOSSdk/SDKTimer.h>
#import <IvyiOSSdk/SDKConstants.h>
#import <IvyiOSSdk/SDKCache.h>
// #import <Crashlytics/Crashlytics.h>
//#import "SDKFirebaseInit.h"
#import <Firebase/Firebase.h>

@implementation SDKFirebaseAnalyse

-(void)setup:(NSDictionary *)conf
{
    _platform = SDK_ANALYSE_FIREBASE;
    id _id = [[SDKCache cache] objectForKey:SDK_FIREBASE_USERID];
    if (!_id) {
        dispatch_queue_t fetch_thread = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_BACKGROUND, 0ul);
        dispatch_async(fetch_thread, ^{
            [[SDKCache cache] setObject:[FIRAnalytics appInstanceID] forKey:SDK_FIREBASE_USERID];
        });
    }
    DLog(@"[analyse] init firebase success");
}

-(void)logError:(int)errorcode errorDomain:(NSString *)errorDomain reason:(NSString *)reason description:(NSString *)desc suggest:(NSString *)suggest
{
    NSDictionary *userInfo = @{
        NSLocalizedDescriptionKey: NSLocalizedString(desc, nil),
        NSLocalizedFailureReasonErrorKey: NSLocalizedString(reason, nil),
        NSLocalizedRecoverySuggestionErrorKey: NSLocalizedString(suggest, nil)
    };

    // NSError *error = [NSError errorWithDomain:errorDomain code:errorcode userInfo:userInfo];
    // [CrashlyticsKit recordError:error];
}

-(void)logEvent:(NSString *)eventId
{
    [FIRAnalytics logEventWithName:eventId parameters:nil];
}

- (void)logEvent:(NSString *)eventId action:(NSString *)action label:(NSString *)label value:(long)value
{
    [FIRAnalytics logEventWithName:eventId parameters:@{@"action": action ? action : @"null", @"label":label ? label : @"null", @"value":@(value)}];
}

-(void)logEvent:(nonnull NSString *)eventId action:(nullable NSString *)action
{
    [FIRAnalytics logEventWithName:eventId parameters:@{@"action": action}];
}

-(void)logEvent:(NSString *)eventId withData:(NSDictionary *)data
{
    [FIRAnalytics logEventWithName:eventId parameters:data];
}

-(void)logEvent:(NSString *)eventId valueToSum:(double)value parameters:(NSDictionary *)parameters
{
    [FIRAnalytics logEventWithName:eventId parameters:parameters];
}

-(void)logPlayerLevel:(int)levelId
{
    [self logEvent:kFIREventLevelUp withData:@{kFIRParameterLevel: @(levelId)}];
}

-(void)logPageStart:(nonnull NSString *)pageName
{
    [self logEvent:@"pageStart" withData:@{@"page": pageName}];
}

-(void)logPageEnd:(nonnull NSString *)pageName
{
    [self logEvent:@"pageEnd" withData:@{@"page": pageName}];
}

-(void)logStartLevel:(nonnull NSString *)level
{
    [self logEvent:kFIREventLevelStart withData:@{kFIRParameterLevelName: level}];
}

-(void)logFailLevel:(nonnull NSString *)level
{
    [self logEvent:@"level_fail" withData:@{kFIRParameterLevelName: level, kFIRParameterSuccess: @(0)}];
}

-(void)logFinishLevel:(nonnull NSString *)level
{
    [self logEvent:kFIREventLevelEnd withData:@{kFIRParameterLevelName: level, kFIRParameterSuccess: @(1)}];
}

- (void)logFinishTutorial:(NSString *)tutorial
{
    NSDictionary *params = @{kFIRParameterLevelName : tutorial};
    [FIRAnalytics logEventWithName:kFIREventTutorialComplete parameters:params];
}

-(void)logFinishAchievement:(NSString *)achievement
{
    NSDictionary *params = @{kFIRParameterAchievementID : achievement};
    [FIRAnalytics logEventWithName:kFIREventUnlockAchievement parameters:params];
}

-(void)logRate:(CGFloat)star
{
    NSDictionary *params = @{@"rate" : @(5)};
    [FIRAnalytics logEventWithName:@"rate" parameters:params];
}

-(void)logAdImpression:(NSString *)eventName params:(NSDictionary *)params
{
    [FIRAnalytics logEventWithName:eventName parameters:params];
}

-(void)logAdClick:(NSString *)eventName params:(NSDictionary *)params
{
    [FIRAnalytics logEventWithName:eventName parameters:params];
}

-(void)logStartPay:(NSDictionary *)data
{
    NSDictionary *params = @{
                             kFIRParameterItemID : [data objectForKey:@"id"],
                             kFIRParameterPrice : [data objectForKey:@"price"],
                             kFIRParameterCurrency : [data objectForKey:@"currency"]
                             };
    [FIRAnalytics logEventWithName:kFIREventBeginCheckout parameters:params];
}

-(void)logSubscribe:(NSDictionary *)data
{
    NSDictionary *params = @{
                             kFIRParameterItemID : [data objectForKey:@"id"],
                             kFIRParameterTransactionID : [data objectForKey:@"orderId"],
                             kFIRParameterPrice : [data objectForKey:@"price"],
                             kFIRParameterCurrency: [data objectForKey:@"currency"]};
    [FIRAnalytics logEventWithName:@"subscribe" parameters:params];
}

-(void)logPay:(NSString *)payId price:(NSNumber *)price name:(NSString *)itemName number:(int)number currency:(NSString *)currency
{
    NSDictionary *params = @{
                             kFIRParameterItemID : payId,
                             kFIRParameterPrice : price,
                             kFIRParameterValue : price,
                             kFIRParameterItemName : itemName,
                             kFIRParameterCurrency : currency
                             };
    [FIRAnalytics logEventWithName:kFIREventPurchase parameters:params];
}

-(void)logBuy:(NSString *)itemName itemType:(NSString *)itemType count:(int)count price:(double)price
{
    [FIRAnalytics logEventWithName:@"buy" parameters:@{@"itemName": itemName, @"itemType": itemType, @"number": [@(count) stringValue],@"price": [@(price) stringValue]}];
}

-(void)logUse:(NSString *)itemName number:(int)number price:(double)price
{
    [FIRAnalytics logEventWithName:@"use" parameters:@{@"itemName": itemName, @"number": [@(number) stringValue],@"price": [@(price) stringValue]}];
}

-(void)logBonus:(NSString *)itemName number:(int)number price:(double)price trigger:(int)trigger
{
    [FIRAnalytics logEventWithName:@"bonus" parameters:@{@"itemName": itemName, @"number": [@(number) stringValue],@"price": [@(price) stringValue]}];
}

-(void)trackScreen:(NSString *)screenClass screenName:(NSString *)screenName
{
    [FIRAnalytics logEventWithName:kFIREventScreenView
                      parameters:@{kFIRParameterScreenClass: screenClass,
                                   kFIRParameterScreenName: screenName}];
}
@end
