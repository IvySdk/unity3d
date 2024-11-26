package com.ivy.ads.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.appsflyer.AdRevenueScheme;
import com.appsflyer.MediationNetwork;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ivy.IvySdk;
import com.ivy.ads.events.EventID;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.util.Logger;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdmobSplashBannerAdapter {

    private static final String TAG = "Admob-Banner";
    private AdView mAdview;

    private String placement;
    private EventTracker eventTracker;

    private String mediation;

    private FrameLayout mBannerContainer;

    private boolean hadClosed = false;

    public AdmobSplashBannerAdapter(EventTracker eventLogger, String placement) {
        this.eventTracker = eventLogger;
        this.placement = placement;
        fetch(IvySdk.getActivity());
    }

    public void show(FrameLayout mBannerContainer) {
        this.mBannerContainer = mBannerContainer;
        if (hadClosed){
            fetch(IvySdk.getActivity());
        }
        if (mBannerContainer != null && mAdview != null) {
            mBannerContainer.addView(mAdview);
        }
    }

    public void close() {
        try {
            if (mAdview != null) {
                if (mAdview.getParent() != null) {
                    ((ViewGroup) mAdview.getParent()).removeAllViews();
                }
                mAdview.destroy();
                mAdview = null;
            }
        } catch (Exception e) {
        }finally {
            hadClosed = true;
        }
        if (mBannerContainer != null) {
            mBannerContainer.setVisibility(View.GONE);
        }
//        try {
//            if (mBannerContainer != null && mBannerContainer.getParent() != null) {
//                ((ViewGroup) mBannerContainer.getParent()).removeAllViews();
//            }
//        } catch (Exception e) {
//        }
    }

    private void fetch(Activity activity) {
        try {
            if (this.mAdview != null) {
                this.mAdview.destroy();
                this.mAdview = null;
            }

            if (placement == null || "".equals(placement)) {
                Logger.error("splash banner id can't be null");

                return;
            }
            this.mAdview = new AdView(activity);
            this.mAdview.setAdUnitId(placement);

            AdSize mAdSize = getAdSize(activity);
            this.mAdview.setAdSize(mAdSize);
            this.mAdview.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    Logger.debug(TAG, "onAdClosed()");
//                AdmobSplashBannerAdapter.this.onAdClosed(false);
                }

                @Override
                public void onAdClicked() {
                    Logger.debug(TAG, "onAdClicked()");
                    AdmobSplashBannerAdapter.this.onAdClickCalled();
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    int errorCode = loadAdError.getCode();
                    Logger.debug(TAG, "errorCode: %s", errorCode);
//                String reason = AdmobManager.errorCodeToMessage(errorCode);
//                AdmobSplashBannerAdapter.this.onAdLoadFailed(reason);
                }

                @Override
                public void onAdOpened() {
                    Logger.debug(TAG, "onAdOpened()");
                }

                @Override
                public void onAdLoaded() {
                    Logger.debug(TAG, "onAdLoaded()");
                    try {
                        mediation = AdmobSplashBannerAdapter.this.mAdview.getResponseInfo().getLoadedAdapterResponseInfo().getAdSourceName();
                    } catch (Exception e) {
                        AdmobSplashBannerAdapter.this.mediation = null;
                    }
//                AdmobSplashBannerAdapter.this.onAdLoadSuccess();
                }

                @Override
                public void onAdImpression() {
                    Logger.debug(TAG, "onAdImpression()");
                    AdmobSplashBannerAdapter.this.onAdShowSuccessCalled();
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

                        onGmsPaidEvent("admob", "splashbanner", "banner", placement, currencyCode, precisionType, valueMicros);
                    } catch (Throwable t) {
                        Logger.error(TAG, "onPaidEvent exception", t);
                    }
                }
            });

            AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
            this.mAdview.loadAd(adRequestBuilder.build());
        } catch (Exception e) {
        }
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

    public void onGmsPaidEvent(String ad_network, String ad_format, String placement, String adunit, String currencyCode, int precisionType, long valueMacros) {
        if (this.eventTracker == null) {
            return;
        }
        try {
            double revenue = valueMacros / 1000000.0f;
            if (revenue >= 10.0 || revenue <= 0) {
                return;
            }
            // log this single impresssion
            Bundle bundle = new Bundle();
            bundle.putString("ad_network", ad_network);
            bundle.putString("ad_format", ad_format);
            bundle.putString("placement", placement);
            bundle.putString("adunit", adunit);
            bundle.putDouble(FirebaseAnalytics.Param.VALUE, revenue);
            bundle.putString(FirebaseAnalytics.Param.CURRENCY, currencyCode);
            bundle.putInt("precision", precisionType);
            if (mediation != null) {
                bundle.putString("mediation", mediation);
            }
//            Bundle devices = DeviceUtil.getInstance().getData();
//            if (devices != null){
//                bundle.putAll(devices);
//            }
            eventTracker.logToFirebase(EventID.GMS_AD_IMPRESSION_PING, bundle);
            eventTracker.parfkaLog(EventID.GMS_AD_IMPRESSION_PING, bundle);
//            eventTracker.afAdImpressionPing(bundle, revenue);

            Map<String, Object> afParams = new HashMap<>();
            afParams.put(AdRevenueScheme.COUNTRY, Locale.getDefault().getCountry());
            afParams.put(AdRevenueScheme.AD_UNIT, adunit);
            afParams.put(AdRevenueScheme.AD_TYPE, ad_format);
            afParams.put(AdRevenueScheme.PLACEMENT, ad_format);
            if (mediation == null) {
                mediation = ad_network;
            }
            eventTracker.afAdImpressionPing(mediation.toLowerCase(), MediationNetwork.GOOGLE_ADMOB, currencyCode, revenue, afParams);

        } catch (Throwable t) {
            // ignore
        }
    }

    public void onAdShowSuccessCalled() {
        String eventName = "splash_banner_show";//EventID.BANNER_DISPLAYED;
        Bundle bundle = new Bundle();
        if (mediation != null) {
            bundle.putString("mediation", mediation);
        }
        bundle.putString("ad_network", "admob");
        bundle.putString("ad_format", "splashbanner");
        bundle.putString("placement", placement);
        bundle.putString("adunit", placement);
        eventTracker.logEvent(eventName, bundle);
    }

    public void onAdClickCalled() {
        String eventName = "splash_banner_click";//EventID.BANNER_CLICKED;
        Bundle bundle = new Bundle();
        if (mediation != null) {
            bundle.putString("mediation", mediation);
        }
        bundle.putString("ad_network", "admob");
        bundle.putString("ad_format", "splashbanner");
        bundle.putString("placement", placement);
        bundle.putString("adunit", placement);
        eventTracker.logEvent(eventName, bundle);
    }


}
