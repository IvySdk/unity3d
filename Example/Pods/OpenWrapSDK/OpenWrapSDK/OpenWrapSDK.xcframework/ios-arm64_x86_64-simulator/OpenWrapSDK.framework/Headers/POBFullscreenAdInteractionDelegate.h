
#ifndef POBFullscreenAdInteractionDelegate_h
#define POBFullscreenAdInteractionDelegate_h

NS_ASSUME_NONNULL_BEGIN
@class POBReward;
/**
 ------------------------------------------------------------------------------------
 POBFullscreenAdInteractionDelegate Protocol
 ------------------------------------------------------------------------------------
 */

/**
 This delegate is used to inform the fullscreen ad events from OpenWrap SDK ads. Implement this if your ad server implementation requires to be informed about these events.
 */
@protocol POBFullscreenAdInteractionDelegate <NSObject>
@optional
/**
 * @abstract OW SDK calls this method immediately after a fullscreen ad loads succesfully.
 *
 */
- (void)fullscreenAdEventDidLoad;

/**
 * @abstract OW SDK calls this method immediately after a fullscreen ad fails to load.
 * @param error The error object describing why the ad failed to load
 */
- (void)fullscreenAdEventFailedToLoadWithError:(NSError * _Nullable)error;

/**
 * @abstract OW SDK calls this method when the application has attempted to show a fullscreen ad and it cannot be shown.
 *
 * @discussion A common usage of this delegate method is when the application tries to play a fullscreen video ad and the ad is not available to play.
 *
 * @param error The error describing why the video couldn't play.
 */
- (void)fullscreenAdEventFailedToShowWithError:(NSError *)error;

/**
 * @abstract OW SDK calls this method when the user should be rewarded.
 *
 * @param reward The reward object that contains the currency type as well as the amount that should be rewarded to the user.
 */
- (void)fullscreenAdEventWillRewardUser:(POBReward*)reward;

/**
 * @abstract OW SDK calls this method if a previously loaded fullscreen ad got expired.
 */
- (void)fullscreenAdEventDidExpire;

/**
 * @abstract OW SDK calls this method when a fullscreen ad is about to appear.
 *
 * @discussion If OW SDK is integrated using mediation waterfall with your ad server, you **must** use this method to
 * inform the 'ad is about to present(willAppear)' event to your ad server SDK to prevent stalling of future ad requests
 */
- (void)fullscreenAdEventWillAppear;

/**
 * @abstract OW SDK calls this method when a fullscreen ad has finished appearing.
 *
 * @discussion If OW SDK is integrated using mediation waterfall with your ad server, you **must** use this method to
 * inform the 'ad has finished appearing(didAppear)' event to your ad server SDK to prevent stalling of future ad requests
 */
- (void)fullscreenAdEventDidAppear;

/**
 * @abstract OW SDK calls this method when a fullscreen ad is about to disappear.
 *
 * @discussion If OW SDK is integrated using mediation waterfall with your ad server, you **must** use this method to
 * inform the 'ad is about to dismiss(willDisappear)' event to your ad server SDK to prevent stalling of future ad requests
 */
- (void)fullscreenAdEventWillDisappear;

/**
 * @abstract OW SDK calls this method when a fullscreen ad has finished disappearing.
 *
 * @discussion If OW SDK is integrated using mediation waterfall with your ad server, you **must** use this method to
 * inform the 'ad has finished dismissing(didDisappear)' event to your ad server SDK to prevent stalling of future ad requests
 */
- (void)fullscreenAdEventDidDisappear;

/**
 * @abstract OW SDK calls this method when the fullscreen ad will cause the user to leave the application.
 *
 */
- (void)fullscreenAdEventWillLeaveApplication;

/*!
@abstract Optional method to receive instruction to track the impression.
*/
- (void)fullscreenAdEventImpression;

/*!
@abstract Optional method to receive instruction to track the ad click.
*/
- (void)fullscreenAdEventClick;
NS_ASSUME_NONNULL_END
@end


#endif /* POBFullscreenAdInteractionDelegate_h */
