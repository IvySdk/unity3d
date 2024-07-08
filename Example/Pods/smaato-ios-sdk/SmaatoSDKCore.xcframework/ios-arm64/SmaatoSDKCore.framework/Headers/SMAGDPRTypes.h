//
//  SMAGDPRTypes.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 4/26/18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#ifndef SMAGDPRTypes_h
#define SMAGDPRTypes_h

/**
 |      Field          |   Purpose #1  |   Purpose #3  |
 |  gps                |                        |        X            |
 |  ifa          |          X            |        X            |
 |  fcid                |          X            |        X            |
 |  iosadtracking |         X            |                      |
 |  gender           |                       |        X            |
 |  age                |                       |        X            |
 |  zip                  |                       |        X           |
 |  devicemodel  |                       |        X            |
 */
typedef NS_ENUM(NSInteger, SMAAdPrivateData) {
    kSMAAdPrivateDataGpsCoordinates,                 // gps
    kSMAAdPrivateDataAdvertisingIdentifier,          // ifa
    kSMAAdPrivateDataFcid,                           // fcid
    kSMAAdPrivateDataUserGender,                     // gender
    kSMAAdPrivateDataUserAge,                        // age
    kSMAAdPrivateDataZipCode,                        // zip
    kSMAAdPrivateDataDeviceModel,                    // devicemodel
    kSMAAdPrivateDataAdvertisingTrackingEnabled,     // iosadtracking
    kSMAAdPrivacyDataPublisherProvidedIdentifier,    // ppid
    kSMAAdPrivacyDataIdentifierForVendor             // idfv
};

typedef NS_ENUM(NSInteger, SMASubjectToGDPR) {
    /// Unknown
    SMASubjectToGDPR_Unknown = -1,

    /// Is not subject to GDPR
    SMASubjectToGDPR_No = 0,

    /// Is subject to GDPR
    SMASubjectToGDPR_Yes = 1
};

static NSErrorDomain const SMAGDPRErrorDomain = @"SMAAdErrorDomain";

typedef NS_ERROR_ENUM(SMAGDPRErrorDomain, SMAGDPRErrorCode){ /// Not authorized
                                                             kSMAGDPRErrorCodeNotAuthorized = 0
};

typedef NS_ENUM(NSInteger, SMARestrictionType) {
    /// Purpose Flatly Not Allowed by Publisher (regardless of Vendor declarations)
    SMARestrictionTypeNotAllowed = 0,

    /// If Vendor has declared the Purpose IDs legal basis as Legitimate Interest and flexible
    SMARestrictionTypeRequiredConsent = 1,

    /// If Vendor has declared the Purpose IDs legal basis as Consent and flexible
    SMARestrictionTypeRequiredLI = 2,

    // Not used
    SMARestrictionTypeUndefined = 3,
};

/**
 * Known vendor identifiers.
 */
typedef NS_ENUM(NSInteger, SMAGDPRVendor) {
    /// Smaato vendor ID.
    kSMAGDPRVendorSmaato = 82,
};

/**
 * Consent purposes as defined by IAB CMP reference implementation.
 */
typedef NS_ENUM(NSInteger, SMAGDPRPurpose) {

    /**
     * The storage of information, or access to information that is already stored, on your device such as
     * advertising identifiers, device identifiers, cookies, and similar technologies.
     */
    kSMAGDPRPurposeInformationStorageAndAccess = 1,

    /**
     * The collection and processing of information about your use of this service to subsequently personalise
     * advertising and/or content for you in other contexts, such as on other websites or apps, over time.
     * Typically, the content of the site or app is used to make inferences about your interests, which inform
     * future selection of advertising and/or content.
     */
    kSMAGDPRPurposePersonalisation = 2,

    /**
     * The collection of information, and combination with previously collected information, to select
     * and deliver advertisements for you, and to measure the delivery and effectiveness of such advertisements.
     * This includes using previously collected information about your interests to select ads, processing data
     * about what advertisements were shown, how often they were shown, when and where they were shown,
     * and whether you took any action related to the advertisement, including for example clicking an ad or
     * making a purchase. This does not include personalisation, which is the collection and processing of information
     * about your use of this service to subsequently personalise advertising and/or content for you in other contexts,
     * such as websites or apps, over time.
     */
    kSMAGDPRPurposeAdSelectionDeliveryReporting = 3,

    /**
     * The collection of information, and combination with previously collected information, to select and
     * deliver content for you, and to measure the delivery and effectiveness of such content. This includes using
     * previously collected information about your interests to select content, processing data about what
     * content was shown, how often or how long it was shown, when and where it was shown, and whether the you took
     * any action related to the content, including for example clicking on content. This does not include
     * personalisation, which is the collection and processing of information about your use of this service to
     * subsequently personalise content and/or advertising for you in other contexts, such as websites or apps,
     * over time.
     */
    kSMAGDPRPurposeContentSelectionDeliveryReporting = 4,

    /**
     * The collection of information about your use of the content, and combination with previously collected
     * information, used to measure, understand, and report on your usage of the service. This does not include
     * personalisation, the collection of information about your use of this service to subsequently personalise
     * content and/or advertising for you in other contexts, i.e. on other service, such as websites or apps, over time.
     */
    kSMAGDPRPurposeMeasurement = 5,
};

/**
 * Known vendor identifiers.
 */
typedef NS_ENUM(NSInteger, SMAGDPRSpecialFeatures) {
    /// Smaato vendor ID.
    SMAGDPRSpecialFeaturesUsePreciseGeolocationData = 1,
    SMAGDPRSpecialFeaturesActivelyScanDeviceCharacteristicsForIdentification = 2,
};

/**
 * Consent purposes as defined by IAB CMP reference implementation for TCFv2.
 */
typedef NS_ENUM(NSInteger, SMAGDPRPurposeV2) {
    /**
     Cookies, device identifiers, or other information can be stored or accessed on your device for the purposes presented to you.
     */
    SMAGDPRPurposeV2StoreAndAccess = 1,

    /**
     Ads can be shown to you based on the content you’re viewing, the app you’re using, your approximate location, or your device type
     */
    SMAGDPRPurposeV2SelectBasisAds = 2,

    /**
     A profile can be built about you and your interests to show you personalised ads that are relevant to you
     */
    SMAGDPRPurposeV2PersonalisedAdsProfile = 3,

    /**
     Personalised ads can be shown to you based on a profile about you
     */
    SMAGDPRPurposeV2SelectPersonalisedAds = 4,

    /**
     A profile can be built about you and your interests to show you personalised content that is relevant to you
     */
    SMAGDPRPurposeV2CreatePersonalisedContentProfile = 5,

    /**
     Personalised content can be shown to you based on a profile about you
     */
    SMAGDPRPurposeV2SelectPersonalisedContent = 6,

    /**
     The performance and effectiveness of ads that you see or interact with can be measured
     */
    SMAGDPRPurposeV2MeasureAndPerformance = 7,

    /**
     The performance and effectiveness of content that you see or interact with can be measured
     */
    SMAGDPRPurposeV2MeasureContentPerformance = 8,

    /**
     Market research can be used to learn more about the audiences who visit sites/apps and view ads
     */
    SMAGDPRPurposeV2ApplyMarketResearch = 9,

    /**
     Your data can be used to monitor for and prevent fraudulent activity, and ensure systems and processes work properly and securely
     */
    SMAGDPRPurposeV2DevelopAndImproveProducts = 10,
};

#endif /* SMAGDPRTypes_h */
