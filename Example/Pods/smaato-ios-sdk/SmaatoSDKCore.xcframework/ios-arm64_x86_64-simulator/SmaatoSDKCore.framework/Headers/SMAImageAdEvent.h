//
//  SMAImageAdEvent.h
//  SmaatoSDKCore
//
//  Created by Stefan Meyer on 02.06.21.
//  Copyright © 2021 Smaato Inc. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <SmaatoSDKCore/SMAModelObject.h>

NS_ASSUME_NONNULL_BEGIN

@class SMAViewabilityVerificationResource;

typedef NS_ENUM(NSUInteger, SMAImageAdEventType) {
    // The OpenMeasurement native tags for the native ad
    SMAImageAdEventTypeOM = 555
};

@interface SMAImageAdEvent: SMAModelObject

@property (nonatomic, readonly) SMAImageAdEventType eventType;
@property (nonatomic, copy, readonly) NSURL *url;
@property (nonatomic, copy, readonly) NSDictionary *extension;
@property (nonatomic, copy) NSArray<SMAViewabilityVerificationResource *> *resources;
@end

NS_ASSUME_NONNULL_END
