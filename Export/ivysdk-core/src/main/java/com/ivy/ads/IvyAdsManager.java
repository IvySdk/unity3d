package com.ivy.ads;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.adsfall.R;
import com.ivy.IvySdk;
import com.ivy.ads.events.EventID;
import com.ivy.ads.events.EventParams;
import com.ivy.ads.interfaces.IvyAdCallbacks;
import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyBannerAd;
import com.ivy.ads.interfaces.IvyFullpageAd;
import com.ivy.ads.interfaces.IvyNativeAd;
import com.ivy.ads.interfaces.IvyPromote;
import com.ivy.ads.interfaces.IvySoftCallbacks;
import com.ivy.ads.managers.CommonAdManager;
import com.ivy.ads.utils.HandlerFactory;
import com.ivy.event.CommonEvents;
import com.ivy.event.EventBus;
import com.ivy.networks.DownloadFeedback;
import com.ivy.networks.grid.GridManager;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.util.CommonUtil;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IvyAdsManager implements IvyAdCallbacks, IvySoftCallbacks {
    private static final String TAG = IvyAdsManager.class.getCanonicalName();
    private FrameLayout mBannerContainer;

    private IvyBannerAd mBannerAds;
    private IvyFullpageAd mRewardedInterstitialAds;
    private IvyFullpageAd mInterstitialAds;
    private IvyNativeAd mNativeAds;
    private IvyFullpageAd mRewardedAds;
    private IvyPromote mPromote;

    private Activity main;

    private Map<IvyAdType, IvyAdCallbacks> adCallbacks = new HashMap<>();

    private boolean hasHomeAD = false;
    private static long lastHomeAdPauseTime = 0;
    private static long lastHomeAdShowTime = 0;

    private static int homeAdStaySecs = 10;
    private static int minHomeAdShowDurationSecs = 10;

    private boolean callShowBannerAd = false;

    private volatile boolean willDisplayingAd = false;

    public void onCreate(Activity main, EventTracker eventLogger, GridManager gridManager) {
        this.main = main;

        IvyAds.init(main, eventLogger, gridManager);

        this.mInterstitialAds = IvyAds.getInterstitials();
        this.mRewardedAds = IvyAds.getRewardedVideos();
        this.mBannerAds = IvyAds.getBanners();
        this.mNativeAds = IvyAds.getNativeAds();
        this.mPromote = IvyAds.getPromote();
        this.mRewardedInterstitialAds = IvyAds.getRewardedInterstitials();

        this.mInterstitialAds.setCallback(this);
        this.mRewardedAds.setCallback(this);
        this.mRewardedInterstitialAds.setCallback(this);
        this.mBannerAds.setCallback(this);
        this.mNativeAds.setCallback(this);

        this.mInterstitialAds.setSoftCallback(this);
        this.mRewardedAds.setSoftCallback(this);
        this.mRewardedInterstitialAds.setSoftCallback(this);


        this.hasHomeAD = GridManager.getGridData().has("homeAd");
        if (hasHomeAD) {
            Logger.debug(TAG, "Home Ad Enabled");
            JSONObject homeAdSettings = GridManager.getGridData().optJSONObject("homeAd");
            if (homeAdSettings != null) {
                homeAdStaySecs = homeAdSettings.optInt("backgroundStayTime", 10);
                minHomeAdShowDurationSecs = homeAdSettings.optInt("minShowDuration", 10);
            }
        }
    }

    public void onPause(Activity activity) {
        IvyAds.onPause(activity);

        if (!willDisplayingAd) {
            lastHomeAdPauseTime = System.currentTimeMillis();
        } else {
            lastHomeAdPauseTime = 0L;
        }
    }

    public void onResume(Activity activity) {
        IvyAds.onResume(activity);

        if (hasHomeAD) {
            if (System.currentTimeMillis() - lastHomeAdShowTime < minHomeAdShowDurationSecs * 1000) {
                return;
            }

            if (lastHomeAdPauseTime != 0 && System.currentTimeMillis() - lastHomeAdPauseTime > homeAdStaySecs * 1000) {
                setAdCallback(IvyAdType.INTERSTITIAL, new IvyAdCallbacks() {
                    @Override
                    public void onAdClicked(IvyAdInfo adInfo) {
                    }

                    @Override
                    public void onAdClosed(IvyAdInfo adInfo, boolean gotReward) {
                        lastHomeAdShowTime = System.currentTimeMillis();
                    }

                    @Override
                    public void onAdLoadFail(IvyAdType adInfo) {
                    }

                    @Override
                    public void onAdLoadSuccess(IvyAdInfo adInfo) {
                    }

                    @Override
                    public void onAdShowFail(IvyAdType adInfo) {
                    }

                    @Override
                    public void onAdShowSuccess(IvyAdInfo adInfo) {
                    }
                });
                showInterstitial(main, "homead");
            }
        }
    }

    public void onDestroy(Activity activity) {
        IvyAds.onDestroy(activity);
    }

    public void showBanners(Activity activity, FrameLayout container) {
        IvySdk.logEvent(EventID.CLICK_SHOW_BANNER, new Bundle());

        this.mBannerContainer = container;
        this.callShowBannerAd = true;
        this.mBannerAds.show(activity, container);
    }

    public void setAdCallback(IvyAdType adType, IvyAdCallbacks callback) {
        this.adCallbacks.put(adType, callback);
    }

    public void hideBanners() {
        Logger.debug(TAG, "Hide Banner");
        this.callShowBannerAd = false;
        if (this.mBannerAds != null) {
            this.mBannerAds.hide();
        }
    }

    public void disableBanners() {
        Logger.debug(TAG, "Disable Banner");

        hideBanners();
        if (this.mBannerContainer != null) {
            this.mBannerContainer.setVisibility(View.GONE);
        }
    }

    public void setBannerPosition(int position, Activity activity) {
        this.mBannerAds.setBannerPosition(position, activity);
    }

    public void fetchRewardedInterstitial(Activity activity) {
        this.mRewardedInterstitialAds.fetch(activity);
    }

    public void fetchInterstitial(Activity activity) {
        this.mInterstitialAds.fetch(activity);
    }

    public void showRewardedInterstitial(Activity activity, String tag) {
        try {
//      Bundle bundle = new Bundle();
//      bundle.putString(EventParams.PARAM_LABEL, tag);
//      IvySdk.logEvent(EventID.CLICK_SHOW_REWARD_INTERSTITIAL, bundle);

            if (this.mRewardedInterstitialAds.isLoaded()) {
                willDisplayingAd = true;
                this.mRewardedInterstitialAds.show(activity, tag);
            } else {
                this.fetchRewardedInterstitial(activity);
                Logger.debug(TAG, "Rewarded Interstitial not available. reload");
            }
        } catch (Throwable ex) {
            Logger.error(TAG, "showRewardedInterstitial exception");
        }
    }

    public void showInterstitial(Activity activity, String tag) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString(EventParams.PARAM_LABEL, tag);
            IvySdk.logEvent(EventID.CLICK_SHOW_INTERSTITIAL, bundle);

            if (this.mInterstitialAds.isLoaded()) {
                willDisplayingAd = true;
                this.mInterstitialAds.show(activity, tag);
            } else {

                fetchInterstitial(activity);

                Log.d(TAG, "mInterstitialAds not ready");
                int connectivity_type = CommonUtil.getConnectivityType(activity);

                bundle.putInt("connectivity", connectivity_type);
                boolean isLoading = this.mInterstitialAds.isLoading();
                if (isLoading) {
                    bundle.putString(EventParams.PARAM_LABEL, "1");
                }
                IvySdk.logEvent("ad_show_interstitial_skip", bundle);

                // use Ivy to full fill this ad
                boolean preFillInterstitial = GridManager.getGridData().optBoolean("preFillInterstitial", true);
                if (preFillInterstitial && mInterstitialAds instanceof CommonAdManager) {
                    willDisplayingAd = true;
                    ((CommonAdManager) mInterstitialAds).showAdsFallInterstitial(activity);
                }

            }
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }

    public boolean haveInterstitial() {
        return this.mInterstitialAds.isLoaded();
    }

    public boolean haveRewardedInterstitial() {
        return this.mRewardedInterstitialAds != null && this.mRewardedInterstitialAds.isLoaded();
    }

    public boolean haveNative() {
        return false;
//        if (this.mNativeAds == null) {
//            Logger.error(TAG, "Native ad not defined");
//            return false;
//        }
//
//        boolean result = this.mNativeAds.isLoaded();
//        if (result) {
//            Logger.debug(TAG, "Native ad already loaded");
//            return true;
//        }
//
//        JSONObject gridData = GridManager.getGridData();
//        if (gridData == null) {
//            return false;
//        }
//
//        boolean preFillNative = gridData.optBoolean("preFillNative", true);
//        if (!preFillNative) {
//            Logger.debug(TAG, "preFillNative: false");
//            return false;
//        }
//
//        if (mPromote == null || !(mPromote instanceof CommonAdManager)) {
//            Logger.error(TAG, "Invalid Promote");
//            return false;
//        }
//
//        PromoteConfig promoteConfig = ((CommonAdManager) mPromote).getPromiteConfig();
//        if (promoteConfig == null) {
//            Logger.error(TAG, "Invalid PromoteConfig");
//            return false;
//        }
//
//        JSONObject app = promoteConfig.selectOne(main, PromoteConfig.IMAGE_TYPE_BANNER, false);
//        if (app != null) {
//            Logger.error(TAG, "No banner app defined for native promote");
//
//            return true;
//        }
//        return false;
    }

    public void fetchRewarded(Activity activity) {
        Logger.debug(TAG, "Fetch Rewarded called");
        this.mRewardedAds.fetch(activity);
    }

    public boolean haveRewardAd() {
        if (this.mRewardedAds == null) {
            Log.e(TAG, "Reward ad module not loaded");
            return false;
        }
        return this.mRewardedAds.isLoaded();
    }

    private boolean displayRewardVideoWhenLoaded = false;

    public void showRewarded(final Activity activity, final String tag) {
        try {
            if (this.mRewardedAds == null) {
                Logger.error(TAG, "Reward ad module not loaded, showRewarded is impossible");
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(EventParams.PARAM_LABEL, tag);
            IvySdk.logEvent(EventID.CLICK_SHOW_REWARDED, bundle);
            if (this.mRewardedAds.isLoaded()) {
                willDisplayingAd = true;
                this.mRewardedAds.show(activity, tag);
            } else {

                boolean autoDisplayRewarded = GridManager.getGridData().optBoolean("auto_display_rewarded", false);
                if (autoDisplayRewarded) {
                    showLoadingDialog(IvyAdType.REWARDED);
                    displayRewardVideoWhenLoaded = true;
                }
                this.fetchRewarded(activity);

                Log.d(TAG, "Reward ad not ready");
                int connectivity_type = CommonUtil.getConnectivityType(activity);
                bundle.putInt("connectivity", connectivity_type);
                boolean isLoading = this.mRewardedAds.isLoading();
                if (isLoading) {
                    bundle.putString("label", "1");
                }
                IvySdk.logEvent("ad_show_video_skip", bundle);

            }
        } catch (Exception ex) {
            // ignore
        }
    }

    public void onAdShowSuccess(IvyAdInfo adInfo) {
        Logger.debug(TAG, "onAdShowSuccess %s, %s", adInfo.getAdType(), adInfo.getAdapter().getName());
        if (this.adCallbacks.containsKey(adInfo.getAdType())) {
            this.adCallbacks.get(adInfo.getAdType()).onAdShowSuccess(adInfo);
        }
//    try{
//      if(this.callShowBannerAd && adInfo.getAdType() == IvyAdType.BANNER){
//        this.callShowBannerAd = false;
//        IvySdk.logEvent(EventID.BANNER_FIRST_SHOW_SUCCESS, null);
//      }
//    } catch (Exception e){}
    }

    public void onAdShowFail(IvyAdType adType) {
        Logger.debug(TAG, "onAdShowFail %s", adType);
        willDisplayingAd = false;

        if (adCallbacks.containsKey(adType)) {
            adCallbacks.get(adType).onAdShowFail(adType);
        }

        if (adType == IvyAdType.REWARDED) {
            fetchRewarded(main);
        } else if (adType == IvyAdType.INTERSTITIAL) {
            fetchInterstitial(main);
        } else if (adType == IvyAdType.NATIVE_AD) {
            fetchNativeAd(main);
        }
    }

    public void onAdLoadSuccess(IvyAdInfo adInfo) {
        if (adInfo == null) {
            return;
        }
        try {
            if (adCallbacks.containsKey(adInfo.getAdType())) {
                adCallbacks.get(adInfo.getAdType()).onAdLoadSuccess(adInfo);
            }

            EventBus.getInstance().fireEvent(CommonEvents.AD_LOADED, adInfo.getAdType().ordinal());

            switch (adInfo.getAdType()) {
                case REWARDED:
                    if (displayRewardVideoWhenLoaded) {
                        dismissLoadingDialog();
                        displayRewardVideoWhenLoaded = false;
                        this.mRewardedAds.show(main, "default");
                    }
                    return;
                case INTERSTITIAL:
                    return;
                case NATIVE_AD:
                    return;
                default:
                    return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void onAdLoadFail(IvyAdType adType) {
        Logger.debug(TAG, "onAdLoadFail %s", adType);
        if (adCallbacks.containsKey(adType)) {
            adCallbacks.get(adType).onAdLoadFail(adType);
        }
        switch (adType) {
            case REWARDED:
                dismissLoadingDialog();
                if (displayRewardVideoWhenLoaded) {
                    displayRewardVideoWhenLoaded = false;
                    Toast.makeText(main, main.getString(R.string.reward_video_load_failed), Toast.LENGTH_SHORT).show();
                }
                return;
            default:
                return;
        }
    }

    @Override
    public void onAdClosed(IvyAdInfo adInfo, boolean isReward) {
        Logger.debug(TAG, "onAdClosed %s", adInfo);

        // reset the home Ad show and pause time
        willDisplayingAd = false;
        lastHomeAdShowTime = System.currentTimeMillis();
        lastHomeAdPauseTime = System.currentTimeMillis();

        if (adInfo == null) {
            return;
        }

        try {
            if (adCallbacks != null) {
                if (adCallbacks.containsKey(adInfo.getAdType())) {
                    adCallbacks.get(adInfo.getAdType()).onAdClosed(adInfo, isReward);
                }
            }

            IvyAdType adType = adInfo.getAdType();
            if (adType == IvyAdType.REWARDED) {
                displayRewardVideoWhenLoaded = false;
                fetchRewarded(main);
            } else if (adType == IvyAdType.INTERSTITIAL) {
                fetchInterstitial(main);
            } else if (adType == IvyAdType.NATIVE_AD) {
                fetchNativeAd(main);
            } else if (adType == IvyAdType.REWARDED_INTERSTITIAL) {
                fetchRewardedInterstitial(main);
            }
        } catch (Exception ex) {
            // ex.printStackTrace();
        }
    }

    public void onAdClicked(IvyAdInfo adInfo) {
        Logger.debug(TAG, "onAdClicked %s", adInfo);
        if (adCallbacks.containsKey(adInfo.getAdType())) {
            adCallbacks.get(adInfo.getAdType()).onAdClicked(adInfo);
        }
    }

    public void softPause(IvyAdInfo o7AdInfo) {
    }

    public void softResume(IvyAdInfo o7AdInfo) {
    }

    public void manualPreload() {
        if (!mRewardedAds.isLoaded()) {
            fetchRewarded(main);
        }
        if (!mInterstitialAds.isLoaded()) {
            fetchNativeAd(main);
        }

        if (!mNativeAds.isLoaded()) {
            fetchNativeAd(main);
        }

        if (!mRewardedInterstitialAds.isLoaded()) {
            fetchRewardedInterstitial(main);
        }

        preloadPromoteCreativeInBackground();
    }

    public void preload(JSONObject config) {
        if (config == null) {
            return;
        }

        int preFetchRewardDelaySecs = config.optInt("preFetchRewardDelaySecs", 3);
        int preFetchInterstitialDelaySecs = config.optInt("preFetchInterstitialDelaySecs", 6);
        int preFetchNativeDelaySecs = config.optInt("preFetchNativeDelaySecs", 20);
        int preFetchRewardedInterstitialSecs = config.optInt("preFetchRewardedInterstitialSecs", 20);
        try {
            // fetchReward ad when ad providers setup, if no present
            Handler handler = HandlerFactory.createUiHandler();
            if (!mRewardedAds.isLoaded()) {
                if (preFetchRewardDelaySecs > 0) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fetchRewarded(main);
                        }
                    }, preFetchRewardDelaySecs * 1000);
                }
            }

            if (!mInterstitialAds.isLoaded()) {
                if (preFetchInterstitialDelaySecs > 0) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fetchInterstitial(main);
                        }
                    }, preFetchInterstitialDelaySecs * 1000);
                }
            }

            if (!mNativeAds.isLoaded()) {
                if (preFetchNativeDelaySecs > 0) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fetchNativeAd(main);
                        }
                    }, preFetchNativeDelaySecs * 1000);
                }
            }


            if (!mRewardedInterstitialAds.isLoaded()) {
                if (preFetchRewardedInterstitialSecs > 0) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fetchRewardedInterstitial(main);
                        }
                    }, preFetchRewardedInterstitialSecs * 1000);
                }
            }


            preloadPromoteCreativeInBackground();

        } catch (Throwable t) {
            Log.e(TAG, "Error preload ads", t);
        }
    }


    public int getBannerHeightInPx(Context context) {
        int height = 0;
        if (this.mBannerContainer != null) {
            height = this.mBannerContainer.getHeight();
        }
        if (height == 0) {
            return (int) (51.0f * context.getResources().getDisplayMetrics().density);
        }
        return height;
    }

    public boolean hasEnoughTimePassedToFetchInterstitial(Context context) {
        return true;
    }


    public void fetchNativeAd(Activity activity) {
        Logger.debug(TAG, "fetchNativeAd start");
        if (this.mNativeAds != null) {
            this.mNativeAds.fetch(activity);
        }
    }

    public boolean isNativeAdLoaded() {
        if (this.mNativeAds != null) {
            return this.mNativeAds.isLoaded();
        }
        return false;
    }

    public boolean showNativeAd(Activity activity, Map<String, View> adViews, Map<String, Object> params) {
        try {
//      IvySdk.logEvent(EventID.CLICK_SHOW_NATIVEAD, new Bundle());
            if (this.mNativeAds != null) {
                return this.mNativeAds.showNativeAd(activity, adViews, params);
            }
        } catch (Exception ex) {
            Logger.error(TAG, "showNativeAd exception", ex);
        }
        return false;
    }

    public void closeNativeAd() {
        if (this.mNativeAds != null) {
            this.mNativeAds.closeNativeAd();
        }
    }


    private boolean promoteCreativePreloaded = false;

    /**
     * preload promote creative in background, so display directly
     */
    private void preloadPromoteCreativeInBackground() {
//    if (promoteCreativePreloaded) {
//      return;
//    }
//
//    JSONObject promote = GridManager.getGridData().optJSONObject("promote");
//    if (promote == null) {
//      return;
//    }
//    JSONObject apps = promote.has("apps") ? promote.optJSONObject("apps") : null;
//    if (apps == null) {
//      return;
//    }
//    // load cover, banner, icon
//    List<String> urls = new ArrayList<>();
//    Iterator<String> it = apps.keys();
//
//    while (it.hasNext()) {
//      JSONObject app = apps.optJSONObject(it.next());
//      if (app != null) {
//        String url = app.optString("icon");
//        if (url != null && !"".equals(url)) {
//          urls.add(url);
//        }
//        url = app.optString("cover");
//        if (url != null && !"".equals(url)) {
//          urls.add(url);
//        }
//        url = app.optString("banner");
//        if (url != null && !"".equals(url)) {
//          urls.add(url);
//        }
////        url = app.optString("gificon");
////        if (url != null && !"".equals(url)) {
////          urls.add(url);
////        }
//        url = app.optString("delicious_banner");
//        if (url != null && !"".equals(url)) {
//          urls.add(url);
//        }
//
//        url = app.optString("banner_ad");
//        if (url != null && !"".equals(url)) {
//          urls.add(url);
//        }
//      }
//    }
//    // only preload 10 urls
//    int startedDownloadCounts = 10;
//    for (String url : urls) {
//      boolean started = ((PromoteAdManager) mPromote).getCreativePath(url, null);
//      if (started) {
//        startedDownloadCounts++;
//      }
//      // 只启动10个下载，其他下载等下次打开或者按需触发吧
//      if (startedDownloadCounts > 10) {
//        break;
//      }
//    }
//    promoteCreativePreloaded = true;
    }

    /**
     * IVY内部交叉推广的形式。 交叉推广内容从grid数据的promote中读取
     *
     * 1. 全屏广告，可以滑动的从promote中提取的左右滑动的全屏广告  showPromoteAd
     * 2. GIF ICON, 客户设定大小，及屏幕绝对位置的GIF动画，点击GIF动画进入一个新的Activity,播放视频广告动画 showDeliciousIcon
     * 3. GIF BANNER 显示所有BANNER动画推广 showDeliciousBanner
     * 4. 全屏包含GIF BANNER的视频广告 showDeliciousAd
     * 5. 游戏退出广告位, 显示自定义的退出插屏及更多游戏链接 onQuit
     * 6. 游戏墙 moreGame
     */

    /**
     * IVY的划屏全屏广告。
     */
    public void showPromoteAd(Activity activity) {
        if (mInterstitialAds != null && mInterstitialAds instanceof CommonAdManager) {
            ((CommonAdManager) mInterstitialAds).showAdsFallInterstitial(activity);
        }
    }

    /**
     * 在当前屏幕显示一个图标，图标可以打开ivy delicious 推广链接。
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param promotepackage 要推广的包名，如果包名为null,将在promote中随机选择一个。优先选择gif,没有gif显示icon
     */
    public void showDeliciousIcon(int x, int y, int width, int height, String promotepackage) {
        if (mPromote != null) {
            mPromote.showDeliciousIcon(x, y, width, height, promotepackage);
        }
    }

    public void closeDeliciousIconAd() {
        if (mPromote != null) {
            mPromote.closeDeliciousIconAd();
        }
    }

    /**
     * 在屏幕位置显示delicious banner
     *
     * @param defaultpackage
     */
    public void showDeliciousBanner(int x, int y, int w, int h, String defaultpackage) {
        if (mPromote != null) {
            mPromote.showDeliciousBanner(x, y, w, h, null);
        }
    }

    public void closeDeliciousBanner() {
        if (mPromote != null) {
            mPromote.closeDeliciousBanner();
        }
    }

    /**
     * 启动delicious视频集
     */
    public void showDeliciousAd() {
        if (mPromote != null) {
            mPromote.showDeliciousAd();
        }
    }

    /**
     * 检查是否有退出广告位，如果有，退出广告位完毕,退出游戏
     */
    public void onQuit() {
        if (mPromote != null) {
            mPromote.onQuit();
        }
    }

    public void getCreativePath(String url, DownloadFeedback downloadFeedback) {
//    if (mPromote != null) {
//      ((PromoteAdManager) mPromote).getCreativePath(url, downloadFeedback);
//    }
    }

    public JSONObject getPromoteApp(String packageName) {
        if (packageName == null || "".equals(packageName)) {
            return null;
        }

        JSONObject promote = GridManager.getGridData().optJSONObject("promote");
        if (promote == null) {
            return null;
        }
        JSONObject apps = promote.has("apps") ? promote.optJSONObject("apps") : null;
        if (apps == null) {
            return null;
        }

        Iterator<String> it = apps.keys();
        while (it.hasNext()) {
            JSONObject app = apps.optJSONObject(it.next());
            if (app != null) {
                if (packageName.equals(app.optString("package"))) {
                    return app;
                }
            }
        }
        return null;
    }

    /**
     * 显示推广墙
     */
    public void moreGame() {
        mPromote.moreGame();
    }

    private AlertDialog loadingDialog;

    private void showLoadingDialog(IvyAdType adType) {
        try {
            if (this.loadingDialog == null) {
                this.loadingDialog = new AlertDialog.Builder(main).create();
            }
            this.loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
            this.loadingDialog.setCancelable(false);
            this.loadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == KeyEvent.KEYCODE_SEARCH || i == KeyEvent.KEYCODE_BACK) {
                        displayRewardVideoWhenLoaded = false;
                        dismissLoadingDialog();
                        onAdLoadFail(adType);
                        return false;
                    }
                    return false;
                }
            });
            this.loadingDialog.show();

            View inflate = LayoutInflater.from(main).inflate(R.layout.loading_alert, null);
            if (inflate != null) {
                this.loadingDialog.setContentView(inflate);
                this.loadingDialog.setCanceledOnTouchOutside(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissLoadingDialog() {
        if (this.loadingDialog != null && this.loadingDialog.isShowing()) {
            this.loadingDialog.dismiss();
            this.loadingDialog = null;
        }
    }
}
