//
//  SDKGCManager.h
//
#include <TargetConditionals.h>

// As of version 5.3, SDKGCManager only runs on iOS 7.0+ and OS X 10.9+, check for compatibility before building. See the GitHub Releases page (https://github.com/nihalahmed/SDKGCManager/releases) for older versions which work with iOS 4.1 and higher and OS X 10.8 and higher. The last supported version for iOS < 7.0 is version 5.2. The last supported version for OS X < 10.9 is also version 5.2.
#if TARGET_OS_IOS || (TARGET_OS_IPHONE && !TARGET_OS_TV)
    #ifndef __IPHONE_7_0
        #warning SDKGCManager uses features only available in iOS SDK 7.0 and later. Running on an older version of iOS may result in a crash. Download an older release from GitHub for compatibility with iOS SDK < 7.0
    #endif
#elif TARGET_OS_TV
    //
#else
    #ifndef __MAC_10_9
        #warning SDKGCManager uses features only available in OS X SDK 10.9 and later. Running on an older version of OS X may result in a crash. Download an older release from GitHub for compatibility with OS X SDK < 10.9
    #endif
#endif

@import Foundation;
@import GameKit;

#if TARGET_OS_IPHONE
    @import UIKit;
#else
    @import Cocoa;
#endif

#import <IvyiOSSdk/NSData+AES256.h>
#import <IvyiOSSdk/SDKConstants.h>
#import "SDKGameCenterDelegate.h"

#if TARGET_OS_IPHONE
// Multiplayer is currently only available for the iOS platform
#import "SDKGCMultiplayer.h"
#endif

/// GameCenter Manager helps to manage Game Center in iOS and Mac apps. Report and keep track of high scores, achievements, and challenges for different players. GameCenter Manager also takes care of the heavy lifting - checking internet availability, saving data when offline and uploading it when online, etc.

typedef void (^SDKGCLoginHandler)(BOOL, NSString * _Nonnull);

@class SDKGCManager;
@interface SDKGCManager : NSObject <GKGameCenterControllerDelegate>
typedef NS_OPTIONS(NSUInteger, SDK_GC_UNAVAILABLE_STATUS) {
    SDK_GC_UNAVAILABLE = 1,
    SDK_INTERNET_UNAVAILABLE = 2,
    SDK_UNAUTHENTICATED = 3
};

/// Returns the shared instance of SDKGCManager.
+ (nonnull SDKGCManager *)sharedManager;

/// SDKGCManager delegate property that can be used to set the delegate
@property (nonatomic, weak, nullable) id <SDKGameCenterDelegate> delegate;


#if TARGET_OS_IPHONE
/// The multiplayer object used to facilitate and create peer-to-peer multiplayer sessions
@property (nonatomic, strong, nullable) SDKGCMultiplayer *multiplayerObject;
#endif

/** Saves score locally and reports it to Game Center. If error occurs, score is saved to be submitted later. 
 
 @param score The long long value of the score to be submitted to Game Center. This score should not be formatted, instead it should be a plain long long (int). For example, if you wanted to submit a score of 45.28 meters then you would submit it as an integer of 4528. To format your scores, you must set the Score Formatter for your leaderboard in iTunes Connect.
 @param identifier The Leaderboard ID set through iTunes Connect. This is different from the name of the leaderboard, and it is not shown to the user. 
 @param order The score sort order that you set in iTunes Connect - either high to low or low to high. This is used to determine if the user has a new highscore before submitting. */
- (void)saveAndReportScore:(long long)score leaderboard:(nonnull NSString *)identifier sortOrder:(GameCenterSortOrder)order __attribute__((nonnull));

/** Saves achievement locally and reports it to Game Center. If error occurs, achievement is saved to be submitted later.
 
 @param identifier The Achievement ID set through iTunes Connect. This is different from the name of the achievement, and it is not shown to the user.
 @param percentComplete A percentage value that states how far the player has progressed on this achievement. The range of legal values is between 0.0 and 100.0. Submitting 100.0 will mark the achievement as completed. Submitting a percent which is lower than what the user has already achieved will be ignored - the user's achievement progress cannot go down.
 @param displayNotification YES if GCManager should display a Game Center Achievement banner. NO if no banner should be displayed */
- (void)saveAndReportAchievement:(nonnull NSString *)identifier percentComplete:(double)percentComplete shouldDisplayNotification:(BOOL)displayNotification __attribute__((nonnull));


/// Reports scores and achievements which could not be reported earlier.
- (void)reportSavedScoresAndAchievements;

/// Saves score to be submitted later.
- (void)saveScoreToReportLater:(nonnull GKScore *)score;

/// Saves achievement to be submitted later.
- (void)saveAchievementToReportLater:(nonnull NSString *)identifier percentComplete:(double)percentComplete;


/// Returns local player's high score for specified leaderboard.
- (long long)myHighScore:(nonnull NSString *)identifier;

/// Returns local player's high scores for multiple leaderboards.
- (nullable NSDictionary *)myHighScores:(nonnull NSArray *)identifiers;


/// Returns local player's percent completed for specified achievement.
- (double)getAchievementProgress:(nonnull NSString *)identifier;

/// Returns local player's percent completed for multiple achievements.
- (nullable NSDictionary *)getAchievementProgresss:(nonnull NSArray *)identifiers;


/** Gets a list of challenges for the current player and game. If GameCenter is not available it will return nil and provide an error using the gc_error: delegate method. Use the completion handler to get challenges.
 @param handler Completion handler with an NSArray containing challenges and an NSError. The NSError object will be nil if there is no error. */
- (void)getChallengesWithCompletion:(void (^)(NSArray *challenges, NSError *error))handler __attribute__((nonnull));


#if TARGET_OS_IPHONE
/// Presents the GameCenter Achievements ViewController over the specified ViewController. Dismissal and delegation is handled by SDKGCManager.
- (void)presentAchievementsOnViewController:(nonnull UIViewController *)viewController;

/// DEPRECATED. Use presentLeaderboardsOnViewController: withLeaderboard: instead.
- (void)presentLeaderboardsOnViewController:(nonnull UIViewController *)viewController timeScope:(GKLeaderboardTimeScope)scope;

/// Presents the GameCenter Leaderboards ViewController with Leaderboard Identifier over the specified ViewController. Dismissal and delegation is handled by SDKGCManager.
- (void)presentLeaderboardsOnViewController:(nonnull UIViewController *)viewController withLeaderboard:(nullable NSString *)leaderboard timeScope:(GKLeaderboardTimeScope)scope;

/// Presents the GameCenter Challenges ViewController over the specified ViewController. Dismissal and delegation is handled by SDKGCManager.
- (void)presentChallengesOnViewController:(nonnull UIViewController *)viewController;
#endif


#if TARGET_OS_IPHONE
/// Resets all of the local player's achievements and progress for the current game
- (void)resetAchievementsWithCompletion:(void (^)(NSError *error))handler __attribute__((nonnull));
#else
/// Resets all of the local player's achievements and progress for the current game
- (void)resetAchievementsWithCompletion:(void (^)(NSError *error))handler __attribute__((nonnull));

/// DEPRECATED. Use resetAchievementsWithCompletion: instead.
- (void)resetAchievements __deprecated __unavailable;
#endif


/// Returns currently authenticated local player ID. If no player is authenticated, "unknownPlayer" is returned.
- (NSString *)localPlayerId;

/// Returns currently authenticated local player's display name (alias or actual name depending on friendship). If no player is authenticated, "unknownPlayer" is returned. Player Alias will be returned if the Display Name property is not available
- (NSString *)localPlayerDisplayName;

/// Returns currently authenticated local player and all associated data. If no player is authenticated, `nil` is returned.
- (GKLocalPlayer *)localPlayerData;

#if TARGET_OS_IPHONE
/// Fetches a UIImage with the local player's profile picture at full resolution. The completion handler passes a UIImage object when the image is downloaded from the GameCenter Servers
- (void)localPlayerPhoto:(void (^)(UIImage *playerPhoto))handler __attribute__((nonnull)) __OSX_AVAILABLE_STARTING(__OSX_10_8,__IPHONE_5_0);
#else
/// Fetches an NSImage with the local player's profile picture at full resolution. The completion handler passes an NSImage object when the image is downloaded from the GameCenter Servers
- (void)localPlayerPhoto:(void (^)(NSImage *playerPhoto))handler __attribute__((nonnull));
#endif

-(void)checkGameCenterAvailable:(BOOL)alertEnable;
/// Returns YES if an active internet connection is available.
- (BOOL)isInternetAvailable;

- (void)login:(SDKGCLoginHandler _Nullable )onResult;
/// Use this property to check if Game Center is available and supported on the current device.
@property (nonatomic, assign) BOOL isGameCenterAvailable;
@property (nonatomic, assign) SDK_GC_UNAVAILABLE_STATUS gameCenterUnavailableStatus;

+ (nonnull GKLocalPlayer *)getLocalPlayer;
@end
