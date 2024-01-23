//package com.ivy.AIHelp;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.ivy.IvySdk;
//
//import net.aihelp.config.ApiConfig;
//import net.aihelp.config.UserConfig;
//import net.aihelp.config.enums.ShowConversationMoment;
//import net.aihelp.init.AIHelpSupport;
//import net.aihelp.ui.listener.OnAIHelpInitializedCallback;
//import net.aihelp.ui.listener.OnMessageCountArrivedCallback;
//
//import org.json.JSONObject;
//
//public class AIHelp implements OnAIHelpInitializedCallback {
//
//
//    public static final String TAG = "AIHelp";
//
//    private boolean hasInitialized = false;
//
//    private static AIHelp _instance = null;
//
//    public static AIHelp getInstance() {
//        if (_instance == null) {
//            synchronized (AIHelp.class) {
//                if (_instance == null) {
//                    _instance = new AIHelp();
//                }
//            }
//        }
//        return _instance;
//    }
//
//    private AIHelp() {
//    }
//
//    /**
//     * 初始化
//     *
//     * @param context
//     * @param appKey
//     * @param domain
//     * @param appId
//     */
//    public void init(Context context, String appKey, String domain,
//                     String appId) {
//        //AIHelp初始化回调
//        AIHelpSupport.setOnAIHelpInitializedCallback(this);
//        AIHelpSupport.init(context, appKey, domain, appId);
//    }
//
//    /**
//     * 初始化
//     *
//     * @param context
//     * @param appKey
//     * @param domain
//     * @param appId
//     * @param language 不传则默认为本机语言
//     */
//    public void init(Context context, String appKey, String domain,
//                     String appId, String language) {
//        //AIHelp初始化回调
//        AIHelpSupport.setOnAIHelpInitializedCallback(this);
//        AIHelpSupport.init(context, appKey, domain, appId);
//    }
//
//    @Override
//    public void onAIHelpInitialized(boolean isSuccess, String message) {
//        Log.i(TAG, "AIHelp init callback: isSuccess:" + isSuccess + " message:" + message);
//        updateUserInfo(null, null);//设置userId，防止因延迟设置userId导致的未读消息数量推送不正确
//        hasInitialized = isSuccess;
//    }
//
//    public void loadUnreadMessageCount(OnMessageCountArrivedCallback callback){
//        if (callback == null){
//            Log.e(TAG, "load unread message count shouldn't with a null callback");
//            return;
//        }
//        AIHelpSupport.startUnreadMessageCountPolling(callback);
//    }
//
//    public void stopUnreadMessageCountPolling(){
//        AIHelpSupport.stopUnreadMessageCountPolling();
//    }
//
//    public boolean isHasInitialized() {
//        return hasInitialized;
//    }
//
//    /**
//     * 是否在展示AIHelp
//     *
//     * @return
//     */
//    public boolean isAIHelpShowing() {
//        return AIHelpSupport.isAIHelpShowing();
//    }
//
//    /**
//     * 打开url
//     *
//     * @param url
//     */
//    public void showUrl(String url) {
//        if (isEmpty(url)) {
//            Log.e(TAG, "can't open url:" + url);
//            return;
//        }
//        AIHelpSupport.showUrl(url);
//    }
//
//    /**
//     * 打开自定义入口
//     *
//     * @param entranceId 自定义入口id
//     */
//    public void show(String entranceId) {
//        if (isEmpty(entranceId)) {
//            Log.e(TAG, "can't open entranceId:" + entranceId);
//            return;
//        }
//        AIHelpSupport.show(entranceId);
//    }
//
//    /**
//     * 打开自定义入口，携带自定义欢迎语
//     *
//     * @param entranceId     自定义入口id
//     * @param welcomeMessage 自定义欢迎语
//     */
//    public void show(String entranceId, String welcomeMessage) {
//        if (isEmpty(entranceId)) {
//            Log.e(TAG, "can't open entranceId:" + entranceId);
//            return;
//        }
//        ApiConfig.Builder builder = new ApiConfig.Builder();
//        builder.setEntranceId(entranceId);
//        if (!isEmpty(welcomeMessage)) {
//            builder.setWelcomeMessage(welcomeMessage);
//        }
//        AIHelpSupport.show(builder.build());
//    }
//
//    public void close(){
//        AIHelpSupport.close();
//    }
//
//    /**
//     * 打开指定 faq 页面
//     *
//     * @param faqId
//     * @param moment FAQ 页面展示联系客服按钮的时机: 0 不显示；1 点踩后显示；2 只在回复页显示； 默认一直显示；
//     */
//    public void showSingleFAQ(String faqId, int moment) {
//        if (isEmpty(faqId)) {
//            Log.e(TAG, "can't open faqId:" + faqId);
//            return;
//        }
//        if (moment == 0) {
//            AIHelpSupport.showSingleFAQ(faqId, ShowConversationMoment.NEVER);
//        } else if (moment == 1) {
//            AIHelpSupport.showSingleFAQ(faqId, ShowConversationMoment.AFTER_MARKING_UNHELPFUL);
//        } else if (moment == 2) {
//            AIHelpSupport.showSingleFAQ(faqId, ShowConversationMoment.ONLY_IN_ANSWER_PAGE);
//        } else {
//            AIHelpSupport.showSingleFAQ(faqId, ShowConversationMoment.ALWAYS);
//        }
//
//    }
//
//    /**
//     * 配置用户信息，携带用户标签
//     * 在具体为用户配置标签时，你应该确保已经提前在 AIHelp 后台配置好了对应的标签内容
//     *
//     * @param data
//     * @param tags
//     */
//    public void updateUserInfo(JSONObject data, String tags) {
//        UserConfig.Builder config = new UserConfig.Builder()
//                .setUserId(IvySdk.getUUID());
//        if (data != null){
//            config.setCustomData(data.toString());
//        }
//        if (tags != null){
//            config.setUserTags(tags);
//        }
//        AIHelpSupport.updateUserInfo(config.build());
//    }
//
//    /**
//     * 重置用户信息
//     */
//    public void resetUserInfo() {
//        AIHelpSupport.resetUserInfo();
//    }
//
//    /**
//     * 设置语言
//     *
//     * @param lang
//     */
//    public void updateSDKLanguage(String lang) {
//        if (lang == null) {
//            Log.e(TAG, "setup language failed:" + lang);
//            return;
//        }
//        AIHelpSupport.updateSDKLanguage(lang);
//    }
//
//    private boolean isEmpty(String data) {
//        if (data == null) return true;
//        if (data.trim().length() == 0) return true;
//        return false;
//    }
//
//}
