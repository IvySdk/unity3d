//
//  SMALocation.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 07/09/2018.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>

/**
 A \c SMALocation object contains the geographical location and altitude of a user.
 */
@interface SMALocation: NSObject <NSCopying>

/**
 The latitude associated with the location, specified using the WGS 84 reference frame.
 */
@property (nonatomic, readonly) double latitude;

/**
 The longitude associated with the location, specified using the WGS 84 reference frame.
 */
@property (nonatomic, readonly) double longitude;

/**
 An estimate of the radius (in meters) of the region which the device is visiting.
 */
@property (nonatomic, readonly) double horizontalAccuracy;

/**
 Returns the timestamp when this location was determined.
 */
@property (nonatomic, copy, readonly, nonnull) NSDate *timestamp;

/**
 Returns a Boolean value indicating whether the specified coordinate is valid.

 A coordinate is considered invalid if its latitude is not in [-90..90] degrees range or its longitude is not in [-180..180] degrees range.
 */
@property (nonatomic, readonly, getter=isValid) BOOL valid;

/**
 Creates a location object with the specified latitude and longitude.

 @param latitude              The latitude of the geographical coordinate.
 @param longitude             The longitude of the geographical coordinate.
 @param horizontalAccuracy    An estimate of the radius (in meters) of the region which the device is visiting.
 @param timestamp             The timestamp when this location was determined.

 @return            A location object initialized with the specified geographical coordinate.
 */
- (null_unspecified instancetype)initWithLatitude:(double)latitude
                                        longitude:(double)longitude
                               horizontalAccuracy:(double)horizontalAccuracy
                                        timestamp:(NSDate *_Nonnull)timestamp NS_DESIGNATED_INITIALIZER;

+ (null_unspecified instancetype)new NS_UNAVAILABLE;
- (null_unspecified instancetype)init NS_UNAVAILABLE;

@end
