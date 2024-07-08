//
//  SDKAutoEvent.h
//  FBSDKCoreKit
//
//  Created by 余冰星 on 2020/1/9.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface SDKAutoEvent : NSObject
-(nonnull instancetype)initWithTargetEvent:(nonnull NSString *)targetEvent;
-(void)add:(nonnull NSString *)sendEvent data:(nonnull NSDictionary *)obj;
-(void)check;
-(BOOL)isValid;
@end

NS_ASSUME_NONNULL_END
