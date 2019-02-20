package com.jetpack.demos.generic.genericinterface;

/**
 * @author wzc
 * @date 2019/2/18
 */
public class Test {
    public static void main(String[] args) {
        InfoImpl0 wzc = new InfoImpl0("wzc");
        System.out.println(wzc.getVar());

        InfoImpl1<String> wzj = new InfoImpl1<>("wzj");
        System.out.println(wzj.getVar());
    }
}
