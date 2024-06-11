package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.util.Log;
//
//import com.google.android.gms.ads.identifier.AdvertisingIdClient;
//import com.my.target.common.MyTargetConfig;
//import com.my.target.common.MyTargetManager;
//
//import java.io.IOException;
//
//public class MytargetManager {
//
//    private static final String TAG = "MytargetManager";
//    private static boolean initialized = false;
//    private static final MytargetManager INSTANCE = new MytargetManager();
//
//
//    public static synchronized MytargetManager getInstance() {
//        return INSTANCE;
//    }
//
//    public synchronized void initialize(Activity activity) {
//        if (!initialized) {
//            boolean debug = false;
//            try {
//                try {
//                    ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
//                    if (ai != null && ai.metaData != null) {
//                        debug = ai.metaData.getBoolean("ivy.debug", false);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                MyTargetConfig config = new MyTargetConfig.Builder().withTestDevices("2a627eee-b62b-4515-a135-339c58393022").build();
//                MyTargetManager.setSdkConfig(config);
//                MyTargetManager.setDebugMode(debug);
//                MyTargetManager.initSdk(activity.getApplication());
//                initialized = true;
//
//               new Thread(new Runnable(){
//                   @Override
//                   public void run() {
//                       getGAID(activity);
//                   }
//               }).start();
//
//            } catch (Throwable ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//
//    public static String getGAID(Context context){
//        String gaid= "";
//        AdvertisingIdClient.Info adInfo = null ;
//        try {
//            adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
//        } catch (Exception e) {
//            Log.e("parfka", "Exception:"+e.toString());
//            // Encountered a recoverable error connecting to Google Play services.
//        }
//        if (adInfo!= null){
//            gaid= adInfo.getId();
//            Log.e("parfka", "gaid:"+gaid);
//        }
//        return gaid;
//    }
//
//
//}
