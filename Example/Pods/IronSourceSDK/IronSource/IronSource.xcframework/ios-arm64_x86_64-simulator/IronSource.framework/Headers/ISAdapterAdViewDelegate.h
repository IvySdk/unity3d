//
//  ISAdapterAdViewDelegate.h
//  IronSource
//
//  Created by Guy Lis on 27/03/2023.
//  Copyright © 2023 IronSource. All rights reserved.
//

#ifndef ISAdapterAdViewDelegate_h
#define ISAdapterAdViewDelegate_h

#import "ISAdapterAdDelegate.h"
#import <UIKit/UIKit.h>

@protocol ISAdapterAdViewDelegate <ISAdapterAdDelegate>

// mandatory callbacks

/**
  * @param view the view that was loaded
  */
- (void)adDidLoadWithView:(UIView *) view;


// optional callbacks (must be implemented in the adapter but can have empty implementation)

/**
  * This method should be invoked before the user is taken out of the application after a click
  */
- (void)adWillLeaveApplication;

/**
  * This method should be invoked after the ad view presents fullscreen content
  */
- (void)adWillPresentScreen;

/**
  * This method should be invoked after the fullscreen content is dismissed
  */
- (void)adDidDismissScreen;

@end


#endif /* ISAdapterAdViewDelegate_h */
