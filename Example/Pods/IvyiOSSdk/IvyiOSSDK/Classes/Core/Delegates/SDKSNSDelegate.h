//
//  SDKSNSDelegate.h
//  Pods
//
//  Created by 余冰星 on 2017/8/4.
//
//
#ifndef SDKSNSDelegate_h
#define SDKSNSDelegate_h

#import <Foundation/Foundation.h>
typedef void (^sns_login_result)(NSError * _Nullable error);
@protocol SDKSNSDelegate<NSObject>

@required

-(void)signInAppleSuccess:(nonnull NSString *)appidUID;

-(void)signInAppleFailure:(nonnull NSString *)error;

-(void)snsLoginSuccess;

-(void)snsLoginFailure:(nullable NSString *)error;

-(void)snsLoginCancel;

@optional
-(void)onGameCenterLoginSuccess:(nonnull NSString *)playerId;

-(void)onGameCenterLoginFailure;

- (void)snsInviteSuccess;

- (void)snsInviteFailure;

- (void)snsShareSuccess;

- (void)snsShareFailure;

- (void)snsShareCancel;

- (void)snsOpenUrl:(nullable NSString *)url;

- (void)snsReceiveScores:(nullable NSArray *)data;

- (void)snsReceiveFriends:(nullable NSArray *)data;

- (void)snsReceiveInvitableFriends:(nullable NSArray *)data;
@end
#endif /* SDKSNSDelegate_h */
