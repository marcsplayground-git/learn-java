package com.personalplayground.staticintro.bad;

public class BadGlobalStateDemo {

    public static void main(String[] args) {

        BadGlobalState.increment();
        BadGlobalState.increment();

        // Any other code anywhere in the program can mutate this
        System.out.println("Counter: " + BadGlobalState.counter);

        // Someone else doing this causes chaos:
        BadGlobalState.counter = -999; // ❌ absolutely terrible
    }
}
