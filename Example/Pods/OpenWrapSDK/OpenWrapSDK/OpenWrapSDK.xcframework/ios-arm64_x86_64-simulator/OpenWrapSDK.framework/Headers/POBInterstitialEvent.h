/*
* PubMatic Inc. ("PubMatic") CONFIDENTIAL
* Unpublished Copyright (c) 2006-2024 PubMatic, All Rights Reserved.
*
* NOTICE:  All information contained herein is, and remains the property of PubMatic. The intellectual and technical concepts contained
* herein are proprietary to PubMatic and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
* Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
* from PubMatic.  Access to the source code contained herein is hereby forbidden to anyone except current PubMatic employees, managers or contractors who have executed
* Confidentiality and Non-disclosure agreements explicitly covering such access or to such other persons whom are directly authorized by PubMatic to access the source code and are subject to confidentiality and nondisclosure obligations with respect to the source code.
*
* The copyright notice above does not evidence any actual or intended publication or disclosure  of  this source code, which includes
* information that is confidential and/or proprietary, and is a trade secret, of  PubMatic.   ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC  PERFORMANCE,
* OR PUBLIC DISPLAY OF OR THROUGH USE  OF THIS  SOURCE CODE  WITHOUT  THE EXPRESS WRITTEN CONSENT OF PUBMATIC IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
* LAWS AND INTERNATIONAL TREATIES.  THE RECEIPT OR POSSESSION OF  THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS
* TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR SELL ANYTHING THAT IT  MAY DESCRIBE, IN WHOLE OR IN PART.
*/

#ifndef POBInterstitialEvent_h
#define POBInterstitialEvent_h
#import <UIKit/UIKit.h>
#import "POBAdRendering.h"
#import "POBBid.h"
#import "POBFullscreenAdInteractionDelegate.h"

NS_ASSUME_NONNULL_BEGIN
/**
 ------------------------------------------------------------------------------------
 POBInterstitialEventDelegate Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 The interstitial custom event delegate. It is used to inform ad server events back to the OpenWrap SDK
 */
@protocol POBInterstitialEventDelegate <NSObject>
/*!
 @abstract Call this when the ad server SDK signals about partner bid win with bidId
 @param bidId identifier of the winning bid to be rendered
 */
 - (void)openWrapPartnerDidWinForBid:(NSString * _Nullable)bidId;

/*!
@abstract Returns bids provider
*/
- (id<POBBidsProvider>)bidsProvider;

/*!
 @abstract Call this when the ad server SDK renders its own ad
 */
- (void)adServerDidWin;

/*!
 @abstract Call this when the ad server SDK fails to load the ad
 @param error detailed error object describing the cause of ad failure
 */
- (void)failedToLoadWithError:(NSError *)error;

/*!
 @abstract Call this when the ad server SDK fails to show the ad
 @param error detailed error object describing the cause of ad failure
 */
- (void)failedToShowWithError:(NSError *)error;

/*!
 @abstract Call this when the ad server SDK is about to present a modal
 */
- (void)willPresentAd;

/*!
 @abstract Call this when the ad server SDK has presented a modal
 */
- (void)didPresentAd;

/*!
 @abstract Call this when the ad server SDK dissmisses a modal
 */
- (void)didDismissAd;


/*!
 @abstract Call this when the ad server SDK informs about app leave event as a result of user interaction.
 */
- (void)willLeaveApp;

/*!
 @abstract Call this when the ad server SDK informs about click event as a result of user interaction.
 */
- (void)didClickAd;

/*!
 @abstract Call this when the ad server SDK informs about ad expiration.
 */
- (void)adDidExpireAd;

/*!
 @abstract Call this when the ad server SDK records the impression as applicable.
 */
- (void)adServerAdDidRecordImpression;

@end

@class POBBid;
/**
 ------------------------------------------------------------------------------------
 POBInterstitialEvent Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 The interstitial custom event protocol. Your interstitial custom event handler must implement this protocol to communicate with ad server SDK.
 */
@protocol POBInterstitialEvent<NSObject>
@required

/*!
 @abstract OpenWrap SDK calls this method when it has valid bid/s to pass to the ad server.
 @discussion Note that, if the OpenWrap SDK does not have valid bids, `requestAdWithBid:` will be called with a `nil` bid value.
 @param bid bid details object having price and other useful information that can be passed to the ad server SDK
 */
- (void)requestAdWithBid:(POBBid *_Nullable)bid;

/*!
 @abstract OpenWrap SDK calls this method to set POBInterstitialEventDelegate, so that the interstitial custom event handler can inform the OpenWrap SDK about the events related to the ad server communication.
 @discussion Save the `delegate` to use it in later phases of ad server events execution
 @param delegate object of POBInterstitialEventDelegate implmentation
 */
- (void)setDelegate:(id<POBInterstitialEventDelegate>) delegate;

/*!
 @abstract OpenWrap SDK calls this method to show the interstitial ad from the ad server SDK
 @param controller view controller to be used for presenting the interstitial ad
*/
- (void)showFromViewController:(UIViewController * _Nonnull)controller;

@optional
/*!
 Optional delegate to provide implementation of POBInterstitialRendering protocol to render ad in custom rendering component. If not implemented, the OpenWrap SDK internally creates its own renderer capable of handling simple HTML and MRAID creatives
 @param partner name of the partner associated with the winning bid
 */
- (id<POBInterstitialRendering> _Nullable)rendererForPartner:(NSString *)partner;

/*!
 @abstract Return a delegate object to receive all the ad lifecycle events.
 @discussion OW SDK will inform the ad lifecycle events to this object. This is mostly useful when ad servers are integrated using mediation waterfall.
 @return id<POBFullscreenAdInteractionDelegate> interaction delegate
 */
- (id<POBFullscreenAdInteractionDelegate>)interactionDelegate;

@end
NS_ASSUME_NONNULL_END

#endif /* POBInterstitialEvent_h */
