package com.ivy.ads.promote.cache;

import java.util.HashMap;

public class PromoCreativeImageCachePool {
  private final HashMap<String, PromoCreativeImageCache> filePathCaches = new HashMap<>();

  public PromoCreativeImageCache get(String filePath) {
    PromoCreativeImageCache promoCreativeImageCache;
    synchronized (this) {
      promoCreativeImageCache = this.filePathCaches.get(filePath);
    }
    return promoCreativeImageCache;
  }

  public void set(String filePath, PromoCreativeImageCache cache) {
    synchronized (this) {
      this.filePathCaches.put(filePath, cache);
    }
  }

  public PromoCreativeImageCache getOrCreate(String filePath) {
    PromoCreativeImageCache cache;
    synchronized (this) {
      cache = get(filePath);
      if (cache == null) {
        cache = create(filePath);
        set(filePath, cache);
      }
    }
    return cache;
  }

  protected PromoCreativeImageCache create(String filePath) {
    return new PromoCreativeImageCache(filePath);
  }
}
