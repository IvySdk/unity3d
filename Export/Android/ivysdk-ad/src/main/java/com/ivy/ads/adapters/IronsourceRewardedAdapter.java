package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//
//import com.ironsource.mediationsdk.IronSource;
//import com.ironsource.mediationsdk.logger.IronSourceError;
//import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.util.Logger;
//
//import org.json.JSONObject;
//
//public class IronsourceRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> implements ISDemandOnlyRewardedVideoListener {
//  private static final String TAG = "IronsourceReward";
//  private static boolean mIsInitialised = false;
//
//  private boolean gotReward = false;
//
//  @Override
//  public void onRewardedVideoAdLoadSuccess(String instanceId) {
//    Logger.debug(TAG, "onRewardedVideoAdLoadSuccess, instanceId: " + instanceId);
//
//    onAdLoadSuccess();
//
//  }
//
//  @Override
//  public void onRewardedVideoAdLoadFailed(String instanceId, IronSourceError ironSourceError) {
//    Logger.debug(TAG, "onRewardedVideoAdLoadFailed, instanceId: " +instanceId + " code: " + ironSourceError.getErrorCode() + ironSourceError.getErrorMessage());
//    onAdLoadFailed(String.valueOf(ironSourceError.getErrorCode()));
//
//  }
//
//  @Override
//  public void onRewardedVideoAdOpened(String instanceId) {
//    Logger.debug(TAG, "onRewardedVideoAdOpened, instanceId: " + instanceId);
//
//    onAdShowSuccess();
//
//  }
//
//  @Override
//  public void onRewardedVideoAdClosed(String instanceId) {
//    Logger.debug(TAG, "onRewardedVideoAdClosed, instanceId: " + instanceId);
//
//    onAdClosed(gotReward);
//
//  }
//
//  @Override
//  public void onRewardedVideoAdShowFailed(String instanceId, IronSourceError ironSourceError) {
//    Logger.debug(TAG, "onRewardedVideoAdShowFailed, instanceId: " + instanceId);
//    onAdShowFail();
//  }
//
//  @Override
//  public void onRewardedVideoAdClicked(String instanceId) {
//    Logger.debug(TAG, "onRewardedVideoAdClicked: instanceId: " + instanceId);
//    onAdClicked();
//  }
//
//  @Override
//  public void onRewardedVideoAdRewarded(String instanceId) {
//    Logger.debug(TAG, "onRewardedVideoAdRewarded, instanceId: " + instanceId);
//    gotReward = true;
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
//  public IronsourceRewardedAdapter(Context context, String gridName, IvyAdType adType) {
//    super(context, gridName, adType);
//  }
//
//  public void setup(Activity activity) {
//    super.setup(activity);
//    try {
//      Logger.debug(TAG, "setup()");
//      if (!mIsInitialised) {
//        IronsourceManager.init(this, activity, getAppKey(), IronSource.AD_UNIT.REWARDED_VIDEO);
//        mIsInitialised = true;
//      }
//      IronsourceManager.setRewardedVideoMap(getPlacementId(), this);
//    } catch(Throwable t) {
//      t.printStackTrace();
//    }
//  }
//
//
//  public void fetch(Activity activity) {
//    gotReward = false;
//    if (IronSource.isISDemandOnlyRewardedVideoAvailable(getPlacementId())) {
//      super.onAdLoadSuccess();
//    } else {
//      IronSource.loadISDemandOnlyRewardedVideo(activity, getPlacementId());
//    }
//  }
//
//  public void show(Activity activity) {
//    gotReward = false;
//    Logger.debug(TAG, "[%s]show()", getAdType());
//    boolean available = IronSource.isISDemandOnlyRewardedVideoAvailable(getPlacementId());
//    if (available) {
//      IronSource.showISDemandOnlyRewardedVideo(getPlacementId());
//    } else {
//      onAdShowFail();
//    }
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
