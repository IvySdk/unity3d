//package com.cocos.game;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Handler;
//import android.os.Looper;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.android.client.AdEventListener;
//import com.android.client.AdListener;
//import com.android.client.AdLoadedListener;
//import com.android.client.AndroidSdk;
//import com.android.client.DatabaseChangedListener;
//import com.android.client.DatabaseConnectListener;
//import com.android.client.DatabaseListener;
//import com.android.client.GoogleListener;
//import com.android.client.PaymentSystemListener;
//import com.android.client.SKUDetail;
//import com.android.client.SdkResultListener;
//import com.android.client.UrlListener;
//import com.android.client.UserCenterListener;
//import com.cocos.lib.JsbBridgeWrapper;
//import com.ivy.IvySdk;
//import com.ivy.ads.interfaces.IvyAdType;
//import com.ivy.billing.impl.IPurchaseQueryCallback;
//import com.ivy.firestore.FirestoreAdapter;
//import com.ivy.util.Logger;
//
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.lang.ref.WeakReference;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//public class CocosCreator {
//
//    private static final String TAG = CocosCreator.class.getSimpleName();
//
//    public static final String TRUE = "0";
//    public static final String FALSE = "1";
//    static WeakReference<Activity> activityWeakReference;
//    static Handler handler;
//
//    private static void unbindJsEvents() {
//        JsbBridgeWrapper.getInstance().removeAllListeners();
//    }
//
//    private static void bindJsEvents() {
//        JsbBridgeWrapper.getInstance().removeAllListeners();
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onAdEvent, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tLoag("onAdEvent:" + arg);
//                        onAdEvent(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onPaymentEvent, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tLoag("onPaymentEvent:" + arg);
//                        onPaymentEvent(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onTrackEvent, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tLoag("onTrackEvent:" + arg);
//                        onTrackEvent(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onLoginGoogleEvent, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tLoag("onLoginGoogleEvent:" + arg);
//                        onLoginGoogleEvent(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onLeaderBoardEvent, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tLoag("onLeaderBoardEvent:" + arg);
//                        onLeaderBoardEvent(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onAchievementEvent, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tLoag("onAchievementEvent:" + arg);
//                        onAchievementEvent(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onFacebookUserEvent, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tLoag("onFacebookUserEvent:" + arg);
//                        onFacebookUserEvent(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onFirestoreEvent, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        toast("onFirestoreEvent:" + arg);
//                        onFirestoreEvent(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onRemoteConfig, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        tLoag("onRemoteConfig:" + arg);
//                        onRemoteConfig(arg);
//                    }
//                });
//            }
//        });
//        JsbBridgeWrapper.getInstance().addScriptEventListener(CocosCreatorEvents.onOther, new JsbBridgeWrapper.OnScriptEventListener() {
//            @Override
//            public void onScriptEvent(final String arg) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        onOther(arg);
//                    }
//                });
//            }
//        });
//    }
//
//    private static void sendToJs(String event, String data) {
//        try {
//            JsbBridgeWrapper.getInstance().dispatchEventToScript(event, data);
//            Log.e("send to js ", "event:" + event + "  data:" + data);
//        } catch (Exception e) {
//            Logger.error("send " + event + " to js error:" + e.getMessage());
//        }
//    }
//
//    private static void onAdEvent(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("showFullAd".equals(action)) {
//                String tag = data.optString("tag", "default");
//                showFullAd(tag);
//            } else if ("showRewardedAd".equals(action)) {
//                int id = data.optInt("id", 1);
//                String tag = data.optString("tag", "default");
//                showRewardAd(tag, id);
//            } else if ("hasRewardedAd".equals(action)) {
//                boolean hasRewardAd = hasRewardAd();
//                data.put("value", hasRewardAd ? TRUE : FALSE);
//                sendToJs(CocosCreatorEvents.onAdEvent, data.toString());
//            } else if ("showBannerAd".equals(action)) {
//                int position = data.optInt("position", 4);
//                showBanner(position);
//            } else if ("closeBannerAd".equals(action)) {
//                closeBanner();
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onPaymentEvent(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action");
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("pay".equals(action)) {
//                int billId = data.optInt("billId", -1);
//                String payload = data.optString("payload");
//                if (TextUtils.isEmpty(payload)) {
//                    pay(billId);
//                } else {
//                    pay(billId, payload);
//                }
//            } else if ("query".equals(action)) {
//                int billId = data.optInt("billId", -1);
//                query(billId);
//            } else if ("PurchaseHistory".equals(action)) {
//                String skuType = data.optString("skuType");
//                getPurchaseHistory(skuType);
//            } else if ("PaymentDataAsyn".equals(action)) {
//                int billId = data.optInt("billId", -1);
//                getPaymentDataAsyn(billId);
//            } else if ("PaymentData".equals(action)) {
//                int billId = data.optInt("billId", -1);
//                String billIdData = getPaymentData(billId);
//                data.put("value", billIdData);
//                sendToJs(CocosCreatorEvents.onPaymentEvent, data.toString());
//            } else if ("PaymentDatas".equals(action)) {
//                String paymentDatas = getPaymentDatas();
//                data.put("value", paymentDatas);
//                sendToJs(CocosCreatorEvents.onPaymentEvent, data.toString());
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onTrackEvent(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("UserProperty".equals(action)) {
//                String key = data.optString("key");
//                String value = data.optString("value");
//                setUserProperty(key, value);
//            } else {
//                int type = data.optInt("type", -1);
//                if (type == 1) {
//                    String category = data.optString("category");
//                    String _action = data.optString("action");
//                    String label = data.optString("label");
//                    int value = data.optInt("value");
//                    trackEvent(category, _action, label, value);
//                } else if (type == 2) {
//                    String eventName = data.optString("eventName");
//                    String _data = data.optString("data");
//                    trackEvent(eventName, _data);
//                } else {
//                    Logger.error("invalid event type:" + type);
//                }
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onLoginGoogleEvent(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("silentLoginGoogle".equals(action)) {
//                silentLoginGoogle();
//            } else if ("loginGoogle".equals(action)) {
//                loginGoogle();
//            } else if ("isGoogleLogin".equals(action)) {
//                boolean isLogin = isGoogleLogin();
//                data.put("value", isLogin ? TRUE : FALSE);
//                sendToJs(CocosCreatorEvents.onLoginGoogleEvent, data.toString());
//            } else if ("logoutGoogle".equals(action)) {
//                logoutGoogle();
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onLeaderBoardEvent(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("updateGoogleLeaderBoard".equals(action)) {
//                String id = data.optString("id");
//                long score = data.optLong("score", 0);
//                updateGoogleLeaderBoard(id, score);
//            } else if ("ShowGoogleLeaderBoard".equals(action)) {
//                String id = data.optString("id");
//                if (TextUtils.isEmpty(id)) {
//                    showGoogleLeaderBoards();
//                } else {
//                    showGoogleLeaderBoard(id);
//                }
//            } else if ("ShowGoogleLeaderBoards".equals(action)) {
//                showGoogleLeaderBoards();
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onAchievementEvent(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (action == null) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("updateGoogleAchievement".equals(action)) {
//                String id = data.optString("id");
//                int step = data.optInt("score", 0);
//                updateGoogleAchievement(id, step);
//            } else if ("showGoogleAchievements".equals(action)) {
//                showGoogleAchievements();
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onFacebookUserEvent(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("loginFacebook".equals(action)) {
//                login();
//            } else if ("logoutFacebook".equals(action)) {
//                logout();
//            } else if ("isloginFacebook".equals(action)) {
//                boolean isLogin = isLogin();
//                data.put("value", isLogin ? TRUE : FALSE);
//                sendToJs(CocosCreatorEvents.onFacebookUserEvent, data.toString());
//            } else if ("facebookUserData".equals(action)) {
//                String _data = me();
//                data.put("value", _data);
//                sendToJs(CocosCreatorEvents.onFacebookUserEvent, data.toString());
//            } else if ("facebookFriends".equals(action)) {
//                String friends = friends();
//                if (friends == null) {
//                    data.put("value", "");
//                    sendToJs(CocosCreatorEvents.onFacebookUserEvent, data.toString());
//                }
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onFirestoreEvent(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("loginFirestore".equals(action)) {
//                String provider = data.optString("provider");
//                initFirestoreAfterSignIn(provider);
//            } else if ("logoutFirestore".equals(action)) {
//                signOutFirestore();
//            } else if ("setFirestore".equals(action)) {
//                String collection = data.optString("collection");
//                String _data = data.optString("data");
//                setFirestore(collection, _data);
//            } else if ("readFirestore".equals(action)) {
//                String collection = data.optString("collection");
//                String documentId = data.optString("documentId");
//                if (TextUtils.isEmpty(documentId)) {
//                    readFirestore(collection);
//                } else {
//                    readFirestore(collection, documentId);
//                }
//            } else if ("updateFirestore".equals(action)) {
//                String collection = data.optString("collection");
//                String transactionId = data.optString("transactionId");
//                String _data = data.optString("data");
//                updateFirestore(collection, transactionId, _data);
//            } else if ("mergeFirestore".equals(action)) {
//                String collection = data.optString("collection");
//                String _data = data.optString("data");
//                mergeFirestore(collection, _data);
//            } else if ("queryFirestore".equals(action)) {
//                String collection = data.optString("collection");
//                queryFirestore(collection);
//            } else if ("deleteFirestore".equals(action)) {
//                String collection = data.optString("collection");
//                deleteFirestore(collection);
//            } else if ("snapshotFirestore".equals(action)) {
//                String collection = data.optString("collection");
//                String documentId = data.optString("documentId");
//                if (TextUtils.isEmpty(documentId)) {
//                    snapshotFirestore(collection);
//                } else {
//                    snapshotFirestore(collection, documentId);
//                }
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onRemoteConfig(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            String key = data.optString("key");
//            if (TextUtils.isEmpty(key)) {
//                throw new IllegalArgumentException("invalid remote config key");
//            }
//            if ("RemoteConfigInt".equals(action)) {
//                int value = getRemoteConfigInt(key);
//                data.put("value", value);
//            } else if ("RemoteConfigLong".equals(action)) {
//                long value = getRemoteConfigLong(key);
//                data.put("value", value);
//            } else if ("RemoteConfigDouble".equals(action)) {
//                double value = getRemoteConfigDouble(key);
//                data.put("value", value);
//            } else if ("RemoteConfigBoolean".equals(action)) {
//                boolean value = getRemoteConfigBoolean(key);
//                data.put("value", value ? TRUE : FALSE);
//            } else if ("RemoteConfigString".equals(action)) {
//                String value = getRemoteConfigString(key);
//                data.put("value", value);
//            }
//            sendToJs(CocosCreatorEvents.onRemoteConfig, data.toString());
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    private static void onOther(String info) {
//        try {
//            JSONObject data = new JSONObject(info);
//            String action = data.optString("action", null);
//            if (TextUtils.isEmpty(action)) {
//                throw new IllegalArgumentException("invalid action");
//            }
//            if ("cacheurl".equals(action)) {
//                tLoag("onOther:" + info);
//                String url = data.optString("url");
//                if (!TextUtils.isEmpty(url)) {
//                    cacheUrl(url);
//                }
//            } else if ("share".equals(action)) {
//                tLoag("onOther:" + info);
//                share();
//            } else if ("rate".equals(action)) {
//                tLoag("onOther:" + info);
//                int star = data.optInt("star", 5);
//                rate(star);
//            } else if ("exitGame".equals(action)) {
//                tLoag("onOther:" + info);
//                onQuit();
//            } else if ("toast".equals(action)) {
//                String msg = data.optString("msg");
//                toast(msg);
//            }
//        } catch (JSONException e) {
//            Logger.error("bad json:" + info + " err:" + e.getMessage());
//        } catch (Exception e1) {
//            Logger.error("onAdEvent error:" + e1.getMessage());
//        }
//    }
//
//    public static void getPurchaseHistory(String skuType) {
//        AndroidSdk.getPurchaseHistory(skuType, new IPurchaseQueryCallback<String>() {
//            @Override
//            public void onResult(String data) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onPaymentEvent);
//                    object.put("action", "PurchaseHistory");
//                    object.put("skuType", skuType);
//                    object.put("value", data);
//                    sendToJs(CocosCreatorEvents.onPaymentEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static void getPaymentDataAsyn(int bill) {
//        SKUDetail skuDetail = AndroidSdk.getSKUDetail(bill);
//        try {
//            JSONObject object = new JSONObject();
//            object.put("event", CocosCreatorEvents.onPaymentEvent);
//            object.put("action", "PaymentDataAsyn");
//            object.put("billId", bill);
//            object.put("value", (skuDetail == null ? "{}" : skuDetail.toJson().toString()));
//            sendToJs(CocosCreatorEvents.onPaymentEvent, object.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void signOutFirestore() {
//        FirestoreAdapter.getInstance().signOutFirestore();
//    }
//
//    public static void initFirestoreAfterSignIn(String provider) {
//        FirestoreAdapter.getInstance().initializeAfterSignIn(provider, new DatabaseConnectListener() {
//            @Override
//            public void onSuccess() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "loginFirestore");
//                    object.put("provider", provider);
//                    object.put("value", TRUE);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFail() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "loginFirestore");
//                    object.put("provider", provider);
//                    object.put("value", FALSE);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAccountLinkFail() {
//                //  sendMessage("onFirestoreLinkError", "");
//            }
//        });
//
//    }
//
//    public static void readFirestore(String collection, String documentId) {
//        FirestoreAdapter.getInstance().read(collection, documentId, new DatabaseListener() {
//            @Override
//            public void onData(String collection, String data) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "readFirestore");
//                    object.put("collection", collection);
//                    object.put("documentId", documentId);
//                    object.put("value", data);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onSuccess(String collection) {
//                // not use
//            }
//
//            @Override
//            public void onFail(String collection) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "readFirestore");
//                    object.put("collection", collection);
//                    object.put("documentId", documentId);
//                    object.put("value", "{}");
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//    public static void readFirestore(String collection) {
//        FirestoreAdapter.getInstance().read(collection, new DatabaseListener() {
//            @Override
//            public void onData(String collection, String data) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "readFirestore");
//                    object.put("collection", collection);
//                    object.put("value", data);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onSuccess(String collection) {
//                // not use
//            }
//
//            @Override
//            public void onFail(String collection) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "readFirestore");
//                    object.put("collection", collection);
//                    object.put("value", "{}");
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static void mergeFirestore(String collection, String jsonData) {
//        try {
//            if (jsonData == null || "".equals(jsonData)) {
//                Logger.warning(TAG, "Empty " + jsonData);
//                return;
//            }
//            FirestoreAdapter.getInstance().merge(collection, jsonData, new DatabaseListener() {
//                @Override
//                public void onData(String collection, String data) {
//                    // not use
//                }
//
//                @Override
//                public void onSuccess(String collection) {
//                    try {
//                        JSONObject object = new JSONObject();
//                        object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                        object.put("action", "mergeFirestore");
//                        object.put("collection", collection);
//                        object.put("value", TRUE);
//                        sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFail(String collection) {
//                    try {
//                        JSONObject object = new JSONObject();
//                        object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                        object.put("action", "mergeFirestore");
//                        object.put("collection", collection);
//                        object.put("value", FALSE);
//                        sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        } catch (Throwable t) {
//            Logger.error(TAG, "mergeFirestore exception " + jsonData, t);
//        }
//    }
//
//    public static void setFirestore(String collection, String jsonData) {
//        try {
//            if (jsonData == null || "".equals(jsonData)) {
//                Logger.warning(TAG, "Empty " + jsonData);
//                return;
//            }
//            FirestoreAdapter.getInstance().set(collection, jsonData, new DatabaseListener() {
//                @Override
//                public void onData(String collection, String data) {
//                    // not use
//                }
//
//                @Override
//                public void onSuccess(String collection) {
//                    try {
//                        JSONObject object = new JSONObject();
//                        object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                        object.put("action", "setFirestore");
//                        object.put("collection", collection);
//                        object.put("value", TRUE);
//                        sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFail(String collection) {
//                    try {
//                        JSONObject object = new JSONObject();
//                        object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                        object.put("action", "setFirestore");
//                        object.put("collection", collection);
//                        object.put("value", FALSE);
//                        sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        } catch (Throwable t) {
//            Logger.error(TAG, "setFirestore exception: " + jsonData, t);
//        }
//    }
//
//    public static void updateFirestore(String collection, String transactionId, String jsonData) {
//        try {
//            if (jsonData == null || "".equals(jsonData)) {
//                Logger.warning(TAG, "Empty " + jsonData);
//                return;
//            }
//            String id = "";
//            FirestoreAdapter.getInstance().update(collection, jsonData, new DatabaseListener() {
//                @Override
//                public void onData(String collection, String data) {
//                    // not use
//                }
//
//                @Override
//                public void onSuccess(String collection) {
//                    try {
//                        JSONObject object = new JSONObject();
//                        object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                        object.put("action", "updateFirestore");
//                        object.put("collection", collection);
//                        object.put("transactionId", transactionId);
//                        object.put("value", TRUE);
//                        sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void onFail(String collection) {
//                    try {
//                        JSONObject object = new JSONObject();
//                        object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                        object.put("action", "updateFirestore");
//                        object.put("collection", collection);
//                        object.put("transactionId", transactionId);
//                        object.put("value", FALSE);
//                        sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        } catch (Throwable t) {
//            Logger.error(TAG, "updateFirestore exception: " + jsonData, t);
//        }
//    }
//
//    public static void deleteFirestore(String collection) {
//        FirestoreAdapter.getInstance().delete(collection, new DatabaseListener() {
//            @Override
//            public void onData(String collection, String data) {
//                // not use
//            }
//
//            @Override
//            public void onSuccess(String collection) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "deleteFirestore");
//                    object.put("collection", collection);
//                    object.put("value", TRUE);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFail(String collection) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "deleteFirestore");
//                    object.put("collection", collection);
//                    object.put("value", FALSE);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//    public static void snapshotFirestore(String collection) {
//        FirestoreAdapter.getInstance().snapshot(collection, new DatabaseChangedListener() {
//            @Override
//            public void onData(String collection, String jsonData) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "snapshotFirestore");
//                    object.put("collection", collection);
//                    object.put("value", jsonData);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static void snapshotFirestore(String collection, String documentId) {
//        FirestoreAdapter.getInstance().snapshot(collection, documentId, new DatabaseChangedListener() {
//
//            @Override
//            public void onData(String collection, String jsonData) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "snapshotFirestore");
//                    object.put("collection", collection);
//                    object.put("documentId", documentId);
//                    object.put("value", jsonData);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static void queryFirestore(String collection) {
//        FirestoreAdapter.getInstance().query(collection, new DatabaseListener() {
//            @Override
//            public void onData(String collection, String data) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "queryFirestore");
//                    object.put("collection", collection);
//                    object.put("value", data);
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onSuccess(String collection) {
////                sendMessage("onFirestoreQuerySuccess", collection);
//            }
//
//            @Override
//            public void onFail(String collection) {
//               // sendMessage("onFirestoreQueryFail", collection);
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onFirestoreEvent);
//                    object.put("action", "queryFirestore");
//                    object.put("collection", collection);
//                    object.put("value", "{}");
//                    sendToJs(CocosCreatorEvents.onFirestoreEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//
//    public static void onCreate(Activity activity) {
//        activityWeakReference = new WeakReference<>(activity);
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                handler = new Handler(Looper.getMainLooper());
//                if (activityWeakReference.get() != null) {
//                    AndroidSdk.Builder builder = new AndroidSdk.Builder();
//                    builder.setSdkResultListener(new SdkResultListener() {
//                        @Override
//                        public void onInitialized() {
////                            sendMessage("onInitialized", "");
//                            sendToJs(CocosCreatorEvents.onInitialized, "");
//                        }
//
//                        @Override
//                        public void onReceiveServerExtra(String data) {
////                            sendMessage("onReceiveServerExtra", data);
//                            sendToJs(CocosCreatorEvents.onReceiveServerExtra, data);
//                        }
//
//                        @Override
//                        public void onReceiveNotificationData(String data) {
//
//                        }
//                    }).setPaymentListener(new PaymentSystemListener() {
//                        @Override
//                        public void onPaymentSuccess(int billId) {
////                            sendMessage("onPaymentEvent", String.valueOf(billId));
//                            try {
//                                JSONObject object = new JSONObject();
//                                object.put("event", CocosCreatorEvents.onPaymentEvent);
//                                object.put("action", "pay");
//                                object.put("billId", billId);
//                                object.put("value", TRUE);
//                                sendToJs(CocosCreatorEvents.onPaymentEvent, object.toString());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onPaymentSuccess(int bill, String payload) {
//                            try {
//                                JSONObject object = new JSONObject();
//                                object.put("event", CocosCreatorEvents.onPaymentEvent);
//                                object.put("action", "pay");
//                                object.put("billId", billId);
//                                object.put("payload", payload);
//                                object.put("value", TRUE);
//                                sendToJs(CocosCreatorEvents.onPaymentEvent, object.toString());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onPaymentSuccess(int bill, String payload, String orderInfo) {
//                            try {
//                                JSONObject object = new JSONObject();
//                                object.put("event", CocosCreatorEvents.onPaymentEvent);
//                                object.put("action", "pay");
//                                object.put("billId", billId);
//                                object.put("payload", payload);
//                                object.put("orderInfo", orderInfo);
//                                object.put("value", TRUE);
//                                sendToJs(CocosCreatorEvents.onPaymentEvent, object.toString());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onPaymentFail(int billId) {
//                            //sendMessage("onPaymentFail", String.valueOf(billId));
//                            try {
//                                JSONObject object = new JSONObject();
//                                object.put("event", CocosCreatorEvents.onPaymentEvent);
//                                object.put("action", "pay");
//                                object.put("billId", billId);
//                                object.put("value", FALSE);
//                                sendToJs(CocosCreatorEvents.onPaymentEvent, object.toString());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onPaymentSystemValid() {
//                            // sendMessage("onPaymentSystemValid", "");
//                            try {
//                                JSONObject object = new JSONObject();
//                                object.put("event", CocosCreatorEvents.onPaymentSystemEvent);
//                                object.put("action", "paymentSystemValid");
//                                object.put("value", TRUE);
//                                sendToJs(CocosCreatorEvents.onPaymentSystemEvent, object.toString());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onPaymentCanceled(int bill) {
//                            // sendMessage("onPaymentCanceled", String.valueOf(bill));
//                            try {
//                                JSONObject object = new JSONObject();
//                                object.put("event", CocosCreatorEvents.onPaymentEvent);
//                                object.put("action", "pay");
//                                object.put("billId", bill);
//                                object.put("value", FALSE);
//                                object.put("msg", "pay canceled");
//                                sendToJs(CocosCreatorEvents.onPaymentEvent, object.toString());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onPaymentSystemError(int code, String message) {
//                            // sendMessage("onPaymentSystemError", String.valueOf(code) + "|" + message);
//                            try {
//                                JSONObject object = new JSONObject();
//                                object.put("event", CocosCreatorEvents.onPaymentSystemEvent);
//                                object.put("action", "paymentSystemValid");
//                                object.put("value", FALSE);
//                                sendToJs(CocosCreatorEvents.onPaymentSystemEvent, object.toString());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onStoreLoaded(String skuType) {
//
//                        }
//
//                        @Override
//                        public void onReceiveBillPrices(String prices) {
//                            // sendMessage("onReceiveBillPrices", prices);
//                        }
//                    }).setUrlListener(new UrlListener() {
//                        @Override
//                        public void onSuccess(int tag, String path) {
//                            //sendMessage("onCacheUrlResult", tag + "|0|" + path);
//
//                        }
//
//                        @Override
//                        public void onFailure(int tag) {
//                            // sendMessage("onCacheUrlResult", tag + "|1");
//                        }
//                    }).setUserCenterListener(new UserCenterListener() {
//                        @Override
//                        public void onReceiveLoginResult(boolean success) {
//                            // sendMessage("onReceiveLoginResult", success ? TRUE : FALSE);
//                        }
//
//                        @Override
//                        public void onReceiveFriends(String friends) {
//                            //  sendMessage("onReceiveFriends", friends);
//                        }
//
//                        @Override
//                        public void onReceiveInviteResult(boolean success) {
//                            //  sendMessage("onReceiveInviteResult", success ? TRUE : FALSE);
//                        }
//
//                        @Override
//                        public void onReceiveChallengeResult(int count) {
//                            //  sendMessage("onReceiveChallengeResult", String.valueOf(count));
//                        }
//
//                        @Override
//                        public void onReceiveLikeResult(boolean success) {
//                            //   sendMessage("onReceiveLikeResult", success ? TRUE : FALSE);
//                        }
//                    });
//
////                    builder.setAdLoadedListener(new AdLoadedListener() {
////                        @Override
////                        public void onAdLoaded(int adType) {
////                            try {
////                                int newAdType = 0;
////                                if (adType == IvyAdType.INTERSTITIAL.ordinal()) {
////                                    newAdType = AD_FULL;
////                                } else if (adType == IvyAdType.REWARDED.ordinal()) {
////                                    newAdType = AD_VIDEO;
////                                } else if (adType == IvyAdType.BANNER.ordinal()) {
////                                    newAdType = AD_BANNER;
////                                } else if (adType == IvyAdType.NATIVE_AD.ordinal()) {
////                                    newAdType = AD_NATIVE;
////                                } else if (adType == IvyAdType.REWARDED_INTERSTITIAL.ordinal()) {
////                                    newAdType = AD_REWARDED_INTERSTITIAL;
////                                }
////                                Logger.debug(TAG, "Notify AdLoaded: " + newAdType);
////
////                                sendMessage("onAdLoaded", String.valueOf(newAdType));
////
////                            } catch (Throwable t) {
////                                Logger.error(TAG, "adloaded not defined", t);
////                            }
////                        }
////                    });
////
//                    AndroidSdk.onCreate(activityWeakReference.get(), builder);
//                    AndroidSdk.registerAdEventListener(new AdEventListener() {
//                        @Override
//                        public void onAdShow(String tag, String platform, int type) {
//                            super.onAdShow(tag, platform, type);
//                            //  sendMessage("onAdShow", type + "|" + tag);
//                        }
//
//                        @Override
//                        public void onAdClicked(String tag, String platform, int type) {
//                            super.onAdClicked(tag, platform, type);
//                            //   sendMessage("onAdClicked", type + "|" + tag);
//                        }
//                    });
//                    bindJsEvents();
//                }
//            }
//        });
//    }
//
//
//    public static void onStart() {
//        if (handler != null) {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    AndroidSdk.onStart();
//                }
//            });
//        }
//    }
//
//    public static void onStop() {
//        if (handler != null) {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    AndroidSdk.onStop();
//                }
//            });
//        }
//    }
//
//    public static void onPause() {
//        if (handler != null) {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    AndroidSdk.onPause();
//                }
//            });
//        }
//    }
//
//    public static void onResume() {
//        if (activityWeakReference.get() != null) {
//            if (handler != null) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        AndroidSdk.onResume(activityWeakReference.get());
//                    }
//                });
//            }
//        }
//    }
//
//    public static void onDestroy() {
//        if (handler != null) {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    unbindJsEvents();
//                    AndroidSdk.onDestroy();
//                }
//            });
//        }
//    }
//
//    public static void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
//        if (handler != null) {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    AndroidSdk.onRequestPermissionsResult(requestCode, permissions, grantResults);
//                }
//            });
//        }
//    }
//
//    public static void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
//        Log.d(TAG, "Unity3D@onActivityResult called");
//        if (handler != null) {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    AndroidSdk.onActivityResult(requestCode, resultCode, data);
//                }
//            });
//        }
//    }
//
//    public static void setAdStatus(boolean enabled) {
//        IvySdk.updateAdStatus(enabled);
//    }
//
//    public static void showBanner(String tag, int pos, int animate) {
//        AndroidSdk.showBanner(tag, pos);
//    }
//
//    public static void showBanner(String tag, int pos) {
//        AndroidSdk.showBanner(tag, pos);
//    }
//
//    public static void showBanner(int pos) {
//        AndroidSdk.showBanner("default", pos);
//    }
//
//    public static void closeBanner() {
//        AndroidSdk.closeBanner("default");
//    }
//
//    public static void closeBanner(String tag) {
//        AndroidSdk.closeBanner(tag);
//    }
//
//    public static void showFullAd(final String tag) {
//        AndroidSdk.showFullAd(tag, new AdListener() {
//            @Override
//            public void onAdClosed() {
//                // sendMessage("onFullAdClosed", tag);
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showFullAd");
//                    object.put("tag", tag);
//                    object.put("value", "AdClosed");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdClicked() {
//                //  sendMessage("onFullAdClicked", tag);
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showFullAd");
//                    object.put("tag", tag);
//                    object.put("value", "AdClicked");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdShow() {
//                super.onAdShow();
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showFullAd");
//                    object.put("tag", tag);
//                    object.put("value", "AdShown");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdShowFails() {
//                super.onAdShowFails();
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showFullAd");
//                    object.put("tag", tag);
//                    object.put("value", "AdShowFails");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static void showRewardAd(int id) {
//        showRewardAd("default", id);
//    }
//
//    public static boolean hasRewardAd() {
//        return AndroidSdk.hasRewardAd("default");
//    }
//
//    public static void showRewardAd(final String tag, final int id) {
//        AndroidSdk.showRewardAd(tag, new AdListener() {
//            @Override
//            public void onAdReward(boolean skip) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdReward");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdShowFails() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdShowFails");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdClosed() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdClosed");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdClicked() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdClicked");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdShow() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdShown");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static boolean hasRewardedInterstitial() {
//        return AndroidSdk.hasRewardedInterstitial("default");
//    }
//
//    public static void showRewardedInterstitial(final int id) {
//        showRewardedInterstitial("default", id);
//    }
//
//    public static void showRewardedInterstitial(final String tag, final int id) {
//        AndroidSdk.showRewardedInterstitial(tag, new AdListener() {
//            @Override
//            public void onAdReward(boolean skip) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdReward");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdLoadFails() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdLoadFails");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdLoadSuccess() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdLoadSuccess");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdShowFails() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdShowFails");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdClosed() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdClosed");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdClicked() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdClicked");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onAdShow() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onAdEvent);
//                    object.put("action", "showRewardedAd");
//                    object.put("tag", tag);
//                    object.put("id", id);
//                    object.put("value", "AdShown");
//                    sendToJs(CocosCreatorEvents.onAdEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//
//    public static boolean hasBanner(String tag) {
//        return AndroidSdk.hasBanner(tag);
//    }
//
//    public static boolean hasFull(String tag) {
//        return AndroidSdk.hasFull(tag);
//    }
//
//    public static boolean hasRewardAd(String tag) {
//        return AndroidSdk.hasRewardAd(tag);
//    }
//
//    public static void pay(int id) {
//        AndroidSdk.pay(id);
//    }
//
//    public static void pay(int id, String payload) {
//        AndroidSdk.pay(id, null, payload);
//    }
//
//    public static void query(int id) {
//        AndroidSdk.query(id);
//    }
//
//    public static void isSubscriptionActive(int billId) {
//        AndroidSdk.isSubscriptionActive(billId, new IPurchaseQueryCallback<Boolean>() {
//            @Override
//            public void onResult(Boolean data) {
//                try{
//
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public static void share() {
//        AndroidSdk.share();
//    }
//
//    public static void share(String url) {
//        AndroidSdk.share(url, null);
//    }
//
//    public static void rate() {
//        AndroidSdk.rateUs();
//    }
//
//    public static void rate(float star) {
//        AndroidSdk.rateUs();
//    }
//
//
//    public static void trackEvent(String category, String action, String label, int value) {
//        AndroidSdk.track(category, action, label, value);
//    }
//
//    public static void trackEvent(String event, String data) {
//        Map<String, Object> extra = new HashMap<>();
//        if (data != null) {
//            String[] splits = data.split(",");
//            if (splits.length > 1) {
//                for (int i = 0; i < splits.length; i += 2) {
//                    try {
//                        extra.put(splits[i], Double.parseDouble(splits[i + 1]));
//                        continue;
//                    } catch (Exception ignore) {
//                    }
//
//                    try {
//                        extra.put(splits[i], Float.parseFloat(splits[i + 1]));
//                        continue;
//                    } catch (Exception ignore) {
//                    }
//
//                    try {
//                        extra.put(splits[i], Long.parseLong(splits[i + 1]));
//                        continue;
//                    } catch (Exception ignore) {
//                    }
//
//                    try {
//                        extra.put(splits[i], Integer.parseInt(splits[i + 1]));
//                        continue;
//                    } catch (Exception ignore) {
//                    }
//
//                    try {
//                        extra.put(splits[i], splits[i + 1]);
//                    } catch (Exception ignore) {
//                    }
//                }
//            }
//        }
//        AndroidSdk.track(event, extra);
//    }
//
//    public static String getExtraData() {
//        return AndroidSdk.getExtraData();
//    }
//
//    public static void onQuit() {
//        AndroidSdk.onQuit();
//    }
//
//    public static void login() {
//        try {
//            JSONObject object = new JSONObject();
//            object.put("event", CocosCreatorEvents.onFacebookUserEvent);
//            object.put("action", "loginFacebook");
//            object.put("value", TRUE);
//            sendToJs(CocosCreatorEvents.onFacebookUserEvent, object.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void setUserProperty(String key, String value) {
//        AndroidSdk.setUserProperty(key, value);
//    }
//
//    public static void logout() {
//        AndroidSdk.logout();
//    }
//
//    public static boolean isLogin() {
//        return AndroidSdk.isLogin();
//    }
//
//    public static String me() {
//        return AndroidSdk.me();
//    }
//
//    public static String friends() {
//        return AndroidSdk.friends();
//    }
//
//    public static boolean hasNotch() {
//        return AndroidSdk.hasNotch();
//    }
//
//    public static int getNotchHeight() {
//        return AndroidSdk.getNotchHeight();
//    }
//
//    public static boolean isNetworkConnected() {
//        return AndroidSdk.isNetworkConnected();
//    }
//
//    /**
//     * download this url and cache the result into sdcard
//     *
//     * @param url
//     * @return file path in sdcard: /sdcard/0/.cache/383292918283483291
//     */
//    public static String cacheUrl(String url) {
//        return AndroidSdk.cacheUrl(url);
//    }
//
//    public static String getConfig(int configKey) {
//        return AndroidSdk.getConfig(configKey);
//    }
//
//    public static void alert(String title, String message) {
//        AndroidSdk.alert(title, message);
//    }
//
//    public static void toast(String message) {
//        AndroidSdk.toast(message);
//    }
//
//    private static void tLoag(String message) {
//        Log.e("events", "js message:" + message);
//    }
//
//    public static String getConfig(String packageName, int configKey) {
//        return AndroidSdk.getConfig(packageName, configKey);
//    }
//
//    public static boolean isPaymentValid() {
//        return AndroidSdk.isPaymentValid();
//    }
//
//    public static String getPrices() {
//        return AndroidSdk.getPrices();
//    }
//
//    public static String getPaymentDatas() {
//        return AndroidSdk.getPrices();
//    }
//
//    public static String getPaymentData(int billId) {
//        SKUDetail skuDetail = AndroidSdk.getSKUDetail(billId);
//        return skuDetail == null ? "{}" : skuDetail.toJson().toString();
//    }
//
//    public static int getRemoteConfigInt(String key) {
//        return AndroidSdk.getRemoteConfigInt(key);
//    }
//
//    public static long getRemoteConfigLong(String key) {
//        return AndroidSdk.getRemoteConfigLong(key);
//    }
//
//    public static double getRemoteConfigDouble(String key) {
//        return AndroidSdk.getRemoteConfigDouble(key);
//    }
//
//    public static boolean getRemoteConfigBoolean(String key) {
//        return AndroidSdk.getRemoteConfigBoolean(key);
//    }
//
//    public static String getRemoteConfigString(String key) {
//        return AndroidSdk.getRemoteConfigString(key);
//    }
//
//    public static void silentLoginGoogle() {
//        AndroidSdk.silentLoginGoogle(new GoogleListener() {
//            @Override
//            public void onSuccess(String googleId, String googleEmail) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLoginGoogleEvent);
//                    object.put("action", "silentLoginGoogle");
//                    object.put("value", TRUE);
//                    object.put("googleId", googleId);
//                    object.put("email", googleEmail);
//                    sendToJs(CocosCreatorEvents.onLoginGoogleEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFails() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLoginGoogleEvent);
//                    object.put("action", "silentLoginGoogle");
//                    object.put("value", FALSE);
//                    sendToJs(CocosCreatorEvents.onLoginGoogleEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//    public static void loginGoogle() {
//        AndroidSdk.loginGoogle(new GoogleListener() {
//            @Override
//            public void onSuccess(String googleId, String googleEmail) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLoginGoogleEvent);
//                    object.put("action", "loginGoogle");
//                    object.put("value", TRUE);
//                    object.put("googleId", googleId);
//                    object.put("email", googleEmail);
//                    sendToJs(CocosCreatorEvents.onLoginGoogleEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFails() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLoginGoogleEvent);
//                    object.put("action", "loginGoogle");
//                    object.put("value", FALSE);
//                    sendToJs(CocosCreatorEvents.onLoginGoogleEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//    public static void logoutGoogle() {
//        AndroidSdk.logoutGoogle(new GoogleListener() {
//            @Override
//            public void onSuccess(String googleId, String googleEmail) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLoginGoogleEvent);
//                    object.put("action", "logoutGoogle");
//                    object.put("value", TRUE);
//                    sendToJs(CocosCreatorEvents.onLoginGoogleEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFails() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLoginGoogleEvent);
//                    object.put("action", "logoutGoogle");
//                    object.put("value", FALSE);
//                    sendToJs(CocosCreatorEvents.onLoginGoogleEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//    public static boolean isGoogleLogin() {
//        return AndroidSdk.isGoogleLogin();
//    }
//
//    public static void updateGoogleAchievement(final String id, int step) {
//        AndroidSdk.updateGoogleAchievement(id, step, new GoogleListener() {
//            @Override
//            public void onSuccess(String googleId, String googleEmail) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLeaderBoardEvent);
//                    object.put("action", "updateGoogleAchievement");
//                    object.put("id", id);
//                    object.put("value", TRUE);
//                    sendToJs(CocosCreatorEvents.onLeaderBoardEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFails() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLeaderBoardEvent);
//                    object.put("action", "updateGoogleAchievement");
//                    object.put("id", id);
//                    object.put("value", FALSE);
//                    sendToJs(CocosCreatorEvents.onLeaderBoardEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//    public static void updateGoogleLeaderBoard(final String id, long value) {
//        AndroidSdk.updateGoogleLeaderBoard(id, value, new GoogleListener() {
//            @Override
//            public void onSuccess(String googleId, String googleEmail) {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLeaderBoardEvent);
//                    object.put("action", "updateGoogleLeaderBoard");
//                    object.put("id", id);
//                    object.put("value", TRUE);
//                    sendToJs(CocosCreatorEvents.onLeaderBoardEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFails() {
//                try {
//                    JSONObject object = new JSONObject();
//                    object.put("event", CocosCreatorEvents.onLeaderBoardEvent);
//                    object.put("action", "updateGoogleLeaderBoard");
//                    object.put("id", id);
//                    object.put("value", FALSE);
//                    sendToJs(CocosCreatorEvents.onLeaderBoardEvent, object.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
//
//    public static void showGoogleAchievements() {
//        AndroidSdk.showGoogleAchievements();
//    }
//
//    public static void showGoogleLeaderBoard(String id) {
//        AndroidSdk.showGoogleLeaderBoards(id);
//    }
//
//    public static void showGoogleLeaderBoards() {
//        AndroidSdk.showGoogleLeaderBoards();
//    }
//
//    public static boolean isGoogleSupport() {
//        return AndroidSdk.isGoogleSupport();
//    }
//
//
//
//}
