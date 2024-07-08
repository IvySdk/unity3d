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
#import "POBDataTypes.h"

extern NSString *const kPOBRTBTagIdParam;
extern NSString *const kPOBRTBInstlParam;
extern NSString *const kPOBRTBBannerParam;
extern NSString *const kPOBRTBWidthParam;
extern NSString *const kPOBRTBHeightParam;
extern NSString *const kPOBRTBFormatParam;
extern NSString *const kPOBRTBAPIParam;
extern NSString *const kPOBRTBVideoParam;
extern NSString *const kPOBRTBDisplayManagerVerParam;
extern NSString *const kPOBRTBDisplayManagerParam;
extern NSString *const kPOBPositionParam;
////Video obj params
extern NSString *const kPOBRTBMIMETypesParam;
extern NSString *const kPOBRTBProtocolsParam;
extern NSString *const kPOBRTBPlacementParam;
extern NSString *const kPOBRTBLinearityParam;
extern NSString *const kPOBRTBBoxingAllowedParam;
extern NSString *const kPOBRTBDeliveryParam;
extern NSString *const kPOBRTBCompanionAdParam;
extern NSString *const kPOBRTBCompanionTypeParam;
extern NSString *const kPOBRTBPlaybackEndParam;
extern NSString *const kPOBRTBStartDelay;

// Companion Banner
/*
 Relevant only for Banner objects used with a Video object
 (Section 3.2.7) in an array of companion ads. Indicates the
 companion banner rendering mode relative to the associated
 video, where 0 = concurrent, 1 = end-card.
 */
extern NSString *const kPOBRTBVCMParam;
extern NSInteger const kPOBRTBVCMParamValue;

@class POBBanner, POBVideo;
@protocol POBNative;

/*!
 Fold placement of the ad to be served.
 */
typedef NS_ENUM(NSInteger, POBAdPosition) {
    
    /**
     * Unable to determine the ad position, use this value
     */
    POBAdPositionUnKnown,
    
    /**
     * Ad position is visible
     */
    POBAdPositionAboveFold,
    
    /**
     * Ad position is not visible & it needs user to scroll the page to make it visible
     */
    POBAdPositionBelowFold = 3,
    
    /**
     * Header position
     */
    POBAdPositionHeader,
    
    /**
     * Footer position
     */
    POBAdPositionFooter,
    
    /**
     * In side menu
     */
    POBAdPositionSidebar,
    
    /**
     * Ad is in full screen
     */
    POBAdPositionFullscreen
};

/*!
 ------------------------------------------------------------------------------------
 POBImpression Class
 ------------------------------------------------------------------------------------
 */

/*!
 Holds the general information about an ad impression. Use this class setters to pass impression level information in bid request.
 */
@interface POBImpression : NSObject

/**
 ------------------------------------------------------------------------------------
 @name Instance Methods
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract Initializes POBImpression object with given impression details
 @param impId Unique identifier of the impression
 @param adUnitId The unique identifier which represents an ad placement on the app screen. This is the one you defined while creating profile mappings on the OpenWrap dashboard.
 @result Instance of POBImpression
 @warning This class serves as base for other concrete impression classes, do not directly create an instance of this class
 */
- (instancetype)initWithImpressionId:(NSString *)impId
                            adUnitId:(NSString *)adUnitId;

/*!
 @abstract Checks if the impression is valid
 @result YES/NO value
 */
- (BOOL)isValid;

@property(nonatomic, strong) POBBanner *banner;

@property(nonatomic, assign, getter=isInterstitial) BOOL interstitial;

@property(nonatomic, strong) POBVideo *video;

@property(nonatomic, strong) id<POBNative> native;

/**
 ------------------------------------------------------------------------------------
 @name Properties
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract This parameter is used to hold impression identifier
 */
@property(nonatomic, readonly) NSString *impressionId;

/*!
 @abstract The unique identifier which represents an ad placement on the app screen. This is the one you defined while creating profile mappings on the OpenWrap dashboard.
 */
@property(nonatomic, readonly) NSString *adUnitId;

/*!
 @abstract This parameter is used to pass a zone ID for reporting.
 */
@property(nonatomic, strong) NSString *pmZoneId;

/*!
 @abstract This parameter is used to request a test creative.
 */
@property(nonatomic, strong) NSString *testCreativeId;

/*!
 @abstract Fold placement of the ad to be served.
 Note: If you are unable to determine it, you can set the default value as Unknown in
 this parameter.
 @see POBAdPosition
 */
@property(nonatomic, assign) POBAdPosition adPosition;

/*!
 @abstract Sets custom parameters in the form of a dictionary, to set multiple values against same key, use array.
 
 @warning Only use string or array of string as values
 
 Exmaple :
 
 customParams = @{@"hobbies":@[@"sports",@"reading"],@"age":@"22"};
 
 */
@property(nonatomic, strong) NSDictionary *customParams;

/*!
@abstract Decides whether the ad is rewarded or not. Depending on the value of this parameter, impt.ext.reward param will be send in bid request with the value as 1.
*/
@property(nonatomic, assign, getter=isRewardedAd) BOOL rewardedAd;

@end
