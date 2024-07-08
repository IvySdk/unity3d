//
//  SDKJSONHelper.h
//  MusicRadio
//
//  Created by 余冰星 on 17/2/17.
//  Copyright © 2017年 IVYMOBILE INTERNATIONAL ENTERPRISE LIMITED. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SDKJSONHelper : NSObject

// 将字典或者数组转化为JSON数据
+ (nullable NSData *)toJSONData:(nullable id)theData prettyPrint:(BOOL)pretty;

+ (nullable NSString *)toJSONString:(nullable id)theData;

+ (nullable NSString *)toPrettyJSONString:(nullable id)theData;

// 将JSON字符串转化为字典或者数组
+ (nullable id)toArrayOrNSDictionary:(nonnull NSString *)jsonString;
+ (nullable id)decode:(nullable NSData *)data;
+ (nullable NSMutableDictionary *)getJsonDataFrom:(nonnull NSString *)jsonfileOrUrl bundle:(nullable NSBundle *)bundle;
+ (nullable NSMutableDictionary *)getJsonDataFrom:(nonnull NSString *)filePath bundle:(nullable NSBundle *)bundle extension:(nonnull NSString *)ext AESKey:(nullable NSString *)key;
+ (nullable NSString *)calcVerificationCode:(nullable NSMutableDictionary *)data;
@end
