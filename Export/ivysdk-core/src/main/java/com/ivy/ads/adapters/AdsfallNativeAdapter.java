package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adsfall.R;
import com.ivy.IvySdk;
import com.ivy.IvyUtils;
import com.ivy.ads.configuration.PromoteConfig;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.ads.managers.PromoteAdManager;
import com.ivy.networks.DownloadFeedback;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.Map;

public class AdsfallNativeAdapter extends NativeAdapter<NativeAdapter.GridParams> {
  private static final String DEVICE_ID_HASH_PREFS_KEY = "deviceIdHash";
  private static final String PREFS_NAME = "FBAdPrefs";
  private static final String TAG = Logger.createTag(AdsfallNativeAdapter.class);

  private static Bitmap coverOfflineBitmap = null;
  private static Bitmap iconOfflineBitmap = null;

  public AdsfallNativeAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
    if (coverOfflineBitmap == null) {
      try {
        InputStream in = activity.getResources().getAssets().open("cover_offline.jpg");
        coverOfflineBitmap = BitmapFactory.decodeStream(in);
        in.close();
      } catch(Throwable ex) {
      }
    }

    if (iconOfflineBitmap == null) {
      try {
        InputStream in = activity.getResources().getAssets().open("icon_offline.png");
        iconOfflineBitmap = BitmapFactory.decodeStream(in);
        in.close();
      } catch(Throwable ex) {
      }
    }
  }

  private PromoteConfig promoteConfig;

  public void setPromoteConfig(PromoteConfig config) {
    this.promoteConfig = config;
  }

  private JSONObject selectedPromoteApp = null;

  /**
   * @param activity
   */
  public void fetch(Activity activity) {
    this.selectedPromoteApp = this.promoteConfig.selectOne(getApplicationContext(), PromoteConfig.IMAGE_TYPE_BANNER, false);
    if (this.selectedPromoteApp == null) {
      onAdLoadFailed(IvyLoadStatus.BANNER_NO_FILL);
      return;
    }

    this.addEventParams("promoteapp", this.selectedPromoteApp.optString("package"));
    String bannerURL = this.selectedPromoteApp.optString("banner");
    if (bannerURL != null && !"".equals(bannerURL)) {
      IvySdk.getCreativePath(bannerURL, new DownloadFeedback() {
        @Override
        public void onSuccess(String filePath) {
        }

        @Override
        public void onFail() {
        }
      });
    }

    onAdLoadSuccess();
  }

  public void show(Activity activity) {

  }

  public boolean showNativeAd(final Activity activity, Map<String, View> adViews) {
    ViewGroup nativeAdContainer = (ViewGroup) adViews.get("NativeAdsContainerView");
    if (nativeAdContainer == null || this.selectedPromoteApp == null) {
      return false;
    }
    View holerView = LayoutInflater.from(activity).inflate(R.layout.native_ad_banner, nativeAdContainer, false);

    ImageView adLabel = holerView.findViewById(R.id.adLabel);
    if (adLabel != null && this.promoteConfig.hideAdLabel) {
      adLabel.setVisibility(View.GONE);
    }

    TextView native_ad_title = holerView.findViewById(R.id.ad_title);
    final ImageView native_ad_icon = holerView.findViewById(R.id.ad_icon);
    TextView native_ad_social_context = holerView.findViewById(R.id.ad_desc);
    TextView native_ad_call_to_action = (TextView) holerView.findViewById(R.id.ad_action);
    final ImageView ad_image =  holerView.findViewById(R.id.ad_image);
    if (selectedPromoteApp.has("name")) {
      native_ad_title.setText(selectedPromoteApp.optString("name"));
    }
    String desc = "";
    if (selectedPromoteApp.has("desc")) {
      desc = selectedPromoteApp.optString("desc");
      if ("".equals(desc)) {
        if (selectedPromoteApp.has("summary")) {
          desc = selectedPromoteApp.optString("summary");
        }
      }
    }

    native_ad_social_context.setText(desc);
    native_ad_call_to_action.setText(activity.getString(R.string.android_sdk_download));

    String bannerURL = this.selectedPromoteApp.optString("banner");
    if (bannerURL != null && !"".equals(bannerURL)) {
      if (!PromoteAdManager.isCreativeAvailable(bannerURL)) {
        if (coverOfflineBitmap != null && !coverOfflineBitmap.isRecycled()) {
          ad_image.setImageBitmap(coverOfflineBitmap);
        }
      }
      IvySdk.getCreativePath(bannerURL, new DownloadFeedback() {
        @Override
        public void onSuccess(final String filePath) {
          try {
            activity.runOnUiThread(new Runnable() {
              @Override
              public void run() {
                ad_image.setImageURI(Uri.parse(filePath));
              }
            });
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }

        @Override
        public void onFail() {
        }
      });
    }

    String iconURL = this.selectedPromoteApp.optString("icon");
    if (iconURL != null && !"".equals(iconURL)) {
      if (!PromoteAdManager.isCreativeAvailable(iconURL)) {
        if (iconOfflineBitmap != null && !iconOfflineBitmap.isRecycled()) {
          native_ad_icon.setImageBitmap(iconOfflineBitmap);
        }
      }
      IvySdk.getCreativePath(iconURL, new DownloadFeedback() {
        @Override
        public void onSuccess(String filePath) {
          activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              try {
                native_ad_icon.setImageURI(Uri.parse(filePath));
              } catch (Exception ex) {
                ex.printStackTrace();
              }
            }
          });

        }

        @Override
        public void onFail() {
          if (iconOfflineBitmap != null && !iconOfflineBitmap.isRecycled()) {
            native_ad_icon.setImageBitmap(iconOfflineBitmap);
          }
        }
      });
    }

    ad_image.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String pkg = selectedPromoteApp.optString("package");
        IvyUtils.openPlayStore(activity, pkg, "fallback_native", selectedPromoteApp);
      }
    });

    native_ad_call_to_action.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String pkg = selectedPromoteApp.optString("package");
        IvyUtils.openPlayStore(activity, pkg, "fallback_native", selectedPromoteApp);
      }
    });


    nativeAdContainer.addView(holerView);

    onAdShowSuccess();
    return true;
  }

  public void closeNativeAd() {
    Logger.debug(TAG, "closeNativeAd");
    onAdClosed(false);
  }

  public String getPlacementId() {
    return ((GridParams) getGridParams()).placement;
  }

  protected GridParams newGridParams() {
    return new GridParams();
  }

  public static class GridParams extends com.ivy.ads.adapters.BaseAdapter.GridParams {
    public String placement;

    public GridParams fromJSON(JSONObject jsonObject) {
      this.placement = jsonObject.optString("placement");
      return this;
    }

    protected String getParams() {
      return "placement=" + this.placement;
    }
  }

  @Override
  public void onDestroy(Activity activity) {
    super.onDestroy(activity);
    if (coverOfflineBitmap != null && !coverOfflineBitmap.isRecycled()) {
      coverOfflineBitmap.recycle();
      coverOfflineBitmap = null;
    }

    if (iconOfflineBitmap != null && !iconOfflineBitmap.isRecycled()) {
      iconOfflineBitmap.recycle();
      iconOfflineBitmap = null;
    }
  }
}
