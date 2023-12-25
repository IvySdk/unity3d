package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.util.Logger;

import org.json.JSONObject;

public class AdxNonRewardedAdapter extends FullpageAdapter<FullpageAdapter.GridParams> {
  private static final String TAG = "AdxNonRewarded";
  private volatile AdManagerInterstitialAd mInterstitial;

  private FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
    @Override
    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
      Logger.debug(TAG, "onAdFailedToShowFullScreenContent");
      AdxNonRewardedAdapter.this.onAdShowFail();
    }

    @Override
    public void onAdShowedFullScreenContent() {
      Logger.debug(TAG, "onAdShowedFullScreenContent");
      AdxNonRewardedAdapter.this.onAdShowSuccess();
    }

    @Override
    public void onAdDismissedFullScreenContent() {
      Logger.debug(TAG, "onAdDismissedFullScreenContent");
      AdxNonRewardedAdapter.this.onAdClosed(false);
    }

    @Override
    public void onAdImpression() {
      Logger.debug(TAG, "onAdImpression");
    }
  };

  public AdxNonRewardedAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  public void setup(Activity activity) {
    super.setup(activity);
  }

  public void fetch(Activity activity) {

    String placement = getPlacementId();
    if(placement == null || "".equals(placement)) {
      super.onAdLoadFailed("INVALID");
      return;
    }
    AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

    AdManagerInterstitialAd.load(activity, placement, adRequest, new AdManagerInterstitialAdLoadCallback() {
      @Override
      public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
        // The mAdManagerInterstitialAd reference will be null until
        // an ad is loaded.
        mInterstitial = interstitialAd;
        mInterstitial.setFullScreenContentCallback(fullScreenContentCallback);
        AdxNonRewardedAdapter.this.onAdLoadSuccess();
        Logger.debug(TAG, "onAdLoaded");
      }

      @Override
      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
        // Handle the error
        mInterstitial = null;
        Logger.debug(TAG, "onAdFailedToLoad >>> " + loadAdError.getMessage());
        AdxNonRewardedAdapter.this.onAdLoadFailed(String.valueOf(loadAdError.getCode()));
      }
    });
  }

  public void show(Activity activity) {
    if (this.mInterstitial != null) {
      this.mInterstitial.show(activity);
      this.mInterstitial = null;
    } else {
      super.onAdShowFail();
      this.mInterstitial = null;
    }
  }

  @Override
  public String getPlacementId() {
    return ((GridParams) getGridParams()).placement;
  }

  protected GridParams newGridParams() {
    return new GridParams();
  }

  public static class GridParams extends com.ivy.ads.adapters.BaseAdapter.GridParams {
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
