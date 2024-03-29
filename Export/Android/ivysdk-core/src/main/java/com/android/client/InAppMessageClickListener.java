package com.android.client;

public interface InAppMessageClickListener {
  void messageDisplayed(String campaignId, String dataJson);
  void messageClicked(String actionUrl);
}
