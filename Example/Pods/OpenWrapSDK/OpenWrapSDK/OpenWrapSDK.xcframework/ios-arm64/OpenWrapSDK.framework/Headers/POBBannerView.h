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

#import "POBBannerEvent.h"
#import <UIKit/UIKit.h>
#import "POBRequest.h"
#import "POBBidEvent.h"

@class POBBannerView;
NS_ASSUME_NONNULL_BEGIN

/**
 ------------------------------------------------------------------------------------
 POBBannerViewDelegate Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 Protocol for interaction with the POBBannerView instance.
 
 All messages are guaranteed to occur on the main thread.
 */
@protocol POBBannerViewDelegate<NSObject>

@required

/** @name Methods */
/*!
 @abstract Asks the delegate for a view controller instance to use for presenting modal views as a result of user interaction on an ad. Usual implementation may simply return self, if it is a view controller class.
 */
- (UIViewController *)bannerViewPresentationController;

@optional

/*!
 @abstract Notifies the delegate that an ad has been successfully loaded and rendered..
 @param bannerView The POBBannerView instance sending the message.
 */
- (void)bannerViewDidReceiveAd:(POBBannerView *)bannerView;

/*!
 @abstract Notifies the delegate of an error encountered while loading or rendering an ad.
 @param bannerView The POBBannerView instance sending the message.
 @param error The error encountered while attempting to receive or render the
 ad.
 */
- (void)bannerView:(POBBannerView *)bannerView
didFailToReceiveAdWithError:(NSError *)error;

/*!
 @abstract Notifies the delegate whenever current app goes in the background due to user click.
 @param bannerView The POBBannerView instance sending the message.
 */
- (void)bannerViewWillLeaveApplication:(POBBannerView *)bannerView;

/*!
 @abstract Notifies delegate that the banner view will launch a modal on top of the current view controller, as a result of user interaction.
 @param bannerView The POBBannerView instance sending the message.
 */
- (void)bannerViewWillPresentModal:(POBBannerView *)bannerView;

/*!
 @abstract Notifies delegate that the banner view has dismissed the modal on top of
 the current view controller.
 @param bannerView The POBBannerView instance sending the message.
 */
- (void)bannerViewDidDismissModal:(POBBannerView *)bannerView;

/*!
 @abstract Notifies the delegate that the banner view was clicked.
 @param bannerView The POBBannerView instance sending the message.
 */
- (void)bannerViewDidClickAd:(POBBannerView *)bannerView;

/**
 * @abstract Notifies the delegate that the banner ad has recorded the impression.
 *
 * @param bannerView The POBBannerView instance sending the message.
 */
- (void)bannerViewDidRecordImpression:(POBBannerView *)bannerView;

@end



/**
 ------------------------------------------------------------------------------------
 POBBannerView Class
 ------------------------------------------------------------------------------------
 */

/*!
 The view class that displays the banner ad.
 
 It renders a banner ad from either the ad server SDK or openwrap partner whichever gets a chance in the auction.
 
 To request banner ad, your delegate must conform to POBBannerViewDelegate, to pass mandatory UIViewController using
 
 - (UIViewController * _Nonnull)bannerViewPresentationController;
 
 */
@interface POBBannerView : UIView<POBBidEvent>

/*!
 @abstract Initializes & returns a newly allocated banner view object.
 @param publisherId Unique identifier assigned at the time of publisher onboarding.
 @param profileId Profile id of the ad tag.
 @param adUnitId Ad unit id used to identify unique placement on screen.
 @param eventHandler Object conforming to POBBannerEvent protocol
 @result Instance of POBBannerView
 */
- (instancetype _Nullable)initWithPublisherId:(NSString *)publisherId
                          profileId:(NSNumber *)profileId
                           adUnitId:(NSString *)adUnitId
                            eventHandler:(id<POBBannerEvent>)eventHandler;

/*!
 @abstract Initializes & returns a newly allocated banner view object for supporting 'No Ad Server Configuration'.
 @param publisherId Unique identifier assigned at the time of publisher onboarding.
 @param profileId Profile id of the ad tag.
 @param adUnitId Ad unit id used to identify unique placement on screen.
 @param adSizes List of banner ad sizes for this impression
 @result Instance of POBBannerView
 */
- (instancetype _Nullable)initWithPublisherId:(NSString *)publisherId
                          profileId:(NSNumber *)profileId
                           adUnitId:(NSString *)adUnitId
                            adSizes:(NSArray<POBAdSize *> *)adSizes;

/*!
 @abstract Initiates the loading of the banner ad
 */
- (void)loadAd;

/*!
 Pauses the auto refresh, By default, banner refreshes automatically as per
 configured refresh interval on openwrap portal. Calling this method
 prevents the refresh cycle to happen even if a refresh interval has been specified.
 
 It is recommended to use this method whenever the ad view is about to be hidden
 from the user for any period of time, to avoid unnecessary ad requests. You can
 then call `resumeAutoRefresh` to resume the refresh when banner becomes
 visible.
 @see resumeAutoRefresh
 */
- (void)pauseAutoRefresh;

/*!
 Resumes the autorefresh as per configured refresh interval on openwrap portal, call this method only if you have previously paused autorefresh using `pauseAutoRefresh`. This method has no effect if a refresh interval has not been set.
 @see pauseAutoRefresh
 */
- (void)resumeAutoRefresh;

/*!
 @abstract Cancels existing ad requests and initiates new ad request
 @warning It may skip force refresh if ad creative is being loaded, user interacting with ad (Opening Internal browser or expanding ad) or waiting response from ad server SDK if applicable.
 @result Status YES/NO, about force refresh, as described it can skip in few cases by returning 'NO'
*/
- (BOOL)forceRefresh;

/*!
 @abstract Sets the POBBannerViewDelegate receiver for the ad view.
 @warning Proper reference management practices should be observed when using delegates.
 @warning Ensure that the delegate is set to nil prior to setting nil to banner view's object reference.
 */
@property (nonatomic, weak) id <POBBannerViewDelegate> _Nullable delegate;

/*!
 @abstract Object representing bid request
 @discussion You may set some additional targeting parameters on this object
 @see POBRequest
 */
@property (nonatomic, readonly)  POBRequest *request;

/*!
 @abstract Managed impression object created internally. It provides APIs to set pubmatic zone id and other custom parameters.
 */
@property (nonatomic, readonly)  POBImpression *impression;

/*!
 @abstract Size of the rendered ad creative
 */
- (POBAdSize *)creativeSize;

/*!
@abstract Returns the winning bid that will be used to render the ad
 */
- (POBBid *)bid;
@end
NS_ASSUME_NONNULL_END
