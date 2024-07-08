//
//  SMABannerView.h
//  SmaatoSDKBanner
//
//  Created by Smaato Inc on 20/04/2018.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <SmaatoSDKBanner/SMABannerTypes.h>
#import <SmaatoSDKCore/SmaatoSDKCore.h>
#import <UIKit/UIKit.h>

@class SMABannerView;
@class SMAAdRequestParams;

/**
 A set of methods that are called by the \c SMABannerView instance in response to important events during its lifetime.

 @warning   The delegate of a \c SMABannerView object must adopt the \c SMABannerViewDelegate protocol.
 */
@protocol SMABannerViewDelegate <NSObject>
@required

/**
 A view controller that will be used to present modal view controllers.

 @param bannerView  The banner view sending the message.
 @return            A presenting view controller.
 */
- (nonnull UIViewController *)presentingViewControllerForBannerView:(SMABannerView *_Nonnull)bannerView;

/**
 Sent when TTL has expired, based on the timestamp from the SOMA header.

 @param bannerView  The banner view sending the message.
 */
- (void)bannerViewDidTTLExpire:(SMABannerView *_Nonnull)bannerView;

@optional

/**
 Sent when the banner view loads an ad successfully.

 @param bannerView The banner view sending the message.
 */
- (void)bannerViewDidLoad:(SMABannerView *_Nonnull)bannerView;

/**
 Sent when banner view is clicked.

 @param bannerView  The banner view sending the message.
 */
- (void)bannerViewDidClick:(SMABannerView *_Nonnull)bannerView;

/**
 Sent when the banner view fails to load an ad successfully.

 @param bannerView  The banner view sending the message.
 @param error       An error object containing details of why the banner view failed to load an ad.
 */
- (void)bannerView:(SMABannerView *_Nonnull)bannerView didFailWithError:(NSError *_Nonnull)error;

/**
 Sent when the user taps on an ad and modal content will be presented (e.g. internal browser).

 @param bannerView  The banner view sending the message.
 */
- (void)bannerViewWillPresentModalContent:(SMABannerView *_Nonnull)bannerView;

/**
 Sent when modal content has been presented.

 @param bannerView  The banner view sending the message.
 */
- (void)bannerViewDidPresentModalContent:(SMABannerView *_Nonnull)bannerView;

/**
 Sent when modal content will be dismissed.

 @param bannerView  The banner view sending the message.
 */
- (void)bannerViewDidDismissModalContent:(SMABannerView *_Nonnull)bannerView;

/**
 Sent when the ad causes the user to leave the application.

 @param bannerView  The banner view sending the message.
 */
- (void)bannerWillLeaveApplicationFromAd:(SMABannerView *_Nonnull)bannerView;

/**
 Sent when the ad view impression has been tracked by the sdk.

 @param bannerView  The banner view sending the message.
 */
- (void)bannerViewDidImpress:(SMABannerView *_Nonnull)bannerView;

@end

/**
 Banner view used to display Image and RichMedia ad formats.
 */
@interface SMABannerView: UIView

/**
 Ad space identifier assigned by Smaato.
 */
@property (nonatomic, readonly, copy, nonnull) NSString *adSpaceId;

/**
 Banner ad size.

 @warning   Please ensure that the \c SMABannerView has enough space on the screen to display an ad of the requested size.
            Otherwise, the \c SMABannerView won't display the ad and impressions won't be counted.

 You may rely on its intristic size when using Auto Layout.
 */
@property (nonatomic, readonly) SMABannerAdSize adSize;

/**
 Time interval before Ad is reloaded. Defaults to \c kSMABannerAutoreloadIntervalDefault.

 @warning       Don't rely on the exact time intervals as they are only approximate values.
                The actual time interval may vary depending on a number of factors such as the banner visibility or network availability.
 */
@property (nonatomic) SMABannerAutoreloadInterval autoreloadInterval;

/**
 Session Id assigned by Smaato to the ad object.

 @warning   The value is only available after the ad has been received successfully. If fetched before, returns \c nil.
 */
@property (nonatomic, readonly, nullable) NSString *sessionId;

/**
 Smaato Creative Id.

 @warning   The value is only available after the ad has been received successfully. If fetched before, returns \c nil.
 */
@property (nonatomic, readonly, nullable) NSString *sci;

/**
 The object that acts as the delegate of the banner view.
 */
@property (nonatomic, weak, null_unspecified) IBOutlet id<SMABannerViewDelegate> delegate;

/**
 Load new banner ad. You must call this method programmatically to initiate loading of the first ad.
 The automatic refresh is controlled by the \c autoreloadInterval property. Final result as a rendered banner view or
 an error will be returned through \c SMABannerViewDelegate delegate callback methods.

 @param adSpaceId       Ad space identifier assigned by Smaato.
 @param adSize          Banner ad size.

 @warning   Please ensure that the \c SMABannerView has enough space on the screen to display an ad of the requested size.
 Otherwise, the \c SMABannerView won't display the ad and impressions won't be counted.
 */
- (void)loadWithAdSpaceId:(NSString *_Nonnull)adSpaceId adSize:(SMABannerAdSize)adSize;

/**
 Advanced version of `loadWithAdSpaceId:adSize:` method for ad experts

 @param adSpaceId       Ad space identifier assigned by Smaato.
 @param adSize          Banner ad size.
 @param requestParams   Ad request optional parameters for Unified Bidding or Mediation Network support

 @warning   Please ensure that the \c SMABannerView has enough space on the screen to display an ad of the requested size.
 Otherwise, the \c SMABannerView won't display the ad and impressions won't be counted.
 */
- (void)loadWithAdSpaceId:(NSString *_Nonnull)adSpaceId
                   adSize:(SMABannerAdSize)adSize
            requestParams:(SMAAdRequestParams *_Nullable)requestParams;

@end
