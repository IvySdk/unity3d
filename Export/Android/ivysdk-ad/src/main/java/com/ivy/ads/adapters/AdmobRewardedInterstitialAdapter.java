package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.ivy.ads.interfaces.IvyAdType;


import org.json.JSONObject;

public class AdmobRewardedInterstitialAdapter extends FullpageAdapter<FullpageAdapter.GridParams> implements OnUserEarnedRewardListener {
  private static final String TAG = "Adapter-Admob-Rewarded-Interstitial";
  private RewardedInterstitialAd mRewardInterstitialAd;
  private String mediation = null;
  private boolean gotReward = false;
  public AdmobRewardedInterstitialAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  private FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
    @Override
    public void onAdFailedToShowFullScreenContent(AdError adError) {
      onAdShowFail();
      mRewardInterstitialAd = null;
    }

    @Override
    public void onAdShowedFullScreenContent() {
      onAdShowSuccess();
      mRewardInterstitialAd = null;
    }

    @Override
    public void onAdDismissedFullScreenContent() {
      onAdClosed(gotReward);
    }
  };



  public void fetch(Activity activity) {
    AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

    gotReward = false;
    RewardedInterstitialAd.load(activity, getPlacementId(), adRequestBuilder.build(), new RewardedInterstitialAdLoadCallback() {
      @Override
      public void onAdLoaded(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
        try{
          mediation = rewardedInterstitialAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();
        } catch (Exception e){
          AdmobRewardedInterstitialAdapter.this.mediation = null;
        }
        AdmobRewardedInterstitialAdapter.this.mRewardInterstitialAd = rewardedInterstitialAd;
        mRewardInterstitialAd.setFullScreenContentCallback(fullScreenContentCallback);
        onAdLoadSuccess();
      }

      @Override
      public void onAdFailedToLoad(LoadAdError loadAdError) {
        onAdLoadFailed(String.valueOf(loadAdError.getCode()));
        mRewardInterstitialAd = null;
      }
    });

  }

  public void show(Activity activity) {
    if (mRewardInterstitialAd != null) {
      mRewardInterstitialAd.show(activity, this);
    } else {
      super.onAdShowFail();
    }
  }


  @Override
  public String getPlacementId() {
    return ((GridParams) getGridParams()).placement;
  }

  protected GridParams newGridParams() {
    return new GridParams();
  }

  @Override
  public String getMediation() {
    return mediation;
  }

  @Override
  public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
    gotReward = true;
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
