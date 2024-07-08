//
//  SDKNativeModule.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/26.
//

#import "SDKNativeModule.h"
#import <IvyiOSSdk/SDKNative.h>
@implementation SDKNativeModule
{
    @private
    UIView *_adView;
}
-(instancetype)init
{
    _classSuffix = @"Native";
    _adType = SDK_ADTYPE_NATIVE;
    return [super init];
}

-(nullable UIView *)getNativeAdView:(nonnull UIViewController *)vc json:(nonnull NSString *)json
{
    return [self getNativeAdViewWithFrame:vc frame:CGRectMake(0, 0, -1, -1) configJson:json];
}

-(nullable UIView *)getNativeAdViewWithFrame:(nonnull UIViewController *)vc frame:(CGRect)frame configJson:(nonnull NSString *)json
{
    SDKNative *ad = (SDKNative *)[self getAvailableAd];
    if(ad) {
        _adView = [ad getAdView:vc frame:frame configJson:json];
    }
    return _adView;
}

-(void)showNativeAd:(nonnull UIViewController *)vc x:(float)x y:(float)y configJson:(nonnull NSString *)json
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [self closeNativeAd];
        SDKNative *ad = (SDKNative *)[self getAvailableAd];
        if(ad) {
            self->_adView = [ad showAd:vc frame:CGRectMake(x, y, -1, -1) useDesignScale:YES configJson:json];
        }
    });
}

-(void)showNativeAdWithFrame:(nonnull UIViewController *)vc frame:(CGRect)frame configJson:(nonnull NSString *)json
{
    dispatch_async(dispatch_get_main_queue(), ^{
        [self closeNativeAd];
        SDKNative *ad = (SDKNative *)[self getAvailableAd];
        if(ad) {
            self->_adView = [ad showAd:vc frame:frame useDesignScale:NO configJson:json];
        }
    });
}

-(nullable NSDictionary *)fetchNativeAd
{
    SDKNative *ad = (SDKNative *)[self getAvailableAd];
    if (ad) {
        return [ad fetchAdData:_rootVC];
    }
    return nil;
}

-(nullable UIView *)fetchNativeView
{
    SDKNative *ad = (SDKNative *)[self getAvailableAd];
    if (ad) {
        return [ad fetchAdView:_rootVC];
    }
    return nil;
}

-(void)closeNativeAd
{
    if (_adView) {
        [_adView removeFromSuperview];
        _adView = nil;
    }
}
#pragma mark -
#pragma mark Admob & Facebook Method
-(void)setBundleName:(NSString *)bundleName
{
    if(adArr.count > 0) {
        for (int i=0; i<adArr.count; i++) {
            id ad = [adArr objectAtIndex:i];
            if ([ad respondsToSelector:@selector(setBundleName:)]) {
                [ad setBundleName:bundleName];
            }
        }
    }
}

@end
