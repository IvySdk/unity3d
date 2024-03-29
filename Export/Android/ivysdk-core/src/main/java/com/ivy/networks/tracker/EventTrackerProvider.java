package com.ivy.networks.tracker;

import android.content.Context;
import android.os.Bundle;

public interface EventTrackerProvider {
  void initialize(Context context);

  void setUserID(String userID);

  void logEvent(String eventName, Bundle bundle);

  void logPurchase(String contentType, String contentId, String currency, float revenue);

  void setSuppress(boolean suppress);

  void setUserProperty(String key, String value);
}
