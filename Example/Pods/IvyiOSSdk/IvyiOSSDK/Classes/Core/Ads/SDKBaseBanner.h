//
//  SDKBaseBanner.h
//  BGFMDB
//
//  Created by 余冰星 on 2020/3/2.
//

#import <IvyiOSSdk/SDKBaseAd.h>

NS_ASSUME_NONNULL_BEGIN

@interface SDKBaseBanner : SDKBaseAd
{
    @protected
    UIView *_adView;
    BOOL _isRect;
}
@property (nonatomic, assign, readwrite) int reloadInterval;
@property (nonatomic, nullable, strong) UIView *adView;
-(void)onBannerShow:(nonnull UIView *)view;
-(void)close;
@end

NS_ASSUME_NONNULL_END
