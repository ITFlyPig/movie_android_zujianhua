package com.wangyuelin.uimageloadertech.transformation;

/**
 * 圆角的变换
 */
public class RoundTransformation implements TransformationItf {
    private int radius;
    private int margin;
    private Corner corner;

    public RoundTransformation(int radius, int margin, Corner corner) {
        this.radius = radius;
        this.margin = margin;
        this.corner = corner;
    }

    public RoundTransformation(int radius, int margin) {
        this(radius, margin, Corner.ALL);

    }

    public RoundTransformation(int radius) {
        this(radius, 0, Corner.ALL);
    }

    public RoundTransformation() {
        this(20, 0, Corner.ALL);
    }

    public int getRadius() {
        return radius;
    }

    public int getMargin() {
        return margin;
    }

    public Corner getCorner() {
        return corner;
    }

    public enum Corner {
        ALL,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT,
        TOP, BOTTOM, LEFT, RIGHT,
        OTHER_TOP_LEFT, OTHER_TOP_RIGHT, OTHER_BOTTOM_LEFT, OTHER_BOTTOM_RIGHT,
    }
}
