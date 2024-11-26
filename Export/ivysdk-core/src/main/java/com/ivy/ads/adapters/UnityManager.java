package com.ivy.ads.adapters;

import android.app.Activity;

import com.ivy.ads.interfaces.Adapter;
import com.ivy.util.Logger;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.UnityAds;

import java.util.Map;
import java.util.TreeMap;

class UnityManager {
  private static final String TAG = "UnityAds";

  private static final UnityManager INSTANCE = new UnityManager();

  static synchronized UnityManager getInstance() {
    UnityManager unityManager;
    synchronized (UnityManager.class) {
      unityManager = INSTANCE;
    }
    return unityManager;
  }

  private UnityManager() {
  }

  private boolean sInitialized =false;
  public synchronized void init(Adapter adapter, String appId, Activity activity) {
    if (!sInitialized) {
      UnityAds.initialize(activity.getApplicationContext(), appId, false, new IUnityAdsInitializationListener() {
        @Override
        public void onInitializationComplete() {
          Logger.debug(TAG, "Unity onInitializationComplete");
        }

        @Override
        public void onInitializationFailed(UnityAds.UnityAdsInitializationError error, String message) {
          Logger.debug(TAG, "Unity onInitializationFailed " + message);
        }
      });

      sInitialized = true;
    }
  }
}
