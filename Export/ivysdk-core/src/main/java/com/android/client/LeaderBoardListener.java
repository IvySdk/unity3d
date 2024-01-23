package com.android.client;

/**
 * Created by song on 16/6/20.
 */
public interface LeaderBoardListener {
  void onSubmitScoreResult(boolean success, String leaderBoardTag);

  void onReceiveLeaderBoardResult(boolean success, String leaderBoardTag, String data);

  void onReceiveExtraResult(boolean success, String data);

  void onSaveDataResult(boolean success);

  void onReceiveDataResult(boolean success, String data);

  void onSalesClick(int saleId);

  void onVerifyCodeResult(boolean success);
}
