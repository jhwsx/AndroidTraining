package com.jetpack.demos.generic.genericinterface;


/**
 * 这是一个泛型类, 使用泛型类来实现泛型接口，这样可以让用户来定义所使用的变量类型，更加灵活。
 * @author wzc
 * @date 2019/2/13
 */
public class InfoImpl1<T> implements Info<T> {
    private T var;

    public InfoImpl1(T var) {
        this.var = var;
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
