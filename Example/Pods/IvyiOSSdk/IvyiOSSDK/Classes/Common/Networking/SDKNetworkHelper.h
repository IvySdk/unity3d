#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "SDKNetworkReachabilityManager.h"
#import "SDKNetworkActivityIndicatorManager.h"
#import "SDKNetworkCache.h"

#ifndef kIsNetwork
#define kIsNetwork     [SDKNetworkHelper isNetwork]  // 一次性判断是否有网的宏
#endif

#ifndef kIsWWANNetwork
#define kIsWWANNetwork [SDKNetworkHelper isWWANNetwork]  // 一次性判断是否为手机网络的宏
#endif

#ifndef kIsWiFiNetwork
#define kIsWiFiNetwork [SDKNetworkHelper isWiFiNetwork]  // 一次性判断是否为WiFi网络的宏
#endif

typedef NS_ENUM(NSUInteger, PPNetworkStatusType) {
    /** 未知网络*/
    PPNetworkStatusUnknown,
    /** 无网络*/
    PPNetworkStatusNotReachable,
    /** 手机网络*/
    PPNetworkStatusReachableViaWWAN,
    /** WIFI网络*/
    PPNetworkStatusReachableViaWiFi
};

typedef NS_ENUM(NSUInteger, PPRequestSerializer) {
    /** 设置请求数据为JSON格式*/
    PPRequestSerializerJSON,
    /** 设置请求数据为二进制格式*/
    PPRequestSerializerHTTP,
};

typedef NS_ENUM(NSUInteger, PPResponseSerializer) {
    /** 设置响应数据为JSON格式*/
    PPResponseSerializerJSON,
    /** 设置响应数据为二进制格式*/
    PPResponseSerializerHTTP,
};

/** 请求成功的Block */
typedef void(^PPHttpRequestSuccess)(id _Nullable responseObject);

/** 请求失败的Block */
typedef void(^PPHttpRequestFailed)(NSError * _Nullable error);

/** 缓存的Block */
typedef void(^PPHttpRequestCache)(id _Nullable responseCache);

/** 上传或者下载的进度, Progress.completedUnitCount:当前大小 - Progress.totalUnitCount:总大小*/
typedef void (^PPHttpProgress)(NSProgress * _Nonnull progress);

/** 网络状态的Block*/
typedef void(^PPNetworkStatus)(PPNetworkStatusType status);

@class SDKHTTPSessionManager;
@interface SDKNetworkHelper : NSObject
@property (nonatomic, assign) int simulDownloadTaskNumber;
@property (nonatomic, assign) CGFloat maxDownloadFileSize;
- (void)checkReachability:(void (^ _Nullable)(SDKNetworkReachabilityStatus status))block;
/**
 有网YES, 无网:NO
 */
- (BOOL)isNetwork;

/**
 手机网络:YES, 反之:NO
 */
- (BOOL)isWWANNetwork;

/**
 WiFi网络:YES, 反之:NO
 */
- (BOOL)isWiFiNetwork;

/**
 实时获取网络状态,通过Block回调实时获取(此方法可多次调用)
 */
- (void)networkStatusWithBlock:(PPNetworkStatus _Nullable )networkStatus;

/**
 取消所有HTTP请求
 */
- (void)cancelAllHttpRequest;

/**
 取消指定URL的HTTP请求
 */
- (void)cancelHttpRequestWithURL:(NSString *_Nullable)URL;

/**
 开启日志打印 (Debug级别)
 */
- (void)openLog;

/**
 关闭日志打印,默认关闭
 */
- (void)closeLog;


/**
 *  GET请求,无缓存
 *
 *  @param URL        请求地址
 *  @param parameters 请求参数
 *  @param success    请求成功的回调
 *  @param failure    请求失败的回调
 *
 *  @return 返回的对象可取消请求,调用cancel方法
 */
- (__kindof NSURLSessionTask *_Nonnull)GET:(NSString *_Nonnull)URL
                        parameters:(id _Nullable)parameters
                          jsonRequest:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse success:(PPHttpRequestSuccess _Nullable)success
                           failure:(PPHttpRequestFailed _Nullable)failure;

/**
 *  GET请求,自动缓存
 *
 *  @param URL           请求地址
 *  @param parameters    请求参数
 *  @param responseCache 缓存数据的回调
 *  @param success       请求成功的回调
 *  @param failure       请求失败的回调
 *
 *  @return 返回的对象可取消请求,调用cancel方法
 */
- (__kindof NSURLSessionTask *_Nonnull)GET:(NSString *_Nonnull)URL
                        parameters:(id _Nullable)parameters
                    jsonRequest:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse
                     responseCache:(PPHttpRequestCache _Nullable)responseCache
                           success:(PPHttpRequestSuccess _Nullable)success
                           failure:(PPHttpRequestFailed _Nullable)failure;

/**
 *  POST请求,无缓存
 *
 *  @param URL        请求地址
 *  @param parameters 请求参数
 *  @param success    请求成功的回调
 *  @param failure    请求失败的回调
 *
 *  @return 返回的对象可取消请求,调用cancel方法
 */
- (__kindof NSURLSessionTask *_Nonnull)POST:(NSString *_Nonnull)URL
                         parameters:(id _Nullable)parameters
                           jsonRequest:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse success:(PPHttpRequestSuccess _Nullable)success
                            failure:(PPHttpRequestFailed _Nullable)failure;

/**
 *  POST请求,自动缓存
 *
 *  @param URL           请求地址
 *  @param parameters    请求参数
 *  @param responseCache 缓存数据的回调
 *  @param success       请求成功的回调
 *  @param failure       请求失败的回调
 *
 *  @return 返回的对象可取消请求,调用cancel方法
 */
- (__kindof NSURLSessionTask *_Nonnull)POST:(NSString *_Nonnull)URL
                         parameters:(id _Nullable)parameters
                     jsonRequest:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse responseCache:(PPHttpRequestCache _Nullable)responseCache
                            success:(PPHttpRequestSuccess _Nullable)success
                            failure:(PPHttpRequestFailed _Nullable)failure;

/**
 *  上传文件
 *
 *  @param URL        请求地址
 *  @param parameters 请求参数
 *  @param name       文件对应服务器上的字段
 *  @param filePath   文件本地的沙盒路径
 *  @param progress   上传进度信息
 *  @param success    请求成功的回调
 *  @param failure    请求失败的回调
 *
 *  @return 返回的对象可取消请求,调用cancel方法
 */
- (__kindof NSURLSessionTask *_Nonnull)uploadFileWithURL:(NSString *_Nonnull)URL
                                      parameters:(id _Nullable)parameters
                                            name:(NSString *_Nullable)name
                                        filePath:(NSString *_Nullable)filePath
                                        progress:(PPHttpProgress _Nullable)progress
                                         success:(PPHttpRequestSuccess _Nullable)success
                                         failure:(PPHttpRequestFailed _Nullable)failure;

/**
 *  上传单/多张图片
 *
 *  @param URL        请求地址
 *  @param parameters 请求参数
 *  @param name       图片对应服务器上的字段
 *  @param images     图片数组
 *  @param fileNames  图片文件名数组, 可以为nil, 数组内的文件名默认为当前日期时间"yyyyMMddHHmmss"
 *  @param imageScale 图片文件压缩比 范围 (0.f ~ 1.f)
 *  @param imageType  图片文件的类型,例:png、jpg(默认类型)....
 *  @param progress   上传进度信息
 *  @param success    请求成功的回调
 *  @param failure    请求失败的回调
 *
 *  @return 返回的对象可取消请求,调用cancel方法
 */
- (__kindof NSURLSessionTask *_Nonnull)uploadImagesWithURL:(NSString *_Nonnull)URL
                                        parameters:(id _Nullable)parameters
                                              name:(NSString *_Nullable)name
                                            images:(NSArray<UIImage *> *_Nullable)images
                                         fileNames:(NSArray<NSString *> *_Nullable)fileNames
                                        imageScale:(CGFloat)imageScale
                                         imageType:(NSString *_Nullable)imageType
                                          progress:(PPHttpProgress _Nullable)progress
                                           success:(PPHttpRequestSuccess _Nullable)success
                                           failure:(PPHttpRequestFailed _Nullable)failure;
/**
 * 获取下载后文件路径URL
 */
-(nullable NSURL *)getDownloadPathURLWithURL:(NSString *_Nullable)url;
/**
 * 获取下载后文件路径
 */
-(nullable NSString *)getDownloadPathWithURL:(NSString *_Nullable)url;

/**
 * 获取下载后文件内容
 */
-(nullable NSData *)getDownloadContents:(NSString *_Nullable)url;

/**
 * 获取下载后文件内容
 */
-(nullable NSData *)getDownloadContents:(NSString *_Nullable)fileDir fileName:(NSString *_Nullable)fileName;

/**
 * 判断URL是否下载完成
 */
- (BOOL)hasURLDownloaded:(NSString *)url;

/**
 * 判断URL是否下载完成
 */
- (BOOL)hasURLDownloaded:(NSString *)url fileDir:(NSString *_Nullable)fileDir fileName:(NSString *_Nullable)fileName;

/**
 *  下载文件到 sdk 默认存放路径，并以md5(url)文件名的方式存放
 *
 *  @param URL      请求地址
 *  @param progress 文件下载的进度信息
 *  @param success  下载成功的回调(回调参数filePath:文件的路径)
 *  @param failure  下载失败的回调
 *
 *  @return 返回NSURLSessionDownloadTask实例，可用于暂停继续，暂停调用suspend方法，开始下载调用resume方法
 */
- (__kindof NSURLSessionDataTask *_Nullable)downloadWithURL:(NSString *_Nonnull)URL
                                                  progress:(PPHttpProgress _Nullable)progress
                                                   success:(void(^_Nullable)(NSString *_Nullable filePath))success
                                                   failure:(PPHttpRequestFailed _Nullable)failure;
/**
 *  下载文件
 *
 *  @param URL      请求地址
 *  @param fileDir  文件存储目录(默认存储目录为Download)
 *  @param progress 文件下载的进度信息
 *  @param success  下载成功的回调(回调参数filePath:文件的路径)
 *  @param failure  下载失败的回调
 *
 *  @return 返回NSURLSessionDownloadTask实例，可用于暂停继续，暂停调用suspend方法，开始下载调用resume方法
 */
- (__kindof NSURLSessionDataTask *_Nullable)downloadWithURL:(NSURL *_Nonnull)URL
                                       fileDir:(NSString *_Nullable)fileDir
                                      fileName:(NSString *_Nullable)fileName
                                      progress:(PPHttpProgress _Nullable)progress
                                       success:(void(^_Nullable)(NSString *_Nullable filePath))success
                                       failure:(PPHttpRequestFailed _Nullable)failure;

-(NSString *_Nonnull)downloadFilePath:(NSString *_Nullable)fileDir fileName:(NSString *_Nullable)fileName;
/**
 取消所有Download任务
 */
- (void)cancelAllDownloadTask;
/**
 暂停所有Download任务
 */
-(void)pauseAllDownloadTask;
/**
 继续执行所有Download任务
 */
-(void)resumeAllDownloadTask;
/**
 取消指定URL的Download任务
 */
- (void)cancelDownloadTaskWithURL:(NSString *_Nullable)URL;
/**
 暂停指定URL的Download任务
 */
- (void)pauseDownloadTaskWithURL:(NSString *_Nullable)URL;
/*
 **************************************  说明  **********************************************
 *
 * 在一开始设计接口的时候就想着方法接口越少越好,越简单越好,只有GET,POST,上传,下载,监测网络状态就够了.
 *
 * 无奈的是在实际开发中,每个APP与后台服务器的数据交互都有不同的请求格式,如果要修改请求格式,就要在此封装
 * 内修改,再加上此封装在支持CocoaPods后,如果使用者pod update最新SDKNetworkHelper,那又要重新修改此
 * 封装内的相关参数.
 *
 * 依个人经验,在项目的开发中,一般都会将网络请求部分封装 2~3 层,第2层配置好网络请求工具的在本项目中的各项
 * 参数,其暴露出的方法接口只需留出请求URL与参数的入口就行,第3层就是对整个项目请求API的封装,其对外暴露出的
 * 的方法接口只留出请求参数的入口.这样如果以后项目要更换网络请求库或者修改请求URL,在单个文件内完成配置就好
 * 了,大大降低了项目的后期维护难度
 *
 * 综上所述,最终还是将设置参数的接口暴露出来,如果通过CocoaPods方式使用SDKNetworkHelper,在设置项目网络
 * 请求参数的时候,强烈建议开发者在此基础上再封装一层,通过以下方法配置好各种参数与请求的URL,便于维护
 *
 **************************************  说明  **********************************************
 */

#pragma mark - 设置SDKHTTPSessionManager相关属性
#pragma mark 注意: 因为全局只有一个SDKHTTPSessionManager实例,所以以下设置方式全局生效

/**
 *  设置请求超时时间:默认为30S
 *
 *  @param time 时长
 */
- (void)setRequestTimeoutInterval:(NSTimeInterval)time;

/**
 *  是否打开网络状态转圈菊花:默认打开
 *
 *  @param open YES(打开), NO(关闭)
 */
- (void)openNetworkActivityIndicator:(BOOL)open;

/**
 配置自建证书的Https请求, 参考链接: http://blog.csdn.net/syg90178aw/article/details/52839103

 @param cerPath 自建Https证书的路径
 @param validatesDomainName 是否需要验证域名，默认为YES. 如果证书的域名与请求的域名不一致，需设置为NO; 即服务器使用其他可信任机构颁发
        的证书，也可以建立连接，这个非常危险, 建议打开.validatesDomainName=NO, 主要用于这种情况:客户端请求的是子域名, 而证书上的是另外
        一个域名。因为SSL证书上的域名是独立的,假如证书上注册的域名是www.google.com, 那么mail.google.com是无法验证通过的.
 */
- (void)setSecurityPolicyWithCerPath:(NSString *_Nullable)cerPath validatesDomainName:(BOOL)validatesDomainName;

+ (nonnull SDKNetworkHelper *)sharedHelper;
@end

