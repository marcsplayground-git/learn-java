package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class PassengerManager {

    // --- 1. Overload: add passenger by name ---
    public void addPassenger(String name) {
        System.out.println("addPassenger(String): " + name);
        Passenger p = new Passenger(name);
        processPassenger(p);
    }

    // --- 2. Overload: add passenger by name + bags (int) ---
    public void addPassenger(String name, int bags) {
        System.out.println("addPassenger(String, int): " + name + ", bags=" + bags);
        Passenger p = new Passenger(name, bags);
        processPassenger(p);
    }

    // --- 3. Overload: add passenger by Passenger object ---
    public void addPassenger(Passenger p) {
        System.out.println("addPassenger(Passenger): " + p);
        processPassenger(p);
    }

    // --- 4. Varargs ---
    public void addPassengers(Passenger... list) {
        System.out.println("addPassengers(Passenger...): count=" + list.length);
        for (Passenger p : list) {
            processPassenger(p);
        }
    }

    // --- 5. Helper: show mutation vs reassignment ---
    private void processPassenger(Passenger p) {

        System.out.println("  Before addBag(): " + p);

        // MUTATE object
        p.addBag();

        System.out.println("  After addBag() : " + p);

        // REASSIGN (does NOT affect caller)
        p = new Passenger("ReassignedGuy", 99);

        System.out.println("  After reassignment inside method: " + p);
        System.out.println();
    }
}
