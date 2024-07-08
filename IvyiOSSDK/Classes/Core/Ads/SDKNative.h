//
//  SDKNative.h
//  Bolts
//
//  Created by 余冰星 on 2017/12/25.
//
#import <IvyiOSSdk/SDKBaseAd.h>
#import <IvyiOSSdk/SDKNativeView.h>
#import <IvyiOSSdk/SDKNativeDelegate.h>
@interface SDKNative : SDKBaseAd<SDKNativeDelegate>
{
    @protected
    NSMutableArray *_adQueue;
    SDK_NATIVE_ADTYPE _nativeAdType;
    NSString *_nibName;
    NSString *_bundleName;
}
-(nullable UIView *)getAdView:(UIViewController *_Nonnull)vc frame:(CGRect)frame configJson:(nonnull NSString *)json;
-(nullable UIView *)showAd:(UIViewController *_Nonnull)vc frame:(CGRect)frame useDesignScale:(BOOL)useDesignScale configJson:(nonnull NSString *)json;
@end
