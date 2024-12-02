package com.parfka.adjust.sdk;

import static com.parfka.adjust.sdk.Constants.HIGH;
import static com.parfka.adjust.sdk.Constants.LARGE;
import static com.parfka.adjust.sdk.Constants.LONG;
import static com.parfka.adjust.sdk.Constants.LOW;
import static com.parfka.adjust.sdk.Constants.MEDIUM;
import static com.parfka.adjust.sdk.Constants.NORMAL;
import static com.parfka.adjust.sdk.Constants.SMALL;
import static com.parfka.adjust.sdk.Constants.XLARGE;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Date;
import java.util.Locale;

/**
 * Created by pfms on 06/11/14.
 */
public class DeviceInfo {
    public String playAdId;
    public String playAdIdSource;
    public Boolean isTrackingEnabled;
    private boolean nonGoogleIdsReadOnce = false;
    public String macSha1;
    public String macShortMd5;
    public String androidId;
    public String fbAttributionId;
    public String clientSdk;
    public String packageName;
    public String appVersion;
    public String deviceType;
    public String deviceName;
    public String deviceManufacturer;
    public String osName;
    public String osVersion;
    public int apiLevel;
    public String language;
    public String country;
    public String displayWidth;
    public String displayHeight;
    public String hardwareName;
    public String abi;
    public String buildName;
    public String appInstallTime;
    public String appUpdateTime;

    public long appInstallTimestamp;
    public long appUpdateTimestamp;


    DeviceInfo(Context context, String sdkPrefix) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = Util.getLocale(configuration);
        int screenLayout = configuration.screenLayout;
        ContentResolver contentResolver = context.getContentResolver();

        packageName = getPackageName(context);
        appVersion = getAppVersion(context);
        deviceType = getDeviceType(screenLayout);
        deviceName = getDeviceName();
        deviceManufacturer = getDeviceManufacturer();
        osName = getOsName();
        osVersion = getOsVersion();
        apiLevel = getApiLevel();
        language = getLanguage(locale);
        country = getCountry(locale);
        displayWidth = getDisplayWidth(displayMetrics);
        displayHeight = getDisplayHeight(displayMetrics);
        clientSdk = getClientSdk(sdkPrefix);
        fbAttributionId = getFacebookAttributionId(context);
        hardwareName = getHardwareName();
        abi = getABI();
        buildName = getBuildName();
        appInstallTime = getAppInstallTime(context);
        appUpdateTime = getAppUpdateTime(context);

        appInstallTimestamp = getAppInstallTimestamp(context) / 1000;
        appUpdateTimestamp = getAppUpdateTimestamp(context) / 1000;
    }

    public void reloadPlayIds(Context context) {
        try {
            playAdIdSource = null;
            for (int i = 0; i < 3; i += 1) {
                try {
                    GooglePlayServicesClient.GooglePlayServicesInfo gpsInfo = GooglePlayServicesClient.getGooglePlayServicesInfo(context);
                    playAdId = gpsInfo.getGpsAdid();
                    if (playAdId != null) {
                        playAdIdSource = "service";
                        break;
                    }
                } catch (Exception e) {
                }
                playAdId = Util.getPlayAdId(context);
                if (playAdId != null) {
                    playAdIdSource = "library";
                    break;
                }
            }
            for (int i = 0; i < 3; i += 1) {
                try {
                    GooglePlayServicesClient.GooglePlayServicesInfo gpsInfo = GooglePlayServicesClient.getGooglePlayServicesInfo(context);
                    isTrackingEnabled = gpsInfo.isTrackingEnabled();
                    if (isTrackingEnabled != null) {
                        break;
                    }
                } catch (Exception e) {
                }
                isTrackingEnabled = Util.isPlayTrackingEnabled(context);
                if (isTrackingEnabled != null) {
                    break;
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    void reloadNonPlayIds(Context context) {
        if (nonGoogleIdsReadOnce) {
            return;
        }
        try {
            if (!Util.checkPermission(context, android.Manifest.permission.ACCESS_WIFI_STATE)) {
                // AdjustFactory.getLogger().warn("Missing permission: ACCESS_WIFI_STATE");
            }
            String macAddress = Util.getMacAddress(context);
            macSha1 = getMacSha1(macAddress);
            macShortMd5 = getMacShortMd5(macAddress);
            androidId = Util.getAndroidId(context);
            nonGoogleIdsReadOnce = true;
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private String getPackageName(Context context) {
        return context.getPackageName();
    }

    private String getAppVersion(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            String name = context.getPackageName();
            PackageInfo info = packageManager.getPackageInfo(name, 0);
            return info.versionName;
        } catch (Exception e) {
            return null;
        }
    }

    private String getDeviceType(int screenLayout) {
        int screenSize = screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return "phone";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
            case 4:
                return "tablet";
            default:
                return null;
        }
    }

    private String getDeviceName() {
        return Build.MODEL;
    }

    private String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    private String getOsName() {
        return "android";
    }

    private String getOsVersion() {
        return Build.VERSION.RELEASE;
    }

    private int getApiLevel() {
        return Build.VERSION.SDK_INT;
    }

    private String getLanguage(Locale locale) {
        return locale.getLanguage();
    }

    private String getCountry(Locale locale) {
        return locale.getCountry();
    }

    private String getBuildName() {
        return Build.ID;
    }

    private String getHardwareName() {
        return Build.DISPLAY;
    }

    private String getScreenSize(int screenLayout) {
        int screenSize = screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch (screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return SMALL;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return NORMAL;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return LARGE;
            case 4:
                return XLARGE;
            default:
                return null;
        }
    }

    private String getScreenFormat(int screenLayout) {
        int screenFormat = screenLayout & Configuration.SCREENLAYOUT_LONG_MASK;

        switch (screenFormat) {
            case Configuration.SCREENLAYOUT_LONG_YES:
                return LONG;
            case Configuration.SCREENLAYOUT_LONG_NO:
                return NORMAL;
            default:
                return null;
        }
    }

    private String getScreenDensity(DisplayMetrics displayMetrics) {
        int density = displayMetrics.densityDpi;
        int low = (DisplayMetrics.DENSITY_MEDIUM + DisplayMetrics.DENSITY_LOW) / 2;
        int high = (DisplayMetrics.DENSITY_MEDIUM + DisplayMetrics.DENSITY_HIGH) / 2;

        if (density == 0) {
            return null;
        } else if (density < low) {
            return LOW;
        } else if (density > high) {
            return HIGH;
        }
        return MEDIUM;
    }

    private String getDisplayWidth(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.widthPixels);
    }

    private String getDisplayHeight(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.heightPixels);
    }

    private String getClientSdk(String sdkPrefix) {
        if (sdkPrefix == null) {
            return Constants.CLIENT_SDK;
        } else {
            return Util.formatString("%s@%s", sdkPrefix, Constants.CLIENT_SDK);
        }
    }

    private String getMacSha1(String macAddress) {
        if (macAddress == null) {
            return null;
        }
        String macSha1 = Util.sha1(macAddress);

        return macSha1;
    }

    private String getMacShortMd5(String macAddress) {
        if (macAddress == null) {
            return null;
        }
        String macShort = macAddress.replaceAll(":", "");
        String macShortMd5 = Util.md5(macShort);

        return macShortMd5;
    }

    private String getFacebookAttributionId(final Context context) {
        try {
            final ContentResolver contentResolver = context.getContentResolver();
            final Uri uri = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
            final String columnName = "aid";
            final String[] projection = {columnName};
            final Cursor cursor = contentResolver.query(uri, projection, null, null, null);

            if (cursor == null) {
                return null;
            }
            if (!cursor.moveToFirst()) {
                cursor.close();
                return null;
            }

            int columnIndex = cursor.getColumnIndex(columnName);
            String attributionId = null;
            if (columnIndex >= 0) {
                attributionId = cursor.getString(columnIndex);
            }
            cursor.close();
            return attributionId;
        } catch (Exception e) {
            return null;
        }
    }

    private String getABI() {
        String[] SupportedABIS = Util.getSupportedAbis();

        // SUPPORTED_ABIS is only supported in API level 21
        // get CPU_ABI instead
        if (SupportedABIS == null || SupportedABIS.length == 0) {
            return Util.getCpuAbi();
        }

        return SupportedABIS[0];
    }


    private long getFirstOpenTimestamp(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("parfka", Context.MODE_PRIVATE);
            return sharedPreferences.getLong("app_first_open", 0L);
        } catch (Exception e) {
            return 0L;
        }
    }

    private long getAppInstallTimestamp(Context context) {
        return getFirstOpenTimestamp(context);
//        try {
//            PackageManager packageManager = context.getPackageManager();
//            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
//
//            return packageInfo.firstInstallTime;
//        } catch (Exception ex) {
//            return 0L;
//        }
    }

    private long getAppUpdateTimestamp(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            return packageInfo.lastUpdateTime;
        } catch (Exception ex) {
            return 0L;
        }
    }


    private String getAppInstallTime(Context context) {
        try {
//            PackageManager packageManager = context.getPackageManager();
//            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
            long firstOpenTime = getFirstOpenTimestamp(context);

            String appInstallTime = Util.dateFormatter.format(new Date(firstOpenTime));

            return appInstallTime;
        } catch (Exception ex) {
            return null;
        }
    }

    private String getAppUpdateTime(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);

            String appInstallTime = Util.dateFormatter.format(new Date(packageInfo.lastUpdateTime));

            return appInstallTime;
        } catch (Exception ex) {
            return null;
        }
    }
}
