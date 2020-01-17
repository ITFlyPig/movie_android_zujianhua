package com.wangyuelin.basebiz.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wangyuelin.basebiz.R;
import com.wangyuelin.basebiz.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author : yuelinwang
 * time   : 2020-01-16 10:42
 * desc   : 确认对话框
 */
public class ConfirmDialog extends BaseDialog implements View.OnClickListener {

    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_ok)
    TextView tvOk;
    @BindView(R2.id.tv_cancel)
    TextView tvCancel;
    @BindView(R2.id.tv_content)
    TextView tvContent;

    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private ConfirmListener mConfirmListener; //不能放在budle里面存

    public static ConfirmDialog newInstance(String title, String content) {
        ConfirmDialog confirmDialog = new ConfirmDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        bundle.putString(CONTENT, content);
        confirmDialog.setArguments(bundle);
        return confirmDialog;
    }

    @Override
    protected View getContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_confirm, container, false);
        ButterKnife.bind(this, v);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString(TITLE);
            if (!TextUtils.isEmpty(title)) {
                tvTitle.setText(title);
            }
            String content = bundle.getString(CONTENT);
            if (!TextUtils.isEmpty(content)) {
                tvContent.setText(content);
            }
        }
        tvOk.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取Listener
        if (getParentFragment() instanceof ConfirmListener) {
            mConfirmListener = (ConfirmListener) getParentFragment();
        } else if (getActivity() instanceof ConfirmListener) {
            mConfirmListener = (ConfirmListener) getActivity();
        }

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_ok) {
            if (mConfirmListener != null) {
                mConfirmListener.onOk();
            }
            dismiss();
        } else if (v.getId() == R.id.tv_cancel) {
            if (mConfirmListener != null) {
                mConfirmListener.onCancel();
            }
            dismiss();
        }
    }


    public interface ConfirmListener {
        void onOk();

        void onCancel();
    }
}
