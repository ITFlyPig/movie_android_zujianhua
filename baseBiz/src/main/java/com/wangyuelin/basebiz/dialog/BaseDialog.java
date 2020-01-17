package com.wangyuelin.basebiz.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.wangyuelin.basebiz.R;

/**
 * author : yuelinwang
 * time   : 2020-01-16 10:19
 * desc   : Dialog的base类
 * <p>
 * <p>
 * <p>
 * 在 DialogFragment中，就算我们重写了onCreateView,并在布局中设定了根布局为wrap_content。这是没有用的，因为 container 是为空的，手动设定LayoutParams 也是没有用的。
 * 原因是 dialog 会根据自己的 Window Params 重新设置 ViewRoot 大小，最好的办法是这样设置：
 * dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
 */
public abstract class BaseDialog extends DialogFragment {

    private DismissListener mDismissListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullScreenNormalDialog);

    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        if (window != null) {
            //onCreateView返回的View，DialogFragment 会根据自己的 Window Params 重新设置 ViewRoot 大小。
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //窗口背景，使用系统自带的背景周边会存在padding，替换为自己的后宽度可以全屏
//            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setGravity(layoutGravity());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FrameLayout whole = (FrameLayout) inflater.inflate(R.layout.dialog_base, container, false);
        View content = getContentView(inflater, whole, savedInstanceState);
        if (content != null) {
            whole.addView(content);
        }
        return whole;

    }

    /**
     * 获取dialog的内容区域
     *
     * @return
     */
    protected abstract View getContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 决定内容区域位置
     *
     * @return
     */
    protected int layoutGravity() {
        return Gravity.CENTER;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getParentFragment() instanceof DismissListener) {
            mDismissListener = (DismissListener) getParentFragment();
        } else if (getActivity() instanceof DismissListener) {
            mDismissListener = (DismissListener) getActivity();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
    }

    public interface DismissListener {
        void onDismiss();
    }
}
