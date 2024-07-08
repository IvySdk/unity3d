//
//  SDKNativeView.h
//  Bolts
//
//  Created by 余冰星 on 2017/12/28.
//

#import <UIKit/UIKit.h>
#import <IvyiOSSdk/SDKNativeDelegate.h>
#import <IvyiOSSdk/CALayer+XibBorderColor.h>
#import <IvyiOSSdk/UIKit+SDKNetworking.h>
@interface SDKNativeView : UIView<CAAnimationDelegate>
{
    @protected
    UIViewController *_vc;
    NSDictionary *_config;
    UIView *_nibView;
    id _nativeAd;
    id<SDKNativeDelegate> _owner;
}
@property(nonatomic, nullable, readwrite, retain)UIViewController *vc;
@property(nonatomic, nullable, readwrite, retain)id<SDKNativeDelegate> owner;

-(nullable instancetype)initWithOwner:(nonnull id<SDKNativeDelegate>)owner;
-(void)setViewsWithConfig:(nonnull NSDictionary *)config;
-(void)onViewsDidShown;
-(void)setViewWithConfig:(nonnull UIView *)view config:(nonnull NSDictionary *)config keyName:(nullable NSString *)key;
-(void)setViewBg:(nonnull UIImageView *)view config:(nonnull NSDictionary *)config keyName:(nullable NSString *)key;
-(void)loadConfig:(nonnull NSString *)json frame:(CGRect)frame useDesignScale:(BOOL)useDesignScale;
-(void)loadNativeAd:(nullable id)nativeAd;
-(void)close;
@end
