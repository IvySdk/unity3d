package com.ivy.networks.tracker.impl;

import android.content.Context;
import android.os.Bundle;

import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AFInAppEventType;
import com.appsflyer.AppsFlyerLib;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.networks.tracker.EventTrackerProvider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AppflyersTracker implements EventTrackerProvider {
  private Context context;
  private boolean suppress = false;
  @Override
  public void initialize(Context context) {
    this.context = context;
  }

  @Override
  public void setUserID(String userID) {
    if (userID != null && !"".equals(userID)) {
      AppsFlyerLib.getInstance().setCustomerUserId(userID);
    }
  }

  @Override
  public void setUserProperty(String key, String value) {
  }

  @Override
  public void setSuppress(boolean suppress) {
    this.suppress = suppress;
  }

  @Override
  public void logPurchase(String contentType, String contentId, String currency, float revenue) {
    Map<String, Object> eventValue = new HashMap<>();
    eventValue.put(AFInAppEventParameterName.REVENUE,revenue);
    if (contentType != null) {
      eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, contentType);
    }
    if (contentId != null) {
      eventValue.put(AFInAppEventParameterName.CONTENT_ID, contentId);
    }
    if (currency != null) {
      eventValue.put(AFInAppEventParameterName.CURRENCY, currency);
    } else {
      eventValue.put(AFInAppEventParameterName.CURRENCY, "USD");
    }

    AppsFlyerLib.getInstance().logEvent(context , AFInAppEventType.PURCHASE , eventValue);
  }

  @Override
  public void logEvent(String eventName, Bundle bundle) {
    if (suppress) {
      return;
    }
    Map<String, Object> eventValue = new HashMap<String, Object>();
    if (bundle != null) {
      Iterator it = bundle.keySet().iterator();
      while (it.hasNext()) {
        String n = String.valueOf(it.next());
        if (EventTracker.KEY_EVENT_PRIPORITY.equals(n)) {
          continue;
        }
        Object v = bundle.get(n);
        if (!"".equals(n) && !"".equals(v)) {
          eventValue.put(n, v);
        }
      }
    }
    AppsFlyerLib.getInstance().logEvent(context , eventName , eventValue);
  }
}
