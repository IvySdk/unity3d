package com.ivy.ads.models;


public class GridAdShowTimeoutPayload {
  public Ad ad = new Ad();

  public static class Ac {
    public int interstitialInitialSilenceSessions;
    public int interstitialInitialSilenceTime;
    public String[] interstitialPreloadTimeouts;
    public String[] interstitialTimeouts;
    public ISTs[] transitions;
  }

  public static class Ad {
    public Ac aC = new Ac();
    public int timeToWaitForAdToShowSeconds;
  }

  public static class ISTs {
    public String from;
    public String to;
  }
}
