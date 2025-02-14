package com.android.client;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.adsfall.R;
import com.ivy.IvySdk;
import com.ivy.IvyUtils;
import com.ivy.ads.events.EventID;
import com.ivy.ads.events.EventParams;
import com.ivy.ads.managers.PromoteAdManager;
import com.ivy.util.Logger;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CrossPromotionAd {
  private CrossPromotionAd() {
    //hide constructor
  }

  private enum Singleton {
    INSTANCE;

    private final CrossPromotionAd instance;

    Singleton() {
      instance = new CrossPromotionAd();
    }

    private CrossPromotionAd getInstance() {
      return instance;
    }
  }

  public static CrossPromotionAd getInstance() {
    return CrossPromotionAd.Singleton.INSTANCE.getInstance();
  }

  private final static String TAG = CrossPromotionAd.class.getCanonicalName();
  //记录当前状态
  private CrossPromotionAdStatus adStatus = CrossPromotionAdStatus.Idle;
  private WeakReference<Activity> activityRef;
  private boolean isInitView = false;
  private RelativeLayout promotionRootLayout;
  private ImageView promotionImageView;
  private Button promotionSkipButton;
  private final AdShowController finishShowTimer = new AdShowController();
  private String promotePackage;

  /**
   * @param imageUrl          图片url
   * @param promotionTime     5  //开屏交叉倒计时5秒后退出
   * @param promotionKeep     3   //开屏交叉保持3秒不能点击“跳过”
   * @param promotionDayTimes 2  //开屏广告每天展示次数
   * @param promotionInterval 7200  //开屏广告展示间隔2小时
   */
  public void loadAd(Activity activity, String promotePackage, String imageUrl,
                     int promotionTime, int promotionKeep, int promotionDayTimes, int promotionInterval) {
    activityRef = new WeakReference<>(activity);
    this.promotePackage = promotePackage;
    //如果不处于空闲状态，则不加载
    if (adStatus != CrossPromotionAdStatus.Idle) {
      return;
    }
    if (!checkCanShowToday(promotionDayTimes, promotionInterval)) {
      Logger.debug(TAG, "will not shown today");
      return;
    }
    promotionKeep = Math.min(promotionTime, promotionKeep);
    finishShowTimer.setUp(promotionTime, promotionKeep, new AdShowListener() {
      @Override
      public void onLeftTime(int second, boolean cancelable) {
        activityRef.get().runOnUiThread(new Runnable() {
          @Override
          public void run() {
            updateSkipButton(second, cancelable);
          }
        });
      }

      @Override
      public void onFinished() {
        activityRef.get().runOnUiThread(new Runnable() {
          @Override
          public void run() {
            hidePromotionView();
          }
        });
      }
    });

    if (!PromoteAdManager.isCreativeAvailable(imageUrl)) {
      Logger.debug(TAG, "Creative not ready : " + imageUrl);
      return;
    }

    activityRef.get().runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (!isInitView) {
          initView();
          isInitView = true;
        }
        String filePath = PromoteAdManager.getCreativeCachePath(imageUrl);
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        showPromotionView(bitmap);
        recordShow();
        finishShowTimer.start();
      }
    });
  }

  private void updateSkipButton(int secondLeft, boolean cancelable) {
    @SuppressLint("StringFormatMatches") String skipText = cancelable ?
      String.format(activityRef.get().getResources().getString(R.string.promotion_skip_format), secondLeft) : String.valueOf(secondLeft);
    promotionSkipButton.setText(skipText);
  }

  private void initView() {
    promotionRootLayout = (RelativeLayout) LayoutInflater.from(activityRef.get()).inflate(R.layout.layout_full_cross, null);
    promotionImageView = promotionRootLayout.findViewById(R.id.iv_promotion_image);
    promotionImageView.setOnClickListener(view -> {

      IvyUtils.openPlayStore(activityRef.get(), promotePackage, "splash", null);

//      Bundle bundle = new Bundle();
//      bundle.putString(EventParams.PARAM_PROMOTEAPP, promotePackage);
//      IvySdk.logEvent(EventID.SPLASH_PROMOTION_CLICKED, bundle);
    });
    promotionSkipButton = promotionRootLayout.findViewById(R.id.bt_promotion_skip);
    promotionSkipButton.setOnClickListener(view -> {
      if (finishShowTimer != null) {
        finishShowTimer.cancel();
      }
//      Bundle bundle = new Bundle();
//      bundle.putString(EventParams.PARAM_PROMOTEAPP, promotePackage);
//      IvySdk.logEvent(EventID.SPLASH_PROMOTION_SKIPPED, bundle);
    });
    isInitView = true;
  }

  private void showPromotionView(Bitmap bitmap) {
    promotionImageView.setImageBitmap(bitmap);
    Point outPoint = getRealScreenEndPoint(activityRef.get().getApplication());
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(outPoint.x, outPoint.y);
    promotionRootLayout.setVisibility(View.VISIBLE);
    if (promotionRootLayout.getParent() == null) {
      activityRef.get().addContentView(promotionRootLayout, lp);
    }
    setActivityFullScreenMode();
    adStatus = CrossPromotionAdStatus.Showing;
  }

  private void hidePromotionView() {
    adStatus = CrossPromotionAdStatus.Idle;
    promotionRootLayout.setVisibility(View.GONE);
    //remove view
    ViewParent viewParent = promotionRootLayout.getParent();
    if (viewParent instanceof ViewGroup) {
      ViewGroup viewGroup = (ViewGroup) (viewParent);
      viewGroup.removeView(promotionRootLayout);
    }
    //即时释放bitmap，否则内存会升高
    Drawable drawable = promotionImageView.getDrawable();
    if (drawable instanceof BitmapDrawable) {
      BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
      Bitmap bitmap = bitmapDrawable.getBitmap();
      bitmap.recycle();
    }
    resetActivityScreenMode();
    adStatus = CrossPromotionAdStatus.Idle;
  }


  ///////////////////当前屏幕原始设置///////////////////////////
  private int originSystemUiVisibility;
  private int originStatusBarColor;
  private int originNavBarColor;
  private int originDisplayCutoutMode;
  private boolean originShowActionBar;
  ///////////////////当前屏幕原始设置///////////////////////////

  private void setActivityFullScreenMode() {
    View decorView = activityRef.get().getWindow().getDecorView();
    originSystemUiVisibility = decorView.getSystemUiVisibility();
    decorView.setSystemUiVisibility(
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        | View.SYSTEM_UI_FLAG_FULLSCREEN
        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    );

    Window window = activityRef.get().getWindow();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      originStatusBarColor = window.getStatusBarColor();
      originNavBarColor = window.getNavigationBarColor();

      window.setStatusBarColor(Color.TRANSPARENT);
      window.setNavigationBarColor(Color.TRANSPARENT);
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      WindowManager.LayoutParams layoutParams = window.getAttributes();
      originDisplayCutoutMode = layoutParams.layoutInDisplayCutoutMode;
      layoutParams.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
      window.setAttributes(layoutParams);
    }
    android.app.ActionBar actionBar = activityRef.get().getActionBar();
    if (actionBar != null) {
      originShowActionBar = actionBar.isShowing();
      actionBar.hide();
    }
  }

  private void resetActivityScreenMode() {
    View decorView = activityRef.get().getWindow().getDecorView();
    decorView.setSystemUiVisibility(originSystemUiVisibility);

    Window window = activityRef.get().getWindow();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      window.setStatusBarColor(originStatusBarColor);
      window.setNavigationBarColor(originNavBarColor);
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      WindowManager.LayoutParams layoutParams = window.getAttributes();
      layoutParams.layoutInDisplayCutoutMode = originDisplayCutoutMode;
      window.setAttributes(layoutParams);
    }
    android.app.ActionBar actionBar = activityRef.get().getActionBar();
    if (actionBar != null) {
      if (originShowActionBar) {
        actionBar.show();
      } else {
        actionBar.hide();
      }
    }
  }

  private Point getRealScreenEndPoint(Application application) {
    WindowManager windowManager = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);
    final Display display = windowManager.getDefaultDisplay();
    Point outPoint = new Point();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      display.getRealSize(outPoint);
    } else {
      display.getSize(outPoint);
    }
    return outPoint;
  }

  private static class AdShowController {

    private final Timer timer = new Timer();
    private long startTime = 0; //开始及时时间
    private long finishTime = 0; //取消或结束时间
    private int executeTime = 0;

    private int promotionTime; //持续时间
    private int promotionKeep; //不可取消时间
    private AdShowListener listener;
    private TimerTask timerTask;

    public void start() {
      startTime = System.currentTimeMillis();
      timerTask = new TimerTask() {
        @Override
        public void run() {
          checkFinished();
        }
      };
      timer.schedule(timerTask, 0, 1000);
    }

    public void cancel() {
      if (cancelable()) {
        timerTask.cancel();
        timer.purge();
        finishTime = System.currentTimeMillis();
        listener.onFinished();
        this.reset();
      } else {
        Log.w(TAG, "now is in keep time,no able to cancel");
      }
    }

    public void setUp(int promotionTime, int promotionKeep, AdShowListener listener) {
      this.promotionTime = promotionTime;
      this.promotionKeep = promotionKeep;
      this.listener = listener;
    }

    public void reset() {
      this.startTime = 0;
      this.finishTime = 0;
      this.executeTime = 0;
      this.promotionTime = 0;
      this.promotionKeep = 0;
      this.timerTask = null;
      this.listener = null;
    }

    //是否可以取消
    public boolean cancelable() {
      return startTime != 0   //已经开始
        && finishTime == 0  //还没结束
        && executeTime >= promotionKeep; //在锁定时间内
    }

    private void checkFinished() {
      if (promotionTime - executeTime <= 0) {
        cancel();
      } else {
        listener.onLeftTime(promotionTime - executeTime, cancelable());
      }
      executeTime++;
    }
  }

  private final String todayShowTimeKey = String.format("_show_time_%s", getTodayDateStr());
  private final String lastShowTimeKey = "_last_show_time";

  public void resetShowTime() {
    SharedPreferences promotion = activityRef.get().getSharedPreferences("_cyj_promotion", MODE_PRIVATE);
    SharedPreferences.Editor editor = promotion.edit();
    editor.putInt(todayShowTimeKey, 0);
    editor.putLong(lastShowTimeKey, 0);
    editor.apply();
  }

  private void recordShow() {
    SharedPreferences promotion = activityRef.get().getSharedPreferences("_cyj_promotion", MODE_PRIVATE);
    int todayShowTime = promotion.getInt(todayShowTimeKey, 0);
    SharedPreferences.Editor editor = promotion.edit();
    editor.putInt(todayShowTimeKey, todayShowTime + 1);
    editor.putLong(lastShowTimeKey, System.currentTimeMillis());
    editor.apply();

//    Bundle bundle = new Bundle();
//    bundle.putString(EventParams.PARAM_PROMOTEAPP, promotePackage);
//    IvySdk.logEvent(EventID.SPLASH_PROMOTION_DISPLAYED, bundle);
  }

  private boolean checkCanShowToday(int promotionDayTimes, int promotionInterval) {
    SharedPreferences promotion = activityRef.get().getSharedPreferences("_cyj_promotion", MODE_PRIVATE);
    int todayShow = promotion.getInt(todayShowTimeKey, 0);
    long lastShowTime = promotion.getLong(lastShowTimeKey, 0);
    if (todayShow >= promotionDayTimes) {
      Log.e(TAG, String.format("today show times: 【%s】 more than define: 【%s】", todayShow, promotionDayTimes));
      return false;
    }
    long current = System.currentTimeMillis();
    long interval = System.currentTimeMillis() - lastShowTime;
    if (interval < promotionInterval * 1000) {
      Log.e(TAG, String.format("interval: 【%s】 between: 【%s】 and 【%s】 less than define interval: 【%s】",
        interval, lastShowTime, current, promotionInterval *1000));
      return false;
    }
    return true;
  }

  private String getTodayDateStr() {
    Date date = new Date();
    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
    return dateFormat.format(date);
  }

  public interface AdShowListener {
    void onLeftTime(int seconds, boolean cancelable);
    void onFinished();
  }

  enum CrossPromotionAdStatus {
    Idle, Loading, Showing
  }
}
