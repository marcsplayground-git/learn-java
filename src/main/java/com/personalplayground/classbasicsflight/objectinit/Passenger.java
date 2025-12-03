package com.personalplayground.classbasicsflight.objectinit;

public class Passenger {

    // -------------------------
    // Field initializers
    // -------------------------

    // numeric, with initializer
    private int freeBags = 1;

    private double baseFee = 50.0;

    // initializer using an expression
    private double totalFee = baseFee * 1.1; // 10% tax

    // boolean initializer
    private boolean vip = true;

    // initializer calling a method
    private String name = generateDefaultName();

    // initializer depending on another field
    private int points = freeBags * 10;

    // char initializer
    private char seatLetter = 'A';

    // used by initialization blocks
    private String temporaryId;       // will be set in init block #1
    private boolean initializedViaBlock; // true will be set in init block #2


    // ------------------------------------------------------
    // Initialization Blocks (INSTANCE INITIALIZERS)
    // ------------------------------------------------------

    // First initializer block
    {
        System.out.println("Running INIT BLOCK #1...");
        temporaryId = "TEMP-" + Math.random();
    }

    // Second initializer block
    {
        System.out.println("Running INIT BLOCK #2...");
        initializedViaBlock = true;
    }

    // ------------------------------------------------------
    // Master constructor (PRIVATE) - centralizes shared logic
    // ------------------------------------------------------
    private Passenger(double perBagFee) {
        System.out.println("Running PRIVATE master constructor (perBagFee)...");
        this.baseFee = perBagFee;
        this.totalFee = baseFee * 1.1; // recompute total fee after override
    }

    public Passenger() {
        this(50.0);  // calls the private master constructor
        System.out.println("Running NO-ARG constructor...");
        // name & freeBags come from initializers
    }

    public Passenger(String name, int freeBags) {
        this(50.0);  // call master
        System.out.println("Running constructor (name, freeBags)...");
        this.name = name;
        this.freeBags = freeBags;
    }

    public Passenger(boolean vip) {
        this(75.0);  // VIP passengers may have different fee rules
        System.out.println("Running constructor (vip only)...");
        this.vip = vip;
    }

    // -------------------------
    // Helper for initializer
    // -------------------------
    private String generateDefaultName() {
        return "Unnamed Passenger";
    }

    // -------------------------
    // Print current state
    // -------------------------
    public void printState() {
        System.out.println("Passenger state after field initializers:");
        System.out.println("  name       = " + name);
        System.out.println("  freeBags   = " + freeBags);
        System.out.println("  baseFee    = " + baseFee);
        System.out.println("  totalFee   = " + totalFee);
        System.out.println("  vip        = " + vip);
        System.out.println("  points     = " + points);
        System.out.println("  seatLetter = " + seatLetter);
        System.out.println();
        System.out.println("  temporaryId        = " + temporaryId);
        System.out.println("  initializedViaBlock = " + initializedViaBlock);
    }
}
