package com.jetpack.demos.generic.intro;

/**
 * @author wzc
 * @date 2019/2/18
 */
public class ObjectPoint {
    private Object x;
    private Object y;

    public ObjectPoint() {
    }

    public ObjectPoint(Object x, Object y) {
        this.x = x;
        this.y = y;
    }

    public Object getX() {
        return x;
    }

    public void setX(Object x) {
        this.x = x;
    }

    public Object getY() {
        return y;
    }

    public void setY(Object y) {
        this.y = y;
    }
}
