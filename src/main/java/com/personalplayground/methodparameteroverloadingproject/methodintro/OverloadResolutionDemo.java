package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class OverloadResolutionDemo {

    // --- Exact match ---
    public static void test(int x) {
        System.out.println("test(int): " + x);
    }

    // --- Widening ---
    public static void test(long x) {
        System.out.println("test(long): " + x);
    }

    // --- Boxing ---
    public static void test(Integer x) {
        System.out.println("test(Integer): " + x);
    }

    // --- Varargs ---
    public static void test(int... values) {
        System.out.print("test(int...): ");
        for (int v : values) System.out.print(v + " ");
        System.out.println();
    }
}
