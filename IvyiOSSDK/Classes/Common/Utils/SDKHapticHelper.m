#import "SDKHapticHelper.h"
#import <CoreHaptics/CoreHaptics.h>
#import <AVFoundation/AVFoundation.h>
@implementation SDKHapticHelper

+(void)playAHAP:(nonnull NSString *)name inDirectory:(nullable NSString *)folder
{
    NSError *error = nil;
    if (@available(iOS 13.0, *)) {
        __strong static CHHapticEngine *engine;
        engine = [[CHHapticEngine alloc] initAndReturnError:&error];
        [engine startAndReturnError:&error];
        NSURL *url = [[NSBundle mainBundle] URLForResource:name withExtension:@"ahap" subdirectory:folder];
        if([engine playPatternFromURL:url error:&error]) {
            [engine notifyWhenPlayersFinished:^CHHapticEngineFinishedAction(NSError * _Nullable error) {
                [engine stopWithCompletionHandler:nil];
                return CHHapticEngineFinishedActionStopEngine;
            }];
        } else {
            [[self class] generateFeedback:FeedbackType_Impact_Medium];
        }
    } else {
        [[self class] generateFeedback:FeedbackType_Impact_Medium];
    }
}

+(void)generateFeedback:(FeedbackType)type{
    
    if ([[UIDevice currentDevice] systemVersion].floatValue < 10.0){
        return;
    }
    
    switch (type) {
        case FeedbackType_Selection:
            [self generateSelectionFeedback];
            break;
        case FeedbackType_Impact_Light:
            [self generateImpactFeedback:UIImpactFeedbackStyleLight];
            break;
        case FeedbackType_Impact_Medium:
            [self generateImpactFeedback:UIImpactFeedbackStyleMedium];
            break;
        case FeedbackType_Impact_Heavy:
            [self generateImpactFeedback:UIImpactFeedbackStyleHeavy];
            break;
        case FeedbackType_Notification_Success:
            [self generateNotificationFeedback:UINotificationFeedbackTypeSuccess];
            break;
        case FeedbackType_Notification_Warning:
            [self generateNotificationFeedback:UINotificationFeedbackTypeWarning];
            break;
        case FeedbackType_Notification_Error:
            [self generateNotificationFeedback:UINotificationFeedbackTypeError];
            break;
        default:
            break;
    }
}

+(void)generateSelectionFeedback{
    UISelectionFeedbackGenerator *generator = [[UISelectionFeedbackGenerator alloc] init];
    [generator prepare];
    [generator selectionChanged];
    generator = nil;
}

+(void)generateImpactFeedback:(UIImpactFeedbackStyle)style{
    UIImpactFeedbackGenerator *generator = [[UIImpactFeedbackGenerator alloc] initWithStyle:style];
    [generator prepare];
    [generator impactOccurred]; 
    generator = nil;
}

+(void)generateNotificationFeedback:(UINotificationFeedbackType)notificationType{
    UINotificationFeedbackGenerator *generator = [[UINotificationFeedbackGenerator alloc] init];
    [generator prepare];
    [generator notificationOccurred:notificationType];
    generator = nil;
}

@end
