//
//  SDKAppsflyerDelegate.h
//  IvyiOSSdk
//
//  Created by ivy on 2024/1/11.
//

#ifndef SDKAppsflyerDelegate_h
#define SDKAppsflyerDelegate_h

#import <Foundation/Foundation.h>

@protocol SDKAppsflyerDelegate<NSObject>

- (void)onAppsflyerInitSuccess;

- (void)onAppsflyerInitFail:(nullable NSString *)data;

- (void)onAppsflyerAppOpenAttribution:(nullable NSString *)data;

- (void)onAppsflyerAppOpenAttributionFailure:(nullable NSString *)data;

- (void)onAppsflyerConversionDataFail:(nullable NSString *)data;

- (void)onAppsflyerConversionDataSuccesss:(nullable NSString *)data;

@end

#endif /* SDKAppsflyerDelegate_h */
