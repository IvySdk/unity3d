//
//  NSString+URLEncoding.h
//  Bolts
//
//  Created by 余冰星 on 2019/4/24.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface NSString (URLEncoding)

// 字符串转码
- (nonnull NSString*)urlEncodeString;
// 反URL编码
- (nonnull NSString *)urlDecodeString;

@end

NS_ASSUME_NONNULL_END
