//
//  SMAAdMobSmaatoBannerAdapter.h
//  SmaatoSDKAdapters
//
//  Created by Smaato Inc on 20.11.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>
#import <GoogleMobileAds/GoogleMobileAds.h>

@interface SMAAdMobSmaatoBannerAdapter: NSObject <GADMediationAdapter>
@property (class, nonatomic, readonly) NSString *version;
@end
