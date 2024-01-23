//package com.ivy.billing;
//
//import com.ivy.billing.PurchaseManager.PurchaseState;
//
//public class PurchaseStateChangeData {
//  private String developerPayload;
//  private String itemId;
//  private boolean justRestore;
//  private String orderId;
//  private PurchaseState purchaseState;
//  private long purchaseTime;
//  private String purchaseToken;
//  private int quantity;
//  private boolean isAutoRenewing;
//
//  private String signature;
//  private String receipt;
//
//  private String packageName;
//
//
//
//  private String skuJson;
//
//
//  public PurchaseStateChangeData(String orderId, PurchaseState purchaseState, String itemId, int quantity, long purchaseTime, String developerPayload, boolean justRestore, String purchaseToken, boolean isAutoRenewing) {
//    this(orderId, purchaseState, itemId, quantity, purchaseTime, developerPayload, justRestore, purchaseToken, isAutoRenewing, Double.NaN, Double.NaN);
//  }
//
//  private PurchaseStateChangeData(String orderId, PurchaseState purchaseState, String itemId, int quantity, long purchaseTime, String developerPayload, boolean justRestore, String purchaseToken, boolean isAutoRenewing, double moneySpentInRequestedApp, double moneySpentInAllApps) {
//    this.orderId = orderId;
//    this.purchaseState = purchaseState;
//    this.itemId = itemId;
//    this.quantity = quantity;
//    this.purchaseTime = purchaseTime;
//    this.developerPayload = developerPayload;
//    this.justRestore = justRestore;
//    this.purchaseToken = purchaseToken;
//    this.isAutoRenewing = isAutoRenewing;
//  }
//
//  public String getSkuJson() {
//    return skuJson;
//  }
//
//  public void setSkuJson(String skuJson) {
//    this.skuJson = skuJson;
//  }
//
//  public String getOrderId() {
//    return this.orderId;
//  }
//
//  public void setOrderId(String orderId) {
//    this.orderId = orderId;
//  }
//
//  public PurchaseState getPurchaseState() {
//    return this.purchaseState;
//  }
//
//  public void setPurchaseState(PurchaseState purchaseState) {
//    this.purchaseState = purchaseState;
//  }
//
//  public String getItemId() {
//    return this.itemId;
//  }
//
//  public void setItemId(String itemId) {
//    this.itemId = itemId;
//  }
//
//  public int getQuantity() {
//    return this.quantity;
//  }
//
//  public void setQuantity(int quantity) {
//    this.quantity = quantity;
//  }
//
//  public long getPurchaseTime() {
//    return this.purchaseTime;
//  }
//
//  public void setPurchaseTime(long purchaseTime) {
//    this.purchaseTime = purchaseTime;
//  }
//
//  public String getDeveloperPayload() {
//    return this.developerPayload;
//  }
//
//  public void setDeveloperPayload(String developerPayload) {
//    this.developerPayload = developerPayload;
//  }
//
//  public boolean getJustRestore() {
//    return this.justRestore;
//  }
//
//  public String getPurchaseToken() {
//    return this.purchaseToken;
//  }
//
//  public String getSignature() {
//    return signature;
//  }
//
//  public void setSignature(String signature) {
//    this.signature = signature;
//  }
//
//  public String getReceipt() {
//    return receipt;
//  }
//
//  public void setReceipt(String receipt) {
//    this.receipt = receipt;
//  }
//
//  public String getPackageName() {
//    return packageName;
//  }
//
//  public void setPackageName(String packageName) {
//    this.packageName = packageName;
//  }
//
//
//  public String toString() {
//    StringBuilder builder = new StringBuilder();
//    builder.append("PurchaseStateChangeData [orderId=");
//    builder.append(this.orderId);
//    builder.append(", purchaseState=");
//    builder.append(this.purchaseState);
//    builder.append(", itemId=");
//    builder.append(this.itemId);
//    builder.append(", quantity=");
//    builder.append(this.quantity);
//    builder.append(", purchaseTime=");
//    builder.append(this.purchaseTime);
//    builder.append(", developerPayload=");
//    builder.append(this.developerPayload);
//    builder.append(", justRestore=");
//    builder.append(this.justRestore);
//    builder.append(", purchaseToken=");
//    builder.append(this.purchaseToken);
//    builder.append("]");
//    return builder.toString();
//  }
//}
