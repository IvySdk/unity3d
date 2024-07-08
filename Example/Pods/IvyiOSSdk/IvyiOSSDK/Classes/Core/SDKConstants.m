//
//  SDKConstants.m
//  Pods
//
//  Created by 余冰星 on 2017/6/12.
//
//

#import "SDKConstants.h"

@implementation SDKConstants
NSString * const SDK_BANNER_CLOSE_NOTIF = @"SDK_BANNER_CLOSE";
NSString * const SDK_SNS_LOGIN_SUCCESS = @"sns_login_success";
//NSString * const SDK_SNS_MEDETAILS = @"medetails";
NSString * const SDK_SNS_FRIENDS = @"friends";
NSString * const SDK_FIRST_OPEN = @"fst";
NSString * const SDK_SYS_RATE_COUNT = @"sysratecount";
NSString * const SDK_ADS_ENABLE = @"ADS_ENABLE";
NSString * const SDK_AD_PERSONALIZED = @"adpersonalized";
NSString * const SDK_HAS_GDPR = @"hasgdpr";
NSString * const SDK_PUSH_DATA = @"pushdata";
NSString * const SDK_REMOTE_CONFIG = @"remoteconfig";
NSString * const SDK_FIREBASE_USERID = @"firebase_userid";

NSString * const SDK_ADTAG_START = @"start";
NSString * const SDK_ADTAG_PAUSE = @"pause";
NSString * const SDK_ADTAG_CUSTOM = @"custom";
NSString * const SDK_ADTAG_DEFAULT = @"default";
NSString * const SDK_ADTAG_FALLBACK = @"fallback";

NSString * const SDK_ADTAG_INTERTITIAL = @"cover";
NSString * const SDK_ADTAG_BANNER = @"banner";
NSString * const SDK_ADTAG_VIDEO = @"video";
NSString * const SDK_ADTAG_ICON = @"icon";
NSString * const SDK_ADTAG_NATIVE = @"native";
NSString * const SDK_ADTAG_GIFICON = @"gificon";
NSString * const SDK_ADTAG_DOWN_ICON = @"down";
NSString * const SDK_ADTAG_DELICIOUS_BANNER = @"delicious_banner";

NSString * const GC_UNAVAILABLE = @"GameCenter Unavailable";
NSString * const GC_UNAVAILABLE_MSG = @"GameKit Framework not available on this device. GameKit is only available on devices with iOS 4.1 or higher. Some devices running iOS 4.1 may not have GameCenter enabled.";
NSString * const INTERNET_UNAVAILABLE = @"Internet Unavailable";
NSString * const INTERNET_UNAVAILABLE_MSG = @"Cannot connect to the internet. Connect to the internet to establish a connection with GameCenter. Achievements and scores will still be saved locally and then uploaded later.";
NSString * const UNAUTHENTICATED = @"Player not Authenticated";
NSString * const UNAUTHENTICATED_MSG = @"Player is not signed into GameCenter, has declined to sign into GameCenter, or GameKit had an issue validating this game / app.";
NSString * const SDK_NOTICE_UPDATE_TITLE = @"New features added";
NSString * const SDK_NOTICE_UPDATE_DESC = @"Brand new function added, upgrade now!";
NSString * const SDK_NOTICE_UPDATE_CONFIRM = @"Upgrade";
NSString * const SDK_NOTICE_UPDATE_CANCEL = @"Cancel";
NSString * const SDK_NOTICE_UPDATE_OK = @"OK";

NSString * const SDK_VERIFY_AGE = @"SDK_VERIFY_AGE";
NSString * const SDK_VERIFY_NAME = @"SDK_VERIFY_NAME";
NSString * const SDK_VERIFY_IDCARD = @"SDK_VERIFY_IDCARD";
NSString * const SDK_HAS_START_VERIFY = @"SDK_HAS_START_VERIFY";
NSString * const SDK_DEMO_TIMER = @"SDK_DEMO_TIMER";
NSString * const SDK_DEMO_LAST_STARTTIME = @"SDK_DEMO_LAST_STARTTIME";
NSString * const SDK_DEMO_LEFTSECOND = @"SDK_DEMO_LEFTSECOND";
NSString * const SDK_DAILY_PAY = @"SDK_DAILY_PAY";
NSString * const SDK_MONTH_PAY = @"SDK_MONTH_PAY";
NSString * const SDK_UUID = @"SDK_UUID";
NSString * const SDK_CROSS_PROMOTED_CAMPAIGN = @"ivysdk";

NSString * const SDK_ANALYSE_FIREBASE = @"firebase";
NSString * const SDK_ANALYSE_FACEBOOK = @"facebook";
NSString * const SDK_ANALYSE_APPSFLYER = @"appsflyer";
NSString * const SDK_ANALYSE_UMENG = @"umeng";
NSString * const SDK_ANALYSE_IVY = @"ivy";
NSString * const SDK_ANALYSE_GA = @"ga";
NSString * const SDK_ANALYSE_TOUTIAO = @"toutiao";

+ (nonnull NSString*) getAdTypeName:(SDK_ADTYPE)type
{
    switch (type) {
        case SDK_ADTYPE_INTERTITIAL:
            return @"interstitial";
        case SDK_ADTYPE_ICON:
            return @"icon";
        case SDK_ADTYPE_VIDEO:
            return @"video";
        case SDK_ADTYPE_BANNER:
            return @"banner";
        case SDK_ADTYPE_NATIVE:
            return @"native";
        case SDK_ADTYPE_GIFICON:
            return @"gificon";
        default:
            return @"";
    }
}
@end
