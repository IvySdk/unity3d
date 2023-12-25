package com.android.client;

public interface GoogleListener {
  void onSuccess(String googleId, String googleEmail);

  void onFails();
}
