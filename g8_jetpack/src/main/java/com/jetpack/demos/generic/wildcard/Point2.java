package com.jetpack.demos.generic.wildcard;

/**
 * @author wzc
 * @date 2019/2/14
 */
public class Point2<T extends Number> {
    private T x;
    private T y;

    public Point2() {
    }

    public Point2(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }
}
