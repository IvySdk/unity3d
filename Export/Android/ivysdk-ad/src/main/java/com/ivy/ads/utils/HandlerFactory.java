package com.ivy.ads.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public final class HandlerFactory {
  public static Handler createHandler(String name) {
    HandlerThread handlerThread = new HandlerThread(name);
    handlerThread.start();
    return new Handler(handlerThread.getLooper());
  }

  public static Handler createHandler(Class clazz) {
    return createHandler(clazz.getSimpleName() + "Thread");
  }

  public static Handler createUiHandler() {
    return new Handler(Looper.getMainLooper());
  }
}
