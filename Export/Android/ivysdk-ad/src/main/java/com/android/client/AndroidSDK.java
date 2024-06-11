package com.android.client;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.ivy.IvySdk;
import com.ivy.Logger;
import com.ivy.ads.interfaces.IvyAdCallbacks;
import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.event.CommonEvents;
import com.ivy.event.EventBus;
import com.ivy.event.EventListener;

import java.util.Map;

public class AndroidSDK {


    public static class Builder {

        IInitResultListener initResultListener;
        IGMSPaidEventListener gmsPaidEventListener;
        AdEventListener adEventListener;
        AdLoadedListener adLoadedListener;

        public Builder setInitResultListener(IInitResultListener initResultListener) {
            this.initResultListener = initResultListener;
            return this;
        }

        public Builder setGmsPaidEventListener(IGMSPaidEventListener gmsPaidEventListener) {
            this.gmsPaidEventListener = gmsPaidEventListener;
            return this;
        }

        public Builder setAdEventListener(AdEventListener adEventListener) {
            this.adEventListener = adEventListener;
            return this;
        }

        public Builder setAdLoadedListener(AdLoadedListener adLoadedListener) {
            this.adLoadedListener = adLoadedListener;
            return this;
        }
    }

    public static Builder builder;
    private static boolean sdkInitialized = false;

    public synchronized static void onCreate(Activity activity, Builder builder) {
        if (sdkInitialized){
            Logger.warn("already initialized !!!");
            return;
        }
        if (activity == null) {
            Logger.error("Pass NULL activity!!!");
            return;
        }
        if (builder == null) {
            Logger.error("Pass NULL builder, all events will be not received.");
            return;
        }
        sdkInitialized = true;
        AndroidSDK.builder = builder;
        // 这里交由外部初始化
//        try {
//            FirebaseApp.initializeApp(activity.getApplication());
//        } catch (Exception e){}
        IvySdk.initialize(activity, null, new IvySdk.InitializeCallback() {
            @Override
            public void onInitialized() {

            }
        });

        EventBus.getInstance().addListener(CommonEvents.AD_LOADED, new EventListener() {
            @Override
            public void onEvent(int i, Object obj) {
                if (i == CommonEvents.AD_LOADED) {
                    if (obj != null && obj instanceof Integer) {
                        int adType = (Integer) obj;
                        if (builder != null && builder.adLoadedListener != null) {
                            builder.adLoadedListener.onAdLoaded(adType);
                        }
                    }
                }
            }
        });
        try {
            builder.initResultListener.onInitialized();
        } catch (Throwable t) {
            Logger.error("sdk onInit exception::"+ t);
        }

    }

    public static boolean hasBanner(String tag) {
        return IvySdk.haveBanner();
    }

    public static boolean hasFull(String tag) {
        try {
            boolean got = IvySdk.haveInterstitial();
            if (!got) {
                Logger.debug( "No full, try to fetch one");
                IvySdk.fetchInterstitial();
            }
            return IvySdk.haveInterstitial();
        } catch (Throwable t) {
            //
            return false;
        }
    }

    private static long lastCheckRewardInterstitialTime = 0L;

    public static boolean hasRewardedInterstitial(String tag) {
        try {
            boolean got = IvySdk.haveRewardedInterstitial();
            if (!got) {
                if (System.currentTimeMillis() - lastCheckRewardInterstitialTime > 30000) {
                    Logger.debug("No reward Interstitial, auto fetch");
                    IvySdk.fetchRewardedInterstitial();
                    lastCheckRewardInterstitialTime = System.currentTimeMillis();
                }
            }
            return IvySdk.haveRewardedInterstitial();
        } catch (Throwable t) {
            return false;
        }
    }

    public static void showBanner(String tag, int pos) {
        try {
            IvySdk.showBannerAd(pos);
        } catch (Exception ex) {
            //
        }
    }

    public static void showBanner(String tag, int pos, int animate) {
        Logger.debug( "showBanner(tag, pos, animate)");
        try {
            IvySdk.showBannerAd(pos);
        } catch (Exception ex) {
            //
        }
    }

    public static void showBannerNonExistent(String tag, int pos) {
        try {
            IvySdk.showBannerNonExistent(pos);
        } catch (Exception ex) {
            //
        }
    }

    public static void showBannerNonExistent(String tag, int pos, int animate) {
        Logger.debug("showBanner(tag, pos, animate)");
        try {
            IvySdk.showBannerNonExistent(pos);
        } catch (Exception ex) {
            //
        }
    }

    public static void closeBanner(String tag) {
        Logger.debug( "closeBanner(tag)");
        try {
            IvySdk.closeBanners();
        } catch (Throwable t) {
            //
        }
    }

    public static void showFullAd(String tag) {
        try {
            showFullAd(tag, new AdListener());
        } catch (Exception ex) {
            //
        }
    }

    public static void showRewardedInterstitial(String tag, final AdListener listener) {
        Logger.debug( "showRewardedInterstitial");
        try {
            IvySdk.setAdCallback(IvyAdType.REWARDED_INTERSTITIAL, new IvyAdCallbacks() {
                @Override
                public void onAdClicked(IvyAdInfo adInfo) {
                    Logger.debug( "onAdClicked");
                    if (listener != null) {
                        listener.onAdClicked();
                    }
                }

                @Override
                public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                    Logger.debug( "onAdClosed, gotReward: " + gotReward);
                    if (listener != null) {
                        if (gotReward) {
                            listener.onAdReward(false);
                        }
                        listener.onAdClosed();
                    }
                }

                @Override
                public void onAdLoadFail(IvyAdType adInfo) {
                    Logger.debug( "onAdLoadFail");

                    if (listener != null) {
                        listener.onAdLoadFails();
                    }
                }

                @Override
                public void onAdLoadSuccess(IvyAdInfo adInfo) {
                    Logger.debug( "onAdLoadSuccess");

                    if (listener != null) {
                        listener.onAdLoadSuccess();
                    }
                }

                @Override
                public void onAdShowFail(IvyAdType adInfo) {
                    Logger.debug( "onAdShowFail");

                    if (listener != null) {
                        listener.onAdShowFails();
                    }
                }

                @Override
                public void onAdShowSuccess(IvyAdInfo adInfo) {
                    Logger.debug( "onAdShowSuccess");
                    if (listener != null) {
                        listener.onAdShow();
                    }
                }
            });
            IvySdk.showRewardedInterstitial(tag);

        } catch (Throwable t) {
            // ignore
        }
    }

    public static void showFullAd(String tag, final AdListener listener) {
        Logger.debug( "showFullAd called");
        try {
            IvySdk.setAdCallback(IvyAdType.INTERSTITIAL, new IvyAdCallbacks() {
                @Override
                public void onAdClicked(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdClicked();
                    }
                }

                @Override
                public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                    if (listener != null) {
                        listener.onAdClosed();
                    }
                }

                @Override
                public void onAdLoadFail(IvyAdType adInfo) {
                    if (listener != null) {
                        listener.onAdLoadFails();
                    }
                }

                @Override
                public void onAdLoadSuccess(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdLoadSuccess();
                    }
                }

                @Override
                public void onAdShowFail(IvyAdType adInfo) {
                    if (listener != null) {
                        listener.onAdShowFails();
                    }
                }

                @Override
                public void onAdShowSuccess(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdShow();
                    }
                }
            });
            IvySdk.showInterstitialAd(tag);
        } catch (Throwable t) {
            //
        }
    }

    public static void loadFullAd(String tag, AdListener listener) {
        try {
            if (listener != null) {
                IvySdk.setAdCallback(IvyAdType.INTERSTITIAL, new IvyAdCallbacks() {
                    @Override
                    public void onAdClicked(IvyAdInfo adInfo) {
                        if (listener != null) {
                            listener.onAdClicked();
                        }
                    }

                    @Override
                    public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                        if (listener != null) {
                            listener.onAdClosed();
                        }
                    }

                    @Override
                    public void onAdLoadFail(IvyAdType adInfo) {
                        if (listener != null) {
                            listener.onAdLoadFails();
                        }
                    }

                    @Override
                    public void onAdLoadSuccess(IvyAdInfo adInfo) {
                        if (listener != null) {
                            listener.onAdLoadSuccess();
                        }
                    }

                    @Override
                    public void onAdShowFail(IvyAdType adInfo) {
                        if (listener != null) {
                            listener.onAdShowFails();
                        }
                    }

                    @Override
                    public void onAdShowSuccess(IvyAdInfo adInfo) {
                        if (listener != null) {
                            listener.onAdShow();
                        }
                    }
                });
            }
            IvySdk.fetchInterstitial();
        } catch (Throwable t) {
            //
        }
    }

    public static void showRewardAd(String tag, AdListener listener) {
        try {
            IvySdk.setAdCallback(IvyAdType.REWARDED, new IvyAdCallbacks() {
                @Override
                public void onAdClicked(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdClicked();
                    }
                }

                @Override
                public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                    if (listener != null) {
                        listener.onAdReward(!gotReward);
                        listener.onAdClosed();
                    }
                }

                @Override
                public void onAdLoadFail(IvyAdType adInfo) {
                    if (listener != null) {
                        listener.onAdLoadFails();
                    }
                }

                @Override
                public void onAdLoadSuccess(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdLoadSuccess();
                    }
                }

                @Override
                public void onAdShowFail(IvyAdType adInfo) {
                    if (listener != null) {
                        listener.onAdShowFails();
                    }
                }

                @Override
                public void onAdShowSuccess(IvyAdInfo adInfo) {
                    if (listener != null) {
                        listener.onAdShow();
                    }
                }
            });
            IvySdk.showRewardAd(tag);
        } catch (Throwable t) {
            //
        }
    }

    private static long lastTriggerAutoFetchTime = 0;

    public static boolean hasRewardAd(String tag) {
        try {
            boolean hasReward = IvySdk.haveRewardAd();
            if (!hasReward) {
                if (System.currentTimeMillis() - lastTriggerAutoFetchTime > 30000) {
                    Logger.debug( "No reward, we trigger to fetch");
                    IvySdk.fetchRewardVideo();
                    lastTriggerAutoFetchTime = System.currentTimeMillis();
                }
                return false;
            }
            hasReward = IvySdk.haveRewardAd();
            Logger.debug( "hasRewardAd() : " + hasReward);
            return hasReward;
        } catch (Throwable t) {
            //
            return false;
        }
    }

    public static boolean hasNativeAd(String tag) {
        boolean checkNative = IvySdk.haveNative();
        Logger.debug("hasNativeAd, tag: " + tag + " -> " + checkNative);
        return checkNative;
    }

    public static boolean hasNative(String tag) {
        boolean checkNative = IvySdk.haveNative();
        Logger.debug( "hasNativeAd, tag: " + tag + " -> " + checkNative);
        return checkNative;
    }

    public static void closeNativeAd(String tag) {
        IvySdk.closeNativeAd();
    }

    public static void onGMSPaid(Map<String, Object> map) {
        if (builder != null && builder.gmsPaidEventListener != null) {
            builder.gmsPaidEventListener.onGMSPaid(map);
        }
    }

}
