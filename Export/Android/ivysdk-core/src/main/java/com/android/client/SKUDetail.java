package com.android.client;

import com.android.billingclient.api.SkuDetails;

import org.json.JSONException;
import org.json.JSONObject;

public class SKUDetail {
  private String mSku;
  private String mType;
  private String mPrice;
  private long mPriceAmountMicros;
  private String mPriceCurrencyCode;
  private long mOriginalPriceAmountMicros;
  private String mOriginalPrice;
  private String mTitle;
  private String mDescription;
  private double usd;

  public SKUDetail(SkuDetails skuDetails, double usd) {
    this.mType = skuDetails.getType();
    this.mSku = skuDetails.getSku();
    this.mPrice = skuDetails.getPrice();
    this.mPriceAmountMicros = skuDetails.getPriceAmountMicros();
    this.mPriceCurrencyCode = skuDetails.getPriceCurrencyCode();
    this.mOriginalPriceAmountMicros = skuDetails.getOriginalPriceAmountMicros();
    this.mOriginalPrice = skuDetails.getOriginalPrice();
    this.mTitle = skuDetails.getTitle();
    this.mDescription = skuDetails.getDescription();
    this.usd = usd;
  }

  public String getSku() {
    return mSku;
  }

  public boolean isSubscription() {
    return "subs".equals(mType);
  }

  public String getPrice() {
    return mPrice;
  }

  public long getPriceAmountMicros() {
    return mPriceAmountMicros;
  }

  public String getPriceCurrencyCode() {
    return mPriceCurrencyCode;
  }

  public String getTitle() {
    return mTitle;
  }

  public String getDescription() {
    return mDescription;
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    try {
      json.put("id", this.mSku);
      json.put("type", this.mType);
      json.put("price", this.mPrice);
      if (this.mOriginalPrice != null) {
        json.put("original_price", this.mOriginalPrice);
      }

      json.put("price_amount", this.mPriceAmountMicros / 1000000.0f);
      json.put("original_price_amount", this.mOriginalPriceAmountMicros / 1000000.0f);
      json.put("currency", this.mPriceCurrencyCode);
      json.put("title", this.mTitle);
      json.put("desc", this.mDescription);
      json.put("usd", this.usd);

      return json;
    } catch(JSONException ex) {
        ex.printStackTrace();
    }
    return json;
  }

  @Override
  public String toString() {
    return toJson().toString();
  }
}
