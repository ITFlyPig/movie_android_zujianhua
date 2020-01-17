package com.wangyuelin.uimageloadertech;

import android.graphics.Bitmap;

/**
 * author : yuelinwang
 * date : 2020-01-07 10:59
 * description : Bitmap加载监听
 */
public interface BitmapLoadListener {
    void onStart();                  //开始
    void onFailed(String errorMsg);  //失败
    void onLoaded(Bitmap bitmap);   //加载完成
}

