package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class ParameterBasicsDemo {

    public static void main(String[] args) {

        System.out.println("=== Milestone 1: Primitive vs Object Parameters ===\n");

        // 1) Primitive parameter behavior
        int x = 10;
        System.out.println("Before modifyPrimitive(x): x = " + x);
        modifyPrimitive(x);
        System.out.println("After modifyPrimitive(x):  x = " + x);

        System.out.println("\n-----------------------------\n");

        // 2) Object reference parameter behavior (reassignment, NOT mutation yet)
        NumberBox box = new NumberBox(10);
        System.out.println("Before reassignBoxRef(box): box = " + box);
        reassignBoxRef(box);
        System.out.println("After reassignBoxRef(box):  box = " + box);

        System.out.println("\nExplanation:");
        System.out.println(" - Java always passes parameters by value.");
        System.out.println(" - For primitives, the value itself is copied.");
        System.out.println(" - For objects, the REFERENCE value is copied.");
        System.out.println(" - Reassigning the parameter inside the method does NOT change the caller's variable.");
    }

    // -----------------------------
    // Primitive parameter
    // -----------------------------
    private static void modifyPrimitive(int value) {
        System.out.println("Inside modifyPrimitive: received value = " + value);
        value = 99;
        System.out.println("Inside modifyPrimitive: changed value to = " + value);
    }

    // -----------------------------
    // Object reference parameter (reassignment only)
    // -----------------------------
    private static void reassignBoxRef(NumberBox boxParam) {
        System.out.println("Inside reassignBoxRef: initial boxParam = " + boxParam);
        boxParam = new NumberBox(999);
        System.out.println("Inside reassignBoxRef: reassigned boxParam = " + boxParam);
        System.out.println("Inside reassignBoxRef: leaving method now...");
    }
}
