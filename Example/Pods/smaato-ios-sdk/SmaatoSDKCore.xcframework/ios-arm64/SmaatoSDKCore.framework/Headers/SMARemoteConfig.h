//
//  SMARemoteConfig.h
//  SmaatoSDKCore
//
//  Created by ashu on 11/07/22.
//  Copyright © 2022 Smaato Inc. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SMARemoteConfigParser.h"

@interface SMARemoteConfig: NSObject

@property (strong, nonatomic) SMARemoteConfigParser *remoteConfigParser;

+ (instancetype)sharedInstanceRemoteConfig;
@end
