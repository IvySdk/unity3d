//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.adsfall.R;
//import com.ivy.IvySdk;
//import com.ivy.IvyUtils;
//import com.ivy.ads.configuration.PromoteConfig;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.ads.interfaces.IvyLoadStatus;
//import com.ivy.ads.managers.PromoteAdManager;
//import com.ivy.ads.ui.PagerIndicator;
//import com.ivy.networks.DownloadFeedback;
//import com.ivy.networks.grid.GridManager;
//import com.ivy.networks.util.Util;
//import com.ivy.util.Logger;
//import com.smarx.notchlib.NotchScreenManager;
//
//import org.json.JSONObject;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AdsfallNonRewardedAdapter extends FullpageAdapter<FullpageAdapter.GridParams> {
//  private static final String TAG = "AdsfallNonRewarded";
//
//  private static Bitmap offLineBrandImage = null;
//
//  static class MyBounceInterpolator implements android.view.animation.Interpolator {
//    private double mAmplitude = 1;
//    private double mFrequency = 10;
//
//    MyBounceInterpolator(double amplitude, double frequency) {
//      mAmplitude = amplitude;
//      mFrequency = frequency;
//    }
//
//    public float getInterpolation(float time) {
//      return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
//        Math.cos(mFrequency * time) + 1);
//    }
//  }
//
//
//  public AdsfallNonRewardedAdapter(Context context, String gridName, IvyAdType adType) {
//    super(context, gridName, adType);
//  }
//
//  public void setup(Activity activity) {
//    super.setup(activity);
//  }
//
//  public void fetch(Activity activity) {
//    List<JSONObject> _validApps = promoteConfig.selectCoverApps(activity);
//    // check all cover image is downlaoded
//    List<JSONObject> validApps = new ArrayList<>();
//    for (JSONObject o : _validApps) {
//      String cover = o.optString("cover");
//      if (PromoteAdManager.isCreativeAvailable(cover)) {
//        validApps.add(o);
//      }
//    }
//    if (validApps.size() > 0) {
//      onAdLoadSuccess();
//    } else {
//      try {
//        if (offLineBrandImage == null || offLineBrandImage.isRecycled()) {
//          InputStream in = activity.getResources().getAssets().open("interstitial_offline.jpg");
//          offLineBrandImage = BitmapFactory.decodeStream(in);
//          in.close();
//        }
//        if (offLineBrandImage != null) {
//          super.onAdLoadSuccess();
//        } else {
//          super.onAdLoadFailed(IvyLoadStatus.NO_FILL);
//        }
//      } catch (Throwable ex) {
//        offLineBrandImage = null;
//        super.onAdLoadFailed(IvyLoadStatus.NO_FILL);
//      }
//    }
//  }
//
//
//  public void show(Activity activity) {
//    Logger.debug(TAG, "Full#showing.... platform ");
//    List<JSONObject> _validApps = promoteConfig.selectCoverApps(activity);
//    // check all cover image is downlaoded
//    List<JSONObject> validApps = new ArrayList<>();
//    for (JSONObject o : _validApps) {
//      String cover = o.optString("cover");
//      if (PromoteAdManager.isCreativeAvailable(cover)) {
//        validApps.add(o);
//      }
//    }
//
//    if (validApps.size() > 0) {
//      final JSONObject firstApp = validApps.get(0);
//      boolean useFullImage = firstApp.optBoolean("fullimage", false);
//
//      if (!useFullImage) {
//        useFullImage = GridManager.getGridData().optBoolean("useFullImage", false);
//      }
//
//      if (useFullImage) {
//        Logger.debug(TAG, "display full brand image");
//
//        View v = LayoutInflater.from(activity).inflate(R.layout.ad_full_image, null);
//
//        String cover = firstApp.optString("cover");
//        String filePath = PromoteAdManager.getCreativeCachePath(cover);
//
//        Bitmap bitmap = null;
//        try {
//          bitmap = BitmapFactory.decodeFile(filePath);
//          if (bitmap == null) {
//            onAdShowFail();
//            return;
//          }
//        } catch(Throwable t) {
//          t.printStackTrace();
//        }
//
//        ImageView containerImage = v.findViewById(R.id.fullimage);
//        containerImage.setImageBitmap(bitmap);
//        final AlertDialog dialog = showDialog(activity, v);
//
//        final Bitmap bm = bitmap;
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//          @Override
//          public void onDismiss(DialogInterface dialogInterface) {
//            try {
//              if (bm != null && !bm.isRecycled()) {
//                bm.recycle();
//              }
//
//              onAdClosed(false);
//            } catch(Throwable e) {
//              e.printStackTrace();
//            }
//          }
//        });
//        dialog.getWindow().setWindowAnimations(R.style.DialogAnimationUpDown);
//        containerImage.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//            String pkg = firstApp.optString("package");
//            getEventParams().put("promoteapp", pkg);
//            AdsfallNonRewardedAdapter.this.onAdClicked();
//
//            IvyUtils.openPlayStore(v.getContext(), pkg, "fallback_ad", firstApp);
//
//            dialog.dismiss();
//          }
//        });
//
//        View returnView = v.findViewWithTag("closebtn");
//        if (returnView != null) {
//          returnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              dialog.dismiss();
//            }
//          });
//        }
//
//        mUiHandler.postDelayed(new Runnable() {
//          @Override
//          public void run() {
//            final Animation myAnim = AnimationUtils.loadAnimation(activity, R.anim.bounce);
//
//            // Use bounce interpolator with amplitude 0.2 and frequency 20
//            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
//            myAnim.setInterpolator(interpolator);
//
//            returnView.startAnimation(myAnim);
//          }
//        }, 400);
//
//        container = (ViewGroup) v;
//        onAdShowSuccess();
//        return;
//      }
//      final View vv = createView(activity, R.layout.android_sdk_ad_start, validApps);
//      final AlertDialog dialog;
//
//      dialog = showDialog(activity, vv);
//
//      onAdShowSuccess();
//
//      final ViewPager pager = (ViewPager) vv.findViewWithTag("pager");
//
//      dialog.getWindow().setWindowAnimations(R.style.DialogStyle);
//      dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//        @Override
//        public void onCancel(DialogInterface dialog) {
//          dismiss();
//          onAdClosed(false);
//        }
//      });
//
//      View returnView = vv.findViewWithTag("closebtn");
//      if (returnView != null) {
//        returnView.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            dialog.cancel();
//          }
//        });
//      }
//      container = (ViewGroup) vv;
//    } else {
//      if (offLineBrandImage != null) {
//        Logger.debug(TAG, "display offline brand image");
//
//        View v = LayoutInflater.from(activity).inflate(R.layout.ad_full_image, null);
//        ImageView containerImage = v.findViewById(R.id.fullimage);
//        containerImage.setImageBitmap(offLineBrandImage);
//        final AlertDialog dialog = showDialog(activity, v);
//
//        String defaultBrandUrl = GridManager.getGridData().optString("brandUrl", "");
//        if (!"".equals(defaultBrandUrl)) {
//          containerImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              Util.openURL(defaultBrandUrl, activity);
//            }
//          });
//        }
//
//        View returnView = v.findViewWithTag("closebtn");
//        if (returnView != null) {
//          returnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              dialog.cancel();
//            }
//          });
//        }
//        container = (ViewGroup) v;
//
//        Bundle bundle = new Bundle();
//        IvySdk.logEvent("af_brand_shown", bundle);
//
//        onAdShowSuccess();
//      } else {
//        onAdShowFail();
//      }
//    }
//  }
//
//  private PromoteConfig promoteConfig;
//
//  public void setPromoteConfig(PromoteConfig config) {
//    this.promoteConfig = config;
//  }
//
//  public String getId() {
//    return ((GridParams) getGridParams()).placement;
//  }
//
//  public String getPlacementId() {
//    return ((GridParams) getGridParams()).placement;
//  }
//
//  protected GridParams newGridParams() {
//    return new GridParams();
//  }
//
//  public static class GridParams extends com.ivy.ads.adapters.BaseAdapter.GridParams {
//    public String placement;
//
//    public GridParams fromJSON(JSONObject jsonObject) {
//      this.placement = jsonObject.optString("placement");
//      return this;
//    }
//
//    protected String getParams() {
//      return "placement=" + this.placement;
//    }
//  }
//
//  private ViewGroup container;
//  private ImageView icon;
//
//  public static AlertDialog showDialog(Activity context, View v) {
//    AlertDialog dialog = new AlertDialog.Builder(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen).create();
//    NotchScreenManager.getInstance().setDisplayInNotch(dialog);
//    if (context.isFinishing()) {
//      Logger.warning(TAG, "dialog: invalid context");
//    } else {
//      dialog.show();
//      Window window = dialog.getWindow();
//      if (window != null) {
//        window.setContentView(v);
//      }
//    }
//
//    return dialog;
//  }
//
//  public void dismiss() {
//    try {
//      if (container != null) {
//        container.removeAllViews();
//        container = null;
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
//
//  private View createView(final Context context, int layoutId, final List<JSONObject> validApps) {
//    View v = LayoutInflater.from(context).inflate(layoutId, null);
//    final ViewPager vp = (ViewPager) v.findViewWithTag("pager");
//
//    final PagerIndicator indicator = (PagerIndicator) v.findViewWithTag("indicator");
//    indicator.setCount(validApps.size() >= 3 ? 3 : validApps.size());
//    final ImageView iconView = (ImageView) v.findViewWithTag("icon");
//
//    vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//      @Override
//      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//      }
//
//      @Override
//      public void onPageSelected(int position) {
//        indicator.setSelection(position);
//        if (iconView != null) {
//          final JSONObject app = validApps.get(position);
//          try {
//            IvySdk.getCreativePath(app.optString("icon"), new DownloadFeedback() {
//              @Override
//              public void onSuccess(final String filePath) {
//                activity.runOnUiThread(new Runnable() {
//                  @Override
//                  public void run() {
//                    iconView.setImageURI(Uri.parse(filePath));
//                  }
//                });
//              }
//
//              @Override
//              public void onFail() {
//                Logger.warning(TAG, "failed to download icon file: " + app.optString("icon"));
//              }
//            });
//          } catch (Exception | Error e) {
//            e.printStackTrace();
//          }
//        }
//      }
//
//      @Override
//      public void onPageScrollStateChanged(int state) {
//
//      }
//    });
//
//    vp.setAdapter(new PagerAdapter() {
//      @Override
//      public int getCount() {
//        return validApps.size() >= 3 ? 3 : validApps.size();
//      }
//
//      @Override
//      public boolean isViewFromObject(View view, Object object) {
//        return object == view;
//      }
//
//      @Override
//      public Object instantiateItem(ViewGroup container, int position) {
//        final JSONObject app = validApps.get(position);
//        Logger.debug("Full#our: appid " + app.optInt("appid") + " app: " + app);
//        final ImageView v = new ImageView(container.getContext());
//        v.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//
//            String pkg = app.optString("package");
//            getEventParams().put("promoteapp", pkg);
//            AdsfallNonRewardedAdapter.this.onAdClicked();
//
//            IvyUtils.openPlayStore(v.getContext(), pkg, "fallback_ad", app);
//          }
//        });
//        v.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//        if (app != null) {
//          String cover = app.optString("cover");
//          if (cover != null && !"".equals(cover)) {
//            IvySdk.getCreativePath(app.optString("cover"), new DownloadFeedback() {
//              @Override
//              public void onSuccess(final String filePath) {
//                activity.runOnUiThread(new Runnable() {
//                  @Override
//                  public void run() {
//                    v.setImageURI(Uri.parse(filePath));
//                  }
//                });
//              }
//
//              @Override
//              public void onFail() {
//                Logger.warning(TAG, "failed to download cover file: " + app.optString("cover"));
//              }
//            });
//          }
//        }
//        container.addView(v);
//        return v;
//      }
//
//      @Override
//      public void destroyItem(ViewGroup container, int position, Object object) {
//        try {
//          ImageView v = (ImageView) object;
//          BitmapDrawable drawable = (BitmapDrawable) v.getDrawable();
//          if (drawable != null) {
//            Bitmap bitmap = drawable.getBitmap();
//            if (bitmap != null) {
//              bitmap.recycle();
//              System.gc();
//            }
//          }
//          container.removeView(v);
//        } catch(Exception ex) {
//          ex.printStackTrace();
//        }
//      }
//    });
//
//    View download = v.findViewWithTag("downloadbtn");
//    if (download != null) {
//      download.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//        }
//      });
//    }
//
//    icon = iconView;
//
//    return v;
//  }
//
//  @Override
//  public void onDestroy(Activity activity) {
//    super.onDestroy(activity);
//    if (offLineBrandImage != null && !offLineBrandImage.isRecycled()) {
//      offLineBrandImage.recycle();
//      offLineBrandImage = null;
//    }
//  }
//}
