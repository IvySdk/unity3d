package com.ivy.xsolla;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfigProduct {
    public String billId;
    public String feename;
    public int repeat;
    public double usd;

    public ConfigProduct(String id, JSONObject src) throws JSONException {
        this.billId = id;
        this.feename = src.getString("feename");
        this.repeat = src.getInt("repeat");
        this.usd = src.getDouble("usd");
    }

    @Override
    public String toString() {
        return "ConfigProduct{" +
                "billId='" + billId + '\'' +
                ", feename='" + feename + '\'' +
                ", repeat=" + repeat +
                ", usd=" + usd +
                '}';
    }
}