package com.parfka.adjust.sdk;

public interface InstallReferrerReadListener {
    void onInstallReferrerRead(String installReferrer, long referrerClickTimestampSeconds, long installBeginTimestampSeconds);
}
