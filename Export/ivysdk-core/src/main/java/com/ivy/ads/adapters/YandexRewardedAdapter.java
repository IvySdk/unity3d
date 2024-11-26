package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.util.Logger;
import com.yandex.mobile.ads.common.AdError;
import com.yandex.mobile.ads.common.AdRequestConfiguration;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.rewarded.Reward;
import com.yandex.mobile.ads.rewarded.RewardedAd;
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener;
import com.yandex.mobile.ads.rewarded.RewardedAdLoadListener;
import com.yandex.mobile.ads.rewarded.RewardedAdLoader;

import org.json.JSONObject;

public class YandexRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> {
    private static final String TAG = "Adapter-Yandex-Rewarded";
    private boolean gotReward;
    private RewardedAd mRewardedAd = null;

    @Override
    public void setup(Activity activity) {
        super.setup(activity);
        YandexManager.getInstance().initialize(activity);
    }

    public YandexRewardedAdapter(Context context, String gridName, IvyAdType adType) {
        super(context, gridName, adType);
    }

    private RewardedAdEventListener adEventListener = new RewardedAdEventListener() {
        @Override
        public void onAdShown() {
            Logger.debug(TAG, "onAdShown()");
            YandexRewardedAdapter.this.onAdShowSuccess();
        }

        @Override
        public void onAdFailedToShow(@NonNull AdError adError) {
            YandexRewardedAdapter.this.onAdShowFail();
        }

        @Override
        public void onAdDismissed() {
            Logger.debug(TAG, "onAdDismissed()");
            YandexRewardedAdapter.this.onAdClosed(gotReward);
        }

        @Override
        public void onAdClicked() {
            Logger.debug(TAG, "onAdClicked()");
            YandexRewardedAdapter.this.onAdClicked();
        }

        @Override
        public void onAdImpression(@Nullable ImpressionData impressionData) {
            try{
                assert impressionData != null;
                JSONObject data = new JSONObject(impressionData.getRawData());
                JSONObject network = data.getJSONObject("network");

                if (adBundle == null) {
                    adBundle = new Bundle();
                } else {
                    adBundle.clear();
                }
                String ad_network = network.getString("adapter");
                String adUnitId = network.getString("ad_unit_id");
                double revenue = data.getDouble("revenueUSD");
                adBundle.putString("ad_network", ad_network);
                adBundle.putString("ad_source_instance", ad_network);
                adBundle.putString("mediation_group", network.getString("name"));

                onGMSPaidTrackEvent(ad_network, "rewarded", "rewarded", adUnitId,"USD", 1, (float) revenue);
            } catch (Exception e){

            }
        }

        @Override
        public void onRewarded(@NonNull Reward reward) {
            Logger.debug(TAG, "onRewarded()");
            gotReward = true;
        }
    };

    @Override
    public String getMediation() {
        return "yandex";
    }

    public void fetch(Activity activity) {
        Logger.debug(TAG, "fetch()");
        if (!YandexManager.getInstance().isSdkInitialized()) {
            Logger.error("Yandex sdk not initialized");
            onAdLoadFailed("not_init");
            return;
        }
        try {
            String placement = getPlacementId();
            if (placement == null || "".equals(placement)) {
                super.onAdLoadFailed("INVALID");
                return;
            }
            RewardedAdLoader adLoader = new RewardedAdLoader(activity.getApplicationContext());
            adLoader.setAdLoadListener(new RewardedAdLoadListener() {
                @Override
                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                    YandexRewardedAdapter.this.mRewardedAd = rewardedAd;
                    YandexRewardedAdapter.this.mRewardedAd.setAdEventListener(YandexRewardedAdapter.this.adEventListener);
                    YandexRewardedAdapter.this.onAdLoadSuccess();
                }

                @Override
                public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {
                    int errorCode = adRequestError.getCode();
                    String description = adRequestError.getDescription();
                    Logger.debug(TAG, "onAdFailedToLoad, error: " + errorCode + ", description: " + description);
                    YandexRewardedAdapter.this.onAdLoadFailed(String.valueOf(errorCode));
                }
            });
            AdRequestConfiguration configuration = new AdRequestConfiguration.Builder(placement).build();
            adLoader.loadAd(configuration);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void show(Activity activity) {
        Logger.debug(TAG, "show()");
        this.gotReward = false;
        if (this.mRewardedAd != null) {
            this.mRewardedAd.show(activity);
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