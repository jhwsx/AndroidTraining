package com.jetpack.demos.generic.typebind;

public class StringCompare implements Comparable<StringCompare> {
    private String mString;

    public StringCompare(String string) {
        mString = string;
    }

    @Override
    public boolean compareTo(StringCompare r) {
        return mString.length() > r.mString.length();
    }

    @Override
    public String toString() {
        return "StringCompare{" +
                "mString='" + mString + '\'' +
                '}';
    }
}