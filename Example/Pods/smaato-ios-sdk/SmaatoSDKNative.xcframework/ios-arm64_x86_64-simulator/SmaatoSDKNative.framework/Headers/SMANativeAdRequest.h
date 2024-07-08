//
//  SMANativeAdRequest.h
//  SmaatoSDKNative
//
//  Created by siarhei on 20.04.20.
//  Copyright © 2020 Smaato Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
\SMANativeAdRequest class contains settings and data for Native ads request customization
*/
@interface SMANativeAdRequest: NSObject

/**
 Ad space identifier assigned by Smaato
 */
@property (nonatomic, copy, nonnull, readonly) NSString *adSpaceId;

/**
A set of strings defining the required assets for the native ad object.
*/
@property (nonatomic, nullable) NSSet *requiredAssets;

/**
If the value is not set, SDK will download the privacy URL, Defaults to YES
*/
@property (nonatomic) BOOL shouldFetchPrivacy;

/**
 Set this property to YES if you want to get only URLs on image creatives and download them yourself. Set this property to NO if you want to
 get downloaded image creatives. Defaults to YES.
 */
@property (nonatomic) BOOL returnUrlsForImageAssets;

/**
 Set this property to YES if you allow multiple main images for Native ad. Defaults to NO.
 */
@property (nonatomic) BOOL allowMultipleImages;


- (instancetype _Nonnull)init NS_UNAVAILABLE;

- (instancetype _Nonnull)new NS_UNAVAILABLE;

/**
 Create Native ad request

 @param adspaceId  Ad space identifier assigned by Smaato
 @return           Native ad request
 */
- (instancetype _Nullable)initWithAdSpaceId:(NSString *_Nonnull)adspaceId NS_DESIGNATED_INITIALIZER;

@end
