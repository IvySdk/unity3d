package com.ivy.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.adsfall.R;

import org.json.JSONObject;

import java.util.Random;
public class NotificationUtil {
    private static final String CHANNEL_ID = "Game";
    private static final String CHANNEL_NAME = "GameMode";
    public static void showNotification(Context context,
                                        String title,
                                        String subtitle,
                                        String bigText,
                                        String smallIcon,
                                        String largeIcon,
                                        String bigPicture,
                                        boolean autoCancel,
                                        String action) throws Exception {
        NotificationUtil.showNotification(context, CHANNEL_ID, CHANNEL_NAME, title, subtitle, bigText, smallIcon, largeIcon, bigPicture, autoCancel, action);
    }
    public static void showFirebaseRemoteNotification(Context context,
                                                      String data) throws Exception {
        JSONObject obj = new JSONObject(data);
        String channelId = obj.optString("channel_id", CHANNEL_ID);
        String channelName = obj.optString("channel_name", CHANNEL_NAME);
        boolean autoCancel = obj.optBoolean("auto_cancel", true);
        String defaultTitle;
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo info = packageManager.getApplicationInfo(context.getPackageName(), 0);
            defaultTitle = packageManager.getApplicationLabel(info).toString();
        } catch (Exception e) {
            defaultTitle = "";
        }
        String title = obj.optString("title", defaultTitle);
        String subtitle = obj.optString("subtitle", "");
        String clickAction = obj.optString("click_action", "");
        String bigText = obj.optString("bigText", "");
        String largeIcon = obj.optString("large_icon", "");
        String bigPicture = obj.optString("big_picture", "");
        // String sound = obj.optString("sound", "");
        if (!TextUtils.isEmpty(largeIcon) || !TextUtils.isEmpty(bigPicture) || !TextUtils.isEmpty(bigPicture)) {
            Data inputData = new Data.Builder()
                    .putString("channelId", channelId)
                    .putString("channelName", channelName)
                    .putString("title", title)
                    .putString("subtitle", subtitle)
                    .putString("clickAction", clickAction)
                    .putString("bigText", bigText)
                    .putString("largeIcon", largeIcon)
                    .putString("bigPicture", bigPicture)
                    // .putString("sound", sound)
                    .putBoolean("autoClose", autoCancel)
                    .build();
            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest downloadWorkRequest = new OneTimeWorkRequest.Builder(NotificationResourceTask.class)
                    .setConstraints(constraints)
                    .setInputData(inputData)
                    .build();
            WorkManager.getInstance(context).enqueue(downloadWorkRequest);
        } else {
            NotificationUtil.showNotification(context, channelId, channelName, title, subtitle, bigText, "", "", null, autoCancel, clickAction);
        }
    }
    public static void showNotification(Context context,
                                        String channelId,
                                        String channelName,
                                        String title,
                                        String subtitle,
                                        String bigText,
                                        String smallIcon,
                                        String largeIcon,
                                        String bigPicture,
                                        boolean autoCancel,
                                        String action) throws Exception {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        if (!TextUtils.isEmpty(action)) {
            intent.putExtra("notification_data", action);
        }
        intent.setPackage(context.getPackageName());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_MUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(autoCancel)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        if (!TextUtils.isEmpty(title)) {
            builder.setContentTitle(title);
        }
        if (!TextUtils.isEmpty(subtitle)) {
            builder.setContentText(subtitle);
        }
        try {
            if (TextUtils.isEmpty(smallIcon)) {
                builder.setSmallIcon(R.drawable.icon_notification);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Bitmap bitmap = BitmapFactory.decodeFile(smallIcon);
                    if (bitmap != null) {
                        builder.setSmallIcon(IconCompat.createWithBitmap(bitmap));
                    } else {
                        builder.setSmallIcon(R.drawable.icon_notification);
                    }
                } else {
                    builder.setSmallIcon(R.drawable.icon_notification);
                }
            }
        } catch (Exception e) {
            builder.setSmallIcon(R.drawable.icon_notification);
        }
        Bitmap bitmap_large_icon = null;
        try {
            if (!TextUtils.isEmpty(largeIcon)) {
                bitmap_large_icon = BitmapFactory.decodeFile(largeIcon);
                if (bitmap_large_icon != null) {
                    builder.setLargeIcon(bitmap_large_icon);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean shouldCheckBigText = true;
        try {
            if (!TextUtils.isEmpty(bigPicture)) {
                Bitmap bitmap_big_picture = BitmapFactory.decodeFile(bigPicture);
                if (bitmap_big_picture != null) {
                    if (bitmap_large_icon != null) {
                        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap_big_picture).bigLargeIcon(bitmap_large_icon));
                    } else {
                        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap_big_picture));
                    }
                    shouldCheckBigText = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (shouldCheckBigText && !TextUtils.isEmpty(bigText)) {
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
        }

        Notification notification = builder.build();
        manager.notify(getNotificationId(), notification);
    }
    public static void showNotification(Context context,
                                        String channelId,
                                        String channelName,
                                        String title,
                                        String subtitle,
                                        String bigText,
                                        Bitmap largeIcon,
                                        Bitmap bigPicture,
                                        boolean autoCancel,
                                        String action) throws Exception {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        if (!TextUtils.isEmpty(action)) {
            intent.putExtra("notification_data", action);
        }
        intent.setPackage(context.getPackageName());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_MUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.icon_notification)
                .setAutoCancel(autoCancel)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        if (!TextUtils.isEmpty(title)) {
            builder.setContentTitle(title);
        }
        if (!TextUtils.isEmpty(subtitle)) {
            builder.setContentText(subtitle);
        }
        try {
            if (largeIcon != null) {
                builder.setLargeIcon(largeIcon);
            }
            if (bigPicture != null) {
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bigPicture));
            } else if (!TextUtils.isEmpty(bigText)) {
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Notification notification = builder.build();
        manager.notify(getNotificationId(), notification);
    }

    public static void showNotification(Context context,
                                        String title,
                                        String subtitle,
                                        String bigText,
                                        Bitmap smallIcon,
                                        Bitmap largeIcon,
                                        Bitmap bigPicture,
                                        boolean autoCancel,
                                        String action) throws Exception {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        if (!TextUtils.isEmpty(action)) {
            intent.putExtra("notification_data", action);
        }
        intent.setPackage(context.getPackageName());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_MUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(autoCancel)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH);
        if (!TextUtils.isEmpty(title)) {
            builder.setContentTitle(title);
        }
        if (!TextUtils.isEmpty(subtitle)) {
            builder.setContentText(subtitle);
        }
        if (smallIcon == null){
            builder.setSmallIcon(R.drawable.icon_notification);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                builder.setSmallIcon(IconCompat.createWithBitmap(smallIcon));
            } else {
                builder.setSmallIcon(R.drawable.icon_notification);
            }
        }
        try {
            if (largeIcon != null) {
                builder.setLargeIcon(largeIcon);
            }
            if (bigPicture != null) {
                builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bigPicture));
            } else if (!TextUtils.isEmpty(bigText)) {
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Notification notification = builder.build();
        manager.notify(getNotificationId(), notification);
    }

    private static int getNotificationId() {
        return new Random().nextInt() % 100;
    }
}