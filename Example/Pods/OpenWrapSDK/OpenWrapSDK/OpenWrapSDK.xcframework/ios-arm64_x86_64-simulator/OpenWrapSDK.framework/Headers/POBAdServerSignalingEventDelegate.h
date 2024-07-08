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

#ifndef POBAdServerSignalingEvent_h
#define POBAdServerSignalingEvent_h

#import "POBBid.h"

/*!
 @abstract Protocol to notify primary ad server signals (e.g. if ad server has won the auction or PubMatic has won, or failure etc.) to OpenWrap SDK.
 */
@protocol POBAdServerSignalingEventDelegate <NSObject>

/*!
@abstract Returns bids provider
*/
- (id<POBBidsProvider> _Nullable)bidsProvider;
/*!
 @abstract Call this when the ad server SDK signals about partner bid win with bid id.
 @param bidId identifier of the winning bid to be rendered
 */
- (void)openWrapPartnerDidWinForBid:(NSString * _Nullable)bidId;
/*!
 @abstract Call this when the ad server SDK renders its own ad
 */
- (void)adServerDidWin;
/*!
 @abstract Call this when the ad server SDK fails to load the ad
 @param error detailed error object describing the cause of ad failure
*/
- (void)failedWithError:(NSError *_Nullable)error;
/*!
 @abstract Returns a view controller instance to be used by ad server SDK for showing modals
 @result a UIViewController instance for showing modals
 */
- (UIViewController * _Nonnull)viewControllerForPresentingModal;

@end

#endif /* POBAdServerSignalingEvent_h */
