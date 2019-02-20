package com.jetpack.demos.generic.genericinterface;


/**
 * @author wzc
 * @date 2019/2/13
 */
public class InfoImpl2<T,U,V> implements Info<T> {
    private T var;
    private U mU;
    private V mV;

    public InfoImpl2(T var) {
        setVar(var);
    }

    @Override
    public T getVar() {
        return var;
    }

    @Override
    public void setVar(T x) {
        var = x;
    }
}
