//
//  SDKUserDB.h
//  IvySdkCore
//
//  Created by 余冰星 on 2017/6/5.
//  Copyright © 2017年 IVY. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SDKUserDB : NSObject
-(void)setString:(nullable NSString *)value forKey:(nonnull NSString *)key;
- (NSString *_Nullable)stringForKey:(nonnull NSString *)key defaultString:(nullable NSString *)defaultString;
-(void)setDouble:(double)value forKey:(nonnull NSString *)key;
- (double)doubleForKey:(nonnull NSString *)key defaultValue:(float)defaultValue;
-(void)setFloat:(float)value forKey:(nonnull NSString *)key;
- (float)floatForKey:(nonnull NSString *)key defaultValue:(float)defaultValue;
-(void)setBool:(BOOL)value forKey:(nonnull NSString *)key;
- (BOOL)boolForKey:(nonnull NSString *)key defaultValue:(BOOL)defaultValue;
-(void)setInteger:(NSInteger)value forKey:(nonnull NSString *)key;
- (NSInteger)integerForKey:(nonnull NSString *)key defaultInteger:(NSInteger)defaultInteger;
-(void)setData:(nullable NSData *)data forKey:(nonnull NSString *)key;
-(NSData *_Nullable)dataForKey:(nonnull NSString *)key defaultData:(nullable NSData *)defaultData;
-(void)setArray:(nullable NSArray *)array forKey:(nonnull NSString *)key;
-(NSArray *_Nullable)arrayForKey:(nonnull NSString *)key defaultArray:(nullable NSArray *)defaultArray;
-(void)setObject:(nullable NSCoder *)object forKey:(nonnull NSString *)key;
-(id _Nullable )objectForKey:(nonnull NSString *)key defaultObject:(nullable NSCoder *)defaultObject;
-(void)setDictionary:(nullable NSDictionary *)dict forKey:(nonnull NSString *)key;
-(NSArray *_Nullable)dictionaryForKey:(nonnull NSString *)key defaultDictionary:(nullable NSArray *)defaultDictionary;
-(BOOL)hasKey:(nonnull NSString *)key;
-(void)remove:(nonnull NSString *)key;
-(void)save;

+ (nonnull SDKUserDB *)sharedInstance;
@end
