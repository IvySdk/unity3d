package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//
//import androidx.annotation.NonNull;
//
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.util.Logger;
//import com.my.target.ads.MyTargetView;
//
//import org.json.JSONObject;
//
//public class MytargetBannerAdapter extends BannerAdapter<BaseAdapter.GridParams> {
//
//    private static final String TAG = "MyTarget-Banner";
//
//    private MyTargetView adView = null;
//
//    public MytargetBannerAdapter(Context context, String gridName, IvyAdType adType) {
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
//    public View getView() {
//        return adView;
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
//            adView = new MyTargetView(activity.getApplication());
//            int __p = Integer.parseInt(placement);
//            adView.setSlotId(__p);
//
//            adView.setRefreshAd(false);
//            adView.setAdSize(MyTargetView.AdSize.ADSIZE_320x50);
//            adView.setListener(myTargetViewListener);
//
//            RelativeLayout.LayoutParams adViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            adViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//            adView.setLayoutParams(adViewLayoutParams);
//            adView.load();
//        } catch (Exception e) {
//            super.onAdLoadFailed(e.getMessage());
//        }
//    }
//
//    private MyTargetView.MyTargetViewListener myTargetViewListener = new MyTargetView.MyTargetViewListener() {
//        @Override
//        public void onLoad(@NonNull MyTargetView myTargetView) {
//            MytargetBannerAdapter.this.adView = myTargetView;
//            MytargetBannerAdapter.super.onAdLoadSuccess();
//        }
//
//        @Override
//        public void onNoAd(@NonNull String s, @NonNull MyTargetView myTargetView) {
//            MytargetBannerAdapter.super.onAdLoadFailed(s);
//        }
//
//        @Override
//        public void onShow(@NonNull MyTargetView myTargetView) {
//            MytargetBannerAdapter.super.onAdShowSuccess();
//        }
//
//        @Override
//        public void onClick(@NonNull MyTargetView myTargetView) {
//            MytargetBannerAdapter.super.onAdClicked();
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
//    public static class GridParams extends BaseAdapter.GridParams {
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
