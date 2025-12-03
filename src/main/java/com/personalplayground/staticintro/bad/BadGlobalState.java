package com.personalplayground.staticintro.bad;

public class BadGlobalState {

    public static int counter = 0; // ❌ mutable static field

    public static void increment() {
        counter++;
    }
}
