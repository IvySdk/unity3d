//
//  ISGlobalDataWriter.h
//  Environment
//
//  Created by Asaf Gur on 12/06/2021.
//  Copyright © 2021 ironSource. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ISGlobalDataConstants.h"

NS_ASSUME_NONNULL_BEGIN

@interface ISGlobalDataWriter : NSObject

- (void)setInitialData;

- (void)setData:(id)value
         forKey:(NSString *)key;

- (void)setData:(NSDictionary *)data;

- (void)extendData:(NSDictionary *)data
            forKey:(NSString *)objToExtend;

- (void)setData:(id)value
         forKey:(NSString *)key
      andAdUnit:(ISGlobalDataAdUnit)adUnit;

- (void)removeDataForKey:(NSString *)key
               andAdUnit:(ISGlobalDataAdUnit)adUnit;
@end

NS_ASSUME_NONNULL_END
