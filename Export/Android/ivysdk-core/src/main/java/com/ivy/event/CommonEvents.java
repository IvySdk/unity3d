package com.ivy.event;

public interface CommonEvents {
  int MAIN_ACTIVITY_ON_DESTROY = -6;

  int PUSH_NOTIFICATION_SHOW = -902;

  int BILLING_PAYMENT_SYSTEM_ERROR = -300;
  int BILLING_BECOMES_AVAILABLE = -200;
  int BILLING_PURCHASE_RESPONSE = -201;
  int BILLING_PURCHASE_STATE_CHANGE = -202;
  int BILLING_STORE_LOADED = -205;

  int NETWORK_STATUS_CHANGED = -500;
  int AD_LOADED = -501;

  int DEEPLINK_RECEIVED = -504;

  int DELICIOUS_ICON_CLICKED = -505;

  int XSOLLA_BILLING_VALID = -601;
  int XSOLLA_BILLING_RESULT = -602;
  int XSOLLA_LOGIN_RESULT = -603;

}