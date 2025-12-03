package com.personalplayground.classbasicsflight.objectinit;

public class ConstructorChainingDemo {

    public static void main(String[] args) {

        System.out.println("=== Constructor Chaining Demo ===\n");

        System.out.println("1) new Passenger() ---------------");
        Passenger p1 = new Passenger();
        p1.printState();

        System.out.println("2) new Passenger(\"Alice\", 2) ---");
        Passenger p2 = new Passenger("Alice", 2);
        p2.printState();

        System.out.println("3) new Passenger(true) -----------");
        Passenger p3 = new Passenger(true);
        p3.printState();
    }
}
