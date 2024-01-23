package com.android.client;

public interface UrlListener {
  void onSuccess(int tag, String path);

  void onFailure(int tag);
}
