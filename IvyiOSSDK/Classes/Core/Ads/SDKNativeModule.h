//
//  SDKNativeModule.h
//  Bolts
//
//  Created by 余冰星 on 2017/12/26.
//

#import <IvyiOSSdk/SDKBaseAdModule.h>
@interface SDKNativeModule : SDKBaseAdModule
-(nullable UIView *)getNativeAdView:(nonnull UIViewController *)vc json:(nonnull NSString *)json;
-(nullable UIView *)getNativeAdViewWithFrame:(nonnull UIViewController *)vc frame:(CGRect)frame configJson:(nonnull NSString *)json;
-(void)showNativeAd:(nonnull UIViewController *)vc x:(float)x y:(float)y configJson:(nonnull NSString *)json;
-(void)showNativeAdWithFrame:(nonnull UIViewController *)vc frame:(CGRect)frame configJson:(nonnull NSString *)json;
-(nullable NSDictionary *)fetchNativeAd;
-(nullable UIView *)fetchNativeView;
-(void)closeNativeAd;
-(void)setBundleName:(nonnull NSString *)bundleName;
@end
