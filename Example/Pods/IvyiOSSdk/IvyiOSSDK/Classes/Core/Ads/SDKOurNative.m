//
//  SDKOurNative.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/28.
//

#import "SDKOurNative.h"
#import "SDKOurNativeView.h"
#import "SDKFacade.h"
@implementation SDKOurNative
#pragma mark ================== Native Ads ===================
-(NSString *)defaultNibName
{
    return @"SDKOurNativeView";
}

-(NSString *)defaultBundleName
{
    return @"IvyiOSSdk-Core";
}

-(Class)nativeAdViewClass
{
    return [SDKOurNativeView class];
}

-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    _loadIntervalSec = 0;
    return self;
}

-(BOOL)loadAd:(UIViewController *)vc
{
    [super loadAd:vc];
    NSMutableArray *arr = [[SDKFacade sharedInstance] getOurAdDatas:SDK_ADTYPE_NATIVE];
    if([arr count] > 0) {
        [self adLoaded:[arr objectAtIndex:(arc4random() % arr.count)]];
    } else {
        [self adFailed:@"No ad to show!"];
    }
    return true;
}

-(NSMutableDictionary *)nativeAdToDict:(nonnull id)nativeAd
{
    NSMutableDictionary *data = [[NSMutableDictionary alloc] init];
    NSDictionary *ad = (NSDictionary *)nativeAd;
    [data setObject:[ad objectForKey:@"name"] forKey:@"title"];
    [data setObject:[ad objectForKey:@"desc"] forKey:@"body"];
    [data setObject:[ad objectForKey:@"icon"] forKey:@"icon"];
    [data setObject:[ad objectForKey:@"cover"] forKey:@"cover"];
    return data;
}
@end
