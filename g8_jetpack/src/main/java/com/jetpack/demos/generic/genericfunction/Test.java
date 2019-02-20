package com.jetpack.demos.generic.genericfunction;

/**
 * @author wzc
 * @date 2019/2/18
 */
public class Test {
    public static void main(String[] args) {
        StaticFans.<String>staticMethod("wzc");
        StaticFans staticFans = new StaticFans();
        staticFans.<Integer>ordinaryMethod(new Integer(18));
        StaticFans.staticMethod2("wzc");
        String object = StaticFans.getObject(String.class);
        Integer[] integers = StaticFans.fun1(1, 2, 3, 4);
        System.out.println(integers);
    }
}
