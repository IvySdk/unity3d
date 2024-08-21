# Chartboost SDK for iOS

*Version 9.5.1*

The Chartboost iOS SDK is the cornerstone of the Chartboost network. It
provides the functionality for showing interstitial, rewarded and banner ads.


## Usage
1. Before you begin:
 - [Have you signed up for a Chartboost account?](https://www.chartboost.com/signup/)
 - [Did you add an app to your dashboard?](https://answers.chartboost.com/hc/en-us/articles/200797729)
 - [Did you download the latest SDK?](https://answers.chartboost.com/en-us/articles/download)
 - [Do you have an active publishing campaign?](https://answers.chartboost.com/hc/en-us/articles/200797729)
 - Note only iOS 11.0 or higher is supported.

2. Link the ChartboostSDK.xcframework and add the ChartboostSDKResources.bundle to your project, or use CocoaPods to manage the framework dependency for you.

3. Add value "-ObjC" in "Other Linker Flags" for both Debug and Release.

4. Add a new dictionary with Chartboost's SKAdNetworkIdentifier value `f38h382jlk.skadnetwork` and additional identifiers to the `SKAdNetworkItems` array in your Info.plist.
Make sure to check our [online documentation](https://answers.chartboost.com/en-us/child_article/ios) for a full required SKAdNetwork IDs list.
```
<key>SKAdNetworkItems</key>
<array>
  <dict>
    <key>SKAdNetworkIdentifier</key>
    <string>f38h382jlk.skadnetwork</string>
  </dict>
</array>
```

5. Add the import header `#import <ChartboostSDK/ChartboostSDK.h>` to your AppDelegate.m file.

6. Initialize Chartboost in your `didFinishLaunchingWithOptions` method.
```
(BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
    // Initialize the Chartboost library
    [Chartboost startWithAppId:@"YOUR_CHARTBOOST_APP_ID"
                  appSignature:@"YOUR_CHARTBOOST_APP_SIGNATURE"
                    completion:^(CHBStartError * _Nullable error) {
                        if (error) {
                            NSLog(@"Chartboost SDK initialization finished with error %@", error);
                        } else {
                            NSLog(@"Chartboost SDK initialization finished with success");
                        }
                    }];
    return YES;
}
```

 **Please note:**
 - `startWithAppId:appSignature:completion:` must always be called on bootup, no matter what other actions your app takes.

7. Add your app ID and app signature.
    - Replace `YOUR_CHARTBOOST_APP_ID` and `YOUR_CHARTBOOST_APP_SIGNATURE` with your app ID and app signature.
    - [Where can I find my app ID and app signature?](https://answers.chartboost.com/en-us/articles/209981506#appidappsign)

8. Learn how to load and show ads with Chartboost SDK.
 - Check out our Example app (more info below).
 - Read the headers included in the Chartboost framework. They contain detailed explanations and code examples. See `Chartboost.h`, `CHBInterstitial.h`, `CHBRewarded.h`, `CHBBanner.h` and `CHBAdDelegate.h`. 
 - Check our [online documentation](https://answers.chartboost.com/en-us/child_article/ios) to learn more.

9. Test your integration.
    - Build and run your project from Xcode on a device or Simulator.
    - [If you have an active publishing campaign and have integrated "show interstitial" or "show rewarded video" calls, you should see live ads.] (https://answers.chartboost.com/hc/en-us/articles/204930539)
    - [If you don't have any publishing campaigns and you've still integrated these calls, you can use Test Mode to see if test ads show up.](https://answers.chartboost.com/hc/en-us/articles/200780549)
    - [Why can't I see ads in my game?](https://answers.chartboost.com/en-us/articles/209981506#cantseeads)

10. Check the SDK icon in the Chartboost dashboard.
    - Go to your app's App Settings > Basic Settings in your [dashboard](https://dashboard.chartboost.com/).
    - When our servers successfully receive a bootup call from our SDK using your app ID, the SDK icon underneath your app’s icon will turn from gray to green.


## Example App

Check out our [example app](http://github.com/ChartBoost/ios-sdk-example/) which showcases how to integrate the Chartboost SDK. 
