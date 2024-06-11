package com.ivy.networks.tracker.impl;

import android.content.Context;
import android.os.Bundle;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.ivy.networks.tracker.EventTrackerProvider;

import java.math.BigDecimal;
import java.util.Currency;

public class FacebookTracker implements EventTrackerProvider {
  private AppEventsLogger logger = null;
  private boolean suppress = false;
  @Override
  public void initialize(Context context) {
    try {
      logger = AppEventsLogger.newLogger(context);
    } catch(Throwable t) {
      t.printStackTrace();
    }
  }

  @Override
  public void setSuppress(boolean suppress) {
    this.suppress = suppress;
  }

  @Override
  public void setUserID(String userID) {
    try {
      if (logger != null) {
        AppEventsLogger.setUserID(userID);
      }
    } catch(Throwable t) {
      t.printStackTrace();
    }
  }

  @Override
  public void setUserProperty(String key, String value) {
  }


  @Override
  public void logPurchase(String contentType, String contentId, String currency, float revenue) {
    if (logger != null) {
      try {
        Bundle parameters = new Bundle();
        if (contentId != null) {
          parameters.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, contentId);
        }
        if (contentType != null) {
          parameters.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, contentType);
        }
        logger.logPurchase(new BigDecimal(revenue), Currency.getInstance(currency));
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  /**
   * This function assumes logger is an instance of AppEventsLogger and has been
   * created using AppEventsLogger.newLogger() call.
   */
  public void logAchieveLevelEvent(String level) {
    if (logger == null) {
      return;
    }
    Bundle params = new Bundle();
    params.putString(AppEventsConstants.EVENT_PARAM_LEVEL, level);
    logger.logEvent(AppEventsConstants.EVENT_NAME_ACHIEVED_LEVEL, params);
  }

  /**
   * This function assumes logger is an instance of AppEventsLogger and has been
   * created using AppEventsLogger.newLogger() call.
   */
  public void logUnlockAchievementEvent(String description) {
    if (logger == null) {
      return;
    }
    Bundle params = new Bundle();
    params.putString(AppEventsConstants.EVENT_PARAM_DESCRIPTION, description);
    logger.logEvent(AppEventsConstants.EVENT_NAME_UNLOCKED_ACHIEVEMENT, params);
  }

  /**
   * This function assumes logger is an instance of AppEventsLogger and has been
   * created using AppEventsLogger.newLogger() call.
   */
  public void logViewContentEvent(String contentType, String contentData, String contentId, String currency, double price) {
    if (logger == null) {
      return;
    }

    Bundle params = new Bundle();
    params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, contentType);
    params.putString(AppEventsConstants.EVENT_PARAM_CONTENT, contentData);
    params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, contentId);
    params.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency);
    logger.logEvent(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT, price, params);
  }

  /**
   * This function assumes logger is an instance of AppEventsLogger and has been
   * created using AppEventsLogger.newLogger() call.
   */
  public void logSpendCreditsEvent(String contentData, String contentId, String contentType, double totalValue) {
    if (logger == null) {
      return;
    }
    Bundle params = new Bundle();
    params.putString(AppEventsConstants.EVENT_PARAM_CONTENT, contentData);
    params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, contentId);
    params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, contentType);
    logger.logEvent(AppEventsConstants.EVENT_NAME_SPENT_CREDITS, totalValue, params);
  }

  /**
   * This function assumes logger is an instance of AppEventsLogger and has been
   * created using AppEventsLogger.newLogger() call.
   */
  public void logAdClickEvent(String adType) {
    if (logger == null) {
      return;
    }
    Bundle params = new Bundle();
    params.putString(AppEventsConstants.EVENT_PARAM_AD_TYPE, adType);
    logger.logEvent(AppEventsConstants.EVENT_NAME_AD_CLICK, params);
  }

  /**
   * This function assumes logger is an instance of AppEventsLogger and has been
   * created using AppEventsLogger.newLogger() call.
   */
  public void logAdImpressionEvent(String adType) {
    if (logger == null) {
      return;
    }
    Bundle params = new Bundle();
    params.putString(AppEventsConstants.EVENT_PARAM_AD_TYPE, adType);
    logger.logEvent(AppEventsConstants.EVENT_NAME_AD_IMPRESSION, params);
  }

  /**
   * This function assumes logger is an instance of AppEventsLogger and has been
   * created using AppEventsLogger.newLogger() call.
   */
  public void logSubscribeEvent(String orderId, String currency, double price) {
    if (logger == null) {
      return;
    }
    Bundle params = new Bundle();
    params.putString(AppEventsConstants.EVENT_PARAM_ORDER_ID, orderId);
    params.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency);
    logger.logEvent(AppEventsConstants.EVENT_NAME_SUBSCRIBE, price, params);
  }

  @Override
  public void logEvent(String eventName, Bundle bundle) {
    if (suppress) {
      return;
    }
    if (logger != null) {
      try {
        logger.logEvent(eventName, bundle);
     //   Log.e("FaceBook-event", eventName + "--" + bundle.toString());
      } catch(Throwable t) {
        t.printStackTrace();
      }
    }
  }
}
