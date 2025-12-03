package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class VarargsRunner {

    public static void main(String[] args) {

        System.out.println("=== Milestone 5: Varargs ===\n");

        // 1. Sum with varargs
        System.out.println("sum(1, 2, 3) = " + VarargsDemo.sum(1, 2, 3));
        System.out.println("sum() = " + VarargsDemo.sum());

        // 2. String lists
        VarargsDemo.printList("apple", "banana", "orange");
        VarargsDemo.printList();  // zero arguments

        // 3. Regular param + varargs
        VarargsDemo.printWithTitle("Fruits", "apple", "banana");
        VarargsDemo.printWithTitle("Empty List");

        // 4. Overloading with varargs
        System.out.println("\n-- Overloading behavior --");
        VarargsDemo.demo(5);        // exact match for demo(int)
        VarargsDemo.demo(5, 6, 7);  // varargs version
    }
}
