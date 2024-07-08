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

#ifndef POBNativeEvent_h
#define POBNativeEvent_h
#import "POBBid.h"
#import "POBNativeAd.h"
#import "POBAdServerSignalingEventDelegate.h"

NS_ASSUME_NONNULL_BEGIN

/**
 ------------------------------------------------------------------------------------
 POBNativeAdEventDelegate Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 The native custom event delegate. It is used to inform the ad server SDK events back to the OpenWrap SDK.
 */
@protocol POBNativeAdEventDelegate <NSObject>

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
 @abstract Call this when the ad server SDK informs about impression event as a result of user interaction.
 */
- (void)didRecordImpression;

@end

/**
 ------------------------------------------------------------------------------------
 POBNativeAdEventBridge Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 Bridge for the native custom event protocol that manages communication with an ad server SDK.
*/
@protocol POBNativeAdEventBridge <NSObject>

@required

/*!
 @abstract OpenWrap SDK calls this method when it has valid bid/s to pass to the ad server.
 @discussion Note that, if the OpenWrap SDK does not have valid bids, `requestAdWithBid:` will be called with a `nil` bid value.
 @param bid bid details object having price and other useful information that can be passed to the ad server SDK
*/
- (void)requestAdWithBid:(POBBid * _Nullable)bid;

/*!
 @abstract OpenWrap SDK calls this method to set POBNativeAdEventDelegate, so that the native custom event handler can inform the OpenWrap SDK about the events related to the ad server communication.
 @discussion Save the `delegate` to use it in later phases of ad server events execution
 @param delegate object conforming to POBNativeAdEventDelegate
 */
- (void)setDelegate:(id<POBNativeAdEventDelegate>)delegate;

/*!
 @abstract Set delegate to manage ad server signalling, whether ad server has won or OpenWrap has won.
 @param signalingDelegate Object confirming POBAdServerSignalingEventDelegate
 */
- (void)setSignalingDelegate:(id<POBAdServerSignalingEventDelegate>)signalingDelegate;

/*!
 @abstract Returns the ad server ad view
 @result View intsance
 */
- (UIView * _Nullable)adServerAdView;

@optional

/*!
@abstract Optional method to receive instruction to track the impression.
*/
- (void)trackImpression;

/*!
@abstract Optional method to receive instruction to track the ad click.
*/
- (void)trackClick;

@end

/**
 ------------------------------------------------------------------------------------
 POBNativeAdEvent Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 The native custom event protocol. Your native custom event handler must implement this protocol to communicate with the ad server SDK.
*/
@protocol POBNativeAdEvent <NSObject>
- (id<POBNativeAdEventBridge>)createBridge;
@end

NS_ASSUME_NONNULL_END
#endif /* POBNativeEvent_h */
