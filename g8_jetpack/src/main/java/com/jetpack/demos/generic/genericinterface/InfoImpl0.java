package com.jetpack.demos.generic.genericinterface;


/**
 * 这不是一个泛型类
 * @author wzc
 * @date 2019/2/13
 */
public class InfoImpl0 implements Info<String> {
    private String var;

    public InfoImpl0(String var) {
        this.var = var;
    }

    @Override
    public String getVar() {
        return var;
    }

    @Override
    public void setVar(String x) {
        var = x;
    }
}
