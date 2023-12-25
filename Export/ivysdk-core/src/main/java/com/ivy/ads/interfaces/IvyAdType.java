package com.ivy.ads.interfaces;

public enum IvyAdType {
  BANNER(0),
  INTERSTITIAL(1),
  REWARDED(2),
  NATIVE_AD(3),
  PROMOTE(4),
  REWARDED_INTERSTITIAL(5);

  private final int mAdType;

  IvyAdType(int adType) {
    this.mAdType = adType;
  }
}
