//
//  ISAdUnitAdapterProtocol.h
//  IronSource
//
//  Copyright © 2023 IronSource. All rights reserved.
//

#import "ISReleaseMemoryAdapterProtocol.h"
#import "ISNetworkInitCallbackProtocol.h"

@protocol ISAdUnitAdapterProtocol <ISReleaseMemoryAdapterProtocol, ISNetworkInitCallbackProtocol>

@end

