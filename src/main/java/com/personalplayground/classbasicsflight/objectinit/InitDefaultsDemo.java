package com.personalplayground.classbasicsflight.objectinit;

public class InitDefaultsDemo {

    public static void main(String[] args) {

        System.out.println("=== Field Initializer Demo ===\n");

        Passenger p = new Passenger();

        p.printState();

        System.out.println("Expected behavior:");
        System.out.println("  • Field initializers override Java default values.");
        System.out.println("  • Expressions using other fields run AFTER defaults but BEFORE constructor.");
        System.out.println("  • Method calls inside initializers run before the constructor body.");
    }
}
