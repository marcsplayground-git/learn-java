package com.personalplayground.classbasicsflight.objectinit;

public class ConstructorDemo {

    public static void main(String[] args) {

        System.out.println("=== Constructor Demo ===\n");

        // 1. No-arg constructor
        System.out.println("Creating Passenger p1 with NO-ARG constructor...");
        Passenger p1 = new Passenger();
        p1.printState();

        // 2. Parameterized constructor (name, freeBags)
        System.out.println("Creating Passenger p2 with (name, freeBags) constructor...");
        Passenger p2 = new Passenger("Alice", 3);
        p2.printState();

        // 3. Parameterized constructor (vip flag)
        System.out.println("Creating Passenger p3 with (vip) constructor...");
        Passenger p3 = new Passenger(false);
        p3.printState();

        System.out.println("Note: Each constructor runs AFTER field initializers have already executed.");
    }
}
