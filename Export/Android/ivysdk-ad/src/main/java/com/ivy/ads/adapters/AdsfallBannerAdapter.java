//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.RelativeLayout.LayoutParams;
//import android.widget.TextView;
//
//import com.ivy.IvySdk;
//import com.ivy.IvyUtils;
//import com.ivy.ads.configuration.PromoteConfig;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.ads.interfaces.IvyLoadStatus;
//import com.ivy.networks.DownloadFeedback;
//
//
//import org.json.JSONObject;
//
//import java.io.InputStream;
//
//public class AdsfallBannerAdapter extends BannerAdapter<BannerAdapter.GridParams> {
//
//  public static final String TAG = "AdsFall-Banner";
//  private View adView;
//
//  public boolean initialized = false;
//
//
//  private static Bitmap iconOfflineBitmap = null;
//
//  public AdsfallBannerAdapter(Context context, String gridName, IvyAdType adType) {
//    super(context, gridName, adType);
//
//    if (iconOfflineBitmap == null) {
//      try {
//        InputStream in = activity.getResources().getAssets().open("icon_offline.png");
//        iconOfflineBitmap = BitmapFactory.decodeStream(in);
//        in.close();
//      } catch(Throwable ex) {
//      }
//    }
//  }
//
//  @Override
//  public void onDestroy(Activity activity) {
//    super.onDestroy(activity);
//    if (iconOfflineBitmap != null && !iconOfflineBitmap.isRecycled()) {
//      iconOfflineBitmap.recycle();
//      iconOfflineBitmap = null;
//    }
//  }
//
//  public static class GridParams extends BaseAdapter.GridParams {
//    @Override
//    public BaseAdapter.GridParams fromJSON(JSONObject jsonObject) {
//      return this;
//    }
//    public String getParams() {
//      return "";
//    }
//  }
//
//  private PromoteConfig promoteConfig = null;
//  public void setPromoteConfig(PromoteConfig config) {
//    this.promoteConfig = config;
//  }
//
//  public View getView() {
//    return this.adView;
//  }
//
//  private JSONObject bannerApps = null;
//  public boolean markReady() {
//    if (this.promoteConfig == null) {
//      return false;
//    }
//    return true;
//  }
//
//  public void fetch(Activity activity) {
//    this.adView = null;
//    this.bannerApps = null;
//    if (this.promoteConfig == null) {
//      super.onAdLoadFailed(IvyLoadStatus.OTHER);
//      return;
//    }
//
//    this.bannerApps = this.promoteConfig.selectOne(this.getApplicationContext(), PromoteConfig.IMAGE_TYPE_ICON, true);
//    if (this.bannerApps == null) {
//      Logger.debug(TAG, "No promote can be selected for adsfall banner");
//      onAdLoadFailed(IvyLoadStatus.NO_FILL);
//      return;
//    }
//
//    String pkg = this.bannerApps.optString("package");
//    Logger.debug(TAG, "Select " + pkg);
//    this.addEventParams("promoteapp", pkg);
//
//    if (this.adView == null) {
//      this.adView = LayoutInflater.from(activity).inflate(R.layout.banner_adsfall_normal, this.mBannerContainer, false);
//      this.adView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//      View native_ad_call_to_action = this.adView.findViewById(R.id.native_ad_call_to_action);
//      native_ad_call_to_action.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//          onAdClicked();
//          String pkg = String.valueOf(view.getTag());
//
//          IvyUtils.openPlayStore(adView.getContext(), pkg, "banner_click", bannerApps);
//        }
//      });
//    }
//
//    onAdLoadSuccess();
//  }
//
//  @Override
//  public void show(Activity activity) {
//    if (this.bannerApps == null) {
//      Logger.debug(TAG, "No banner app can be selected to fullfill the banner");
//      onAdShowFail();
//      return;
//    }
//
//    final TextView ad_title = adView.findViewById(R.id.native_ad_title);
//    final ImageView native_icon_view = adView.findViewById(R.id.native_icon_view);
//    final TextView ad_desc = adView.findViewById(R.id.native_ad_desc);
//
//    ad_title.setText(bannerApps.optString("name"));
//    ad_desc.setText(bannerApps.optString("desc"));
//
//    View native_ad_call_to_action = this.adView.findViewById(R.id.native_ad_call_to_action);
//    native_ad_call_to_action.setTag(bannerApps.optString("package"));
//
//    String url = bannerApps.optString("icon");
//
//    if (iconOfflineBitmap != null && !iconOfflineBitmap.isRecycled()) {
//      native_icon_view.setImageBitmap(iconOfflineBitmap);
//    }
//
//    IvySdk.getCreativePath(url, new DownloadFeedback() {
//      @Override
//      public void onSuccess(String filePath) {
//        try {
//          activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//              native_icon_view.setImageURI(Uri.parse(filePath));
//            }
//          });
//        } catch(Throwable ex) {
//          ex.printStackTrace();
//        }
//      }
//
//      @Override
//      public void onFail() {
//
//      }
//    });
//
//    onAdShowSuccess();
//  }
//
//  protected GridParams newGridParams() {
//    return new GridParams();
//  }
//
//  public String getPlacementId() {
//    return "";
//  }
//
//}
