//
//  ISBaseAdAdapter.h
//  IronSource
//
//  Created by Yonti Makmel on 27/04/2021.
//  Copyright © 2021 ironSource. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ISAdData.h"
#import "ISAdUnit.h"
#import "ISAdapterAdDelegate.h"
#import "ISAdapterBaseProtocol.h"
#import "ISAdapterConfig.h"

NS_ASSUME_NONNULL_BEGIN

@interface ISBaseAdAdapter : NSObject

@property(nonatomic) ISAdUnit *adUnit;
@property(nonatomic, readonly) ISAdapterConfig *adapterConfig;
@property(nonatomic, readonly, nullable) NSUUID *adUnitObjectId;

/// @param adUnit the ad unit represented by the adapter
/// @param adapterConfig the configuration relevant for the adapter instance
- (instancetype)initWithAdUnit:(ISAdUnit *)adUnit adapterConfig:(ISAdapterConfig *)adapterConfig;

/// @param adUnit the ad unit represented by the adapter
/// @param adapterConfig the configuration relevant for the adapter instance
/// @param adUnitObjectId the object id for the ad loaded

- (instancetype)initWithAdUnit:(ISAdUnit *)adUnit
                 adapterConfig:(ISAdapterConfig *)adapterConfig
                adUnitObjectId:(nullable NSUUID *)adUnitObjectId;

/// the network sdk version
- (nullable id<ISAdapterBaseProtocol>)getNetworkAdapter;

/// When the adapter needs to release certain elements to avoid memory leaks before being destroyed
- (void)releaseMemory;

@end

NS_ASSUME_NONNULL_END
