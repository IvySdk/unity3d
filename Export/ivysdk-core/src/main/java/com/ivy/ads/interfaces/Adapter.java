package com.ivy.ads.interfaces;

import android.content.Context;

import com.ivy.ads.adapters.BaseAdapter;

public interface Adapter<T extends BaseAdapter.GridParams> extends IvyAdapterInfo {

  Context getApplicationContext();

  String getNetworkName();

  String getPlacementId();

  boolean isDebugMode();

  boolean isIBAMode();

  boolean isTestMode();
}