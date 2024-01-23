//package com.android.client;
//
//import android.app.PendingIntent;
//import android.content.Context;
//
//import java.util.Date;
//
//public class ClockMaster {
//  public static void startClock(Context context, Date date, PendingIntent pi) {
////    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
////    if (alarmManager == null) {
////      return;
////    }
////    long nextTime = date.getTime();
////    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////      AlarmManager.AlarmClockInfo ai = new AlarmManager.AlarmClockInfo(nextTime, pi);
////      alarmManager.setAlarmClock(ai, pi);
////    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
////      alarmManager.setExact(AlarmManager.RTC_WAKEUP, nextTime, pi);
////    } else {
////      alarmManager.set(AlarmManager.RTC_WAKEUP, nextTime, pi);
////    }
//  }
//
//  public static void cancelClock(Context context, PendingIntent pi) {
////    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
////    if (alarmManager != null) {
////      alarmManager.cancel(pi);
////    }
//  }
//}
