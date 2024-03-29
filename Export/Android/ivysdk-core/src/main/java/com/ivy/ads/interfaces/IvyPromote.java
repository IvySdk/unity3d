package com.ivy.ads.interfaces;


public interface IvyPromote extends IvyAd {
  void showPromoteAd(String tag);

  void showDeliciousIcon(int x, int y, int width, int height, String promotepackage);

  void closeDeliciousIconAd();

  void closeDeliciousBanner();

  void showDeliciousBanner(int x, int y, int w, int h, String defaultpackage);

  void showDeliciousAd();

  void onQuit();

  void moreGame();
}
