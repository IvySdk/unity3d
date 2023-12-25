package com.ivy.ads.interfaces;

/**
 * 广告通用事件。
 */
public interface IvyAdCallbacks {
  void onAdClicked(IvyAdInfo adInfo);

  void onAdClosed(IvyAdInfo adInfo, boolean gotReward);

  void onAdLoadFail(IvyAdType adInfo);

  void onAdLoadSuccess(IvyAdInfo adInfo);

  void onAdShowFail(IvyAdType adInfo);

  void onAdShowSuccess(IvyAdInfo adInfo);
}
