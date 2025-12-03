package com.personalplayground.staticmasteryproject.staticintro;

public class StaticInitializerDemo {

    public static void main(String[] args) {

        System.out.println("=== Static Initializer Demo ===");

        // No Plane objects created yet
        System.out.println("Company Name: " + Plane.getCompanyName());
        System.out.println("Max Passengers: " + Plane.getMaxPassengersPerPlane());

        System.out.println("\nCreating first Plane object...");
        Plane p = new Plane();

        System.out.println("\nCreating second Plane object...");
        Plane p2 = new Plane();
    }
}
