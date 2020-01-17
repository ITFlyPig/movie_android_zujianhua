package com.wangyuelin.uimageloadertech.transformation;

/**
 * 圆角图片的裁剪
 */
public class CircleTransformation implements TransformationItf {

    private int borderSize;
    private int borderColor = -1;

    public CircleTransformation() {
    }

    public CircleTransformation(int borderSize, int borderColor) {
        this.borderSize = borderSize;
        this.borderColor = borderColor;
    }

    public int getBorderSize() {
        return borderSize;
    }

    public int getBorderColor() {
        return borderColor;
    }
}
