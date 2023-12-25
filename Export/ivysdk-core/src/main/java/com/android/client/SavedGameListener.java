package com.android.client;

public interface SavedGameListener {
  void onDataRead(String name, String data);
  void onDataWritten(String name);
  void onFail();
}
