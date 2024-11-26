package com.android.client;

public interface AccountLinkListener {
  void onFail(String currentProviderId, String targetProviderId, String error);
  void onSuccess(String currentProviderId, String targetProviderId);
}
