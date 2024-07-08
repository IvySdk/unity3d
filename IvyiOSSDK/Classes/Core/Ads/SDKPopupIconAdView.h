//
//  SDKPopupIconAdView.h
//  Bolts
//
//  Created by 余冰星 on 2017/12/21.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
@interface SDKPopupIconAdView : UIView<CAAnimationDelegate>
-(void)hide;
+(void)show:(nonnull NSArray *)arr;
+(void)hide;
@end
