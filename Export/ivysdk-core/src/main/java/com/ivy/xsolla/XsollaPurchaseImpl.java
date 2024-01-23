//package com.ivy.xsolla;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//
//import com.ivy.IvySdk;
//import com.ivy.event.CommonEvents;
//import com.ivy.event.EventBus;
//import com.ivy.networks.grid.GridManager;
//
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * 配置说明：
// * <p>
// * "payment": {
// * "checkout": {
// * billId: {                  //  billId： 商品在配置中唯一id
// * "feename": "25coins",    //  为虚拟物品时即为配置中的sku； 为订阅商品时 为 订阅计划的名称
// * "repeat": 1,             //  1 为订阅商品，  0 为虚拟物品
// * "usd": 1.99              //  配置中价格
// * }
// * }
// * }
// */
//public class XsollaPurchaseImpl {
//
//    private boolean debug = false;
//
//    private XsollaUtil xsollaUtil = null;
//
//    private final EventBus eventBus;
//    //    private EventTracker eventTracker = null;
//    private volatile boolean isPaying = false;
//    private SharedPreferences sp;
//    private Map<String, ConfigProduct> configProductMap = new HashMap<>();
//    private Map<String, ConfigProduct> configProductMap2 = new HashMap<>();
//
//    private void fireEvent(final int eventId, final Object eventData) {
//        IvySdk.runOnUiThreadCustom(new Runnable() {
//            @Override
//            public void run() {
//                if (XsollaPurchaseImpl.this.eventBus != null) {
//                    XsollaPurchaseImpl.this.eventBus.fireEvent(eventId, eventData);
//                }
//            }
//        });
//    }
//
//    private IXsollaPayListener xsollaPayListener = new IXsollaPayListener() {
//        @Override
//        public void onSuccess() {
//            fireEvent(CommonEvents.XSOLLA_LOGIN_RESULT, true);
//        }
//
//        @Override
//        public void onFail() {
//            fireEvent(CommonEvents.XSOLLA_LOGIN_RESULT, false);
//        }
//
//        @Override
//        public void onInitResult(boolean state) {
//            DLog.d("xsolla init end :" + state);
//            if (state) {
//                fireEvent(CommonEvents.XSOLLA_BILLING_VALID, "");
//            }
//        }
//
//        @Override
//        public void onPaySuccess(String sku) {
//            try {
//                DLog.d("xsolla pay success :" + sku);
//                ConfigProduct configProduct = XsollaPurchaseImpl.this.configProductMap2.get(sku);
//                PayResult payResult = new PayResult();
//                payResult.result = true;
//                payResult.billId = configProduct.billId;
//                try {
//                    payResult.payload = XsollaPurchaseImpl.this.sp.getString(sku, null);
//                } catch (Exception e) {
//                }
//                fireEvent(CommonEvents.XSOLLA_BILLING_RESULT, payResult);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                XsollaPurchaseImpl.this.isPaying = false;
//            }
//        }
//
//        @Override
//        public void onPayFailed(String sku) {
//            try {
//                DLog.e("xsolla pay fail :" + sku);
//                ConfigProduct configProduct = XsollaPurchaseImpl.this.configProductMap2.get(sku);
//                PayResult payResult = new PayResult();
//                payResult.result = false;
//                payResult.billId = configProduct.billId;
//                fireEvent(CommonEvents.XSOLLA_BILLING_RESULT, payResult);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                XsollaPurchaseImpl.this.isPaying = false;
//            }
//        }
//    };
//
//    public XsollaPurchaseImpl(Context context, EventBus eventBus) {
//        try {
//            ApplicationInfo ai = IvySdk.getActivity().getPackageManager().getApplicationInfo(IvySdk.getActivity().getPackageName(), PackageManager.GET_META_DATA);
//            if (ai != null && ai.metaData != null) {
//                debug = ai.metaData.getBoolean("ivy.debug", false);
//            }
//        } catch (Exception e) {
//        }
//
//        this.eventBus = eventBus;
////        this.eventTracker = eventLogger;
//        this.sp = context.getSharedPreferences("pays", Context.MODE_PRIVATE);
//        xsollaUtil = new XsollaUtil(context, xsollaPayListener, debug);
//        loadAllConfigs();
//    }
//
//    public void login() {
//        xsollaUtil.login();
//    }
//
//    public boolean isLogIn() {
//        return xsollaUtil.isLogin();
//    }
//
//    public void logout() {
//        xsollaUtil.logout();
//    }
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (xsollaUtil != null) xsollaUtil.onActivityResult(requestCode, resultCode, data);
//    }
//
//    /**
//     * 加载配置文件中的商品信息
//     */
//    private void loadAllConfigs() {
//        try {
//            JSONObject checkout = GridManager.getGridData().getJSONObject("payment").getJSONObject("checkout");
//            Iterator<String> iterator = checkout.keys();
//            while (iterator.hasNext()) {
//                String billId = iterator.next();
//                JSONObject item = checkout.getJSONObject(billId);
//                ConfigProduct product = new ConfigProduct(billId, item);
//                configProductMap.put(billId, product);
//                configProductMap2.put(product.feename, product);
//            }
//            DLog.d("xsolla load all config products info:" + configProductMap.size());
//        } catch (Exception e) {
//            DLog.e("xsolla load config products failed :" + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public ConfigProduct getConfigProduct(String billId) {
//        if (configProductMap.containsKey(billId))
//            return configProductMap.get(billId);
//        return null;
//    }
//
//    /**
//     * 获取 Xsolla后台配置的商品信息
//     * ！！！[IMPORTANT] 虚拟物品和订阅计划的数据结构不一致
//     *
//     * @param billId
//     * @return
//     */
//    public Product getProductDetail(String billId) {
//        Product product = null;
//        try {
//            if (configProductMap.containsKey(billId)) {
//                ConfigProduct configProduct = configProductMap.get(billId);
//                String feename = configProduct.feename;
//                if (xsollaUtil.hasVirtualProduct(feename)) {
//                    product = xsollaUtil.getVirtualProduct(feename);
//                } else if (xsollaUtil.hasSubscriptionPlan(feename)) {
//                    product = xsollaUtil.getSubscriptionPlan(feename);
//                }
//                if (product != null) {
//                    product.setUSD(configProduct.usd);
//                }
//            }
//        } catch (Exception e) {
//        }
//        return product;
//    }
//
//    /**
//     * 购买商品
//     *
//     * @param billId
//     * @param developerPayload
//     * @return
//     */
//    public boolean buy(String billId, String developerPayload) {
//        DLog.d("xsolla start pay:" + billId);
//        if (isPaying) {
//            DLog.d("xsolla pay fail:" + billId + "  last pay not finished");
//            return false;
//        }
//        if (!xsollaUtil.isLogin()) {
//            DLog.d("xsolla pay fail:" + billId + ";not log yet,start log");
//            xsollaUtil.login();
//            return false;
//        }
//        ConfigProduct configProduct = getConfigProduct(billId);
//        if (configProduct == null) {
//            DLog.e("xsolla invalid:" + billId + ",no config product find");
//            return false;
//        }
//        if (developerPayload != null) {
//            sp.edit().putString(billId, developerPayload).apply();
//        } else {
//            sp.edit().remove(billId).apply();
//        }
//        if (xsollaUtil.hasVirtualProduct(configProduct.feename)) {
//            DLog.d("xsolla start virtual product pay :" + configProduct.toString());
//            isPaying = true;
//            xsollaUtil.payForVirtualProduct(configProduct.feename);
//            return true;
//        } else if (xsollaUtil.hasSubscriptionPlan(configProduct.feename)) {
//            DLog.d("xsolla start sub plan pay :" + configProduct.toString());
//            isPaying = true;
//            xsollaUtil.payForSubscriptionPlan(configProduct.feename);
//            return true;
//        }
//        DLog.e("xsolla invalid billId");
//        return false;
//    }
//
//    /**
//     * 查询商品状态
//     * ！！！[IMPORTANT]
//     * 1. 订阅计划的付费状态是可查询的
//     * 2. 虚拟商品由于商品类型不同，查询结果也是不相同的
//     * 1）. 永久商品、时效性商品 可查询
//     * 2）. 消耗性商品如未被消耗则会消耗该商品并返回结果，否则 无回调
//     *
//     * @param billId
//     */
//    public void queryPaymentState(String billId) {
//        DLog.d("xsolla start query:" + billId);
//        if ("-1".equals(billId)) {
//            xsollaUtil.queryVirtualProducts();
//            xsollaUtil.querySubscriptionPlanPayResult();
//        } else {
//            ConfigProduct configProduct = getConfigProduct(billId);
//            if (configProduct == null) return;
//            if (xsollaUtil.hasVirtualProduct(configProduct.feename)) {
//                DLog.d("xsolla start query virtual product :" + billId);
//                xsollaUtil.queryVirtualProduct(configProduct.feename);
//            } else if (xsollaUtil.hasSubscriptionPlan(configProduct.feename)) {
//                DLog.d("xsolla start query sub plan :" + billId);
//                xsollaUtil.querySubscriptionPlanPayResult(configProduct.feename);
//            }
//        }
//    }
//}