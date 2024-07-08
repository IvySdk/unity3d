//
//  SDKLocalizedString.h
//  Pods
//
//  Created by LiuShulong on 23/04/2017.
//
//

#import <Foundation/Foundation.h>

/**
 * 从bundle或framework中获取本地化字符串。如果未指定后缀名，会先从bundle中查找，查找不到会从framework中查找，都查找不到则返回nil。
 * pod中多语言文字文件名称为Localizable.strings
 * 多语言文字创建方式参考wiki:http://wiki.intra.xiaojukeji.com/pages/viewpage.action?pageId=91695519
 * eg.
 *    NSString *str = SDKLocalizedStr("首页","Pod.bundle");
 *    NSString *str = SDKLocalizedStr("首页","Pod");
 *    NSString *str = SDKLocalizedStr("首页","Pod.framework");

 * @param key        字符串key
 * @param container 如果没有bundle或framework后缀的话会自动添加
 * @return string    本地化的字符串,不存在会默认返回key
 */
FOUNDATION_EXPORT NSString *SDKLocalizedStr(NSString *key, NSString *defaultStr, NSString *container);

/**
 * 从指定bundle中获取本地化图片,支持并按照后缀名为 png,jpg 的顺序查找图片。图片添加方式与多语言添加方式相同。
 * eg.
 *  UIImage *str = SDKLocalizedImg("test","Pod.bundle");
 *  UIImage *str = SDKLocalizedImg("test.png","Pod");
 *  UIImage *str = SDKLocalizedImg("test@3x.png","Pod.framework");
 * @param  imageName   图片名称
 * @param  container  如果没有bundle或framework后缀的话会自动添加
 * @return image      本地化的图片,不存在会返回nil
 */
FOUNDATION_EXPORT UIImage *SDKLocalizedImg(NSString *imageName,NSString *container);


@interface SDKLocalize : NSObject



@end

