package com.ivy.ads.promote.our;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.adsfall.R;
import com.ivy.IvySdk;
import com.ivy.IvyUtils;
import com.ivy.ads.configuration.PromoteConfig;
import com.ivy.ads.managers.PromoteAdManager;
import com.ivy.ads.ui.PagerIndicator;
import com.ivy.networks.DownloadFeedback;
import com.ivy.util.Logger;
import com.smarx.notchlib.NotchScreenManager;

import org.json.JSONObject;

import java.util.List;


public final class Full {
  public static final String TAG = Logger.createTag(Full.class);

  private static boolean isDisplaying = false;
  private static long lastCloseTime = 0L;

  private static AlertDialog showDialog(Activity context, View v) {
    AlertDialog dialog = new AlertDialog.Builder(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen).create();
    if (context.isFinishing()) {
      Logger.warning(TAG, "dialog: invalid context");
    } else {
      Window window = dialog.getWindow();
      NotchScreenManager.getInstance().setDisplayInNotch(dialog);
      if (window != null) {
        window.setContentView(v);
      }
      dialog.show();
    }
    return dialog;
  }

  public static void show(final PromoteAdManager adManager, String tag, PromoteConfig config) {
    if (System.currentTimeMillis() - lastCloseTime < 2000) {
      Logger.debug(TAG, "Two small duration, ignore this call");
      return;
    }

    if (isDisplaying) {
      Logger.debug(TAG, "Full displaying, ignore this call");
      return;
    }

    Logger.debug(TAG, "Full#showing.... platform ");
    Activity activity = adManager.getActivity();
    if (activity == null) {
      return;
    }
    List<JSONObject> validApps = config.selectAvailableCoverApps(activity);
    if (validApps != null) {
      if (validApps.size() > 0) {
        Logger.debug(TAG, "Full#showing....");
        final View vv = createView(adManager, activity, R.layout.android_sdk_ad_start, tag, validApps);
        final AlertDialog dialog;

        dialog = showDialog(activity, vv);

        final ViewPager pager = vv.findViewWithTag("pager");
        final Runnable autoSlideRunner = new Runnable() {
          @Override
          public void run() {
            if (pager != null) {
              try {
                int count = pager.getAdapter().getCount();
                int current = pager.getCurrentItem();
                pager.setCurrentItem(++current % count, true);
                adManager.getUiHandler().postDelayed(this, 5000);
              } catch (Exception | Error e) {
                e.printStackTrace();
              }
            }
          }
        };


        dialog.getWindow().setWindowAnimations(R.style.DialogStyle);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
          @Override
          public void onCancel(DialogInterface dialog) {
            Log.d(TAG, "Dialog dismiss....");
            isDisplaying = false;
            adManager.getUiHandler().removeCallbacks(autoSlideRunner);
            lastCloseTime = System.currentTimeMillis();
            dialog.dismiss();
          }
        });

        View returnView = vv.findViewWithTag("closebtn");
        if (returnView != null) {
          returnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              dialog.cancel();
            }
          });
        }
        adManager.getUiHandler().postDelayed(autoSlideRunner, 5000);
      }
    }
  }

  private static View createView(final PromoteAdManager adManager, final Context context, int layoutId, final String tag, final List<JSONObject> validApps) {
    View v = LayoutInflater.from(context).inflate(layoutId, null);
    final ViewPager vp = v.findViewWithTag("pager");

    final PagerIndicator indicator = v.findViewWithTag("indicator");
    indicator.setCount(validApps.size() >= 3 ? 3 : validApps.size());
    final ImageView iconView = v.findViewWithTag("icon");

    vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        indicator.setSelection(position);
        if (iconView != null) {
          final JSONObject app = validApps.get(position);
          try {
            // TODO:
            IvySdk.getCreativePath(app.optString("icon"), new DownloadFeedback() {
              @Override
              public void onSuccess(final String filePath) {
                adManager.getUiHandler().post(new Runnable() {
                  @Override
                  public void run() {
                    iconView.setImageURI(Uri.parse(filePath));
                  }
                });

              }

              @Override
              public void onFail() {
                Logger.warning(TAG, "failed to download icon file: " + app.optString("icon"));
              }
            });
            // iconView.setImageURI(Uri.parse(SdkCache.cache().makeUri("icon" + app.icon, false)));
          } catch (Exception | Error e) {
            e.printStackTrace();
          }
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });

    vp.setAdapter(new PagerAdapter() {
      @Override
      public int getCount() {
        return validApps.size() >= 3 ? 3 : validApps.size();
      }

      @Override
      public boolean isViewFromObject(View view, Object object) {
        return object == view;
      }

      @Override
      public Object instantiateItem(ViewGroup container, int position) {
        final JSONObject app = validApps.get(position);
        Logger.debug("Full#our: appid " + app.optInt("appid") + " app: " + app);
        final ImageView v = new ImageView(container.getContext());
        v.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String pkg = app.optString("package");
            IvyUtils.openPlayStore(v.getContext(), pkg, tag, app);
          }
        });
        v.setScaleType(ImageView.ScaleType.CENTER_CROP);

        IvySdk.getCreativePath(app.optString("cover"), new DownloadFeedback() {
          @Override
          public void onSuccess(final String filePath) {
            adManager.getUiHandler().post(new Runnable() {
              @Override
              public void run() {
                v.setImageURI(Uri.parse(filePath));
              }
            });
          }
          @Override
          public void onFail() {
            Logger.warning(TAG, "failed to download cover file: " + app.optString("cover"));
          }
        });
        container.addView(v);
        return v;
      }

      @Override
      public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView v = (ImageView) object;
        container.removeView(v);
      }
    });
    return v;
  }
}
