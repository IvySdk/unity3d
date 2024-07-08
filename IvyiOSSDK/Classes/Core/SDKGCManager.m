//
//  SDKGCManager.m
//
// SDKGCManager uses ARC, check for compatibility before building
#if !__has_feature(objc_arc)
#error SDKGCManager uses Objective-C ARC. Compile these files with ARC enabled. Add the -fobjc-arc compiler flag to enable ARC for only these files.
#endif

#import "SDKGCManager.h"
#import <IvyiOSSdk/SDKNetworkHelper.h>
#import <IvyiOSSdk/SDKCache.h>
#import <IvyiOSSdk/SDKHelper.h>
#import <IvyiOSSdk/SDKFacade.h>
//------------------------------------------------------------------------------------------------------------//
//------- GameCenter Manager Singleton -----------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------//
#pragma mark GameCenter Manager

#define IS_IOS_8_OR_LATER    ([[[UIDevice currentDevice] systemVersion] floatValue] >= 8.0)

@interface SDKGCManager () {
    NSMutableArray *GCMLeaderboards;
    
#if TARGET_OS_IPHONE
    UIBackgroundTaskIdentifier backgroundProcess;
#endif
}
@end
NSString * const SDK_GC_MYSCORES = @"MY_SCORES";
NSString * const SDK_GC_MYACHIEVEMENTS = @"MY_ACHIEVEMENTS";
NSString * const SDK_GC_SAVEDSCORES = @"SavedScores";
NSString * const SDK_GC_SAVEDACHIEVEMENTS = @"SavedAchievements";
@implementation SDKGCManager
{
    @private
    NSMutableDictionary *_myScores;
    NSMutableDictionary *_myAchievements;
    SDKGCLoginHandler _loginHandler;
    UIViewController *_loginVC;
}
@synthesize delegate = _delegate;
@synthesize multiplayerObject = _multiplayerObject;
@synthesize isGameCenterAvailable = _isGameCenterAvailable;
@synthesize gameCenterUnavailableStatus = _gameCenterUnavailableStatus;

#pragma mark - Object Lifecycle

+ (SDKGCManager *)sharedManager {
    static SDKGCManager *singleton;
    
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        singleton = [[self alloc] init];
    });
    
    return singleton;
}

- (instancetype)init {
    self = [super init];
    if (self) {
        if (!_myScores) {
            id tmp = [[SDKCache cache] objectForKey:SDK_GC_MYSCORES];
            _myScores = tmp ? [NSMutableDictionary dictionaryWithDictionary:tmp] : [[NSMutableDictionary alloc] init];
        }
        if (!_myAchievements) {
            id tmp = [[SDKCache cache] objectForKey:SDK_GC_MYACHIEVEMENTS];
            _myAchievements = tmp ? [NSMutableDictionary dictionaryWithDictionary:tmp] : [[NSMutableDictionary alloc] init];
        }
    }
    
    return self;
}

-(void)checkGameCenterAvailable:(BOOL)alertEnable
{
    [self _checkGameCenterAvailability:^(BOOL isGameCenterAvailable){
        if (isGameCenterAvailable) {
            [self syncGameCenter];
            [self reportSavedScoresAndAchievements];
        } else if(alertEnable) {
            NSString *ok = [SDKHelper localizedStr:@"SDK_NOTICE_UPDATE_OK" defaultString:SDK_NOTICE_UPDATE_OK];
            NSString *title = [SDKHelper localizedStr:@"UNAUTHENTICATED" defaultString:UNAUTHENTICATED];
            NSString *message = [SDKHelper localizedStr:@"UNAUTHENTICATED_MSG" defaultString:UNAUTHENTICATED_MSG];
            switch ([SDKGCManager sharedManager].gameCenterUnavailableStatus) {
                case SDK_INTERNET_UNAVAILABLE:
                    title = [SDKHelper localizedStr:@"INTERNET_UNAVAILABLE" defaultString:INTERNET_UNAVAILABLE];
                    message = [SDKHelper localizedStr:@"INTERNET_UNAVAILABLE_MSG" defaultString:INTERNET_UNAVAILABLE_MSG];
                    break;
                case SDK_GC_UNAVAILABLE:
                    title = [SDKHelper localizedStr:@"GC_UNAVAILABLE" defaultString:GC_UNAVAILABLE];
                    message = [SDKHelper localizedStr:@"GC_UNAVAILABLE_MSG" defaultString:GC_UNAVAILABLE_MSG];
                    break;
                default:
                    break;
            }
            
            [SDKHelper showAlertDialog:title desc:message okBtn:ok cancelBtn:nil onOk:nil onCancel:nil];
        }
    }];
}

//------------------------------------------------------------------------------------------------------------//
//------- GameCenter Manager Setup ---------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------//
#pragma mark - GameCenter Manager Setup

- (void)gc_loginSuccess:(void(^__nullable)(BOOL))onResult
{
    _gameCenterUnavailableStatus = 0;
    self.isGameCenterAvailable = YES;
    dispatch_async(dispatch_get_main_queue(), ^{
         NSDictionary *successDictionary = [NSDictionary dictionaryWithObject:@"GameCenter Available" forKey:@"status"];
        if ([[self delegate] respondsToSelector:@selector(gc_availabilityChanged:)]) {
            [[self delegate] gc_availabilityChanged:successDictionary];
        }
        if (onResult) {
            onResult(YES);
        }
        if (self->_loginHandler) {
            self->_loginHandler(YES, self.localPlayerId);
            self->_loginHandler = nil;
        }
    });
}

- (void)_checkGameCenterAvailability:(void(^__nullable)(BOOL))onResult
{
#if TARGET_OS_IPHONE
    // First, check if the the GameKit Framework exists on the device. Return NO if it does not.
    BOOL localPlayerClassAvailable = (NSClassFromString(@"GKLocalPlayer")) != nil;
    NSString *reqSysVer = @"4.1";
    NSString *currSysVer = [[UIDevice currentDevice] systemVersion];
    BOOL osVersionSupported = ([currSysVer compare:reqSysVer options:NSNumericSearch] != NSOrderedAscending);
    BOOL isGameCenterAPIAvailable = (localPlayerClassAvailable && osVersionSupported);
#else
    BOOL isGameCenterAPIAvailable = (NSClassFromString(@"GKLocalPlayer")) != nil;
#endif
    
    if (!isGameCenterAPIAvailable) {
        _gameCenterUnavailableStatus = SDK_GC_UNAVAILABLE;
        NSString *title = [SDKHelper localizedStr:@"GC_UNAVAILABLE" defaultString:GC_UNAVAILABLE];
        NSString *message = [SDKHelper localizedStr:@"GC_UNAVAILABLE_MSG" defaultString:GC_UNAVAILABLE_MSG];
        NSDictionary *errorDictionary = @{@"message": message, @"title": title};
        
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_availabilityChanged:)])
                [[self delegate] gc_availabilityChanged:errorDictionary];
        });
        
        self.isGameCenterAvailable = NO;
        
    } else {
        // The GameKit Framework is available. Now check if an internet connection can be established
        BOOL internetAvailable = [self isInternetAvailable];
        if (!internetAvailable) {
            _gameCenterUnavailableStatus = SDK_INTERNET_UNAVAILABLE;
            NSString *title = [SDKHelper localizedStr:@"INTERNET_UNAVAILABLE" defaultString:INTERNET_UNAVAILABLE];
            NSString *message = [SDKHelper localizedStr:@"INTERNET_UNAVAILABLE_MSG" defaultString:INTERNET_UNAVAILABLE_MSG];
            NSDictionary *errorDictionary = @{@"message": message, @"title": title};
            
            dispatch_async(dispatch_get_main_queue(), ^{
                if ([[self delegate] respondsToSelector:@selector(gc_availabilityChanged:)])
                    [[self delegate] gc_availabilityChanged:errorDictionary];
            });
            
            self.isGameCenterAvailable = NO;
            
        } else {
            // The internet is available and the current device is connected. Now check if the player is authenticated
            __block GKLocalPlayer *localPlayer = [SDKGCManager getLocalPlayer];
            localPlayer.authenticateHandler = ^(UIViewController *viewController, NSError *error) {
                if ([[GKLocalPlayer localPlayer] isAuthenticated]) {
                    [self gc_loginSuccess:onResult];
                }else if(viewController){
                    self->_loginVC = viewController;
                    dispatch_async(dispatch_get_main_queue(), ^{
                        self->_gameCenterUnavailableStatus = SDK_UNAUTHENTICATED;
                        NSString *title = [SDKHelper localizedStr:@"UNAUTHENTICATED" defaultString:UNAUTHENTICATED];
                        NSString *message = [SDKHelper localizedStr:@"UNAUTHENTICATED_MSG" defaultString:UNAUTHENTICATED_MSG];
                        NSDictionary *errorDictionary = @{@"message": message, @"title": title};
                        if ([[self delegate] respondsToSelector:@selector(gc_availabilityChanged:)])
                            [[self delegate] gc_availabilityChanged:errorDictionary];
                        
                        if ([[self delegate] respondsToSelector:@selector(gc_authenticateUser:)]) {
                            [[self delegate] gc_authenticateUser:viewController];
                        } else {
                            DLog(@"[GC][ERROR] %@ Fails to Respond to the required delegate method gc_authenticateUser:. This delegate method must be properly implemented to use GC Manager", [self delegate]);
                        }
                        [[SDKFacade sharedInstance].rootVC presentViewController:viewController animated:NO completion:nil];
                    });
                }
                else{
                    if (!error) {
                        DLog(@"授权完成");
                        [self _checkGameCenterAvailability:onResult];
                    }else{
                        DLog(@"取消授权");
                        DLog(@"AuthPlayer error: %@", [error localizedDescription]);
                    }
                }
            };
            if (!localPlayer.authenticated) {
                _gameCenterUnavailableStatus = SDK_UNAUTHENTICATED;
                NSString *title = [SDKHelper localizedStr:@"UNAUTHENTICATED" defaultString:UNAUTHENTICATED];
                NSString *message = [SDKHelper localizedStr:@"UNAUTHENTICATED_MSG" defaultString:UNAUTHENTICATED_MSG];
                NSDictionary *errorDictionary = @{@"message": message, @"title": title};
                
                if ([[self delegate] respondsToSelector:@selector(gc_availabilityChanged:)])
                    [[self delegate] gc_availabilityChanged:errorDictionary];
                if (_loginVC) {
                    dispatch_async(dispatch_get_main_queue(), ^{
                        [[SDKFacade sharedInstance].rootVC presentViewController:self->_loginVC animated:NO completion:nil];
                    });
                }
                self.isGameCenterAvailable = NO;
            } else {
                dispatch_async(dispatch_get_main_queue(), ^{
                    [self gc_loginSuccess:onResult];
                });
            }
        }
    }
}

// Check for internet with Reachability
- (BOOL)isInternetAvailable {
    
    if (![[SDKNetworkHelper sharedHelper] isNetwork]) {
        DLog(@"[GC]Internet unavailable");
        NSError *error = [NSError errorWithDomain:[NSString stringWithFormat:@"Internet unavailable - could not connect to the internet. Connect to WiFi or a Cellular Network to upload data to GameCenter."] code:GCMErrorInternetNotAvailable userInfo:nil];
        
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                [[self delegate] gc_error:error];
        });
        
        return NO;
    } else {
        return YES;
    }
}

- (void)login:(SDKGCLoginHandler)onResult
{
    if (self.isGameCenterAvailable) {
        if (onResult) {
            onResult(true, self.localPlayerId);
            _loginHandler = nil;
        }
    } else {
        if (onResult) {
            onResult(false, self.localPlayerId);
            _loginHandler = onResult;
        }
    }
}
//------------------------------------------------------------------------------------------------------------//
//------- GameCenter Syncing ---------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------//
#pragma mark - GameCenter Syncing

- (void)syncGameCenter {
    // Begin Syncing with GameCenter
    
    // Ensure the task isn't interrupted even if the user exits the app
    backgroundProcess = [[UIApplication sharedApplication] beginBackgroundTaskWithExpirationHandler:^{
        //End the Background Process
        [[UIApplication sharedApplication] endBackgroundTask:self->backgroundProcess];
        self->backgroundProcess = UIBackgroundTaskInvalid;
    }];
    
    // Move the process to the background thread to avoid clogging up the UI
    dispatch_queue_t syncGameCenterOnBackgroundThread = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0ul);
    dispatch_async(syncGameCenterOnBackgroundThread, ^{
        
        if ([self isGameCenterAvailable]) {
            // Check if Leaderboard Scores are synced
            [self syncLeaderboards];
            // Check if Achievements are synced
            [self syncAchievements];
        } else {
            NSError *error = [NSError errorWithDomain:[NSString stringWithFormat:@"GameCenter unavailable."] code:GCMErrorNotAvailable userInfo:nil];
            dispatch_async(dispatch_get_main_queue(), ^{
                if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                    [[self delegate] gc_error:error];
            });
        }
    });
    
    // End the Background Process
    [[UIApplication sharedApplication] endBackgroundTask:backgroundProcess];
    backgroundProcess = UIBackgroundTaskInvalid;
}

-(void)syncLeaderboards
{
    if (self->GCMLeaderboards == nil) {
        [GKLeaderboard loadLeaderboardsWithCompletionHandler:^(NSArray *leaderboards, NSError *error) {
            if (error == nil) {
                self->GCMLeaderboards = [[NSMutableArray alloc] initWithArray:leaderboards];
                [self syncLeaderboards];
            } else {
                dispatch_async(dispatch_get_main_queue(), ^{
                    if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                        [[self delegate] gc_error:error];
                });
            }
        }];
        return;
    }
    
    if (self->GCMLeaderboards.count > 0) {
        
        GKLeaderboard *leaderboardRequest = [[GKLeaderboard alloc] initWithPlayers:[NSArray arrayWithObject:[SDKGCManager getLocalPlayer]]];
        [leaderboardRequest setIdentifier:[(GKLeaderboard *)[self->GCMLeaderboards objectAtIndex:0] identifier]];
        [leaderboardRequest loadScoresWithCompletionHandler:^(NSArray *scores, NSError *error) {
            if (error == nil) {
                if (scores.count > 0) {
                    [self saveMyHighScore:leaderboardRequest.localPlayerScore.value identifier:leaderboardRequest.localPlayerScore.leaderboardIdentifier];
                }
                
                // Seeing an NSRangeException for an empty array when trying to remove the object
                // Despite the check above in this scope that leaderboards count is > 0
                if (self->GCMLeaderboards.count > 0) {
                    [self->GCMLeaderboards removeObjectAtIndex:0];
                }
                
                [self syncLeaderboards];
            } else {
                dispatch_async(dispatch_get_main_queue(), ^{
                    if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                        [[self delegate] gc_error:error];
                });
            }
        }];
    }
}

-(void)syncAchievements
{
    [GKAchievement loadAchievementsWithCompletionHandler:^(NSArray *achievements, NSError *error) {
        if (error == nil) {
            DLog(@"[GC]Number of Achievements: %@", achievements);
            if (achievements.count > 0) {
                for (GKAchievement *achievement in achievements) {
                    [self saveMyAchievementProgress:achievement.percentComplete identifier:achievement.identifier];
                }
            }
        } else {
            dispatch_async(dispatch_get_main_queue(), ^{
                if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                    [[self delegate] gc_error:error];
            });
        }
    }];
}

- (void)reportSavedScoresAndAchievements {
    if ([self isInternetAvailable] == NO) return;
    
    GKScore *gkScore = nil;
    id tmp = [[SDKCache cache] objectForKey:SDK_GC_SAVEDSCORES];
    NSMutableArray *savedScores = tmp ? [tmp mutableCopy] : nil;
    
    if (savedScores) {
        if (savedScores.count > 0) {
            gkScore = [NSKeyedUnarchiver unarchiveObjectWithData:[savedScores objectAtIndex:0]];
            [savedScores removeObjectAtIndex:0];
            [[SDKCache cache] setObject:savedScores forKey:SDK_GC_SAVEDSCORES];
        }
    }
    
    if (gkScore != nil && gkScore.value != 0) {
        [GKScore reportScores:@[gkScore] withCompletionHandler:^(NSError *error) {
            if (error == nil) {
                [self reportSavedScoresAndAchievements];
            } else {
                [self saveScoreToReportLater:gkScore];
            }
        }];
    } else {
        if ([SDKGCManager getLocalPlayer].authenticated) {
            NSString *identifier = nil;
            double percentComplete = 0;
            id tmp = [[SDKCache cache] objectForKey:SDK_GC_SAVEDACHIEVEMENTS];
            NSMutableDictionary *savedAchievements = tmp ? [tmp mutableCopy] : nil;
            if (savedAchievements) {
                if (savedAchievements.count > 0) {
                    identifier = [[savedAchievements allKeys] objectAtIndex:0];
                    percentComplete = [[savedAchievements objectForKey:identifier] doubleValue];
                    
                    [savedAchievements removeObjectForKey:identifier];
                    [[SDKCache cache] setObject:savedAchievements forKey:SDK_GC_SAVEDACHIEVEMENTS];
                }
            }
            
            if (identifier != nil) {
                GKAchievement *achievement = [[GKAchievement alloc] initWithIdentifier:identifier];
                achievement.percentComplete = percentComplete;
                [GKAchievement reportAchievements:@[achievement] withCompletionHandler:^(NSError *error) {
                    if (error == nil) {
                        [self reportSavedScoresAndAchievements];
                    } else {
                        [self saveAchievementToReportLater:achievement.identifier percentComplete:achievement.percentComplete];
                    }
                }];
            }
        }
    }
}

//------------------------------------------------------------------------------------------------------------//
//------- Score and Achievement Reporting --------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------//
#pragma mark - Score and Achievement Reporting

- (void)saveAndReportScore:(long long)score leaderboard:(NSString *)identifier sortOrder:(GameCenterSortOrder)order  {
    long long savedHighScoreValue = [self myHighScore:identifier];
    
    // Determine if the new score is better than the old score
    BOOL isScoreBetter = NO;
    switch (order) {
        case GameCenterSortOrderLowToHigh: // A lower score is better
            if (score < savedHighScoreValue) isScoreBetter = YES;
            break;
        default:
            if (score > savedHighScoreValue) // A higher score is better
                isScoreBetter = YES;
            break;
    }
    
    if (isScoreBetter) {
        [self saveMyHighScore:score identifier:identifier];
        if ([self isGameCenterAvailable]) {
#if TARGET_OS_IPHONE
            GKScore *gkScore = [[GKScore alloc] initWithLeaderboardIdentifier:identifier];
#else
#ifdef __MAC_10_10
            GKScore *gkScore = [[GKScore alloc] initWithLeaderboardIdentifier:identifier];
#else
            GKScore *gkScore = [[GKScore alloc] initWithCategory:identifier];
#endif
#endif
            [gkScore setValue:score];
            
            [GKScore reportScores:@[gkScore] withCompletionHandler:^(NSError *error) {
                if (error) {
                    [self saveScoreToReportLater:gkScore];
                }
                
                dispatch_async(dispatch_get_main_queue(), ^{
                    if ([[self delegate] respondsToSelector:@selector(gc_reportedScore:withError:)])
                        [[self delegate] gc_reportedScore:gkScore withError:error];
                });
            }];
        } else {
#if TARGET_OS_IPHONE
            GKScore *gkScore = [[GKScore alloc] initWithLeaderboardIdentifier:identifier];
#else
#ifdef __MAC_10_10
            GKScore *gkScore = [[GKScore alloc] initWithLeaderboardIdentifier:identifier];
#else
            GKScore *gkScore = [[GKScore alloc] initWithCategory:identifier];
#endif
#endif
            [gkScore setValue:score];
            [self saveScoreToReportLater:gkScore];
        }
    }
    
}

- (void)saveAndReportAchievement:(NSString *)identifier percentComplete:(double)percentComplete shouldDisplayNotification:(BOOL)displayNotification {
    double savedPercentCompleteValue = [self getAchievementProgress:identifier];
    if (percentComplete > savedPercentCompleteValue) {
        [self saveMyAchievementProgress:percentComplete identifier:identifier];
    }
    
    if ([self isGameCenterAvailable]) {
        GKAchievement *achievement = [[GKAchievement alloc] initWithIdentifier:identifier];
        achievement.percentComplete = percentComplete;
        if (displayNotification == YES) achievement.showsCompletionBanner = YES;
        else achievement.showsCompletionBanner = NO;
        
        [GKAchievement reportAchievements:@[achievement] withCompletionHandler:^(NSError *error) {
            if (error) {
                [self saveAchievementToReportLater:identifier percentComplete:percentComplete];
            }
            dispatch_async(dispatch_get_main_queue(), ^{
                if ([[self delegate] respondsToSelector:@selector(gc_reportedAchievement:withError:)])
                    [[self delegate] gc_reportedAchievement:achievement withError:error];
            });
        }];
    } else {
        [self saveAchievementToReportLater:identifier percentComplete:percentComplete];
    }
}

- (void)saveScoreToReportLater:(GKScore *)score {
    if(score.value == 0) {
        return;
    }
    NSData *scoreData = [NSKeyedArchiver archivedDataWithRootObject:score];
    id tmp = [[SDKCache cache] objectForKey:SDK_GC_SAVEDSCORES];
    NSMutableArray *savedScores = tmp ? [tmp mutableCopy] : nil;
    if (savedScores != nil) {
        [savedScores addObject:scoreData];
    } else {
        savedScores = [NSMutableArray arrayWithObject:scoreData];
    }
    
    [[SDKCache cache] setObject:savedScores forKey:SDK_GC_SAVEDSCORES];
    
    dispatch_async(dispatch_get_main_queue(), ^{
        if ([[self delegate] respondsToSelector:@selector(gc_didSaveScore:)]) {
            [[self delegate] gc_didSaveScore:score];
        }
    });
}

- (void)saveAchievementToReportLater:(NSString *)identifier percentComplete:(double)percentComplete {
    id tmp = [[SDKCache cache] objectForKey:SDK_GC_SAVEDACHIEVEMENTS];
    NSMutableDictionary *savedAchievements = tmp ? [tmp mutableCopy] : nil;
    if (savedAchievements) {
        double savedPercentCompleteValue = 0;
        NSNumber *savedPercentComplete = [savedAchievements objectForKey:identifier];
        
        if (savedPercentComplete != nil) {
            savedPercentCompleteValue = [savedPercentComplete doubleValue];
        }
        
        // Compare the saved percent and the percent that was just submitted, if the submitted percent is greater save it
        if (percentComplete > savedPercentCompleteValue) {
            savedPercentComplete = [NSNumber numberWithDouble:percentComplete];
            [savedAchievements setObject:savedPercentComplete forKey:identifier];
        }
    } else {
        savedAchievements = [NSMutableDictionary dictionaryWithObjectsAndKeys:[NSNumber numberWithDouble:percentComplete], identifier, nil];
    }
    [[SDKCache cache] setObject:savedAchievements forKey:SDK_GC_SAVEDACHIEVEMENTS];
    
    GKAchievement *achievement = [[GKAchievement alloc] initWithIdentifier:identifier];
    NSNumber *percentNumber = [NSNumber numberWithDouble:percentComplete];
    
    if (percentNumber && achievement) {
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_didSaveAchievement:)])
                [[self delegate] gc_didSaveAchievement:achievement];
        });
    } else {
        NSError *error = [NSError errorWithDomain:[NSString stringWithFormat:@"Could not save achievement because necessary data is missing. GameCenter needs an Achievement ID and Percent Completed to save the achievement. You provided the following data:\nAchievement: %@\nPercent Completed:%@", achievement, percentNumber]
                                             code:GCMErrorAchievementDataMissing userInfo:nil];
        
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                [[self delegate] gc_error:error];
        });
    }
}

//------------------------------------------------------------------------------------------------------------//
//------- Score, Achievement, and Challenge Retrieval --------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------//
#pragma mark - Score, Achievement, and Challenge Retrieval

- (long long)myHighScore:(NSString *)identifier
{
    id tmp = [_myScores objectForKey:identifier];
    return tmp ? [(NSNumber *)tmp longLongValue] : 0;
}

- (void)saveMyHighScore:(long long)score identifier:(NSString *)identifier
{
    [_myScores setObject:[NSNumber numberWithLongLong:score] forKey:identifier];
    [[SDKCache cache] setObject:_myScores forKey:SDK_GC_MYSCORES];
}

- (NSDictionary *)myHighScores:(NSArray *)identifiers
{
    return [_myScores dictionaryWithValuesForKeys:identifiers];
}

- (double)getAchievementProgress:(NSString *)identifier
{
    id tmp = [_myAchievements objectForKey:identifier];
    return tmp ? [(NSNumber *)tmp doubleValue] : 0;
}

- (void)saveMyAchievementProgress:(double)progress identifier:(NSString *)identifier
{
    [_myAchievements setObject:[NSNumber numberWithDouble:progress] forKey:identifier];
    [[SDKCache cache] setObject:_myAchievements forKey:SDK_GC_MYACHIEVEMENTS];
}

- (NSDictionary *)getAchievementProgresss:(NSArray *)identifiers
{
    return [_myAchievements dictionaryWithValuesForKeys:identifiers];
}

- (void)getChallengesWithCompletion:(void (^)(NSArray *challenges, NSError *error))handler
{
    if ([self isGameCenterAvailable]) {
        BOOL isGameCenterChallengeAPIAvailable = (NSClassFromString(@"GKChallenge")) != nil;
        
        if (isGameCenterChallengeAPIAvailable == YES) {
            [GKChallenge loadReceivedChallengesWithCompletionHandler:^(NSArray *challenges, NSError *error) {
                if (error == nil) {
                    handler(challenges, nil);
                } else {
                    handler(nil, error);
                }
            }];
        } else {
#if TARGET_OS_IPHONE
            NSError *error = [NSError errorWithDomain:[NSString stringWithFormat:@"GKChallenge Class is not available. GKChallenge is only available on iOS 6.0 and higher. Current iOS version: %@", [[UIDevice currentDevice] systemVersion]] code:GCMErrorFeatureNotAvailable userInfo:nil];
#else
            NSError *error = [NSError errorWithDomain:[NSString stringWithFormat:@"GKChallenge Class is not available. GKChallenge is only available on OS X 10.8.2 and higher."] code:GCMErrorFeatureNotAvailable userInfo:nil];
#endif
            if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                [[self delegate] gc_error:error];
        }
    } else {
        NSError *error = [NSError errorWithDomain:[NSString stringWithFormat:@"GameCenter Unavailable"] code:GCMErrorNotAvailable userInfo:nil];
        if ([[self delegate] respondsToSelector:@selector(gc_error:)])
            [[self delegate] gc_error:error];
    }
}

//------------------------------------------------------------------------------------------------------------//
//------- Presenting GameKit Controllers ---------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------//
#pragma mark - Presenting GameKit Controllers
#if TARGET_OS_IPHONE

- (void)presentAchievementsOnViewController:(UIViewController *)viewController
{
    GKGameCenterViewController *achievementsViewController = [[GKGameCenterViewController alloc] init];
    #if TARGET_OS_IOS || (TARGET_OS_IPHONE && !TARGET_OS_TV)
    achievementsViewController.viewState = GKGameCenterViewControllerStateAchievements;
    #endif
    achievementsViewController.gameCenterDelegate = self;
    [viewController presentViewController:achievementsViewController animated:YES completion:^{
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_gameCenterViewControllerPresented:)])
                [[self delegate] gc_gameCenterViewControllerPresented:YES];
        });
    }];
}

// left here for backwards compatibility
- (void)presentLeaderboardsOnViewController:(UIViewController *)viewController timeScope:(GKLeaderboardTimeScope)scope
{
    DLog(@"[GC]WARNING: Calling a deprecated SDKGCManager method that may become obsolete in future versions. Use presentLeaderboardsOnViewController: withLeaderboard: instead. %s", __PRETTY_FUNCTION__);
    [self presentLeaderboardsOnViewController:viewController withLeaderboard:nil timeScope:scope];
}

- (void)presentLeaderboardsOnViewController:(UIViewController *)viewController withLeaderboard:(NSString *)leaderboard timeScope:(GKLeaderboardTimeScope)scope {
    GKGameCenterViewController *leaderboardViewController = [[GKGameCenterViewController alloc] init];
    #if TARGET_OS_IOS || (TARGET_OS_IPHONE && !TARGET_OS_TV)
    leaderboardViewController.viewState = GKGameCenterViewControllerStateLeaderboards;
    /*
     Passing nil to leaderboardViewController.leaderboardIdentifier works fine,
     but to make sure futur updates will not brake, we'll check it first
     */
    if (leaderboard != nil) {
        leaderboardViewController.leaderboardIdentifier = leaderboard;
    }
    #elif TARGET_OS_TV
         #warning For tvOS you must set leaderboard ID's in the Assets catalogue - Click on this warning for more info.
        /**
         To get the Leaderboards to show up:
         1. Achievements and Leaderboards are merged into a single GameCenter view, with the Leaderboards shown above the achievements.
         2. For tvOS adding the Leaderboards to the GameViewController is all about the Image Assets.
         3. You must add a "+ -> GameCenter -> New Apple TV Leaderboard (or Set)." to your Image Asset.
         4. You must set the "Identifier" for this Leaderboard asset to exactly what your identifier is for each of your leaderboards. Example:
         grp.SDKGCManager.PlayerScores
         5. You must have the image sizes 659 × 371 for the Leaderboard Images.*/
    #endif
    leaderboardViewController.gameCenterDelegate = self;
    [leaderboardViewController setLeaderboardTimeScope:scope];
    [viewController presentViewController:leaderboardViewController animated:YES completion:^{
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_gameCenterViewControllerPresented:)])
                [[self delegate] gc_gameCenterViewControllerPresented:YES];
        });
    }];
}

- (void)presentChallengesOnViewController:(UIViewController *)viewController {
    GKGameCenterViewController *challengeViewController = [[GKGameCenterViewController alloc] init];
    #if TARGET_OS_IOS || (TARGET_OS_IPHONE && !TARGET_OS_TV)
    challengeViewController.viewState = GKGameCenterViewControllerStateChallenges;
    #endif
    challengeViewController.gameCenterDelegate = self;
    [viewController presentViewController:challengeViewController animated:YES completion:^{
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_gameCenterViewControllerPresented:)])
                [[self delegate] gc_gameCenterViewControllerPresented:YES];
        });
    }];
}
#endif

- (void)gameCenterViewControllerDidFinish:(GKGameCenterViewController *)gameCenterViewController {
#if TARGET_OS_IPHONE
    [gameCenterViewController dismissViewControllerAnimated:YES completion:^{
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_gameCenterViewControllerDidFinish:)])
                [[self delegate] gc_gameCenterViewControllerDidFinish:YES];
        });
    }];
#else
    [gameCenterViewController dismissViewController:gameCenterViewController];
    dispatch_async(dispatch_get_main_queue(), ^{
        if ([[self delegate] respondsToSelector:@selector(gc_gameCenterViewControllerDidFinish:)])
            [[self delegate] gc_gameCenterViewControllerDidFinish:YES];
    });
#endif
}

//------------------------------------------------------------------------------------------------------------//
//------- Resetting Data -------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------//
#pragma mark - Resetting Data

- (void)resetAchievementsWithCompletion:(void (^)(NSError *))handler {
    [[SDKCache cache] removeObjectForKey:SDK_GC_SAVEDACHIEVEMENTS];
    if ([self isGameCenterAvailable]) {
        [GKAchievement resetAchievementsWithCompletionHandler:^(NSError *error) {
            if (error == nil) {
                [[NSUserDefaults standardUserDefaults] setBool:NO forKey:[@"achievementsSynced" stringByAppendingString:[self localPlayerId]]];
                [[NSUserDefaults standardUserDefaults] synchronize];
                
                [self syncGameCenter];
                
                dispatch_async(dispatch_get_main_queue(), ^{
                    handler(nil);
                });
            } else {
                dispatch_async(dispatch_get_main_queue(), ^{
                    handler(error);
                });
            }
        }];
    }
}

//------------------------------------------------------------------------------------------------------------//
//------- Player Data ----------------------------------------------------------------------------------------//
//------------------------------------------------------------------------------------------------------------//
#pragma mark - Player Data

- (NSString *)localPlayerId {
    if ([self isGameCenterAvailable]) {
        if ([SDKGCManager getLocalPlayer].authenticated) {
            return [SDKGCManager getLocalPlayer].playerID;
        }
    }
    return @"unknownPlayer";
}

- (NSString *)localPlayerDisplayName {
    if ([self isGameCenterAvailable] && [SDKGCManager getLocalPlayer].authenticated) {
        if ([[SDKGCManager getLocalPlayer] respondsToSelector:@selector(displayName)]) {
            return [SDKGCManager getLocalPlayer].displayName;
        } else {
            return [SDKGCManager getLocalPlayer].alias;
        }
    }
    
    return @"unknownPlayer";
}

- (GKLocalPlayer *)localPlayerData {
    if ([self isGameCenterAvailable] && [SDKGCManager getLocalPlayer].authenticated) {
        return [SDKGCManager getLocalPlayer];
    } else {
        return nil;
    }
}

#if TARGET_OS_IPHONE
- (void)localPlayerPhoto:(void (^)(UIImage *playerPhoto))handler {
    if ([self isGameCenterAvailable]) {
        [[self localPlayerData] loadPhotoForSize:GKPhotoSizeNormal withCompletionHandler:^(UIImage *photo, NSError *error) {
            handler(photo);
            if (error) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                        [[self delegate] gc_error:error];
                });
            }
        }];
    } else {
        NSError *error = [NSError errorWithDomain:[NSString stringWithFormat:@"GameCenter Unavailable"] code:GCMErrorNotAvailable userInfo:nil];
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_error:)]) {
                [[self delegate] gc_error:error];
            }
        });
    }
}
#else
- (void)localPlayerPhoto:(void (^)(NSImage *playerPhoto))handler {
    if ([self isGameCenterAvailable]) {
        [[self localPlayerData] loadPhotoForSize:GKPhotoSizeNormal withCompletionHandler:^(NSImage *photo, NSError *error) {
            handler(photo);
            if (error) {
                dispatch_async(dispatch_get_main_queue(), ^{
                    if ([[self delegate] respondsToSelector:@selector(gc_error:)])
                        [[self delegate] gc_error:error];
                });
            }
        }];
    } else {
        NSError *error = [NSError errorWithDomain:[NSString stringWithFormat:@"GameCenter Unavailable"] code:GCMErrorNotAvailable userInfo:nil];
        dispatch_async(dispatch_get_main_queue(), ^{
            if ([[self delegate] respondsToSelector:@selector(gc_error:)]) {
                [[self delegate] gc_error:error];
            }
        });
    }
}
#endif

+ (GKLocalPlayer *)getLocalPlayer
{
    if (@available(iOS 13.0, *)) {
        return [GKLocalPlayer local];
    } else {
        return [GKLocalPlayer localPlayer];
    }
}
@end
