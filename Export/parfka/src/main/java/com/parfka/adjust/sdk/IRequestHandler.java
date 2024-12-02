package com.parfka.adjust.sdk;

public interface IRequestHandler {
    void init(IActivityHandler activityHandler, IPackageHandler packageHandler);

    void sendPackage(ActivityPackage activityPackage, int queueSize);

    void sendNow(ActivityPackage activityPackage);

    void teardown();
}
