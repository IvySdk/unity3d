//
//  SDKAnalyse.m
//  Pods
//
//  Created by 余冰星 on 2017/8/8.
//
//

#import "SDKAnalyse.h"
@implementation SDKAnalyse
-(instancetype)init
{
    NSAssert(![self isMemberOfClass:[SDKAnalyse class]], @"SDKAnalyse is an abstract class, you should not instantiate it directly.");
    return [super init];
}
@synthesize platform = _platform;
-(void)setup:(NSDictionary *)conf{}
-(void)logEvent:(NSString *)eventId{}
-(void)logPageEnd:(NSString *)pageName{}
-(void)logEvent:(NSString *)eventId action:(NSString *)action label:(NSString *)label value:(long)value{}
-(void)logEvent:(NSString *)eventId action:(nullable NSString *)action{}
-(void)logEvent:(NSString *)eventId withData:(NSDictionary<NSString *,id> *)data{}
-(void)logError:(int)errorcode errorDomain:(NSString *)errorDomain reason:(NSString *)reason description:(NSString *)desc suggest:(NSString *)sugges{}
-(void)logPlayerLevel:(int)levelId{}
-(void)logPageStart:(NSString *)pageName{}
-(void)logEvent:(NSString *)eventId valueToSum:(double)value parameters:(nonnull NSDictionary *)parameters{}
-(void)logStartLevel:(NSString *)level{}
-(void)logFailLevel:(NSString *)level{}
-(void)logFinishLevel:(NSString *)level{}
-(void)logFinishAchievement:(NSString *)achievement{}
-(void)logFinishTutorial:(NSString *)tutorial{}
-(void)logRate:(CGFloat)star{}
-(void)logAdClick:(NSString *)eventName params:(nonnull NSDictionary *)params{}
-(void)logAdImpression:(NSString *)eventName params:(nonnull NSDictionary *)params{}
-(void)logStartPay:(NSDictionary *)data{}
-(void)logSubscribe:(NSDictionary *)data{}
-(void)logPay:(NSString *)payId price:(NSNumber *)price name:(NSString *)itemName number:(int)number currency:(NSString *)currency{}
-(void)logBuy:(NSString *)itemName itemType:(nonnull NSString *)itemType count:(int)count price:(double)price{}
-(void)logUse:(NSString *)itemName number:(int)number price:(double)price{}
-(void)logBonus:(NSString *)itemName number:(int)number price:(double)price trigger:(int)trigger{}
-(void)logRegister:(nonnull NSString*) provider{}
-(void)trackScreen:(nonnull NSString*) screenClass screenName:(nonnull NSString*)screenName{}
@end
