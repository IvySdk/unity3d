package com.ivy.xsolla;

import com.xsolla.android.store.entity.response.common.Price;
import com.xsolla.android.store.entity.response.items.VirtualItemsResponse;

import org.json.JSONObject;

import java.math.BigDecimal;

class VirtualProduct extends Product {
    public String sku;
    public String name;
    public String description;
    public boolean isFree;
    public String type;
    public String virtualType;
    public String amountRaw;
    public String amountWithoutDiscountRaw;
    public BigDecimal amountDecimal;
    public BigDecimal amountWithoutDiscountDecimal;
    public String currency;

    public VirtualProduct(VirtualItemsResponse.Item item) {
        this.sku = item.getSku();
        this.name = item.getName();
        this.description = item.getDescription();
        this.isFree = item.isFree();
        this.type = item.getType();
        Price price = item.getPrice();
        if (price != null) {
            this.amountRaw = price.getAmountRaw();
            this.amountWithoutDiscountRaw = price.getAmountWithoutDiscountRaw();
//            this.amountDecimal = price.getAmountDecimal();
//            this.amountWithoutDiscountDecimal = price.getAmountWithoutDiscountDecimal();
            this.currency = price.getCurrency();
        }
        this.virtualType = item.getVirtualItemType();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        try {
            json.put("id", this.sku);
            json.put("type", this.type);
            json.put("price", getCurrencySymbol(this.currency) + this.amountRaw);
            if (!this.amountRaw.equals(this.amountWithoutDiscountRaw)) {
                json.put("original_price", this.amountWithoutDiscountRaw + getCurrencySymbol(this.currency));
            }
            json.put("price_amount", this.amountRaw);
            json.put("usd", this.usd);
            json.put("currency", this.currency);
            if (this.description != null)
                json.put("desc", this.description);
            json.put("title", this.name);
        } catch (Exception e) {

        }
//        try {
//            json.put("sku", this.sku);
//            json.put("name", this.name);
//            json.put("type", this.type);
//            if (this.description != null)
//                json.put("desc", this.description);
//            json.put("isFree", this.isFree);
//            json.put("virtualType", this.virtualType);
//            json.put("amountRaw", this.amountRaw);
//            json.put("amountWithoutDiscountRaw", this.amountWithoutDiscountRaw);
//            json.put("amountDecimal", this.amountDecimal);
//            json.put("amountWithoutDiscountDecimal", this.amountWithoutDiscountDecimal);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return json;
    }

    @Override
    public String toString() {
        return toJson().toString();
    }
}