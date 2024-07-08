//
//  SMANativeAd.h
//  SmaatoSDKNative
//
//  Created by Smaato Inc on 22.01.20.
//  Copyright © 2020 Smaato Inc. All rights reserved.
//

#import <SmaatoSDKCore/SmaatoSDKCore.h>
#import <UIKit/UIKit.h>

@class SMANativeAdAssets;
@class SMANativeAdRenderer;
@class SMAAdRequestParams;
@class SMANativeAd;
@class SMANativeAdRequest;

/**
A set of methods that are called by the \c SMANativeAd instance in response to important events during its lifetime.
@warning   The delegate of a \c SMANativeAd object must adopt the \c SMANativeAdDelegate protocol.
*/
@protocol SMANativeAdDelegate <NSObject>

@required
/**
Sent when the native ad loads a creative successfully.

@param nativeAd The native ad sending the message.
@param renderer Native ad renderer that acts as mediator between native raw data assets and visual representation
*/
- (void)nativeAd:(SMANativeAd *_Nonnull)nativeAd didLoadWithAdRenderer:(SMANativeAdRenderer *_Nonnull)renderer;

/**
Sent when the native ad fails to load an ad successfully.

@param nativeAd The native ad sending the message.
@param error    An error object containing details of why the native ad failed to load an ad.
*/
- (void)nativeAd:(SMANativeAd *_Nonnull)nativeAd didFailWithError:(NSError *_Nonnull)error;

/**
A view controller that will be used to present modal view controllers.

@param nativeAd  The native ad sending the message.
@return          A presenting view controller.
*/
- (nonnull UIViewController *)presentingViewControllerForNativeAd:(SMANativeAd *_Nonnull)nativeAd;

/**
Sent when TTL has expired, based on the timestamp from the ad header.

@param nativeAd The native ad sending the message.
*/
- (void)nativeAdDidTTLExpire:(SMANativeAd *_Nonnull)nativeAd;

@optional
/**
Sent when native ad is clicked.

@param nativeAd The native ad sending the message.
*/
- (void)nativeAdDidClick:(SMANativeAd *_Nonnull)nativeAd;

/**
Sent when the user taps on an ad and modal content will be presented (e.g. internal browser).

@param nativeAd The native ad sending the message.
*/
- (void)nativeAdWillPresentModalContent:(SMANativeAd *_Nonnull)nativeAd;

/**
Sent when modal content has been presented.

@param nativeAd The native ad sending the message.
*/
- (void)nativeAdDidPresentModalContent:(SMANativeAd *_Nonnull)nativeAd;

/**
Sent when modal content has been dismissed.

@param nativeAd The native ad sending the message.
*/
- (void)nativeAdDidDismissModalContent:(SMANativeAd *_Nonnull)nativeAd;

/**
Sent when the ad causes the user to leave the application.

@param nativeAd The native ad sending the message.
*/
- (void)nativeAdWillLeaveApplicationFromAd:(SMANativeAd *_Nonnull)nativeAd;

/**
Sent when the ad view impression has been tracked by the sdk.

@param nativeAd The native ad sending the message.
*/
- (void)nativeAdDidImpress:(SMANativeAd *_Nonnull)nativeAd;

@end

/**
\c SMANativeAd class encapsulates public properties and methods and acts as entry point for Native ad request.
*/
@interface SMANativeAd: NSObject

/**
Ad space identifier assigned by Smaato.
*/
@property (nonatomic, copy, readonly, nonnull) NSString *adSpaceId;

/**
The object that acts as the delegate of the native ad.
*/
@property (nonatomic, weak, null_unspecified) id<SMANativeAdDelegate> delegate;

/**
Load new native ad. You must call this method programmatically to initiate loading of the first ad.

@param adRequest       Native ad request that contains ad space identifier assigned by Smaato.

*/
- (void)loadWithAdRequest:(SMANativeAdRequest *_Nonnull)adRequest;

/**
 Advanced version of `loadWithAdSpaceId:` method for ad experts

 @param adRequest       Native ad request that contains sd space identifier
 @param requestParams   Ad request optional parameters for Unified Bidding or Mediation Network support
 */
- (void)loadWithAdRequest:(SMANativeAdRequest *_Nonnull)adRequest requestParams:(SMAAdRequestParams *_Nullable)requestParams;

@end
