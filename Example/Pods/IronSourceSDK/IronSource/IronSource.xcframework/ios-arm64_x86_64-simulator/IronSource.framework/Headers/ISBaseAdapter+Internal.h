
//
//  ISBaseAdapter+Internal.h
//  IronSource
//
//  Created by Yotam Ohayon on 01/03/2016.
//  Copyright © 2016 IronSource. All rights reserved.
//

#ifndef ISBaseAdapter_Internal_h
#define ISBaseAdapter_Internal_h

#import "ISBaseAdapter.h"

#import "ISRewardedVideoAdapterDelegate.h"
#import "ISInterstitialAdapterDelegate.h"
#import "ISBannerAdapterDelegate.h"
#import "ISOfferwallAdapterDelegate.h"
#import "ISNativeAdAdapterDelegate.h"

#import "ISConfigurations.h"
#import "ISMetaDataUtils.h"
#import "ISMetaDataConstants.h"

#import "ISError.h"
#import "ISConcurrentMutableSet.h"
#import "ISConcurrentMutableDictionary.h"
#import "ISNetworkInitCallbackProtocol.h"
#import "ISLog.h"

#import "ISBaseRewardedVideoAdapter.h"
#import "ISBaseInterstitialAdapter.h"
#import "ISBaseBannerAdapter.h"
#import "ISBaseNativeAdAdapter.h"

#import "ISAdapterNativeAdData.h"
#import "ISAdapterNativeAdViewBinder.h"
#import "ISNativeAdViewHolder.h"
#import "UIView+ISNativeView.h"

@interface ISBaseAdapter ()

#pragma mark - Validation
- (BOOL)isConfigValueValid:(NSString *)value;
- (NSError *)errorForMissingCredentialFieldWithName:(NSString *)fieldName;
    
#pragma mark - Private
- (UIViewController *) topMostController;
- (void)setConsent:(BOOL)consent;
- (void)setMetaDataWithKey:(NSString *)key
                 andValues:(NSMutableArray *)values;

@end

#endif /* ISBaseAdapter_Internal_h */

