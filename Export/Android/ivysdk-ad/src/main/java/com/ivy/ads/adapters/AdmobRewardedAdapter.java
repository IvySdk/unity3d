package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.ivy.IvySdk;
import com.ivy.Logger;
import com.ivy.ads.interfaces.IvyAdType;


import org.json.JSONObject;

import java.util.Locale;

public class AdmobRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> {
  private static final String TAG = "Adapter-Admob-Rewarded";
  private boolean gotReward;
  private RewardedAd mRewardedAd = null;
  private String mediation = null;

  private FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
    public void onAdFailedToShowFullScreenContent(AdError adError) {
      onAdShowFail();
    }

    public void onAdShowedFullScreenContent() {
      onAdShowSuccess();
    }

    public void onAdDismissedFullScreenContent() {
      onAdClosed(gotReward);
    }

    public void onAdImpression() {
    }

    public void onAdClicked() {
      AdmobRewardedAdapter.this.onAdClicked();
    }
  };

  private final OnUserEarnedRewardListener onUserEarnedRewardListener = new OnUserEarnedRewardListener() {
    @Override
    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
      gotReward = true;
    }
  };

  private OnPaidEventListener onPaidEventListener = adValue -> {
    try {
      Logger.debug( String.format(Locale.US,
        "Paid event of value %d in currency %s of precision %s%n.",
        adValue.getValueMicros(),
        adValue.getCurrencyCode(),
        adValue.getPrecisionType())
      );

      String currencyCode = adValue.getCurrencyCode();
      int precisionType = adValue.getPrecisionType();
      long valueMicros = adValue.getValueMicros();

      onGmsPaidEvent("admob", "rewarded", "rewarded", getPlacementId(), currencyCode, precisionType, valueMicros);
    } catch(Throwable t) {
    }
  };

  public AdmobRewardedAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  public void fetch(Activity activity) {
    try {
      String placement = getPlacementId();
      if (placement == null || "".equals(placement)) {
        super.onAdLoadFailed("INVALID");
        return;
      }
      AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
      AdRequest adRequest = adRequestBuilder.build();

      RewardedAd.load(activity, placement, adRequest, new RewardedAdLoadCallback() {
        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
          try{
            mediation = rewardedAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();
          } catch (Exception e){
            AdmobRewardedAdapter.this.mediation = null;
          }
          AdmobRewardedAdapter.this.mRewardedAd = rewardedAd;
          AdmobRewardedAdapter.this.mRewardedAd.setFullScreenContentCallback(fullScreenContentCallback);
          AdmobRewardedAdapter.this.mRewardedAd.setOnPaidEventListener(onPaidEventListener);
          AdmobRewardedAdapter.this.onAdLoadSuccess();
        }

        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
          int errorCode = loadAdError.getCode();
          AdmobRewardedAdapter.this.mRewardedAd = null;
          AdmobRewardedAdapter.this.onAdLoadFailed(String.valueOf(errorCode));
        }
      });
    } catch(Throwable t) {
      t.printStackTrace();
    }
  }

  public void show(Activity activity) {
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

  @Override
  public String getMediation() {
    return mediation;
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
