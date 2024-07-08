//
//  SDKRemoteConfigDelegate.h
//  Pods
//
//  Created by 余冰星 on 2018/10/29.
//

#ifndef SDKRemoteConfigDelegate_h
#define SDKRemoteConfigDelegate_h

#import <Foundation/Foundation.h>
@protocol SDKRemoteConfigDelegate<NSObject>

@required
-(void)setDefaults:(nullable NSDictionary *)data;
-(nonnull NSSet *)remoteConfigKeysWithPrefix:(nullable NSString *)prefix;
-(int)getRemoteConfigIntValue:(nonnull NSString *)key;
-(long)getRemoteConfigLongValue:(nonnull NSString *)key;
-(double)getRemoteConfigDoubleValue:(nonnull NSString *)key;
-(BOOL)getRemoteConfigBoolValue:(nonnull NSString *)key;
-(nonnull NSString *)getRemoteConfigStringValue:(nonnull NSString *)key;
-(void)setUserPropertyString:(nonnull NSString *)value forName:(nonnull NSString *)key;
@end

#endif /* SDKRemoteConfigDelegate_h */
