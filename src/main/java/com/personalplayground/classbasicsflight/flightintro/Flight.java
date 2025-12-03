package com.personalplayground.classbasicsflight.flightintro;

public class Flight {

    // ------------------------------
    // Fields (state)
    // Always private for encapsulation
    // ------------------------------
    private int passengers;
    private int seats;
    private Passenger[] passengerList;
    private static int flightCount = 0;

    // ------------------------------
    // Constructors
    // ------------------------------

    // Constructor = how to build a Flight object
    // Main constructor
    public Flight(int seats) {
        setSeats(seats); // use setter to ensure validation
        this.passengers = 0;
        this.passengerList = new Passenger[seats];
        flightCount++; // static counter
    }

    // Overloaded constructor (no-arg)
    public Flight() {
        this(150); // default seats = 150
    }

    // ------------------------------
    // Behavior (methods)
    // ------------------------------

    public void add1Passenger() {
        if (hasAvailableSeat()) {
            this.passengers++;
        } else {
            handleTooMany();
        }
    }

    public void addPassenger(Passenger passenger) {
        if (hasAvailableSeat()) {
            passengerList[passengers] = passenger;
            this.passengers++;
        } else {
            handleTooMany();
        }
    }

    public void addPassenger(String name, int bags) {
        Passenger p = new Passenger(name, bags);
        addPassenger(p); // reuse existing method
    }


    public void printPassengerManifest() {
        System.out.println("Passenger Manifest for Flight (" + passengers + "/" + seats + "):");

        for (int i = 0; i < passengers; i++) {
            System.out.println(" - " + passengerList[i]);
        }

        if (passengers == 0) {
            System.out.println("   <No passengers yet>");
        }
    }


    // Private helper method
    private boolean hasAvailableSeat() {
        return passengers < seats;
    }

    // Private helper method for consistent error handling
    private void handleTooMany() {
        System.out.println("Cannot add passenger: flight is full ("
                + passengers + "/" + seats + ")");
    }

    // ------------------------------
    // Getters (accessors)
    // ------------------------------

    // Simple getters (we'll talk more about getters/setters next milestones)
    public int getPassengers() {
        return passengers;
    }

    public Passenger getPassenger(int index) {
        if (index < 0 || index >= passengers) {
            return null; // safe failure
        }
        return passengerList[index];
    }

    public int getSeats() {
        return seats;
    }

    public static int getFlightCount() {
        return flightCount;
    }


    // ------------------------------
    // Setters (mutators)
    // ------------------------------

    public static Flight createWithDefaultPassengers(int seats, Passenger... passengers) {
        Flight f = new Flight(seats);
        for (Passenger p : passengers) {
            f.addPassenger(p);
        }
        return f;
    }

    public Flight combineFlights(Flight other) {

        // Create a new Flight with combined seat count
        Flight newFlight = new Flight(this.seats + other.seats);

        // Move all passengers from this flight
        for (int i = 0; i < this.passengers; i++) {
            newFlight.addPassenger(this.passengerList[i]);
        }

        // Move all passengers from the other flight
        for (int i = 0; i < other.passengers; i++) {
            newFlight.addPassenger(other.passengerList[i]);
        }

        return newFlight; // return a new object reference
    }


    public void setSeats(int seats) {
        if (seats < 0) {
            System.out.println("Invalid seat count: cannot be negative.");
            return;
        }
        this.seats = seats;
    }

    public void setPassengers(int passengers) {
        if (passengers < 0) {
            System.out.println("Invalid passenger count: cannot be negative.");
            return;
        }
        if (passengers > seats) {
            System.out.println("Invalid passenger count: cannot exceed available seats.");
            return;
        }
        this.passengers = passengers;
    }
}
