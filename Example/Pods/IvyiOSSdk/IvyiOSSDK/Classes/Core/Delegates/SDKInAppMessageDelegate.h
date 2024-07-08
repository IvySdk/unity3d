#ifndef SDKInAppMessageDelegate_h
#define SDKInAppMessageDelegate_h
#import <Foundation/Foundation.h>

@protocol SDKInAppMessageDelegate<NSObject>
- (void)onInAppMessageDisplayed:(nonnull NSString*)dataJson;
- (void)inAppMessageDismissed;
- (void)inAppMessageClicked;
- (void)inAppMessageEvent:(nonnull NSString*) dataJson;

@end

#endif /* SDKInAppMessageDelegate_h */
