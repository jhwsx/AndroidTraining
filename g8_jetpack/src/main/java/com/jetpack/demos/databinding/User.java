package com.jetpack.demos.databinding;

/**
 * @author wangzhichao
 * @since 2019/12/28
 */
public class User {
    public String firstName;
    public String lastName;
    public int age;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
