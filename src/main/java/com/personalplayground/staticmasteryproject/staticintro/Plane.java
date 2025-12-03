package com.personalplayground.staticmasteryproject.staticintro;

public class Plane {

    // -----------------------------
    // Instance field (per object)
    // -----------------------------
    private int passengers = 0;

    // -----------------------------
    // Static field (shared)
    // -----------------------------
    private static int totalPassengers = 0;

    // ------------------------------------------------------
    // Static Fields (class-level constants & settings)
    // ------------------------------------------------------
    private static int maxPassengersPerPlane;
    private static final String COMPANY_NAME;

    // ------------------------------------------------------
    // Static Initialization Block
    // Runs once when the class loads
    // ------------------------------------------------------
    static {
        System.out.println("Static initializer running...");

        maxPassengersPerPlane = 180;

        // Example: load constant only once
        COMPANY_NAME = "Global Air";

        System.out.println("Static initialization complete.");
    }


    // Constructor
    public Plane() {
        System.out.println("Plane created.");
    }

    // Instance method: adds passenger to THIS plane
    public void boardPassenger() {
        passengers++;
        totalPassengers++; // updates class-wide count
    }

    // Getters
    public int getPassengers() {
        return passengers;
    }

    public static int getTotalPassengers() {
        return totalPassengers;
    }

    public static void printGeneralInfo() {
        System.out.println("A plane carries passengers.");
    }

    public static void resetTotalPassengers() {
        System.out.println("Resetting total passengers...");
        totalPassengers = 0;
    }

    public void printPlaneSummary() {
        System.out.println("Plane summary:");
        System.out.println("  This plane's passengers: " + passengers);
        System.out.println("  Total passengers (all planes): " + totalPassengers);
    }

    public static int getMaxPassengersPerPlane() {
        return maxPassengersPerPlane;
    }

    public static String getCompanyName() {
        return COMPANY_NAME;
    }


}
