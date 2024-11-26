package com.ivy.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class CommonUtil {
  private static final byte[] ivBytes = {0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30};
  private static String _keyHash;

  @SuppressWarnings("PackageManagerGetSignatures")
  public static String getKeyStoreHash(Context context) {
    try {
      if (context == null) {
        return null;
      }
      PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),
        PackageManager.GET_SIGNATURES);
      for (Signature signature : info.signatures) {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(signature.toByteArray());
        return Base64.encodeToString(md.digest(), Base64.DEFAULT).trim();
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String getKeyHash(Context context) {
    if (_keyHash == null) {
      _keyHash = getKeyStoreHash(context);
      if (_keyHash != null && _keyHash.length() < 32) {
        int padding = 32 - _keyHash.length();
        byte[] s = new byte[padding];
        System.arraycopy(ivBytes, 0, s, 0, padding);
        _keyHash = _keyHash + new String(s);
      }
    }
    return _keyHash;
  }

  public static String encodeParams(Context context, String data) {
    return encodeParams(context, data.getBytes());
  }

  public static String encodeParams(Context context, byte[] data) {
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(getKeyHash(context).getBytes(), "AES"), new IvParameterSpec(ivBytes));
      byte[] plainText = cipher.doFinal(data);
      String result =  Base64.encodeToString(plainText, Base64.URL_SAFE | Base64.NO_WRAP).trim();

      Logger.debug("AndroidSdk", "encodeParams result: >>> " + result);
      return result;
    } catch (Throwable e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String decodeParams(Context context, String data) {
    return decodeParams(context, data.getBytes());
  }


  public static String decodeWithKey(String content, String key){
    try {
      byte[] decode = Base64.decode(content, Base64.URL_SAFE|Base64.NO_WRAP);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec(ivBytes));
      byte[] plainText= cipher.doFinal(decode);
      return (new String(plainText));
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String decodeParams(Context context, byte[] data) {
    try {
      byte[] decode = Base64.decode(data, Base64.URL_SAFE | Base64.NO_WRAP);
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getKeyHash(context).getBytes(), "AES"), new IvParameterSpec(ivBytes));
      byte[] plainText = cipher.doFinal(decode);
      return (new String(plainText));
    } catch (Throwable e) {
      e.printStackTrace();
      return null;
    }
  }
}
