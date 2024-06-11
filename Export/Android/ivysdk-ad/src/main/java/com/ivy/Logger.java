package com.ivy;

import android.util.Log;

public class Logger {

    private static final String TAG = "ADSFALL";
    private static boolean enable = false;

    public static void setEnable(boolean en){
        enable = en;
    }

    public static void debug(String msg) {
        if (!enable) return;
        Log.d(TAG, msg);
    }

    public static void warn(String msg) {
        if (!enable) return;
        Log.w(TAG, msg);
    }

    public static void error(String msg) {
        if (!enable) return;
        Log.e(TAG, msg);
    }

    public static void error(String tag, String msg) {
        if (!enable) return;
        Log.e(TAG + "_" + tag, msg);
    }

}
