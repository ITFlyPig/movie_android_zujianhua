package com.wangyuelin.adbiz.helper;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.wangyuelin.adbiz.R;

/**
 * 谷歌广告帮助类
 */
public class GoogleAdHelper {
    private InterstitialAd mInterstitialAd;
    private TemplateView mTemplateView;
    /**
     * 展示Banner广告
     * @param adContainer
     */
    public void showBannerAd(ViewGroup adContainer, AdListener adListener) {
        if (adContainer == null) {
            return;
        }
        AdView adView = (AdView) LayoutInflater.from(adContainer.getContext()).inflate(R.layout.v_adview, adContainer, false);
        adContainer.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        if (adListener != null) {
            adView.setAdListener(adListener);
        }
        adView.loadAd(adRequest);
    }

    /**
     * 准备插屏广告，一般只需要在每个需要展示的Activity的onCreate中调用一次就行了
     * @param context
     */
    public void loadInterstitialAd(Context context, AdListener adListener) {
        if (context == null) {
            return;
        }
        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(context);
        }
        if (adListener != null) {
            mInterstitialAd.setAdListener(adListener);
        }
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");//这个是测试广告单元id，发布的时候需要替换为正式的
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    /**
     * 插屏广告是否准备好了
     * @return
     */
    public boolean isInterstitialAdReady() {
        return  mInterstitialAd != null && mInterstitialAd.isLoaded();
    }

    /**
     * 展示插屏广告
     */
    public void showInterstitialAd() {
        if (mInterstitialAd != null) {
            mInterstitialAd.show();
        }
    }


    public void loadNativeAd(Context context,UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener, AdListener adListener) {
        loadNativeAd(context, 1, onUnifiedNativeAdLoadedListener, adListener);

    }

    /**
     * 加载原生广告
     * @param context
     * @param num
     * @param onUnifiedNativeAdLoadedListener
     * @param adListener
     */
    public void loadNativeAd(Context context, int num, UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener, AdListener adListener) {
        AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
                .forUnifiedNativeAd(onUnifiedNativeAdLoadedListener)//在这个回调里面展示广告
                .withAdListener(adListener)
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();
        //开始加载原生广告
        if (num == 1) {
            adLoader.loadAd(new AdRequest.Builder().build());
        } else if (num > 1) {
            adLoader.loadAds(new AdRequest.Builder().build(), num);
        }
    }

    /**
     * 将原生广告的信息填充到广告模板中，显示广告
     * @param container
     * @param nativeAd
     */
    public  void showNativeAd(ViewGroup container, UnifiedNativeAd nativeAd) {
        if (container == null || nativeAd == null) {
            return;
        }
        if (mTemplateView == null) {
            mTemplateView = (TemplateView) LayoutInflater.from(container.getContext()).inflate(R.layout.v_native_ad, container, false);
            container.addView(mTemplateView);
        }

        int whiteColor = container.getContext().getResources().getColor(R.color.white);
        NativeTemplateStyle styles = new NativeTemplateStyle.Builder().withMainBackgroundColor(new ColorDrawable(whiteColor)).build();
        mTemplateView.setStyles(styles);
        mTemplateView.setNativeAd(nativeAd);

    }

}
