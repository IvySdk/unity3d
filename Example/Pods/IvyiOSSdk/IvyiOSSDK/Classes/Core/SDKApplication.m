//
//  SDKApplication.m
//  Pods
//
//  Created by IceStar on 2017/9/14.
//
//

#import "SDKApplication.h"
#import "SDKFacade.h"
@implementation SDKApplication
-(BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    return [[SDKFacade sharedInstance] application:application didFinishLaunchingWithOptions:launchOptions];
}
-(void)applicationDidBecomeActive:(UIApplication *)application
{
    [[SDKFacade sharedInstance] applicationDidBecomeActive:application];
}
-(void)applicationDidEnterBackground:(UIApplication *)application
{
    [[SDKFacade sharedInstance] applicationDidEnterBackground:application];
}
-(void)application:(UIApplication *)application didFailToRegisterForRemoteNotificationsWithError:(NSError *)error
{
    [[SDKFacade sharedInstance] application:application didFailToRegisterForRemoteNotificationsWithError:error];
}
-(void)application:(UIApplication *)application didRegisterForRemoteNotificationsWithDeviceToken:(NSData *)deviceToken
{
    [[SDKFacade sharedInstance] application:application didRegisterForRemoteNotificationsWithDeviceToken:deviceToken];
}
-(void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo
{
    [[SDKFacade sharedInstance]application:application didReceiveRemoteNotification:userInfo];
}
-(void)application:(UIApplication *)application didReceiveRemoteNotification:(NSDictionary *)userInfo fetchCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler
{
    if (completionHandler) {
        completionHandler(UIBackgroundFetchResultNewData);
    }
    [[SDKFacade sharedInstance]application:application didReceiveRemoteNotification:userInfo];
}
-(void)application:(UIApplication *)application didReceiveLocalNotification:(UILocalNotification *)notification
{
    [[SDKFacade sharedInstance] application:application didReceiveLocalNotification:notification];
}
-(BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation
{
    return [[SDKFacade sharedInstance] application:application openURL:url sourceApplication:sourceApplication annotation:annotation];
}
-(BOOL)application:(UIApplication *)application openURL:(NSURL *)url options:(NSDictionary *)options
{
    return [[SDKFacade sharedInstance] application:application openURL:url options:options];
}

-(BOOL)application:(UIApplication *)application continueUserActivity:(NSUserActivity *)userActivity restorationHandler:(void (^)(NSArray<id<UIUserActivityRestoring>> * _Nullable))restorationHandler
{
    return [[SDKFacade sharedInstance] application:application continueUserActivity:userActivity restorationHandler:restorationHandler];
}

- (void)applicationWillTerminate:(UIApplication *)application
{
    [[SDKFacade sharedInstance] applicationWillTerminate:application];
}
@end
