package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdapterResponseInfo;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.util.Logger;

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
            String message = adError != null ? adError.toString() : " Null";
            Logger.debug(TAG, "onAdFailedToShowFullScreenContent, adError: " + message);
            onAdShowFail();
            mRewardInterstitialAd = null;
        }

        @Override
        public void onAdShowedFullScreenContent() {
            Logger.debug(TAG, "onAdShowedFullScreenContent");
            onAdShowSuccess();
            mRewardInterstitialAd = null;
        }

        @Override
        public void onAdDismissedFullScreenContent() {
            Logger.debug(TAG, "onAdDismissedFullScreenContent, gotReward: " + gotReward);
            onAdClosed(gotReward);
        }
    };


    public void fetch(Activity activity) {
        Logger.debug(TAG, "fetch()");

        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        if (lastPAM != null) {
            Bundle extras = new Bundle();
            Bundle customTargetingBundle = new Bundle();
            customTargetingBundle.putString("userGroup", lastPAM.key);
            extras.putBundle("admob_custom_keyvals", customTargetingBundle);
            adRequestBuilder.addNetworkExtrasBundle(AdMobAdapter.class, extras);
        }
        gotReward = false;
        RewardedInterstitialAd.load(activity, getPlacementId(), adRequestBuilder.build(), new RewardedInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedInterstitialAd rewardedInterstitialAd) {
                Logger.debug(TAG, "onRewardedInterstitialAdLoaded");
                try {
                    mediation = rewardedInterstitialAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();
                } catch (Exception e) {
                    AdmobRewardedInterstitialAdapter.this.mediation = null;
                }
                AdmobRewardedInterstitialAdapter.this.mRewardInterstitialAd = rewardedInterstitialAd;
                mRewardInterstitialAd.setFullScreenContentCallback(fullScreenContentCallback);

                try {
                    mediation = mRewardInterstitialAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();
                } catch (Exception e) {
                }

                onAdLoadSuccess();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                Logger.debug(TAG, "onRewardedInterstitialAdFailedToLoad");
                onAdLoadFailed(String.valueOf(loadAdError.getCode()));
                mRewardInterstitialAd = null;
            }
        });

    }

    public void show(Activity activity) {
        Logger.debug(TAG, "show()");
        if (mRewardInterstitialAd != null) {

            try {
                if (adBundle == null) {
                    adBundle = new Bundle();
                } else {
                    adBundle.clear();
                }

                mediation = mRewardInterstitialAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();

                AdapterResponseInfo loadedAdapterResponseInfo = mRewardInterstitialAd.getResponseInfo().
                        getLoadedAdapterResponseInfo();
                String adSourceName = loadedAdapterResponseInfo.getAdSourceName();
//                String adSourceId = loadedAdapterResponseInfo.getAdSourceId();
                String adSourceInstanceName = loadedAdapterResponseInfo.getAdSourceInstanceName();
//                String adSourceInstanceId = loadedAdapterResponseInfo.getAdSourceInstanceId();

                Bundle extras = mRewardInterstitialAd.getResponseInfo().getResponseExtras();
                String mediationGroupName = extras.getString("mediation_group_name");
                String mediationABTestName = extras.getString("mediation_ab_test_name");
//                String mediationABTestVariant = extras.getString("mediation_ab_test_variant");

                adBundle.putString("ad_network", adSourceInstanceName);
                adBundle.putString("ad_source_instance", adSourceInstanceName);
                adBundle.putString("mediation_group", mediationGroupName);
                adBundle.putString("mediation_ab_test", mediationABTestName);

            } catch (Exception e) {
                AdmobRewardedInterstitialAdapter.this.mediation = null;
            }
            mRewardInterstitialAd.show(activity, this);
        } else {
            Logger.debug(TAG, "Rewarded Interstitial show failed");
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
