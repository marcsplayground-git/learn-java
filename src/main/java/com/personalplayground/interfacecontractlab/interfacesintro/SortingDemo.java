package com.personalplayground.interfacecontractlab.interfacesintro;

import java.util.Arrays;

public class SortingDemo {

    public static void main(String[] args) {

        System.out.println("=== Milestone 2: Comparable & Sorting ===\n");

        Passenger[] passengers = {
                new Passenger("Bob",   1,  10),
                new Passenger("Alice", 3, 120),
                new Passenger("Charlie", 3,  85),
                new Passenger("David", 2,  50)
        };

        System.out.println("Before sorting:");
        for (Passenger p : passengers) {
            System.out.println(" - " + p);
        }

        Arrays.sort(passengers);

        System.out.println("\nAfter sorting (natural order):");
        for (Passenger p : passengers) {
            System.out.println(" - " + p);
        }

        System.out.println("\nNotes:");
        System.out.println(" - Higher memberLevel comes first.");
        System.out.println(" - If same level, more memberDays comes first.");
        System.out.println(" - If both same, alphabetical name.");
        System.out.println(" - Arrays.sort() uses compareTo() under the hood.");
    }
}
