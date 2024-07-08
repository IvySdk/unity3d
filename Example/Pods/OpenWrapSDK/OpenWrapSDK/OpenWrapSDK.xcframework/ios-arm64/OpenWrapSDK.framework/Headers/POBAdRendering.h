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

#ifndef POBAdRendering_h
#define POBAdRendering_h
#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "POBDataTypes.h"

@protocol POBAdDescriptor;
@class POBSKANData;
  /*!
 Bids provider protocol, which is used to provide all the bids and targetting information for all those bids
 */
 @protocol POBBidsProvider <NSObject>

  /// Returns all the client side bids and server side bids
 - (NSArray<id<POBAdDescriptor>> *)allBids;

  /// Returns targetting info for all the bids
 - (NSDictionary *)targettingInfo;

/*!
 @abstract Returns a bid for given bid id from all the bids
 @param bidId bid id
 */
 - (id<POBAdDescriptor>)bidWithId:(NSString *)bidId;
  @end

/*!
 Ad descriptor protocol, provided creative string through `renderableContent`. POBAdRendering protocol renders object which conforms to POBAdDescriptor protocol
 */
@protocol POBAdDescriptor <NSObject>
- (NSString *)renderableContent;
- (CGSize )contentSize;

/// Returns type of descriptor (e.g. video, banner ...etc )
- (NSString *)type;

- (BOOL)isVideo;
- (NSDictionary*)rawBidJson;
// Returns current refresh interval
- (NSTimeInterval)adRefreshInterval;

@optional
/**
 * Returns the unique identifier of the DSP associated with bid object.
 *
 * @return An NSInteger representing the DSP identifier.
 */
- (NSInteger)dspId;

@end


/*!
 Ad banner rendering delegate, It is used to provide feedback events from custom renderer
 */
@protocol POBAdRendererDelegate <NSObject>
- (void)rendererDidRenderAd:(id)renderedAd forDescriptor:(id<POBAdDescriptor>)ad;
- (void)rendererDidFailToRenderAdWithError:(NSError *)error
                             forDescriptor:(id<POBAdDescriptor>)ad;

- (void)rendererWillLeaveApp;
- (void)rendererWillPresentModal;
- (void)rendererDidDismissModal;
- (UIViewController *)viewControllerForPresentingModal;
- (void)rendererDidRecordImpression;

/*!
 This method is called to notify ad expiration
 */
@optional
- (void)rendererDidPresentModal;
- (void)rendererDidExpireAd;
- (void)rendererDidUnloadMRAIDAd;
- (void)rendererDidClickAdForDescriptor:(id<POBAdDescriptor>)ad;

/*!
@abstract Renderer requests ad refresh after given interval.
@param refreshInterval refresh interval after which ad should get refresh
*/
-(void)rendererRequestedAdRefreshAfterInterval:(NSTimeInterval)refreshInterval;

/*!
@abstract Renderer notifies before ad is skipped..
@param skipBlock to nofity SDK to continue to skip
*/
-(void)rendererAboutToSkipWithCompletion:(void (^)(BOOL shouldSkip))skipBlock;

@end

/*!
 Ad banner rendering protocol, implement these methods to write custom ad renderer
 */
@protocol POBBannerRendering <NSObject>
- (void)setSKANData:(POBSKANData*)skanData;
- (void)setDelegate:(id<POBAdRendererDelegate>)delegate;
- (void)renderAdDescriptor:(id<POBAdDescriptor>)descriptor;

@end

/*!
Video rendering delegate, It is used to provide video events from video renderer
*/
@protocol POBVideoRenderingDelegate<NSObject>
@optional
/*!
 @abstract This method is called to notify video ad events
 @param event POBVideoAdEvent
*/
- (void)videoRendererDidOccurAdEvent:(POBVideoAdEvent)event;
@end

/*!
 Ad video rendering protocol, implement these methods to write custom ad renderer
 */
@protocol POBVideoRendering <POBBannerRendering>
- (void)setVideoDelegate:(id<POBVideoRenderingDelegate>)delegate;
@end

/*!
 Ad interstitial rendering delegate, It is used to provide feedback events from custom renderer
 */
@protocol POBInterstitialRendererDelegate <NSObject>
- (void)interstitialRendererDidRenderAd;
- (void)interstitialRendererDidFailToRenderAdWithError:(NSError *)error;
- (void)interstitialRendererDidClick;
- (void)interstitialRendererWillLeaveApp;
- (void)interstitialRendererWillPresentModal;
-(void) interstitialRendererDidPresentModal;

- (void)interstitialRendererDidDismissModal;
- (UIViewController *)viewControllerForPresentingModal;

/**
 * @abstract Notifies the delegate that the interstitial ad renderer has recorded the impression.
 * This will be called for both display and video interstitial ads.
 */
- (void)interstitialRendererDidRecordImpression;

@optional
/*!
 This method is called to notify ad expiration
 */
- (void)interstitialRendererDidExpireAd;
- (void)interstitialRendererDidUnloadMRAIDAd;

@end

/*!
 Ad interstitial rendering protocol, implement these methods to write custom ad renderer
 */
@protocol POBInterstitialRendering <NSObject>
- (void)setDelegate:(id<POBInterstitialRendererDelegate>)delegate;
- (void)renderAdDescriptor:(id<POBAdDescriptor>)descriptor;
- (void)showFromViewController:(UIViewController *)controller inOrientation:(UIInterfaceOrientation)loadTimeOrientation;

@optional
- (void)setVideoDelegate:(id<POBVideoRenderingDelegate>)delegate;

@end

#endif /* POBAdRendering_h */
