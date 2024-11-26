package com.ivy.networks.grid;

import org.json.JSONArray;
import org.json.JSONObject;

public class PAM {

    public String key;
    public double min;
    public double max;
    public double minRam;
    public double maxRam;

    public PAM(String key, double min, double max, double minRam, double maxRam) {
        this.key = key;
        this.min = min;
        this.max = max;
        this.minRam = minRam;
        this.maxRam = maxRam;
    }

    public static PAM format(JSONObject obj) {
        try {
            String key = obj.getString("value");
//            double ram = ramobj.optDouble("ram", 99999.0d);
            JSONArray ram = obj.optJSONArray("ram");
            double minRam = -1;
            double maxRam = 999999;
            if (ram != null && ram.length() == 2) {
                minRam = ram.optDouble(0, -1);
                maxRam = ram.optDouble(1, 999999);
            }

            JSONArray array = obj.getJSONArray("range");
            if (array.length() == 2) {
                double min = array.getDouble(0);
                double max = array.getDouble(1);
                return new PAM(key, min, max, minRam, maxRam);
            } else {
                return new PAM(key, -1, -1, -1, 999999.0d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
