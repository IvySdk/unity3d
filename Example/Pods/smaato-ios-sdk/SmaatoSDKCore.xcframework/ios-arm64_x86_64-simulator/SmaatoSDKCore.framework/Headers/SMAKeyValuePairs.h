//
//  SMAKeyValuePairs.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 24/05/2019.
//  Copyright © 2019 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>

@interface SMAKeyValuePairs: NSObject

// If aKey already exists in the dictionary, anObject is appended to the existing objects. Otherwise, key-value pair is added.
- (void)addTargetingValue:(NSString *_Nonnull)anObject forKey:(NSString *_Nonnull)aKey;

// If aKey already exists in the dictionary, anObject takes its place. Otherwise, key-value pair is added.
- (void)setTargetingValue:(NSString *_Nonnull)anObject forKey:(NSString *_Nonnull)aKey;

// If aKey already exists in the dictionary, anObject takes its place. Otherwise, key-value pair is added.
- (void)setTargetingValues:(NSArray<NSString *> *_Nonnull)anObject forKey:(NSString *_Nonnull)aKey;

// Empties the dictionary of its values.
- (void)removeAllKeyValuePairs;

// Removes specific key-value pair from the targeting.
- (void)removeKeyValuePairWithKey:(NSString *_Nonnull)aKey;

// Returns all key-value pairs that will be added to the query request.
- (NSDictionary *_Nullable)allKeyValuePairs;

@end
