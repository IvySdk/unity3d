//
//  ZSMyTimer.h
//  MusicRadio
//
//  Created by 余冰星 on 16/11/3.
//  Copyright © 2016年 IVYMOBILE INTERNATIONAL ENTERPRISE LIMITED. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SDKDefine.h"
@interface SDKTimer : NSObject
@property (nonatomic, assign) BOOL isRunning;
+(SDKTimer * _Nonnull)startTimer:(int)second interval:(double)interval onComplete:(nullable dispatch_block_t)onComplete onUpdate:(nullable dispatch_block_t)onUpdate;

-(SDKTimer * _Nonnull)create:(int)second timeInterval:(double)interval onComplete:(nullable dispatch_block_t)onComplete onUpdate:(nullable dispatch_block_t)onUpdate;

-(void)start;

-(void)stop;

-(int)currentCount;

@end

