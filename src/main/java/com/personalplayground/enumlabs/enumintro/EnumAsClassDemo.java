package com.personalplayground.enumlabs.enumintro;

public class EnumAsClassDemo {

    public static void main(String[] args) {

        System.out.println("=== Milestone 4: Enums as Mini-Classes ===\n");

        FlightCrewJob job = FlightCrewJob.PILOT;

        System.out.println("Job: " + job);
        System.out.println("Title: " + job.getTitle());
        System.out.println("Rank: " + job.getRank());

        System.out.println("\nDoes the PILOT outrank the COPILOT?");
        System.out.println("Answer: " +
                FlightCrewJob.PILOT.outranks(FlightCrewJob.COPILOT));

        System.out.println("\nShowing all jobs with titles and ranks:");
        for (FlightCrewJob j : FlightCrewJob.values()) {
            System.out.println(
                    j + " - " + j.getTitle() + " (rank=" + j.getRank() + ")"
            );
        }
    }
}
