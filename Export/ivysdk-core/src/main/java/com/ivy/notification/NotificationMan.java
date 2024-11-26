package com.ivy.notification;

import android.content.Context;
import android.text.TextUtils;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

public class NotificationMan {

    public static void pushMessage(Context context,
                                   String tag,
                                   String title,
                                   String subtitle,
                                   String bigText,
                                   String largeIcon,
                                   String bigPicture,
                                   long delay,
                                   boolean autoCancel,
                                   String action,
                                   boolean repeat,
                                   boolean onNetWorkOn,
                                   boolean requireCharging) throws Exception {
        if (repeat) {
            pushPeriodicTask(context, tag, title, subtitle, bigText, largeIcon, bigPicture, delay, autoCancel, action, onNetWorkOn, requireCharging);
        } else {
            pushOneTimeTask(context, tag, title, subtitle, bigText, largeIcon, bigPicture, delay, autoCancel, action, onNetWorkOn, requireCharging);
        }
    }


    /**
     * @param tag        任务标志，可根据此标志关闭任务
     * @param title      通知栏标题
     * @param subtitle   通知栏副标题
     * @param delay      延迟时间，单位秒
     * @param autoCancel 是否自动关闭
     */
    public static void pushOneTimeTask(Context context,
                                       String tag,
                                       String title,
                                       String subtitle,
                                       String bigText,
                                       String largeIcon,
                                       String bigPicture,
                                       long delay,
                                       boolean autoCancel,
                                       String action,
                                       boolean onNetWorkOn,
                                       boolean requireCharging) throws Exception {
        if (TextUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("notification task tag can't be null");
        }
        if (TextUtils.isEmpty(title)) {
            throw new IllegalArgumentException("notification title tag can't be null");
        }
        if (TextUtils.isEmpty(subtitle)) {
            throw new IllegalArgumentException("notification subtitle tag can't be null");
        }
        Data data = new Data.Builder()
                .putString("title", title)
                .putString("subtitle", subtitle)
                .putString("action", action)
                .putString("bigText", bigText)
                .putString("largeIcon", largeIcon)
                .putString("bigPicture", bigPicture)
                .putBoolean("autoClose", autoCancel)
                .build();
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(onNetWorkOn ? NetworkType.CONNECTED : NetworkType.NOT_REQUIRED)
                .setRequiresCharging(requireCharging)
                .build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(NotificationTask.class)
                .setConstraints(constraints)
                .setInputData(data)
                .setInitialDelay(delay, TimeUnit.SECONDS)
                .setBackoffCriteria(BackoffPolicy.LINEAR, 5, TimeUnit.MINUTES)//任务重试间隔
                .addTag(tag)
                .build();
        WorkManager.getInstance(context).enqueueUniqueWork(tag, ExistingWorkPolicy.KEEP, oneTimeWorkRequest);
    }

    public static void pushPeriodicTask(Context context,
                                        String tag,
                                        String title,
                                        String subtitle,
                                        String bigText,
                                        String largeIcon,
                                        String bigPicture,
                                        long delay,
                                        boolean autoCancel,
                                        String action,
                                        boolean onNetWorkOn,
                                        boolean requireCharging) throws Exception {
        if (TextUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("notification task tag can't be null");
        }
        if (TextUtils.isEmpty(title)) {
            throw new IllegalArgumentException("notification title tag can't be null");
        }
        if (TextUtils.isEmpty(subtitle)) {
            throw new IllegalArgumentException("notification subtitle tag can't be null");
        }
        cancelTask(context, tag);
        Data data = new Data.Builder()
                .putString("title", title)
                .putString("subtitle", subtitle)
                .putString("action", action)
                .putString("bigText", bigText)
                .putString("largeIcon", largeIcon)
                .putString("bigPicture", bigPicture)
                .putBoolean("autoClose", autoCancel)
                .build();
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(onNetWorkOn ? NetworkType.CONNECTED : NetworkType.NOT_REQUIRED)
                .setRequiresCharging(requireCharging)
                .build();
        PeriodicWorkRequest oneTimeWorkRequest = new PeriodicWorkRequest.Builder(NotificationTask.class, delay, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .setInputData(data)
                .setBackoffCriteria(BackoffPolicy.LINEAR, 5, TimeUnit.MINUTES)//任务重试间隔
                .addTag(tag)
                .build();
        WorkManager.getInstance(context).enqueue(oneTimeWorkRequest);
    }

    /**
     * 关闭对应tag的任务，无法确保一定会关闭
     *
     * @param context
     * @param tag
     */
    public static void cancelTask(Context context, String tag) {
        WorkManager.getInstance(context).cancelAllWorkByTag(tag);
    }

    /**
     * 关闭所有任务
     *
     * @param context
     */
    public static void cancelAll(Context context) {
        WorkManager.getInstance(context).cancelAllWork();
    }


}
