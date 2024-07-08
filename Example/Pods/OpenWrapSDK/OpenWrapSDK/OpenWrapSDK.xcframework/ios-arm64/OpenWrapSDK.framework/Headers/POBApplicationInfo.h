/*
* PubMatic Inc. ("PubMatic") CONFIDENTIAL
* Unpublished Copyright (c) 2006-2024 PubMatic, All Rights Reserved.
*
* NOTICE:  All information contained herein is, and remains the property of PubMatic. The intellectual and technical concepts contained
* herein are proprietary to PubMatic and may be covered by U.S. and Foreign Patents, patents in process, and are protected by trade secret or copyright law.
* Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained
* from PubMatic.  Access to the source code contained herein is hereby forbidden to anyone except current PubMatic employees, managers or contractors who have executed
* Confidentiality and Non-disclosure agreements explicitly covering such access or to such other persons whom are directly authorized by PubMatic to access the source code and are subject to confidentiality and nondisclosure obligations with respect to the source code.
*
* The copyright notice above does not evidence any actual or intended publication or disclosure  of  this source code, which includes
* information that is confidential and/or proprietary, and is a trade secret, of  PubMatic.   ANY REPRODUCTION, MODIFICATION, DISTRIBUTION, PUBLIC  PERFORMANCE,
* OR PUBLIC DISPLAY OF OR THROUGH USE  OF THIS  SOURCE CODE  WITHOUT  THE EXPRESS WRITTEN CONSENT OF PUBMATIC IS STRICTLY PROHIBITED, AND IN VIOLATION OF APPLICABLE
* LAWS AND INTERNATIONAL TREATIES.  THE RECEIPT OR POSSESSION OF  THIS SOURCE CODE AND/OR RELATED INFORMATION DOES NOT CONVEY OR IMPLY ANY RIGHTS
* TO REPRODUCE, DISCLOSE OR DISTRIBUTE ITS CONTENTS, OR TO MANUFACTURE, USE, OR SELL ANYTHING THAT IT  MAY DESCRIBE, IN WHOLE OR IN PART.
*/

#import <Foundation/Foundation.h>
#import "POBDataTypes.h"

NS_ASSUME_NONNULL_BEGIN

/**
 ------------------------------------------------------------------------------------
 POBApplicationInfo Class
 ------------------------------------------------------------------------------------
 */

/*!
  Provides setters to pass application information, Like store URL, application domain, categories, ...etc. It is very important to provide transparency for buyers of your app inventory.
 */
@interface POBApplicationInfo : NSObject

/*!
 @abstract Indicates the domain of the mobile application (e.g. “mygame.foo.com”)
 */
@property(nonatomic, strong) NSString *domain;

/*!
 @abstract Valid URL of the application on App store
 
 It is mandatory to pass a valid storeURL, containing the itunes id of your app, It is very important for platform identification.
 
 Example : https://itunes.apple.com/us/app/pubmatic-sdk-app/id1175273098?mt=8
 */
@property(nonatomic, strong) NSURL *storeURL;

/*!
 @abstract Indicates whether the mobile application is a paid version or not.
 Possible values are:
 
 POBBOOLNo - Free version
 
 POBBOOLYes - Paid version
 */
@property(nonatomic, assign) POBBOOL paid;

/*!
 @abstract Comma separated list of IAB categories for the application. e.g. "IAB-1, IAB-2"
 */
@property(nonatomic, strong) NSString *categories;

/*!
 @abstract A comma-separated list of keywords giving more information about the publisher app.
 */
@property (nonatomic, strong) NSString *keywords;

@end

NS_ASSUME_NONNULL_END
