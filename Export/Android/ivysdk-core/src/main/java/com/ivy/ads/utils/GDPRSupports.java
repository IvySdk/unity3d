//package com.ivy.ads.utils;
//
//import android.app.Application;
//import android.text.TextUtils;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class GDPRSupports {
//    private static final Set<String> mSupportGDPRCountrys = new HashSet<>();
//
//    static {
//        mSupportGDPRCountrys.add("AT");
//        mSupportGDPRCountrys.add("BE");
//        mSupportGDPRCountrys.add("BG");
//        mSupportGDPRCountrys.add("HR");
//        mSupportGDPRCountrys.add("CY");
//        mSupportGDPRCountrys.add("CZ");
//        mSupportGDPRCountrys.add("DK");
//        mSupportGDPRCountrys.add("EE");
//        mSupportGDPRCountrys.add("FI");
//        mSupportGDPRCountrys.add("FR");
//        mSupportGDPRCountrys.add("DE");
//        mSupportGDPRCountrys.add("EL");
//        mSupportGDPRCountrys.add("HU");
//        mSupportGDPRCountrys.add("IE");
//        mSupportGDPRCountrys.add("IT");
//        mSupportGDPRCountrys.add("LV");
//        mSupportGDPRCountrys.add("LT");
//        mSupportGDPRCountrys.add("LU");
//        mSupportGDPRCountrys.add("MT");
//        mSupportGDPRCountrys.add("NL");
//        mSupportGDPRCountrys.add("PL");
//        mSupportGDPRCountrys.add("PT");
//        mSupportGDPRCountrys.add("RO");
//        mSupportGDPRCountrys.add("SK");
//        mSupportGDPRCountrys.add("SI");
//        mSupportGDPRCountrys.add("ES");
//        mSupportGDPRCountrys.add("SE");
//        mSupportGDPRCountrys.add("UK");
//        mSupportGDPRCountrys.add("CH");
//    }
//
//    public static boolean support(String str) {
//        try {
//            if (TextUtils.isEmpty(str)) {
//                return true;
//            }
//            return mSupportGDPRCountrys.contains(str.toUpperCase());
//        } catch (Exception e2) {
//            e2.printStackTrace();
//            return true;
//        }
//    }
//
//    public static boolean support(Application application) {
//        if (application == null) {
//            return false;
//        }
//        try {
//            return mSupportGDPRCountrys.contains(application.getResources().getConfiguration().locale.getCountry().toUpperCase());
//        } catch (Exception e2) {
//            e2.printStackTrace();
//            return true;
//        }
//    }
//}
