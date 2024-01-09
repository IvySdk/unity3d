package com.ivy.xsolla;

import androidx.annotation.NonNull;

import com.xsolla.android.subscriptions.entity.response.Plan;
import com.xsolla.android.subscriptions.entity.response.PlanCharge;

import org.json.JSONObject;

class SubscriptionPlan extends Product {
    public int planId;
    public String type;
    public String planExternalId;
    public String planName;
    public String planDescription;
    public double price;
    public String currency;

    public SubscriptionPlan(Plan plan) {
        this.type = "SUBSCRIPTION_PLAN";
        this.planId = plan.getPlanId();
        this.planExternalId = plan.getPlanExternalId();
        this.planName = plan.getPlanName();
        this.planDescription = plan.getPlanDescription();
        PlanCharge charge = plan.getCharge();
        this.price = charge.getAmount();
        this.currency = charge.getCurrency();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try{
            json.put("id", this.planName);
            json.put("type", this.type);
            json.put("price", getCurrencySymbol(this.currency) + this.price);
            json.put("price_amount", this.price);
            json.put("usd", this.usd);
            json.put("currency", this.currency);
            if (this.planDescription != null)
                json.put("desc", this.planDescription);
            json.put("title", this.planName);
        } catch (Exception e){

        }

//        try {
//            json.put("planId", this.planId);
//            json.put("planExternalId", this.planExternalId);
//            json.put("planName", this.planName);
//            json.put("type", this.type);
//            json.put("planDescription", this.planDescription);
//            json.put("price", this.price);
//            json.put("currency", this.currency);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return json;
    }

    @NonNull
    @Override
    public String toString() {
        return toJson().toString();
    }
}