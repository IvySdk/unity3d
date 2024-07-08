// UIImageView+SDKNetworking.m
// Copyright (c) 2011–2016 Alamofire Software Foundation ( http://alamofire.org/ )
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

#import "UIImageView+SDKNetworking.h"
#import <objc/runtime.h>

#if TARGET_OS_IOS || TARGET_OS_TV

#import "SDKImageDownloader.h"
#import "UIImage+animatedGIF.h"

@interface UIImageView (_SDKNetworking)
@property (readwrite, nonatomic, strong, setter = af_setActiveImageDownloadReceipt:) SDKImageDownloadReceipt *af_activeImageDownloadReceipt;
@end

@implementation UIImageView (_SDKNetworking)

- (SDKImageDownloadReceipt *)af_activeImageDownloadReceipt {
    return (SDKImageDownloadReceipt *)objc_getAssociatedObject(self, @selector(af_activeImageDownloadReceipt));
}

- (void)af_setActiveImageDownloadReceipt:(SDKImageDownloadReceipt *)imageDownloadReceipt {
    objc_setAssociatedObject(self, @selector(af_activeImageDownloadReceipt), imageDownloadReceipt, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

@end

#pragma mark -
static char const * const fadeEnableKey = "fadeEnableKey";
NSString * const FADE_ANIMATION = @"FADE_ANIMATION";
@implementation UIImageView (SDKNetworking)
@dynamic enableFade;
-(BOOL)enableFade
{
    NSNumber *number = objc_getAssociatedObject(self, fadeEnableKey);
    return [number boolValue];
}

-(void)setEnableFade:(BOOL)enableFade {
    NSNumber *number = [NSNumber numberWithBool: enableFade];
    objc_setAssociatedObject(self, fadeEnableKey, number , OBJC_ASSOCIATION_RETAIN);
    if(enableFade && ![self.layer animationForKey:FADE_ANIMATION]) {
        CATransition *transition = [CATransition animation];
        transition.type = kCATransitionFade;
        transition.duration = 2.0f;
        transition.timingFunction = [CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut];
        [self.layer addAnimation:transition forKey:FADE_ANIMATION];
    }
}

+ (SDKImageDownloader *)sharedImageDownloader {
    
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wgnu"
    return objc_getAssociatedObject(self, @selector(sharedImageDownloader)) ?: [SDKImageDownloader defaultInstance];
#pragma clang diagnostic pop
}

+ (void)setSharedImageDownloader:(SDKImageDownloader *)imageDownloader {
    objc_setAssociatedObject(self, @selector(sharedImageDownloader), imageDownloader, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

+ (void)cacheURL:(NSURL *)url
{
    __block NSURL *_url = url;
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0ul), ^{
        NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:_url];
        [request addValue:@"image/*" forHTTPHeaderField:@"Accept"];
        SDKImageDownloader *downloader = [[self class] sharedImageDownloader];
        NSUUID *downloadID = [NSUUID UUID];
        [downloader downloadImageForURLRequest:request withReceiptID:downloadID success:nil failure:nil];
    });
}

#pragma mark -

- (void)setImageWithURL:(NSURL *)url {
    [self setImageWithURL:url placeholderImage:nil];
}

- (void)setImageWithURL:(NSURL *)url
       placeholderImage:(UIImage *)placeholderImage
{
    if (!url) {
        return;
    }
    if (url.isFileURL) {
        __block UIImage *image = [url.pathExtension.lowercaseString isEqualToString:@"gif"] ? [UIImage animatedImageWithAnimatedGIFURL:url] : [[UIImage alloc] initWithData:[NSData dataWithContentsOfURL:url]];
        if(self.enableFade) {
            [UIView transitionWithView:self duration:1 options:UIViewAnimationOptionTransitionCrossDissolve animations:^{
                __strong UIImage* img = image;
                if (img) {
                    self.image = img;
                }
            } completion:nil];
        } else {
            self.image = image;
        }
    } else {
        NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:url];
        [request addValue:@"image/*" forHTTPHeaderField:@"Accept"];
        [self setImageWithURLRequest:request placeholderImage:placeholderImage success:nil failure:nil];
    }
}


- (void)setImageWithURLRequest:(NSURLRequest *)urlRequest
              placeholderImage:(UIImage *)placeholderImage
                       success:(void (^)(NSURLRequest *request, NSHTTPURLResponse * _Nullable response, UIImage *image))success
                       failure:(void (^)(NSURLRequest *request, NSHTTPURLResponse * _Nullable response, NSError *error))failure
{
    if(self.enableFade) {
        [UIView transitionWithView:self duration:1 options:UIViewAnimationOptionTransitionCrossDissolve animations:^{
            [self _setImageWithURLRequest:urlRequest placeholderImage:placeholderImage success:success failure:failure];
        } completion:nil];
    } else {
        [self _setImageWithURLRequest:urlRequest placeholderImage:placeholderImage success:success failure:failure];
    }
}

- (void)_setImageWithURLRequest:(NSURLRequest *)urlRequest
               placeholderImage:(UIImage *)placeholderImage
                        success:(void (^)(NSURLRequest *request, NSHTTPURLResponse * _Nullable response, UIImage *image))success
                        failure:(void (^)(NSURLRequest *request, NSHTTPURLResponse * _Nullable response, NSError *error))failure
{
    if(urlRequest == nil || urlRequest == NULL)
        return;
    
    if ([urlRequest URL] == nil) {
        [self cancelImageDownloadTask];
        self.image = placeholderImage;
        return;
    }
    
    if ([self isActiveTaskURLEqualToURLRequest:urlRequest]){
        return;
    }
    
    [self cancelImageDownloadTask];
    
    SDKImageDownloader *downloader = [[self class] sharedImageDownloader];
    id <SDKImageRequestCache> imageCache = downloader.imageCache;
    
    //Use the image from the image cache if it exists
    UIImage *cachedImage = [imageCache imageforRequest:urlRequest withAdditionalIdentifier:nil];
    if (cachedImage) {
        if (success) {
            success(urlRequest, nil, cachedImage);
        } else {
            self.image = cachedImage;
        }
        [self clearActiveDownloadInformation];
    } else {
        if (placeholderImage) {
            self.image = placeholderImage;
        }
        
        __weak __typeof(self)weakSelf = self;
        NSUUID *downloadID = [NSUUID UUID];
        SDKImageDownloadReceipt *receipt;
        receipt = [downloader
                   downloadImageForURLRequest:urlRequest
                   withReceiptID:downloadID
                   success:^(NSURLRequest * _Nonnull request, NSHTTPURLResponse * _Nullable response, UIImage * _Nonnull responseObject) {
                       __strong __typeof(weakSelf)strongSelf = weakSelf;
                       if ([strongSelf.af_activeImageDownloadReceipt.receiptID isEqual:downloadID]) {
                           if (success) {
                               success(request, response, responseObject);
                           } else if(responseObject) {
                               strongSelf.image = responseObject;
                           }
                           [strongSelf clearActiveDownloadInformation];
                       }
                       
                   }
                   failure:^(NSURLRequest * _Nonnull request, NSHTTPURLResponse * _Nullable response, NSError * _Nonnull error) {
                       __strong __typeof(weakSelf)strongSelf = weakSelf;
                       if ([strongSelf.af_activeImageDownloadReceipt.receiptID isEqual:downloadID]) {
                           if (failure) {
                               failure(request, response, error);
                           }
                           [strongSelf clearActiveDownloadInformation];
                       }
                   }];
        
        self.af_activeImageDownloadReceipt = receipt;
    }
}

- (void)cancelImageDownloadTask {
    if (self.af_activeImageDownloadReceipt != nil) {
        [[self.class sharedImageDownloader] cancelTaskForImageDownloadReceipt:self.af_activeImageDownloadReceipt];
        [self clearActiveDownloadInformation];
    }
}

- (void)clearActiveDownloadInformation {
    self.af_activeImageDownloadReceipt = nil;
}

- (BOOL)isActiveTaskURLEqualToURLRequest:(NSURLRequest *)urlRequest {
    return [self.af_activeImageDownloadReceipt.task.originalRequest.URL.absoluteString isEqualToString:urlRequest.URL.absoluteString];
}

@end

#endif
