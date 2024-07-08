//
//  SDKInterstitialViewViewController.h
//  Pods
//
//  Created by 余冰星 on 2017/6/15.
//
//

#import <UIKit/UIKit.h>
#import <IvyiOSSdk/SDKInterstitial.h>

@interface SDKInterstitialViewController : UIViewController<UIViewControllerTransitioningDelegate>
@property (nonatomic, assign) BOOL isShowing;
@property (nonatomic, strong, nullable) SDKInterstitial *owner;
@property (weak, nonatomic) IBOutlet UIImageView *_Nullable imageView;
@property (weak, nonatomic) IBOutlet UIImageView *_Nullable closeBtn;
-(void)show:(nonnull UIViewController *)vc data:(nonnull NSDictionary *)data;
-(void)close;

+(nonnull SDKInterstitialViewController *)fetchInstance;
@end
