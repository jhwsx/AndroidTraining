package com.jetpack.demos.generic.typebind;

import java.io.Serializable;

/**
 * 类型绑定之绑定类的例子
 *
 * @author wzc
 * @date 2019/2/14
 */
public class FruitUtil {
    public static <T extends Fruit> String getFruitName(T t) {
        return t.getName();
    }

    public static <T extends Fruit & Serializable> String getFruitName2(T t) {
        return t.getName();
    }
}
