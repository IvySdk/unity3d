//
//  ISBaseAdapter.h
//  IronSource
//
//  Copyright (c) 2015 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "ISBannerAdapterProtocol.h"
#import "ISRewardedVideoAdapterProtocol.h"
#import "ISInterstitialAdapterProtocol.h"
#import "ISOfferwallAdapterProtocol.h"
#import "ISNativeAdAdapterProtocol.h"
#import "ISBiddingDataAdapterProtocol.h"

#import "ISAdapterConfig.h"
#import "ISLoadWhileShowSupportState.h"
#import "ISConcurrentMutableDictionary.h"

@interface ISBaseAdapter : NSObject <ISInterstitialAdapterProtocol, ISRewardedVideoAdapterProtocol, ISBannerAdapterProtocol, ISOfferwallAdapterProtocol, ISBiddingDataAdapterProtocol, ISNativeAdAdapterProtocol>
{
    @protected ISLoadWhileShowSupportState LWSState;
}

@property (nonatomic, strong) NSString                       *adapterName;
@property (strong, nonatomic) NSString                       *pluginType;
@property (strong, nonatomic) NSString                       *userId;
@property (strong, nonatomic) ISConcurrentMutableDictionary  *adUnitAdapters;

- (instancetype)initAdapter:(NSString *)name;
- (void)earlyInitWithAdapterConfig:(ISAdapterConfig *)adapterConfig;

- (NSString *)sdkVersion;
- (NSString *)version;
- (NSString *)dynamicUserId;

// to be used by adapters that implement each ad unit separately
- (ISLoadWhileShowSupportState)getLWSSupportState:(ISAdapterConfig *)adapterConfig;

- (void)setRewardedVideoAdapter:(id<ISRewardedVideoAdapterProtocol>)rewardedVideoAdapter;
- (void)setInterstitialAdapter:(id<ISInterstitialAdapterProtocol>)interstitialAdapter;
- (void)setBannerAdapter:(id<ISBannerAdapterProtocol>)bannerAdapter;
- (void)setNativeAdAdapter:(id<ISNativeAdAdapterProtocol>)nativeAdAdapter;

- (id<ISRewardedVideoAdapterProtocol>)getRewardedVideoAdapter;
- (id<ISInterstitialAdapterProtocol>)getInterstitialAdapter;
- (id<ISBannerAdapterProtocol>)getBannerAdapter;
- (id<ISNativeAdAdapterProtocol>)getNativeAdAdapter;

// check if the network supports adaptive banners
- (BOOL)getAdaptiveBannerSupport;

@end
