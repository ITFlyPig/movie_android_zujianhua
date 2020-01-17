package com.wangyuelin.adbiz.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdListener;
import com.wangyuelin.adbiz.helper.GoogleAdHelper;
import com.wangyuelin.adbiz.R;
import com.wangyuelin.adbiz.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoogleAdActivity extends Activity {

    @BindView(R2.id.rl_banner)
    RelativeLayout rlBanner;
    @BindView(R2.id.rl_native_ad)
    RelativeLayout rlNativeAd;
    private GoogleAdHelper mGoogleAdHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_ad_activity);
        ButterKnife.bind(this);

//        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.loadAd(adRequest);

        mGoogleAdHelper = new GoogleAdHelper();

        //加载Banner广告
//        mGoogleAdHelper.showBannerAd(rlBanner, new AdListener() {
//            @Override
//            public void onAdFailedToLoad(int i) {
//                super.onAdFailedToLoad(i);
//                Log.d("wyl", "谷歌广告加载失败：" + i);
//            }
//
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                Log.d("wyl", "谷歌广告加载成功");
//            }
//        });

        //加载插屏广告
//        mGoogleAdHelper.loadInterstitialAd(this, new AdListener(){
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                if (mGoogleAdHelper.isInterstitialAdReady()) {
//                    mGoogleAdHelper.showInterstitialAd();
//                }
//            }
//        });

        mGoogleAdHelper.loadNativeAd(this, unifiedNativeAd -> {
            //显示Native广告
            mGoogleAdHelper.showNativeAd(rlNativeAd, unifiedNativeAd);

        }, new AdListener(){

        });

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, GoogleAdActivity.class);
        context.startActivity(intent);

    }
}
