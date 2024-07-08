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

#ifndef POBBidding_h
#define POBBidding_h
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "POBAdRendering.h"

@class POBBid, POBBidderResult, POBResponse;

@protocol POBBidding, POBPartnerInstantiator;

/*!
 Ad bidder delegate protocol, which lists methods to be implmented by bidder delegate receiver.
 */
@protocol POBBidderDelegate<NSObject>

/*!
 @abstract Notifies the delegate after the bid manager receives bid response from the openwrap API.
 @param bidder The bidder instance sending the message.
 @param adResponse The bidder response
 */
- (void)bidder:(id<POBBidding>_Nonnull)bidder didReceiveAdResponse:(POBResponse *_Nonnull)adResponse;

/*!
 @abstract Notifies the delegate of an error encoutered while fetching bids from the bidder API.
 @param bidder The bidder instance sending the message.
 @param error The error encountered while fetching bids.
 */
-(void)bidder:(id<POBBidding>_Nonnull)bidder didFailToReceiveBidsWithError:(NSError * _Nullable)error;

@end

/*!
 Ad bidding protocol, which lists methods to be implmented to add new bidder. which is responsible for getting bids from various bidders.
 */
@protocol POBBidding <NSObject>

/*!
 The object that acts as the bidder delegate of the bidder & interested in getting notificaiton from bidder about bit success/failure response.
  */
@property (nonatomic, weak) id<POBBidderDelegate> _Nullable bidderDelegate;

/*!
 Set/Gets the id/name of bidder.
 */
@property (nonatomic, strong) NSString *_Nullable identifier;

/*!
 Sends RTB request to fetch the available bids.
 */
- (void)requestBids;

/*!
 Returns bidder response dictionary, It contains the metadata about the response e.g. network response time, error reasons, bids...etc
 */
- (NSDictionary<NSString *, POBBidderResult * > *_Nonnull)bidderResultMappings;

@optional

/*!
 Returns config updated by bidder response.
 */
- (POBResponse *_Nullable)response;

@end

#endif /* POBBiddin_Nullableg_h */
