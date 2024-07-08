/*
 * PubMatic Inc. ("PubMatic") CONFIDENTIAL
 * Unpublished Copyright (c) 2006-2024 PubMatic, All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property of
 * PubMatic. The intellectual and technical concepts contained herein are
 * proprietary to PubMatic and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from PubMatic.
 * Access to the source code contained herein is hereby forbidden to anyone
 * except current PubMatic employees, managers or contractors who have executed
 * Confidentiality and Non-disclosure agreements explicitly covering such access
 * or to such other persons whom are directly authorized by PubMatic to access
 * the source code and are subject to confidentiality and nondisclosure
 * obligations with respect to the source code.
 *
 * The copyright notice above does not evidence any actual or intended
 * publication or disclosure  of  this source code, which includes information
 * that is confidential and/or proprietary, and is a trade secret, of  PubMatic.
 * ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC  PERFORMANCE, OR PUBLIC
 * DISPLAY OF OR THROUGH USE  OF THIS  SOURCE CODE  WITHOUT  THE EXPRESS WRITTEN
 * CONSENT OF PUBMATIC IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
 * LAWS AND INTERNATIONAL TREATIES.  THE RECEIPT OR POSSESSION OF  THIS SOURCE
 * CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS TO
 * REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR
 * SELL ANYTHING THAT IT  MAY DESCRIBE, IN WHOLE OR IN PART.
 */

#import "POBNativeAdResponseAsset.h"
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/*!
 @abstract Native ad data asset received in response
 */
@interface POBNativeAdDataResponseAsset : POBNativeAdResponseAsset

/*!
 @abstract Integer   length of the data element
 */
@property(nonatomic, readonly, assign) NSInteger length;

/*!
 @abstract Represents data value.
 */
@property(nonatomic, readonly, strong) NSString *value;

/*!
 @abstract Type of the data asset as defined in enum POBNativeDataAssetType.
 @discussion Type of the data asset as defined in enum POBNativeDataAssetType.
 Possible values are :
 
 - POBNativeDataAssetTypeSponsored,
 - POBNativeDataAssetTypeDescription,
 - POBNativeDataAssetTypeRating,
 - POBNativeDataAssetTypeLikes,
 - POBNativeDataAssetTypeDownloads,
 - POBNativeDataAssetTypePrice,
 - POBNativeDataAssetTypeSalePrice,
 - POBNativeDataAssetTypePhone,
 - POBNativeDataAssetTypeAddress,
 - POBNativeDataAssetTypeDescription2,
 - POBNativeDataAssetTypeDisplayURL,
 - POBNativeDataAssetTypeCTAText
 
 @see POBNativeDataAssetType
 */
@property(nonatomic, readonly, assign) POBNativeDataAssetType type;

/*!
 @abstract Initializes POBNativeAdDataResponseAsset object with given details
 @param type Type of the data asset as defined in enum POBNativeDataAssetType.
 @param value Represents data value.
 @param length length of the data element
 @param builder Builder to build the POBNativeAdDataResponseAsset
 @result Instance of POBNativeAdImageResponseAsset
 */
- (instancetype)initWithType:(POBNativeDataAssetType)type
                       value:(NSString *)value
                      length:(NSInteger)length
                  builder:(POBNativeAdResponseAssetBuilder *)builder;

@end

NS_ASSUME_NONNULL_END
