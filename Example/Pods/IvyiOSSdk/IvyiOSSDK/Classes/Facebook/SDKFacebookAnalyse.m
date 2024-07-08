//
//  SDKFacebookAdapter.m
//  Pods
//
//  Created by 余冰星 on 2017/6/13.
//
//

#import "SDKFacebookAnalyse.h"
#import <IvyiOSSdk/SDKJSONHelper.h>
#import <IvyiOSSdk/SDKConstants.h>
@import FBSDKCoreKit;
//#import <FBSDKCoreKit/FBSDKAppEvents+Internal.h>
@implementation SDKFacebookAnalyse
-(void)setup:(NSDictionary *)conf
{
    _platform = SDK_ANALYSE_FACEBOOK;
    DLog(@"[analyse] init facebook success");
}

-(void)logEvent:(NSString *)eventId
{
    
    [[FBSDKAppEvents shared] logEvent:eventId];
}

- (void)logEvent:(NSString *)eventId action:(NSString *)action label:(NSString *)label value:(long)value
{
    if (value > 0) {
        [[FBSDKAppEvents shared] logEvent:eventId valueToSum:value parameters:@{@"action": action ? action : @"null", @"label":label ? label : @"null", @"value":@(value)}];
    } else {
        [[FBSDKAppEvents shared] logEvent:eventId parameters:@{@"action": action ? action : @"null", @"label":label ? label : @"null"}];
    }
}

-(void)logEvent:(nonnull NSString *)eventId action:(nullable NSString *)action
{
    [[FBSDKAppEvents shared] logEvent:eventId parameters:@{@"action": action}];
}
    
-(void)logEvent:(NSString *)eventId withData:(NSDictionary *)data
{
    [[FBSDKAppEvents shared] logEvent:eventId parameters:data];
}

-(void)logEvent:(NSString *)eventId valueToSum:(double)value parameters:(NSDictionary *)parameters
{
    [[FBSDKAppEvents shared] logEvent:eventId valueToSum:value parameters:parameters];
}

-(void)logPlayerLevel:(int)levelId
{
    [self logEvent:@"playerLevel" withData:@{@"level": @(levelId)}];
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
    [self logEvent:@"startLevel" withData:@{@"level": level}];
}
    
-(void)logFailLevel:(nonnull NSString *)level
{
    [self logEvent:@"failLevel" withData:@{@"level": level}];
}
    
-(void)logFinishLevel:(nonnull NSString *)level
{
    NSDictionary *params = @{FBSDKAppEventParameterNameLevel : level};
    [[FBSDKAppEvents shared] logEvent:FBSDKAppEventNameAchievedLevel parameters:params];
}

- (void)logFinishTutorial:(NSString *)tutorial
{
    NSDictionary *params = @{
                             FBSDKAppEventParameterNameContent : @"[]",
                             FBSDKAppEventParameterNameContentID : tutorial,
                             FBSDKAppEventParameterNameSuccess : @(1)
                             };
    [[FBSDKAppEvents shared] logEvent:FBSDKAppEventNameCompletedTutorial parameters:params];
}

-(void)logFinishAchievement:(NSString *)achievement
{
    NSDictionary *params = @{FBSDKAppEventParameterNameDescription : achievement};
    [[FBSDKAppEvents shared] logEvent:FBSDKAppEventNameUnlockedAchievement parameters:params];
}

-(void)logRate:(CGFloat)star
{
    NSDictionary *params = @{
                             FBSDKAppEventParameterNameContentType : @"product",
                             FBSDKAppEventParameterNameContent : @"[]",
                             FBSDKAppEventParameterNameContentID : @"rate",
                             FBSDKAppEventParameterNameMaxRatingValue : @(5)
                             };
    [[FBSDKAppEvents shared] logEvent:FBSDKAppEventNameRated valueToSum:star parameters:params];
}

-(void)logAdClick:(NSString *)eventName params:(NSDictionary *)params
{
    [[FBSDKAppEvents shared] logEvent:eventName parameters:params];
}

- (void)logAdImpression:(NSString *)eventName params:(NSDictionary *)params
{
    [[FBSDKAppEvents shared] logEvent:eventName parameters:params];
}

-(void)logStartPay:(NSDictionary *)data
{
    NSDictionary *params = @{
                             FBSDKAppEventParameterNameContent : [SDKJSONHelper toJSONString:data],
                             FBSDKAppEventParameterNameContentID : [data objectForKey:@"id"],
                             FBSDKAppEventParameterNameContentType : @"product",
                             FBSDKAppEventParameterNameNumItems : @(1),
                             FBSDKAppEventParameterNamePaymentInfoAvailable : @(1),
                             FBSDKAppEventParameterNameCurrency : [data objectForKey:@"currency"]
                             };
    [[FBSDKAppEvents shared] logEvent:FBSDKAppEventNameInitiatedCheckout valueToSum:[(NSNumber *)[data objectForKey:@"price"] doubleValue]  parameters:params];
}

-(void)logSubscribe:(NSDictionary *)data
{
    NSDictionary *params = @{
                            FBSDKAppEventParameterNameOrderID : [data objectForKey:@"orderId"],
                            FBSDKAppEventParameterNameCurrency : [data objectForKey:@"currency"]
                            };
    [[FBSDKAppEvents shared] logEvent:FBSDKAppEventNameSubscribe valueToSum:[(NSNumber *)[data objectForKey:@"price"] doubleValue] parameters:params];
}

-(void)logPay:(NSString *)payId price:(NSNumber *)price name:(NSString *)itemName number:(int)number currency:(NSString *)currency
{
    NSDictionary *params = @{
                             FBSDKAppEventParameterNameContent : itemName,
                             FBSDKAppEventParameterNameContentID : payId,
                             FBSDKAppEventParameterNameContentType : @"product",
                             FBSDKAppEventParameterNameNumItems : @(number),
                             FBSDKAppEventParameterNamePaymentInfoAvailable : @(1),
                             FBSDKAppEventParameterNameCurrency : currency
                             };
    [[FBSDKAppEvents shared] logPurchase:price.doubleValue currency:currency parameters:params];
}
    
-(void)logBuy:(NSString *)itemName itemType:(NSString *)itemType count:(int)count price:(double)price
{
    [[FBSDKAppEvents shared] logEvent:@"buy" valueToSum:count parameters:@{@"itemName": itemName, @"itemType": itemType, @"price": [@(price) stringValue]}];
}
    
-(void)logUse:(NSString *)itemName number:(int)number price:(double)price
{
    [[FBSDKAppEvents shared] logEvent:@"use" valueToSum:number parameters:@{@"itemName": itemName, @"price": [@(price) stringValue]}];
}
    
-(void)logBonus:(NSString *)itemName number:(int)number price:(double)price trigger:(int)trigger
{
    [[FBSDKAppEvents shared] logEvent:@"bonus" valueToSum:number parameters:@{@"itemName": itemName, @"price": [@(price) stringValue]}];
}
@end
