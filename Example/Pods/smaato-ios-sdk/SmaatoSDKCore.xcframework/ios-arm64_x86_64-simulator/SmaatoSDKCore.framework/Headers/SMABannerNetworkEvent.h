//
//  SMABannerNetworkEvent.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 20.12.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <SmaatoSDKCore/SMANetworkEvent.h>

@protocol SMABannerNetworkEventDelegate <NSObject>

/**
 The view controller that is presenting the ad.
*/
- (UIViewController *_Nonnull)presentingViewController;

/**
 The banner ad was loaded
*/
- (void)bannerDidLoad:(UIView *_Nonnull)bannerView;

/**
 The banner ad did fail to load
*/
- (void)bannerDidFailWithError:(NSError *_Nonnull)error;

/**
 The banner ad did click
*/
- (void)bannerDidClick;

/**
 The banner ad will present modal content
*/
- (void)bannerWillPresentModalContent;

/**
 The banner ad will dismiss modal content
*/
- (void)bannerDidPresentModalContent;

/**
 The banner ad did dismiss modal content
*/
- (void)bannerDidDismissModalContent;

/**
 The banner ad will leave application
*/
- (void)bannerWillLeaveApplication;

/**
 The banner ad has expired.
*/
- (void)bannerTTLExpired;

@end

/**
 \c SMABannerNetworkEvent should be implemented by the CSM adapter in order to have access to the set of methods used to communicate with
 the ad network of choice. Use \c requestBannerAdWithSize:customData to load the banner request with the ad unit Id parsed from the \c
 customData dictionary that has been previously set up on the SPX dashboard.
*/
@protocol SMABannerNetworkEvent <SMANetworkEvent>

@property (nonatomic, weak) id<SMABannerNetworkEventDelegate> _Nullable delegate;

@optional
- (void)requestBannerAdWithSize:(CGSize)adSize
                     customData:(NSDictionary *_Nonnull)customData
                   objectExtras:(NSDictionary *_Nullable)objectExtras;

- (void)requestBannerAdWithSize:(CGSize)adSize
                       adUnitId:(NSString *_Nonnull)adUnitId
                          appId:(NSString *_Nullable)appId
                   objectExtras:(NSDictionary *_Nullable)objectExtras;

@end
