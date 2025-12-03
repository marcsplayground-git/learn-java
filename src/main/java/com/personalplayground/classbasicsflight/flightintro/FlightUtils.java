package com.personalplayground.classbasicsflight.flightintro;

public final class FlightUtils {

    private FlightUtils() {
        // Prevent instantiation
    }

    public static int totalBags(Flight flight) {
        int sum = 0;

        for (int i = 0; i < flight.getPassengers(); i++) {
            Passenger p = flight.getPassenger(i);
            if (p != null) {
                sum += p.getBags();
            }
        }

        return sum;
    }
}
