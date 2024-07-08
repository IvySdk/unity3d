//
//  SMANativeAdAssets.h
//  SmaatoSDKNative
//
//  Created by Smaato Inc on 24.01.20.
//  Copyright © 2020 Smaato Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

@class SMANativeImage;

/**
\c SMANativeAdAssets class contains raw data (values) from Native ad response.
*/
@interface SMANativeAdAssets: NSObject

/**
Text\string that should be rendered as main text (or description) on view with native ad
*/
@property (nonatomic, copy, readonly, nullable) NSString *mainText;

/**
Text\string that should be rendered as title on view with native ad
*/
@property (nonatomic, copy, readonly, nullable) NSString *title;

/**
Text\string that should be rendered as special label\mark about ads content
*/
@property (nonatomic, copy, readonly, nullable) NSString *sponsored;

/**
Text\string that should be rendered as text of Click-To-Action button on view with native ad
*/
@property (nonatomic, copy, readonly, nullable) NSString *cta;

/**
List of image creative URLs (up to 3)  that should be rendered as main (large) image control(s) on view with native ad
*/
@property (nonatomic, copy, readonly, nullable) NSArray<SMANativeImage *> *images;

/**
URL of image creative that should be rendered as small icon control on view with native ad
*/
@property (nonatomic, readonly, nullable) SMANativeImage *icon;

/**
Digital value (integer value in interval 1..5) that should be rendered as stars rating (or other way) control on view with native ad
*/
@property (nonatomic, readonly) CGFloat rating;

/**
Returns true if the native response contains extra ad markup
*/
@property (nonatomic, readonly) BOOL hasAdMarkup;

@end
