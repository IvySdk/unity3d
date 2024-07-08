//
//  SDKBaseAdapter.m
//  Pods
//
//  Created by 余冰星 on 2017/9/19.
//
//

#import "SDKBaseAdModule.h"
#import "SDKTimer.h"
static SDKBaseAd *_showAd;
@implementation SDKBaseAdModule
{
    @private
    SDKBaseAd *_lastVaildAd;
    int _timeout;
    BOOL _hasLoaded;
}
@synthesize tag = _tag;
@synthesize cache = _cache;

- (UIViewController *)_rootVC {
//    return [[[UIApplication sharedApplication] keyWindow] rootViewController];
    return [SDKFacade sharedInstance].rootVC;
}

-(instancetype)init
{
    NSAssert(![self isMemberOfClass:[SDKBaseAdModule class]], @"SDKBaseAdModule is an abstract class, you should not instantiate it directly.");
//    _lock = [[NSRecursiveLock alloc] init];
//    _rootVC = [SDKFacade sharedInstance].rootVC;
    _curLoadIdx = -1;
    return [super init];
}

-(instancetype)init:(nonnull NSString *)tag config:(nonnull NSDictionary *)config
{
    self = [self init];
    _tag = tag;
    _config = config;
    NSNumber *tmp = (NSNumber *) [config objectForKey:@"cache"];
    _cache = tmp ? [tmp boolValue] : false;
//    tmp = (NSNumber *) [config objectForKey:@"load-all"];
//    _loadall = tmp ? [tmp boolValue] : false;
    _adSize = [config objectForKey:@"size"];
    tmp = (NSNumber *)[config objectForKey:@"optimize"];
    _optimize = tmp ? [tmp boolValue] : false;
    tmp = (NSNumber *)[config objectForKey:@"step"];
    _step = tmp ? [tmp intValue] : 1;
    tmp = (NSNumber *)[config objectForKey:@"timeout"];
    _timeout = tmp ? [tmp intValue] : 30;
    NSArray *ids = [config objectForKey:@"ids"];
    if(ids && ids.count > 0) {
        NSArray *_ids = [ids sortedArrayUsingComparator:^NSComparisonResult(NSDictionary *lsData, NSDictionary *rsData) {
            NSNumber *ls = [lsData objectForKey:@"priority"];
            NSNumber *rs = [rsData objectForKey:@"priority"];
            return [ls compare:rs];
        }];
        [self createAds:_ids];
    }
    return self;
}

-(void)createAds:(nonnull NSArray *)ids
{
    if(!adArr) {
        adArr = [[NSMutableArray alloc] init];
    }
    for (int i=0; i<ids.count; i++) {
        NSDictionary *data = [ids objectAtIndex:i];
        NSString *platform = [data objectForKey:@"platform"];
        NSString *orientation = [data objectForKey:@"orientation"];
        SDK_ORIENTATION _orientation = SDK_ORIENTATION_LANDSCAPE;
        if (orientation) {
            if ([orientation isEqualToString:@"landscape"]) {
                _orientation = SDK_ORIENTATION_LANDSCAPE;
            } else if ([orientation isEqualToString:@"portrait"]) {
                _orientation = SDK_ORIENTATION_PORTRAIT;
            } else {
                _orientation = SDK_ORIENTATION_ANY;
            }
        }
        if(platform && platform.length > 0) {
            @try {
                platform = [[platform lowercaseString] capitalizedString];
                Class class = NSClassFromString([NSString stringWithFormat:@"SDK%@%@", platform, _classSuffix]);
                SDKBaseAd *ad = nil;
                if(class) {
                    ad = [[class alloc] init:_tag index:i config:data size:_adSize orientation:_orientation delegate:self];
                    if(ad) {
                        ad.timeoutSeconds = _timeout;
                        [adArr addObject:ad];
                        [self onAdCreated:ad];
                    }
                }
            } @catch (NSException *exception) {
                DLog(@"[adlog]Exception: %@", [exception description]);
            } @catch (NSError *error) {
                DLog(@"[adlog]Error: %@", [error localizedDescription]);
            } @finally {
            }
        }
    }
    if (_cache) {
        [SDKTimer startTimer:3 interval:0.3 onComplete:^{
            if (self->_curLoadIdx < 0) {
                [self loadAd];
            }
        } onUpdate:nil];
    }
}

-(void)onAdCreated:(SDKBaseAd *)ad
{
}

-(BOOL)enable
{
    return adArr && adArr.count > 0;
}

-(nullable SDKBaseAd *)getAvailableAd
{
    if (_lastVaildAd && _lastVaildAd.isAvailable) {
        return _lastVaildAd;
    }
    int n = (int)adArr.count;
    if(n > 0) {
        for (int i=0; i<n; i++) {
            SDKBaseAd *ad = [adArr objectAtIndex:i];
            if ([ad isAvailable]) {
                _lastVaildAd = ad;
                return ad;
            }
        }
    }
    [self loadAd];
    return nil;
}

-(void)loadAd
{
    if(adArr.count<=0)return;
    _hasLoaded = NO;
    [self _loadAd:_curLoadIdx >= adArr.count ? 0 : _curLoadIdx < 0 ? 0 : _curLoadIdx];
}

-(void)_loadAd:(int)idx
{
    if(adArr.count<=0)return;
    _curLoadIdx = idx;
    SDKBaseAd *__block ad = [adArr objectAtIndex:_curLoadIdx];
    if (ad.isAvailable) {
        [ad adLoaded];
    } else {
        dispatch_async(dispatch_get_main_queue(), ^{
            @synchronized (self->_tag) {
                [ad loadAd:self._rootVC];
                ad = nil;
            }
        });
    }
}

-(void)_loadNext
{
    if (_hasLoaded) {
        return;
    }
    _curLoadIdx++;
    _curLoadIdx = _curLoadIdx >= adArr.count ? 0 : _curLoadIdx;
    [self _loadAd:_curLoadIdx];
}

-(SDKBaseAd *)showAd:(UIViewController *)vc
{
    if (_showAd && _showAd.isShowing) {
        return _showAd;
    }
    SDKBaseAd *ad = [self getAvailableAd];
    if (ad && !ad.isShowing) {
        _showAd = ad;
        _lastVaildAd = nil;
        dispatch_async(dispatch_get_main_queue(), ^{
            if (ad) {
                [ad showAd:vc];
            }
        });
    }
    return ad;
}

-(SDKBaseAd *)showVideo:(UIViewController *)vc rewardId:(int)rewardId
{
    if (_showAd && _showAd.isShowing) {
        return _showAd;
    }
    SDKBaseAd *ad = [self getAvailableAd];
    if (ad && !ad.isShowing) {
        ad.rewardId = rewardId;
        _showAd = ad;
        _lastVaildAd = nil;
        dispatch_async(dispatch_get_main_queue(), ^{
            if (ad) {
                [ad showAd:vc];
            }
        });
    }
    return ad;
}

#pragma mark =================== ad delegate =====================
-(void)adLoaded:(NSString *)tag adType:(int)adType
{
    if (_optimize) {
        _curLoadIdx = _curLoadIdx -= _step < 0 ? 0 : _curLoadIdx;
    } else {
        _curLoadIdx = 0;
    }
    _hasLoaded = YES;
    id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
    if(delegate && [delegate respondsToSelector:@selector(adLoaded:adType:)]) {
        [delegate adLoaded:tag adType:adType];
    }
}
-(void)adFailed:(NSString *)tag adType:(int)adType forError:(NSError *)error
{
    if (_curLoadIdx >= adArr.count - 1) {
        _curLoadIdx = 0;
        // all load complete
        id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
        if(delegate && [delegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
            [delegate adFailed:tag adType:adType forError:error];
        }
        return;
    }
    [self _loadNext];
}
-(void)adNeedReload:(NSString *)tag adType:(int)adType
{
    if(_cache) {
        [self loadAd];
        id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
        if(delegate && [delegate respondsToSelector:@selector(adNeedReload:adType:)]) {
            [delegate adNeedReload:tag adType:adType];
        }
    }
}
-(void)adDidClose:(NSString *)tag adType:(int)adType
{
    id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
    if(delegate && [delegate respondsToSelector:@selector(adDidClose:adType:)]) {
        [delegate adDidClose:tag adType:adType];
    }
    [[SDKFacade sharedInstance] onAdClose];
}
-(void)adDidShown:(NSString *)tag adType:(int)adType
{
    id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
    if(delegate && [delegate respondsToSelector:@selector(adDidShown:adType:)]) {
        [delegate adDidShown:tag adType:adType];
    }
}
- (void)adShowFailed:(NSString *)tag adType:(int)adType
{
    id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
    if(delegate && [delegate respondsToSelector:@selector(adShowFailed:adType:)]) {
        [delegate adShowFailed:tag adType:adType];
    }
    [self _loadNext];
}
-(void)adReward:(NSString *)tag rewardId:(int)rewardId
{
    id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
    if(delegate && [delegate respondsToSelector:@selector(adReward:rewardId:)]) {
        [delegate adReward:tag rewardId:rewardId];
    }
}
-(void)adDidClick:(NSString *)tag adType:(int)adType
{
    id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
    if(delegate && [delegate respondsToSelector:@selector(adDidClick:adType:)]) {
        [delegate adDidClick:tag adType:adType];
    }
}
@end
