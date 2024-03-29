//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.util.Logger;
//import com.my.target.ads.Reward;
//import com.my.target.ads.RewardedAd;
//
//import org.json.JSONObject;
//
//public class MytargetRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> {
//
//    private static final String TAG = "MyTarget-RewardedAd";
//    private RewardedAd rewardedAd = null;
//    private boolean gotReward = false;
//
//    public MytargetRewardedAdapter(Context context, String gridName, IvyAdType adType) {
//        super(context, gridName, adType);
//    }
//
//    @Override
//    public void setup(Activity activity) {
//        super.setup(activity);
//        MytargetManager.getInstance().initialize(activity);
//    }
//
//    @Override
//    public void fetch(Activity activity) {
//        String placement = getPlacementId();
//        if (placement == null || "".equals(placement)) {
//            Logger.warning(TAG, "invalid placement");
//            super.onAdLoadFailed("INVALID");
//            return;
//        }
//        try {
//            rewardedAd = new RewardedAd(Integer.parseInt(placement), activity.getApplication());
//            rewardedAd.setListener(rewardedAdListener);
//            rewardedAd.load();
//        } catch (Exception e) {
//            super.onAdLoadFailed(e.getMessage());
//        }
//    }
//
//    @Override
//    public void show(Activity activity) {
//        this.gotReward = false;
//        if (rewardedAd != null) {
//            rewardedAd.show(activity);
//        } else {
//            super.onAdShowFail();
//        }
//    }
//
//    private RewardedAd.RewardedAdListener rewardedAdListener = new RewardedAd.RewardedAdListener() {
//        @Override
//        public void onLoad(@NonNull RewardedAd rewardedAd) {
//            MytargetRewardedAdapter.this.rewardedAd = rewardedAd;
//            MytargetRewardedAdapter.super.onAdLoadSuccess();
//        }
//
//        @Override
//        public void onNoAd(@NonNull String s, @NonNull RewardedAd rewardedAd) {
//            MytargetRewardedAdapter.super.onAdLoadFailed(s);
//        }
//
//        @Override
//        public void onClick(@NonNull RewardedAd rewardedAd) {
//            MytargetRewardedAdapter.super.onAdClicked();
//        }
//
//        @Override
//        public void onDismiss(@NonNull RewardedAd rewardedAd) {
//            MytargetRewardedAdapter.super.onAdClosed(MytargetRewardedAdapter.this.gotReward);
//        }
//
//        @Override
//        public void onReward(@NonNull Reward reward, @NonNull RewardedAd rewardedAd) {
//            MytargetRewardedAdapter.this.gotReward = true;
//        }
//
//        @Override
//        public void onDisplay(@NonNull RewardedAd rewardedAd) {
//            MytargetRewardedAdapter.super.onAdShowSuccess();
//        }
//    };
//
//    @Override
//    protected MytargetNonRewardedAdapter.GridParams newGridParams() {
//        return new MytargetNonRewardedAdapter.GridParams();
//    }
//
//    @Override
//    public String getPlacementId() {
//        return ((MytargetNonRewardedAdapter.GridParams) getGridParams()).placement;
//    }
//
//    public static class GridParams extends com.ivy.ads.adapters.BaseAdapter.GridParams {
//        public String placement;
//
//        public GridParams fromJSON(JSONObject jsonObject) {
//            this.placement = jsonObject.optString("placement");
//            return this;
//        }
//
//        protected String getParams() {
//            return "placement=" + this.placement;
//        }
//    }
//}
