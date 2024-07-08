//
//  SDKRemoteConfig.m
//  Bolts
//
//  Created by 余冰星 on 2021/1/27.
//
#import "SDKRemoteConfig.h"
#import <IvyiOSSdk/SDKFacade.h>
@implementation SDKRemoteConfig
{
    @private
    NSDictionary *_data;
}

-(void)setDefaults:(NSDictionary *)data
{
    if([data isKindOfClass:[NSDictionary class]]) {
        _data = data;
    }
}

-(nonnull NSSet *)remoteConfigKeysWithPrefix:(nullable NSString *)prefix
{
    NSMutableSet *keys = [[NSMutableSet alloc] init];
    if (_data) {
        NSArray *allKeys = [_data allKeys];
        if (!prefix.length) {
            keys = [NSMutableSet setWithArray:allKeys];
        } else {
            for (NSString *key in allKeys) {
                if ([key hasPrefix:prefix]) {
                    [keys addObject:key];
                }
            }
        }
    }
    return [keys copy];
}

-(int)getRemoteConfigIntValue:(nonnull NSString *)key
{
    if (_data) {
        NSNumber *num = [_data objectForKey:key];
        return num ? num.intValue : 0;
    }
    return 0;
}

-(long)getRemoteConfigLongValue:(nonnull NSString *)key
{
    if (_data) {
        NSNumber *num = [_data objectForKey:key];
        return num ? num.longValue : 0;
    }
    return 0;
}

-(double)getRemoteConfigDoubleValue:(nonnull NSString *)key
{
    if (_data) {
        NSNumber *num = [_data objectForKey:key];
        return num ? num.doubleValue : 0;
    }
    return 0;
}

-(BOOL)getRemoteConfigBoolValue:(nonnull NSString *)key
{
    if (_data) {
        NSNumber *num = [_data objectForKey:key];
        return num ? num.boolValue : 0;
    }
    return NO;
}

-(nonnull NSString *)getRemoteConfigStringValue:(nonnull NSString *)key
{
    if (_data) {
        NSString *str = [_data objectForKey:key];
        return str ? str : @"";
    }
    return @"";
}

-(void)setUserPropertyString:(NSString *)value forName:(NSString *)key
{
}
@end
