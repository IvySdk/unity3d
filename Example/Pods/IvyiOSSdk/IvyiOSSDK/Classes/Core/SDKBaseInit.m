//
//  SDKBaseInit.m
//  FBSDKCoreKit
//
//  Created by 余冰星 on 2019/8/8.
//

#import "SDKBaseInit.h"
#import "SDKDefine.h"
@implementation SDKBaseInit
-(void)doInit:(NSDictionary *)data onComplete:(dispatch_block_t)onComplete
{
    DLog(@"[sdk] [%@ : init]", NSStringFromClass(self.class));
}
@end
