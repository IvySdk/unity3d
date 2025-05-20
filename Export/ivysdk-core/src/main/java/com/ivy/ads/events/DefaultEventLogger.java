package com.ivy.ads.events;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.android.client.AndroidSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ivy.IvySdk;
import com.ivy.networks.tracker.EventTracker;
import com.ivy.networks.tracker.EventTrackerProvider;
import com.ivy.networks.tracker.impl.AppflyersTracker;
import com.ivy.networks.tracker.impl.FacebookTracker;
import com.ivy.networks.tracker.impl.FirebaseTracker;
import com.ivy.util.Logger;
import com.tencent.mmkv.BuildConfig;
import com.tencent.mmkv.MMKV;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DefaultEventLogger extends EventTracker {
    private static final String TAG = "event";

    private static final String ID_MMKV = "ev";

    // org event --> might cause generate new events
    private final Map<String, List<String>> checkEvents = new HashMap<>();

    //     "ltv" : {
    //      "ltv_good" : {
    //        "min": 0,
    //        "max": 2,
    //        "d" : 30
    //      }
    //    },
    private MMKV mmkv = null;

    private ExecutorService executor = null;

    private JSONObject newEventSettings = null;

    private JSONObject eventTargets = null;

    private final FirebaseTracker firebaseTracker = new FirebaseTracker();

    private final AppflyersTracker appflyersTracker = new AppflyersTracker();

    private final FacebookTracker facebookTracker = new FacebookTracker();


    private Map<String, Map<Integer, String>> summaryEvents = null;

    private boolean enableAppflyers = false;

    private boolean enableFacebookLogPurchase = true;

    private long firstEventTime = 0;

    private static final long ONE_DAY = 24 * 3600 * 1000;

    private JSONObject remoteConfigEventMap;

    private Map<String, Integer> inAppConversionEvents = null;
    private String eventConversionUrl = null;

    private double adPingThreshold = 0.1f;
    private static final String KEY_ENGAGEMENT_TIME = "_et_times";

    private JSONObject eventValues = null;

    // 自动请求机器学习结果，此地址包含了机器学习的数据，游戏将根据此数据，对玩家当前的游戏进行定制
    private String aiURL = null;

    private boolean enableAIPush = false;

    private boolean enableAfAdPing = true;

    private boolean defaultOnlyFirebase = false;

    private static final String KEY_UAC_DAY3_TOP10 = "_uac_day3_top_10";

    private static final String KEY_UAC_DAY3_TOP20 = "_uac_day3_top_20";

    private static final String KEY_UAC_DAY3_TOP30 = "_uac_day3_top_30";
    private static final String KEY_UAC_TOP10 = "_uac_top_10";
    private static final String KEY_UAC_TOP20 = "_uac_top_20";
    private static final String KEY_UAC_TOP30 = "_uac_top_30";
    private static final String KEY_UAC_TOP40 = "_uac_top_40";
    private static final String KEY_UAC_TOP50 = "_uac_top_50";
    private static final String KEY_UAC_TOP60 = "_uac_top_60";
    private static final String KEY_UAC_TOP70 = "_uac_top_70";
    private static final String KEY_UAC_TOP80 = "_uac_top_80";
    private static final String KEY_UAC_TOP90 = "_uac_top_90";

    private static final String KEY_UAC_UPDATE_TIME = "_uac_update_ts";

    private static final String KEY_UAC_PREFIX_TODAY = "_uac_rev_";

    private static final String PREFIX_EVENT_UAC = "AdLtv_OneDay_Top";

    public static final String KEY_TOTAL_AD_REVENUE = "mkv_total_ad_revenue";
    private String todayKey = "";

    /**
     * key: 事件名，事件发生时，检查此hashMap,如果有匹配,将list取出，并逐一检查并触发trigger
     */
    private Map<String, List<InAppMessageTrigger>> inAppMessageTriggerMap = null;

    public DefaultEventLogger(Activity activity) {
        super(activity);
        installTrackProviders(activity);

        try {
            mmkv = MMKV.mmkvWithID(ID_MMKV);

            firstEventTime = mmkv.decodeLong("_first_event_time", 0);
            if (firstEventTime == 0) {
                firstEventTime = System.currentTimeMillis();
                mmkv.encode("_first_event_time", firstEventTime);
            }
            executor = Executors.newSingleThreadExecutor();

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            todayKey = simpleDateFormat.format(date);
        } catch (Exception ex) {
            Logger.error(TAG, "initialize event logger exception", ex);
        }
    }

    /**
     * init parameters with griddata
     *
     * @param gridData
     */
    public void setGridData(JSONObject gridData) {
        if (gridData == null) {
            Logger.error(TAG, "Grid Data is null");
            return;
        }

        defaultOnlyFirebase = gridData.optBoolean("eventDefaultOnlyFirebase", false);
        adPingThreshold = gridData.optDouble("adPingThreshold", 0.1f);
        enableFacebookLogPurchase = gridData.optBoolean("facebook.logpurchase.enable", true);

        enableAfAdPing = gridData.optBoolean("enableAfAdPing", true);
        if (gridData.has("appflyers.devkey")) {
            String appflyersDevKen = gridData.optString("appflyers.devkey", "");
            if (!"".equals(appflyersDevKen)) {
                enableAppflyers = true;
            }
        }

        // check suppress options
        boolean suppressFacebookEvent = gridData.optBoolean("suppressFacebookEvent", false);
        if (suppressFacebookEvent) {
            facebookTracker.setSuppress(true);
        }
        boolean suppressFirebaseEvent = gridData.optBoolean("suppressFirebaseEvent", false);
        if (suppressFirebaseEvent) {
            firebaseTracker.setSuppress(true);
        }
        boolean suppressAfEvent = gridData.optBoolean("suppressAfEvent", false);
        if (suppressAfEvent) {
            appflyersTracker.setSuppress(true);
        }

        if (gridData.has("event.conversion.url")) {
            this.eventConversionUrl = gridData.optString("event.conversion.url", null);
            Logger.debug(TAG, "conversion URL: " + eventConversionUrl);
        }

        if (gridData.has("ai.url")) {
            this.aiURL = gridData.optString("ai.url", null);
            Logger.debug(TAG, "ai URL: " + aiURL);
        }

        String uacTopUserAdValueApi = gridData.optString("api.top_user_advalue", "");
        if (!"".equals(uacTopUserAdValueApi)) {
            checkAndUpdateUacTop(uacTopUserAdValueApi);
        }
    }

    public void setEventTargets(JSONObject eventTargets) {
        this.eventTargets = eventTargets;
    }

//    public void overrideConfigByRemoteConfig(FirebaseRemoteConfig remoteConfig) {
//        if (remoteConfig == null) {
//            Logger.error(TAG, "RemoteConfig disabled");
//            return;
//        }
//
//        double remoteAdPingThreshold = remoteConfig.getDouble("ad_ping_threshold");
//        if (remoteAdPingThreshold > 0) {
//            this.adPingThreshold = remoteAdPingThreshold;
//        }
//
//        // inapp_message_trigger
//        String inAppMessageTriggerConfig = remoteConfig.getString("inapp_message_trigger");
//        if (!"".equals(inAppMessageTriggerConfig)) {
//            try {
//                inAppMessageTriggerMap = new HashMap<>();
//
//                JSONObject jsonObject = new JSONObject(inAppMessageTriggerConfig);
//                Iterator<String> it = jsonObject.keys();
//                while (it.hasNext()) {
//                    String triggerResult = it.next();
//                    JSONObject triggerConditionConfig = jsonObject.optJSONObject(triggerResult);
//                    Iterator<String> eventIt = triggerConditionConfig.keys();
//                    while (eventIt.hasNext()) {
//                        String orgEventName = eventIt.next();
//                        JSONObject orgEventCondition = triggerConditionConfig.optJSONObject(orgEventName);
//
//                        if (!inAppMessageTriggerMap.containsKey(orgEventName)) {
//                            inAppMessageTriggerMap.put(orgEventName, new ArrayList<>());
//                        }
//
//                        InAppMessageTrigger inAppMessageTrigger = new InAppMessageTrigger(triggerResult);
//                        inAppMessageTrigger.addConditionsFromJsonObject(orgEventCondition);
//
//                        inAppMessageTriggerMap.get(orgEventName).add(inAppMessageTrigger);
//                    }
//                }
//            } catch (Throwable t) {
//                // ignore
//            }
//        }
//
//        // 转化事件， 如果配置了inapp_conversions
//        String inAppConversions = remoteConfig.getString("inapp_conversions");
//        if (!"".equals(inAppConversions)) {
//            Logger.debug(TAG, "inAppConversions >>> " + inAppConversions);
//            try {
//                JSONArray jsonArray = new JSONArray(inAppConversions);
//                if (jsonArray.length() > 0) {
//                    inAppConversionEvents = new HashMap<>();
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        String eventName = jsonArray.optString(i);
//                        if (eventName != null && !"".equals(eventName)) {
//                            inAppConversionEvents.put(eventName, 1);
//                        }
//                    }
//                }
//            } catch (Throwable t) {
//                // ignore
//            }
//        }
//
//        // remote config event override
//        String remoteRemoteConfigEvent = remoteConfig.getString("remote_config_event");
//        if (!"".equals(remoteRemoteConfigEvent)) {
//            try {
//                remoteConfigEventMap = new JSONObject(remoteRemoteConfigEvent);
//            } catch (Throwable t) {
//                // ignore
//            }
//        }
//
//        String remoteEventValues = remoteConfig.getString("eventValues");
//        if (!"".equals(remoteEventValues) && !"{}".equals(remoteEventValues)) {
//            try {
//                eventValues = new JSONObject(remoteEventValues);
//            } catch (Throwable t) {
//                // ignore
//            }
//        }
//
//        String remoteAIUrl = remoteConfig.getString("aiURL");
//        if (!"".equals(remoteAIUrl)) {
//            this.aiURL = remoteAIUrl;
//        }
//
//        this.enableAIPush = remoteConfig.getBoolean("enableAIPush");
//    }
//
//
//    /**
//     * 检查Remote config的设置会不会触发到预测事件，如果触发到，则生成事件
//     */
//    public void checkRemoteConfigEvents(FirebaseRemoteConfig remoteConfig) {
//        try {
//            if (remoteConfigEventMap == null || remoteConfigEventMap.length() == 0 || mmkv == null) {
//                return;
//            }
//
//            Iterator<String> it = remoteConfigEventMap.keys();
//            while (it.hasNext()) {
//                String remoteConfigEventName = it.next();
//                if (remoteConfigEventName == null || "".equals(remoteConfigEventName)) {
//                    continue;
//                }
//                JSONObject remoteConfigCheckEventParams = remoteConfigEventMap.optJSONObject(remoteConfigEventName);
//                boolean alreadySent = mmkv.decodeBool("ev_" + remoteConfigEventName, false);
//                if (alreadySent) {
//                    continue;
//                }
//                Iterator<String> conditionIt = remoteConfigCheckEventParams.keys();
//                boolean remoteConditionMatched = true;
//                while (conditionIt.hasNext()) {
//                    String remoteConfigKey = conditionIt.next();
//                    int remoteConfigValue = remoteConfigCheckEventParams.optInt(remoteConfigKey, 0);
//                    if (remoteConfig.getLong(remoteConfigKey) != remoteConfigValue) {
//                        remoteConditionMatched = false;
//                        break;
//                    }
//                }
//                if (remoteConditionMatched) {
//                    logEventDirectly(remoteConfigEventName, new Bundle());
//                    mmkv.encode("ev_" + remoteConfigEventName, true);
//                }
//            }
//
//            // auto event trigger
//            String autoEventTriggerName = remoteConfig.getString("auto_event_trigger");
//            if (!"".equals(autoEventTriggerName)) {
//                // 是否仅打一次事件, 默认仅一次
//                boolean alreadySent = mmkv.decodeBool("sent_" + autoEventTriggerName, false);
//                if (!alreadySent) {
//                    logEventDirectly(autoEventTriggerName, null);
//                    mmkv.encode("sent_" + autoEventTriggerName, true);
//                }
//            }
//        } catch (Throwable t) {
//            Logger.error(TAG, "checkRemoteConfigEvents exception", t);
//        }
//    }

    public void setSummaryEventSettings(JSONObject summaryEventSettings) {
        if (summaryEventSettings == null) {
            return;
        }

        summaryEvents = new HashMap<>();
        Iterator<String> it = summaryEventSettings.keys();
        while (it.hasNext()) {
            String gen_event = it.next();
            try {
                if ("remoteconfig".equals(gen_event)) {
                    Object o = summaryEventSettings.opt(gen_event);
                    if (o instanceof JSONObject) {
                        this.remoteConfigEventMap = (JSONObject) o;
                    } else {
                        Logger.error(TAG, "Wrong remote config event configuration");
                    }
                } else {
                    Object o = summaryEventSettings.opt(gen_event);
                    if (o instanceof JSONObject) {
                        JSONObject a = (JSONObject) o;
                        if (a.has("count") && a.has("e")) {
                            String event_token = a.getString("e");
                            if (!checkEvents.containsKey(event_token)) {
                                checkEvents.put(event_token, new ArrayList<>());
                            }
                            List<String> checkNewEvents = checkEvents.get(event_token);
                            checkNewEvents.add(gen_event);

                            Map<Integer, String> countEvents = new HashMap<>();
                            JSONArray counts = a.getJSONArray("count");
                            for (int i = 0; i < counts.length(); i++) {
                                int c = counts.optInt(i);
                                countEvents.put(c, gen_event + "_" + c);
                            }
                            summaryEvents.put(event_token, countEvents);
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.error(TAG, "Check summary events settings exception", ex);
            }
        }
    }


    public void setEventHooks(final JSONObject settings) {
        if (settings == null || settings.length() == 0) {
            Logger.warning(TAG, "No gen_events settings");
            return;
        }

        this.newEventSettings = settings;

        try {
            // iterator all gen events area
            Iterator<String> keys = settings.keys();
            while (keys.hasNext()) {
                String e = keys.next();
                JSONArray configs = settings.optJSONArray(e);
                if (configs != null) {
                    for (int i = 0; i < configs.length(); i++) {
                        JSONObject rule = configs.optJSONObject(i);
                        String e1 = rule.has("e1") ? rule.optString("e1") : null;
                        String e2 = rule.has("e2") ? rule.optString("e2") : null;

                        if (e1 != null) {
                            if (!checkEvents.containsKey(e1)) {
                                checkEvents.put(e1, new ArrayList<>());
                            }
                            List<String> checkEvent = checkEvents.get(e1);
                            checkEvent.add(e);
                        }

                        if (e2 != null) {
                            if (!checkEvents.containsKey(e2)) {
                                checkEvents.put(e2, new ArrayList<>());
                            }
                            List<String> checkEvent = checkEvents.get(e2);
                            checkEvent.add(e);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            // ignore
            Logger.debug(TAG, "setEventHooks exception", t);
        }
    }

    private void installTrackProviders(Context context) {
        firebaseTracker.initialize(context);
        appflyersTracker.initialize(context);
        facebookTracker.initialize(context);
    }

    @Override
    public void logPurchase(String contentType, String contentId, String currency, float revenue) {
        if (BuildConfig.DEBUG) {
            return;
        }

        if (enableAppflyers) {
            appflyersTracker.logPurchase(contentType, contentId, currency, revenue);
        }

        if (enableFacebookLogPurchase) {
            facebookTracker.logPurchase(contentType, contentId, currency, revenue);
        }

        // record this event conversion
//        if (eventConversionUrl != null && !"".equals(eventConversionUrl)) {
//            Bundle bundle = new Bundle();
//            bundle.putString("type", contentType);
//            bundle.putString("itemid", contentId);
//            bundle.putString("currency", currency);
//            bundle.putFloat("revenue", revenue);
//            AndroidSdk.recordEventConversion(eventConversionUrl, "iap_purchased", bundle);
//        }
    }

    private void logEventDirectly(String eventType, Bundle bundle) {
        if (eventValues != null && eventValues.has(eventType)) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putDouble(FirebaseAnalytics.Param.VALUE, eventValues.optDouble(eventType, 0.0d));
        }

        firebaseTracker.logEvent(eventType, bundle);

        if (enableAppflyers) {
            appflyersTracker.logEvent(eventType, bundle);
        }

        facebookTracker.logEvent(eventType, bundle);
    }

    @Override
    public void afAdImpressionPing(Bundle bundle, double revenue) {
        if (enableAfAdPing && enableAppflyers) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putDouble("af_revenue", revenue);
            appflyersTracker.logEvent("af_ad_revenue", bundle);
        }
    }

    @Override
    public void logEvent(String eventType) {
        logEvent(eventType, null);
    }

    @Override
    public void parfkaLog(String eventId, Bundle bundle) {

    }

    @Override
    public void logToAppsflyer(String eventId, Bundle bundle) {
        if (appflyersTracker != null)
            appflyersTracker.logEvent(eventId, bundle);
    }

    public void logToFirebase(String eventId, Bundle bundle) {
        firebaseTracker.logEvent(eventId, bundle);
    }

    public void logToFacebook(String eventId, Bundle bundle) {
        if (facebookTracker != null)
            facebookTracker.logEvent(eventId, bundle);
    }

    public void logToConversation(String eventId, Bundle bundle) {
        if (firebaseTracker != null)
            firebaseTracker.logEvent(eventId, bundle);
        if (facebookTracker != null)
            facebookTracker.logEvent(eventId, bundle);
        if (appflyersTracker != null)
            appflyersTracker.logEvent(eventId, bundle);
    }

    @Override
    public void pingROAS(float revenue, String type) {
        try {
            float resultAdValue = mmkv.decodeFloat("ad_roas", 0) + revenue;
            if (resultAdValue >= adPingThreshold) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.CURRENCY, "USD");
                bundle.putFloat(FirebaseAnalytics.Param.VALUE, resultAdValue);
                logEventDirectly(EventID.ROAS_LTV_PING, bundle);
                mmkv.encode("ad_roas", 0.0f);
            } else {
                mmkv.encode("ad_roas", resultAdValue);
            }

            float totalRevenue = mmkv.decodeFloat(KEY_TOTAL_AD_REVENUE) + revenue;
            mmkv.encode(KEY_TOTAL_AD_REVENUE, totalRevenue);
            // 检查价值事件触发
            executor.submit(() -> {
                checkUacLtvConversion(revenue, totalRevenue);
                checkFirstThreeDaysLTV(totalRevenue);
            });
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public void logEvent(String eventType, Bundle bundle) {
        if (eventTargets != null && eventTargets.has(eventType)) {
            int target = eventTargets.optInt(eventType);
            if (target == 0) {
                return;
            }

            if (target == 9) {
                facebookTracker.logEvent(eventType, bundle);
                return;
            }

            if (target == 10) {
                if (enableAppflyers) {
                    appflyersTracker.logEvent(eventType, bundle);
                }
                return;
            }

            if (target == 1) {
                firebaseTracker.logEvent(eventType, bundle);
            } else if (target == 2) {
            } else if (target == 3) {
                firebaseTracker.logEvent(eventType, bundle);
                facebookTracker.logEvent(eventType, bundle);
                if (enableAppflyers) {
                    appflyersTracker.logEvent(eventType, bundle);
                }
            } else if (target == 4) {
                firebaseTracker.logEvent(eventType, bundle);
                facebookTracker.logEvent(eventType, bundle);
                if (enableAppflyers) {
                    appflyersTracker.logEvent(eventType, bundle);
                }
            } else {
                firebaseTracker.logEvent(eventType, bundle);
            }
        } else {
            // 2023/02/23   要求以下事件只打点到 appflyer clickhouse
            if (eventType.equals(EventID.CLICK_SHOW_BANNER) ||
                    eventType.equals(EventID.CLICK_SHOW_REWARDED) ||
                    eventType.equals(EventID.CLICK_SHOW_INTERSTITIAL) ||
                    eventType.equals(EventID.BANNER_DISPLAYED) ||
                    eventType.equals(EventID.INTERSTITIAL_SHOWN) ||
                    eventType.equals(EventID.VIDEO_SHOWN) ||
                    eventType.equals(EventID.VIDEO_COMPLETED)) {
                if (enableAppflyers) {
                    appflyersTracker.logEvent(eventType, bundle);
                }
                firebaseTracker.logEvent(eventType, bundle);
            } else {
                firebaseTracker.logEvent(eventType, bundle);
                if (!defaultOnlyFirebase) {
                    if (enableAppflyers) {
                        appflyersTracker.logEvent(eventType, bundle);
                    }
                    facebookTracker.logEvent(eventType, bundle);
                }
            }
        }
        checkEvents(eventType, bundle);
    }

    private void checkEvents(String eventType, Bundle bundle) {
        checkInAppMessageTriggger(eventType, bundle);

        checkConversionEvent(eventType, bundle);

        if (executor != null) {
            if (mmkv != null && checkEvents.containsKey(eventType)) {
                try {
                    executor.submit(() -> {
                        try {
                            int eventCount = mmkv.decodeInt(eventType, 0) + 1;
                            mmkv.encode(eventType, eventCount);

                            List<String> checkNewEvent = checkEvents.get(eventType);
                            if (checkNewEvent != null) {
                                for (String newEvent : checkNewEvent) {
                                    // check the gen event settings
                                    if (newEventSettings.has(newEvent)) {
                                        boolean alreadySent = mmkv.decodeBool("ev_" + newEvent, false);
                                        if (!alreadySent) {
                                            JSONArray eventSettings = newEventSettings.optJSONArray(newEvent);
                                            if (checkAllRules(eventSettings, newEvent)) {
                                                // fire this event
                                                logEventDirectly(newEvent, new Bundle());

                                                // if r : true, we will reset this event count to 0
                                                // only one condition and contains r
                                                if (eventSettings.length() == 1) {
                                                    JSONObject firstCondition = eventSettings.optJSONObject(0);
                                                    if (firstCondition != null && firstCondition.optBoolean("r", false)) {
                                                        // increase times
                                                        int times = mmkv.decodeInt(newEvent + "_times", 1) + 1;
                                                        mmkv.encode(newEvent + "_times", times);
                                                    } else {
                                                        mmkv.encode("ev_" + newEvent, true);
                                                    }
                                                } else {
                                                    mmkv.encode("ev_" + newEvent, true);
                                                }
                                            }
                                        }
                                    }
                                }

                                // check summary event
                                // check the summary events settings
                                if (summaryEvents != null && summaryEvents.containsKey(eventType)) {
                                    Map<Integer, String> summaryEvent = summaryEvents.get(eventType);
                                    if (summaryEvent != null && summaryEvent.containsKey(eventCount)) {
                                        String newEvent = summaryEvent.get(eventCount);
                                        String eventKey = "ev_" + newEvent + eventCount;
                                        boolean alreadySent = mmkv.decodeBool(eventKey, false);
                                        if (!alreadySent) {
                                            logEventDirectly(newEvent, new Bundle());
                                            mmkv.encode(eventKey, true);
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private boolean checkAllRules(JSONArray conditions, String genEvent) {
        if (conditions == null) {
            return false;
        }

        for (int i = 0; i < conditions.length(); i++) {
            JSONObject condition = conditions.optJSONObject(i);

            String event1 = condition.has("e1") ? condition.optString("e1") : null;
            String event2 = condition.has("e2") ? condition.optString("e2") : null;
            String op = condition.has("op") ? condition.optString("op") : null;
            double value = condition.has("v") ? condition.optDouble("v") : 0;

            if (condition.has("r")) {
                int times = mmkv.decodeInt(genEvent + "_times", 1);
                value = value * times;
            }

            int durationDays = condition.has("d") ? condition.optInt("d") : 0;

            if (!checkRule(event1, event2, op, value, durationDays)) {
                return false;
            }
        }

        Logger.debug(TAG, "conditions matched, generate new event: " + genEvent);
        return true;
    }

    /**
     * 检查事件是否满足条件, 如满足，返回true, 不满足返回false
     * <p>
     * event1/event2: open_times, installed_days, 事件名
     * op: 默认>=, 支持>,<,=,/,*
     * value: float 要比较的值
     *
     * @param event1
     * @param event2 null or 事件名
     * @param op     操作符
     * @param value
     * @return
     */
    private boolean checkRule(String event1, String event2, String op, double value, int durationDays) {
        int eventCount1 = mmkv.decodeInt(event1, 0);
        // 1. 如果没有值，直接返回false, 规则不匹配
        if (eventCount1 == 0) {
            return false;
        }

        // if current time - first open time > durationDays, just skip
        if (durationDays > 0) {
            if (System.currentTimeMillis() - firstEventTime > durationDays * ONE_DAY) {
                return false;
            }
        }

        // 单值运算
        if (event2 == null) {
            if (op == null || ">=".equals(op)) {
                return eventCount1 >= value;
            } else if ("<".equals(op)) {
                return eventCount1 < value;
            } else if (">".equals(op)) {
                return eventCount1 > value;
            } else if ("=".equals(op)) {
                return eventCount1 == (int) value;
            }
            return false;
        }

        // 双值运算
        int eventCount2 = mmkv.decodeInt(event2, 0);
        // 双值运算中，第二个事件，没有数据，直接返回false,规则不匹配
        if (eventCount2 == 0) {
            return false;
        }

        double v1 = eventCount1 * 1.0 / eventCount2;
        if (op == null || ">=".equals(op)) {
            return v1 >= value;
        } else if ("<".equals(op)) {
            return v1 < value;
        } else if (">".equals(op)) {
            return v1 > value;
        } else if ("=".equals(op)) {
            return Double.compare(v1, value) == 0;
        }

        return false;
    }

    public void destroy() {
        try {
            if (executor != null) {
                executor.shutdownNow();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void trackEngagement(long seconds) {
        if (mmkv == null) {
            return;
        }
        long totalTimes = mmkv.decodeLong(KEY_ENGAGEMENT_TIME, 0);
        mmkv.encode(KEY_ENGAGEMENT_TIME, totalTimes + seconds);

        Bundle bundle = new Bundle();
        bundle.putInt("times", IvySdk.appStartTimes);
        bundle.putLong("value", seconds);
        parfkaLog("track_engagement", bundle);
    }

    @Override
    public void setUserProperty(String key, String value) {
        if (key == null || value == null) {
            return;
        }

        if ("af_customer_user_id".equals(key)) {
            Logger.debug(TAG, "Set UserID >>> " + value);
            if (enableAppflyers) {
                appflyersTracker.setUserID(value);
            }
            firebaseTracker.setUserID(value);
            return;
        }

        if ("firebase_userId".equals(key)) {
            firebaseTracker.setUserID(value);
            return;
        }

        firebaseTracker.setUserProperty(key, value);
    }

    /**
     * 用户触发应用内消息的条件对象
     */
    private static class InAppMessageTrigger {
        // 触发的条件
        private Map<String, Object> conditions;
        private final String inAppEventName;

        public InAppMessageTrigger(String inAppEventName) {
            this.inAppEventName = inAppEventName;
        }

        public void addConditionsFromJsonObject(JSONObject json) {
            try {
                if (json.length() == 0) {
                    return;
                }
                conditions = new HashMap<>();
                Iterator<String> it = json.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    Object value = json.opt(key);
                    if (value != null) {
                        conditions.put(key, value);
                    }
                }
            } catch (Throwable t) {
                // ignore
            }
        }

        private void doTrigger() {
            Logger.debug(TAG, "InAppMessage condition matched, event fired: " + inAppEventName);
//            IvySdk.triggerInAppMessage(inAppEventName);
        }

        /**
         * 检查事件的bundle是否匹配条件，如果匹配，则触发，否则返回
         *
         * @param bundle
         */
        public void checkTrigger(Bundle bundle) {
            // 不需要条件，说明此事件无条件触发
            if (conditions == null || conditions.size() == 0) {
                doTrigger();
                return;
            }
            // 需要条件触发，但bundle为空，不满足条件，不能触发事件
            if (bundle == null || bundle.size() == 0) {
                return;
            }
            // 逐一检查条件Map, 如果不满足，自动返回
            for (Map.Entry<String, Object> entry : conditions.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (!bundle.containsKey(key) || !value.equals(bundle.get(key))) {
                    return;
                }
            }
            // 条件满足，可以触发
            doTrigger();
        }
    }

    public void trackConversion(String eventName, Bundle bundle) {
        if (this.eventConversionUrl == null || "".equals(this.eventConversionUrl)) {
            return;
        }

        Logger.debug(TAG, "trackConversion >>> " + eventName);
        // 发生转化事件
//        try {
//            AndroidSdk.recordEventConversion(this.eventConversionUrl, eventName, bundle);
//        } catch (Throwable t) {
//            // ignore
//        }
    }

    /**
     * 游戏内转化事件回调，当游戏内记录特殊的转化事件时，会自动将事件与基本事件数据信息传入
     *
     * @param eventName 事件名
     * @param bundle    事件参数
     */
    private void checkConversionEvent(String eventName, Bundle bundle) {
        if (inAppConversionEvents == null || this.eventConversionUrl == null || "".equals(this.eventConversionUrl) || !inAppConversionEvents.containsKey(eventName)) {
            return;
        }
        // 发生转化事件
//        try {
//            AndroidSdk.recordEventConversion(this.eventConversionUrl, eventName, bundle);
//        } catch (Throwable t) {
//            // ignore
//        }
    }

    private void checkInAppMessageTriggger(String eventName, Bundle bundle) {
        if (inAppMessageTriggerMap == null || inAppMessageTriggerMap.size() == 0 || !inAppMessageTriggerMap.containsKey(eventName)) {
            return;
        }
        List<InAppMessageTrigger> inAppMessageTriggers = inAppMessageTriggerMap.get(eventName);
        if (inAppMessageTriggers != null) {
            for (InAppMessageTrigger inAppMessageTrigger : inAppMessageTriggers) {
                inAppMessageTrigger.checkTrigger(bundle);
            }
        }
    }

    @Override
    public void onResume() {
//        try {
//            if (this.enableAIPush && this.aiURL != null && executor != null) {
//                executor.submit(() -> {
//                    try {
//                        Logger.debug(TAG, "onResume, track aiURL ");
//                        AndroidSdk.recordEventConversion(aiURL, "ai", null);
//                    } catch (Throwable t) {
//                        t.printStackTrace();
//                    }
//                });
//            }
//        } catch (Throwable t) {
//            // ignore
//            t.printStackTrace();
//        }
    }

    @Override
    public long getEngagementTimestamp() {
        try {
            return mmkv != null ? mmkv.decodeLong(KEY_ENGAGEMENT_TIME, 0) : 0;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onPause() {
    }

    @Override
    public EventTrackerProvider getEventTracker(String provider) {
        if ("facebook".equals(provider)) {
            return facebookTracker;
        }  else if ("firebase".equals(provider)) {
            return firebaseTracker;
        } else if ("appsflyer".equals(provider)) {
            return appflyersTracker;
        }
        return null;
    }

    /**
     * 从服务器获取最新的转化数据。可以保持每2小时的API请求频率，
     * 此函数在应用启动时，调用，如果最后一次获取正常的数据时间未超过2小时，直接使用上次的数据
     */
    private void checkAndUpdateUacTop(String apiUrl) {
        long lastCheckTs = mmkv.decodeLong(KEY_UAC_UPDATE_TIME, 0L);
        if (System.currentTimeMillis() - lastCheckTs < 2 * 3600 * 1000L) {
            return;
        }

        OkHttpClient okHttpClient = IvySdk.getOkHttpClient();
        String appId = AndroidSdk.getConfig(AndroidSdk.CONFIG_KEY_APP_ID);
        String packageName = AndroidSdk.getConfig(AndroidSdk.CONFIG_KEY_PACKAGE_NAME);

        Request request = new Request.Builder()
                .url(apiUrl + "?appId=" + appId + "&packageName=" + packageName)
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Logger.error(TAG, "checkAndUpdateUacTop exception", e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                Logger.debug(TAG, "checkAndUpdateUacTop result");
                try {
                    String data = response.body().string();
                    JSONObject result = new JSONObject(data);
                    String status = result.getString("status");
                    if (!"success".equals(status)) {
                        return;
                    }
                    JSONObject topData = result.optJSONObject("data");
                    if (topData != null) {
                        double top10 = topData.optDouble("t10");
                        double top20 = topData.optDouble("t20");
                        double top30 = topData.optDouble("t30");
                        double top40 = topData.optDouble("t40");
                        double top50 = topData.optDouble("t50");
                        double top60 = topData.optDouble("t60");
                        double top70 = topData.optDouble("t70");
                        double top80 = topData.optDouble("t80");
                        double top90 = topData.optDouble("t90");

                        mmkv.encode(KEY_UAC_TOP10, top10);
                        mmkv.encode(KEY_UAC_TOP20, top20);
                        mmkv.encode(KEY_UAC_TOP30, top30);
                        mmkv.encode(KEY_UAC_TOP40, top40);
                        mmkv.encode(KEY_UAC_TOP50, top50);
                        mmkv.encode(KEY_UAC_TOP60, top60);
                        mmkv.encode(KEY_UAC_TOP70, top70);
                        mmkv.encode(KEY_UAC_TOP80, top80);
                        mmkv.encode(KEY_UAC_TOP90, top90);

                        mmkv.encode(KEY_UAC_UPDATE_TIME, System.currentTimeMillis());
                    }

                    JSONObject top3Data = result.optJSONObject("day3_data");
                    if (top3Data != null){
                        double day3Top30 = top3Data.optDouble("t30");
                        double day3Top20 = top3Data.optDouble("t20");
                        double day3Top10 = top3Data.optDouble("t10");
                        mmkv.encode(KEY_UAC_DAY3_TOP10, day3Top10);
                        mmkv.encode(KEY_UAC_DAY3_TOP20, day3Top20);
                        mmkv.encode(KEY_UAC_DAY3_TOP30, day3Top30);
                    }
                } catch (Throwable t) {
                    Logger.error(TAG, "checkAndUpdateUacTop exception", t);
                }
            }
        });
    }

    private void logUacConversionEvent(int top, double dayRevenue, double totalRevenue) {
        String sendFlagKey = KEY_UAC_PREFIX_TODAY + todayKey + top + "_gen";
        boolean alreadySent = mmkv.decodeBool(sendFlagKey, false);
        if (alreadySent) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putDouble("total_revenue", totalRevenue);
        bundle.putDouble("day_revenue", dayRevenue);
        bundle.putString("label", todayKey);
        bundle.putInt(EventParams.PARAM_CATALOG, top);

        String eventName = PREFIX_EVENT_UAC + top;
        firebaseTracker.logEvent(eventName, bundle);

        facebookTracker.logEvent(eventName, bundle);
        appflyersTracker.logEvent(eventName, bundle);
        mmkv.encode(sendFlagKey, true);
    }

    private void checkFirstThreeDaysLTV(float totalRevenue){
        long duration = System.currentTimeMillis() - IvySdk.firstAppStartTime;
        if (duration <= 0) return;
        int hours = (int) (duration / 1000 / 60 / 60);
        if (hours > 72) return;
        double day3Top30 = mmkv.decodeDouble(KEY_UAC_DAY3_TOP30, 0.0);
        if (day3Top30 > 0){
            if (totalRevenue >= day3Top30){
                logFirstThreeDaysLTV("30", totalRevenue, hours);
            } else {
                return;
            }
        }
        double day3Top20 = mmkv.decodeDouble(KEY_UAC_DAY3_TOP20, 0.0);
        if (day3Top20 > 0){
            if (totalRevenue >= day3Top20){
                logFirstThreeDaysLTV("20", totalRevenue, hours);
            } else {
                return;
            }
        }
        double day3Top10 = mmkv.decodeDouble(KEY_UAC_DAY3_TOP10, 0.0);
        if (day3Top10 > 0){
            if (totalRevenue >= day3Top10){
                logFirstThreeDaysLTV("10", totalRevenue, hours);
            } else {
                return;
            }
        }
    }

    private void logFirstThreeDaysLTV(String tag, float totalRevenue, int hours){
        String sendFlag = "AdLtv_day3_top" + tag + "_gen";
        boolean alreadySent = mmkv.decodeBool(sendFlag, false);
        if (alreadySent) return;
        Bundle bundle = new Bundle();
        bundle.putDouble("total_revenue", totalRevenue);
        bundle.putInt("hours", hours);
        bundle.putString(EventParams.PARAM_CATALOG, "day3");
        String eventName = "AdLtv_day3_top" + tag;
        firebaseTracker.logEvent(eventName, bundle);
        mmkv.encode(sendFlag, true);
    }

    /**
     * 获取当前日期(UTC+0)并将广告收入累积到当日的KEY上
     * 1. 检查日是否触发topN，逐一生成对应事件到firebase和af
     *
     * @param revenue
     */
    private void checkUacLtvConversion(float revenue, float totalRevenue) {
        double todayRevenue = mmkv.decodeDouble(KEY_UAC_PREFIX_TODAY + todayKey, 0) + revenue;
        mmkv.encode(KEY_UAC_PREFIX_TODAY + todayKey, todayRevenue);

        // 检查是否生成adLtv事件
        double top90 = mmkv.decodeDouble(KEY_UAC_TOP90, 0.0);
        if (top90 > 0) {
            if (todayRevenue >= top90) {
                logUacConversionEvent(90, todayRevenue, totalRevenue);
            } else {
                return;
            }
        }

        double top80 = mmkv.decodeDouble(KEY_UAC_TOP80, 0.0);
        if (top80 > 0) {
            if (todayRevenue >= top80) {
                logUacConversionEvent(80, todayRevenue, totalRevenue);
            } else {
                return;
            }
        }

        double top70 = mmkv.decodeDouble(KEY_UAC_TOP70, 0.0);
        if (top70 > 0) {
            if (todayRevenue >= top70) {
                logUacConversionEvent(70, todayRevenue, totalRevenue);
            } else {
                return;
            }
        }

        double top60 = mmkv.decodeDouble(KEY_UAC_TOP60, 0.0);
        if (top60 > 0) {
            if (todayRevenue >= top60) {
                logUacConversionEvent(60, todayRevenue, totalRevenue);
            } else {
                return;
            }
        }

        double top50 = mmkv.decodeDouble(KEY_UAC_TOP50, 0.0);
        if (top50 > 0) {
            if (todayRevenue >= top50) {
                logUacConversionEvent(50, todayRevenue, totalRevenue);
            } else {
                return;
            }
        }

        double top40 = mmkv.decodeDouble(KEY_UAC_TOP40, 0.0);
        if (top40 > 0) {
            if (todayRevenue >= top40) {
                logUacConversionEvent(40, todayRevenue, totalRevenue);
            } else {
                return;
            }
        }

        double top30 = mmkv.decodeDouble(KEY_UAC_TOP30, 0.0);
        if (top30 > 0) {
            if (todayRevenue >= top30) {
                logUacConversionEvent(30, todayRevenue, totalRevenue);
            } else {
                return;
            }
        }

        double top20 = mmkv.decodeDouble(KEY_UAC_TOP20, 0.0);
        if (top20 > 0) {
            if (todayRevenue >= top20) {
                logUacConversionEvent(20, todayRevenue, totalRevenue);
            } else {
                return;
            }
        }

        double top10 = mmkv.decodeDouble(KEY_UAC_TOP10, 0.0);
        if (top10 > 0) {
            if (todayRevenue >= top10) {
                logUacConversionEvent(10, todayRevenue, totalRevenue);
            }
        }
    }


}
