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
#import "POBNativeConstants.h"

NS_ASSUME_NONNULL_BEGIN

@class POBNativeAdResponseAssetBuilder, POBNativeAdLinkResponseAsset, POBNativeAdResponseEventTracker;

/*!
 @abstract Native ad asset received in response. Parent class for all the asset classes containing common code.
 */
@interface POBNativeAdResponseAsset : NSObject

/*!
 @abstract Unique asset ID, assigned by exchange.
 */
@property(nonatomic, readonly, strong) NSNumber *assetId;


/*!
 @abstract Boolean    Set to 1 if asset is required.
 */
@property(nonatomic, readonly, assign) BOOL required;


/*!
 @abstract Link object for call to actions.
 */

@property(nonatomic, readonly, strong) POBNativeAdLinkResponseAsset *link;

/*!
 @abstract Initializer
 @param builder Builder to build the POBNativeAdResponseAsset
 @return Instance of POBNativeAdResponseAsset
 */
- (instancetype) initWithBuilder:(POBNativeAdResponseAssetBuilder *)builder;

@end

NS_ASSUME_NONNULL_END
