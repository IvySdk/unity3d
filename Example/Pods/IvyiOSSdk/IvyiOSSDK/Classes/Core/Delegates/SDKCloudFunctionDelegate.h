#ifndef SDKCloudFunctionDelegate_h
#define SDKCloudFunctionDelegate_h
#import <Foundation/Foundation.h>

@protocol SDKCloudFunctionDelegate<NSObject>

- (void)onCloudFunctionResult:(nullable NSString *)data;

- (void)onCloudFunctionFailed:(nullable NSString *)data;

@end

#endif /* SDKCloudFunctionDelegate_h */
