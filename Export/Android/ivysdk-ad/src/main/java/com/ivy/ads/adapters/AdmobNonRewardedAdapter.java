package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ivy.IvySdk;
import com.ivy.Logger;
import com.ivy.ads.interfaces.IvyAdType;

import org.json.JSONObject;

import java.util.Locale;

public class AdmobNonRewardedAdapter extends FullpageAdapter<FullpageAdapter.GridParams> {
  private static final String TAG = "Adapter-Admob-Interstitial";
  private InterstitialAd mInterstitial;
  private String mediation = null;

  public AdmobNonRewardedAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  private FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
    @Override
    public void onAdFailedToShowFullScreenContent(AdError adError) {
      onAdShowFail();
      mInterstitial = null;
    }

    @Override
    public void onAdClicked() {
    }


    @Override
    public void onAdShowedFullScreenContent() {
      onAdShowSuccess();
      mInterstitial = null;
    }

    @Override
    public void onAdDismissedFullScreenContent() {
      onAdClosed(false);
    }
  };

  private final OnPaidEventListener onPaidEventListener = adValue -> {
    try {
      Logger.debug(String.format(Locale.US,
        "Paid event of value %d in currency %s of precision %s%n.",
        adValue.getValueMicros(),
        adValue.getCurrencyCode(),
        adValue.getPrecisionType())
      );

      String currencyCode = adValue.getCurrencyCode();
      int precisionType = adValue.getPrecisionType();
      long valueMicros = adValue.getValueMicros();

      onGmsPaidEvent("admob", "interstitial", "interstitial", getPlacementId(), currencyCode, precisionType, valueMicros);
    } catch(Throwable t) {
    }
  };

  public void fetch(Activity activity) {
    String placement = getPlacementId();
    if(placement == null || "".equals(placement)) {
      super.onAdLoadFailed("INVALID");
      return;
    }
    AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

    InterstitialAd.load(activity, placement, adRequestBuilder.build(), new InterstitialAdLoadCallback() {
      public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
        try{
          mediation = interstitialAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();
        } catch (Exception e){
          AdmobNonRewardedAdapter.this.mediation = null;
        }
        AdmobNonRewardedAdapter.this.mInterstitial = interstitialAd;
        AdmobNonRewardedAdapter.this.mInterstitial.setFullScreenContentCallback(fullScreenContentCallback);
        AdmobNonRewardedAdapter.this.mInterstitial.setOnPaidEventListener(onPaidEventListener);
        onAdLoadSuccess();
      }

      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
        int errorCode = loadAdError.getCode();
        AdmobNonRewardedAdapter.this.mInterstitial = null;
        onAdLoadFailed(String.valueOf(errorCode));
      }
    });
  }

  public void show(Activity activity) {
    if (this.mInterstitial != null) {
      this.mInterstitial.show(activity);
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
