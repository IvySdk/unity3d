package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.util.Logger;
import com.unity3d.services.banners.BannerErrorInfo;
import com.unity3d.services.banners.BannerView;
import com.unity3d.services.banners.UnityBannerSize;

import org.json.JSONObject;

public class UnityBannerAdapter extends BannerAdapter<BannerAdapter.GridParams> {
    private static final String TAG = "Unity-Banner";
    private BannerView bannerView = null;

    public UnityBannerAdapter(Context context, String gridName, IvyAdType adType) {
        super(context, gridName, adType);
    }

    @Override
    public View getView() {
        return null;
    }

    @Override
    public void fetch(Activity activity) {
        if (this.bannerView != null) {
            this.bannerView.destroy();
            this.bannerView = null;
        }

        String placement = ((AdmobBannerAdapter.GridParams) getGridParams()).placement;
        if (placement == null || "".equals(placement)) {
            Logger.warning(TAG, "invalid placement");
            super.onAdLoadFailed("INVALID");
            return;
        }
        bannerView = new BannerView(activity, placement, new UnityBannerSize(320, 50));
        // Set the listener for banner lifecycle events:
        bannerView.setListener(bannerListener);
        bannerView.load();
    }

    private BannerView.IListener bannerListener = new BannerView.IListener() {
        @Override
        public void onBannerLoaded(BannerView bannerAdView) {
            UnityBannerAdapter.this.bannerView = bannerAdView;
            UnityBannerAdapter.this.onAdLoadSuccess();
        }

        @Override
        public void onBannerShown(BannerView bannerAdView) {
            UnityBannerAdapter.this.onAdShowSuccess();
        }

        @Override
        public void onBannerFailedToLoad(BannerView bannerAdView, BannerErrorInfo errorInfo) {
            UnityBannerAdapter.this.bannerView = null;
            UnityBannerAdapter.this.onAdLoadFailed(errorInfo != null ? errorInfo.errorMessage : "");
        }

        @Override
        public void onBannerClick(BannerView bannerAdView) {
            UnityBannerAdapter.this.onAdClicked();
        }

        @Override
        public void onBannerLeftApplication(BannerView bannerAdView) {
        }
    };


    @Override
    protected GridParams newGridParams() {
        return new GridParams();
    }

    @Override
    public String getPlacementId() {
        return ((GridParams) getGridParams()).placement;
    }

    public String getAppId() {
        return ((GridParams) getGridParams()).gameId;
    }

    public static class GridParams extends BaseAdapter.GridParams {
        public String placement;
        public String gameId;

        public GridParams fromJSON(JSONObject jsonObject) {
            this.gameId = jsonObject.optString("gameId");
            this.placement = jsonObject.optString("placement");
            return this;
        }

        protected String getParams() {
            return "placement=" + this.placement;
        }
    }

}
