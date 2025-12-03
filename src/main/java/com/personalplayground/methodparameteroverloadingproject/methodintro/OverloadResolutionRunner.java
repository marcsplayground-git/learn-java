package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class OverloadResolutionRunner {

    public static void main(String[] args) {

        System.out.println("\n\n=== Milestone 4: Overload Resolution ===\n");

        System.out.println("-- Exact Match --");
        OverloadResolutionDemo.test(10);    // int → exact

        System.out.println("\n-- Widening --");
        short s = 5;
        OverloadResolutionDemo.test(s);     // short → int → exact match to test(int)

        System.out.println("\n-- Boxing --");
        OverloadResolutionDemo.test(Integer.valueOf(20)); // calls test(Integer)

        System.out.println("\n-- Varargs --");
        OverloadResolutionDemo.test();      // no exact match → varargs
        OverloadResolutionDemo.test(1, 2, 3);

        System.out.println("\n-- Automatic Conversion Preference --");
        OverloadResolutionDemo.test(3);     // chooses test(int)

        System.out.println("\n-- Which wins? int vs long vs Integer vs varargs --");
        byte b = 5;
        OverloadResolutionDemo.test(b);     // byte → short → int (widening wins)

        System.out.println("\nRules:");
        System.out.println("1. Exact match");
        System.out.println("2. Widening primitive");
        System.out.println("3. Boxing");
        System.out.println("4. Varargs (last resort)");
    }
}
