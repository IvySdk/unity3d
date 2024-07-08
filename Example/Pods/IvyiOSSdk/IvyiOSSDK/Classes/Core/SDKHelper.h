//
//  SDKHelper.h
//  Pods
//
//  Created by 余冰星 on 2017/6/8.
//
//
#import <Foundation/Foundation.h>
#import <StoreKit/StoreKit.h>
#import <MessageUI/MessageUI.h>
#import "UICKeyChainStore.h"
#import "MBProgressHUD.h"
typedef void (^SDKShareCompleteHandler)(BOOL completed, NSError * __nullable activityError);

@interface SDKHelper : NSObject<MFMailComposeViewControllerDelegate>
//- (void)cacheAppStoreId:(nonnull NSString *)appStoreId;
- (void)openAppStoreId:(nonnull NSString *)appStoreId;
- (void)sendMail:(nonnull NSString *)address subject:(nonnull NSString *)subject content:(nonnull NSString *)content isHTML:(BOOL)isHTML;
+ (nonnull NSDate *)getInstallTime;
+ (nonnull NSString *)getLangcode;
+ (nonnull NSString *)getCountryCode;
+ (void)setCountryCode:(nullable NSString *)code;
+ (void)showLoading;
+ (void)hideLoading;
+ (void)showMessage:(nullable NSString *)msg;
+ (void)showMessage:(nullable NSString *)msg withSeconds:(double)seconds onComplete:(nullable dispatch_block_t)block;
+ (void)showProgressCircle:(double)seconds withText:(nullable NSString *)text onComplete:(nullable dispatch_block_t)block;
+ (void)showProgressBar:(double)seconds withText:(nullable NSString *)text onComplete:(nullable dispatch_block_t)block;
+ (nonnull NSString *)md5:(nonnull NSString *)str;
+ (BOOL)isAppInstalled:(nonnull NSString *)appid;
// 获取当前的时间戳（秒
+ (long)currentTime;
+ (void)registerNotif:(nonnull NSString *)name observer:(nonnull id)observer selector:(nonnull SEL)aSelector;
+ (void)removeNotif:(nonnull NSString *)name observer:(nonnull id)observer;
+ (void)sendNotif:(nonnull NSString *)name object:(nullable id)anObject userInfo:(nullable NSDictionary *)UserInfo;
+ (void)sendNotif:(nonnull NSString *)name;
+ (BOOL)isNullObject:(nullable id)obj;
+ (BOOL)isEmptyString:(nullable NSString *)string;
+ (void)toast:(nullable NSString *)msg isLongTime:(BOOL)longTime;
+ (nullable NSMutableDictionary *)toDictionary:(nullable id)data;
+ (void)doBackground:(nonnull dispatch_block_t)block inMainProcess:(BOOL)inMain;
+ (void)shareScreenshot:(nullable SDKShareCompleteHandler)completeHandler;
+ (void)shareScreenshot:(nullable NSString *)title completeHandler:(nullable SDKShareCompleteHandler)completeHandler;
+ (void)shareScreenshot:(nullable NSString *)title url:(nullable NSURL *)url completeHandler:(nullable SDKShareCompleteHandler)completeHandler;
+ (void)share:(nullable NSString *)title image:(nullable UIImage *)image url:(nullable NSURL *)url completeHandler:(nullable SDKShareCompleteHandler)completeHandler;
+ (nonnull UIImage *)imageWithScreenshot;
+ (void)saveBase64ImageToCameraRoll:(nonnull NSString *)base64Image;
+ (void)takeScreenshotToCameraRoll;
+ (nonnull NSString *)takeScreenshotToDocumentsDirectory;
+ (nonnull NSString *)takeScreenshotToDirectoryAtPath:(nonnull NSString *)path;
+ (nullable UINib *)getNibFromCache:(nonnull NSString *)nibName bundleName:(nonnull NSString *)bundleName;
+ (void)showAlertDialog:(nonnull NSString *)title desc:(nonnull NSString *)desc okBtn:(nonnull NSString *)okBtn cancelBtn:(nullable NSString *)cancelBtn onOk:(nullable dispatch_block_t)onOk onCancel:(nullable dispatch_block_t)onCancel;
+ (nonnull UIColor *)intToColor:(int)rgbValue;
+ (nonnull UIColor *)hexToColor:(nonnull NSString *)rgbValue;
+ (BOOL)isAppInBackground;
+ (float)getFolderSize:(NSString *_Nullable)filePath sizeWithMb:(BOOL)sizeWithMb;
+ (nullable NSArray<NSString *> *)autoCleanFolder:(NSString *_Nullable)path limitsSize:(CGFloat)size sizeWithMb:(BOOL)sizeWithMb;
+ (nullable UIImage *)getImageFromeBundle:(NSString *_Nonnull)imageName bundleName:(NSString *_Nullable)bundleName;
+(nonnull NSData *)decryptDataWithAES:(nonnull NSString *)base64String key:(nonnull NSString *)key;
+(nonnull NSString *)decryptWithAES:(nonnull NSString *)base64String key:(nonnull NSString *)key;
+(nonnull NSString *)encryptWithAES:(nonnull NSString *)str key:(nonnull NSString *)key;
+(nonnull NSString *)encryptWithBase64:(nonnull NSData *)data;
+(nonnull NSData *)decryptWithBase64:(nonnull NSString *)base64String;
+(nonnull NSData *)decrypt:(nonnull NSData *)data;
+ (nonnull NSString*)getAppName;
+ (nullable NSString *)getIDFAString;
+ (nullable NSString *)getIDFVString;
+ (nonnull NSString *)UUIDString;
+ (nonnull NSString *)getAppstoreUrl:(nonnull NSString *)appstoreId;
+ (nonnull NSString *)localizedStr:(nonnull NSString *)key defaultString:(nonnull NSString *)defaultString;
+(void)copyToClipboard:(nonnull NSString *)copyStr;
+ (nonnull SDKHelper *)sharedHelper;
+ (nonnull UICKeyChainStore *)getKeychainStore;
+(void)saveToKeychain:(nonnull NSString *)data forKey:(nonnull NSString *)key;
+ (nonnull NSString *)getFromKeychain:(nonnull NSString *)key;
+ (void)deleteInKeychain:(nonnull NSString *)key;
@end
