package com.ivy.ads.configuration;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RewardedInterstitialConfig extends BaseConfig {
  public List<JSONObject> adRewardsProviders = new ArrayList<>();

  @Override
  public BaseConfig fillFromJson(JSONObject jsonObject) {
    super.fillFromJson(jsonObject);
    this.adRewardsProviders = new ArrayList<>();

    if (jsonObject.has("reward_interstitial")) {
      JSONArray arr = jsonObject.optJSONArray("reward_interstitial");
      if (arr != null) {
        for (int i = 0; i < arr.length(); i++) {
          JSONObject o = arr.optJSONObject(i);
          this.adRewardsProviders.add(o);
        }
      }
    }
    return this;
  }
}
