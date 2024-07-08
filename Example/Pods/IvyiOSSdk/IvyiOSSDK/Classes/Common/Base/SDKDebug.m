//
//  SDKDebug.m
//  BGFMDB
//
//  Created by 余冰星 on 2020/3/7.
//
#import <sys/utsname.h>
#import "NSString+URLEncoding.h"
#import "SDKDebug.h"
@implementation SDKDebug
{
//    @private
//    SRWebSocket *_ws;
////    NSDateFormatter *_dateFormatter;
//    long _color;
}
#pragma mark -
#pragma mark override
//-(instancetype)init
//{
//    self = [super init];
//    NSMutableArray<NSNumber *> *_Colors = [[NSMutableArray alloc] init];
//    [_Colors addObject:@(0xc1cbd7)];
//    [_Colors addObject:@(0xafb0b2)];
//    [_Colors addObject:@(0xe0e5df)];
//    [_Colors addObject:@(0xb5c4b1)];
//    [_Colors addObject:@(0xececea)];
//    [_Colors addObject:@(0xfffaf4)];
//    [_Colors addObject:@(0x9ca8b8)];
//    [_Colors addObject:@(0xdfd7d7)];
//    [_Colors addObject:@(0xd8caaf)];
//    [_Colors addObject:@(0xc5b8a5)];
//    [_Colors addObject:@(0xfdf9ee)];
//    [_Colors addObject:@(0xf0ebe5)];
//    [_Colors addObject:@(0xd3d4cc)];
//    [_Colors addObject:@(0xe0cdcf)];
//    [_Colors addObject:@(0xb7b1a5)];
//    [_Colors addObject:@(0xdadad8)];
//    [_Colors addObject:@(0xf8ebd8)];
//    [_Colors addObject:@(0xf0ebe5)];
//    [_Colors addObject:@(0xcac3bb)];
//    [_Colors addObject:@(0xead0d1)];
//    [_Colors addObject:@(0xfaead3)];
//    [_Colors addObject:@(0xc7b8a1)];
//    [_Colors addObject:@(0xc9c0d3)];
//    [_Colors addObject:@(0xeee5f8)];
//    _color = [_Colors objectAtIndex:(arc4random() % _Colors.count)].longValue;
//    
////    10.80.1.131:8000  10.80.1.11:9000
//    _ws = [[SRWebSocket alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"ws://10.80.1.11:9000/binlog/%@/%@", [UIDevice currentDevice].name.urlEncodeString, [NSBundle mainBundle].bundleIdentifier]]];
//    _ws.delegate = self;
//    [_ws open];
////    _dateFormatter = [[NSDateFormatter alloc] init];
////    [_dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss.SSS"];
//    return self;
//}

#pragma mark -
#pragma mark static
+ (SDKDebug *)debug
{
    static SDKDebug *_instance = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _instance = [[SDKDebug alloc] init];
    });
    return _instance;
}
//#pragma mark -
//#pragma mark websocket
//-(void)webSocketDidOpen:(SRWebSocket *)webSocket
//{
//}
//
//-(void)webSocket:(SRWebSocket *)webSocket didReceivePong:(NSData *)pongPayload
//{
//}
//
//-(void)webSocket:(SRWebSocket *)webSocket didFailWithError:(NSError *)error
//{
//}
//
//- (void)webSocket:(SRWebSocket *)webSocket didReceiveMessage:(id)message {
//}
//
//-(NSString *)logWeb:(NSString *)msg
//{
//    if (msg && _ws.readyState == SR_OPEN) {
//        NSMutableData *data = [[NSMutableData alloc] init];
//        int lv = 2;
//        [data appendBytes:&lv length:sizeof(lv)];
//        [data appendBytes:&_color length:sizeof(_color)];
//        long time = [NSDate date].timeIntervalSince1970;
//        [data appendBytes:&time length:sizeof(time)];
//        [data appendData:[msg dataUsingEncoding:NSUTF8StringEncoding]];
//        [_ws sendData:data error:nil];
//    }
//    return msg;
//}

+ (nonnull NSString *)deviceModel
{
    struct utsname systemInfo;
    uname(&systemInfo);
    NSString *deviceModel = [NSString stringWithCString:systemInfo.machine
                                               encoding:NSUTF8StringEncoding];
    
    // 模拟器
    if ([deviceModel isEqualToString:@"i386"])         return @"Simulator";
    if ([deviceModel isEqualToString:@"x86_64"])       return @"Simulator";
    
    // iPhone 系列
    if ([deviceModel isEqualToString:@"iPhone1,1"])    return @"iPhone 1G";
    if ([deviceModel isEqualToString:@"iPhone1,2"])    return @"iPhone 3G";
    if ([deviceModel isEqualToString:@"iPhone2,1"])    return @"iPhone 3GS";
    if ([deviceModel isEqualToString:@"iPhone3,1"])    return @"iPhone 4 (GSM)";
    if ([deviceModel isEqualToString:@"iPhone3,2"])    return @"Verizon iPhone 4";
    if ([deviceModel isEqualToString:@"iPhone3,3"])    return @"iPhone 4 (CDMA/Verizon/Sprint)";
    if ([deviceModel isEqualToString:@"iPhone4,1"])    return @"iPhone 4S";
    if ([deviceModel isEqualToString:@"iPhone5,1"])    return @"iPhone 5";
    if ([deviceModel isEqualToString:@"iPhone5,2"])    return @"iPhone 5";
    if ([deviceModel isEqualToString:@"iPhone5,3"])    return @"iPhone 5C";
    if ([deviceModel isEqualToString:@"iPhone5,4"])    return @"iPhone 5C";
    if ([deviceModel isEqualToString:@"iPhone6,1"])    return @"iPhone 5S";
    if ([deviceModel isEqualToString:@"iPhone6,2"])    return @"iPhone 5S";
    if ([deviceModel isEqualToString:@"iPhone7,1"])    return @"iPhone 6 Plus";
    if ([deviceModel isEqualToString:@"iPhone7,2"])    return @"iPhone 6";
    if ([deviceModel isEqualToString:@"iPhone8,1"])    return @"iPhone 6s";
    if ([deviceModel isEqualToString:@"iPhone8,2"])    return @"iPhone 6s Plus";
    if ([deviceModel isEqualToString:@"iPhone8,4"])    return @"iPhone SE";
    if ([deviceModel isEqualToString:@"iPhone9,1"])    return @"iPhone 7 (CDMA)";
    if ([deviceModel isEqualToString:@"iPhone9,3"])    return @"iPhone 7 (GSM)";
    if ([deviceModel isEqualToString:@"iPhone9,2"])    return @"iPhone 7 Plus (CDMA)";
    if ([deviceModel isEqualToString:@"iPhone9,4"])    return @"iPhone 7 Plus (GSM)";
    if ([deviceModel isEqualToString:@"iPhone10,1"])    return @"iPhone 8 (CDMA)";
    if ([deviceModel isEqualToString:@"iPhone10,4"])    return @"iPhone 8 (GSM)";
    if ([deviceModel isEqualToString:@"iPhone10,2"])    return @"iPhone 8 Plus (CDMA)";
    if ([deviceModel isEqualToString:@"iPhone10,5"])    return @"iPhone 8 Plus (GSM)";
    if ([deviceModel isEqualToString:@"iPhone10,3"])    return @"iPhone X (CDMA)";
    if ([deviceModel isEqualToString:@"iPhone10,6"])    return @"iPhone X (GSM)";
    
    // iPod 系列
    if ([deviceModel isEqualToString:@"iPod1,1"])      return @"iPod Touch 1G";
    if ([deviceModel isEqualToString:@"iPod2,1"])      return @"iPod Touch 2G";
    if ([deviceModel isEqualToString:@"iPod3,1"])      return @"iPod Touch 3G";
    if ([deviceModel isEqualToString:@"iPod4,1"])      return @"iPod Touch 4G";
    if ([deviceModel isEqualToString:@"iPod5,1"])      return @"iPod Touch 5G";
    if ([deviceModel isEqualToString:@"iPod7,1"])      return @"iPod Touch 6G";
    
    // iPad 系列
    if ([deviceModel isEqualToString:@"iPad1,1"])      return @"iPad";
    if ([deviceModel isEqualToString:@"iPad1,2"])      return @"iPad 3G";
    
    if ([deviceModel isEqualToString:@"iPad2,1"])      return @"iPad 2 (WiFi)";
    if ([deviceModel isEqualToString:@"iPad2,2"])      return @"iPad 2 (GSM)";
    if ([deviceModel isEqualToString:@"iPad2,3"])      return @"iPad 2 (CDMA)";
    if ([deviceModel isEqualToString:@"iPad2,4"])      return @"iPad 2 (32nm)";
    if ([deviceModel isEqualToString:@"iPad2,5"])      return @"iPad Mini (WiFi)";
    if ([deviceModel isEqualToString:@"iPad2,6"])      return @"iPad Mini (GSM)";
    if ([deviceModel isEqualToString:@"iPad2,7"])      return @"iPad Mini (CDMA)";
    
    if ([deviceModel isEqualToString:@"iPad3,1"])      return @"iPad 3(WiFi)";
    if ([deviceModel isEqualToString:@"iPad3,2"])      return @"iPad 3(CDMA)";
    if ([deviceModel isEqualToString:@"iPad3,3"])      return @"iPad 3(4G)";
    if ([deviceModel isEqualToString:@"iPad3,4"])      return @"iPad 4 (WiFi)";
    if ([deviceModel isEqualToString:@"iPad3,5"])      return @"iPad 4 (4G)";
    if ([deviceModel isEqualToString:@"iPad3,6"])      return @"iPad 4 (CDMA)";
    
    if ([deviceModel isEqualToString:@"iPad4,1"])      return @"iPad Air";
    if ([deviceModel isEqualToString:@"iPad4,2"])      return @"iPad Air";
    if ([deviceModel isEqualToString:@"iPad4,3"])      return @"iPad Air";
    if ([deviceModel isEqualToString:@"iPad4,4"])      return @"iPad Mini 2";
    if ([deviceModel isEqualToString:@"iPad4,5"])      return @"iPad Mini 2";
    if ([deviceModel isEqualToString:@"iPad4,6"])      return @"iPad Mini 2";
    if ([deviceModel isEqualToString:@"iPad4,7"])      return @"iPad Mini 3";
    if ([deviceModel isEqualToString:@"iPad4,8"])      return @"iPad Mini 3";
    if ([deviceModel isEqualToString:@"iPad4,9"])      return @"iPad Mini 3";
    
    if ([deviceModel isEqualToString:@"iPad5,1"])      return @"iPad Mini 4";
    if ([deviceModel isEqualToString:@"iPad5,2"])      return @"iPad Mini 4";
    if ([deviceModel isEqualToString:@"iPad5,3"])      return @"iPad Air 2";
    if ([deviceModel isEqualToString:@"iPad5,4"])      return @"iPad Air 2";
    
    if ([deviceModel isEqualToString:@"iPad6,3"])      return @"iPad PRO (12.9)";
    if ([deviceModel isEqualToString:@"iPad6,4"])      return @"iPad PRO (12.9)";
    if ([deviceModel isEqualToString:@"iPad6,7"])      return @"iPad PRO (9.7)";
    if ([deviceModel isEqualToString:@"iPad6,8"])      return @"iPad PRO (9.7)";
    if ([deviceModel isEqualToString:@"iPad6,11"])      return @"iPad 5";
    if ([deviceModel isEqualToString:@"iPad6,12"])      return @"iPad 5";
    
    if ([deviceModel isEqualToString:@"iPad7,1"])      return @"iPad PRO 2 (12.9)";
    if ([deviceModel isEqualToString:@"iPad7,2"])      return @"iPad PRO 2 (12.9)";
    if ([deviceModel isEqualToString:@"iPad7,3"])      return @"iPad PRO (10.5)";
    if ([deviceModel isEqualToString:@"iPad7,4"])      return @"iPad PRO (10.5)";
    
    return deviceModel;
}

@end
