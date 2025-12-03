package com.personalplayground.staticintro;

public class StaticFieldDemo {

    public static void main(String[] args) {

        Plane.printGeneralInfo();

        System.out.println("=== Static Field Demo ===\n");

        Plane p1 = new Plane();
        Plane p2 = new Plane();

        // Board passengers
        p1.boardPassenger();
        p1.boardPassenger();
        p2.boardPassenger();

        Plane.resetTotalPassengers();
        System.out.println("Total passengers after reset: " + Plane.getTotalPassengers());


        System.out.println("Passengers on p1: " + p1.getPassengers());
        System.out.println("Passengers on p2: " + p2.getPassengers());

        // Static field accessed via class
        System.out.println("TOTAL passengers across all planes: " + Plane.getTotalPassengers());
    }
}
