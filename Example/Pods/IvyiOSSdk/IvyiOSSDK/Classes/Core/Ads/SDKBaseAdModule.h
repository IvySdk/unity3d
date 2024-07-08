//
//  SDKBaseAdapter.h
//  Pods
//
//  Created by 余冰星 on 2017/9/19.
//
//

#import <Foundation/Foundation.h>
#import <IvyiOSSdk/SDKBaseAd.h>
#import <IvyiOSSdk/SDKAdDelegate.h>
#import <IvyiOSSdk/SDKFacade.h>

@interface SDKBaseAdModule : NSObject<SDKAdDelegate>
{
@protected
    NSMutableArray<SDKBaseAd *> *adArr;
    NSDictionary *_config;
    NSString *_tag;
    int _adType;
    BOOL _cache;
    BOOL _optimize;
    int _step;
    int _curLoadIdx;
    UIViewController *_rootVC;
    NSString *_adSize;
    NSString *_classSuffix;
}

@property (nonatomic, strong, readonly, nullable) UIViewController * _rootVC;
@property(nonatomic, strong, readonly, nonnull) NSString *tag;
@property(nonatomic, assign, readonly) BOOL cache;
//@property(nonatomic, assign, readonly) BOOL loadall;

-(nonnull instancetype)init:(nonnull NSString *)tag config:(nonnull NSDictionary *)config;
-(void)createAds:(nonnull NSArray *)ids;
-(void)onAdCreated:(nonnull SDKBaseAd *)ad;
-(BOOL)enable;
-(nullable SDKBaseAd *)getAvailableAd;
-(void)loadAd;
-(nullable SDKBaseAd *)showAd:(nonnull UIViewController *)vc;
-(nullable SDKBaseAd *)showVideo:(nonnull UIViewController *)vc rewardId:(int)rewardId;
-(void)_loadNext;
@end
