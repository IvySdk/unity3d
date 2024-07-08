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
#import "POBNativeRequestAsset.h"
#import "POBNativeConstants.h"

NS_ASSUME_NONNULL_BEGIN

/**
 @abstract The Image object to be used for all image elements of the Native ad such as Icons, Main Image, etc.
 */
@interface POBNativeRequestImageAsset : NSObject <POBNativeRequestAsset>

/**
 @abstract Optional parameter. Type ID of the image element supported by the publisher. The publisher can display this information in an appropriate format.
 */
@property (nonatomic, readonly) POBNativeImageAssetType type;

/**
 @abstract Recommended parameter. The minimum requested width of the image in pixels. This option should be used for any rescaling of images by the client. Either w or wmin should be transmitted. If only w is included, it should be considered an exact requirement.
 */
@property (nonatomic, readonly) NSInteger wmin;

/**
 @abstract Recommended parameter. The minimum requested height of the image in pixels. This option should be used for any rescaling of images by the client. Either h or hmin should be transmitted. If only h is included, it should be considered an exact requirement.
 */
@property (nonatomic, readonly) NSInteger hmin;

/**
 @abstract Whitelist of content MIME types supported. Popular MIME types include, but are not limited to “image/jpg”,  “image/gif”. If not set, defaultSupportedMimeTypes will be used.
 */
@property (nonatomic, strong) NSArray<NSString *> *mimes;

/**
 @abstract Initializer
 @param assetId Unique asset ID, assigned by exchange. Typically a counter for the array.
 @param required Set to YES if asset is required
 @param type Type ID of the image element supported by the publisher. The publisher can display this information in an appropriate format.
 @param wmin The minimum requested width of the image in pixels
 @param hmin The minimum requested height of the image in pixels
 @return Instance of POBNativeRequestImageAsset
 */
- (instancetype)initWithId:(NSInteger)assetId
                  required:(BOOL)required
                      type:(POBNativeImageAssetType)type
              minimumWidth:(NSInteger)wmin
             minimumHeight:(NSInteger)hmin;

@end

NS_ASSUME_NONNULL_END
