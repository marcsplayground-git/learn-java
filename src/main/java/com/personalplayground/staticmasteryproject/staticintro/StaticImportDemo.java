package com.personalplayground.staticmasteryproject.staticintro;

// STATIC IMPORTS
import static com.personalplayground.staticmasteryproject.staticintro.Plane.getCompanyName;
import static com.personalplayground.staticmasteryproject.staticintro.Plane.getMaxPassengersPerPlane;
import static com.personalplayground.staticmasteryproject.staticintro.Plane.resetTotalPassengers;

public class StaticImportDemo {

    public static void main(String[] args) {

        System.out.println("=== Static Import Demo ===\n");

        // We call the static methods directly without class qualification
        System.out.println("Company: " + getCompanyName());
        System.out.println("Max passengers: " + getMaxPassengersPerPlane());

        resetTotalPassengers(); // direct call

        System.out.println("\nNo Plane. prefix needed!");
    }
}
