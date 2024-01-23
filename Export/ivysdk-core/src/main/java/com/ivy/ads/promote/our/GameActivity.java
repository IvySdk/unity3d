//package com.ivy.ads.promote.our;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.LruCache;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import androidx.core.content.ContextCompat;
//import androidx.viewpager.widget.PagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.adsfall.R;
//import com.ivy.IvySdk;
//import com.ivy.IvyUtils;
//import com.ivy.ads.configuration.PromoteConfig;
//import com.ivy.ads.events.EventID;
//import com.ivy.ads.ui.PagerIndicator;
//import com.ivy.networks.DownloadFeedback;
//import com.ivy.util.Logger;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 更多游戏Acitivity
// */
//public class GameActivity extends Activity {
//  private static final String TAG = GameActivity.class.getCanonicalName();
//
//  public static final String EXTRA_CONFIG = "config";
//  private static Handler handler;
//  BitmapLoader iconLoader = new BitmapLoader();
//  ViewPager pager;
//  private Runnable autoSlideRunner = new Runnable() {
//    @Override
//    public void run() {
//      if (!GameActivity.this.isFinishing()) {
//      if (pager != null && pager.getAdapter() != null) {
//        int count = pager.getAdapter().getCount();
//        if (count > 0) {
//          int current = pager.getCurrentItem();
//          pager.setCurrentItem(++current % count, true);
//          handler.postDelayed(this, 5000);
//        }
//      }
//    }
//    }
//  };
//
//  public static void launch(Context context, PromoteConfig config) {
//    Intent intent = new Intent(context, GameActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
//    intent.putExtra(EXTRA_CONFIG, config.toBundle());
//    context.startActivity(intent);
//  }
//
//  @Override
//  protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//
//    handler = new Handler(getMainLooper());
//
//    setContentView(R.layout.android_sdk_list);
//
//    Bundle configBundle = getIntent().getBundleExtra(EXTRA_CONFIG);
//
//    final PromoteConfig config = PromoteConfig.fromBundle(configBundle);
//
//    final JSONArray moreAppsSettings = config.moreGames;
//
//    List<JSONObject> moreGames = new ArrayList<>();
//    // filter all installed packaged
//    if (moreAppsSettings != null) {
//      for (int i = 0; i < moreAppsSettings.length(); i++) {
//        int appId = moreAppsSettings.optInt(i);
//        JSONObject o = config.getAppIfNotInstalled(this, appId);
//        if (o != null) {
//          String icon = o.optString("icon");
//          if (icon != null && !"".equals(icon)) {
//            moreGames.add(o);
//          }
//        }
//      }
//    }
//
//    View decorView = getWindow().getDecorView();
//    decorView.findViewWithTag("closebtn").setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        onBackPressed();
//      }
//    });
//
//    View offerwall_title = decorView.findViewById(R.id.offerwall_title);
//    if (offerwall_title != null) {
//      offerwall_title.setTag(0);
//      offerwall_title.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//          Object clickObject = view.getTag();
//          int clickCount = 0;
//          if (clickObject == null) {
//            view.setTag(1);
//          } else {
//            if (clickObject instanceof Integer) {
//              clickCount = Integer.parseInt(String.valueOf(clickObject)) + 1;
//              view.setTag(clickCount);
//            }
//          }
//        }
//      });
//    }
//    ListView listView = decorView.findViewWithTag("listview");
//
//    ViewGroup pager = (ViewGroup) getLayoutInflater().inflate(R.layout.android_sdk_list_view_pager, listView, false);
//    ViewPager viewPager = pager.findViewWithTag("adv_pager");
//    final PagerIndicator indicator = pager.findViewWithTag("indicator");
//
//    int maxCount = 3;
//
//    final ArrayList<JSONObject> banners = new ArrayList<>(maxCount);
//    List<JSONObject> hasBannerApps = config.selectBannerApps(this);
//
//    if (hasBannerApps != null) {
//      for (JSONObject app : hasBannerApps) {
//        banners.add(app);
//        if (--maxCount == 0) {
//          break;
//        }
//      }
//    }
//
//    indicator.setCount(banners.size());
//    indicator.setSelection(0);
//
//    viewPager.setAdapter(new PagerAdapter() {
//      @Override
//      public int getCount() {
//        return banners.size();
//      }
//
//      @Override
//      public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//      }
//
//      @Override
//      public Object instantiateItem(ViewGroup container, int position) {
//        final JSONObject app = banners.get(position);
//        final ImageView v = new ImageView(container.getContext());
//        v.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        v.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//            String pkg = app.optString("package");
//            IvyUtils.openPlayStore(GameActivity.this, pkg, "moreGame", app);
//
////            Bundle bundle = new Bundle();
////            bundle.putString("promoteapp", pkg);
////            bundle.putString("label", "banner");
////            IvySdk.logEvent("gamewall", bundle);
//          }
//        });
//
//        //  set Banner Conver image
//        IvySdk.getCreativePath(app.optString("banner"), new DownloadFeedback() {
//          @Override
//          public void onSuccess(final String filePath) {
//            handler.post(new Runnable() {
//              @Override
//              public void run() {
//                v.setImageURI(Uri.parse(filePath));
//              }
//            });
//          }
//
//          @Override
//          public void onFail() {
//            Logger.warning(TAG, "failed to download cover file: " + app.optString("banner"));
//          }
//        });
//
//        v.setImageDrawable(ContextCompat.getDrawable(GameActivity.this, R.drawable.ad_banner_placeholder));
//        container.addView(v);
//        return v;
//      }
//
//      @Override
//      public void destroyItem(ViewGroup container, int position, Object object) {
//        ImageView v = (ImageView) object;
//        v.setOnClickListener(null);
//        v.setImageDrawable(null);
//
//        container.removeView(v);
//      }
//    });
//
//    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//      @Override
//      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//      }
//
//      @Override
//      public void onPageSelected(int position) {
//        indicator.setSelection(position);
//      }
//
//      @Override
//      public void onPageScrollStateChanged(int state) {
//
//      }
//    });
//    this.pager = viewPager;
//    handler.postDelayed(autoSlideRunner, 5000);
//
//    listView.addHeaderView(pager);
//    listView.setAdapter(new BaseAdapter() {
//      @Override
//      public int getCount() {
//        return moreGames.size();
//      }
//
//      @Override
//      public Object getItem(int position) {
//        return position;
//      }
//
//      @Override
//      public long getItemId(int position) {
//        return position;
//      }
//
//      @Override
//      public View getView(int position, View convertView, ViewGroup parent) {
//        try {
//          ViewHolder holder;
//          if (convertView == null) {
//            convertView = getLayoutInflater().inflate(R.layout.android_sdk_list_item, parent, false);
//            holder = new ViewHolder(convertView, iconLoader);
//          } else {
//            holder = (ViewHolder) convertView.getTag();
//          }
//          holder.bind(moreGames.get(position), position % 2 == 0);
//        } catch(Exception ex) {
//          ex.printStackTrace();
//        }
//        return convertView;
//      }
//    });
//
//    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//      @Override
//      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        position -= 1;
//        try {
//          JSONObject app = moreGames.get(position);
//
//          String pkg = app.optString("package");
//
//          IvyUtils.openPlayStore(GameActivity.this, pkg, "moreGame", app);
//
////          Bundle bundle = new Bundle();
////          bundle.putString("promoteapp", pkg);
////          bundle.putString("label", "gamelist");
////          IvySdk.logEvent("gamewall", bundle);
//        } catch(Exception ex) {
//          ex.printStackTrace();
//        }
//      }
//    });
//
////    Bundle bundle = new Bundle();
////    IvySdk.logEvent(EventID.GAMEWALL_DISPLAYED, bundle);
//  }
//
//  @Override
//  protected void onDestroy() {
//    handler.removeCallbacks(autoSlideRunner);
//    if (iconLoader != null) {
//      iconLoader.destroy();
//      iconLoader = null;
//    }
//    super.onDestroy();
//  }
//
//  private static class ViewHolder {
//    View bg;
//    ImageView icon;
//    TextView title;
//    TextView desc;
//    BitmapLoader loader;
//    private Context context;
//
//    public ViewHolder(View root, BitmapLoader loader) {
//      context = root.getContext();
//      bg = root.findViewWithTag("bg");
//      icon = (ImageView) root.findViewWithTag("icon");
//      title = (TextView) root.findViewWithTag("title");
//      desc = (TextView) root.findViewWithTag("desc");
//      root.setTag(this);
//      this.loader = loader;
//    }
//
//    public void bind(JSONObject app, boolean black) {
//      if (black) {
//        bg.setBackgroundResource(R.drawable.android_sdk_list_banner1);
//      } else {
//        bg.setBackgroundResource(R.drawable.android_sdk_list_banner2);
//      }
//      title.setText(app.optString("name"));
//      desc.setText(app.optString("desc"));
//
//      // reset the image icon to default
//      icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ad_icon_placeholder));
//      loader.load(app.optString("icon"), icon);
//    }
//  }
//
//  public static class BitmapLoader {
//    LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(24) {
//      @Override
//      protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
//        if (oldValue != null) {
//          oldValue.recycle();
//        }
//      }
//    };
//
//    public void destroy() {
//      cache.evictAll();
//    }
//
//    public void load(final String url, final ImageView view) {
//      IvySdk.getCreativePath(url, new DownloadFeedback() {
//        @Override
//        public void onSuccess(final String filePath) {
//          handler.post(new Runnable() {
//            @Override
//            public void run() {
//              try {
//                view.setTag(url);
//                view.setImageBitmap(BitmapFactory.decodeFile(filePath));
//              } catch(Throwable ex) {
//                ex.printStackTrace();
//              }
//            }
//          });
//        }
//
//        @Override
//        public void onFail() {
//
//        }
//      });
//    }
//  }
//}
