package com.android.client;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

public final class WebViewHelper {
  private static final String TAG = WebViewHelper.class.getSimpleName();
  public interface WebViewListener {
    void onSuccess();

    void onFailure();

    void onClose();

    String call(String param);
  }

  private WebViewHelper _instance = null;
  private Handler _handler;
  private WebView _webView;
  private WebViewListener _listener;

  @SuppressWarnings({"setJavaScriptEnabled", "addJavascriptInterface"})
  public void showWebView(final Activity activity, final String url, final int x, final int y, final int w, final int h, final WebViewListener listener) {
    if (_handler == null) {
      _handler = new Handler(Looper.getMainLooper());
    }
    _listener = listener;
    _handler.post(new Runnable() {
      @Override
      public void run() {
        try {
          if (_webView == null) {
            _webView = new WebView(activity);
          }
          WebSettings settings = _webView.getSettings();

          // 设置与Js交互的权限
          settings.setJavaScriptEnabled(true);
          settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
          settings.setAllowFileAccess(true);

//                        settings.setDomStorageEnabled(true);
//                        settings.setAppCacheMaxSize(1024 * 1024 * 8);
//                        String appCachePath = activity.getCacheDir().getAbsolutePath();
//                        settings.setAppCachePath(appCachePath);
//                        settings.setAppCacheEnabled(true);
          _webView.addJavascriptInterface(new JsCallAndroid(listener), "android");
          _webView.setWebViewClient(new WebViewClient() {
            private boolean isLoadFailure = false;

            private void loadFailure() {
              isLoadFailure = true;
              if (listener != null) {
                listener.onFailure();
              }
              closeWebView();
            }

            private void loadSuccess() {
              FrameLayout rootView = (FrameLayout) activity.getWindow().getDecorView().getRootView();
              FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(w, h);
              if (_webView.getParent() != null) {
                ((ViewGroup) _webView.getParent()).removeView(_webView);
              }
              params.leftMargin = x;
              params.topMargin = y;
              rootView.addView(_webView, params);

              if (listener != null) {
                listener.onSuccess();
              }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
              super.onPageFinished(view, url);
              if (isLoadFailure) return;
              loadSuccess();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
              super.onReceivedError(view, request, error);
              loadFailure();
            }

          });
          _webView.loadUrl(url);

        } catch (Throwable e) {
          e.printStackTrace();
        }
      }
    });
  }

  public boolean isWebViewDisplayed() {
    return _webView != null && _webView.getParent() != null;
  }

  public void closeWebView() {
    if (_handler != null) {
      _handler.post(new Runnable() {
        @Override
        public void run() {
          if (_webView != null && _webView.getParent() != null) {
            ((ViewGroup) _webView.getParent()).removeView(_webView);
            _webView.destroy();
            _webView = null;
            if (_listener != null)
              _listener.onClose();
          }
        }
      });
    }
  }

  class JsCallAndroid extends Object {
    private WebViewListener listener;

    public JsCallAndroid(WebViewListener listener) {
      this.listener = listener;
    }

    @JavascriptInterface
    public void close() {
      Log.e(TAG, "call closeWebview from javascript");
      closeWebView();
    }

    @JavascriptInterface
    public String call(String param) {
      if (this.listener == null) return "";
      try {
        return this.listener.call(param);
      } catch (Throwable e) {
        e.printStackTrace();
      }
      return "";
    }
  }
}
