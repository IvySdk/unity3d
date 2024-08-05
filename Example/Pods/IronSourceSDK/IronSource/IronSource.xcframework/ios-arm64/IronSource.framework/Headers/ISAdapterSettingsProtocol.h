//
//  ISAdapterSettingsProtocol.h
//  IronSource
//
//  Created by Yonti Makmel on 11/10/2021.
//  Copyright © 2021 IronSource. All rights reserved.
//

#import "ISAdapterConfig.h"
#import "ISLoadWhileShowSupportState.h"

@protocol ISAdapterSettingsProtocol <NSObject>

- (ISLoadWhileShowSupportState)getLoadWhileShowSupportedStateWithAdapterConfig:
    (ISAdapterConfig*)adapterConfig;

@end
