//
//  SMALoggerConfiguration.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 8/10/18.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>

/**
 Logging levels supported by Smaato SDK
 */
typedef NS_ENUM(NSInteger, SMALogLevel) {
    /** Error-level messages are intended for reporting errors */
    kSMALogLevelError = 0,
    /** Event which may cause an error */
    kSMALogLevelWarning = 1,
    /** Message that may be helpful, but isn’t essential */
    kSMALogLevelInfo = 2,
    /** Used in a development environment */
    kSMALogLevelDebug = 3,
    /** Generally used only as a last resort when debugging a difficult problem
     (example: local variables within an algorithm implementation */
    kSMALogLevelVerbose = 4,
    /** Default log level */
    kSMALogLevelDefault = kSMALogLevelInfo,
};
