package com.android.client;


public class AdEventListener {
  public static final int AD_TYPE_FULL = 1;
  public static final int AD_TYPE_VIDEO = 2;
  public static final int AD_TYPE_BANNER = 3;
  // ios has AD_TYPE_ICON = 4;
  public static final int AD_TYPE_NATIVE = 5;
  public static final int AD_TYPE_GIF_ICON = 6;

  public void onAdShow(String tag, String platform, int type) {
  }

  public void onAdClicked(String tag, String platform, int type) {
  }
}
