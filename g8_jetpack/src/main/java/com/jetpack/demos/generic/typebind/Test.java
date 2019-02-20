package com.jetpack.demos.generic.typebind;

/**
 * @author wzc
 * @date 2019/2/18
 */
public class Test {
    public static void main(String[] args) {
        StringCompare min = Compare.min(new StringCompare("123"),
                new StringCompare("1234"));
        System.out.println(min);

        System.out.println(FruitUtil.getFruitName(new Banana()));
        System.out.println(FruitUtil.getFruitName(new Apple()));
//        System.out.println(FruitUtil.getFruitName2(new Apple()));
        System.out.println(FruitUtil.getFruitName2(new PineApple()));
    }
}
