package com.ivy.xsolla;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ivy.IvySdk;
import com.ivy.networks.grid.GridManager;
import com.xsolla.android.inventory.XInventory;
import com.xsolla.android.inventory.callback.ConsumeItemCallback;
import com.xsolla.android.inventory.callback.GetInventoryCallback;
import com.xsolla.android.inventory.entity.response.InventoryResponse;
import com.xsolla.android.payments.XPayments;
import com.xsolla.android.payments.data.AccessToken;
import com.xsolla.android.store.XStore;
import com.xsolla.android.store.callbacks.CreateOrderCallback;
import com.xsolla.android.store.callbacks.GetVirtualItemsCallback;
import com.xsolla.android.store.entity.request.payment.PaymentOptions;
import com.xsolla.android.store.entity.response.items.VirtualItemsResponse;
import com.xsolla.android.store.entity.response.payment.CreateOrderResponse;
import com.xsolla.android.subscriptions.XSubscriptions;
import com.xsolla.android.subscriptions.callback.GetSubscriptionDetailsCallback;
import com.xsolla.android.subscriptions.callback.GetSubscriptionPlansCallback;
import com.xsolla.android.subscriptions.callback.GetSubscriptionPurchaseUrlCallback;
import com.xsolla.android.subscriptions.callback.GetSubscriptionsCallback;
import com.xsolla.android.subscriptions.entity.response.ListPlans;
import com.xsolla.android.subscriptions.entity.response.ListSubscriptions;
import com.xsolla.android.subscriptions.entity.response.PaymentLink;
import com.xsolla.android.subscriptions.entity.response.Plan;
import com.xsolla.android.subscriptions.entity.response.Subscription;
import com.xsolla.android.subscriptions.entity.response.SubscriptionDetails;
import com.xsolla.android.subscriptions.entity.response.SubscriptionStatus;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

class XsollaUtil {
    public static final int XSOLLA_PAYMENT_VIRTUAL_REQUEST_CODE = 2568;
    public static final int XSOLLA_PAYMENT_SUBSCRIPTION_REQUEST_CODE = 2569;

    private XsollaLoginUtil loginUtil;

    private boolean logStatus = false;

    private boolean debug = false;
    private String currentPayingSku;

    private int projectId;
    private int oAuthId;
    private Map<String, VirtualProduct> virtualProductMap = new HashMap<>();
    private Map<String, SubscriptionPlan> subscriptionPlanMap = new HashMap<>();
    private IXsollaPayListener listener;
    private IXsollaLoginListener xsollaLoginListener = new IXsollaLoginListener() {
        @Override
        public void onSuccess() {
            DLog.d("xsolla login end & success");
            XsollaUtil.this.logStatus = true;
            initStore();
            loadVirtualProducts();
            loadSubscriptionPlans();
            if (listener != null) listener.onSuccess();
        }

        @Override
        public void onFail() {
            //..ignore
            DLog.e("xsolla login end & failed");
            XsollaUtil.this.logStatus = false;
            if (listener != null) listener.onFail();
        }
    };

    public void setListener(IXsollaPayListener listener) {
        this.listener = listener;
    }

    public XsollaUtil(Context context, boolean debug) {
        this.debug = debug;
        loginUtil = new XsollaLoginUtil();
        initProjectConfig();
        loginUtil.initLoginConfig(context, projectId + "", oAuthId);
        loginUtil.isLogIn(xsollaLoginListener);
    }

    public XsollaUtil(Context context, IXsollaPayListener listener, boolean debug) {
        this.debug = debug;
        this.listener = listener;
        loginUtil = new XsollaLoginUtil();
        initProjectConfig();
        loginUtil.initLoginConfig(context, projectId + "", oAuthId);
        loginUtil.isLogIn(xsollaLoginListener);
    }

    /**
     * 获取XSolla的配置信息
     */
    public void initProjectConfig() {
        try {
            if (!GridManager.getGridData().has("xsolla")) {
                DLog.e("get xsolla config failed!!! check it");
                return;
            }
            JSONObject config = GridManager.getGridData().getJSONObject("xsolla");
            projectId = config.getInt("projectId");
            oAuthId = config.getInt("oauthId");
        } catch (Exception e) {
            DLog.e("get xsolla config failed!!! check it");
        }
    }

    public void login() {
        loginUtil.Login(xsollaLoginListener);
    }

    public boolean isLogin() {
        return XsollaUtil.this.logStatus;
    }

    public void logout() {
        loginUtil.logout();
    }

    /**
     * 初始化 xsolla 商店
     */
    public void initStore() {
        DLog.d("xsolla start init store & subscriptins & pay history");
        String authToken = loginUtil.getToken();
        XStore.init(projectId, authToken);
        XInventory.init(projectId, authToken);
        XSubscriptions.init(projectId, debug);
        XSubscriptions.authenticate(authToken);
    }

    /**
     * 拉取货架上所有商品信息
     */
    public void loadVirtualProducts() {
        DLog.d("xsolla start load all virtual products");
        virtualProductMap.clear();
        XStore.getVirtualItems(new GetVirtualItemsCallback() {
            @Override
            public void onSuccess(@NotNull VirtualItemsResponse virtualItemsResponse) {
                if (virtualItemsResponse.getItems().size() > 0) {
                    DLog.d("xsolla load virtual payments data success");
                    for (VirtualItemsResponse.Item item : virtualItemsResponse.getItems()) {
                        VirtualProduct virtualProduct = new VirtualProduct(item);
                        virtualProductMap.put(item.getSku(), virtualProduct);
                        DLog.d("xsolla loaded virtual:" + item.getSku());
                    }
                    if (listener != null) listener.onInitResult(true);
                }
            }

            @Override
            public void onError(@Nullable Throwable throwable, @Nullable String s) {
                DLog.e("xsolla load payments data failed!!!");
                if (listener != null) listener.onInitResult(false);
            }
        });
    }

    /**
     * 货架上是否有此商品
     *
     * @param sku
     * @return
     */
    public boolean hasVirtualProduct(String sku) {
        return virtualProductMap != null && virtualProductMap.containsKey(sku);
    }

    public VirtualProduct getVirtualProduct(String sku) {
        if (hasVirtualProduct(sku)) {
            return virtualProductMap.get(sku);
        }
        return null;
    }

    /**
     * 根据商品sku发起购买
     *
     * @param sku
     */
    public void payForVirtualProduct(String sku) {
        DLog.d("xsolla start pay for: " + sku);
        if (virtualProductMap.containsKey(sku)) {
            currentPayingSku = sku;
            PaymentOptions paymentOptions = new PaymentOptions(null, null, debug, null, null);
            boolean finalDebug = debug;
            XStore.createOrderByItemSku(new CreateOrderCallback() {
                @Override
                public void onSuccess(@NotNull CreateOrderResponse createOrderResponse) {
                    DLog.d("xsolla create order success && jump payment page");
                    String token = createOrderResponse.getToken();
                    //int orderId = createOrderResponse.getOrderId();
                    Intent intent = XPayments.createIntentBuilder(IvySdk.getActivity())
                            .accessToken(new AccessToken(token))
                            .isSandbox(finalDebug)
                            .build();
                    IvySdk.getActivity().startActivityForResult(intent, XSOLLA_PAYMENT_VIRTUAL_REQUEST_CODE);
                }

                @Override
                public void onError(@Nullable Throwable throwable, @Nullable String s) {
                    //...订单创建失败
                    DLog.e("xsolla create order failed:" + s);
                    if (listener != null) {
                        listener.onPayFailed(sku);
                    }
                }
            }, sku, paymentOptions);
        } else {
            if (listener != null) {
                listener.onPayFailed(sku);
            }
            DLog.e("xsolla invalid sku to pay");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        DLog.d("xsolla onActivityResult:" + requestCode);
        if (requestCode == XSOLLA_PAYMENT_VIRTUAL_REQUEST_CODE) {
            XPayments.Result result = XPayments.Result.fromResultIntent(data);
            if (result.getStatus() == XPayments.Status.COMPLETED) {
                //.., pay success
                DLog.d("xsolla onActivityResult pay status success");
                XsollaUtil.this.queryVirtualProduct(currentPayingSku);
            } else {
                //... pay failed
                DLog.e("xsolla onActivityResult pay status fail");
                if (listener != null) listener.onPayFailed(currentPayingSku);
            }
        } else if (requestCode == XSOLLA_PAYMENT_SUBSCRIPTION_REQUEST_CODE) {
            querySubscriptionPlanPayResult(currentPayingSku);
        } else {
            if (loginUtil != null) loginUtil.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 查询当前用户已拥有的可消耗性商品并消耗
     */
    public void queryVirtualProduct(String sku) {
        DLog.d("xsolla start query purchased virtual products");
        XInventory.getInventory(new GetInventoryCallback() {
            @Override
            public void onSuccess(@NotNull InventoryResponse inventoryResponse) {
                DLog.e("xsolla " + sku + " query success");
                for (InventoryResponse.Item item : inventoryResponse.getItems()) {
                    if (item.getSku() != null &&
                            item.getSku().equals(sku)) {
                        DLog.d("xsolla query purchased virtual product:" + sku + " type=" + item.getVirtualItemType());
                        if (item.getVirtualItemType() == InventoryResponse.Item.VirtualItemType.CONSUMABLE) {
                            DLog.d("xsolla start consume purchased virtual product:" + sku);
                            XInventory.consumeItem(item.getSku(),
                                    item.getQuantity(),
                                    item.getInstanceId(),
                                    new ConsumeItemCallback() {
                                        @Override
                                        public void onSuccess() {
                                            DLog.d("xsolla consume purchased virtual product success:" + sku);
                                            if (listener != null)
                                                listener.onPaySuccess(item.getSku());
                                        }

                                        @Override
                                        public void onError(@Nullable Throwable throwable, @Nullable String s) {
                                            DLog.d("xsolla consume purchased virtual product fail:" + sku);
                                            if (listener != null)
                                                listener.onPayFailed(item.getSku());
                                        }
                                    });
                        } else {
                            DLog.d("xsolla: " + sku + " is not consumable purchased virtual product");
                            if (listener != null)
                                listener.onPaySuccess(item.getSku());
                        }
                    } else {
                        // ...
                    }
                }
            }

            @Override
            public void onError(@Nullable Throwable throwable, @Nullable String s) {
                if (listener != null)
                    listener.onPayFailed(sku);
                DLog.e("xsolla query purchased Virtual Product failed:" + s);
            }
        });
    }

    public void queryVirtualProducts() {
        DLog.d("xsolla start query purchased virtual products");
        XInventory.getInventory(new GetInventoryCallback() {
            @Override
            public void onSuccess(@NotNull InventoryResponse inventoryResponse) {
                DLog.e("xsolla query virtual products:" + inventoryResponse.getItems().size());
                for (InventoryResponse.Item item : inventoryResponse.getItems()) {
                    if (item.getVirtualItemType() == InventoryResponse.Item.VirtualItemType.CONSUMABLE) {
                        //查询到可消耗性商品未被消耗，执行消耗处理，返回消耗结果
                        if (item.getSku() != null) {
                            DLog.d("xsolla find consumable purchased virtual products:" + item.getSku());
                            XInventory.consumeItem(item.getSku(),
                                    item.getQuantity(),
                                    item.getInstanceId(),
                                    new ConsumeItemCallback() {
                                        @Override
                                        public void onSuccess() {
                                            DLog.d("xsolla find&consume purchased virtual products success:" + item.getSku());
                                            if (listener != null)
                                                listener.onPaySuccess(item.getSku());
                                        }

                                        @Override
                                        public void onError(@Nullable Throwable throwable, @Nullable String s) {
                                            DLog.d("xsolla find&consume purchased virtual products fail:" + item.getSku());
                                            if (listener != null)
                                                listener.onPayFailed(item.getSku());
                                        }
                                    });
                        }
                    } else if (item.getVirtualItemType() == InventoryResponse.Item.VirtualItemType.NON_CONSUMABLE) {
                        // 查询到永久商品
                        if (item.getSku() != null) {
                            DLog.d("xsolla find non_consumable purchased virtual products:" + item.getSku());
                            if (listener != null) listener.onPaySuccess(item.getSku());
                        }
                    } else if (item.getVirtualItemType() == InventoryResponse.Item.VirtualItemType.TIME_LIMITED_ITEM) {
                        if (item.getSku() != null && item.getRemainingUses() > 0) {
                            DLog.d("xsolla find time_limit purchased virtual products:" + item.getSku());
                            if (listener != null) listener.onPaySuccess(item.getSku());
                        }
                    }
                }
            }

            @Override
            public void onError(@Nullable Throwable throwable, @Nullable String s) {
                //...ignore
                DLog.e("xsolla query purchased Virtual Product failed:" + s);
            }
        });
    }

    /**
     * 加载所有该用户可用的订阅计划
     */
    public void loadSubscriptionPlans() {
        DLog.d("xsolla start load all Sub Plans");
        XSubscriptions.getSubscriptionPlans(new GetSubscriptionPlansCallback() {
            @Override
            public void onSuccess(@NotNull ListPlans listPlans) {
                DLog.d("xsolla loaded all Sub Plans");
                if (listPlans != null && listPlans.getItems() != null) {
                    for (Plan item : listPlans.getItems()) {
                        DLog.d("xsolla loaded  Sub Plan:" + item.getPlanName());
                        SubscriptionPlan plan = new SubscriptionPlan(item);
                        subscriptionPlanMap.put(item.getPlanName(), plan);
                    }
                }
            }

            @Override
            public void onError(@Nullable Throwable throwable, @Nullable String s) {
                //... ignore  加载可用的订阅计划失败
                DLog.e("load xsolla sub plans failed!!!");
            }
        });
    }

    public boolean hasSubscriptionPlan(String planName) {
        return subscriptionPlanMap != null && subscriptionPlanMap.containsKey(planName);
    }

    public SubscriptionPlan getSubscriptionPlan(String planName) {
        if (hasSubscriptionPlan(planName)) {
            return subscriptionPlanMap.get(planName);
        }
        return null;
    }

    public void payForSubscriptionPlan(String planName) {
        DLog.d("xsolla start pay Sub Plan:" + planName);
        SubscriptionPlan plan = getSubscriptionPlan(planName);
        if (plan == null) {
            DLog.d("xsolla invalid Sub Plan to pay:" + planName);
            if (listener != null) listener.onPayFailed(planName);
            return;
        }
        currentPayingSku = planName;
        XSubscriptions.getSubscriptionPurchaseUrl(new GetSubscriptionPurchaseUrlCallback() {
            @Override
            public void onSuccess(@NotNull PaymentLink paymentLink) {
                String paymentLinkUrl = paymentLink.getLink();
                try {
                    DLog.e("xsolla sub plan purchase url:" + paymentLinkUrl);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(paymentLinkUrl);
                    intent.setData(uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    IvySdk.getActivity().startActivityForResult(intent, XSOLLA_PAYMENT_SUBSCRIPTION_REQUEST_CODE);
                } catch (Exception e) {
                    DLog.e("xsolla sub plan pay failed:" + planName + " " + e.getMessage());
                    if (listener != null) listener.onPayFailed(planName);
                }
            }

            @Override
            public void onError(@Nullable Throwable throwable, @Nullable String s) {
                //... pay for plan failed
                DLog.e("xsolla sub plan pay failed:" + planName + " " + s);
                if (listener != null) listener.onPayFailed(planName);
            }
        }, plan.planExternalId);
    }

    public void querySubscriptionPlanPayResult() {
        DLog.d("xsolla start query purchased sub plan pay result");
        XSubscriptions.getSubscriptions(new GetSubscriptionsCallback() {
            @Override
            public void onSuccess(@NotNull ListSubscriptions listSubscriptions) {
                DLog.e("xsolla query purchased sub plan pay result success");
                for (Subscription item : listSubscriptions.getItems()) {
                    if (item.getStatus() == SubscriptionStatus.ACTIVE) {
                        DLog.e("xsolla purchased sub plan:" + item.getPlanName());
                        if (listener != null) listener.onPaySuccess(item.getPlanName());
                    }
                }
            }

            @Override
            public void onError(@Nullable Throwable throwable, @Nullable String s) {
                //... ignore  订阅计划失败
                DLog.e("xsolla query purchased sub plan pay result failed");
            }
        });
    }

    public void querySubscriptionPlanPayResult(String planName) {
        DLog.d("xsolla start query sub plan pay result:" + planName);
        SubscriptionPlan plan = getSubscriptionPlan(planName);
        if (plan == null) {
            DLog.d("xsolla start query invalid sub plan:" + planName);
            if (listener != null) listener.onPayFailed(planName);
            return;
        }
        XSubscriptions.getSubscriptionDetails(new GetSubscriptionDetailsCallback() {
            @Override
            public void onSuccess(@NotNull SubscriptionDetails subscriptionDetails) {
                DLog.e("xsolla query sub plan pay result success:" + planName);
                if (subscriptionDetails.getPlanName().equals(planName) &&
                        subscriptionDetails.getStatus() == SubscriptionStatus.ACTIVE) {
                    DLog.e("xsolla pay " + planName + " success");
                    if (listener != null) listener.onPaySuccess(planName);
                } else {
                    DLog.e("xsolla pay " + planName + " fail");
                    if (listener != null) listener.onPayFailed(planName);
                }
            }

            @Override
            public void onError(@Nullable Throwable throwable, @Nullable String s) {
                if (listener != null) listener.onPayFailed(planName);
                DLog.e("xsolla query sub plan pay result failed:" + planName + "  " + s);
            }
        }, plan.planId);
    }
}