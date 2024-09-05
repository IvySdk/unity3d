//
//  ISTimer.h
//  Environment
//
//  Created by Liron Matityahu on 19/01/2023.
//
#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

//needs to be retained
@interface ISTimer : NSObject

typedef void(^ISTimerBlock)(ISTimer * _Nullable timer);
typedef void(^ISTimerCancelBlock)(void);

- (instancetype)initWithTimeMeasurement:(NSMeasurement *)timeMeasurement
                                  queue:(nullable dispatch_queue_t)queue
                                repeats:(BOOL)repeats
                                  block:(ISTimerBlock)block
                            cancelBlock:(nullable ISTimerCancelBlock)cancelBlock;

+ (instancetype)scheduledTimerWithTimeMeasurement:(NSMeasurement *)timeMeasurement
                                          repeats:(BOOL)repeats
                                            block:(ISTimerBlock)block;

+ (instancetype)scheduledTimerWithTimeMeasurement:(NSMeasurement *)timeMeasurement
                                            queue:(nullable dispatch_queue_t)queue
                                          repeats:(BOOL)repeats
                                            block:(ISTimerBlock)block
                                      cancelBlock:(nullable ISTimerCancelBlock)cancelBlock;



- (void)activate;
- (BOOL)canceled;
- (void)cancel;

@end

NS_ASSUME_NONNULL_END
