package com.ivy.networks;

import android.content.Context;

import com.ivy.util.Logger;

import java.util.Locale;

public class IvyNetworks {
  public static final String TAG = IvyNetworks.class.getName();

  private static String accountId = null;

  public static String replaceApps2Maybe(String s, Context context) {
    return replaceApps2Maybe(s, getBaseUrl(context), context);
  }

  public static String replaceApps2Maybe(String s, String apps, Context context) {
    String[] hplArrayList = getHplList(context).split(",");
    int i = 0;
    while (i < hplArrayList.length) {
      String hostProvider = hplArrayList[i];
      if ((!s.contains(hostProvider) && !hostProvider.contains(s)) || hostProvider.contains(apps) || apps.contains(hostProvider)) {
        i++;
      } else {
        if (apps.startsWith("http")) {
          apps = apps.substring(apps.indexOf("://") + 3);
        }
        if (hostProvider.startsWith("http")) {
          hostProvider = hostProvider.substring(hostProvider.indexOf("://") + 3);
        }
        Logger.debug(TAG, "%s replaced: %s with: %s", s, hostProvider, apps);
        return s.replace(hostProvider, apps);
      }
    }
    return s;
  }

  public static String getBaseUrl(Context c) {
    String defaultValue;
    if (Locale.getDefault().getLanguage().startsWith("zh")) {
      defaultValue = "https://apps2-hk.adsfall.com";
    } else {
      defaultValue = "https://apps.adsfall.com";
    }
    return c.getSharedPreferences("prefs", 0).getString("apps", defaultValue);
  }

  public static String getHplList(Context c) {
    return c.getSharedPreferences("prefs", 0).getString("hpl", "https://apps.adsfall.com");
  }

  public static String getAccountId() {
    return accountId;
  }

}
