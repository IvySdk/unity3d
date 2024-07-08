
#import <Foundation/Foundation.h>

#import "SDKTweenTimingFunctions.h"

typedef CGFloat(*SDKTweenTimingFunction)(CGFloat, CGFloat, CGFloat, CGFloat);
#if NS_BLOCKS_AVAILABLE
@class SDKTweenPeriod;
typedef void (^SDKTweenUpdateBlock)(SDKTweenPeriod *period);
typedef void (^SDKTweenCompleteBlock)();
#endif

@interface SDKTweenPeriod : NSObject {
    CGFloat duration;
    CGFloat delay;
    CGFloat startOffset;
    CGFloat startValue;
    CGFloat endValue;
    CGFloat tweenedValue;
}

@property (nonatomic) CGFloat startValue;
@property (nonatomic) CGFloat endValue;
@property (nonatomic) CGFloat tweenedValue;
@property (nonatomic) CGFloat duration;
@property (nonatomic) CGFloat delay;
@property (nonatomic) CGFloat startOffset;

+ (id)periodWithStartValue:(CGFloat)aStartValue endValue:(CGFloat)anEndValue duration:(CGFloat)duration;

@end

@protocol SDKTweenLerpPeriod

- (NSValue*)tweenedValueForProgress:(CGFloat)progress;
- (void)setProgress:(CGFloat)progress;

@end

@interface SDKTweenLerpPeriod : SDKTweenPeriod {
    NSValue *startLerp;
    NSValue *endLerp;
    NSValue *tweenedLerp;
}

@property (nonatomic, copy) NSValue *startLerp;
@property (nonatomic, copy) NSValue *endLerp;
@property (nonatomic, copy) NSValue *tweenedLerp;

+ (id)periodWithStartValue:(NSValue*)aStartValue endValue:(NSValue*)anEndValue duration:(CGFloat)duration;

@end

@interface SDKTweenCGPointLerpPeriod : SDKTweenLerpPeriod <SDKTweenLerpPeriod>
+ (id)periodWithStartCGPoint:(CGPoint)aStartPoint endCGPoint:(CGPoint)anEndPoint duration:(CGFloat)duration;
- (CGPoint)startCGPoint;
- (CGPoint)tweenedCGPoint;
- (CGPoint)endCGPoint;
@end

@interface SDKTweenCGRectLerpPeriod : SDKTweenLerpPeriod <SDKTweenLerpPeriod>
+ (id)periodWithStartCGRect:(CGRect)aStartRect endCGRect:(CGRect)anEndRect duration:(CGFloat)duration;
- (CGRect)startCGRect;
- (CGRect)tweenedCGRect;
- (CGRect)endCGRect;
@end

@interface SDKTweenCGSizeLerpPeriod : SDKTweenLerpPeriod <SDKTweenLerpPeriod>
+ (id)periodWithStartCGSize:(CGSize)aStartSize endCGSize:(CGSize)anEndSize duration:(CGFloat)duration;
- (CGSize)startCGSize;
- (CGSize)tweenedCGSize;
- (CGSize)endCGSize;
@end

@interface SDKTweenOperation : NSObject {
    SDKTweenPeriod *period;
    NSObject *target;
    SEL updateSelector;
    SEL completeSelector;
    SDKTweenTimingFunction timingFunction;
    
    CGFloat *boundRef;
    SEL boundGetter;
    SEL boundSetter;
    
    BOOL override;
    
#if NS_BLOCKS_AVAILABLE
    SDKTweenUpdateBlock updateBlock;
    SDKTweenCompleteBlock completeBlock; 
#endif
    
    @private
    BOOL canUseBuiltAnimation;
}

@property (nonatomic, retain) SDKTweenPeriod *period;
@property (nonatomic, retain) NSObject *target;
@property (nonatomic) SEL updateSelector;
@property (nonatomic) SEL completeSelector;
@property (nonatomic, assign) SDKTweenTimingFunction timingFunction;

#if NS_BLOCKS_AVAILABLE
@property (nonatomic, copy) SDKTweenUpdateBlock updateBlock;
@property (nonatomic, copy) SDKTweenCompleteBlock completeBlock;
#endif

@property (nonatomic, assign) CGFloat *boundRef;
@property (nonatomic, retain) id boundObject;
@property (nonatomic) SEL boundGetter;
@property (nonatomic) SEL boundSetter;
@property (nonatomic) BOOL override;

@end

@interface SDKTweenCGPointLerp : NSObject
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration;
#if NS_BLOCKS_AVAILABLE
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGPoint)from to:(CGPoint)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;
#endif
@end

@interface SDKTweenCGRectLerp : NSObject
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration;
#if NS_BLOCKS_AVAILABLE
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGRect)from to:(CGRect)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;
#endif
@end

@interface SDKTweenCGSizeLerp : NSObject
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration;
#if NS_BLOCKS_AVAILABLE
+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;
+ (SDKTweenOperation *)lerp:(id)object property:(NSString *)property from:(CGSize)from to:(CGSize)to duration:(CGFloat)duration delay:(CGFloat)delay timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;
#endif
@end

@interface SDKTween : NSObject {
    NSMutableArray *tweenOperations;
    NSMutableArray *expiredTweenOperations;
    NSTimer *timer;
    CGFloat timeOffset;
    
    SDKTweenTimingFunction defaultTimingFunction;
    BOOL useBuiltInAnimationsWhenPossible;
}

@property (nonatomic, readonly) CGFloat timeOffset;
@property (nonatomic, assign) SDKTweenTimingFunction defaultTimingFunction;
@property (nonatomic, assign) BOOL useBuiltInAnimationsWhenPossible;

+ (SDKTween *)sharedInstance;

+ (SDKTweenOperation *)tween:(id)object property:(NSString*)property from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject*)target completeSelector:(SEL)selector;

+ (SDKTweenOperation *)tween:(CGFloat*)ref from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject*)target completeSelector:(SEL)selector;

+ (SDKTweenOperation *)tween:(id)object property:(NSString*)property from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration;

+ (SDKTweenOperation *)tween:(CGFloat*)ref from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration;

+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property period:(SDKTweenLerpPeriod <SDKTweenLerpPeriod> *)period timingFunction:(SDKTweenTimingFunction)timingFunction target:(NSObject *)target completeSelector:(SEL)selector;

- (SDKTweenOperation *)addTweenOperation:(SDKTweenOperation*)operation;
- (SDKTweenOperation *)addTweenPeriod:(SDKTweenPeriod *)period target:(NSObject *)target selector:(SEL)selector;
- (SDKTweenOperation *)addTweenPeriod:(SDKTweenPeriod *)period target:(NSObject *)target selector:(SEL)selector timingFunction:(SDKTweenTimingFunction)timingFunction;
- (void)removeTweenOperation:(SDKTweenOperation*)tweenOperation;

#if NS_BLOCKS_AVAILABLE
+ (SDKTweenOperation *)tween:(id)object property:(NSString*)property from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;

+ (SDKTweenOperation *)tween:(CGFloat*)ref from:(CGFloat)from to:(CGFloat)to duration:(CGFloat)duration timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;

+ (SDKTweenOperation *)lerp:(id)object property:(NSString*)property period:(SDKTweenLerpPeriod <SDKTweenLerpPeriod> *)period timingFunction:(SDKTweenTimingFunction)timingFunction updateBlock:(SDKTweenUpdateBlock)updateBlock completeBlock:(SDKTweenCompleteBlock)completeBlock;

- (SDKTweenOperation *)addTweenPeriod:(SDKTweenPeriod *)period updateBlock:(SDKTweenUpdateBlock)updateBlock completionBlock:(SDKTweenCompleteBlock)completeBlock;
- (SDKTweenOperation *)addTweenPeriod:(SDKTweenPeriod *)period updateBlock:(SDKTweenUpdateBlock)updateBlock completionBlock:(SDKTweenCompleteBlock)completionBlock timingFunction:(SDKTweenTimingFunction)timingFunction;
#endif

@end
