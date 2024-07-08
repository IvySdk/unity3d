//
//  SDKDeliciousIconView.m
//  Bolts
//
//  Created by 余冰星 on 2018/6/10.
//

#import "SDKDeliciousIconView.h"
#import <IvyiOSSdk/UIImageView+SDKNetworking.h>
#import <IvyiOSSdk/SDKGCDTimer.h>
#import <IvyiOSSdk/SDKNetworkHelper.h>
#import <IvyiOSSdk/SDKFacade.h>
#import "SDKDeliciousInterstitial.h"
@implementation SDKDeliciousIconView
{
@private
    NSDictionary *_conf;
    UIImageView *_iconView;
    UIImageView *_iconBg;
    UIImageView *_adSign;
    BOOL _bgCoverMode;
    BOOL jumpToVideo;
    SDKDeliciousInterstitial *_inter;
}
@synthesize adDatas = _adDatas;
@synthesize currentIconIndex = _currentIconIndex;
@synthesize iconDelegate = _iconDelegate;
@synthesize cornerRadius = _cornerRadius;
@synthesize iconWidth = _iconWidth;
@synthesize iconHeight = _iconHeight;
@synthesize offsetX = _offsetX;
@synthesize offsetY = _offsetY;
@synthesize enableAnimation = _enableAnimation;
@synthesize autoRotateIcons = _autoRotateIcons;
static CAKeyframeAnimation* _iconAnimation;

-(instancetype)initWithConfig:(NSDictionary *)conf
{
    self = [super init];
    _conf = conf;
    _iconWidth = _iconHeight = 90;
    _enableAnimation = _autoRotateIcons = YES;
    CGRect bgframe = CGRectMake(0, 0, _iconWidth, _iconHeight);
    self.autoresizesSubviews = YES;
    self.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
#if DEBUG
    self.layer.borderColor = [[UIColor redColor] CGColor];
    self.layer.borderWidth = 1;
#endif
    if(!_iconAnimation) {
        _iconAnimation = [CAKeyframeAnimation animationWithKeyPath:@"transform"];
        _iconAnimation.duration = 1.5;// 动画时间
        NSMutableArray *values = [NSMutableArray array];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.1, 1.1, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(0.9, 0.9, 1.0)]];
        [values addObject:[NSValue valueWithCATransform3D:CATransform3DMakeScale(1.0, 1.0, 1.0)]];
        _iconAnimation.values = values;
        _iconAnimation.repeatCount = MAXFLOAT;
    }
    if (!_iconView) {
        _iconView = [[UIImageView alloc] init];
        _iconView.layer.masksToBounds = true;
#if DEBUG
        _iconView.layer.borderColor = [[UIColor redColor] CGColor];
        _iconView.layer.borderWidth = 1;
#endif
    }
    if (_conf) {
        float scale = [UIScreen mainScreen].scale;
        NSDictionary *data = [_conf objectForKey:@"icon"];
        NSNumber *tmp = [data objectForKey:@"autoRotato"];
        _autoRotateIcons = tmp ? tmp.intValue > 0 : YES;
        tmp = [data objectForKey:@"iconAnimate"];
        _enableAnimation = tmp ? tmp.intValue > 0 : YES;
        tmp = [data objectForKey:@"offsetX"];
        _offsetX = tmp ? tmp.floatValue / scale : 0;
        tmp = [data objectForKey:@"offsetY"];
        _offsetY = tmp ? tmp.floatValue / scale : 0;
        tmp = [data objectForKey:@"iconWidth"];
        _iconWidth = tmp ? tmp.floatValue / scale  : 90;
        tmp = [data objectForKey:@"iconHeight"];
        _iconHeight = tmp ? tmp.floatValue / scale  : 90;
        tmp = [data objectForKey:@"cornerRadius"];
        _cornerRadius = tmp ? tmp.floatValue / scale : 0;
        if (_cornerRadius > 0)
            _iconView.layer.cornerRadius = _cornerRadius;
        jumpToVideo = !![_conf objectForKey:@"jumpToVideo"];
        _bgCoverMode = !![_conf objectForKey:@"bgCoverMode"];
        NSString *bgImgName = [data objectForKey:@"bg"];
        if (bgImgName) {
            NSURL *bgUrl = [[NSBundle mainBundle] URLForResource:bgImgName.stringByDeletingPathExtension withExtension:bgImgName.pathExtension];
            if (bgUrl) {
                if (!_iconBg) {
                    _iconBg = [[UIImageView alloc] init];
                }
                [_iconBg setImageWithURL:bgUrl];
                if (_iconBg.image) {
                    bgframe = CGRectMake(0, 0, _iconBg.image.size.width / scale, _iconBg.image.size.height / scale);
                }
            }
        }
        NSString *adSign = [data objectForKey:@"adSign"];
        if (adSign) {
            NSURL *bgUrl = [[NSBundle mainBundle] URLForResource:adSign.stringByDeletingPathExtension withExtension:adSign.pathExtension];
            if (bgUrl) {
                if (!_adSign) {
                    _adSign = [[UIImageView alloc] init];
                }
                [_adSign setImageWithURL:bgUrl];
                if (_adSign.image) {
                    _adSign.frame = CGRectMake(0, 0, _adSign.image.size.width / scale, _adSign.image.size.height / scale);
                }
            }
        }
    }
    _iconView.frame = CGRectMake(_offsetX, _offsetY, _iconWidth, _iconHeight);
    _iconView.enableFade = YES;
    self.frame = bgframe;
    if (_bgCoverMode) {
        [self addSubview:_iconView];
    }
    if (_iconBg) {
        [self addSubview:_iconBg];
    }
    if (!_bgCoverMode) {
        [self addSubview:_iconView];
    }
    if (_adSign) {
        [self addSubview:_adSign];
    }
    self.currentIconIndex = 0;
    self.userInteractionEnabled = YES;
    [self addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
    return self;
}

-(void)setFrame:(CGRect)frame
{
    float scaleX = self.frame.size.width > 0 ? frame.size.width / self.frame.size.width : 1;
    float scaleY = self.frame.size.height > 0 ? frame.size.height / self.frame.size.height : 1;
    [super setFrame:frame];
    if(_iconBg) {
        _iconBg.frame = CGRectMake(0, 0, frame.size.width, frame.size.height);
    }
    CGRect tmp = _iconView.frame;
    _iconView.frame = CGRectMake(tmp.origin.x * scaleX, tmp.origin.y * scaleY, tmp.size.width * scaleX, tmp.size.height * scaleY);
    if (_iconView.layer.cornerRadius > 0)
        _iconView.layer.cornerRadius = _iconView.layer.cornerRadius * scaleX;
}

-(void)setAdDatas:(NSArray *)adDatas
{
    _adDatas = adDatas;
    self.currentIconIndex = _currentIconIndex;
}

-(void)addTo:(UIView *)view frame:(CGRect)frame
{
    self.frame = frame;
    [self setEnableAnimation:_enableAnimation];
    [self setAutoRotato:_autoRotateIcons];
    [view addSubview:self];
}

-(void)removeSelf
{
    [self removeFromSuperview];
    [self setEnableAnimation:NO];
    [self setAutoRotato:NO];
}

-(void)setCurrentIconIndex:(NSInteger)currentIconIndex
{
    _currentIconIndex = currentIconIndex;
    if (_adDatas && _adDatas.count > 0) {
        _currentIconIndex = currentIconIndex < 0 ? _adDatas.count - 1 : currentIconIndex >= _adDatas.count ? 0 : currentIconIndex;
        NSDictionary *data = [_adDatas objectAtIndex:_currentIconIndex];
        NSString *url = [data objectForKey:SDK_ADTAG_GIFICON];
        [_iconView setImageWithURL:[[SDKNetworkHelper sharedHelper] getDownloadPathURLWithURL:url]];
    }
}

-(void)setEnableAnimation:(BOOL)enableAnimation
{
    _enableAnimation = enableAnimation;
    if (enableAnimation) {
        [self.layer addAnimation:_iconAnimation forKey:@"iconAnimation"];
    } else {
        [self.layer removeAnimationForKey:@"iconAnimation"];
    }
}

-(void)setAutoRotato:(BOOL)autoRotateIcons
{
    _autoRotateIcons = autoRotateIcons;
    if (autoRotateIcons) {
        [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:@"change_delicious_icons" interval:15 queue:dispatch_get_main_queue() repeats:YES option:SDKGCDTimerOptionCancelPrevAction action:^{
            [self setCurrentIconIndex:self->_currentIconIndex+1];
        }];
    } else {
        [[SDKGCDTimer sharedInstance] cancelTimerWithName:@"change_delicious_icons"];
    }
}

-(void)dealloc
{
    [[SDKGCDTimer sharedInstance] cancelTimerWithName:@"change_delicious_icons"];
    _iconView = nil;
    _iconBg = nil;
    _inter = nil;
}

-(void)onClick:(UITapGestureRecognizer *)sender
{
    if (sender) {
        NSDictionary *data = [_adDatas objectAtIndex:_currentIconIndex];
         if (data) {
             if (jumpToVideo) {
                 NSString *down = [data objectForKey:SDK_ADTAG_DOWN_ICON];
                 NSString *video = [data objectForKey:SDK_ADTAG_VIDEO];
                 if ([[SDKNetworkHelper sharedHelper] hasURLDownloaded:down]
                     && [[SDKNetworkHelper sharedHelper] hasURLDownloaded:video]) {
                     if (!_inter || !_inter.isShowing) {
                         _inter = [[SDKDeliciousInterstitial alloc] initWithConfig:_conf];
                         [_inter showAd:[SDKFacade sharedInstance].rootVC index:_currentIconIndex easeIn:NO];
                         //            [[SDKFacade sharedInstance] showRewardVideoWithTag:@"freevideo" rewardId:0];
                     }
                 }
             } else {
                 NSString *delicious_banner = [data objectForKey:SDK_ADTAG_DELICIOUS_BANNER];
                 bool flag = YES;
                 if (delicious_banner && [[SDKNetworkHelper sharedHelper]   hasURLDownloaded:delicious_banner]) {
                     NSString *bannerDownPath = [[SDKNetworkHelper sharedHelper] getDownloadPathWithURL:delicious_banner];
                     id<SDKAdDelegate> delegate = [SDKFacade sharedInstance].adDelegate;
                     if (delegate && [delegate respondsToSelector:@selector(deliciousIconClick:jumpURL:)]) {
                        flag = NO;
                        [delegate deliciousIconClick:bannerDownPath jumpURL:[SDKHelper getAppstoreUrl:[data objectForKey:@"appstoreid"]]];
                     }
                 }
                 if(flag) {
                     [SDKFacade trackAndOpenStore:[data objectForKey:@"appstoreid"] type:SDK_ADTYPE_GIFICON];
                 }
                 [[SDKFacade sharedInstance] logAdClick:[SDKConstants getAdTypeName:SDK_ADTYPE_GIFICON] adPos:@"icon" platform:@"our"];
             }
         }
        if (_iconDelegate) {
            [_iconDelegate onIconClick:_currentIconIndex];
        }
    }
}
@end
