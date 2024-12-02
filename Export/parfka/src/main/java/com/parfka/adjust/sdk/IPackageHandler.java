package com.parfka.adjust.sdk;

import android.content.Context;

public interface IPackageHandler {
    void init(IActivityHandler activityHandler, Context context, boolean startsSending);

    void addPackage(ActivityPackage activityPackage);

    void sendNow(ActivityPackage activityPackage);

    void sendFirstPackage();

    void sendNextPackage(ResponseData responseData);

    void closeFirstPackage(ResponseData responseData, ActivityPackage activityPackage);

    void pauseSending();

    void resumeSending();

    void updatePackages(SessionParameters sessionParameters);

    void flush();

    String getBasePath();

    String getGdprPath();

    void teardown();
}
