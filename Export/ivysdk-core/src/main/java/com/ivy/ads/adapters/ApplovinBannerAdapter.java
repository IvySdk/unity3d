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
import com.ivy.util.Logger;

import org.json.JSONObject;

public class ApplovinBannerAdapter extends BannerAdapter<BannerAdapter.GridParams> implements AppLovinAdDisplayListener, AppLovinAdLoadListener, AppLovinAdClickListener, AppLovinAdViewEventListener {
    private static final String TAG = Logger.createTag(ApplovinBannerAdapter.class);
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
        Logger.debug(TAG, "fetch()");
        AppLovinSdk sdk = ApplovinManager.getInstance(activity);
        if (sdk == null) {
            Logger.error(TAG, "Applovin SDK initialize failed");
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
        Logger.debug(TAG, "Applovin Banner clicked");
        super.onAdClicked();
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        Logger.debug(TAG, "Applovin Banner displayed");
        super.onAdShowSuccess();
    }

    public void adHidden(AppLovinAd appLovinAd) {
        Logger.debug(TAG, "Applovin Banner hidden");
    }

    public void adReceived(AppLovinAd appLovinAd) {
        Logger.debug(TAG, "Applovin ad received");
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
        Logger.debug(TAG, "Applovin failed to receive ad: %s", reason);
        super.onAdLoadFailed(reason);
    }

    public void adOpenedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
    }

    public void adClosedFullscreen(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
    }

    public void adLeftApplication(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView) {
    }

    public void adFailedToDisplay(AppLovinAd appLovinAd, AppLovinAdView appLovinAdView, AppLovinAdViewDisplayErrorCode appLovinAdViewDisplayErrorCode) {
        Logger.debug(TAG, "Applovin failed to display ad");
    }

  public static class GridParams extends com.ivy.ads.adapters.BaseAdapter.GridParams {
        public String sdkKey;
        public String zoneId = "";

        public GridParams fromJSON(JSONObject jsonObject) {
            this.sdkKey = jsonObject.optString("sdkKey");
            this.zoneId = jsonObject.optString("zoneId");
            return this;
        }

        protected String getParams() {
            return "placement=" + this.sdkKey + (this.zoneId != null ? ", zoneId=" + this.zoneId : "");
        }
    }
}
