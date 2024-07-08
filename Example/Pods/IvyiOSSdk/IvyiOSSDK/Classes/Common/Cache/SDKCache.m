//
//  SDKCache.m
//  SDKCache <https://github.com/ibireme/SDKCache>
//
//  Created by ibireme on 15/2/13.
//  Copyright (c) 2015 ibireme.
//
//  This source code is licensed under the MIT-style license found in the
//  LICENSE file in the root directory of this source tree.
//

#import "SDKCache.h"
#import "SDKMemoryCache.h"
#import "SDKDiskCache.h"
NSString * const SDK_CACHE_EXPIRED_TIMES = @"SDK_CACHE_EXPIRED_TIMES";
@implementation SDKCache
{
    @private
    NSMutableDictionary<NSString *, NSNumber *> *_expiredTimes;
}
@synthesize expiredTimes = _expiredTimes;
- (instancetype) init {
    DLog(@"[cache] Use \"initWithName\" or \"initWithPath\" to create SDKCache instance.");
    return [self initWithPath:@""];
}

- (instancetype)initWithName:(NSString *)name {
    if (name.length == 0) return nil;
    NSString *cacheFolder = [NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, YES) firstObject];
    NSString *path = [cacheFolder stringByAppendingPathComponent:name];
    return [self initWithPath:path];
}

- (instancetype)initWithPath:(NSString *)path {
    if (path.length == 0) return nil;
    SDKDiskCache *diskCache = [[SDKDiskCache alloc] initWithPath:path];
    if (!diskCache) return nil;
    NSString *name = [path lastPathComponent];
    SDKMemoryCache *memoryCache = [SDKMemoryCache new];
    memoryCache.name = name;
    
    self = [super init];
    _name = name;
    _diskCache = diskCache;
    _memoryCache = memoryCache;
    [self checkAndRemoveExpiredKeys];
    return self;
}

+ (instancetype)cacheWithName:(NSString *)name {
    return [[SDKCache alloc] initWithName:name];
}

+ (instancetype)cacheWithPath:(NSString *)path {
    return [[SDKCache alloc] initWithPath:path];
}

-(NSMutableDictionary *)expiredTimes
{
    if (!_expiredTimes) {
        _expiredTimes = (NSMutableDictionary *)[self objectForKey:SDK_CACHE_EXPIRED_TIMES];
        if (!_expiredTimes) {
            _expiredTimes = [[NSMutableDictionary alloc] init];
        }
    }
    return _expiredTimes;
}

-(void)checkAndRemoveExpiredKeys
{
    if ([self containsObjectForKey:SDK_CACHE_EXPIRED_TIMES]) {
        if (self.expiredTimes.count > 0) {
            long now = [NSDate date].timeIntervalSince1970;
            for (NSString *key in self.expiredTimes.allKeys) {
                if ([self containsObjectForKey:key]) {
                    NSNumber *expiredTime = (NSNumber *)[self.expiredTimes objectForKey:key];
                    long _expiredTime = expiredTime ? expiredTime.longValue : 0;
                    if (now > _expiredTime) {
                        [self removeObjectForKey:key];
                        [self.expiredTimes removeObjectForKey:key];
                    }
                } else {
                    [self.expiredTimes removeObjectForKey:key];
                }
            }
        }
        if (self.expiredTimes.count <= 0) {
            [self removeObjectForKey:SDK_CACHE_EXPIRED_TIMES];
        }
    }
}

-(void)setTTL:(long)ttl key:(nonnull NSString *)key
{
    NSNumber *_time = [NSNumber numberWithLong:([NSDate date].timeIntervalSince1970 + ttl)];
    [self.expiredTimes setObject:_time forKey:key];
    [self setObject:self.expiredTimes forKey:SDK_CACHE_EXPIRED_TIMES];
}

- (BOOL)containsObjectForKey:(NSString *)key {
    return [_memoryCache containsObjectForKey:key] || [_diskCache containsObjectForKey:key];
}

- (void)containsObjectForKey:(NSString *)key withBlock:(void (^)(NSString *key, BOOL contains))block {
    if (!block) return;
    
    if ([_memoryCache containsObjectForKey:key]) {
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            block(key, YES);
        });
    } else  {
        [_diskCache containsObjectForKey:key withBlock:block];
    }
}

- (id<NSCoding>)objectForKey:(NSString *)key {
    id<NSCoding> object = [_memoryCache objectForKey:key];
    if (!object) {
        object = [_diskCache objectForKey:key];
        if (object) {
            [_memoryCache setObject:object forKey:key];
        }
    }
    return object;
}

- (void)objectForKey:(NSString *)key withBlock:(void (^)(NSString *key, id<NSCoding> object))block {
    if (!block) return;
    id<NSCoding> object = [_memoryCache objectForKey:key];
    if (object) {
        dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
            block(key, object);
        });
    } else {
        [_diskCache objectForKey:key withBlock:block];
    }
}

- (void)setObject:(id<NSCoding>)object forKey:(NSString *)key {
    [_memoryCache setObject:object forKey:key];
    [_diskCache setObject:object forKey:key];
}

- (void)setObject:(id<NSCoding>)object forKey:(NSString *)key withTTL:(long)ttl {
    [_memoryCache setObject:object forKey:key];
    [_diskCache setObject:object forKey:key];
    [self setTTL:ttl key:key];
}

- (void)setObject:(id<NSCoding>)object forKey:(NSString *)key withBlock:(void (^)(void))block {
    [_memoryCache setObject:object forKey:key];
    [_diskCache setObject:object forKey:key withBlock:block];
}

- (void)removeObjectForKey:(NSString *)key {
    [_memoryCache removeObjectForKey:key];
    [_diskCache removeObjectForKey:key];
}

- (void)removeObjectForKey:(NSString *)key withBlock:(void (^)(NSString *key))block {
    [_memoryCache removeObjectForKey:key];
    [_diskCache removeObjectForKey:key withBlock:block];
}

- (void)removeAllObjects {
    [_memoryCache removeAllObjects];
    [_diskCache removeAllObjects];
}

- (void)removeAllObjectsWithBlock:(void(^)(void))block {
    [_memoryCache removeAllObjects];
    [_diskCache removeAllObjectsWithBlock:block];
}

- (void)removeAllObjectsWithProgressBlock:(void(^)(int removedCount, int totalCount))progress
                                 endBlock:(void(^)(BOOL error))end {
    [_memoryCache removeAllObjects];
    [_diskCache removeAllObjectsWithProgressBlock:progress endBlock:end];
    
}

- (NSString *)description {
    if (_name) return [NSString stringWithFormat:@"<%@: %p> (%@)", self.class, self, _name];
    else return [NSString stringWithFormat:@"<%@: %p>", self.class, self];
}

+ (SDKCache *)cache
{
    static SDKCache *_cache = nil;
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        _cache = [[self class] cacheWithName:@"_IVYSDK_"];
    });
    return _cache;
}
@end
