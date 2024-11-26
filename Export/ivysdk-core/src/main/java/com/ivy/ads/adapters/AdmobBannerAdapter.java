package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdapterResponseInfo;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.ivy.IvySdk;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.player.Player;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.Locale;

public final class AdmobBannerAdapter extends BannerAdapter<BannerAdapter.GridParams> {
    private static final String TAG = "Admob-Banner";
    private AdView mAdview;
    private AdSize mAdSize;
    private boolean isSmartBanner = false;
    private String mediation = null;

    public AdmobBannerAdapter(Context context, String gridName, IvyAdType adType) {
        super(context, gridName, adType);
    }

    public void fetch(Activity activity) {
        Logger.debug(TAG, "fetch admob banner begin");
        if (this.mAdview != null) {
            this.mAdview.destroy();
            this.mAdview = null;
        }

        String placement = ((GridParams) getGridParams()).placement;
        if (placement == null || "".equals(placement)) {
            Logger.warning(TAG, "invalid placement");
            super.onAdLoadFailed("INVALID");
            return;
        }
        this.mAdview = new AdView(activity);
        this.mAdview.setAdUnitId(placement);

        String setSize = getSize();
        if (setSize != null) {
            if ("banner".equals(setSize)) {
                this.mAdSize = AdSize.BANNER;
                this.mAdview.setAdSize(this.mAdSize);
            } else {
                this.mAdSize = AdSize.SMART_BANNER;
                this.mAdview.setAdSize(this.mAdSize);
            }
        } else {
            if (isAdaptive()) {
                this.mAdSize = getAdSize(activity);
                // Step 4 - Set the adaptive ad size on the ad view.
                this.mAdview.setAdSize(this.mAdSize);
            } else {
                boolean z = isHeightLowerThan720DpAngHigherThan400Dp(activity) && isSmartBannerEnabled();
                this.isSmartBanner = z;
                AdSize adSize = isTablet() ? AdSize.LEADERBOARD : this.isSmartBanner ? AdSize.SMART_BANNER : AdSize.BANNER;
                this.mAdview.setAdSize(adSize);
            }
        }

        this.mAdview.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Logger.debug(TAG, "onAdClosed()");
                AdmobBannerAdapter.this.onAdClosed(false);
                onPAMAdClose("banner", placement);
            }

            @Override
            public void onAdClicked() {
                Logger.debug(TAG, "onAdClicked()");
                AdmobBannerAdapter.this.onAdClicked();
                onPAMAdClick("banner", placement);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                int errorCode = loadAdError.getCode();
                Logger.debug(TAG, "errorCode: %s", errorCode);
                String reason = AdmobManager.errorCodeToMessage(errorCode);
                AdmobBannerAdapter.this.onAdLoadFailed(reason);

                onPAMAdLoad(loadAdError.getResponseInfo(), "banner", getPlacementId(), errorCode + "");

            }

            @Override
            public void onAdOpened() {
                Logger.debug(TAG, "onAdOpened()");
            }

            @Override
            public void onAdLoaded() {
                Logger.debug(TAG, "onAdLoaded()");
                ResponseInfo responseInfo = AdmobBannerAdapter.this.mAdview.getResponseInfo();
                try {
//                    try{
//                        String adResponseId = AdmobBannerAdapter.this.mAdview.getResponseInfo().getResponseId();
//                        FirebaseCrashlytics.getInstance().setCustomKey("banner_ad_response_id", adResponseId);
//                    } catch (Exception e){}
                    mediation = responseInfo.getLoadedAdapterResponseInfo().getAdSourceName();

                    if (adBundle == null) {
                        adBundle = new Bundle();
                    } else {
                        adBundle.clear();
                    }

                    mediation = AdmobBannerAdapter.this.mAdview.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();

                    AdapterResponseInfo loadedAdapterResponseInfo = AdmobBannerAdapter.this.mAdview.getResponseInfo().
                            getLoadedAdapterResponseInfo();
                    String adSourceName = loadedAdapterResponseInfo.getAdSourceName();
//                String adSourceId = loadedAdapterResponseInfo.getAdSourceId();
                    String adSourceInstanceName = loadedAdapterResponseInfo.getAdSourceInstanceName();
//                String adSourceInstanceId = loadedAdapterResponseInfo.getAdSourceInstanceId();

                    Bundle extras = AdmobBannerAdapter.this.mAdview.getResponseInfo().getResponseExtras();
                    String mediationGroupName = extras.getString("mediation_group_name");
                    String mediationABTestName = extras.getString("mediation_ab_test_name");
//                String mediationABTestVariant = extras.getString("mediation_ab_test_variant");

                    adBundle.putString("ad_network", adSourceInstanceName);
                    adBundle.putString("ad_source_instance", adSourceInstanceName);
                    adBundle.putString("mediation_group", mediationGroupName);
                    adBundle.putString("mediation_ab_test", mediationABTestName);

                    Log.e("admobLog", "banner loaded");
                    for (String s : adBundle.keySet()) {
                        Log.e("admobLog", "key:" + s + " :: value:" + adBundle.get(s));
                    }
                } catch (Exception e) {
                    AdmobBannerAdapter.this.mediation = null;
                }

                onPAMAdLoad(responseInfo, "banner", placement, "-1");
                AdmobBannerAdapter.this.onAdLoadSuccess();
            }

            @Override
            public void onAdImpression() {
                Logger.debug(TAG, "onAdImpression()");
                AdmobBannerAdapter.this.onAdShowSuccess();
                onPAMAdShow("banner", placement, "-1");
            }
        });

        this.mAdview.setOnPaidEventListener(new OnPaidEventListener() {
            @Override
            public void onPaidEvent(AdValue adValue) {
                if (adValue == null) {
                    return;
                }
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

                    onGmsPaidEvent("admob", "banner", "banner", getPlacementId(), currencyCode, precisionType, valueMicros);

                    onPAMEvent(valueMicros);

                } catch (Throwable t) {
                    Logger.error(TAG, "onPaidEvent exception", t);
                }
            }
        });

        onPAMRequest("banner");
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        String collapsible = ((GridParams) getGridParams()).collapsible;
        Bundle extras = new Bundle();
        if (collapsible != null && !"none".equals(collapsible)) {
            extras.putString("collapsible", collapsible);
        }
        if (lastPAM != null) {
            Bundle customTargetingBundle = new Bundle();
            customTargetingBundle.putString("userGroup", lastPAM.key);
            Log.e("ADSFALL", "banner user group::" + lastPAM.key);
            extras.putBundle("admob_custom_keyvals", customTargetingBundle);
        }
        adRequestBuilder.addNetworkExtrasBundle(AdMobAdapter.class, extras);
        this.mAdview.loadAd(adRequestBuilder.build());
    }

    @Override
    public String getPlacementId() {
        return ((GridParams) getGridParams()).placement;
    }

    public boolean isAdaptive() {
        return ((GridParams) getGridParams()).adaptive;
    }

    public String getSize() {
        return ((GridParams) getGridParams()).size;
    }

    public View getView() {
        return this.mAdview;
    }

    @Override
    public String getMediation() {
        return mediation;
    }

    private boolean isHeightLowerThan720DpAngHigherThan400Dp(Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        float dpHeight = ((float) displayMetrics.heightPixels) / displayMetrics.density;
        Logger.debug(TAG, "screen height in dp = %s", Float.valueOf(dpHeight));
        return dpHeight < 720.0f && dpHeight > 400.0f;
    }


    private AdSize getAdSize(Activity activity) {
        // Step 2 - Determine the screen width (less decorations) to use for the ad width.
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        // Step 3 - Get adaptive ad size and return for setting on the ad view.
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    @Override
    public int getWidth() {
        // Determine the screen width (less decorations) to use for the ad width.
        if (isAdaptive() && this.mAdSize != null) {
            return this.mAdSize.getWidth();
        }

        return BannerAdapter.SMART_BANNER_WIDTH;
    }

    @Override
    public int getHeight() {
        if (isAdaptive() && this.mAdSize != null) {
            return this.mAdSize.getHeight();
        }
        return STANDARD_BANNER_HEIGHT;
    }

    protected GridParams newGridParams() {
        return new GridParams();
    }

    public static class GridParams extends BaseAdapter.GridParams {
        public String placement;
        public boolean adaptive = true;
        public String size = null;
        public String collapsible = null;

        public GridParams fromJSON(JSONObject jsonObject) {
            try {
                if (IvySdk.getRemoteConfigAsBoolean("is_pam_banner")) {
                    this.placement = IvySdk.getRemoteConfigAsString("PAM_ad_unit_android_banner");
                    if (this.placement == null || this.placement.isEmpty()) {
                        throw new IllegalArgumentException("get remote config banner ad unit id failed");
                    }
                } else {
                    Logger.debug("pam banner remote config set to default");
                    this.placement = jsonObject.optString("placement");
                }
            } catch (Exception e) {
                Logger.debug(e.getMessage());
                this.placement = jsonObject.optString("placement");
            }

            this.adaptive = jsonObject.optBoolean("adaptive", true);
            this.size = jsonObject.optString("size", null);
            this.collapsible = jsonObject.optString("collapsible", "none");
            return this;
        }

        protected String getParams() {
            return "placement=" + this.placement;
        }
    }
}
