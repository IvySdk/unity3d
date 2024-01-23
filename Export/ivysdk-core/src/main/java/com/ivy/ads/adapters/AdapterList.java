package com.ivy.ads.adapters;

import android.content.Context;
import android.util.Log;

import com.ivy.IvySdk;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.networks.grid.GridManager;
import com.ivy.util.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public final class AdapterList {
    private static String getNetworkByProvider(String provider) {
        if (provider == null) {
            return null;
        }
        if (!provider.contains("_")) {
            return provider;
        }
        String[] data = provider.split("_");
        if (data.length > 1) {
            return data[0];
        }
        return null;
    }

    /**
     * @param context
     * @return
     */
    public static Set<BaseAdapter> getAdapters(Context context) {
        Set<BaseAdapter> registeredProviders = new HashSet<>();
        try {
            JSONObject gridData = GridManager.getGridData();
            // Log.e("AppLovin", "grid data:" + gridData.toString());
            //1. initialize banners
            if (gridData.has("banner")) {
                JSONArray ads = gridData.optJSONArray("banner");
                int size = ads.length();
                for (int i = 0; i < size; i++) {
                    JSONObject adapterSetting = ads.optJSONObject(i);
                    JSONObject placementSettings = adapterSetting.optJSONObject("p");
                    String gridName = adapterSetting.optString("provider");

                    String network = null;
                    if (!placementSettings.has("network")) {
                        network = getNetworkByProvider(gridName);
                    } else {
                        network = placementSettings.optString("network");
                    }
                    if (network == null) {
                        Logger.error("ADSFALL", "No network found for provider: " + gridName);
                        continue;
                    }
                    Log.e("AppLovin", "network:" + network);
                    if (AdNetworkName.ADMOB.equals(network)) {
                        registeredProviders.add(new AdmobBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.ADMOB));
                    } else if (AdNetworkName.ADX.equals(network)) {
                        registeredProviders.add(new AdxBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.ADX));
                    } else if (AdNetworkName.APPLOVIN.equals(network)) {
                        registeredProviders.add(new ApplovinBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.APPLOVIN));
                    } else if (AdNetworkName.UNITY.equals(network)) {
                    } else if (AdNetworkName.IRONSOURCE.equals(network)) {
//            registeredProviders.add(new IronsourceBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.IRONSOURCE));
                    } else if (AdNetworkName.YANDEX.equals(network)) {
                        registeredProviders.add(new YandexBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.YANDEX));
                    } else if (AdNetworkName.ADCOLONY.equals(network)) {
                        // registeredProviders.add(new AdcolonyBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.ADCOLONY));
                    } else if (AdNetworkName.CHARTBOOST.equals(network)) {
//            registeredProviders.add(new ChartboostBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.CHARTBOOST));
                    } else if (AdNetworkName.MOPUB.equals(network)) {
//            registeredProviders.add(new MoPubBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.MOPUB));
                    } else if (AdNetworkName.SMAATO.equals(network)) {
//             registeredProviders.add(new SmaatoBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.SMAATO));
                    } else if (AdNetworkName.FYBER.equals(network)) {
//            registeredProviders.add(new FyberBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.FYBER));
                    } else if (AdNetworkName.APS.equals(network)) {
                        //            registeredProviders.add(new ApsBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.APS));
                    } else if (AdNetworkName.TOUTIAO.equals(network)) {
//             registeredProviders.add(new TTBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.TOUTIAO));
                    } else if (AdNetworkName.APPLOVIN_MAX.equals(network)) {
                        // registeredProviders.add(new ApplovinMaxBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.APPLOVIN_MAX));
                    } else if (AdNetworkName.MYTARGET.equals(network)) {
                 //       registeredProviders.add(new MytargetBannerAdapter(context, gridName, IvyAdType.BANNER).setNetworkName(AdNetworkName.MYTARGET));
                    } else {
                        Log.e("ADSFALL", "No banner adapter for network " + network);
                    }
                }
            }

            // 2. initialize interstitial
            if (gridData.has("full")) {
                JSONArray ads = gridData.optJSONArray("full");
                int size = ads.length();
                for (int i = 0; i < size; i++) {
                    JSONObject adapterSetting = ads.optJSONObject(i);

                    JSONObject placementSettings = adapterSetting.optJSONObject("p");
                    String gridName = adapterSetting.optString("provider");
                    String network = null;
                    if (!placementSettings.has("network")) {
                        network = getNetworkByProvider(gridName);
                    } else {
                        network = placementSettings.optString("network");
                    }
                    if (network == null) {
                        Logger.error("ADSFALL", "No network found for provider: " + gridName);
                        continue;
                    }

                    if (AdNetworkName.ADMOB.equals(network)) {
                        registeredProviders.add(new AdmobNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.ADMOB));
                    } else if (AdNetworkName.ADX.equals(network)) {
                        registeredProviders.add(new AdxNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.ADX));
                    } else if (AdNetworkName.FACEBOOK.equals(network)) {
//            registeredProviders.add(new FacebookNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.FACEBOOK));
                    } else if (AdNetworkName.APPLOVIN.equals(network)) {
                        registeredProviders.add(new ApplovinNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.APPLOVIN));
                    } else if (AdNetworkName.UNITY.equals(network)) {
//            registeredProviders.add(new UnityNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.UNITY));
                    } else if (AdNetworkName.IRONSOURCE.equals(network)) {
//            registeredProviders.add(new IronsourceNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.IRONSOURCE));
                    } else if (AdNetworkName.YANDEX.equals(network)) {
                        registeredProviders.add(new YandexNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.YANDEX));
                    } else if (AdNetworkName.VUNGLE.equals(network)) {
//             registeredProviders.add(new VungleNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.VUNGLE));
                    } else if (AdNetworkName.ADCOLONY.equals(network)) {
//             registeredProviders.add(new AdcolonyNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.ADCOLONY));
                    } else if (AdNetworkName.CHARTBOOST.equals(network)) {
//            registeredProviders.add(new ChartboostNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.CHARTBOOST));
                    } else if (AdNetworkName.MYTARGET.equals(network)) {
             //           registeredProviders.add(new MytargetNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.MYTARGET));
                    } else if (AdNetworkName.MOPUB.equals(network)) {
//            registeredProviders.add(new MoPubNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.MOPUB));
                    } else if (AdNetworkName.SMAATO.equals(network)) {
//             registeredProviders.add(new SmaatoNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.SMAATO));
                    } else if (AdNetworkName.FYBER.equals(network)) {
//            registeredProviders.add(new FyberNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.FYBER));
                    } else if (AdNetworkName.APS.equals(network)) {
//             registeredProviders.add(new ApsNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.APS));
                    } else if (AdNetworkName.FACEBOOK_BID.equals(network)) {
//             registeredProviders.add(new FacebookBidderNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.FACEBOOK_BID));
                    } else if (AdNetworkName.TOUTIAO.equals(network)) {
//             registeredProviders.add(new TTNonRewardedVideoAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.TOUTIAO));
                    } else if (AdNetworkName.APPLOVIN_MAX.equals(network)) {
                        //    registeredProviders.add(new ApplovinMaxNonRewardedAdapter(context, gridName, IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.APPLOVIN_MAX));
                    } else {
                        Log.e("ADSFALL", "No interstitial adapter for network " + network);
                    }
                }
            }

            // 3. video
            if (gridData.has("video")) {
                JSONArray ads = gridData.optJSONArray("video");
                int size = ads.length();
                for (int i = 0; i < size; i++) {
                    JSONObject adapterSetting = ads.optJSONObject(i);
                    String gridName = adapterSetting.optString("provider");
                    JSONObject placementSettings = adapterSetting.optJSONObject("p");
                    String network = null;
                    if (!placementSettings.has("network")) {
                        network = getNetworkByProvider(gridName);
                    } else {
                        network = placementSettings.optString("network");
                    }
                    if (network == null) {
                        Logger.error("ADSFALL", "No network found for provider: " + gridName);
                        continue;
                    }
                    if (AdNetworkName.ADMOB.equals(network)) {
                        registeredProviders.add(new AdmobRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.ADMOB));
                    } else if (AdNetworkName.ADX.equals(network)) {
                        registeredProviders.add(new AdxRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.ADX));
                    } else if (AdNetworkName.FACEBOOK.equals(network)) {
//            registeredProviders.add(new FacebookRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.FACEBOOK));
                    } else if (AdNetworkName.APPLOVIN.equals(network)) {
                        registeredProviders.add(new ApplovinRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.APPLOVIN));
                    } else if (AdNetworkName.UNITY.equals(network)) {
//            registeredProviders.add(new UnityRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.UNITY));
                    } else if (AdNetworkName.IRONSOURCE.equals(network)) {
//            registeredProviders.add(new IronsourceRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.IRONSOURCE));
                    } else if (AdNetworkName.YANDEX.equals(network)) {
                        registeredProviders.add(new YandexRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.YANDEX));
                    } else if (AdNetworkName.VUNGLE.equals(network)) {
//             registeredProviders.add(new VungleRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.VUNGLE));
                    } else if (AdNetworkName.ADCOLONY.equals(network)) {
//             registeredProviders.add(new AdcolonyRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.ADCOLONY));
                    } else if (AdNetworkName.CHARTBOOST.equals(network)) {
//            registeredProviders.add(new ChartboostRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.CHARTBOOST));
                    } else if (AdNetworkName.MYTARGET.equals(network)) {
                  //      registeredProviders.add(new MytargetRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.MYTARGET));
                    } else if (AdNetworkName.MOPUB.equals(network)) {
//            registeredProviders.add(new MoPubRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.MOPUB));
                    } else if (AdNetworkName.SMAATO.equals(network)) {
//             registeredProviders.add(new SmaatoRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.SMAATO));
                    } else if (AdNetworkName.FYBER.equals(network)) {
//            registeredProviders.add(new FyberRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.FYBER));
                    } else if (AdNetworkName.FACEBOOK_BID.equals(network)) {
//             registeredProviders.add(new FacebookBidderRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.FACEBOOK_BID));
                    } else if (AdNetworkName.TOUTIAO.equals(network)) {
//             registeredProviders.add(new TTRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.TOUTIAO));
                    } else if (AdNetworkName.APPLOVIN_MAX.equals(network)) {
                        //  registeredProviders.add(new ApplovinMaxRewardedAdapter(context, gridName, IvyAdType.REWARDED).setNetworkName(AdNetworkName.APPLOVIN_MAX));
                    } else {
                        Log.e("ADSFALL", "No video adapter for network " + network);
                    }
                }
            }

            // reward interstitial
            if (gridData.has("reward_interstitial")) {
                JSONArray ads = gridData.optJSONArray("reward_interstitial");
                int size = ads.length();
                for (int i = 0; i < size; i++) {
                    JSONObject adapterSetting = ads.optJSONObject(i);
                    String gridName = adapterSetting.optString("provider");
                    JSONObject placementSettings = adapterSetting.optJSONObject("p");
                    String network = null;
                    if (!placementSettings.has("network")) {
                        network = getNetworkByProvider(gridName);
                    } else {
                        network = placementSettings.optString("network");
                    }
                    if (network == null) {
                        Logger.error("ADSFALL", "No network found for provider: " + gridName);
                        continue;
                    }
                    if (AdNetworkName.ADMOB.equals(network)) {
                        registeredProviders.add(new AdmobRewardedInterstitialAdapter(context, gridName, IvyAdType.REWARDED_INTERSTITIAL).setNetworkName(AdNetworkName.ADMOB));
                    } else {
                        Log.e("ADSFALL", "No reward interstitial adapter for network " + network);
                    }
                }
            }

            // 3. native
            if (gridData.has("native")) {
                JSONArray ads = gridData.optJSONArray("native");
                int size = ads.length();
                for (int i = 0; i < size; i++) {
                    JSONObject adapterSetting = ads.optJSONObject(i);
                    String gridName = adapterSetting.optString("provider");
                    JSONObject placementSettings = adapterSetting.optJSONObject("p");
                    String network = null;
                    if (!placementSettings.has("network")) {
                        network = getNetworkByProvider(gridName);
                    } else {
                        network = placementSettings.optString("network");
                    }
                    if (network == null) {
                        Logger.error("ADSFALL", "No network found for provider: " + gridName);
                        continue;
                    }
                    if (AdNetworkName.ADMOB.equals(network)) {
                        registeredProviders.add(new AdmobNativeAdapter(context, gridName, IvyAdType.NATIVE_AD).setNetworkName(AdNetworkName.ADMOB));
                    } else if (AdNetworkName.ADX.equals(network)) {
//            registeredProviders.add(new AdxNativeAdapter(context, gridName, IvyAdType.NATIVE_AD).setNetworkName(AdNetworkName.ADX));
                    } else if (AdNetworkName.FACEBOOK.equals(network)) {
//            registeredProviders.add(new FacebookNativeAdapter(context, gridName, IvyAdType.NATIVE_AD).setNetworkName(AdNetworkName.FACEBOOK));
                    }
                }
            }

            // always register the ivy adapters
            registerAdsfallAdapters(context, registeredProviders);

        } catch (Exception ex) {
            IvySdk.debugToast(ex.getMessage());
            Logger.error("ADSFALL", "wrong adapter settings", ex);
        }
        return registeredProviders;
    }

    private static void registerAdsfallAdapters(Context context, Set<BaseAdapter> registeredProviders) {
//        registeredProviders.add(new AdsfallBannerAdapter(context, "adsfall", IvyAdType.BANNER).setNetworkName(AdNetworkName.ADSFALL));
//        registeredProviders.add(new AdsfallNativeAdapter(context, "adsfall", IvyAdType.NATIVE_AD).setNetworkName(AdNetworkName.ADSFALL));
//        registeredProviders.add(new AdsfallNonRewardedAdapter(context, "adsfall", IvyAdType.INTERSTITIAL).setNetworkName(AdNetworkName.ADSFALL));
    }
}
