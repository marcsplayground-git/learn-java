package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class PassengerCapstoneRunner {

    public static void main(String[] args) {

        System.out.println("=== Milestone 6: Mini Capstone ===\n");

        PassengerManager manager = new PassengerManager();

        // --- Simple overload ---
        manager.addPassenger("Alice");

        // --- Overload with bags ---
        manager.addPassenger("Bob", 2);

        // --- Passing object reference ---
        Passenger charlie = new Passenger("Charlie", 0);
        manager.addPassenger(charlie);

        // After method: Charlie should have 1 bag (mutated)
        System.out.println("Caller sees Charlie now: " + charlie + "\n");

        // --- Varargs version ---
        Passenger d1 = new Passenger("Dana", 1);
        Passenger d2 = new Passenger("Derek", 2);
        manager.addPassengers(d1, d2);

        System.out.println("Caller sees Dana: " + d1);
        System.out.println("Caller sees Derek: " + d2);
    }
}
