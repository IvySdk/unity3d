package com.ivy.ads.selectors;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.ivy.IvyUtils;
import com.ivy.Logger;
import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.ads.configuration.BaseConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAdType;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 瀑布流选择
 * <p>
 * 默认:
 * force: 强制默认，强制唤醒所有在30s内会苏醒的adapter
 * quick: 根据广告show的事件，快速选择最近加载快的adapter, quick模式将最近展示
 *
 * @param <T>
 */
public abstract class WaterfallAdSelector<T extends BaseConfig> implements AdSelectorCallback, AdSelector<T> {
  private static final String TAG = "AdSelector";
  private static final String SP_KEY = "as_sp";
  private static final int MIN_WATERFALL_LENGTH = 5;
  private final IvyAdType mAdType;
  private final Lock mLock = new ReentrantLock();
  private final Condition mCond = this.mLock.newCondition();
  private final Context mContext;
  private final Handler mUiHandler;
  private boolean isSelectorForceStopped;
  private boolean mCycleBanners = false;
  private BaseEventHandler mEventHandler;
  private boolean mIsDebugMode;
  private volatile BaseAdapter mCurrentLoadedAdapter = null;
  private volatile BaseAdapter mCurrentLoadingAdapter = null;

  private List<BaseAdapter> mPreviouslyLoadedAdapterList = new ArrayList<>();

  // 当前用户能够够到的对应平台的最佳数据(可以乐观观点), 默认值配置在配置文件里。
  //    大过滤将会将同平台的ecpm高于此值的都过滤调
  //    除非是grid文件制定，否则，我们都取最高层的价格，作为起始值
  // 更新规则:
  //    1. 每次广告加载成功，将用户本平台的锚定的价格向上移一个单位, 如admob, 加载成功了1.2, 那么我们就将其锚定价格向上找个admob的，看是啥价格，比如是1.3,那我们就设置为1.3
  //    2. 每次连续两次广告相同network加载失败，我们将用户本平台的锚定价格，下探1位，比如admob ecpm 1的失败了两次了，我们向下找个admob, 看他啥价格, 比如0.7, 那我们就锚定价格为0.7
  private int waterfallIndex = 0;

  // 上一次selectAd接口的调用时间
  private long lastSelectAdTime = 0L;

  public WaterfallAdSelector(IvyAdType adType, Handler uiHandler, Context context, BaseEventHandler eventHandler) {
    this.mUiHandler = uiHandler;
    this.mAdType = adType;
    this.mContext = context.getApplicationContext();
    this.mEventHandler = eventHandler;
    this.waterfallIndex = 0;
  }

  protected abstract long getTimeout(T t);

  @Override
  public BaseAdapter getReadyAdapter(List<BaseAdapter> gridAndRegisteredProviders) {
    if (gridAndRegisteredProviders == null || gridAndRegisteredProviders.size() == 0) {
      return null;
    }
    try {
      int index = 0;
      int totalSize = gridAndRegisteredProviders.size();
      while (index < totalSize) {
        final BaseAdapter adapter = gridAndRegisteredProviders.get(index);
        if (adapter.isReadyForSelect()) {
          mCurrentLoadedAdapter = adapter;
          Logger.debug( adapter.getName() + " getReadyAdapter");
          return adapter;
        }
        index++;
      }
    } catch (Throwable t) {
      // IGNORE
    }
    return null;
  }

  @Nullable
  public BaseAdapter selectAd(Activity activity, T config, List<BaseAdapter> gridAndRegisteredProviders) {
    if (gridAndRegisteredProviders.size() == 0) {
      Logger.debug( "No adapter for " + mAdType);
      return null;
    }

    this.isSelectorForceStopped = false;
    this.waterfallIndex++;
    this.lastSelectAdTime = System.currentTimeMillis();
    if (IvyUtils.isOnline(this.mContext)) {
      BaseAdapter adProvider;
      adProvider = fetchAd(activity, config, gridAndRegisteredProviders, false);
      if (adProvider == null) {
        // not fetch a valid ad, reset all to fetch again
        this.mPreviouslyLoadedAdapterList.clear();
        Logger.debug( "!!! Try Again, reset the force skip, and awake all");
        //reset it
        adProvider = fetchAd(activity, config, gridAndRegisteredProviders, true);
        return adProvider;
      }

      this.mPreviouslyLoadedAdapterList.add(adProvider);
      if (this.mPreviouslyLoadedAdapterList.size() == gridAndRegisteredProviders.size()) {
        Logger.debug( "All available ad cycled, reset");
        this.mPreviouslyLoadedAdapterList.clear();
      }
      return adProvider;
    }
    Logger.debug( "We are offline. Doing nothing...");
    return null;
  }

  /**
   * 加载成功，记录此adapter的ecpm值，如果超过当前记录，更新记录
   *
   * @param adapter
   */
  public void adLoadSuccess(BaseAdapter adapter) {
    if (adapter == null) {
      Logger.error(TAG, "wrong adapter notify, adapter is null at adLoadSuccess");
      return;
    }
    Logger.debug( "Great, " + mAdType.name() + adapter.getName() + " notify loaded success");
    this.mLock.lock();
    try {
      this.mCurrentLoadedAdapter = adapter;
      this.mCond.signal();
    } finally {
      this.mLock.unlock();
    }
  }

  public void adLoadFailed(BaseAdapter adapter) {
    if (adapter == null) {
      Logger.error(TAG, "wrong adapter notify, adapter is null at adLoadFailed");
      return;
    }

    if (mCurrentLoadingAdapter != null) {
      if (!mCurrentLoadingAdapter.getName().equals(adapter.getName())) {
        Logger.debug( "Previous adapter loaded failed, ignore this message, should not awake");
        return;
      }
    }
    this.mLock.lock();
    try {
      this.mCond.signal();
    } finally {
      this.mLock.unlock();
    }
  }

  public Handler getUiHandler() {
    return this.mUiHandler;
  }

  protected void setCycleBanners(boolean cycleBanners) {
    this.mCycleBanners = cycleBanners;
  }

  public void setDebugMode(boolean isDebugMode) {
    this.mIsDebugMode = isDebugMode;
  }

  public void forceStopSelector() {
    Logger.debug( "forceStopSelector");
    this.isSelectorForceStopped = true;
  }

  @Nullable
  private BaseAdapter fetchAd(final Activity activity, T config, List<BaseAdapter> gridAndRegisteredProviders, boolean force) {
    Logger.debug( "fetch new " + mAdType.name() + " started");

    long waterfallStartTime = System.currentTimeMillis();

    BaseAdapter adProvider = null;
    this.mLock.lock();
    int index = 0;

    long waitingLoadingTimeout = getTimeout(config);

    mCurrentLoadingAdapter = null;

    // quick look any LOADED status, and service with this adapter quickly
    if (mAdType != IvyAdType.BANNER && mAdType != IvyAdType.NATIVE_AD) {
      while (index < gridAndRegisteredProviders.size()) {
        final BaseAdapter adapter = gridAndRegisteredProviders.get(index);
        if (adapter.isReadyForSelect()) {
          mCurrentLoadedAdapter = adapter;
          Logger.debug( adapter.getName() + " is loaded, fulfill this request with this adapter");
          adapter.resetOperationCount();
          this.mLock.unlock();

          return adapter;
        }
        index++;
      }
    }

    this.mCurrentLoadedAdapter = null;
    index = 0;

    String debugMsg;
    try {
      Logger.debug( "[WATERFALL] waterfall started");

      while (index < gridAndRegisteredProviders.size()) {
        final BaseAdapter adapter = gridAndRegisteredProviders.get(index);

        // 1. adapter如果正在请求， 忽略，60s自动超时
        if (adapter.isFetching()) {
          index++;
          Logger.debug( "Adapter " + adapter.getName() + " is still in fetching, try next adapter");
          continue;
        }

        // 2. adapter正在休眠期，直接跳过高
        if (adapter.isSleeping()) {
          index++;
          continue;
        }

        if (this.mCycleBanners && this.mPreviouslyLoadedAdapterList.contains(adapter)) {
          adapter.skipFetch(AdapterSkipReason.DEBUG_FAIL);
          Logger.debug( "[WATERFALL] waterfall skipped " + adapter.getName() + ": cycle banners");
        } else if (!force && adapter.getSelectedWeight() == 0) {
          adapter.skipFetch(AdapterSkipReason.SKIP_ZERO_WEIGHT);
          Logger.debug( "[WATERFALL] waterfall skipped " + adapter.getName() + ": zero weight");
          Logger.debug( "Adapter " + adapter.getName() + " disable by zero weight");
        } else if (adapter.isForceSkippedByCountry()) {
          adapter.skipFetch(AdapterSkipReason.SKIP_BY_COUNTRY);
          Logger.debug( "[WATERFALL] waterfall skipped " + adapter.getName() + ": country specified");
        } else if (force || !adapter.isForceSkipped()) {
          debugMsg = adapter.getName() + ":" + this.mAdType + " trying to load";
          Logger.debug( "[WATERFALL] waterfall skipped " + adapter.getName() + ": force skipped");
          adapter.resetOperationCount();

          Logger.debug( "Putting " + adapter.getName() + " in loading queue");
          this.mCurrentLoadingAdapter = adapter;

          getUiHandler().post(() -> adapter.fetch(activity, WaterfallAdSelector.this));

          Logger.debug( "Adapter " + adapter.getName() + " waiting " + waitingLoadingTimeout + " seconds for the loading result...");
          if (!this.mCond.await(waitingLoadingTimeout, TimeUnit.SECONDS)) {
            debugMsg = adapter.getName() + ":" + this.mAdType + " timed out after " + getTimeout(config) + "s.";
            adapter.timeout();

          } else if (this.mCurrentLoadedAdapter != null) {
            Logger.debug( "We are loading " + adapter.getName());
            Logger.debug( this.mCurrentLoadedAdapter.getName() + " reported loaded success");

            adProvider = this.mCurrentLoadedAdapter;
            debugMsg = "GREAT ! " + adProvider.getName() + " : " + this.mAdType + " loaded";

            Logger.debug( debugMsg);
            break;
          } else {
            debugMsg = adapter.getName() + ":" + this.mAdType + " failed to load";
            Logger.debug( debugMsg);
          }
        } else {
          String reason = adapter.getForceSkippedReason();
          adapter.skipFetch(reason);

          Logger.debug( "Waterfall skipped: " + reason);
        }
        index++;
      }
      this.mLock.unlock();
    } catch (Throwable th) {
      this.mLock.unlock();
    }

    if (adProvider == null && gridAndRegisteredProviders.size() != 0) {
      debugMsg = "All " + this.mAdType + " ad providers in waterfall returned no fill. ";
      Logger.debug( debugMsg + gridAndRegisteredProviders);
    } else if (gridAndRegisteredProviders.size() == 0) {
      debugMsg = "For " + this.mAdType + ", there are no providers registered";
      Logger.warn( debugMsg);
    } else {
      Logger.debug( "Fulfill " + mAdType.name() + " this ad with: " + adProvider.getName());
    }

    return adProvider;
  }
}
