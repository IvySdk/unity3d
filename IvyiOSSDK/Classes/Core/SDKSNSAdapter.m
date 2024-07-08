//
//  SDKSNSAdapter.m
//  Pods
//
//  Created by 余冰星 on 2017/8/4.
//
//

#import "SDKSNSAdapter.h"
#import "SDKJSONHelper.h"
#import "SDKHelper.h"

@implementation SDKSNSAdapter
{
    @private
    id<SDKSNSDelegate> _snsDelegate;
}

-(instancetype)init
{
    NSAssert(![self isMemberOfClass:[SDKSNSAdapter class]], @"SDKSNSAdapter is an abstract class, you should not instantiate it directly.");
    DLog(@"[snslog] %@ init", NSStringFromClass(self.class));
    return [super init];
}

-(void)setSNSDelegate:(id<SDKSNSDelegate>)snsDelegate
{
    DLog(@"[snslog] %@ setSNSDelegate, delegate = %@", NSStringFromClass(self.class), snsDelegate ? @"YES" : @"nil");
    _snsDelegate = snsDelegate;
}

-(void)setup:(nonnull NSDictionary *)conf
{
    DLog(@"[snslog] %@ setup", NSStringFromClass(self.class));
    if(conf) {
        _achievementURLS = [conf objectForKey:@"achievements"];
    }
}

-(void)login:(sns_login_result _Nullable)handler;
{
    DLog(@"[snslog] %@ login", NSStringFromClass(self.class));
}

-(void)logout
{
    DLog(@"[snslog] %@ logout", NSStringFromClass(self.class));
}

-(BOOL)isLogin
{
    return NO;
}

-(void)invite
{
    DLog(@"[snslog] %@ invite", NSStringFromClass(self.class));
}

-(void)sendRequestWithFriendIDs:(NSArray *)friendIDs title:(NSString *)title message:(NSString *)message data:(NSDictionary *)data
{
    DLog(@"[snslog] %@ sendRequestWithFriendIDs, friendIDs = %@", NSStringFromClass(self.class), [SDKJSONHelper toJSONString:friendIDs]);
}

-(void)sendInviteWithFriendIDs:(NSArray *)friendIDs title:(NSString *)title message:(NSString *)message
{
    DLog(@"[snslog] %@ sendInviteWithFriendIDs, friendIDs = %@", NSStringFromClass(self.class), [SDKJSONHelper toJSONString:friendIDs]);
}

-(NSString *)meFirstName {
    return @"";
}

-(NSString *)meLastName {
    return @"";
}

-(NSString *)meName {
    return @"";
}

-(NSString *)meId {
    return @"";
}

-(NSString *)mePictureURL {
    return @"";
}

+(NSString *)fetchPictureURL: (NSString *)fbid
{
    return [NSString stringWithFormat:@"https://graph.facebook.com/%@/picture?width=256&height=256", fbid];
}

-(void)fetchFriends:(BOOL)invitable
{
    DLog(@"[snslog] %@ fetchFriends, invitable = %@", NSStringFromClass(self.class), invitable ? @"YES" : @"NO");
}

-(nonnull NSString *)me
{
    return @"{}";
}

-(nonnull NSString *)friends
{
    return @"[]";
}

-(void)fetchScores
{
    DLog(@"[snslog] %@ fetchScores", NSStringFromClass(self.class));
}

-(void)sendScore:(int)score
{
    DLog(@"[snslog] %@ sendScore, score = %d", NSStringFromClass(self.class), score);
}

-(void)sendAchievement:(int)achievement
{
    DLog(@"[snslog] %@ sendAchievement, achievement = %d", NSStringFromClass(self.class), achievement);
}

-(void)sendAppInvite:(NSString *)appLinkURL previewImgURL:(NSString *)previewURL
{
    DLog(@"[snslog] %@ sendAppInvite, appLinkURL = %@", NSStringFromClass(self.class), appLinkURL);
}

-(void)share
{
    DLog(@"[snslog] %@ share", NSStringFromClass(self.class));
}

-(void)share:(NSString *)contentURL withTag:(NSString *)tag withQuote:(NSString *)quote
{
    DLog(@"[snslog] %@ share, contentURL = %@", NSStringFromClass(self.class), contentURL);
}

-(void)shareSheet:(NSString *)linkURL withTag:(NSString *)tag withQuote:(NSString *)quote
{
    DLog(@"[snslog] %@ shareSheet, linkURL = %@", NSStringFromClass(self.class), linkURL);
}

-(void)shareSheetOS:(NSString *)linkURL title:(NSString *)title
{
    DLog(@"[snslog] %@ shareSheetOS, linkURL = %@", NSStringFromClass(self.class), linkURL);
//    NSArray *activityItems = [NSArray arrayWithObjects:title, linkURL, nil];
//    UIActivityViewController *activityViewController = [[UIActivityViewController alloc] initWithActivityItems:activityItems applicationActivities:nil];
//    activityViewController.modalTransitionStyle = UIModalTransitionStyleCoverVertical;
//
//    [[UIApplication sharedApplication].keyWindow.rootViewController presentViewController:activityViewController animated:YES completion:nil];
    [SDKHelper share:title image:nil url:[NSURL URLWithString:linkURL] completeHandler:nil];
}

-(UIView *)getLoginButton
{
    return nil;
}

-(nullable UIView *)getLikeButton:(nonnull NSString *)url
{
    return nil;
}

-(UIView *)getShareButton:(NSString *)contentURL withTag:(NSString *)tag withQuote:(NSString *)quote
{
    return nil;
}

-(UIView *)getSendButton:(NSString *)contentURL withTag:(NSString *)tag withQuote:(NSString *)quote
{
    return nil;
}

- (BOOL)application:(nullable UIApplication *)application openURL:(nullable NSURL *)url sourceApplication:(nullable NSString *)sourceApplication annotation:(nullable id)annotation
{
    return NO;
}

-(void)snsLoginSuccess
{
    DLog(@"[snslog] %@ login success, me = %@", NSStringFromClass(self.class), [self me]);
    [SDKHelper sendNotif:SDK_SNS_LOGIN_SUCCESS];
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsLoginSuccess)]) {
        [_snsDelegate snsLoginSuccess];
    }
}

-(void)snsLoginFailure:(nullable NSString *)error
{
    DLog(@"[snslog] %@ login failure, error = %@", NSStringFromClass(self.class), error ? error : @"");
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsLoginFailure:)]) {
        [_snsDelegate snsLoginFailure:error];
    }
}

-(void)snsLoginCancel
{
    DLog(@"[snslog] %@ login cancel", NSStringFromClass(self.class));
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsLoginCancel)]) {
        [_snsDelegate snsLoginCancel];
    }
}

- (void)snsInviteSuccess
{
    DLog(@"[snslog] %@ invite success", NSStringFromClass(self.class));
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsInviteSuccess)]) {
        [_snsDelegate snsInviteSuccess];
    }
}

- (void)snsInviteFailure
{
    DLog(@"[snslog] %@ invite failure", NSStringFromClass(self.class));
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsInviteFailure)]) {
        [_snsDelegate snsInviteFailure];
    }
}

- (void)snsShareSuccess
{
    DLog(@"[snslog] %@ share success", NSStringFromClass(self.class));
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsShareSuccess)]) {
        [_snsDelegate snsShareSuccess];
    }
}

- (void)snsShareFailure
{
    DLog(@"[snslog] %@ share failure", NSStringFromClass(self.class));
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsShareFailure)]) {
        [_snsDelegate snsShareFailure];
    }
}

- (void)snsShareCancel
{
    DLog(@"[snslog] %@ share cancel", NSStringFromClass(self.class));
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsShareCancel)]) {
        [_snsDelegate snsShareCancel];
    }
}

- (void)snsOpenUrl:(nullable NSString *)url
{
    DLog(@"[snslog] %@ open url : %@", NSStringFromClass(self.class), url ? url : @"");
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsOpenUrl:)]) {
        [_snsDelegate snsOpenUrl:url];
    }
}

- (void)snsReceiveScores:(nullable NSArray *)data
{
    DLog(@"[snslog] %@ receive scores, data = %@", NSStringFromClass(self.class), data ? [SDKJSONHelper toJSONString:data] : @"nil");
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsReceiveScores:)]) {
        [_snsDelegate snsReceiveScores:data];
    }
}

- (void)snsReceiveFriends:(nullable NSArray *)data
{
    DLog(@"[snslog] %@ receive friends, data = %@", NSStringFromClass(self.class), data ? [SDKJSONHelper toJSONString:data] : @"nil");
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsReceiveFriends:)]) {
        [_snsDelegate snsReceiveFriends:data];
    }
}

- (void)snsReceiveInvitableFriends:(nullable NSArray *)data
{
    DLog(@"[snslog] %@ receive invitable friends, data = %@", NSStringFromClass(self.class), data ? [SDKJSONHelper toJSONString:data] : @"nil");
    if(_snsDelegate && [_snsDelegate respondsToSelector:@selector(snsReceiveInvitableFriends:)]) {
        [_snsDelegate snsReceiveInvitableFriends:data];
    }
}

- (void)signInAppleFailure:(nonnull NSString *)error {
}


- (void)signInAppleSuccess:(nonnull NSString *)appidUID {
}

@end
