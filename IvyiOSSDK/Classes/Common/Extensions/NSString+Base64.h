//
//  NSString+Base64.h
//  Newsstand
//
//  Created by Carlo Vigiani on 29/Oct/11.
//  Copyright (c) 2011 viggiosoft. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (Base64)

+ (nonnull NSString *) base64StringFromData:(nonnull NSData *)data length:(long)length;

+ (nonnull NSString *) base64DecodeFromString:(nonnull NSString *)origin;

+ (nonnull NSString *) base64EncodeFromString:(nonnull NSString *)origin;
@end
