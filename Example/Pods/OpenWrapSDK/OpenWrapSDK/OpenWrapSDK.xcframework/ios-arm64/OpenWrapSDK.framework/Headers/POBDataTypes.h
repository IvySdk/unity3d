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
#import <CoreGraphics/CGBase.h>
#import <CoreGraphics/CGGeometry.h>

#ifndef POBDataTypes_h
#define POBDataTypes_h

extern NSString * const kPOBErrorDomain;

/*!
 *  Various Ad Event Types
 */
typedef NS_ENUM(NSUInteger, POBVideoAdEvent) {
    
    /// Event Type Impression occured
    POBVideoAdEventImpression,
    
    /// Event Type video paused
    POBVideoAdEventPause,
    
    /// Event Type video resumed
    POBVideoAdEventResume,
    
    /// Event Type First Quartile occured
    POBVideoAdEventFirstQuartile,
    
    /// Event Type Midpoint occured
    POBVideoAdEventMidpoint,
    
    /// Event Type Third Quartile occured
    POBVideoAdEventThirdQuartile,
    
    /// Event Type Video skipped
    POBVideoAdEventSkip,
    
    /// Event Type Video finished
    POBVideoAdEventComplete,
    
    /// Event Type Video muted
    POBVideoAdEventMute,
    
    /// Event Type Video unmuted
    POBVideoAdEventUnmute,
    
    /// Event Type Video clicked
    POBVideoAdEventClicked,
    
    /// Event Type Industry icon clicked
    POBVideoAdEventIconClicked
};


/// Error codes PubMatic SDK gives in error callback.
typedef NS_ENUM(NSInteger, POBErrorCode)  {
    
    /// Invalid ad request. e.g, missing or invalid parameters
    POBErrorInvalidRequest = 1001,
    
    /// There was no ads available to deliver for ad request.
    POBErrorNoAds,
    
    /// There was an error while retrieving the data from the network.
    POBErrorNetworkError,
    
    /// Failed to process ad request by Ad Server.
    POBErrorServerError,
    
    /// Ad request was timed out
    POBErrorTimeout,
    
    /// Internal error
    POBErrorInternalError,
    
    /// Invalid ad response. SDK does not able to parse the response received from Server.
    POBErrorInvalidResponse,
    
    /// Ad request gets cancelled.
    POBErrorRequestCancelled,
    
    /// There was some issue while rendering the creative.
    POBErrorRenderError,
    
    /// Ad server SDK sent unexpectd/delayed partner bid win response
    POBSignalingError,
    
    /// Indicates ad is expired
    POBErrorAdExpired,
    
    /// Indicates  ad request not allowed for device's current country
     POBErrorAdRequestNotAllowed,
    
    /// Indicates ad is already shown
    POBErrorAdAlreadyShown = 2001,
    
    /// Indicated ad is not ready to show yet
    POBErrorAdNotReady = 2002,
    
    /// Bid lost the client auction between OpenWrap  server side partners and client side partners-
    POBErrorClientSideAuctionLost = 3001,

    /// Bid won the auction between OpenWrap partners but lost the primary ad server's auction
    POBErrorAdServerAuctionLost,

    /// Bid won the auction but never used.
    POBErrorAdNotUsed,
    
    /// Indicates that partner details are not found
    POBErrorNoPartnerDetails = 4001,
    
    /// Indicates invalid reward selection
    POBErrorInvalidRewardSelected = 5001
};

/*!
 List of supported API frameworks for this impression. If an API is not
 explicitly listed, it is assumed not to be supported.
 */
typedef NS_ENUM(NSInteger, POBAPI) {
    /// API VPAID 1.0
    POBAPI_VPAID_1_0 = 1,
    
    /// API VPAID 2.0
    POBAPI_VPAID_2_0,
    
    /// API MRAID-1
    POBAPI_MRAID_1,
    
    /// API ORMMA
    POBAPI_ORMMA,
    
    /// API MRAID-2
    POBAPI_MRAID_2,
    
    /// API MRAID-3
    POBAPI_MRAID_3,
    
    /// API OMSDK
    POBAPI_OMSDK
};

/// Gender to help deliver more relevant ads.
typedef NS_ENUM(NSInteger, POBGender) {
    
    /// Other gender
    POBGenderOther = 0,
    /// Gender male
    POBGenderMale,
    /// Gender female
    POBGenderFemale
};

/// Video ad placement type with respect to main content
typedef NS_ENUM(NSInteger, POBPlacement){
    /// In-stream video ad
    POBPlacementInStream = 1,
    /// Video ad as a Banner
    POBPlacementInBanner,
    /// Video ad in Article
    POBPlacementInArticle,
    /// Video ad in App feeds
    POBPlacementInFeed,
    /// Video ad as an Interstitial
    POBPlacementInterstitial
};

/// Expected video linearity type by Vast Player
typedef NS_ENUM(NSInteger, POBLinearity){
    
    /// Video Linearity type Any
    POBLinearityAny = 0,
    /// Video Linearity type In stream
    POBLinearityLinearInStream,
    /// Video Linearity type Overlay
    POBLinearityNonLinearOverlay
};

/*!
 Specify the bid event error
 */
typedef NS_ENUM(NSUInteger, POBBidEventErrorCode) {
    
    /**
     *  Indicates client-side auction loss
     */
    POBBidEventErrorClientSideAuctionLoss,
    
    /**
     *  Indicates bid expiry
     */
    POBBidEventErrorAdExpiry,

    /**
     *  Other error, for custom use cases
     */
    POBBidEventErrorOther
};

/// Indicates the ad format to request/render
typedef NS_ENUM(NSInteger, POBAdType) {
    /// Banner
    POBAdTypeBanner = 0,
    /// Interstitial
    POBAdTypeInterstitial,
    /// Rewarded
    POBAdTypeRewarded,
    /// Native
    POBAdTypeNative
};

NSString *POBBidEventErrorString(POBBidEventErrorCode errorCode);

typedef NSInteger POBBOOL;
typedef NSString POBBidderId;

extern POBBOOL const POBBOOLYes;
extern POBBOOL const POBBOOLNo;

@class POBAdSize;
POBAdSize *POBAdSizeMakeFromCGSize(CGSize size);
POBAdSize *POBAdSizeMake(CGFloat width, CGFloat height);

#pragma mark - PMAdSize

// Most used Banner Ad Size for both iphone and ipad
#define POBBannerAdSize320x50  POBAdSizeMake(320, 50)
#define POBBannerAdSize320x100  POBAdSizeMake(320, 100)
#define POBBannerAdSize300x250  POBAdSizeMake(300, 250)
#define POBBannerAdSize250x250  POBAdSizeMake(250, 250)

// Most used Banner Ad Size for ipad
#define POBBannerAdSize468x60  POBAdSizeMake(468, 60)
#define POBBannerAdSize768x90 POBAdSizeMake(768, 90)
#define POBBannerAdSize120x600  POBAdSizeMake(120, 600)

/**
 ------------------------------------------------------------------------------------
 POBAdSize Class
 ------------------------------------------------------------------------------------
 */

/*!
 Defines the size of a banner ad.
 */
@interface POBAdSize : NSObject
/*!
 @abstract Initializes size with a given CGSize
 @param size A CGSize to initialize POBAdSize with
 @result instance of POBAdSize
*/
- (instancetype)initWithCGSize:(CGSize)size;
/*!
 @abstract Returns a CGSize respective to this size
 @result CGSize
 */
- (CGSize)cgSize;
/*!
 @abstract Tells whether the size is CGSizeZero
 @result YES, if the size is equal to CGSizeZero.
          NO, if the size is not equal to CGSizeZero.
 */
- (BOOL)isZero;
/*
 Returns width for this size
 @result CGFloat value indicating width
 */
- (CGFloat)width;
/*
 Returns height for this size
 @result CGFloat value indicating height
 */
- (CGFloat)height;
@end

#endif /* POBDataTypes_h */
