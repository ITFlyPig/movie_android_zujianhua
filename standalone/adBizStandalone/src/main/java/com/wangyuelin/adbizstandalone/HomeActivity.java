package com.wangyuelin.adbizstandalone;

import android.content.Context;
import android.content.Intent;

import com.wangyuelin.basebiz.BaseActivity;

/**
 * author : yuelinwang
 * time   : 2020-01-14 15:20
 * desc   : 沉浸式全屏（显示状态栏）
 */
public class HomeActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected boolean isUseTitleView() {
        return false;
    }

    public static void start(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
