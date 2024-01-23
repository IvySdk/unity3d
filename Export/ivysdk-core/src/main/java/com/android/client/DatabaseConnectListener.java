package com.android.client;

public interface DatabaseConnectListener {
  void onSuccess();
  void onFail();
  void onAccountLinkFail();
}
