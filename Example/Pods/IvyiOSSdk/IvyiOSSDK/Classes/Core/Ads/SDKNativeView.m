//
//  SDKNativeView.m
//  Bolts
//
//  Created by 余冰星 on 2017/12/28.
//
#import "SDKDefine.h"
#import "SDKNativeView.h"
#import "SDKHelper.h"
#import "SDKJSONHelper.h"
#import "UIImage+animatedGIF.h"
#import <IvyiOSSdk/SDKTimer.h>
@implementation SDKNativeView
{
    NSString *_loadConfigName;
//    NSMutableArray<__kindof NSLayoutConstraint *> *_constraints;
    float scaleX, scaleY;
}
@synthesize vc = _vc;
@synthesize owner = _owner;

-(instancetype)initWithOwner:(id<SDKNativeDelegate>)owner
{
    self = [super init];
    _owner = owner;
    if (_nibView) {
        [_nibView removeFromSuperview];
    }
    NSString *bundleName = [owner bundleName];
    NSString *nibName = [owner nibName];
    NSString *defaultNib = [owner defaultNibName];
    NSString *defaultBundle = [owner defaultBundleName];
    if (![nibName isEqualToString:defaultNib] && [bundleName isEqualToString:defaultBundle]) {
        bundleName = @"mainBundle";
    }
    id bundleOwner = self;
//    NSBundle *bundle = nil;
//    if([bundleName isEqualToString:@"mainBundle"]) {
//        bundle = [NSBundle mainBundle];
//    } else {
//        bundle = [NSBundle bundleForClass:self.class];
//        bundle = [NSBundle bundleWithURL:[bundle URLForResource:[owner bundleName] withExtension:@"bundle"]];
//    }
//    UINib *nib = [UINib nibWithNibName:nibName bundle:bundle];
    UINib *nib = [SDKHelper getNibFromCache:nibName bundleName:bundleName];
    NSArray *arr = [nib instantiateWithOwner:bundleOwner options:nil];

//    NSArray *arr = [bundle loadNibNamed:nibName owner:bundleOwner options:nil];
    _nibView = [arr firstObject];
    if (_nibView) {
        [self addSubview:_nibView];
    } else {
        NSString *msg = [NSString stringWithFormat:@"%@ setNibName failure! Not found the nib resource [%@] in %@ bundle", NSStringFromClass(self.class), nibName, bundleName];
        NSAssert(FALSE, msg);
    }
//    [self setBackgroundColor:[UIColor redColor]];
//    [_nibView setBackgroundColor:[UIColor colorWithRed:0 green:1 blue:0 alpha:0.5]];
    return self;
}

-(void)loadConfig:(nonnull NSString *)json frame:(CGRect)frame useDesignScale:(BOOL)useDesignScale
{
    _config = [SDKJSONHelper getJsonDataFrom:json bundle:nil];
    if (_config) {
        int w = [(NSNumber *)[_config objectForKey:@"w"] intValue];
        int h = [(NSNumber *)[_config objectForKey:@"h"] intValue];
        int sw = [(NSNumber *)[_config objectForKey:@"sw"] intValue];
        int sh = [(NSNumber *)[_config objectForKey:@"sh"] intValue];
        CGRect rect_screen = [[UIScreen mainScreen]bounds];
        CGSize size_screen = rect_screen.size;
        CGFloat scale_screen = [UIScreen mainScreen].scale;
        if (useDesignScale) {
            scaleX = size_screen.width / sw;
            scaleY = size_screen.height / sh;
            frame = CGRectMake(frame.origin.x * scaleX, frame.origin.y * scaleY, w * scaleX, h * scaleY);
        } else {
            frame = CGRectMake(frame.origin.x / scale_screen, frame.origin.y / scale_screen, frame.size.width / scale_screen, frame.size.height / scale_screen);
            scaleX = frame.size.width / w;
            scaleY = frame.size.height / h;
        }
        self.frame = frame;
        if (_loadConfigName && [_loadConfigName isEqualToString:json]) {
            [self doEnterAnim];
            return;
        }
//        if (_constraints) {
//            [_constraints removeAllObjects];
//        } else {
//            _constraints = [[NSMutableArray alloc] init];
//        }
        [_nibView removeConstraints:_nibView.constraints];
        [self setViewsWithConfig:_config];
//        [NSLayoutConstraint activateConstraints:_constraints];
        _loadConfigName = json;
        
    } else {
        DLog(@"[adlog] %@ load %@.json failure! please check!", NSStringFromClass(self.class), json);
        self.frame = ScreenRect;
    }
    [self doEnterAnim];
}

-(void)doEnterAnim
{
    if (_config) {
        id tmp = [_config objectForKey:@"enter"];
        if (tmp) {
            NSString *enter = (NSString *)tmp;
            CATransition *transition = [CATransition animation];
            transition.type = kCATransitionPush;
            if ([enter isEqualToString:@"left"]) {
                transition.subtype = kCATransitionFromLeft;
            } else if ([enter isEqualToString:@"right"]) {
                transition.subtype = kCATransitionFromRight;
            } else if ([enter isEqualToString:@"top"]) {
                transition.subtype = kCATransitionFromBottom;
            } else if ([enter isEqualToString:@"bottom"]) {
                transition.subtype = kCATransitionFromTop;
            }
            tmp = [_config objectForKey:@"enter_duration"];
            transition.duration = tmp ? [(NSNumber *)tmp floatValue] : 0.3;
            transition.delegate = self;
            [self.layer removeAllAnimations];
            [self.layer addAnimation:transition forKey:nil];
        } else {
            [SDKTimer startTimer:2 interval:0.4 onComplete:^{
                [self onViewsDidShown];
            } onUpdate:nil];
        }
    }
}

-(void)animationDidStart:(CAAnimation *)anim
{
    
}

-(void)animationDidStop:(CAAnimation *)anim finished:(BOOL)flag
{
    if (flag) {
        [self onViewsDidShown];
    }
}

-(void)onViewsDidShown
{
}

-(void)setViewsWithConfig:(nonnull NSDictionary *)config
{
}

-(UIColor *)strToColor:(NSString *)urlStr
{
    UIColor *color = nil;
    if (urlStr && [urlStr hasPrefix:@"0x"]) {
        color = [SDKHelper hexToColor:urlStr];
    }
    return color;
}

-(NSURL *)strToURL:(NSString *)urlStr
{
    NSURL *url = nil;
    if (urlStr && urlStr.length > 0) {
        if ([urlStr hasPrefix:@"http://"]) {
            url = [NSURL URLWithString:urlStr];
        } else if([urlStr pathExtension]) {
            url = [[NSBundle mainBundle] URLForResource:urlStr.stringByDeletingPathExtension withExtension:urlStr.pathExtension];
        }
    }
    return url;
}

-(void)setViewBg:(nonnull UIImageView *)view config:(nonnull NSDictionary *)config keyName:(nullable NSString *)key
{
//    [view removeConstraints:view.constraints];
    view.enableFade = YES;
//    [_constraints addObject:[NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeLeft relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeLeft multiplier:1 constant:0]];
//    [_constraints addObject:[NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeTop relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeTop multiplier:1 constant:0]];
//    [_constraints addObject:[NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeWidth relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeWidth multiplier:1 constant:0]];
//    [_constraints addObject:[NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeHeight relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeHeight multiplier:1 constant:0]];
    view.frame = CGRectMake(0, 0, self.frame.size.width, self.frame.size.height);
    NSDictionary *data = key ? [config objectForKey:key] : config;
    NSString *bgUrl = [data objectForKey:@"bg"];
    if (bgUrl) {
        NSURL *url = [self strToURL:bgUrl];
        UIColor *color = [self strToColor:bgUrl];
        if (url) {
            [view setImageWithURL:url];
        } else if (color) {
            [view setBackgroundColor:color];
        }
    }
}

-(void)setViewWithConfig:(nonnull UIView *)view config:(nonnull NSDictionary *)config keyName:(nullable NSString *)key
{
    if (!view) {
        return;
    }
    NSDictionary *data = key ? [config objectForKey:key] : config;
    if (data) {
#if DEBUG
//        view.layer.borderColor = [UIColor redColor].CGColor;
//        view.layer.borderWidth = 2;
#endif
//        [view removeConstraints:view.constraints];
        float x = [(NSNumber *)[data objectForKey:@"x"] floatValue] * scaleX;
        float y = [(NSNumber *)[data objectForKey:@"y"] floatValue] * scaleY;
        float w = ceilf([(NSNumber *)[data objectForKey:@"w"] floatValue] * scaleX);
        float h = ceilf([(NSNumber *)[data objectForKey:@"h"] floatValue] * scaleY);
        
//        [_constraints addObject:[NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeLeft relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeLeft multiplier:1 constant:x]];
//        [_constraints addObject:[NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeTop relatedBy:NSLayoutRelationEqual toItem:self attribute:NSLayoutAttributeTop multiplier:1 constant:y]];
//        [_constraints addObject:[NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeWidth relatedBy:NSLayoutRelationEqual toItem:nil attribute:NSLayoutAttributeWidth multiplier:1 constant:w]];
//        [_constraints addObject:[NSLayoutConstraint constraintWithItem:view attribute:NSLayoutAttributeHeight relatedBy:NSLayoutRelationEqual toItem:nil attribute:NSLayoutAttributeHeight multiplier:1 constant:h]];
        view.frame = CGRectMake(x, y, w, h);
        
        id tmp = [data objectForKey:@"radius"];
        if(tmp) {
            CGFloat radius = [(NSNumber *)tmp floatValue];
            view.layer.cornerRadius = radius * scaleX;
            view.layer.masksToBounds = true;
        }
        UIColor *bgcolor = nil;
        NSURL *imgUrl = nil;
        UIColor *fontColor = [UIColor whiteColor];
        CGFloat fontSize = 10;
        tmp = [data objectForKey:@"bg"];
        if (tmp) {
            NSString *bg = (NSString *)tmp;
            bgcolor = [self strToColor:bg];
            imgUrl = [self strToURL:bg];
        }
        tmp = [data objectForKey:@"color"];
        if (tmp) {
            fontColor = [SDKHelper hexToColor:tmp];
        }
        tmp = [data objectForKey:@"size"];
        if (tmp) {
            fontSize = [(NSNumber *)tmp floatValue] * scaleX;
        }
        if ([view isKindOfClass:[UIButton class]]) {
            UIButton *btn = (UIButton *)view;
            [btn setEnabled:YES];
            if (bgcolor) {
                [btn setBackgroundColor:bgcolor];
            } else if (imgUrl && imgUrl.isFileURL) {
                UIImage *img = nil;
                if ([imgUrl.pathExtension isEqualToString:@"gif"]) {
                    img = [UIImage animatedImageWithAnimatedGIFURL:imgUrl];
                } else {
                    img = [UIImage imageWithData:[NSData dataWithContentsOfURL:imgUrl]];
                }
                if(img) {
                    [btn setBackgroundImage:img forState:UIControlStateNormal];
                    [btn setBackgroundImage:img forState:UIControlStateSelected];
                }
            }
            btn.titleLabel.font = [UIFont systemFontOfSize:fontSize];
            [btn setTitleColor:fontColor forState:UIControlStateNormal];
            [btn setTitleColor:fontColor forState:UIControlStateSelected];
        } else if ([view isKindOfClass:[UIImageView class]]) {
            UIImageView *imgView = (UIImageView *)view;
            if (bgcolor) {
                [imgView setBackgroundColor:bgcolor];
            } else if (imgUrl) {
                [imgView setImageWithURL:imgUrl];
            }
        } else if ([view isKindOfClass:[UILabel class]]) {
            UILabel *label = (UILabel *)view;
            label.font = [UIFont systemFontOfSize:fontSize];
            label.textColor = fontColor;
            NSString *align = [data objectForKey:@"align"];
            NSTextAlignment textAlign = NSTextAlignmentLeft;
            if (align) {
                if ([align isEqualToString:@"right"]) {
                    textAlign = NSTextAlignmentRight;
                } else if ([align isEqualToString:@"center"]) {
                    textAlign = NSTextAlignmentCenter;
                }
            }
            label.textAlignment = textAlign;
        }
        [view setHidden:NO];
    } else {
        [view setHidden:YES];
    }
}

-(void)setFrame:(CGRect)frame
{
    if(_nibView) {
        [_nibView setFrame:CGRectMake(0, 0, frame.size.width, frame.size.height)];
    }
    [super setFrame:frame];
}

-(void)loadNativeAd:(id)nativeAd
{
    DLog(@"[adlog] %@ show ad = %@", NSStringFromClass(self.class), nativeAd);
    _nativeAd = nativeAd;
}

-(void)close
{
    [self removeFromSuperview];
}

-(void)willMoveToSuperview:(UIView *)newSuperview
{
    if (!newSuperview) {
        [_owner recycleView:self];
    }
}
@end
