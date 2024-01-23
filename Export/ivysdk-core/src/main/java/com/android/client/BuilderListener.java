package com.android.client;

/**
 * Created by song on 2017/6/28.
 */

public class BuilderListener  { // implements SdkListener {
    private AndroidSdk.Builder builder;

    public void setBuilder(AndroidSdk.Builder builder) {
        this.builder = builder;
    }

    public void onReceiveReward(boolean success, int rewardId, String tag) {
//        if (builder.rewardAdListener != null) {
//            builder.rewardAdListener.onReceiveReward(success, rewardId, tag);
//        }
    }

    public void onPaymentSuccess(int billId) {
//        if (builder.paymentResultListener != null) {
//            builder.paymentResultListener.onPaymentSuccess(billId);
//        }
    }

    public void onPaymentFail(int billId) {
//        if (builder.paymentResultListener != null) {
//            builder.paymentResultListener.onPaymentFail(billId);
//        }
    }

    public void onPaymentCanceled(int bill) {
//        if (builder.paymentResultListener != null) {
//            builder.paymentResultListener.onPaymentCanceled(bill);
//        }
    }

    public void onPaymentSystemValid() {
//        if (builder.paymentResultListener != null) {
//            builder.paymentResultListener.onPaymentSystemValid();
//        }
    }

    public void onPaymentSystemError(int causeId, final String message) {
//        SdkLog.log("Payment system error: " + causeId + " msg: " + message);
//        SdkEnv.toast(message);
    }

    public void onInitialized() {
        if (builder.sdkResultListener != null) {
            builder.sdkResultListener.onInitialized();
        }
    }

    public void onReceiveServerExtra(String data) {
        if (builder.sdkResultListener != null) {
            builder.sdkResultListener.onReceiveServerExtra(data);
        }
    }

    public void onFullAdClosed(String tag) {
//        if (builder.rewardAdListener != null) {
//            builder.rewardAdListener.onFullAdClosed(tag);
//        }
    }

    public void onFullAdClicked(String tag) {
//        if (builder.rewardAdListener != null) {
//            builder.rewardAdListener.onFullAdClicked(tag);
//        }
    }

    public void onVideoAdClosed(String tag) {
//        if (builder.rewardAdListener != null) {
//            builder.rewardAdListener.onVideoAdClosed(tag);
//        }
    }

    public void onBannerAdClicked(String tag) {
//        AdMaster.master().setShowHomeAd(false);
//        if (builder.rewardAdListener != null) {
//            builder.rewardAdListener.onBannerAdClicked(tag);
//        }
    }

    public void onCrossAdClicked(String tag) {
//        AdMaster.master().setShowHomeAd(false);
//        if (builder.rewardAdListener != null) {
//            builder.rewardAdListener.onCrossAdClicked(tag);
//        }
    }
}
