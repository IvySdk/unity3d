//
//  SDKDefine.h
//  Pods
//
//  Created by 余冰星 on 2017/6/12.
//
//
#ifdef DEBUG
//#import <IvyiOSSdk/SDKDebug.h>
#endif
#ifndef SDKDefine_h
#define SDKDefine_h
#define _SDK_EXIST
#define Screen_height  [[UIScreen mainScreen] bounds].size.height
#define Screen_width  [[UIScreen mainScreen] bounds].size.width
#define ScreenRect    CGRectMake(0, 0, Screen_width, Screen_height)
#define KeyWindow       [[UIApplication sharedApplication] keyWindow]
#define GloomyBlackColor  [UIColor colorWithRed:0 green:0 blue:0 alpha:0.5]
#define GloomyClearCloler  [UIColor colorWithRed:1 green:1 blue:1 alpha:0]
#define isPad (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
#define SDKWeakObj(o)   @autoreleasepool {} __weak typeof(o) o ## Weak = o;
#define SDKStringIsEmpty(str) ([str isKindOfClass:[NSNull class]] || str == nil || [str length] < 1 ? YES : NO )

#ifdef DEBUG
//#define DLog(...) NSLog(@"[sdk] %@", [[SDKDebug debug] logWeb:[NSString stringWithFormat:__VA_ARGS__]])
#define DLog(...) NSLog(@"[sdk]::%@", [NSString stringWithFormat:__VA_ARGS__])
#define debugMethod() NSLog(@"%s", __func__)
#else
#define DLog(...)
#define debugMethod()
#endif
#endif /* SDKDefine_h */
