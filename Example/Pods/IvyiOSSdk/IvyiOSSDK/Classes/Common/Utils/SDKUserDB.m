//
//  SDKUserDB.m
//  IvySdkCore
//
//  Created by 余冰星 on 2017/6/5.
//  Copyright © 2017年 IVY. All rights reserved.
//
#import "SDKUserDB.h"

@implementation SDKUserDB
{
@private
    NSString *keys;
}

NSString * const KEYS_LIST = @"_K_E_Y_S_";

-(void)setString:(nullable NSString *)value forKey:(nonnull NSString *)key
{
    [self setKey:key];
    [[NSUserDefaults standardUserDefaults] setObject:value forKey:key];
}
- (NSString *)stringForKey:(nonnull NSString *)key defaultString:(nullable NSString *)defaultString
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        return [user stringForKey:key];
    } else {
        return defaultString;
    }
}
-(void)setInteger:(NSInteger)value forKey:(nonnull NSString *)key
{
    [self setKey:key];
    [[NSUserDefaults standardUserDefaults] setInteger:value forKey:key];
}
- (NSInteger)integerForKey:(nonnull NSString *)key defaultInteger:(NSInteger)defaultInteger
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        return [user integerForKey:key];
    } else {
        return defaultInteger;
    }
}
-(void)setBool:(BOOL)value forKey:(nonnull NSString *)key
{
    [self setKey:key];
    [[NSUserDefaults standardUserDefaults] setBool:value forKey:key];
}
- (BOOL)boolForKey:(nonnull NSString *)key defaultValue:(BOOL)defaultValue
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        return [user boolForKey:key];
    } else {
        return defaultValue;
    }
}
-(void)setFloat:(float)value forKey:(nonnull NSString *)key
{
    [self setKey:key];
    [[NSUserDefaults standardUserDefaults] setFloat:value forKey:key];
}
- (float)floatForKey:(nonnull NSString *)key defaultValue:(float)defaultValue
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        return [user floatForKey:key];
    } else {
        return defaultValue;
    }
}
-(void)setDouble:(double)value forKey:(nonnull NSString *)key
{
    [self setKey:key];
    [[NSUserDefaults standardUserDefaults] setDouble:value forKey:key];
}
- (double)doubleForKey:(nonnull NSString *)key defaultValue:(float)defaultValue
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        return [user doubleForKey:key];
    } else {
        return defaultValue;
    }
}
-(void)setArray:(nullable NSArray *)array forKey:(nonnull NSString *)key
{
    if(array == nil || array == NULL)return;
    [self setKey:key];
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    [user setObject:[NSMutableArray arrayWithArray:array] forKey:key];
}
-(NSArray *)arrayForKey:(nonnull NSString *)key defaultArray:(nullable NSArray *)defaultArray
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        return [user objectForKey:key];
    } else {
        return defaultArray;
    }
}
-(void)setData:(nullable NSData *)data forKey:(nonnull NSString *)key
{
    if(data == nil || data == NULL)return;
    [self setKey:key];
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    [user setObject:[NSMutableData dataWithData:data] forKey:key];
}
-(NSData *)dataForKey:(nonnull NSString *)key defaultData:(nullable NSData *)defaultData
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        return [user dataForKey:key];
    } else {
        return defaultData;
    }
}
-(void)setDictionary:(nullable NSDictionary *)dict forKey:(nonnull NSString *)key
{
    if(dict == nil || dict == NULL)return;
    [self setKey:key];
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    [user setObject:[NSMutableDictionary dictionaryWithObject:[dict allValues] forKey:[dict allKeys]] forKey:key];
}
-(NSArray *)dictionaryForKey:(nonnull NSString *)key defaultDictionary:(nullable NSArray *)defaultDictionary
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        return [user objectForKey:key];
    } else {
        return defaultDictionary;
    }
}
-(void)setObject:(nullable NSCoder *)object forKey:(nonnull NSString *)key
{
    if(object == nil || object == NULL)return;
    [self setKey:key];
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    NSData *data = [NSKeyedArchiver archivedDataWithRootObject:object];
    [user setObject:data forKey:key];
}
-(id)objectForKey:(nonnull NSString *)key defaultObject:(nullable NSCoder *)defaultObject
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    if ([self hasKey:key]) {
        NSData *data = [user objectForKey:key];
        id object = [NSKeyedUnarchiver unarchiveObjectWithData:data];
        return object;
    } else {
        return defaultObject;
    }
}
-(void)setKey:(nonnull NSString *)key
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    key = [NSString stringWithFormat:@"<%@>", key];
    if(![keys containsString:key]) {
        keys = [NSString stringWithFormat:@"%@%@", keys, key];
        [user setObject:keys forKey:KEYS_LIST];
    }
}
-(void)removeKey:(nonnull NSString *)key
{
    NSUserDefaults *user = [NSUserDefaults standardUserDefaults];
    key = [NSString stringWithFormat:@"<%@>", key];
    if([keys containsString:key]) {
        NSRange range = [keys rangeOfString:key];
        NSString *head = [keys substringToIndex:range.location];
        NSString *last = [keys substringFromIndex:(range.location + range.length)];
        keys = [NSString stringWithFormat:@"%@%@", head, last];
        [user setObject:keys forKey:KEYS_LIST];
    }
}
-(BOOL)hasKey:(nonnull NSString *)key
{
    BOOL result = [keys containsString:[NSString stringWithFormat:@"<%@>", key]];
    return result;
}
-(void)remove:(nonnull NSString *)key
{
    if ([self hasKey:key]) {
        [self removeKey:key];
        [[NSUserDefaults standardUserDefaults] removeObjectForKey:key];
        [self save];
    }
}
-(void)save
{
    [[NSUserDefaults standardUserDefaults] synchronize];
}

- (instancetype)init
{
    self = [super init];
    keys = [[NSUserDefaults standardUserDefaults] stringForKey:KEYS_LIST];
    if(keys == nil)
        keys = @"";
    return self;
}

+ (SDKUserDB *)sharedInstance
{
    static SDKUserDB *_instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[self alloc] init];
    });
    return _instance;
}
@end
