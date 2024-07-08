//
//  SDKHelper.m
//  Pods
//
//  Created by 余冰星 on 2017/6/8.
//
//
#import "SDKHelper.h"
#import "SDKDefine.h"
#import "SDKTimer.h"
#import <AdSupport/AdSupport.h>
#import <CommonCrypto/CommonDigest.h>
#import "MBProgressHUD+Extension.h"
#import <QuartzCore/QuartzCore.h>
#import <CommonCrypto/CommonCryptor.h>
#import <Social/Social.h>
#import <UIKit/UIKit.h>
#import <IvyiOSSdk/SDKCache.h>
#import <IvyiOSSdk/SDKConstants.h>
#import <IvyiOSSdk/SDKLocalize.h>
#import <IvyiOSSdk/NSData+AES256.h>
#import <IvyiOSSdk/GTMBase64.h>
#import <IvyiOSSdk/NSString+Base64.h>

NSString * const IV_STR = @"0000000000000000";
NSString * const SDK_COUNTRY_CODE = @"SDK_COUNTRY_CODE";
NSString * const SDK_KEYCHAIN = @"IVY_SDK_KEYCHAIN";
@implementation SDKHelper
{
@private
//    SKStoreProductViewController *_sc;
}
static NSString * _lang;
static NSString * _countryCode;
static MBProgressHUD * _loadingHUD;
static NSMutableDictionary<NSString *, UINib *> *nibCache;
    
//- (void)productViewControllerDidFinish:(SKStoreProductViewController *)viewController
//{
//    if(viewController && _sc) {
//        [_sc dismissViewControllerAnimated:YES completion:nil];
//    }
//}

//- (void)cacheAppStoreId:(nonnull NSString *)appStoreId
//{
//    [self cacheAppStoreId:appStoreId onGetStoreProductVC:nil];
//}

//-(void)cacheAppStoreId:(nonnull NSString *)appStoreId onGetStoreProductVC:(nullable void (^)(SKStoreProductViewController * _Nonnull sc, bool isLoaded))block
//{
//    static NSMutableDictionary<NSString *, SKStoreProductViewController *> * _scs;
//    static dispatch_once_t onceToken;
//    dispatch_once(&onceToken, ^{
//        _scs = [[NSMutableDictionary alloc] init];
//    });
//    __block SKStoreProductViewController *tmp = [_scs objectForKey:appStoreId];
//    BOOL isloaded = YES;
//    if (!tmp) {
//        tmp = [[SKStoreProductViewController alloc] init];
//        tmp.delegate = self;
//        isloaded = NO;
//        [tmp loadProductWithParameters:@{SKStoreProductParameterITunesItemIdentifier:appStoreId} completionBlock:^(BOOL r, NSError* error){
//            if (!error && tmp) {
//                [_scs setObject:tmp forKey:appStoreId];
//            }
//        }];
//    }
//    if (block) {
//        block(tmp, isloaded);
//    }
//}

//-(void)_showStoreProductVC:(SKStoreProductViewController *)sc
//{
//    @try {
//        sc.delegate = self;
//        if (![UIApplication sharedApplication].keyWindow.rootViewController.presentedViewController) {
//            UIViewController *container = [[UIApplication sharedApplication].keyWindow rootViewController];
//            [container presentViewController:sc animated:true completion:nil];
//        }
//    }@catch(NSException *e) {
//        [self productViewControllerDidFinish:sc];
//    }@finally {
//    }
//}
#pragma mark -

-(void)openAppStoreId:(nonnull NSString *)appStoreId
{
    if(appStoreId == nil || appStoreId == NULL)return;
//    Class isAllow = NSClassFromString(@"SKStoreProductViewController");
//    if (isAllow != nil && isPad && ![[UIDevice currentDevice].model isEqualToString:@"iPhone Simulator"]) {
//        [self cacheAppStoreId:appStoreId onGetStoreProductVC:^(SKStoreProductViewController * _Nonnull sc, bool isLoaded) {
//            self->_sc = sc;
//            if (!isLoaded) {
//                [SDKHelper showLoading];
//                [SDKTimer startTimer:5 interval:1.0 onComplete:^{
//                    [SDKHelper hideLoading];
//                } onUpdate:nil];
//            }
//            [self _showStoreProductVC:self->_sc];
//        }];
//    } else {
        NSString * url = [[self class] getAppstoreUrl:appStoreId];
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:url] options:@{} completionHandler:nil];
//    }
}

+ (NSString *)getAppstoreUrl:(NSString *)appstoreId
{
    return [NSString stringWithFormat:@"https://itunes.apple.com/WebObjects/MZStore.woa/wa/viewSoftware?id=%@", appstoreId];
}

- (void)sendMail:(NSString *)address subject:(NSString *)subject content:(NSString *)content isHTML:(BOOL)isHTML
{
    //判断用户是否已设置邮件账户
    if ([MFMailComposeViewController canSendMail]) {
        // 创建邮件发送界面
        MFMailComposeViewController *mailCompose = [[MFMailComposeViewController alloc] init];
        // 设置邮件代理
        [mailCompose setMailComposeDelegate:self];
        // 设置收件人
        if ([address containsString:@","]) {
            [mailCompose setToRecipients:[address componentsSeparatedByString:@","]];
        } else {
            [mailCompose setToRecipients:@[address]];
        }
        // 设置抄送人
//        [mailCompose setCcRecipients:@[@"1622849369@qq.com"]];
        // 设置密送人
//        [mailCompose setBccRecipients:@[@"15690725786@163.com"]];
        // 设置邮件主题
        [mailCompose setSubject:subject];
        //设置邮件的正文内容
        // 是否为HTML格式
        [mailCompose setMessageBody:content isHTML:isHTML];
        // 如使用HTML格式，则为以下代码
        // [mailCompose setMessageBody:@"<html><body><p>Hello</p><p>World！</p></body></html>" isHTML:YES];
        //添加附件
//        UIImage *image = [UIImage imageNamed:@"qq"];
//        NSData *imageData = UIImagePNGRepresentation(image);
//        [mailCompose addAttachmentData:imageData mimeType:@"" fileName:@"qq.png"];
//        NSString *file = [[NSBundle mainBundle] pathForResource:@"EmptyPDF" ofType:@"pdf"];
//        NSData *pdf = [NSData dataWithContentsOfFile:file];
//        [mailCompose addAttachmentData:pdf mimeType:@"" fileName:@"EmptyPDF.pdf"];
        // 弹出邮件发送视图
        UIViewController *_curVC = [[UIApplication sharedApplication].keyWindow rootViewController];
        [_curVC presentViewController:mailCompose animated:YES completion:nil];
    }else{
        //给出提示,设备未开启邮件服务
        //创建可变的地址字符串对象
        NSMutableString *mailUrl = [[NSMutableString alloc] init];
        //添加收件人,如有多个收件人，可以使用componentsJoinedByString方法连接，连接符为","
        NSString *recipients = address;
        [mailUrl appendFormat:@"mailto:%@?", recipients];
        //添加抄送人
//        NSString *ccRecipients = @"1622849369@qq.com";
//        [mailUrl appendFormat:@"&cc=%@", ccRecipients];
        //添加密送人
//        NSString *bccRecipients = @"15690725786@163.com";
//        [mailUrl appendFormat:@"&bcc=%@", bccRecipients];
        //添加邮件主题
        [mailUrl appendFormat:@"&subject=%@", subject];
        //添加邮件内容
        [mailUrl appendFormat:@"&body=%@", content];
        //跳转到系统邮件App发送邮件
        NSString *emailPath = [mailUrl stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLFragmentAllowedCharacterSet]];
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:emailPath] options:@{} completionHandler:nil];
    }
}

#pragma mark - MFMailComposeViewControllerDelegate的代理方法：
-(void)mailComposeController:(MFMailComposeViewController *)controller didFinishWithResult:(MFMailComposeResult)result error:(NSError *)error{
    switch (result) {
        case MFMailComposeResultCancelled:
            DLog(@"Mail send canceled: 用户取消编辑");
            break;
        case MFMailComposeResultSaved:
            DLog(@"Mail saved: 用户保存邮件");
            break;
        case MFMailComposeResultSent:
            DLog(@"Mail sent: 用户点击发送");
            break;
        case MFMailComposeResultFailed:
            DLog(@"Mail send errored: %@ : 用户尝试保存或发送邮件失败", [error localizedDescription]);
            break;
    }
    // 关闭邮件发送视图
    UIViewController *_curVC = [[UIApplication sharedApplication].keyWindow rootViewController];
    [_curVC dismissViewControllerAnimated:YES completion:nil];
}

+(void)showLoading
{
    dispatch_async(dispatch_get_main_queue(), ^{
        if(_loadingHUD && [_loadingHUD superview])
            [_loadingHUD hideAnimated:YES];
        _loadingHUD = [MBProgressHUD showHUDAddedTo:KeyWindow animated:YES];
    });
}

+(void)hideLoading
{
    dispatch_async(dispatch_get_main_queue(), ^{
        if(_loadingHUD && [_loadingHUD superview]) {
            [_loadingHUD hideAnimated:YES];
        }
    });
}

+(void)showMessage:(NSString *)msg
{
    [self showMessage:msg withSeconds:1.5f onComplete:nil];
}

+(void)showMessage:(NSString *)msg withSeconds:(double)seconds onComplete:(dispatch_block_t)block
{
    if(seconds <= 0)return;
    if(msg && msg.length > 0) {
        MBProgressHUD *hud = [MBProgressHUD showMessage:msg];
        [hud hideAnimated:YES afterDelay:seconds];
    }
}

+(void)showProgressCircle:(double)seconds withText:(nullable NSString *)text onComplete:(dispatch_block_t)block
{
    if(seconds <= 0)return;
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:KeyWindow animated:YES];
    hud.mode = MBProgressHUDModeAnnularDeterminate;
    if(text && text.length > 0)
        hud.label.text = text;
    unsigned int usecond = seconds * 10000;
    dispatch_async(dispatch_get_global_queue(QOS_CLASS_USER_INITIATED, 0), ^{
        [[self class] _doSomeWorkWithProgress:usecond];
        dispatch_async(dispatch_get_main_queue(), ^{
            [hud hideAnimated:YES];
            if(block) {
                block();
            }
        });
    });
}

+(void)showProgressBar:(double)seconds withText:(nullable NSString *)text onComplete:(dispatch_block_t)block
{
    if(seconds <= 0)return;
    MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:KeyWindow animated:YES];
    hud.mode = MBProgressHUDModeDeterminateHorizontalBar;
    if(text && text.length > 0)
        hud.label.text = text;
    unsigned int usecond = seconds * 10000;
    dispatch_async(dispatch_get_global_queue(QOS_CLASS_USER_INITIATED, 0), ^{
        [[self class] _doSomeWorkWithProgress:usecond];
        dispatch_async(dispatch_get_main_queue(), ^{
            [hud hideAnimated:YES];
            if(block) {
                block();
            }
        });
    });
}

+ (void)_doSomeWorkWithProgress:(unsigned int)useconds {
    float progress = 0.0f;
    while (progress < 1.0f) {
        progress += 0.01f;
        dispatch_async(dispatch_get_main_queue(), ^{
            [MBProgressHUD HUDForView:KeyWindow].progress = progress;
        });
        usleep(useconds);
    }
}

+(void)toast:(NSString *)msg isLongTime:(BOOL)longTime
{
    if(msg) {
        MBProgressHUD *hud = [MBProgressHUD showHUDAddedTo:KeyWindow animated:YES];
        hud.mode = MBProgressHUDModeText;
        hud.label.numberOfLines = msg.length / 30 + 1;
        hud.label.text = msg;
        hud.label.lineBreakMode = NSLineBreakByWordWrapping;
        hud.offset = CGPointMake(0.f, MBProgressMaxOffset);
        [hud hideAnimated:YES afterDelay:(longTime ? 3.f : 1.5f)];
    }
}

+ (nonnull NSString *)md5:(nonnull NSString *)str
{
    NSString *md5_result = @"";
    const char *cStr = [str UTF8String];
    unsigned char result[CC_MD5_DIGEST_LENGTH];
    CC_MD5( cStr, (int)strlen(cStr), result ); // This is the md5 call
    
    md5_result = [NSString stringWithFormat:
                  @"%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x",
                  result[0], result[1], result[2], result[3],
                  result[4], result[5], result[6], result[7],
                  result[8], result[9], result[10], result[11],
                  result[12], result[13], result[14], result[15]
                  ];
    return md5_result;
}

+(NSDate *)getInstallTime
{
    NSString *path = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];

    NSDictionary *FileInfo = [[NSFileManager defaultManager] attributesOfItemAtPath:path error:nil];//获取文件信息
    NSDate *firstDate = [FileInfo objectForKey:NSFileCreationDate];//获取文件创建
    
    DLog(@"install app time = %@", firstDate);
    return firstDate;
//    return firstDate.timeIntervalSince1970;
}

+(NSString *)getLangcode
{
    if (!_lang) {
        _lang = [[NSLocale preferredLanguages] objectAtIndex:0];
        if ([_lang containsString:@"_"]) {
            NSArray<NSString *> *tmp = [_lang componentsSeparatedByString:@"_"];
            _lang = tmp[0];
        } else if([_lang containsString:@"-"]){
            NSArray<NSString *> *tmp = [_lang componentsSeparatedByString:@"-"];
            int count = (int)tmp.count;
            if(count < 2) {
                _lang = tmp[0];
            } else {
                _lang = tmp[0];
                for (int i=1; i<count-1; i++) {
                    _lang = [NSString stringWithFormat:@"%@-%@", _lang, tmp[i]];
                }
            }
        }
        _lang = _lang ? _lang : @"en";
    }
    return _lang;
}

+(void)setCountryCode:(nullable NSString *)code
{
    if (code) {
        _countryCode = code.uppercaseString;
        [[SDKCache cache] setObject:code forKey:SDK_COUNTRY_CODE];
    }
}

+(NSString *)getCountryCode
{
    if(!_countryCode) {
        _countryCode = (NSString *)[[SDKCache cache] objectForKey:SDK_COUNTRY_CODE];
        if (!_countryCode) {
            _countryCode = [[NSLocale currentLocale] objectForKey:NSLocaleCountryCode];
        }
    }
    return _countryCode.uppercaseString;
}

+(BOOL)isAppInstalled:(nonnull NSString *)appid
{
    if(appid == nil || appid == NULL || appid.length < 1)return NO;
    NSString *scheme = [NSString stringWithFormat:@"ivyapp.%@://", appid];
    NSURL * url = [NSURL URLWithString:scheme];
    BOOL result = [[UIApplication sharedApplication] canOpenURL:url];
    return result;
}

//获取当前的时间戳（秒）
+(long)currentTime
{
    NSDate *currentDate = [NSDate date];//获取当前时间，日期
    long nowTime = currentDate.timeIntervalSince1970;
    return nowTime;
}

//注册通知
+ (void)registerNotif:(NSString *)name observer:(nonnull id)observer selector:(SEL)aSelector
{
    [self removeNotif:name observer:observer];
    [[NSNotificationCenter defaultCenter] addObserver:observer selector:aSelector name:name object:nil];
}

//取消通知
+ (void)removeNotif:(NSString *)name observer:(nonnull id)observer
{
    [[NSNotificationCenter defaultCenter] removeObserver:observer name:name object:nil];
}
//发送通知
+ (void)sendNotif:(NSString *)name object:(id)anObject userInfo:(NSDictionary *)UserInfo
{
    [[NSNotificationCenter defaultCenter] postNotification:[NSNotification notificationWithName:name object:anObject userInfo:UserInfo]];
}

//发送通知
+ (void)sendNotif:(NSString *)name
{
    [[NSNotificationCenter defaultCenter] postNotification:[NSNotification notificationWithName:name object:nil userInfo:nil]];
}

+ (BOOL)isNullObject:(nullable id)obj
{
    return obj == nil || obj == NULL;
}

+ (BOOL)isEmptyString:(nullable NSString *)string
{
    return string == nil || string == NULL || string.length < 1;
}

+(nullable NSMutableDictionary *)toDictionary:(nullable id)data
{
    NSMutableDictionary *dict = nil;
    if(data) {
        if([data isKindOfClass:[NSDictionary class]]) {
            dict = [NSMutableDictionary dictionaryWithDictionary:data];
        } else if ([data isKindOfClass:[NSArray class]]) {
            dict = [[NSMutableDictionary alloc] init];
            NSArray *tmpArr = data;
            for (int i=0; i<tmpArr.count; i++) {
                [dict setObject:[tmpArr objectAtIndex:i] forKey:[@(i) stringValue]];
            }
        }
    }
    return dict;
}

+(void)doBackground:(dispatch_block_t)block inMainProcess:(BOOL)inMain
{
    __block UIBackgroundTaskIdentifier backgroundProcess = [[UIApplication sharedApplication] beginBackgroundTaskWithExpirationHandler:^{
        //End the Background Process
        [[UIApplication sharedApplication] endBackgroundTask:backgroundProcess];
        backgroundProcess = UIBackgroundTaskInvalid;
    }];
    
    dispatch_queue_t backgroundThread = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0ul);
    dispatch_async(backgroundThread, ^{
        if(inMain) {
            dispatch_async(dispatch_get_main_queue(), block);
        } else {
            block();
        }
    });
    [[UIApplication sharedApplication] endBackgroundTask:backgroundProcess];
    backgroundProcess = UIBackgroundTaskInvalid;
}

+ (NSData *)dataWithScreenshotInPNGFormat
{
    CGSize imageSize = CGSizeZero;
    UIInterfaceOrientation orientation = [UIApplication sharedApplication].statusBarOrientation;
    if (UIInterfaceOrientationIsPortrait(orientation)) imageSize = [UIScreen mainScreen].bounds.size;
    else imageSize = CGSizeMake([UIScreen mainScreen].bounds.size.height, [UIScreen mainScreen].bounds.size.width);
    
    UIGraphicsBeginImageContextWithOptions(imageSize, NO, 0);
    CGContextRef context = UIGraphicsGetCurrentContext();
    for (UIWindow *window in [[UIApplication sharedApplication] windows]) {
        CGContextSaveGState(context);
        CGContextTranslateCTM(context, window.center.x, window.center.y);
        CGContextConcatCTM(context, window.transform);
        CGContextTranslateCTM(context, -window.bounds.size.width * window.layer.anchorPoint.x, -window.bounds.size.height * window.layer.anchorPoint.y);
        if (orientation == UIInterfaceOrientationLandscapeLeft) {
            CGContextRotateCTM(context, M_PI_2);
            CGContextTranslateCTM(context, 0, -imageSize.width);
        } else if (orientation == UIInterfaceOrientationLandscapeRight) {
            CGContextRotateCTM(context, -M_PI_2);
            CGContextTranslateCTM(context, -imageSize.height, 0);
        } else if (orientation == UIInterfaceOrientationPortraitUpsideDown) {
            CGContextRotateCTM(context, M_PI);
            CGContextTranslateCTM(context, -imageSize.width, -imageSize.height);
        }
        if ([window respondsToSelector:@selector(drawViewHierarchyInRect:afterScreenUpdates:)]) {
            [window drawViewHierarchyInRect:window.bounds afterScreenUpdates:YES];
        } else {
            [window.layer renderInContext:context];
        }
        CGContextRestoreGState(context);
    }
    UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    
    return UIImagePNGRepresentation(image);
}

+ (UIImage *)imageWithScreenshot
{
    NSData *imageData = [self dataWithScreenshotInPNGFormat];
    return [UIImage imageWithData:imageData];
}

+ (void)shareScreenshot:(nullable SDKShareCompleteHandler)completeHandler
{
    [self shareScreenshot:nil url:nil completeHandler:completeHandler];
}

+ (void)shareScreenshot:(NSString *)title completeHandler:(nullable SDKShareCompleteHandler)completeHandler
{
    [self shareScreenshot:title url:nil completeHandler:completeHandler];
}

+ (void)shareScreenshot:(NSString *)title url:(NSURL *)url completeHandler:(nullable SDKShareCompleteHandler)completeHandler
{
    UIImage *image = [self imageWithScreenshot];
    [self share:title image:image url:url completeHandler:completeHandler];
}

+ (void)share:(NSString *)title image:(UIImage *)image url:(NSURL *)url completeHandler:(SDKShareCompleteHandler)completeHandler
{
    if ([UIActivityViewController class] == nil) {
        DLog(@"UIActivityViewController is not supported on iOS versions less than 6.0");
        return;
    }
    
    NSMutableArray *data = [[NSMutableArray alloc] init];
    if(title) {
        [data addObject:title];
    }
    if(image) {
        [data addObject:image];
    }
    if(url && url.scheme.length > 0) {
        [data addObject:url];
    }
    if([data count] > 0) {
//        UIViewController *topVC = [self topViewController];
        UIViewController *topVC = [UIApplication sharedApplication].keyWindow.rootViewController;
        UIActivityViewController *controller = [[UIActivityViewController alloc] initWithActivityItems:data applicationActivities:nil];
        controller.modalTransitionStyle = UIModalTransitionStyleCoverVertical;
        controller.modalPresentationStyle = UIModalPresentationPageSheet;
//        controller.excludedActivityTypes = @[UIActivityTypeMessage,
//                                             UIActivityTypePrint,
//                                             UIActivityTypeSaveToCameraRoll,
//                                             UIActivityTypeAddToReadingList,
//                                             UIActivityTypePostToFlickr,
//                                             UIActivityTypePostToVimeo,
//                                             UIActivityTypeAirDrop];
        __block UILabel *label = nil;
        if (isPad) {
            label = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 1, 1)];
            [topVC.view addSubview:label];
            controller.popoverPresentationController.sourceView = label;
        }
        [controller setCompletionWithItemsHandler:^(NSString * __nullable activityType, BOOL completed, NSArray * __nullable returnedItems, NSError * __nullable activityError){
            __strong UILabel *_label = label;
            if (_label) {
                [_label removeFromSuperview];
                _label = nil;
            }
            // 显示选中的分享类型
            DLog(@"当前选择分享平台 %@",activityType);
            if (completed) {
                DLog(@"[sdklog] 分享成功");
            }else {
                DLog(@"[sdklog] 分享失败");
            }
            if(completeHandler) {
                completeHandler(completed, activityError);
            }
        }];
        [topVC presentViewController:controller animated:YES completion:nil];
    }
}

+ (void)saveBase64ImageToCameraRoll:(nonnull NSString *)base64Image
{
    @try {
        NSData *decodeData = [[NSData alloc]initWithBase64EncodedString:base64Image options:(NSDataBase64DecodingIgnoreUnknownCharacters)];
        if (decodeData) {
            UIImage *image = [UIImage imageWithData:decodeData];
            if (image) {
                UIImageWriteToSavedPhotosAlbum(image, nil, nil, nil);
            }
        }
    } @catch (NSException *exception) {
        DLog(@"%@", exception.description);
    } @finally {
    }
}

+ (void)takeScreenshotToCameraRoll
{
    UIImage *image = [self imageWithScreenshot];
    UIImageWriteToSavedPhotosAlbum(image, nil, nil, nil);
}

+ (nonnull NSString *)takeScreenshotToDocumentsDirectory
{
    NSString *documentsPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) lastObject];
    return [self takeScreenshotToDirectoryAtPath:documentsPath];
}

+ (nonnull NSString *)takeScreenshotToDirectoryAtPath:(NSString *)path
{
    static NSDateFormatter *dateFormatter = nil;
    if (dateFormatter == nil) {
        dateFormatter = [[NSDateFormatter alloc] init];
        [dateFormatter setDateFormat:@"Y-MM-d HH-mm-ss-SSS"];
    }
    
    NSData *imageData = [self dataWithScreenshotInPNGFormat];
    NSString *imageFileName = [NSString stringWithFormat:@"%@.png", [dateFormatter stringFromDate:[NSDate date]]];
    NSString *imagePath = [path stringByAppendingPathComponent:imageFileName];
    [[NSFileManager defaultManager] createFileAtPath:imagePath contents:imageData attributes:nil];
    return imagePath;
}

+ (UIViewController *)topViewController
{
    // Returns the currently visible ViewController, including active modal ViewControllers.
    NSAssert([UIApplication sharedApplication].keyWindow, @"Application should have a key window");
    NSAssert([UIApplication sharedApplication].keyWindow.rootViewController, @"Window should have a root view controller");
    return [self topViewControllerWithRootViewController:[UIApplication sharedApplication].keyWindow.rootViewController];
}

+ (UIViewController *)topViewControllerWithRootViewController:(UIViewController *)rootViewController
{
    // Check type of rootViewController and return current or topmost child ViewController.
    // (Implementation by kleo, see http://stackoverflow.com/a/17578272/170132.)
    
    if ([rootViewController isKindOfClass:[UITabBarController class]]) {
        UITabBarController *tabBarController = (UITabBarController *)rootViewController;
        return [self topViewControllerWithRootViewController:tabBarController.selectedViewController];
    } else if ([rootViewController isKindOfClass:[UINavigationController class]]) {
        UINavigationController *navigationController = (UINavigationController *)rootViewController;
        return [self topViewControllerWithRootViewController:navigationController.visibleViewController];
    } else if (rootViewController.presentedViewController) {
        UIViewController *presentedViewController = rootViewController.presentedViewController;
        return [self topViewControllerWithRootViewController:presentedViewController];
    }
    return rootViewController;
}

+(UINib *)getNibFromCache:(NSString *)nibName bundleName:(NSString *)bundleName
{
    if (!nibCache) {
        nibCache = [[NSMutableDictionary alloc] init];
    }
    NSString *key = [NSString stringWithFormat:@"%@_%@", bundleName, nibName];
    id tmp = [nibCache objectForKey:key];
    if (tmp) {
        return tmp;
    } else {
        NSBundle *bundle = nil;
        if([bundleName isEqualToString:@"mainBundle"]) {
            bundle = [NSBundle mainBundle];
        } else {
            bundle = [NSBundle bundleForClass:self.class];
            bundle = [NSBundle bundleWithURL:[bundle URLForResource:bundleName withExtension:@"bundle"]];
        }
        UINib *nib = [UINib nibWithNibName:nibName bundle:bundle];
        if (nib) {
            [nibCache setObject:nib forKey:key];
        }
        return nib;
    }
}

+(void)showAlertDialog:(nonnull NSString *)title desc:(nonnull NSString *)desc okBtn:(nonnull NSString *)okBtn cancelBtn:(nullable NSString *)cancelBtn onOk:(nullable dispatch_block_t)onOk onCancel:(nullable dispatch_block_t)onCancel
{
    dispatch_async(dispatch_get_main_queue(), ^{
        UIAlertController* alert = [UIAlertController alertControllerWithTitle:title
                                                                       message:desc
                                                                preferredStyle:UIAlertControllerStyleAlert];
        
        UIAlertAction* defaultAction = [UIAlertAction
                                        actionWithTitle:okBtn
                                        style:UIAlertActionStyleDefault
                                        handler:^(UIAlertAction * action) {
                                            //响应事件
                                            if (onOk) {
                                                onOk();
                                            }
                                        }];
        [alert addAction:defaultAction];
        
        if (cancelBtn) {
            UIAlertAction* cancelAction = [UIAlertAction actionWithTitle:cancelBtn
                                                                   style:UIAlertActionStyleDefault
                                                                 handler:^(UIAlertAction * action) {
                                                                     //响应事件
                                                                     if (onCancel) {
                                                                         onCancel();
                                                                     }
                                                                 }];
            
            [alert addAction:cancelAction];
        }
        [[UIApplication sharedApplication].keyWindow.rootViewController presentViewController:alert animated:YES completion:nil];
    });
}

+ (UIColor *)intToColor:(int)rgbValue
{
    return [UIColor colorWithRed:((float)((rgbValue & 0xFF0000) >> 16))/255.0 green:((float)((rgbValue & 0xFF00) >> 8))/255.0 blue:((float)(rgbValue & 0xFF))/255.0 alpha:1.0];
}

+ (UIColor *)hexToColor:(NSString *)str
{
    unsigned int rgbValue;
    NSScanner* scanner = [NSScanner scannerWithString:str];
    [scanner scanHexInt:&rgbValue];
    return [SDKHelper intToColor:rgbValue];
}

+(BOOL)isAppInBackground
{
    return [UIApplication sharedApplication].applicationState == UIApplicationStateBackground;
}

/**
 获取目录下文件总大小，不包含子目录
 */
+ (float)getFolderSize:(NSString*)filePath sizeWithMb:(BOOL)sizeWithMb
{
    if (filePath) {
        NSFileManager *manager = [NSFileManager defaultManager];
        if ([manager fileExistsAtPath:filePath]) {
            NSEnumerator *childFilesEnumerator = [[manager subpathsAtPath:filePath] objectEnumerator];
            NSString* fileName;
            long long folderSize = 0;
            
            while ((fileName = [childFilesEnumerator nextObject]) != nil){
                NSString* fileAbsolutePath = [filePath stringByAppendingPathComponent:fileName];
                BOOL isDir;
                [manager fileExistsAtPath:fileAbsolutePath isDirectory:&isDir];
                if (!isDir) {
                    folderSize += [[manager attributesOfItemAtPath:fileAbsolutePath error:nil] fileSize];
                }
            }
            return sizeWithMb ? folderSize/1048576.0 : folderSize;//kb
        }
    }
    return 0;
}

+ (NSArray<NSString *> *)autoCleanFolder:(NSString *)path limitsSize:(CGFloat)size sizeWithMb:(BOOL)sizeWithMb
{
    if (!path) {
        return nil;
    }
    CGFloat delta = [[self class] getFolderSize:path sizeWithMb:sizeWithMb] - size;
    delta = sizeWithMb ? delta * 1048576.0: delta;
    if(delta > 0) {
        NSFileManager *fileMgr = [NSFileManager defaultManager];
        NSArray *paths = [fileMgr subpathsAtPath:path];//取得文件列表
        NSArray *sortedPaths = [paths sortedArrayUsingComparator:^(NSString * firstPath, NSString* secondPath) {//
            NSString *firstUrl = [path stringByAppendingPathComponent:firstPath];//获取前一个文件完整路径
            NSString *secondUrl = [path stringByAppendingPathComponent:secondPath];//获取后一个文件完整路径
            NSDictionary *firstFileInfo = [[NSFileManager defaultManager] attributesOfItemAtPath:firstUrl error:nil];//获取前一个文件信息
            NSDictionary *secondFileInfo = [[NSFileManager defaultManager] attributesOfItemAtPath:secondUrl error:nil];//获取后一个文件信息
            id firstData = [firstFileInfo objectForKey:NSFileModificationDate];//获取前一个文件修改时间
            id secondData = [secondFileInfo objectForKey:NSFileModificationDate];//获取后一个文件修改时间
            return [firstData compare:secondData];//升序
            // return [secondData compare:firstData];//降序
        }];
        long long calcSize = 0;
        NSMutableArray<NSString *> * result = [[NSMutableArray alloc] init];
        for (int i=0; i<sortedPaths.count; i++) {
            NSString *filePath = [path stringByAppendingPathComponent:sortedPaths[i]];
            BOOL isDir;
            [fileMgr fileExistsAtPath:filePath isDirectory:&isDir];
            if (!isDir) {
                calcSize += [[fileMgr attributesOfItemAtPath:filePath error:nil] fileSize];
            }
            if (calcSize > delta) {
                break;
            } else {
                [fileMgr removeItemAtPath:filePath error:nil];
                [result addObject:filePath];
            }
        }
        return result;
    }
    return nil;
}

+ (UIImage *)getImageFromeBundle:(NSString *)imageName bundleName:(NSString *)bundleName
{
    NSBundle *bundle = nil;
    if (bundleName) {
        bundle = [NSBundle bundleForClass:self.class];
        bundle = [NSBundle bundleWithURL:[bundle URLForResource:bundleName withExtension:@"bundle"]];
    }
    bundle = bundle ? bundle : [NSBundle mainBundle];
    UIImage *img = [UIImage imageWithContentsOfFile:[bundle pathForResource:imageName ofType:@"png"]];
//    img = [img stretchableImageWithLeftCapWidth:2 topCapHeight:img.size.height];
    return img;
}

+(nonnull NSData *)decryptDataWithAES:(nonnull NSString *)base64String key:(nonnull NSString *)key
{
    NSData *data = [GTMBase64 webSafeDecodeString:base64String];
    NSData *decode = [data AES256DecryptWithKey:key Iv:IV_STR];
    return decode;
}

+(nonnull NSString *)decryptWithAES:(nonnull NSString *)base64String key:(nonnull NSString *)key
{
    NSData *decrypt = [self.class decryptDataWithAES:base64String key:key];
    NSString *str = decrypt ? [[NSString alloc] initWithData:decrypt encoding:NSUTF8StringEncoding] : base64String;
    return str;
}

+(nonnull NSString *)encryptWithAES:(nonnull NSString *)str key:(nonnull NSString *)key
{
    NSString *result = @"";
    if (str && key) {
        NSData *encryptedData = [[str dataUsingEncoding:NSUTF8StringEncoding] AES256EncryptWithKey:key Iv:IV_STR];
        result = [GTMBase64 stringByWebSafeEncodingData:encryptedData padded:NO];
    }
    return result;
}

+(nonnull NSString *)encryptWithBase64:(nonnull NSData *)data
{
    long len = data.length;
    char buffer[len + 5];
    Byte seed = (Byte)arc4random() % 63 + 1;
    buffer[0] = seed;
    buffer[1] =  (Byte) ((len>>24) & 0xFF);
    buffer[2] =  (Byte) ((len>>16) & 0xFF);
    buffer[3] =  (Byte) ((len>>8) & 0xFF);
    buffer[4] =  (Byte) (len & 0xFF);
    
    Byte tmpData[len];
    [data getBytes:tmpData range:NSMakeRange(0, data.length)];
    
    for (int i = 0; i < len; i++) {
        int tmp = tmpData[i];
        tmp = (tmp + seed) < 256 ? tmp + seed : tmp;
        buffer[i+5] = (Byte)tmp;
    }
    
    NSString *result = [NSString base64StringFromData:[NSData dataWithBytes:buffer length:len+5] length:len+5];
    return result;
}

+(nonnull NSData *)decryptWithBase64:(nonnull NSString *)base64String
{
    NSData *data = [[NSData alloc] initWithBase64EncodedString:base64String options:0];
    return [[self class] decrypt:data];
}

+(nonnull NSData *)decrypt:(nonnull NSData *)data
{
    Byte byte[5] = {};
    [data getBytes:byte length:5];
    int seed = (int) byte[0];
    int len = (int) (((byte[1] & 0xFF)<<24)
                   | ((byte[2] & 0xFF)<<16)
                   | ((byte[3] & 0xFF)<<8)
                   | (byte[4] & 0xFF));
    Byte tmpData[data.length - 5];
    [data getBytes:tmpData range:NSMakeRange(5, data.length - 5)];
    
    char buffer[len];
    for (int i = 0; i < len; i++) {
        int tmp = tmpData[i];
        int t = tmp + seed;
        tmp = t > 255 && tmp >= 128 ? tmp : tmp - seed;
        buffer[i] = tmp;
    }
    return [NSData dataWithBytes:buffer length:len];
}
    
+ (NSString*)getAppName
{
    NSBundle *bundle = [NSBundle bundleForClass:[self class]];
    NSString *appName = [bundle objectForInfoDictionaryKey:@"CFBundleDisplayName"];
    if (!appName) {
        appName = [bundle objectForInfoDictionaryKey:@"CFBundleName"];
    }
    return appName;
}
    
+ (NSString *)getIDFAString
{
    NSString *adId = [[[ASIdentifierManager sharedManager] advertisingIdentifier] UUIDString];
    return adId;
}

+ (UICKeyChainStore *)getKeychainStore
{
    UICKeyChainStore *keychain = [UICKeyChainStore keyChainStoreWithService:SDK_KEYCHAIN];
    return keychain;
}

+(void)saveToKeychain:(NSString *)data forKey:(NSString *)key
{
    [[SDKHelper getKeychainStore] setString:data forKey:key];
}
//读
+ (NSString *)getFromKeychain:(NSString *)key
{
    NSError *error;
    NSString *token = [[SDKHelper getKeychainStore] stringForKey:key error:&error];
    if (error) {
        DLog(@"[err] [getFromKeychain] : %@", error.localizedDescription);
    }
    return token;
}
//删除
+ (void)deleteInKeychain:(NSString *)key
{
    [[SDKHelper getKeychainStore] removeItemForKey:key];
}
    
+ (NSString *)getIDFVString
{
    NSString *idfv = [[[UIDevice currentDevice] identifierForVendor] UUIDString];
    return idfv;
}

+ (NSString *)UUIDString
{
    SDKCache *cache = [SDKCache cache];
    NSString *uuid = (NSString *)[cache objectForKey:SDK_UUID];
    if (uuid) {
        return uuid;
    }
    uuid = [NSUUID UUID].UUIDString;
    [cache setObject:uuid forKey:SDK_UUID];
    return uuid;
}

+ (nonnull NSString *)localizedStr:(nonnull NSString *)key defaultString:(nonnull NSString *)defaultString
{
    return SDKLocalizedStr(key, defaultString, @"IvyiOSSdk-Core");
}

+(void)copyToClipboard:(nonnull NSString *)copyStr
{
    UIPasteboard *pasteboard = [UIPasteboard generalPasteboard];
    [pasteboard setString:copyStr];
}

+ (SDKHelper *)sharedHelper
{
    static SDKHelper *_instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[self alloc] init];
    });
    return _instance;
}
@end
