package com.personalplayground.classbasicsflight.flightintro;

public class FleetDemo {

    public static void main(String[] args) {

        // 1) Declare an array of Flight references (no objects yet)
        Flight[] fleet;

        // 2) Create the array object: space for 3 references (all null)
        fleet = new Flight[3];

        // At this point: fleet[0], fleet[1], fleet[2] are all null

        // 3) Initialize each element with actual Flight objects
        fleet[0] = createFlight(100);   // Flight with 100 seats
        fleet[1] = createFlight(150);   // Flight with 150 seats
        // Let's deliberately leave fleet[2] as null for now

        // 4) Use the flights (add passengers, print info)
        System.out.println("=== Initial fleet status ===");
        for (int i = 0; i < fleet.length; i++) {
            Flight flight = fleet[i];
            if (flight != null) {
                flight.add1Passenger();
                System.out.println("Flight at index " + i + " → passengers: "
                        + flight.getPassengers() + ", seats: " + flight.getSeats());
            } else {
                System.out.println("Flight at index " + i + " is null (not initialized yet).");
            }
        }

        // 5) Demonstrate reference behavior: aliasing inside arrays
        System.out.println("\n=== Reference behavior demo ===");
        fleet[2] = fleet[0];  // fleet[2] now refers to the SAME Flight as fleet[0]

        System.out.println("Adding 1 passenger via fleet[2]...");
        fleet[2].add1Passenger();

        System.out.println("Passengers via fleet[0]: " + fleet[0].getPassengers());
        System.out.println("Passengers via fleet[2]: " + fleet[2].getPassengers());

        // 6) Null safety demo
        System.out.println("\n=== Null safety demo ===");
        Flight maybeNull = null;

        if (maybeNull != null) {
            // This block will NOT run
            System.out.println("This will not print");
        } else {
            System.out.println("maybeNull is null, so we won't call methods on it.");
        }

        // If you uncomment the line below, it would cause a NullPointerException:
        // maybeNull.add1Passenger();
    }

    // Helper / factory method for creating flights
    private static Flight createFlight(int seats) {
        Flight flight = new Flight(seats);
        // Could add setup logic here if needed later
        return flight;
    }
}
