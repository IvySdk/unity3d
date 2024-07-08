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

#import <UIKit/UIKit.h>
#import "POBInterstitialEvent.h"
#import "POBRequest.h"
#import "POBBidEvent.h"

@class POBInterstitial;
NS_ASSUME_NONNULL_BEGIN
/**
 ------------------------------------------------------------------------------------
 POBInterstitialDelegate Protocol
 ------------------------------------------------------------------------------------
 */

/*!
 Protocol for interaction with the POBInterstitial instance.
 All messages are guaranteed to occur on the main thread.
 */
@protocol POBInterstitialDelegate<NSObject>
@optional
/*!
 @abstract Notifies the delegate that an ad has been received successfully.
 @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialDidReceiveAd:(POBInterstitial *)interstitial;

/*!
 @abstract Notifies the delegate of an error encountered while loading an ad.
 @param interstitial The POBInterstitial instance sending the message.
 @param error The error encountered while loading an ad.
 */
- (void)interstitial:(POBInterstitial *)interstitial
didFailToReceiveAdWithError:(NSError *)error;

/*!
 @abstract Notifies the delegate of an error encountered while showing an ad.
 @param interstitial The POBInterstitial instance sending the message.
 @param error The error encountered while showing an ad.
 */
- (void)interstitial:(POBInterstitial *)interstitial didFailToShowAdWithError:(NSError *)error;


/*!
 @abstract Notifies the delegate that the interstitial ad will be presented as a modal on top of the current view controller.
 @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialWillPresentAd:(POBInterstitial *)interstitial;

/*!
 @abstract Notifies the delegate that the interstitial ad is presented as a modal on top of the current view controller.
 @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialDidPresentAd:(POBInterstitial *)interstitial;

/*!
 @abstract Notifies the delegate that the interstitial ad has been animated off the screen.
 @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialDidDismissAd:(POBInterstitial *)interstitial;

/*!
 @abstract Notifies the delegate that a user interaction will open another app (e.g. App Store), leaving the current app. To handle user clicks that open the
 landing page URL in the internal browser, use 'interstitialDidClickAd:'
 instead.
 @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialWillLeaveApplication:(POBInterstitial *)interstitial;

/*!
 @abstract Notifies the delegate of ad click
 @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialDidClickAd:(POBInterstitial *)interstitial;

/*!
 @abstract Notifies the delegate of an ad expiration. After this callback, this 'POBInterstitial' instance is marked as invalid & will not be shown.
 @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialDidExpireAd:(POBInterstitial *)interstitial;

/**
 * @abstract Notifies the delegate that the interstitial ad has recorded the impression.
 *
 * @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialDidRecordImpression:(POBInterstitial *)interstitial;

@end

/*!
 Protocol for VAST based video ad events
 All messages are guaranteed to occur on the main thread.
 */
@protocol POBInterstitialVideoDelegate<NSObject>
@optional
/*!
 @abstract Notifies the delegate that playback has completed
 @param interstitial The POBInterstitial instance sending the message.
 */
- (void)interstitialDidFinishVideoPlayback:(POBInterstitial *)interstitial;

@end

/* ------------------------------------------------------------------------------------
 POBInterstitial Class
 ------------------------------------------------------------------------------------
 */

/*!
 Displays Interstitial Ads.
 */
@interface POBInterstitial : NSObject<POBBidEvent>
/*!
 @abstract Initializes & returns a newly initialized Interstitial object with the specified POBInterstitialEvent implementaion and other details.
 @param publisherId Unique identifier assigned at the time of publisher onboarding.
 @param profileId Profile id.
 @param adUnitId Ad unit id used to identify unique placement on screen.
 @param eventHandler Object conforming to POBInterstitialEvent protocol
 @result Instance of POBInterstitial
 */
- (instancetype _Nullable)initWithPublisherId:(NSString *)publisherId
                                    profileId:(NSNumber *)profileId
                                     adUnitId:(NSString *)adUnitId
                                 eventHandler:(id<POBInterstitialEvent>)eventHandler;

/*!
 @abstract Initializes & returns a newly initialized Interstitial object for supporting 'No Ad Server Configuration'.
 @param publisherId Unique identifier assigned at the time of publisher onboarding.
 @param profileId Profile id.
 @param adUnitId Ad unit id used to identify a unique placement on screen.
 @result Instance of POBInterstitial
 */
- (instancetype _Nullable)initWithPublisherId:(NSString *)publisherId
                                    profileId:(NSNumber *)profileId
                                     adUnitId:(NSString *)adUnitId;

/*!
 @abstract Initiates the loading of an interstitial ad
 */
- (void)loadAd;

/*!
 @abstract Returns the winning bid that will be used to render the ad
 */
- (POBBid *)bid;

/*!
 @abstract Presents an interstitial ad in full screen view until the user dismisses it. Calling this method has no effect until the ad is received(interstitialDidReceiveAd:) and is ready to show, i.e. 'isReady' flag is true.
 @param controller UIViewController to be used for presenting the interstitial ad.
 */
- (void)showFromViewController:(UIViewController*)controller;

/*!
 @abstract Sets the POBInterstitialDelegate delegate receiver for the interstitial ad
 @warning Proper reference management practices should be observed when using delegates.
 @warning Ensure that the delegate is set to nil prior to setting nil to POBInterstitial's object reference.
 */
@property (nonatomic, weak) id <POBInterstitialDelegate> _Nullable delegate;

/*!
 @abstract Object representing bid request
 @discussion You may set some additional targeting parameters on this object
 @see POBRequest
 */
@property (nonatomic, readonly) POBRequest *request;

/*!
 @abstract Managed impression object created internally. It provides APIs to set PubMatic zone id and other custom parameterss
 */
@property (nonatomic, readonly) POBImpression *impression;

/*!
 @abstract Returns YES if the interstitial is ready to be displayed. The delegate’s interstitialDidReceiveAd: will be called after this property switches from NO to YES.
 */
@property (nonatomic, assign, readonly) BOOL isReady;

/*!
 @abstract Sets the POBInterstitialVideoDelegate delegate receiver for the interstitial ad which will notify about VAST based video events.
 Renderer which does not render VAST videos in Interstitial, may avoid setting this delegate.
 */
@property (nonatomic, weak) id <POBInterstitialVideoDelegate> videoDelegate;
@end
NS_ASSUME_NONNULL_END
