//
//  SMAConfigurationSettings+MRAID.h
//  SmaatoSDKRichMedia
//
//  Created by Smaato Inc on 08.11.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <SmaatoSDKCore/SMAConfigurationSettings.h>
#import <SmaatoSDKRichMedia/SMAMRAIDSettings.h>

@interface SMAConfigurationSettings (MRAID)

@property (class, nonatomic, readonly) SMAMRAIDSettings *mraidSettings;

@end
