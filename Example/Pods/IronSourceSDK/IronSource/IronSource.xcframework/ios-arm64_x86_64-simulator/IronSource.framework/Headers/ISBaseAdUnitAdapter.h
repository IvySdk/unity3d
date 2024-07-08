//
//  ISBaseAdUnitAdapter.h
//  IronSource
//
//  Copyright © 2023 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ISNetworkInitCallbackProtocol.h"
#import "ISAdapterConfig.h"

@interface ISBaseAdUnitAdapter : NSObject <ISNetworkInitCallbackProtocol>

- (NSString *)getStringValueFromAdapterConfig:(ISAdapterConfig *)adapterConfig
                                       forKey:(NSString *)key;
- (NSString *)getMissingParamErrorStringWithKey:(NSString *)key;

@end
