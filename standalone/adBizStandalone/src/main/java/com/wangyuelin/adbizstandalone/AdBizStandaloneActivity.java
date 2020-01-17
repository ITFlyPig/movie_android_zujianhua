package com.wangyuelin.adbizstandalone;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.wangyuelin.adbiz.helper.UnityAdHelper;
import com.wangyuelin.uimageloadertech.BitmapLoadListener;
import com.wangyuelin.uimageloadertech.ImageLoader;
import com.wangyuelin.uimageloadertech.transformation.RoundTransformation;
import com.wangyuelin.utils.CacheUtil;
import com.wangyuelin.utils.PersistenceUtil;

import java.io.Serializable;

public class AdBizStandaloneActivity extends Activity {
    private UnityAdHelper mUnityAdHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_biz_standanline_activity);
        PersistenceUtil.initialize(this);
//        mUnityAdHelper =  new UnityAdHelper();
//        mUnityAdHelper.init(this, "3010931", null);
//        findViewById(R.id.tv).setOnClickListener(v -> {
////                if (mUnityAdHelper.isReady("rewardedVideo")) {
////                    mUnityAdHelper.show();
////                }
//
//            GoogleAdActivity.startActivity(AdBizStandaloneActivity.this);
//        });

        ImageView imageView = findViewById(R.id.imageview);
        imageView.setOnClickListener(v -> {
            ImageLoader.with(AdBizStandaloneActivity.this).load("https://gitee.com/CASE_CAI/img/raw/master/huanshimensheng.jpg").transform(new RoundTransformation()).into(imageView);
            ImageLoader.with(AdBizStandaloneActivity.this).load("https://gitee.com/CASE_CAI/img/raw/master/huanshimensheng.jpg").transform(new RoundTransformation()).submit(new BitmapLoadListener() {
                @Override
                public void onStart() {
                    Log.e("wyl", "开始下载Bitmap");

                }

                @Override
                public void onFailed(String errorMsg) {
                    Log.e("wyl", "Bitmap下载失败：" + errorMsg);

                }

                @Override
                public void onLoaded(Bitmap bitmap) {
                    Log.e("wyl", "Bitmap下载成功：" + bitmap.toString() + " 线程：" + Thread.currentThread().getName());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });

                }
            });

        });



//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 1000; i++) {
//            Stu stu = new Stu("name:" + i, i);
//            CacheUtil.saveObj(stu, "stu" + i);
//            Stu stu1 = CacheUtil.getObj("stu" + i);
//            Log.e("wyl", "获取到的学生：" + stu1.name + " : " + stu1.age);
//        }
//
//        Log.e("tt", "CacheUtil 耗时：" + (System.currentTimeMillis() - start)); //结果：1800左右

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            Stu stu = new Stu("name:" + i, i);
//            PersistenceUtil.save("stu" + i, stu);
            Stu stu1 = PersistenceUtil.getObj("stu" + i);
//            Log.e("wyl", "获取到的学生：" + stu1.name + " : " + stu1.age);
        }

        Log.e("tt", "PersistenceUtil 耗时：" + (System.currentTimeMillis() - start2)); //结果：800左右




    }

    private static class Stu implements Serializable{
        private String name;
        private int age;

        public Stu(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
