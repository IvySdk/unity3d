package com.ivy.networks.grid;

import android.content.SharedPreferences;

import com.ivy.networks.util.Util;

public class GridSetup {
  private GridManager gridManager;
  private SharedPreferences settings;

  public GridSetup(GridManager gridManager) {
    this.gridManager = gridManager;
    this.settings = this.gridManager.activity.getSharedPreferences("prefs", 0);
  }

  public synchronized void checkGrid(boolean force) {
    long lastGridDownload = this.settings.getLong("lastGridDownload", 0);
    boolean forceCheckBecauseOfBetterConnectivity = false;
    boolean lastConnectivityWasWifi = this.settings.getBoolean("lastConnectivityWasWifi", true);
    if (!lastConnectivityWasWifi && Util.isWifi(this.gridManager.activity)) {
      forceCheckBecauseOfBetterConnectivity = true;
    }
    long nextGridTsDefault = lastGridDownload + GridManager.GRID_CHECK_INTERVAL_MILLIS;

    String nextGridTs = this.settings.getString("nextGridTs", nextGridTsDefault + "");

    long nextGridTsLong = nextGridTsDefault;
    try {
      nextGridTsLong = Long.parseLong(nextGridTs);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (System.currentTimeMillis() >= Math.min(nextGridTsLong, nextGridTsDefault) || force || forceCheckBecauseOfBetterConnectivity || lastGridDownload == 0) {
      gridManager.setupGrid();
    }
  }
}