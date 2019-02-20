package com.jetpack.demos.generic.typebind;

/**
 * @author wzc
 * @date 2019/2/14
 */
public class Fruit {
    private String name;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
