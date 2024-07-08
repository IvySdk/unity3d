//
//  SDKDeliciousBannerView.m
//  Bolts
//
//  Created by 余冰星 on 2018/6/8.
//

#import "SDKDeliciousBannerView.h"
#import <IvyiOSSdk/SDKConstants.h>
#import <IvyiOSSdk/SDKDefine.h>
#import "SDKDeliciousInterstitial.h"
#import <IvyiOSSdk/SDKNetworkHelper.h>
#import <IvyiOSSdk/UIImage+animatedGIF.h>
#import <IvyiOSSdk/UIImageView+SDKNetworking.h>
#import <IvyiOSSdk/SDKTween.h>
#import <IvyiOSSdk/SDKFacade.h>
static CAKeyframeAnimation* _iconAnimation;
@implementation SDKDeliciousBannerIconView
{
    @private
    UIImageView *_iconView;
    UIImageView *_iconBg;
    UIImageView *_iconSelectBg;
    BOOL _bgCoverMode;
}
@synthesize select = _select;
@synthesize enableSelectedAnimation = _enableSelectedAnimation;
@synthesize cornerRadius = _cornerRadius;
@synthesize iconWidth = _iconWidth;
@synthesize iconHeight = _iconHeight;
@synthesize iconFrameWidth = _iconFrameWidth;
@synthesize iconFrameHeight = _iconFrameHeight;
@synthesize offsetX = _offsetX;
@synthesize offsetY = _offsetY;
-(instancetype)initWithData:(NSDictionary *)data
{
    self = [super init];
    self.clipsToBounds = NO;
    self.layer.masksToBounds = NO;
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
    _iconWidth = _iconHeight = 90;
    self.autoresizesSubviews = YES;
    self.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
#if DEBUG
    self.layer.borderColor = [[UIColor redColor] CGColor];
    self.layer.borderWidth = 1;
#endif
    if (!_iconView) {
        _iconView = [[UIImageView alloc] init];
        _iconView.layer.masksToBounds = true;
#if DEBUG
        _iconView.layer.borderColor = [[UIColor redColor] CGColor];
        _iconView.layer.borderWidth = 1;
#endif
    }
    if (!_iconBg) {
        _iconBg = [[UIImageView alloc] init];
    }
    if (!_iconSelectBg) {
        _iconSelectBg = [[UIImageView alloc] init];
        _iconSelectBg.hidden = YES;
    }
    _bgCoverMode = !![data objectForKey:@"bgCoverMode"];
    if (data) {
        float scale = [UIScreen mainScreen].scale;
        NSNumber *tmp = [data objectForKey:@"offsetX"];
        _offsetX = tmp ? tmp.floatValue / scale : 0;
        tmp = [data objectForKey:@"offsetY"];
        _offsetY = tmp ? tmp.floatValue / scale : 0;
        tmp = [data objectForKey:@"iconWidth"];
        _iconWidth = tmp ? tmp.floatValue / scale  : 90;
        tmp = [data objectForKey:@"iconHeight"];
        _iconHeight = tmp ? tmp.floatValue / scale  : 90;
        tmp = [data objectForKey:@"iconFrameWidth"];
        _iconFrameWidth = tmp ? tmp.floatValue / scale  : 0;
        tmp = [data objectForKey:@"iconFrameHeight"];
        _iconFrameHeight = tmp ? tmp.floatValue / scale  : 0;
        tmp = [data objectForKey:@"cornerRadius"];
        _cornerRadius = tmp ? tmp.floatValue / scale : 15;
        if (_cornerRadius > 0)
            _iconView.layer.cornerRadius = _cornerRadius;
        
        NSString *bgImgName = [data objectForKey:@"bg"];
        NSURL *bgUrl = [[NSBundle mainBundle] URLForResource:bgImgName.stringByDeletingPathExtension withExtension:bgImgName.pathExtension];
        if (bgUrl) {
            [_iconBg setImageWithURL:bgUrl];
        }
        
        NSString *selectBgName = [data objectForKey:@"selectBg"];
        selectBgName = !selectBgName ? selectBgName : bgImgName;
        if (selectBgName) {
            bgUrl = [[NSBundle mainBundle] URLForResource:selectBgName.stringByDeletingPathExtension withExtension:selectBgName.pathExtension];
            if (bgUrl) {
                [_iconSelectBg setImageWithURL:bgUrl];
            }
        }
    }
    _iconView.frame = CGRectMake(_offsetX, _offsetY, _iconWidth, _iconHeight);
    _iconView.enableFade = YES;
    self.frame = CGRectMake(0, 0, _iconFrameWidth, _iconFrameHeight);
    if(_bgCoverMode) {
        [self addSubview:_iconView];
    }
    if (_iconSelectBg.image) {
        [self addSubview:_iconSelectBg];
    }
    if (_iconBg.image) {
        [self addSubview:_iconBg];
    }
    if(!_bgCoverMode) {
        [self addSubview:_iconView];
    }
    self.userInteractionEnabled = YES;
    return self;
}

-(void)dealloc
{
    _iconView = nil;
    _iconBg = nil;
    _iconSelectBg = nil;
}

-(void)setSelect:(BOOL)select
{
    _select = select;
    if (_iconBg.image && _iconSelectBg.image) {
        _iconBg.hidden = select;
        _iconSelectBg.hidden = !select;
    }
    if (!_iconBg.image && !_iconSelectBg.image) {
        if (select) {
            self.layer.borderColor = [[UIColor cyanColor] CGColor];
            self.layer.borderWidth = 4;
        } else {
            self.layer.borderWidth = 0;
        }
    }
    if (_enableSelectedAnimation) {
        if (select) {
            [self.layer addAnimation:_iconAnimation forKey:@"scale"];
        } else {
            [self.layer removeAnimationForKey:@"scale"];
        }
    }
}

-(void)setFrame:(CGRect)frame
{
    float scaleX = self.frame.size.width > 0 ? frame.size.width / self.frame.size.width : 1;
    float scaleY = self.frame.size.height > 0 ? frame.size.height / self.frame.size.height : 1;
    [super setFrame:frame];
    _iconBg.frame = CGRectMake(0, 0, frame.size.width, frame.size.height);
    if (_iconSelectBg.image) {
        float scale = [UIScreen mainScreen].scale;
        float w = _iconSelectBg.image.size.width * scaleX / scale;
        float h = _iconSelectBg.image.size.height * scaleX / scale;
        float x = (frame.size.width - w) / 2;
        float y = (frame.size.height - h) / 2;
        _iconSelectBg.frame = CGRectMake(x, y, w, h);
    }
    CGRect tmp = _iconView.frame;
    _iconView.frame = CGRectMake(tmp.origin.x * scaleX, tmp.origin.y * scaleY, tmp.size.width * scaleX, tmp.size.height * scaleY);
    if (_iconView.layer.cornerRadius > 0)
        _iconView.layer.cornerRadius = _iconView.layer.cornerRadius * scaleX;
}

-(void)setUrl:(NSURL *)url
{
    [_iconView setImageWithURL:url];
}
@end
@interface SDKDeliciousBannerView()<UIScrollViewDelegate>
@end
@implementation SDKDeliciousBannerView
{
    @private
    NSDictionary *_conf;
    NSMutableArray<SDKDeliciousBannerIconView *> *_itemViews;
    NSMutableDictionary *_iconData;
    UIScrollView *_bannerView;
    UIImageView *_bannerBg;
    NSURL *_iconFrameUrl;
    CGFloat _iconFrameWidth;
    CGFloat _iconFrameHeight;
    SDKDeliciousInterstitial *_inter;
    SDKTweenOperation *_scrollTween;
}
@synthesize adDatas = _adDatas;
@synthesize paddingUp = _paddingUp;
@synthesize paddingLeft = _paddingLeft;
@synthesize paddingRight = _paddingRight;
@synthesize iconSpace = _iconSpace;
@synthesize currentSelectIndex = _currentSelectIndex;
@synthesize bannerDelegate = _bannerDelegate;
@synthesize iconSelectAnimation = _iconSelectAnimation;
@synthesize jumpToVideo = _jumpToVideo;

-(instancetype)initWithConfig:(NSDictionary *)conf
{
    self = [super init];
    _conf = conf;
    _itemViews = [[NSMutableArray alloc] init];
    _currentSelectIndex = -1;
    _iconSpace = _paddingLeft = _paddingRight = _paddingUp = 10;
    float height = 100;
#if DEBUG
    self.layer.borderColor = [[UIColor redColor] CGColor];
    self.layer.borderWidth = 1;
#endif
    if (!_bannerView) {
        _bannerView = [[UIScrollView alloc] init];
#if DEBUG
        _bannerView.layer.borderColor = [[UIColor redColor] CGColor];
        _bannerView.layer.borderWidth = 1;
#endif
        _bannerView.delaysContentTouches = YES;
        _bannerView.delegate = self;
        _bannerView.scrollEnabled = YES;
        _bannerView.decelerationRate = 0.3;
        _bannerView.alwaysBounceHorizontal = YES;
        _bannerView.alwaysBounceVertical = NO;
        _bannerView.bounces = YES;
        _bannerView.canCancelContentTouches = YES;
        _bannerView.showsVerticalScrollIndicator = NO;
        _bannerView.showsHorizontalScrollIndicator = NO;
        _bannerView.clipsToBounds = YES;
        _bannerView.layer.masksToBounds = YES;
    }
    if (!_bannerBg) {
        _bannerBg = [[UIImageView alloc] init];
    }
    if (conf) {
        float scale = [UIScreen mainScreen].scale;
        NSDictionary *data = [conf objectForKey:@"banner"];
        if (data) {
            _jumpToVideo = !![data objectForKey:@"jumpToVideo"];
            NSString *bgImgName = [data objectForKey:@"bg"];
            NSString *bgPath = [[NSBundle mainBundle] pathForResource:bgImgName.stringByDeletingPathExtension ofType:bgImgName.pathExtension];
            if (bgPath) {
                UIImage *bg = [UIImage imageWithContentsOfFile:bgPath];
                if (bg) {
                    height = bg.size.height / scale;
                    [_bannerBg setImage:bg];
                }
            }
            _iconData = [NSMutableDictionary dictionaryWithDictionary:[data objectForKey:@"icon"]];
            
            NSNumber *tmp = [data objectForKey:@"paddingLeft"];
            _paddingLeft = tmp ? tmp.floatValue / scale : 0;
            tmp = [data objectForKey:@"paddingRight"];
            _paddingRight = tmp ? tmp.floatValue / scale : 0;
            tmp = [data objectForKey:@"paddingUp"];
            _paddingUp = tmp ? tmp.floatValue / scale  : 0;
            tmp = [data objectForKey:@"iconSpace"];
            _iconSpace = tmp ? tmp.floatValue / scale  : 0;
            tmp = [data objectForKey:@"iconFrameWidth"];
            [_iconData setObject:tmp forKey:@"iconFrameWidth"];
            _iconFrameWidth = tmp ? tmp.floatValue / scale : 0;
            tmp = [data objectForKey:@"iconFrameHeight"];
            [_iconData setObject:tmp forKey:@"iconFrameHeight"];
            _iconFrameHeight = tmp ? tmp.floatValue / scale : 0;
            tmp = [data objectForKey:@"iconSelectAnimation"];
            _iconSelectAnimation = tmp ? tmp.boolValue : NO;
        }
    }
    _bannerView.frame = CGRectMake(_paddingLeft, 0, Screen_width - _paddingLeft - _paddingRight, _iconFrameHeight);
    self.frame = CGRectMake(0, 0, Screen_width, height);
    
    [self addSubview:_bannerBg];
    [self addSubview:_bannerView];
    
    return self;
}

-(void)setFrame:(CGRect)frame
{
    float scaleX = self.frame.size.width > 0 ? frame.size.width / self.frame.size.width : 1;
    float scaleY = self.frame.size.height > 0 ? frame.size.height / self.frame.size.height : 1;
    [super setFrame:frame];
    _bannerBg.frame = CGRectMake(0, 0, frame.size.width, frame.size.height);
    CGRect tmp = _bannerView.frame;
    _bannerView.frame = CGRectMake(tmp.origin.x * scaleX, tmp.origin.y + scaleY, tmp.size.width * scaleX, frame.size.height);
    _iconSpace = _iconSpace * scaleX;
    _iconFrameWidth = _iconFrameWidth * scaleY;
    _iconFrameHeight = _iconFrameHeight * scaleY;
    [self reloadData];
}

-(void)dealloc
{
    if (_scrollTween) {
        [[SDKTween sharedInstance] removeTweenOperation:_scrollTween];
    }
    _scrollTween = nil;
    _bannerDelegate = nil;
    _bannerView = nil;
    _bannerBg = nil;
    _inter = nil;
    if (_itemViews) {
        [_itemViews removeAllObjects];
        _itemViews = nil;
    }
}

-(void)setAdDatas:(NSArray *)adDatas
{
    _adDatas = adDatas;
    [self resetContentSize];
}

-(void)resetContentSize
{
    float w = _iconFrameWidth + (_adDatas.count - 1) * (_iconSpace + _iconFrameWidth) + 20;
    _bannerView.contentSize = CGSizeMake(w, _iconFrameHeight);
    
    CGPoint point = CGPointMake(_iconFrameWidth * _adDatas.count, 0);
    _bannerView.contentOffset = point;
}

-(NSInteger)iconCount
{
    return _itemViews.count;
}

-(void)reloadData
{
    if (_adDatas && _adDatas.count > 0) {
        if (_itemViews && _itemViews.count > 0) {
            for (UIView *view in _itemViews) {
                [view removeFromSuperview];
            }
            [_itemViews removeAllObjects];
        }
        int i = 0;
        
        for (NSDictionary *data in _adDatas) {
            NSString *icon = [data objectForKey:SDK_ADTAG_GIFICON];
            SDKDeliciousBannerIconView *view = [[SDKDeliciousBannerIconView alloc] initWithData:_iconData];
            view.enableSelectedAnimation = _iconSelectAnimation;
            if (i == 0) {
                view.frame = CGRectMake(10, _paddingUp, _iconFrameWidth, _iconFrameHeight);
            } else {
                view.frame = CGRectMake(i * (_iconFrameWidth + _iconSpace) + 10, _paddingUp, _iconFrameWidth, _iconFrameHeight);
            }
            view.url = [[SDKNetworkHelper sharedHelper] getDownloadPathURLWithURL:icon];
            [view addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClick:)]];
            [_bannerView addSubview:view];
            [_itemViews addObject:view];
            i++;
        }
        [self resetContentSize];
    }
}

-(void)scrollToIndex:(NSInteger)index
{
    index = index < 0 ? 0 : index >= _itemViews.count ? _itemViews.count - 1 : index;
    UIView *view = [_itemViews objectAtIndex:index];
    CGPoint point = _bannerView.contentOffset;
    point.y = 0;
    BOOL enable = YES;
    if (point.x > view.frame.origin.x) {
        point.x = view.frame.origin.x;
    } else if (point.x + _bannerView.frame.size.width < view.frame.origin.x + view.frame.size.width + _iconSpace) {
        point.x = view.frame.origin.x + view.frame.size.width + _iconSpace - _bannerView.frame.size.width;
    } else {
        enable = NO;
    }
    if (enable) {
        if (_scrollTween) {
            [[SDKTween sharedInstance] removeTweenOperation:_scrollTween];
        }
        _scrollTween = [SDKTweenCGPointLerp lerp:_bannerView property:@"contentOffset" from:_bannerView.contentOffset to:point duration:0.3f];
    }
}

-(void)scrollViewDidScroll:(UIScrollView *)scrollView
{
    CGPoint point = scrollView.contentOffset;
    // 限制y轴不动
    point.y = 0.f;
    scrollView.contentOffset = point;
    DLog(@"scroll offsetX = %f, contentSizeW = %f", point.x, _bannerView.contentSize.width);
}

-(void)onClick:(UITapGestureRecognizer *)sender
{
    if (sender) {
        SDKDeliciousBannerIconView *view = (SDKDeliciousBannerIconView *)sender.view;
        NSUInteger index = [_itemViews indexOfObject:view];
        [self select:index];
        if (_bannerDelegate) {
            [_bannerDelegate onDeliciousIconClick:index];
        } else {
            NSDictionary *data = [_adDatas objectAtIndex:index];
            if (data) {
                if (self.jumpToVideo) {
                    NSString *down = [data objectForKey:SDK_ADTAG_DOWN_ICON];
                    NSString *video = [data objectForKey:SDK_ADTAG_VIDEO];
                    if ([[SDKNetworkHelper sharedHelper] hasURLDownloaded:down]
                        && [[SDKNetworkHelper sharedHelper] hasURLDownloaded:video]) {
                        if (!_inter || !_inter.isShowing) {
                            _inter = [[SDKDeliciousInterstitial alloc] initWithConfig:_conf];
                            [_inter showAd:[SDKFacade sharedInstance].rootVC index:index easeIn:NO];
                        }
                    }
                } else {
                    [SDKFacade trackAndOpenStore:[data objectForKey:@"appstoreid"] type:SDK_ADTYPE_GIFICON];
                }
            }
        }
    }
}

-(void)select:(NSInteger)index
{
    @try
    {
        if (_currentSelectIndex >= 0) {
            SDKDeliciousBannerIconView *view = [_itemViews objectAtIndex:_currentSelectIndex];
            view.select = NO;
        }
        SDKDeliciousBannerIconView *view = [_itemViews objectAtIndex:index];
        view.select = YES;
        _currentSelectIndex = index;
        [self scrollToIndex:index];
    }@catch(NSException *ex) {
    }
}
@end
