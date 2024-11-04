//
//  ISAdapterAdInteractionDelegate.h
//  IronSource
//
//  Created by Bar David on 21/10/2021.
//  Copyright © 2021 IronSource. All rights reserved.
//

#ifndef ISAdapterAdInteractionDelegate_h
#define ISAdapterAdInteractionDelegate_h

#import "ISAdapterAdDelegate.h"

NS_ASSUME_NONNULL_BEGIN

@protocol ISAdapterAdInteractionDelegate <ISAdapterAdDelegate>

// Mandatory callbacks

- (void)adDidClose;

// Optional callbacks

- (void)adDidShowSucceed;

- (void)adDidBecomeVisible;

- (void)adDidStart;

- (void)adDidEnd;

@end

NS_ASSUME_NONNULL_END

#endif /* ISAdapterAdInteractionDelegate_h */
