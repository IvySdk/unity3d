package com.ivy.task;

public interface TaskFeedback<V> {
  void onCancel();

  void onError(Exception exception);

  void onFinish(V v);

  void onStart();
}
