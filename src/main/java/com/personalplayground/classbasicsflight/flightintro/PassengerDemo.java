package com.personalplayground.classbasicsflight.flightintro;

public class PassengerDemo {

    public static void main(String[] args) {

        // Create a flight with 3 seats
        Flight flight = new Flight(3);

        // Create passengers
        Passenger p1 = new Passenger("Alice", 2);
        Passenger p2 = new Passenger("Bob", 1);
        Passenger p3 = new Passenger("Charlie", 0);
        Passenger p4 = new Passenger("Diana", 1); // extra passenger

        // Add them
        flight.addPassenger(p1);
        flight.addPassenger(p2);
        flight.addPassenger(p3);

        // Flight is full now, this should print a warning
        flight.addPassenger(p4);

        // Print manifest
        flight.printPassengerManifest();
    }
}
