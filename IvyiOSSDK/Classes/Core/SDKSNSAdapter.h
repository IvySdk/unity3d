//
//  SDKSNSAdapter.h
//  Pods
//
//  Created by 余冰星 on 2017/8/4.
//
//

#import <Foundation/Foundation.h>
#import <IvyiOSSdk/SDKCache.h>
#import <IvyiOSSdk/SDKDefine.h>
#import <IvyiOSSdk/SDKSNSDelegate.h>
#import "SDKConstants.h"
@interface SDKSNSAdapter : NSObject<SDKSNSDelegate>
{
    @protected
    NSArray<NSString *> *_achievementURLS;
}
-(void)setup:(nonnull NSDictionary *)conf;
-(void)login:(sns_login_result _Nullable)handler;
-(void)logout;
-(BOOL)isLogin;
-(void)invite;
-(nonnull NSString *)meFirstName;
-(nonnull NSString *)meLastName;
-(nonnull NSString *)meName;
-(nonnull NSString *)meId;
-(nonnull NSString *)mePictureURL;
-(nonnull NSString *)me;
-(nonnull NSString *)friends;
-(void)fetchFriends:(BOOL)invitable;
-(void)fetchScores;
-(void)sendScore: (int)score;
-(void)sendAchievement: (int)achievement;
-(void)sendAppInvite:(nonnull NSString *)appLinkURL previewImgURL:(nullable NSString *)previewURL;
-(void)share;
-(void)share:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote;
-(void)shareSheet:(nonnull NSString *)linkURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote;
-(void)shareSheetOS:(nonnull NSString *)linkURL title:(nonnull NSString *)title;
-(void)sendInviteWithFriendIDs: (nonnull NSArray *)friendIDs title:(nonnull NSString *)title message:(nonnull NSString *)message;
-(void)sendRequestWithFriendIDs: (nonnull NSArray *)friendIDs title:(nonnull NSString *)title message:(nonnull NSString *)message data:(nonnull NSDictionary *)data;
-(nullable UIView *)getLoginButton;
-(nullable UIView *)getShareButton:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote;
-(nullable UIView *)getSendButton:(nonnull NSString *)contentURL withTag:(nullable NSString *)tag withQuote:(nullable NSString *)quote;
-(nullable UIView *)getLikeButton:(nonnull NSString *)url;
-(void)setSNSDelegate:(nullable id<SDKSNSDelegate>)snsDelegate;
@end
