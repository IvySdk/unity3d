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

NS_ASSUME_NONNULL_BEGIN

extern NSString *const kPOBRTBExtObjParam;
extern NSString *const kPOBRTBIDParam;
extern NSString *const kPOBRTBNameParam;
extern NSString *const kPOBRTBValueParam;
extern NSString *const kPOBRTBSegtaxParam;
extern NSString *const kPOBRTBSegmentParam;

/*!
 @abstract This class represents RTB 'Segment' object
 */
@interface POBSegment : NSObject
/*!
 @warning Use one of the custom initializers instead of default init
 */
- (instancetype)init NS_UNAVAILABLE;
/*!
 @abstract Initializes segment object with identifier & name
 @param identifier segment id
 @param name segment name
 @return POBSegment instance
 */
- (instancetype)initWithId:(NSString*)identifier andName:(NSString*)name;
/*!
 @abstract Initializes segment object with identifier
 @param identifier segment id
 @return POBSegment instance
 */
- (instancetype)initWithId:(NSString*)identifier;

/*!
 @abstract segment id
 */
@property (nonatomic, readonly) NSString *identifier;
/*!
 @abstract segment name
 */
@property (nonatomic, strong) NSString *name;
/*!
 @abstract segment value
 */
@property (nonatomic, strong) NSString *value;
@end

/*!
 @abstract This class represents RTB 'Data' object containing Segment details
 */
@interface POBDataProvider : NSObject
/*!
 @warning Use one of the custom initializers instead of default init
 */
- (instancetype)init NS_UNAVAILABLE;
/*!
 @abstract Initializes data object with data provider name
 @param name data provider's name
 @return POBDataProvider instance
 */
- (instancetype)initWithName:(NSString*)name;
/*!
 @abstract Initializes data object with data provider identifier & name
 @param name data provider's name
 @param identifier data provider's id
 @return POBDataProvider instance
 */
- (instancetype)initWithName:(NSString*)name andId:(NSString*)identifier;
/*!
 @abstract Adds a segment details to data object
 @param segment segment details to be added to data object
 */
- (void)addSegment:(POBSegment*)segment;
/*!
 @abstract Removes a segment details from data object
 @param identifier id for which a segment is to be removed
 */
- (void)removeSegmentForId:(NSString*)identifier;

/*!
 @abstract Removes all segments from the data object
 */
- (void)removeAllSegments;

/*!
 @abstract Returns all the segments associated with this data object
 @return Array of POBSegments
 */
- (NSArray<POBSegment*>*)segments;

/*!
 @abstract Returns a segment for given id
 @param identifier segment identifier
 @return Reference of the POBSegment object associated with the given identifier
 */
- (POBSegment *_Nullable)segmentForId:(NSString*)identifier;

/*!
 @abstract data provider's id
 */
@property (nonatomic, strong) NSString *identifier;
/*!
 @abstract data provider's name
 */
@property (nonatomic, readonly) NSString *name;
/*!
 @abstract segment taxonomy id
 @ref  https://github.com/InteractiveAdvertisingBureau/AdCOM/blob/master/AdCOM%20v1.0%20FINAL.md#list--category-taxonomies-
 */
@property (nonatomic, assign) int segTax;
/*!
 @abstract RTB extension object for this data
 */
@property (nonatomic, strong) NSDictionary *ext;
@end

NS_ASSUME_NONNULL_END
