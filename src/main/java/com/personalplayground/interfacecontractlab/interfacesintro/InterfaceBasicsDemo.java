package com.personalplayground.interfacecontractlab.interfacesintro;

public class InterfaceBasicsDemo {

    public static void main(String[] args) {

        System.out.println("=== Milestone 1: Basic Interface & Implementation ===\n");

        Passenger p1 = new Passenger("Alice", 3, 120);
        Passenger p2 = new Passenger("Bob", 1, 20);

        // Using concrete type
        System.out.println("Concrete type:");
        System.out.println(" - " + p1.getDescription());
        System.out.println(" - " + p2.getDescription());

        // Using interface type (polymorphism)
        Describable d1 = p1;
        Describable d2 = p2;

        System.out.println("\nVia interface reference (Describable):");
        System.out.println(" - " + d1.getDescription());
        System.out.println(" - " + d2.getDescription());

        // Array of Describable (can hold many implementing types later)
        Describable[] items = { p1, p2 };
        System.out.println("\nLooping over Describable[]:");
        for (Describable d : items) {
            System.out.println(" * " + d.getDescription());
        }

        System.out.println("\nNotes:");
        System.out.println(" - Describable is a contract: getDescription().");
        System.out.println(" - Passenger implements that contract.");
        System.out.println(" - We can store Passenger in a Describable variable.");
        System.out.println(" - Code using Describable doesn’t care about the concrete class.");
    }
}
