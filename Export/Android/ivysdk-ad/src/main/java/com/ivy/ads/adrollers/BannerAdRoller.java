package com.ivy.ads.adrollers;

import android.app.Activity;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.ivy.Logger;
import com.ivy.ads.adapters.BannerAdapter;
import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.ads.configuration.BannerConfig;
import com.ivy.ads.managers.BannerAdManager;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.selectors.AdSelectorCallback;
import com.ivy.networks.grid.GridManager;


import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BannerAdRoller {
    private static final String TAG = "BannerAdRoller";

    private static final long MAX_WAIT_TIME_FOR_NEXT_BANNER_FETCH_MIN = 5;
    private static final long NO_WORKING_AD_PROVIDER_REFRESH_INTERVAL_MILLIS = 5000;
    private static final int MAX_ROLLER = 9999;
    private final BannerAdManager mAdManager;
    private final AdSelector mAdSelector;
    private final Lock mLock;
    private final Condition mRefreshBannerCondition;
    private final Condition mSleepUntilNextWaterfallCycleCondition;
    private final Handler mUiHandler;
    private BannerAdapter mAdapter;
    private BannerConfig mConfig;
    private Handler mHandler;
    private volatile boolean mIsAdRollerRunning;
    private boolean mIsFirstRun = true;
    private boolean mIsRollerFirstStart = true;
    private long mLastProviderFetchTimeMillis;
    private volatile boolean mStopAdRoller;
    private BannerAdapter previousAdapter;
    private Timer timer;

    public BannerAdRoller(Handler handler, Handler uiHandler, BannerAdManager adManager, BannerConfig config, AdSelector adSelector) {
        this.mHandler = handler;
        this.mUiHandler = uiHandler;
        this.mAdManager = adManager;
        this.mConfig = config;
        this.mAdSelector = adSelector;
        this.mLock = new ReentrantLock();
        this.mRefreshBannerCondition = this.mLock.newCondition();
        this.mSleepUntilNextWaterfallCycleCondition = this.mLock.newCondition();
    }

    private void initTimer(final BannerAdapter adapter) {
        if (this.timer != null) {
            this.timer.cancel();
        }
        this.timer = new Timer("timer");
        this.timer.schedule(new TimerTask() {
            public void run() {
                long shownTime = adapter.getShownTime();
                if (BannerAdRoller.this.isBannerShownEnoughTime(adapter)) {
                    Logger.debug( "banner has shown enough time, signal the banner refresh thread...");
                    BannerAdRoller.this.timer.cancel();
                    BannerAdRoller.this.mLock.lock();
                    try {
                        BannerAdRoller.this.mRefreshBannerCondition.signal();
                    } finally {
                        BannerAdRoller.this.mLock.unlock();
                    }
                }
            }
        }, 0, 1000);
    }

    public void setConfig(BannerConfig config) {
        this.mConfig = config;
    }

    @Nullable
    public BaseAdapter getAdapter() {
        return this.mAdapter;
    }

    @Nullable
    public BannerAdapter getPreviousAdapter() {
        return this.previousAdapter;
    }

    private void setPreviousAdapter(BannerAdapter bannerAdapter) {
        this.previousAdapter = bannerAdapter;
    }

    public void start(final Activity activity) {
        Logger.debug( "AdRoller start called ");
        this.mStopAdRoller = false;
        this.mHandler.post(new Runnable() {
            public void run() {
                if (!BannerAdRoller.this.mStopAdRoller) {
                    BannerAdRoller.this.mIsAdRollerRunning = true;
                    int rollerIteration = 0;
                    boolean offlineDisplayed = false;

                    while (!BannerAdRoller.this.mStopAdRoller) {
                        int adRefreshIntervalMillis;
                        BannerAdapter adapter;
                        int rollerIteration2 = rollerIteration + 1;
                        if (rollerIteration2 > MAX_ROLLER) {
                            Logger.debug( "reach max roller, stop ad roller");
                            BannerAdRoller.this.mIsAdRollerRunning = false;
                            return;
                        }

                        boolean newBannerFetched = false;
                        if (BannerAdRoller.this.mAdapter == null || BannerAdRoller.this.isBannerShownEnoughTime(BannerAdRoller.this.mAdapter)) {
//              if (BannerAdRoller.this.mAdapter != null) {
//                Logger.debug(BannerAdRoller.TAG, "Using overdue banner from previous session but fetching new one");
//                BannerAdRoller.this.setPreviousAdapter(BannerAdRoller.this.mAdapter);
//                BannerAdRoller.this.notifyBannerIsFetched(BannerAdRoller.this.mAdapter, activity, true, false);
//              }

                            BannerAdRoller.this.mAdapter = null;

                            adRefreshIntervalMillis = BannerAdRoller.this.getAdRefreshIntervalMillis();
                            Logger.debug( "BannerAdRoller selectAd");
                            adapter = (BannerAdapter) BannerAdRoller.this.mAdSelector.selectAd(activity, BannerAdRoller.this.mConfig, BannerAdRoller.this.mAdManager.getGridAndRegisteredProviders());
                            BannerAdRoller.this.mLastProviderFetchTimeMillis = System.currentTimeMillis();
                            newBannerFetched = true;
                        } else {
                            adapter = BannerAdRoller.this.mAdapter;
                            adRefreshIntervalMillis = (int) (((long) BannerAdRoller.this.getAdRefreshIntervalMillis()) - adapter.getShownTime());
//                            Logger.debug(TAG, "Not fetching new banner but reusing existing one, %s , as not enough time has passed. Will fetch new one in: %s ms", adapter.getName(), Integer.valueOf(adRefreshIntervalMillis));
                        }

                        if (BannerAdRoller.this.mStopAdRoller) {
                            if (adapter != null) {
                                Logger.debug( "Banner was loaded but ad roller was stopped. So we save it for later use.");
                                BannerAdRoller.this.setPreviousAdapter(BannerAdRoller.this.mAdapter);
                                BannerAdRoller.this.mAdapter = adapter;
                                BannerAdRoller.this.mAdManager.clearBannerShownTimeStopwatch(false);
                            }
                        } else if (adapter == null) {
//              Logger.debug(TAG, "Waterfall returned no fill, use Adsfall to fill");
//              if (!offlineDisplayed) {
//                showOfflineBanner(activity);
//                offlineDisplayed = true;
//              }
                        } else {
                            BannerAdRoller.this.initTimer(adapter);
                            BannerAdRoller.this.setPreviousAdapter(BannerAdRoller.this.mAdapter);
                            BannerAdRoller.this.mAdapter = adapter;
                            BannerAdRoller.this.notifyBannerIsFetched(adapter, activity, false, newBannerFetched);
//                            Logger.debug(BannerAdRoller.TAG, "Banner loaded. Sleeping for: %s, %s", adRefreshIntervalMillis, adapter.getName());
                            BannerAdRoller.this.mLock.lock();
                            try {
                                Logger.debug( "Sleeping, wait to signal awake");
                                BannerAdRoller.this.mRefreshBannerCondition.await(5, TimeUnit.MINUTES);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                BannerAdRoller.this.mLock.unlock();
                            }
                        }
                        BannerAdRoller.this.mLock.lock();
                        try {
                            if (BannerAdRoller.this.mStopAdRoller) {
                                Logger.debug("AdRoller stopped");
                            } else if (adapter == null) {
//                                Logger.debug(BannerAdRoller.TAG, "Sleeping for %s ms, before restarting waterfall", 15000);
                                BannerAdRoller.this.mSleepUntilNextWaterfallCycleCondition.await(15000, TimeUnit.MILLISECONDS);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        } finally {
                            try {
                                mLock.unlock();
                            } catch (Exception unlockE) {
                                unlockE.printStackTrace();
                            }
                        }
                        BannerAdRoller.this.mIsFirstRun = false;
                        rollerIteration = rollerIteration2;
                    }
                    BannerAdRoller.this.mIsAdRollerRunning = false;
                } else {
                    Logger.debug( "Ad Roller is stopped");
                }
            }
        });
    }

    private void notifyBannerIsFetched(BannerAdapter adapter, Activity activity, boolean usingOverdueBannerDuringLoading, boolean newBannerFetched) {
        final BannerAdapter bannerAdapter = adapter;
        final Activity activity2 = activity;
        final boolean z = usingOverdueBannerDuringLoading;
        final boolean z2 = newBannerFetched;
        this.mUiHandler.post(new Runnable() {
            public void run() {
                BannerAdRoller.this.mAdManager.onBannerLoaded(bannerAdapter, activity2, z);
                if (z2) {
                    BannerAdRoller.this.mAdManager.clearBannerShownTimeStopwatch(true);
                }
            }
        });
    }

//    private void showOfflineBanner(final Activity activity) {
//        Logger.debug( "showOfflineBanner called");
//        boolean prefillbanner = GridManager.getGridData().optBoolean("preFillBanner", true);
//        if (!prefillbanner) {
//            return;
//        }
//        final AdsfallBannerAdapter offlineBannerAdapter = (AdsfallBannerAdapter) this.mAdManager.getAdProvidersMap().get("adsfall");
//        if (offlineBannerAdapter != null) {
//            if (!offlineBannerAdapter.initialized) {
//                offlineBannerAdapter.setPromoteConfig(this.mAdManager.getPromiteConfig());
//                offlineBannerAdapter.resetOperationCount();
//                offlineBannerAdapter.setEventHandler(this.mAdManager.getEventHandler());
//                offlineBannerAdapter.markReady();
//                offlineBannerAdapter.initialized = true;
//            }
//            this.mUiHandler.post(new Runnable() {
//                public void run() {
//                    if (offlineBannerAdapter != null) {
//                        offlineBannerAdapter.fetch(activity, new AdSelectorCallback() {
//                            public void adLoadSuccess(BaseAdapter adapter) {
//                                Logger.debug(BannerAdRoller.TAG, "Offline banner fetched. Now showing it");
//                                BannerAdRoller.this.setPreviousAdapter(offlineBannerAdapter);
//                                BannerAdRoller.this.notifyBannerIsFetched(offlineBannerAdapter, activity, false, false);
//                            }
//
//                            public void adLoadFailed(BaseAdapter adapter) {
//                                Logger.debug(BannerAdRoller.TAG, "Offline banner fetch failed. Probably missing creative");
//                            }
//                        });
//                    }
//                }
//            });
//        }
//    }


    private int getAdRefreshIntervalMillis() {
        return this.mConfig.adRefreshInterval * 1000;
    }

    private boolean isBannerShownEnoughTime(BannerAdapter adapter) {
        return adapter.getShownTime() >= ((long) getAdRefreshIntervalMillis());
    }

    public void stop() {
        if (this.mIsAdRollerRunning) {
            Logger.debug( "AdRoller stopping ");
        }
        this.mStopAdRoller = true;
        this.mAdSelector.forceStopSelector();
        this.mLock.lock();
        try {
            this.mRefreshBannerCondition.signal();
            this.mSleepUntilNextWaterfallCycleCondition.signal();
            BaseAdapter adapter = getAdapter();
            if (adapter != null) {
                adapter.hide();
            }
        } finally {
            this.mLock.unlock();
        }
    }

    public boolean isAdRollerStopped() {
        return this.mStopAdRoller;
    }
}