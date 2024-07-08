//
//  SMAConfiguration.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 10/07/2018.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <SmaatoSDKCore/SMALoggerConfiguration.h>

/**
 The following constants are provided by \c SMAConfiguration to communicate the max allowed age rating creative classification.
 */
typedef NS_ENUM(NSUInteger, SMAMaxAdContentRating) {
    /// Undefined, Age not set
    kSMAMaxAdContentRatingUndefined = 0,
    /// Age < 9
    kSMAMaxAdContentRatingG,
    /// Age >= 9 and Age < 13
    kSMAMaxAdContentRatingPG,
    /// Age >= 13 and Age < 18
    kSMAMaxAdContentRatingT,
    /// Age >= 18
    kSMAMaxAdContentRatingMA,
};

/**
 A configuration that is used to initialize the SDK.
 */
@interface SMAConfiguration: NSObject

/**
 Set this flag to YES to require secure creatives.
 Secure creative is a creative for which responses have links with HTTPS scheme only and links that redirect to HTTPS only.

 Default value is reflecting ATS settings (true when ATS is enabled, false otherwise), when \c httpsOnly accessor is called before setter.
 In such case, setting \c httpsOnly to different value is not possible anymore.

 @note Setting this flag may get overridden because of your application's Application Transport Security settings,
       so if ATS is enabled and you set this flag to NO, the final value of flag will be YES.
       Alternatively, setting this flag to YES or NO, while ATS is disabled, will be respected.
 */
@property (nonatomic) BOOL httpsOnly;

/**
 If this property is not changed, default logging level is \c kSMALogLevelInfo
 */
@property (nonatomic) SMALogLevel logLevel;

/**
 Set this property to YES if you want to disable log messages from SDK
 */
@property (nonatomic) BOOL loggingDisabled;

/**
 The max allowed age rating creative classification.
 The default value is \c kSMAMaxAdContentRatingUndefined.
 */
@property (nonatomic) SMAMaxAdContentRating maxAdContentRating;

/**
 Publisher identifier assigned by Smaato.
 */
@property (nonatomic, readonly, nonnull) NSString *publisherId;


/**
 Set this property to YES if you not want the SDK to track app foreground sessions.
 */
@property (nonatomic) BOOL disableSessionTracking;

/**
 private
 */

/**
 Creates a configuration object initialized with the specified publisherId.

 @param publisherId     Publisher identifier assigned by Smaato.

 @return                A configuration object initialized with the specified publisherId.
 */

- (null_unspecified instancetype)initWithPublisherId:(NSString *_Nonnull)publisherId NS_DESIGNATED_INITIALIZER;

/**
 Unavailable. Please use class properties/methods instead.
 */
+ (null_unspecified instancetype)new NS_UNAVAILABLE;

/**
 Unavailable. Please use class properties/methods instead.
 */
- (null_unspecified instancetype)init NS_UNAVAILABLE;

@end
