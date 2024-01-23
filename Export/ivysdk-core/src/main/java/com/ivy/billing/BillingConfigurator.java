//package com.ivy.billing;
//
//
//import com.ivy.billing.impl.BillingPreferences;
//import com.ivy.networks.grid.GridManager;
//
//import org.json.JSONObject;
//
//public class BillingConfigurator {
//  public static BillingPreferences setUpBillingPreferences() {
//
//    BillingPreferences billingPreferences = new BillingPreferences();
//    JSONObject gridData = GridManager.getGridData();
//    if (!gridData.has("payment")) {
//      return billingPreferences;
//    }
//    JSONObject paymentObject = gridData.optJSONObject("payment");
//    if (!paymentObject.has("checkout")) {
//      return billingPreferences;
//    }
//
//    JSONObject checkoutObject = paymentObject.optJSONObject("checkout");
//    if (checkoutObject != null) {
//      billingPreferences.publicKey = checkoutObject.optString("key", "");
//      billingPreferences.verifyUrl = checkoutObject.optString("verify-url", null);
//      billingPreferences.autoConsume = checkoutObject.optInt("auto-consume", 1) == 1;
//      billingPreferences.mustVerify = checkoutObject.optInt("force-check", 0) == 1;
//      billingPreferences.clientCheck = checkoutObject.optInt("force-check", 0) == 2;
//    }
//    return billingPreferences;
//  }
//}
