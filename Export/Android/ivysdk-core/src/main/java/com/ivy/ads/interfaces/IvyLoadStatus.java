package com.ivy.ads.interfaces;

public interface IvyLoadStatus {
  String OK = "ok";
  String NO_FILL = "no-fill";
  String TIMEOUT = "timeout";
  String OTHER = "other";
  String ERROR = "error";
  String BANNER_NO_FILL = "fail-nofill";
  String BANNER_TIMEOUT = "fail-timeout";
  String BANNER_OTHER = "fail-other";
}
