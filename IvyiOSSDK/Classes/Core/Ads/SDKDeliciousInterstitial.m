//
//  SDKDeliciousInterstitial.m
//  Bolts
//
//  Created by 余冰星 on 2018/5/21.
//

#import "SDKDeliciousInterstitial.h"
#import "SDKTimer.h"
#import "SDKFacade.h"
#import "SDKDeliciousBannerView.h"
#import <IvyiOSSdk/SDKGCDTimer.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKNetworkHelper.h>
#import <IvyiOSSdk/UIImageView+SDKNetworking.h>
#import <IvyiOSSdk/SDKJSONHelper.h>
static CAKeyframeAnimation* _downAnimation;
@interface SDKDeliciousVideoView : UIView
-(void)play:(NSDictionary *_Nonnull)data easeIn:(BOOL)easeIn;
-(void)pause;
@end
@implementation SDKDeliciousVideoView
{
    @private
    AVPlayer *_player;
    UIImageView *_downBtn;
    SDKDeliciousInterstitial *_owner;
    NSDictionary *_data;
    CGFloat _downPersentY;
}
//static NSMutableArray<NSString *> *_hasShownBtns;
-(instancetype)initWithOwnerAndFrame:(SDKDeliciousInterstitial *)owner frame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    _owner = owner;
//    if (!_hasShownBtns) {
//        _hasShownBtns = [[NSMutableArray alloc] init];
//    }
    return self;
}

-(void)dealloc
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
//    [_hasShownBtns removeAllObjects];
    _downBtn = nil;
    [_player pause];
    _player = nil;
    _data = nil;
}

-(void)pause
{
    [_player pause];
}

-(void)play:(NSDictionary *)data easeIn:(BOOL)easeIn
{
    _data = data;
    if (easeIn) {
        [self easeIn];
    }
    if (!_downAnimation) {
        if(!_downAnimation) {
            _downAnimation = [CAKeyframeAnimation animationWithKeyPath:@"transform"];
            NSMutableArray *values = [NSMutableArray array];
            [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(0, 0, 0)]];
            [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
            [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.2, 1.2, 1.0)]];
            [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
            _downAnimation.repeatCount = 0;
            _downAnimation.values = values;
            _downAnimation.duration = 0.5;// 动画时间
            _downAnimation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
        }
    }

    if (!_downBtn) {
        _downBtn = [[UIImageView alloc] init];
        [_downBtn setUserInteractionEnabled:YES];
        [_downBtn addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
    }
    
    NSString *video = [data objectForKey:@"video"];
    NSURL *_url = [[SDKNetworkHelper sharedHelper] getDownloadPathURLWithURL:video];
    if (_player) {
        AVPlayerItem *item = [AVPlayerItem playerItemWithURL:_url];
        [_player replaceCurrentItemWithPlayerItem:item];
    } else {
        _player = [AVPlayer playerWithURL:_url];
        AVPlayerLayer *playerLayer = [AVPlayerLayer playerLayerWithPlayer:_player];
        playerLayer.frame = self.frame;
        [self.layer addSublayer:playerLayer];
    }
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    //给AVPlayerItem添加播放完成通知
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(playbackFinished:) name:AVPlayerItemDidPlayToEndTimeNotification object:_player.currentItem];
    [_player play];
    
    
    NSNumber *tmp = (NSNumber *)[data objectForKey:@"down_st"];
    CGFloat interval = tmp ? [tmp floatValue] : 3;
    
    tmp = (NSNumber *)[data objectForKey:@"down_persentY"];
    _downPersentY = tmp ? [tmp floatValue] : 0.5;
    NSString *down = [data objectForKey:SDK_ADTAG_DOWN_ICON];
    UIImage *downImg = [UIImage imageWithContentsOfFile:[[SDKNetworkHelper sharedHelper] getDownloadPathWithURL:down]];
    if (downImg) {
        BOOL landscape = UIInterfaceOrientationIsLandscape(UIApplication.sharedApplication.statusBarOrientation);
        float w = downImg.size.width * Screen_width / (landscape ? 1280 : 720);
        float h = downImg.size.height * Screen_width / (landscape ? 1280 : 720);
        _downBtn.frame = CGRectMake((Screen_width - w) / 2, (Screen_height - h) * _downPersentY, w, h);
        [_downBtn setImage:downImg];
    }
    [_downBtn removeFromSuperview];
//    if ([_hasShownBtns containsObject:video]) {
//        interval = 0.5;
//    } else {
//        [_hasShownBtns addObject:video];
//    }
    [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:@"showDownBtn" interval:interval queue:dispatch_get_main_queue() repeats:NO option:SDKGCDTimerOptionCancelPrevAction action:^{
        [self addSubview:self->_downBtn];
        [self->_downBtn.layer addAnimation:_downAnimation forKey:@"downAnimation"];
    }];
    
    [SDKFacade trackCrossPromoteImpression:[_data objectForKey:@"appstoreid"] type:SDK_ADTYPE_VIDEO];
}

-(void)onClick:(id)sender
{
    if (_data) {
        [SDKFacade trackAndOpenStore:[_data objectForKey:@"appstoreid"] type:SDK_ADTYPE_VIDEO];
        [_owner adDidClick];
    }
}

-(void)easeIn
{
    CATransition *transition = [CATransition animation];
    transition.type = kCATransitionFade;
    transition.duration = 0.5f;
    transition.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
    [self.layer addAnimation:transition forKey:@"easeIn"];
}
/**
 *  播放完成通知
 *
 *  @param notification 通知对象
 */
-(void)playbackFinished:(NSNotification *)notification{
    DLog(@"[adlog]视频播放完成.");
    // 播放完成后重复播放
    // 跳到最新的时间点开始播放
    [_player seekToTime:CMTimeMake(0, 1)];
    [_player play];
}
@end

@interface SDKDeliciousInterstitial() <SDKDeliciousBannerDelegate>
@end
@implementation SDKDeliciousInterstitial
{
    @private
    UIImageView *_closeBtn;
    UIView *_container;
    SDKDeliciousVideoView *_videoView;
    SDKDeliciousBannerView *_bannerView;
    NSDictionary *_data;
}
@synthesize adDatas = _adDatas;
@synthesize bannerFrame = _bannerFrame;

-(void)dealloc
{
    _closeBtn = nil;
    _container = nil;
    _videoView = nil;
    _bannerView = nil;
    _data = nil;
}

-(instancetype)initWithConfig:(NSDictionary *_Nullable)conf
{
    self = [super init];
    _delegate = [SDKFacade sharedInstance].adDelegate;
    _platform = @"delicious";
    [self onCreate:conf];
    [self loadAd:[SDKFacade sharedInstance].rootVC];
    return self;
}

-(instancetype)init:(NSString *)tag index:(int)index config:(NSDictionary *)config size:(NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(id<SDKAdDelegate>)delegate
{
    self = [super init:tag index:index config:config size:size orientation:orientation delegate:delegate];
    _name = nil;
    NSString *configName = [config objectForKey:@"config"];
    [self onCreate:[SDKJSONHelper getJsonDataFrom:configName bundle:nil]];
    return self;
}

-(void)onCreate:(NSDictionary *)conf
{
    if (conf) {
        _isShowing = YES;
        _loadIntervalSec = 0;
        _data = [conf objectForKey:@"interstitial"];
        _adType = SDK_ADTYPE_INTERTITIAL;
        if (_data) {
            _bannerFrame = CGRectMake(0, Screen_height - 90, Screen_width, 90);
            NSNumber *tmp = [_data objectForKey:@"sw"];
            float sw = tmp ? tmp.floatValue : 720;
            tmp = [_data objectForKey:@"sh"];
            float sh = tmp ? tmp.floatValue : 1280;
            NSString *frame = [_data objectForKey:@"bannerFrame"];
            if (frame) {
                NSArray<NSString *> *arr = [frame componentsSeparatedByString:@","];
                float scaleX = Screen_width / sw;
                float scaleY = Screen_height / sh;
                if (arr.count >= 4) {
                    float x = [arr objectAtIndex:0].floatValue * scaleX;
                    float y = [arr objectAtIndex:1].floatValue * scaleY;
                    float w = [arr objectAtIndex:2].floatValue * scaleX;
                    float h = [arr objectAtIndex:3].floatValue * scaleY;
                    _bannerFrame = CGRectMake(x, y, w, h);
                }
            }
        }
    }
}

-(void)prepareShow
{
    _adDatas = [[SDKFacade sharedInstance] getDeliciousAdDatas:YES];
    if (!_container) {
        _container = [[UIView alloc] initWithFrame:ScreenRect];
        _container.userInteractionEnabled = YES;
//        _container.backgroundColor = [UIColor grayColor];
        _container.backgroundColor = [UIColor blackColor];
        _container.opaque = YES;
        
        _bannerView = [[SDKDeliciousBannerView alloc] initWithConfig:_data];
        _bannerView.frame = _bannerFrame;
        _bannerView.jumpToVideo = YES;
        _bannerView.adDatas = _adDatas;
        _bannerView.bannerDelegate = self;
        [_bannerView reloadData];
        _bannerView.backgroundColor = [UIColor colorWithRed:0 green:0 blue:0 alpha:0.35];
        
        UIImage *close = [SDKHelper getImageFromeBundle:@"sdk_btn_close" bundleName:@"IvyiOSSdk-Core"];
        _closeBtn = [[UIImageView alloc] initWithImage:close];
        [_closeBtn setUserInteractionEnabled:YES];
        [_closeBtn addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(adDidClose)]];
        _closeBtn.frame = CGRectMake(Screen_width - 45, [SDKFacade isIPhoneX] ? 30 : 13, 40, 40);
        
        _videoView = [[SDKDeliciousVideoView alloc] initWithOwnerAndFrame:self frame:_container.frame];
        _videoView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
        [_videoView setUserInteractionEnabled:YES];
        //    [_videoView addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
        UISwipeGestureRecognizer *swipeLeft = [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(swipe:)];
        UISwipeGestureRecognizer *swipeRight = [[UISwipeGestureRecognizer alloc] initWithTarget:self action:@selector(swipe:)];
        swipeLeft.direction = UISwipeGestureRecognizerDirectionLeft;
        swipeRight.direction = UISwipeGestureRecognizerDirectionRight;
        [_videoView addGestureRecognizer:swipeLeft];
        [_videoView addGestureRecognizer:swipeRight];
        
        [_container addSubview:_videoView];
        [_container addSubview:_closeBtn];
        [_container addSubview:_bannerView];
    }
}

-(BOOL)loadAd:(UIViewController *)vc
{
    [super loadAd:vc];
    _adDatas = [[SDKFacade sharedInstance] getDeliciousAdDatas:YES];
    if(_adDatas && _adDatas.count > 0) {
        [self adLoaded];
    } else {
        [self adFailed:nil];
    }
    return true;
}

- (void)swipe:(UISwipeGestureRecognizer *)swipe
{
    NSInteger i = _bannerView.currentSelectIndex;
    i = swipe.direction == UISwipeGestureRecognizerDirectionRight ? i-1 : i+1;
    i = i < 0 ? _adDatas.count - 1 : i >= _adDatas.count ? 0 : i;
    [self select:i easeIn:YES];
}

-(void)showAd:(UIViewController *)vc index:(NSInteger)index easeIn:(BOOL)easeIn
{
    if (self.isAvailable) {
        @try{
            UIWindow *window = [SDKFacade sharedInstance].window;
            vc = window.rootViewController;
            [window makeKeyAndVisible];
            [self prepareShow];
            [self select:index easeIn:easeIn];
            [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:@"showDeliciousInsterstitial" interval:0.1 queue:dispatch_get_main_queue() repeats:NO option:SDKGCDTimerOptionCancelPrevAction action:^{
                [vc.view addSubview:self->_container];
                [self adDidShown];
            }];
            [SDKFacade sharedInstance].justShowFullAd = YES;
        }@catch (NSException *exception) {
        }
    } else {
        [self adShowFailed];
    }
}

-(void)showAd:(UIViewController *)vc
{
    [self showAd:vc index:0 easeIn:NO];
}

- (void)select:(NSInteger)index easeIn:(BOOL)easeIn
{
    NSDictionary *data = [_adDatas objectAtIndex:index];
    [_videoView play:data easeIn:easeIn];
    [_bannerView select:index];
}

-(void)doClose:(id)sender
{
    [self adDidClose];
}

-(void)onDeliciousIconClick:(NSInteger)index
{
    [self select:index easeIn:YES];
}

-(void)adDidClose
{
    _isShowing = NO;
    [SDKFacade sharedInstance].justShowFullAd = NO;
    [[SDKFacade sharedInstance].originWindow makeKeyAndVisible];
    [[SDKFacade sharedInstance].window removeFromSuperview];
    if (_closeBtn) {
        [_closeBtn removeFromSuperview];
        _closeBtn = nil;
    }
    if (_videoView) {
        [_videoView removeFromSuperview];
        _videoView = nil;
    }
    if (_bannerView) {
        [_bannerView removeFromSuperview];
        _bannerView = nil;
    }
    if (_container) {
        [_container removeFromSuperview];
        _container = nil;
    }
    [super adDidClose];
}
@end
