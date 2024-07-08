//
//  SDKObjectPool.h
//  Pods
//
//  Created by 余冰星 on 2017/6/15.
//
//

#import <Foundation/Foundation.h>

typedef id _Nonnull (^CreateObjectBlock)(NSError *_Nullable* _Nullable outError);

@interface SDKObjectPool : NSObject
+ (instancetype _Nonnull) poolWithCreateBlock:(nonnull CreateObjectBlock) createBlock;

- (nullable id) objectFromPoolWithError:(NSError *_Nullable* _Nullable) outError;

- (void) returnObjectToPool:(nonnull id) object;

@property (retain, readonly, nonnull) NSArray *allObjects;

@end
