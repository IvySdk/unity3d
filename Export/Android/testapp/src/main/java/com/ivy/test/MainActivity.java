package com.ivy.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.client.AdEventListener;
import com.android.client.AdListener;
import com.android.client.AdLoadedListener;
import com.android.client.AndroidSDK;
import com.android.client.IGMSPaidEventListener;
import com.android.client.IInitResultListener;
import com.ivy.IvySdk;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        AndroidSDK.Builder builder = new AndroidSDK.Builder().setAdEventListener(new AdEventListener() {
//            @Override
//            public void onAdShow(String tag, String platform, int type) {
//                super.onAdShow(tag, platform, type);
//                Log.e("ADSFALL", "global onAdShow::" + tag + "::" + platform + "::" + type);
//            }
//
//            @Override
//            public void onAdClicked(String tag, String platform, int type) {
//                super.onAdClicked(tag, platform, type);
//                Log.e("ADSFALL", "global onAdClicked::" + tag + "::" + platform + "::" + type);
//            }
        }).setAdLoadedListener(new AdLoadedListener() {
            @Override
            public void onAdLoaded(int adType) {
                Log.e("ADSFALL", "onAdLoaded::" + adType);
            }
        }).setGmsPaidEventListener(new IGMSPaidEventListener() {
            @Override
            public void onGMSPaid(Map<String, Object> map) {
                Log.e("ADSFALL", "onGMSPaid::" + map);
            }
        }).setInitResultListener(new IInitResultListener() {
            @Override
            public void onInitialized() {
                Log.e("ADSFALL", "onInitialized");
            }
        });

        AndroidSDK.onCreate(this, builder);

    }

   public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.showBanner:
                AndroidSDK.showBanner("default", IvySdk.POS_CENTER_BOTTOM);
                break;
            case R.id.closeBanner:
                AndroidSDK.closeBanner("default");
                break;
            case R.id.hasFull:
                Toast.makeText(view.getContext(), "has full ad loaded::" + AndroidSDK.hasFull("default"), Toast.LENGTH_SHORT).show();
                break;
            case R.id.showFull:
                AndroidSDK.showFullAd("default", new AdListener(){
                    @Override
                    public void onAdShow() {
                        super.onAdShow();
                        Log.e("ADSFALL", "impl::full::onAdShow");
                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                        Log.e("ADSFALL", "impl::full::onAdClicked");
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        Log.e("ADSFALL", "impl::full::onAdClosed");
                    }

                    @Override
                    public void onAdLoadFails() {
                        super.onAdLoadFails();
                        Log.e("ADSFALL", "impl::full::onAdLoadFails");
                    }

                    @Override
                    public void onAdLoadSuccess() {
                        super.onAdLoadSuccess();
                        Log.e("ADSFALL", "impl::full::onAdLoadSuccess");
                    }

                    @Override
                    public void onAdShowFails() {
                        super.onAdShowFails();
                        Log.e("ADSFALL", "impl::full::onAdShowFails");
                    }
                });
                break;
            case R.id.showRewarded:
                AndroidSDK.showRewardAd("default", new AdListener(){
                    @Override
                    public void onAdShow() {
                        super.onAdShow();
                        Log.e("ADSFALL", "impl::rewarded::onAdShow");
                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                        Log.e("ADSFALL", "impl::rewarded::onAdClicked");
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        Log.e("ADSFALL", "impl::rewarded::onAdClosed");
                    }

                    @Override
                    public void onAdLoadFails() {
                        super.onAdLoadFails();
                        Log.e("ADSFALL", "impl::rewarded::onAdLoadFails");
                    }

                    @Override
                    public void onAdLoadSuccess() {
                        super.onAdLoadSuccess();
                        Log.e("ADSFALL", "impl::rewarded::onAdLoadSuccess");
                    }

                    @Override
                    public void onAdShowFails() {
                        super.onAdShowFails();
                        Log.e("ADSFALL", "impl::rewarded::onAdShowFails");
                    }

                    @Override
                    public void onAdReward(boolean skip) {
                        super.onAdReward(skip);
                        Log.e("ADSFALL", "impl::rewarded::onAdReward::" + skip);
                    }
                });
                break;
            case R.id.hasRewarded:
                Toast.makeText(view.getContext(), "has rewaeded ad loaded::" + AndroidSDK.hasRewardAd("default"), Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
