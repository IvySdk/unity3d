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

#ifndef POBNativeConstants_h
#define POBNativeConstants_h


/*! Native Image Asset Type
 */
typedef NS_ENUM(NSInteger, POBNativeImageAssetType) {
    
    /// Icon image to rendered in native Ad
    POBNativeImageAssetTypeIcon        =   1,
    
    /// Main image to rendered in native Ad
    POBNativeImageAssetTypeMain        =   3
    
} ;


/*! Native Event Type for Event Tracking
 */
typedef NS_ENUM(NSInteger, POBNativeEventType) {
    
    /// impression
    POBNativeEventTypeImpression        =   1,
    
    /// Visible impression using MRC definition at 50% in view for 1 second
    POBNativeEventTypeViewMRC50         =   2,

    /// 100% in view for 1 second (ie GroupM standard)
    POBNativeEventTypeViewMRC100        =   3,
    
    /// Signifies the event as OMID event.
    POBNativeEventTypeOMID              = 555

} ;


/*! Native Event Tracking Methods
 */
typedef NS_ENUM(NSInteger, POBNativeEventTrackingMethod) {
    
    /// Image-pixel tracking - URL provided will be inserted as a 1x1 pixel at the time of the event.
    POBNativeEventTrackingMethodImage    =   1,
    
    
    /// Javascript-based tracking - URL provided will be inserted as a js tag at the time of the event.
    POBNativeEventTrackingMethodJS       =   2,
    
} ;


///
///  POBNativeDataAssetType.h
///

/*! Native Data asset types
 */
typedef NS_ENUM(NSInteger, POBNativeDataAssetType) {
    
    /*! Sponsored By message where response should contain the brand name of the sponsor. Value is of type Text
     */
    POBNativeDataAssetTypeSponsored        =   1,
    
    
    /*! Descriptive text associated with the product or text service being advertised. Value is of type Text
     */
    POBNativeDataAssetTypeDescription      =   2,
    
    
    //// Rating of the product being offered to the user. For example an app’s rating in an app store from 0-5.Value will be    number formatted as string
    POBNativeDataAssetTypeRating           =   3,
    
    
    /// Number of social ratings or “likes” of the product being offered to the user. Value will be number formatted as string
    POBNativeDataAssetTypeLikes            =   4,
    
    
    /// Number downloads/installs of this product.  Value will be number formatted as string
    POBNativeDataAssetTypeDownloads        =   5,
    
    
    /// Price for product / app / in-app purchase. Value should include currency symbol in localized format. Value will be number formatted as string
    POBNativeDataAssetTypePrice            =   6,
    
    
    /// Sale price that can be used together with price to indicate a discounted price compared to a regular price. Value should include currency symbol in localized format.     Value will be number formatted as string
    POBNativeDataAssetTypeSalePrice        =   7,
    
    
    /// Represents Phone number of user. Value will be string
    POBNativeDataAssetTypePhone            =   8,
    
    
    /// Represents Address of user. Value will be Text
    POBNativeDataAssetTypeAddress          =   9,
    
    
    /// Additional descriptive text associated with the product or service being advertised. Value will be Text
    POBNativeDataAssetTypeDescription2     =   10,
    
    
    /// Display URL for the text ad. Value will be Text
    POBNativeDataAssetTypeDisplayURL       =   11,
    
    
    /// CTA description - descriptive text describing a ‘call to action’ button for the destination URL.    Value will be Text
    POBNativeDataAssetTypeCTAText          =   12
    
};


/// The context in which the ad appears - what type of content is surrounding the ad on the  page at a high level.
typedef NS_ENUM(NSInteger, POBNativeContextType) {
    /// Content-centric context such as newsfeed, article, image gallery, video gallery, or similar.
    POBNativeContextTypeContentCentric = 1,
    
    /// Social-centric context such as social network feed, email, chat, or similar.
    POBNativeContextTypeSocialCentric = 2,
    
    /// Product context such as product listings, details, recommendations, reviews, or similar.
    POBNativeContextTypeProductSpcific = 3
};


/// Next-level context in which the ad appears. Again this reflects the primary context, and does not imply no presence of other elements.
typedef NS_ENUM(NSInteger, POBNativeContextSubType) {
    
    /// General or mixed content.
    POBNativeContextSubTypeGeneral = 10,
    
    /// Primarily article content (which of course could include images, etc as part of the article)
    POBNativeContextSubTypeArtical = 11,
    
    /// Primarily video content
    POBNativeContextSubTypeVideo = 12,
    
    /// Primarily audio content
    POBNativeContextSubTypeAudio = 13,
    
    /// Primarily image content
    POBNativeContextSubTypeImage = 14,
    
    /// User-generated content - forums, comments, etc.
    POBNativeContextSubTypeUserGenerated = 15,
    
    /// General social content such as a general social network
    POBNativeContextSubTypeSocial = 20,
    
    /// Primarily email content
    POBNativeContextSubTypeEmail = 21,
    
    /// Primarily chat/IM content
    POBNativeContextSubTypeChatIM = 22,
    
    /// Content focused on selling products, whether digital or physical
    POBNativeContextSubTypeSellingProducts = 30,
    
    /// Application store/marketplace
    POBNativeContextSubTypeMarketplace = 31,
    
    /// Product reviews site primarily (which may sell product secondarily)
    POBNativeContextSubTypeProductReviewSite = 32
};


/// The FORMAT of the ad you are purchasing, separate from the surrounding context
typedef NS_ENUM(NSInteger, POBNativePlacementType) {
    
    /// In the feed of content - for example as an item inside the organic feed/grid/listing/carousel.
    POBNativePlacementTypeFeed = 1,
    
    /// In the atomic unit of the content - IE in the article page or single image page
    POBNativePlacementTypeAtomic = 2,
    
    /// Outside the core content - for example in the ads section on the right rail, as a banner-style placement near the content, etc.
    POBNativePlacementTypeOutsideCoreContent = 3,
    
    /// Recommendation widget, most commonly presented below the article content.
    POBNativePlacementTypeBelowArticle = 4
};

/// The Type of the native ad template
typedef NS_ENUM(NSInteger, POBNativeTemplateType) {

    /// template type small
    POBNativeTemplateTypeSmall,

    /// template type medium
    POBNativeTemplateTypeMedium,
    
    /// template type custom
    POBNativeTemplateTypeCustom
};

#endif /* POBNativeConstants_h */
