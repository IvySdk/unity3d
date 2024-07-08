//
//  SDKBaseAd.h
//  Pods
//
//  Created by IceStar on 2017/7/12.
//
//

#import <Foundation/Foundation.h>
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/SDKAdDelegate.h>
#import <IvyiOSSdk/SDKConstants.h>
//typedef void (^ad_load_result_block)(BOOL success, NSError * _Nullable error);
@interface SDKBaseAd : NSObject
{
@protected
    id<SDKAdDelegate> _delegate;
    NSString *_adId;
    NSString *_tag;
    NSString *_platform;
    NSDictionary *_config;
    NSString *_adSize;
    BOOL _hasShown;
    BOOL _isShowing;
    BOOL _isAvailable;
//    BOOL _needLoadInMainThread;
    int _index;
    int _adType;
    int _orientation;
    int _timeoutSeconds;
    long _lastLoadTime;
    long _loadIntervalSec;
    UIViewController *_vc;
}
@property (nonatomic, assign) int rewardId;
@property (nonatomic, assign, readonly) BOOL isAvailable;
@property (nonatomic, assign, readonly) BOOL isLoading;
@property (nonatomic, assign, readonly) BOOL isShowing;
@property (nonatomic, assign, readonly) int adType;
@property (nonatomic, assign, readonly) int orientation;
@property (nonatomic, assign, readwrite) int timeoutSeconds;
@property (nonatomic, strong, readonly, nonnull) NSDictionary * config;
@property (nonatomic, copy, readonly, nonnull) NSString *tag;
-(nonnull instancetype)init:(nonnull NSString *)tag index:(int)index config:(nonnull NSDictionary *)config size:(nullable NSString *)size orientation:(SDK_ORIENTATION)orientation delegate:(nonnull id<SDKAdDelegate>) delegate;
-(BOOL)loadAd:(nonnull UIViewController *)vc;
-(void)showAd:(nonnull UIViewController *)vc;
-(void)adFailed:(nullable NSString *)error;
-(void)adDidShown;
-(void)startCheckShowFailedTimer;
-(void)adShowFailed;
-(void)adLoaded;
-(void)adDidClose;
-(void)adNeedReload;
-(void)adDidClick;
-(void)didBecomeActive;
@end
