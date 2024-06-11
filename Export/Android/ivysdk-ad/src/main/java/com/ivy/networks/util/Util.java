package com.ivy.networks.util;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;

import com.ivy.IvyUtils;
import com.ivy.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Util {
  private static Map<String, Lock> dataFileLocks = new HashMap();


  public static int dpToPx(Context c, int dp) {
    return (int) ((((float) ((int) (((double) dp)))) * c.getResources().getDisplayMetrics().density) + 0.5f);
  }

  public static void storeReferrer(Context c, String referrer) throws Exception {
    File referrerFile;
    File referrerFolder;
    if (checkSdCard()) {
      referrerFolder = getSdCardApplicationDir(c);
      referrerFolder.mkdirs();
      referrerFile = new File(referrerFolder, ".referrer");
    } else {
      referrerFolder = new File(c.getFilesDir(), "data");
      referrerFolder.mkdirs();
      referrerFile = new File(referrerFolder, ".referrer");
    }
    DataOutputStream dos = new DataOutputStream(new FileOutputStream(referrerFile));
    dos.writeBytes(referrer);
    dos.close();
  }


  public static File getSdCardApplicationDir(Context c) {
    return new File(Environment.getExternalStorageDirectory(), "/Android/data/" + c.getPackageName() + "/");
  }

  public static boolean checkSdCard() {
    String state = Environment.getExternalStorageState();
    if ("mounted".equals(state)) {
      return true;
    }
    if ("mounted_ro".equals(state)) {
    }
    return false;
  }



  public static boolean isWifi(Context c) {
    NetworkInfo netInfo = ((ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
    if (netInfo == null || netInfo.getType() != 1) {
      return false;
    }
    return true;
  }


  public static String convToHex(byte[] b) {
    StringBuffer sigHex = new StringBuffer();
    for (byte b2 : b) {
      String hex = Integer.toHexString(b2 & 255);
      if (hex.length() == 1) {
        sigHex.append('0');
      }
      sigHex.append(hex);
    }
    return sigHex.toString();
  }

  public static String SHA1(String text) {
    try {
      return SHA1(text.getBytes("UTF-8"));
    } catch (Exception e) {
      return "";
    }
  }

  public static String SHA1(byte[] bytes) {
    try {
      return convToHex(MessageDigest.getInstance("SHA-1").digest(bytes));
    } catch (Exception e) {
      return "";
    }
  }

  private static Lock initLock(String lockName) {
    Throwable th;
    synchronized (dataFileLocks) {
      try {
        Lock lock = dataFileLocks.get(lockName);
        if (lock == null) {
          Lock lock2 = new ReentrantLock();
          try {
            dataFileLocks.put(lockName, lock2);
            lock = lock2;
          } catch (Throwable th2) {
            th = th2;
            lock = lock2;
            throw th;
          }
        }
        return lock;
      } catch (Throwable th3) {
        th = th3;
      }
    }
    return null;
  }

  public static void storeData(Context ctx, String file, String data) {
    File dataFile = new File(ctx.getFilesDir(), file + ".data");
//    synchronized (jsonObjectCache) {
//      jsonObjectCache.remove(file);
//    }
    Lock lock = initLock(file);
    lock.lock();
    BufferedWriter bw;
    try {
      bw = new BufferedWriter(new FileWriter(dataFile));
      bw.write(data, 0, data.length());
      bw.flush();
      bw.close();
      lock.unlock();
    } catch (Throwable th) {
      lock.unlock();
    }
  }

  public static String retrieveData(Context ctx, String file) {
    File dataFile = new File(ctx.getFilesDir(), file + ".data");
    if (!dataFile.exists()) {
      return null;
    }
    String s = null;
    Lock lock = initLock(file);
    lock.lock();
    try {
      FileInputStream fin = new FileInputStream(dataFile);

      s = IvyUtils.streamToString(fin);

      lock.unlock();
    } catch (FileNotFoundException e) {
      lock.unlock();
    } catch (Throwable th) {
      lock.unlock();
    }
    return s;
  }

  public static void removeData(Context ctx, String file) {
    File dataFile = new File(ctx.getFilesDir(), file + ".data");
//    synchronized (jsonObjectCache) {
//      jsonObjectCache.remove(file);
//    }
    Lock lock = initLock(file);
    lock.lock();
    try {
      dataFile.delete();
    } finally {
      lock.unlock();
    }
  }

  public static JSONObject readJSONObjectFromFile(Context ctx, String file) throws IOException {
    JSONObject json = null;
//    synchronized (jsonObjectCache) {
//      json = jsonObjectCache.get(file);
//    }
    if (json == null) {
      String data = retrieveData(ctx, file);
      if (data == null || data.length() == 0) {
        data = "{}";
      }
      try {
        json = new JSONObject(data);
      } catch (JSONException ex) {
        ex.printStackTrace();
        json = new JSONObject();
      }
//      synchronized (jsonObjectCache) {
//        jsonObjectCache.put(file, json);
//      }
    }
    return json;
  }

  public static int getVersionCode(Context c) {
    int versionCode = 1;
    try {
      return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionCode;
    } catch (Exception e) {
      return versionCode;
    }
  }

  public static String getVersionName(Context c) {
    String versionName = "";
    try {
      return c.getPackageManager().getPackageInfo(c.getPackageName(), 0).versionName;
    } catch (Exception e) {
      return versionName;
    }
  }

  public static void showDialog(Activity activity, int title, int message) {
    showDialog(activity, title, message, false);
  }

  public static void showDialog(final Activity activity, final int title, final int message, final boolean finish) {
    activity.runOnUiThread(new Runnable() {

      public void run() {
        Builder builder = new Builder(activity);
        if (title > 0) {
          builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setNeutralButton(R.string.ok, new OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int i) {
            dialog.dismiss();
            if (finish) {
              activity.finishAffinity();
            }
          }
        });
        builder.setOnCancelListener(new OnCancelListener() {
          @Override
          public void onCancel(DialogInterface dialogInterface) {
            if (finish) {
              activity.finishAffinity();
            }
          }
        });
        try {
          builder.create().show();
        } catch (Exception e) {
        }
      }
    });
  }

  public static boolean openURL(String url, Context context) {
    Intent intent = new Intent("android.intent.action.VIEW");
    try {
      intent.setData(Uri.parse(url));
      context.startActivity(intent);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
