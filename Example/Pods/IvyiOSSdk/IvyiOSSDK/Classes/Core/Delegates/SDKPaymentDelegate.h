//
//  SDKPaymentDelegate.h
//  Pods
//
//  Created by 余冰星 on 2017/7/14.
//
//
#ifndef SDKPaymentDelegate_h
#define SDKPaymentDelegate_h
#import <Foundation/Foundation.h>

@protocol SDKPaymentDelegate<NSObject>

@required

- (void)onPaymentSuccess:(int)paymentId;

- (void)onPaymentSuccess:(int)paymentId payload:(nullable NSString *)payload;

- (void)onPaymentSuccess:(int)paymentId payload:(nullable NSString *)payload orderInfo:(nonnull NSString *)orderInfo;

- (void)onPaymentFailure:(int)paymentId forError:(nullable NSString *)error;

- (void)onAppStorePayRequest:(int)paymentId;

@optional

- (void)onPaymentReady;

- (void)onCheckSubscriptionResult:(int)paymentId remainSeconds:(long)seconds;

- (void)onRestoreSuccess:(int)paymentId;

- (void)onRestoreFailure:(nullable NSString *)error;

@end
#endif /* SDKPaymentDelegate_h */
