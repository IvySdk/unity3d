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

#import "POBImpression.h"
#import "OpenWrapSDK.h"
#import <Foundation/Foundation.h>

//These types are common for all the bidders
typedef NSString POBBidderId;
typedef NSString POBBidderKey;

/**
 ------------------------------------------------------------------------------------
 POBRequest Class
 ------------------------------------------------------------------------------------
 */

/*!
  Represents an ad request for the given impression/s & profile.
 */
@interface POBRequest : NSObject

/**
 ------------------------------------------------------------------------------------
 @name Properties
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract This is used to specify profile version. If this is not
 specified, live version of the profile is considered.
 This is used for debug purpose only to test staging profiles. So, it won't be used in the release mode.
 */
@property (nonatomic, strong) NSNumber *versionId;

/*!
 @abstract Enable/Disable bid summary in the response.
 By default, summary is disabled.
 */
@property (nonatomic, assign) BOOL bidSummaryEnabled;

/*!
 @abstract Enable/Disable debug information in the response.
 By default, debug is disabled & no debug information will be available in bid response.
 */
@property (nonatomic, assign) BOOL debug;

/*!
 Identifier of the publisher.
 */
@property (strong, readonly) NSString *publisherId;

/*!
 @abstract Profile Identifier of the ad tag.
 */
@property (strong, readonly) NSNumber *profileId;

/*!
 @abstract Array of impressions
 */
@property (nonatomic, readonly) NSArray<POBImpression *> *impressions;

/*!
 @abstract Custom server URL for debugging purpose.
 */
@property (nonatomic, strong) NSString *adServerURL;

/*!
 @abstract Indicates whether this request is a test request.
 @discussion By default, 'Test Mode' is disabled.
 When enabled, this request is treated as a test request. SDK may deliver only test ads which are not billiable. Please disable the Test Mode for requests before you submit your application to the AppStore.
 */
@property (nonatomic, assign, getter=isTestModeEnabled) BOOL testModeEnabled NS_SWIFT_NAME(testModeEnabled);

/*!
 @abstract Sets the network timeout (in seconds) for making an Ad request.
 Default value is 5 seconds,
 Different value can be set using this API, given that value is greater/equal to 1.0
 */
@property (nonatomic, assign) NSTimeInterval networkTimeout;

/**
 ------------------------------------------------------------------------------------
 @name Instance Methods
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract Initializes openwrap request for given impression/s & profile.
 @param pubId Publisher ID
 @param profileId Profile ID to be used for the request
 @param impressions Array of POBImpression objects representing the ad impressions
 @see POBImpression
 @result New instance of POBRequest
 */
- (instancetype)initWithPublisherId:(NSString *)pubId
                          profileId:(NSNumber *)profileId
                        impressions:(NSArray<POBImpression *> *)impressions;

@end
