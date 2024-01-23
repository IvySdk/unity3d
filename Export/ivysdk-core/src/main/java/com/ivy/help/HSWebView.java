//package com.ivy.help;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Paint;
//import android.os.Build.VERSION;
//import android.util.AttributeSet;
//import android.view.View;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//
//public class HSWebView extends WebView {
//  public HSWebView(Context context, AttributeSet attrs) {
//    super(context, attrs);
//    this.init();
//  }
//
//  public HSWebView(Context context, AttributeSet attrs, int defStyleAttr) {
//    super(context, attrs, defStyleAttr);
//    this.init();
//  }
//
//  public HSWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//    super(context, attrs, defStyleAttr);
//    this.init();
//  }
//
//  public HSWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
//    super(context, attrs, defStyleAttr, privateBrowsing);
//    this.init();
//  }
//
//  public HSWebView(Context context) {
//    super(context);
//    this.init();
//  }
//
//  private void init() {
//    if (!this.isInEditMode()) {
//      this.configureWebView();
//    }
//
//  }
//
//  @SuppressLint("SetJavaScriptEnabled")
//  private void configureWebView() {
//    WebSettings settings = this.getSettings();
//    settings.setJavaScriptEnabled(true);
//    settings.setJavaScriptCanOpenWindowsAutomatically(true);
//    settings.setSupportMultipleWindows(true);
//    settings.setDomStorageEnabled(true);
//    if (VERSION.SDK_INT >= 21) {
//      settings.setMixedContentMode(2);
//    }
//
//    settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//    settings.setAllowFileAccess(true);
//    if (VERSION.SDK_INT >= 19) {
//      this.setLayerType(View.LAYER_TYPE_HARDWARE, (Paint) null);
//    } else {
//      this.setLayerType(View.LAYER_TYPE_SOFTWARE, (Paint) null);
//    }
//
//  }
//
//  public void destroyCustomWebview() {
//    this.removeAllViews();
//    this.destroy();
//  }
//}
