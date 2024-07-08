//
//  SDKRemoteConfigDelegate.h
//  Pods
//
//  Created by 余冰星 on 2018/10/29.
//

#ifndef SDKExtendDelegate_h
#define SDKExtendDelegate_h

#import <Foundation/Foundation.h>
@protocol SDKExtendDelegate<NSObject>
typedef void(^share_result_block)(BOOL success, NSString *_Nullable error);
typedef void(^string_result_block)(NSString *_Nullable result);
typedef void(^bool_result_block)(BOOL result);
@optional

- (void)requestPhotoWritePermission:(nullable void(^)(BOOL permit))result;

- (BOOL)hasPhotoWritePermission;

- (void)openSettingPage;
/**
 *
 * 是否正在录制
 **/
- (BOOL)isRecording;

/**
 *
 * 开始录制屏幕
 * 此方法必须和- (void)stopRecording成对出现,且必须在主线程启动
 * 由于stopRecording有相册存储的操作，为保证用户体验，可先提示用户相册权限。
 * 录制功能只支持iOS11及以上版本，为保证用户体验，iOS11以下版本接入方录制前可自行增加弹窗提示。
 **/
- (void)startRecording;

/**
 *
 * 暂停录制 此方法并没有真正暂停录制，只是丢弃了录制的媒体数据
 **/
- (void)pauseRecording;

/**
 *
 * 继续录制
 **/
- (void)continueRecording;

/**
 *
 * 停止录制
 * 此方法必须和- (void)startRecording成对出现，且必须在主线程停止
 * localIdent 当前视频的相册标识
 **/
- (void)stopRecording:(nullable bool_result_block)complete;

- (void)shareRecentRecordVideo:(nullable share_result_block)result;
/**
 * 分析多个图片
 * @param photoIdentifiers 相册中的图片资源id数组
 */
- (BOOL)sharePhotos:(nonnull NSArray *)photoIdentifiers result:(nullable share_result_block)result;

/**
 * 分析多个视频
 * @param videoIdentifiers 相册中的视频资源id数组
 */
- (BOOL)shareVideos:(nonnull NSArray *)videoIdentifiers result:(nullable share_result_block)result;

/**
 * @param path 视频存储路径 按照SDK提供的默认模版编辑视频
 * @param complete 视频编辑完成后的回调，返回参数是合成后视频所在的路径;当设置isSaveToAlbum=YES时返回的是在相册的标识
 */
- (void)editVideo:(nonnull NSString *)path complete:(nullable string_result_block)complete;

- (void)showPromotion:(CGPoint)position;
@end

#endif /* SDKRemoteConfigDelegate_h */
