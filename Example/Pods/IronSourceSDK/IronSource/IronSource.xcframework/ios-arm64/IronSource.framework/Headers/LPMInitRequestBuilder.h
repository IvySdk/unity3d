//
//  LPMInitRequestBuilder.h
//  IronSource
//
//  Copyright © 2024 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LPMInitRequest.h"

NS_ASSUME_NONNULL_BEGIN

@interface LPMInitRequestBuilder : NSObject

- (instancetype)init NS_UNAVAILABLE;
- (instancetype)new NS_UNAVAILABLE;

- (instancetype)initWithAppKey:(NSString *)appKey;

- (LPMInitRequest *)build;

- (LPMInitRequestBuilder *)withLegacyAdFormats:(NSArray<NSString *> *)legacyAdFormats;

- (LPMInitRequestBuilder *)withUserId:(NSString *)userId;

@end

NS_ASSUME_NONNULL_END
