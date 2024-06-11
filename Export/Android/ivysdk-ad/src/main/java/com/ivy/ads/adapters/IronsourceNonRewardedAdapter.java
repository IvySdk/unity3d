package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//
//import com.ironsource.mediationsdk.IronSource;
//import com.ironsource.mediationsdk.logger.IronSourceError;
//import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.util.Logger;
//
//import org.json.JSONObject;
//
//public class IronsourceNonRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> implements ISDemandOnlyInterstitialListener {
//  private static final String TAG = "IronsourceNonReward";
//  private static boolean mIsInitialised = false;
//
//  @Override
//  public void onInterstitialAdReady(String instanceId) {
//    Logger.debug(TAG, "onInterstitialAdReady(), instanceId: " + instanceId);
//    onAdLoadSuccess();
//  }
//
//  @Override
//  public void onInterstitialAdLoadFailed(String instanceId, IronSourceError ironSourceError) {
//    Logger.debug(TAG, "onInterstitialAdLoadFailed(), instanceId: " + instanceId + " code: " + ironSourceError.getErrorCode() + ", message: " + ironSourceError.getErrorMessage());
//     onAdLoadFailed(String.valueOf(ironSourceError.getErrorCode()));
//  }
//
//  @Override
//  public void onInterstitialAdOpened(String instanceId) {
//    Logger.debug(TAG, "onInterstitialAdOpened(), instanceId: " + instanceId);
//     onAdShowSuccess();
//  }
//
//  @Override
//  public void onInterstitialAdClosed(String instanceId) {
//    Logger.debug(TAG, "onInterstitialAdClosed(), instanceId: " + instanceId);
//     onAdClosed(false);
//  }
//
//  @Override
//  public void onInterstitialAdShowFailed(String instanceId, IronSourceError ironSourceError) {
//    Logger.debug(TAG, "onInterstitialAdShowFailed(), instanceId: " + instanceId);
//    onAdShowFail();
//  }
//
//  @Override
//  public void onInterstitialAdClicked(String instanceId) {
//    Logger.debug(TAG, "onInterstitialAdClicked(), instanceId: " + instanceId);
//    onAdClicked();
//  }
//
//  public static class GridParams extends BaseAdapter.GridParams {
//    public String appKey;
//    public String placement;
//
//    public GridParams() {
//    }
//
//    @Override
//    public BaseAdapter.GridParams fromJSON(JSONObject jsonObject) {
//      this.appKey = jsonObject.optString("appKey");
//      this.placement = jsonObject.optString("placement");
//      return this;
//    }
//
//    protected String getParams() {
//      return "placement=" + this.placement + ", appKey=" + this.appKey;
//    }
//  }
//
//  public IronsourceNonRewardedAdapter(Context context, String gridName, IvyAdType adType) {
//    super(context, gridName, adType);
//  }
//
//  public void setup(Activity activity) {
//    super.setup(activity);
//    try {
//      Logger.debug(TAG, "setup()");
//      if (!mIsInitialised) {
//        IronsourceManager.init(this, activity, getAppKey(), IronSource.AD_UNIT.INTERSTITIAL);
//        mIsInitialised = true;
//      }
//      IronsourceManager.setInterstitial(getPlacementId(), this);
//    } catch(Throwable t) {
//      t.printStackTrace();
//    }
//  }
//
//  public void fetch(Activity activity) {
//    if (IronSource.isISDemandOnlyInterstitialReady(getPlacementId())) {
//      super.onAdLoadSuccess();
//    } else {
//      IronSource.loadISDemandOnlyInterstitial(activity, getPlacementId());
//    }
//  }
//
//  public void show(Activity activity) {
//    Logger.debug(TAG, "show()");
//    if (IronSource.isISDemandOnlyInterstitialReady(getPlacementId())) {
//      IronSource.showISDemandOnlyInterstitial(getPlacementId());
//      return;
//    }
//    super.onAdShowFail();
//  }
//
//  public String getPlacementId() {
//    return ((GridParams) getGridParams()).placement;
//  }
//
//  protected GridParams newGridParams() {
//    return new GridParams();
//  }
//
//  public String getAppKey() {
//    return ((GridParams) getGridParams()).appKey;
//  }
//}
