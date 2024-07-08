//
//  SDKAdmobNative.h
//  Bolts
//
//  Created by 余冰星 on 2017/12/25.
//

#import <IvyiOSSdk/SDKNative.h>
#import <GoogleMobileAds/GADMobileAds.h>
#import <GoogleMobileAds/GADNativeAd.h>
#import <GoogleMobileAds/GADNativeAdDelegate.h>
@interface SDKAdmobNative : SDKNative<GADNativeAdLoaderDelegate, GADNativeAdDelegate>

@end
