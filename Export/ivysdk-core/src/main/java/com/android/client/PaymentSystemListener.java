package com.android.client;


public class PaymentSystemListener { // implements PaymentListener {

  public void onPaymentSuccess(int bill) {
  }

  public void onPaymentSuccess(int bill, String payload) {

  }

  public void onPaymentSuccess(int bill, String payload, String orderInfo) {

  }

  public void onPaymentFail(int bill) {
  }

  public void onPaymentCanceled(int bill) {
  }

  public void onReceiveBillPrices(String prices) {
  }

  public void onPaymentSystemValid() {
  }

  public void onPaymentSystemError(int causeId, String message) {
  }

  public void onStoreLoaded(String skuType) {

  }
}
