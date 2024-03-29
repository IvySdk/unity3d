package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.util.Logger;
import com.yandex.mobile.ads.banner.AdSize;
import com.yandex.mobile.ads.banner.BannerAdEventListener;
import com.yandex.mobile.ads.banner.BannerAdView;
import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;

import org.json.JSONObject;

public class YandexBannerAdapter extends BannerAdapter<BannerAdapter.GridParams> {
    private static final String TAG = "Yandex-Banner";
    private BannerAdView mAdview = null;

    public YandexBannerAdapter(Context context, String gridName, IvyAdType adType) {
        super(context, gridName, adType);
    }

    @Override
    public void setup(Activity activity) {
        super.setup(activity);
        YandexManager.getInstance().initialize(activity);
    }

    public void fetch(Activity activity) {
        Logger.debug(TAG, "fetch yandex banner begin");
        if (!YandexManager.getInstance().isSdkInitialized()) {
            Logger.error("Yandex sdk not initialized");
            onAdLoadFailed("not_init");
            return;
        }
        if (this.mAdview != null) {
            this.mAdview.destroy();
            this.mAdview = null;
        }
        String placement = ((GridParams) getGridParams()).placement;
        if (placement == null || "".equals(placement)) {
            Logger.warning(TAG, "invalid placement");
            super.onAdLoadFailed("INVALID");
            return;
        }
        this.mAdview = new BannerAdView(activity);
        this.mAdview.setAdUnitId(placement);
        this.mAdview.setAdSize(AdSize.BANNER_320x50);
        // Creating an ad targeting object.
        final AdRequest adRequest = new AdRequest.Builder().build();
        this.mAdview.setBannerAdEventListener(new BannerAdEventListener() {
            @Override
            public void onAdLoaded() {
                Logger.debug(TAG, "onAdLoaded()");
                onAdLoadSuccess();
            }

            @Override
            public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {
                int errorCode = adRequestError.getCode();
                String description = adRequestError.getDescription();
                Logger.debug(TAG, "onAdFailedToLoad, error: " + errorCode + ", description: " + description);
                YandexBannerAdapter.this.onAdLoadFailed(String.valueOf(errorCode));
            }

            @Override
            public void onAdClicked() {
                Logger.debug(TAG, "onAdClicked()");
                YandexBannerAdapter.this.onAdClicked();
            }

            @Override
            public void onLeftApplication() {
            }

            @Override
            public void onReturnedToApplication() {
            }

            @Override
            public void onImpression(@Nullable ImpressionData impressionData) {
//        logAdShowEvent("yandex", "banner", "banner", placement);
                YandexBannerAdapter.this.onAdShowSuccess();
            }
        });
        this.mAdview.loadAd(adRequest);
    }

    @Override
    public String getPlacementId() {
        return ((GridParams) getGridParams()).placement;
    }

    public View getView() {
        return this.mAdview;
    }

    @Override
    public int getWidth() {
        return BannerAdapter.SMART_BANNER_WIDTH;
    }

    @Override
    public int getHeight() {
        return STANDARD_BANNER_HEIGHT;
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