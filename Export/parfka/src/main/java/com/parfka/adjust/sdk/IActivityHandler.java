package com.parfka.adjust.sdk;

import android.content.Context;
import android.net.Uri;

/**
 * Created by pfms on 15/12/14.
 */
public interface IActivityHandler {
    void init(AdjustConfig config);

    void onResume();

    void onPause();

    void trackEvent(AdjustEvent event);

    void trackEventNow(AdjustEvent event);

    void finishedTrackingActivity(ResponseData responseData);

    void setEnabled(boolean enabled);

    boolean isEnabled();

    void updateAttribution(AdjustAttribution attribution);

    void readOpenUrl(Uri url, long clickTime);

    boolean updateAttributionI(AdjustAttribution attribution);

    void launchEventResponseTasks(EventResponseData eventResponseData);

    void launchSessionResponseTasks(SessionResponseData sessionResponseData);

    void launchSdkClickResponseTasks(SdkClickResponseData sdkClickResponseData);

    void launchAttributionResponseTasks(AttributionResponseData attributionResponseData);

    void sendReftagReferrer();

    void sendInstallReferrer(String installReferrer, long referrerClickTimestampSeconds, long installBeginTimestampSeconds);

    void setOfflineMode(boolean enabled);

    void setAskingAttribution(boolean askingAttribution);

    void sendFirstPackages();

    void addSessionCallbackParameter(String key, String value);

    void addSessionPartnerParameter(String key, String value);

    void removeSessionCallbackParameter(String key);

    void removeSessionPartnerParameter(String key);

    void resetSessionCallbackParameters();

    void resetSessionPartnerParameters();

    void teardown();

    void setPushToken(String token, boolean preSaved);

    void gdprForgetMe();

    void gotOptOutResponse();

    Context getContext();

    String getAdid();

    AdjustAttribution getAttribution();

    AdjustConfig getAdjustConfig();

    DeviceInfo getDeviceInfo();

    ActivityState getActivityState();

    SessionParameters getSessionParameters();

    String getBasePath();

    String getGdprPath();
}
