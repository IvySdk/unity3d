//
//  SDKInterstitial.m
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//

#import "SDKInterstitial.h"
#import <IvyiOSSdk/SDKHelper.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKCache.h>

@implementation SDKInterstitial
NSString * const CLICK_INTER = @"CLICK_INTER";
NSString * const SHOW_INTER = @"SHOW_INTER";

-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(nullable NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    _adType = SDK_ADTYPE_INTERTITIAL;
    _name = [SDKHelper md5:[NSString stringWithFormat:@"%d", arc4random()]];
    return [super init];
}

-(BOOL)loadAd:(UIViewController *)vc
{
    if (_isShowing) {
        return false;
    }
    return [super loadAd:vc];
}

-(void)adDidShown
{
    [super adDidShown];
    [SDKFacade sharedInstance].justShowFullAd = YES;
    if (_name) {
        id tmp = [[SDKCache cache] objectForKey:@"FirstAdClickFlag"];
        if (!tmp) {
            [[SDKCache cache] setObject:[NSString stringWithFormat:@"%@", _name] forKey:@"FirstAdClickFlag"];
        }
    }
    if (![_platform isEqualToString:@"our"] && ![_platform isEqualToString:@"delicious"]) {
        id tmp = [[SDKCache cache] objectForKey:SHOW_INTER];
        int count = tmp ? [(NSNumber *)tmp intValue]: 0;
        count = count + 1;
        NSArray *arr = [[SDKFacade sharedInstance] getTrackAdCountArray:@"si"];
        if (arr && arr.count > 0) {
            for (int i=0; i<arr.count; i++) {
                long value = [(NSNumber *)[arr objectAtIndex:i] intValue];
                if (count == value) {
                    [[SDKFacade sharedInstance] logEvent:[NSString stringWithFormat:@"si%d", count]];
                    break;
                }
            }
        }
        [[SDKCache cache] setObject:[NSNumber numberWithInt:count] forKey:SHOW_INTER];
    }
}

-(void)adDidClick
{
    [super adDidClick];
    if (_name) {
        id tmp = [[SDKCache cache] objectForKey:@"FirstAdClick"];
        if (!tmp) {
            NSString *showAdName = (NSString *)[[SDKCache cache] objectForKey:@"FirstAdClickFlag"];
            if (showAdName && [showAdName isEqualToString:_name]) {
                [[SDKCache cache] setObject:_name forKey:@"FirstAdClick"];
                [[SDKFacade sharedInstance] logEvent:@"FirstAdClick"];
            }
        }
        
        NSString *clicktag = [NSString stringWithFormat:@"%@_click", _tag];
        id tmp2 = [[SDKCache cache] objectForKey:clicktag];
        if (!tmp2) {
            [[SDKCache cache] setObject:_tag forKey:clicktag];
            [[SDKFacade sharedInstance] logEvent:clicktag];
        }
    }
    if (![_platform isEqualToString:@"our"] && ![_platform isEqualToString:@"delicious"]) {
        id tmp = [[SDKCache cache] objectForKey:CLICK_INTER];
        int count = tmp ? [(NSNumber *)tmp intValue]: 0;
        count = count + 1;
        NSArray *arr = [[SDKFacade sharedInstance] getTrackAdCountArray:@"ci"];
        if (arr && arr.count > 0) {
            for (int i=0; i<arr.count; i++) {
                long value = [(NSNumber *)[arr objectAtIndex:i] intValue];
                if (count == value) {
                    [[SDKFacade sharedInstance] logEvent:[NSString stringWithFormat:@"ci%d", count]];
                    break;
                }
            }
        }
        [[SDKCache cache] setObject:[NSNumber numberWithInt:count] forKey:CLICK_INTER];
    }
}

-(void)adDidClose
{
    [SDKFacade sharedInstance].justShowFullAd = NO;
    [[SDKFacade sharedInstance].originWindow makeKeyAndVisible];
    [super adDidClose];
}
@end
