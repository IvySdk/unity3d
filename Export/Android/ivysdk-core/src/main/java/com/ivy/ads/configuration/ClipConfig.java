package com.ivy.ads.configuration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClipConfig extends BaseConfig {
  public Ad ad = new Ad();
  public List<JSONObject> adRewardsProviders = new ArrayList();

  @Override
  public BaseConfig fillFromJson(JSONObject jsonObject) {
    super.fillFromJson(jsonObject);
    this.adRewardsProviders = new ArrayList<>();

    if (jsonObject.has("video")) {
      JSONArray arr = jsonObject.optJSONArray("video");
      if (arr != null) {
        for (int i = 0; i < arr.length(); i++) {
          JSONObject o = arr.optJSONObject(i);
          this.adRewardsProviders.add(o);
        }
      }
    }
    this.ad.fillFromJson(jsonObject);
    return this;
  }

  public class Ad extends BaseConfig.Ad {
    public long rewardedClipPreloadDelaySeconds = 3;
    public boolean rewardedClipRestartWaterfallAfterVolatileClip = true;
    public boolean rewardedClipUseVolatileClips = true;
    public long rewardedClipVolatileWaitSeconds = 5;
    public Map<String, Cap> rewardedClipsCaps = new HashMap();
    public boolean s2sRewardedClipsInLandscape = true;
    public boolean useVideoClipPreloading = false;

    public void fillFromJson(JSONObject jsonObject) {
      if (jsonObject.has("rewardedClipPreloadDelaySeconds")) {
        this.rewardedClipPreloadDelaySeconds = jsonObject.optInt("rewardedClipPreloadDelaySeconds");
      }
      if (jsonObject.has("rewardedClipRestartWaterfallAfterVolatileClip")) {
        this.rewardedClipRestartWaterfallAfterVolatileClip = jsonObject.optBoolean("rewardedClipRestartWaterfallAfterVolatileClip");
      }
      if (jsonObject.has("rewardedClipUseVolatileClips")) {
        this.rewardedClipUseVolatileClips = jsonObject.optBoolean("rewardedClipUseVolatileClips");
      }
      if (jsonObject.has("rewardedClipVolatileWaitSeconds")) {
        this.rewardedClipVolatileWaitSeconds = jsonObject.optInt("rewardedClipVolatileWaitSeconds");
      }
      if (jsonObject.has("useVideoClipPreloading")) {
        this.useVideoClipPreloading = jsonObject.optBoolean("useVideoClipPreloading");
      }
      if (jsonObject.has("rewardedClipLoadTimeoutSeconds")) {
        this.rewardedClipLoadTimeoutSeconds = jsonObject.optInt("rewardedClipLoadTimeoutSeconds", 8);
      }
    }

    public class Cap {
      public float intervalHours;
      public int maxImpressions;

      public String toString() {
        return "" + this.maxImpressions + ":" + this.intervalHours + "h(" + intervalAsMillis() + "ms)";
      }

      long intervalAsMillis() {
        return (long) (((this.intervalHours * 60.0f) * 60.0f) * 1000.0f);
      }
    }
  }
}
