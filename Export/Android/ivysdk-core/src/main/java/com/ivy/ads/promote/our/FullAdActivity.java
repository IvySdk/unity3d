package com.ivy.ads.promote.our;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.adsfall.R;
import com.ivy.IvySdk;
import com.ivy.IvyUtils;
import com.ivy.ads.configuration.PromoteConfig;
import com.ivy.ads.events.EventID;
import com.ivy.ads.ui.PagerIndicator;
import com.ivy.networks.DownloadFeedback;
import com.ivy.util.Logger;

import org.json.JSONObject;

import java.util.List;

/**
 * 退出游戏Activity,包含一个退出按钮， app交叉推广，更多游戏链接(启动GameWallActivity)
 * <p>
 * Created by song on 16/5/23.
 */
public class FullAdActivity extends Activity {
  private static final String TAG = "FullAd";

  public static final String EXTRA_CONFIG = "config";
  private static final String EXTRA_AD_POS = "_AD_POS_";
  private Handler handler = null;
  private String adPos;

  public static void launch(Context context, String adPos, PromoteConfig config) {
    Intent intent = new Intent(context, FullAdActivity.class).putExtra(EXTRA_AD_POS, adPos).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
    intent.putExtra(EXTRA_CONFIG, config.toBundle());
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (savedInstanceState != null) {
      adPos = savedInstanceState.getString(EXTRA_AD_POS);
    } else {
      adPos = getIntent().getStringExtra(EXTRA_AD_POS);
    }

    handler = new Handler(getMainLooper());
    try {
      setupFullAd();
    } catch(Throwable t) {
      // ignore crash protection
      t.printStackTrace();
    }
//    Bundle bundle = new Bundle();
//    IvySdk.logEvent(EventID.QUIT_DISPLAYED, bundle);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(EXTRA_AD_POS, adPos);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    adPos = savedInstanceState.getString(EXTRA_AD_POS);
  }

  private void setupFullAd() {

    Bundle configBundle = getIntent().getBundleExtra(EXTRA_CONFIG);

    final PromoteConfig config = PromoteConfig.fromBundle(configBundle);

    final boolean exit = "exit".equals(adPos);
    final List<JSONObject> validApps = config.selectAllCoverApps(this);
    if (validApps != null) {
      if (validApps.size() > 0) {
        setContentView(exit ? R.layout.android_sdk_ad_quit : R.layout.android_sdk_ad_start);

        View vv = getWindow().getDecorView();

        final ViewPager vp = vv.findViewWithTag("pager");

        final PagerIndicator indicator = vv.findViewWithTag("indicator");
        indicator.setCount(validApps.size() >= 3 ? 3 : validApps.size());
        final ImageView iconView = vv.findViewWithTag("icon");

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
                IvySdk.getCreativePath(app.optString("icon"), new DownloadFeedback() {
                  @Override
                  public void onSuccess(final String filePath) {
                    handler.post(new Runnable() {
                      @Override
                      public void run() {
                        try {
                          if (!FullAdActivity.this.isFinishing()) {
                            iconView.setImageURI(Uri.parse(filePath));
                          }
                        } catch(Throwable t) {
                          t.printStackTrace();
                        }
                      }
                    });
                  }

                  @Override
                  public void onFail() {
                    Logger.warning(TAG, "failed to download cover file: " + app.optString("icon"));
                  }
                });
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
          @NonNull
          public Object instantiateItem(ViewGroup container, int position) {
            try {
              final ImageView v = new ImageView(container.getContext());
              final JSONObject app = validApps.get(position);
              Logger.info(TAG, "Full#our: appid " + app.optInt("appid") + " app: " + app);
              v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  String pkg = app.optString("package");
                  IvyUtils.openPlayStore(FullAdActivity.this, pkg, adPos, app);

//                  Bundle bundle = new Bundle();
//                  bundle.putString("promoteapp", pkg);
//                  bundle.putString("label", "exitpage");
//                  IvySdk.logEvent("gamewall", bundle);
                }
              });
              v.setScaleType(ImageView.ScaleType.CENTER_CROP);
              String cover = app.optString("cover");
              if (cover != null && !"".equals(cover)) {
                IvySdk.getCreativePath(app.optString("cover"), new DownloadFeedback() {
                  @Override
                  public void onSuccess(final String filePath) {
                    handler.post(new Runnable() {
                      @Override
                      public void run() {
                        try {
                          if (!FullAdActivity.this.isFinishing()) {
                            v.setImageURI(Uri.parse(filePath));
                          }
                        } catch (Throwable t) {
                          t.printStackTrace();
                        }
                      }
                    });
                  }

                  @Override
                  public void onFail() {
                    Logger.warning(TAG, "failed to download cover file: " + app.optString("cover"));
                  }
                });
              }

              container.addView(v);
              return v;
            } catch(Exception ex) {
              ex.printStackTrace();
            }
            return null;
          }

          @Override
          public void destroyItem(ViewGroup container, int position, Object object) {
            try {
              ImageView v = (ImageView) object;
              container.removeView(v);
            } catch(Exception ex) {
              ex.printStackTrace();
            }
          }
        });

        View download = vv.findViewWithTag("downloadbtn");
        if (download != null) {
          download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              JSONObject app = validApps.get(vp.getCurrentItem());
              if (app != null) {
                String pkg = app.optString("package");
                IvyUtils.openPlayStore(FullAdActivity.this, pkg, adPos, app);
              }
            }
          });
        }

        if (iconView != null) {
          final JSONObject app = validApps.get(0);
          // TODO: click to download app
          IvySdk.getCreativePath(app.optString("icon"), new DownloadFeedback() {
            @Override
            public void onSuccess(final String filePath) {
              handler.post(new Runnable() {
                @Override
                public void run() {
                  try {
                    if (!FullAdActivity.this.isFinishing()) {
                      iconView.setImageURI(Uri.parse(filePath));
                    }
                  } catch(Throwable t) {
                    t.printStackTrace();
                  }
                }
              });

            }

            @Override
            public void onFail() {
              Logger.warning(TAG, "failed to download cover file: " + app.optString("icon"));
            }
          });
          iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              JSONObject app = validApps.get(vp.getCurrentItem());
              if (app != null) {
                String pkg =  app.optString("package");
                IvyUtils.openPlayStore(FullAdActivity.this, app.optString("package"), adPos, app);
              }
            }
          });
        }

        View moreGame = vv.findViewWithTag("morebtn");
        if (moreGame != null) {
          moreGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              GameActivity.launch(FullAdActivity.this, config);
            }
          });
        }

        View exitView = vv.findViewWithTag("yesbtn");
        if (exitView != null) {
          exitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finishAffinity();
              android.os.Process.killProcess(android.os.Process.myPid());
            }
          });
        }

        View returnView = vv.findViewWithTag("closebtn");
        if (returnView != null) {
          returnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
          });
        }
      } else {
        Logger.info("Full#valid apps is empty");
        finish();
      }
    } else {
      Logger.info("Full#valid apps is null");
      finish();
    }
  }

  @Override
  public void onDestroy() {
    try {
      handler.removeCallbacksAndMessages(null);
    } catch(Throwable t) {
      t.printStackTrace();
    }
    super.onDestroy();
  }
}
