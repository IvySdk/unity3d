package com.ivy.ads.adapters;

import android.content.Context;

import com.ivy.ads.interfaces.IvyAdType;

public abstract class FullpageAdapter<T extends BaseAdapter.GridParams> extends BaseAdapter<T> {
  public FullpageAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  @Override
  public String getMediation() {
    return null;
  }

}
