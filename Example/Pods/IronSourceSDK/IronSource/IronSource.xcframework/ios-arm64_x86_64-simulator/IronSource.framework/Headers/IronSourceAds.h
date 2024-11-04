//
//  IronSourceAds.h
//  IronSource
//
//  Copyright © 2024 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ISAAdFormat.h"
#import "ISAInitRequest.h"

NS_ASSUME_NONNULL_BEGIN

/**
 Defines the completion callback for IronSourceAds SDK initialization.
 */
typedef void (^ISAInitCompletionHandler)(BOOL success, NSError *_Nullable error);

/**
 Object used to initialize IronSourceAds network.
 */
@interface IronSourceAds : NSObject

- (instancetype)init NS_UNAVAILABLE;
- (instancetype)new NS_UNAVAILABLE;

/**
 Initializes IronSourceAds SDK.

 @param request The initialization request containing the necessary configurations for
 initialization.
 @param completion The completion for initialization. The completion will be invoked on the main
 thread.
 */
+ (void)initWithRequest:(ISAInitRequest *)request completion:(ISAInitCompletionHandler)completion;

@end

NS_ASSUME_NONNULL_END
