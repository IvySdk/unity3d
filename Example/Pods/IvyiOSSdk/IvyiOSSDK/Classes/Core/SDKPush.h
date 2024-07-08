//
//  SDKPush.h
//  Pods
//
//  Created by 余冰星 on 2017/9/20.
//
//

#import <Foundation/Foundation.h>
@interface SDKPush : NSObject
{
    @protected
    NSDictionary *_conf;
    NSString *_pushServerUrl;
    NSString *_token;
}
@property (nonatomic, nullable, strong) NSString *token;
@property (nonatomic, nullable, strong) NSString *lastSendToken;
-(void)setup:(nonnull NSDictionary *)conf;
-(void)submitTokenToServer;
-(void)subscribeToTopic:(nonnull NSString *)topic;
-(void)unsubscribeFromTopic:(nonnull NSString *)topic;
/** 发送远程通知
 @param key                 消息的id
 @param title               消息的标题
 @param content             消息内容
 @param useLocalTimeZone    是否按照收件人时区发送
 @param pushTime            发送的时间戳，精确到秒
 @param facebookIds         用户的facebook id列表，多个ID以逗号“,”分隔，下同
 @param uuids               设备的唯一识别码列表，多个ID以逗号“,”分隔，下同
 @param topic               对注册对应主题的设备发送消息(参数优先级：facebookIds, uuids, topic)
 @param iosBadge            ios设备的角标
 @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
 @param soundName           默认为default，如需自定义请设置该选项
 @param data                捆绑的KV对，格式为json字符串："{\"hello\":\"world\"}"
 */
- (void)pushMessage:(nullable NSString *)key title:(nonnull NSString *)title content:(nonnull NSString *)content pushTime:(long)pushTime useLocalTimeZone:(BOOL)useLocalTimeZone facebookIds:(nullable NSString *)facebookIds uuids:(nullable NSString *)uuids topic:(nullable NSString *)topic iosBadge:(int)iosBadge useSound:(BOOL)useSound soundName:(nullable NSString *)soundName extraData:(nullable NSDictionary *)data;

- (void)cancelPush:(nonnull NSString *)key;
///** 发送循环的远程通知
// @param messageId           消息ID，如果messageId后台已存在，将覆盖后台的push任务
// @param title               消息的标题
// @param content             消息内容
// @param useLocalTimeZone    是否按照收件人时区发送
// @param pushTime            发送的时间戳，精确到秒
// @param facebookIds         用户的facebook id列表，多个ID以逗号“,”分隔，下同
// @param uuids               设备的唯一识别码列表，多个ID以逗号“,”分隔，下同
// @param topic               对注册对应主题的设备发送消息(参数优先级：facebookIds, uuids, topic)
// @param iosBadge            ios设备的角标
// @param useSound            是否自定义消息的声音,如果未true，请设置下面一项
// @param soundName           默认为default，如需自定义请设置该选项
// @param data                捆绑的KV对，格式为json字符串："{\"hello\":\"world\"}"
// @param repeatInterval      重复发送周期，<=0时不重复发送，单位为秒
// */
//- (void)pushRepeatMessage:(nonnull NSString *)messageId title:(nonnull NSString *)title content:(nonnull NSString *)content pushTime:(long)pushTime useLocalTimeZone:(BOOL)useLocalTimeZone facebookIds:(nullable NSString *)facebookIds uuids:(nullable NSString *)uuids topic:(nullable NSString *)topic iosBadge:(int)iosBadge useSound:(BOOL)useSound soundName:(nullable NSString *)soundName extraData:(nullable NSDictionary *)data repeatInterval:(int)repeatInterval;
///** 取消循环远程通知
// @param messageId           消息ID
// */
//-(void)cancelRepeatPush:(nonnull NSString *)messageId;
- (void)didReceiveRemoteNotification:(nonnull NSDictionary *)userInfo;
- (void)didFailToRegisterForRemoteNotificationsWithError:(nonnull NSError *)error;
- (void)didRegisterForRemoteNotificationsWithDeviceToken:(nonnull NSData *)deviceToken;
- (void)applicationDidBecomeActive:(nullable UIApplication *)application;
- (void)applicationDidEnterBackground:(nullable UIApplication *)application;
@end
