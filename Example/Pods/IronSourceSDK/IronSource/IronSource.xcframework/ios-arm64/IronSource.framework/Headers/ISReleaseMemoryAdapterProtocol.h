//
//  ISReleaseMemoryAdapterProtocol.h
//  IronSource
//
//  Copyright © 2023 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ISAdapterConfig.h"

@protocol ISReleaseMemoryAdapterProtocol <NSObject>

@optional

- (void)releaseMemoryWithAdapterConfig:(ISAdapterConfig *)adapterConfig;

@end
