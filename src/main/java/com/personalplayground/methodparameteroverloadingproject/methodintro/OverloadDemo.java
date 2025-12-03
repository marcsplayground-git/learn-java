package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class OverloadDemo {

    // --- Overload #1: no parameters ---
    public static void print() {
        System.out.println("print() called");
    }

    // --- Overload #2: one int ---
    public static void print(int value) {
        System.out.println("print(int) called: " + value);
    }

    // --- Overload #3: one double ---
    public static void print(double value) {
        System.out.println("print(double) called: " + value);
    }

    // --- Overload #4: two parameters ---
    public static void print(int a, int b) {
        System.out.println("print(int, int) called: " + (a + b));
    }

    // --- Invalid: changing ONLY return type would fail ---
    // public static int print(int value) { return value; } // <-- COMPILE ERROR
}
