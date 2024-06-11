package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import com.adsfall.R;
//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdLoader;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.VideoController;
//import com.google.android.gms.ads.formats.MediaView;
//import com.google.android.gms.ads.formats.UnifiedNativeAd;
//import com.google.android.gms.ads.formats.UnifiedNativeAdView;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.ads.interfaces.IvyLoadStatus;
//import com.ivy.util.Logger;
//
//import org.json.JSONObject;
//
//import java.util.Map;
//
//public class AdxNativeAdapter extends NativeAdapter<NativeAdapter.GridParams> implements UnifiedNativeAd.OnUnifiedNativeAdLoadedListener {
//  private static final String TAG = "AdxNativeAdapter";
//  private AdLoader adLoader;
//  private UnifiedNativeAd unifiedNativeAd;
//
//  public AdxNativeAdapter(Context context, String gridName, IvyAdType adType) {
//    super(context, gridName, adType);
//  }
//
//  @Override
//  public void onUnifiedNativeAdLoaded(UnifiedNativeAd ad) {
//    unifiedNativeAd = ad;
//    onAdLoadSuccess();
//  }
//
//  public void setup(Activity activity) {
//    super.setup(activity);
//
//    this.adLoader = new AdLoader.Builder(activity, getPlacementId()).forUnifiedNativeAd(this).withAdListener(new AdListener() {
//      public void onAdClosed() {
//        AdxNativeAdapter.this.onAdClosed(false);
//      }
//
//      public void onAdFailedToLoad(int var1) {
//        AdxNativeAdapter.this.onAdLoadFailed(IvyLoadStatus.NO_FILL);
//      }
//
//      public void onAdLeftApplication() {
//        AdxNativeAdapter.this.onAdClicked();
//      }
//
//      public void onAdOpened() {
//        AdxNativeAdapter.this.onAdShowSuccess();
//      }
//
//      public void onAdLoaded() {
//        Logger.debug(TAG, "onAdLoaded");
//        AdxNativeAdapter.this.onAdLoadSuccess();
//      }
//
//      public void onAdClicked() {
//        Logger.debug(TAG, "onAdClicked");
//        AdxNativeAdapter.this.onAdClicked();
//      }
//
//      public void onAdImpression() {
//        onAdShowSuccess();
//      }
//    }).build();
//  }
//
//  public boolean showNativeAd(Activity activity, Map<String, View> adViews) {
//    if (this.unifiedNativeAd == null) {
//      super.onAdShowFail();
//      return false;
//    }
//
//    ViewGroup nativeAdContainer = (ViewGroup) adViews.get("NativeAdsContainerView");
//    UnifiedNativeAdView adView = (UnifiedNativeAdView) LayoutInflater.from(activity).inflate(R.layout.ad_custom_native, null);
//    if (this.unifiedNativeAd != null && nativeAdContainer != null) {
//      populateUnifiedNativeAdView(unifiedNativeAd, adView);
//      nativeAdContainer.removeAllViews();
//      nativeAdContainer.addView(adView);
//    }
//    return true;
//  }
//
//  public void closeNativeAd() {
//    if (this.unifiedNativeAd != null) {
//      this.unifiedNativeAd.destroy();
//      this.unifiedNativeAd = null;
//    }
//  }
//
//  public void fetch(Activity activity) {
//    this.adLoader.loadAd(new AdRequest.Builder().build());
//  }
//
//  public void show(Activity activity) {
//  }
//
//  public String getPlacementId() {
//    return isTestMode() ? "ca-app-pub-3940256099942544/2247696110" : ((GridParams) getGridParams()).placement;
//  }
//
//  protected GridParams newGridParams() {
//    return new GridParams();
//  }
//
//  /**
//   * Populates a {@link UnifiedNativeAdView} object with data from a given
//   * {@link UnifiedNativeAd}.
//   *
//   * @param nativeAd the object containing the ad's assets
//   * @param adView   the view to be populated
//   */
//  private boolean populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
//    // Set the media view. Media content will be automatically populated in the media view once
//    // adView.setNativeAd() is called.
//    MediaView mediaView = adView.findViewById(R.id.ad_media);
//    adView.setMediaView(mediaView);
//
//    // Set other ad assets.
//    adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
//    adView.setBodyView(adView.findViewById(R.id.ad_body));
//    adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
//    adView.setIconView(adView.findViewById(R.id.ad_app_icon));
////    adView.setPriceView(adView.findViewById(R.id.ad_price));
//    adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
////    adView.setStoreView(adView.findViewById(R.id.ad_store));
//    adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
//
//    // The headline is guaranteed to be in every UnifiedNativeAd.
//    ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
//
//    // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
//    // check before trying to display them.
//    if (nativeAd.getBody() == null) {
//      adView.getBodyView().setVisibility(View.INVISIBLE);
//    } else {
//      adView.getBodyView().setVisibility(View.VISIBLE);
//      ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
//    }
//
//    if (nativeAd.getCallToAction() == null) {
//      adView.getCallToActionView().setVisibility(View.INVISIBLE);
//    } else {
//      adView.getCallToActionView().setVisibility(View.VISIBLE);
//      ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
//    }
//
//    if (nativeAd.getIcon() == null) {
//      adView.getIconView().setVisibility(View.GONE);
//    } else {
//      ((ImageView) adView.getIconView()).setImageDrawable(
//        nativeAd.getIcon().getDrawable());
//      adView.getIconView().setVisibility(View.VISIBLE);
//    }
//
//    if (nativeAd.getStarRating() == null) {
//      adView.getStarRatingView().setVisibility(View.INVISIBLE);
//    } else {
//      ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
//      adView.getStarRatingView().setVisibility(View.VISIBLE);
//    }
//
//    if (nativeAd.getAdvertiser() == null) {
//      adView.getAdvertiserView().setVisibility(View.INVISIBLE);
//    } else {
//      ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
//      adView.getAdvertiserView().setVisibility(View.VISIBLE);
//    }
//
//    // This method tells the Google Mobile Ads SDK that you have finished populating your
//    // native ad view with this native ad. The SDK will populate the adView's MediaView
//    // with the media content from this native ad.
//    adView.setNativeAd(nativeAd);
//
//    // Get the video controller for the ad. One will always be provided, even if the ad doesn't
//    // have a video asset.
//    VideoController vc = nativeAd.getVideoController();
//
//    // Updates the UI to say whether or not this ad has a video asset.
//    if (vc.hasVideoContent()) {
////      videoStatus.setText(String.format(Locale.getDefault(),
////        "Video status: Ad contains a %.2f:1 video asset.",
////        vc.getAspectRatio()));
//
//      // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
//      // VideoController will call methods on this object when events occur in the video
//      // lifecycle.
//      vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
//        @Override
//        public void onVideoEnd() {
//          // Publishers should allow native ads to complete video playback before
//          // refreshing or replacing them with another ad in the same UI location.
////          refresh.setEnabled(true);
////          videoStatus.setText("Video status: Video playback has ended.");
//          super.onVideoEnd();
//        }
//      });
//    } else {
////      videoStatus.setText("Video status: Ad does not contain a video asset.");
////      refresh.setEnabled(true);
//    }
//    return true;
//  }
//
//
//  public static class GridParams extends com.ivy.ads.adapters.BaseAdapter.GridParams {
//    public String placement;
//
//    public GridParams fromJSON(JSONObject jsonObject) {
//      this.placement = jsonObject.optString("placement");
//      return this;
//    }
//
//    protected String getParams() {
//      return "placement=" + this.placement;
//    }
//  }
//}
