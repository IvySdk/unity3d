//
//  LPMInitRequest.h
//  IronSource
//
//  Copyright © 2024 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface LPMInitRequest : NSObject

- (instancetype)init NS_UNAVAILABLE;
- (instancetype)new NS_UNAVAILABLE;

- (instancetype)initWithAppKey:(NSString *)appKey
               legacyAdFormats:(NSArray<NSString *> *)legacyAdFormats
                        userId:(nullable NSString *)userId;

@property(readonly, nonatomic) NSString *appKey;
@property(readonly, nonatomic) NSArray<NSString *> *legacyAdFormats;
@property(readonly, nonatomic, nullable) NSString *userId;

@end

NS_ASSUME_NONNULL_END
