package com.ivy.notification;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.InputStream;

public class NotificationTask extends Worker {
    public NotificationTask(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    @NonNull
    @Override
    public Result doWork() {
        try {
            Data data = getInputData();
            String title = data.getString("title");
            String subtitle = data.getString("subtitle");
            String action = data.getString("action");
            String bigText = data.getString("bigText");
            String smallIcon = data.getString("smallIcon");
            String largeIcon = data.getString("largeIcon");
            String bigPicture = data.getString("bigPicture");
            Boolean autoClose = data.getBoolean("autoClose", true);

            Bitmap bitmapSmallIcon = null;
            if (!TextUtils.isEmpty(smallIcon)) {
                try {
                    InputStream input = getApplicationContext().getAssets().open(smallIcon);
                    bitmapSmallIcon = BitmapFactory.decodeStream(input);
                    input.close();
                } catch (Exception e) {

                }
            }

            Bitmap bitmapLargeIcon = null;
            if (!TextUtils.isEmpty(largeIcon)) {
                try {
                    InputStream input = getApplicationContext().getAssets().open(largeIcon);
                    bitmapLargeIcon = BitmapFactory.decodeStream(input);
                    input.close();
                } catch (Exception e) {

                }
            }

            Bitmap bitmapPicture = null;
            if (!TextUtils.isEmpty(bigPicture)) {
                try {
                    InputStream input = getApplicationContext().getAssets().open(bigPicture);
                    bitmapPicture = BitmapFactory.decodeStream(input);
                    input.close();
                } catch (Exception e) {

                }
            }

NotificationUtil.showNotification(getApplicationContext(), title, subtitle, bigText, bitmapSmallIcon, bitmapLargeIcon, bitmapPicture, autoClose, action);
//            NotificationUtil.showNotification(getApplicationContext(), title, subtitle, bigText, smallIcon, largeIcon, bigPicture, autoClose, action);
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.success();
    }
}