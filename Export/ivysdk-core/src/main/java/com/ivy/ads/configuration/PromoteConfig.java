//package com.ivy.ads.configuration;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//
////import com.ivy.ads.managers.PromoteAdManager;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
//
//public class PromoteConfig extends BaseConfig {
//  public static final int IMAGE_TYPE_NONE = 0;
//  public static final int IMAGE_TYPE_COVER = 1;
//  public static final int IMAGE_TYPE_BANNER = 2;
//  public static final int IMAGE_TYPE_ICON = 3;
//  public static final int IMAGE_TYPE_GIFT_ICON = 4;
//  public static final int IMAGE_TYPE_VIDEO = 5;
//  public static final int IMAGE_TYPE_DOWNLOAD = 6;
//  public static final int IMAGE_TYPE_COUNT = 7;
//  public static final String TAG_GIFICON = "gificon";
//  public static final String TAG_BANNER = "banner";
//  public static final String TAG_COVER = "cover";
//  public static final String TAG_ICON = "icon";
//  public JSONObject apps;
//  public JSONObject deliciousConfig;
//  public JSONArray moreGames = new JSONArray();
//
//  public static PromoteConfig fromBundle(Bundle bundle) {
//    PromoteConfig config = new PromoteConfig();
//
//    try {
//      if (bundle.containsKey("delicious")) {
//        String deliciousStr = bundle.getString("delicious");
//        config.deliciousConfig = new JSONObject(deliciousStr);
//      }
//
//      if (bundle.containsKey("promote")) {
//        String promoteStr = bundle.getString("promote");
//        config.apps = new JSONObject(promoteStr);
//      }
//
//      if (bundle.containsKey("more")) {
//        String moreStr = bundle.getString("more");
//        config.moreGames = new JSONArray(moreStr);
//      }
//    } catch (Throwable ex) {
//      ex.printStackTrace();
//    }
//    return config;
//  }
//
//  public PromoteConfig fillFromJson(JSONObject jsonObject) {
//    super.fillFromJson(jsonObject);
//
//    if (jsonObject.has("promote")) {
//      JSONObject root = jsonObject.optJSONObject("promote");
//
//      if (root != null && root.has("apps")) {
//        this.apps = root.optJSONObject("apps");
//      }
//
//      if (root != null && root.has("delicious")) {
//        this.deliciousConfig = root.optJSONObject("delicious");
//      }
//      if (root != null && root.has("more")) {
//        this.moreGames = root.optJSONArray("more");
//      }
//    }
//    return this;
//  }
//
//  public Bundle toBundle() {
//    Bundle bundle = new Bundle();
//    if (this.deliciousConfig != null && this.deliciousConfig.length() > 0) {
//      bundle.putString("delicious", this.deliciousConfig.toString());
//    }
//    if (this.apps != null && this.apps.length() > 0) {
//      bundle.putString("promote", this.apps.toString());
//    }
//    if (this.moreGames != null && this.moreGames.length() > 0) {
//      bundle.putString("more", this.moreGames.toString());
//    }
//    return bundle;
//  }
//
//  /**
//   * if
//   * @param context
//   * @param appid
//   * @return
//   */
//  public JSONObject getAppIfNotInstalled(Context context, int appid) {
//    JSONObject o = selectApp(appid);
//    if (o == null) {
//      return null;
//    }
//
//    PackageManager pm = context.getPackageManager();
//    if (isPackageInstalled(pm, o.optString("package"))) {
//      return null;
//    }
//    return o;
//  }
//
//  public JSONObject selectApp(int appid) {
//    if (this.apps == null || this.apps.length() == 0) {
//      return null;
//    }
//    Iterator<String> it = this.apps.keys();
//    JSONObject firstApp = null;
//    while (it.hasNext()) {
//      String id = it.next();
//      JSONObject o = this.apps.optJSONObject(id);
//      if (firstApp == null) {
//        firstApp = o;
//      }
//      if (o.optInt("appid") == appid) {
//        return o;
//      }
//    }
//
//    return firstApp;
//  }
//
//  public List<JSONObject> selectBannerApps(Context context) {
//    if (this.apps == null || this.apps.length() == 0 || this.moreGames == null || this.moreGames.length() == 0) {
//      return new ArrayList<>();
//    }
//
//    List<JSONObject> allGifApps = new ArrayList<>();
//    String findKey = TAG_BANNER;
//    PackageManager pm = context.getPackageManager();
//
//    for (int i = 0; i < moreGames.length(); i++) {
//      String id = String.valueOf(moreGames.optInt(i));
//      if (!this.apps.has(id)) {
//        continue;
//      }
//
//      JSONObject o = this.apps.optJSONObject(id);
//      if (o != null && o.has(findKey)) {
//        String giftIcon = o.optString(findKey);
//        if (!"".equals(giftIcon)) {
//          String pkg = o.optString("package");
//          if (isPackageInstalled(pm, pkg)) {
//            continue;
//          }
//          allGifApps.add(o);
//        }
//      }
//    }
//
//    return allGifApps;
//  }
//
//  private boolean isPackageInstalled(PackageManager pm, String pkgName) {
//    try {
//      pm.getPackageInfo(pkgName, 0);
//      return true;
//    } catch(Throwable ex) {
//      return false;
//    }
//  }
//
//  public List<JSONObject> selectAllCoverApps(Activity activity) {
//    if (this.apps == null || this.apps.length() == 0 || this.moreGames.length() == 0) {
//      return new ArrayList<>();
//    }
//
//    List<JSONObject> realAllGifApps = new ArrayList<>();
//    List<JSONObject> allGifApps = new ArrayList<>();
//    String findKey = TAG_COVER;
//
//    PackageManager pm = activity.getPackageManager();
//
//    for (int i = 0; i < moreGames.length(); i++) {
//      String id = String.valueOf(moreGames.optInt(i));
//      if (!this.apps.has(id)) {
//        continue;
//      }
//      JSONObject o = this.apps.optJSONObject(id);
//      if (o.has(findKey)) {
//        String url = o.optString(findKey);
//        if (!"".equals(url)) {
//          realAllGifApps.add(o);
//          String pkg = o.optString("package");
//          if (isPackageInstalled(pm, pkg)) {
//            continue;
//          }
//          allGifApps.add(o);
//        }
//      }
//    }
//
//    if (allGifApps.size() == 0) {
//      return realAllGifApps;
//    }
//    return allGifApps;
//  }
//
//  public List<JSONObject> selectAvailableCoverApps(Activity activity) {
//    if (this.apps == null || this.apps.length() == 0 || this.moreGames.length() == 0) {
//      return new ArrayList<>();
//    }
//
//    List<JSONObject> allGifApps = new ArrayList<>();
//    String findKey = TAG_COVER;
//
//    PackageManager pm = activity.getPackageManager();
//
//    for (int i = 0; i < moreGames.length(); i++) {
//      String id = String.valueOf(moreGames.optInt(i));
//      if (!this.apps.has(id)) {
//        continue;
//      }
//      JSONObject o = this.apps.optJSONObject(id);
//      if (o.has(findKey)) {
//        String url = o.optString(findKey);
//        if (!"".equals(url)) {
//
//          String pkg = o.optString("package");
//          if (isPackageInstalled(pm, pkg)) {
//            continue;
//          }
//          if (PromoteAdManager.isCreativeDownloaded(url)) {
//            allGifApps.add(o);
//          }
//        }
//      }
//    }
//
//    Collections.shuffle(allGifApps);
//
//    return allGifApps;
//  }
//
//  public List<JSONObject> selectCoverApps(Activity activity) {
//    if (this.apps == null || this.apps.length() == 0 || this.moreGames.length() == 0) {
//      return new ArrayList<>();
//    }
//
//    List<JSONObject> allGifApps = new ArrayList<>();
//    String findKey = TAG_COVER;
//
//    PackageManager pm = activity.getPackageManager();
//
//    for (int i = 0; i < moreGames.length(); i++) {
//      String id = String.valueOf(moreGames.optInt(i));
//      if (!this.apps.has(id)) {
//        continue;
//      }
//      JSONObject o = this.apps.optJSONObject(id);
//      if (o.has(findKey)) {
//        String url = o.optString(findKey);
//        if (!"".equals(url)) {
//          String pkg = o.optString("package");
//          if (isPackageInstalled(pm, pkg)) {
//            continue;
//          }
//          allGifApps.add(o);
//        }
//      }
//    }
//
//    Collections.shuffle(allGifApps);
//
//    return allGifApps;
//  }
//
//  public List<JSONObject> selectGifApps(Context activity) {
//    if (this.apps == null || this.apps.length() == 0) {
//      return new ArrayList<>();
//    }
//    List<JSONObject> allGifApps = new ArrayList<>();
//    String findKey = TAG_GIFICON;
//    Iterator<String> it = this.apps.keys();
//
//    PackageManager pm = activity.getPackageManager();
//
//    while (it.hasNext()) {
//      String id = it.next();
//      JSONObject o = this.apps.optJSONObject(id);
//      if (o.has(findKey)) {
//        String giftIcon = o.optString(findKey);
//        if (!"".equals(giftIcon)) {
//          String pkg = o.optString("package");
//          if (isPackageInstalled(pm, pkg)) {
//            continue;
//          }
//          allGifApps.add(o);
//        }
//      }
//    }
//    return allGifApps;
//  }
//
//  public JSONObject selectOne(Context context, int type) {
//    return selectOne(context, type, true);
//  }
//
//  public JSONObject selectOne(Context context, int type, boolean validCreative) {
//    if (this.apps == null || this.apps.length() == 0) {
//      return null;
//    }
//
//    String findKey = TAG_GIFICON;
//
//    if (type == IMAGE_TYPE_GIFT_ICON) {
//      findKey = TAG_GIFICON;
//    } else if (type == IMAGE_TYPE_BANNER) {
//      findKey = TAG_BANNER;
//    } else if (type == IMAGE_TYPE_ICON) {
//      findKey = TAG_ICON;
//    }
//
//    List<JSONObject> validApps = new ArrayList<>();
//
//    PackageManager pm = context.getPackageManager();
//
//    Iterator<String> it = this.apps.keys();
//    while (it.hasNext()) {
//      String id = it.next();
//      JSONObject o = this.apps.optJSONObject(id);
//      if (o.has(findKey)) {
//        String giftIcon = o.optString(findKey);
//        if (!"".equals(giftIcon)) {
//          String pkg = o.optString("package");
//          if (isPackageInstalled(pm, pkg)) {
//            continue;
//          }
//          if (validCreative) {
//            if (PromoteAdManager.isCreativeAvailable(giftIcon)) {
//              validApps.add(o);
//            }
//          } else {
//            PromoteAdManager.checkCreativeReloaded(giftIcon);
//            validApps.add(o);
//          }
//        }
//      }
//    }
//
//    if (validApps.size() > 0) {
//      Random rand = new Random();
//      return validApps.get(rand.nextInt(validApps.size()));
//    }
//    return null;
//  }
//}
