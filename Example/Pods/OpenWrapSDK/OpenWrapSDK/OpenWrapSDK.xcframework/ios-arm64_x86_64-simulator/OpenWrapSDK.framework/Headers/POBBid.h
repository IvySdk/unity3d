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


#import <Foundation/Foundation.h>
#import "POBAdRendering.h"
#import "POBDataTypes.h"
#import "POBReward.h"

#define BID_STATUS_OK  @1

extern NSString *const kPOBWDealIdKey;
extern NSString *const kPOBBidIdKey;
extern NSString *const kPOBBidPriceKey;
extern NSString *const kPOBBidStatusKey;
extern NSString *const kPOBBidPartnerKey;
extern NSString *const kPOBBidSizeKey;
extern NSString *const kPOBWrapperPlatformKey;
extern NSString *const kPOBImpressionId;
extern NSString *const kPOBBidId;
extern NSString *const kPOBBidPrice;
extern NSString *const kPOBBidRequestPartner;
extern NSString *const kPOBBidRequestedAdSize;
extern NSString *const kPOBBidTypeStatic;
extern NSString *const kPOBBidCrTypeExtPath;

@class POBBidSummary, POBRequest;
/**
 ------------------------------------------------------------------------------------
 POBBid Class
 ------------------------------------------------------------------------------------
 */

/*!
 Holds information of winning bid along with all the bids that participated in the auction.
 */
@interface POBBid : NSObject<POBAdDescriptor>
/*!
 ------------------------------------------------------------------------------------
 @name Properties
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract Impression id
 */
@property (nonatomic, readonly) NSString *impressionId;

/*!
 @abstract bid id
 */
@property (nonatomic, readonly) NSString *bidId;

/*!
 @abstract bundle / itunes id of the advertised app
 */
@property (nonatomic, readonly) NSString *bundle;

/*!
 @abstract Net bid price / Net ECPM value
 @note This property is updated to return net price from OW SDK v2.4.0.
 */
@property (nonatomic, readonly) NSNumber *price;

/*!
 @abstract Gross bid price
 @deprecated In favour of @c price property, deprecated in v3.5.0 and will be removed in future releases.
 */
@property (nonatomic, readonly) NSNumber *grossPrice __attribute__((deprecated));

/*!
 @abstract Ad size
 */
@property (nonatomic, readonly) CGSize size;

/*!
 @abstract Bid status
 */
@property (nonatomic, readonly) NSNumber *status;

/*!
 @abstract Identifier of the bid creative
 */
@property (nonatomic, readonly) NSString *creativeId;

/*!
 @abstract Win notice URL called by the exchange if the bid wins (not necessarily indicative of a delivered, viewed, or billable ad); optional means of serving ad markup.
 */
@property (nonatomic, readonly) NSString *nurl;

/*!
 @abstract Loss notice URL called by the exchange when a bid is known to have been lost.
 */
@property (nonatomic, readonly) NSString *lurl;

/*!
 @abstract Creative tag
 */
@property (nonatomic, readonly) NSString *creativeTag;

/*!
 @abstract Available rewards
*/
@property (nonatomic, readonly) NSArray<POBReward *> *rewards;

/*!
 @abstract Creative type
 */
@property (nonatomic, readonly) NSString *creativeType;

/*!
 @abstract Partner name
 */
@property (nonatomic, readonly) NSString *partner;

/*!
 @abstract Deal ID
 */
@property (nonatomic, readonly) NSString *dealId;

/*!
 @abstract List of Bid summary objects
 @see POBBidSummary
 */
@property (nonatomic, readonly) NSArray<POBBidSummary *> *summary;

@property (nonatomic, readonly) NSDate *bidReceivedTimestamp;

/*!
 @abstract refresh interval in seconds
 */
@property (nonatomic, readonly) NSTimeInterval refreshInterval;

@property (nonatomic, readonly) BOOL serverSideAuctionWinner;

/*!
 @abstract winning status with respect to primary ad server, expected to be updated externally.
 */
@property (nonatomic, readwrite) BOOL hasWon;

/*!
 ------------------------------------------------------------------------------------
 @name Instance Methods
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract Initializes POBBid object with given bid details and partner name
 @param bidDetails Details of Bid
 @param requestDetails request details
 @result Instance of POBBid
 */
- (instancetype)initWithBidDetails:(NSDictionary *)bidDetails
                 andRequestDetails:(NSDictionary *)requestDetails;

/*!
 @abstract Initializes POBBid object with given bid object, targetingParams are copied to target bid if original bid do not have any targetingParams already
 @param bid Existing bid object
 @param targetingParams Targeting parameters
 @result Instance of POBBid
 */
+ (POBBid *) bidWithBid:(POBBid *)bid customTargeting:(NSDictionary *)targetingParams;

/*!
 @abstract Initializes POBBid object with given bid object, targetingParams are copied to target bid if original bid do not have any targetingParams already
 @param bid Existing bid object
 @param refreshInterval new refresh interval
 @param expiresAfter time interval in seconds after which bid expires
 @param targetingParams Targeting parameters
 @result Instance of POBBid
 */
+ bidWithBid:(POBBid *)bid expiresAfter:(NSTimeInterval)expiresAfter refreshInterval:(NSTimeInterval)refreshInterval customTargeting:(NSDictionary *)targetingParams;

/*!
 @abstract Create a copy of bid object and update the given details
 @param details Instance of NSDictionary with key as String and values ad any objects
 @result Updated instance of POBBid
 */
- (POBBid *)copyByUpdatingKVCDetails:(NSDictionary *)details;

/*!
 @abstract Returns targeting information that needs to be passed to the ad server SDK.
 @return dictionary of standard key-value pairs for targeting
 */
- (NSDictionary *)targetingInfo;

/*!
 @abstract Returns true if bid is expired
 @Note - SDK do not render expired bid
 */
- (BOOL)isExpired;

/*!
@abstract Returns the POBReward object (if any) from the rewards array under bid extension. It contains the Reward-type and its value.
For example: {
  "seatbid.bid.ext.rewardInfo": {
    "rewards":[{
    "rewardType": "type",
    "rewardValue": "value" }]
  }
}
@note If SDK don't receive rewardInfo in response then POBReward object will be returned with default values as type = @"" and value = -1
@return POBReward object
*/
- (POBReward *)reward;
@end

/*!
 ------------------------------------------------------------------------------------
 POBBidSummary Class
 ------------------------------------------------------------------------------------
 */

/*!
 Holds the information of a single bid which participated in the auction.
 */
@interface POBBidSummary : NSObject

/*!
 ------------------------------------------------------------------------------------
 @name Properties
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract Name of the Bidder
 */
@property (nonatomic, readonly) NSString *bidder;

/*!
 @abstract Bid value
 */
@property (nonatomic, readonly) float bid;

/*!
 @abstract Ad size
 */
@property (nonatomic, readonly) CGSize size;

/*!
 @abstract Error
 */
@property (nonatomic, readonly) NSError *error;

/*!
 ------------------------------------------------------------------------------------
 @name Instance Methods
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract Initializes POBBidSummary class instance
 @param summaryDict Dictionary having bid summary details.
 */
- (instancetype)initWithSummary:(NSDictionary *)summaryDict;

@end
