//package com.ivy.task;
//
//import android.os.Handler;
//
//public class HandlerTaskFeedbackWrapper<V> implements TaskFeedback<V> {
//  private final TaskFeedback<V> feedback;
//  private final Handler handler;
//
//  public HandlerTaskFeedbackWrapper(TaskFeedback<V> feedback) {
//    this(feedback, new Handler());
//  }
//
//  public HandlerTaskFeedbackWrapper(TaskFeedback<V> feedback, Handler handler) {
//    this.feedback = feedback;
//    this.handler = handler;
//  }
//
//  public void onStart() {
//    this.handler.post(new Runnable() {
//      @Override
//      public void run() {
//        if (feedback != null) {
//          feedback.onStart();
//        }
//      }
//    });
//  }
//
//  public void onCancel() {
//    this.handler.post(new Runnable() {
//      @Override
//      public void run() {
//        if (feedback != null) {
//          feedback.onCancel();
//        }
//      }
//    });
//  }
//
//  public void onFinish(final V result) {
//    this.handler.post(new Runnable() {
//      public void run() {
//        if (feedback != null) {
//          feedback.onFinish(result);
//        }
//      }
//    });
//  }
//
//  public void onError(final Exception e) {
//    this.handler.post(new Runnable() {
//      public void run() {
//        if (feedback != null) {
//          feedback.onError(e);
//        }
//      }
//    });
//  }
//
//
//  public Handler getHandler() {
//    return this.handler;
//  }
//}
