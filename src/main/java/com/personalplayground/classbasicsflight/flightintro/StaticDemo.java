package com.personalplayground.classbasicsflight.flightintro;

public class StaticDemo {

    public static void main(String[] args) {

        System.out.println("Initial Flight count: " + Flight.getFlightCount());

        Flight f1 = new Flight(3);
        Flight f2 = new Flight(5);

        System.out.println("After creating flights: " + Flight.getFlightCount());

        // Static factory method
        Flight f3 = Flight.createWithDefaultPassengers(
                2,
                new Passenger("Mike", 1),
                new Passenger("Sarah", 2)
        );

        System.out.println("After creating f3: " + Flight.getFlightCount());

        // Use static utility
        System.out.println("\nTotal bags on f3: " + FlightUtils.totalBags(f3));
    }
}
