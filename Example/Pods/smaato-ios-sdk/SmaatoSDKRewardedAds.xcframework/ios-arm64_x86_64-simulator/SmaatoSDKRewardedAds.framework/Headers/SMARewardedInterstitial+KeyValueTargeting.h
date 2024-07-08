//
//  SMARewardedInterstitial+KeyValueTargeting.h
//  SmaatoSDKRewardedAds
//
//  Created by Smaato Inc on 13/06/2019.
//  Copyright © 2019 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <SmaatoSDKRewardedAds/SMARewardedInterstitial.h>

@interface SMARewardedInterstitial (KeyValueTargeting)

/**
 Object keeping key-value pairs to target more specific ads.
 Can be added up to 7 key value pairs and each key can have up to 7 values as well.
 Key-value information is not PII and therefore it won't be stripped down for COPPA and GDPR purposes.
 */
@property (nonatomic, nullable, class) SMAKeyValuePairs *keyValuePairs;

@end
