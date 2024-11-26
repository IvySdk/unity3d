package com.ivy.player;

import org.json.JSONObject;

public class Player {

    public String user_level;
    public String current_level;

    private Player() {
    }

    private Player(String userLevel, String currentLevel) {
        this.user_level = userLevel;
        this.current_level = currentLevel;
    }

    public static Player build(String data) {
        Player player = null;
        try {
            JSONObject obj = new JSONObject(data);
            String userLevel = obj.getString("user_level");
            String currentLevel = obj.getString("current_level");
            player = new Player(userLevel, currentLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }


}
