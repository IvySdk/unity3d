//
//  SDKWebviewDelegate.h
//  Pods
//
//  Created by 余冰星 on 2020/1/16.
//

#ifndef SDKWebviewDelegate_h
#define SDKWebviewDelegate_h
#import <Foundation/Foundation.h>

@protocol SDKWebviewDelegate<NSObject>

-(void) onWebviewLoadSuccess;

-(void) onWebviewLoadFailure;

-(void) onWebviewClose;

-(void)jsCallOC:(nullable NSString *)param;

@end

#endif /* SDKWebviewDelegate_h */
