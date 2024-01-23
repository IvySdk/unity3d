package com.ivy.networks;

public interface DownloadFeedback {
  void onSuccess(String filePath);

  void onFail();
}
