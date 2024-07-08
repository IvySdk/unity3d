//
//  SDKOurNativeView.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/28.
//

#import "SDKOurNativeView.h"
#import <IvyiOSSdk/SDKFacade.h>
@implementation SDKOurNativeView

-(instancetype)initWithOwner:(id<SDKNativeDelegate>)owner
{
    self = [super initWithOwner:owner];
    if ([owner enableClickSmall]) {
        if (self.closeButton) {
            [self.closeButton addTarget:self action:@selector(close) forControlEvents:UIControlEventTouchUpInside];
        }
        if (self.adCoverMediaView) {
            [self.adCoverMediaView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
        }
        if (self.adIconImageView) {
            [self.adIconImageView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
        }
        if (self.adTitleLabel) {
            [self.adTitleLabel addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
        }
        if (self.adBodyLabel) {
            [self.adBodyLabel addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
        }
        if (self.adCallToActionButton) {
            [self.adCallToActionButton addTarget:self action:@selector(onClick:) forControlEvents:UIControlEventTouchUpInside];
        }
    } else {
        [self addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
    }
    return self;
}

-(void)setViewsWithConfig:(NSDictionary *)config
{
    [self setViewBg:self.bgImageView config:config keyName:nil];
    [self setViewWithConfig:self.adIconImageView config:config keyName:@"icon"];
    [self setViewWithConfig:self.adCoverMediaView config:config keyName:@"image"];
    [self setViewWithConfig:self.adTitleLabel config:config keyName:@"title"];
    [self setViewWithConfig:self.adBodyLabel config:config keyName:@"desc"];
    [self setViewWithConfig:self.adCallToActionButton config:config keyName:@"action"];
    [self setViewWithConfig:self.adCornerView config:config keyName:@"ad"];
    [self setViewWithConfig:self.closeButton config:config keyName:@"close"];
}

-(void)loadNativeAd:(id)nativeAd
{
    [super loadNativeAd:nativeAd];
    if (nativeAd) {
        NSDictionary *ad = nativeAd;
        if (self.adCoverMediaView) {
            [self.adCoverMediaView setImageWithURL:[NSURL URLWithString:[ad objectForKey:@"native"]]];
            self.adCoverMediaView.enableFade = YES;
        }
        if (self.adTitleLabel) {
            self.adTitleLabel.text = [ad objectForKey:@"name"];
        }
        if (self.adBodyLabel) {
            self.adBodyLabel.text = [ad objectForKey:@"desc"];
        }
        if (self.adIconImageView) {
            [self.adIconImageView setImageWithURL:[NSURL URLWithString:[ad objectForKey:@"icon"]]];
            self.adIconImageView.enableFade = YES;
        }
//        if (self.adCallToActionButton) {
//            [self.adCallToActionButton setTitle:nativeAd.callToAction forState:UIControlStateNormal];
//        }
        
        [SDKFacade trackCrossPromoteImpression:[nativeAd objectForKey:@"appstoreid"] type:SDK_ADTYPE_NATIVE];
    }
}

-(void)onClick:(id)sender
{
    if (_nativeAd) {
        [SDKFacade trackAndOpenStore:[_nativeAd objectForKey:@"appstoreid"] type:SDK_ADTYPE_NATIVE];
    }
}
@end
