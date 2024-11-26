package com.ivy.payermax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.client.AndroidSdk;
import com.android.client.OnSkuDetailsListener;
import com.android.client.SKUDetail;
import com.ivy.IvySdk;
import com.ivy.billing.PurchaseManager;
import com.ivy.billing.impl.IPurchaseQueryCallback;
import com.ivy.event.CommonEvents;
import com.ivy.event.EventBus;
import com.ivy.event.EventListener;
import com.ivy.networks.tracker.EventTracker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class Order {
    public String sku;
    public String orderId;

    public static Order build(String sku, String orderId) {
        Order order = new Order();
        order.sku = sku;
        order.orderId = orderId;
        return order;
    }
}

public class PayerMaxPurchaseImpl implements PurchaseManager, EventListener {

    private final String TAG = "PAYERMAX";

    private String currentIapId;
    private Map<String, JSONObject> storeItems = new HashMap<>();
    private String currentBillItemId;
    private EventTracker eventTracker = null;
    private SharedPreferences sp;
    private EventBus eventBus;
    private final Map<String, SKUDetail> skuDetailMap = new HashMap<>();
    private final Map<String, SKUDetail> storeSkuDetailsMap = new HashMap<>();

    private OkHttpClient okHttpClient;

    public PayerMaxPurchaseImpl(@NonNull Context context, EventBus eventBus, EventTracker eventLogger) {
        this.eventBus = eventBus;
        try {
            this.sp = context.getSharedPreferences("pays", Context.MODE_PRIVATE);
            this.eventTracker = eventLogger;
            eventBus.addListener(CommonEvents.BILLING_PURCHASE_STATE_CHANGE, this);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private OkHttpClient getHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(2, TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }

    @Override
    public boolean buy(String iapId, String developerPayload) {
        if (developerPayload != null) {
            sp.edit().putString(iapId, developerPayload).apply();
        } else {
            sp.edit().remove(iapId).apply();
        }
        if (!storeSkuDetailsMap.containsKey(iapId)) {
            Log.e(TAG, "iapId " + iapId + " not preload, we try to load and start buy process");
            return false;
        }
        SKUDetail detail = storeSkuDetailsMap.get(iapId);
        this.currentIapId = iapId;

        //TODO: call pay
        try {
            FormBody body = new FormBody.Builder()
                    .add("action", "payermax_order")
                    .add("price", detail.usd + "")
                    .add("itemSku", iapId)
                    .add("country", Locale.getDefault().getCountry())
                    .add("pay_channel", "app")
                    .add("appid", AndroidSdk.getConfig(AndroidSdk.CONFIG_KEY_APP_ID))
                    .add("userId", AndroidSdk.getConfig(AndroidSdk.CONFIG_KEY_UUID))
                    .add("frontCallbackUrl", AndroidSdk.getConfig(AndroidSdk.CONFIG_KEY_PACKAGE_NAME) + "://payermax.callback")
                    .build();
            Request request = new Request.Builder()
                    .addHeader("Content-Type", "application/json")
                    .url("https://webpay.ivymobile.com/")
                    .post(body)
                    .build();
            new OkHttpClient.Builder().build().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Log.e(TAG, "call pay failed:" + e.getMessage());
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try {
                        String data = response.body().string();
                        Log.e(TAG, "call pay result:" + data);
                        JSONObject object = new JSONObject(data).getJSONObject("data");
                        String orderId = object.getString("orderId");
                       // addOrderId(iapId, orderId);
                        String url = object.getString("url");
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        IvySdk.getActivity().startActivity(intent);
                    } catch (Exception e) {
                        Log.e(TAG, "call pay failed:" + e.getMessage());
                    }
                }
            });

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void checkBillingSupported(List<String> skus) {
        fireEvent(CommonEvents.BILLING_BECOMES_AVAILABLE, new ArrayList<>());
        if (skus != null && storeItems != null) {
            for (String sku : skus) {
                JSONObject storeItem = storeItems.get(sku);
                String type = "inapp";
                if (isSubscription(sku)) {
                    type = "subs";
                } else {
                    if (isAutoLoadIap(sku)) {
                        type = "inapp";
                    }
                }
                skuDetailMap.put(sku, new SKUDetail(sku, storeItem, type));
                storeSkuDetailsMap.put(sku, new SKUDetail(sku, storeItem, type));
            }
            fireEvent(CommonEvents.BILLING_STORE_LOADED, "inapp");
        }
    }

    @Override
    public void setStoreItems(Map<String, JSONObject> storeItems) {
        this.storeItems = storeItems;
    }

    @Override
    public JSONObject getStoreItem(String productId) {
        if (this.storeItems != null) {
            return this.storeItems.get(productId);
        }
        return null;
    }

    private List<Order> findUnConsumedOrderId(String sku) {
        List<Order> orders = new ArrayList<>();
        try {
            String data = IvySdk.mmGetStringValue("payermax", "");
            JSONObject object = new JSONObject(data);
            Iterator<String> it = object.keys();
            while (it.hasNext()) {
                String key = it.next();
                if (sku != null) {
                    if (key.equals(sku)) {
                        JSONArray array = object.optJSONArray(key);
                        if (array != null && array.length() > 0) {
                            for (int i = 0; i < array.length(); i++) {
                                Order order = Order.build(key, array.optString(i));
                                orders.add(order);
                            }
                        }
                    }
                } else {
                    JSONArray array = object.optJSONArray(key);
                    if (array != null && array.length() > 0) {
                        for (int i = 0; i < array.length(); i++) {
                            Order order = Order.build(key, array.optString(i));
                            orders.add(order);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    private void addOrderId(String sku, String order) {
        String data = IvySdk.mmGetStringValue("payermax", "");
        try {
            JSONObject object = new JSONObject(data);
            JSONArray array = object.optJSONArray(sku);
            if (array == null) {
                array = new JSONArray();
            }
            array.put(order);
            IvySdk.mmSetStringValue("payermax", object.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeOrderId(String sku, String order) {
        String data = IvySdk.mmGetStringValue("payermax", "");
        try {
            JSONObject object = new JSONObject(data);
            JSONArray array = object.optJSONArray(sku);
            if (array != null) {
                int index = -1;
                for (int i = 0; i < array.length(); i++) {
                    if (array.optString(i, "").equals(order)) {
                        index = i;
                        break;
                    }
                }
                if (index != -1) {
                    array.remove(index);
                }
            }
            IvySdk.mmSetStringValue("payermax", object.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createOrderId() {
        //TODO: create order id
        Random random = new Random();
        String seed = "qwertyuioplkjhgfdsazxcvbnm0123456789Q";
        StringBuilder builder = new StringBuilder();
        builder.append(System.currentTimeMillis());
        builder.append(UUID.randomUUID().toString().replaceAll("-", ""));
        for (int i = 0; i < 25; i++) {
            int index = random.nextInt(seed.length());
            builder.append(seed.charAt(index));
        }
        builder.reverse();
        return builder.substring(0, 64).toString();
    }

    @Override
    public void queryPurchases(String sku) {
        try {
            List<Order> orders = findUnConsumedOrderId(sku);
            //TODO：查询
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void queryUnconsumedPurchases() {
        try {
            List<Order> orders = findUnConsumedOrderId(null);
            //TODO：查询
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPurchaseHistory(String skuType, IPurchaseQueryCallback<List<JSONObject>> callback) {
        if (callback != null) {
            callback.onResult(null);
        }
    }

    @Override
    public void changeSku(String newIapId, String oldIapId, String payload) {

    }

    @Override
    public SKUDetail getSKUDetail(String iapId) {
        return this.skuDetailMap.get(iapId);
    }

    @Override
    public void querySKUDetails(List<String> iapIds, OnSkuDetailsListener onSkuDetailsListener) {
        if (onSkuDetailsListener != null) {
            onSkuDetailsListener.onReceived();
        }
    }

    @Override
    public void setPayVerifyUrl(String verifyUrl) {

    }

    @Override
    public void setBillItemId(String itemId) {
        currentBillItemId = itemId;
    }

    @Override
    public boolean isClientCheck() {
        return false;
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

    private void fireEvent(final int eventId, final Object eventData) {
        IvySdk.runOnUiThreadCustom(() -> PayerMaxPurchaseImpl.this.eventBus.fireEvent(eventId, eventData));
    }

    @Override
    public void onEvent(int i, Object obj) {

    }
}
