package com.android.client;

public interface UserCenterListener {
  void onReceiveLoginResult(boolean success);

  void onReceiveFriends(String friends);

  void onReceiveInviteResult(boolean success);

  void onReceiveChallengeResult(int count);

  void onReceiveLikeResult(boolean success);
}
