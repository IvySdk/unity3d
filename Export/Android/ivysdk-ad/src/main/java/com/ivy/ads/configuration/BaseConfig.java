package com.ivy.ads.configuration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BaseConfig {
  private static final long FIXED_AD_REFRESH_TIMEOUT = 10000;
  public static long AD_REFRESH_TIMEOUT = 10000;
  static long AD_REFRESH_INTERVAL = 30000;
  public Ad ad = new Ad();
  public int adLoadTimeout = 10;
  public int adRefreshInterval = ((int) (AD_REFRESH_INTERVAL / 1000));
  public String clientCountryCode = "";
  public boolean hideAdLabel = false;

  /**
   * 从JSON数据中获取配置
   *
   * @param jsonObject
   */
  public BaseConfig fillFromJson(JSONObject jsonObject) {
    if (jsonObject.has("adLoadTimeout")) {
      this.adLoadTimeout = jsonObject.optInt("adLoadTimeout");
    }
    if (jsonObject.has("adRefreshInterval")) {
      this.adRefreshInterval = jsonObject.optInt("adRefreshInterval");
    }
    if (jsonObject.has("clientCountryCode")) {
      this.clientCountryCode = jsonObject.optString("clientCountryCode");
    }

    if (jsonObject.has("hideAdLabel")) {
      this.hideAdLabel = jsonObject.optBoolean("hideAdLabel");
    }
    return this;
  }

  public static class Ad {
    public boolean bannerFingerPrintingUseExtendedBarcode = true;
    public long connectionEstablishedTimeoutMilliseconds = 10000;
    public long rewardedClipLoadTimeoutSeconds = 8;
    public boolean s2sDisableDownloadManager = false;
    public List<String> s2sTemplateURLs = new ArrayList();
    public boolean s2sUseBuiltInBrowser = true;
    public long s2sWebViewLoadWaitTimeSeconds = 5;
    public long socketTimeoutMilliseconds = 10000;
    public String soomlaTrackingAppId = null;
    public int timeToWaitForAdToShowSeconds = 3;
    public boolean useBannerFingerPrinting = false;
  }
}
