package com.personalplayground.staticintro;

public final class StringUtils {

    // private constructor prevents instantiation
    private StringUtils() {}

    // pure utility method
    public static String capitalize(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    // another utility method
    public static String repeat(String s, int times) {
        return s.repeat(times);
    }
}
