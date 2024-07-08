//
//  SDKAdmobRequest.m
//  Bolts
//
//  Created by 余冰星 on 2018/5/24.
//

#import "SDKAdmobRequest.h"
#import <IvyiOSSdk/SDKGdpr.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <GoogleMobileAds/GoogleMobileAds.h>
#import <GoogleMobileAds/GADExtras.h>
@implementation SDKAdmobRequest
+(GADRequest *)createRequest
{
    GADRequest *request = [GADRequest request];
    if ([SDKFacade sharedInstance].adsUnderAgeMode) {
//        [request tagForChildDirectedTreatment:YES];
//        [GADMobileAds.sharedInstance.requestConfiguration tagForChildDirectedTreatment:@YES];
//        [GADMobileAds.sharedInstance.requestConfiguration tagForChildDirectedTreatment:YES];
    }
//    if ([[SDKFacade sharedInstance] hasGdpr] && ![SDKGdpr enableAdPersonalized]) {
//        GADExtras *extras = [[GADExtras alloc] init];
//        extras.additionalParameters = @{@"npa": @"1"};
//        [request registerAdNetworkExtras:extras];
//    }
    return request;
}
@end
