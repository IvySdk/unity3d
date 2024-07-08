//
//  SDKGameCenterDelegate.h
//  Pods
//
//  Created by 余冰星 on 2017/7/31.
//
//
#ifndef SDKGameCenterDelegate_h
#define SDKGameCenterDelegate_h
#import <Foundation/Foundation.h>
#import <GameKit/GameKit.h>
@protocol SDKGameCenterDelegate<NSObject>
#if TARGET_OS_IPHONE
@required
/// Required Delegate Method called when the user needs to be authenticated using the GameCenter Login View Controller
- (void)gc_authenticateUser:(UIViewController *)gameCenterLoginController;
#else
@required
/// Required Delegate Method called when the user needs to be authenticated using the Game Center Login View Controller
- (void)gc_authenticateUser:(NSViewController *)gameCenterLoginController;
#endif

@optional
/// Delegate Method called when the availability of GameCenter changes
- (void)gc_availabilityChanged:(NSDictionary *)availabilityInformation;

/// Delegate Method called when the there is an error with GameCenter or GC Manager
- (void)gc_error:(NSError *)error;

/// Sent to the delegate when a score is reported to GameCenter
- (void)gc_reportedScore:(GKScore *)score withError:(NSError *)error;
/// Sent to the delegate when an achievement is reported to GameCenter
- (void)gc_reportedAchievement:(GKAchievement *)achievement withError:(NSError *)error;

/// Sent to the delegate when an achievement is saved locally
- (void)gc_didSaveAchievement:(GKAchievement *)achievement;
/// Sent to the delegate when a score is saved locally
- (void)gc_didSaveScore:(GKScore *)score;
/// Sent to the delegate when the Game Center is synced
- (void)gc_gameCenterSynced:(BOOL)synced;

/// Sent to the delegate when the Game Center View Controller is On Screen
- (void)gc_gameCenterViewControllerPresented:(BOOL)finished;
/// Sent to the delegate when the Game Center View Controller has been dismissed
- (void)gc_gameCenterViewControllerDidFinish:(BOOL)finished;

@end

#endif /* SDKGameCenterDelegate_h */
