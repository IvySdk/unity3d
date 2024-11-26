package com.ivy.notification;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.ivy.IvySdk;
public class NotificationPermissionUtil {

    private static final int NOTIFICATION_REQUEST_CODE = 1001;

    /**
     * 判断是否以获取权限
     *
     * @param context
     * @return
     */
    public static boolean isPermissionEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED;
        } else {
            return NotificationManagerCompat.from(context).areNotificationsEnabled();
        }
    }
    /**
     * 判断当前权限状态
     * 0: 权限被彻底拒绝，需要跳转设置页面开启
     * 1: 权限已开启
     * 2: 权限状态待定，仍可通过系统接口请求
     *
     * @param activity
     * @return
     */
    public static int getPermissionState(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查权限是否已被授予
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // 用户未授予权限，检查是否可以请求权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS)) {
                    // 可以请求权限，显示解释
                    return 2;
                } else {
                    // 用户选择了“不要再询问”或是初次请求
                    boolean state = IvySdk.mmGetBoolValue("is_first_notification", false);
                    if (!state) {
                        //初次请求，默认可使用系统接口申请权限
                        return 2;
                    }
                    return 0;
                }
            } else {
                // 权限已被授予
                return 1;
            }
        }
        return 1;
    }

    /**
     * 确保需要收到权限请求回调，以重置shouldShowRequestPermissionRationale在首次请求时一直返回false的问题
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try {
            if (requestCode == NOTIFICATION_REQUEST_CODE && permissions != null) {
            for (String permission : permissions) {
                if (permission.equals(Manifest.permission.POST_NOTIFICATIONS)) {
                    boolean state = ActivityCompat.shouldShowRequestPermissionRationale(IvySdk.getActivity(), Manifest.permission.POST_NOTIFICATIONS);
                    boolean preState = IvySdk.mmGetBoolValue("is_first_notification", false);
                    if (state && !preState) {
                        IvySdk.mmSetBoolValue("is_first_notification", true);
                    }
                }
            }
        }
        } catch (Exception e) {
        }
    }

    /**
     * 仅弹出系统权限请求弹窗
     *
     * @param activity
     */
    public static void requestPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查权限是否已被授予
            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // 用户未授予权限，检查是否可以请求权限
                ActivityCompat.requestPermissions(
                        activity,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        NOTIFICATION_REQUEST_CODE
                );
            } else {
                // 权限已被授予
            }
        } else {
            // Android 6.0 以下版本，权限自动授予
        }
    }
    /**
     * 会自动跳转设置页
     * @param activity
     */
    public static void enablePermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                // 检查权限是否已被授予
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // 用户未授予权限，检查是否可以请求权限
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.POST_NOTIFICATIONS)) {
                        // 可以请求权限，显示解释
                        ActivityCompat.requestPermissions(
                                activity,
                                new String[]{Manifest.permission.POST_NOTIFICATIONS},
                                NOTIFICATION_REQUEST_CODE
                        );
                    } else {
                        // 用户选择了“不要再询问”或是初次请求
                        boolean state = IvySdk.mmGetBoolValue("is_first_notification", true);
                        if (state){
                            //初次请求，默认可使用系统接口申请权限
                            ActivityCompat.requestPermissions(
                                    activity,
                                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                                    NOTIFICATION_REQUEST_CODE
                            );
                        } else {
                            openPermissionSetting(activity);
                        }
                    }
                } else {
                    // 权限已被授予
                }
            } else {
                boolean enabled = NotificationManagerCompat.from(activity).areNotificationsEnabled();
                if (!enabled) {
                    openPermissionSetting(activity);
                }
            }
        } else {
            // Android 6.0 以下版本，权限自动授予
        }
    }
    /**
     * 跳转设置页
     *
     * @param activity
     */
    public static void openPermissionSetting(Activity activity) {
        try {
            Intent intent = new Intent();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.getPackageName());
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", activity.getPackageName());
                intent.putExtra("app_uid", activity.getApplicationInfo().uid);
            } else {
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + activity.getPackageName()));
            }
            activity.startActivity(intent);
        } catch (Throwable t) {
        }
    }


    public static boolean isChanelClosed(Context context, String channelId) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = manager.getNotificationChannel(channelId);
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                return true;
            }
        }
        return false;
    }

    public static void openChannel(Context context, String channelId) {
        try {
            Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
            context.startActivity(intent);
        } catch (Exception e) {
//            Log.e("")
        }
    }

}