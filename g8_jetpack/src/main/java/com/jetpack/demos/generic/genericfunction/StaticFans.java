package com.jetpack.demos.generic.genericfunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2019/2/13
 */
public class StaticFans {
    public static <T> void staticMethod(T t) {
        System.out.println("staticMethod: t=" + t);
    }

    public <T> void ordinaryMethod(T t) {
        System.out.println("ordinaryMethod: t=" + t);
    }

    public static <T> List<T> staticMethod2(T t){
        ArrayList<T> arrayList = new ArrayList<>();
        arrayList.add(t);
        return arrayList;
    }

    // 使用 Class<T> 传递泛型类 class 对象
    public static <T> T getObject(Class<T> t) {
        try {
            return t.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 泛型数组
    public static <E> E[] fun1(E...e) {
        return e;
    }

}
