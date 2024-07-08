//
//  SDKFacebookNativeView.h
//  Bolts
//
//  Created by 余冰星 on 2017/12/27.
//
#import <IvyiOSSdk/SDKNativeView.h>
#import <GoogleMobileAds/GADNativeAd.h>
#import <GoogleMobileAds/GADVideoControllerDelegate.h>
@interface SDKAdmobNativeView : SDKNativeView<GADVideoControllerDelegate>
@property (strong, nonatomic) IBOutlet GADNativeAdView *adView;
@property (weak, nonatomic) IBOutlet UIImageView *adCornerView;
@property (weak, nonatomic) IBOutlet UIButton *closeButton;
@property (weak, nonatomic) IBOutlet UIImageView *bgImageView;
@end
