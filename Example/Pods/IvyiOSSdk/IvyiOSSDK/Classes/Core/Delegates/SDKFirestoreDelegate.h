#ifndef SDKFirestoreDelegate_h
#define SDKFirestoreDelegate_h
#import <Foundation/Foundation.h>

@protocol SDKFirestoreDelegate<NSObject>

- (void) onFirestoreData: (nullable NSString*) collection data:(nullable NSString *)data;
- (void) onFirestoreSuccess:(nullable NSString *)operation data: (nullable NSString *)data;
- (void) onFirestoreFail:(nullable NSString *)operation data: (nullable NSString *)data;
- (void) onFirestoreConnected:(nullable NSString *)data;
- (void) onFirestoreConnectError:(nullable NSString *)data;
- (void) onFirestoreSnapshot:(nullable NSString*) data;

@end

#endif /* SDKFirestoreDelegate_h */
