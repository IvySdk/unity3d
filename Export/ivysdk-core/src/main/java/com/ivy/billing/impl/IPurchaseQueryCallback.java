package com.ivy.billing.impl;

public interface IPurchaseQueryCallback<T> {

    void onResult(T data);

}
