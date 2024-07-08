//
//  SDKAnalyse.h
//  Pods
//
//  Created by 余冰星 on 2017/8/8.
//
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <IvyiOSSdk/SDKDefine.h>
@interface SDKAnalyse : NSObject
{
    @protected
    NSString *_platform;
}
@property (nonatomic, nullable, strong) NSString *platform;
-(void)setup:(nonnull NSDictionary *)conf;
-(void)logEvent:(nonnull NSString *)eventId;
-(void)logEvent:(nonnull NSString *)eventId action:(nullable NSString *)action label:(nullable NSString *)label value:(long)value;
-(void)logEvent:(nonnull NSString *)eventId action:(nullable NSString *)action;
-(void)logEvent:(nonnull NSString *)eventId withData:(nullable NSDictionary *)data;
-(void)logEvent:(nonnull NSString *)eventId valueToSum:(double)value parameters:(nonnull NSDictionary *)parameters;
-(void)logError:(int)errorcode errorDomain:(nonnull NSString *)errorDomain reason:(nonnull NSString *)reason  description:(nonnull NSString *)desc suggest:(nullable NSString *)suggest;
-(void)logPlayerLevel:(int)levelId;
-(void)logPageStart:(nonnull NSString *)pageName;
-(void)logPageEnd:(nonnull NSString *)pageName;
-(void)logStartLevel:(nonnull NSString *)level;
-(void)logFailLevel:(nonnull NSString *)level;
-(void)logFinishLevel:(nonnull NSString *)level;
-(void)logFinishAchievement:(nonnull NSString *)achievement;
-(void)logFinishTutorial:(nonnull NSString *)tutorial;
-(void)logRate:(CGFloat)star;
-(void)logAdClick:(nonnull NSString *)eventName params:(nonnull NSDictionary *)params;
-(void)logAdImpression:(nonnull NSString *)eventName params:(nonnull NSDictionary *)params;
-(void)logStartPay:(nonnull NSDictionary *)data;
-(void)logSubscribe:(nonnull NSDictionary *)data;
-(void)logPay:(nonnull NSString *)payId price:(nonnull NSNumber *)price name:(nonnull NSString *)itemName number:(int)number currency:(nonnull NSString *)currency;
-(void)logBuy:(nonnull NSString *)itemName itemType:(nonnull NSString *)itemType count:(int)count price:(double)price;
-(void)logUse:(nonnull NSString *)itemName number:(int)number price:(double)price;
-(void)logBonus:(nonnull NSString *)itemName number:(int)number price:(double)price trigger:(int)trigger;
-(void)logRegister:(nonnull NSString*) provider;
-(void)trackScreen:(nonnull NSString*) screenClass screenName:(nonnull NSString*)screenName;
@end
