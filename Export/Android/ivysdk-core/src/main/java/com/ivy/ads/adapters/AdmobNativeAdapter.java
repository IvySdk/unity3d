package com.ivy.ads.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.adsfall.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.ivy.ads.interfaces.IvyAdType;
import com.ivy.ads.interfaces.IvyLoadStatus;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.Map;

public class AdmobNativeAdapter extends NativeAdapter<NativeAdapter.GridParams> implements NativeAd.OnNativeAdLoadedListener {
  private static final String TAG = "Adapter-Admob-Native";
  private AdLoader adLoader;
  private NativeAd unifiedNativeAd;

  public AdmobNativeAdapter(Context context, String gridName, IvyAdType adType) {
    super(context, gridName, adType);
  }

  public void setup(Activity activity) {
    super.setup(activity);
    this.adLoader = new AdLoader.Builder(activity.getApplicationContext(), getPlacementId()).forNativeAd(this).withAdListener(new AdListener() {
      @Override
      public void onAdFailedToLoad(LoadAdError loadAdError) {
        if (loadAdError != null) {
          Logger.warning(TAG, "Native ad load error: " + (loadAdError != null ? loadAdError.toString() : " no error code"));
          AdmobNativeAdapter.this.onAdLoadFailed(String.valueOf(loadAdError.getCode()));
        } else {
          Logger.warning(TAG, "Native ad load error, empty");
          AdmobNativeAdapter.this.onAdLoadFailed(IvyLoadStatus.OTHER);
        }
      }

      public void onAdLoaded() {
        Logger.debug(TAG, "onAdLoaded");
      }

      public void onAdClicked() {
        Logger.debug(TAG, "onAdClicked");
        AdmobNativeAdapter.this.onAdClicked();
      }
    }).withNativeAdOptions(new NativeAdOptions.Builder().build()).build();
  }

  public boolean showNativeAd(Activity activity, Map<String, View> adViews) {
    if (this.unifiedNativeAd == null) {
      Logger.debug(TAG, "No native ad available");
      super.onAdShowFail();
      return false;
    }

    try {
      Logger.debug(TAG, "Render native ad begin");

      ViewGroup nativeAdContainer = (ViewGroup) adViews.get("NativeAdsContainerView");

      NativeAdView unifiedNativeAdView = (NativeAdView) LayoutInflater.from(activity).inflate(R.layout.ad_custom_native, null);

      // headline
      TextView headlineView = unifiedNativeAdView.findViewById(R.id.ad_headline);
      String headline = unifiedNativeAd.getHeadline();
      if (!TextUtils.isEmpty(headline)) {
        headlineView.setText(unifiedNativeAd.getHeadline());
        unifiedNativeAdView.setHeadlineView(headlineView);
      } else {
        headlineView.setVisibility(View.GONE);
      }

      // app icon
      ImageView iconView = unifiedNativeAdView.findViewById(R.id.ad_app_icon);
      if (unifiedNativeAd.getIcon() != null && unifiedNativeAd.getIcon().getDrawable() != null) {
        iconView.setImageDrawable(unifiedNativeAd.getIcon().getDrawable());
        unifiedNativeAdView.setIconView(iconView);
      } else {
        iconView.setVisibility(View.GONE);
      }

      // body
      TextView bodyView = unifiedNativeAdView.findViewById(R.id.ad_body);
      if (!TextUtils.isEmpty(unifiedNativeAd.getBody())) {
        bodyView.setText(unifiedNativeAd.getBody());
        unifiedNativeAdView.setBodyView(bodyView);
      } else {
        bodyView.setVisibility(View.GONE);
      }

      RatingBar rb = unifiedNativeAdView.findViewById(R.id.ad_stars);
      unifiedNativeAdView.setStarRatingView(rb);

      // cta
      Button ctaButton = unifiedNativeAdView.findViewById(R.id.ad_call_to_action);
      String cta = unifiedNativeAd.getCallToAction();
      if (TextUtils.isEmpty(cta)) {
        cta = activity.getString(R.string.risesdk_install);
      }
      ctaButton.setText(cta);
      unifiedNativeAdView.setCallToActionView(ctaButton);

      // mediaview
      MediaView mediaView = unifiedNativeAdView.findViewById(R.id.ad_media);
      mediaView.setImageScaleType(ImageView.ScaleType.CENTER_INSIDE);
      mediaView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
        @Override
        public void onChildViewAdded(View parent, View child) {
          if (child instanceof ImageView) {
            ImageView imageView = (ImageView) child;
            imageView.setAdjustViewBounds(true);
          }
        }

        @Override
        public void onChildViewRemoved(View parent, View child) {}
      });

      unifiedNativeAdView.setMediaView(mediaView);

      unifiedNativeAdView.setNativeAd(this.unifiedNativeAd);

      nativeAdContainer.removeAllViews();
      nativeAdContainer.addView(unifiedNativeAdView);
      unifiedNativeAdView.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.fade_in));
      super.onAdShowSuccess();
    } catch(Throwable ex) {
      Logger.error(TAG, "showNativeAd exception", ex);
    }
    return true;
  }

  public void closeNativeAd() {
    Logger.debug(TAG, "closeNativeAd");
    if (this.unifiedNativeAd != null) {
      this.unifiedNativeAd.destroy();
      this.unifiedNativeAd = null;
    }
    onAdClosed(false);
  }

  public void fetch(Activity activity) {
    if (this.unifiedNativeAd != null) {
      this.unifiedNativeAd.destroy();
      this.unifiedNativeAd = null;
    }
    AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
    this.adLoader.loadAd(adRequestBuilder.build());
  }

  public void show(Activity activity) {
  }

  public String getPlacementId() {
    return isTestMode() ? "ca-app-pub-3940256099942544/2247696110" : ((GridParams) getGridParams()).placement;
  }

  protected GridParams newGridParams() {
    return new GridParams();
  }

  @Override
  public void onNativeAdLoaded(NativeAd unifiedNativeAd) {
    Logger.debug(TAG, "onUnifiedNativeAdLoaded");
    this.unifiedNativeAd = unifiedNativeAd;
    super.onAdLoadSuccess();
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
}
