package com.wangyuelin.adbiz;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;

public class AdApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("wyl", "开始初始化谷歌广告");
        MobileAds.initialize(this, initializationStatus -> Log.d("wyl", "谷歌广告初始化完成"));
    }
}
