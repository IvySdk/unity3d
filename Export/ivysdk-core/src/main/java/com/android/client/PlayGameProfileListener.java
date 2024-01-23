package com.android.client;

import org.json.JSONObject;

public interface PlayGameProfileListener {
  void onSuccess(JSONObject userProfile);
  void onFail(String message);
}
