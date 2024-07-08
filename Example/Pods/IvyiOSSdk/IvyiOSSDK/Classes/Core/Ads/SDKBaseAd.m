//
//  SDKBaseAd.m
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//
#import "SDKBaseAd.h"
#import "SDKNetworkHelper.h"
#import <IvyiOSSdk/SDKGCDTimer.h>
#import "SDKFacade.h"
NSString * const SHOW_FAIL_TIMER = @"ShowFailedTimer";
NSString * const LOAD_TIMEOUT_TIMER = @"LoadTimer";
@implementation SDKBaseAd
@synthesize isAvailable = _isAvailable;
@synthesize isLoading = _isLoading;
@synthesize tag = _tag;
@synthesize adType = _adType;
@synthesize config = _config;
@synthesize orientation = _orientation;
@synthesize rewardId = _rewardId;
@synthesize isShowing = _isShowing;
//@synthesize needLoadInMainThread = _needLoadInMainThread;

-(instancetype)init
{
    NSAssert(![self isMemberOfClass:[SDKBaseAd class]], @"SDKBaseAd is an abstract class, you should not instantiate it directly.");
    return [super init];
}

-(nonnull instancetype)init:(nonnull NSString *)tag index:(int)index config:(nonnull NSDictionary *)config size:(NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(nonnull id<SDKAdDelegate>)delegate
{
    self = [self init];
    _timeoutSeconds = _timeoutSeconds <= 0 ? 60 : _timeoutSeconds;
    _index = index;
    _config = config;
    _adSize = size;
    _orientation = (int)orientation;
    _tag = tag ? tag : @"default";
    _adId = [config objectForKey:@"id"];
    _adId = _adId ? _adId : @"";
    _platform = [config objectForKey:@"platform"];
    _delegate = delegate;
    _lastLoadTime = 0;
    _loadIntervalSec = 8;
    _isShowing = NO;
    return self;
}

-(BOOL)loadAd:(UIViewController *)vc
{
    _vc = vc;
    if(!_adId) {
        [self adFailed:@"placement is null!"];
        return false;
    }
//    if(_isShowing) {
//        [self adFailed:@"ad is on showing!"];
//        return false;
//    }
    if (_isLoading) {
        if (!self.isAvailable) {
            long now = CACurrentMediaTime();
            if (now - _lastLoadTime >= _timeoutSeconds) {
                [self adTimeout];
            }
        }
        return false;
    }
    long now = CACurrentMediaTime();
    if (now - _lastLoadTime >= _loadIntervalSec) {
        _isLoading = YES;
        _isAvailable = NO;
        _lastLoadTime = now;
        [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:[NSString stringWithFormat:@"%@:%@", LOAD_TIMEOUT_TIMER, _adId] interval:_timeoutSeconds queue:dispatch_get_main_queue() repeats:NO option:SDKGCDTimerOptionCancelPrevAction action:^{
            [self adTimeout];
        }];
        DLog(@"[adlog] [tag:%@(%d)] [%@:loadAd] [id:%@] [size:%@]", _tag, _index, NSStringFromClass(self.class), _adId, _adSize);
        return true;
    } else {
        [self adFailed:[NSString stringWithFormat:@"load too frequently, please wait %ld seconds to reload!", _loadIntervalSec]];
        return false;
    }
}

-(BOOL)isAvailable
{
    if (_isLoading && _isAvailable) {
        _isLoading = NO;
    }
    return _isAvailable;
}

-(void)startCheckShowFailedTimer
{
    [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:SHOW_FAIL_TIMER interval:10 queue:dispatch_get_main_queue() repeats:NO option:SDKGCDTimerOptionCancelPrevAction action:^{
        if (!self->_hasShown) {
            [self adShowFailed];
        }
    }];
}

-(void)showAd:(UIViewController *)vc
{
    _hasShown = NO;
}

-(void)fail:(NSString *)err
{
    [[SDKGCDTimer sharedInstance] cancelTimerWithName:[NSString stringWithFormat:@"%@:%@", LOAD_TIMEOUT_TIMER, _adId]];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adFailed] [id:%@] [err:%@]", _tag, _index, NSStringFromClass(self.class), _adId, err ? err : @"");
    _isAvailable = NO;
    if(_delegate && [_delegate respondsToSelector:@selector(adFailed:adType:forError:)]) {
        NSError *error = nil;
        if(err) {
            error = [[NSError alloc] initWithDomain:err code:0 userInfo:nil];
        }
        [_delegate adFailed:_tag adType:_adType forError:error];
    }
}

-(void)adTimeout
{
    if (!self.isAvailable) {
        long now = CACurrentMediaTime();
        if (now - _lastLoadTime >= 300) {
            _isLoading = NO;
        }
        [self fail:@"sdk load timeout!"];
    }
}

-(void)adFailed:(NSString *)errorStr
{
    _isLoading = NO;
    [self fail:errorStr];
}

-(void)adLoaded
{
    [[SDKGCDTimer sharedInstance] cancelTimerWithName:[NSString stringWithFormat:@"%@:%@", LOAD_TIMEOUT_TIMER, _adId]];
    _isAvailable = YES;
    _isLoading = NO;
    DLog(@"[adlog] [tag:%@(%d)] [%@:adLoaded] [id:%@] [size:%@]", _tag, _index, NSStringFromClass(self.class), _adId, _adSize);
    if(_delegate && [_delegate respondsToSelector:@selector(adLoaded:adType:)]) {
        [_delegate adLoaded:_tag adType:_adType];
    }
}

-(void)adDidShown
{
    [[SDKGCDTimer sharedInstance] cancelTimerWithName:SHOW_FAIL_TIMER];
    [[SDKFacade sharedInstance] logAdImpression:[SDKConstants getAdTypeName:_adType] adPos:_tag platform:_platform];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adDidShown] [id:%@] [size:%@]", _tag, _index, NSStringFromClass(self.class), _adId, _adSize);
    _hasShown = YES;
    _isShowing = YES;
    if(_delegate && [_delegate respondsToSelector:@selector(adDidShown:adType:)]) {
        [_delegate adDidShown:_tag adType:_adType];
    }
}

-(void)adShowFailed
{
    [[SDKGCDTimer sharedInstance] cancelTimerWithName:SHOW_FAIL_TIMER];
    DLog(@"[adlog] [tag:%@(%d)] [%@:adShowFailed] [id:%@] [size:%@]", _tag, _index, NSStringFromClass(self.class), _adId, _adSize);
    _isAvailable = NO;
    _hasShown = NO;
    if(_delegate && [_delegate respondsToSelector:@selector(adShowFailed:adType:)]) {
        [_delegate adShowFailed:_tag adType:_adType];
    }
}

-(void)adDidClose
{
    _isShowing = NO;
    DLog(@"[adlog] [tag:%@(%d)] [%@:adDidClose] [id:%@] [size:%@]", _tag, _index, NSStringFromClass(self.class), _adId, _adSize);
    if(_delegate && [_delegate respondsToSelector:@selector(adDidClose:adType:)]) {
        [_delegate adDidClose:_tag adType:_adType];
    }
    [self adNeedReload];
}

-(void)adNeedReload
{
    DLog(@"[adlog] [tag:%@(%d)] [%@:adNeedReload] [id:%@] [size:%@]", _tag, _index, NSStringFromClass(self.class), _adId, _adSize);
    _isAvailable = NO;
    if(_delegate && [_delegate respondsToSelector:@selector(adNeedReload:adType:)]) {
        [self->_delegate adNeedReload:self->_tag adType:self->_adType];
    }
}

-(void)adDidClick
{
    if (_adType != SDK_ADTYPE_VIDEO) {
        [[SDKFacade sharedInstance] logAdClick:[SDKConstants getAdTypeName:_adType] adPos:_tag platform:_platform];
    }
    DLog(@"[adlog] [tag:%@(%d)] [%@:adDidClick] [id:%@] [size:%@]", _tag, _index, NSStringFromClass(self.class), _adId, _adSize);
    if(_delegate && [_delegate respondsToSelector:@selector(adDidClick:adType:)]) {
        [_delegate adDidClick:_tag adType:_adType];
    }
}

-(void)didBecomeActive
{
}
@end

