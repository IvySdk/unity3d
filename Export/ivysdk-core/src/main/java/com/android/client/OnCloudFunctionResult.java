package com.android.client;

public interface OnCloudFunctionResult {
  void onResult(String data);
  void onFail(String errorMessage);
}
