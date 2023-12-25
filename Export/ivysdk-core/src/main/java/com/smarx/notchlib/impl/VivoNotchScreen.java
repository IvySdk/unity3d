package com.smarx.notchlib.impl;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;

import com.smarx.notchlib.INotchScreen;
import com.smarx.notchlib.utils.ScreenUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 测试之后发现vivo并不需要适配，因为vivo没有将显示区域绘制到耳朵区的API
 */
@TargetApi(Build.VERSION_CODES.O)
public class VivoNotchScreen implements INotchScreen {
    @SuppressWarnings("PrivateApi")
    public static boolean isNotch() {
        boolean value = false;
        int mask = 0x00000020;
        try {
            Class<?> cls = Class.forName("android.util.FtFeature");
            Method hideMethod = cls.getMethod("isFtFeatureSupport", int.class);
            Object object = cls.newInstance();
            value = (boolean) hideMethod.invoke(object, mask);
        } catch (Exception e) {
            Log.e("tag", "get error() ", e);
        }
        return value;
    }

    /**
     * vivo的适配文档中就告诉是27dp，未告知如何动态获取
     */
    public static int getNotchHeight(Context context) {
        float density = getDensity(context);
        return (int) (27 * density);
    }

    /**
     * vivo的适配文档中就告诉是100dp，未告知如何动态获取
     */
    public static int getNotchWidth(Context context) {
        float density = getDensity(context);
        return (int) (100 * density);
    }

    private static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    @Override
    public boolean hasNotch(Activity activity) {
        return isNotch();
    }

    @Deprecated
    @Override
    public void setDisplayInNotch(Dialog dialog) {

    }

    @Deprecated
    @Override
    public void setDisplayInNotch(Activity activity) {

    }

    @Override
    public int getNotchHeight(Activity activity) {
        try {
            Rect rect = ScreenUtil.calculateNotchRect(activity, getNotchWidth(activity), getNotchHeight(activity));
            return rect.height();
        } catch(Throwable t) {

        }
        return 0;
    }

    @Override
    public void getNotchRect(Activity activity, NotchSizeCallback callback) {
        ArrayList<Rect> rects = new ArrayList<>();
        Rect rect = ScreenUtil.calculateNotchRect(activity, getNotchWidth(activity), getNotchHeight(activity));
        rects.add(rect);
        callback.onResult(rects);
    }
}
