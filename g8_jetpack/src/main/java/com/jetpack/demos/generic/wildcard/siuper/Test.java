package com.jetpack.demos.generic.wildcard.siuper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wzc
 * @date 2019/2/14
 */
public class Test {
    public static void main(String[] args) {
        List<? super Manager> list;
        list = new ArrayList<Employee>();
        list = new ArrayList<Manager>();
//        list = new ArrayList<CEO>();

        List<? super Manager> list2 = new ArrayList<Employee>();
//        list2.add(new Employee());
        list2.add(new Manager());
        list2.add(new CEO());
        Object object = list.get(0);
//        Employee employee = list.get(0);

    }
}
