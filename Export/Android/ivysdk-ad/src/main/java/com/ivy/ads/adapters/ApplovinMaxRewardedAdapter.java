package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.util.Log;
//
//import com.applovin.mediation.MaxAd;
//import com.applovin.mediation.MaxAdFormat;
//import com.applovin.mediation.MaxAdRevenueListener;
//import com.applovin.mediation.MaxError;
//import com.applovin.mediation.MaxReward;
//import com.applovin.mediation.MaxRewardedAdListener;
//import com.applovin.mediation.ads.MaxRewardedAd;
//import com.applovin.mediation.openwrap.ALPubMaticOpenWrapConstants;
//import com.applovin.sdk.AppLovinSdk;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.util.Logger;
//
//import org.json.JSONObject;
//
//public class ApplovinMaxRewardedAdapter extends FullpageAdapter<FullpageAdapter.GridParams> implements MaxRewardedAdListener, MaxAdRevenueListener {
//  private static final String TAG = "Adapter-Max-Rewarded";
//  private boolean gotReward;
//  private MaxRewardedAd mAppLovinClip;
//
//  public ApplovinMaxRewardedAdapter(Context context, String gridName, IvyAdType adType) {
//    super(context, gridName, adType);
//  }
//
//  @Override
//  public void fetch(Activity activity) {
//    Logger.debug(TAG, "fetch()");
//    AppLovinSdk sdk = ApplovinManager.getInstance(activity);
//    if (sdk == null) {
//      Logger.error(TAG, "Applovin SDK initialize failed");
//      super.onAdLoadFailed("not_init");
//      return;
//    }
//
//    this.gotReward = false;
//    try {
//      this.mAppLovinClip = MaxRewardedAd.getInstance(getPlacementId(), activity);
//      mAppLovinClip.setLocalExtraParameter(ALPubMaticOpenWrapConstants.NETWORK_TIMEOUT_KEY, 5);
//      this.mAppLovinClip.setListener(this);
//      this.mAppLovinClip.setRevenueListener(this);
//      this.mAppLovinClip.loadAd();
//    } catch(Throwable t) {
//      t.printStackTrace();
//    }
//  }
//
//  @Override
//  public void show(Activity activity) {
//    Logger.debug(TAG, "show()");
//    if (this.activity == null || this.mAppLovinClip == null) {
//      Logger.error(TAG, "showAd: mAppLovinClip object is null. Meaning we do not have an ad");
//      super.onAdShowFail();
//      return;
//    }
//    this.gotReward = false;
//    if (this.mAppLovinClip.isReady()) {
//      this.mAppLovinClip.showAd();
//    } else {
//      Logger.error(TAG, "Applovin clip is not ready to display");
//      super.onAdShowFail();
//    }
//  }
//
//  @Override
//  public String getPlacementId() {
//    return ((GridParams) getGridParams()).zoneId;
//  }
//
//  protected GridParams newGridParams() {
//    return new GridParams();
//  }
//
//  @Override
//  public void onRewardedVideoStarted(MaxAd ad) {
//
//  }
//
//  @Override
//  public void onRewardedVideoCompleted(MaxAd ad) {
//    Logger.debug(TAG, "onRewardedVideoCompleted()");
//    this.gotReward = true;
//  }
//
//  @Override
//  public void onUserRewarded(MaxAd ad, MaxReward reward) {
//    Logger.debug(TAG, "onRewardedVideoCompleted()");
//    this.gotReward = true;
//  }
//
//  @Override
//  public void onAdLoaded(MaxAd ad) {
//    Logger.debug(TAG, "onAdLoaded()");
//    super.onAdLoadSuccess();
//  }
//
//  @Override
//  public void onAdDisplayed(MaxAd ad) {
//    Logger.debug(TAG, "onAdDisplayed()");
//    super.onAdShowSuccess();
//  }
//
//  @Override
//  public void onAdHidden(MaxAd ad) {
//    super.onAdClosed(gotReward);
//  }
//
//  @Override
//  public void onAdClicked(MaxAd ad) {
//    Logger.debug(TAG, "onAdClicked()");
//    super.onAdClicked();
//  }
//
//  @Override
//  public void onAdLoadFailed(String adUnitId, MaxError error) {
//    Logger.debug(TAG, "onAdLoadFailed()");
//    super.onAdLoadFailed(String.valueOf(error != null ? error.getCode() : "empty_code"));
//  }
//
//  @Override
//  public void onAdDisplayFailed(MaxAd ad, MaxError error) {
//    Logger.debug(TAG, "onAdDisplayFailed()");
//    super.onAdShowFail();
//  }
//
//  @Override
//  public void onAdRevenuePaid(MaxAd ad) {
//    try {
//      if (ad == null) {
//        return;
//      }
//      double revenue = ad.getRevenue(); // In USD
//      if (revenue <= 0) {
//        return;
//      }
//
//      String networkName = ad.getNetworkName(); // Display name of the network that showed the ad (e.g. "AdColony")
//      String adUnitId = ad.getAdUnitId(); // The MAX Ad Unit ID
//      MaxAdFormat adFormat = ad.getFormat(); // The ad format of the ad (e.g. BANNER, MREC, INTERSTITIAL, REWARDED)
//      String placement = ad.getPlacement(); // The placement this ad's postbacks are tied to
//      String networkPlacement = ad.getNetworkPlacement(); // The placement ID from the network that showed the ad
//      super.onGmsPaidEvent(networkName, "rewarded", placement, adUnitId, "USD", 0, (long) (revenue * 1000000.0f));
//    } catch(Throwable t) {
//      t.printStackTrace();
//    }
//  }
//
//  public static class GridParams extends com.ivy.ads.adapters.BaseAdapter.GridParams {
//    public String zoneId = "";
//
//    public GridParams fromJSON(JSONObject jsonObject) {
//      this.zoneId = jsonObject.optString("placement");
//      return this;
//    }
//
//    protected String getParams() {
//      return "placement="  + (this.zoneId != null ? ", zoneId=" + this.zoneId : "");
//    }
//  }
//}
