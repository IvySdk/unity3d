package com.android.client;

public interface ShareResultListener {
  void onSuccess(String postId);
  void onCancel();
  void onError(String message);
}
