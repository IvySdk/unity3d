package com.ivy.billing;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.android.client.OnSkuDetailsListener;
import com.android.client.SKUDetail;
import com.ivy.IvySdk;
import com.ivy.billing.impl.IPurchaseQueryCallback;
import com.ivy.event.CommonEvents;
import com.ivy.event.EventBus;
import com.ivy.event.EventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PurchaseManagerWrapper implements EventListener {
  protected Activity activity;
  private List<String> iapIds;
  private PurchaseManager purchaseManager;

  public PurchaseManagerWrapper(Activity activity) {
    this.activity = activity;
    EventBus.getInstance().addListener(CommonEvents.BILLING_PURCHASE_STATE_CHANGE, this);
  }

  public void setStoreItems(Map<String, JSONObject> storeItems) {
    if (this.purchaseManager != null) {
      this.purchaseManager.setStoreItems(storeItems);
    }
  }

  public void lateInit(@NonNull Context context) {
    try {
      this.purchaseManager = IvySdk.getPurchaseManager(context);
    } catch(Throwable t) {
      // ignore
    }
  }

  public SKUDetail getSKUDetail(String iapId) {
    if (this.purchaseManager != null) {
      return this.purchaseManager.getSKUDetail(iapId);
    }
    return null;
  }


  @Override
  public void onEvent(int eventId, Object eventData) {
    switch (eventId) {
      case CommonEvents.BILLING_PURCHASE_STATE_CHANGE:
        PurchaseStateChangeData data = (PurchaseStateChangeData) eventData;
        switch (data.getPurchaseState()) {
          case PURCHASED:
            onBuyComplete(data.getItemId(), data.getOrderId(), data.getPurchaseToken(), data.getDeveloperPayload());
            return;
          case CANCELED:
            onBuyFail(data.getItemId(), true);
            return;
          case ERROR:
            onBuyFail(data.getItemId(), false);
            return;
          default:
            return;
        }
      default:
        return;
    }
  }

  private void onBuyComplete(String iapId, String orderId, String purchaseToken, String developerPayload) {
  }

  private void onBuyFail(String iapId, boolean canceled) {
  }

  public boolean isStoreAvailable() {
    return true;
  }

  public void startLoadingStoreData(@NonNull final Context context, final List<String> iapIdsS, Map<String, JSONObject> storeItems) {
    IvySdk.runOnUiThreadCustom(new Runnable() {
      public void run() {
        if (PurchaseManagerWrapper.this.purchaseManager == null) {
          PurchaseManagerWrapper.this.lateInit(context);
        }
        PurchaseManagerWrapper.this.iapIds = iapIdsS;
        PurchaseManagerWrapper.this.setStoreItems(storeItems);
        PurchaseManagerWrapper.this.purchaseManager.checkBillingSupported(iapIds);
      }
    });
  }

  public void queryPurchases(String productId) {
    if (this.purchaseManager != null) {
      PurchaseManagerWrapper.this.purchaseManager.queryPurchases(productId);
    }
  }

  public void queryUnconsumedPurchases() {
    if (this.purchaseManager != null) {
      PurchaseManagerWrapper.this.purchaseManager.queryUnconsumedPurchases();
    }
  }

  public void querySKUDetails(List<String> iapIds, OnSkuDetailsListener onSkuDetailsListener) {
    IvySdk.runOnUiThreadCustom(new Runnable() {
      @Override
      public void run() {
        try {
          if (purchaseManager != null) {
            purchaseManager.querySKUDetails(iapIds, onSkuDetailsListener);
          }
        } catch(Throwable t) {
          // ignore
          t.printStackTrace();
        }
      }
    });
  }

  public void getPurchaseHistory(String skuType, IPurchaseQueryCallback<List<JSONObject>> callback) {
    if (purchaseManager != null) {
       purchaseManager.getPurchaseHistory(skuType, callback);
    } else {
      if (callback != null) callback.onResult(null);
    }
  }

  public void changeSku(String newIapId, String oldIapId, String payload) {
    IvySdk.runOnUiThreadCustom(new Runnable() {
      public void run() {
        if (PurchaseManagerWrapper.this.purchaseManager != null) {
          PurchaseManagerWrapper.this.purchaseManager.changeSku(newIapId, oldIapId, payload);
        }
      }
    });
  }

  public void setPayVerifyUrl(String verifyUrl) {
    if (this.purchaseManager != null) {
      this.purchaseManager.setPayVerifyUrl(verifyUrl);
    }
  }

  public boolean isClientCheck() {
    if (this.purchaseManager != null) {
      return this.purchaseManager.isClientCheck();
    }
    return false;
  }

  public void startBuying(String iapId) {
    startBuying(iapId, null);
  }

  public void setBillItemName(String itemId) {
    if (purchaseManager != null) {
      purchaseManager.setBillItemId(itemId);
    }
  }

  public void startBuying(final String iapId, final String developerPayload) {
    IvySdk.runOnUiThreadCustom(new Runnable() {
      public void run() {
        if (PurchaseManagerWrapper.this.purchaseManager != null) {
          try {
            PurchaseManagerWrapper.this.purchaseManager.buy(iapId, developerPayload);
          } catch(Throwable t) {
            // ignore
          }
        }
      }
    });
  }
}
