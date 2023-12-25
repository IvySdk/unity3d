package com.ivy.ads.managers;

import android.app.Activity;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.ivy.ads.adapters.AdOpenCloseCallback;
import com.ivy.ads.adapters.AdsfallNonRewardedAdapter;
import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.ads.adproviders.AdProvidersRegistry;
import com.ivy.ads.configuration.BaseConfig;
import com.ivy.ads.configuration.ConfigurationParser;
import com.ivy.ads.configuration.PromoteConfig;
import com.ivy.ads.events.BaseEventHandler;
import com.ivy.ads.interfaces.IvyAd;
import com.ivy.ads.interfaces.IvyAdCallbacks;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.models.AdProviderGridDetails;
import com.ivy.ads.models.AdProviderGridPayload;
import com.ivy.ads.selectors.AdSelector;
import com.ivy.ads.selectors.AdSelectorCallback;
import com.ivy.ads.selectors.AdapterSkipReason;
import com.ivy.ads.summary.AdSummaryEventHandler;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class CommonAdManager<T extends BaseConfig> implements IvyAd {
  private static final String TAG = CommonAdManager.class.getCanonicalName();
  private final Map<String, BaseAdapter> mAdProvidersMap;
  private final AdSelector mAdSelector;
  private final IvyAdType mAdType;
  private final ConfigurationParser mConfigurationParser;
  private final BaseEventHandler mEventHandler;
  private final Handler mHandler;
  private final Handler mUiHandler;
  protected Activity mActivity;
  private AdSummaryEventHandler mAdSummaryEventHandler;
  private IvyAdCallbacks mCallback;
  private List<BaseAdapter> mGridAndRegisteredProviders = new LinkedList<>();
  private AdOpenCloseCallback mInternalCallback;
  private boolean mIsDebugMode;
  private boolean mIsTestMode;

  public CommonAdManager(Activity activity, ConfigurationParser configurationParser, AdSelector adSelector, AdProvidersRegistry adProvidersRegistry, Handler uiHandler, Handler handler, IvyAdType adType, BaseEventHandler eventHandler, AdSummaryEventHandler adSummaryEventHandler) {
    this.mActivity = activity;
    this.mConfigurationParser = configurationParser;
    this.mAdSelector = adSelector;
    this.mAdProvidersMap = adProvidersRegistry.getAdProvidersMap(adType);
    this.mUiHandler = uiHandler;
    this.mAdType = adType;
    this.mEventHandler = eventHandler;
    this.mHandler = handler;
    this.mAdSummaryEventHandler = adSummaryEventHandler;
  }

  public abstract List<JSONObject> getGridProviderList();

  public abstract Class<T> getManagerConfigClass();


  public void onResume(Activity activity) {
  }

  public void onPause(Activity activity) {
  }

  public boolean isDebugMode() {
    return this.mIsDebugMode;
  }

  protected void setDebugMode(boolean isDebugMode) {
    this.mIsDebugMode = isDebugMode;
    for (BaseAdapter adapter : this.mGridAndRegisteredProviders) {
      adapter.setDebugMode(isDebugMode);
    }
    if (this.mAdSelector != null) {
      this.mAdSelector.setDebugMode(isDebugMode);
    }
  }

  protected void setTestMode(boolean isTestMode) {
    this.mIsTestMode = isTestMode;
    for (BaseAdapter adapter : this.mGridAndRegisteredProviders) {
      adapter.setTestMode(isTestMode);
    }
  }

  public void setupAdProviders() {
    getManagerHandler().post(new Runnable() {
      public void run() {
        try {
          List<JSONObject> gridProviderList = getGridProviderList();
          if (gridProviderList != null) {

            mGridAndRegisteredProviders = new ArrayList<>(intersectGridAndRegisteredProviders(gridProviderList).values());

            setTestMode(CommonAdManager.this.mIsTestMode);
            setDebugMode(CommonAdManager.this.mIsDebugMode);
            if (Logger.isDebugEnabled()) {
              Logger.debug(CommonAdManager.TAG, "[setupAdProviders] Grid and registered providers intersected list for %s: %s", CommonAdManager.this.getAdType(), Arrays.toString(CommonAdManager.this.mGridAndRegisteredProviders.toArray()));
            }
            setupProviders();
          } else {
            Logger.error(TAG, "gridProviderList is empty");
          }
        } catch(Throwable t) {
          t.printStackTrace();
        }
      }
    });
  }

  public BaseEventHandler getEventHandler() {
    return this.mEventHandler;
  }

  public Map<String, BaseAdapter> intersectGridAndRegisteredProviders(List<JSONObject> gridAdProviders) {
    Map<String, BaseAdapter> gridAndRegisteredProviders = new LinkedHashMap<>();
    for (int gridIndex = 0; gridIndex < gridAdProviders.size(); gridIndex++) {
      JSONObject gridAdProvider = gridAdProviders.get(gridIndex);
      BaseAdapter adapter = processGridAdProvider(gridAdProvider, gridIndex);
      if (adapter != null) {
        gridAndRegisteredProviders.put(gridAdProvider.optString("provider"), adapter);
      } else {
        Logger.debug(TAG, "provider " + gridAdProvider.optString("provider") + " NOT FOUND!");
      }
    }
    return gridAndRegisteredProviders;
  }

  private BaseAdapter processGridAdProvider(JSONObject gridAdProvider, int gridIndex) {
    AdProviderGridPayload providerPayload = AdProviderGridPayload.getAdProviderGridPayload(gridAdProvider);
    if (providerPayload == null) {
      return null;
    }
    BaseAdapter adapter;
    if (providerPayload.details.integrationType != AdProviderGridDetails.IntegrationType.s2s) {
      adapter = getAdProvidersMap().get(providerPayload.details.integrationData.sdkId);
      if (adapter == null) {
        Logger.warning(TAG, "BE sent an unknown %s provider: %s", getAdType(), providerPayload.details.integrationData.sdkId);
        return null;
      }
      JSONObject placement = providerPayload.placement;
      adapter.setCountrySpecified(providerPayload.providerPayloadJson);
      adapter.setGridParams(placement);
      if (placement.has("network")) {
        adapter.setNetworkName(placement.optString("network"));
      }
    } else {
      String s2sProviderMapKeyName =  "<=>" + providerPayload.details.integrationData.templateName;
      adapter = getAdProvidersMap().get(s2sProviderMapKeyName);

      if (adapter == null) {
        return null;
      }
      adapter.setGridParams(providerPayload.providerPayloadJson);
    }

    adapter.setAdSummaryEventHandler(this.mAdSummaryEventHandler);
    adapter.setUiHandler(this.mUiHandler);
    adapter.setGridIndex(gridIndex);
    adapter.setEventHandler(getEventHandler());
    if (providerPayload.adRequestTimeoutOverride != null) {
      adapter.setAdProviderAgeLimit(providerPayload.adRequestTimeoutOverride.adProviderAgeLimit);
    }
    if (providerPayload.adRequestTimeoutOverride != null) {
      adapter.setCoppaDynamic(providerPayload.adRequestTimeoutOverride.adCoppaDynamic);
    }
    if ("".equals(adapter.getPlacementId())) {
      adapter.skipFetch(AdapterSkipReason.NO_PLACEMENT_FAIL);
    }
    if (adapter.checkGridParams()) {
      if (providerPayload.adRequestTimeoutOverride == null) {
        return adapter;
      }
      adapter.setTimeoutOverride(providerPayload.adRequestTimeoutOverride.adRequestTimeoutSeconds);
      return adapter;
    }
    Logger.error(TAG, "Adapter %s failed grid params check!", adapter.getName());
    return null;
  }

  protected void setupProviders() {
    for (final BaseAdapter adapter : this.mGridAndRegisteredProviders) {
      getUiHandler().post(new Runnable() {
        public void run() {
          if (!adapter.isSetupDone()) {
            adapter.setup(getActivity());
            adapter.markSetupDone();
          }
        }
      });
    }
  }

  public List<BaseAdapter> getGridAndRegisteredProviders() {
    return this.mGridAndRegisteredProviders;
  }

  public void setGridAndRegisteredProviders(List<BaseAdapter> mGridAndRegisteredProviders) {
    this.mGridAndRegisteredProviders = mGridAndRegisteredProviders;
  }

  protected ConfigurationParser getConfigurationParser() {
    return this.mConfigurationParser;
  }

  protected T getManagerConfig() {
    return getConfigurationParser().getConfig(getManagerConfigClass());
  }

  public PromoteConfig getPromiteConfig() {
    return getConfigurationParser().getConfig(PromoteConfig.class);
  }

  @Nullable
  public IvyAdCallbacks getCallback() {
    return this.mCallback;
  }

  public void setCallback(IvyAdCallbacks callback) {
    this.mCallback = callback;
  }

  AdOpenCloseCallback getInternalCallback() {
    return this.mInternalCallback;
  }

  void setInternalCallback(AdOpenCloseCallback callback) {
    this.mInternalCallback = callback;
  }

  public Activity getActivity() {
    return this.mActivity;
  }

  public Handler getManagerHandler() {
    return this.mHandler;
  }

  public Handler getUiHandler() {
    return this.mUiHandler;
  }

  AdSelector getAdSelector() {
    return this.mAdSelector;
  }

  public Map<String, BaseAdapter> getAdProvidersMap() {
    return this.mAdProvidersMap;
  }

  public IvyAdType getAdType() {
    return this.mAdType;
  }

  public AdSummaryEventHandler getAdSummaryEventHandler() {
    return this.mAdSummaryEventHandler;
  }

  public void showAdsFallInterstitial(final Activity activity) {
    final AdsfallNonRewardedAdapter offlineNonRewardedAdapter = (AdsfallNonRewardedAdapter) this.getAdProvidersMap().get("adsfall");
    if (offlineNonRewardedAdapter != null) {
      offlineNonRewardedAdapter.setPromoteConfig(this.getPromiteConfig());
      offlineNonRewardedAdapter.resetOperationCount();
      offlineNonRewardedAdapter.setEventHandler(this.getEventHandler());
    }
    this.mUiHandler.post(new Runnable() {
      public void run() {
        if (offlineNonRewardedAdapter != null) {
          offlineNonRewardedAdapter.fetch(activity, new AdSelectorCallback() {
            public void adLoadSuccess(BaseAdapter adapter) {
              Logger.debug(TAG, "Offline interstitial fetched. Now showing it");
              offlineNonRewardedAdapter.show(activity, (NonRewardedAdManager)CommonAdManager.this);
            }

            public void adLoadFailed(BaseAdapter adapter) {
              Logger.debug(TAG, "Offline interstitial fetch failed. Probably missing creative");
            }
          });
        }
      }
    });
  }

}
