//
//  SMAConsentParser.h
//  SmaatoSDKCore
//
//  Created by Beniamin Muntean on 26/02/2020.
//  Copyright © 2020 Smaato Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface SMAConsentParser: NSObject

+ (NSNumber *)parseConsentVersionFrom:(NSString *)consentString;
+ (NSString *)parseCreatedFrom:(NSString *)consentString;
+ (NSString *)parseUpdatedFrom:(NSString *)consentString;
+ (NSNumber *)parseCMPIDFrom:(NSString *)consentString;
+ (NSNumber *)parseCMPVersionFrom:(NSString *)consentString;
+ (NSNumber *)parseConsentScreenFrom:(NSString *)consentString;
+ (NSString *)parseConsentLanguageFrom:(NSString *)consentString;
+ (NSNumber *)parseVendorListVersionFrom:(NSString *)consentString;
+ (NSNumber *)parseTcfPolicyVersionFrom:(NSString *)consentString;
+ (BOOL)parseIsServerSpecificFrom:(NSString *)consentString;
+ (BOOL)parseUseNonStandardStacksFrom:(NSString *)consentString;
+ (NSString *)parseSpecialFeatureOptInsFrom:(NSString *)consentString;
+ (NSString *)parsePurposesConsentFrom:(NSString *)consentString;
+ (NSString *)parsePurposesLITransparencyFrom:(NSString *)consentString;
+ (NSNumber *)parsePurposeOneTreatmentFrom:(NSString *)consentString;
+ (NSString *)parsePublisherCCFrom:(NSString *)consentString;
+ (NSString *)parseVendorConsentsFrom:(NSString *)consentString;
+ (NSString *)parseVendorConsentLiFrom:(NSString *)consentString;
+ (NSArray *)parsePublisherRestrictionsFrom:(NSString *)consentString;

@end

NS_ASSUME_NONNULL_END
