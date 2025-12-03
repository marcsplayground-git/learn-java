package com.personalplayground.classbasicsflight.flightintro;

public class CombineDemo {

    public static void main(String[] args) {

        Flight f1 = new Flight(2);
        Flight f2 = new Flight(3);

        f1.addPassenger("Alice", 1);
        f1.addPassenger("Bob", 2);

        f2.addPassenger("Charlie", 0);
        f2.addPassenger("Diana", 1);

        System.out.println("=== Before Combining ===");

        f1.printPassengerManifest();
        f2.printPassengerManifest();

        Flight combined = f1.combineFlights(f2);

        System.out.println("\n=== After Combining (New Flight) ===");
        combined.printPassengerManifest();

        System.out.println("\nOriginal flights remain unchanged:");
        f1.printPassengerManifest();
        f2.printPassengerManifest();
    }
}
