//
//  SDKBaseInit.h
//  FBSDKCoreKit
//
//  Created by 余冰星 on 2019/8/8.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface SDKBaseInit : NSObject
- (void)doInit:(nullable NSDictionary *)data onComplete:(nullable dispatch_block_t)onComplete;
@end

NS_ASSUME_NONNULL_END
