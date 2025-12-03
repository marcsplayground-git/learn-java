package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class OverloadRunner {

    public static void main(String[] args) {

        System.out.println("=== Milestone 3: Method Overloading ===\n");

        OverloadDemo.print();
        OverloadDemo.print(10);
        OverloadDemo.print(3.14);
        OverloadDemo.print(4, 5);

        // Widening example:
        short s = 7;
        OverloadDemo.print(s);   // short → int (widening)
    }
}
