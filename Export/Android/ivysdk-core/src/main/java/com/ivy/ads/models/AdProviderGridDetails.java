package com.ivy.ads.models;


import org.json.JSONObject;

public class AdProviderGridDetails {
  public IntegrationData integrationData;
  public IntegrationType integrationType;

  public void readFromJSONObject(JSONObject jsonObject) {
    this.integrationData = new IntegrationData();
    this.integrationData.sdkId = jsonObject.optString("provider");
    this.integrationData.templateName = "";

    this.integrationType = IntegrationType.sdk;
  }

  public enum IntegrationType {
    s2s,
    sdk
  }

  public static class IntegrationData {
    // @JsonProperty("sI")
    public String sdkId;
    // @JsonProperty("tN")
    public String templateName;
  }
}
