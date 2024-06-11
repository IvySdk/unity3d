package com.ivy.networks.grid;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.util.Log;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ivy.IvySdk;
import com.ivy.IvyUtils;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.networks.util.Util;
import com.ivy.util.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public final class GridManager {
    public static final String TAG = "GridManager";

    public static final String FILE_JSON_RESPONSE = "jsonResponse";
    public static final String FILE_PROMOTE_RESPONSE = "promoteResponse";
    public static final long GRID_CHECK_INTERVAL_MILLIS = 3600000;
    private static final String PREFS = "prefs";
    private static final String PREFS_JSON_GTS = "gts";
    private static final String PREFS_JSON_GV = "gv";
    public static final String TAG_GRID = "Grid";
    private static int vc;
    private static Long gts = null;
    private final GridSetup gridSetup;
    protected final Activity activity;
    private ExecutorService gridFetchPool = Executors.newFixedThreadPool(1);

    private static JSONObject gridData = new JSONObject();

    private boolean lastGridNetworkStatus = true;

    public GridManager(Activity activity, EventTracker eventTracker, long startupTime, boolean forceUpdate) {
        this.activity = activity;

        this.gridSetup = new GridSetup(this);

        vc = Util.getVersionCode(activity);

        // 如果当前的游戏版本超过保存的grid版本， 需要清除缓存数据，使用assets目录下的资源
        SharedPreferences sp = this.activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        // load the grid data from file cache, if not found, load the default grid data from assets
        String gridString = IvySdk.loadGridData();

        int localCacheVersion = sp.getInt("gridDataVersion", vc);
        if (localCacheVersion != vc) {
            Logger.debug(TAG, "user upgrade the game, we should local the grid from assets");
            gridString = null;
        }

        // read grid data from assets directory
        if (gridString == null) {
            gridString = checkCountryGrid();
            if (gridString == null) {
                String packageName = activity.getPackageName();
                int versionCode = 1;
                try {
                    PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(packageName, 0);
                    versionCode = packageInfo.versionCode;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                final String suffix = activity.getPackageName() + versionCode;
                String fileName = IvyUtils.md5("config_" + suffix);

                Log.d(TAG, "try load security config file:" + fileName);

                gridString = IvyUtils.readSecureText(activity, fileName);

                Logger.debug(TAG, "gridData: " + gridString);

                if (gridString == null) {
                    Log.d(TAG, "Security file is empty, try to load default.json directly");
                    InputStream in = IvyUtils.openAsset(activity, "default.json");
                    if (in != null) {
                        Log.d(TAG, "Plain config file is OK, use it");
                        gridString = IvyUtils.streamToString(in);
                    }
                } else {
                    Log.d(TAG, "Use security config file");
                }
            } else {
                Logger.debug(TAG, "Load country grid for ");
            }

            if (gridString != null) {
                sp.edit().putInt("gridDataVersion", vc).apply();
                Util.storeData(activity, GridManager.FILE_JSON_RESPONSE, gridString);
            }
        }

        try {
            if (gridString != null) {
                GridManager.gridData = new JSONObject(gridString);
                mergeGridWithRemoteConfig();
            }
        } catch (Throwable ex) {
            Logger.error(TAG, "parse grid data exception", ex);
        }

        gridCheck(forceUpdate);

    }

    public String getClientCountryCode() {
        if (this.activity == null) {
            return Locale.getDefault().getCountry().toLowerCase(Locale.ENGLISH);
        }

        SharedPreferences sp = this.activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return sp.getString("clientCountryCode", Locale.getDefault().getCountry().toLowerCase(Locale.ENGLISH));
    }

    private String checkCountryGrid() {
        String sdkCg = null;//IvyUtils.readSecureText(activity, "sdk_cg");
        if (sdkCg == null) {
            IvySdk.debugToast("没有设置分国家配置");
            return null;
        }

        try {
            JSONObject countryConfig = new JSONObject(sdkCg);
            // 获得我当前的country
            SharedPreferences sp = this.activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            String countryCode = sp.getString("clientCountryCode", Locale.getDefault().getCountry().toLowerCase(Locale.ENGLISH));
            if ("".equals(countryCode)) {
                IvySdk.debugToast("Country code is null");
                return null;
            } else {
                IvySdk.debugToast("当前国家: " + countryCode);
            }

            Iterator<String> it = countryConfig.keys();
            String selectedGroupId = null;
            while (it.hasNext()) {
                String groupId = it.next();
                JSONArray countries = countryConfig.optJSONArray(groupId);
                int size = countries.length();
                for (int i = 0; i < size; i++) {
                    if (countryCode.equalsIgnoreCase(countries.optString(i))) {
                        selectedGroupId = groupId;
                        break;
                    }
                }
                if (selectedGroupId != null) {
                    break;
                }
            }
            if (selectedGroupId == null) {
                IvySdk.debugToast("No group specified for country " + countryCode);

                Logger.debug(TAG, "No group specified for country: " + countryCode);
                return null;
            }

            // 检查对应国家的grid文件是否存在
            String countryGridFile = IvyUtils.md5("sdk_" + selectedGroupId);
            String countryGrid = IvyUtils.readSecureText(activity, countryGridFile);
            if (countryGrid != null) {
                JSONObject grid = new JSONObject(countryGrid);
                // 检查下grid文件的格式
                if (!grid.has("gts") || !grid.has("domain")) {
                    return null;
                } else {
                    return countryGrid;
                }
            } else {
                Logger.debug(TAG, "grid file not found for country: " + countryCode);
            }
        } catch (Exception e) {
            Log.e(TAG, "Can not parse country config data");
        }

        return null;
    }

//  private synchronized boolean downloadGridData(Activity activity) {
//    if (gridData == null || activity == null) {
//      Logger.warning(TAG, "No grid data defined locally");
//      return false;
//    }
//
//    lastGridNetworkStatus = IvyUtils.isOnline(activity);
//    if (!lastGridNetworkStatus) {
//      Logger.warning(TAG, "No network, Check the grid download by worker available");
//      // if the worker download file exists? if it does, we will check the grid file, and try to use it
//      return false;
//    }
//
//    Editor preferencesEditor;
//    JSONObject json;
//    long nextGridTsLong;
//
//    long gts;
//
//    SharedPreferences settings = activity.getSharedPreferences("prefs", Context.MODE_PRIVATE);
//    preferencesEditor = settings.edit();
//
//    // 尝试从多个服务器节点下载新的
//    try {
//      String localDomain = gridData.optString("domain");
//
//      String url = IvySdk.getFirebaseRemoteConfigAsString("json_domain", localDomain);
//
//      if (url == null || "".equals(url)) {
//        Log.w(TAG, "domain url is empty");
//        return false;
//      }
//
//      StringBuilder urlBuilder = new StringBuilder(url);
//      // append app_version
//      String adid = null;//Adjust.getAdid();
//
//      urlBuilder.append("&app_version=");
//      urlBuilder.append(vc);
//      urlBuilder.append("&language=");
//      urlBuilder.append(Locale.getDefault().getLanguage());
//      if (adid != null) {
//        urlBuilder.append("&gps_adid=");
//        urlBuilder.append(adid);
//      }
//      String country = getClientCountryCode();
//      if (country != null && !"".equals(country)){
//        urlBuilder.append("&country=");
//        urlBuilder.append(country);
//      }
//      String installReferrer = settings.getString("parfka_install_referrer", null);
//      if (installReferrer != null && !"".equals(installReferrer)) {
//        if (installReferrer.toLowerCase(Locale.ENGLISH).contains("xiaomi")) {
//          urlBuilder.append("&channel=xiaomi");
//        }
//      }
//
//      String realGridUrl = urlBuilder.toString();
//
//      Logger.debug(TAG, "Get Grid from " + realGridUrl);
//
//      OkHttpClient okHttpClient = IvySdk.getOkHttpClient();
//
//      Request request = new Request.Builder().url(realGridUrl).build();
//      Response response = okHttpClient.newCall(request).execute();
//      if (!response.isSuccessful()) {
//        Log.w(TAG, "domain response failed, ignore this request");
//        return false;
//      }
//
//      String data = response.body().string();
//      String decryptData = IvyUtils.readSecureTextByKeystore(activity, data);
//      if (decryptData == null) {
//        Log.w(TAG, "Grid data not valid, ignore " + data);
//        return false;
//      }
//      if ("".equals(decryptData) || "[]".equals(decryptData) || "{}".equals(decryptData)) {
//        Logger.debug(TAG, "Grid data not changed, just use local version");
//        return false;
//      }
//
//      Logger.debug(TAG, "DecryptData: " + decryptData);
//      json = new JSONObject(decryptData);
//      IvySdk.debugToast("New grid downloaded");
//    } catch (Exception e5) {
//      Log.w(TAG, "Grid data remote failed: ", e5);
//      IvySdk.debugToast("Attention! Grid Data Error: " + e5.getMessage());
//      return false;
//    }
//
//    // 更新本地grid最近下载时间
//    if (json.has(PREFS_JSON_GTS)) {
//      gts = json.optLong(PREFS_JSON_GTS);
//
//      preferencesEditor.putBoolean("lastConnectivityWasWifi", Util.isWifi(activity));
//      preferencesEditor.putLong("lastGridDownload", System.currentTimeMillis());
//
//      // if current gts bigger than this gts, ignore this grid data
//      long currentGts = gridData.optLong(PREFS_JSON_GTS);
//      if (currentGts >= gts) {
//        preferencesEditor.apply();
//        Logger.warning(TAG, "current grid data gts is bigger than remote, ignore the remote settings, current: " + currentGts + ", remote: " + gts);
//        return false;
//      }
//      preferencesEditor.putLong(PREFS_JSON_GTS, gts);
//      setGts(gts);
//    } else {
//      Logger.error(TAG, "Invalid grid json, ignore");
//      return false;
//    }
//
//    if (json.has(PREFS_JSON_GV)) {
//      int gv = json.optInt(PREFS_JSON_GV);
//      if (gv < gridData.optInt(PREFS_JSON_GV)) {
//        Log.w(TAG, "grid version small than local settings, ignore the remote settings");
//        return false;
//      }
//    }
//
//    // 下次更新grid的请求时间戳,
//    // 设定的值, 必须是当前时间1小时后, 如果没有包含此字段, 或小于1 hour, 将触发清除缓存的nextGridTs操作
//    // 下次打开将请求新的grid数据
//    if (json.has("nextGridTs")) {
//      nextGridTsLong = json.optLong("nextGridTs", 0);
//      if (nextGridTsLong > System.currentTimeMillis() + 3600000) {
//        preferencesEditor.putString("nextGridTs", nextGridTsLong + "");
//      } else {
//        if (settings.contains("nextGridTs")) {
//          preferencesEditor.remove("nextGridTs");
//        }
//      }
//    } else {
//      if (settings.contains("nextGridTs")) {
//        preferencesEditor.remove("nextGridTs");
//      }
//    }
//
//    // update the client country code by grid data
//    if (json.has("clientCountryCode")) {
//      String country = json.optString("clientCountCode", "");
//      if (!"".equals(country)) {
//        preferencesEditor.putString("clientCountryCode", country);
//      }
//    }
//
//    preferencesEditor.putInt("gridDataVersion", Util.getVersionCode(activity));
//    preferencesEditor.apply();
//
//    Util.storeData(activity, FILE_JSON_RESPONSE, json.toString());
//
//    Logger.debug(TAG, "new grid saved");
//    gridData = json;
//    mergeGridWithRemoteConfig();
//    ConfigurationParser.clearCache();
//
//    // log the grid update event
//    Log.w(TAG, "grid was override by remote");
//
//    return true;
//  }

    private static void setGts(Long gts) {
        if (gts < 0) {
            gts = null;
        }
        GridManager.gts = gts;
    }

    private void gridCheck(boolean force) {
        if (this.gridSetup != null) {
            this.gridSetup.checkGrid(force);
        }
    }

//  private AtomicBoolean isDownloading = new AtomicBoolean(false);
//  public void setupGrid() {
//    if (this.gridFetchPool != null) {
//      this.gridFetchPool.execute(new Runnable() {
//        public void run() {
//          if (isDownloading.getAndSet(true)) {
//            Log.w(TAG, "Grid is downloading");
//            return;
//          }
//          try {
//            if (!downloadGridData(activity)) {
//              Logger.debug(TAG, "Grid Download failed");
//            }
//          } catch (Exception ex) {
//            Logger.error(TAG, "setupGrid failed", ex);
//          } finally {
//            isDownloading.set(false);
//          }
//        }
//      });
//    }
//  }

    public interface AdProvidersCallback {
        void setupAdProviders(String str, boolean z);
    }

    public static JSONObject getGridData() {
        return gridData;
    }

    public static boolean isMixAdEvents() {
        try {
            if (gridData.has("mixAdEvents")) {
                return gridData.optBoolean("mixAdEvents", false);
            }
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 从remote config中读取部分配置并覆盖
     */
    private void mergeGridWithRemoteConfig() {
        FirebaseRemoteConfig firebaseRemoteConfig = IvySdk.getFirebaseRemoteConfig();
        if (firebaseRemoteConfig == null) {
            return;
        }

        String remoteGridData = firebaseRemoteConfig.getString("config_grid_data_android");
        if (!FirebaseRemoteConfig.DEFAULT_VALUE_FOR_STRING.equals(remoteGridData)) {
            try {
                JSONObject jsonObject = new JSONObject(remoteGridData);
                if (jsonObject.has("v_api")) {
                    gridData = jsonObject;
                }
            } catch (Throwable t) {
                Logger.error(TAG, "remote grid data exception", t);
            }
        }

        // banner配置 ad_config_banner
        String bannerConfig = firebaseRemoteConfig.getString("ad_config_banner");
        if (!FirebaseRemoteConfig.DEFAULT_VALUE_FOR_STRING.equals(bannerConfig)) {
            try {
                JSONObject jsonObject = new JSONObject(bannerConfig);
                if (jsonObject.has("ads")) {
                    JSONArray banner = jsonObject.optJSONArray("ads");
                    if (banner != null && banner.length() > 0) {
                        gridData.put("banner", banner);
                        if (jsonObject.has("bannerLoadTimeoutSeconds")) {
                            int bannerLoadTimeoutSeconds = jsonObject.optInt("bannerLoadTimeoutSeconds", 0);
                            if (bannerLoadTimeoutSeconds > 0) {
                                gridData.put("bannerLoadTimeoutSeconds", bannerLoadTimeoutSeconds);
                            }
                        }
                        if (jsonObject.has("adRefreshInterval")) {
                            int adRefreshInterval = jsonObject.optInt("adRefreshInterval", 0);
                            if (adRefreshInterval > 0) {
                                gridData.put("adRefreshInterval", adRefreshInterval);
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                Logger.error(TAG, "ad_config_banner data error");
            }
        }

        // 插屏配置 ad_config_full
        String fullConfig = firebaseRemoteConfig.getString("ad_config_full");
        if (!FirebaseRemoteConfig.DEFAULT_VALUE_FOR_STRING.equals(fullConfig)) {
            try {
                JSONObject jsonObject = new JSONObject(fullConfig);
                if (jsonObject.has("ads")) {
                    JSONArray full = jsonObject.optJSONArray("ads");
                    if (full != null && full.length() > 0) {
                        gridData.put("full", full);
                    }
                }
            } catch (Throwable t) {
                Logger.error(TAG, "ad_config_full data error");
            }
        }

        // 激励视频配置
        String rewardedConfig = firebaseRemoteConfig.getString("ad_config_video");
        if (!FirebaseRemoteConfig.DEFAULT_VALUE_FOR_STRING.equals(rewardedConfig)) {
            try {
                JSONObject jsonObject = new JSONObject(rewardedConfig);
                if (jsonObject.has("ads")) {
                    JSONArray video = jsonObject.optJSONArray("ads");
                    if (video != null && video.length() > 0) {
                        gridData.put("video", video);
                    }
                }
            } catch (Throwable t) {
                Logger.error(TAG, "ad_config_video data error");
            }
        }
    }
}