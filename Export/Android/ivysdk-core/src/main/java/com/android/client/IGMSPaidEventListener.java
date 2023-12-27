package com.android.client;

public interface IGMSPaidEventListener {

    void onGMSPaid(String currencyCode, int precisionType, long valueMacros);

}
