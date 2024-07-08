//
//  SDKDebug.h
//  BGFMDB
//
//  Created by 余冰星 on 2020/3/7.
//

#import <Foundation/Foundation.h>
//#import <SocketRocket/SocketRocket.h>
NS_ASSUME_NONNULL_BEGIN

@interface SDKDebug : NSObject
//-(nullable NSString *)logWeb:(nullable NSString *)msg;
+(nonnull SDKDebug *)debug;
+ (nonnull NSString *)deviceModel;
@end

NS_ASSUME_NONNULL_END
