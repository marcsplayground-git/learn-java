package com.personalplayground.interfacecontractlab.interfacesintro;

public class IterableDemo {

    public static void main(String[] args) {

        System.out.println("=== Milestone 3: Iterable & Hidden Collections ===\n");

        Passenger p1 = new Passenger("Alice", 3, 120);
        Passenger p2 = new Passenger("Bob", 1, 20);
        Passenger p3 = new Passenger("Charlie", 2, 50);

        Flight flight = new Flight();
        flight.addPassenger(p1);
        flight.addPassenger(p2);
        flight.addPassenger(p3);

        System.out.println(flight);

        // --- For-each using Iterable ---
        System.out.println("\nListing all passengers via for-each:");
        for (Passenger p : flight) {
            System.out.println(" - " + p.getDescription());
        }

        System.out.println("\nNotes:");
        System.out.println(" - Flight implements Iterable<Passenger>.");
        System.out.println(" - for-each calls flight.iterator() behind the scenes.");
        System.out.println(" - We safely expose iteration without exposing passengerList.");
    }
}
