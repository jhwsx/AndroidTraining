package com.jetpack.demos.generic.typebind;

/**
 * 类型绑定之绑定接口例子
 * @author wzc
 * @date 2019/2/13
 */
public class Compare {

    public static <T extends Comparable> T min(T...t){
        T smallest = t[0];
        for (T t1 : t) {
            if (smallest.compareTo(t1)) {
                smallest = t1;
            }
        }
        return smallest;
    }
}

/**
 * 总结：
 * 1，类型绑定：<T extends BoundingType>
 *     表示 T 是 BoundingType 的子类型，而不是说继承 BoundingType
 *     T 和 BoundingType 可以是类，接口
 */
