//
//  SDKDeliciousIconView.h
//  Bolts
//
//  Created by 余冰星 on 2018/6/10.
//

#import <UIKit/UIKit.h>
@protocol SDKDeliciousIconDelegate
@optional
-(void)onIconClick:(NSInteger)index;
@end
@interface SDKDeliciousIconView : UIView
-(nonnull instancetype)initWithConfig:(NSDictionary *_Nullable)conf;
@property (nonatomic, strong, nullable) NSArray *adDatas;
@property (nonatomic, strong, nullable) id<SDKDeliciousIconDelegate> iconDelegate;
@property (nonatomic, assign) CGFloat iconWidth;
@property (nonatomic, assign) CGFloat iconHeight;
@property (nonatomic, assign) CGFloat cornerRadius;
@property (nonatomic, assign) CGFloat offsetX;
@property (nonatomic, assign) CGFloat offsetY;
@property (nonatomic, assign) BOOL enableAnimation;
@property (nonatomic, assign) BOOL autoRotateIcons;
@property (nonatomic, assign) NSInteger currentIconIndex;
-(void)addTo:(nonnull UIView *)view frame:(CGRect)frame;
-(void)removeSelf;
@end
