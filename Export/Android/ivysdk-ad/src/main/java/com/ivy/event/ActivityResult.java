package com.ivy.event;

import android.content.Intent;

public class ActivityResult {
  private Intent data;
  private int requestCode;
  private int resultCode;

  public ActivityResult(int requestCode, int resultCode, Intent data) {
    this.requestCode = requestCode;
    this.resultCode = resultCode;
    this.data = data;
  }

  public int getRequestCode() {
    return this.requestCode;
  }

  public void setRequestCode(int requestCode) {
    this.requestCode = requestCode;
  }

  public int getResultCode() {
    return this.resultCode;
  }

  public void setResultCode(int resultCode) {
    this.resultCode = resultCode;
  }

  public Intent getData() {
    return this.data;
  }

  public void setData(Intent data) {
    this.data = data;
  }

  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ActivityResult [requestCode=");
    builder.append(this.requestCode);
    builder.append(", resultCode=");
    builder.append(this.resultCode);
    builder.append(", data=");
    builder.append(this.data);
    builder.append("]");
    return builder.toString();
  }
}
