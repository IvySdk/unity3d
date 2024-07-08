//
//  SDKOurNativeView.h
//  Bolts
//
//  Created by 余冰星 on 2017/12/28.
//

#import <IvyiOSSdk/SDKNativeView.h>

@interface SDKOurNativeView : SDKNativeView
@property (weak, nonatomic) IBOutlet UIButton *closeButton;
@property (weak, nonatomic) IBOutlet UIImageView *bgImageView;
@property (weak, nonatomic) IBOutlet UIImageView *adCoverMediaView;
@property (weak, nonatomic) IBOutlet UILabel *adBodyLabel;
@property (weak, nonatomic) IBOutlet UIButton *adCallToActionButton;
@property (weak, nonatomic) IBOutlet UILabel *adTitleLabel;
@property (weak, nonatomic) IBOutlet UIImageView *adIconImageView;
@property (weak, nonatomic) IBOutlet UIImageView *adCornerView;

@end
