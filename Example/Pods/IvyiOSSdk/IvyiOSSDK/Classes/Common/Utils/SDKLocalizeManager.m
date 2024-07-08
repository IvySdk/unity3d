//
//  SDKLocalizeManager.m
//  Pods
//
//  Created by LiuShulong on 23/04/2017.
//
//

#import "SDKLocalizeManager.h"
#import "SDKHelper.h"
NSString * const SDKLanguageDidChangeNotification = @"SDKLanguageDidChangeNotification";
NSString * const SDKLanguageKey = @"SDKLanguageKey";
NSString * const SDKAppleLanguageKey = @"AppleLanguages";

@interface SDKLocalizeManager ()

@property (nonatomic,copy) NSString *userLanguage;/**<用户当前语言设置,内存保存一份提高查询效率 */

@end

@implementation SDKLocalizeManager

+ (instancetype)sharedInstance {
    static dispatch_once_t onceToken;
    static SDKLocalizeManager *manager;
    dispatch_once(&onceToken, ^{
        manager = [[SDKLocalizeManager alloc] init];
    });
    return manager;
}

- (instancetype)init {
    
    if (self = [super init]) {
        NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
        _userLanguage = [ud stringForKey:SDKLanguageKey];
    }
    return self;
}

+ (NSString *)currentLanguage {
    
    //用户主动设置的语言
    NSString *language = [SDKLocalizeManager sharedInstance].userLanguage;
    if (language) {
        return language;
    }
    
    //系统默认语言
    NSString *syslanguage = [SDKHelper getLangcode];
    [SDKLocalizeManager sharedInstance].userLanguage = syslanguage;
    
    return syslanguage;
}



+ (BOOL)isLanguageRTLWithLanguage:(NSString *)language{
    
    if ([language length] == 0) {
        return NO;
    }
    
    return ([NSLocale characterDirectionForLanguage:language] == NSLocaleLanguageDirectionRightToLeft);
}

+ (void)configureSysWithLanguage:(NSString *)language {
    NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
    //系统文字顺序
    NSString *sysLanguage = language;
    if([sysLanguage length] > 0) {
        [ud setObject:@[sysLanguage] forKey:SDKAppleLanguageKey];
        
        BOOL isRightToLeft = [self isLanguageRTLWithLanguage:sysLanguage];
        if (isRightToLeft) {
            if ([[[UIView alloc] init] respondsToSelector:@selector(setSemanticContentAttribute:)]) {
                if (@available(iOS 9.0, *)) {
                    [[UIView appearance] setSemanticContentAttribute:
                     UISemanticContentAttributeForceRightToLeft];
                } else {
                    // Fallback on earlier versions
                }
            }
        }else {
            if ([[[UIView alloc] init] respondsToSelector:@selector(setSemanticContentAttribute:)]) {
                if (@available(iOS 9.0, *)) {
                    [[UIView appearance] setSemanticContentAttribute:UISemanticContentAttributeForceLeftToRight];
                } else {
                    // Fallback on earlier versions
                }
            }
        }
        [ud setBool:isRightToLeft forKey:@"AppleTextDirection"];
        [ud setBool:isRightToLeft forKey:@"NSForceRightToLeftWritingDirection"];
    }
    [ud synchronize];
}

+ (BOOL)setLanguage:(NSString *)language {
    
    @synchronized (self) {
        if ([language length] == 0) {
            NSLog(@"Error:语言设置为空");
            return NO;
        }
        
        NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
        [ud setObject:language forKey:SDKAppleLanguageKey];
        
        //系统文字顺序
        [self configureSysWithLanguage:language];
        
        [SDKLocalizeManager sharedInstance].userLanguage = language;
        
        dispatch_async(dispatch_get_main_queue(), ^{
            [[NSNotificationCenter defaultCenter] postNotificationName:SDKLanguageDidChangeNotification object:language];
        });
        
        return YES;
    }
    
}

@end
