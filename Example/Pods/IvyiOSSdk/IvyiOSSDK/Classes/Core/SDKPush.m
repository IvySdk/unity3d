//
//  SDKPush.m
//  Pods
//
//  Created by 余冰星 on 2017/9/20.
//
//

#import "SDKPush.h"
#import "SDKConstants.h"
#import "SDKCache.h"
#import "SDKHelper.h"
#import "SDKFacade.h"
@implementation SDKPush
@synthesize token = _token;
@synthesize lastSendToken = _lastSendToken;
-(instancetype)init
{
    NSAssert(![self isMemberOfClass:[SDKPush class]], @"SDKPush is an abstract class, you should not instantiate it directly.");
    return [super init];
}

-(void)setup:(NSDictionary *)conf{
    _conf = conf;
    _pushServerUrl = [conf objectForKey:@"push-server-url"];
    _lastSendToken = (NSString *)[[SDKCache cache] objectForKey:[NSString stringWithFormat:@"%@_LST", NSStringFromClass(self.class)]];
    if (!(_pushServerUrl && [_pushServerUrl containsString:@"http"]))
        _pushServerUrl = nil;
    [SDKHelper registerNotif:SDK_SNS_LOGIN_SUCCESS observer:self selector:@selector(submitTokenToServer)];
}

-(void)setToken:(NSString *)token
{
    if (token) {
        _token = token;
        if (_lastSendToken && [_lastSendToken isEqualToString:_token] && ![SDKFacade sharedInstance].isChangeVersion) {
            return;
        }
        [self submitTokenToServer];
    }
}

-(void)setLastSendToken:(NSString *)lastSendToken
{
    _lastSendToken = lastSendToken;
    [[SDKCache cache] setObject:_lastSendToken forKey:[NSString stringWithFormat:@"%@_LST", NSStringFromClass(self.class)]];
}

-(void)submitTokenToServer
{
}

-(void)subscribeToTopic:(NSString *)topic
{
}

-(void)unsubscribeFromTopic:(NSString *)topic
{
}

-(void)pushMessage:(nullable NSString *)key title:(NSString *)title content:(NSString *)content pushTime:(long)pushTime useLocalTimeZone:(BOOL)useLocalTimeZone facebookIds:(NSString *)facebookIds uuids:(NSString *)uuids topic:(NSString *)topic iosBadge:(int)iosBadge useSound:(BOOL)useSound soundName:(NSString *)soundName extraData:(NSDictionary *)data
{
}

-(void)cancelPush:(nonnull NSString *)key
{
}

//-(void)pushRepeatMessage:(NSString *)messageId title:(NSString *)title content:(NSString *)content pushTime:(long)pushTime useLocalTimeZone:(BOOL)useLocalTimeZone facebookIds:(NSString *)facebookIds uuids:(NSString *)uuids topic:(NSString *)topic iosBadge:(int)iosBadge useSound:(BOOL)useSound soundName:(NSString *)soundName extraData:(NSDictionary *)data repeatInterval:(int)repeatInterval
//{
//}
//
//-(void)cancelRepeatPush:(NSString *)messageId
//{
//}

-(void)didReceiveRemoteNotification:(NSDictionary *)userInfo
{
    [[UIApplication sharedApplication] setApplicationIconBadgeNumber:0];
    if (userInfo) {
        [[SDKCache cache] setObject:userInfo forKey:SDK_PUSH_DATA];
    }
}

-(void)didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
}

-(void)didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
}

-(void)applicationDidBecomeActive:(UIApplication *)application
{
}

-(void)applicationDidEnterBackground:(UIApplication *)application
{
}
@end
