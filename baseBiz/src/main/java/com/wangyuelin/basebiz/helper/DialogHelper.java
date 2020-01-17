package com.wangyuelin.basebiz.helper;

import android.text.TextUtils;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * author : yuelinwang
 * time   : 2020-01-16 17:10
 * desc   : Dialog的帮助类
 */
public class DialogHelper {
    /**
     * 显示Dialog
     * @param dialogFragment
     * @param fragmentManager
     * @param tag
     */
    public static void showDialog(DialogFragment dialogFragment, FragmentManager fragmentManager, String tag) {
        if (dialogFragment == null || fragmentManager == null) {
            return;
        }
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (!TextUtils.isEmpty(tag)) {
            Fragment preFragment = fragmentManager.findFragmentByTag(tag);
            if (preFragment != null) {
                ft.remove(preFragment);
            }
        }
        ft.addToBackStack(null);
        dialogFragment.show(ft, tag);
    }

}
