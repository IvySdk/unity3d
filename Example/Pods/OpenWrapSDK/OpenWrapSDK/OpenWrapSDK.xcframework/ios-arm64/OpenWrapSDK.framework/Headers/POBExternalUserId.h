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

NS_ASSUME_NONNULL_BEGIN

/**
 Class to hold the user's identity data.
 This data will be fetched by the application from identity vendors which is required by different identity partners.
 Application should pass this data to OpenWrap SDK which will be sent in each ad request.
 */
@interface POBExternalUserId : NSObject

/**
 @abstract Name of the data partner
 */
@property (nonatomic, readonly) NSString *source;

/**
 @abstract Id of the pata partner
 */
@property (nonatomic, readonly) NSString *externalUserId;

/**
 @abstract Custom key-value data.
 @discussion The extension can have keys as string and values should be NSString, NSNumber, NSArray, NSDictionary etc.
 */
@property (nonatomic, strong) NSDictionary<NSString *, NSObject *> *extension;

/**
 @abstract A non-zero value for user agent type
 @discussion Please refer the [IAB document](https://github.com/InteractiveAdvertisingBureau/AdCOM/blob/master/AdCOM%20v1.0%20FINAL.md#list_agenttypes) for more details.
 */
@property (nonatomic, assign) int atype;

/**
 @abstract Method to instantiate POBExternalUserId
 @param source Name of the data partner. It is a mandatory parameter.
 @param externalUserId Id of the pata partner. It is a mandatory parameter.
 @return Instance of POBExternalUserId
 */
- (instancetype)initWithSource:(NSString *)source
                         andId:(NSString *)externalUserId;

@end

NS_ASSUME_NONNULL_END
