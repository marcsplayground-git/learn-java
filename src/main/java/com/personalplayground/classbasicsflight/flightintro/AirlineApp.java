package com.personalplayground.classbasicsflight.flightintro;

public class AirlineApp {

    public static void main(String[] args) {

        System.out.println("=== Airline Reservation System ===\n");

        // 1) Create flights
        Flight f1 = new Flight(3);
        Flight f2 = new Flight(2);

        // 2) Add passengers
        f1.addPassenger("Alice", 1);
        f1.addPassenger("Bob", 2);

        f2.addPassenger("Charlie", 0);
        f2.addPassenger("Diana", 1);

        // 3) Print manifests
        System.out.println("Initial Flight Manifests:");
        f1.printPassengerManifest();
        f2.printPassengerManifest();

        // 4) Show static counter
        System.out.println("\nTotal flights created: " + Flight.getFlightCount());

        // 5) Use FlightUtils
        System.out.println("Total bags on Flight 1: " + FlightUtils.totalBags(f1));
        System.out.println("Total bags on Flight 2: " + FlightUtils.totalBags(f2));

        // 6) Combine flights
        System.out.println("\nCombining flights...");
        Flight combined = f1.combineFlights(f2);

        // 7) Print combined flight
        System.out.println("\n=== Combined Flight Manifest ===");
        combined.printPassengerManifest();

        // 8) Show updated static count
        System.out.println("\nFlights created including combined: " + Flight.getFlightCount());
    }
}
