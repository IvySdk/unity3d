//
//  SDKGCDTimer.h
//
//  Created by Ice on 2018/2/26.
//  Copyright © 2018年 Ice. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SDKDefine.h"

// 自定义timer添加场景
typedef  NS_ENUM(NSInteger, SDKGCDTimerOption){
    SDKGCDTimerOptionCancelPrevAction = 0, // 取消上一次timer计时任务
    SDKGCDTimerOptionMergePrevAction,      // 合并上一次timer计时任务
};


@interface SDKGCDTimer : NSObject

// 单例
+ (SDKGCDTimer *)sharedInstance;


/**
 启动一个timer (默认精度为0.1s)
 @param timerName       timer的名称，作为唯一标识。
 @param interval        执行的时间间隔。
 @param queue           timer将被放入的队列，也就是最终action执行的队列。传入nil将自动放到一个全局子线程队列中。
 @param repeats         timer是否循环调用。
 @param option          多次schedule同一个timer时的操作选项(目前提供将之前的任务废除或合并的选项)。
 @param action          时间间隔到点时执行的block。
 */
- (void)scheduleGCDTimerWithName:(NSString *)timerName
                        interval:(double)interval
                           queue:(dispatch_queue_t)queue
                         repeats:(BOOL)repeats
                          option:(SDKGCDTimerOption)option
                          action:(dispatch_block_t)action;

/**
 取消timer
 @param timerName timer的名称，作为唯一标识。
 */
- (void)cancelTimerWithName:(NSString *)timerName;

- (void)cancelAllTimer;
@end
