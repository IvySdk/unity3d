//
//  SDKGdpr.m
//  Bolts
//
//  Created by 余冰星 on 2018/5/24.
//

#import "SDKGdpr.h"
#import <IvyiOSSdk/SDKDefine.h>

@implementation SDKGdpr
-(void)onCreate:(nonnull NSDictionary *)conf
{
    if (conf) {
        NSDictionary *obj = [conf objectForKey:@"gdpr"];
        if (obj) {
            _privacyUrl = [obj objectForKey:@"privacy-url"];
            _publisherId = [obj objectForKey:@"pid"];
            if (!_privacyUrl) {
                DLog(@"[adlog]the gdpr privacy_url is null!");
            }
            if (!_publisherId) {
                DLog(@"[adlog]the gdpr publisherId is null!");
            }
        }
    }
}

-(void)resetGdpr
{
}

-(BOOL)hasGdpr
{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    BOOL tmp = [userDefaults boolForKey:SDK_HAS_GDPR];
    return tmp;
}

+(BOOL)enableAdPersonalized
{
    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    BOOL tmp = [userDefaults boolForKey:SDK_AD_PERSONALIZED];
    return tmp;
}
@end
