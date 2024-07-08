//
//  SMAErrors.h
//  SmaatoSDKCore
//
//  Created by Smaato Inc on 13/07/2018.
//  Copyright © 2018 Smaato Inc. All rights reserved.￼
//  Licensed under the Smaato SDK License Agreement￼
//  https://www.smaato.com/sdk-license-agreement/
//

#import <Foundation/Foundation.h>

/// Domain for errors encountered by SmaatoSDK
FOUNDATION_EXTERN NSErrorDomain const SMAErrorDomain;

/// Error codes in the \c SMAErrorDomain
typedef NS_ERROR_ENUM(SMAErrorDomain, SMAErrorCode){
    /// Internal error
    kSMAErrorCodeInternalError = -1,

    /// No ad is currently available
    kSMAErrorCodeNoAdAvailable = -2,

    /// Invalid ad request
    kSMAErrorCodeInvalidRequest = -3,

    /// Network error
    kSMAErrorCodeNetworkError = -4,

    /// Timeout
    kSMAErrorCodeTimeout = -5,
};
