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

#ifndef POBNativeAd_h
#define POBNativeAd_h
#import "POBNativeAdDataResponseAsset.h"
#import "POBNativeAdImageResponseAsset.h"
#import "POBNativeAdTitleResponseAsset.h"
#import "POBNativeAdView.h"
NS_ASSUME_NONNULL_BEGIN

@protocol POBNativeAdDelegate, POBNativeAd;
@class POBNativeTemplateView;

/*!
 @abstract Rendering completion block for native ads
 */
typedef void(^POBNativeAdRenderingCompletionBlock)(id<POBNativeAd> nativeAd, NSError *_Nullable error);

/**
 ------------------------------------------------------------------------------------
 POBNativeAd Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 @abstract The protocol that the native ad implements.
*/
@protocol POBNativeAd <NSObject>
/*!
 @abstract Sets delegate for receiving the ad workflow events
 @param delegate POBNativeAdDelegate delegate to get Native ad events
 */
- (void)setAdDelegate:(id<POBNativeAdDelegate>)delegate;

/*!
 @abstract Getter for rendered native ad view. This view needs to be attached to the screen.
 @return Instance of POBNativeAdView
 */
- (POBNativeAdView *)adView;

/*!
 @abstract Returns title asset for the respective identifier
 @param identifier Identifier of the title asset
 @return Instance of POBNativeAdTitleResponseAsset
 */
- (POBNativeAdTitleResponseAsset *)titleAssetWithId:(NSInteger)identifier;

/*!
 @abstract Returns data asset for the respective identifier
 @param identifier Identifier of the data asset
 @return Instance of POBNativeAdDataResponseAsset
 */
- (POBNativeAdDataResponseAsset *)dataAssetWithId:(NSInteger)identifier;

/*!
 @abstract Returns image asset for the respective identifier
 @param identifier Identifier of the image asset
 @return Instance of POBNativeAdImageResponseAsset
 */
- (POBNativeAdImageResponseAsset *)imageAssetWithId:(NSInteger)identifier;

/*!
 @abstract Renders a native ad
 @param completion completion block
 */
- (void)renderAdWithCompletion:(POBNativeAdRenderingCompletionBlock)completion;

/*!
 @abstract Renders the native ad with block for native ad rendering customizations
 @param templateview POBNativeTemplateView with customized layout and appearance.
 @param completion Rendering completion block for native ads
 */
- (void)renderAdWithTemplateView:(POBNativeTemplateView *)templateview
                   andCompletion:(POBNativeAdRenderingCompletionBlock)completion;

/*!
 @abstract Registers the rendered views for tracking impression/clicks
 @param adView UIView representing the native ad
 @param clickableViews clickable asset views
 */
- (void)registerViewForInteractions:(UIView *)adView
                     clickableViews:(NSArray<UIView *> *)clickableViews;

@end

/**
 ------------------------------------------------------------------------------------
 POBNativeAdDelegate Protocol
 ------------------------------------------------------------------------------------
 */
/*!
 @abstract Protocol to inform native ad workflow events to the app
 */
@protocol POBNativeAdDelegate <NSObject>
@optional
/*!
 @abstract Notifies delegate that the native ad will launch a modal on top of the current view controller, as a result of user interaction.
 @param nativeAd Instance of POBNativeAd
 */
- (void)nativeAdWillPresentModal:(id<POBNativeAd>)nativeAd;

/*!
 @abstract Notifies delegate that the native ad have launched a modal on top of the current view controller, as a result of user interaction.
 @param nativeAd Instance of POBNativeAd
 */
- (void)nativeAdDidPresentModal:(id<POBNativeAd>)nativeAd;

/*!
 @abstract Notifies delegate that the native ad has dismissed the modal on top of the current view controller.
 @param nativeAd Instance of POBNativeAd
 */
- (void)nativeAdDidDismissModal:(id<POBNativeAd>)nativeAd;

/*!
 @abstract Notifies the delegate whenever current app goes in the background due to user click.
 @param nativeAd Instance of POBNativeAd
 */
- (void)nativeAdWillLeaveApplication:(id<POBNativeAd>)nativeAd;

/*!
 @abstract Informs delegate that the native ad has recorded an impression
 @param nativeAd Instance of POBNativeAd
 */
- (void)nativeAdDidRecordImpression:(id<POBNativeAd>)nativeAd;

/*!
 @abstract Informs delegate that the native ad has recorded a click
 @param nativeAd Instance of POBNativeAd
 */
- (void)nativeAdDidRecordClick:(id<POBNativeAd>)nativeAd;

/*!
 @abstract Informs delegate that the native ad has recorded a click for a particular asset
 @param nativeAd Instance of POBNativeAd
 @param assetId Id of the asset clicked
 */
- (void)nativeAd:(id<POBNativeAd>)nativeAd didRecordClickForAsset:(NSInteger)assetId;
@end

NS_ASSUME_NONNULL_END
#endif /* POBNativeAd_h */
