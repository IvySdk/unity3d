//
//  SMARewardedNetworkEvent.h
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

@protocol SMARewardedNetworkEventDelegate <NSObject>

/**
 The rewarded ad was loaded.
*/
- (void)rewardedAdDidLoad;

/**
 The rewarded ad failed ot be loaded.
*/
- (void)rewardedAdDidFailWithError:(NSError *_Nonnull)error;

/**
 The rewarded ad has failed to be presented on screen.
*/
- (void)rewardedAdDidFailToPresentWithError:(NSString *_Nullable)errorMessage;

/**
 The rewarded ad did receive reward.
*/
- (void)rewardedAdDidReward;

/**
 The rewarded ad will appear.
*/
- (void)rewardedAdWillAppear;

/**
 The rewarded ad did appear.
*/
- (void)rewardedAdDidAppear;

/**
 The rewarded ad will disappear.
*/
- (void)rewardedAdWillDisappear;

/**
 The rewarded ad did disappear.
*/
- (void)rewardedAdDidDisappear;

/**
 The rewarded ad was clicked.
*/
- (void)rewardedAdDidClick;

/**
 The rewarded ad will leave application.
*/
- (void)rewardedAdWillLeaveApplication;

/**
 The rewarded ad has expired.
*/
- (void)rewardedAdTTLExpired;

@end

/**
 \c SMARewardedNetworkEvent should be implemented by the CSM adapter in order to have access to the set of methods used to communicate with
 the ad network of choice. Use \c requestRewardedAdWithCustomData: to load the rewarded ad request with the ad unit Id parsed from the \c
 customData dictionary that has been previously set up on the SPX dashboard.
*/
@protocol SMARewardedNetworkEvent <SMANetworkEvent>

@property (nonatomic, weak) id<SMARewardedNetworkEventDelegate> _Nullable delegate;

@optional
- (void)requestRewardedAdWithCustomData:(NSDictionary *_Nonnull)customData objectExtras:(NSDictionary *_Nullable)objectExtras;

- (void)requestRewardedWithAdUnitId:(NSString *_Nonnull)adUnitId
                              appId:(NSString *_Nonnull)appId
                       objectExtras:(NSDictionary *_Nullable)objectExtras;

@required
- (void)showRewardedAdFromViewController:(UIViewController *_Nonnull)viewController;

@end
