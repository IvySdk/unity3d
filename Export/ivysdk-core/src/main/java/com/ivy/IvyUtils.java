package com.ivy;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.UrlQuerySanitizer;

import androidx.core.content.ContextCompat;

import com.ivy.util.CommonUtil;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

public final class IvyUtils {
  public static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=";

  public static boolean isOnline(Context c) {
    if (c == null) {
      return true;
    }
    try {
      if (ContextCompat.checkSelfPermission(c, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
        Object o = c.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (o != null) {
          NetworkInfo netInfo = ((ConnectivityManager) o).getActiveNetworkInfo();
          return netInfo != null && netInfo.isConnectedOrConnecting();
        }
      }
    } catch(Throwable t) {
      // ignore
    }
    return true;
  }

  @SuppressLint("QueryPermissionsNeeded")
  public static boolean validApp(Context context, String packageName, Intent intent) {
    if (context != null && packageName != null && hasApp(context, packageName)) {
      intent.setPackage(packageName);
      return context.getPackageManager().queryIntentActivities(intent, 0).size() > 0;
    } else {
      return false;
    }
  }

  public static String readSecureText(Context context, String fileName) {
    try {
      InputStream is = openAsset(context, fileName);
      return decode(is);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static String decode(InputStream is) throws Exception {
    DataInputStream in = new DataInputStream(is);
    int seed = in.readByte();
    int len = in.readInt();
    int fileLen = in.readInt();
    byte[] data = new byte[fileLen];
    in.read(data);
    for (int i = 0, n = data.length; i < n; i++) {
      int tmp = data[i];
      if (len-- > 0) {
        int t = tmp + seed;
        tmp = t > 255 && tmp >= 128 ? tmp : tmp - seed;
      }
      data[i] = (byte) tmp;
    }
    in.close();
    return new String(data).trim();
  }

  public static String readSecureTextByKeystore(Context context, String content) {
    try {
      if (content != null) {
        return CommonUtil.decodeParams(context, content.getBytes());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return content;
  }

  public static String md5(String content) {
    try {
      byte[] bytes = MessageDigest.getInstance("md5").digest(content.getBytes());
      StringBuilder ret = new StringBuilder(bytes.length << 1);
      for (byte aByte : bytes) {
        ret.append(Character.forDigit((aByte >> 4) & 0xf, 16));
        ret.append(Character.forDigit(aByte & 0xf, 16));
      }
      return ret.toString();
    } catch (Exception exp) {
      return content;
    }
  }

  public static InputStream openAsset(Context context, String fileName) {
    try {
      return context.getAssets().open(fileName);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String streamToString(InputStream is) {
    try {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024 * 4];
      int len = 0;
      while ((len = is.read(buffer)) != -1) {
        baos.write(buffer, 0, len);
      }
      baos.flush();
      baos.close();
      is.close();
      byte[] byteArray = baos.toByteArray();
      return new String(byteArray);
    } catch (Exception e) {
      return null;
    }
  }

  private static String fixUrl(Context context, String url, String tag) {
    url = appendReferrer(context, url, tag);
    if (url.startsWith("http")) {
      return url;
    } else {
      return GOOGLE_PLAY_URL + url;
    }
  }

  private static boolean isPlayStoreUrl(String url) {
    return url.startsWith(GOOGLE_PLAY_URL);
  }

  private static String appendReferrer(Context context, String url, String tag) {
    if (!url.contains("&referrer")) {
      StringBuilder sb = new StringBuilder(url);
      sb.append("&referrer=utm_source%3D").append("ivy")
        .append("%26utm_campaign%3D").append(context.getPackageName())
        .append("%26utm_medium%3D").append(tag)
        .append("%26utm_term%3D").append(tag)
        .append("%26utm_content%3D").append(tag);
      return sb.toString();
    } else {
      return url;
    }
  }

  public static boolean hasApp(Context context, String packageName) {
    try {
      int playStoreEnabled = context.getPackageManager().getApplicationEnabledSetting(packageName);
      return playStoreEnabled == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT || playStoreEnabled == PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
    } catch (Exception ignore) {
      return false;
    }
  }

  public static boolean hasPlayStore(Context context) {
    return hasApp(context, "com.android.vending");
  }

  public static void openBrowser(Context context, String url) {
    if (context == null) {
      return;
    }
    Intent i = new Intent(Intent.ACTION_VIEW);
    launchBrowser(context, url, i);
  }


  public static void openPlayStore(Context context, String pkg, String referrer, JSONObject app) {
    if (context == null) {
      return;
    }

    Intent i = new Intent(Intent.ACTION_VIEW);
    if (app != null && app.has("url")) {
      String linkUrl = app.optString("url");
      if (!"".equals(linkUrl)) {
        Logger.debug("ADSFALL", "Open link: " + linkUrl);
        try {
          i.setData(Uri.parse(linkUrl));
          context.startActivity(i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
          return;
        } catch(Throwable t) {
          // ignore
        }
      }
    }

    String url = fixUrl(context, pkg, referrer);
    boolean isGooglePlay = isPlayStoreUrl(url);

    if (isGooglePlay) {
      if (hasPlayStore(context)) {
        launchPlayStore(context, url, i);
      } else {
        launchBrowser(context, url, i);
      }
    } else {
      launchBrowser(context, url, i);
    }
  }

  private static void launchPlayStore(Context context, String url, Intent i) {
    final String marketUrl = "market://details?id=";
    url = url.replace(GOOGLE_PLAY_URL, marketUrl);
    i.setPackage("com.android.vending");
    i.setData(Uri.parse(url));
    // launchApp(context, i);
    try {
      context.startActivity(i);
    } catch(Exception e) {

    }
  }

  private static void launchApp(Context context, Intent i) {
    try {
      context.startActivity(i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void launchBrowser(Context context, String url, Intent i) {
    i.setData(Uri.parse(url));
    String browserPackageName = getDefaultBrowserPackageName(context, i);
    if (browserPackageName != null) {
      i.setPackage(browserPackageName);
    }
    launchApp(context, i);
  }

  private static String getDefaultBrowserPackageName(Context context, Intent intent) {
    PackageManager packageManager = context.getPackageManager();
    @SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
    if (resolveInfos.size() > 0) {
      ResolveInfo info = resolveInfos.get(0);
      return info.activityInfo.packageName;
    } else {
      return null;
    }
  }

  public static JSONObject getUrlParametersWithJson(String url) {
    try {
      JSONObject object = new JSONObject();
      UrlQuerySanitizer sanitizer = new UrlQuerySanitizer();
      sanitizer.setAllowUnregisteredParamaters(true);
      sanitizer.parseUrl(url);
      Iterator<UrlQuerySanitizer.ParameterValuePair> it = sanitizer.getParameterList().iterator();
      while (it.hasNext()) {
        UrlQuerySanitizer.ParameterValuePair pair = it.next();
        object.put(pair.mParameter, pair.mValue);
      }
      return object;
    } catch(Throwable t) {
      t.printStackTrace();
    }
    return null;
  }
}
