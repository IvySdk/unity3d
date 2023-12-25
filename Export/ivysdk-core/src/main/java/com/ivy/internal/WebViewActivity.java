package com.ivy.internal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.adsfall.R;

public class WebViewActivity extends AppCompatActivity {
  private WebView Wv;

  // The loading bar on the base of the ActionBar
  protected ProgressBar loadingBar;


  @SuppressLint("SetJavaScriptEnabled")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Intent intent = getIntent();
    String title = intent.getStringExtra("title");
    String url = intent.getStringExtra("url");
    setContentView(R.layout.common_webview);
    Wv = (WebView) findViewById(R.id.common_webview);

    WebSettings Ws = Wv.getSettings();
    Ws.setJavaScriptEnabled(true);


    // Get the loading bar we're going to show
    loadingBar = (ProgressBar)findViewById(R.id.loadingbar);
    // Set a WebClient to display loading
    Wv.setWebChromeClient(new WebChromeClient(){
      public void onProgressChanged(WebView view, int progress){
        // If loading isn't complete, make sure the loading bar is visible
        if(progress < 100 && loadingBar.getVisibility() == ProgressBar.GONE){
          // Set the loading bar as visible
          loadingBar.setVisibility(ProgressBar.VISIBLE);
        }
        // Update the loading bar
        loadingBar.setProgress(progress);
        // If loading is done
        if(progress == 100){
          // Remove the loading bar
          loadingBar.setVisibility(ProgressBar.GONE);
          // Display the WebView
          Wv.setVisibility(View.VISIBLE);
        }
      }
    });
    Wv.setWebViewClient(new WebViewClient(){
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
      }
    });
    Wv.loadUrl(url);
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setHomeButtonEnabled(true);
      actionBar.setDisplayHomeAsUpEnabled(true);
      if (title != null) {
        actionBar.setTitle(title);
      }
    }
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (event.getAction() == KeyEvent.ACTION_DOWN) {
      switch (keyCode) {
        case KeyEvent.KEYCODE_BACK:
          if ((keyCode == KeyEvent.KEYCODE_BACK) && Wv.canGoBack()) {
            Wv.goBack();
            return true;
          }
      }

    }
    return super.onKeyDown(keyCode, event);
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        //Write your logic here
        this.finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
