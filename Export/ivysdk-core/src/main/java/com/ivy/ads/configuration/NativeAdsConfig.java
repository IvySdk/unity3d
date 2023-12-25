package com.ivy.ads.configuration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NativeAdsConfig extends BaseConfig {
  public Ad ad = new Ad();
  public List<JSONObject> gameWallNativeAdProviderList = new ArrayList();

  @Override
  public BaseConfig fillFromJson(JSONObject jsonObject) {
    super.fillFromJson(jsonObject);
    this.gameWallNativeAdProviderList = new ArrayList<>();

    if (jsonObject.has("native")) {

      JSONArray arr = jsonObject.optJSONArray("native");
      for (int i = 0; i < arr.length(); i++) {
        Object o = arr.opt(i);
        if (o instanceof String) {
          try {
            this.gameWallNativeAdProviderList.add(new JSONObject(String.valueOf(o)));
          } catch (JSONException ex) {
            ex.printStackTrace();
          }
        } else {
          this.gameWallNativeAdProviderList.add((JSONObject) o);
        }
      }
    }
    return this;
  }

  public static class Ad {
    public int gameWallLoadTimeoutSeconds = 15;
  }
}
