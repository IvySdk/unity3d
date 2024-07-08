//
//  SDKDeliciousInterstitial.h
//  Bolts
//
//  Created by 余冰星 on 2018/5/21.
//

#import <Foundation/Foundation.h>
#import <IvyiOSSdk/SDKInterstitial.h>
#import <AVKit/AVKit.h>

@interface SDKDeliciousInterstitial : SDKInterstitial
@property (nonatomic, strong, nullable) NSArray *adDatas;
@property (nonatomic, assign) CGRect bannerFrame;
-(nonnull instancetype)initWithConfig:(NSDictionary *_Nullable)conf;
- (void)select:(NSInteger)index easeIn:(BOOL)easeIn;
-(void)showAd:(nonnull UIViewController *)vc index:(NSInteger)index easeIn:(BOOL)easeIn;
@end
