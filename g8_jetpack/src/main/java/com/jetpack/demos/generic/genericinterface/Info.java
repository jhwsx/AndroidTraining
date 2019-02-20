package com.jetpack.demos.generic.genericinterface;

/**
 * @author wzc
 * @date 2019/2/13
 */
public interface Info<T> {
    T getVar();
    void setVar(T x);
}
