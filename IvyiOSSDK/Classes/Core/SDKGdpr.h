//
//  SDKGdpr.h
//  Bolts
//
//  Created by 余冰星 on 2018/5/24.
//

#import <Foundation/Foundation.h>
#import "SDKConstants.h"
@interface SDKGdpr : NSObject
{
@protected
    NSString *_privacyUrl;
    NSString *_publisherId;
}
-(void)onCreate:(nonnull NSDictionary *)conf;
-(BOOL)hasGdpr;
-(void)resetGdpr;
+(BOOL)enableAdPersonalized;
@end
