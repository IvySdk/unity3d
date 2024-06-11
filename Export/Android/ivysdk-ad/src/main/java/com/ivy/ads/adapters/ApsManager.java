package com.ivy.ads.adapters;//package com.ivy.ads.adapters;
//
//import android.app.Activity;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//
//import com.amazon.device.ads.AdRegistration;
//import com.ivy.ads.utils.ExecutorUtils;
//import com.ivy.ads.utils.GDPRSupports;
//
//public class ApsManager {
//
//    private static boolean initialized = false;
//    private static final ApsManager INSTANCE = new ApsManager();
//
//
//    public static synchronized ApsManager getInstance() {
//        return INSTANCE;
//    }
//
//    public synchronized void initialize(Activity activity) {
//        if (!initialized) {
//            boolean debug = false;
//            String apsId = null;
//            try {
//                ApplicationInfo ai = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
//                if (ai != null && ai.metaData != null) {
//                    debug = ai.metaData.getBoolean("ivy.debug", false);
//                    apsId = ai.metaData.getString("aps.id", null);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            try {
//                AdRegistration.getInstance(apsId, activity.getApplication());
//                AdRegistration.enableTesting(debug);
//                AdRegistration.enableLogging(debug);
//                if (GDPRSupports.support(activity.getApplication())) {
//                    ExecutorUtils.runExecutorService(new Runnable() {
//                        public void run() {
//                            AdRegistration.setConsentStatus(AdRegistration.ConsentStatus.EXPLICIT_YES);
//                        }
//                    });
//                }
//                initialized = true;
//            } catch (Throwable ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//
//}
