//
//  SMARewardedInterstitial.h
//  SmaatoSDKRewardedAds
//
//  Created by Smaato Inc on 14.11.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

@import UIKit;

@class SMARewardedInterstitial;

/**
 The delegate of a \c SMARewardedInterstitial object must adopt the \c SMARewardedInterstitialDelegate
 protocol to get callbacks about Rewarded interstitial Ad events.
 */
@protocol SMARewardedInterstitialDelegate <NSObject>

@required

/// Rewarded interstitial did successfully loaded and could be shown immediately. You
/// have about 5 minutes to show the Rewarded interstitial Ad, otherwise it will be marked as
/// expired and deleted from the memory cache.
- (void)rewardedInterstitialDidLoad:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// Rewarded interstitial did fail loading. An error contains detailed description of issue.
- (void)rewardedInterstitialDidFail:(SMARewardedInterstitial *_Nullable)rewardedInterstitial withError:(NSError *_Nonnull)error;

/// Rewarded interstitial has not shown and expires. New Rewarded interstitial should be loaded by publisher request.
- (void)rewardedInterstitialDidTTLExpire:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// Rewarded interstitial finishes the playing.
- (void)rewardedInterstitialDidReward:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

@optional

/// ViewController with rewarded content is presented on screen and got \c rewardedInterstitialWillAppear: callback.
- (void)rewardedInterstitialWillAppear:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// ViewController with rewarded content is presented on screen and got \c rewardedInterstitialDidAppear: callback.
- (void)rewardedInterstitialDidAppear:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// ViewController with rewarded content is dismmised from screen and got \c rewardedInterstitialWillDisappear: callback.
- (void)rewardedInterstitialWillDisappear:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// ViewController with rewarded content is dismmised from screen and got \c rewardedInterstitialDidDisappear: callback.
- (void)rewardedInterstitialDidDisappear:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// Rewarded interstitial starts the playing.
- (void)rewardedInterstitialDidStart:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// Rewarded interstitial end card was clicked.
- (void)rewardedInterstitialDidClick:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// Rewarded video causes the user to leave application.
- (void)rewardedInterstitialWillLeaveApplication:(SMARewardedInterstitial *_Nonnull)rewardedInterstitial;

/// Rewarded interstitial OnAd  loading fail. An error contains detailed description of issue.
- (void)rewardedInterstitialOnAdFailedToLoad:(SMARewardedInterstitial *_Nullable)rewardedInterstitial withError:(NSError *_Nonnull)error;

@end

@interface SMARewardedInterstitial: NSObject
/**
 Registered adSpaceId (assigned by Smaato in SPX dashboard).
 */
@property (nonatomic, copy, readonly, nonnull) NSString *adSpaceId;

/**
 Flag marks the creative has been downloaded and is ready for displaying.
 */
@property (nonatomic, assign, readonly) BOOL availableForPresentation;

/**
 Background color to be used for the presenting view controller. Black by default.
 */
@property (nonatomic, null_resettable, copy) UIColor *backgroundColor;

/**
 Sets the alpha value of the presenting view's background
 Default value: 1
 */
@property (nonatomic, assign) CGFloat alpha;

/**
 Session Id assigned by Smaato to the ad object.

 @warning   The value is only available after the ad has been received successfully. If fetched before, returns \c nil.
 */
@property (nonatomic, readonly, nullable) NSString *sessionId;

/**
 Smaato Creative Id.

 @warning   The value is only available after the ad has been received successfully. If fetched before, returns \c nil.
 */
@property (nonatomic, readonly, nullable) NSString *sci;

/**
 Show fullscreen Rewarded interstitial Ad on modal view controller from any parent view controller.

 @param viewController  UIViewController for presentation
 */
- (void)showFromViewController:(UIViewController *_Nonnull)viewController;

/**
 Not recommended to use. Call `[SmaatoSDK loadRewardedInterstitialForAdSpaceId:delegate:]` instead.
 */
+ (null_unspecified instancetype)new NS_UNAVAILABLE;

/**
 Not recommended to use. Call `[SmaatoSDK loadRewardedInterstitialForAdSpaceId:delegate:]` instead.
 */
- (null_unspecified instancetype)init NS_UNAVAILABLE;

@end
