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
#import "POBNativeAdEvent.h"
#import "POBImpression.h"
#import "POBNativeAdLoaderConfig.h"
#import "POBRequest.h"
#import "POBNativeRequestAsset.h"
#import "POBBidEvent.h"

NS_ASSUME_NONNULL_BEGIN

@class POBNativeAdLoader;

/**
 ------------------------------------------------------------------------------------
 POBNativeAdLoaderDelegate Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract Protocol for interaction with the POBNativeAdLoader instance. All messages are guaranteed to occur on the main thread.
 */
@protocol POBNativeAdLoaderDelegate <NSObject>
@required
/*!
 @abstract Returns a view controller instance to be used by ad server SDK for showing modals
 @result a UIViewController instance for showing modals
 */
- (UIViewController *)viewControllerForPresentingModal;

@optional
/*!
 @abstract Notifies the delegate that an ad has been successfully loaded.
 @param adLoader Instance of POBNativeAdLoader
 @param nativeAd Instance of standard native ad from PubMatic
 */
- (void)nativeAdLoader:(POBNativeAdLoader *)adLoader didReceiveAd:(id<POBNativeAd>)nativeAd;

/*!
 @abstract Notifies the delegate of an error encountered while loading an ad.
 @param adLoader Instance of POBNativeAdLoader
 @param error The error encountered while attempting to receive the ad.
 */
- (void)nativeAdLoader:(POBNativeAdLoader *)adLoader didFailToReceiveAdWithError:(NSError *)error;

@end


/**
 ------------------------------------------------------------------------------------
 POBNativeAdLoader Class
 ------------------------------------------------------------------------------------
 */

/*!
@abstract Ad loader class to load native ad from PubMatic as per the template specified during initialization.
 
 This class also interacts with primary ad server for header bidding via event handler if specified.
 */
@interface POBNativeAdLoader : NSObject

/*!
 @abstract Sets the POBNativeAdLoaderDelegate receiver for the ad loader..
 */
@property (nonatomic, weak) id<POBNativeAdLoaderDelegate> delegate;

/*!
 @abstract Sets the POBBidEventDelegate to notify the bid events.
 */
@property (nonatomic, weak) id<POBBidEventDelegate> bidEventDelegate;

/*!
 @abstract Object representing bid request
 @discussion You may set some additional targeting parameters on this object
 @see POBRequest
 */
@property (nonatomic, readonly) POBRequest *request;

/*!
 @abstract Managed impression object created internally. It provides APIs to set pubmatic zone id and other custom parameters.
 */
@property (nonatomic, readonly) POBImpression *impression;

/*!
 @abstract Configuration to specify various properties for native ads.
 */
@property (nonatomic, readonly) POBNativeAdLoaderConfig *config;

/*!
 @abstract List of native assets. Must be set if templateType is POBNativeTemplateTypeCustom
 @see POBNativeTemplateType
 */
@property (nonatomic, strong) NSArray<id<POBNativeRequestAsset>> *assetList;

/*!
 @abstract Initializes & returns a newly allocated ad loader object.
 @param publisherId Unique identifier assigned at the time of publisher onboarding.
 @param profileId Profile id of the ad tag.
 @param adUnitId Ad unit id used to identify unique placement on screen.
 @param templateType Native ad template type.
 @seealso POBNativeTemplateType
 @param eventHandler Object conforming to POBNativeAdEvent protocol
 @result Instance of POBNativeAdLoader
 */
- (instancetype)initWithPublisherId:(NSString *)publisherId
                          profileId:(NSNumber *)profileId
                           adUnitId:(NSString *)adUnitId
                       templateType:(POBNativeTemplateType)templateType
                       eventHandler:(id<POBNativeAdEvent>)eventHandler;

/*!
 @abstract Initializes & returns a newly allocated ad loader object for supporting 'No Ad Server Configuration'.
 @param publisherId Unique identifier assigned at the time of publisher onboarding.
 @param profileId Profile id of the ad tag.
 @param adUnitId Ad unit id used to identify unique placement on screen.
 @param templateType Native ad template type.
 @seealso POBNativeTemplateType
 @result Instance of POBNativeAdLoader
 */
- (instancetype)initWithPublisherId:(NSString *)publisherId
                          profileId:(NSNumber *)profileId
                           adUnitId:(NSString *)adUnitId
                       templateType:(POBNativeTemplateType)templateType;

/*!
 @abstract Initiates the loading of the native ad.
 */
- (void)loadAd;

@end

NS_ASSUME_NONNULL_END
