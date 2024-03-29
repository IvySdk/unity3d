package com.android.client;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

@Deprecated
public class UnityPlayerNativeActivity extends com.unity3d.player.UnityPlayerNativeActivity {

  private static final String TAG = "UnityPlayerActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSdk.setDisplayInNotch(this);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    Log.d(TAG, "onActivityResult(), requestCode: " + requestCode);
    AndroidSdk.onActivityResult(requestCode, resultCode, data);
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
    Log.d(TAG, "onRequestPermissionsResult(), requestCode: " + requestCode);
    AndroidSdk.onRequestPermissionsResult(requestCode, permissions, grantResults);
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }
}
