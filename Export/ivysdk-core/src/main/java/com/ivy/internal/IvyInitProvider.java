package com.ivy.internal;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.ivy.IvySdk;

public final class IvyInitProvider extends ContentProvider {
  private static final String TAG = IvyInitProvider.class.getSimpleName();

  @Override
  public boolean onCreate() {
    try {
      IvySdk.sdkInitialize(getContext());
    } catch (Throwable ex) {
      Log.e(TAG, "Failed to auto initialize the SDK", ex);
    }
    return false;
  }

  @Override
  public Cursor query(
    final Uri uri,
    final String[] projection,
    final String selection,
    final String[] selectionArgs,
    final String sortOrder) {
    return null;
  }

  @Override
  public String getType(final Uri uri) {
    return null;
  }

  @Override
  public Uri insert(final Uri uri, final ContentValues values) {
    return null;
  }

  @Override
  public int delete(final Uri uri, final String selection, final String[] selectionArgs) {
    return 0;
  }

  @Override
  public int update(
    final Uri uri,
    final ContentValues values,
    final String selection,
    final String[] selectionArgs) {
    return 0;
  }
}
