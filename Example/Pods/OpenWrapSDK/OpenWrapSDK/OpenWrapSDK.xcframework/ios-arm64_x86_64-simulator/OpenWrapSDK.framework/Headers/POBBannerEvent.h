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

#ifndef POBBannerEvent_h
#define POBBannerEvent_h
#import <UIKit/UIKit.h>
#import "POBAdRendering.h"
#import "POBBid.h"
#import "POBBidEvent.h"

NS_ASSUME_NONNULL_BEGIN
@protocol POBBannerEvent;
/**
 ------------------------------------------------------------------------------------
 POBBannerEventDelegate Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 The banner custom event delegate. It is used to inform the ad server SDK events back to the OpenWrap SDK.
 */
@protocol POBBannerEventDelegate<NSObject>
/*!
 @abstract Call this when the ad server SDK signals about partner bid win with bid id.
 @param bidId identifier of the winning bid to be rendered
 */
 - (void) openWrapPartnerDidWinForBid:(NSString * _Nullable)bidId;

/*!
@abstract Returns bids provider
*/
- (id<POBBidsProvider>)bidsProvider;

/*!
 @abstract Call this when the ad server SDK renders its own ad
 @param view rendered ad view from the ad server SDK
 */
- (void)adServerDidWin:(UIView *)view;

/*!
 @abstract Call this when the ad server SDK fails to load the ad
 @param error detailed error object describing the cause of ad failure
*/
- (void)failedWithError:(NSError *)error;

/*!
 @abstract Call this when the ad server SDK is about to present a modal
 */
- (void)willPresentModal;

/*!
 @abstract Call this when the ad server SDK dissmisses a modal
 */
- (void)didDismissModal;

/*!
 @abstract Call this when the ad server SDK informs about app leave event as a result of user interaction.
 */
- (void)willLeaveApp;

/*!
 @abstract Returns a view controller instance to be used by ad server SDK for showing modals
 @result a UIViewController instance for showing modals
 */
- (UIViewController *)viewControllerForPresentingModal;

/*!
 @abstract Call this when the ad server SDK records the impression as applicable
 */
- (void)adServerAdDidRecordImpression;

@optional

/*!
 @abstract Call this when the ad server SDK completes the ad execution workflow by itself &
 OW SDK should just reset the ad state
 */
- (void)didCompleteAdExecution;

/*!
 @abstract Call this when the ad server SDK informs about the ad click
 */
- (void)didClickAd;

@end

/**
 ------------------------------------------------------------------------------------
 POBBannerEvent Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 The banner custom event protocol. Your banner custom event handler must implement this protocol to communicate with ad server SDK.
*/
@class POBAdSize;
@protocol POBBannerEvent<NSObject>

@required

/*!
 @abstract OpenWrap SDK calls this method when it has valid bid/s to pass to the ad server.
 @discussion Note that, if the OpenWrap SDK does not have valid bids, `requestAdWithBid:` will be called with a `nil` bid value.
 @param bid bid details object having price and other useful information that can be passed to the ad server SDK
*/
- (void)requestAdWithBid:(POBBid * _Nullable)bid;

/*!
 @abstract OpenWrap SDK calls this method to set POBBannerEventDelegate, so that the banner custom event handler can inform the OpenWrap SDK about the events related to the ad server communication.
 @discussion Save the `delegate` to use it in later phases of ad server events execution
 @param delegate object conforming to POBBannerEventDelegate
 */
- (void)setDelegate:(id<POBBannerEventDelegate>) delegate;

/*!
 @abstract OpenWrap SDK calls this method to get the size of the ad rendered by the ad server SDK
 @result ad size
 */
- (CGSize)adContentSize;

/*!
 @abstract OpenWrap SDK calls this method to get the ad sizes for which bid request should be made.
 @result Array of POBAdSize
 */
- (NSArray<POBAdSize *> *)requestedAdSizes;

@optional
/*!
 @abstract Optional method to provide implementation of POBBannerRendering protocol to render ad in custom rendering component. If not implemented, the OpenWrap SDK  internally creates its own renderer capable of handling simple HTML and MRAID creatives.
 @param partner name of the partner associated with the winning bid
 @result renderer object conforming to `POBBannerRendering` protocol
 */
- (id<POBBannerRendering>_Nullable)rendererForPartner:(NSString *)partner;

/*!
@abstract Optional method to receive instruction to track the impression.
*/
- (void)trackImpression;

/*!
@abstract Optional method to receive instruction to track the ad click.
*/
- (void)trackClick;

/*!
 @abstract Returns the ad server ad view
 @result View intsance
 */
- (UIView *)adServerAdView;

@end
NS_ASSUME_NONNULL_END
#endif /* POBBannerEvent_h */
