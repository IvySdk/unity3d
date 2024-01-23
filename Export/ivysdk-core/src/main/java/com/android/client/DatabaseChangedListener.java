package com.android.client;

public interface DatabaseChangedListener {
  void onData(String collection, String jsonData);
}
