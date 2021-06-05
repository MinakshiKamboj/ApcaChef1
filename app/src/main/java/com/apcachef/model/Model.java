package com.apcachef.model;

public class Model {
    public static final int LEFT_VIEW = 0;
    public static final int RIGHT_VIEW = 1;

    public int viewPosition;

    public Model(int viewPosition) {
        this.viewPosition = viewPosition;
    }
}
