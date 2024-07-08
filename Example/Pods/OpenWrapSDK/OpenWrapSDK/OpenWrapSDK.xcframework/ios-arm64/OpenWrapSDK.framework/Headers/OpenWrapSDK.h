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
#import <CoreLocation/CoreLocation.h>
#import "POBAdRendering.h"
#import "POBApplicationInfo.h"
#import "POBBannerEvent.h"
#import "POBBannerView.h"
#import "POBBid.h"
#import "POBBidding.h"
#import "POBBidEvent.h"
#import "POBDataTypes.h"
#import "POBImpression.h"
#import "POBInterstitial.h"
#import "POBInterstitialEvent.h"
#import "POBRequest.h"
#import "POBReward.h"
#import "POBRewardedAd.h"
#import "POBRewardedAdEvent.h"
#import "POBRewardedAdEventDelegate.h"
#import "POBUserInfo.h"
#import "POBExternalUserId.h"

/// Native ads
#import "POBNativeAdLoader.h"
#import "POBNativeAdLoaderConfig.h"
#import "POBNativeAd.h"
#import "POBNativeTemplateView.h"
#import "POBNativeAdSmallTemplateView.h"
#import "POBNativeAdMediumTemplateView.h"
#import "POBNativeAdView.h"
#import "POBNativeConstants.h"
#import "POBAdServerSignalingEventDelegate.h"
#import "POBNativeAdEvent.h"
#import "POBNativeAdResponseAsset.h"
#import "POBNativeAdDataResponseAsset.h"
#import "POBNativeAdImageResponseAsset.h"
#import "POBNativeAdTitleResponseAsset.h"
#import "POBNativeRequestAsset.h"
#import "POBNativeRequestTitleAsset.h"
#import "POBNativeRequestDataAsset.h"
#import "POBNativeRequestImageAsset.h"
#import "POBNativeAdResponseAsset.h"
#import "POBNativeAdTitleResponseAsset.h"
#import "POBNativeAdDataResponseAsset.h"
#import "POBNativeAdImageResponseAsset.h"
#import "POBNativeUXSpecs.h"

//! Project version number for OpenWrapSDK.
FOUNDATION_EXPORT double OpenWrapSDKVersionNumber;

//! Project version string for OpenWrapSDK.
FOUNDATION_EXPORT const unsigned char OpenWrapSDKVersionString[];

// In this header, you should import all the public headers of your framework using statements like #import <OpenWrapSDK/PublicHeader.h>

/*!
 *  Log levels to filter logs.
 */
typedef NS_ENUM(NSUInteger, POBSDKLogLevel){
    /**
     *  No logs
     */
    POBSDKLogLevelOff       = 0,
    
    /**
     *  Error logs only
     */
    POBSDKLogLevelError,
    
    /**
     *  Error and warning logs
     */
    POBSDKLogLevelWarning,
    
    /**
     *  Error, warning and info logs
     */
    POBSDKLogLevelInfo,
    
    /**
     *  Error, warning, info and debug logs
     */
    POBSDKLogLevelDebug,
    
    /**
     *  Error, warning, info, debug and verbose logs
     */
    POBSDKLogLevelVerbose,
    
    /**
     *  All logs
     */
    POBSDKLogLevelAll,
};

/*!
Location source
*/
typedef NS_ENUM(NSInteger, POBLocSource)  {
    
    /// Location is determined from GPS
    POBLocSourceGPS = 1,
    /// Location is determined from IP address
    POBLocSourceIPAddress,
    /// Location is provided by user
    POBLocSourceUserProvided
    
};

/*!
 ------------------------------------------------------------------------------------
 OpenWrapSDK Class
 ------------------------------------------------------------------------------------
 */

/*!
 Provides global configurations for the OpenWrap SDK, e.g. enabling logging, location access, etc. These configurations are globally applicable for OpenWrap SDK; you don't have to set these for every ad request.
 */
@interface OpenWrapSDK : NSObject


/**
 ------------------------------------------------------------------------------------
 @name Instance Methods
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract Returns the OpenWrap SDK's version.
 */
+ (NSString*)version;

/*!
 @abstract Sets log level across all ad formats. Default log level is POBSDKLogLevelWarn
 @param logLevel log level to set.
 @see POBSDKLogLevel
 */
+ (void)setLogLevel:(POBSDKLogLevel)logLevel;

/*!
 @abstract Used to enable/disable location access.
 @discussion This value decides whether the OpenWrap SDK should access device location usings Core Location APIs to serve location-based ads. When set to NO, the SDK will not attempt to access device location. When set to YES, the SDK will periodically try to fetch location efficiently.
 Note that, this only occurs if location services are enabled and the user has already authorized the use of location services for the application. The OpenWrap SDK never asks permission to use location services by itself.
 
 The default value is YES.
 
 @param allow YES/NO value
 */
+ (void)allowLocationAccess:(BOOL)allow;

/*!
 @abstract Tells OpenWrap SDK to use the internal SDK browser, instead of the default device browser, for opening landing pages when the user clicks on an ad.
 By default, the use of an internal browser is disabled.
 
 @note
 From version 2.7.0, the default behaviour changed to using device's default browser
 
 @param use BOOL value that enables/disables the use of internal browser.
 */
+ (void)useInternalBrowser:(BOOL)use;

/*!
 @abstract Sets user's location and its source. It is useful in delivering geographically relevant ads.
 @discussion
 If your application is already accessing the device location, it is highly recommended to set the location coordinates inferred from the device GPS. If you are inferring location from any other source, make sure you set the appropriate location source.
 
 @see POBLocSource
 @param location User's current location
 @param source Source of user's location.
 */
+ (void)setLocation:(CLLocation *)location source:(POBLocSource)source;

/*!
 @abstract Indicates whether the visitor is COPPA-specific or not. For COPPA (Children's Online Privacy Protection Act) compliance, if the visitor's age is below 13, then such visitors should not be served targeted ads.

 @param enable boolean value

 - No : Indicates that the visitor is not COPPA-specific and can be served targeted ads.
 - Yes : Indicates that the visitor is COPPA-specific and should be served only COPPA-compliant ads.
 */
+ (void)setCoppaEnabled:(BOOL)enable;

/*!
 @abstract Enable/disable secure ad calls.
 @discussion By default, OpenWrap SDK initiates secure ad calls from an application to the ad server and delivers only secure ads. You can allow non secure ads by passing NO to this method.
 
 @param enable boolean value
 */
+ (void)setSSLEnabled:(BOOL)enable;


/*!
 @abstract Indicates whether Advertisment ID(IDFA) should be sent in the request. 
 
 @param allow boolean value

 - YES : Advertising Identifier will be sent in the request.
 - NO : Advertising Identifier will be masked in the request.
 
 */
+ (void)allowAdvertisingId:(BOOL)allow;

/*!
 @abstract Indicates whether the OW SDK is allowed to access the shared AVAudioSession
 @note This method disables the audio session access only for OpenWrap SDK. You may have to use a similar provision from ad server sdk, if available, to restrict it from potentially interfering with your app's audio.
 @param allow boolean value

 - YES(Default) : SDK may access the shared AVAudioSession
 - NO : SDK should not access the shared AVAudioSession
 */
+ (void)allowAVAudioSessionAccess:(BOOL)allow;

/*!
 @abstract Sets Application information, which contains various attributes about app, such as application category, store URL, domain, etc, for more relevant ads.
 @param appInfo POBApplicationInfo object having application information
 @see POBApplicationInfo
 */
+ (void)setApplicationInfo:(POBApplicationInfo *)appInfo;

/*!
 @abstract Application information, which contains various attributes about app, such as application category, store URL, domain, etc, for more relevant ads.
 @see POBApplicationInfo
 */
@property (class, nonatomic, readonly) POBApplicationInfo *applicationInfo;

/*!
 @abstract Sets user information, such as birth year, gender, region, etc, for more relevant ads.
 @param userInfo User object having user related information
 @see POBUserInfo
 */
+ (void)setUserInfo:(POBUserInfo *)userInfo;

/*!
 @abstract User information, such as birth year, gender, region, etc, for more relevant ads.
 @see POBUserInfo
 */
@property (class, nonatomic, readonly) POBUserInfo *userInfo;

/**
 @abstract Add user's raw/hashed data required by identity partners which will be passed in the ad request.
 @discussion Ususally the application fetches the user ids from identity vendors and it should be
 passed to OpenWrap SDK using this API.
 
 This external user id passed to OpenWrap SDK will persist for an app session only.
 
 @param externalUserId Instance of POBExternalUserId storing user's data received from identity vendors.
 */
+ (void)addExternalUserId:(POBExternalUserId *)externalUserId;

/**
 @abstract API to get all the added user Ids in OpenWrap SDK using API addExternalUserId.
 @return List of all the POBExternalUserId
 */
+ (NSArray <POBExternalUserId *>*)externalUserIds;

/**
 @abstract API to remove the external user id containg user's data received from identity partners,
 passed to OpenWrap SDK using addExternalUserId.
 
 @param source Source of the user id to which is to be removed.
 */
+ (void)removeExternalUserIdsWithSource:(NSString *)source;

/**
 @abstract API to remove all the external user ids containg user's data received from identity partners,
 passed to OpenWrap SDK using addExternalUserId.
*/
+ (void)removeAllExternalUserIds;

@end
