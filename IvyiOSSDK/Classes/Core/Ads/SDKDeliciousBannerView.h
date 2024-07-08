//
//  SDKDeliciousBannerView.h
//  Bolts
//
//  Created by 余冰星 on 2018/6/8.
//

#import <UIKit/UIKit.h>
@protocol SDKDeliciousBannerDelegate
@optional
-(void)onDeliciousIconClick:(NSInteger)index;
@end
@interface SDKDeliciousBannerIconView : UIView
-(nonnull instancetype)initWithData:(NSDictionary *_Nullable)data;
@property (nonatomic, strong, nullable) NSURL *url;
@property (nonatomic, assign) BOOL select;
@property (nonatomic, assign) BOOL enableSelectedAnimation;
@property (nonatomic, assign) CGFloat cornerRadius;
@property (nonatomic, assign) CGFloat iconWidth;
@property (nonatomic, assign) CGFloat iconHeight;
@property (nonatomic, assign) CGFloat iconFrameWidth;
@property (nonatomic, assign) CGFloat iconFrameHeight;
@property (nonatomic, assign) CGFloat offsetX;
@property (nonatomic, assign) CGFloat offsetY;
@end

@interface SDKDeliciousBannerView : UIView
@property (nonatomic, strong, nullable) NSArray *adDatas;
@property (nonatomic, strong, nullable) id<SDKDeliciousBannerDelegate> bannerDelegate;
@property (nonatomic, assign) CGFloat paddingLeft;
@property (nonatomic, assign) CGFloat paddingRight;
@property (nonatomic, assign) CGFloat paddingUp;
@property (nonatomic, assign) CGFloat iconSpace;
@property (nonatomic, assign) BOOL jumpToVideo;
@property (nonatomic, assign) BOOL iconSelectAnimation;
@property (nonatomic, assign, readonly) NSInteger iconCount;
@property (nonatomic, assign, readonly) NSInteger currentSelectIndex;

-(nonnull instancetype)initWithConfig:(NSDictionary *_Nullable)conf;
-(void)reloadData;
-(void)select:(NSInteger)index;
@end
