//package com.ivy.xsolla;
//
//
//
//import org.json.JSONObject;
//
//import java.util.Currency;
//
//public abstract class Product {
//
//    protected double usd;
//
//    public abstract JSONObject toJson();
//
//    public void setUSD(double usd){
//        this.usd = usd;
//    }
//
//    protected String getCurrencySymbol(String currencyCode){
//        try {
//            Currency currency = Currency.getInstance(currencyCode);
//            String symbol = currency.getSymbol();
////            NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
////            numberFormat.setCurrency(currency);
//            System.out.println("货币符号为：" + symbol);
//            return symbol;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//}