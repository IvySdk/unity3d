//
//  ISGlobalDataReader.h
//  Environment
//
//  Created by Asaf Gur on 14/06/2021.
//  Copyright © 2021 ironSource. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ISGlobalDataConstants.h"

NS_ASSUME_NONNULL_BEGIN

@interface ISGlobalDataReader : NSObject

- (nonnull NSDictionary *)dataByKeys:(NSArray<NSString *> *)keys;

- (nonnull NSDictionary *)dataByKeys:(NSArray<NSString *> *)keys
                           andAdUnit:(ISGlobalDataAdUnit)adUnit;

@end

NS_ASSUME_NONNULL_END
