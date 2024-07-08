//
//  SDKLocalizeManager.h
//  Pods
//
//  Created by LiuShulong on 23/04/2017.
//
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 * 用户语言设置改变通知，通知中的 object 为用户当前选中语言，类型为 NSString 类型。
 */
FOUNDATION_EXPORT NSString* const SDKLanguageDidChangeNotification;

/**
 用户语言管理类，如用户选中的语言等
 */
@interface SDKLocalizeManager : NSObject

/**
 * 用户当前语言，编码请参考苹果本地化编码。
 * https://developer.apple.com/library/content/documentation/MacOSX/Conceptual/BPInternational/LanguageandLocaleIDs/LanguageandLocaleIDs.html
 *
 * return 当前语言编码。如果用户没有设置过则返回系统默认语言，设置过则返回用户设置的语言
 */
+ (NSString *)currentLanguage;

/**
 * 设置用户当前语言，编码请参考苹果本地化编码。该方法是线程安全的
 * https://developer.apple.com/library/content/documentation/MacOSX/Conceptual/BPInternational/LanguageandLocaleIDs/LanguageandLocaleIDs.html
 *
 * param language 语言编码，如 @"en"，@"zh-Hant"
 * return YES - 设置成功， NO - 设置失败。
 */
+ (BOOL)setLanguage:(NSString *)language;



@end

NS_ASSUME_NONNULL_END
