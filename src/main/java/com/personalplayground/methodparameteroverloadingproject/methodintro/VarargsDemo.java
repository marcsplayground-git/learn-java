package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class VarargsDemo {

    // --- 1. Simple varargs: sum a list of ints ---
    public static int sum(int... values) {
        int total = 0;
        for (int v : values) {
            total += v;
        }
        return total;
    }

    // --- 2. Varargs with zero arguments ---
    public static void printList(String... items) {
        System.out.print("Items: ");
        for (String s : items) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    // --- 3. Regular parameter + varargs (varargs MUST be last) ---
    public static void printWithTitle(String title, String... items) {
        System.out.print(title + ": ");
        for (String s : items) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    // --- Illegal examples (explain only) ---
    // public static void wrongMethod(String... items, int num) {}  // ❌ varargs must be last
    // public static void duplicateVarargs(int... a, int... b) {}   // ❌ only ONE varargs allowed

    // --- 4. Overload interaction ---
    public static void demo(int x) {
        System.out.println("demo(int): " + x);
    }

    public static void demo(int... values) {
        System.out.println("demo(int...): count=" + values.length);
    }
}
