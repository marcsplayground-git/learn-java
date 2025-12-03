package com.personalplayground.enumlabs.enumintro;

public class EnumComparisonDemo {

    public static void main(String[] args) {

        System.out.println("=== Milestone 2: Enum Comparisons & switch ===\n");

        FlightCrewJob job1 = FlightCrewJob.PILOT;
        FlightCrewJob job2 = FlightCrewJob.COPILOT;

        // --- 1. Comparing with '==' ---
        System.out.println("job1 == PILOT ? " + (job1 == FlightCrewJob.PILOT));
        System.out.println("job1 == job2 ? " + (job1 == job2));

        // --- 2. Using != ---
        System.out.println("job1 != job2 ? " + (job1 != job2));

        // --- 3. Using switch with enums ---
        System.out.println("\nSwitching on job1:");
        switch (job1) {
            case FLIGHT_ATTENDANT:
                System.out.println("Pilot must coordinate with attendants.");
                break;
            case COPILOT:
                System.out.println("Pilot must work closely with copilot.");
                break;
            case PILOT:
                System.out.println("Pilot is the captain in command!");
                break;
        }

        // --- 4. compareTo for relative ordering ---
        System.out.println("\nRelative ordering:");
        System.out.println("PILOT.compareTo(FLIGHT_ATTENDANT) = " +
                FlightCrewJob.PILOT.compareTo(FlightCrewJob.FLIGHT_ATTENDANT));

        System.out.println("FLIGHT_ATTENDANT.compareTo(FLIGHT_ATTENDANT) = " +
                FlightCrewJob.FLIGHT_ATTENDANT.compareTo(FlightCrewJob.FLIGHT_ATTENDANT));

        System.out.println("PILOT.compareTo(COPILOT) = " +
                FlightCrewJob.PILOT.compareTo(FlightCrewJob.COPILOT));

        System.out.println("COPILOT.compareTo(PILOT) = " +
                FlightCrewJob.COPILOT.compareTo(FlightCrewJob.PILOT));

        System.out.println("FLIGHT_ATTENDANT.compareTo(COPILOT) = " +
                FlightCrewJob.FLIGHT_ATTENDANT.compareTo(FlightCrewJob.COPILOT));

        System.out.println("\nNotes:");
        System.out.println(" - '==' is safe because each enum constant is a singleton.");
        System.out.println(" - switch on enums is type-safe and easy to read.");
        System.out.println(" - compareTo uses ordinal() ordering (0-based).");
    }
}
