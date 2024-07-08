//
//  NSDataAES256.h
//

#import <Foundation/Foundation.h>

@interface NSData (AES256)

- (NSData *)encryptedWithKey:(NSData *)key;
- (NSData *)decryptedWithKey:(NSData *)key;
- (NSData *)AES128EncryptWithKey:(NSString *)key Iv:(NSString *)Iv;   //加密
- (NSData *)AES128DecryptWithKey:(NSString *)key Iv:(NSString *)Iv;   //解密
- (NSData *)AES256EncryptWithKey:(NSString *)key Iv:(NSString *)Iv;   //加密
- (NSData *)AES256DecryptWithKey:(NSString *)key Iv:(NSString *)Iv;   //解密
@end
