package com.android.client;

public interface DatabaseListener {
  void onData(String collection, String data);
  void onSuccess(String collection);
  void onFail(String collection);
}
