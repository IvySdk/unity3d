//
//  PackageBuilder.java
//  Adjust SDK
//
//  Created by Christian Wellenbrock (@wellle) on 25th June 2013.
//  Copyright (c) 2013-2018 Adjust GmbH. All rights reserved.
//

package com.parfka.adjust.sdk;

import java.util.Iterator;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;

import android.text.TextUtils;
import android.content.ContentResolver;

public class PackageBuilder {
  private static ILogger logger = AdjustFactory.getLogger();
  private long createdAt;
  private DeviceInfo deviceInfo;
  private AdjustConfig adjustConfig;
  private ActivityStateCopy activityStateCopy;
  private SessionParameters sessionParameters;

  long clickTimeInSeconds = -1;
  long clickTimeInMilliseconds = -1;
  long installBeginTimeInSeconds = -1;
  String reftag;
  String deeplink;
  String referrer;
  String rawReferrer;
  AdjustAttribution attribution;
  Map<String, Object> extraParameters;

  private class ActivityStateCopy {
    int eventCount = -1;
    int sessionCount = -1;
    int subsessionCount = -1;
    long timeSpent = -1;
    long lastInterval = -1;
    long sessionLength = -1;
    String uuid = null;
    String pushToken = null;
    String userHashId = null;

    ActivityStateCopy(ActivityState activityState) {
      if (activityState == null) {
        return;
      }
      this.eventCount = activityState.eventCount;
      this.sessionCount = activityState.sessionCount;
      this.subsessionCount = activityState.subsessionCount;
      this.timeSpent = activityState.timeSpent;
      this.lastInterval = activityState.lastInterval;
      this.sessionLength = activityState.sessionLength;
      this.uuid = activityState.uuid;
      this.pushToken = activityState.pushToken;
      this.userHashId = activityState.userHashId;
    }
  }

  PackageBuilder(AdjustConfig adjustConfig,
                 DeviceInfo deviceInfo,
                 ActivityState activityState,
                 SessionParameters sessionParameters,
                 long createdAt) {
    this.createdAt = createdAt;
    this.deviceInfo = deviceInfo;
    this.adjustConfig = adjustConfig;
    this.activityStateCopy = new ActivityStateCopy(activityState);
    this.sessionParameters = sessionParameters;
  }

  ActivityPackage buildEventPackage(AdjustEvent event, boolean isInDelay) {
    Map<String, Object> parameters = getEventParameters(event, isInDelay);
    ActivityPackage eventPackage = getDefaultActivityPackage(ActivityKind.EVENT);
    eventPackage.setPath("/event");
    eventPackage.setSuffix(getEventSuffix(event));
    eventPackage.setParameters(parameters);

    if (isInDelay) {
      eventPackage.setPartnerParameters(event.partnerParameters);
    }

    return eventPackage;
  }

  ActivityPackage buildInfoPackage(String source) {
    Map<String, Object> parameters = getInfoParameters(source);
    ActivityPackage clickPackage = getDefaultActivityPackage(ActivityKind.INFO);
    clickPackage.setPath("/sdk_info");
    clickPackage.setSuffix("");
    clickPackage.setParameters(parameters);
    return clickPackage;
  }

  ActivityPackage buildClickPackage(String source) {
    Map<String, Object> parameters = getClickParameters(source);
    ActivityPackage clickPackage = getDefaultActivityPackage(ActivityKind.CLICK);
    clickPackage.setPath("/sdk_click");
    clickPackage.setSuffix("");
    clickPackage.setClickTimeInMilliseconds(clickTimeInMilliseconds);
    clickPackage.setClickTimeInSeconds(clickTimeInSeconds);
    clickPackage.setInstallBeginTimeInSeconds(installBeginTimeInSeconds);
    clickPackage.setParameters(parameters);
    return clickPackage;
  }

  ActivityPackage buildAttributionPackage(String initiatedByDescription) {
    Map<String, Object> parameters = getAttributionParameters(initiatedByDescription);
    ActivityPackage attributionPackage = getDefaultActivityPackage(ActivityKind.ATTRIBUTION);
    attributionPackage.setPath("attribution"); // does not contain '/' because of Uri.Builder.appendPath
    attributionPackage.setSuffix("");
    attributionPackage.setParameters(parameters);
    return attributionPackage;
  }

  ActivityPackage buildGdprPackage() {
    Map<String, Object> parameters = getGdprParameters();
    ActivityPackage gdprPackage = getDefaultActivityPackage(ActivityKind.GDPR);
    gdprPackage.setPath("/gdpr_forget_device");
    gdprPackage.setSuffix("");
    gdprPackage.setParameters(parameters);
    return gdprPackage;
  }




  private void putAttribution(Map<String, Object> parameters, AdjustAttribution attribution) {
    // Attribution parameters.
    if (attribution != null) {
//      PackageBuilder.addString(parameters, "tracker", attribution.trackerName);
//      PackageBuilder.addString(parameters, "adgroup", attribution.adgroup);
//      PackageBuilder.addString(parameters, "creative", attribution.creative);
      PackageBuilder.addString(parameters, "campaign", attribution.campaign);
      // creative --> traffic_source_medium
      PackageBuilder.addString(parameters, "traffic_source_medium", attribution.creative);
      // trackerName --> traffic_source_name
      PackageBuilder.addString(parameters, "traffic_source_name", attribution.trackerName);
      // trackToken -> traffic_source_ad_name
      PackageBuilder.addString(parameters, "traffic_source_ad_name", attribution.trackerToken);
      // adgroup --> traffic_source_term_id
      PackageBuilder.addString(parameters, "traffic_source_term_id", attribution.adgroup);
    }
  }

  public Map<String, Object> getEventParameters(AdjustEvent event, boolean isInDelay) {
    Map<String, Object> parameters = new HashMap<String, Object>();

    // Callback and partner parameters.
    if (!isInDelay) {
      PackageBuilder.addJsonAll(parameters,  event.partnerParameters);
    }

    // Device identifiers.
    deviceInfo.reloadPlayIds(adjustConfig.context);

    PackageBuilder.addString(parameters, "android_uuid", activityStateCopy.uuid);
    PackageBuilder.addString(parameters, "gps_adid", deviceInfo.playAdId);
    PackageBuilder.addString(parameters, "gps_adid_src", deviceInfo.playAdIdSource);

    if (!containsPlayIds(parameters)) {
      logger.warn("Google Advertising ID not detected, fallback to non Google Play identifiers will take place");
      deviceInfo.reloadNonPlayIds(adjustConfig.context);
      PackageBuilder.addString(parameters, "mac_sha1", deviceInfo.macSha1);
      PackageBuilder.addString(parameters, "mac_md5", deviceInfo.macShortMd5);
      PackageBuilder.addString(parameters, "android_id", deviceInfo.androidId);
    }

    this.putAttribution(parameters, attribution);

    // parameters indexed by server
    PackageBuilder.addString(parameters, "app_token", adjustConfig.appToken);
      PackageBuilder.addLong(parameters, "api_level", deviceInfo.apiLevel);
    PackageBuilder.addString(parameters, "country", deviceInfo.country);
    PackageBuilder.addString(parameters, "cpu_type", deviceInfo.abi);
    PackageBuilder.addString(parameters, "device_manufacturer", deviceInfo.deviceManufacturer);
    PackageBuilder.addString(parameters, "device_name", deviceInfo.deviceName);
    PackageBuilder.addString(parameters, "device_type", deviceInfo.deviceType);
    PackageBuilder.addString(parameters, "display_height", deviceInfo.displayHeight);
    PackageBuilder.addString(parameters, "display_width", deviceInfo.displayWidth);
    PackageBuilder.addString(parameters, "os_build", deviceInfo.buildName);
    PackageBuilder.addString(parameters, "os_name", deviceInfo.osName);
    PackageBuilder.addString(parameters, "os_version", deviceInfo.osVersion);
    PackageBuilder.addString(parameters, "language", deviceInfo.language);
    PackageBuilder.addString(parameters, "package_name", deviceInfo.packageName);
    PackageBuilder.addString(parameters, "app_version", deviceInfo.appVersion);
    PackageBuilder.addString(parameters, "mcc", Util.getMcc(adjustConfig.context));
    PackageBuilder.addString(parameters, "mnc", Util.getMnc(adjustConfig.context));

    // Rest of the parameters.
    PackageBuilder.addString(parameters, "app_secret", adjustConfig.appSecret);
    PackageBuilder.addLong(parameters, "connectivity_type", Util.getConnectivityType(adjustConfig.context));
    PackageBuilder.addLong(parameters, "online", Util.getOnlineStatus(adjustConfig.context));
    PackageBuilder.addDateInMilliseconds(parameters, "created_at", createdAt);
    PackageBuilder.addString(parameters, "currency", event.currency);
    PackageBuilder.addString(parameters, "event_callback_id", event.callbackId);
    PackageBuilder.addLong(parameters, "event_count", activityStateCopy.eventCount);
    PackageBuilder.addString(parameters, "event_token", event.eventToken);
    PackageBuilder.addString(parameters, "fb_id", deviceInfo.fbAttributionId);
    PackageBuilder.addLong(parameters, "network_type", Util.getNetworkType(adjustConfig.context));
    if (event.revenue != null && event.revenue > 0) {
      PackageBuilder.addDouble(parameters, "revenue", event.revenue);
    }
    PackageBuilder.addString(parameters, "secret_id", adjustConfig.secretId);
    PackageBuilder.addLong(parameters, "session_count", activityStateCopy.sessionCount);
    PackageBuilder.addDuration(parameters, "session_length", activityStateCopy.sessionLength);
    PackageBuilder.addLong(parameters, "subsession_count", activityStateCopy.subsessionCount);
    PackageBuilder.addDuration(parameters, "time_spent", activityStateCopy.timeSpent);
    PackageBuilder.addString(parameters, "installed_at", deviceInfo.appInstallTime);

    PackageBuilder.addLong(parameters, "it", deviceInfo.appInstallTimestamp);
    PackageBuilder.addLong(parameters, "ut", deviceInfo.appUpdateTimestamp);
    checkDeviceIds(parameters);
    return parameters;
  }

  private Map<String, Object> getInfoParameters(String source) {
    Map<String, Object> parameters = new HashMap<String, Object>();

    // Device identifiers.
    deviceInfo.reloadPlayIds(adjustConfig.context);
    PackageBuilder.addString(parameters, "android_uuid", activityStateCopy.uuid);
    PackageBuilder.addBoolean(parameters, "tracking_enabled", deviceInfo.isTrackingEnabled);
    PackageBuilder.addString(parameters, "gps_adid", deviceInfo.playAdId);
    PackageBuilder.addString(parameters, "gps_adid_src", deviceInfo.playAdIdSource);

    if (!containsPlayIds(parameters)) {
      logger.warn("Google Advertising ID not detected, fallback to non Google Play identifiers will take place");
      deviceInfo.reloadNonPlayIds(adjustConfig.context);
      PackageBuilder.addString(parameters, "mac_sha1", deviceInfo.macSha1);
      PackageBuilder.addString(parameters, "mac_md5", deviceInfo.macShortMd5);
      PackageBuilder.addString(parameters, "android_id", deviceInfo.androidId);
    }

    // Rest of the parameters.
    PackageBuilder.addString(parameters, "app_secret", adjustConfig.appSecret);
    PackageBuilder.addString(parameters, "app_token", adjustConfig.appToken);
    PackageBuilder.addString(parameters, "package_name", deviceInfo.packageName);
    PackageBuilder.addBoolean(parameters, "attribution_deeplink", true);
    PackageBuilder.addDateInMilliseconds(parameters, "created_at", createdAt);
    PackageBuilder.addString(parameters, "environment", adjustConfig.environment);
    PackageBuilder.addString(parameters, "secret_id", adjustConfig.secretId);
    PackageBuilder.addString(parameters, "source", source);

    checkDeviceIds(parameters);
    return parameters;
  }

  private Map<String, Object> getClickParameters(String source) {
    ContentResolver contentResolver = adjustConfig.context.getContentResolver();
    Map<String, Object> parameters = new HashMap<String, Object>();


    if (extraParameters != null) {
      this.sessionParameters.partnerParameters.putAll(extraParameters);
    }
    PackageBuilder.addJsonAll(parameters,  this.sessionParameters.partnerParameters);

    // Device identifiers.
    deviceInfo.reloadPlayIds(adjustConfig.context);
    PackageBuilder.addString(parameters, "android_uuid", activityStateCopy.uuid);
    PackageBuilder.addBoolean(parameters, "tracking_enabled", deviceInfo.isTrackingEnabled);
    PackageBuilder.addString(parameters, "gps_adid", deviceInfo.playAdId);
    PackageBuilder.addString(parameters, "gps_adid_src", deviceInfo.playAdIdSource);

    if (!containsPlayIds(parameters)) {
      logger.warn("Google Advertising ID not detected, fallback to non Google Play identifiers will take place");
      deviceInfo.reloadNonPlayIds(adjustConfig.context);
      PackageBuilder.addString(parameters, "mac_sha1", deviceInfo.macSha1);
      PackageBuilder.addString(parameters, "mac_md5", deviceInfo.macShortMd5);
      PackageBuilder.addString(parameters, "android_id", deviceInfo.androidId);
    }

    // Attribution parameters.
    this.putAttribution(parameters, attribution);

    // parameters indexed by server
    PackageBuilder.addString(parameters, "app_token", adjustConfig.appToken);
    PackageBuilder.addLong(parameters, "api_level", deviceInfo.apiLevel);
    PackageBuilder.addString(parameters, "country", deviceInfo.country);
    PackageBuilder.addString(parameters, "cpu_type", deviceInfo.abi);
    PackageBuilder.addString(parameters, "device_manufacturer", deviceInfo.deviceManufacturer);
    PackageBuilder.addString(parameters, "device_name", deviceInfo.deviceName);
    PackageBuilder.addString(parameters, "device_type", deviceInfo.deviceType);
    PackageBuilder.addString(parameters, "display_height", deviceInfo.displayHeight);
    PackageBuilder.addString(parameters, "display_width", deviceInfo.displayWidth);
    PackageBuilder.addString(parameters, "hardware_name", deviceInfo.hardwareName);
    PackageBuilder.addString(parameters, "os_build", deviceInfo.buildName);
    PackageBuilder.addString(parameters, "os_name", deviceInfo.osName);
    PackageBuilder.addString(parameters, "os_version", deviceInfo.osVersion);
    PackageBuilder.addString(parameters, "language", deviceInfo.language);
    PackageBuilder.addString(parameters, "environment", adjustConfig.environment);
    PackageBuilder.addString(parameters, "package_name", deviceInfo.packageName);

    // Rest of the parameters.
    PackageBuilder.addString(parameters, "app_secret", adjustConfig.appSecret);
    PackageBuilder.addString(parameters, "app_version", deviceInfo.appVersion);
    PackageBuilder.addBoolean(parameters, "attribution_deeplink", true);
    PackageBuilder.addDateInMilliseconds(parameters, "click_time", clickTimeInMilliseconds);
    PackageBuilder.addDateInSeconds(parameters, "click_time", clickTimeInSeconds);
    PackageBuilder.addLong(parameters, "connectivity_type", Util.getConnectivityType(adjustConfig.context));
    PackageBuilder.addLong(parameters, "online", Util.getOnlineStatus(adjustConfig.context));
    PackageBuilder.addDateInMilliseconds(parameters, "created_at", createdAt);
    PackageBuilder.addString(parameters, "deeplink", deeplink);
    PackageBuilder.addString(parameters, "fb_id", deviceInfo.fbAttributionId);
    PackageBuilder.addDateInSeconds(parameters, "install_begin_time", installBeginTimeInSeconds);
    PackageBuilder.addString(parameters, "installed_at", deviceInfo.appInstallTime);
    PackageBuilder.addDuration(parameters, "last_interval", activityStateCopy.lastInterval);
    PackageBuilder.addString(parameters, "mcc", Util.getMcc(adjustConfig.context));
    PackageBuilder.addString(parameters, "mnc", Util.getMnc(adjustConfig.context));
    PackageBuilder.addLong(parameters, "network_type", Util.getNetworkType(adjustConfig.context));
    PackageBuilder.addString(parameters, "raw_referrer", rawReferrer);
    PackageBuilder.addString(parameters, "referrer", referrer);
    PackageBuilder.addString(parameters, "reftag", reftag);
    PackageBuilder.addString(parameters, "secret_id", adjustConfig.secretId);
    PackageBuilder.addLong(parameters, "session_count", activityStateCopy.sessionCount);
    PackageBuilder.addDuration(parameters, "session_length", activityStateCopy.sessionLength);
    PackageBuilder.addString(parameters, "source", source);
    PackageBuilder.addLong(parameters, "subsession_count", activityStateCopy.subsessionCount);
    PackageBuilder.addDuration(parameters, "time_spent", activityStateCopy.timeSpent);

    PackageBuilder.addLong(parameters, "it", deviceInfo.appInstallTimestamp);
    PackageBuilder.addLong(parameters, "ut", deviceInfo.appUpdateTimestamp);

    checkDeviceIds(parameters);
    return parameters;
  }

  private Map<String, Object> getAttributionParameters(String initiatedBy) {
    Map<String, Object> parameters = new HashMap<String, Object>();

    // Device identifiers.
    deviceInfo.reloadPlayIds(adjustConfig.context);
    PackageBuilder.addString(parameters, "android_uuid", activityStateCopy.uuid);
    PackageBuilder.addBoolean(parameters, "tracking_enabled", deviceInfo.isTrackingEnabled);
    PackageBuilder.addString(parameters, "gps_adid", deviceInfo.playAdId);
    PackageBuilder.addString(parameters, "gps_adid_src", deviceInfo.playAdIdSource);

    if (!containsPlayIds(parameters)) {
      logger.warn("Google Advertising ID not detected, fallback to non Google Play identifiers will take place");
      deviceInfo.reloadNonPlayIds(adjustConfig.context);
//      PackageBuilder.addString(parameters, "mac_sha1", deviceInfo.macSha1);
//      PackageBuilder.addString(parameters, "mac_md5", deviceInfo.macShortMd5);
//      PackageBuilder.addString(parameters, "android_id", deviceInfo.androidId);
    }

    // Rest of the parameters.
    PackageBuilder.addLong(parameters, "api_level", deviceInfo.apiLevel);
    PackageBuilder.addString(parameters, "app_secret", adjustConfig.appSecret);
    PackageBuilder.addString(parameters, "app_token", adjustConfig.appToken);
    PackageBuilder.addString(parameters, "app_version", deviceInfo.appVersion);
    PackageBuilder.addBoolean(parameters, "attribution_deeplink", true);
    PackageBuilder.addDateInMilliseconds(parameters, "created_at", createdAt);
    PackageBuilder.addBoolean(parameters, "device_known", adjustConfig.deviceKnown);
    PackageBuilder.addString(parameters, "device_name", deviceInfo.deviceName);
    PackageBuilder.addString(parameters, "device_type", deviceInfo.deviceType);
    PackageBuilder.addString(parameters, "environment", adjustConfig.environment);
    PackageBuilder.addString(parameters, "initiated_by", initiatedBy);
    PackageBuilder.addString(parameters, "os_name", deviceInfo.osName);
    PackageBuilder.addString(parameters, "os_version", deviceInfo.osVersion);
    PackageBuilder.addString(parameters, "package_name", deviceInfo.packageName);
    PackageBuilder.addString(parameters, "secret_id", adjustConfig.secretId);

    PackageBuilder.addLong(parameters, "it", deviceInfo.appInstallTimestamp);
    PackageBuilder.addLong(parameters, "ut", deviceInfo.appUpdateTimestamp);

    checkDeviceIds(parameters);
    return parameters;
  }

  private Map<String, Object> getGdprParameters() {
    Map<String, Object> parameters = new HashMap<String, Object>();

    // Device identifiers.
    deviceInfo.reloadPlayIds(adjustConfig.context);
    PackageBuilder.addString(parameters, "android_uuid", activityStateCopy.uuid);
    PackageBuilder.addBoolean(parameters, "tracking_enabled", deviceInfo.isTrackingEnabled);
    PackageBuilder.addString(parameters, "gps_adid", deviceInfo.playAdId);
    PackageBuilder.addString(parameters, "gps_adid_src", deviceInfo.playAdIdSource);

    if (!containsPlayIds(parameters)) {
      logger.warn("Google Advertising ID not detected, fallback to non Google Play identifiers will take place");
      deviceInfo.reloadNonPlayIds(adjustConfig.context);
//      PackageBuilder.addString(parameters, "mac_sha1", deviceInfo.macSha1);
//      PackageBuilder.addString(parameters, "mac_md5", deviceInfo.macShortMd5);
//      PackageBuilder.addString(parameters, "android_id", deviceInfo.androidId);
    }

    // Rest of the parameters.
    PackageBuilder.addLong(parameters, "api_level", deviceInfo.apiLevel);
    PackageBuilder.addString(parameters, "app_secret", adjustConfig.appSecret);
    PackageBuilder.addString(parameters, "app_token", adjustConfig.appToken);
    PackageBuilder.addString(parameters, "app_version", deviceInfo.appVersion);
    PackageBuilder.addDateInMilliseconds(parameters, "created_at", createdAt);
    PackageBuilder.addBoolean(parameters, "device_known", adjustConfig.deviceKnown);
    PackageBuilder.addString(parameters, "device_name", deviceInfo.deviceName);
    PackageBuilder.addString(parameters, "device_type", deviceInfo.deviceType);
    PackageBuilder.addString(parameters, "environment", adjustConfig.environment);
    PackageBuilder.addString(parameters, "os_name", deviceInfo.osName);
    PackageBuilder.addString(parameters, "os_version", deviceInfo.osVersion);
    PackageBuilder.addString(parameters, "package_name", deviceInfo.packageName);
    PackageBuilder.addString(parameters, "secret_id", adjustConfig.secretId);

    checkDeviceIds(parameters);
    return parameters;
  }

  private ActivityPackage getDefaultActivityPackage(ActivityKind activityKind) {
    ActivityPackage activityPackage = new ActivityPackage(activityKind);
    activityPackage.setClientSdk(deviceInfo.clientSdk);
    return activityPackage;
  }

  public static void addString(Map<String, Object> parameters, String key, String value) {
    if (TextUtils.isEmpty(value)) {
      return;
    }
    parameters.put(key, value);
  }

  public static void addBoolean(Map<String, Object> parameters, String key, Boolean value) {
    if (value == null) {
      return;
    }
    int intValue = value ? 1 : 0;
    parameters.put(key, intValue);
  }


  static void addJsonAll(Map<String, Object> parameters, Map<String, Object> map) {
    if (map == null) {
      return;
    }
    if (map.size() == 0) {
      return;
    }

    Iterator<String> it = map.keySet().iterator();
    while(it.hasNext()) {
      String key = it.next();
      Object value = map.get(key);
      parameters.put(key, value);
    }
  }

  static void addMapJson(Map<String, Object> parameters, String key, Map<String, Object> map) {
    if (map == null) {
      return;
    }
    if (map.size() == 0) {
      return;
    }

    parameters.put(key, map);
  }

  public static void addLong(Map<String, Object> parameters, String key, long value) {
    if (value < 0) {
      return;
    }
    parameters.put(key, value);
  }

  private static void addDateInMilliseconds(Map<String, Object> parameters, String key, long value) {
    if (value <= 0) {
      return;
    }
    parameters.put(key, value);
  }

  private static void addDateInSeconds(Map<String, Object> parameters, String key, long value) {
    if (value <= 0) {
      return;
    }
    Date date = new Date(value * 1000);
    PackageBuilder.addDate(parameters, key, date);
  }

  private static void addDate(Map<String, Object> parameters, String key, Date value) {
    if (value == null) {
      return;
    }
    String dateString = Util.dateFormatter.format(value);
    PackageBuilder.addString(parameters, key, dateString);
  }

  private static void addDuration(Map<String, Object> parameters, String key, long durationInMilliSeconds) {
    if (durationInMilliSeconds < 0) {
      return;
    }
    long durationInSeconds = (durationInMilliSeconds + 500) / 1000;
    PackageBuilder.addLong(parameters, key, durationInSeconds);
  }

  private static void addDouble(Map<String, Object> parameters, String key, Double value) {
    if (value == null) {
      return;
    }
    String doubleString = Util.formatString("%.5f", value);
    PackageBuilder.addString(parameters, key, doubleString);
  }

  private boolean containsPlayIds(Map<String, Object> parameters) {
    if (parameters == null) {
      return false;
    }
    return parameters.containsKey("tracking_enabled") || parameters.containsKey("gps_adid");
  }

  private void checkDeviceIds(Map<String, Object> parameters) {
    if (parameters != null && !parameters.containsKey("mac_sha1")
      && !parameters.containsKey("mac_md5")
      && !parameters.containsKey("android_id")
      && !parameters.containsKey("gps_adid")) {
      logger.error("Missing device id's. Please check if Proguard is correctly set with Adjust SDK");
    }
  }

  private String getEventSuffix(AdjustEvent event) {
    if (event.revenue == null) {
      return Util.formatString("'%s'", event.eventToken);
    } else {
      return Util.formatString("(%.5f %s, '%s')", event.revenue, event.currency, event.eventToken);
    }
  }
}
