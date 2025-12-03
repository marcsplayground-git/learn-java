package com.personalplayground.enumlabs.enumintro;

public class EnumBasicsDemo {

    public static void main(String[] args) {

        System.out.println("=== Milestone 1: Enum Basics ===\n");

        // 1. Declare a variable of enum type
        FlightCrewJob job;

        // 2. Assign a constant
        job = FlightCrewJob.PILOT;

        System.out.println("Current job: " + job);

        // 3. Show that it's type-safe: cannot assign random String/int
        // job = "PILOT";     // ❌ illegal
        // job = 1;           // ❌ illegal

        // 4. Show the ordinal
        System.out.println("Ordinal of " + job + " = " + job.ordinal());

        // 5. Try another constant
        job = FlightCrewJob.FLIGHT_ATTENDANT;
        System.out.println("\nChanged job: " + job);
        System.out.println("Ordinal of " + job + " = " + job.ordinal());

        // 6. Print all constants
        System.out.println("\nAll FlightCrewJob constants:");
        for (FlightCrewJob j : FlightCrewJob.values()) {
            System.out.println(" - " + j + " (ordinal=" + j.ordinal() + ")");
        }

        System.out.println("\nNotes:");
        System.out.println(" - FlightCrewJob is an enum type.");
        System.out.println(" - Each constant (PILOT, COPILOT, etc.) is a singleton instance.");
        System.out.println(" - ordinal() gives the 0-based position in the declaration.");
    }
}
