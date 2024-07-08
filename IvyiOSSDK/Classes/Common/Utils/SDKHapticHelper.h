#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

typedef enum {
    FeedbackType_Selection,
    FeedbackType_Impact_Light,
    FeedbackType_Impact_Medium,
    FeedbackType_Impact_Heavy,
    FeedbackType_Notification_Success,
    FeedbackType_Notification_Warning,
    FeedbackType_Notification_Error
}FeedbackType;

@interface SDKHapticHelper : NSObject

+ (void)generateFeedback:(FeedbackType)type;
+ (void)playAHAP:(nonnull NSString *)name inDirectory:(nullable NSString *)folder;
@end
