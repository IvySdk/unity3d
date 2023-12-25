package com.ivy.facebook;

public interface FacebookLoginListener {
  void onReceiveLoginResult(boolean success);
  void onReceiveFriends(String friends);
}
