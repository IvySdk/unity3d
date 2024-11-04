//
//  ISAdUnitAdapterProtocol.h
//  IronSource
//
//  Copyright © 2023 IronSource. All rights reserved.
//

#import "ISNetworkInitCallbackProtocol.h"
#import "ISReleaseMemoryAdapterProtocol.h"

@protocol ISAdUnitAdapterProtocol <ISReleaseMemoryAdapterProtocol, ISNetworkInitCallbackProtocol>

@end
