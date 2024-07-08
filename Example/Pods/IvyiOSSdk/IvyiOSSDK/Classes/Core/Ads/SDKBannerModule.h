//
//  SDKBaseBannerAdapter.h
//  Pods
//
//  Created by 余冰星 on 2017/9/19.
//
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/SDKBaseBanner.h>
#import <IvyiOSSdk/SDKBaseAdModule.h>
#import <IvyiOSSdk/SDKConstants.h>
@interface SDKBannerModule : SDKBaseAdModule
-(nullable UIView *)getBannerView:(bool)show;
-(nullable UIView *)getBannerViewWithFrame:(CGRect)frame show:(bool)show;
-(void)showBannerAtPos:(SDK_BANNER_POS)pos;
-(void)showBannerWithFrame:(CGRect)frame;
+(void)closeBanner;
+(BOOL)isBannerShowing;
@end
