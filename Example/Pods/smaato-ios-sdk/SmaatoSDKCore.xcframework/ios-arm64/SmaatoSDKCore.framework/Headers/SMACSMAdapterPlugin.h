//
//  SMACSMAdapterPlugin.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 20.12.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>

static NSString *const kSMACSMNetworkKey = @"network";
static NSString *const kSMACSMAdMobNetworkValue = @"admob";

@interface SMACSMAdapterPlugin: NSObject

+ (void)registerAdapterClass:(Class)adapterClass;

+ (void)unregisterAdapterClass:(Class)adapterClass;

- (NSDictionary<NSString *, NSString *> *)extraParameters NS_REQUIRES_SUPER;

@end
