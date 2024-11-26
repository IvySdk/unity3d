package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdapterResponseInfo;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.ivy.IvySdk;
import com.ivy.ads.events.EventParams;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.Locale;

public class AdmobRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> {
    private static final String TAG = "Adapter-Admob-Rewarded";
    private boolean gotReward;
    private RewardedAd mRewardedAd = null;
    private String mediation = null;


    private FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
        public void onAdFailedToShowFullScreenContent(AdError adError) {
            Logger.debug(TAG, "onAdFailedToShowFullScreenContent()");
            onAdShowFail();
        }

        public void onAdShowedFullScreenContent() {
            Logger.debug(TAG, "onAdShowSuccess()");
            AdmobRewardedAdapter.this.onAdShowSuccess();
            onPAMAdShow("rewarded", getPlacementId(), "-1");
        }

        public void onAdDismissedFullScreenContent() {
            Logger.debug(TAG, "onRewardedAdClosed()");
            onAdClosed(gotReward);
            onPAMAdClose("rewarded", getPlacementId());
        }

        public void onAdImpression() {
        }

        public void onAdClicked() {
            onPAMAdClick("rewarded", getPlacementId());
        }
    };

    private final OnUserEarnedRewardListener onUserEarnedRewardListener = new OnUserEarnedRewardListener() {
        @Override
        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
            gotReward = true;
            try {
                Bundle bundle = new Bundle();
                if (!TextUtils.isEmpty(mediation)) {
                    bundle.putString(EventParams.PARAM_MEDIATION, mediation);
                }
                mEventHandler.getEventLogger().logEvent("video_rewarded", bundle);
            } catch (Exception e){
                e.printStackTrace();
            }
            onPAMAdRewarded("rewarded", getPlacementId(), rewardItem.getAmount(), "USD");
        }
    };

    private OnPaidEventListener onPaidEventListener = adValue -> {
        try {
            Logger.debug(TAG, String.format(Locale.US,
                    "Paid event of value %d in currency %s of precision %s%n.",
                    adValue.getValueMicros(),
                    adValue.getCurrencyCode(),
                    adValue.getPrecisionType())
            );

            String currencyCode = adValue.getCurrencyCode();
            int precisionType = adValue.getPrecisionType();
            long valueMicros = adValue.getValueMicros();

            onGmsPaidEvent("admob", "rewarded", "rewarded", getPlacementId(), currencyCode, precisionType, valueMicros);

            onPAMEvent(valueMicros);
        } catch (Throwable t) {
            Logger.error(TAG, "onPaidEvent exception", t);
        }
    };

    public AdmobRewardedAdapter(Context context, String gridName, IvyAdType adType) {
        super(context, gridName, adType);
    }

    public void fetch(Activity activity) {
        Logger.debug(TAG, "fetch()");

        try {
            String placement = getPlacementId();
            if (placement == null || "".equals(placement)) {
                super.onAdLoadFailed("INVALID");
                return;
            }
            AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
            AdRequest adRequest = adRequestBuilder.build();
            if (lastPAM != null) {
                Bundle extras = new Bundle();
                Bundle customTargetingBundle = new Bundle();
                customTargetingBundle.putString("userGroup", lastPAM.key);
                Log.e("ADSFALL", "rewarded setup userGroup::" + lastPAM.key);
                extras.putBundle("admob_custom_keyvals", customTargetingBundle);
                adRequestBuilder.addNetworkExtrasBundle(AdMobAdapter.class, extras);
            }
            onPAMRequest("rewarded");
            RewardedAd.load(activity, placement, adRequest, new RewardedAdLoadCallback() {
                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {

                    AdmobRewardedAdapter.this.mRewardedAd = rewardedAd;
                    AdmobRewardedAdapter.this.mRewardedAd.setFullScreenContentCallback(fullScreenContentCallback);
                    AdmobRewardedAdapter.this.mRewardedAd.setOnPaidEventListener(onPaidEventListener);
                    mediation = mRewardedAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();
                    try{
                        mediation = mRewardedAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();
                    } catch (Exception e){}

                    AdmobRewardedAdapter.this.onAdLoadSuccess();
                    onPAMAdLoad(rewardedAd.getResponseInfo(), "rewarded", placement, "-1");
                }

                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    int errorCode = loadAdError.getCode();
                    Logger.warning(TAG, "onAdFailedToLoad : " + errorCode);
                    AdmobRewardedAdapter.this.mRewardedAd = null;
                    AdmobRewardedAdapter.this.onAdLoadFailed(String.valueOf(errorCode));
                    onPAMAdLoad(loadAdError.getResponseInfo(), "rewarded", placement, errorCode + "");
                }
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void show(Activity activity) {
        Logger.debug(TAG, "show()");
        this.gotReward = false;
        if (this.mRewardedAd != null) {
            try {
                if (adBundle == null) {
                    adBundle = new Bundle();
                } else {
                    adBundle.clear();
                }

                mediation = mRewardedAd.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();

                AdapterResponseInfo loadedAdapterResponseInfo = mRewardedAd.getResponseInfo().
                        getLoadedAdapterResponseInfo();
                String adSourceName = loadedAdapterResponseInfo.getAdSourceName();
//                String adSourceId = loadedAdapterResponseInfo.getAdSourceId();
                String adSourceInstanceName = loadedAdapterResponseInfo.getAdSourceInstanceName();
//                String adSourceInstanceId = loadedAdapterResponseInfo.getAdSourceInstanceId();

                Bundle extras = mRewardedAd.getResponseInfo().getResponseExtras();
                String mediationGroupName = extras.getString("mediation_group_name");
                String mediationABTestName = extras.getString("mediation_ab_test_name");
//                String mediationABTestVariant = extras.getString("mediation_ab_test_variant");

                adBundle.putString("ad_network", adSourceInstanceName);
                adBundle.putString("ad_source_instance", adSourceInstanceName);
                adBundle.putString("mediation_group", mediationGroupName);
                adBundle.putString("mediation_ab_test", mediationABTestName);
                Log.e("admobLog", "rewarded video loaded");
                for (String s : adBundle.keySet()) {
                    Log.e("admobLog", "key:" + s + " :: value:" + adBundle.get(s));
                }
            } catch (Exception e) {
                AdmobRewardedAdapter.this.mediation = null;
            }
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
            try {
                if (IvySdk.getRemoteConfigAsBoolean("is_pam_video")) {
                    this.placement = IvySdk.getRemoteConfigAsString("PAM_ad_unit_android_rewarded");
                    if (this.placement == null || this.placement.isEmpty()) {
                        throw new IllegalArgumentException("get remote config rewarded ad unit id failed");
                    }
                } else {
                    Logger.debug("pam rewarded remote config set to default");
                    this.placement = jsonObject.optString("placement");
                }
            } catch (Exception e) {
                Logger.debug(e.getMessage());
                this.placement = jsonObject.optString("placement");
            }
            // this.placement = jsonObject.optString("placement");
            return this;
        }

        protected String getParams() {
            return "placement=" + this.placement;
        }
    }
}
