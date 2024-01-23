//package com.android.client;
//
//import android.app.Activity;
//import android.view.ViewGroup;
//
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdSize;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.LoadAdError;
//import com.ivy.util.Logger;
//
//public class AdmobRectBanner extends AdListener {
//  private static final String TAG = "AdmobRectBanner";
//  private AdView mAdview;
//
//  public void close(Activity activity) {
//    try {
//      ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
//      decorView.removeView(this.mAdview);
//
//      this.mAdview.destroy();
//      this.mAdview = null;
//    } catch(Throwable t) {
//      t.printStackTrace();
//    }
//  }
//
//  public void show(Activity activity, String adId, int x, int y) {
//    try {
//      int screenWidth = activity.getResources().getDisplayMetrics().widthPixels;
//      int screenHeight = activity.getResources().getDisplayMetrics().heightPixels;
//
//      if (this.mAdview != null) {
//        this.mAdview.destroy();
//        this.mAdview = null;
//      }
//      this.mAdview = new AdView(activity);
//      this.mAdview.setAdUnitId(adId);
//      this.mAdview.setAdSize(AdSize.MEDIUM_RECTANGLE);
//
//      this.mAdview.setAdListener(this);
//
//      AdRequest adRequest = new AdRequest.Builder().build();
//      this.mAdview.loadAd(adRequest);
//
//      ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
//      this.mAdview.setTag("_exit_admob_banner");
//      this.mAdview.setTranslationX(x);
//      this.mAdview.setTranslationY(y);
////  this.mAdview.setPivotX(0);
////  this.mAdview.setPivotY(0);
////  this.mAdview.setX(x);
////  this.mAdview.setY(y);
////  ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-2, -2);
//      decorView.addView(this.mAdview);
//    } catch(Throwable t) {
//      t.printStackTrace();
//    }
//  }
//
//  @Override
//  public void onAdClosed() {
//    Logger.debug(TAG, "onAdClosed");
//  }
//
//  @Override
//  public void onAdFailedToLoad(LoadAdError var1) {
//    Logger.debug(TAG, "onAdFailedToLoad");
//  }
//
//  @Override
//  public void onAdOpened() {
//    Logger.debug(TAG, "onAdOpened");
//  }
//
//  @Override
//  public void onAdLoaded() {
//    Logger.debug(TAG, "onAdLoaded");
//  }
//
//  @Override
//  public void onAdClicked() {
//    Logger.debug(TAG, "onAdClicked");
//  }
//
//  @Override
//  public void onAdImpression() {
//    Logger.debug(TAG, "onAdImpression");
//  }
//}
