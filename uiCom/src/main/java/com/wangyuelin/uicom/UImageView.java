package com.wangyuelin.uicom;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.wangyuelin.uimageloadertech.ImageLoader;

import java.io.File;

/**
 * author : yuelinwang
 * time   : 2020-01-08 14:25
 * desc   : 图片显示控件，主要包含：图片加载和图片形状
 */
public class UImageView extends AppCompatImageView {
    public UImageView(Context context) {
        this(context, null);
    }

    public UImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    public void setPath(String filePath) {
        if(TextUtils.isEmpty(filePath)) {
            return;
        }
        ImageLoader.with(this).load(filePath).into(this);

    }

    public void setFile(File file) {
        if (file == null) {
            return;
        }
        ImageLoader.with(this).load(file).into(this);

    }

    public void setUri(Uri uri) {
        if (uri == null) {
            return;
        }
        ImageLoader.with(this).load(uri).into(this);

    }

    public void setResource(int resourceId) {
        ImageLoader.with(this).load(resourceId).into(this);
    }


}
