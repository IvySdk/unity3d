//
//  SDKAdDelegate.h
//  Pods
//
//  Created by 余冰星 on 2017/9/19.
//
//

#ifndef SDKAdDelegate_h
#define SDKAdDelegate_h

#import <Foundation/Foundation.h>

@protocol SDKAdDelegate<NSObject>

@optional

/// Tells the delegate that the ad failed to load.
- (void)adFailed:(NSString *)tag adType:(int)adType forError:(NSError *)error;

/// Tells the delegate that a ad was loaded.
- (void)adLoaded:(NSString *)tag adType:(int)adType;

/// Tells the delegate that the ad did shown.
- (void)adDidShown:(NSString *)tag adType:(int)adType;

- (void)adShowFailed:(NSString *)tag adType:(int)adType;

/// Tells the delegate that the ad closed.
- (void)adDidClose:(NSString *)tag adType:(int)adType;

/// Tells the delegate that the ad did click.
- (void)adDidClick:(NSString *)tag adType:(int)adType;

- (void)adReward:(NSString *)tag rewardId:(int)rewardId;

- (void)adNeedReload:(NSString *)tag adType:(int)adType;

- (void)deliciousIconClick:(NSString *)bannerDownPath jumpURL:(NSString *)url;

- (void)adPaid:(NSString *)dict;

@end

#endif /* SDKAdDelegate_h */
