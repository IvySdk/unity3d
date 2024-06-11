package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Build;
//import android.view.View;
//
//import com.amazon.device.ads.DTBAdBannerListener;
//import com.amazon.device.ads.DTBAdCallback;
//import com.amazon.device.ads.DTBAdRequest;
//import com.amazon.device.ads.DTBAdResponse;
//import com.amazon.device.ads.DTBAdSize;
//import com.amazon.device.ads.DTBAdView;
//import com.amazon.device.ads.SDKUtilities;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.util.Logger;
//
//import org.json.JSONObject;
//
//public class ApsBannerAdapter extends BannerAdapter<BannerAdapter.GridParams> implements DTBAdBannerListener {
//
//    private static final String TAG = "ApsManager";
//
//    private View adView;
//
//    public ApsBannerAdapter(Context context, String gridName, IvyAdType adType) {
//        super(context, gridName, adType);
//    }
//
//    @Override
//    public void setup(Activity activity) {
//        super.setup(activity);
//        ApsManager.getInstance().initialize(activity);
//    }
//
//    @Override
//    public View getView() {
//        return adView;
//    }
//
//    @Override
//    public void fetch(Activity activity) {
//        if (Build.VERSION.SDK_INT < 19) {
//            onAdLoadFailed("not support this version:" + Build.VERSION.SDK_INT);
//            return;
//        }
//        String placement = ((AdmobBannerAdapter.GridParams) getGridParams()).placement;
//        if (placement == null || "".equals(placement)) {
//            Logger.warning(TAG, "invalid placement");
//            super.onAdLoadFailed("INVALID placement id");
//            return;
//        }
//        DTBAdRequest dTBAdRequest = new DTBAdRequest();
//        dTBAdRequest.setSizes(new DTBAdSize(320, 50, placement));
//        dTBAdRequest.loadAd(new DTBAdCallback() {
//
//            @Override
//            public void onFailure(com.amazon.device.ads.AdError adError) {
//                onAdLoadFailed(adError.getMessage());
//            }
//
//            @Override
//            public void onSuccess(DTBAdResponse dTBAdResponse) {
//                try {
//                    String bidInfo = SDKUtilities.getBidInfo(dTBAdResponse);
//                    DTBAdView dTBAdView = new DTBAdView(mApplicationContext, ApsBannerAdapter.this);
//                    dTBAdView.fetchAd(bidInfo);
//                } catch (Exception unused) {
//                    onAdLoadFailed("Couldn't construct DTBAdView");
//                }
//            }
//        });
//    }
//
//    @Override
//    protected GridParams newGridParams() {
//        return new GridParams();
//    }
//
//    public static class GridParams extends BaseAdapter.GridParams {
//        public String placement;
//
//        @Override
//        public BaseAdapter.GridParams fromJSON(JSONObject jsonObject) {
//            this.placement = jsonObject.optString("placement");
//            return this;
//        }
//
//        @Override
//        protected String getParams() {
//            return "placement=" + this.placement;
//        }
//    }
//
//    @Override
//    public String getPlacementId() {
//        return ((GridParams) getGridParams()).placement;
//    }
//
//    @Override
//    public void onAdLoaded(View view) {
//        this.adView = view;
//        onAdLoadSuccess();
//    }
//
//    @Override
//    public void onAdFailed(View view) {
//        onAdLoadFailed("failed load aps banner");
//    }
//
//    @Override
//    public void onAdClicked(View view) {
//        onAdClicked();
//    }
//
//    @Override
//    public void onAdLeftApplication(View view) {
//
//    }
//
//    @Override
//    public void onAdOpen(View view) {
//
//    }
//
//    @Override
//    public void onAdClosed(View view) {
//        onAdClosed(false);
//    }
//
//    @Override
//    public void onImpressionFired(View view) {
//        onAdShowSuccess();
//    }
//}
