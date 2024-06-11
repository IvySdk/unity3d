package com.ivy.ads.adapters;

import android.content.Context;

import com.ivy.Logger;
import com.yandex.mobile.ads.common.InitializationListener;
import com.yandex.mobile.ads.common.MobileAds;

public class YandexManager {
    private static final String TAG = "YandexManager";
    private static boolean initialized = false;
    private static final YandexManager INSTANCE = new YandexManager();

    public static synchronized YandexManager getInstance() {
        return INSTANCE;
    }

    private volatile boolean sdkInitialized = false;

    public synchronized void initialize(Context context) {
        if (!initialized) {
            try {
                MobileAds.initialize(context, (InitializationListener) () -> {
                    sdkInitialized = true;
                    Logger.debug( "Yandex SDK initialized");
                });
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
            initialized = true;
        }
    }

    public boolean isSdkInitialized() {
        return sdkInitialized;
    }
}