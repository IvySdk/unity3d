package com.android.client;

public interface DatabaseConnectListener2 {
    void onSuccess();

    void onFail(String errorCode, String errorMessage);
}