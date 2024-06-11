package com.ivy.ads.adproviders;

import android.app.Activity;

import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.ads.interfaces.IvyAdType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class AdProvidersRegistry {
  private final Map<IvyAdType, Map<String, BaseAdapter>> mAdProvidersRegistry = new HashMap<>();

  public AdProvidersRegistry() {
    this.mAdProvidersRegistry.put(IvyAdType.INTERSTITIAL, new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER));
    this.mAdProvidersRegistry.put(IvyAdType.REWARDED, new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER));
    this.mAdProvidersRegistry.put(IvyAdType.BANNER, new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER));
    this.mAdProvidersRegistry.put(IvyAdType.NATIVE_AD, new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER));
    this.mAdProvidersRegistry.put(IvyAdType.REWARDED_INTERSTITIAL, new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER));
  }

  public Map<String, BaseAdapter> getAdProvidersMap(IvyAdType type) {
    return this.mAdProvidersRegistry.get(type);
  }

  public Map<IvyAdType, Map<String, BaseAdapter>> getAdProviders() {
    return this.mAdProvidersRegistry;
  }

  public void onResume(Activity activity) {
    for (Map<String, BaseAdapter> adaptersMap : this.mAdProvidersRegistry.values()) {
      for (BaseAdapter adapter : adaptersMap.values()) {
        if (adapter.isSetupDone()) {
          adapter.onResume(activity);
        }
      }
    }
  }

  public void onPause(Activity activity) {
    for (Map<String, BaseAdapter> adaptersMap : this.mAdProvidersRegistry.values()) {
      for (BaseAdapter adapter : adaptersMap.values()) {
        if (adapter.isSetupDone()) {
          adapter.onPause(activity);
        }
      }
    }
  }

  public void onDestroy(Activity activity) {
    for (Map<String, BaseAdapter> adaptersMap : this.mAdProvidersRegistry.values()) {
      for (BaseAdapter adapter : adaptersMap.values()) {
        if (adapter.isSetupDone()) {
          adapter.onDestroy(activity);
        }
      }
    }
  }
}
