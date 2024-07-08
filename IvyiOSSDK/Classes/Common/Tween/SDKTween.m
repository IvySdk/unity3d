
#import "SDKTween.h"

#define kSDKTweenFramerate 1.0/60

@implementation SDKTweenPeriod
@synthesize startValue;
@synthesize endValue;
@synthesize tweenedValue;
@synthesize duration;
@synthesize delay;
@synthesize startOffset;

+ (id)periodWithStartValue:(CGFloat)aStartValue endValue:(CGFloat)anEndValue duration:(CGFloat)duration {
    SDKTweenPeriod *period = [SDKTweenPeriod new];
    
    period.startValue = period.tweenedValue = aStartValue;
    period.endValue = anEndValue;
    period.duration = duration;
    period.startOffset = [[SDKTween sharedInstance] timeOffset];
    
    return period;
}

@end

@implementation SDKTweenLerpPeriod
@synthesize startLerp;
@synthesize endLerp;
@synthesize tweenedLerp;

+ (id)periodWithStartValue:(NSValue*)aStartValue endValue:(NSValue*)anEndValue duration:(CGFloat)duration {
    SDKTweenLerpPeriod *period = [[self class] new];
    period.startLerp = aStartValue;
    period.tweenedLerp = aStartValue;
    period.endLerp = anEndValue;
    period.duration = duration;
    period.startOffset = [[SDKTween sharedInstance] timeOffset];
    
    return period;
}

@end

@implementation SDKTweenCGPointLerpPeriod

+ (id)periodWithStartCGPoint:(CGPoint)aStartPoint endCGPoint:(CGPoint)anEndPoint duration:(CGFloat)duration {
    return [SDKTweenCGPointLerpPeriod periodWithStartValue:[NSValue valueWithCGPoint:aStartPoint] endValue:[NSValue valueWithCGPoint:anEndPoint] duration:duration];
}

- (CGPoint)startCGPoint { return [self.startLerp CGPointValue]; }
- (CGPoint)tweenedCGPoint { return [self.tweenedLerp CGPointValue]; }
- (CGPoint)endCGPoint { return [self.endLerp CGPointValue]; }

- (NSValue*)tweenedValueForProgress:(CGFloat)progress {
    
    CGPoint startPoint = self.startCGPoint;
    CGPoint endPoint = self.endCGPoint;
    CGPoint distance = CGPointMake(endPoint.x - startPoint.x, endPoint.y - startPoint.y);
    CGPoint tweenedPoint = CGPointMake(startPoint.x + distance.x * progress, startPoint.y + distance.y * progress);
    
    return [NSValue valueWithCGPoint:tweenedPoint];
    
}

- (void)setProgress:(CGFloat)progress {
    self.tweenedLerp = [self tweenedValueForProgress:progress];
}

@end

@implementation SDKTweenCGRectLerpPeriod

+ (id)periodWithStartCGRect:(CGRect)aStartRect endCGRect:(CGRect)anEndRect duration:(CGFloat)duration {
    return [SDKTweenCGRectLerpPeriod periodWithStartValue:[NSValue valueWithCGRect:aStartRect] endValue:[NSValue valueWithCGRect:anEndRect] duration:duration];
}

- (CGRect)startCGRect { return [self.startLerp CGRectValue]; }
- (CGRect)tweenedCGRect { return [self.tweenedLerp CGRectValue]; }
- (CGRect)endCGRect { return [self.endLerp CGRectValue]; }

- (NSValue *)tweenedValueForProgress:(CGFloat)progress {
    
    CGRect startRect = self.startCGRect;
    CGRect endRect = self.endCGRect;
    CGRect distance = CGRectMake(endRect.origin.x - startRect.origin.x, endRect.origin.y - startRect.origin.y, endRect.size.width - startRect.size.width, endRect.size.height - startRect.size.height);
    CGRect tweenedRect = CGRectMake(startRect.origin.x + distance.origin.x * progress, startRect.origin.y + distance.origin.y * progress, startRect.size.width + distance.size.width * progress, startRect.size.height + distance.size.height * progress);
    
    return [NSValue valueWithCGRect:tweenedRect];
    
}

- (void)setProgress:(CGFloat)progress {
    self.tweenedLerp = [self tweenedValueForProgress:progress];
}

@end

@implementation SDKTweenCGSizeLerpPeriod

+ (id)periodWithStartCGSize:(CGSize)aStartSize endCGSize:(CGSize)anEndSize duration:(CGFloat)duration {
    return [SDKTweenCGRectLerpPeriod periodWithStartValue:[NSValue valueWithCGSize:aStartSize] endValue:[NSValue valueWithCGSize:anEndSize] duration:duration];
}

- (CGSize)startCGSize { return [self.startLerp CGSizeValue]; }
- (CGSize)tweenedCGSize { return [self.tweenedLerp CGSizeValue]; }
- (CGSize)endCGSize { return [self.endLerp CGSizeValue]; }

- (NSValue *)tweenedValueForProgress:(CGFloat)progress {
    
    CGSize startSize = self.startCGSize;
    CGSize endSize = self.endCGSize;
    CGSize distance = CGSizeMake(endSize.width - startSize.width, endSize.height - startSize.height);
    CGSize tweenedSize = CGSizeMake(startSize.width + distance.width * progress, startSize.height + distance.height * progress);
    return [NSValue valueWithCGSize:tweenedSize];
    
}

- (void)setProgress:(CGFloat)progress {
    self.tweenedLerp = [self tweenedValueForProgress:progress];
}

@end

@interface SDKTweenOperation ()
@property (nonatomic) BOOL canUseBuiltAnimation;
@end

@implementation SDKTweenOperation
@synthesize period;
@synthesize target;
@synthesize updateSelector;
@synthesize completeSelector;
@synthesize timingFunction;
@synthesize boundRef;
@synthesize boundObject;
@synthesize boundGetter;
@synthesize boundSetter;
@synthesize canUseBuiltAnimation;
@synthesize override;

#if NS_BLOCKS_AVAILABLE
@synthesize updateBlock;
@synthesize completeBlock;
#endif

@end

@implementation SDKTweenCGPointLerp

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector {
    return [SDKTween lerp:object property:property period:[SDKTweenCGPointLerpPeriod periodWithStartCGPoint:from endCGPoint:to duration:duration] timingFunction:timingFunction target:target completeSelector:selector];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector {
    SDKTweenCGPointLerpPeriod *period = [SDKTweenCGPointLerpPeriod periodWithStartCGPoint:from endCGPoint:to duration:duration];
    period.delay = delay;
    return [SDKTween lerp:object property:property period:period timingFunction:timingFunction target:target completeSelector:selector];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration {
    return [SDKTweenCGPointLerp lerp:object property:property from:from to:to duration:duration timingFunction:NULL target:nil completeSelector:NULL];
}

#if NS_BLOCKS_AVAILABLE
+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    return [SDKTween lerp:object property:property period:[SDKTweenCGPointLerpPeriod periodWithStartCGPoint:from endCGPoint:to duration:duration] timingFunction:timingFunction updateBlock:updateBlock completeBlock:completeBlock];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    
    SDKTweenCGPointLerpPeriod *period = [SDKTweenCGPointLerpPeriod periodWithStartCGPoint:from endCGPoint:to duration:duration];
    [period setDelay:delay];
    
    return [SDKTween lerp:object property:property period:period timingFunction:timingFunction updateBlock:updateBlock completeBlock:completeBlock];
}
#endif

@end

@implementation SDKTweenCGRectLerp

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector {
    return [SDKTween lerp:object property:property period:[SDKTweenCGRectLerpPeriod periodWithStartCGRect:from endCGRect:to duration:duration] timingFunction:timingFunction target:target completeSelector:selector];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector {
    SDKTweenCGRectLerpPeriod *period = [SDKTweenCGRectLerpPeriod periodWithStartCGRect:from endCGRect:to duration:duration];
    period.delay = delay;
    return [SDKTween lerp:object property:property period:period timingFunction:timingFunction target:target completeSelector:selector];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration {
    return [SDKTweenCGRectLerp lerp:object property:property from:from to:to duration:duration timingFunction:NULL target:nil completeSelector:NULL];
}

#if NS_BLOCKS_AVAILABLE
+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    return [SDKTween lerp:object property:property period:[SDKTweenCGRectLerpPeriod periodWithStartCGRect:from endCGRect:to duration:duration] timingFunction:timingFunction updateBlock:updateBlock completeBlock:completeBlock];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    
    SDKTweenCGRectLerpPeriod *period = [SDKTweenCGRectLerpPeriod periodWithStartCGRect:from endCGRect:to duration:duration];
    [period setDelay:delay];
    
    return [SDKTween lerp:object property:property period:period timingFunction:timingFunction updateBlock:updateBlock completeBlock:completeBlock];
}
#endif

@end

@implementation SDKTweenCGSizeLerp

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector {
    return [SDKTween lerp:object property:property period:[SDKTweenCGSizeLerpPeriod periodWithStartCGSize:from endCGSize:to duration:duration] timingFunction:timingFunction target:target completeSelector:selector];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector {
    SDKTweenCGPointLerpPeriod *period = [SDKTweenCGSizeLerpPeriod periodWithStartCGSize:from endCGSize:to duration:duration];
    period.delay = delay;
    return [SDKTween lerp:object property:property period:period timingFunction:timingFunction target:target completeSelector:selector];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration {
    return [SDKTweenCGSizeLerp lerp:object property:property from:from to:to duration:duration timingFunction:NULL target:nil completeSelector:NULL];
}

#if NS_BLOCKS_AVAILABLE
+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    return [SDKTween lerp:object property:property period:[SDKTweenCGSizeLerpPeriod periodWithStartCGSize:from endCGSize:to duration:duration] timingFunction:timingFunction updateBlock:updateBlock completeBlock:completeBlock];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    
    SDKTweenCGSizeLerpPeriod *period = [SDKTweenCGSizeLerpPeriod periodWithStartCGSize:from endCGSize:to duration:duration];
    [period setDelay:delay];
    
    return [SDKTween lerp:object property:property period:period timingFunction:timingFunction updateBlock:updateBlock completeBlock:completeBlock];
}
#endif

@end

@interface SDKTween ()
+ (SEL)setterFromProperty:(NSString *)property;
- (void)update;
@end

static SDKTween *instance = nil;
static NSArray *animationSelectorsForCoreAnimation = nil;
static NSArray *animationSelectorsForUIView = nil;

@implementation SDKTween
@synthesize timeOffset;
@synthesize defaultTimingFunction;
@synthesize useBuiltInAnimationsWhenPossible;

+ (SDKTween *)sharedInstance {
    if (instance == nil) {
        instance = [[SDKTween alloc] init];
        instance.useBuiltInAnimationsWhenPossible = YES;
    }
    return instance;
}

+ (SDKTweenOperation *)tween:(id)object property:(NSString*)property from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject*)target completeSelector:(SEL)selector {
    
    SDKTweenPeriod *period = [SDKTweenPeriod periodWithStartValue:from endValue:to duration:duration];
    SDKTweenOperation *operation = [SDKTweenOperation new];
    operation.period = period;
    operation.timingFunction = timingFunction;
    operation.target = target;
    operation.completeSelector = selector;
    operation.boundObject = object;
    operation.boundGetter = NSSelectorFromString([NSString stringWithFormat:@"%@", property]);
    operation.boundSetter = [SDKTween setterFromProperty:property];
    [operation addObserver:[SDKTween sharedInstance] forKeyPath:@"period.tweenedValue" options:NSKeyValueObservingOptionNew context:NULL];
    
    [[SDKTween sharedInstance] performSelector:@selector(addTweenOperation:) withObject:operation afterDelay:0];
    return operation;
    
}

+ (SDKTweenOperation *)tween:(CGFloat *)ref from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject*)target completeSelector:(SEL)selector {
    
    SDKTweenPeriod *period = [SDKTweenPeriod periodWithStartValue:from endValue:to duration:duration];
    SDKTweenOperation *operation = [SDKTweenOperation new];
    operation.period = period;
    operation.timingFunction = timingFunction;
    operation.target = target;
    operation.completeSelector = selector;
    operation.boundRef = ref;
    [operation addObserver:[SDKTween sharedInstance] forKeyPath:@"period.tweenedValue" options:NSKeyValueObservingOptionNew context:NULL];
    
    [[SDKTween sharedInstance] performSelector:@selector(addTweenOperation:) withObject:operation afterDelay:0];
    return operation;
    
}

+ (SDKTweenOperation *)tween:(id)object property:(NSString*)property from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration {
    return [SDKTween tween:object property:property from:from to:to duration:duration timingFunction:NULL target:nil completeSelector:NULL];
}

+ (SDKTweenOperation *)tween:(CGFloat *)ref from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration {
    return [SDKTween tween:ref from:from to:to duration:duration timingFunction:NULL target:nil completeSelector:NULL];
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property period:(SDKTweenLerpPeriod <SDKTweenLerpPeriod> *)period  timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector {
    
    //SDKTweenPeriod *period = [SDKTweenLerpPeriod periodWithStartValue:from endValue:to duration:duration];
    SDKTweenOperation *operation = [SDKTweenOperation new];
    operation.period = period;
    operation.timingFunction = timingFunction;
    operation.target = target;
    operation.completeSelector = selector;
    operation.boundObject = object;
    operation.boundGetter = NSSelectorFromString([NSString stringWithFormat:@"%@", property]);
    operation.boundSetter = [SDKTween setterFromProperty:property];
    [operation addObserver:[SDKTween sharedInstance] forKeyPath:@"period.tweenedLerp" options:NSKeyValueObservingOptionNew context:NULL];
    
    [[SDKTween sharedInstance] performSelector:@selector(addTweenOperation:) withObject:operation afterDelay:0];
    return operation;
    
}

#if NS_BLOCKS_AVAILABLE
+ (SDKTweenOperation *)tween:(id)object property:(NSString*)property from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    
    SDKTweenPeriod *period = [SDKTweenPeriod periodWithStartValue:from endValue:to duration:duration];
    SDKTweenOperation *operation = [SDKTweenOperation new];
    operation.period = period;
    operation.timingFunction = timingFunction;
    operation.updateBlock = updateBlock;
    operation.completeBlock = completeBlock;
    operation.boundObject = object;
    operation.boundGetter = NSSelectorFromString([NSString stringWithFormat:@"%@", property]);
    operation.boundSetter = [SDKTween setterFromProperty:property];
    [operation addObserver:[SDKTween sharedInstance] forKeyPath:@"period.tweenedValue" options:NSKeyValueObservingOptionNew context:NULL];
    
    [[SDKTween sharedInstance] performSelector:@selector(addTweenOperation:) withObject:operation afterDelay:0];
    return operation;
    
}

+ (SDKTweenOperation *)tween:(CGFloat *)ref from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    
    SDKTweenPeriod *period = [SDKTweenPeriod periodWithStartValue:from endValue:to duration:duration];
    SDKTweenOperation *operation = [SDKTweenOperation new];
    operation.period = period;
    operation.timingFunction = timingFunction;
    operation.updateBlock = updateBlock;
    operation.completeBlock = completeBlock;
    operation.boundRef = ref;
    [operation addObserver:[SDKTween sharedInstance] forKeyPath:@"period.tweenedValue" options:NSKeyValueObservingOptionNew context:NULL];
    
    [[SDKTween sharedInstance] performSelector:@selector(addTweenOperation:) withObject:operation afterDelay:0];
    return operation;
    
}

+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property period:(SDKTweenLerpPeriod <SDKTweenLerpPeriod> *)period  timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock {
    
    //SDKTweenPeriod *period = [SDKTweenLerpPeriod periodWithStartValue:from endValue:to duration:duration];
    SDKTweenOperation *operation = [SDKTweenOperation new];
    operation.period = period;
    operation.timingFunction = timingFunction;
    operation.updateBlock = updateBlock;
    operation.completeBlock = completeBlock;
    operation.boundObject = object;
    operation.boundGetter = NSSelectorFromString([NSString stringWithFormat:@"%@", property]);
    operation.boundSetter = [SDKTween setterFromProperty:property];
    [operation addObserver:[SDKTween sharedInstance] forKeyPath:@"period.tweenedLerp" options:NSKeyValueObservingOptionNew context:NULL];
    
    [[SDKTween sharedInstance] performSelector:@selector(addTweenOperation:) withObject:operation afterDelay:0];
    return operation;
    
}
#endif

- (void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary *)change context:(void *)context {
    
    SDKTweenOperation *operation = (SDKTweenOperation*)object;
    
    if ([operation.period isKindOfClass:[SDKTweenLerpPeriod class]]) {
        SDKTweenLerpPeriod *lerpPeriod = (SDKTweenLerpPeriod*)operation.period;
        
        NSUInteger bufferSize = 0;
        NSGetSizeAndAlignment([lerpPeriod.tweenedLerp objCType], &bufferSize, NULL);
        void *tweenedValue = malloc(bufferSize);
        [lerpPeriod.tweenedLerp getValue:tweenedValue];
        
        if (operation.boundObject && [operation.boundObject respondsToSelector:operation.boundGetter] && [operation.boundObject respondsToSelector:operation.boundSetter]) {
            NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:[[operation.boundObject class] instanceMethodSignatureForSelector:operation.boundSetter]];
            [invocation setTarget:operation.boundObject];
            [invocation setSelector:operation.boundSetter];
            [invocation setArgument:tweenedValue atIndex:2];
            [invocation invoke];
        }
        
        free(tweenedValue);
        
    } else {
        
        CGFloat tweenedValue = operation.period.tweenedValue;
        
        if (operation.boundObject && [operation.boundObject respondsToSelector:operation.boundGetter] && [operation.boundObject respondsToSelector:operation.boundSetter]) {
            NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:[[operation.boundObject class] instanceMethodSignatureForSelector:operation.boundSetter]];
            [invocation setTarget:operation.boundObject];
            [invocation setSelector:operation.boundSetter];
            [invocation setArgument:&tweenedValue atIndex:2];
            [invocation invoke];
        } else if (operation.boundRef) {
            *operation.boundRef = tweenedValue;
        }
        
    }
    
}

- (id)init {
    self = [super init];
    if (self != nil) {
        tweenOperations = [[NSMutableArray alloc] init];
        expiredTweenOperations = [[NSMutableArray alloc] init];
        timeOffset = 0;
        if (timer == nil) {
            timer = [NSTimer scheduledTimerWithTimeInterval:kSDKTweenFramerate target:self selector:@selector(update) userInfo:nil repeats:YES];
        }
        self.defaultTimingFunction = &SDKTweenTimingFunctionQuadInOut;
    }
    return self;
}

- (SDKTweenOperation*)addTweenOperation:(SDKTweenOperation*)operation {
    
    if (useBuiltInAnimationsWhenPossible && !operation.override) {
    
        if (animationSelectorsForCoreAnimation == nil) {
            animationSelectorsForCoreAnimation = [[NSArray alloc] initWithObjects:
                                      @"setBounds:",            // CGRect
                                      @"setPosition:",          // CGPoint
                                      @"setZPosition:",         // CGFloat
                                      @"setAnchorPoint:",       // CGPoint
                                      @"setAnchorPointZ:",      // CGFloat
                                      //@"setTransform:",         // CATransform3D
                                      //@"setSublayerTransform:", // CATransform3D
                                      @"setFrame:",             // CGRect
                                      @"setContentsRect"        // CGRect
                                      @"setContentsScale:",     // CGFloat
                                      @"setContentsCenter:",    // CGPoint
                                      //@"setBackgroundColor:",   // CGColorRef
                                      @"setCornerRadius:",      // CGFloat
                                      @"setBorderWidth:",       // CGFloat
                                      @"setOpacity:",           // CGFloat
                                      //@"setShadowColor:",       // CGColorRef
                                      @"setShadowOpacity:",     // CGFloat
                                      @"setShadowOffset:",      // CGSize
                                      @"setShadowRadius:",      // CGFloat
                                      //@"setShadowPath:",
                                      nil];
        }
        
        if (animationSelectorsForUIView == nil) {
            animationSelectorsForUIView = [[NSArray alloc] initWithObjects:
                                        @"setFrame:",           // CGRect
                                        @"setBounds:",          // CGRect
                                        @"setCenter:",          // CGPoint
                                        @"setTransform:",       // CGAffineTransform
                                        @"setAlpha:",           // CGFloat
                                        //@"setBackgroundColor:", // UIColor
                                        @"setContentStretch:",  // CGRect
                                        nil];
        }
        
        if (operation.boundSetter && operation.boundObject && !(operation.timingFunction == &SDKTweenTimingFunctionCADefault ||
                                                                operation.timingFunction == &SDKTweenTimingFunctionCAEaseIn ||
                                                                operation.timingFunction == &SDKTweenTimingFunctionCAEaseOut ||
                                                                operation.timingFunction == &SDKTweenTimingFunctionCAEaseInOut ||
                                                                operation.timingFunction == &SDKTweenTimingFunctionCALinear ||
                                                                operation.timingFunction == &SDKTweenTimingFunctionUIViewEaseIn ||
                                                                operation.timingFunction == &SDKTweenTimingFunctionUIViewEaseOut ||
                                                                operation.timingFunction == &SDKTweenTimingFunctionUIViewEaseInOut ||
                                                                operation.timingFunction == &SDKTweenTimingFunctionUIViewLinear ||
                                                                operation.timingFunction == NULL)) {
            goto complete;
        }
                                                               
        
        if (operation.boundSetter && operation.boundObject && [operation.boundObject isKindOfClass:[CALayer class]]) {
            for (NSString *selector in animationSelectorsForCoreAnimation) {
                NSString *setter = NSStringFromSelector(operation.boundSetter);
                if ([selector isEqualToString:setter]) {
                    NSLog(@"Using Core Animation for %@", NSStringFromSelector(operation.boundSetter));
                    operation.canUseBuiltAnimation = YES;
                    
                    NSString *propertyUnformatted = [selector stringByReplacingCharactersInRange:NSMakeRange(0, 3) withString:@""];
                    NSString *propertyFormatted = [[propertyUnformatted stringByReplacingCharactersInRange:NSMakeRange(0, 1) withString:[[propertyUnformatted substringToIndex:1] lowercaseString]] substringToIndex:[propertyUnformatted length] - 1];
                    //NSLog(@"%@", propertyFormatted);
                    CABasicAnimation *animation = [CABasicAnimation animationWithKeyPath:propertyFormatted];
                    animation.duration = operation.period.duration;
                    
                    if (![operation.period isKindOfClass:[SDKTweenLerpPeriod class]] && ![operation.period conformsToProtocol:@protocol(SDKTweenLerpPeriod)]) {
                        animation.fromValue = [NSNumber numberWithFloat:operation.period.startValue];
                        animation.toValue = [NSNumber numberWithFloat:operation.period.endValue];
                    } else {
                        SDKTweenLerpPeriod *period = (SDKTweenLerpPeriod*)operation.period;
                        animation.fromValue = period.startLerp;
                        animation.toValue = period.endLerp;
                    }
                    
                    if (operation.timingFunction == &SDKTweenTimingFunctionCAEaseIn) {
                        animation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseIn];
                    } else if (operation.timingFunction == &SDKTweenTimingFunctionCAEaseOut) {
                        animation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseOut];
                    } else if (operation.timingFunction == &SDKTweenTimingFunctionCAEaseInOut) {
                        animation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
                    } else if (operation.timingFunction == &SDKTweenTimingFunctionCALinear) {
                        animation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionLinear];
                    } else {
                        animation.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionDefault];
                    }
                    
                    [operation.boundObject setValue:animation.toValue forKeyPath:propertyFormatted];
                    [operation.boundObject addAnimation:animation forKey:@"SDKTweenCAAnimation"];
                    
                    goto complete;
                }
            }
        } else if (operation.boundSetter && operation.boundObject && [operation.boundObject isKindOfClass:[UIView class]]) {
            for (NSString *selector in animationSelectorsForUIView) {
                NSString *setter = NSStringFromSelector(operation.boundSetter);
                if ([selector isEqualToString:setter]) {
                    NSLog(@"Using UIView Animation for %@", NSStringFromSelector(operation.boundSetter));
                    operation.canUseBuiltAnimation = YES;
                    
                    NSString *propertyUnformatted = [selector stringByReplacingCharactersInRange:NSMakeRange(0, 3) withString:@""];
                    NSString *propertyFormatted = [[propertyUnformatted stringByReplacingCharactersInRange:NSMakeRange(0, 1) withString:[[propertyUnformatted substringToIndex:1] lowercaseString]] substringToIndex:[propertyUnformatted length] - 1];
                    
                    NSValue *fromValue = nil;
                    NSValue *toValue = nil;
                    
                    if (![operation.period isKindOfClass:[SDKTweenLerpPeriod class]] && ![operation.period conformsToProtocol:@protocol(SDKTweenLerpPeriod)]) {
                        fromValue = [NSNumber numberWithFloat:operation.period.startValue];
                        toValue = [NSNumber numberWithFloat:operation.period.endValue];
                    } else {
                        SDKTweenLerpPeriod *period = (SDKTweenLerpPeriod*)operation.period;
                        fromValue = period.startLerp;
                        toValue = period.endLerp;
                    }
                    
                    [operation.boundObject setValue:fromValue forKeyPath:propertyFormatted];
                    [UIView beginAnimations:nil context:NULL];
                    [UIView setAnimationDuration:operation.period.duration];
                    
                    if (operation.timingFunction == &SDKTweenTimingFunctionUIViewEaseIn) {
                        [UIView setAnimationCurve:UIViewAnimationCurveEaseIn];
                    } else if (operation.timingFunction == &SDKTweenTimingFunctionUIViewEaseOut) {
                        [UIView setAnimationCurve:UIViewAnimationCurveEaseOut];
                    } else if (operation.timingFunction == &SDKTweenTimingFunctionUIViewEaseInOut) {
                       [UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
                    } else if (operation.timingFunction == &SDKTweenTimingFunctionUIViewLinear) {
                        [UIView setAnimationCurve:UIViewAnimationCurveLinear];
                    }
                    
                    [operation.boundObject setValue:toValue forKeyPath:propertyFormatted];
                    [UIView commitAnimations];
                    
                    goto complete;
                }
            }
        }
        
    }
    
complete:
    [tweenOperations addObject:operation];
    return operation;
}

#if NS_BLOCKS_AVAILABLE
- (SDKTweenOperation*)addTweenPeriod:(SDKTweenPeriod *)period
                        updateBlock:(void (^)(SDKTweenPeriod *period))updateBlock
                    completionBlock:(void (^)())completeBlock {
    return [self addTweenPeriod:period updateBlock:updateBlock completionBlock:completeBlock timingFunction:self.defaultTimingFunction];
}

- (SDKTweenOperation*)addTweenPeriod:(SDKTweenPeriod *)period
                        updateBlock:(void (^)(SDKTweenPeriod *period))anUpdateBlock
                    completionBlock:(void (^)())completeBlock 
                     timingFunction:(SDKTweenTimingFunction)timingFunction {
    
    SDKTweenOperation *tweenOperation = [SDKTweenOperation new];
    tweenOperation.period = period;
    tweenOperation.timingFunction = timingFunction;
    tweenOperation.updateBlock = anUpdateBlock;
    tweenOperation.completeBlock = completeBlock;
    return [self addTweenOperation:tweenOperation];
    
}
#endif

- (SDKTweenOperation*)addTweenPeriod:(SDKTweenPeriod *)period target:(NSObject *)target selector:(SEL)selector {
    return [self addTweenPeriod:period target:target selector:selector timingFunction:self.defaultTimingFunction];
}

- (SDKTweenOperation*)addTweenPeriod:(SDKTweenPeriod *)period target:(NSObject *)target selector:(SEL)selector timingFunction:(SDKTweenTimingFunction)timingFunction {
    
    SDKTweenOperation *tweenOperation = [SDKTweenOperation new];
    tweenOperation.period = period;
    tweenOperation.target = target;
    tweenOperation.timingFunction = timingFunction;
    tweenOperation.updateSelector = selector;
    
    return [self addTweenOperation:tweenOperation];
    
}

- (void)removeTweenOperation:(SDKTweenOperation *)tweenOperation {
    if (tweenOperation != nil) {
        if ([tweenOperations containsObject:tweenOperation]) {
            [expiredTweenOperations addObject:tweenOperation];
        }
    }
}

+ (SEL)setterFromProperty:(NSString *)property {
    return NSSelectorFromString([NSString stringWithFormat:@"set%@:", [property stringByReplacingCharactersInRange:NSMakeRange(0, 1) withString:[[property substringToIndex:1] capitalizedString]]]);
}

- (void)update {
    timeOffset += kSDKTweenFramerate;
    
    for (SDKTweenOperation *tweenOperation in tweenOperations) {
        
        SDKTweenPeriod *period = tweenOperation.period;
        
        // if operation is delayed, pass over it for now
        if (timeOffset <= period.startOffset + period.delay) {
            continue;
        }
        
        CGFloat (*timingFunction)(CGFloat, CGFloat, CGFloat, CGFloat) = tweenOperation.timingFunction;
        if (timingFunction == NULL) {
            timingFunction = self.defaultTimingFunction;
        }
        
        if (timingFunction != NULL && tweenOperation.canUseBuiltAnimation == NO) {
            if (timeOffset <= period.startOffset + period.delay + period.duration) {
                if ([period isKindOfClass:[SDKTweenLerpPeriod class]]) {
                    if ([period conformsToProtocol:@protocol(SDKTweenLerpPeriod)]) {
                        SDKTweenLerpPeriod <SDKTweenLerpPeriod> *lerpPeriod = (SDKTweenLerpPeriod <SDKTweenLerpPeriod> *)period;
                        CGFloat progress = timingFunction(timeOffset - period.startOffset - period.delay, 0.0, 1.0, period.duration);
                        [lerpPeriod setProgress:progress];
                    } else {
                        // @TODO: Throw exception
                        NSLog(@"Class doesn't conform to SDKTweenLerp");
                    }
                } else {
                    // if tween operation is valid, calculate tweened value using timing function
                    period.tweenedValue = timingFunction(timeOffset - period.startOffset - period.delay, period.startValue, period.endValue - period.startValue, period.duration);
                }
            } else {
                // move expired tween operations to list for cleanup
                period.tweenedValue = period.endValue;
                [expiredTweenOperations addObject:tweenOperation];
            }
            
            NSObject *target = tweenOperation.target;
            SEL selector = tweenOperation.updateSelector;
            
            if (period != nil) {
                if (target != nil && selector != NULL) {
                    [target performSelector:selector withObject:period afterDelay:0];    
                }
                
                // Check to see if blocks/GCD are supported
                if (kCFCoreFoundationVersionNumber >= kCFCoreFoundationVersionNumber_iOS_4_0) {
                    // fire off update block
                    if (tweenOperation.updateBlock != NULL) {
                        tweenOperation.updateBlock(period);
                    } 
                }
            }
        } else if (tweenOperation.canUseBuiltAnimation == YES) {
            if (timeOffset > period.startOffset + period.delay + period.duration) {
                [expiredTweenOperations addObject:tweenOperation];
            }
        }
    }
    
    // clean up expired tween operations
    for (__strong SDKTweenOperation *tweenOperation in expiredTweenOperations) {
        
        if (tweenOperation.completeSelector) [tweenOperation.target performSelector:tweenOperation.completeSelector withObject:nil afterDelay:0];
        // Check to see if blocks/GCD are supported
        if (kCFCoreFoundationVersionNumber >= kCFCoreFoundationVersionNumber_iOS_4_0) {        
            if (tweenOperation.completeBlock != NULL) {
                tweenOperation.completeBlock();
            }
        }

        // @HACK: Come up with a better pattern for removing observers.
        @try {
            [tweenOperation removeObserver:[SDKTween sharedInstance] forKeyPath:@"period.tweenedValue"];
        } @catch (id exception) {
        }
        @try {
            [tweenOperation removeObserver:[SDKTween sharedInstance] forKeyPath:@"period.tweenedLerp"];
        } @catch (id exception) {
        }
        
        [tweenOperations removeObject:tweenOperation];
        tweenOperation = nil;
    }
    [expiredTweenOperations removeAllObjects];
}

- (void)dealloc {
    tweenOperations = nil;
    expiredTweenOperations = nil;

    [timer invalidate];
}

@end
