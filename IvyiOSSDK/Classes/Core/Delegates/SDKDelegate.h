//
//  SDKDelegate.h
//  Pods
//
//  Created by 余冰星 on 2019/4/10.
//

#ifndef SDKDelegate_h
#define SDKDelegate_h
#import <Foundation/Foundation.h>

@protocol SDKDelegate<NSObject>

- (void)onInitialized;

- (void)onReceiveServerExtra:(nullable NSString *)data;

- (void)onReceiveNotificationData:(nullable NSString *)data;

- (void)onNetworkChanged:(int)status;

- (void)onDeepLinkReceived:(nullable NSURL *)url;

-(void)onThirdLoginSuccess:(NSString * _Nonnull)openId platformType:(NSString * _Nonnull)platform;

-(void)onThirdLoginAnonymous;

-(void)onThirdLoginCancel:(NSString * _Nonnull)platform;

-(void)onThirdLoginError:(NSString * _Nonnull)errorMsg platformType:(NSString * _Nonnull)platform;

-(void)onThirdLoginClientNotInstalled:(NSString * _Nonnull) platform;

//是否同意用户协议
- (void)onThirdLoginPrivacyAccept:(BOOL) isAccept;

- (void)onThirdLoginReceiveIdCardVerifiedResult:(int) age;

//用户是否同意苹果隐私跟踪
- (void)onAttAccepted:(BOOL)isAccept;

- (void)unreadMessageCount:(int)count;

- (void)onNotificationOpen:(BOOL)isOpen;
@end

#endif /* SDKDelegate_h */
