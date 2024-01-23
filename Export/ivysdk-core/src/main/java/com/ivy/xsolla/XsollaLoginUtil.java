//package com.ivy.xsolla;
//
//import android.content.Context;
//import android.content.Intent;
//
//import com.ivy.IvySdk;
//import com.xsolla.android.login.LoginConfig;
//import com.xsolla.android.login.XLogin;
//import com.xsolla.android.login.callback.FinishSocialCallback;
//import com.xsolla.android.login.callback.RefreshTokenCallback;
//import com.xsolla.android.login.callback.StartSocialCallback;
//import com.xsolla.android.login.social.SocialNetwork;
//
//import org.jetbrains.annotations.Nullable;
//
//class XsollaLoginUtil {
//
//    private IXsollaLoginListener listener;
//
//    private boolean isEmpty(String s){
//        return s != null && s.trim().length() > 0;
//    }
//
//    /**
//     * xsolla登陆
//     *
//     * @param callback
//     */
//    public void Login(IXsollaLoginListener callback) {
//        this.listener = callback;
//        loginVK();
//    }
//
//    public void isLogIn(IXsollaLoginListener callback) {
//        try {
//            //XLogin 未初始化时会报错
//            if (!isEmpty(XLogin.getToken())){
//                // 没有token记录
//                DLog.d("xsolla no last login token");
//                if (callback != null) callback.onFail();
//            } else {
//                // 有token 记录
//                if (XLogin.isTokenExpired()){
//                    // token 过期
//                    DLog.d("xsolla last login token Expired");
//                    if (XLogin.canRefreshToken()){
//                        DLog.d("xsolla last login token can refresh");
//                        XLogin.refreshToken(new RefreshTokenCallback() {
//                            @Override
//                            public void onSuccess() {
//                                DLog.d("xsolla token refresh success");
//                                if (callback != null) callback.onSuccess();
//                            }
//
//                            @Override
//                            public void onError(@Nullable Throwable throwable, @Nullable String s) {
//                                DLog.e("xsolla login expired!!!  do login:" + s);
//                                if (callback != null) callback.onFail();
//                            }
//                        });
//                    } else {
//                        DLog.d("xsolla last login token can not refresh");
//                        if (callback != null) callback.onFail();
//                    }
//                } else {
//                    // token 有效
//                    DLog.d("xsolla last login token valid:" + XLogin.getToken());
//                    if (callback != null) callback.onSuccess();
//                }
//            }
//        } catch (Exception e) {
//            DLog.e("xsolla call is login err:" + e.getMessage());
//            if (callback != null) callback.onFail();
//        }
//    }
//
//    /**
//     * 初始化 登陆项目配置
//     *
//     * @param context
//     * @param projectId
//     * @param oAuthId
//     */
//    public void initLoginConfig(Context context, String projectId, int oAuthId) {
//        LoginConfig loginConfig = new LoginConfig.OauthBuilder()
//                .setProjectId(projectId)
//                .setOauthClientId(oAuthId)
//                .build();
//        XLogin.init(context, loginConfig);
//    }
//
//    /**
//     * vk登陆
//     */
//    private void loginVK() {
//        XLogin.startSocialAuth(IvySdk.getActivity(), SocialNetwork.VK, new StartSocialCallback() {
//            @Override
//            public void onAuthStarted() {
//                DLog.d("xsolla start auth");
//            }
//
//            @Override
//            public void onError(@Nullable Throwable throwable, @Nullable String s) {
//                if (listener != null) listener.onFail();
//                DLog.e("xsolla auth error:" + s);
//            }
//        });
//    }
//
//
//    /**
//     * VK登陆结果
//     *
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode != 31000) return; // 此处持保留性设置，待确认
//        XLogin.finishSocialAuth(IvySdk.getActivity(), SocialNetwork.VK, requestCode, resultCode, data, new FinishSocialCallback() {
//            @Override
//            public void onAuthSuccess() {
//                DLog.d("xsolla login success");
//                //TODO: initStore
//                if (listener != null) listener.onSuccess();
//            }
//
//            @Override
//            public void onAuthCancelled() {
//                DLog.d("xsolla login canceled");
//                if (listener != null) listener.onFail();
//            }
//
//            @Override
//            public void onAuthError(@Nullable Throwable throwable, @Nullable String s) {
//                DLog.e("xsolla login failed:" + s);
//                if (listener != null) listener.onFail();
//            }
//        });
//    }
//
//    /**
//     * 退出登陆
//     */
//    public void logout() {
//        DLog.d("xsolla log out");
//        XLogin.logout();
//    }
//
//    /**
//     * 获取登陆 token
//     *
//     * @return
//     */
//    public String getToken() {
//        return XLogin.getToken();
//    }
//}
