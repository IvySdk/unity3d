package com.ivy.ads.models;

public class InterstitialTransition {
  private String transitionFrom;
  private String transitionTo;

  public void setTransitionFrom(String transitionFrom) {
    this.transitionFrom = transitionFrom;
  }

  public void setTransitionTo(String transitionTo) {
    this.transitionTo = transitionTo;
  }

  public boolean transitionEqualsTo(String from, String to) {
    return isEqualOrUniversal(this.transitionFrom, from) && isEqualOrUniversal(this.transitionTo, to);
  }

  private boolean isEqualOrUniversal(String gridTransitionName, String appTransitionName) {
    return gridTransitionName.equals("*") || gridTransitionName.equals(appTransitionName) || appTransitionName.equals("*");
  }
}
