//
//  SDKAutoEvent.m
//  FBSDKCoreKit
//
//  Created by 余冰星 on 2020/1/9.
//

#import "SDKAutoEvent.h"
#import <IvyiOSSdk/SDKFacade.h>
#import <IvyiOSSdk/SDKCache.h>
#import <IvyiOSSdk/SDKCache.h>
NSString * const _EVENT_SEND_COUNT = @"ESC";
NSString * const _TARGET_SEND = @"TS";

@interface _SDKAutoEvent : NSObject
@property (nonatomic, nullable, strong) NSString *sendEvent;
@property (nonatomic, nullable, strong) NSString *e1;
@property (nonatomic, nullable, strong) NSString *op;
@property (nonatomic, assign) int v;
@property (nonatomic, assign) int d;
@property (nonatomic, assign) BOOL r;
-(void)check;
-(BOOL)isValid;
-(long)getCreateTime;
@end
@implementation _SDKAutoEvent
{
    @private
    BOOL _isValid;
}
@synthesize sendEvent = _sendEvent;
@synthesize e1 = _e1;
@synthesize op = _op;
@synthesize d = _d;
@synthesize v = _v;
@synthesize r = _r;
-(instancetype)init
{
    self = [super init];
    _op = @">0";
    _r = NO;
    _d = _v = 0;
    _isValid = YES;
    return self;
}

-(long) getCreateTime {
    //事件在cache中的键值
    NSString* fotKey = [NSString stringWithFormat:@"%@:%@:create", _TARGET_SEND, _sendEvent];
    //获取系统当前时间
    long firstOpenTime = [SDKHelper currentTime];
    //cache中是否存在对应键值
    if([[SDKCache cache] containsObjectForKey:fotKey]){
        //存在  获取cache 中的数值
        firstOpenTime = ((NSNumber*)[[SDKCache cache] objectForKey:fotKey]).longValue;
    }else {
        //不存在 cache写入事件创建时间
        [[SDKCache cache] setObject:[NSNumber numberWithLong:firstOpenTime] forKey:fotKey];
    }
    return firstOpenTime;
}

-(BOOL)isValid
{
    if (_isValid) {
        if (_sendEvent && _e1 && _v > 0) {
            long firstOpenTime = [self getCreateTime];
            long now = [SDKHelper currentTime];
            if (_d > 0 && now - firstOpenTime > _d * 86400) {
                _isValid = NO;
            }
            NSString *key = [NSString stringWithFormat:@"%@:%@", _TARGET_SEND, _sendEvent];
            if ([[SDKCache cache] containsObjectForKey:key]) {
                _isValid = NO;
            }
        }
    }
    return _isValid;
}

-(void)check
{
    if(_isValid) {
        long firstOpenTime = [self getCreateTime];
        long now = [SDKHelper currentTime];
        if (_d > 0 && now - firstOpenTime > _d * 86400) {
            _isValid = NO;
            return;
        }
        SDKCache *cache = [SDKCache cache];
        int count = 0;
        NSString *key = [NSString stringWithFormat:@"%@:%@:%@", _EVENT_SEND_COUNT, _e1, _sendEvent];
        id tmp = [cache objectForKey:key];
        if (tmp) {
            count = [(NSNumber *)tmp intValue];
        }
        count++;
        [cache setObject:[NSNumber numberWithInt:count] forKey:key];
        BOOL flag = NO;
        if ([_op isEqualToString:@">="]) {
            flag = count >= _v;
        } else if ([_op isEqualToString:@">"]) {
            flag = count > _v;
        } else if ([_op isEqualToString:@"<="]) {
            flag = count <= _v;
        } else if ([_op isEqualToString:@"<"]) {
            flag = count < _v;
        } else if ([_op isEqualToString:@"="]) {
            flag = count > _v;
        }
        if (flag) {
            [[SDKFacade sharedInstance] logEventWithoutCheck:_sendEvent];
            if (_r) {
                [cache setObject:[NSNumber numberWithInt:0] forKey:key];
            } else {
                key = [NSString stringWithFormat:@"%@:%@", _TARGET_SEND, _sendEvent];
                [cache setObject:[NSNumber numberWithBool:YES] forKey:key];
                _isValid = NO;
            }
        }
    }
}
@end

@implementation SDKAutoEvent
{
    @private
    NSMutableArray<_SDKAutoEvent *> *_arr;
    NSString *_targetEvent;
}

-(instancetype)initWithTargetEvent:(NSString *)targetEvent
{
    self = [super init];
    _targetEvent = targetEvent;
    return self;
}

-(void)add:(NSString *)sendEvent data:(NSDictionary *)obj
{
    _SDKAutoEvent *e = [[_SDKAutoEvent alloc] init];
    e.sendEvent = sendEvent;
    for (NSString *k in obj) {
        if ([k isEqualToString:@"e1"]) {
            e.e1 = obj[k];
        } else if ([k isEqualToString:@"v"]) {
            e.v = [(NSNumber *)obj[k] intValue];
        } else if ([k isEqualToString:@"op"]) {
            e.op = obj[k];
        } else if ([k isEqualToString:@"d"]) {
            e.d = [(NSNumber *)obj[k] intValue];
        } else if ([k isEqualToString:@"r"]) {
            e.r = [(NSNumber *)obj[k] boolValue];
        }
    }
    if ([e isValid]) {
        if (!_arr) {
            _arr = [[NSMutableArray alloc] init];
        }
        [_arr addObject:e];
    }
}

-(BOOL)isValid
{
    return _arr && _arr.count > 0;
}

-(void)check
{
    for (_SDKAutoEvent *e in _arr) {
        [e check];
    }
}

@end
