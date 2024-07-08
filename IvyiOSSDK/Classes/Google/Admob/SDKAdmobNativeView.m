//
//  SDKFacebookNativeView.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/27.
//

#import "SDKAdmobNativeView.h"
#import <GoogleMobileAds/GADNativeAd.h>
#import <IvyiOSSdk/SDKDefine.h>
@implementation SDKAdmobNativeView
-(void)loadNativeAd:(GADNativeAd *)nativeAd
{
    [super loadNativeAd:nativeAd];
    
    self.adView.nativeAd = nativeAd;
    BOOL hasVideo = nativeAd.mediaContent.hasVideoContent;
    if (hasVideo) {
        float ratio = nativeAd.mediaContent.aspectRatio;
        //检查宽高比和配置的横竖屏不符合就返回false
        if (ratio > 1 && [_owner getImgOrientation] == SDK_ORIENTATION_PORTRAIT) {
            hasVideo = false;
        } else if (ratio < 1 && [_owner getImgOrientation] == SDK_ORIENTATION_LANDSCAPE) {
            hasVideo = false;
        }
    }
    if (hasVideo) {
        // The video controller has content. Show the media view.
        self.adView.mediaView.hidden = NO;
        self.adView.imageView.hidden = YES;
        
        // By acting as the delegate to the GADVideoController, this ViewController
        // receives messages about events in the video lifecycle.
        nativeAd.mediaContent.videoController.delegate = self;
    } else {
        // If the ad doesn't contain a video asset, the first image asset is shown
        // in the image view. The existing lower priority height constraint is used.
        self.adView.mediaView.hidden = YES;
        self.adView.imageView.hidden = NO;
        
        GADNativeAdImage *firstImage = nativeAd.images.firstObject;
        ((UIImageView *)self.adView.imageView).image = firstImage.image;
    }
    
    if (self.adView.iconView && nativeAd.icon) {
        ((UIImageView *)self.adView.iconView).image = nativeAd.icon.image;
    }
    if (self.adView.headlineView) {
        ((UILabel *)self.adView.headlineView).text = nativeAd.headline;
    }
    if (self.adView.bodyView) {
        ((UILabel *)self.adView.bodyView).text = nativeAd.body;
    }
    if (self.adView.adChoicesView) {
        DLog(@"[adlog]%@", self.adView.adChoicesView);
    }
    if (self.adView.callToActionView) {
        [(UIButton *)self.adView.callToActionView setTitle:nativeAd.callToAction forState:UIControlStateNormal];
        // In order for the SDK to process touch events properly, user interaction
        // should be disabled.
        self.adView.callToActionView.userInteractionEnabled = NO;
    }
    if (self.closeButton) {
        [self.closeButton addTarget:self action:@selector(close) forControlEvents:UIControlEventTouchUpInside];
    }
}

-(void)setViewsWithConfig:(NSDictionary *)config
{
    [self setViewBg:self.bgImageView config:config keyName:nil];
    [self setViewWithConfig:self.adView.iconView config:config keyName:@"icon"];
    [self setViewWithConfig:self.adView.imageView config:config keyName:@"image"];
    [self setViewWithConfig:self.adView.mediaView config:config keyName:@"image"];
    [self setViewWithConfig:self.adView.headlineView config:config keyName:@"title"];
    [self setViewWithConfig:self.adView.bodyView config:config keyName:@"desc"];
    [self setViewWithConfig:self.adView.callToActionView config:config keyName:@"action"];
    [self setViewWithConfig:self.adCornerView config:config keyName:@"ad"];
    [self setViewWithConfig:self.closeButton config:config keyName:@"close"];
    for (id v in self.adView.subviews) {
        NSString *className = NSStringFromClass([v class]);
        if ([className isEqualToString:@"GADOUnifiedNativeAdView"] || [className isEqualToString:@"GADNUnifiedNativeAdView"]) {
            [v removeConstraints:[v constraints]];
            [v setHidden:YES];
            break;
        }
    }
}

-(void)onViewsDidShown
{
    for (id v in self.adView.subviews) {
        NSString *className = NSStringFromClass([v class]);
        if ([className isEqualToString:@"GADOUnifiedNativeAdView"] || [className isEqualToString:@"GADNUnifiedNativeAdView"]) {
            [v removeConstraints:[v constraints]];
            [self setViewWithConfig:v config:_config keyName:@"adchoice"];
            break;
        }
    }
}

-(void)setVc:(UIViewController *)vc
{
    [super setVc:vc];
}

- (void)videoControllerDidEndVideoPlayback:(GADVideoController *)videoController {
}
@end
