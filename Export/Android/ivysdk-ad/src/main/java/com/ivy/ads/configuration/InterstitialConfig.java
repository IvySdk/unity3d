package com.ivy.ads.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InterstitialConfig extends BaseConfig {
  public Ad ad = new Ad();
  public long adFullScreenTimespan = 300;
  public boolean dontShowFullPageAdsOnSlowConnection = true;
  public List<JSONObject> fullPageAdProviders = new ArrayList();

  @Override
  public BaseConfig fillFromJson(JSONObject jsonObject) {
    super.fillFromJson(jsonObject);

    if (jsonObject.has("adFullScreenTimespan")) {
      this.adFullScreenTimespan = jsonObject.optInt("adFullScreenTimespan");
    }
    if (jsonObject.has("dontShowFullPageAdsOnSlowConnection")) {
      this.dontShowFullPageAdsOnSlowConnection = jsonObject.optBoolean("dontShowFullPageAdsOnSlowConnection");
    }
    if (jsonObject.has("ad")) {
      this.ad.fillFromJson(jsonObject.optJSONObject("ad"));
    }
    this.fullPageAdProviders = new ArrayList<>();
    if (jsonObject.has("full")) {
      JSONArray arr = jsonObject.optJSONArray("full");
      for (int i = 0; i < arr.length(); i++) {
        Object o = arr.opt(i);
        if (o instanceof String) {
          try {
            this.fullPageAdProviders.add(new JSONObject(String.valueOf(o)));
          } catch (JSONException ex) {
            ex.printStackTrace();
          }
        } else {
          this.fullPageAdProviders.add(arr.optJSONObject(i));
        }
      }
    }
    return this;
  }

  public static class Ac {
    // @JsonProperty("fIPSS")
    public int interstitialInitialSilenceSessions;
    // @JsonProperty("fIPT")
    public int interstitialInitialSilenceTime;
    // @JsonProperty("iPTs")
    public int[] interstitialPreloadTimeouts;
    // @JsonProperty("iTs")
    public int[] interstitialTimeouts;
    // @JsonProperty("iSTs")
    public ISTs[] transitions;

    public void fillFromJson(JSONObject jsonObject) {
      if (jsonObject.has("fIPSS")) {
        this.interstitialInitialSilenceSessions = jsonObject.optInt("fIPSS");
      }
      if (jsonObject.has("fIPT")) {
        this.interstitialInitialSilenceTime = jsonObject.optInt("fIPT");
      }

      if (jsonObject.has("iPTs")) {
        JSONArray iPTs = jsonObject.optJSONArray("iPTs");
        this.interstitialPreloadTimeouts = new int[iPTs.length()];
        for (int i = 0; i < iPTs.length(); i++) {
          this.interstitialPreloadTimeouts[i] = iPTs.optInt(i);
        }
      }

      if (jsonObject.has("iTs")) {
        JSONArray iTs = jsonObject.optJSONArray("iTs");
        this.interstitialTimeouts = new int[iTs.length()];
        for (int i = 0; i < iTs.length(); i++) {
          this.interstitialTimeouts[i] = iTs.optInt(i);
        }
      }

      if (jsonObject.has("iSTs")) {
        JSONArray iSTs = jsonObject.optJSONArray("iSTs");
        this.transitions = new ISTs[iSTs.length()];
        for (int i = 0; i < iSTs.length(); i++) {
          this.transitions[i] = new ISTs(iSTs.optJSONObject(i));
        }
      }

    }
  }

  public static class Ad extends BaseConfig.Ad {
    public Ac aC = new Ac();
    // @JsonProperty("iLTS")
    public int interstitialLoadTimeoutSeconds = 10;
    public long maxInterstitialCachingTimeSeconds = 120;

    public void fillFromJson(JSONObject jsonObject) {
      if (jsonObject.has("interstitialLoadTimeoutSeconds")) {
        this.interstitialLoadTimeoutSeconds = jsonObject.optInt("interstitialLoadTimeoutSeconds");
      }
      if (jsonObject.has("maxInterstitialCachingTimeSeconds")) {
        this.maxInterstitialCachingTimeSeconds = jsonObject.optInt("maxInterstitialCachingTimeSeconds");
      }
      if (jsonObject.has("aC")) {
        this.aC.fillFromJson(jsonObject.optJSONObject("aC"));
      }
    }
  }

  public static class ISTs {
    // @JsonProperty("f")
    public String from;
    // @JsonProperty("t")
    public String to;
    public ISTs(JSONObject o) {
      this.from = o.optString("f", "*");
      this.to = o.optString("t", "*");
    }
  }
}
