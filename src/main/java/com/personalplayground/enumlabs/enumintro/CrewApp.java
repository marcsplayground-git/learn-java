package com.personalplayground.enumlabs.enumintro;

import java.util.Scanner;

public class CrewApp {

    public static void main(String[] args) {

        System.out.println("=== Milestone 6: Enum Capstone ===\n");

        CrewMember alice = new CrewMember("Alice", FlightCrewJob.FLIGHT_ATTENDANT);
        CrewMember bob   = new CrewMember("Bob", FlightCrewJob.COPILOT);
        CrewMember carol = new CrewMember("Carol", FlightCrewJob.PILOT);

        System.out.println("Crew Members:");
        System.out.println(" - " + alice);
        System.out.println(" - " + bob);
        System.out.println(" - " + carol);

        // --- Compare ranks ---
        System.out.println("\nDoes Carol outrank Bob?");
        System.out.println("Answer: " + carol.getJob().outranks(bob.getJob()));

        // --- Use compareTo() ---
        System.out.println("\ncompareTo demo:");
        int c = FlightCrewJob.COPILOT.compareTo(FlightCrewJob.FLIGHT_ATTENDANT);
        System.out.println("COPILOT.compareTo(FLIGHT_ATTENDANT) = " + c);

        // --- List all crew job data ---
        System.out.println("\nAll crew job info:");
        for (FlightCrewJob job : FlightCrewJob.values()) {
            System.out.println(
                    job + " → " + job.getTitle() + " (rank=" + job.getRank() + ")"
            );
        }

        // --- Parse user input ---
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter a crew job (pilot, copilot, flight_attendant): ");
        String input = scanner.nextLine().trim().toUpperCase();

        try {
            FlightCrewJob chosen = FlightCrewJob.valueOf(input);
            System.out.println("You selected: " + chosen.getTitle());

            // Switch demo
            switch (chosen) {
                case PILOT:
                    System.out.println("Pilots command the aircraft.");
                    break;
                case COPILOT:
                    System.out.println("Copilots support the captain.");
                    break;
                case FLIGHT_ATTENDANT:
                    System.out.println("Flight attendants assist passengers.");
                    break;
            }

        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid job entered: " + input);
        }

        System.out.println("\nEnd of capstone.");
    }
}
