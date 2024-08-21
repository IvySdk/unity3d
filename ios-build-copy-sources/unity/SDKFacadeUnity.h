//
//  SDKFacadeUnity.h
//  Pods
//
//  Created by 余冰星 on 2017/7/25.
//
//
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <IvyiOSSdk/SDKDelegate.h>
#import <IvyiOSSdk/SDKAdDelegate.h>
#import <IvyiOSSdk/SDKPaymentDelegate.h>
#import <IvyiOSSdk/SDKSNSDelegate.h>
#import <IvyiOSSdk/SDKWebviewDelegate.h>
#import <IvyiOSSdk/SDKCloudFunctionDelegate.h>
#import <IvyiOSSdk/SDKFirestoreDelegate.h>
#import <IvyiOSSdk/SDKFirebaseDatabaseDelegate.h>
#import <IvyiOSSdk/SDKInAppMessageDelegate.h>
@interface SDKFacadeUnity : NSObject<SDKDelegate, SDKAdDelegate, SDKPaymentDelegate, SDKSNSDelegate, SDKWebviewDelegate, SDKCloudFunctionDelegate, SDKFirestoreDelegate, SDKFirebaseDatabaseDelegate, SDKInAppMessageDelegate>
@end
