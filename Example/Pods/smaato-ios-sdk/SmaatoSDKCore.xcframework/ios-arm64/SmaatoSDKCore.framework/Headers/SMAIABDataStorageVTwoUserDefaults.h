//
//  SMAIABDataStorageVTwoUserDefaults.h
//  SmaatoSDKCore
//
//  Created by muhammad on 22.04.20.
//  Copyright © 2020 Smaato Inc. All rights reserved.
//

#import "SMAIABDataStorageProtocol.h"

@interface SMAIABDataStorageVTwoUserDefaults: NSObject <SMAGDPRDataStorageProtocol,SMACCPAStorageProtocol, SMAGPPStorageProtocol>

+ (instancetype)sharedInstance;

- (void)parseConsentString:(NSString *)consentString;

@end
