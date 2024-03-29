package com.ivy.help;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.adsfall.R;
import com.ivy.util.Logger;

public class TiledeskActivity extends AppCompatActivity {

  private static final String TAG = "Tiledesk";
  public static final int REQUEST_SELECT_FILE = 100;
  public ValueCallback<Uri[]> uploadMessage;

  private static final String BASE_URL = "https://widget.adsfall.com/assets/twp/index.html?tiledesk_projectid=%s&tiledesk_userFullname=%s&tiledesk_fullscreenMode=true&tiledesk_hideHeaderCloseButton=false&tiledesk_isOpen=true&extra=%s";

  @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tiledesk);

    try {
      Intent intent = getIntent();
      if (intent != null && intent.hasExtra("projectId")) {
        String project_id = intent.getStringExtra("projectId");
        String customerName = intent.getStringExtra("customerName");

        String customInfo = intent.getStringExtra("customInfo");

        HSWebView webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
          webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
          webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        webView.setInitialScale(1);

        if (Build.VERSION.SDK_INT >= 19) {
          // chromium, enable hardware acceleration
          webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
          // older android version, disable hardware acceleration
          webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        webView.setWebChromeClient(new FileChooserWebChromeClient(this) {
          @Override
          public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Logger.debug(TAG, consoleMessage.message());
            return super.onConsoleMessage(consoleMessage);
          }
        });
        webView.addJavascriptInterface(new Object() {
          @JavascriptInterface
          public void action(String param) {
            if ("closeHelpEngagement".equals(param)) {
              runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  try {
                    webView.destroy();
                    TiledeskActivity.this.finish();
                  } catch (Throwable ignored) {
                  }
                }
              });
            }
          }
        }, "AndroidTileDesk");

        String _url = String.format(BASE_URL, project_id, customerName, customInfo);

        Logger.debug(TAG, "URL >>> " + _url);
        webView.loadUrl(_url);
      } else {
        Log.e("tiledesk", "a project id should be set");
      }
    } catch(Throwable ignored) {

    }
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_SELECT_FILE) {
      if (uploadMessage == null) return;
      uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
      uploadMessage = null;
    }
  }

  public class FileChooserWebChromeClient extends WebChromeClient {

    private final Activity myActivity;

    public FileChooserWebChromeClient(Activity myActivity) {
      this.myActivity = myActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
      uploadMessage = filePathCallback;
//
      Intent intent = fileChooserParams.createIntent();
      try {
        myActivity.startActivityForResult(intent, REQUEST_SELECT_FILE);
      } catch (ActivityNotFoundException e) {
        Toast.makeText(myActivity, "Cannot open file chooser", Toast.LENGTH_LONG).show();
        return false;
      }

      return true;
    }
  }
}