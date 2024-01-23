package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerAdView;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.util.Logger;

import org.json.JSONObject;

public class AdxBannerAdapter extends BannerAdapter<BaseAdapter.GridParams> {
  private static final String TAG = "Adx-Banner";
  private AdManagerAdView mAdManagerAdView;

  public AdxBannerAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  public void fetch(Activity activity) {
    if (this.mAdManagerAdView != null) {
      this.mAdManagerAdView.destroy();
      this.mAdManagerAdView = null;
    }

    String placement = ((GridParams) getGridParams()).placement;
    if(placement == null || "".equals(placement)) {
      super.onAdLoadFailed("INVALID");
      return;
    }
    this.mAdManagerAdView = new AdManagerAdView(activity);
    this.mAdManagerAdView.setAdUnitId(placement);

    AdSize adaptiveSize = getAdSize(activity);
    // Step 4 - Set the adaptive ad size on the ad view. Note that this sets
    // both the adaptive ad size for backfill inventory as well as the supported
    // reservation sizes.
    this.mAdManagerAdView.setAdSizes(adaptiveSize, AdSize.BANNER);

    this.mAdManagerAdView.setAdListener(new AdListener() {
      @Override
      public void onAdClosed() {
        Logger.debug(TAG, "onAdClosed()");
        AdxBannerAdapter.this.onAdClosed(false);
      }

      @Override
      public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
        Logger.debug(TAG, "onAdFailedToLoad errorCode: %s", loadAdError.getCode());
        AdxBannerAdapter.this.onAdLoadFailed(IvyLoadStatus.NO_FILL);
      }

      @Override
      public void onAdOpened() {
      }

      @Override
      public void onAdLoaded() {
        AdxBannerAdapter.this.onAdLoadSuccess();
        Logger.debug(TAG, "onAdLoaded()");
      }

      @Override
      public void onAdClicked() {
        Logger.debug(TAG, "onAdClicked()");
        AdxBannerAdapter.this.onAdClicked();
      }

      @Override
      public void onAdImpression() {
        Logger.debug(TAG, "onAdImpression()");
        AdxBannerAdapter.this.onAdShowSuccess();
      }
    });

    AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
    this.mAdManagerAdView.loadAd(adRequest);
  }

  @Override
  public String getPlacementId() {
    return ((GridParams) getGridParams()).placement;
  }

  private AdSize getAdSize(Activity activity) {
    // Step 2 - Determine the screen width (less decorations) to use for the ad width.
    Display display = activity.getWindowManager().getDefaultDisplay();
    DisplayMetrics outMetrics = new DisplayMetrics();
    display.getMetrics(outMetrics);

    float widthPixels = outMetrics.widthPixels;
    float density = outMetrics.density;

    int adWidth = (int) (widthPixels / density);

    // Step 3 - Get adaptive ad size and return for setting on the ad view.
    return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
  }

  public View getView() {
    return this.mAdManagerAdView;
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
