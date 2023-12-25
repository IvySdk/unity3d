package com.ivy.ads.adapters;

import com.ivy.ads.interfaces.IvyAdInfo;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyAdapterInfo;

public interface AdOpenCloseCallback {
  void onAdClicked(IvyAdapterInfo o7AdapterInfo);

  void onAdClosed(IvyAdapterInfo o7AdapterInfo, boolean z);

  void onAdShown(IvyAdapterInfo o7AdapterInfo);

  void onAdShownFail(IvyAdType o7AdType);

  void softPause(IvyAdInfo o7AdInfo);

  void softResume(IvyAdInfo o7AdInfo);
}
