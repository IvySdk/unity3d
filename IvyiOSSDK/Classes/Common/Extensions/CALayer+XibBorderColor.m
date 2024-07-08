//
//  CALayer+XibBorderColor.m
//  Bolts
//
//  Created by 余冰星 on 2018/1/4.
//

#import "CALayer+XibBorderColor.h"
#import <UIKit/UIKit.h>
@implementation CALayer (XibBorderColor)
- (void)setBorderColorWithUIColor:(UIColor *)color
{
    self.borderColor = color.CGColor;
}
@end
