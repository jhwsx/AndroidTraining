package com.jetpack.demos.generic.genericclass;

/**
 * @author wzc
 * @date 2019/2/18
 */
public class Point<T> {
    private T x;
    private T y;

    public Point() {
    }

    public Point(T x, T y) {
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
