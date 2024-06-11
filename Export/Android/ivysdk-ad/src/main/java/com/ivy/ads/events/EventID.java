package com.ivy.ads.events;

public interface EventID {

  String BANNER_ADSFALL_DISPLAYED = "banner_af_displayed";

  String BANNER_DISPLAYED = "banner_shown";
  String BANNER_CLICKED = "banner_clicked";

  String BANNER_ADSFALL_CLICKED = "banner_af_clicked";

  String INTERSTITIAL_REQUESTED = "interstitial_requested";
  String INTERSTITIAL_LOAD_FAILED = "interstitial_load_failed";
  String INTERSTITIAL_SHOW_FAILED = "interstitial_show_failed";
  String INTERSTITIAL_SHOWN = "interstitial_shown";
  String INTERSTITIAL_CLICKED = "interstitial_clicked";

  String INTERSTITIAL_CLOSED = "interstitial_closed";

  String INTERSTITIAL_ADSFALL_SHOW_FAILED = "interstitial_af_show_failed";
  String INTERSTITIAL_ADSFALL_SHOWN = "interstitial_af_shown";
  String INTERSTITIAL_ADSFALL_CLICKED = "interstitial_af_clicked";

  String INTERSTITIAL_ADSFALL_CLOSED= "interstitial_af_closed";

  String VIDEO_FAILED = "video_failed";
  String VIDEO_SHOWN = "video_shown";
  String VIDEO_COMPLETED = "video_completed";

  String REWARD_INTERSTITIAL_SHOWN = "ri_shown";
  String REWARD_INTERSTITIAL_FAILED = "ri_failed";
  String REWARD_INTERSTITIAL_COMPLETED= "ri_completed";


  String NATIVE_SHOW_FAILED = "native_show_failed";
  String NATIVE_SHOWN = "native_shown";
  String NATIVE_CLICKED = "native_clicked";

  String NATIVE_ADSFALL_SHOW_FAILED = "native_af_show_failed";
  String NATIVE_ADSFALL_SHOWN = "native_af_shown";
  String NATIVE_ADSFALL_CLICKED = "native_af_clicked";

  String CLICK_SHOW_REWARD_INTERSTITIAL = "click_show_ri";
  String CLICK_SHOW_INTERSTITIAL = "click_show_interstitial";
  String CLICK_SHOW_REWARDED  = "click_show_rewarded";
  String CLICK_SHOW_NATIVEAD = "click_show_nativead";
  String CLICK_SHOW_BANNER = "click_show_banner";

  String GAMEWALL_DISPLAYED = "gamewall_displayed";

  String QUIT_DISPLAYED = "quit_displayed";

  String IAP_PURCHASED = "iap_purchased";
  
  String SPLASH_PROMOTION_DISPLAYED = "sp_displayed";
  String SPLASH_PROMOTION_CLICKED = "sp_clicked";
  String SPLASH_PROMOTION_SKIPPED = "sp_skipped";

  String ROAS_LTV_PING = "gms_ad_paid_event";
  String GMS_AD_IMPRESSION_PING = "ad_impression_revenue";

  String ENGAGEMENT_EVENT = "engagement_time";
  // facebook 分享成功
  String FB_SHARE = "fb_share";

  String GMS_PAM_BANNER = "PAM_Banner_Ad_Impression";

  String GMS_PAM_INTERSTITIAL = "PAM_Interstitial_Ad_Impression";

  String GMS_PAM_REWARDED = "PAM_Rewarded_Ad_Impression";


  String INTERSTITIAL_REQUEST_TIMES = "interstitial_request_times";
  String INTERSTITIAL_FILLED_TIMES = "interstitial_filled_times";
  String INTERSTITIAL_FILL_DURATION = "interstitial_fill_duration";
  String INTERSTITIAL_RESPONSE_TIME_OUT = "interstitial_response_time_out";
  String VIDEO_REQUEST_TIMES = "video_request_times";
  String VIDEO_FILLED_TIMES = "video_filled_times";
  String VIDEO_FILL_DURATION = "video_fill_duration";
  String VIDEO_RESPONSE_TIME_OUT = "video_response_time_out";

  String BANNER_FIRST_SHOW_SUCCESS = "banner_first_show_success";

}
