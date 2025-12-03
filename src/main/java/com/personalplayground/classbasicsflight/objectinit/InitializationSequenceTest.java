package com.personalplayground.classbasicsflight.objectinit;

public class InitializationSequenceTest {

    public static void main(String[] args) {

        System.out.println("=== FULL INITIALIZATION ORDER DEMO ===\n");

        System.out.println(">>> Creating obj1 with NO-ARG constructor\n");
        InitializationOrderDemo obj1 = new InitializationOrderDemo();
        obj1.printState();

        System.out.println("\n------------------------------------------------\n");

        System.out.println(">>> Creating obj2 with PARAMETERIZED constructor\n");
        InitializationOrderDemo obj2 = new InitializationOrderDemo(777);
        obj2.printState();
    }
}
