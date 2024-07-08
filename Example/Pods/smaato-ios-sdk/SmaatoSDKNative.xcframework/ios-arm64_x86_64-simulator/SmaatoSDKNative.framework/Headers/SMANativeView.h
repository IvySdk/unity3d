//
//  SMANativeView.h
//  SmaatoSDKNative
//
//  Created by Smaato Inc on 10.03.20.
//  Copyright © 2020 Smaato Inc. All rights reserved.
//

#import <UIKit/UIKit.h>
@protocol SMANativeView <NSObject>

@optional
/**
Optional UILabel control for sponsored label rendering. Apple HIG requires to mark the view that contains
ad content. Might be \c nil if you care about ad marking yourself.
*/
@property (nonatomic) UILabel *smaSponsoredLabel;

/**
Optional UILabel control for native ad main text (description) rendering. Might be \c nil.
*/
@property (nonatomic) UILabel *smaMainTextLabel;

/**
Optional UILabel control for title text rendering. Might be \c nil.
*/
@property (nonatomic) UILabel *smaTitleLabel;

/**
Optional UIView placeholder for native ad icon creative rendering. Might be \c nil.
*/
@property (nonatomic) UIView *smaIconView;

/**
Optional UIView placeholder for native ad main image creative or video content rendering only. This \c smaMediaView acts as view container
(superview) for specified view with media content. Might be \c nil.
*/
@property (nonatomic) UIView *smaMediaView;

/**
Optional UILabel control for CTA (Click-To-Action) text rendering. Might be \c nil.
*/
@property (nonatomic) UILabel *smaCTALabel;

/**
Optional UIView placeholder for native ad rating rendering. This \c smaRatingView acts as view container (superview) for specified view with
rendered rating. Might be \c nil.
*/
@property (nonatomic) UIView *smaRatingView;

/**
Optional UIView placeholder for Ad Network privacy icon rendering. Tap on \c smaPrivacyView follows to web page that contains Privacy Poicy
of given Ad Network, which provided this ad. Expected size of view is 16*16 pixels, Might be \c nil.
*/
@property (nonatomic) UIView *smaPrivacyView;

@end
