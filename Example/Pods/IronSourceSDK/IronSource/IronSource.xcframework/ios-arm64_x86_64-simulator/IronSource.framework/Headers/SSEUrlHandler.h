//
//  SSEUrlHandler.h
//  Environment
//
//  Created by Itai Asaf on 22/03/2016.
//  Copyright © 2016 Supersonic. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UIKit/UIKit.h"

@interface SSEUrlHandler : NSObject

+(void)openURL:(NSString *_Nonnull)url completionHandler:(void (^ __nullable)(BOOL success))completion;

@end
