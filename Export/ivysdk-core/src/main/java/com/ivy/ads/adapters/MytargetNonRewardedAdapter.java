//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.util.Logger;
//import com.my.target.ads.InterstitialAd;
//
//import org.json.JSONObject;
//
//public class MytargetNonRewardedAdapter extends FullpageAdapter<BaseAdapter.GridParams> {
//
//    private static final String TAG = "MyTarget-Interstitial";
//    private InterstitialAd interstitialAd = null;
//
//    public MytargetNonRewardedAdapter(Context context, String gridName, IvyAdType adType) {
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
//            interstitialAd = new InterstitialAd(Integer.parseInt(placement), activity.getApplication());
//            interstitialAd.setListener(interstitialAdListener);
//            interstitialAd.load();
//        } catch (Exception e) {
//            super.onAdLoadFailed(e.getMessage());
//        }
//    }
//
//    @Override
//    public void show(Activity activity) {
//        if (interstitialAd != null){
//            interstitialAd.show(activity);
//        } else {
//            super.onAdShowFail();
//        }
//    }
//
//    private InterstitialAd.InterstitialAdListener interstitialAdListener = new InterstitialAd.InterstitialAdListener() {
//        @Override
//        public void onLoad(@NonNull InterstitialAd interstitialAd) {
//            MytargetNonRewardedAdapter.this.interstitialAd = interstitialAd;
//            MytargetNonRewardedAdapter.super.onAdLoadSuccess();
//        }
//
//        @Override
//        public void onNoAd(@NonNull String s, @NonNull InterstitialAd interstitialAd) {
//            MytargetNonRewardedAdapter.super.onAdLoadFailed(s);
//        }
//
//        @Override
//        public void onClick(@NonNull InterstitialAd interstitialAd) {
//            MytargetNonRewardedAdapter.super.onAdClicked();
//        }
//
//        @Override
//        public void onDismiss(@NonNull InterstitialAd interstitialAd) {
//            MytargetNonRewardedAdapter.super.onAdClosed(false);
//        }
//
//        @Override
//        public void onVideoCompleted(@NonNull InterstitialAd interstitialAd) {
//
//        }
//
//        @Override
//        public void onDisplay(@NonNull InterstitialAd interstitialAd) {
//            MytargetNonRewardedAdapter.super.onAdShowSuccess();
//        }
//    };
//
//    @Override
//    protected GridParams newGridParams() {
//        return new GridParams();
//    }
//
//    @Override
//    public String getPlacementId() {
//        return ((GridParams) getGridParams()).placement;
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
//
//}
