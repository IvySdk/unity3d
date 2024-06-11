package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ivy.R;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.utils.Stopwatch;

public abstract class BannerAdapter<T extends BannerAdapter.GridParams> extends BaseAdapter<T> {
  public static int SMART_BANNER_WIDTH = -1;
  public static int MATCH_PARENT_WIDTH = -2;

  public static int STANDARD_BANNER_HEIGHT = 50;

  private boolean isSmartBannerEnabled = true;
  private Stopwatch mStopwatch = new Stopwatch(getClass().getSimpleName());

  protected ViewGroup mBannerContainer;
  protected boolean isBannerHide = true;

  public BannerAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
    this.isSmartBannerEnabled = context.getResources().getBoolean(R.bool.isSmartBannerEnabled);
  }

  public abstract View getView();

  public void render(ViewGroup bannerContainer) {

  }

  public void show(Activity activity, AdOpenCloseCallback adOpenCloseCallback) {
    super.show(activity, adOpenCloseCallback);
    isBannerHide = false;
    this.mStopwatch.resume();
  }

  public void setBannerContainer(ViewGroup container) {
    this.mBannerContainer = container;
  }

  @Override
  public void show() {
    super.show();
    isBannerHide = false;
  }

  public void hide() {
    super.hide();
    isBannerHide = true;
    this.mStopwatch.pause();
  }

  public void resetOperationCount() {
    super.resetOperationCount();
    this.mStopwatch.clear();
  }

  public long getShownTime() {
    return this.mStopwatch.getElapsedTimeMillis();
  }

  public int getWidth() {
    return -1;
  }

  public int getHeight() {
    return STANDARD_BANNER_HEIGHT;
  }

  public boolean isTablet() {
    return false;
  }

  public void show(Activity activity) {
  }

  public boolean isSmartBannerEnabled() {
    return this.isSmartBannerEnabled;
  }

  @Override
  public String getMediation() {
    return null;
  }

}
