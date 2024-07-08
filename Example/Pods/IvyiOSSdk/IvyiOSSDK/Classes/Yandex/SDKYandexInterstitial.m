//
//  SDKYandexInterstitial.m
//  Pods
//
//  Created by IceStar on 2023/06/01.
//
//

#import "SDKYandexInterstitial.h"
#import <IvyiOSSdk/SDKFacade.h>

@implementation SDKYandexInterstitial
{
@private
    YMAInterstitialAd *_interstitialAd;
}
#pragma mark ================== Interstitial AD ===================

-(BOOL)loadAd:(UIViewController *)vc
{
    _interstitialAd = nil;
    if([super loadAd:vc]) {
        _interstitialAd = [[YMAInterstitialAd alloc] initWithAdUnitID:_adId];
        _interstitialAd.delegate = self;
        [_interstitialAd load];
        return true;
    }
    return false;
}

-(void)showAd:(UIViewController *)vc
{
    [super showAd:vc];
    if (self.isAvailable && _interstitialAd) {
        @try{
            @synchronized (_interstitialAd) {
                [_interstitialAd presentFromViewController:vc];
            }
        }@catch (NSException *exception) {
            _interstitialAd = nil;
        }
    } else {
        [self adShowFailed];
    }
}

-(void)interstitialAdDidLoad:(YMAInterstitialAd *)interstitialAd
{
    [self adLoaded];
}

-(void)interstitialAdDidFailToLoad:(YMAInterstitialAd *)interstitialAd error:(NSError *)error
{
    _interstitialAd = nil;
    [self adFailed:error.localizedDescription];
}

-(void)interstitialAdDidFailToPresent:(YMAInterstitialAd *)interstitialAd error:(NSError *)error
{
    _interstitialAd = nil;
    [self adShowFailed];
}

-(void)interstitialAdDidDisappear:(YMAInterstitialAd *)interstitialAd
{
    [self adDidClose];
    _interstitialAd = nil;
}

-(void)interstitialAdDidAppear:(YMAInterstitialAd *)interstitialAd
{
    [self adDidShown];
}

-(void)interstitialAdDidClick:(YMAInterstitialAd *)interstitialAd
{
    [self adDidClick];
}

-(void)interstitialAd:(YMAInterstitialAd *)interstitialAd didTrackImpressionWithData:(id<YMAImpressionData>)impressionData
{
    @try {
        if (impressionData != NULL && impressionData.rawData != NULL) {
            NSString * rawData = impressionData.rawData;
            NSData* jsonData = [rawData dataUsingEncoding:NSUTF8StringEncoding];
            NSError* error;
            NSDictionary* dict = [NSJSONSerialization JSONObjectWithData:jsonData options:kNilOptions error:&error];
            if (error) {
                NSLog(@"parse impression data error::%@", error);
            } else {
                NSDictionary* network = dict[@"network"];
                [[SDKFacade sharedInstance] logAdRevenue:network[@"name"] mediationType:SdkMediationNetworkTypeYandex adType:@"rewarded" adUnit:network[@"ad_unit_id"] placement:self->_tag country:[SDKHelper getCountryCode].lowercaseString currency:@"USD" value:dict[@"revenue_USD"]];
            }
        }
    } @catch (NSException *exception) {
        NSLog(@"log yandex ad revenue error::%@", exception);
    }
}
@end
