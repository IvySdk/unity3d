/*
 * PubMatic Inc. ("PubMatic") CONFIDENTIAL
 * Unpublished Copyright (c) 2006-2023 PubMatic, All Rights Reserved.
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
@import GoogleMobileAds;
#import <OpenWrapSDK/POBNativeConstants.h>

/**
 This class contains various parameters, that will be passed to PubMatic in an ad request.
 Publisher should create instance of this class, set required parameters and pass to AdMob's GADRequest
 as ad network extras.
 */
@interface AdMobOpenWrapAdNetworkExtras : NSObject<GADAdNetworkExtras>

/// This is used to specify PubMatic profile version
@property (nonatomic, strong) NSNumber *versionId;

/// Sets the network timeout (in seconds) for making an PubMatic Ad request.
@property (nonatomic, assign) NSTimeInterval networkTimeout;

/// Indicates whether this PubMatic request is a test request.
@property (nonatomic, assign) BOOL testModeEnabled;

/// Custom ad server URL for debugging purpose.
@property (nonatomic, strong) NSString *adServerURL;

/// Enable/Disable debug information in the response of PubMatic ad request.
@property (nonatomic, assign) BOOL debug;

/// Enable/Disable adapter level logs, default value is 'NO'
@property (nonatomic, assign) BOOL enableAdapterLogs;

/// This parameter is used to pass a zone ID for reporting.
@property(nonatomic, strong) NSString *pmZoneId;

/// This parameter is used to request a test creative from PubMatic.
@property(nonatomic, strong) NSString *testCreativeId;

/**
Disables automatic size conversion, default value is 'NO'.
OpenWrap adapter automatically converts inline/anchored adaptive or non standard size  to following standard sizes. To use AdMob banner size as-is & skip automatic size conversion, set disableBannerSizeConversion = YES;

# Standard sizes
+------------------+--------------------------+-------------------------+
| Size                   | Description                   | Availability                 |
+------------------+--------------------------+-------------------------+
| 320x50              | Banner                          | Phones and tablets    |
| 320x100            | Large banner                | Phones and tablets    |
| 300x250            | IAB medium rectangle  | Phones and tablets    |
| 468x60              | IAB full-size banner      | Tablets                        |
| 728x90              | IAB leaderboard           | Tablets                        |
+------------------+--------------------------+-------------------------+
*/
@property (nonatomic, assign) BOOL disableBannerSizeConversion;

/// This parameter is used to get the context in which the ad appears.
@property (nonatomic, assign) POBNativeContextType contextType;

/// This parameter is used to get more detailed context in which the ad appears.
@property (nonatomic, assign) POBNativeContextSubType contextSubType;

/// This parameter is used to get The design/format/layout of the ad unit being offered.
@property (nonatomic, assign) POBNativePlacementType placementType;

@end
