package com.ivy.ads.selectors;

import android.app.Activity;

import com.ivy.ads.adapters.BaseAdapter;
import com.ivy.ads.configuration.BaseConfig;

import java.util.List;

public interface AdSelector<T extends BaseConfig> {
  void forceStopSelector();

  BaseAdapter selectAd(Activity activity, T t, List<BaseAdapter> list);

  BaseAdapter getReadyAdapter(List<BaseAdapter> list);

  void setDebugMode(boolean z);

}
