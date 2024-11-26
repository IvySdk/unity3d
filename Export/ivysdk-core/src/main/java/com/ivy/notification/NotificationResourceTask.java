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
import java.net.HttpURLConnection;
import java.net.URL;

public class NotificationResourceTask extends Worker {

    public NotificationResourceTask(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    public Bitmap downloadImage(Context context, String imageUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);

        } catch (Exception e) {
        }
        return bitmap;
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Data data = getInputData();
            String channelId = data.getString("channelId");
            String channelName = data.getString("channelName");
            String title = data.getString("title");
            String subtitle = data.getString("subtitle");
            String action = data.getString("clickAction");
            String bigText = data.getString("bigText");
            String largeIcon = data.getString("largeIcon");
            String bigPicture = data.getString("bigPicture");
            String sound = data.getString("sound");
            Boolean autoClose = data.getBoolean("autoClose", true);
            Bitmap bitmap_large_icon = null;
            if (!TextUtils.isEmpty(largeIcon)) {
                bitmap_large_icon = downloadImage(getApplicationContext(), largeIcon);
            }
            Bitmap bitmap_big_picture = null;
            if (!TextUtils.isEmpty(bigPicture)) {
                bitmap_big_picture = downloadImage(getApplicationContext(), bigPicture);
            }
            NotificationUtil.showNotification(getApplicationContext(), channelId, channelName, title, subtitle, bigText, bitmap_large_icon, bitmap_big_picture, autoClose, action);

            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.retry();
    }
}
