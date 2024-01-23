package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.util.Logger;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.interstitial.InterstitialAd;
import com.yandex.mobile.ads.interstitial.InterstitialAdEventListener;

import org.json.JSONObject;

public class YandexNonRewardedAdapter extends FullpageAdapter<FullpageAdapter.GridParams> {
    private static final String TAG = "Adapter-Yandex-Interstitial";
    private InterstitialAd mInterstitial = null;

    public YandexNonRewardedAdapter(Context context, String gridName, IvyAdType adType) {
        super(context, gridName, adType);
    }

    @Override
    public void setup(Activity activity) {
        super.setup(activity);
        YandexManager.getInstance().initialize(activity);
    }

    public void fetch(Activity activity) {
        String placement = getPlacementId();
        if (placement == null || "".equals(placement)) {
            super.onAdLoadFailed("INVALID");
            return;
        }
        if (!YandexManager.getInstance().isSdkInitialized()) {
            Logger.error("Yandex sdk not initialized");
            onAdLoadFailed("not_init");
            return;
        }
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        // Creating an InterstitialAd instance.
        mInterstitial = new InterstitialAd(activity);
        mInterstitial.setAdUnitId(placement);
        mInterstitial.setInterstitialAdEventListener(new InterstitialAdEventListener() {
            @Override
            public void onAdLoaded() {
                Logger.debug(TAG, "onAdLoaded()");
                YandexNonRewardedAdapter.this.onAdLoadSuccess();
            }

            @Override
            public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {
                int errorCode = adRequestError.getCode();
                String description = adRequestError.getDescription();
                Logger.debug(TAG, "onAdFailedToLoad, error: " + errorCode + ", description: " + description);
                YandexNonRewardedAdapter.this.onAdLoadFailed(String.valueOf(errorCode));
            }

            @Override
            public void onAdShown() {
                Logger.debug(TAG, "onAdShown()");
                YandexNonRewardedAdapter.this.onAdShowSuccess();
            }

            @Override
            public void onAdDismissed() {
                Logger.debug(TAG, "onAdDismissed()");
                YandexNonRewardedAdapter.this.onAdClosed(false);
            }

            @Override
            public void onAdClicked() {
                Logger.debug(TAG, "onAdClicked()");
                YandexNonRewardedAdapter.this.onAdClicked();
            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {
            }
        });
        mInterstitial.loadAd(adRequestBuilder.build());
    }

    public void show(Activity activity) {
        if (this.mInterstitial != null) {
            this.mInterstitial.show();
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