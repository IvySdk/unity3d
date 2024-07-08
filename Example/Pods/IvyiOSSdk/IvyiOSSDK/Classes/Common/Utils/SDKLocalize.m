//
//  SDKLocalizedString.m
//  Pods
//
//  Created by LiuShulong on 23/04/2017.
//
//

#import "SDKLocalize.h"
#import "SDKLocalizeManager.h"

@interface SDKLocalize ()
+ (UIImage *)localizedImage:(NSString *)imageName inBundle:(NSString *)bundleName;
+ (nonnull NSString *)localizedString:(nonnull NSString *)key defaultStr:(nonnull NSString *)defaultStr inBundle:(NSString *)bundleName;

@end

//copy from yyImage
static NSArray *_SDKNSBundlePreferredScales() {
    static NSArray *scales;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        CGFloat screenScale = [UIScreen mainScreen].scale;
        if (screenScale <= 1) {
            scales = @[@1,@2,@3];
        } else if (screenScale <= 2) {
            scales = @[@2,@3,@1];
        } else {
            scales = @[@3,@2,@1];
        }
    });
    return scales;
}

/**
 Add scale modifier to the file name (without path extension),
 From @"name" to @"name@2x".
 
 e.g.
 <table>
 <tr><th>Before     </th><th>After(scale:2)</th></tr>
 <tr><td>"icon"     </td><td>"icon@2x"     </td></tr>
 <tr><td>"icon "    </td><td>"icon @2x"    </td></tr>
 <tr><td>"icon.top" </td><td>"icon.top@2x" </td></tr>
 <tr><td>"/p/name"  </td><td>"/p/name@2x"  </td></tr>
 <tr><td>"/path/"   </td><td>"/path/"      </td></tr>
 </table>
 
 @param scale Resource scale.
 @return String by add scale modifier, or just return if it's not end with file name.
 */
static NSString *_SDKNSStringByAppendingNameScale(NSString *string, CGFloat scale) {
    if (!string) return nil;
    if (fabs(scale - 1) <= __FLT_EPSILON__ || string.length == 0 || [string hasSuffix:@"/"]) return string.copy;
    return [string stringByAppendingFormat:@"@%@x", @(scale)];
}

NSString *SDKLocalizedStr(NSString *key, NSString *defaultStr, NSString *bundleName) {
    return [SDKLocalize localizedString:key defaultStr:defaultStr inBundle:bundleName];
}

UIImage *SDKLocalizedImg(NSString *imageName, NSString *bundleName){
    return [SDKLocalize localizedImage:imageName inBundle:bundleName];
}

/**
 eg:
 NSBundle *bundle = SDKBundleWithName(@"SDKUIKit.bundle")
 */
static inline NSBundle *SDKBundleWithName(NSString *name) {
    NSString *tmpBundleName = [name copy];
    if (![name hasSuffix:@".bundle"]) {
        tmpBundleName = [NSString stringWithFormat:@"%@.bundle",tmpBundleName];
    }
        
    NSString *mainBundlePath = [[NSBundle mainBundle] resourcePath];
    NSString *bundlePath = [mainBundlePath stringByAppendingPathComponent:tmpBundleName];
    NSBundle *bundle = [NSBundle bundleWithPath:bundlePath];
    if (bundle) {
        return bundle;
    }
    
    NSString *tempFramework = [name copy];
    NSString *frameExtension = @".framework";
    if (![tempFramework hasSuffix:frameExtension]) {
        tempFramework = [tempFramework stringByAppendingString:frameExtension];
    }
    
    NSString*path =  [[[NSBundle mainBundle] privateFrameworksPath] stringByAppendingPathComponent:tempFramework];
    return [NSBundle bundleWithPath:[path stringByAppendingPathComponent:tmpBundleName]];
    
}


@implementation SDKLocalize

+ (UIImage *)localizedImage:(nonnull NSString *)imageName inBundle:(NSString *)bundleName {
    
    if (imageName.length == 0) return nil;
    if ([imageName hasSuffix:@"/"]) return nil;
    
    NSString *res = imageName.stringByDeletingPathExtension;
    NSString *ext = imageName.pathExtension;
    NSString *path = nil;
    CGFloat scale = 1;
    
    NSArray *exts = ext.length > 0 ? @[ext] : @[@"", @"png", @"jpg"];
    NSArray *scales = _SDKNSBundlePreferredScales();
    NSBundle *tmp = SDKBundleWithName(bundleName);
    if (!tmp) {
        NSLog(@"bundle为空");
    }
    
    NSString *language = [SDKLocalizeManager currentLanguage];
    
    NSString *fileNamePrefix = language;
    if (!fileNamePrefix) {
        NSLog(@"语言编码转化错误:%@",fileNamePrefix);
        return nil;
    }
    
    NSString *bundlePath = [tmp pathForResource:fileNamePrefix ofType:@"lproj"];
    NSBundle *bundle = [NSBundle bundleWithPath:bundlePath];
    
    for (int s = 0; s < scales.count; s++) {
        scale = ((NSNumber *)scales[s]).floatValue;
        NSString *scaledName = _SDKNSStringByAppendingNameScale(res, scale);
        for (NSString *e in exts) {
            path = [bundle pathForResource:scaledName ofType:e];
            if (path) break;
        }
        if (path) break;
    }
    if (path.length == 0) return nil;
    
    NSData *data = [NSData dataWithContentsOfFile:path];
    if (data.length == 0) return nil;
    
    return [[UIImage alloc] initWithData:data scale:scale];
    
}

+ (nonnull NSString *)localizedString:(nonnull NSString *)key defaultStr:(nonnull NSString *)defaultStr inBundle:(NSString *)bundleName {
    NSBundle *tmp = SDKBundleWithName(bundleName);
    NSString *language = [SDKLocalizeManager currentLanguage];
    
    NSString *path = [tmp pathForResource:language ofType:@"lproj"];
    NSBundle *bundle = [NSBundle bundleWithPath:path];
    if (!bundle) {
        language = @"en";
        path = [tmp pathForResource:language ofType:@"lproj"];
        bundle = [NSBundle bundleWithPath:path];
    }

    NSString *localizedString = [bundle localizedStringForKey:key value:defaultStr table:@"Localizable"];
    
    if (!localizedString) {
        localizedString = defaultStr;
    }
    return localizedString;
    
}



@end

