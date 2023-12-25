package com.ivy.ads.interfaces;

public enum IvyShowFailStatus {
  OK("ok"),
  TIMEOUT("timeout"),
  OTHER("other");

  private final String mValue;

  IvyShowFailStatus(String value) {
    this.mValue = value;
  }

  public String toString() {
    return this.mValue;
  }
}
