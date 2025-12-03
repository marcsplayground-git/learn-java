package com.personalplayground.interfacecontractlab.interfacesintro;

public class Passenger implements Describable, Comparable<Passenger> {

    private final String name;
    private final int memberLevel;
    private final int memberDays;

    public Passenger(String name, int memberLevel, int memberDays) {
        this.name = name;
        this.memberLevel = memberLevel;
        this.memberDays = memberDays;
    }

    public String getName() {
        return name;
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public int getMemberDays() {
        return memberDays;
    }

    // Implementation of the Describable contract
    @Override
    public String getDescription() {
        return "Passenger " + name +
                " (level " + memberLevel +
                ", days " + memberDays + ")";
    }

    @Override
    public String toString() {
        return getDescription();
    }

    @Override
    public int compareTo(Passenger other) {

        // Primary sort: higher memberLevel first
        int levelDiff = other.memberLevel - this.memberLevel;
        if (levelDiff != 0)
            return levelDiff;

        // Secondary sort: more memberDays first
        int daysDiff = other.memberDays - this.memberDays;
        if (daysDiff != 0)
            return daysDiff;

        // Tertiary: alphabetical by name
        return this.name.compareTo(other.name);
    }

}
