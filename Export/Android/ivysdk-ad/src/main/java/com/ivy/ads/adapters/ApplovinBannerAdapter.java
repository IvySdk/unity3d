package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinAdViewDisplayErrorCode;
import com.applovin.adview.AppLovinAdViewEventListener;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyLoadStatus;


import org.json.JSONObject;

public class ApplovinBannerAdapter extends BannerAdapter<BannerAdapter.GridParams> implements AppLovinAdDisplayListener, AppLovinAdLoadListener, AppLovinAdClickListener, AppLovinAdViewEventListener {
    private AppLovinAdView bannerAdView;

    public ApplovinBannerAdapter(Context context, String gridName, IvyAdType adType) {
        super(context, gridName, adType);
    }

    @Override
    public String getPlacementId() {
        return ((GridParams) getGridParams()).zoneId;
    }

    public View getView() {
        return this.bannerAdView;
    }

    public void fetch(Activity activity) {
        AppLovinSdk sdk = ApplovinManager.getInstance(activity);
        if (sdk == null) {
            return;
        }
        this.bannerAdView = new AppLovinAdView(sdk, AppLovinAdSize.BANNER, activity);
        this.bannerAdView.setAdClickListener(this);
        this.bannerAdView.setAdDisplayListener(this);
        this.bannerAdView.setAdLoadListener(this);
        if (getPlacementId() == null || getPlacementId().trim().isEmpty()) {
            sdk.getAdService().loadNextAd(AppLovinAdSize.BANNER, this);
        } else {
            sdk.getAdService().loadNextAdForZoneId(getPlacementId(), this);
        }
    }

    protected GridParams newGridParams() {
        return new GridParams();
    }

    public void adClicked(AppLovinAd appLovinAd) {
        super.onAdClicked();
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        super.onAdShowSuccess();
    }

    public void adHidden(AppLovinAd appLovinAd) {
    }

    public void adReceived(AppLovinAd appLovinAd) {
        this.bannerAdView.renderAd(appLovinAd);
        super.onAdLoadSuccess();
    }

    public void failedToReceiveAd(int errorCode) {
        String reason = "";
        switch (errorCode) {
            case AppLovinErrorCodes.NO_FILL:
                reason = IvyLoadStatus.NO_FILL;
                break;
            default:
                reason = ApplovinManager.errorCodeToMessage(errorCode);
                break;
        }
        super.onAdLoadFailed(reason);
    }

    public void adOpenedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
    }

    public void adClosedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
    }

    public void adLeftApplication(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
    }

    public void adFailedToDisplay(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinAdViewDisplayErrorCode appLovinAdViewDisplayErrorCode) {

    }

  public static class GridParams extends BaseAdapter.GridParams {
        public String sdkKey;
        public String zoneId = "";

        public GridParams fromJSON(JSONObject jsonObject) {
            this.sdkKey = jsonObject.optString("sdkkey");
            this.zoneId = jsonObject.optString("zoneId");
            return this;
        }

        protected String getParams() {
            return "placement=" + this.sdkKey + (this.zoneId != null ? ", zoneId=" + this.zoneId : "");
        }
    }
}
