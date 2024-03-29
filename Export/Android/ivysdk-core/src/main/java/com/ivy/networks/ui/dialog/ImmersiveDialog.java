package com.ivy.networks.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;

public class ImmersiveDialog extends Dialog {
  public ImmersiveDialog(Context context) {
    super(context);
  }

  public ImmersiveDialog(Context context, int theme) {
    super(context, theme);
  }

  protected ImmersiveDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
    super(context, cancelable, cancelListener);
  }

  public void show() {
    if (getOwnerActivity() != null) {
      getWindow().setFlags(8, 8);
      if (VERSION.SDK_INT >= 19) {
        getWindow().getDecorView().setSystemUiVisibility(getOwnerActivity().getWindow().getDecorView().getSystemUiVisibility());
      }
    }
    super.show();
    if (getOwnerActivity() != null) {
      getWindow().clearFlags(8);
    }
  }
}
