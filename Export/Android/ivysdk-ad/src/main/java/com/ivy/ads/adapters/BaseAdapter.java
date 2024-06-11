package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import com.android.client.AndroidSDK;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ivy.Logger;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.events.EventID;
import com.ivy.ads.interfaces.Adapter;
import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.ads.interfaces.IvyShowFailStatus;
import com.ivy.ads.selectors.AdSelectorCallback;
import com.ivy.ads.selectors.AdapterSkipReason;
import com.ivy.ads.summary.AdSummaryEventHandler;
import com.ivy.ads.utils.HandlerFactory;


import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class BaseAdapter<T extends BaseAdapter.GridParams> implements Adapter<T> {
    private static String TAG = "Adapter";
    private final IvyAdType mAdType;
    protected Activity activity;
    protected final Context mApplicationContext;
    private final String mGridName;
    protected AdOpenCloseCallback mAdOpenCloseCallback;
    protected long mShowStartTime;
    private int adProviderAgeLimit = -1;
    private String mCoppaDynamic = null;
    private BaseEventHandler mEventHandler;
    private AdSelectorCallback mFetchCallback;
    private int mGridIndex;
    private GridParams mGridParams;
    private boolean mIBAMode;
    private boolean mIsDebugMode;
    private boolean mIsSetupDone;
    private boolean mIsTestMode;
    private boolean mIsDevMode;
    private long mLoadStartTime = 0;
    private long mLoadSuccessTime;
    private IvyShowFailStatus mShowStatus;
    private int mShownCount;
    private Integer mTimeoutSeconds;
    protected Handler mUiHandler = HandlerFactory.createUiHandler();
    private String networkName = AdNetworkName.NONE;
    private boolean timeoutCalled;

    private float mHBFloorPrice = 0.0f;

    private boolean forceSkipped = false;
    private boolean forceSkippedByCountry = false;
    private boolean forceSkippedUntilNextAppOpen = false;
    private int forceSkippedTimes = 0;
    private String forceSkippedReason;

    private int mSelectedWeight = 1;

    // 此adapter最近的加载时间，waterfall quick 模式会在
    private long latestLoadTimeMS = Integer.MAX_VALUE;

    // 此Adapter后台配置的估算ecpm
    private float estimatedEcpm = 0;

    private int totalRequestCount = 0;
    // 连续加载失败的
    private int totalLoadFailedCount = 0;
    // 连续超时
    private int totalLoadTimeoutCount = 0;
    private int totalLoadSuccessCount = 0;
    private int totalShowSuccessCount = 0;
    private int totalShowFailCount = 0;
    private int totalClickCount = 0;
    private int totalForceSkippedCount = 0;

    // ad status, loading loaded_success loaded_failed, shown_success, shown_failed
    // 正在获取广告信息
    public static final int STATUS_FETCHING = 1;
    // 广告加载成功
    public static final int STATUS_LOADED_SUCCESS = 2;
    // 广告加载失败
    public static final int STATUS_LOADED_FAILED = 3;
    // 广告展示成功(被消耗)
    public static final int STATUS_SHOWN_SUCCESS = 4;
    // 广告展示失败(被消耗)
    public static final int STATUS_SHOWN_FAILED = 5;
    //
    public static final int STATUS_CLOSED = 6;

    protected int mAdapterStatus;

    private int mFetchedSuccessIndex = 0;

    private AdSummaryEventHandler mAdSummaryEventHandler;

    private final Map<String, String> eventParams = new HashMap<>();

    // adapter不重新fetch直到某个时间, 在adapter展示成功，加载成功，加载失败后
    // 每个Adapter有个睡眠时间，不再fetch, 让其他adapter有展示机会
    private long mSleepEndTimes = 0L;

    // adapter的睡眠时间设置
    private static final int KEY_SLEEP_DISPLAYED = 1;
    private static final int KEY_SLEEP_LOADED_SUCCESS = 2;
    private static final int KEY_SLEEP_LOADED_NOFILL = 3;
    private static final int KEY_SLEEP_LOADED_OTHER_ERROR = 4;

    // 一个广告组被展示后, 下次fetch时间延迟3s,
    // 这样避免同一个广告平台反复的被加载，
    private static final int SLEEP_DISPLAYED = 0;
    private static final int SLEEP_LOADED_SUCCESS = 0;
    private static final int SLEEP_LOADED_NOFILL = 0;
    private static final int SLEEP_LOADED_OTHER_ERROR = 0;

    private static int ADAPTER_GLOBAL_INDEX = 0;

    private int globalAdapterIndex = 0;

    private String displayedTag = null;

    public int getCaculatedWeight() {
        return this.mSelectedWeight;
    }

    public int getSelectedWeight() {
        return this.mSelectedWeight;
    }

    public BaseAdapter(Context context, String gridName, IvyAdType adType) {
        this.mApplicationContext = context.getApplicationContext();
        this.activity = (Activity) context;
        this.mGridName = gridName;
        this.mAdType = adType;

        this.globalAdapterIndex = ADAPTER_GLOBAL_INDEX++;
        // restore the performance data from preference

        resetPerformance();
    }

    public void updateDisplayedTag(String displayedTag) {
        this.displayedTag = displayedTag;
    }

    public void resetDisplayTag() {
        this.displayedTag = null;
    }

    public String getDisplayedTag() {
        return this.displayedTag;
    }

    public int getGlobalAdapterIndex() {
        return this.globalAdapterIndex;
    }

    public boolean isReadyForSelect() {
        return mAdapterStatus == STATUS_LOADED_SUCCESS && isValid();
    }

    public boolean isValid() {
        return true;
    }

    public void setCountrySpecified(JSONObject payload) {
        if (!payload.has("country-specified")) {
            return;
        }

        this.forceSkippedByCountry = true;
        String country = Locale.getDefault().getCountry();
        JSONArray countries = payload.optJSONArray("country-specified");
        if (countries.length() > 0) {
            for (int i = 0; i < countries.length(); i++) {
                if (country.equalsIgnoreCase(countries.optString(i))) {
                    this.forceSkippedByCountry = false;
                    break;
                }
            }
        }

        if (this.forceSkippedByCountry) {
            Logger.debug("Adapter " + this.toString() + " skipped by country specified settings");
        }
    }

    public void resetPerformance() {
        this.totalRequestCount = 0;
        this.totalLoadFailedCount = 0;
        this.totalLoadTimeoutCount = 0;
        this.totalLoadSuccessCount = 0;
        this.totalShowSuccessCount = 0;
        this.totalShowFailCount = 0;
        this.totalClickCount = 0;
        this.totalForceSkippedCount = 0;
    }

    /**
     * Check Adapter performance, if hit the ratio, will cause force skipped
     */
    private void checkPerformance() {
        if (this.totalLoadFailedCount >= 2) {
            Logger.debug( "Adapter " + this.mGridName + " failed for 2 times, will skipped on next waterall");
            this.markForceSkipped(AdapterSkipReason.SKIP_LOAD_FAILED_MANYTIMES);
        }

        if (this.totalLoadTimeoutCount >= 2) {
            this.markForceSkipped(AdapterSkipReason.SKIP_LOAD_TIMEOUT_MANYTIMES);
        }

        if (this.totalShowFailCount >= 2) {
            this.markForceSkipped(AdapterSkipReason.SKIP_SHOW_FAILE_MANYTIMES);
        }
    }

    private void checkPerformanceOnAdLoadFailed() {
        if (this.totalLoadFailedCount >= 2) {
            Logger.debug( "Adapter " + this.mGridName + " failed for 2 times, will skipped on next waterall");
            this.markForceSkipped(AdapterSkipReason.SKIP_LOAD_FAILED_MANYTIMES);
        }

        if (this.totalLoadTimeoutCount >= 2) {
            this.markForceSkipped(AdapterSkipReason.SKIP_LOAD_TIMEOUT_MANYTIMES);
        }

        if (this.totalLoadFailedCount >= 10) {
            this.forceSkippedUntilNextAppOpen = true;
        }
    }

    public boolean isForceSkippedByCountry() {
        return this.forceSkippedByCountry;
    }

    public boolean isForceSkipped() {
        this.forceSkippedTimes++;
        if (forceSkippedUntilNextAppOpen) {
            return true;
        }
        if (this.forceSkippedTimes > 5) {
            Logger.debug( "This adpater force skipped 10 times, try to use again.");
            resetForceSkipped();
        }
        return forceSkipped;
    }

    public boolean isFetching() {
        if (mLoadStartTime > 0) {
            long notResponseSecs = (System.currentTimeMillis() - this.mLoadStartTime) / 1000;
            if (notResponseSecs >= 60) {
                Logger.debug( mAdType.name() + " Adapter " + getName() + " not response for " + notResponseSecs + "s, reset to loaded failed status");
                mAdapterStatus = STATUS_LOADED_FAILED;
                return false;
            }
        }
        return mAdapterStatus == STATUS_FETCHING;
    }

    /**
     * 判断Adapter时候在睡眠，
     *
     * @return
     */
    public boolean isSleeping() {
        boolean sleeping = System.currentTimeMillis() < mSleepEndTimes;
        if (sleeping) {
            Logger.debug( "Adapter is sleeping ,will awake in " + (mSleepEndTimes - System.currentTimeMillis()) / 1000 + "seconds");
        }
        return sleeping;
    }

    public void markForceSkipped(String reason) {
        this.forceSkipped = true;
        this.forceSkippedReason = reason;
        this.forceSkippedTimes = 0;
        this.totalForceSkippedCount++;
    }

    public void resetForceSkipped() {
        this.forceSkipped = false;
        this.forceSkippedReason = "";
        this.forceSkippedTimes = 0;
    }

    public String getForceSkippedReason() {
        return this.forceSkippedReason;
    }

    /**
     * 从广告源中获取广告。调用广告SDK的构造方法实际创建广告对象。
     *
     * @param activity
     */
    public abstract void fetch(Activity activity);

    public abstract void show(Activity activity);

    protected abstract T newGridParams();

    public void setup(Activity activity) {
    }

    public void setEventHandler(BaseEventHandler eventHandler) {
        this.mEventHandler = eventHandler;
    }

    public void setUiHandler(Handler handler) {
        this.mUiHandler = handler;
    }

    public String getName() {
        return this.mGridName;
    }

    public void fetch(Activity activity, AdSelectorCallback callback) {
        Logger.debug( this.getAdType().name() + ", " + this.getName() + " fetch, begin");
        // 广告正在被加载, 忽略本次fetch的请求, 需要等待适配loaded failed, loaded success的状态
        if (mAdapterStatus == STATUS_FETCHING) {
            Logger.debug( "Adapter: " + this.getClass().getName() + " is fetching, waiting the adapter load result");
            return;
        }

        this.mFetchCallback = callback;

        // 广告LOADED SUCCESS状态， 适配器上次加载成功，但未被消耗, 直接翻译success回调
        if (mAdapterStatus == STATUS_LOADED_SUCCESS) {
            Logger.debug( "Adapter" + this.getClass().getName() + " already in loaded success status, just do the callback");
            onAdLoadSuccess();
            return;
        }

        mAdapterStatus = STATUS_FETCHING;
        this.lastAdLoadFailedReason = null;

        this.totalRequestCount++;
        this.mShownCount = 0;

        this.timeoutCalled = false;
        this.mLoadStartTime = System.currentTimeMillis();
        resetEventParams();
        fetch(activity);
        if (this.mEventHandler != null) {
            this.mEventHandler.fetchCalled(this);
        } else {
            Logger.error(TAG, "Event handler is null");
        }
    }

    public String getCoppaDynamic() {
        return this.mCoppaDynamic;
    }

    public void setCoppaDynamic(String coppaDynamic) {
        this.mCoppaDynamic = coppaDynamic;
    }

    public void skipFetch(String reason) {
//        Logger.debug( "Skipping ad provider: '%s' for reason: '%s' / '%s'", new Object[]{getName(), reason, reason});
    }

    public void show(Activity activity, AdOpenCloseCallback adOpenCloseCallback) {
        if (getAdType() == IvyAdType.BANNER) {
            int i = this.mShownCount;
            this.mShownCount = i + 1;
            if (i != 0) {
                return;
            }
        }
        this.mShowStartTime = System.currentTimeMillis();
        this.mAdOpenCloseCallback = adOpenCloseCallback;
        show(activity);
        if (this.mEventHandler != null) {
            this.mEventHandler.showCalled(this);
        }
    }

    public void show() {
    }

    public void hide() {
    }

    public void timeout() {
        this.totalLoadTimeoutCount++;
        checkPerformance();
        this.timeoutCalled = true;
        if (this.mEventHandler != null) {
            this.mEventHandler.timeoutCalled(this);
        }
    }

    public long getLoadTimeMs() {
        return System.currentTimeMillis() - this.mLoadStartTime;
    }

    public long getShowTimeMs() {
        return System.currentTimeMillis() - this.mShowStartTime;
    }

    public boolean isSetupDone() {
        return this.mIsSetupDone;
    }

    public void markSetupDone() {
        this.mIsSetupDone = true;
    }

    public int getGridIndex() {
        return this.mGridIndex;
    }

    public void setGridIndex(int gridIndex) {
        this.mGridIndex = gridIndex;
    }

    public void onPause(Activity activity) {
    }

    public void onResume(Activity activity) {
    }

    public void onDestroy(Activity activity) {
    }


    public float getHBFloorPrice() {
        return this.mHBFloorPrice;
    }

    public void setHBFloorPrice(float f) {
        this.mHBFloorPrice = f;
    }


    public Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public IvyAdType getAdType() {
        return this.mAdType;
    }

    public void setTimeoutOverride(int seconds) {
        this.mTimeoutSeconds = seconds;
    }

    public void setSleepAttributeSettings(JSONObject attributeSettings) {
    }

    public String toString() {
        return "BaseAdapter{name='" + this.mGridName + '\'' + ", adType=" + this.mAdType;
    }

    public void softPause() {
        final BaseAdapter mBaseAdapter = this;
        this.mUiHandler.post(() -> {
//            Logger.w(BaseAdapter.TAG, "%s softPause called, mAdopenCloseCallback is null = %s", BaseAdapter.this.getName(), Boolean.valueOf(BaseAdapter.this.mAdOpenCloseCallback == null));
            if (BaseAdapter.this.mAdOpenCloseCallback != null) {
                BaseAdapter.this.mAdOpenCloseCallback.softPause(new IvyAdInfo(mBaseAdapter.getAdType(), mBaseAdapter));
            }
        });
    }

    public void softResume() {
        final BaseAdapter mBaseAdapter = this;
        this.mUiHandler.post(() -> {
//            Logger.warning(BaseAdapter.TAG, "%s softResume called, mAdopenCloseCallback is null = %s", BaseAdapter.this.getName(), Boolean.valueOf(BaseAdapter.this.mAdOpenCloseCallback == null));
            if (BaseAdapter.this.mAdOpenCloseCallback != null) {
                BaseAdapter.this.mAdOpenCloseCallback.softResume(new IvyAdInfo(mBaseAdapter.getAdType(), mBaseAdapter));
            }
        });
    }

    public int getFetchedSuccessIndex() {
        return mFetchedSuccessIndex;
    }

    /**
     * 将Adapter转入休眠状态。
     * 如果当前苏醒的时间>System.currentTimeMillis+sleepSeconds*1000 忽略次操作，否则覆盖
     *
     * @param sleepSeconds
     */
    public void markSleepSecs(int sleepSeconds) {
        long realSleepEndTimes = System.currentTimeMillis() + sleepSeconds * 1000;
        if (realSleepEndTimes > this.mSleepEndTimes) {
            this.mSleepEndTimes = realSleepEndTimes;
        }
    }

    public void onGmsPaidEvent(String ad_network, String ad_format, String placement, String adunit, String currencyCode, int precisionType, long valueMacros) {
        if (this.mEventHandler == null) {
            return;
        }
        try {
            double revenue = valueMacros / 1000000.0f;
            if (revenue >= 10.0 || revenue <= 0) {
                return;
            }

            if (mAdSummaryEventHandler != null) {
                mAdSummaryEventHandler.onAdPaid(mAdType, mGridName, (float) revenue);
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
            if (getMediation() != null) {
                bundle.putString("mediation", getNetworkName());
            }
            mEventHandler.getEventLogger().logToFirebase(EventID.GMS_AD_IMPRESSION_PING, bundle);
            mEventHandler.getEventLogger().parfkaLog(EventID.GMS_AD_IMPRESSION_PING, bundle);
            mEventHandler.getEventLogger().afAdImpressionPing(bundle, revenue);

            Map<String, Object> map = new HashMap<>();
            for (String s : bundle.keySet()) {
                map.put(s, bundle.get(s));
            }
            AndroidSDK.onGMSPaid(map);

            if ("banner".equals(ad_format)) {
                Bundle pam = new Bundle();
                pam.putDouble("Banner_Ad_Impression_Value", revenue * 1000);
                mEventHandler.getEventLogger().logToFirebase(EventID.GMS_PAM_BANNER, pam);
            } else if ("interstitial".equals(ad_format)) {
                Bundle pam = new Bundle();
                pam.putDouble("Interstitial_Ad_Impression_Value", revenue * 1000);
                mEventHandler.getEventLogger().logToFirebase(EventID.GMS_PAM_INTERSTITIAL, pam);
            } else if ("rewarded".equals(ad_format)) {
                Bundle pam = new Bundle();
                pam.putDouble("Rewarded_Ad_Impression_Value", revenue * 1000);
                mEventHandler.getEventLogger().logToFirebase(EventID.GMS_PAM_REWARDED, pam);
            }

        } catch (Throwable t) {
            // ignore
        }
    }

    public void onAdLoadSuccess() {
        markAdLoadDuration(false);
        this.totalLoadSuccessCount++;
        // reset the load faile count
        this.totalLoadFailedCount = 0;

        this.latestLoadTimeMS = System.currentTimeMillis() - this.mLoadStartTime;
        this.mSleepEndTimes = System.currentTimeMillis() + SLEEP_LOADED_SUCCESS;

        this.mUiHandler.post(() -> {
            mAdapterStatus = STATUS_LOADED_SUCCESS;
            mLoadSuccessTime = System.currentTimeMillis();
            mFetchedSuccessIndex++;
            // mark this adapter loadsuccess after timeout

//            Logger.debug(BaseAdapter.TAG, "%s Load success", BaseAdapter.this.getName());

            if (mFetchCallback != null) {
                mFetchCallback.adLoadSuccess(BaseAdapter.this);
            } else {
//                Logger.warning(BaseAdapter.TAG, "%s has no fetchCallback", BaseAdapter.this.getName());
            }
            if (BaseAdapter.this.mEventHandler != null) {
                BaseAdapter.this.mEventHandler.onAdLoadSuccessCalled(BaseAdapter.this);
            }
        });
    }

    private String lastAdLoadFailedReason = null;

    public String getLastAdLoadFailedReason() {
        return this.lastAdLoadFailedReason;
    }

    public void onAdLoadFailed(final String failReason) {
        markAdLoadDuration(false);
        this.lastAdLoadFailedReason = failReason;
        this.latestLoadTimeMS = Integer.MAX_VALUE;
        this.totalLoadFailedCount++;
        if (IvyLoadStatus.NO_FILL.equals(failReason)) {
            mSleepEndTimes = System.currentTimeMillis() + SLEEP_LOADED_NOFILL * totalLoadFailedCount;
        } else {
            mSleepEndTimes = System.currentTimeMillis() + SLEEP_LOADED_OTHER_ERROR * totalLoadFailedCount;
        }
        checkPerformanceOnAdLoadFailed();
        this.mUiHandler.post(() -> {
            mAdapterStatus = STATUS_LOADED_FAILED;
//            Logger.warning(TAG, "[%s] %s Load failed, reason: %s", mAdType.name(), BaseAdapter.this.getName(), failReason);

            if (mFetchCallback != null) {
                BaseAdapter.this.mFetchCallback.adLoadFailed(BaseAdapter.this);
            } else {
//                Logger.warning(BaseAdapter.TAG, "%s has no fetchCallback", BaseAdapter.this.getName());
            }
            if (BaseAdapter.this.mEventHandler != null) {
                BaseAdapter.this.mEventHandler.onAdLoadFailCalled(BaseAdapter.this, failReason);
                return;
            }
        });
    }

    public void onAdShowSuccess() {
        mAdapterStatus = STATUS_SHOWN_SUCCESS;

        this.mSleepEndTimes = System.currentTimeMillis() + SLEEP_DISPLAYED;
        this.totalShowSuccessCount++;
        this.totalShowFailCount = 0;

        this.mUiHandler.post(() -> {
            if (BaseAdapter.this.mAdOpenCloseCallback != null) {
                BaseAdapter.this.mAdOpenCloseCallback.onAdShown(BaseAdapter.this);
                if (BaseAdapter.this.mEventHandler != null) {
                    BaseAdapter.this.mEventHandler.onAdShowSuccessCalled(BaseAdapter.this);
                }
            }
        });
    }

    public void onAdShowFail() {
        Logger.debug( "Adapter " + mGridName + " show failed");
        mAdapterStatus = STATUS_SHOWN_FAILED;
        this.totalShowFailCount++;

        checkPerformance();
        this.mUiHandler.post(() -> {

            if (BaseAdapter.this.mAdOpenCloseCallback != null) {
                BaseAdapter.this.mAdOpenCloseCallback.onAdShownFail(BaseAdapter.this.getAdType());
                if (BaseAdapter.this.mEventHandler != null) {
                    BaseAdapter.this.mEventHandler.onAdShowFailCalled(BaseAdapter.this);
                    return;
                }
                return;
            }
//            Logger.warning(BaseAdapter.TAG, "%s Got callback from ad provider but no listener is registered. Doing nothing", BaseAdapter.this.getName());
        });
    }

    public void onAdClicked() {
        this.totalClickCount++;

        checkPerformance();
        this.mUiHandler.post(() -> {
            if (mAdOpenCloseCallback != null) {
                mAdOpenCloseCallback.onAdClicked(BaseAdapter.this);
                if (BaseAdapter.this.mEventHandler != null) {
                    BaseAdapter.this.mEventHandler.onAdClickCalled(BaseAdapter.this);
                }
                return;
            }
//            Logger.warning(BaseAdapter.TAG, "%s Got callback from ad provider but no listener is registered. Doing nothing", BaseAdapter.this.getName());
        });
    }

    public BaseAdapter enableIBAMode(String networkName) {
        this.mIBAMode = true;
        setNetworkName(networkName);
        return this;
    }

    public boolean isIBAMode() {
        return this.mIBAMode;
    }


    public boolean isTestMode() {
        return this.mIsTestMode;
    }

    public void setTestMode(boolean isTestMode) {
        this.mIsTestMode = isTestMode;
    }

    public boolean isDebugMode() {
        return this.mIsDebugMode;
    }

    public void setDebugMode(boolean isDebugMode) {
        this.mIsDebugMode = isDebugMode;
    }

    public void setAdSummaryEventHandler(AdSummaryEventHandler adSummaryEventHandler) {
        this.mAdSummaryEventHandler = adSummaryEventHandler;
    }

    public void onAdClosed(final boolean isReward) {
//        Logger.debug(TAG, "onAdClosed: " + isReward);
        this.mAdapterStatus = STATUS_CLOSED;
        this.mUiHandler.post(new Runnable() {
            public void run() {
                if (mAdOpenCloseCallback != null) {
                    mAdOpenCloseCallback.onAdClosed(BaseAdapter.this, isReward);
                    if (mEventHandler != null) {
                        mEventHandler.onAdClosedCalled(BaseAdapter.this, isReward);
                    }
                    return;
                }
//                Logger.warning(BaseAdapter.TAG, "%s Got callback from ad provider but no listener is registered. Doing nothing", BaseAdapter.this.getName());
            }
        });
    }

    public GridParams getGridParams() {
        if (this.mGridParams == null) {
            this.mGridParams = newGridParams();
        }
        return this.mGridParams;
    }

    public void setWeight(int weight) {
//        Logger.debug(TAG, "Update adapter weights: " + getName() + " --> new weight: " + weight);
        this.mSelectedWeight = weight;
    }

    public void setGridParams(JSONObject gridParams) {
        if (gridParams != null) {
            this.mGridParams = getGridParams().fromJSON(gridParams);
        } else {
        }
    }

    public void resetOperationCount() {
    }

    private CountDownTimer countDownTimer = null;

    protected void markAdLoadDuration(boolean loading) {
        if ("adsfall".equals(getNetworkName())) {
            return;
        }
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long l) {
                }

                @Override
                public void onFinish() {
                    if (BaseAdapter.this.mEventHandler != null) {
                        BaseAdapter.this.mEventHandler.onAdLoadTimeOut(BaseAdapter.this);
                    }
                }
            };
        }
        if (loading) {
            countDownTimer.start();
        } else {
            countDownTimer.cancel();
        }
    }

    public String getNetworkName() {
        return this.networkName;
    }

    public BaseAdapter setNetworkName(String networkName) {
        this.networkName = networkName;
        return this;
    }

    public int getAdProviderAgeLimit() {
        return this.adProviderAgeLimit;
    }

    public void setAdProviderAgeLimit(int adProviderAgeLimit) {
        this.adProviderAgeLimit = adProviderAgeLimit;
    }

    private boolean isGridParamsNull(GridParams gridParams, Field field) {
        try {
            if (field.get(gridParams) == null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public boolean checkGridParams() {
        GridParams gridParams = getGridParams();
        if (gridParams == null) {
            return true;
        }
        if (gridParams.isValid()) {
            return true;
        }
        return false;
    }

    public IvyShowFailStatus getShowStatus() {
        if (this.mShowStatus == null) {
            return IvyShowFailStatus.OTHER;
        }
        return this.mShowStatus;
    }

    public void setShowFailStatus(IvyShowFailStatus status) {
        this.mShowStatus = status;
    }

    protected void addEventParams(String key, String value) {
        eventParams.put(key, value);
    }

    public Map<String, String> getEventParams() {
        return eventParams;
    }

    protected void resetEventParams() {
        this.eventParams.clear();
    }

    public static abstract class GridParams {
        public String toString() {
            return "" + getClass().getName() + "{" + getParams() + "}@" + hashCode();
        }

        public boolean isValid() {
            return true;
        }

        public GridParams fromJSON(JSONObject jsonObject) {
            return this;
        }

        protected String getParams() {
            return "";
        }
    }
}
