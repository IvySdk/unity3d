package com.ivy.ads.models;

import androidx.annotation.Nullable;

import com.ivy.util.Logger;

import org.json.JSONObject;

/**
 * 广告组的配置信息
 */
public class AdProviderGridPayload {
  private static final String TAG = Logger.createTag(AdProviderGridPayload.class);
  public AdRequestTimeout adRequestTimeoutOverride;

  public AdProviderGridDetails details;

  public JSONObject placement;

  public JSONObject providerPayloadJson;

  public JSONObject weight;
  public JSONObject attribute;

  public static AdProviderGridPayload readFromJSONObject(JSONObject jsonObject) {
    AdProviderGridPayload gridPayload = new AdProviderGridPayload();
    gridPayload.details = new AdProviderGridDetails();
    gridPayload.details.readFromJSONObject(jsonObject);
    gridPayload.adRequestTimeoutOverride = new AdRequestTimeout();
    gridPayload.placement = jsonObject.optJSONObject("p");
    gridPayload.weight = jsonObject.optJSONObject("weight");
    gridPayload.attribute = jsonObject.optJSONObject("attribute");

    return gridPayload;
  }

  @Nullable
  public static AdProviderGridPayload getAdProviderGridPayload(JSONObject gridAdProvider) {
    AdProviderGridPayload providerPayload = readFromJSONObject(gridAdProvider);
    if (providerPayload.details == null || providerPayload.details.integrationType == null) {
      if (Logger.isDebugEnabled()) {
        Logger.error(TAG, "Missing 'details' json subsection from provider payload", new Exception());
      }
      return null;
    } else if (providerPayload.details.integrationData == null || (providerPayload.details.integrationData.sdkId == null && providerPayload.details.integrationData.templateName == null)) {
      return null;
    } else {
      providerPayload.providerPayloadJson = gridAdProvider;
      return providerPayload;
    }
  }

  public static class AdRequestTimeout {
    public String adCoppaDynamic = null;
    public int adProviderAgeLimit = -1;
    public int adRequestTimeoutSeconds;
  }
}
