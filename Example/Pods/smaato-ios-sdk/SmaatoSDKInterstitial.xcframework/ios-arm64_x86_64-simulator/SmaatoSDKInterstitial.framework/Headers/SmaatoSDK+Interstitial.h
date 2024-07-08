//
//  SmaatoSDK+Display.h
//  SmaatoSDKInterstitial
//
//  Created by Smaato Inc on 10/09/2018.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <SmaatoSDKCore/SmaatoSDK.h>
#import <SmaatoSDKInterstitial/SMAInterstitial.h>

@class SMAAdRequestParams;

/**
 \c Smaato SDK provides simple entry point to load and show fullscreen ads (interstitials).
 Steps to integrate interstitial:

 1. Adopt your ViewController to \c SMAInterstitialDelegate protocol. Implement at least the required \c SMAInterstitialDelegate methods
 \c interstitialDidLoad: and \c interstitial:didFailWithError: like this:
 ```
 // interstitial did successfully load
 - (void)interstitialDidLoad:(SMAInterstitial *)interstitial
 {
   self.interstitial = interstitial;
   [self.interstitial showFromViewController:self];
 }

 // Interstitial did fail loading (see also method's documentation for the \c interstitial parameter)
 - (void)interstitial:(nullable SMAInterstitial *)interstitial didFailWithError:(nonnull NSError *)error
 {
   NSLog(@"Interstitial did fail loading with error: %@", error.localizedDescription);
 }
 ```
 2. Create a method in ViewController class in order to load a interstitial and call it when you need:
 ```
 - (void)callWhenYouNeedInterstitial
 {
   [SmaatoSDK loadInterstitialForAdSpaceId:@"0"    // (required) replace with your AdSpaceID (configured on SPX site)
                                    delegate:self];  // (required)
 }
 ```
 3. Call your method, i.e. in \c viewDidLoad of ViewController class:
 ```
 - (void)viewDidLoad
 {
   [super viewDidLoad];
   // Do any additional setup after loading the view, typically from a nib.
   [self callWhenYouNeedInterstitial];
 }
 ```
 */
@interface SmaatoSDK (Interstitial)

/**
 Request fullscreen, interstitial ad. Any useful info about ad loading could be tracked through of \c SMAInterstitialDelegate methods.&nbsp;
 In the case of successfully loaded ad, you can call \c showFromViewController: method of \c SMAInterstitial object to show the ad.&nbsp;
 In the case of an error you should investigate the reasons of the issue (console logs, NSError details),
 and request new ad after some time interval to prevent running into request frequency cap situation.

 @param adSpaceId    Smaato ad space id
 @param delegate     Delegate object to get the information about important interstitial ad events
 */
+ (void)loadInterstitialForAdSpaceId:(NSString *_Nonnull)adSpaceId delegate:(id<SMAInterstitialDelegate> _Nonnull)delegate;

/**
 Advanced version of `loadInterstitialForAdSpaceId:delegate:` method for ad experts

 @param adSpaceId       Smaato ad space id
 @param delegate        Delegate object to get the information about important interstitial ad events
 @param requestParams   Ad request optional parameters for Unified Bidding or Mediation Network support
 */
+ (void)loadInterstitialForAdSpaceId:(NSString *_Nonnull)adSpaceId
                            delegate:(id<SMAInterstitialDelegate> _Nonnull)delegate
                       requestParams:(SMAAdRequestParams *_Nullable)requestParams;

@end
