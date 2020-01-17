package com.wangyuelin.uicom;

import android.text.TextUtils;
import android.widget.Toast;

import io.github.prototypez.appjoint.commons.AppBase;

/**
 * author : yuelinwang
 * time   : 2020-01-17 10:16
 * desc   : Toast控件
 */
public class UToast {

    /**
     * Toast显示
     * @param content
     */
    public static void show(String content) {
        if (TextUtils.isEmpty(content)) {
            return;
        }
        Toast.makeText(AppBase.INSTANCE, content, Toast.LENGTH_SHORT).show();
    }
}
