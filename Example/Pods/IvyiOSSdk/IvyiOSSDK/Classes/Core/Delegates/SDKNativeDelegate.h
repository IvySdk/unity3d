//
//  SDKNativeDelegate.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/28.
//
#ifndef SDKNativeDelegate_h
#define SDKNativeDelegate_h

#import <UIKit/UIKit.h>
#import "SDKConstants.h"
@protocol SDKNativeDelegate<NSObject>

@required
-(int)getImgOrientation;
-(void)adLoaded:(nonnull id)nativeAd;
-(nullable NSMutableDictionary *)nativeAdToDict:(nonnull id)nativeAd;
-(nonnull Class)nativeAdViewClass;
-(nullable UIView *)fetchAdView:(nonnull UIViewController *)vc;
-(nullable NSDictionary *)fetchAdData:(nonnull UIViewController *)vc;
-(void)recycleView:(nonnull UIView *)adView;
-(BOOL)enableClickSmall;
-(void)setNativeAdType:(SDK_NATIVE_ADTYPE)adType;
-(SDK_NATIVE_ADTYPE)nativeAdType;
-(nonnull NSString *)bundleName;
-(void)setBundleName:(nonnull NSString *)bundleName;
-(nonnull NSString *)nibName;
-(void)setNibName:(nonnull NSString *)nibName;
-(nonnull NSString *)defaultBundleName;
-(nonnull NSString *)defaultNibName;
@end
#endif /* SDKNativeDelegate_h */
