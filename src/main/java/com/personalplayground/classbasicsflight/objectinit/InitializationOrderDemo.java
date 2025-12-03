package com.personalplayground.classbasicsflight.objectinit;

public class InitializationOrderDemo {

    // ------------------------------------------------------
    // 1. Fields (default values come first)
    // ------------------------------------------------------
    private int a;                 // default: 0
    private String b;              // default: null
    private boolean c;             // default: false

    // ------------------------------------------------------
    // 2. Field Initializers
    // ------------------------------------------------------
    private int x = initX();       // runs BEFORE constructor
    private String y = "Field initializer Y";
    private boolean z = true;

    // ------------------------------------------------------
    // 3. Instance Initializer Blocks
    // ------------------------------------------------------
    {
        System.out.println("Instance Init Block #1 running...");
        initBlock1Value = 100;
    }

    {
        System.out.println("Instance Init Block #2 running...");
        initBlock2Value = 200;
    }

    private int initBlock1Value;
    private int initBlock2Value;

    // ------------------------------------------------------
    // 4. Constructors (will run LAST)
    // ------------------------------------------------------

    public InitializationOrderDemo() {
        System.out.println("NO-ARG constructor running...");
        constructorValue = 999;
    }

    public InitializationOrderDemo(int a) {
        this(); // chains to no-arg constructor
        System.out.println("Parameterized constructor (int a) running...");
        this.a = a;
    }

    private int constructorValue;

    // ------------------------------------------------------
    // Helper method for field initializer
    // ------------------------------------------------------
    private int initX() {
        System.out.println("Field initializer for X is running...");
        return 42;
    }

    // ------------------------------------------------------
    // Print final state
    // ------------------------------------------------------
    public void printState() {
        System.out.println("\nFinal State After Initialization:");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("z = " + z);
        System.out.println("initBlock1Value = " + initBlock1Value);
        System.out.println("initBlock2Value = " + initBlock2Value);
        System.out.println("constructorValue = " + constructorValue);
        System.out.println();
    }
}
