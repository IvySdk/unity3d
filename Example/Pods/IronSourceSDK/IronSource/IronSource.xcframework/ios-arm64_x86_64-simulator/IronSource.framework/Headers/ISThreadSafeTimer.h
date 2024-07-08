//
//  ISThreadSafeTimer.h
//  IronSource
//
//  Created by Ariel Barsheshet on 19/10/2022.
//  Copyright © 2022 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>

#ifndef ISThreadSafeTimer_h
#define ISThreadSafeTimer_h

@protocol ISSafeTimerDelegate

-(void)onSafeTimerTick;

@end


/*
 a thread safe timer for start/stop the timer from different threads. once the timer fires, the onSafeTimerTick is invoked for the delegate.
 */
@interface ISThreadSafeTimer : NSObject

- (instancetype)initWithTimeInterval:(NSTimeInterval)timeInterval
                        shouldRepeat:(BOOL)shouldRepeat
                            delegate:(id<ISSafeTimerDelegate>)delegate;
- (void)start;
- (void)stop;

@end


#endif /* ISThreadSafeTimer_h */
