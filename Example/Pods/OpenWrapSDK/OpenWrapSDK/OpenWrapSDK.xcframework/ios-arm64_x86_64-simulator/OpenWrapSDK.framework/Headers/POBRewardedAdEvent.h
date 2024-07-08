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

#import "POBBid.h"
#import "POBRewardedAdEventDelegate.h"
#import "POBFullscreenAdInteractionDelegate.h"

#ifndef POBRewardedAdEvent_h
#define POBRewardedAdEvent_h
extern NSString * _Nonnull const kPOBHandlerConfigIdentifier;
extern NSString * _Nonnull const kPOBHandlerConfigAllowMultipleInstancesForAdUnit;
/**
 ------------------------------------------------------------------------------------
 POBRewardedAdEvent Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 The rewarded custom event protocol. Your rewarded custom event handler must implement this protocol to communicate with the ad server SDK.
*/
@protocol POBRewardedAdEvent <NSObject>
NS_ASSUME_NONNULL_BEGIN
/*!
 @abstract OpenWrap SDK calls this method when it has valid bid/s to pass to the ad server.
 @discussion Note that, if the OpenWrap SDK does not have valid bids, `requestAdWithBid:` will be called with a `nil` bid value.
 @param bid bid details object having price and other useful information that can be passed to the ad server SDK
*/
- (void)requestAdWithBid:(POBBid *_Nullable)bid;

/*!
 @abstract OpenWrap SDK calls this method to set POBRewardeAdEventDelegate, so that the rewarded custom event handler can inform the OpenWrap SDK about the events related to the ad server communication.
 @discussion Save the `delegate` to use it in later phases of ad server events execution
 @param delegate object of POBRewardedAdEventDelegate implmentation
*/
- (void)setDelegate:(id<POBRewardedAdEventDelegate>)delegate;

/*!
 @abstract OpenWrap SDK calls this method to show the rewarded ad from the ad server SDK
 @param controller view controller to be used for presenting the rewarded ad
*/
- (void)showFromViewController:(UIViewController *)controller;

@optional
/*!
 @abstract Sets custom data that may be required by the ad server sdk
 @param customData additional data to be passed on to the ad server sdk
 */
- (void)setCustomData:(NSDictionary*_Nullable)customData;

/*!
 @abstract Return a delegate object to receive all the ad lifecycle events.
 @discussion OW SDK will inform the ad lifecycle events to this object. This is mostly useful when ad servers are integrated using mediation waterfall.
 @return id<POBFullscreenAdInteractionDelegate> interaction delegate
 */
- (id<POBFullscreenAdInteractionDelegate>)interactionDelegate;

/*!
 @abstract OW SDK calls this method to fetch available rewards when multiple rewards are configured
 @return available rewards
 */
- (NSArray<POBReward*>*)availableRewards;

/*!
 @abstract OW SDK calls this method to fetch available handler configuration
 @return configurations for the handler
*/
- (NSDictionary*)configuration;

@end

NS_ASSUME_NONNULL_END
#endif /* POBRewardedAdEvent_h */
