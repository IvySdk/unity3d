package com.ivy.billing;


import com.android.client.OnSkuDetailsListener;
import com.android.client.SKUDetail;
import com.ivy.billing.impl.IPurchaseQueryCallback;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface PurchaseManager {
  enum PurchaseState {
    PURCHASED,
    CANCELED,
    ERROR;

    public static PurchaseState valueOf(int index) {
      PurchaseState[] values = values();
      if (index < 0 || index >= values.length) {
        return CANCELED;
      }
      return values[index];
    }
  }

  boolean buy(String str, String str2);

  void checkBillingSupported(List<String> list);

  void setStoreItems(Map<String, JSONObject> storeItems);

  JSONObject getStoreItem(String productId);

  void queryPurchases(String productId);

  void queryUnconsumedPurchases();

//  List<JSONObject> getPurchaseHistory(String skuType);

  void getPurchaseHistory(String skuType, IPurchaseQueryCallback<List<JSONObject>> callback);

  void changeSku(String newIapId, String oldIapId, String payload);

  SKUDetail getSKUDetail(String iapId);

  void querySKUDetails(List<String> iapIds, OnSkuDetailsListener onSkuDetailsListener);

  void setPayVerifyUrl(String verifyUrl);

  void setBillItemId(String itemId);

  boolean isClientCheck();
}
