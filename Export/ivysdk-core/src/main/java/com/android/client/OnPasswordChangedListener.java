package com.android.client;

import androidx.annotation.NonNull;

public interface OnPasswordChangedListener {
    void onSuccess();

    void onError(@NonNull String paramString1, @NonNull String paramString2);
}