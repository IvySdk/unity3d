//
//  SDKIAPHelper.h
//
//  Original Created by Ray Wenderlich on 2/28/11.
//  Created by saturngod on 7/9/12.
//  Copyright 2011 Ray Wenderlich. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <StoreKit/StoreKit.h>


typedef void (^IAPProductsResponseBlock)(SKProductsRequest* _Nullable request , SKProductsResponse*_Nullable response);

typedef void (^IAPbuyProductCompleteResponseBlock)(SKPaymentTransaction*_Nullable transcation);

typedef void (^checkReceiptCompleteResponseBlock)(NSDictionary * _Nullable response,NSError*_Nullable error);

typedef void (^restoreProductsCompleteResponseBlock) (SKPaymentQueue* _Nonnull payment,NSError*_Nullable error);

@interface SDKIAPHelper : NSObject <SKProductsRequestDelegate, SKPaymentTransactionObserver>

@property (nonatomic,strong,nonnull) NSSet *productIdentifiers;
//@property (nonatomic,strong,nullable) NSMutableArray * products;
@property (nonatomic,strong,nullable) NSMutableSet *purchasedProducts;
@property (nonatomic,strong,nullable) SKProductsRequest *request;
@property (nonatomic) BOOL production;

//init With Product Identifiers
- (void)setProductIdentifiers:(nonnull NSSet *)productIdentifiers;

//get Products List
- (void)requestProductsWithCompletion:(nullable IAPProductsResponseBlock)completion;

- (void)requestProductsWithIdentifier:(nonnull NSString *)identifier completion:(nullable IAPProductsResponseBlock)completion;

//Buy Product
- (void)buyProduct:(nonnull SKProduct *)productIdentifier onCompletion:(nullable IAPbuyProductCompleteResponseBlock)completion;

//restore Products
- (void)restoreProductsWithCompletion:(nullable restoreProductsCompleteResponseBlock)completion;

//check isPurchased or not
- (BOOL)isPurchasedProductsIdentifier:(nonnull NSString*)productID;

//check receipt but recommend to use in server side instead of using this function
- (void)checkReceipt:(nullable NSData*)receiptData onCompletion:(nullable checkReceiptCompleteResponseBlock)completion;

- (void)checkReceipt:(nullable NSData*)receiptData AndSharedSecret:(nullable NSString*)secretKey onCompletion:(nullable checkReceiptCompleteResponseBlock)completion;

- (void)doCheckReceipt:(nonnull NSData *)jsonData;

- (nonnull NSString *)getProductIdentifierFromTransaction:(nonnull SKPaymentTransaction *)transaction;

//saved purchased product
- (void)provideContentWithTransaction:(nonnull SKPaymentTransaction *)transaction;

- (void)provideContent:(nonnull NSString *)productIdentifier;

//clear the saved products
- (void)clearSavedPurchasedProducts;
- (void)clearSavedPurchasedProductByID:(nonnull NSString*)productIdentifier;


//Get The Price with local currency
- (nonnull NSString *)getLocalePrice:(nonnull SKProduct *)product;

+ (nonnull SDKIAPHelper *) sharedHelper;
@end
