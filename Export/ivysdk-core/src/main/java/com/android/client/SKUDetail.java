package com.android.client;
import android.text.TextUtils;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.ProductDetails;
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
  public double usd;

  public SKUDetail(String sku, JSONObject storeItem, String type){
    this.mType = type;
    this.mSku = sku;
    this.usd = storeItem.optDouble("usd", 0.0f);
    this.mPrice = "USD" + usd;
    this.mTitle = storeItem.optString("title", "");
    if (TextUtils.isEmpty(mTitle)){
      this.mTitle = storeItem.optString("feename", "");
    }
    this.mDescription = storeItem.optString("desc", "");
  }


  public SKUDetail(ProductDetails productDetails, double usd) {
    this.mType = productDetails.getProductType();
    this.mSku = productDetails.getProductId();
    if (BillingClient.ProductType.INAPP.equals(productDetails.getProductType())) {
      this.mPrice = productDetails.getOneTimePurchaseOfferDetails().getFormattedPrice();
      this.mPriceAmountMicros = productDetails.getOneTimePurchaseOfferDetails().getPriceAmountMicros();
      this.mPriceCurrencyCode = productDetails.getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
    } else if (BillingClient.ProductType.SUBS.equals(productDetails.getProductType())) {
      ProductDetails.PricingPhase pricingPhase = productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0);
      this.mPrice = pricingPhase.getFormattedPrice();
      this.mPriceAmountMicros = pricingPhase.getPriceAmountMicros();
      this.mPriceCurrencyCode = pricingPhase.getPriceCurrencyCode();
    }
//    this.mPrice = skuDetails.getPrice();
//    this.mPriceAmountMicros = skuDetails.getPriceAmountMicros();
//    this.mPriceCurrencyCode = skuDetails.getPriceCurrencyCode();
//    this.mOriginalPriceAmountMicros = skuDetails.getOriginalPriceAmountMicros();
//    this.mOriginalPrice = skuDetails.getOriginalPrice();
    this.mTitle = productDetails.getTitle();
    this.mDescription = productDetails.getDescription();
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
    } catch (JSONException ex) {
      ex.printStackTrace();
    }
    return json;
  }
  @Override
  public String toString() {
    return toJson().toString();
  }
}