//
//  SMAUnityConfiguration.h
//  SmaatoSDKCore
//
//  Created by Stefan Meyer on 01.03.21.
//  Copyright © 2021 Smaato Inc. All rights reserved.
//

#import <SmaatoSDKCore/SMAConfiguration.h>

NS_ASSUME_NONNULL_BEGIN

@interface SMAUnityConfiguration: SMAConfiguration

/// Version of the Unity SDK.
@property (nonatomic, nonnull, copy) NSString *unityVersion;

/**
 Creates a configuration object initialized with the specified publisherId.

 @param publisherId     Publisher identifier assigned by Smaato.
 @param unityVersion    Unity version string.

 @return                A configuration object initialized with the specified publisherId.
 */

- (null_unspecified instancetype)initWithPublisherId:(NSString *_Nonnull)publisherId
                                     andUnityVersion:(NSString *_Nonnull)unityVersion NS_DESIGNATED_INITIALIZER;

@end

NS_ASSUME_NONNULL_END
