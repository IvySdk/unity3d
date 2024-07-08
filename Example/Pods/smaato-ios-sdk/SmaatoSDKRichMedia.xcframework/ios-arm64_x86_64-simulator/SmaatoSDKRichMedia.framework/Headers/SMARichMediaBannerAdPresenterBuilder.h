//
//  SMARichMediaBannerAdPresenterBuilder.h
//  SmaatoSDKRichMedia
//
//  Created by Smaato Inc on 20/01/2019.
//  Copyright © 2019 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <SmaatoSDKCore/SMAAPIContext.h>
#import <SmaatoSDKCore/SMAAdPresenterBuilding.h>

@interface SMARichMediaBannerAdPresenterBuilder: NSObject <SMAAdPresenterBuilding>

/**
 Unavailable.
 Use \c initWithApiContext: to create instances of \c SMARichMediaBannerAdPresenterBuilder class.
 */
- (instancetype)init;

/**
 Designated initializer. Validates \c SMAAPIContext and creates instance of \c SMARichMediaBannerAdPresenterBuilder
 class with the provided \c SMAAPIAdRequst and \c SMAAPIResponse.

 @param apiContext API context with ad request and response. Must not be \c nil and request must be of \c RichMedia Ad format.
 @return The initialized \c SMARichMediaBannerAdPresenterBuilder or \c nil on failure.
 */
- (instancetype)initWithApiContext:(SMAAPIContext *)apiContext NS_DESIGNATED_INITIALIZER;

@end
