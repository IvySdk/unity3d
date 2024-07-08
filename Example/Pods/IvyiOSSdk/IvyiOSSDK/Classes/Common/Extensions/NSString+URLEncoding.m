//
//  NSString+URLEncoding.m
//  Bolts
//
//  Created by 余冰星 on 2019/4/24.
//

#import "NSString+URLEncoding.h"

@implementation NSString (URLEncoding)

// 字符串编码
- (NSString *)urlEncodeString
{
    NSString *charactersToEscape = @"?!@#$^&%*+,:;='\"`<>()[]{}/\\| ";
    NSCharacterSet *allowedCharacters = [[NSCharacterSet characterSetWithCharactersInString:charactersToEscape] invertedSet];
    NSString *upSign = [self stringByAddingPercentEncodingWithAllowedCharacters:allowedCharacters];
    return upSign;
}
// 反URL编码
- (NSString *)urlDecodeString
{
    NSMutableString *outputStr = [NSMutableString stringWithString:self];
    [outputStr replaceOccurrencesOfString:@"+" withString:@"" options:NSLiteralSearch range:NSMakeRange(0,[outputStr length])];
    return [outputStr stringByRemovingPercentEncoding];
}
@end
