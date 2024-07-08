//
//  SDKStartPromotionAd.m
//  EasyShowView
//
//  Created by ivy on 2020/12/25.
//
#import <UIKit/UIKit.h>
#import <IvyiOSSdk/SDKStartPromotionAd.h>
#import <IvyiOSSdk/SDKCache.h>
#import <IvyiOSSdk/SDKGCDTimer.h>
#import <IvyiOSSdk/SDKHelper.h>
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/UIImageView+SDKNetworking.h>
#import <CommonCrypto/CommonDigest.h>
#import <IvyiOSSdk/SDKNetworkHelper.h>
NSString * const SDK_START_PROMOTION_SHOW_TIME = @"SDK_START_PROMOTION_SHOW_TIME";
NSString * const SDK_START_PROMOTION_TIMER = @"SDK_START_PROMOTION_TIMER";
NSString * const SDK_START_PROMOTION_SHOW_TIMESTAMP = @"SDK_START_PROMOTION_SHOW_TIMESTAMP";
NSString * const SDK_START_PROMOTION_TODAY_SHOW_TIMESTAMP = @"SDK_START_PROMOTION_TODAY_SHOW_%@";

NSString * const SPLASH_PROMOTION_DISPLAYED = @"sp_displayed";
NSString * const SPLASH_PROMOTION_CLICKED = @"sp_clicked";
NSString * const SPLASH_PROMOTION_SKIPPED = @"sp_skipped";
@implementation SDKStartPromotionAd{
    UIWindow* rootWindow;
    int leftCount;
    UIButton* skipButton;
    int promotionTime; //开屏交叉倒计时5秒后退出
    int promotionKeep; //开屏交叉保持秒数不能点击“跳过”
    int promotionDayTimes; //开屏广告每天展示次数
    int promotionInterval; //开屏广告展示间隔秒数
    NSArray<NSDictionary *> *apps;
    NSString *_appstoreid;
    NSString *_package;
    NSNumber *_appid;
    NSString *_imageUrl;
    void (^adClickCallback)(NSString*);
    BOOL isBusy;
}

-(BOOL)isValid
{
    return apps && apps.count > 0;
}

-(void)show:(void (^)(NSString * _Nonnull))callback
{
    int index = arc4random() % apps.count;
    NSDictionary *app = [apps objectAtIndex:index];
    if (app) {
        [self loadAdWithUrl:[app objectForKey:@"image"] appid:[app objectForKey:@"appid"] appstoreId:[app objectForKey:@"appstoreid"] package:[app objectForKey:@"package"]];
    }
}

-(instancetype)initWithData:(NSDictionary *)data
{
    self = [self init];
    promotionTime = ((NSNumber *)[data objectForKey:@"promotionTime"]).intValue;
    promotionKeep = ((NSNumber *)[data objectForKey:@"promotionKeep"]).intValue;
    promotionDayTimes = ((NSNumber *)[data objectForKey:@"promotionDayTimes"]).intValue;
    promotionInterval = ((NSNumber *)[data objectForKey:@"promotionInterval"]).intValue;
    apps = (NSArray *)[data objectForKey:@"apps"];
    if (apps && apps.count > 0) {
        NSMutableArray *arr = [[NSMutableArray alloc] init];
        for (NSDictionary *app in apps) {
            NSNumber *appid = (NSNumber *)[app objectForKey:@"appid"];
            NSNumber *package = (NSNumber *)[app objectForKey:@"package"];
            NSString *appstoreid = [app objectForKey:@"appstoreid"];
            NSString *image = [app objectForKey:@"image"];
            if (appid && appstoreid && package && image && [image containsString:@"http"]) {
                if (![SDKHelper isAppInstalled:appid.stringValue]) {
                    [arr addObject:app];
                    [[SDKNetworkHelper sharedHelper] downloadWithURL:image progress:^(NSProgress *progress){
                        if (@available(iOS 11.0, *)) {
                            DLog(@"[adlog] StartPromotion %@ %@", image, progress.localizedDescription);
                        } else {
                            // Fallback on earlier versions
                        }
                    } success:^(NSString *filePath) {
                        DLog(@"[adlog] StartPromotion %@ complete!", filePath);
                    } failure:^(NSError *error) {
                        DLog(@"[adlog] StartPromotion %@ error: %@", image, [error localizedDescription]);
                    }];
                }
            }
        }
        apps = arr;
    }
    return self;
}


-(void)loadAdWithUrl:(NSString *)imageRemoreUrl appid:(NSNumber *) appid appstoreId:(NSString *)appstoreId package:(NSString *)package{
    _imageUrl = imageRemoreUrl;
    _appid = appid;
    _appstoreid = appstoreId;
    _package = package;
    
    if([self checkCanShowToday:promotionDayTimes promotionInterval:promotionInterval]){
        isBusy = true;
        if ([[SDKNetworkHelper sharedHelper] hasURLDownloaded:imageRemoreUrl]) {
            NSURL * cachedImagePath = [[SDKNetworkHelper sharedHelper] getDownloadPathURLWithURL:imageRemoreUrl];
            [self loadImageWindow:cachedImagePath];
            self->leftCount = self->promotionTime;
            [self updateBtnInfo];
            [[SDKGCDTimer sharedInstance] scheduleGCDTimerWithName:SDK_START_PROMOTION_TIMER interval:1 queue:dispatch_get_main_queue() repeats:YES option:SDKGCDTimerOptionCancelPrevAction action:^{
                [self updateBtnInfo];
            }];
            [self recordStartShow];
            [[SDKFacade sharedInstance] logEvent:SPLASH_PROMOTION_DISPLAYED withData:@{@"promoteapp":package, @"promoteappid":appid, @"promoteappstoreid":appstoreId}];
        } else {
            DLog(@"[adlog] StartPromotion ad:%@ (%@) hasn't download complete!", appid, imageRemoreUrl);
        }
    }else{
        DLog(@"[adlog] StartPromotion unable to show launch promotion ad today");
    }
}

-(void)updateBtnInfo{
    NSArray *languages = [NSLocale preferredLanguages];
    NSString *currentLanguage = [languages objectAtIndex:0];
    NSString* btnStr;
    if ([currentLanguage hasPrefix:@"zh"]){
        btnStr = [NSString stringWithFormat:[self skipCanable] ? @"跳过 | %ld":@"%ld",(long)self->leftCount];
    }else{
        btnStr = [NSString stringWithFormat:[self skipCanable] ? @"Skip | %ld":@"%ld",(long)self->leftCount];
    }
    [self->skipButton setTitle:btnStr forState:UIControlStateNormal];
    
    UIFont* skipBtnFont = self->skipButton.titleLabel.font;
    CGSize titleSize  = [btnStr sizeWithAttributes:@{NSFontAttributeName: [UIFont fontWithName:skipBtnFont.fontName size:skipBtnFont.pointSize]}];
    self->skipButton.frame = CGRectMake(self->rootWindow.bounds.size.width - titleSize.width - 40, 20,titleSize.width + 20,titleSize.height + 10);
    if (self->leftCount <= 0) {
        [self hide];
    }
    else {
        self->leftCount --;
    }

}

-(void)loadImageWindow:(NSURL *)imagePath{
    rootWindow = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
    rootWindow.rootViewController = [UIViewController new];
    rootWindow.rootViewController.view.backgroundColor = [UIColor clearColor];
    rootWindow.rootViewController.view.userInteractionEnabled = NO;
    
    rootWindow.windowLevel = UIWindowLevelStatusBar + 1;
    rootWindow.hidden = NO;
    rootWindow.alpha = 1;
    [self setupSubviews:rootWindow image:imagePath];
}

-(void)setupSubviews:(UIWindow *) window image:(NSURL *) imagePath{
    
    //广告图片
    UIImageView *imageView = [[UIImageView alloc] initWithFrame:window.bounds];
    [imageView setImageWithURL:imagePath];
    imageView.userInteractionEnabled = YES;
    imageView.contentMode = UIViewContentModeScaleAspectFill;
    UITapGestureRecognizer* tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(onClickAdCallback)];
    [imageView addGestureRecognizer:tap];
    [window addSubview:imageView];
    
    //左下角AD角标
    UIImage* adIcon = [self _findLocalImageByName:@"ad_corner.png"];
    UIImageView *adIconImage = [[UIImageView alloc] initWithFrame:CGRectMake(10, window.bounds.size.height - 40, 50, 30)];
    adIconImage.image = adIcon;
    [window addSubview:adIconImage];
    
    //右上角跳过按钮
    skipButton = [[UIButton alloc] initWithFrame:CGRectMake(window.bounds.size.width - 50, 20, 80, 30)];
    skipButton.titleLabel.font = [UIFont systemFontOfSize:20];
    skipButton.layer.cornerRadius = 10;//设置圆角的大小
    skipButton.alpha = 0.8;
    [skipButton setBackgroundColor:[UIColor grayColor]];
    
    [skipButton setContentHorizontalAlignment:UIControlContentHorizontalAlignmentCenter];
    [skipButton addTarget:self action:@selector(onClickSkipCallback) forControlEvents:UIControlEventTouchUpInside];
    
    [window addSubview:skipButton];
}

-(BOOL) skipCanable{
    return leftCount < promotionKeep;
}

- (void)onClickAdCallback
{
    [[SDKHelper sharedHelper] openAppStoreId:_appstoreid];
    [[SDKFacade sharedInstance] logEvent:SPLASH_PROMOTION_CLICKED withData:@{@"promoteapp":_package, @"promoteappid":_appid, @"promoteappstoreid":_appstoreid}];
    if(self->adClickCallback != NULL){
        self->adClickCallback(_appid.stringValue);
    }
    [self hide];
}

- (void)onClickSkipCallback
{
    if([self skipCanable]){
        [[SDKGCDTimer sharedInstance] cancelTimerWithName:SDK_START_PROMOTION_TIMER];
        isBusy = false;
        [[SDKFacade sharedInstance] logEvent:SPLASH_PROMOTION_SKIPPED withData:@{@"promoteapp":_package, @"promoteappid":_appid, @"promoteappstoreid":_appstoreid}];
        [self hide];
    }
}

- (void)hide
{
    [UIView animateWithDuration:0.3 animations:^{
        self->rootWindow.alpha = 0;
    } completion:^(BOOL finished) {
        [self->rootWindow.subviews.copy enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
            [obj removeFromSuperview];
        }];
        self->rootWindow.hidden = YES;
        self->rootWindow = nil;
    }];
}

//将所下载的图片保存到本地
-(void) checkOrSaveDataToCacheWithCallback:(NSData *)data withCacheFilePath:(NSString *)filePath {
    DLog(@"[adlog] StartPromotion cache file path is: %@",filePath);
    NSError* error;
    [data writeToFile:filePath options:NSDataWritingAtomic error: &error];
    if(error != nil)
        DLog(@"[adlog] StartPromotion write error %@", error);
}

-(UIImage *) _findLocalImageByName:(NSString *) fileName{
    NSString *bundlePath = [[NSBundle bundleForClass:[self class]] pathForResource:@"IvyiOSSdk-Core" ofType:@"bundle"];
    NSBundle* thirdLoginBundle = [NSBundle bundleWithPath:bundlePath];
    NSString *imagePath = [thirdLoginBundle pathForResource: [fileName stringByDeletingPathExtension] ofType:[fileName pathExtension]];
    return [UIImage imageWithContentsOfFile:imagePath];
}

-(BOOL) checkCanShowToday:(int)promotionDayTimes promotionInterval:(int) promotionInterval{
    NSString* todayShowKey = [self getTodayShowKey];
    NSNumber* tmp = (NSNumber *)[[SDKCache cache] objectForKey:todayShowKey];
    int todayShowTime = tmp ? tmp.intValue : 0;
    
    if(todayShowTime >= promotionDayTimes){
        DLog(@"[adlog] StartPromotion today show times: 【%d】 more than define: 【%d】",todayShowTime,promotionDayTimes);
        return false;
    }
    
    tmp = (NSNumber *)[[SDKCache cache] objectForKey:SDK_START_PROMOTION_SHOW_TIMESTAMP];
    long lastShowTime = tmp ? tmp.longValue : 0;
    long now = [[NSDate date] timeIntervalSince1970];
    long interval = now - lastShowTime;
    if(interval < promotionInterval){
        DLog(@"[adlog] StartPromotion sinterval: 【%ld】 between: 【%ld】 and 【%ld】 less than define interval: 【%d】", interval,lastShowTime,now,promotionInterval);
        return false;
    }
    return true;
}

-(void) recordStartShow{
    NSString* todayShowKey = [self getTodayShowKey];
    NSNumber* tmp =  (NSNumber *)[[SDKCache cache] objectForKey:todayShowKey];
    int todayShowTime = tmp ? tmp.intValue : 0;
    [[SDKCache cache] setObject:[NSNumber numberWithInt:(todayShowTime + 1)] forKey: todayShowKey withTTL:86400];
    [[SDKCache cache] setObject:[NSNumber numberWithLong:[[NSDate date] timeIntervalSince1970]] forKey:SDK_START_PROMOTION_SHOW_TIMESTAMP];
}

-(NSString *)getTodayShowKey
{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"yyyyMMdd"];
    NSDate *currentDate = [NSDate date];
    NSString *currentDateString = [formatter stringFromDate:currentDate];
    return [NSString stringWithFormat:SDK_START_PROMOTION_TODAY_SHOW_TIMESTAMP, currentDateString];
}

@end
