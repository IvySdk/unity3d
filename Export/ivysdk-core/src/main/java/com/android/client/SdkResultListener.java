package com.android.client;

/**
 * Created by song on 16/8/26.
 */
public interface SdkResultListener {
  void onInitialized();

  void onReceiveServerExtra(String data);

  void onReceiveNotificationData(String data);
}
