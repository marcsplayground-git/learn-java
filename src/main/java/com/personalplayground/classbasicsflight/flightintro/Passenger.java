package com.personalplayground.classbasicsflight.flightintro;

public class Passenger {

    private String name;
    private int bags;

    // Constructor
    public Passenger(String name, int bags) {
        this.name = name;
        this.bags = bags;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getBags() {
        return bags;
    }

    @Override
    public String toString() {
        return name + " (" + bags + " bags)";
    }
}
