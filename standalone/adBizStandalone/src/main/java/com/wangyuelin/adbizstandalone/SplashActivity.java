package com.wangyuelin.adbizstandalone;

import android.os.Handler;
import android.util.Log;

import com.wangyuelin.basebiz.BaseActivity;
import com.wangyuelin.basebiz.dialog.BaseDialog;
import com.wangyuelin.basebiz.dialog.ConfirmDialog;
import com.wangyuelin.basebiz.helper.DialogHelper;

/**
 * author : yuelinwang
 * time   : 2020-01-14 15:00
 * desc   : 启动页  全屏（不显示状态栏）
 */
public class SplashActivity extends BaseActivity implements BaseDialog.DismissListener {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
//        new Handler().postDelayed(() -> DialogHelper.showDialog(ConfirmDialog.newInstance("测试", "DialogFragment.show() will take care of adding the fragment"), getSupportFragmentManager(), "confirm"), 1000);
        new Handler().postDelayed(() -> ListActivity.start(getApplicationContext()), 1000);
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected boolean isUseTitleView() {
        return false;
    }

    @Override
    public void onDismiss() {
        Log.d("wyl", "ConfirmDialog onDismiss");
    }
}
