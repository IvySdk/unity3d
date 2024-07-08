//
//  SMAInterstitialNetworkEvent.h
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

@protocol SMAInterstitialNetworkEventDelegate <NSObject>

/**
 The interstitial ad loaded.
*/
- (void)interstitialDidLoad;

/**
 The interstitial ad failed loading.
*/
- (void)interstitialDidFailWithError:(NSError *_Nonnull)error;

/**
 The interstitial ad failed to be presented on screen.
*/
- (void)interstitialDidFailToPresentScreen;

/**
 The interstitial ad will appear.
*/
- (void)interstitialWillAppear;

/**
 The interstitial ad did appear.
*/
- (void)interstitialDidAppear;

/**
 The interstitial ad will disappear.
*/
- (void)interstitialWillDisappear;

/**
 The interstitial ad did disappear.
*/
- (void)interstitialDidDisappear;

/**
 The interstitial ad was clicked.
*/
- (void)interstitialDidClick;

/**
 The interstitial ad has expired.
*/
- (void)interstitialDidExpire;

/**
 The interstitial ad will leave application.
*/
- (void)interstitialWillLeaveApplication;

@end

/**
A set of methods that are called by the \c SMABannerView instance in response to important events during its lifetime.

@warning   The delegate of a \c SMABannerView object must adopt the \c SMABannerViewDelegate protocol.
*/

/**
 \c SMAInterstitialNetworkEvent should be implemented by the CSM adapter in order to have access to the set of methods used to communicate
 with the ad network of choice. Use \c requestInterstitialAdWithCustomData to load the interstitial request with the ad unit Id parsed from
 the \c customData dictionary that has been previously set up on the SPX dashboard.
*/
@protocol SMAInterstitialNetworkEvent <SMANetworkEvent>

@property (nonatomic, weak) id<SMAInterstitialNetworkEventDelegate> _Nullable delegate;

@optional
- (void)requestInterstitialAdWithCustomData:(NSDictionary *_Nonnull)customData objectExtras:(NSDictionary *_Nullable)objectExtras;

- (void)requestInterstitialWithAdUnitId:(NSString *_Nonnull)adUnitId
                                  appId:(NSString *_Nonnull)appId
                           objectExtras:(NSDictionary *_Nullable)objectExtras;

@required
- (void)showInterstitialFromViewController:(UIViewController *_Nonnull)viewController;

@end
