//
//  SDKNative.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/25.
//
#import "SDKNative.h"
#import "SDKObjectPool.h"
#import "SDKHelper.h"
@implementation SDKNative
{
    @private
    SDKObjectPool *pool;
    float _smallClickRate;
    int _retryCount;
}

-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(nullable NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    _adType = SDK_ADTYPE_NATIVE;
//    _needLoadInMainThread = YES;
    NSNumber *tmp = [config objectForKey:@"small-click-rate"];
    _smallClickRate = tmp ? [tmp floatValue] : 100;
    
    tmp = [config objectForKey:@"adtype"];
    [self setNativeAdType:tmp ? [tmp intValue] : SDK_NATIVE_ALL];
    return self;
}

-(int)getImgOrientation
{
    return _orientation;
}

-(void)setNativeAdType:(SDK_NATIVE_ADTYPE)adType
{
    _nativeAdType = adType;
}

-(SDK_NATIVE_ADTYPE)nativeAdType
{
    return _nativeAdType;
}

-(BOOL)enableClickSmall
{
    return arc4random() % 10000 / 100 < _smallClickRate;
}

-(void)showAd:(UIViewController *)vc
{
}

-(nullable UIView *)showAd:(UIViewController *)vc frame:(CGRect)frame useDesignScale:(BOOL)useDesignScale configJson:(nonnull NSString *)json
{
    SDKNativeView *adView = (SDKNativeView *)[self fetchAdView:vc];
    if (adView) {
        adView.vc = vc;
        [adView loadConfig:json frame:frame useDesignScale:useDesignScale];
        [vc.view addSubview:adView];
        return adView;
    }
    return nil;
}

-(nullable UIView *)getAdView:(UIViewController *)vc frame:(CGRect)frame configJson:(nonnull NSString *)json
{
    SDKNativeView *adView = (SDKNativeView *)[self fetchAdView:vc];
    if (adView) {
        adView.vc = vc;
        [adView loadConfig:json frame:frame useDesignScale:YES];
        return adView;
    }
    return nil;
}

-(Class)nativeAdViewClass
{
    return nil;
}

-(void)recycleView:(nonnull UIView *)adView
{
//    [[self getPool] returnObjectToPool:adView];
}

//-(SDKObjectPool *)getPool
//{
//    if(pool == nil || pool == NULL) {
//        __weak typeof(self) weakself = self;
//        pool = [SDKObjectPool poolWithCreateBlock:^id(NSError **outError) {
//            __strong SDKNative* strongSelf = weakself;
//            return [[[strongSelf nativeAdViewClass] alloc] initWithOwner:strongSelf];
//        }];
//    }
//    return pool;
//}

-(NSMutableDictionary *)nativeAdToDict:(id)nativeAd
{
    return nil;
}

-(UIView *)fetchAdView:(UIViewController *)vc
{
    if ([self isAvailable]) {
        id nativeAd = [_adQueue objectAtIndex:0];
        [_adQueue removeObjectAtIndex:0];
        [self loadAd:vc];
//        SDKNativeView *view = [[self getPool] objectFromPoolWithError:nil];
        SDKNativeView *view = [[[self nativeAdViewClass] alloc] initWithOwner:self];
        [view loadNativeAd:nativeAd];
        return view;
    }
    return nil;
}

-(NSDictionary *)fetchAdData:(UIViewController *)vc
{
    if ([self isAvailable]) {
        id nativeAd = [_adQueue objectAtIndex:0];
        [_adQueue removeObjectAtIndex:0];
        [self loadAd:vc];
        return [self nativeAdToDict:nativeAd];
    }
    return nil;
}

-(void)adLoaded:(id)nativeAd
{
    if (!nativeAd)
        return;
    if (!_adQueue) {
        _adQueue = [[NSMutableArray alloc] init];
    }
    [_adQueue addObject:nativeAd];
    [super adLoaded];
}

-(NSString *)defaultBundleName
{
    return @"";
}

-(NSString *)defaultNibName
{
    return @"";
}

-(void)setBundleName:(NSString *)bundleName
{
    _bundleName = bundleName;
}

-(NSString *)bundleName
{
    return _bundleName ? _bundleName : [self defaultBundleName];
}

-(void)setNibName:(NSString *)nibName
{
    _nibName = nibName;
}

-(NSString *)nibName
{
    return _nibName ? _nibName : [self defaultNibName];
}

@end
