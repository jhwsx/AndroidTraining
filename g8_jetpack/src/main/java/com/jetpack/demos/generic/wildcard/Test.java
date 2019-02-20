package com.jetpack.demos.generic.wildcard;

/**
 * @author wzc
 * @date 2019/2/14
 */
public class Test {
    public static void main(String[] args) {
        Point<Integer> integerPoint = new Point<Integer>(4, 4);
        Point<Float> floatPoint = new Point<>(4.5f, 4.5f);
        Point<Double> doublePoint = new Point<>(4.3, 4.7);
        Point<Long> longPoint = new Point<>(1000L, 100L);

        Point<?> point;
        point = new Point<Integer>(4, 4);
        point = new Point<Float>(4.5f, 4.5f);
        point = new Point<Double>(4.3, 4.7);
        point = new Point<Long>(1000L, 100L);
        point = new Point<String>();
        point = new Point<Object>();

        Point<? extends Number> point2;
        point2 = new Point<Integer>(4, 4);
        point2 = new Point<Float>(4.5f, 4.5f);
        point2 = new Point<Double>(4.3, 4.7);
        point2 = new Point<Long>(1000L, 100L);
        point2 = new Point<Number>();
//        point2 = new Point<String>();
//        point2 = new Point<Object>();

        Point2<? extends Number> point3;
        point3 = new Point2<Integer>(4, 4);
        Number x = point3.getX();
//        point3.setX(new Integer(3));
        point3 = new Point2<Float>(4.5f, 4.5f);
        point3 = new Point2<Double>(4.3, 4.7);
        point3 = new Point2<Long>(1000L, 100L);
        point3 = new Point2<Number>();


//        point2 = new Point<String>();
//        point2 = new Point<Object>();


    }
}
