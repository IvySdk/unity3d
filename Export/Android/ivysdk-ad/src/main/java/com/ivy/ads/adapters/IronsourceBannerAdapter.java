package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.View;
//
//import com.ironsource.mediationsdk.ISBannerSize;
//import com.ironsource.mediationsdk.IronSource;
//import com.ironsource.mediationsdk.IronSourceBannerLayout;
//import com.ironsource.mediationsdk.logger.IronSourceError;
//import com.ironsource.mediationsdk.sdk.BannerListener;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.util.Logger;
//
//import org.json.JSONObject;
//
//public class IronsourceBannerAdapter extends BannerAdapter<BannerAdapter.GridParams> implements BannerListener {
//  private static final String TAG = "Ironsource-Banner";
//  private IronSourceBannerLayout ironSourceBanner;
//
//  private IronSourceBannerLayout mAdview = null;
//
//  public static boolean mIsInitialised = false;
//  public IronsourceBannerAdapter(Context context, String gridName, IvyAdType adType) {
//    super(context, gridName, adType);
//  }
//
//  public void setup(Activity activity) {
//    super.setup(activity);
//    Logger.debug(TAG, "setup()");
//    if (!mIsInitialised) {
//      try {
//        IronsourceManager.init(this, activity, getAppKey(), IronSource.AD_UNIT.BANNER);
//        mIsInitialised = true;
//      } catch(Throwable t) {
//        t.printStackTrace();
//      }
//    }
//  }
//
//  public void fetch(Activity activity) {
//    mAdview = null;
//    if (ironSourceBanner != null) {
//      IronSource.destroyBanner(ironSourceBanner);
//      ironSourceBanner = null;
//    }
//
//
//    ironSourceBanner = IronSource.createBanner(activity, ISBannerSize.BANNER);
//    ironSourceBanner.setBannerListener(this);
//    IronSource.loadBanner(ironSourceBanner, getPlacementId());
//  }
//
//  public String getPlacementId() {
//    return ((GridParams) getGridParams()).placement;
//  }
//
//  public View getView() {
//    return this.mAdview;
//  }
//
//  public int getWidth() {
//    return super.getWidth();
//  }
//
//  protected GridParams newGridParams() {
//    return new GridParams();
//  }
//
//  public String getAppKey() {
//    return ((GridParams) getGridParams()).appKey;
//  }
//
//  @Override
//  public void onBannerAdLoaded() {
//    Logger.debug(TAG, "onBannerAdLoaded");
//    this.mAdview = ironSourceBanner;
//    onAdLoadSuccess();
//  }
//
//  @Override
//  public void onBannerAdLoadFailed(IronSourceError ironSourceError) {
//    Logger.debug(TAG, "onBannerAdLoadFailed, code: " + ironSourceError.getErrorCode() + ", message: " + ironSourceError.getErrorMessage());
//    this.mAdview = null;
//    onAdLoadFailed(String.valueOf(ironSourceError.getErrorCode()));
//  }
//
//  @Override
//  public void onBannerAdClicked() {
//    Logger.debug(TAG, "onBannerAdClicked");
//    onAdClicked();
//  }
//
//  @Override
//  public void onBannerAdScreenPresented() {
//    Logger.debug(TAG, "onBannerAdScreenPresented");
//    onAdShowSuccess();
//  }
//
//  @Override
//  public void onBannerAdScreenDismissed() {
//    Logger.debug(TAG, "onBannerAdScreenDismissed");
//    onAdClosed(false);
//  }
//
//  @Override
//  public void onBannerAdLeftApplication() {
//    Logger.debug(TAG, "onBannerAdLeftApplication");
//  }
//
//  public static class GridParams extends BaseAdapter.GridParams {
//    public String appKey;
//    public String placement;
//
//    @Override
//    public BaseAdapter.GridParams fromJSON(JSONObject jsonObject) {
//      this.appKey = jsonObject.optString("appKey");
//      this.placement = jsonObject.optString("placement");
//      return this;
//    }
//
//    protected String getParams() {
//      return "placement=" + this.placement + ", appKey=" + this.appKey;
//    }
//  }
//}