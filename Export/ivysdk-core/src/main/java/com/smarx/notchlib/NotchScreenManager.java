package com.smarx.notchlib;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.Build;

import com.smarx.notchlib.impl.AndroidPNotchScreen;
import com.smarx.notchlib.impl.HuaweiNotchScreen;
import com.smarx.notchlib.impl.MiNotchScreen;
import com.smarx.notchlib.impl.OppoNotchScreen;
import com.smarx.notchlib.impl.VivoNotchScreen;
import com.smarx.notchlib.utils.RomUtils;

import java.util.List;

public class NotchScreenManager {

    private static final NotchScreenManager instance = new NotchScreenManager();
    private final INotchScreen notchScreen;

    private boolean enableDisplayInNotch = true;

    private NotchScreenManager() {
        notchScreen = getNotchScreen();
    }

    public static NotchScreenManager getInstance() {
        return instance;
    }

    public boolean isEnableDisplayInNotch() {
        return enableDisplayInNotch;
    }

    public void setEnableDisplayInNotch(boolean enableDisplayInNotch) {
        this.enableDisplayInNotch = enableDisplayInNotch;
    }

    public int getNotchHeight(Activity activity) {
        if (notchScreen != null) {
            return notchScreen.getNotchHeight(activity);
        }
        return 0;
    }

    public boolean hasNotch(Activity activity) {
        if (notchScreen != null && activity != null) {
            return notchScreen.hasNotch(activity);
        }
        return false;
    }

    public void setDisplayInNotch(Activity activity) {
        if (notchScreen != null && enableDisplayInNotch) {
            notchScreen.setDisplayInNotch(activity);
        }
    }

    public void setDisplayInNotch(Dialog dialog) {
        if (notchScreen != null && enableDisplayInNotch) {
            notchScreen.setDisplayInNotch(dialog);
        }
    }

    public void getNotchInfo(final Activity activity, final INotchScreen.NotchScreenCallback notchScreenCallback) {
        final INotchScreen.NotchScreenInfo notchScreenInfo = new INotchScreen.NotchScreenInfo();
        if (notchScreen != null && notchScreen.hasNotch(activity)) {
            notchScreen.getNotchRect(activity, new INotchScreen.NotchSizeCallback() {
                @Override
                public void onResult(List<Rect> notchRects) {
                    if (notchRects != null && notchRects.size() > 0) {
                        notchScreenInfo.hasNotch = true;
                        notchScreenInfo.notchRects = notchRects;
                    }
                    notchScreenCallback.onResult(notchScreenInfo);
                }
            });
        } else {
            notchScreenCallback.onResult(notchScreenInfo);
        }
    }

    private INotchScreen getNotchScreen() {
        INotchScreen notchScreen = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (RomUtils.isHuawei()) {
                notchScreen = new HuaweiNotchScreen();
            } else if (RomUtils.isOppo()) {
                notchScreen = new OppoNotchScreen();
            } else if (RomUtils.isVivo()) {
                notchScreen = new VivoNotchScreen();
            } else if (RomUtils.isXiaomi()) {
                notchScreen = new MiNotchScreen();
            } else {
                notchScreen = new AndroidPNotchScreen();
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (RomUtils.isHuawei()) {
                notchScreen = new HuaweiNotchScreen();
            } else if (RomUtils.isOppo()) {
                notchScreen = new OppoNotchScreen();
            } else if (RomUtils.isVivo()) {
                notchScreen = new VivoNotchScreen();
            } else if (RomUtils.isXiaomi()) {
                notchScreen = new MiNotchScreen();
            }
        }
        return notchScreen;
    }
}
