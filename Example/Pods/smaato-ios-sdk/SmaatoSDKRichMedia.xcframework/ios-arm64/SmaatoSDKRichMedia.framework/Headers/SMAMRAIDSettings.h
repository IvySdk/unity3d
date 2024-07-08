//
//  SMAMRAIDSettings.h
//  SmaatoSDKRichMedia
//
//  Created by Smaato Inc on 08.11.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>

@interface SMAMRAIDSettings: NSObject

@property (nonatomic, readonly) NSUInteger mraidViewObservingFrequency;
@property (nonatomic, readonly) NSUInteger mraidViewLoadingCheckFrequency;
@property (nonatomic, readonly) NSTimeInterval mraidViewLoadingCompletionExpectationTime;
@property (nonatomic, readonly) CGFloat mraidViewForbiddenDimension;
@property (nonatomic, readonly) NSString *mraidMajorVersion;

@end
