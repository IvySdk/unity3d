//package com.ivy.ads.promote.cache;
//
//import java.io.File;
//
//public class PromoCreativeImageCache {
//  private final String filePath;
//
//  private int downloadFailedCount= 0;
//  public PromoCreativeImageCache(String filePath) {
//    this.filePath = filePath;
//  }
//
//  public boolean exists() {
//    return new File(filePath).exists();
//  }
//
//  public void incFailedCount() {
//    downloadFailedCount++;
//  }
//
//  public int getFailedDownloadCount() {
//    return downloadFailedCount;
//  }
//
//  public String getFilePath() {
//    return this.filePath;
//  }
//}
