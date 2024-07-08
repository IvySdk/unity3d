//
//  SDKConstants.h
//  Pods
//
//  Created by 余冰星 on 2017/6/12.
//
//

#import <Foundation/Foundation.h>
#define isPad (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
@interface SDKConstants : NSObject
extern NSString * _Nonnull const SDK_BANNER_CLOSE_NOTIF;
extern NSString * _Nonnull const SDK_SNS_LOGIN_SUCCESS;
//extern NSString * _Nonnull const SDK_SNS_MEDETAILS;
extern NSString * _Nonnull const SDK_SNS_FRIENDS;
extern NSString * _Nonnull const SDK_FIRST_OPEN;
extern NSString * _Nonnull const SDK_SYS_RATE_COUNT;
extern NSString * _Nonnull const SDK_ADS_ENABLE;
extern NSString * _Nonnull const SDK_AD_PERSONALIZED;
extern NSString * _Nonnull const SDK_HAS_GDPR;
extern NSString * _Nonnull const SDK_PUSH_DATA;
extern NSString * _Nonnull const SDK_REMOTE_CONFIG;
extern NSString * _Nonnull const SDK_FIREBASE_USERID;

extern NSString * _Nonnull const SDK_ADTAG_DEFAULT;
extern NSString * _Nonnull const SDK_ADTAG_START;
extern NSString * _Nonnull const SDK_ADTAG_PAUSE;
extern NSString * _Nonnull const SDK_ADTAG_CUSTOM;
extern NSString * _Nonnull const SDK_ADTAG_FALLBACK;

extern NSString * _Nonnull const SDK_ADTAG_INTERTITIAL;
extern NSString * _Nonnull const SDK_ADTAG_BANNER;
extern NSString * _Nonnull const SDK_ADTAG_VIDEO;
extern NSString * _Nonnull const SDK_ADTAG_ICON;
extern NSString * _Nonnull const SDK_ADTAG_NATIVE;
extern NSString * _Nonnull const SDK_ADTAG_GIFICON;
extern NSString * _Nonnull const SDK_ADTAG_DOWN_ICON;
extern NSString * _Nonnull const SDK_ADTAG_DELICIOUS_BANNER;

extern NSString * _Nonnull const GC_UNAVAILABLE;
extern NSString * _Nonnull const GC_UNAVAILABLE_MSG;
extern NSString * _Nonnull const INTERNET_UNAVAILABLE;
extern NSString * _Nonnull const INTERNET_UNAVAILABLE_MSG;
extern NSString * _Nonnull const UNAUTHENTICATED;
extern NSString * _Nonnull const UNAUTHENTICATED_MSG;
extern NSString * _Nonnull const SDK_NOTICE_UPDATE_TITLE;
extern NSString * _Nonnull const SDK_NOTICE_UPDATE_DESC;
extern NSString * _Nonnull const SDK_NOTICE_UPDATE_CONFIRM;
extern NSString * _Nonnull const SDK_NOTICE_UPDATE_CANCEL;
extern NSString * _Nonnull const SDK_NOTICE_UPDATE_OK;

extern NSString * _Nonnull const SDK_VERIFY_AGE;
extern NSString * _Nonnull const SDK_VERIFY_NAME;
extern NSString * _Nonnull const SDK_VERIFY_IDCARD;
extern NSString * _Nonnull const SDK_HAS_START_VERIFY;
extern NSString * _Nonnull const SDK_DEMO_TIMER;
extern NSString * _Nonnull const SDK_DEMO_LAST_STARTTIME;
extern NSString * _Nonnull const SDK_DEMO_LEFTSECOND;
extern NSString * _Nonnull const SDK_DAILY_PAY;
extern NSString * _Nonnull const SDK_MONTH_PAY;
extern NSString * _Nonnull const SDK_UUID;
extern NSString * _Nonnull const SDK_CROSS_PROMOTED_CAMPAIGN;

extern NSString * _Nonnull const SDK_ANALYSE_FIREBASE;
extern NSString * _Nonnull const SDK_ANALYSE_FACEBOOK;
extern NSString * _Nonnull const SDK_ANALYSE_APPSFLYER;
extern NSString * _Nonnull const SDK_ANALYSE_UMENG;
extern NSString * _Nonnull const SDK_ANALYSE_IVY;
extern NSString * _Nonnull const SDK_ANALYSE_GA;
extern NSString * _Nonnull const SDK_ANALYSE_TOUTIAO;

typedef NS_OPTIONS(NSUInteger, SDK_ADTYPE) {
    SDK_ADTYPE_INTERTITIAL = 1,
    SDK_ADTYPE_VIDEO = 2,
    SDK_ADTYPE_BANNER = 3,
    SDK_ADTYPE_ICON = 4,
    SDK_ADTYPE_NATIVE = 5,
    SDK_ADTYPE_GIFICON = 6
};

typedef NS_OPTIONS(NSUInteger, SDK_NATIVE_ADTYPE) {
    SDK_NATIVE_ALL = 1,
    SDK_NATIVE_CONTENT = 2,
    SDK_NATIVE_APPINSTALL = 3
};

typedef NS_OPTIONS(NSUInteger, SDK_CONFIG_ID) {
    SDK_CONFIG_KEY_APP_ID = 1,
    SDK_CONFIG_KEY_LEADER_BOARD_URL = 2,
    SDK_CONFIG_KEY_API_VERSION = 3,
    SDK_CONFIG_KEY_SCREEN_WIDTH = 4,
    SDK_CONFIG_KEY_SCREEN_HEIGHT = 5,
    SDK_CONFIG_KEY_LANGUAGE = 6,
    SDK_CONFIG_KEY_COUNTRY = 7,
    SDK_CONFIG_KEY_VERSION_CODE = 8,
    SDK_CONFIG_KEY_VERSION_NAME = 9,
    SDK_CONFIG_KEY_PACKAGE_NAME = 10,
    SDK_CONFIG_KEY_UUID = 11,
    SDK_CONFIG_KEY_FACEBOOK_ID = 12,
    SDK_CONFIG_KEY_CHANNEL = 20,
    SDK_CONFIG_KEY_JSON_VERSION = 21,
    SDK_CONFIG_KEY_FIREBASE_USERID = 22
};

typedef NS_OPTIONS(NSUInteger, SDK_BANNER_POS) {
    SDK_BANNER_POS_LEFT_TOP = 1,
    SDK_BANNER_POS_LEFT_BOTTOM = 2,
    SDK_BANNER_POS_TOP = 3,
    SDK_BANNER_POS_BOTTOM = 4,
    SDK_BANNER_POS_CENTER = 5,
    SDK_BANNER_POS_RIGHT_TOP = 6,
    SDK_BANNER_POS_RIGHT_BOTTOM = 7,
    SDK_BANNER_POS_LEFT_CENTER = 8,
    SDK_BANNER_POS_RIGHT_CENTER = 9
};

typedef NS_OPTIONS(NSUInteger, SDK_INTERVAL) {
    SDK_INTERVAL_YEAR = (1UL << 2),
    SDK_INTERVAL_MONTH = (1UL << 3),
    SDK_INTERVAL_DAY = (1UL << 4),
    SDK_INTERVAL_HOUR = (1UL << 5),
    SDK_INTERVAL_MINUTE = (1UL << 6),
    SDK_INTERVAL_SECOND = (1UL << 7),
    SDK_INTERVAL_WEEKDAY = (1UL << 9),
    SDK_INTERVAL_WEEKDAYORDINAL = (1UL << 10)
};
typedef NS_ENUM(NSInteger, SDK_ORIENTATION) {
    SDK_ORIENTATION_ANY = 1,       ///< No orientation preference.
    SDK_ORIENTATION_PORTRAIT = 2,  ///< Prefer portrait images.
    SDK_ORIENTATION_LANDSCAPE = 3  ///< Prefer landscape images.
};
/// Leaderboard sort order. Use this value when submitting new leaderboard scores. This value should match the value set in iTunes Connect for the speicifed leaderboard.
typedef enum GameCenterSortOrder {
    /// Scores are sorted highest to lowest. Higher scores are on the top of the leaderboard
    GameCenterSortOrderHighToLow,
    /// Scores are sorted lowest to highest. Lower scores are on the top of the leaderboard
    GameCenterSortOrderLowToHigh
} GameCenterSortOrder;

enum {
    /// An unknown error occurred
    GCMErrorUnknown = 1,
    /// SDKGCManager is unavailable, possibly for a variety of reasons
    GCMErrorNotAvailable = 2,
    /// The requested feature is unavailable on the current device or iOS version
    GCMErrorFeatureNotAvailable = 3,
    /// There is no active internet connection for the requested operation
    GCMErrorInternetNotAvailable = 4,
    /// The achievement data submitted was not valid because there were missing parameters
    GCMErrorAchievementDataMissing = 5,
    /// The multiplayer data could not be sent with the specified connection type because it was too large
    GCMErrorMultiplayerDataPacketTooLarge = 6
};
/// SDKGCManager error codes that may be passed in a completion handler's error parameter
typedef NSInteger GCMErrorCode;

/// GameCenter availability status. Use these statuss to identify the state of GameCenter's availability.
typedef enum GameCenterAvailability {
    /// GameKit Framework not available on this device
    GameCenterAvailabilityNotAvailable,
    /// Cannot connect to the internet
    GameCenterAvailabilityNoInternet,
    /// Player is not yet signed into GameCenter
    GameCenterAvailabilityNoPlayer,
    /// Player is not signed into GameCenter, has declined to sign into GameCenter, or GameKit had an issue validating this game / app
    GameCenterAvailabilityPlayerNotAuthenticated,
    /// Player is signed into GameCenter
    GameCenterAvailabilityPlayerAuthenticated
} GameCenterAvailability;

typedef NS_CLOSED_ENUM(NSUInteger, SDK_MEDIATION_TYPE) {
    SdkMediationNetworkTypeGoogleAdMob = 1,
    SdkMediationNetworkTypeIronSource = 2,
    SdkMediationNetworkTypeApplovinMax= 3,
    SdkMediationNetworkTypeFyber = 4,
    SdkMediationNetworkTypeAppodeal = 5,
    SdkMediationNetworkTypeAdmost = 6,
    SdkMediationNetworkTypeTopon = 7,
    SdkMediationNetworkTypeTradplus = 8,
    SdkMediationNetworkTypeYandex = 9,
    SdkMediationNetworkTypeChartBoost = 10,
    SdkMediationNetworkTypeUnity = 11,
    SdkMediationNetworkTypeCustom = 12
} NS_SWIFT_NAME(MediationNetworkType);

+ (nonnull NSString*) getAdTypeName:(SDK_ADTYPE)type;
@end
