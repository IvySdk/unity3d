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

#ifndef POBBidEvent_h
#define POBBidEvent_h

@protocol POBBidEventDelegate;

/*!
 This prototol is responsinble for interacting with SDK's banner & interstitial objects to get bid details, continue the bid execution flow.
 All methods of this protocol are expected to be called from Main thread.
 */
@protocol POBBidEvent <NSObject>
/*!
 @abstract Sets the POBBidEventDelegate receiver for the ad object.
 @warning Proper reference management practices should be observed when using delegates.
 @warning Ensure that the delegate is set to nil prior to setting nil to banner view's object reference.
 */
@property (nonatomic, weak) id<POBBidEventDelegate> bidEventDelegate;

/*!
 @abstract Proceeds with bid flow, This method should be called only when bidEvent:didReceiveBid: is triggered,
 @result Returns the BOOL value indicating success or failure
 @note Unnecessary duplicate call will fail & appropriate errors will be logged with return value `NO`
  */
- (BOOL)proceedToLoadAd;

/*!
 @abstract Proceed with bid flow with error, e.g. POBBidEventAuctionLoss.
 If integration type is of primary ad server case like DFP and MoPub, the execution is continued by invoking ad server call ignoring OpenWrap's bid.
 In case of No ad server integration, flow is completed by setting its state to default considering error at client side.
 @discussion If the auto-refresh is enabled for banner ads, the next bid is requested once the ad server flow completes.
 @param code POBBidEventErrorCode values
 @param description Specific description to give more context to passed error code
 */
- (void)proceedOnError:(POBBidEventErrorCode )code andDescription:(NSString *)description;
@end

/*!
 Protocol for bid event notifications
 
 All messages are guaranteed to occur on the main thread.
 */
@protocol POBBidEventDelegate <NSObject>
/*!
 @abstract Notifies the delegate that bid has been successfully received
 @param bidEventObject Instance of object conforming to POBBidEvent (e.g. POBBannerView or POBInterstitial etc)
 @param bid POBBid object
 */
- (void)bidEvent:(id<POBBidEvent>)bidEventObject didReceiveBid:(POBBid *)bid;

/*!
 @abstract Notifies the delegate of an error encountered while fetching the bid
 @param bidEventObject Instance of object conforming to POBBidEvent (e.g. POBBannerView or POBInterstitial etc)
 @param error The error encountered while attempting to receive the bid.
 */
- (void)bidEvent:(id<POBBidEvent>)bidEventObject didFailToReceiveBidWithError:(NSError *)error;
@end

#endif /* POBBidEvent_h */
