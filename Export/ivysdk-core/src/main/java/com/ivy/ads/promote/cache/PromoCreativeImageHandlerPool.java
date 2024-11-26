package com.ivy.ads.promote.cache;

import java.util.HashMap;

public class PromoCreativeImageHandlerPool {
  protected final HashMap<String, PromoCreativeImageHandler> urlHandlers = new HashMap<>();
  private PromoCreativeImageCachePool cachePool;

  private PromoCreativeImageDownloader downloader;

  public PromoCreativeImageHandlerPool() {
    this.downloader = new PromoCreativeImageDownloader();
  }

  public PromoCreativeImageHandler get(String url) {
    PromoCreativeImageHandler promoCreativeImageHandler;
    synchronized (this) {
      promoCreativeImageHandler = this.urlHandlers.get(url);
    }
    return promoCreativeImageHandler;
  }

  public void set(String url, PromoCreativeImageHandler handler) {
    synchronized (this) {
      this.urlHandlers.put(url, handler);
    }
  }

  public PromoCreativeImageHandler getOrCreate(String url, String cacheDirPath) {
    PromoCreativeImageHandler handler;
    synchronized (this) {
      handler = get(url);
      if (handler == null) {
        handler = create(url, cacheDirPath);
        set(url, handler);
      }
    }
    return handler;
  }

  public PromoCreativeImageHandler create(String url, String cacheDirPath) {
    return new PromoCreativeImageHandler(this.downloader, this.cachePool, cacheDirPath, url);
  }

  public void setCachePool(PromoCreativeImageCachePool cachePool) {
    this.cachePool = cachePool;
  }
}
