package com.ivy.ads.managers;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.ivy.Logger;
import com.ivy.R;
import com.ivy.ads.adapters.AdOpenCloseCallback;
import com.ivy.ads.adapters.BannerAdapter;
import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.adrollers.BannerAdRoller;
import com.ivy.ads.configuration.BannerConfig;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.events.BannerEventHandler;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyAdapterInfo;
import com.ivy.ads.interfaces.IvyBannerAd;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.summary.AdSummaryEventHandler;
import com.ivy.ads.utils.HandlerFactory;
import com.ivy.ads.utils.Stopwatch;
import com.ivy.networks.util.Util;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BannerAdManager extends CommonAdManager<BannerConfig> implements IvyBannerAd, AdOpenCloseCallback {
    private static final String TAG = "BannerAdManager";

    public static final int BANNER_POSITION_TOP = 0;
    public static final int BANNER_POSITION_BOTTOM = 1;
    private List<Pair<String, Long>> bannerDurationsList = new ArrayList();
    private int bannerPosition = -1;
    private Stopwatch bannerShownTimeStopwatch = new Stopwatch(getClass().getSimpleName());
    private boolean bannersShowing = false;
    private boolean isBannerForceHidden = false;
    private boolean isBannerPaused = false;
    private BannerAdRoller mAdRoller;
    private ViewGroup mConsumerViewContainer;
    private ViewGroup mIVYAdViewContainer;
    private BannerAdapter previousProvider;
    private boolean previousProviderUsedOverdueBanner = false;

    private Animation fadeInAnimation = null;

    public BannerAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProvidersRegistry, Handler uiHandler, Handler mHandler, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
        super(activity, configurationParser, adSelector, adProvidersRegistry, uiHandler, mHandler, IvyAdType.BANNER, eventHandler, adSummaryEventHandler);
        this.mAdRoller = new BannerAdRoller(HandlerFactory.createHandler(BannerAdRoller.class), uiHandler, this, getManagerConfig(), getAdSelector());
        try {
            this.mIVYAdViewContainer = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.adsfall_banner_adcontainer, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.fadeInAnimation = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
    }

    public Class<BannerConfig> getManagerConfigClass() {
        return BannerConfig.class;
    }


    public List<JSONObject> getGridProviderList() {
        BannerConfig bannerConfig = getManagerConfig();
        if (bannerConfig != null) {
            return bannerConfig.adProviderPriority;
        }
        return null;
    }

    public void setupAdProviders() {
        super.setupAdProviders();
        this.mAdRoller.setConfig(getManagerConfig());
    }

    public void show(final Activity activity, FrameLayout container) {
        Logger.debug( "showBanner");
        this.mConsumerViewContainer = container;
        this.mAdRoller.start(activity);
        this.isBannerForceHidden = false;
    }

    public void onBannerLoaded(BannerAdapter adapter, Activity activity, boolean usingOverdueBannerDuringLoading) {
        if (this.mAdRoller.isAdRollerStopped()) {
            Logger.debug( "Banner ad roller is stopped, not showing the ad");
            return;
        }
        Logger.debug( "onBannerLoaded : " + adapter.getAdType() + ", provider: " + adapter.getName());
        this.mIVYAdViewContainer.setVisibility(View.VISIBLE);
        boolean adLabel = getManagerConfig().adLabel;
        this.mIVYAdViewContainer.findViewById(R.id.adLabelContainer).setVisibility(adLabel ? View.VISIBLE : View.INVISIBLE);
//        Logger.debug( "Showing adLabel: %s", adLabel);
        ViewGroup bannerContainer = this.mIVYAdViewContainer.findViewById(R.id.activead);
        bannerContainer.removeAllViews();
        bannerContainer.setVisibility(View.VISIBLE);
        View adapterView = getAdapterView(adapter);
        if (adapterView != null && removeParent(adapterView, "adapterView")) {
            bannerContainer.addView(adapterView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            LayoutParams lp = bannerContainer.getLayoutParams();
//            Logger.debug(TAG, "width x height: %s x %s", lp.width, lp.height);
            this.mConsumerViewContainer.setVisibility(View.VISIBLE);
            this.mConsumerViewContainer.removeAllViews();
            if (removeParent(this.mIVYAdViewContainer, "o7AdViewContainer")) {

                this.mConsumerViewContainer.addView(this.mIVYAdViewContainer);
                if (this.fadeInAnimation != null) {
                    this.mIVYAdViewContainer.startAnimation(this.fadeInAnimation);
                }

                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) bannerContainer.getLayoutParams();
                int adapterWidth = adapter.getWidth();
                if (adapterWidth == BannerAdapter.SMART_BANNER_WIDTH) {
                    layoutParams.width = LayoutParams.WRAP_CONTENT;
                } else if (adapterWidth == BannerAdapter.MATCH_PARENT_WIDTH) {
                    layoutParams.width = LayoutParams.MATCH_PARENT;
                } else {
                    Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                    DisplayMetrics metrics = new DisplayMetrics();
                    display.getMetrics(metrics);
                    layoutParams.width = (int) (((float) adapterWidth) * metrics.density);

                    int adapterHeight = Math.min(adapter.getHeight(), 70);
                    if (adapterHeight > 0) {
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mConsumerViewContainer.getLayoutParams();
                        int newHeightInPx = Util.dpToPx(activity, adapterHeight);
                        if (params.height != newHeightInPx) {
                            params.height = newHeightInPx;
                            mConsumerViewContainer.setLayoutParams(params);
                        }
                    }
                }
                setBannersShown(adapter, usingOverdueBannerDuringLoading);
                adapter.show(activity, this);
            }
        } else {
            Logger.debug( "Banner adview is null, not able to display");
            adapter.render(bannerContainer);
            setBannersShown(adapter, usingOverdueBannerDuringLoading);
            adapter.show(activity, this);
        }
    }

    private View getAdapterView(BannerAdapter adapter) {
        try {
            return adapter.getView();
        } catch (Exception e) {
//            Logger.error( "getAdapterView()", e);
            return null;
        }
    }

    private boolean removeParent(View view, String name) {
        ViewParent adapterParent = view.getParent();
        if (adapterParent != null) {
            if (adapterParent instanceof ViewManager) {
                ((ViewManager) adapterParent).removeView(view);
            } else {
                return false;
            }
        }
        return true;
    }

    public void hide() {
        if (this.mConsumerViewContainer != null) {
//            Logger.debug(TAG, "hideBanner");
            this.mConsumerViewContainer.setVisibility(View.GONE);
        }
        this.isBannerForceHidden = true;
        this.mAdRoller.stop();
        setBannersHidden();
    }

    public void setBannerPosition(int position, Activity activity) {
        this.bannerPosition = position;
        if (activity == null || this.mConsumerViewContainer == null) {
            Logger.debug( "Activity or mConsumerViewContainer is null. Setting position on next show");
        } else {
        }
    }


    public void onResume(Activity activity) {
        if (this.mAdRoller == null) {
            return;
        }
        if (this.isBannerPaused && !this.isBannerForceHidden) {
            if (this.mConsumerViewContainer != null) {
                this.mAdRoller.start(activity);
            }
            this.isBannerPaused = false;
        } else if (this.isBannerForceHidden) {
            Logger.debug( "Banners were hidden by app - hide() was called before onResume.");
        }
    }

    public void onPause(Activity activity) {
        if (this.mAdRoller == null) {
            return;
        }
        if (this.mAdRoller.isAdRollerStopped()) {
            return;
        }
        Logger.debug( "BannerAdRoller wasn't paused yet, pausing now");
        this.mAdRoller.stop();
        this.isBannerPaused = true;
    }

    public void onAdShown(IvyAdapterInfo adapter) {
    }

    public void onAdShownFail(IvyAdType adType) {
    }

    public void onAdClosed(IvyAdapterInfo adapter, boolean isReward) {
    }

    public void onAdClicked(IvyAdapterInfo adapter) {
        Logger.debug( "adapter: "+ adapter.getName());
        if (getCallback() != null) {
            getCallback().onAdClicked(new IvyAdInfo(getAdType(), adapter));
        }
    }

    public void softPause(IvyAdInfo o7AdInfo) {
    }

    public void softResume(IvyAdInfo o7AdInfo) {
    }

    public void clearBannerShownTimeStopwatch(boolean shouldRestart) {
        if (shouldRestart) {
            Logger.debug( "Banner adShown stopwatch restarted.");
            this.bannerShownTimeStopwatch.restart();
            return;
        }
        Logger.debug( "Banner adShown stopwatch cleared.");
        this.bannerShownTimeStopwatch.clear();
    }

    private void setBannersShown(BannerAdapter bannerAdapter, boolean usingOverdueBannerDuringLoading) {
        Logger.debug( "setBannersShown()");

        bannerAdapter.setBannerContainer((ViewGroup) this.mIVYAdViewContainer.findViewById(R.id.activead));
        if (!this.bannersShowing) {
            Logger.debug( "banner-displayed event fired.");
            ((BannerEventHandler) getEventHandler()).onBannerDisplayed(bannerAdapter);
            this.bannerDurationsList = new ArrayList<>();
        }
        this.bannersShowing = true;
        if (this.previousProviderUsedOverdueBanner && this.bannerDurationsList.size() > 0) {
            Pair<String, Long> removedAdapter = this.bannerDurationsList.remove(this.bannerDurationsList.size() - 1);
            Logger.debug( "Removing previous one - " + ((String) removedAdapter.first) + ":" + removedAdapter.second);
        }
        if (this.previousProvider != null) {
            Logger.debug( "bannerShownTimeStopwatch=" + this.bannerShownTimeStopwatch.getElapsedTimeMillis() + "  " + this.previousProvider.getName());
            this.bannerDurationsList.add(new Pair<>(this.previousProvider.getName(), Long.valueOf(this.bannerShownTimeStopwatch.getElapsedTimeMillis())));
        }
        this.bannerShownTimeStopwatch.resume();
        this.previousProvider = bannerAdapter;
        this.previousProviderUsedOverdueBanner = usingOverdueBannerDuringLoading;
    }

    private void setBannersHidden() {
//        Logger.debug(TAG, "");
        BannerAdapter bannerAdapter = (BannerAdapter) this.mAdRoller.getAdapter();
        if (bannerAdapter == null) {
//            Logger.debug(TAG, "Current banner adapter is null (previous = " + this.mAdRoller.getPreviousAdapter() + ")");
            bannerAdapter = this.mAdRoller.getPreviousAdapter();
            if (bannerAdapter == null) {
//                Logger.debug(TAG, "Previous banner adapter is null. Returning...");
                return;
            }
            bannerAdapter.hide();
        }

        this.bannerShownTimeStopwatch.pause();
        if (this.bannersShowing) {
            this.bannersShowing = false;
//            Logger.debug(TAG, "bannerShownTimeStopwatch=" + this.bannerShownTimeStopwatch.getElapsedTimeMillis() + "  " + bannerAdapter.getName());
//            if (this.previousProviderUsedOverdueBanner && this.bannerDurationsList.size() > 0) {
//                Pair<String, Long> removedAdapter = this.bannerDurationsList.remove(this.bannerDurationsList.size() - 1);
//                Logger.debug(TAG, "Removing previous one - " + ((String) removedAdapter.first) + ":" + removedAdapter.second + " - as overdue banner was used...");
//            }
            this.bannerDurationsList.add(new Pair<>(bannerAdapter.getName(), this.bannerShownTimeStopwatch.getElapsedTimeMillis()));
            this.previousProvider = null;
            this.bannerShownTimeStopwatch.clear();
        }
    }
}
