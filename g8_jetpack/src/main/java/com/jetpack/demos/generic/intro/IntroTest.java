package com.jetpack.demos.generic.intro;

import java.util.ArrayList;

/**
 * @author wzc
 * @date 2019/2/18
 */
public class IntroTest {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<String>();
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        ArrayList<Double> doubleArrayList = new ArrayList<Double>();

        IntegerPoint integerPoint = new IntegerPoint(1, 2);
        DoublePoint doublePoint = new DoublePoint(2.1, 1.2);

        ObjectPoint ointegerPoint = new ObjectPoint();
        ointegerPoint.setX(new Integer(2));
        Object x = ointegerPoint.getX();
        Integer xint = (Integer) x;
        System.out.println(xint);

        ObjectPoint ofloatPoint = new ObjectPoint();
        ofloatPoint.setX(new Float(3.2f));
        Object f = ofloatPoint.getX();
        System.out.println(f.getClass());
        String ofloat = (String) f;
    }
}
