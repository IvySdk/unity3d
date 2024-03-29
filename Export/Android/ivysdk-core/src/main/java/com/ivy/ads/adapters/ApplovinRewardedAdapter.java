package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.Locale;
import java.util.Map;

public class ApplovinRewardedAdapter extends FullpageAdapter<FullpageAdapter.GridParams> implements AppLovinAdLoadListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener, AppLovinAdDisplayListener {
  private static final String TAG = "Adapter-Applovin-Rewarded";
  private boolean gotReward;
  private boolean isUserRewardVerified;
  private AppLovinIncentivizedInterstitial mAppLovinClip;

  public ApplovinRewardedAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  public void fetch(Activity activity) {
    Logger.debug(TAG, "fetch()");
    try {
      if (getPlacementId() == null || getPlacementId().trim().isEmpty()) {
        this.mAppLovinClip = AppLovinIncentivizedInterstitial.create(ApplovinManager.getInstance(activity));
      } else {
        this.mAppLovinClip = AppLovinIncentivizedInterstitial.create(getPlacementId(), ApplovinManager.getInstance(activity));
      }
      this.mAppLovinClip.preload(this);
    } catch(Throwable t) {
      t.printStackTrace();
    }
  }

  public void show(Activity activity) {
    Logger.debug(TAG, "show()");
    if (this.mAppLovinClip == null) {
      Logger.error(TAG, "showAd: mAppLovinClip object is null. Meaning we do not have an ad");
      super.onAdShowFail();
      return;
    }
    this.isUserRewardVerified = false;
    this.gotReward = false;
    if (this.mAppLovinClip.isAdReadyToDisplay()) {
      this.mAppLovinClip.show(activity, this, this, this);
    } else {
      Logger.error(TAG, "Applovin clip is not ready to display");
      super.onAdShowFail();
    }
  }

  public void adDisplayed(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "adDisplayed()");
    super.onAdShowSuccess();
  }

  public void adHidden(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "adHidden()");
    if (this.gotReward && this.isUserRewardVerified) {
      super.onAdClosed(true);
    } else {
      super.onAdClosed(false);
    }
    this.gotReward = false;
    this.isUserRewardVerified = false;
  }

  public void adReceived(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "adReceived()");
    super.onAdLoadSuccess();
  }

  public void failedToReceiveAd(int errorCode) {
    String reason = "";

    if (errorCode == AppLovinErrorCodes.NO_FILL) {
      reason = IvyLoadStatus.NO_FILL;
    } else {
      reason = ApplovinManager.errorCodeToMessage(errorCode);
    }

    Logger.error(TAG, "[Applovin] loading reward ad error: failedToReceiveAd()" + reason);
    super.onAdLoadFailed(reason);
  }

  public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
    Logger.debug(TAG, "userRewardVerified()");
    this.gotReward = true;
  }

  public void userOverQuota(AppLovinAd appLovinAd, Map map) {
    Logger.debug(TAG, "userOverQuota()");
  }

  public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
    Logger.debug(TAG, "userRewardRejected()");
  }

  public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
    Logger.debug(TAG, "validationRequestFailed()");
  }

  public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "userDeclinedToViewAd()");
  }

  public void videoPlaybackBegan(AppLovinAd appLovinAd) {
    Logger.debug(TAG, "videoPlaybackBegan()");
  }

  public void videoPlaybackEnded(AppLovinAd appLovinAd, double v, boolean b) {
    Logger.debug(TAG, "videoPlaybackEnded()");
    this.isUserRewardVerified = true;
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

      try {
        if (jsonObject.has("country")) {
          String country = Locale.getDefault().getCountry().toLowerCase(Locale.ENGLISH);
          JSONObject countrySettings = jsonObject.optJSONObject("country");
          if (countrySettings.has(country)) {
            JSONObject c = countrySettings.optJSONObject(country);
            this.zoneId = c.optString("zoneId");
          }
        }
      } catch(Exception e) {
        e.printStackTrace();
      }
      return this;
    }

    protected String getParams() {
      return "placement=" + this.sdkKey + (this.zoneId != null ? ", zoneId=" + this.zoneId : "");
    }
  }
}
