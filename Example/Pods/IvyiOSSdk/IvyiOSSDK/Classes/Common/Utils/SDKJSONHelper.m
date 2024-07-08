//
//  SDKJSONHelper.m
//  MusicRadio
//
//  Created by 余冰星 on 17/2/17.
//  Copyright © 2017年 IVYMOBILE INTERNATIONAL ENTERPRISE LIMITED. All rights reserved.
//

#import "SDKJSONHelper.h"
#import "SDKHelper.h"
@implementation SDKJSONHelper

// 将字典或者数组转化为JSON数据
+ (NSData *)toJSONData:(id)theData prettyPrint:(BOOL)pretty
{
    if(theData) {
        NSError *error = nil;
        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:theData
                                                           options:(pretty ? NSJSONWritingPrettyPrinted : 0)
                                                             error:&error];
        
        if ([jsonData length] > 0 && error == nil){
            return jsonData;
        }else{
            return nil;
        }
    }
    return nil;
}

+ (NSString *)toJSONString:(id)theData {
    if(theData) {
        NSData *jsonData = [self toJSONData:theData prettyPrint:NO];
        NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
        return jsonString;
    }
    return nil;
}

+ (NSString *)toPrettyJSONString:(id)theData {
    if(theData) {
        NSData *jsonData = [self toJSONData:theData prettyPrint:YES];
        NSString *jsonString = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
        return jsonString;
    }
    return nil;
}

// 将JSON字符串转化为字典或者数组
+ (id)toArrayOrNSDictionary:(NSString *)jsonString {
    if(jsonString) {
        NSData *jsonData = [jsonString dataUsingEncoding:NSUTF8StringEncoding];
        return [SDKJSONHelper decode:jsonData];
    }
    return nil;
}

+ (id)decode:(NSData *)data {
    if(data) {
        NSError *error = nil;
        id jsonObject = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableContainers error:&error];
        
        if (jsonObject != nil && error == nil){
            return jsonObject;
        }else{
            // 解析错误
            return nil;
        }
        return jsonObject;
    }
    return nil;
}

+ (NSMutableDictionary *)getJsonDataFrom:(NSString *)filePath bundle:(NSBundle *)bundle extension:(NSString *)ext AESKey:(NSString *)key
{
    if ([filePath pathExtension]) {
        filePath = [filePath stringByDeletingPathExtension];
    }
    bundle = bundle ? bundle : [NSBundle mainBundle];
    NSString *path = [bundle pathForResource:filePath ofType:ext];
    NSString *encrypt = [NSString stringWithContentsOfFile:path encoding:NSUTF8StringEncoding error:nil];
    if (encrypt) {
        NSData *jsonData = key ? [SDKHelper decryptDataWithAES:encrypt key:key] : [encrypt dataUsingEncoding:NSUTF8StringEncoding];
        NSError *error;
        id jsonObj = [NSJSONSerialization JSONObjectWithData:jsonData options:NSJSONReadingMutableContainers error:&error];
        if (!jsonData || error) {
            //DLog(@"[json] JSON解码失败");
            return nil;
        } else {
            return jsonObj;
        }
    }
    return nil;
}


+ (NSMutableDictionary *)getJsonDataFrom:(NSString *)jsonfileOrUrl bundle:(NSBundle *)bundle
{
    NSData *jsonData = nil;
    if ([jsonfileOrUrl hasPrefix:@"http:"]) {
        jsonData = [NSData dataWithContentsOfURL:[NSURL URLWithString:jsonfileOrUrl]];
    } else {
        if ([jsonfileOrUrl pathExtension]) {
            jsonfileOrUrl = [jsonfileOrUrl stringByDeletingPathExtension];
        }
        bundle = bundle ? bundle : [NSBundle mainBundle];
        NSString *path = [bundle pathForResource:jsonfileOrUrl ofType:@"json"];
        jsonData = [[NSData alloc] initWithContentsOfFile:path];
    }
    
    if (!jsonData) {
        return nil;
    }
    NSError *error;
    id jsonObj = [NSJSONSerialization JSONObjectWithData:jsonData options:NSJSONReadingMutableContainers error:&error];
    if (!jsonData || error) {
        //DLog(@"[json] JSON解码失败");
        return nil;
    } else {
        return jsonObj;
    }
}

+(NSArray *)asort:(NSArray *)array
{
    return [array sortedArrayUsingComparator:^NSComparisonResult(NSString *ls, NSString *rs) {
        return [ls compare:rs];
    }];
}

+(NSArray *)walkJsonValue:(id)data withResult:(nonnull NSMutableArray *)result
{
    if ([data isKindOfClass:[NSArray class]]) {
        NSArray *values = data;
        for (int i=0; i<[values count]; i++) {
            [[self class] walkJsonValue:values[i] withResult:result];
        }
    } else if([data isKindOfClass:[NSDictionary class]]) {
        NSDictionary *obj = data;
        NSArray *values = [obj allValues];
        for (int i=0; i<[values count]; i++) {
            [[self class] walkJsonValue:values[i] withResult:result];
        }
    } else if ([[NSString stringWithFormat:@"%@", [data class]] containsString:@"NSCFBoolean"]) {
        NSNumber *number = (NSNumber *)data;
        [result addObject:[NSString stringWithFormat:@"%@", [number boolValue] ? @"true" : @"false"]];
    } else {
        [result addObject:[NSString stringWithFormat:@"%@", data]];
    }
    return result;
}

+(NSString *)calcVerificationCode:(NSMutableDictionary *)data
{
    NSArray *arr = [[self class] walkJsonValue:data withResult:[[NSMutableArray alloc] init]];
    arr = [[self class] asort:arr];
    NSString *result = [NSString stringWithFormat:@"%@_", [[NSBundle mainBundle]bundleIdentifier]];
    for (int i=0; i<[arr count]; i++) {
        result = [NSString stringWithFormat:@"%@%@", result, arr[i]];
    }
    result = [SDKHelper md5:result];
    return result;
}
@end
