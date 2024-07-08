//
//  SDKStartPromotionAd.h
//  Pods
//
//  Created by ivy on 2020/12/25.
//
#import <Foundation/Foundation.h>
NS_ASSUME_NONNULL_BEGIN
@interface SDKStartPromotionAd : NSObject
-(instancetype)initWithData:(nonnull NSDictionary *)data;
-(BOOL)isValid;
-(void)show:(nullable void(^)(NSString* appid))callback;
@end
NS_ASSUME_NONNULL_END
