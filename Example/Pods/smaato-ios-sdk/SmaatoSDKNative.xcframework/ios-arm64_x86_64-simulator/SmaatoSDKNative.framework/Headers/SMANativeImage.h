//
//  SMANativeImage.h
//  SmaatoSDKNative
//
//  Created by siarhei on 20.04.20.
//  Copyright © 2020 Smaato Inc. All rights reserved.
//

#import <UIKit/UIKit.h>

/**
\SMANativeImage class represents image object of Native ads assets
*/
@interface SMANativeImage: NSObject

/**
URL of image where it might be downloaded from
*/
@property (nonatomic, copy, nonnull, readonly) NSURL *url;

/**
Image width
*/
@property (nonatomic, readonly) NSUInteger width;

/**
Image height
*/
@property (nonatomic, readonly) NSUInteger height;

/**
Downloaded image as binary data if it is available and you specified it through \c SMANativeAdRequest object. Might be \c nil.
*/
@property (nonatomic, nullable, readonly) UIImage *image;

- (instancetype _Nullable)initWithURL:(NSURL *_Nullable)url width:(CGFloat)width height:(CGFloat)height;

@end
