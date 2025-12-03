package com.personalplayground.enumlabs.enumintro;

public enum FlightCrewJob {

    // Enum constants with constructor arguments
    FLIGHT_ATTENDANT("Flight Attendant", 1),
    COPILOT("First Officer", 2),
    PILOT("Captain", 3);

    private final String title;
    private final int rank;

    // Constructor: ALWAYS private or package-private
    FlightCrewJob(String title, int rank) {
        this.title = title;
        this.rank = rank;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public int getRank() {
        return rank;
    }

    // Convenience method
    public boolean outranks(FlightCrewJob other) {
        return this.rank > other.rank;
    }
}
