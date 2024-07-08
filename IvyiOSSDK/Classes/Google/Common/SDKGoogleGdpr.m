//
//  SDKGoogleGdpr.m
//  Bolts
//
//  Created by 余冰星 on 2018/5/23.
//

#import "SDKGoogleGdpr.h"
#import <IvyiOSSdk/SDKDefine.h>
#import <GoogleMobileAds/GADMobileAds.h>
//#import <PersonalizedAdConsent/PersonalizedAdConsent.h>
//#import <MTGSDK/MTGSDK.h>
@implementation SDKGoogleGdpr
{
    @private
}

-(void)onCreate:(nonnull NSDictionary *)conf
{
    [super onCreate:conf];
    DLog(@"[adlog] [init] [gdpr]");
    if (_publisherId) {
//        [PACConsentInformation.sharedInstance requestConsentInfoUpdateForPublisherIdentifiers:@[ _publisherId ] completionHandler:^(NSError *_Nullable error) {
//            NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
//            BOOL hasGdpr = NO;
//            BOOL adPersonalized = NO;
//            if (error) {
//                // Consent info update failed.
//            } else {
//                // Consent info update succeeded. The shared PACConsentInformation
//                // instance has been updated.
//                if (userDefaults) {
//                    hasGdpr = PACConsentInformation.sharedInstance.requestLocationInEEAOrUnknown;
//                    if (hasGdpr) {
//                        if (PACConsentInformation.sharedInstance.consentStatus == PACConsentStatusUnknown) {
//                            [self resetGdpr];
//                        } else {
//                            adPersonalized = PACConsentInformation.sharedInstance.consentStatus == PACConsentStatusPersonalized;
//                        }
//                    } else {
//                        //如果非欧盟区就设置为Personalized
//                        adPersonalized = YES;
//                    }
//                }
//            }
//            if (hasGdpr) {
//                //[[MTGSDK sharedInstance] setConsentStatus:YES];
//            }
//            [userDefaults setBool:hasGdpr forKey:SDK_HAS_GDPR];
//            [userDefaults setBool:adPersonalized forKey:SDK_AD_PERSONALIZED];
//            [userDefaults synchronize];
//            DLog(@"[adlog] has gdpr = %@", (hasGdpr ? @"YES" : @"NO"));
//            DLog(@"[adlog] ad_personalized = %@", (adPersonalized ? @"YES" : @"NO"));
//        }];
    } else {
        DLog(@"[adlog]the gdpr publisherId is wrong!");
    }    
}

-(void)resetGdpr
{
    if (_privacyUrl) {
//        dispatch_async(dispatch_get_main_queue(), ^{
//            NSURL *privacyURL = [NSURL URLWithString:self->_privacyUrl];
//            if (privacyURL) {
//                PACConsentForm *_form = [[PACConsentForm alloc] initWithApplicationPrivacyPolicyURL:privacyURL];
//                _form.shouldOfferPersonalizedAds = YES;
//                _form.shouldOfferNonPersonalizedAds = YES;
//                _form.shouldOfferAdFree = NO;
//                [_form loadWithCompletionHandler:^(NSError *_Nullable error) {
//                    DLog(@"[adlog]Load complete. Error: %@", error);
//                    BOOL personalized = NO;
//                    if (error) {
//                        // Handle error.
//                        if (!PACConsentInformation.sharedInstance.requestLocationInEEAOrUnknown && PACConsentInformation.sharedInstance.consentStatus == PACConsentStatusUnknown) {
//                            personalized = YES;
//                            NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
//                            [userDefaults setBool:personalized forKey:SDK_AD_PERSONALIZED];
//                            [userDefaults synchronize];
//                        }
//                    } else {
//                        // Load successful.
//                        id _vc = [[UIApplication sharedApplication].keyWindow rootViewController];
//                        if (_vc) {
//                            [_form presentFromViewController:_vc dismissCompletion:^(NSError *_Nullable error, BOOL userPrefersAdFree) {
//                                BOOL personalized = NO;
//                                if (error) {
//                                    // Handle error.
//                                    if (!PACConsentInformation.sharedInstance.requestLocationInEEAOrUnknown && PACConsentInformation.sharedInstance.consentStatus != PACConsentStatusUnknown) {
//                                        personalized = YES;
//                                    }
//                                } else if (userPrefersAdFree) {
//                                    // The user prefers to use a paid version of the app.
//                                } else {
//                                    // Check the user's consent choice.
//                                    PACConsentStatus status = PACConsentInformation.sharedInstance.consentStatus;
//                                    personalized = status == PACConsentStatusPersonalized;
//                                }
//                                NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
//                                [userDefaults setBool:personalized forKey:SDK_AD_PERSONALIZED];
//                                [userDefaults synchronize];
//                            }];
//                        }
//                    }
//                }];
//            } else {
//                DLog(@"[adlog]the gdpr privacy_url is wrong!");
//            }
//        });
    }
}

@end
