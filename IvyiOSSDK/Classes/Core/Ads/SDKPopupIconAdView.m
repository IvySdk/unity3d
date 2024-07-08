//
//  SDKPopupIconAdView.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/21.
//

#import "SDKPopupIconAdView.h"
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/UIKit+SDKNetworking.h>
#import <IvyiOSSdk/SDKFacade.h>
@interface SDKIconView : UIImageView
@property (nonatomic, nullable, copy) NSString* appstoreId;
@property (nonatomic, nullable, copy) NSString* title;
@end
@implementation SDKIconView
{
    @private
    SDKPopupIconAdView *_target;
    UILabel *_label;
}
@synthesize appstoreId = _appstoreId;
@synthesize title = _title;
-(instancetype)init
{
    self = [super init];
    _label = [[UILabel alloc] init];
    _label.font = [UIFont systemFontOfSize:11];;
    _label.textColor = [UIColor blackColor];
    _label.numberOfLines = 2;
    [self setUserInteractionEnabled:YES];
    [self addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onIconClick:)]];
    return self;
}

-(void)setTitle:(NSString *)title
{
    _title = title;
    _label.text = title;
    CGFloat w = self.bounds.size.width;
    _label.frame = CGRectMake(0, 0, w, w);
    [_label sizeToFit];
    CGPoint point = self.center;
    _label.center = CGPointMake(point.x, point.y + w / 2 + 17);
    if (![_label superview]) {
        [[self superview] addSubview:_label];
    }
}

-(void)setAppstoreId:(NSString *)appstoreId
{
    _appstoreId = appstoreId;
    [SDKFacade trackCrossPromoteImpression:appstoreId type:SDK_ADTYPE_ICON];
}

-(void)onIconClick:(UITapGestureRecognizer *)gestureRecognizer
{
    [SDKFacade trackAndOpenStore:_appstoreId type:SDK_ADTYPE_ICON];
    [SDKPopupIconAdView hide];
    [[SDKFacade sharedInstance] logAdClick:[SDKConstants getAdTypeName:SDK_ADTYPE_ICON] adPos:@"icon" platform:@"our"];
}

+(instancetype)createInstance
{
    return [[SDKIconView alloc] init];
}
@end

@implementation SDKPopupIconAdView
{
    @protected
    NSArray<SDKIconView *>* iconViews;
    NSArray<UILabel *>* iconLabels;
    UILabel *titleLabel;
    CAKeyframeAnimation* _iconAnimation;
    UIView *container;
    int _curAnimIdx;
    NSArray *_data;
    unsigned long _iconCount;
}

-(instancetype)init
{
    self = [super init];
    UITapGestureRecognizer *tapRecognizer = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onBlankClick:)];
    [self addGestureRecognizer:tapRecognizer];
    _curAnimIdx = -1;
    [self setBackgroundColor:[UIColor colorWithRed:0.33 green:0.33 blue:0.33 alpha:0.5]];
    [self setFrame:ScreenRect];
    if(!container) {
        container = [[UIView alloc] init];
        [container setBackgroundColor:[UIColor colorWithWhite:0.9 alpha:0.9]];
        container.layer.cornerRadius = 20;
        container.layer.masksToBounds = true;
        [self addSubview:container];
    }
    if(!iconViews) {
        iconViews = [NSArray arrayWithObjects:[SDKIconView createInstance], [SDKIconView createInstance], [SDKIconView createInstance], [SDKIconView createInstance], nil];
        CGFloat margin = 33, imageSize = 80, moveUp = 16;
        for (UIImageView *image in iconViews) {
            [image setEnableFade:YES];
            image.layer.cornerRadius = 15;
            image.layer.masksToBounds = true;
            [container addSubview:image];
        }
        UIImageView *image = [iconViews objectAtIndex:0];
        [image setFrame:CGRectMake(margin, margin - moveUp, imageSize, imageSize)];
        image = [iconViews objectAtIndex:1];
        [image setFrame:CGRectMake(margin * 2 + imageSize, margin - moveUp, imageSize, imageSize)];
        image = [iconViews objectAtIndex:2];
        [image setFrame:CGRectMake(margin, margin * 2 + imageSize - moveUp / 4, imageSize, imageSize)];
        image = [iconViews objectAtIndex:3];
        [image setFrame:CGRectMake(margin * 2 + imageSize, margin * 2 + imageSize - moveUp / 4, imageSize, imageSize)];
        CGFloat container_size = margin * 3 + imageSize * 2;
        [container setFrame:CGRectMake(0, 0, container_size, container_size)];
    }
    if(!titleLabel) {
        NSDictionary *infoDict = [[NSBundle mainBundle] infoDictionary];
        id tmp = [infoDict objectForKey:@"isApp"];
        BOOL isApp = tmp ? [(NSNumber *)tmp boolValue] : NO;
        titleLabel = [[UILabel alloc] init];
        UIFont *font = [UIFont boldSystemFontOfSize:40];
        titleLabel.font = font;
        titleLabel.text = isApp ? @"New Apps" : @"New Games";
        titleLabel.textColor = [UIColor whiteColor];
        titleLabel.numberOfLines = 1;
        [self addSubview:titleLabel];
        [titleLabel sizeToFit];
    }
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
        _iconAnimation.repeatCount = 2;
        _iconAnimation.removedOnCompletion = YES;
        [_iconAnimation setDelegate:self];
    }
    return self;
}

-(void)onBlankClick:(UITapGestureRecognizer *)gestureRecognizer
{
    [self hide];
}

-(void)animationDidStart:(CAAnimation *)anim
{
}

-(void)animationDidStop:(CAAnimation *)anim finished:(BOOL)flag
{
    if (flag) {
        [self animateAnother];
    }
}

-(void)animateAnother
{
    if(_iconCount < 1)return;
    if(_curAnimIdx < 0 || _iconCount == 1) {
        _curAnimIdx = 0;
    } else {
        int idx = _curAnimIdx;
        while (idx == _curAnimIdx) {
            idx = arc4random() % _iconCount;
        }
        _curAnimIdx = idx;
    }
    UIImageView* image = [iconViews objectAtIndex:_curAnimIdx];
    [image.layer addAnimation:_iconAnimation forKey:nil];
}

-(void)hide
{
    __weak typeof (self) weakself = self;
    [UIView animateWithDuration:0.3 animations:^{
        self->titleLabel.alpha = 0;
    }];
    [UIView animateWithDuration:0.1 animations:^{
        self->container.transform = CGAffineTransformScale(CGAffineTransformIdentity, 1.2, 1.2);
    } completion:^(BOOL finished) {
        [UIView animateWithDuration:0.2 animations:^{
            self->container.transform = CGAffineTransformScale(CGAffineTransformIdentity, 0.001, 0.001);
        } completion:^(BOOL finished) {
            if ([weakself superview]) {
                [weakself removeFromSuperview];
            }
        }];
    }];
}

-(void)popup:(NSArray *)arr
{
    if(arr && arr.count > 0) {
        _data = arr;
        _iconCount = arr.count;
        unsigned long count = iconViews.count;
        for (int i=0; i<count; i++) {
            SDKIconView* image = [iconViews objectAtIndex:i];
            if (i < _iconCount) {
                NSDictionary *app = [arr objectAtIndex:i];
                NSURL *url = [NSURL URLWithString:[app objectForKey:@"icon"]];
                image.appstoreId = [app objectForKey:@"appstoreid"];
                image.title = [app objectForKey:@"name"];
                [image setImageWithURL:url];
                [image setHidden:NO];
            } else {
                [image setHidden:YES];
            }
        }
        
        [container sizeToFit];
        CGPoint point = [self convertPoint:self.center fromView:self.superview];
        container.center = CGPointMake(point.x, point.y + 10);
        titleLabel.center = CGPointMake(point.x, point.y - container.bounds.size.height / 2 - 20);
        titleLabel.alpha = 0;
        [UIView transitionWithView:titleLabel duration:0.8 options:UIViewAnimationOptionTransitionCrossDissolve animations:^{
            self->titleLabel.alpha = 1;
        } completion:nil];
        
        [self animateAnother];
        if(![self superview]) {
            UIWindow *keyWindow = [UIApplication sharedApplication].keyWindow;
            [keyWindow addSubview:self];
        }
        container.transform = CGAffineTransformScale(CGAffineTransformIdentity, CGFLOAT_MIN, CGFLOAT_MIN);
        [UIView animateWithDuration:0.3 animations:^{
            // 以动画的形式将view慢慢放大至原始大小的1.2倍
            self->container.transform = CGAffineTransformScale(CGAffineTransformIdentity, 1.2, 1.2);
        } completion:^(BOOL finished) {
            [UIView animateWithDuration:0.2 animations:^{
                // 以动画的形式将view恢复至原始大小
                self->container.transform = CGAffineTransformIdentity;
            }];
        }];
    }
}

+(instancetype)_instance
{
    static SDKPopupIconAdView *_view = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _view = [[SDKPopupIconAdView alloc] init];
    });
    return _view;
}

+(void)show:(NSArray *)arr
{
    [[[self class] _instance] popup:arr];
    [[SDKFacade sharedInstance] logAdImpression:[SDKConstants getAdTypeName:SDK_ADTYPE_ICON] adPos:@"icon" platform:@"our"];
}

+(void)hide
{
    [[[self class] _instance] hide];
}
@end
