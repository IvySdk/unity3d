//
//  ISOfferwallAdapterDelegate.h
//  IronSource
//
//  Created by Gili Ariel on 8/1/15.
//  Copyright (c) 2014 IronSource. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol ISOfferwallAdapterDelegate <NSObject>

- (void)adapterOfferwallHasChangedAvailability:(BOOL)available withError:(NSError *)error;

- (void)adapterOfferwallDidShow;

- (void)adapterOfferwallDidFailToShowWithError:(NSError *)error;

- (void)adapterOfferwallDidClose;

- (BOOL)adapterOfferwallDidReceiveCredits:(NSDictionary *)creditInfo;

- (void)adapterOfferwallDidFailToReceiveCreditsWithError:(NSError *)error;

@end
