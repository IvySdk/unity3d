package com.ivy.notification;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

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
            String largeIcon = data.getString("largeIcon");
            String bigPicture = data.getString("bigPicture");
            Boolean autoClose = data.getBoolean("autoClose", true);
            NotificationUtil.showNotification(getApplicationContext(), title, subtitle, bigText, largeIcon, bigPicture, autoClose, action);
//            NotificationUtil.showNormalNotification(getApplicationContext(),
//                    data.getString("title"),
//                    data.getString("subtitle"),
//                    data.getString("action"),
//                    data.getBoolean("autoClose", true));
        } catch (Exception e){
            e.printStackTrace();
        }
        return Result.success();
    }


}
