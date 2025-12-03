package com.personalplayground.enumlabs.enumintro;

import java.util.Scanner;

public class EnumValueParsingDemo {

    public static void main(String[] args) {

        System.out.println("=== Milestone 3: values() & valueOf() ===\n");

        // --- 1. List all constants using values() ---
        System.out.println("Available crew jobs:");
        for (FlightCrewJob job : FlightCrewJob.values()) {
            System.out.println(" - " + job);
        }

        // --- 2. Parsing user input ---
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter a crew job (pilot, copilot, flight_attendant): ");
        String input = scanner.nextLine();

        // Convert to uppercase to match enum naming
        String normalized = input.toUpperCase();

        try {
            FlightCrewJob chosen = FlightCrewJob.valueOf(normalized);
            System.out.println("You selected: " + chosen);

            // Optional: respond based on enum
            switch (chosen) {
                case PILOT:
                    System.out.println("Pilots command the aircraft.");
                    break;
                case COPILOT:
                    System.out.println("Copilots assist the pilot.");
                    break;
                case FLIGHT_ATTENDANT:
                    System.out.println("Flight attendants care for passengers.");
                    break;
            }

        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid job: '" + input + "'");
            System.out.println("Tip: Try one of these:");
            for (FlightCrewJob job : FlightCrewJob.values()) {
                System.out.println(" - " + job);
            }
        }

        System.out.println("\nNotes:");
        System.out.println(" - values() gives all enum constants in order.");
        System.out.println(" - valueOf(String) converts EXACT name to enum.");
        System.out.println("   (Case-sensitive unless you normalize input).");
        System.out.println(" - Use try/catch to handle invalid user input.");
    }
}
