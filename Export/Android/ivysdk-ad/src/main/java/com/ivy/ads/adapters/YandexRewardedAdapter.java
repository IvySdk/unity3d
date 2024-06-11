package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ivy.Logger;
import com.ivy.ads.interfaces.IvyAdType;

import com.yandex.mobile.ads.common.AdRequest;
import com.yandex.mobile.ads.common.AdRequestError;
import com.yandex.mobile.ads.common.ImpressionData;
import com.yandex.mobile.ads.rewarded.Reward;
import com.yandex.mobile.ads.rewarded.RewardedAd;
import com.yandex.mobile.ads.rewarded.RewardedAdEventListener;

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

    public void fetch(Activity activity) {
        Logger.debug( "fetch()");
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
            AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
            AdRequest adRequest = adRequestBuilder.build();
            mRewardedAd = new RewardedAd(activity);
            mRewardedAd.setAdUnitId(placement);
            mRewardedAd.setRewardedAdEventListener(new RewardedAdEventListener() {
                @Override
                public void onAdLoaded() {
                    Logger.debug( "onAdLoaded()");
                    YandexRewardedAdapter.this.onAdLoadSuccess();
                }

                @Override
                public void onAdFailedToLoad(@NonNull AdRequestError adRequestError) {
                    int errorCode = adRequestError.getCode();
                    String description = adRequestError.getDescription();
                    Logger.debug( "onAdFailedToLoad, error: " + errorCode + ", description: " + description);
                    YandexRewardedAdapter.this.onAdLoadFailed(String.valueOf(errorCode));
                }

                @Override
                public void onAdShown() {
                    Logger.debug( "onAdShown()");
                    YandexRewardedAdapter.this.onAdShowSuccess();
                }

                @Override
                public void onAdDismissed() {
                    Logger.debug( "onAdDismissed()");
                    YandexRewardedAdapter.this.onAdClosed(gotReward);
                }

                @Override
                public void onRewarded(@NonNull Reward reward) {
                    Logger.debug( "onRewarded()");
                    gotReward = true;
                }

                @Override
                public void onAdClicked() {
                    Logger.debug( "onAdClicked()");
                    YandexRewardedAdapter.this.onAdClicked();
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
            mRewardedAd.loadAd(adRequest);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void show(Activity activity) {
        this.gotReward = false;
        if (this.mRewardedAd != null) {
            this.mRewardedAd.show();
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