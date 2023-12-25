package com.ivy.ads.events;

import android.os.Bundle;

import java.util.Map;

public class EventParams {

  public static final String PARAM_LABEL = "label";
  public static final String PARAM_VALUE = "value";
  public static final String PARAM_PROVIDER = "provider";
  public static final String PARAM_REASON = "reason";
  public static final String PARAM_PLACEMENT = "placement";
  public static final String PARAM_MEDIATION = "mediation";
  public static final String PARAM_LOADTIME = "loadtimems";
  public static final String PARAM_SHOWNTIME = "showtimems";

  public static final String PARAM_ITEMID = "itemid";

  public static final String PARAM_ORDERID = "orderId";

  public static final String PARAM_TIMES = "times";

  public static final String PARAM_REVENUE = "revenue";

  public static final String PARAM_CATALOG = "catalog";

  public static final String PARAM_PROMOTEAPP = "promoteapp";

  private Bundle bundle = new Bundle();

  public EventParams addParam(String paramType, String value) {
    if (value != null) {
      this.bundle.putString(paramType, value);
    }
    return this;
  }

  public EventParams addParam(String paramType, int value) {
    this.bundle.putInt(paramType, value);
    return this;
  }

  public EventParams addParam(String paramType, float value) {
    this.bundle.putFloat(paramType, value);
    return this;
  }


  public EventParams addParam(String paramType, long value) {
    this.bundle.putLong(paramType, value);
    return this;
  }

  public EventParams addParam(String paramType, boolean value) {
    this.bundle.putBoolean(paramType, value);
    return this;
  }

  public EventParams putAll(Map<String, String> params) {
    if (params == null || params.size() == 0) {
      return this;
    }
    for (Map.Entry<String, String> entry : params.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      if (key != null && value != null) {
        this.bundle.putString(key, value);
      }
    }
    return this;
  }

  public Bundle getParamsBundle() {
    return bundle;
  }
}
