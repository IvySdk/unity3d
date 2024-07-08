//
//  SmaatoSDK+RewardedAds.h
//  SmaatoSDKRewardedAds
//
//  Created by Smaato Inc on 18.04.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <SmaatoSDKCore/SmaatoSDK.h>

@protocol SMARewardedInterstitialDelegate;
@class SMAAdRequestParams;

/**
 \c Smaato SDK provides simple entry point to load and show rewarded interstitial
 Steps to integrate Rewarded Interstitial:

 1. Adopt your ViewController to \c SMARewardedInterstitialDelegate protocol. Implement at least the required
 \c SMARewardedInterstitialDelegate methods \c rewardedInterstitialDidLoad: and \c rewardedInterstitialDidFail:withError: like this
 ```
 // Rewarded interstitial did successfully loaded
 - (void)rewardedInterstitialDidLoad:(SMARewardedInterstitial *)rewardedInterstitial
 {
   self.rewardedInterstitial = rewardedInterstitial;
   [self.rewardedInterstitial showFromViewController:self];
 }

 // Rewarded interstitial did fail loading
 - (void)rewardedInterstitialDidFail:(SMARewardedInterstitial *)rewardedInterstitial withError:(NSError *)error
 {
   NSLog(@"Rewarded ad did fail loading with error: %@", error.localizedDescription);
 }
 ```
 2. Create a method in ViewController class in order to load a rewardedInterstitial and call it when you need.
 ```
 - (void)callWhenYouNeedRewardedAd
 {
   [SmaatoSDK loadRewardedInterstitialForAdSpaceId:@"3090"// (required) replace with your AdSpaceID (configured on SPX site)
                                          delegate:self];
 }
 ```
 3. Call your method, i.e. in \c viewDidLoad of ViewController class
 ```
 - (void)viewDidLoad
 {
   [super viewDidLoad];
   // Do any additional setup after loading the view, typically from a nib.
   [self callWhenYouNeedRewardedAd];
 }
 ```
 */
@interface SmaatoSDK (RewardedAds)

/**
 Requests Rewarded Interstitial Ad. Any useful info about Rewarded Interstitial Ad
 loading could be tracked through of \c SMARewardedInterstitialDelegate callback
 methods.&nbsp; In the case of successful Rewarded Interstitial Ad loading you can call
 \c showFromViewController: method of \c SMARewardedInterstitial object to show
 Rewarded Interstitial Ad as presented view controller.&nbsp; In the case of an
 error you should investigate the reasons of the issue (console logs, NSError
 details) and request new Rewarded Interstitial Ad after some time interval to prevent
 running into request frequency cap situation.

 @warning \c SMARewardedInterstitial result object has own TTL (time-to-live) of about
 5 minutes after downloading, when this rewardedInterstitial Ad could be shown. After this time
 the Rewarded Interstitial Ad is marked as expired and will not be displayed and paid.
 You have to request the new one again.

 @param adSpaceId    Smaato ad space id
 @param delegate     Delegate of \c SMARewardedInterstitial to get the information
 about important rewardedInterstitial ad events
 */
+ (void)loadRewardedInterstitialForAdSpaceId:(NSString *_Nonnull)adSpaceId delegate:(id<SMARewardedInterstitialDelegate> _Nonnull)delegate;

/**
 Advanced version of `loadRewardedInterstitialForAdSpaceId:delegate:` method for ad experts

 @param adSpaceId       Smaato ad space id
 @param delegate        Delegate of \c SMARewardedInterstitial to get the information
 @param requestParams   Ad request optional parameters for Unified Bidding or Mediation Network support
 */
+ (void)loadRewardedInterstitialForAdSpaceId:(NSString *_Nonnull)adSpaceId
                                    delegate:(id<SMARewardedInterstitialDelegate> _Nonnull)delegate
                               requestParams:(SMAAdRequestParams *_Nullable)requestParams;

@end
