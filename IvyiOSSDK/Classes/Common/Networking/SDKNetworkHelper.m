//
//  SDKNetworkHelper.m
//  SDKNetworkHelper
//
//  Created by AndyPang on 16/8/12.
//  Copyright © 2016年 AndyPang. All rights reserved.
//


#import "SDKNetworkHelper.h"
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/SDKGCDTimer.h>
#import <IvyiOSSdk/SDKHelper.h>
#import "SDKNetworking.h"
#import "SDKURLResponseSerialization.h"



#ifdef DEBUG
#define PPLog(...) printf("[%s] %s [第%d行]: %s\n", __TIME__ ,__PRETTY_FUNCTION__ ,__LINE__, [[NSString stringWithFormat:__VA_ARGS__] UTF8String])
#else
#define PPLog(...)
#endif

#define NSStringFormat(format,...) [NSString stringWithFormat:format,##__VA_ARGS__]
NSString * const SDK_DOWNLOAD_DIR = @"_sdkdown_";
NSString * const SDK_DOWNLOAD_TMP_DIR = @"_sdkdowntmp_";
//NSString * const SDK_DOWNLOAD_QUEUE_TIMER = @"SDK_DOWNLOAD_QUEUE_TIMER";

@implementation SDKNetworkHelper
{
@private
    NSMutableArray *_allSessionTask;
    NSMutableArray *_allPreRemoveSessionTask;
    NSMutableArray *_allDownloadTask;
    NSMutableDictionary<NSString *, SDKHTTPSessionManager *> *_sessionManagerMap;
    SDKURLSessionManager *_downloadManager;
}
@synthesize simulDownloadTaskNumber = _simulDownloadTaskNumber;

static BOOL _isOpenLog;   // 是否已开启日志打印

-(instancetype)init
{
    self = [super init];
    _sessionManagerMap = [[NSMutableDictionary alloc] init];
    
    [SDKNetworkActivityIndicatorManager sharedManager].enabled = YES;
    
    
    _simulDownloadTaskNumber = 3; // 同时下载任务数 3
    _maxDownloadFileSize = 104857600; // 最大下载空间大小 100MB
    
    NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration backgroundSessionConfigurationWithIdentifier:SDK_DOWNLOAD_DIR];
    configuration.timeoutIntervalForRequest = 30;
    //在蜂窝网络情况下是否继续请求（上传或下载）
    configuration.allowsCellularAccess = YES;
    // 1. 创建会话管理者
    _downloadManager = [[SDKURLSessionManager alloc] initWithSessionConfiguration:configuration];
    _downloadManager.securityPolicy = [SDKSecurityPolicy policyWithPinningMode:SDKSSLPinningModeNone];
    _downloadManager.securityPolicy.allowInvalidCertificates = YES;
    [_downloadManager.securityPolicy setValidatesDomainName:NO];
    
    NSFileManager *fmgr = [NSFileManager defaultManager];
    // 创建sdk下载临时目录，存放下载进度文件及当前已完成的部分
    NSString *tmpDownPath = [self downloadFilePath:SDK_DOWNLOAD_TMP_DIR fileName:nil];
    [fmgr createDirectoryAtPath:tmpDownPath withIntermediateDirectories:YES attributes:nil error:nil];
    // 创建sdk默认下载目录
    NSString *downPath = [self downloadFilePath:SDK_DOWNLOAD_DIR fileName:nil];
    [fmgr createDirectoryAtPath:downPath withIntermediateDirectories:YES attributes:nil error:nil];
    return self;
}
-(nonnull SDKHTTPSessionManager *)getHttpSessionManager:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse
{
    NSString *key = [NSString stringWithFormat:@"%d:%d", jsonRequest, jsonResponse];
    SDKHTTPSessionManager *_sessionManager = [_sessionManagerMap objectForKey:key];
    if (!_sessionManager) {
        _sessionManager = [SDKHTTPSessionManager manager];
        _sessionManager.securityPolicy = [SDKSecurityPolicy policyWithPinningMode:SDKSSLPinningModeNone];
        _sessionManager.securityPolicy.allowInvalidCertificates = YES;
        [_sessionManager.securityPolicy setValidatesDomainName:NO];
        
        // 设置请求的超时时间
        // 设置服务器返回结果的类型:JSON (SDKJSONResponseSerializer,SDKHTTPResponseSerializer)
        _sessionManager.responseSerializer = [SDKJSONResponseSerializer serializer];
        _sessionManager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json", @"application/xhtml+xml", @"application/xml", @"text/html", @"text/json", @"text/plain", @"text/javascript", @"text/xml", @"image/*", @"video/mp4", nil];
        // 打开状态栏的等待菊花
        _sessionManager.requestSerializer.timeoutInterval = 60.f;
        [_sessionManagerMap setObject:_sessionManager forKey:key];
    }
    if (jsonResponse) {
        _sessionManager.responseSerializer = [SDKJSONResponseSerializer serializer];
    } else {
        _sessionManager.responseSerializer = [SDKHTTPResponseSerializer serializer];
    }
    if (jsonRequest) {
        _sessionManager.requestSerializer = [SDKJSONRequestSerializer serializer];
    } else {
        _sessionManager.requestSerializer = [SDKHTTPRequestSerializer serializer];
    }
    return _sessionManager;
}

#pragma mark - 开始监听网络

- (void)networkStatusWithBlock:(PPNetworkStatus)networkStatus {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        __weak PPNetworkStatus block = networkStatus;
        [[SDKNetworkReachabilityManager sharedManager] setReachabilityStatusChangeBlock:^(SDKNetworkReachabilityStatus status) {
            switch (status) {
                case SDKNetworkReachabilityStatusUnknown: {
                    if(block)
                    block(PPNetworkStatusUnknown);
                    if (_isOpenLog) PPLog(@"未知网络");
                    break;
                }
                case SDKNetworkReachabilityStatusNotReachable: {
                    if(block)
                    block(PPNetworkStatusNotReachable);
                    if (_isOpenLog) PPLog(@"无网络");
                    break;
                }
                case SDKNetworkReachabilityStatusReachableViaWWAN: {
                    if(block)
                    block(PPNetworkStatusReachableViaWWAN);
                    if (_isOpenLog) PPLog(@"手机自带网络");
                    break;
                }
                case SDKNetworkReachabilityStatusReachableViaWiFi: {
                    if(block)
                    block(PPNetworkStatusReachableViaWiFi);
                    if (_isOpenLog) PPLog(@"WIFI");
                    break;
                }
            }
        }];
    });
}

- (BOOL)isNetwork {
    return [SDKNetworkReachabilityManager sharedManager].reachable;
}

- (BOOL)isWWANNetwork {
    return [SDKNetworkReachabilityManager sharedManager].reachableViaWWAN;
}

- (BOOL)isWiFiNetwork {
    return [SDKNetworkReachabilityManager sharedManager].reachableViaWiFi;
}

- (void)openLog {
    _isOpenLog = YES;
}

- (void)closeLog {
    _isOpenLog = NO;
}

- (void)cancelAllHttpRequest {
    // 锁操作
    @synchronized(self) {
        [[self allSessionTask] enumerateObjectsUsingBlock:^(NSURLSessionTask  *_Nonnull task, NSUInteger idx, BOOL * _Nonnull stop) {
            [task cancel];
        }];
        [self->_allPreRemoveSessionTask removeAllObjects];
        [self->_allSessionTask removeAllObjects];
    }
}

- (void)cancelHttpRequestWithURL:(NSString *)URL {
    if (!URL) { return; }
    @synchronized (self) {
        [[self allSessionTask] enumerateObjectsUsingBlock:^(NSURLSessionTask  *_Nonnull task, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([task.currentRequest.URL.absoluteString hasPrefix:URL]) {
                *stop = YES;
                [task cancel];
                [self removeSessionTask:task];
            }
        }];
    }
}

#pragma mark - GET请求无缓存
- (NSURLSessionTask *)GET:(NSString *)URL
               parameters:(id)parameters
               jsonRequest:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse
                  success:(PPHttpRequestSuccess)success
                  failure:(PPHttpRequestFailed)failure {
    return [self GET:URL parameters:parameters jsonRequest:jsonRequest jsonResponse:jsonResponse responseCache:nil success:success failure:failure];
}

#pragma mark - POST请求无缓存
- (NSURLSessionTask *)POST:(NSString *)URL
                parameters:(id)parameters
                jsonRequest:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse
                   success:(PPHttpRequestSuccess)success
                   failure:(PPHttpRequestFailed)failure {
    return [self POST:URL parameters:parameters jsonRequest:jsonRequest jsonResponse:jsonResponse responseCache:nil success:success failure:failure];
}

#pragma mark - GET请求自动缓存
- (NSURLSessionTask *)GET:(NSString *)URL
               parameters:(id)parameters
                jsonRequest:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse
            responseCache:(PPHttpRequestCache)responseCache
                  success:(PPHttpRequestSuccess)success
                  failure:(PPHttpRequestFailed)failure {
    //读取缓存
    if(responseCache)
    responseCache([SDKNetworkCache httpCacheForURL:URL parameters:parameters]);
    
    __strong NSURLSessionTask *sessionTask = [[self getHttpSessionManager:jsonRequest jsonResponse:jsonResponse] GET:URL parameters:parameters progress:^(NSProgress * _Nonnull uploadProgress) {
        
    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        
        if (_isOpenLog) {PPLog(@"responseObject = %@",[self jsonToString:responseObject]);}
        [self removeSessionTask:task];
        if(success) {
            success(responseObject);
        }
        //对数据进行异步缓存
        if(responseCache) {
            [SDKNetworkCache setHttpCache:responseObject URL:URL parameters:parameters];
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        
        if (_isOpenLog) {PPLog(@"error = %@",error);}
        [self removeSessionTask:task];
        if (failure) {
            failure(error);
        }
        
    }];
    // 添加sessionTask到数组
    [self addSessionTask:sessionTask];
    
    return sessionTask;
}

#pragma mark - POST请求自动缓存
- (NSURLSessionTask *)POST:(NSString *)URL
                parameters:(id)parameters
                jsonRequest:(BOOL)jsonRequest jsonResponse:(BOOL)jsonResponse
             responseCache:(PPHttpRequestCache)responseCache
                   success:(PPHttpRequestSuccess)success
                   failure:(PPHttpRequestFailed)failure {
    //读取缓存
    if(responseCache) {
        responseCache([SDKNetworkCache httpCacheForURL:URL parameters:parameters]);
    }
    
    __strong NSURLSessionTask *sessionTask = [[self getHttpSessionManager:jsonRequest jsonResponse:jsonResponse] POST:URL parameters:parameters progress:^(NSProgress * _Nonnull uploadProgress) {
        
    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        
        if (_isOpenLog) {PPLog(@"responseObject = %@",[self jsonToString:responseObject]);}
        [self removeSessionTask:task];
        if(success) {
            success(responseObject);
        }
        //对数据进行异步缓存
        if(responseCache) {
            [SDKNetworkCache setHttpCache:responseObject URL:URL parameters:parameters];
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        
        if (_isOpenLog) {PPLog(@"error = %@",error);}
        [self removeSessionTask:task];
        if(failure) {
            failure(error);
        }
    }];
    
    // 添加最新的sessionTask到数组
    [self addSessionTask:sessionTask];
    return sessionTask;
}

-(void)addSessionTask:(NSURLSessionTask *)sessionTask
{
    if (sessionTask) {
        @synchronized(self) {
            _allSessionTask = [self allSessionTask];
            if (_allPreRemoveSessionTask.count > 0) {
                [_allSessionTask removeObjectsInArray:_allPreRemoveSessionTask];
                [_allPreRemoveSessionTask removeAllObjects];
            }
            if (![_allSessionTask containsObject:sessionTask]) {
                [_allSessionTask addObject:sessionTask];
            }
        }
    }
}

-(void)removeSessionTask:(NSURLSessionTask *)sessionTask
{
    @synchronized(self) {
        if (sessionTask && _allPreRemoveSessionTask) {
            [_allPreRemoveSessionTask addObject:sessionTask];
        }
    }
}

#pragma mark - 上传文件
- (NSURLSessionTask *)uploadFileWithURL:(NSString *)URL
                             parameters:(id)parameters
                                   name:(NSString *)name
                               filePath:(NSString *)filePath
                               progress:(PPHttpProgress)progress
                                success:(PPHttpRequestSuccess)success
                                failure:(PPHttpRequestFailed)failure {
    
    __strong NSURLSessionTask *sessionTask = [[self getHttpSessionManager:false jsonResponse:false] POST:URL parameters:parameters constructingBodyWithBlock:^(id<SDKMultipartFormData>  _Nonnull formData) {
        NSError *error = nil;
        [formData appendPartWithFileURL:[NSURL URLWithString:filePath] name:name error:&error];
        if(failure && error) {
            failure(error);
        }
    } progress:^(NSProgress * _Nonnull uploadProgress) {
        //上传进度
        dispatch_sync(dispatch_get_main_queue(), ^{
            if(progress)  {
                progress(uploadProgress);
            }
        });
    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        
        if (_isOpenLog) {PPLog(@"responseObject = %@",[self jsonToString:responseObject]);}
        [self removeSessionTask:task];
        if(success) {
            success(responseObject);
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        
        if (_isOpenLog) {PPLog(@"error = %@",error);}
        [self removeSessionTask:task];
        if(failure) {
            failure(error);
        }
    }];
    
    // 添加sessionTask到数组
    [self addSessionTask:sessionTask];
    
    return sessionTask;
}

#pragma mark - 上传多张图片
- (NSURLSessionTask *)uploadImagesWithURL:(NSString *)URL
                               parameters:(id)parameters
                                     name:(NSString *)name
                                   images:(NSArray<UIImage *> *)images
                                fileNames:(NSArray<NSString *> *)fileNames
                               imageScale:(CGFloat)imageScale
                                imageType:(NSString *)imageType
                                 progress:(PPHttpProgress)progress
                                  success:(PPHttpRequestSuccess)success
                                  failure:(PPHttpRequestFailed)failure {
    __strong NSURLSessionTask *sessionTask = [[self getHttpSessionManager:false jsonResponse:false] POST:URL parameters:parameters constructingBodyWithBlock:^(id<SDKMultipartFormData>  _Nonnull formData) {
        
        for (NSUInteger i = 0; i < images.count; i++) {
            // 图片经过等比压缩后得到的二进制文件
            NSData *imageData = UIImageJPEGRepresentation(images[i], imageScale ?: 1.f);
            // 默认图片的文件名, 若fileNames为nil就使用
            
            NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
            formatter.dateFormat = @"yyyyMMddHHmmss";
            NSString *str = [formatter stringFromDate:[NSDate date]];
            NSString *imageFileName = NSStringFormat(@"%@%lx.%@",str,(long)i,imageType?:@"jpg");
            
            [formData appendPartWithFileData:imageData
                                        name:name
                                    fileName:fileNames ? NSStringFormat(@"%@.%@",fileNames[i],imageType?:@"jpg") : imageFileName
                                    mimeType:NSStringFormat(@"image/%@",imageType ?: @"jpg")];
        }
        
    } progress:^(NSProgress * _Nonnull uploadProgress) {
        //上传进度
        dispatch_sync(dispatch_get_main_queue(), ^{
            if(progress) {
                progress(uploadProgress);
            }
        });
    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        
        if (_isOpenLog) {PPLog(@"responseObject = %@",[self jsonToString:responseObject]);}
        [self removeSessionTask:task];
        if(success) {
            success(responseObject);
        }
        
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        
        if (_isOpenLog) {PPLog(@"error = %@",error);}
        [self removeSessionTask:task];
        if(failure) {
            failure(error);
        }
    }];
    
    // 添加sessionTask到数组
    [self addSessionTask:sessionTask];
    
    return sessionTask;
}

#pragma mark - 下载文件
- (void)removeHistoryWithUrl:(NSString *_Nullable)url
{
    if (url) {
        NSFileManager *fmgr = [NSFileManager defaultManager];
        NSString *thFile = [self downloadFilePath:SDK_DOWNLOAD_TMP_DIR fileName:[[self url2FileName:url] stringByAppendingString:@".th"]];
        NSString *tdFile = [self downloadFilePath:SDK_DOWNLOAD_TMP_DIR fileName:[[self url2FileName:url] stringByAppendingString:@".td"]];
        NSError *error;
        [fmgr removeItemAtPath:thFile error:&error];
        error = nil;
        [fmgr removeItemAtPath:tdFile error:&error];
    }
}

- (NSData *)getHistoryWithUrl:(NSString *_Nullable)url
{
    if (!url) {
        return nil;
    }
    NSString *fileName = [[self url2FileName:url] stringByAppendingString:@".th"];
    NSData *data = [NSData dataWithContentsOfFile:[self downloadFilePath:SDK_DOWNLOAD_TMP_DIR fileName:fileName]];
    return data;
}

- (void)saveHistoryWithUrl:(NSString *_Nullable)url DownloadTaskResumeData:(NSData *)data{
    if (url && data) {
        NSString *filePath = [self downloadFilePath:SDK_DOWNLOAD_TMP_DIR fileName:[[self url2FileName:url] stringByAppendingString:@".th"]];
        [data writeToFile:filePath atomically:YES];
    }
}

- (NSMutableArray *)allDownloadTask {
    if (!_allDownloadTask) {
        _allDownloadTask = [[NSMutableArray alloc] init];
    }
    return _allDownloadTask;
}

- (void)cancelAllDownloadTask {
    // 锁操作
    @synchronized(self) {
        DLog(@"[Download cancel all] : %ld", [self allDownloadTask].count);
        [[self allDownloadTask] enumerateObjectsUsingBlock:^(NSURLSessionDownloadTask  *_Nonnull task, NSUInteger idx, BOOL * _Nonnull stop) {
            [task cancelByProducingResumeData:^(NSData * _Nullable resumeData){
                [self saveHistoryWithUrl:task.currentRequest.URL.absoluteString DownloadTaskResumeData:resumeData];
            }];
        }];
        [[self allDownloadTask] removeAllObjects];
    }
}

-(void)pauseAllDownloadTask
{
    // 锁操作
    @synchronized(self) {
        DLog(@"[Download pause all] : %ld", [self allDownloadTask].count);
        [[self allDownloadTask] enumerateObjectsUsingBlock:^(NSURLSessionDownloadTask  *_Nonnull task, NSUInteger idx, BOOL * _Nonnull stop) {
            [task suspend];
        }];
    }
}

-(void)resumeAllDownloadTask
{
    if ([self allDownloadTask].count <= 0){
        return;
    }
    __block int downloadingCount = 0;
    [[self allDownloadTask] enumerateObjectsUsingBlock:^(NSURLSessionDownloadTask  *_Nonnull task, NSUInteger idx, BOOL * _Nonnull stop) {
        if (task.state == NSURLSessionTaskStateRunning) {
            downloadingCount++;
        }
        if (downloadingCount < self->_simulDownloadTaskNumber) {
            if (task.state == NSURLSessionTaskStateSuspended) {
                [task resume];
                DLog(@"[Download resume] %@", task.currentRequest.URL.absoluteString);
            }
        } else {
            if (task.state != NSURLSessionTaskStateCanceling || task.state != NSURLSessionTaskStateCompleted) {
                [task suspend];
                DLog(@"[Download suspend] %@", task.currentRequest.URL.absoluteString);
            }
        }
    }];
}

- (void)cancelDownloadTaskWithURL:(NSString *)URL {
    if (!URL) { return; }
    @synchronized (self) {
        [[self allDownloadTask] enumerateObjectsUsingBlock:^(NSURLSessionDownloadTask  *_Nonnull task, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([task.currentRequest.URL.absoluteString hasPrefix:URL]) {
                DLog(@"[Download cancel] %@", task.currentRequest.URL.absoluteString);
                *stop = YES;
                //                [task cancel];
                [task cancelByProducingResumeData:^(NSData * _Nullable resumeData){
                    [self saveHistoryWithUrl:task.currentRequest.URL.absoluteString DownloadTaskResumeData:resumeData];
                }];
                [[self allDownloadTask] removeObject:task];
                [self resumeAllDownloadTask];
            }
        }];
    }
}

- (void)pauseDownloadTaskWithURL:(NSString *)URL {
    if (!URL) { return; }
    @synchronized (self) {
        [[self allDownloadTask] enumerateObjectsUsingBlock:^(NSURLSessionTask  *_Nonnull task, NSUInteger idx, BOOL * _Nonnull stop) {
            if ([task.currentRequest.URL.absoluteString hasPrefix:URL]) {
                DLog(@"[Download pause] %@", task.currentRequest.URL.absoluteString);
                *stop = YES;
                [task suspend];
                [self resumeAllDownloadTask];
            }
        }];
    }
}

- (NSURL *)getDownloadTempFilePath:(NSString *_Nullable)fileName
{
    //拼接文件路径
    NSString *tmpFileName = [NSString stringWithFormat:@"%@.td", fileName];
    NSString *filePath = [self downloadFilePath:SDK_DOWNLOAD_TMP_DIR fileName:tmpFileName];
    //返回文件位置的URL路径
    return [NSURL fileURLWithPath:filePath];
}

-(NSString *)url2FileName:(NSString *)url
{
    NSString *name = [[SDKHelper md5:url] stringByAppendingPathExtension:url.pathExtension];
    return name;
}

-(NSURL *)getDownloadPathURLWithURL:(NSString *)url
{
    NSString *path = [self getDownloadPathWithURL:url];
    NSURL *result = nil;
    if (path) {
        result = [NSURL fileURLWithPath:path];
    }
    return result;
}

-(NSString *)getDownloadPathWithURL:(NSString *)url
{
    NSString *path = [self downloadFilePath:SDK_DOWNLOAD_DIR fileName:[self url2FileName:url]];
    return path;
}

-(NSData *)getDownloadContents:(NSString *)url
{
    return [self getDownloadContents:SDK_DOWNLOAD_DIR fileName:[self url2FileName:url]];
}

-(NSData *)getDownloadContents:(NSString *)fileDir fileName:(NSString *)fileName
{
    NSString *path = [self downloadFilePath:fileDir fileName:fileName];
    if (path) {
        NSError *error;
        NSData *data = [NSData dataWithContentsOfFile:path options:NSDataReadingUncached error:&error];
        return data;
    }
    return nil;
}

- (void)_downloadComplete:(NSURLSessionTask *)downloadTask
                 response:(NSURLResponse *)response
                  fileDir:(NSString *)fileDir fileName:(NSString *)fileName tmpFilePath:(NSString *)tmpFilePath
                  success:(void(^)(NSString *))success
                  failure:(PPHttpRequestFailed)failure error:(NSError *)error
{
    [[self allDownloadTask] removeObject:downloadTask];
    [self resumeAllDownloadTask];
    if (error) {
        DLog(@"[Download failure] %@, Err = %@", response.URL.absoluteString, [error localizedDescription]);
        //下载失败，报错进度
        if(failure) {
            NSData *resumeData = [error.userInfo objectForKey:@"NSURLSessionDownloadTaskResumeData"];
            [self saveHistoryWithUrl:response.URL.absoluteString DownloadTaskResumeData:resumeData];
            
            failure(error);
        };
        return;
    }
    //下载成功
  
    //拼接缓存目录
    __block NSString *downloadDir = [self downloadFilePath:fileDir fileName:nil];;
    DLog(@"[Download complete] %@, Path = %@", response.URL.absoluteString, downloadDir);
    //打开文件管理器
    NSFileManager *fmgr = [NSFileManager defaultManager];
    //创建下载目录
    [fmgr createDirectoryAtPath:downloadDir withIntermediateDirectories:YES attributes:nil error:nil];
    NSString *distPath = [self downloadFilePath:fileDir fileName:fileName ? fileName : [tmpFilePath.lastPathComponent stringByDeletingPathExtension]];
    [fmgr removeItemAtPath:distPath error:&error];
    [fmgr moveItemAtPath:tmpFilePath toPath:distPath error:&error];
    
    [self removeHistoryWithUrl:response.URL.absoluteString];
    if(success) {
        success(distPath /** NSURL->NSString*/);
    }
    // 自动清理下载目录，控制缓存大小
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_LOW, 0), ^{
        NSArray<NSString *>* result = [SDKHelper autoCleanFolder:downloadDir limitsSize:self->_maxDownloadFileSize sizeWithMb:NO];
        if (result && result > 0) {
            [result enumerateObjectsUsingBlock:^(NSString * _Nonnull filePath, NSUInteger idx, BOOL * _Nonnull stop) {
                NSString *cleanFileName = [[filePath lastPathComponent] stringByDeletingPathExtension];
                NSFileManager *fmgr = [NSFileManager defaultManager];
                NSString *tdFilePath = [self downloadFilePath:SDK_DOWNLOAD_TMP_DIR fileName:[cleanFileName stringByAppendingString:@".td"]];
                NSString *thFilePath = [self downloadFilePath:SDK_DOWNLOAD_TMP_DIR fileName:[cleanFileName stringByAppendingString:@".th"]];
                NSError *error;
                [fmgr removeItemAtPath:tdFilePath error:&error];
                error = nil;
                [fmgr removeItemAtPath:thFilePath error:&error];
                
                DLog(@"[autoclean] %@, %@, %@", filePath, tdFilePath, thFilePath);
            }];
        }
    });
}

- (BOOL)hasURLDownloaded:(NSString *)url
{
    return [self hasURLDownloaded:url fileDir:SDK_DOWNLOAD_DIR fileName:[self url2FileName:url]];
}

- (BOOL)hasURLDownloaded:(NSString *)url fileDir:(NSString *)fileDir fileName:(NSString *)fileName
{
    NSFileManager *fmgr = [NSFileManager defaultManager];
    NSString *distPath = [self downloadFilePath:fileDir fileName:fileName];
    BOOL checkDownloadFileExists = [fmgr fileExistsAtPath:distPath];
    return checkDownloadFileExists;
}

- (NSURLSessionTask *)downloadWithURL:(NSString *)URL
                             progress:(PPHttpProgress)progress
                              success:(void(^)(NSString *))success
                              failure:(PPHttpRequestFailed)failure {
    return [self downloadWithURL:[NSURL URLWithString:URL] fileDir:SDK_DOWNLOAD_DIR fileName:[self url2FileName:URL] progress:progress success:success failure:failure];
}

- (NSURLSessionTask *)downloadWithURL:(NSURL *)URL
                              fileDir:(NSString *)fileDir
                             fileName:(NSString *)fileName
                             progress:(PPHttpProgress)progress
                              success:(void(^)(NSString *))success
                              failure:(PPHttpRequestFailed)failure {
    BOOL checkDownloadFileExists = [self hasURLDownloaded:URL.absoluteString fileDir:fileDir fileName:fileName];
    //已下载过，无需下载
    if (checkDownloadFileExists) {
        DLog(@"[Download] %@ 已下载完成，无需下载。", URL.absoluteString);
        return nil;
    }
    __block NSURLSessionTask *downloadTask = nil;
    if ([self allDownloadTask].count > 0) {
        @synchronized (self) {
            [[self allDownloadTask] enumerateObjectsUsingBlock:^(NSURLSessionTask  *_Nonnull task, NSUInteger idx, BOOL * _Nonnull stop) {
                if ([task.currentRequest.URL.absoluteString hasPrefix:URL.absoluteString]) {
                    *stop = YES;
                    downloadTask = task;
                }
            }];
        }
    }
    if (!downloadTask) {
        NSData *downLoadHistoryData = [self getHistoryWithUrl:URL.absoluteString];
        if (downLoadHistoryData) {
            DLog(@"使用断点续传");
            downloadTask = [_downloadManager downloadTaskWithResumeData:downLoadHistoryData progress:^(NSProgress * _Nonnull downloadProgress) {
                //下载进度
                dispatch_sync(dispatch_get_main_queue(), ^{
                    if(progress)
                    progress(downloadProgress);
                    DLog(@"[Downloading] %@ : %d%%", downloadTask.currentRequest.URL.absoluteString, (int)(downloadProgress.completedUnitCount / downloadProgress.totalUnitCount));
                });
            } destination:^NSURL * _Nonnull(NSURL * _Nonnull targetPath, NSURLResponse * _Nonnull response) {
                return [self getDownloadTempFilePath:fileName ? fileName : response.suggestedFilename];
            } completionHandler:^(NSURLResponse * _Nonnull response, NSURL * _Nullable tmpFileURL, NSError * _Nullable error) {
                [self _downloadComplete:downloadTask response:response fileDir:fileDir fileName:fileName tmpFilePath:tmpFileURL.path success:success failure:failure error:error];
            }];
        } else {
            NSURLRequest *request = [NSURLRequest requestWithURL:URL];
            downloadTask = [_downloadManager downloadTaskWithRequest:request progress:^(NSProgress * _Nonnull downloadProgress) {
                //下载进度
                dispatch_sync(dispatch_get_main_queue(), ^{
                    if(progress)
                    progress(downloadProgress);
                    DLog(@"[Downloading] %@ : %d%%", downloadTask.currentRequest.URL.absoluteString, (int)(downloadProgress.completedUnitCount / downloadProgress.totalUnitCount));
                });
            } destination:^NSURL * _Nonnull(NSURL * _Nonnull targetPath, NSURLResponse * _Nonnull response) {
                return [self getDownloadTempFilePath:fileName ? fileName : response.suggestedFilename];
            } completionHandler:^(NSURLResponse * _Nonnull response, NSURL * _Nullable tmpFileURL, NSError * _Nullable error) {
                [self _downloadComplete:downloadTask response:response fileDir:fileDir fileName:fileName tmpFilePath:tmpFileURL.path success:success failure:failure error:error];
            }];
        }
    }
    
    // 添加sessionTask到数组
    if(downloadTask) {
        if (![[self allDownloadTask] containsObject:downloadTask]) {
            [[self allDownloadTask] addObject:downloadTask];
            DLog(@"[Download start] %@", downloadTask.currentRequest.URL.absoluteString);
        }
        //开始下载
        //        [downloadTask resume];
        [self resumeAllDownloadTask];
    }
    return downloadTask;
}

-(NSString *)downloadFilePath:(NSString *)fileDir fileName:(NSString *)fileName
{
    NSString *downloadDir = [[NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) lastObject] stringByAppendingPathComponent:fileDir ? fileDir : SDK_DOWNLOAD_DIR];
    NSString *filePath = fileName ? [downloadDir stringByAppendingPathComponent:fileName] : downloadDir;
    return filePath;
}

/**
 *  json转字符串
 */
- (NSString *)jsonToString:(id)data {
    if(data == nil) { return nil; }
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:data options:NSJSONWritingPrettyPrinted error:nil];
    return [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
}

/**
 存储着所有的请求task数组
 */
- (NSMutableArray *)allSessionTask {
    if (!_allSessionTask) {
        _allSessionTask = [[NSMutableArray alloc] init];
        _allPreRemoveSessionTask = [[NSMutableArray alloc] init];
    }
    return _allSessionTask;
}

#pragma mark - 重置SDKHTTPSessionManager相关属性

- (void)setRequestTimeoutInterval:(NSTimeInterval)time {
    for (SDKHTTPSessionManager *_sessionManager in _sessionManagerMap) {
        _sessionManager.requestSerializer.timeoutInterval = time;
    }
}

- (void)openNetworkActivityIndicator:(BOOL)open {
    [[SDKNetworkActivityIndicatorManager sharedManager] setEnabled:open];
}

- (void)setSecurityPolicyWithCerPath:(NSString *)cerPath validatesDomainName:(BOOL)validatesDomainName {
    NSData *cerData = [NSData dataWithContentsOfFile:cerPath];
    // 使用证书验证模式
    SDKSecurityPolicy *securityPolicy = [SDKSecurityPolicy policyWithPinningMode:SDKSSLPinningModeCertificate];
    // 如果需要验证自建证书(无效证书)，需要设置为YES
    securityPolicy.allowInvalidCertificates = YES;
    // 是否需要验证域名，默认为YES;
    securityPolicy.validatesDomainName = validatesDomainName;
    securityPolicy.pinnedCertificates = [[NSSet alloc] initWithObjects:cerData, nil];
    
    [[self getHttpSessionManager:false jsonResponse:false] setSecurityPolicy:securityPolicy];
}
/**
 开始监测网络状态
 */
- (void)checkReachability:(void (^ _Nullable)(SDKNetworkReachabilityStatus status))block {
    [[SDKNetworkReachabilityManager sharedManager] startMonitoring];
    [[SDKNetworkReachabilityManager sharedManager] setReachabilityStatusChangeBlock:block];
}

+ (nonnull SDKNetworkHelper *)sharedHelper
{
    static SDKNetworkHelper *_instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[self alloc] init];
    });
    return _instance;
}

@end


#pragma mark - NSDictionary,NSArray的分类
/*
 ************************************************************************************
 *新建NSDictionary与NSArray的分类, 控制台打印json数据中的中文
 ************************************************************************************
 */

#ifdef DEBUG
@implementation NSArray (PP)

- (NSString *)descriptionWithLocale:(id)locale {
    NSMutableString *strM = [NSMutableString stringWithString:@"(\n"];
    [self enumerateObjectsUsingBlock:^(id obj, NSUInteger idx, BOOL *stop) {
        [strM appendFormat:@"\t%@,\n", obj];
    }];
    [strM appendString:@")"];
    
    return strM;
}

@end

@implementation NSDictionary (PP)

- (NSString *)descriptionWithLocale:(id)locale {
    NSMutableString *strM = [NSMutableString stringWithString:@"{\n"];
    [self enumerateKeysAndObjectsUsingBlock:^(id key, id obj, BOOL *stop) {
        [strM appendFormat:@"\t%@ = %@;\n", key, obj];
    }];
    
    [strM appendString:@"}\n"];
    
    return strM;
}
@end

#endif

