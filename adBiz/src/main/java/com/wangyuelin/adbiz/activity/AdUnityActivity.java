package com.wangyuelin.adbiz.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

public class AdUnityActivity extends Activity implements IUnityAdsListener {
    private static String gameIDStr = "gameID";
    private static String placementIDStr = "placementID";
    private String gameId;
    private String placementID;
    private boolean isShowing;//标志广告是否正在显示
    private int count;//记录观看的次数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        gameId = getIntent().getStringExtra(gameIDStr);
        placementID = getIntent().getStringExtra(placementIDStr);

        //初始化
        UnityAds.initialize(this, gameId, true);
        UnityAds.addListener(this);

    }


    /**
     * 打开unity广告的显示
     * @param context
     * @param gameID
     * @param placementID
     */
    public static void startActivity(Activity context, String gameID, String placementID) {
        if (TextUtils.isEmpty(gameID) || TextUtils.isEmpty(placementID) || context == null) {
            return;
        }
        Intent intent = new Intent(context, AdUnityActivity.class);
        intent.putExtra(gameIDStr, gameID);
        intent.putExtra(placementIDStr, placementID);
        context.startActivityForResult(intent, 100);
    }

    @Override
    public void onUnityAdsReady(String s) {
        if (UnityAds.isReady(placementID) && !isShowing && count == 0) {
            UnityAds.show(this);
            isShowing = true;
            count++;
        }
    }

    @Override
    public void onUnityAdsStart(String s) {
        isShowing = true;

    }

    @Override
    public void onUnityAdsFinish(String s, UnityAds.FinishState finishState) {
        isShowing = false;
        finish();

    }

    @Override
    public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
        isShowing = false;
        finish();
    }

    private void hideActionBar() {
//        // Hide UI
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }
    }



}
