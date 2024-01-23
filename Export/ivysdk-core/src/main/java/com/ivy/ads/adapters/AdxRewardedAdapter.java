package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.util.Logger;

import org.json.JSONObject;

public class AdxRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> {
  private static final String TAG = "Adapter-Admob-Rewarded";
  private boolean gotReward;
  private RewardedAd mRewardedAd = null;

  private FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
    public void onAdFailedToShowFullScreenContent(AdError adError) {
      Logger.debug(TAG, "onAdFailedToShowFullScreenContent()");
      onAdShowFail();
    }

    public void onAdShowedFullScreenContent() {
      Logger.debug(TAG, "onAdShowSuccess()");
      onAdShowSuccess();
    }

    public void onAdDismissedFullScreenContent() {
      Logger.debug(TAG, "onRewardedAdClosed()");
      onAdClosed(gotReward);
    }

    public void onAdImpression() {
    }
  };

  private OnUserEarnedRewardListener onUserEarnedRewardListener = new OnUserEarnedRewardListener() {
    @Override
    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
      gotReward = true;
    }
  };


  public AdxRewardedAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  public void fetch(Activity activity) {
    Logger.debug(TAG, "fetch()");
    String placement = getPlacementId();
    if(placement == null || "".equals(placement)) {
      super.onAdLoadFailed("INVALID");
      return;
    }
    AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

    this.mRewardedAd = null;
    RewardedAd.load(activity, placement, adRequest, new RewardedAdLoadCallback() {
      public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
        AdxRewardedAdapter.this.mRewardedAd = rewardedAd;
        AdxRewardedAdapter.this.mRewardedAd.setFullScreenContentCallback(fullScreenContentCallback);
        AdxRewardedAdapter.this.onAdLoadSuccess();
      }

      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
        AdxRewardedAdapter.this.mRewardedAd = null;
        AdxRewardedAdapter.this.onAdLoadFailed(String.valueOf(loadAdError.getCode()));
      }
    });
  }

  public void show(Activity activity) {
    Logger.debug(TAG, "show()");
    this.gotReward = false;
    if (this.mRewardedAd != null) {
      this.mRewardedAd.show(activity, onUserEarnedRewardListener);
    }
  }

  @Override
  public String getPlacementId() {
    return ((GridParams) getGridParams()).placement;
  }

  protected GridParams newGridParams() {
    return new GridParams();
  }

  public static class GridParams extends BaseAdapter.GridParams {
    public String placement;

    public GridParams fromJSON(JSONObject jsonObject) {
      this.placement = jsonObject.optString("placement");
      return this;
    }

    protected String getParams() {
      return "placement=" + this.placement;
    }
  }
}
