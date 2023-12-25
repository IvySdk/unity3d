package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.Locale;

public class ApplovinNonRewardedAdapter extends FullpageAdapter<FullpageAdapter.GridParams> implements AppLovinAdDisplayListener, AppLovinAdLoadListener, AppLovinAdClickListener {
  private static final String TAG = "Adapter-Applovin-NonRewarded";
  private AppLovinAd mAppLovinAd;

  private AppLovinInterstitialAdDialog interstitialAd;

  public ApplovinNonRewardedAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  @Override
  public void setup(Activity activity) {
    super.setup(activity);
    try {
      interstitialAd = AppLovinInterstitialAd.create(AppLovinSdk.getInstance(activity), activity);
      interstitialAd.setAdLoadListener(this);
      interstitialAd.setAdClickListener(this);
      interstitialAd.setAdDisplayListener(this);
    } catch (Throwable ignored) {

    }
  }

  public void fetch(Activity activity) {
    Logger.debug(TAG, "fetch()");

    try {
      AppLovinAdService adService = ApplovinManager.getInstance(activity).getAdService();
      if (getPlacementId() == null || getPlacementId().trim().isEmpty()) {
        adService.loadNextAd(AppLovinAdSize.INTERSTITIAL, this);
      } else {
        adService.loadNextAdForZoneId(getPlacementId(), this);
      }
    } catch(Throwable t) {
      t.printStackTrace();
    }
  }

  public void show(android.app.Activity r4) {
    Logger.debug(TAG, "ApplovinNoReward show");
    if (this.mAppLovinAd != null) {
      interstitialAd.showAndRender(this.mAppLovinAd);
    }
  }

  public void adDisplayed(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "adDisplayed()");
    synchronized (this) {
      this.mAppLovinAd = null;
    }
    super.onAdShowSuccess();
  }

  public void adHidden(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "adHidden()");
    synchronized (this) {
      this.mAppLovinAd = null;
    }
    super.onAdClosed(false);
  }

  public void adReceived(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "adReceived()");
    synchronized (this) {
      this.mAppLovinAd = appLovinAd;
    }
    super.onAdLoadSuccess();
  }

  public void failedToReceiveAd(int errorCode) {
    Logger.debug(TAG, "failedToReceiveAd()");
    synchronized (this) {
      this.mAppLovinAd = null;
    }
    String reason = "";
    switch(errorCode) {
      case AppLovinErrorCodes.NO_FILL:
        reason = IvyLoadStatus.NO_FILL;
        break;
      default:
        reason = ApplovinManager.errorCodeToMessage(errorCode);
        break;
    }
    super.onAdLoadFailed(reason);
  }

  public void adClicked(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "adClicked()");
    super.onAdClicked();
  }

  @Override
  public String getPlacementId() {
    return ((GridParams) getGridParams()).zoneId;
  }

  protected GridParams newGridParams() {
    return new GridParams();
  }

  public static class GridParams extends com.ivy.ads.adapters.BaseAdapter.GridParams {
    public String sdkKey;
    public String zoneId = "";

    public GridParams fromJSON(JSONObject jsonObject) {
      this.sdkKey = jsonObject.optString("sdkkey");
      this.zoneId = jsonObject.optString("zoneId");

      if (jsonObject.has("country")) {
        String country = Locale.getDefault().getCountry().toLowerCase(Locale.ENGLISH);
        JSONObject countrySettings = jsonObject.optJSONObject("country");
        if (countrySettings.has(country)) {
          JSONObject c = countrySettings.optJSONObject(country);
          this.zoneId = c.optString("zoneId");
        }
      }
      return this;
    }

    protected String getParams() {
      return "placement=" + this.sdkKey + (this.zoneId != null ? ", zoneId=" + this.zoneId : "");
    }
  }
}
