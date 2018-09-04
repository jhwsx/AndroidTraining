package com.wzc.g6_app_resources;

/**
 * @author wzc
 * @date 2018/9/4
 */
public class LargeData {
    public String mName;
    public int mAge;

    public LargeData(String name, int age) {
        mName = name;
        mAge = age;
    }

    public String getName() {
        return mName == null ? "" : mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }

    @Override
    public String toString() {
        return "LargeData{" +
                "mName='" + mName + '\'' +
                ", mAge=" + mAge +
                '}';
    }
}
