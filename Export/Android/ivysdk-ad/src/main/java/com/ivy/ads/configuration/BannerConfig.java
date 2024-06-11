package com.ivy.ads.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BannerConfig extends BaseConfig {
  private static final long FIXED_AD_REFRESH_TIMEOUT = 10000;
  public static long AD_REFRESH_INTERVAL = 180000;
  public static long AD_REFRESH_TIMEOUT = 10000;
  public Ad ad = new Ad();

  public long adFullScreenTimespan = 300;
  public boolean adLabel;
  public int adParallelRequests = 1;
  public int adParallelWaitTime = 5;

  // 所有
  public List<JSONObject> adProviderPriority = new ArrayList();

  public int adRefreshInterval = ((int) (AD_REFRESH_INTERVAL / 1000));

  @Override
  public BaseConfig fillFromJson(JSONObject jsonObject) {
    super.fillFromJson(jsonObject);
    if (jsonObject.has("adFullScreenTimespan")) {
      this.adFullScreenTimespan = jsonObject.optInt("adFullScreenTimespan");
    }
    if (jsonObject.has("adParallelRequests")) {
      this.adParallelRequests = jsonObject.optInt("adParallelRequests");
    }
    if (jsonObject.has("adParallelWaitTime")) {
      this.adParallelWaitTime = jsonObject.optInt("adParallelWaitTime");
    }
    if (jsonObject.has("adRefreshInterval")) {
      this.adRefreshInterval = jsonObject.optInt("adRefreshInterval");
    }
    this.ad.fillFromJson(jsonObject);
    this.adProviderPriority = new ArrayList<>();

    if (jsonObject.has("banner")) {
      JSONArray arr = jsonObject.optJSONArray("banner");
      for (int i = 0; i < arr.length(); i++) {
        Object o = arr.opt(i);
        if (o instanceof String) {
          try {
            this.adProviderPriority.add(new JSONObject(String.valueOf(o)));
          } catch (JSONException ex) {
            ex.printStackTrace();
          }
        } else {
          this.adProviderPriority.add((JSONObject) o);
        }
      }
    }
    return this;
  }

  public static class Ad extends BaseConfig.Ad {
    public boolean UCB_personalizedWaterfallActive = false;
    public long adDelayFirstInterstitialCallSec = 30;
    public int adInitialLoadInterval = -1;
    public int adNextLoadInterval = -1;
    public List<String> adSkipInitially = new ArrayList();
    public int bannerLoadTimeoutSeconds = 10;
    public int sleepBeforeNextCycle = -1;
    public U u = new U();

    public void fillFromJson(JSONObject jsonObject) {
      if (jsonObject.has("adDelayFirstInterstitialCallSec")) {
        this.adDelayFirstInterstitialCallSec = jsonObject.optInt("adDelayFirstInterstitialCallSec");
      }
      if (jsonObject.has("adInitialLoadInterval")) {
        this.adInitialLoadInterval = jsonObject.optInt("adInitialLoadInterval");
      }
      if (jsonObject.has("adNextLoadInterval")) {
        this.adNextLoadInterval = jsonObject.optInt("adNextLoadInterval");
      }
      if (jsonObject.has("bannerLoadTimeoutSeconds")) {
        this.bannerLoadTimeoutSeconds = jsonObject.optInt("bannerLoadTimeoutSeconds");
      }
      if (jsonObject.has("sleepBeforeNextCycle")) {
        this.sleepBeforeNextCycle = jsonObject.optInt("sleepBeforeNextCycle");
      }

    }

    public static class U {
    }
  }
}
