//
//  SDKGCDTimer.h
//
//  Created by Ice on 2018/2/26.
//  Copyright © 2018年 Ice. All rights reserved.
//

#import <Foundation/Foundation.h>

/**  计时器的时间模型*/
@interface SDKGCDTimerStamp : NSObject

/** 时-分-秒-毫秒*/
@property (nonatomic, copy) NSString *hour;
@property (nonatomic, copy) NSString *minute;
@property (nonatomic, copy) NSString *second;
@property (nonatomic, copy) NSString *millisecond;
@property (nonatomic, assign) NSTimeInterval time;

@end

/** 进度闭包回调*/
typedef void(^SDKGCDTimerProgressBlock)(SDKGCDTimerStamp *progress);
/** 完成闭包回调*/
typedef void(^SDKGCDTimerCompletion)(void);

/** 计时器*/
@interface SDKGCDTimer2 : NSObject

/** 计时器是否为增加模式*/
@property (nonatomic, assign) BOOL isCountDownTimer;
/** 计时器的计时时间*/
@property (nonatomic, assign) NSTimeInterval duration;
/** 计时器触发的精度，默认为100ms触发一次回调，取值区间为100-1000 */
@property (nonatomic, assign) NSInteger interval;
/** 计时器的回调*/
@property (nonatomic, strong) SDKGCDTimerProgressBlock progressBlock;
/** 计时器完成的回调*/
@property (nonatomic, strong) SDKGCDTimerCompletion completion;
/** 是否为暂停状态*/
@property (nonatomic, assign, readonly, getter=isSupsending) BOOL suspend;
/** 是否为运行状态*/
@property (nonatomic, assign, readonly, getter=isRuning) BOOL run;
/** 是否为完成状态*/
@property (nonatomic, assign, readonly, getter=isComplete) BOOL complete;

+(SDKGCDTimer2 * _Nonnull)startTimer:(NSTimeInterval)duration interval:(NSInteger)interval isCountDown:(BOOL)isCountDown onComplete:(nullable SDKGCDTimerCompletion)onComplete onUpdate:(nullable SDKGCDTimerProgressBlock)onUpdate;
/**
 初始化计时器
 
 @param duration 计时的时间
 @param isAscend 是否为增加计时
 @param progressBlock 进度回调
 @param completion 倒计时结束回调
 @return 计时器
 */
- (nonnull instancetype)initWithDuration: (NSTimeInterval)duration interval:(NSInteger)interval isCountDown: (BOOL)isAscend progressBlock: (nullable SDKGCDTimerProgressBlock)progressBlock completionBlock: (nullable SDKGCDTimerCompletion)completion;

/** 开始计时*/
- (void)resume;

/** 暂停计时*/
- (void)suspend;

/** 继续暂停的任务*/
- (void)activate;

/** 停止计时*/
- (void)stop;

/** 重置计时器并开始计时*/
- (void)restart;


@end
