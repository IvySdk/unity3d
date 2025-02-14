package com.ivy.shortCut;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.graphics.drawable.IconCompat;

import com.ivy.util.Logger;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Shortcut {
    /**
     * 添加 3d touch
     *
     * @param context
     * @param id
     * @param order
     * @param shortLabel
     * @param longLabel
     * @param icon
     * @param action
     */
    public static void add(Context context, String id, int order, String shortLabel, String longLabel, String icon, String action) {
        if (TextUtils.isEmpty(id)) {
            Logger.error("shortcut", "invalid id");
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutInfoCompat.Builder builder = new ShortcutInfoCompat.Builder(context, id);
            if (!TextUtils.isEmpty(shortLabel)) {
                builder.setShortLabel(shortLabel);
            }
            if (!TextUtils.isEmpty(longLabel)) {
                builder.setLongLabel(longLabel);
            }
            if (TextUtils.isEmpty(icon)) {
                Logger.error("shortcut", "invalid icon");
                return;
            }
            try {
                Bitmap bitmapIcon = null;
                if (!TextUtils.isEmpty(icon)) {
                    try {
                        InputStream input = context.getAssets().open(icon);
                        bitmapIcon = BitmapFactory.decodeStream(input);
                        input.close();
                    } catch (Exception e) {
                    }
                }
                IconCompat _icon = IconCompat.createWithBitmap(bitmapIcon);
                builder.setIcon(_icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            if (!TextUtils.isEmpty(action)) {
                intent.putExtra("shortcut_action", action);
            }
            builder.setIntent(intent);
            if (order > 0) {
                builder.setRank(order);
            }
            ShortcutInfoCompat shortcutInfoCompat = builder.build();
            List<ShortcutInfoCompat> list = new ArrayList<>();
            list.add(shortcutInfoCompat);
            ShortcutManagerCompat.addDynamicShortcuts(context, list);
        } else {
            Logger.error("shortcut", "unsupported os version");
        }
    }

    /**
     * 更新3d touch
     *
     * @param context
     * @param id
     * @param order
     * @param shortLabel
     * @param longLabel
     * @param icon
     * @param action
     */
    public static void update(Context context, String id, int order, String shortLabel, String longLabel, String icon, String action) {
        if (TextUtils.isEmpty(id)) {
            Logger.error("shortcut", "invalid id");
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutInfoCompat.Builder builder = new ShortcutInfoCompat.Builder(context, id);
            if (!TextUtils.isEmpty(shortLabel)) {
                builder.setShortLabel(shortLabel);
            }
            if (!TextUtils.isEmpty(longLabel)) {
                builder.setLongLabel(longLabel);
            }
            if (TextUtils.isEmpty(icon)) {
                Logger.error("shortcut", "invalid icon");
                return;
            }
            try {
                Bitmap bitmapIcon = null;
                if (!TextUtils.isEmpty(icon)) {
                    try {
                        InputStream input = context.getAssets().open(icon);
                        bitmapIcon = BitmapFactory.decodeStream(input);
                        input.close();
                    } catch (Exception e) {
                    }
                }
                IconCompat _icon = IconCompat.createWithBitmap(bitmapIcon);
                builder.setIcon(_icon);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            if (!TextUtils.isEmpty(action)) {
                intent.putExtra("shortcut_action", action);
            }
            builder.setIntent(intent);
            if (order > 0) {
                builder.setRank(order);
            }
            ShortcutInfoCompat shortcutInfoCompat = builder.build();
            List<ShortcutInfoCompat> list = new ArrayList<>();
            list.add(shortcutInfoCompat);
            ShortcutManagerCompat.addDynamicShortcuts(context, list);
        } else {
            Logger.error("shortcut", "unsupported os version");
        }
    }

    /**
     * 删除3D touch
     *
     * @param context
     * @param id
     */
    public static void delete(Context context, String id) {
        if (TextUtils.isEmpty(id)) {
            Logger.error("shortcut", "invalid id");
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            List<String> list = new ArrayList<>();
            list.add(id);
            ShortcutManagerCompat.removeDynamicShortcuts(context, list);
        } else {
            Logger.error("shortcut", "unsupported os version");
        }
    }
}