package com.personalplayground.classbasicsflight.objectinit;

public class InitBlockDemo {

    public static void main(String[] args) {

        System.out.println("=== Instance Initialization Block Demo ===\n");

        System.out.println("Creating Passenger p1 (no-arg constructor)...");
        Passenger p1 = new Passenger();
        p1.printState();

        System.out.println("\nCreating Passenger p2 (with name, freeBags)...");
        Passenger p2 = new Passenger("Alice", 3);
        p2.printState();
    }
}
