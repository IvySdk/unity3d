#ifndef SDKFirebaseDatabaseDelegate_h
#define SDKFirebaseDatabaseDelegate_h
#import <Foundation/Foundation.h>

@protocol SDKFirebaseDatabaseDelegate<NSObject>

- (void) onChatMessage: (nonnull NSString *)data;

@end

#endif /* SDKFirebaseDatabaseDelegate */
