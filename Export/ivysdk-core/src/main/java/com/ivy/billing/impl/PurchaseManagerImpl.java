package com.ivy.billing.impl;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.android.client.AndroidSdk;
import com.android.client.OnSkuDetailsListener;
import com.android.client.SKUDetail;
import com.ivy.IvySdk;
import com.ivy.ads.events.EventID;
import com.ivy.ads.events.EventParams;
import com.ivy.billing.BillingConfigurator;
import com.ivy.billing.PaymentVerifiedListener;
import com.ivy.billing.PurchaseManager;
import com.ivy.billing.PurchaseStateChangeData;
import com.ivy.event.CommonEvents;
import com.ivy.event.EventBus;
import com.ivy.event.EventListener;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.networks.ui.dialog.ImmersiveDialog;
import com.ivy.util.CommonUtil;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class PurchaseManagerImpl implements PurchaseManager, EventListener, PurchasesUpdatedListener {
    private static final String TAG = PurchaseManagerImpl.class.getName();
    private String currentIapId;
    private String currentBillItemId;
    private final EventBus eventBus;
    private volatile boolean iabSetupFinished = false;
    private boolean init = false;
    private EventTracker eventTracker = null;
    private BillingClient billingClient;
    private final BillingPreferences preferences;
    private SharedPreferences sp;
    private boolean shouldRetryInventory = false;
    private final Map<String, SKUDetail> skuDetailMap = new HashMap<>();
    private final Map<String, ProductDetails> storeSkuDetailsMap = new HashMap<>();
    private Map<String, JSONObject> storeItems = new HashMap<>();
    private int connectRetriesCount = 0;
    private final ProductDetailsResponseListener inAppProductDetailsResponseListener = new ProductDetailsResponseListener() {
        @Override
        public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull List<ProductDetails> list) {
            if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK || list == null) {
                Logger.error(TAG, "Query inventory failed, errorCode: " + billingResult.getResponseCode());
                return;
            }
            for (ProductDetails details : list) {
                String sku = details.getProductId();
                Logger.debug(TAG, "Add Sku:" + sku);
                JSONObject storeItem = storeItems.get(sku);
                if (storeItem != null) {
                    double price = storeItem.optDouble("usd", details.getOneTimePurchaseOfferDetails().getPriceAmountMicros() / 1000000.0f);
                    skuDetailMap.put(details.getProductId(), new SKUDetail(details, price));
                    storeSkuDetailsMap.put(details.getProductId(), details);
                }
            }
            fireEvent(CommonEvents.BILLING_STORE_LOADED, BillingClient.ProductType.INAPP);
        }
    };
    private final ProductDetailsResponseListener subsProductDetailsResponseListener = new ProductDetailsResponseListener() {
        @Override
        public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull List<ProductDetails> list) {
            if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK || list == null) {
                Logger.error(TAG, "Query inventory failed, errorCode: " + billingResult.getResponseCode());
                return;
            }
            for (ProductDetails details : list) {
                String sku = details.getProductId();

                Logger.debug(TAG, "Add Sku:" + sku);
                JSONObject storeItem = storeItems.get(sku);
                if (storeItem != null) {
                    double price = 0.0d;
                    try {
                       // String offerToken = details.getSubscriptionOfferDetails().get(0).getOfferToken();
                        long priceAmountMicros = details.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros();
                        price = storeItem.optDouble("usd", priceAmountMicros / 1000000.0f);
                    } catch (Exception e) {
                        price = storeItem.optDouble("usd", 0.0f);
                    }
                    skuDetailMap.put(details.getProductId(), new SKUDetail(details, price));
                    storeSkuDetailsMap.put(details.getProductId(), details);
                }
            }
            fireEvent(CommonEvents.BILLING_STORE_LOADED, BillingClient.ProductType.SUBS);
            Logger.debug(TAG, "queryPurchases: SUBS ");
            handleUnConsumedPurchases(true);
        }
    };
    public PurchaseManagerImpl(@NonNull Context context, EventBus eventBus, EventTracker eventLogger) {
        this.eventBus = eventBus;
        this.preferences = BillingConfigurator.setUpBillingPreferences();
        try {
            this.sp = context.getSharedPreferences("pays", Context.MODE_PRIVATE);
            this.billingClient = BillingClient.newBuilder(context).enablePendingPurchases().setListener(this).build();
            this.eventTracker = eventLogger;
            eventBus.addListener(CommonEvents.BILLING_PURCHASE_STATE_CHANGE, this);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    private void handlePurchase(BillingResult billingResult, final Purchase purchase) {
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            Logger.debug(PurchaseManagerImpl.TAG, "Purchase successful");
            processPurchase(purchase, false);
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ERROR) {
            Logger.debug(TAG, "IAB BAD RESPONSE: ");
            PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.ERROR, purchase, false);
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            Logger.debug(TAG, "Use Cancelled ");
            PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.CANCELED, purchase, false);
        } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            Logger.debug(TAG, "ITEM_ALREADY_OWNED: ");
            processPurchase(purchase, false);
        } else {
            Logger.debug(TAG, "Billing Response Code: " + billingResult.getResponseCode());
            PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.ERROR, purchase, false);
        }
    }
    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> purchases) {
        try {
            // Logic from onActivityResult should be moved here.
            int responseCode = billingResult.getResponseCode();
            Logger.debug(TAG, "onPurchasesUpdated: " + responseCode);
            if (purchases != null && purchases.size() > 0) {
                for (Purchase purchase : purchases) {
                    if (purchase != null) {
                        handlePurchase(billingResult, purchase);
                    }
                }
            } else {
                // Handle an error caused by a user cancelling or  the purchase flow.
                Logger.error(TAG, "onPurchasesUpdated, purchases is empty, responseCode " + responseCode);
                if (responseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
                    // already owned the item, but purchase is null, we will query the purchase history and restore
                    handleUnConsumedPurchases(false);
                } else {
                    PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.CANCELED, null, false);
                }
            }
        } catch (Throwable t) {
            // exception protect
            Logger.error(TAG, "onPurchasesUpdated exception ", t);
        }
    }
    private void querySkuAndPurchase(String sku) {
        boolean isConsumable = isConsumable(sku);
        QueryProductDetailsParams.Product product = QueryProductDetailsParams.Product.newBuilder()
                .setProductId(sku)
                .setProductType(isConsumable ? BillingClient.ProductType.INAPP : BillingClient.ProductType.SUBS)
                .build();
        QueryProductDetailsParams params = QueryProductDetailsParams
                .newBuilder()
                .setProductList(Collections.singletonList(product))
                .build();
        billingClient.queryProductDetailsAsync(params, new ProductDetailsResponseListener() {
            @Override
            public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull List<ProductDetails> list) {
                if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
                    Logger.error(TAG, "Query inventory failed, errorCode: " + billingResult.getResponseCode());
                    return;
                }
                for (ProductDetails details : list) {
                    String sku = details.getProductId();
                    Logger.debug(TAG, "Add SKU: " + sku);
                    JSONObject storeItem = storeItems.get(sku);
                    if (storeItem != null) {
                        double price = 0.0d;
                        if (details.getProductType() == BillingClient.ProductType.INAPP) {
                            price = storeItem.optDouble("usd", details.getOneTimePurchaseOfferDetails().getPriceAmountMicros() / 1000000.0f);
                        } else if (details.getProductType() == BillingClient.ProductType.SUBS) {
                            try {
                                price = storeItem.optDouble("usd", details.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros() / 1000000.0f);
                            } catch (Exception e) {
                                price = storeItem.optDouble("usd", 0.0d);
                            }
                        }
                        skuDetailMap.put(details.getProductId(), new SKUDetail(details, price));
                        storeSkuDetailsMap.put(details.getProductId(), details);
                    } else {
                        Logger.error(TAG, "StoreItem " + sku + " not defined");
                    }
                }
                currentIapId = sku;
                ProductDetails details = storeSkuDetailsMap.get(sku);
                if (details == null) {
                    Logger.debug(TAG, "sku " + sku + " not found, removed from store?");
                    return;
                }
                List<BillingFlowParams.ProductDetailsParams> productDetailsParamsList = new ArrayList<>();
                for (ProductDetails productDetails : list) {
                    String offerToken = productDetails.getProductType().equals(BillingClient.ProductType.SUBS) ? productDetails.getSubscriptionOfferDetails().get(0).getOfferToken() : "";
                    BillingFlowParams.ProductDetailsParams.Builder builder =  BillingFlowParams.ProductDetailsParams.newBuilder()
                            .setProductDetails(productDetails);
                    if (!TextUtils.isEmpty(offerToken)){
                        builder.setOfferToken(offerToken);
                    }
                    productDetailsParamsList.add(builder.build());
                }
                BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(productDetailsParamsList)
                        .setIsOfferPersonalized(true)
                        .build();
                Activity activity = IvySdk.getActivity();
                if (activity == null) {
                    Logger.error(TAG, "activity is disposed");
                    return;
                }
                billingClient.launchBillingFlow(activity, billingFlowParams);
            }
        });
    }
    private void queryInventoryAsync(List<String> iaps, List<String> subs) {
        if (iaps != null && iaps.size() > 0) {
            List<QueryProductDetailsParams.Product> productList = new ArrayList<>();
            for (String iap : iaps) {
                QueryProductDetailsParams.Product product = QueryProductDetailsParams.Product
                        .newBuilder()
                        .setProductId(iap)
                        .setProductType(BillingClient.ProductType.INAPP)
                        .build();
                productList.add(product);
            }
            QueryProductDetailsParams productDetailsParams = QueryProductDetailsParams.newBuilder()
                    .setProductList(productList)
                    .build();
            billingClient.queryProductDetailsAsync(productDetailsParams, inAppProductDetailsResponseListener);
        }
        if (subs != null && subs.size() > 0) {
            List<QueryProductDetailsParams.Product> productList = new ArrayList<>();
            for (String sub : subs) {
                QueryProductDetailsParams.Product product = QueryProductDetailsParams.Product
                        .newBuilder()
                        .setProductId(sub)
                        .setProductType(BillingClient.ProductType.SUBS)
                        .build();
                productList.add(product);
            }
            QueryProductDetailsParams productDetailsParams = QueryProductDetailsParams.newBuilder()
                    .setProductList(productList)
                    .build();
            billingClient.queryProductDetailsAsync(productDetailsParams, subsProductDetailsResponseListener);
        }
    }
    private void reconnectBillingClient() {
        connectRetriesCount++;
        Logger.debug(TAG, "reconnectBillingClient start, retry count: " + connectRetriesCount);
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult == null) {
                    Logger.debug(TAG, "onBillingSetupFinished, billing result null");
                    return;
                }
                Logger.debug(TAG, "onBillingSetupFinished, response Code: " + billingResult.getResponseCode());
                // Logic from ServiceConnection.onServiceConnected should be moved here.
                if (!PurchaseManagerImpl.this.iabSetupFinished) {
                    PurchaseManagerImpl.this.iabSetupFinished = true;
                    Logger.debug(PurchaseManagerImpl.TAG, "Setup finished");
                    int responseCode = billingResult.getResponseCode();
                    if (responseCode == BillingClient.BillingResponseCode.OK) {
                        Logger.debug(TAG, "Setup successful. Querying inventory");
                        // the payment system is valid
                        fireEvent(CommonEvents.BILLING_BECOMES_AVAILABLE, new ArrayList<>());
                    } else {
                        fireEvent(CommonEvents.BILLING_PAYMENT_SYSTEM_ERROR, responseCode);
                    }
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                // Logic from ServiceConnection.onServiceDisconnected should be moved here.
                Logger.warning(TAG, "onBillingServiceDisconnected, should retry to connect later");
                PurchaseManagerImpl.this.iabSetupFinished = false;
            }
        });
    }
    public void checkBillingSupported(final List<String> skus) {
        if (this.billingClient != null) {
            final List<String> iaps = new ArrayList<>();
            final List<String> subs = new ArrayList<>();
            if (skus != null) {
                for (String sku : skus) {
                    if (isSubscription(sku)) {
                        subs.add(sku);
                    } else {
                        if (isAutoLoadIap(sku)) {
                            iaps.add(sku);
                        }
                    }
                }
            }
            if (!this.init) {
                billingClient.startConnection(new BillingClientStateListener() {
                    @Override
                    public void onBillingSetupFinished(BillingResult billingResult) {
                        if (billingResult == null) {
                            Logger.debug(TAG, "onBillingSetupFinished, billing result null");
                            return;
                        }
                        Logger.debug(TAG, "onBillingSetupFinished, response Code: " + billingResult.getResponseCode());
                        // Logic from ServiceConnection.onServiceConnected should be moved here.
                        if (!PurchaseManagerImpl.this.iabSetupFinished) {
                            PurchaseManagerImpl.this.iabSetupFinished = true;
                            Logger.debug(PurchaseManagerImpl.TAG, "Setup finished");
                            int responseCode = billingResult.getResponseCode();
                            if (responseCode == BillingClient.BillingResponseCode.OK) {
                                Logger.debug(TAG, "Setup successful. Querying inventory");
                                // the payment system is valid
                                fireEvent(CommonEvents.BILLING_BECOMES_AVAILABLE, new ArrayList<>());
                                // query the iaps
                                queryInventoryAsync(iaps, subs);
                            } else {
                                fireEvent(CommonEvents.BILLING_PAYMENT_SYSTEM_ERROR, responseCode);
                            }
                        }
                    }
                    @Override
                    public void onBillingServiceDisconnected() {
                        // Logic from ServiceConnection.onServiceDisconnected should be moved here.
                        Logger.warning(TAG, "onBillingServiceDisconnected, should retry to connect later");
                        PurchaseManagerImpl.this.iabSetupFinished = false;
                    }
                });
                this.init = true;
            } else if (this.shouldRetryInventory) {
                this.shouldRetryInventory = false;
                queryInventoryAsync(iaps, subs);
            }
        }
    }
    private void logPurchase(Purchase purchase) {
        if (purchase == null) {
            return;
        }
        String orderId = purchase.getOrderId();
        if (orderId == null || "".equals(orderId)) {
            return;
        }
        String itemid = purchase.getSkus().get(0);
        if (!storeSkuDetailsMap.containsKey(itemid)) {
            return;
        }
        if (purchase.getPurchaseState() != Purchase.PurchaseState.PURCHASED) {
            return;
        }
        ProductDetails productDetails = storeSkuDetailsMap.get(itemid);
        float revenue = 0.0f;
        String currencyCode = "USD";
        String contentType = "inapp";
        if (productDetails != null) {
            contentType = productDetails.getProductType();
            JSONObject storeItem = storeItems.get(itemid);
            if (storeItem != null && storeItem.has("usd")) {
                currencyCode = "USD";
                revenue = (float) storeItem.optDouble("usd", 0);
                if (revenue < 0.1) {
                    if (contentType.equals(BillingClient.ProductType.INAPP)) {
                        currencyCode = productDetails.getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
                        revenue = (float) (productDetails.getOneTimePurchaseOfferDetails().getPriceAmountMicros() / 1000000.0);
                    } else if (contentType.equals(BillingClient.ProductType.SUBS)) {
                        try {
                            currencyCode = productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode();
                            revenue = (float) (productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros() / 1000000.0);
                        } catch (Exception e) {
                        }
                    }
                }
            } else {
                if (contentType.equals(BillingClient.ProductType.INAPP)) {
                    currencyCode = productDetails.getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
                    revenue = (float) (productDetails.getOneTimePurchaseOfferDetails().getPriceAmountMicros() / 1000000.0);
                } else if (contentType.equals(BillingClient.ProductType.SUBS)) {
                    try {
                        currencyCode = productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode();
                        revenue = (float) (productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros() / 1000000.0);
                    } catch (Exception e) {
                    }
                }
            }
        }
        eventTracker.logPurchase(contentType, itemid, currencyCode, revenue);
    }
    @Override
    public void onEvent(int eventId, Object eventData) {
        Logger.debug(TAG, "OnEvent called: " + eventId);
        switch (eventId) {
            case CommonEvents.BILLING_PURCHASE_STATE_CHANGE:
                final PurchaseStateChangeData data = (PurchaseStateChangeData) eventData;
                final String itemid = data.getItemId();
                switch (data.getPurchaseState()) {
                    case PURCHASED:
                        if (!data.getJustRestore()) {
                            String orderId = data.getOrderId();
                            if (orderId == null || "".equals(orderId)) {
                                return;
                            }
                            boolean eventLogged = sp.getBoolean(orderId + "_logged", false);
                            if (eventLogged) {
                                Logger.debug(TAG, "orderID: " + orderId + " already logged");
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString(EventParams.PARAM_PROVIDER, "google");
                            if (currentBillItemId != null) {
                                bundle.putString("reason", currentBillItemId);
                            }
                            bundle.putString(EventParams.PARAM_ITEMID, itemid);
                            bundle.putString(EventParams.PARAM_ORDERID, data.getOrderId());
                            ProductDetails productDetails = storeSkuDetailsMap.get(itemid);
                            float revenue = 0.0f;
                            String currencyCode = "USD";
                            if (productDetails != null) {
                                JSONObject storeItem = storeItems.get(itemid);
                                if (storeItem != null) {
                                    currencyCode = "USD";
                                    if (BillingClient.ProductType.INAPP.equals(productDetails.getProductType())) {
                                        revenue = (float) storeItem.optDouble("usd", productDetails.getOneTimePurchaseOfferDetails().getPriceAmountMicros() / 1000000.0);
                                        if (revenue < 0.1) {
                                            currencyCode = productDetails.getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
                                            revenue = (float) (productDetails.getOneTimePurchaseOfferDetails().getPriceAmountMicros() / 1000000.0);
                                        }
                                    } else if (BillingClient.ProductType.SUBS.equals(productDetails.getProductType())) {
                                        revenue = (float) storeItem.optDouble("usd", productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros() / 1000000.0);
                                        if (revenue < 0.1) {
                                            currencyCode = productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode();
                                            revenue = (float) (productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros() / 1000000.0);
                                        }
                                    }
                                } else {
                                    if (BillingClient.ProductType.INAPP.equals(productDetails.getProductType())) {
                                        currencyCode = productDetails.getOneTimePurchaseOfferDetails().getPriceCurrencyCode();
                                        revenue = (float) (productDetails.getOneTimePurchaseOfferDetails().getPriceAmountMicros() / 1000000.0);
                                    } else if (BillingClient.ProductType.SUBS.equals(productDetails.getProductType())) {
                                        currencyCode = productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode();
                                        revenue = (float) (productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros() / 1000000.0);
                                    }
                                }
                                bundle.putString(EventParams.PARAM_LABEL, productDetails.getProductType());
                                bundle.putString("currency", currencyCode);
                                if (BillingClient.ProductType.INAPP.equals(productDetails.getProductType())) {
                                    bundle.putString("pay_currency", productDetails.getOneTimePurchaseOfferDetails().getPriceCurrencyCode());
                                    bundle.putLong("pay_price_amount", productDetails.getOneTimePurchaseOfferDetails().getPriceAmountMicros());
                                } else if (BillingClient.ProductType.SUBS.equals(productDetails.getProductType())) {
                                    bundle.putString("pay_currency", productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceCurrencyCode());
                                    bundle.putLong("pay_price_amount", productDetails.getSubscriptionOfferDetails().get(0).getPricingPhases().getPricingPhaseList().get(0).getPriceAmountMicros());
                                }
                                bundle.putDouble(EventParams.PARAM_REVENUE, revenue);
                            } else {
                                JSONObject storeItem = storeItems.get(itemid);
                                if (storeItem != null) {
                                    currencyCode = "USD";
                                    revenue = (float) storeItem.optDouble("usd", 0);
                                    bundle.putString("currency", currencyCode);
                                    bundle.putDouble(EventParams.PARAM_REVENUE, revenue);
                                }
                            }
                            int totalOrders = sp.getInt("total_orders", 0) + 1;
                            bundle.putInt(EventParams.PARAM_TIMES, totalOrders);
                            float totalPaid = sp.getFloat("total_revenue", 0) + revenue;
                            bundle.putFloat("total_revenue", totalPaid);
                            bundle.putFloat(EventParams.PARAM_VALUE, revenue);
                            if (totalOrders == 1) {
                                bundle.putString(EventParams.PARAM_CATALOG, "first_purchase");
                            }
                            try {
                                String gameUUID = IvySdk.getUUID();
                                if (gameUUID != null && !"".equals(gameUUID)) {
                                    bundle.putString("character", gameUUID);
                                }
                                String firebaseUserID = AndroidSdk.getFirebaseUserId();
                                if (firebaseUserID != null && !"".equals(firebaseUserID)) {
                                    bundle.putString("roleId", firebaseUserID);
                                }
                                String savedMainLine = IvySdk.mmGetStringValue(IvySdk.KEY_GAME_MAIN_LINE, "");
                                if (!"".equals(savedMainLine)) {
                                    JSONObject mainLineData = new JSONObject(savedMainLine);
                                    Iterator<String> keys = mainLineData.keys();
                                    while (keys.hasNext()) {
                                        String key = keys.next();
                                        int value = mainLineData.getInt(key);
                                        bundle.putInt(key, value);
                                    }
                                }
                                String remaingCurrency = IvySdk.mmGetStringValue(IvySdk.KEY_VIRUTAL_CURRENCY, "");
                                if (!"".equals(remaingCurrency)) {
                                    JSONObject currencyData = new JSONObject(remaingCurrency);
                                    Iterator<String> keys = currencyData.keys();
                                    while (keys.hasNext()) {
                                        String key = keys.next();
                                        int value = currencyData.getInt(key);
                                        bundle.putInt(key, value);
                                    }
                                }
                                boolean debugMode = false;
                                try {
                                    ApplicationInfo ai = IvySdk.getActivity().getPackageManager().getApplicationInfo(IvySdk.getActivity().getPackageName(), PackageManager.GET_META_DATA);
                                    debugMode = ai.metaData.getBoolean("ivy.debug", false);
                                } catch (Exception e) {
                                    debugMode = false;
                                }
                                if (!debugMode) {
                                    this.eventTracker.logEvent(EventID.IAP_PURCHASED, bundle);
                                    sp.edit()
                                            .putInt("total_orders", totalOrders)
                                            .putFloat("total_revenue", totalPaid)
                                            .putBoolean(orderId + "_logged", true).apply();
                                }
                                Bundle pamBundle = new Bundle();
                                pamBundle.putString("product_id", itemid);
                                pamBundle.putDouble("price", revenue);
                                pamBundle.putString("currency", currencyCode);
                                pamBundle.putInt("quantity", 1);
                                pamBundle.putDouble("value", revenue);
                                    this.eventTracker.logToFirebase("in_app_purchase", pamBundle);
                            } catch (Throwable ignored) {
                            }
                            return;
                        }
                        return;
                    case CANCELED:
                        Bundle bundle = new Bundle();
                        bundle.putString(EventParams.PARAM_PROVIDER, "google");
                        bundle.putString(EventParams.PARAM_ITEMID, itemid);
                        this.eventTracker.logEvent("iap_cancel", bundle);
                        return;
                    default:
                        return;
                }
            default:
                Logger.error(TAG, "Unknown eventId=" + eventId);
                return;
        }
    }
    @Override
    public void changeSku(String newIapId, String oldIapId, String payload) {
        if (!iabSetupFinished) {
            IvySdk.debugToast("Billing Client is not ready.");
            reconnectBillingClient();
            return;
        }
        this.currentIapId = newIapId;
        if (payload != null) {
            sp.edit().putString(newIapId, payload).apply();
        }
        billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.SUBS).build(), new PurchasesResponseListener() {
            @Override
            public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> purchases) {
                if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
                    Logger.error(TAG, "queryPurchases failed: " + billingResult.getResponseCode() + ", " + billingResult.getDebugMessage());
                    return;
                }
                if (purchases == null || purchases.size() == 0) {
                    Logger.error(TAG, "No purchase found, change Sku not possible");
                    return;
                }
                for (Purchase purchase : purchases) {
                    String sku = purchase.getProducts().get(0);
                    if (sku.equals(oldIapId)) {
                        ProductDetails newProductDetails = storeSkuDetailsMap.get(newIapId);
                        if (newProductDetails != null) {
                            BillingFlowParams.SubscriptionUpdateParams updateBillingFlowParams = BillingFlowParams.SubscriptionUpdateParams
                                    .newBuilder()
                                    .setOldPurchaseToken(purchase.getPurchaseToken())
                                    .build();
                            BillingFlowParams params = BillingFlowParams.newBuilder()
                                    .setSubscriptionUpdateParams(updateBillingFlowParams)
                                    .setIsOfferPersonalized(true)
                                    .build();
                            BillingResult _billingResult = billingClient.launchBillingFlow(IvySdk.getActivity(), params);
                            if (_billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
                                Logger.error(TAG, "launchBillingFlow failed, code: " + _billingResult.getResponseCode() + ", " + _billingResult.getDebugMessage());
                            }
                        } else {
                            Logger.warning(TAG, "newIapId " + newIapId + " not found in store");
                        }
                        return;
                    }
                }
            }
        });
//    Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(BillingClient.ProductType.SUBS);
//
//    if (purchasesResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
//      Logger.error(TAG, "queryPurchases failed: " + purchasesResult.getResponseCode() + ", " + purchasesResult.getBillingResult().getDebugMessage());
//      return;
//    }
//
//    List<Purchase> purchases = purchasesResult.getPurchasesList();
//    if (purchases == null || purchases.size() == 0) {
//      Logger.error(TAG, "No purchase found, change Sku not possible");
//      return;
//    }
//
//    for (Purchase purchase : purchases) {
//      String sku = purchase.getSkus().get(0);
//      if (sku.equals(oldIapId)) {
//        SkuDetails newSkuDetails = storeSkuDetailsMap.get(newIapId);
//        if (newSkuDetails != null) {
//
//          BillingFlowParams flowParams = BillingFlowParams.newBuilder()
//            .setSubscriptionUpdateParams(BillingFlowParams.SubscriptionUpdateParams.newBuilder().setOldSkuPurchaseToken(purchase.getPurchaseToken()).build())
//            .setSkuDetails(newSkuDetails)
//            .build();
//          BillingResult billingResult = billingClient.launchBillingFlow(IvySdk.getActivity(), flowParams);
//          if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
//            Logger.error(TAG, "launchBillingFlow failed, code: " + billingResult.getResponseCode() + ", " + billingResult.getDebugMessage());
//          }
//        } else {
//          Logger.warning(TAG, "newIapId " + newIapId + " not found in store");
//        }
//        return;
//      }
//    }
        Logger.warning(TAG, "oldIapId " + oldIapId + " not subscribed, not able to upgrade");
    }
    private void handleUnConsumedPurchases(boolean subs) {
        Logger.debug(TAG, "handleUnConsumedPurchases, subs: " + subs);
        QueryPurchasesParams params = QueryPurchasesParams.newBuilder()
                .setProductType(subs ? BillingClient.ProductType.SUBS : BillingClient.ProductType.INAPP)
                .build();
        billingClient.queryPurchasesAsync(params, new PurchasesResponseListener() {
            @Override
            public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> list) {
                if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
                    Logger.debug(TAG, "queryPurchases failed: " + billingResult.getResponseCode() + ", " + billingResult.getDebugMessage());
                    return;
                }
                if (list.size() > 0) {
                    for (Purchase purchase : list) {
                        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
                            Logger.debug(TAG, "Handle purchased purchase: " + purchase.toString());
                            boolean isNewUser = IvySdk.mmGetBoolValue("billingNewUser", true);
                            IvySdk.mmSetBoolValue("billingNewUser", false);
                            processPurchase(purchase, isNewUser);
                        } else {
                            Logger.debug(TAG, "Purchase state: " + purchase.getPurchaseState());
                            Logger.debug(TAG, "Purchase: " + purchase.toString());
                        }
                    }
                }
            }
        });
    }
    @Override
    public boolean buy(String iapId, String developerPayload) {
        try {
            if (!iabSetupFinished) {
                IvySdk.debugToast("Billing Client is not ready.");
                reconnectBillingClient();
                return false;
            }
            if (developerPayload != null) {
                sp.edit().putString(iapId, developerPayload).apply();
            } else {
                sp.edit().remove(iapId).apply();
            }
            if (!storeSkuDetailsMap.containsKey(iapId)) {
                Logger.debug(TAG, "iapId " + iapId + " not preload, we try to load and start buy process");
                querySkuAndPurchase(iapId);
                return false;
            }
            this.currentIapId = iapId;
            try {
                ProductDetails productDetails = storeSkuDetailsMap.get(iapId);
                if (productDetails == null) {
                    IvySdk.debugToast("Billing Client is not ready for iap: " + iapId);
                    return false;
                }
                // Retrieve a value for "skuDetails" by calling querygSkuDetailsAsync().
                String offerToken = productDetails.getProductType().equals(BillingClient.ProductType.SUBS) ? productDetails.getSubscriptionOfferDetails().get(0).getOfferToken() : null;
                BillingFlowParams.ProductDetailsParams.Builder builder = BillingFlowParams.ProductDetailsParams.newBuilder();
                if (!TextUtils.isEmpty(offerToken)){
                    builder.setOfferToken(offerToken);
                }

                BillingFlowParams.ProductDetailsParams productDetailsParams = builder
                        .setProductDetails(productDetails)
                        .build();
                BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(Collections.singletonList(productDetailsParams))
                        .setIsOfferPersonalized(true)
                        .build();
                Activity activity = IvySdk.getActivity();
                if (activity == null) {
                    Logger.error(TAG, "activity is disposed");
                    return false;
                }
                if (billingClient != null) {
                    BillingResult billingResult = billingClient.launchBillingFlow(activity, flowParams);
                    int billingResponseCode = billingResult.getResponseCode();
                    if (billingResponseCode != BillingClient.BillingResponseCode.OK) {
                        Logger.error(TAG, "launchBillingFlow failed, code: " + billingResult.getResponseCode() + ", " + billingResult.getDebugMessage());
                    }
                    if (billingResponseCode == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
                        // in this case, will try to consume this
                        handleUnConsumedPurchases(false);
                    }
                    return true;
                } else {
                    Logger.error(TAG, "BillingClient is not initialized");
                }
            } catch (Exception ex) {
                Logger.error(TAG, "launchBillingFlow error", ex);
            }
            return true;
        } catch (Throwable e) {
            Logger.error(TAG, "launchBillingFlow error", e);
            PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.ERROR, null, false);
            return false;
        }
    }
    private void purchaseStateChange(PurchaseState state, Purchase purchase, boolean justRestore) {
        purchaseStateChange(state, purchase, justRestore, false, false);
    }
    private void purchaseStateChange(PurchaseState state, Purchase purchase, boolean justRestore, boolean verified, boolean force) {
        if (purchase == null) {
            Logger.warning(TAG, "Purchase is null");
            return;
        }
        String sku = purchase != null ? purchase.getSkus().get(0) : this.currentIapId;
        long purchaseTime = purchase != null ? purchase.getPurchaseTime() : System.currentTimeMillis();
        String orderID = (purchase == null || purchase.getOrderId().equals("")) ? sku + "_" + purchaseTime : purchase.getOrderId();
        String developerPayload = sp.contains(sku) ? sp.getString(sku, "") : null;
        String serverDeveloperPayload = sp.contains(sku + "_server") ? sp.getString(sku + "_server", "") : null;
        if (serverDeveloperPayload != null && !isClientCheck()) {
            developerPayload = serverDeveloperPayload;
        }
        if (sku != null && isConsumable(sku) && !"".equals(orderID)) {
            if (PurchaseState.PURCHASED.equals(state)) {
                if (sp.contains(orderID + "_r_send")) {
                    Logger.debug(TAG, "orderId already filled");
                    return;
                }
            }
        }
        boolean isAutoRenewing = purchase != null && purchase.isAutoRenewing();
        int quantity = 0;
        PurchaseStateChangeData changeData = new PurchaseStateChangeData(orderID, state, sku, quantity, purchaseTime, developerPayload, justRestore, purchase != null ? purchase.getPurchaseToken() : null, isAutoRenewing);
        if (purchase != null) {
            changeData.setSignature(purchase.getSignature());
            changeData.setReceipt(purchase.getOriginalJson());
            changeData.setPackageName(purchase.getPackageName());
            SKUDetail skuDetail = skuDetailMap.get(sku);
            if (skuDetail != null) {
                changeData.setSkuJson(skuDetail.toString());
            }
        }
        fireEvent(CommonEvents.BILLING_PURCHASE_STATE_CHANGE, changeData);
        if (sku != null && isConsumable(sku) && !"".equals(orderID)) {
            if (PurchaseState.PURCHASED.equals(state)) {
                sp.edit().putString(orderID + "_r_send", "send").apply();
            }
        }
    }
    @Override
    public void setStoreItems(Map<String, JSONObject> storeItems) {
        this.storeItems = storeItems;
    }
    private boolean isAutoLoadIap(String productId) {
        JSONObject item = this.storeItems.get(productId);
        if (item != null) {
            if (item.optInt("autoload") == 1) {
                return true;
            }
        }
        return false;
    }
    private boolean isSubscription(String productId) {
        JSONObject item = this.storeItems.get(productId);
        if (item != null) {
            if (item.optInt("repeat") == 0) {
                return true;
            }
        }
        return false;
    }
    private boolean isConsumable(String productId) {
        JSONObject item = this.storeItems.get(productId);
        if (item != null) {
            if (item.optInt("repeat") == 0) {
                return false;
            }
        }
        return true;
    }
    @Override
    public void querySKUDetails(List<String> iapIds, OnSkuDetailsListener onSkuDetailsListener) {
        List<QueryProductDetailsParams.Product> unQueriedIaps = new ArrayList<>();
        for (String iapId : iapIds) {
            if (!this.skuDetailMap.containsKey(iapId)) {
                unQueriedIaps.add(QueryProductDetailsParams.Product.newBuilder().setProductId(iapId).setProductType(BillingClient.ProductType.INAPP).build());
            }
        }
        if (unQueriedIaps.size() == 0) {
            if (onSkuDetailsListener != null) {
                onSkuDetailsListener.onReceived();
            }
            return;
        }
        QueryProductDetailsParams params = QueryProductDetailsParams.newBuilder()
                .setProductList(unQueriedIaps)
                .build();
        billingClient.queryProductDetailsAsync(params, new ProductDetailsResponseListener() {
            @Override
            public void onProductDetailsResponse(@NonNull BillingResult billingResult, @NonNull List<ProductDetails> list) {
                if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
                    Logger.error(TAG, "Query inventory failed, errorCode: " + billingResult.getResponseCode());
                    return;
                }
                Logger.debug(TAG, "Query inapp inventory was successful");
                if (list.size() > 0) {
                    for (ProductDetails details : list) {
                        String sku = details.getProductId();
                        Logger.debug(TAG, "Add SKU: " + sku);
                        JSONObject storeItem = storeItems.get(sku);
                        if (storeItem != null) {
                            try {
                                double usd = storeItem.optDouble("usd", details.getOneTimePurchaseOfferDetails().getPriceAmountMicros() / 1000000.0f);
                                skuDetailMap.put(details.getProductId(), new SKUDetail(details, usd));
                                storeSkuDetailsMap.put(details.getProductId(), details);
                            } catch (Exception e) {
                                Logger.error(TAG, "StoreItem " + sku + "  info error");
                            }
                        } else {
                            Logger.error(TAG, "StoreItem " + sku + " not defined");
                        }
                    }
                }
                if (onSkuDetailsListener != null) {
                    onSkuDetailsListener.onReceived();
                }
            }
        });
    }
    @Override
    public JSONObject getStoreItem(String productId) {
        return storeItems.get(productId);
    }
    @Override
    public SKUDetail getSKUDetail(String iapId) {
        return this.skuDetailMap.get(iapId);
    }
    @Override
    public void queryPurchases(String productId) {
        if (isConsumable(productId)) {
            handleUnConsumedPurchases(false);
        } else {
            handleUnConsumedPurchases(true);
        }
    }
    @Override
    public void queryUnconsumedPurchases() {
        handleUnConsumedPurchases(false);
    }
    @Override
    public void getPurchaseHistory(String skuType, IPurchaseQueryCallback<List<JSONObject>> callback) {
        billingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder()
                        .setProductType(BillingClient.ProductType.SUBS.equals(skuType) ? BillingClient.ProductType.SUBS : BillingClient.ProductType.INAPP)
                        .build(),
                new PurchasesResponseListener() {
                    @Override
                    public void onQueryPurchasesResponse(@NonNull BillingResult billingResult, @NonNull List<Purchase> purchases) {
                        if (billingResult.getResponseCode() != BillingClient.BillingResponseCode.OK) {
                            Logger.error(TAG, "getPurchaseHistory failed, error code: " + billingResult.getResponseCode());
                            Logger.error(TAG, billingResult.getDebugMessage());
                            if (callback != null) {
                                callback.onResult(null);
                            }
                            return;
                        }
                        if (purchases == null || purchases.size() == 0) {
                            if (callback != null) {
                                callback.onResult(null);
                            }
                            return;
                        }
                        List<JSONObject> result = new ArrayList<>();
                        for (Purchase purchase : purchases) {
                            JSONObject item = new JSONObject();
                            try {
                                if (purchase != null) {
                                    String sku = purchase.getSkus().get(0);
                                    item.put("orderId", purchase.getOrderId());
                                    item.put("sku", sku);
                                    item.put("purchaseTime", purchase.getPurchaseTime());
                                    item.put("purchaseState", purchase.getPurchaseState());
                                    item.put("purchaseToken", purchase.getPurchaseToken());
                                    item.put("isAutoRenewing", purchase.isAutoRenewing());
                                    JSONObject storeItem = getStoreItem(sku);
                                    if (storeItem != null) {
                                        item.put("billId", storeItem.optInt("billId"));
                                    }
                                }
                            } catch (Exception ex) {
                                Logger.error(TAG, "create purchase history failed", ex);
                            }
                            result.add(item);
                        }
                        if (callback != null) {
                            callback.onResult(result);
                        }
                    }
                });
//    Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases("subs".equals(skuType) ? BillingClient.SkuType.SUBS : BillingClient.SkuType.INAPP);
//    if (purchasesResult.getBillingResult().getResponseCode() != BillingClient.BillingResponseCode.OK) {
//      Logger.error(TAG, "getPurchaseHistory failed, error code: " + purchasesResult.getBillingResult().getResponseCode());
//      Logger.error(TAG, purchasesResult.getBillingResult().getDebugMessage());
//      return null;
//    }
//
//    List<Purchase> purchases = purchasesResult.getPurchasesList();
//    if (purchases == null || purchases.size() == 0) {
//      return new ArrayList<>();
//    }
//    List<JSONObject> result = new ArrayList<>();
//    for (Purchase purchase : purchases) {
//      JSONObject item = new JSONObject();
//      try {
//        if (purchase != null) {
//          String sku = purchase.getSkus().get(0);
//          item.put("orderId", purchase.getOrderId());
//          item.put("sku", sku);
//          item.put("purchaseTime", purchase.getPurchaseTime());
//          item.put("purchaseState", purchase.getPurchaseState());
//          item.put("purchaseToken", purchase.getPurchaseToken());
//          item.put("isAutoRenewing", purchase.isAutoRenewing());
//          JSONObject storeItem = getStoreItem(sku);
//          if (storeItem != null) {
//            item.put("billId", storeItem.optInt("billId"));
//          }
//        }
//      } catch (Exception ex) {
//        Logger.error(TAG, "create purchase history failed", ex);
//      }
//      result.add(item);
//    }
//
//    return result;
    }
    private void fireEvent(final int eventId, final Object eventData) {
        IvySdk.runOnUiThreadCustom(() -> PurchaseManagerImpl.this.eventBus.fireEvent(eventId, eventData));
    }
    private boolean verifyPurchase(Purchase purchase) {
        String base64PublicKey = preferences.publicKey;
        if (base64PublicKey == null || "".equals(base64PublicKey)) {
            Logger.warning(TAG, "IAP public key is not configured, will NOT verify the purchase");
            return true;
        }
        String signature = purchase.getSignature();
        String purchaseData = purchase.getOriginalJson();
        boolean verified = Security.verifyPurchase(base64PublicKey, purchaseData, signature);
        if (!verified) {
            Logger.error(TAG, "purchase verified failed");
            Logger.debug(TAG, "OrderID: " + purchase.getOrderId());
            Logger.debug(TAG, "Signature: " + purchase.getSignature());
            Logger.debug(TAG, "PurchaseData: " + purchase.getOriginalJson());
        } else {
            Logger.debug(TAG, "Purchase Verified");
        }
        return verified;
    }

    private void logEvent(String event, String sku, boolean isNewUser, long duration){
        Bundle bundle = new Bundle();
        bundle.putString("sku", sku);
        bundle.putString("isNewUser", isNewUser ? "1" : "0");
        if (duration > 0){
            bundle.putString("duration", String.valueOf(duration));
        }
        bundle.putString("uuid", IvySdk.getUUID());
        IvySdk.logToFirebase(event, bundle);
    }

    private void processPurchase(Purchase purchase, boolean isNewUser) {
        Logger.debug(TAG, "processPurchase");
        if (purchase == null || purchase.getPurchaseState() != Purchase.PurchaseState.PURCHASED) {
            Logger.warning(TAG, "Purchase state not PURCHASED, " + purchase.getPurchaseState());
            return;
        }
        String sku = purchase.getProducts().get(0);
        if (isConsumable(sku)) {
            long startTime = System.currentTimeMillis();
            logEvent("bill_start_consume", sku, isNewUser, -1);
            final Activity activity = IvySdk.getActivity();
            showProgressBar(activity);
            Logger.debug(TAG, "Auto consume this item");
            handleVerifiedPurchase(purchase, isNewUser, true, new PaymentVerifiedListener() {
                @Override
                public void onSuccess() {
                    logEvent("bill_verify_success", sku, isNewUser, System.currentTimeMillis() - startTime);
                    Logger.debug(TAG, "handleVerifiedPurchase for inapp onSuccess");
                    ConsumeParams consumeParams = ConsumeParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build();
                    billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
                        @Override
                        public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                            logEvent("bill_consume_end", sku, isNewUser, System.currentTimeMillis() - startTime);
                            hideProgressBar(activity);
                            if (billingResult == null) {
                                return;
                            }
                            Logger.debug(TAG, "Consume purchase response Code: " + billingResult.getResponseCode());
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.PURCHASED, purchase, false);
                            } else {
                                // How to deal with this case?
                            }
                        }
                    });
                }
                @Override
                public void onFail(int errorCode) {
                    logEvent("bill_verify_failed", sku, isNewUser, System.currentTimeMillis() - startTime);
                    Logger.debug(TAG, "handleVerifiedPurchase for inapp onFail, errorCode: " + errorCode);
                    hideProgressBar(activity);
                    PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.ERROR, purchase, false);
                    if (errorCode == SOFT_PURCHASE_ERROR) {
                        ConsumeParams consumeParams = ConsumeParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build();
                        billingClient.consumeAsync(consumeParams, new ConsumeResponseListener() {
                            @Override
                            public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                                Logger.warning(TAG, "Force consumed purchase");
                                if (billingResult != null) {
                                    Logger.error(TAG, "billingResult" + billingResult.getResponseCode() + ", " + billingResult.getDebugMessage());
                                }
                            }
                        });
                    }
                }
            });
        } else {
            if (!purchase.isAcknowledged()) {
                handleVerifiedPurchase(purchase, false, false, new PaymentVerifiedListener() {
                    @Override
                    public void onSuccess() {
                        Logger.debug(TAG, "handleVerifiedPurchase for subscription onSuccess");
                        Logger.debug(TAG, "Subscription required acknowledged");
                        AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build();
                        billingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
                            @Override
                            public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                                if (billingResult == null) {
                                    return;
                                }
                                int responseCode = billingResult.getResponseCode();
                                if (responseCode == BillingClient.BillingResponseCode.OK) {
                                    Logger.debug(TAG, "Good! purchase acknowledge successfully");
                                    PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.PURCHASED, purchase, false, false, true);
                                } else {
                                    Logger.debug(TAG, "Acknowledge purchase response Code: " + responseCode);
                                }
                            }
                        });
                    }
                    @Override
                    public void onFail(int errorCode) {
                        Logger.debug(TAG, "handleVerifiedPurchase for subscription onFail, errorCode: " + errorCode);
                        PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.ERROR, purchase, false);
                        if (errorCode == SOFT_PURCHASE_ERROR) {
                            AcknowledgePurchaseParams acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build();
                            billingClient.acknowledgePurchase(acknowledgePurchaseParams, new AcknowledgePurchaseResponseListener() {
                                @Override
                                public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                                    Logger.warning(TAG, "Force acknowledge Purchase");
                                    if (billingResult != null) {
                                        Logger.debug(TAG, "AcknowledgePurchase result: " + billingResult.toString());
                                    }
                                }
                            });
                        }
                    }
                });
            } else {
                Logger.debug(TAG, "Subscription already acknowledged: " + purchase.toString());
                PurchaseManagerImpl.this.purchaseStateChange(PurchaseState.PURCHASED, purchase, true, false, true);
            }
        }
    }
    private Dialog progress = null;
    public void hideProgressBar(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progress != null) {
                    try {
                        progress.dismiss();
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
        });
    }
    public void showProgressBar(final Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    if (progress != null) {
                        progress.dismiss();
                    }
                    progress = new ImmersiveDialog(activity);
                    progress.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    ProgressBar progressBar = new ProgressBar(activity, null, 16842874);
                    progress.requestWindowFeature(1);
                    progress.setContentView(progressBar);
                    progress.setOwnerActivity(activity);
                    progress.show();
                } catch (Throwable e) {
                    // ignore
                }
            }
        });
    }
    public static final int ERROR_ORDER_FORMAT = 6;
    public static final int ERROR_SIGNED_FAILED = 1;
    public static final int ERROR_RESPONSE_NOT_SUCCESS = 2;
    public static final int ERROR_RESPONSE_EMPTY = 3;
    public static final int ERROR_RESPONSE_WRONG_STATUS = 4;
    public static final int ERROR_VERIFY_SERVER_HTTP_ERROR = 5;
    public static final int SOFT_PURCHASE_ERROR = 10;
    private void logVerifyEvent(String sku, String orderId, int status, int errorCode, String reason, long startTimes) {
        Bundle bundle = new Bundle();
        if (sku != null) {
            bundle.putString(EventParams.PARAM_ITEMID, sku);
        }
        if (orderId != null) {
            bundle.putString(EventParams.PARAM_ORDERID, orderId);
        }
        bundle.putInt(EventParams.PARAM_TIMES, (int) ((System.currentTimeMillis() - startTimes) / 1000));
        if (errorCode > 0) {
            bundle.putString(EventParams.PARAM_LABEL, String.valueOf(errorCode));
        }
        if (reason != null) {
            bundle.putString(EventParams.PARAM_REASON, reason);
        }
        if (status == 1) {
            eventTracker.logEvent("iap_verified", bundle);
        } else {
            eventTracker.logEvent("iap_verified_failed", bundle);
        }
    }
    /**
     * 处理游戏发货.
     * 1. 客户端先进行public key的校验, 如果失败，直接fail
     * 2. 如果没有定义verify-url，直接成功
     * 3. 如果定义了verify-url, 如果verify-url明确的失败，则失败，否则成功!
     *
     * @param purchase 要处理的purchase数据
     * @param listener
     */
    private void handleVerifiedPurchase(final Purchase purchase, boolean isNewUser, boolean consumable, PaymentVerifiedListener listener) {
        if (purchase == null) {
            Logger.error(TAG, "purchase is null, ignore");
            return;
        }
        final String sku = purchase.getSkus().get(0);
        final String orderId = purchase.getOrderId();
        String orderPrefix = IvySdk.getGridConfigString("google.order.prefix");
        if (orderPrefix != null && !"".equals(orderPrefix)) {
            if (!orderId.startsWith(orderPrefix) && !orderId.startsWith(sku) && !"".equals(orderId)) {
                Logger.debug(TAG, "orderId not well, sku: " + sku + ", orderId " + orderId);
                listener.onFail(ERROR_ORDER_FORMAT);
                return;
            }
        }
        long httpStartTime = System.currentTimeMillis();
        if (!verifyPurchase(purchase)) {
            logVerifyEvent(sku, orderId, 0, ERROR_SIGNED_FAILED, "wrong_sign", httpStartTime);
            listener.onFail(ERROR_SIGNED_FAILED);
            return;
        }
        if (preferences.clientCheck) {
            Logger.debug(TAG, "drop the purchase info to client and let it to check");
            try {
                logPurchase(purchase);
            } catch (Exception ex) {
                // ignore
            }
            listener.onSuccess();
            return;
        }
        String verifyUrl = preferences.verifyUrl;
        if (verifyUrl == null || "".equals(verifyUrl)) {
            logVerifyEvent(sku, orderId, 1, ERROR_RESPONSE_NOT_SUCCESS, "empty_verify_url", httpStartTime);
            try {
                logPurchase(purchase);
            } catch (Exception ex) {
                // ignore
            }
            listener.onSuccess();
            return;
        }
        int billId = 0;
        JSONObject storeItem = storeItems.get(sku);
        if (storeItem != null) {
            billId = storeItem.optInt("billId", 0);
        }
        String countryCode = IvySdk.getCountryCode();
        String appid = IvySdk.getGridConfigString("appid");
        SKUDetail skuDetail = skuDetailMap.get(sku);
        FormBody.Builder formBuilder = new FormBody.Builder()
                .add("country", countryCode)
                .add("sku", sku)
                .add("payId", String.valueOf(billId))
                .add("orderId", orderId)
                .add("purchaseTime", String.valueOf(purchase.getPurchaseTime()))
                .add("purchaseToken", purchase.getPurchaseToken())
                .add("purchaseState", String.valueOf(purchase.getPurchaseState()))
                .add("uuid", IvySdk.getUUID())
                .add("packageName", purchase.getPackageName())
                .add("jsonData", purchase.getOriginalJson())
                .add("signature", purchase.getSignature())
                .add("sku_json", skuDetail != null ? skuDetail.toString() : "{}")
                .add("appid", appid != null ? appid : "");
        boolean hasPayload = sp.contains(sku);
        if (hasPayload) {
            String developerPayload = sp.getString(sku, null);
            if (developerPayload != null) {
                formBuilder.add("payload", developerPayload);
                Logger.debug(TAG, "payload -> " + developerPayload);
            }
        }
        RequestBody formBody = formBuilder.build();
        Logger.debug(TAG, "payId -> " + billId);
        Logger.debug(TAG, "OrderID -> " + purchase.getOrderId());
        Logger.debug(TAG, "Signature -> " + purchase.getSignature());
        Logger.debug(TAG, "PurchaseData -> " + purchase.getOriginalJson());
        Request request = new Request.Builder()
                .url(verifyUrl)
                .post(formBody)
                .build();
        Logger.debug(TAG, "Start send verify URL >>> " + verifyUrl);
        long startTime = System.currentTimeMillis();
        IvySdk.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (consumable) {
                    logEvent("bill_verify_net_failed", sku, isNewUser, System.currentTimeMillis() - startTime);
                }
                Logger.error(TAG, "Verify iap failed", e);
                Logger.warning(TAG, "The verify server is down?");
                String clazz = e != null ? e.getClass().getName() : "";
                if (preferences.mustVerify) {
                    logVerifyEvent(sku, orderId, 0, SOFT_PURCHASE_ERROR, "http_on_failed_" + clazz, httpStartTime);
                    listener.onFail(SOFT_PURCHASE_ERROR);
                } else {
                    logVerifyEvent(sku, orderId, 1, 0, "http_on_failed_" + clazz, httpStartTime);
                    listener.onSuccess();
                }
            }
            @Override
            public void onResponse(Call call, Response response) {
                if (consumable) {
                    logEvent("bill_verify_net_success", sku, isNewUser, System.currentTimeMillis() - startTime);
                }
                if (response == null || !response.isSuccessful()) {
                    Logger.error(TAG, "Verify server response error: " + (response != null ? response.code() : " empty"));
                    logVerifyEvent(sku, purchase.getOrderId(), 0, ERROR_RESPONSE_NOT_SUCCESS, "response_not_success", httpStartTime);
                    listener.onFail(ERROR_RESPONSE_NOT_SUCCESS);
                    return;
                }
                try {
                    String responseBody = response.body().string();
                    Logger.debug(TAG, "Receiving response >>>> " + responseBody);
                    String s;
                    if (responseBody.startsWith("{") && responseBody.endsWith("}")) {
                        s = responseBody;
                    } else {
                        s = CommonUtil.decodeWithKey(responseBody, "c3fcd3d76192e4007dfb496cca67e13b");
                    }
                    if (s == null || "".equals(s)) {
                        Logger.error(TAG, "Empty response, verify failed");
                        logVerifyEvent(sku, purchase.getOrderId(), 0, ERROR_RESPONSE_EMPTY, "response_empty", httpStartTime);
                        listener.onFail(ERROR_RESPONSE_EMPTY);
                        return;
                    }
                    Logger.debug(TAG, "Verify Result: " + s);
                    JSONObject o = new JSONObject(s);
                    int status = o.optInt("status");
                    if (status == 1) {
                        // use the server payload
                        if (o.has("payload")) {
                            String serverPayload = o.optString("payload", "");
                            Logger.debug(TAG, "Receiving payload " + serverPayload);
                            sp.edit().putString(sku + "_server", serverPayload).apply();
                        }
                        try {
                            logPurchase(purchase);
                        } catch (Exception ex) {
                            // ignore
                        }
                        logVerifyEvent(sku, orderId, 1, 0, "success", httpStartTime);
                        listener.onSuccess();
                    } else {
                        Logger.error(TAG, "Status Not Success >>> " + status);
                        logVerifyEvent(sku, orderId, 0, status, "status_not_success", httpStartTime);
                        listener.onFail(SOFT_PURCHASE_ERROR);
                    }
                } catch (Throwable t) {
                    Logger.error(TAG, "Error parse the verify response", t);
                    if (preferences.mustVerify) {
                        Logger.warning(TAG, "Force check enabled, onFail");
                        logVerifyEvent(sku, orderId, 0, SOFT_PURCHASE_ERROR, "exception_" + t.getClass().getName(), httpStartTime);
                        listener.onFail(SOFT_PURCHASE_ERROR);
                    } else {
                        Logger.warning(TAG, "Force check disabled, also onSuccess");
                        logVerifyEvent(sku, orderId, 1, SOFT_PURCHASE_ERROR, "force_check_disabled" + t.getClass().getName(), httpStartTime);
                        listener.onSuccess();
                    }
                }
                try {
                    response.close();
                } catch (Throwable t) {
                    //
                }
            }
        });
    }
    public boolean isClientCheck() {
        if (preferences != null) {
            return preferences.clientCheck;
        }
        return false;
    }
    public void setPayVerifyUrl(String verifyUrl) {
        Logger.debug(TAG, "Update verify URL >>> " + verifyUrl);
        if (preferences != null) {
            preferences.verifyUrl = verifyUrl;
        }
    }
    @Override
    public void setBillItemId(String itemId) {
        currentBillItemId = itemId;
    }
}