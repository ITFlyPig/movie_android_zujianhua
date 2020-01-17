package com.wangyuelin.uimageloadertech;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.request.FutureTarget;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.wangyuelin.uimageloadertech.transformation.CircleTransformation;
import com.wangyuelin.uimageloadertech.transformation.RoundTransformation;
import com.wangyuelin.uimageloadertech.transformation.TransformationItf;
import com.wangyuelin.utils.ThreadHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageLoader {
    private String path;
    private File file;
    private Uri uri;
    private int resourceId = -1;
    private ImageView target;
    private Context context;
    private View view;
    private Fragment fragment;
    private FragmentActivity fragmentActivity;
    private Activity activity;
    private int placeholder = -1;  //加载中占位图
    private int error = -1;  //加载失败占位图
    private String tag;
    private int w;//图片的宽
    private int h;//图片的高
    private TransformationItf[] transforms; //只起标记作用，实际的变化得具体写
    private ILoader loader;
    private BitmapLoadListener bitmapLoadListener;


    /**
     * 只有自己才能构造
     */
    private ImageLoader() {
        loader = new GlideLoader();

    }

    /////////////////////////设置属性start////////////////////////////////
    public ImageLoader load(String path) {
        this.path = path;
        return this;
    }

    public ImageLoader load(File file) {
        this.file = file;
        return this;
    }

    public ImageLoader load(Uri uri) {
        this.uri = uri;
        return this;
    }

    public ImageLoader load(int resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ImageLoader reSize(int w, int h) {
        this.w = w;
        this.h = h;
        return this;
    }

    public ImageLoader transform(TransformationItf... transforms) {
        this.transforms = transforms;
        return this;
    }

    public ImageLoader placeholder(@DrawableRes int resourceId) {
        this.placeholder = resourceId;
        return this;
    }

    public ImageLoader error(@DrawableRes int resourceId) {
        this.error = resourceId;
        return this;
    }

    /////////////////////////设置属性end////////////////////////////////


    /////////////////////////真正的加载start////////////////////////////////

    public void into(ImageView target) {
        this.target = target;
        load();
    }

    /**
     * 加载Biamtp，回调是是在子线程，因为：这样能很好满足在主线程、子线程需要Bitmap的情况
     * @param listener
     */
    public void submit(@NonNull BitmapLoadListener listener) {
        bitmapLoadListener = listener;
        load();
    }

    /////////////////////////真正的加载end////////////////////////////////


    /////////////////////////这里为第一个调用的方法////////////////////////////////
    public static ImageLoader with(Context context) {
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.context = context;
        return imageLoader;
    }

    public static ImageLoader with(View view) {
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.view = view;
        return imageLoader;
    }

    public static ImageLoader with(Activity activity) {
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.activity = activity;
        return imageLoader;
    }

    public static ImageLoader with(FragmentActivity fragmentActivity) {
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.fragmentActivity = fragmentActivity;
        return imageLoader;
    }

    public static ImageLoader with(Fragment fragment) {
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.fragment = fragment;
        return imageLoader;
    }
    /////////////////////////////////////////////////////////

    private void load() {
        loader.load(this);
    }


    /**
     * Glide实现的图片下载器
     */
    private static class GlideLoader implements ILoader {
        private RequestManager mRequestManager;
        private RequestBuilder<Drawable> mRequestBuilderDrawable;
        private RequestBuilder<Bitmap> mRequestBuilderBitmap;

        @Override
        public void load(final ImageLoader imageLoader) {
            if (imageLoader == null) {
                return;
            }
            if (imageLoader.context != null) {
                mRequestManager = Glide.with(imageLoader.context);
            } else if (imageLoader.view != null) {
                mRequestManager = Glide.with(imageLoader.view);
            } else if (imageLoader.fragment != null) {
                mRequestManager = Glide.with(imageLoader.fragment);
            } else if (imageLoader.fragmentActivity != null) {
                mRequestManager = Glide.with(imageLoader.fragmentActivity);
            } else if (imageLoader.activity != null) {
                mRequestManager = Glide.with(imageLoader.activity);
            }

            if (mRequestManager == null) {
                throw new IllegalArgumentException("Context不能为空");
            }

            if (imageLoader.bitmapLoadListener != null) {//加载Bitmap
                mRequestBuilderBitmap = mRequestManager.asBitmap();

                if (!TextUtils.isEmpty(imageLoader.path)) {
                    mRequestBuilderBitmap = mRequestBuilderBitmap.load(imageLoader.path);
                } else if (imageLoader.file != null) {
                    mRequestBuilderBitmap = mRequestBuilderBitmap.load(imageLoader.file);
                } else if (imageLoader.uri != null) {
                    mRequestBuilderBitmap = mRequestBuilderBitmap.load(imageLoader.uri);
                } else if (imageLoader.resourceId != -1) {
                    mRequestBuilderBitmap = mRequestBuilderBitmap.load(imageLoader.resourceId);
                }
                if (mRequestBuilderBitmap == null) {
                    throw new IllegalArgumentException("加载参数不能为空");
                }

                Transformation<Bitmap>[] transforms = getTransforms(imageLoader);
                if (transforms != null) {
                    mRequestBuilderBitmap = mRequestBuilderBitmap.transform(transforms);
                }

                final FutureTarget<Bitmap> futureTarget;
                if (imageLoader.w > 0 && imageLoader.h > 0) {
                    futureTarget = mRequestBuilderBitmap.submit(imageLoader.w, imageLoader.h);
                } else {
                    futureTarget = mRequestBuilderBitmap.submit();
                }

                //后台线程加载Bitmap
                ThreadHelper.getInstance().submit(new Runnable() {
                    @Override
                    public void run() {
                        imageLoader.bitmapLoadListener.onStart();
                        int result = 0;
                        String msg = "";
                        Bitmap bitmap = null;
                        try {
                            bitmap = futureTarget.get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                            result = -1;
                            msg = e.getLocalizedMessage();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            result = -1;
                            msg = e.getLocalizedMessage();
                        } finally {
                            if (result == 0) {
                                imageLoader.bitmapLoadListener.onLoaded(bitmap);
                            } else {
                                imageLoader.bitmapLoadListener.onFailed(msg);
                            }
                        }
                    }
                });


            } else {
                if (!TextUtils.isEmpty(imageLoader.path)) {
                    mRequestBuilderDrawable = mRequestManager.load(imageLoader.path);
                } else if (imageLoader.file != null) {
                    mRequestBuilderDrawable = mRequestManager.load(imageLoader.file);
                } else if (imageLoader.uri != null) {
                    mRequestBuilderDrawable = mRequestManager.load(imageLoader.uri);
                } else if (imageLoader.resourceId != -1) {
                    mRequestBuilderDrawable = mRequestManager.load(imageLoader.resourceId);
                }
                if (mRequestBuilderDrawable == null) {
                    throw new IllegalArgumentException("加载参数不能为空");
                }
                if (imageLoader.w > 0 && imageLoader.h > 0) {
                    mRequestBuilderDrawable = mRequestBuilderDrawable.override(imageLoader.w, imageLoader.h);
                }

                Transformation<Bitmap>[] transforms = getTransforms(imageLoader);
                if (transforms != null) {
                    mRequestBuilderDrawable = mRequestBuilderDrawable.transform(transforms);
                }

                if (imageLoader.placeholder != -1) {
                    mRequestBuilderDrawable = mRequestBuilderDrawable.placeholder(imageLoader.placeholder);
                }
                if (imageLoader.error != -1) {
                    mRequestBuilderDrawable = mRequestBuilderDrawable.error(imageLoader.error);
                }

                if (imageLoader.target != null) {
                    mRequestBuilderDrawable.into(imageLoader.target);
                }
            }

        }

        /**
         * 获得变换的集合
         *
         * @param imageLoader
         * @return
         */
        private Transformation<Bitmap>[] getTransforms(ImageLoader imageLoader) {
            if (imageLoader.transforms != null && imageLoader.transforms.length > 0) {
                ArrayList<Transformation<Bitmap>> transformList = new ArrayList<>();

                for (TransformationItf transform : imageLoader.transforms) {
                    if (transform instanceof RoundTransformation) {//圆角的变换
                        RoundTransformation roundTransformation = (RoundTransformation) transform;
                        RoundedCornersTransformation roundedCornersTransformation = parse(roundTransformation);
                        if (roundedCornersTransformation != null) {
                            transformList.add(roundedCornersTransformation);
                        }
                    } else if (transform instanceof CircleTransformation) {
                        CircleTransformation circleTransformation = (CircleTransformation) transform;
                        int borderSize = circleTransformation.getBorderSize();
                        int borderColor = -circleTransformation.getBorderColor();

                        if (borderSize > 0 && borderColor != -1) {//有边框的圆
                            transformList.add(new CropCircleWithBorderTransformation(borderSize, borderColor));
                        } else {//没有边框的圆
                            if (mRequestBuilderDrawable != null) {
                                mRequestBuilderDrawable = mRequestBuilderDrawable.circleCrop();
                            } else if (mRequestBuilderBitmap != null) {
                                mRequestBuilderBitmap = mRequestBuilderBitmap.circleCrop();
                            }
                        }
                    }
                }
                int len = transformList.size();
                if (len > 0) {
                    Transformation[] transformArray = new Transformation[len];
                    for (int i = 0; i < len; i++) {
                        transformArray[i] = transformList.get(i);
                    }
                    return transformArray;
                }
            }
            return null;
        }

        private void loadBitmap(RequestBuilder<Drawable> requestBuilder, BitmapLoadListener listener) {
            if (requestBuilder == null) {
                if (listener != null) {
                    listener.onFailed("请求参数：RequestBuilder 为null");
                }
                return;
            }


        }

        /**
         * 将用户定义的圆角转为Glide专有的圆角
         *
         * @param roundTransformation
         * @return
         */
        private RoundedCornersTransformation parse(RoundTransformation roundTransformation) {
            if (roundTransformation == null) {
                return null;
            }
            RoundedCornersTransformation.CornerType cornerType = RoundedCornersTransformation.CornerType.ALL;
            RoundedCornersTransformation.CornerType[] cornerTypes = RoundedCornersTransformation.CornerType.values();
            for (RoundedCornersTransformation.CornerType type : cornerTypes) {
                if (type.ordinal() == roundTransformation.getCorner().ordinal()) {
                    cornerType = type;
                    break;
                }
            }

            return new RoundedCornersTransformation(roundTransformation.getRadius(), roundTransformation.getMargin(), cornerType);
        }


    }


    /**
     * Picasso实现的图片加载器
     */
    private static class PicassoLoader implements ILoader {
        private Picasso mPicasso;
        private RequestCreator mRequestCreator;

        @Override
        public void load(ImageLoader imageLoader) {
            if (imageLoader == null) {
                return;
            }
            mPicasso = Picasso.get();
            if (!TextUtils.isEmpty(imageLoader.path)) {
                mRequestCreator = mPicasso.load(imageLoader.path);
            } else if (imageLoader.file != null) {
                mRequestCreator = mPicasso.load(imageLoader.file);
            } else if (imageLoader.uri != null) {
                mRequestCreator = mPicasso.load(imageLoader.uri);
            } else if (imageLoader.resourceId != -1) {
                mRequestCreator = mPicasso.load(imageLoader.resourceId);
            }

            if (mRequestCreator == null) {
                throw new IllegalArgumentException("加载参数不能为空");
            }
            if (imageLoader.w > 0 && imageLoader.h > 0) {
                mRequestCreator = mRequestCreator.resize(imageLoader.w, imageLoader.h);
            }
            if (imageLoader.transforms != null) {
                //TODO:待实现

            }
            if (imageLoader.target != null) {
                mRequestCreator.into(imageLoader.target);
            }
        }
    }


}
