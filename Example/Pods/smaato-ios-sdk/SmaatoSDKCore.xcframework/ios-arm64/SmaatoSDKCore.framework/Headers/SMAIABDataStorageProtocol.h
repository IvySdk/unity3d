//
//  SMAIABDataStorageProtocol.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 13.04.18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>
#import <SmaatoSDKCore/SMAGDPRTypes.h>

/// GDPR
#pragma mark - GDPR section

@protocol SMAGDPRDataStorageProtocol <NSObject>
@required

/**
 The consent string passed as a websafe base64-encoded string.
 */
@property (nonatomic) NSString *consentString;

/**
 Enum that indicates    'SubjectToGDPR_Unknown'- value -1, unset.
 'SubjectToGDPR_No' – value 0, not subject to GDPR
 'SubjectToGDPR_Yes' – value 1, subject to GDPR,
 */
@property (nonatomic) SMASubjectToGDPR subjectToGDPR;

/**
 Consent version number : 1 or 2
 */
@property (nonatomic) NSNumber *consentVersion;

/**
String representing the date when TC String was first created
*/
@property (nonatomic) NSString *consentCreatedDate;

/**
 String representing the date when TC String was last updated
 */
@property (nonatomic) NSString *consentUpdatedDate;

/**
 Number representing the ID of CMP SDK
 */
@property (nonatomic) NSNumber *cmpID;

/**
 Number representing the version number of CMP SDK
 */
@property (nonatomic) NSNumber *cmpVersion;

/**
 CMP Screen number at which consent was given for a user with the CMP that last updated this TC String
 */
@property (nonatomic) NSNumber *consentScreen;

/**
 Two-letter ISO 639-1 language code in which the CMP UI was presented
 */
@property (nonatomic) NSString *consentLanguage;

/**
 Version of the GVL used to create this TC String.
 */
@property (nonatomic) NSNumber *vendorListVersion;

/**
 Number the version of the TCF that these consents adhere to.
 */
@property (nonatomic) NSNumber *tcfPolicyVersion;

/**
 Whether the signals encoded in this TC String were from service-specific storage (YES) versus ‘global’ consensu.org shared storage (NO).
 */
@property (nonatomic) BOOL isServerSpecific;

/**
 YES - CMP used a non-standard stack
 NO - CMP did not use a non-standard stack
 */
@property (nonatomic) BOOL useNonStandardStacks;

/**
 String that contains the consent information for all special features.
 */
@property (nonatomic) NSString *specialFeatureOptIns;

/**
 String that contains the consent information for all purposes.
 */
@property (nonatomic) NSString *purposeConsents;

/**
String that contains the consent information for all Legitimate Interest purposes.
*/
@property (nonatomic) NSString *purposesLITransparency;

/**
 0 - no special treatment of purpose one

 1 - purpose one not disclosed

 Unset default - 0

 Vendors can use this value to determine whether consent for purpose one is required.
 */
@property (nonatomic) NSNumber *purposeOneTreatment;

/**
 Two-letter ISO 3166-1 alpha-2 code – Default: AA (unknown)
 */
@property (nonatomic) NSString *publisherCC;

/**
 String that contains the consent information for all vendors.
 */
@property (nonatomic) NSString *vendorConsents;

/**
 String that contains the consent information for all vendors legitimate interest.
 */
@property (nonatomic) NSString *vendorConsentLi;

/**
 String that contains the consent information for all vendors legitimate interest.
 */
@property (nonatomic) NSArray *publisherRestrictions;

/**
 Boolean that indicates if a CMP implementing the iAB specification is present in the application
 */
@property (nonatomic) BOOL cmpPresent;

/**
Method which parses the consent string and initializes the parameters
*/
- (void)parseConsentString:(NSString *)consentString;

@end

/// CCPA
#pragma mark - CCPA section

@protocol SMACCPAStorageProtocol <NSObject>
@required

/**
 Encoded privacy string based on CCPA.
 */
@property (nonatomic) NSString *usPrivacyString;

@end

/// GPP
#pragma mark - GPP section

@protocol SMAGPPStorageProtocol <NSObject>
/**
 Encoded GPP String.
 */
@property (nonatomic) NSString *gppString;

/**
 Encoded GPP SectionID/s.
 */
@property (nonatomic) NSString *gppSid;

@end

/// Common iAB legal shared data protocol

@protocol SMAIABDataStorageProtocol <SMAGDPRDataStorageProtocol, SMACCPAStorageProtocol>

@end
