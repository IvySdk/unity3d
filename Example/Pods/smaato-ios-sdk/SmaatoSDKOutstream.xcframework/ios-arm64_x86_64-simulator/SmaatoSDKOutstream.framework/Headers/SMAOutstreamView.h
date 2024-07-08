//
//  SMAOutstreamView.h
//  SmaatoSDKOutstream
//
//  Created by Stefan Meyer on 27.04.21.
//

#import <SmaatoSDKBanner/SmaatoSDKBanner.h>
#import "SMAOutstreamTypes.h"

NS_ASSUME_NONNULL_BEGIN

@interface SMAOutstreamView: SMABannerView

/**
 Load new outstream ad. You must call this method programmatically to initiate loading of the first ad.
 The automatic refresh is controlled by the \c autoreloadInterval property. Final result as a rendered outstream view or
 an error will be returned through \c SMABannerViewDelegate delegate callback methods.

 @param adSpaceId       Ad space identifier assigned by Smaato.
 @param adSize          Banner ad size.

 @warning   Please ensure that the \c SMABannerView has enough space on the screen to display an ad of the requested size.
 Otherwise, the \c SMABannerView won't display the ad and impressions won't be counted.
 */
- (void)loadWithAdSpaceId:(NSString *)adSpaceId outstreamAdSize:(SMAOutstreamAdSize)adSize;

/**
 Advanced version of `loadWithAdSpaceId:adSize:` method for ad experts

 @param adSpaceId       Ad space identifier assigned by Smaato.
 @param adSize          Banner ad size.
 @param requestParams   Ad request optional parameters for Unified Bidding or Mediation Network support

 @warning   Please ensure that the \c SMAOutstreamView has enough space on the screen to display an ad of the requested size.
 Otherwise, the \c SMAOutstreamView won't display the ad and impressions won't be counted.
 */
- (void)loadWithAdSpaceId:(NSString *)adSpaceId
          outstreamAdSize:(SMAOutstreamAdSize)adSize
            requestParams:(SMAAdRequestParams *_Nullable)requestParams;

@end

NS_ASSUME_NONNULL_END
