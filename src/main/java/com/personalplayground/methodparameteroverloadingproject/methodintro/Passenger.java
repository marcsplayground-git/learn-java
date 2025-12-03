package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class Passenger {

    private String name;
    private int bags;

    public Passenger(String name) {
        this(name, 0);
    }

    public Passenger(String name, int bags) {
        this.name = name;
        this.bags = bags;
    }

    public void addBag() {
        bags++;
    }

    public int getBags() {
        return bags;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (bags=" + bags + ")";
    }
}
