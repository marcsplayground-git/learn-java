package com.personalplayground.enumlabs.enumintro;

public class CrewMember {

    private final String name;
    private final FlightCrewJob job;

    public CrewMember(String name, FlightCrewJob job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public FlightCrewJob getJob() {
        return job;
    }

    @Override
    public String toString() {
        return name + " - " + job.getTitle() + " (rank " + job.getRank() + ")";
    }
}
