package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.util.Logger;
import com.unity3d.ads.IUnityAdsLoadListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import org.json.JSONObject;

public class UnityRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> implements IUnityAdsLoadListener, IUnityAdsShowListener {
  private static final String TAG = "UnityClips";

  private final UnityManager mUnityManager = UnityManager.getInstance();

  public void setup(Activity activity) {
    super.setup(activity);
    this.mUnityManager.init(this, getAppId(), activity);
  }

  @Override
  public void onUnityAdsShowFailure(String placementId, UnityAds.UnityAdsShowError error, String message) {
    Logger.debug(TAG, "onUnityAdsShowFailure, placementId: " + placementId);
    if (getPlacementId() != null && getPlacementId().equals(placementId)) {
      onAdShowFail();
    }
  }

  @Override
  public void onUnityAdsShowStart(String placementId) {
    Logger.debug(TAG, "UnityAds start shown, placementId: " + placementId);
    if (getPlacementId() != null && getPlacementId().equals(placementId)) {
      onAdShowSuccess();
    }
  }

  @Override
  public void onUnityAdsShowClick(String placementId) {
    Logger.debug(TAG, "onUnityAdsShowClick");
    if (getPlacementId() != null && getPlacementId().equals(placementId)) {
      onAdClicked();
    }
  }

  @Override
  public void onUnityAdsShowComplete(String placementId, UnityAds.UnityAdsShowCompletionState state) {
    Logger.debug(TAG, "UnityAds: " + placementId + " onUnityAdsFinish :" + (state == UnityAds.UnityAdsShowCompletionState.COMPLETED ? "VIDEO COMPLETED" : "NOT COMPLETED"));
    if (getPlacementId() != null && getPlacementId().equals(placementId)) {
      onAdClosed(state == UnityAds.UnityAdsShowCompletionState.COMPLETED);
    }
  }

  @Override
  public void onUnityAdsAdLoaded(String placementId) {
    Logger.debug(TAG, "onUnityAdsAdLoaded ");
    super.onAdLoadSuccess();
  }

  @Override
  public void onUnityAdsFailedToLoad(String placementId, UnityAds.UnityAdsLoadError error, String message) {
    Logger.debug(TAG, "onUnityAdsFailedToLoad " + message);
    super.onAdLoadFailed("1");
  }

  public static class GridParams extends BaseAdapter.GridParams {
    public String gameId;
    public String placement;

    public GridParams() {
    }

    @Override
    public BaseAdapter.GridParams fromJSON(JSONObject jsonObject) {
      this.gameId = jsonObject.optString("gameId");
      this.placement = jsonObject.optString("placement");
      return this;
    }

    protected String getParams() {
      return "placement=" + this.placement + ", gameId=" + this.gameId;
    }
  }

  public UnityRewardedAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  public void fetch(Activity activity) {
    if (!UnityAds.isInitialized() || !UnityAds.isSupported()) {
      onAdLoadFailed("not_supported");
      return;
    }
    UnityAds.load(getPlacementId(), this);
  }

  public void show(Activity activity) {
    Logger.debug(TAG, "[%s]show()", getAdType());
    UnityAds.show(activity, getPlacementId(), this);
  }

  @Override
  public String getPlacementId() {
    return ((GridParams) getGridParams()).placement;
  }

  protected GridParams newGridParams() {
    return new GridParams();
  }

  public String getAppId() {
    return ((GridParams) getGridParams()).gameId;
  }
}
