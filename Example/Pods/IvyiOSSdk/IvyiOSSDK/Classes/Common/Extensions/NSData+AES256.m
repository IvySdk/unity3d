//
//  NSDataAES256.m
//

#import "NSData+AES256.h"
#import <CommonCrypto/CommonCryptor.h>

// Key size is 32 bytes for AES256
#define kKeySize kCCKeySizeAES256

@implementation NSData (AES256)

- (NSData *)makeCryptedVersionWithKeyData:(const void *) keyData ofLength:(int) keyLength decrypt:(bool) decrypt {
	// Copy the key data, padding with zeroes if needed
	char key[kKeySize];
	bzero(key, sizeof(key));
	memcpy(key, keyData, keyLength > kKeySize ? kKeySize : keyLength);
    
	size_t bufferSize = [self length] + kCCBlockSizeAES128;
	void *buffer = malloc(bufferSize);
    
	size_t dataUsed;
    
	CCCryptorStatus status = CCCrypt(decrypt ? kCCDecrypt : kCCEncrypt,
									 kCCAlgorithmAES128,
									 kCCOptionPKCS7Padding | kCCOptionECBMode,
									 key, kKeySize,
									 NULL,
									 [self bytes], [self length],
									 buffer, bufferSize,
									 &dataUsed);
    
	switch(status) {
		case kCCSuccess:
			return [NSData dataWithBytesNoCopy:buffer length:dataUsed];
		case kCCParamError:
			NSLog(@"Error: NSDataAES256: Could not %s data: Param error", decrypt ? "decrypt" : "encrypt");
			break;
		case kCCBufferTooSmall:
			NSLog(@"Error: NSDataAES256: Could not %s data: Buffer too small", decrypt ? "decrypt" : "encrypt");
			break;
		case kCCMemoryFailure:
			NSLog(@"Error: NSDataAES256: Could not %s data: Memory failure", decrypt ? "decrypt" : "encrypt");
			break;
		case kCCAlignmentError:
			NSLog(@"Error: NSDataAES256: Could not %s data: Alignment error", decrypt ? "decrypt" : "encrypt");
			break;
		case kCCDecodeError:
			NSLog(@"Error: NSDataAES256: Could not %s data: Decode error", decrypt ? "decrypt" : "encrypt");
			break;
		case kCCUnimplemented:
			NSLog(@"Error: NSDataAES256: Could not %s data: Unimplemented", decrypt ? "decrypt" : "encrypt");
			break;
		default:
			NSLog(@"Error: NSDataAES256: Could not %s data: Unknown error", decrypt ? "decrypt" : "encrypt");
	}
    
	free(buffer);
	return nil;
}

- (NSData *)encryptedWithKey:(NSData *)key {
	return [self makeCryptedVersionWithKeyData:[key bytes] ofLength:(int)[key length] decrypt:NO];
}

- (NSData *)decryptedWithKey:(NSData *)key {
	return [self makeCryptedVersionWithKeyData:[key bytes] ofLength:(int)[key length] decrypt:YES];
}

//(key和iv向量这里是16位的) 这里是CBC加密模式，安全性更高
- (NSData *)AES128EncryptWithKey:(NSString *)key Iv:(NSString *)Iv{//加密
    // 'key' should be 32 bytes for AES128, will be null-padded otherwise
    char keyPtr[kCCKeySizeAES128+1]; // room for terminator (unused)
    bzero(keyPtr, sizeof(keyPtr)); // fill with zeroes (for padding)
    
    // fetch key data
    [key getCString:keyPtr maxLength:sizeof(keyPtr) encoding:NSUTF8StringEncoding];
    
    
    char ivPtr[kCCKeySizeAES128+1];
    memset(ivPtr, 0, sizeof(ivPtr));
    [Iv getCString:ivPtr maxLength:sizeof(ivPtr) encoding:NSUTF8StringEncoding];
    
    NSUInteger dataLength = [self length];
    
    //See the doc: For block ciphers, the output size will always be less than or
    //equal to the input size plus the size of one block.
    //That's why we need to add the size of one block here
    size_t bufferSize = dataLength + kCCBlockSizeAES128;
    void *buffer = malloc(bufferSize);
    
    size_t numBytesEncrypted = 0;
    CCCryptorStatus cryptStatus = CCCrypt(kCCEncrypt, kCCAlgorithmAES128, kCCOptionPKCS7Padding,
                                          keyPtr, kCCKeySizeAES128,
                                          ivPtr /* initialization vector (optional) */,
                                          [self bytes], dataLength, /* input */
                                          buffer, bufferSize, /* output */
                                          &numBytesEncrypted);
    if (cryptStatus == kCCSuccess) {
        //the returned NSData takes ownership of the buffer and will free it on deallocation
        return [NSData dataWithBytesNoCopy:buffer length:numBytesEncrypted];
    }
    
    free(buffer); //free the buffer;
    return nil;
}

//(key和iv向量这里是16位的) 这里是CBC加密模式，安全性更高
- (NSData *)AES256EncryptWithKey:(NSString *)key Iv:(NSString *)Iv{//加密
    // 'key' should be 32 bytes for AES128, will be null-padded otherwise
    char keyPtr[kCCKeySizeAES256+1]; // room for terminator (unused)
    bzero(keyPtr, sizeof(keyPtr)); // fill with zeroes (for padding)
    
    // fetch key data
    [key getCString:keyPtr maxLength:sizeof(keyPtr) encoding:NSUTF8StringEncoding];
    
    
    char ivPtr[kCCKeySizeAES256+1];
    memset(ivPtr, 0, sizeof(ivPtr));
    [Iv getCString:ivPtr maxLength:sizeof(ivPtr) encoding:NSUTF8StringEncoding];
    
    NSUInteger dataLength = [self length];
    
    //See the doc: For block ciphers, the output size will always be less than or
    //equal to the input size plus the size of one block.
    //That's why we need to add the size of one block here
    size_t bufferSize = dataLength + kCCBlockSizeAES128;
    void *buffer = malloc(bufferSize);
    
    size_t numBytesEncrypted = 0;
    CCCryptorStatus cryptStatus = CCCrypt(kCCEncrypt, kCCAlgorithmAES128, kCCOptionPKCS7Padding,
                                          keyPtr, kCCKeySizeAES256,
                                          ivPtr /* initialization vector (optional) */,
                                          [self bytes], dataLength, /* input */
                                          buffer, bufferSize, /* output */
                                          &numBytesEncrypted);
    if (cryptStatus == kCCSuccess) {
        //the returned NSData takes ownership of the buffer and will free it on deallocation
        return [NSData dataWithBytesNoCopy:buffer length:numBytesEncrypted];
    }
    
    free(buffer); //free the buffer;
    return nil;
}

- (NSData *)AES128DecryptWithKey:(NSString *)key Iv:(NSString *)Iv{//解密
    char keyPtr[kCCKeySizeAES128+1]; // room for terminator (unused)
    bzero(keyPtr, sizeof(keyPtr)); // fill with zeroes (for padding)
    
    // fetch key data
    [key getCString:keyPtr maxLength:sizeof(keyPtr) encoding:NSUTF8StringEncoding];
    
    char ivPtr[kCCKeySizeAES128+1];
    memset(ivPtr, 0, sizeof(ivPtr));
    [Iv getCString:ivPtr maxLength:sizeof(ivPtr) encoding:NSUTF8StringEncoding];
    
    NSUInteger dataLength = [self length];
    
    //See the doc: For block ciphers, the output size will always be less than or
    //equal to the input size plus the size of one block.
    //That's why we need to add the size of one block here
    size_t bufferSize = dataLength + kCCBlockSizeAES128;
    void *buffer = malloc(bufferSize);
    
    size_t numBytesDecrypted = 0;
    CCCryptorStatus cryptStatus = CCCrypt(kCCDecrypt, kCCAlgorithmAES128, kCCOptionPKCS7Padding,
                                          keyPtr, kCCKeySizeAES128,
                                          ivPtr /* initialization vector (optional) */,
                                          [self bytes], dataLength, /* input */
                                          buffer, bufferSize, /* output */
                                          &numBytesDecrypted);
    
    if (cryptStatus == kCCSuccess) {
        //the returned NSData takes ownership of the buffer and will free it on deallocation
        return [NSData dataWithBytesNoCopy:buffer length:numBytesDecrypted];
    }
    
    free(buffer); //free the buffer;
    return nil;
}

- (NSData *)AES256DecryptWithKey:(NSString *)key Iv:(NSString *)Iv{//解密
    char keyPtr[kCCKeySizeAES256+1]; // room for terminator (unused)
    bzero(keyPtr, sizeof(keyPtr)); // fill with zeroes (for padding)
    
    // fetch key data
    [key getCString:keyPtr maxLength:sizeof(keyPtr) encoding:NSUTF8StringEncoding];
    
    char ivPtr[kCCKeySizeAES256+1];
    memset(ivPtr, 0, sizeof(ivPtr));
    [Iv getCString:ivPtr maxLength:sizeof(ivPtr) encoding:NSUTF8StringEncoding];
    
    NSUInteger dataLength = [self length];
    
    //See the doc: For block ciphers, the output size will always be less than or
    //equal to the input size plus the size of one block.
    //That's why we need to add the size of one block here
    size_t bufferSize = dataLength + kCCBlockSizeAES128;
    void *buffer = malloc(bufferSize);
    
    size_t numBytesDecrypted = 0;
    CCCryptorStatus cryptStatus = CCCrypt(kCCDecrypt, kCCAlgorithmAES128, kCCOptionPKCS7Padding,
                                          keyPtr, kCCKeySizeAES256,
                                          ivPtr /* initialization vector (optional) */,
                                          [self bytes], dataLength, /* input */
                                          buffer, bufferSize, /* output */
                                          &numBytesDecrypted);
    
    if (cryptStatus == kCCSuccess) {
        //the returned NSData takes ownership of the buffer and will free it on deallocation
        return [NSData dataWithBytesNoCopy:buffer length:numBytesDecrypted];
    }
    
    free(buffer); //free the buffer;
    return nil;
}
@end
