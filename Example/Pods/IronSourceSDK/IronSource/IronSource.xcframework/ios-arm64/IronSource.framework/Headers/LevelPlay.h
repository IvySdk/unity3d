//
//  LevelPlay.h
//  IronSource
//
//  Copyright © 2024 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LPMConfiguration.h"
#import "LPMInitRequest.h"

NS_ASSUME_NONNULL_BEGIN

typedef void (^LPMInitCompletionHandler)(LPMConfiguration *_Nullable config,
                                         NSError *_Nullable error);

@interface LevelPlay : NSObject

- (instancetype)init NS_UNAVAILABLE;
- (instancetype)new NS_UNAVAILABLE;

+ (void)initWithRequest:(LPMInitRequest *)request completion:(LPMInitCompletionHandler)completion;

@end

NS_ASSUME_NONNULL_END
