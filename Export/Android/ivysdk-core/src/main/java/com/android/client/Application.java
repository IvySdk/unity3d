package com.android.client;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.ivy.IvySdk;

import java.util.Timer;
import java.util.TimerTask;

public class Application extends MultiDexApplication implements DefaultLifecycleObserver {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    private long GAME_PLAY_TIME = 0L;
    private boolean GAME_STOPPED = false;
    private Timer timer;

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        GAME_STOPPED = false;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (!GAME_STOPPED) {
//                        Log.e("IvySdk", "timer is working");
                        GAME_PLAY_TIME += 5;
                        IvySdk.setupGamePlayTime(GAME_PLAY_TIME);
                    }
                } catch (Exception e) {
                }
            }
        }, 0, 5000);
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        GAME_STOPPED = false;
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        GAME_STOPPED = true;
    }
}
