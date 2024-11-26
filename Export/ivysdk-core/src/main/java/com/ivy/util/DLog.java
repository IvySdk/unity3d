package com.ivy.util;

import android.util.Log;

public class DLog {

    private static final String TAG = "DLog";

    private static boolean enable = false;

    public static void setEnable(boolean state) {
        DLog.enable = state;
    }

    public static boolean isEnable() {
        return enable;
    }

    public static void v(String msg) {
        if (msg == null) return;
        Log.e(TAG, msg);
    }

    public static void d(String msg) {
        if (msg == null) return;
        Log.e(TAG, msg);
    }

    public static void i(String msg) {
        if (msg == null) return;
        Log.e(TAG, msg);
    }

    public static void w(String msg) {
        if (msg == null) return;
        Log.e(TAG, msg);
    }

    public static void e(String msg) {
        if (msg == null) return;
        Log.e(TAG, msg);
    }


}
