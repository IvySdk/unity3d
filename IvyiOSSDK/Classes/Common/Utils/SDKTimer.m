//
//  ZSMyTimer.m
//  MusicRadio
//
//  Created by 余冰星 on 16/11/3.
//  Copyright © 2016年 IVYMOBILE INTERNATIONAL ENTERPRISE LIMITED. All rights reserved.
//
#import "SDKTimer.h"
@implementation SDKTimer
{
@private
    int _countDown;
    int _maxCount;
    /// The countdown timer.
    NSTimer *_timer;
    NSDate *_beforeDate;
    dispatch_block_t _onComplete;
    dispatch_block_t _onUpdate;
}

-(instancetype)init
{
    self = [super init];
    [[NSNotificationCenter defaultCenter]addObserver:self selector:@selector(enterBG) name:UIApplicationDidEnterBackgroundNotification object:self];
    [[NSNotificationCenter defaultCenter]addObserver:self selector:@selector(enterFG) name:UIApplicationWillEnterForegroundNotification object:self];
    return self;
}

-(void)dealloc
{
    [[NSNotificationCenter defaultCenter]removeObserver:self name:UIApplicationDidEnterBackgroundNotification object:self];
    [[NSNotificationCenter defaultCenter]removeObserver:self name:UIApplicationWillEnterForegroundNotification object:self];
}

-(SDKTimer *)create:(int)second timeInterval:(double)interval onComplete:(void (^ __nullable)(void))onComplete onUpdate:(void (^ __nullable)(void))onUpdate
{
    [self stop];
    _maxCount = _countDown = second < 0 ? 0 : second;
    _onComplete = onComplete;
    _onUpdate = onUpdate;
    _timer = [NSTimer timerWithTimeInterval:interval target:self selector:@selector(update:) userInfo:nil repeats:YES];
    return self;
}

/**
 *  进入后台记录当前时间
 */
-(void)enterBG {
    DLog(@"[timer] 应用进入后台啦");
    _beforeDate = [NSDate date];
}

/**
 *  返回前台时更新倒计时值
 */
-(void)enterFG {
    DLog(@"[timer] 应用将要进入到前台");
    NSDate * now = [NSDate date];
    int interval = (int)ceil([now timeIntervalSinceDate:_beforeDate]);
    int val = _countDown - interval;
    if(val > 1){
        _countDown -= interval;
    }else{
        _countDown = 1;
    }
}

-(void)start
{
    if(_timer) {
        [[NSRunLoop currentRunLoop] addTimer:_timer forMode:NSRunLoopCommonModes];
        _isRunning = YES;
    }
}

-(void)stop
{
    if (_timer) {
        [_timer invalidate];
        _timer = nil;
    }
    _isRunning = NO;
}

-(int)currentCount
{
    return _maxCount - _countDown;
}

/**
 *  倒计时逻辑
 */
-(void)update:(NSTimer *)timer {
    
    switch (_countDown) {
        case 1:
            //            DLog(@"[timer] 倒计时结束，触发方法");
            [self stop];
            if(_onComplete) {
                _onComplete();
            }
            break;
        default:
            _countDown -=1;
            if(_onUpdate) {
                _onUpdate();
            }
            //            DLog(@"[timer] 倒计时中：%d",_countDown);
            break;
    }
}

+(SDKTimer *)startTimer:(int)second interval:(double)interval onComplete:(void (^ __nullable)(void))onComplete onUpdate:(void (^ __nullable)(void))onUpdate
{
    SDKTimer *timer = [[[SDKTimer alloc] init] create:second timeInterval:interval onComplete:onComplete onUpdate:onUpdate];
    [timer start];
    return timer;
}
@end

