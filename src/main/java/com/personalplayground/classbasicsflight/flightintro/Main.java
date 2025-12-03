package com.personalplayground.classbasicsflight.flightintro;

public class Main {
    public static void main(String[] args) {

        // Create Flight objects
        Flight f1 = new Flight(2);    // only 2 seats
        Flight f2 = new Flight();     // default constructor (150 seats)

        // Test adding passengers
        f1.add1Passenger();
        f1.add1Passenger();
        f1.add1Passenger(); // should say full

        // Test getters
        System.out.println("f1 passengers: " + f1.getPassengers());
        System.out.println("f1 seats: " + f1.getSeats());

        // Test setters
        f1.setSeats(-10);   // invalid
        f1.setPassengers(5); // invalid
        f1.setPassengers(1); // valid

        // Show final results
        System.out.println("After setter tests:");
        System.out.println("Passengers: " + f1.getPassengers());
        System.out.println("Seats: " + f1.getSeats());
        System.out.println("After setter tests:");
        System.out.println("Passengers: " + f2.getPassengers());
        System.out.println("Seats: " + f2.getSeats());
    }
}
