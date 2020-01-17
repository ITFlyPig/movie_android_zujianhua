package com.wangyuelin.adbiz.helper;

import android.app.Activity;
import android.text.TextUtils;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

public class UnityAdHelper {
    private Activity activity;

    /**
     * 初始化
     * @param activity
     * @param gameID
     * @param listener
     */
    public  void init(Activity activity, String gameID, IUnityAdsListener listener) {
        if (TextUtils.isEmpty(gameID) || activity == null) {
            return;
        }
        this.activity = activity;
        //初始化
        UnityAds.initialize(activity, gameID, true);
        if (listener != null) {
            UnityAds.addListener(listener);
        }
    }

    /**
     * 是否准备好了
     * @param placementID
     * @return
     */
    public  boolean isReady(String placementID) {
        if (TextUtils.isEmpty(placementID)) {
            return false;
        }
        return UnityAds.isReady(placementID);

    }

    /**
     * 展示广告
     */
    public  void show(){
        UnityAds.show(activity);
    }

}
