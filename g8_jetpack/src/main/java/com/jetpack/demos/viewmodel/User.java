package com.jetpack.demos.viewmodel;

/**
 * @author wzc
 * @date 2019/2/16
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
